const express = require('express');
const session = require('express-session');
const mongoSessionStore = require('connect-mongo');

const next = require('next');
const mongoose = require('mongoose');
const compression = require('compression');
const helmet = require('helmet');

const setupGoogle = require('./google');
const { setupGithub } = require('./github');
const api = require('./api');

const logger = require('./logger');
// const { insertTemplates } = require('./models/EmailTemplate');
const routesWithSlug = require('./routesWithSlug');
const getRootUrl = require('../lib/api/getRootUrl');
const setupSitemapAndRobots = require('./sitemapAndRobots');
const { stripeCheckoutCallback } = require('./stripe');

require('dotenv').config();

const dev = process.env.NODE_ENV !== 'production';
const MONGO_URL = dev ? process.env.MONGO_URL_TEST : process.env.MONGO_URL;

(async () => {
  try {
    mongoose.set('strictQuery', false);
    await mongoose.connect(MONGO_URL);
    logger.info('connected to db');

    // async tasks, for ex, inserting email templates to db
    // logger.info('finished async tasks');
  } catch (err) {
    logger.error(`error: ${err}`);
  }
})();

const port = process.env.PORT || 8000;
const ROOT_URL = getRootUrl();

const URL_MAP = {
  '/login': '/public/login',
  '/my-books': '/customer/my-books',
};

const app = next({ dev });
const handle = app.getRequestHandler();

app.prepare().then(async () => {
  const server = express();

  server.use(helmet({ contentSecurityPolicy: false, crossOriginEmbedderPolicy: false }));
  server.use(compression());

  server.use(express.json());

  // give all Nextjs's request to Nextjs server
  server.get('/_next/*', (req, res) => {
    handle(req, res);
  });

  const sessionOptions = {
    name: process.env.SESSION_NAME,
    secret: process.env.SESSION_SECRET,
    store: mongoSessionStore.create({
      mongoUrl: MONGO_URL,
      ttl: 14 * 24 * 60 * 60, // save session 14 days
    }),
    resave: false,
    saveUninitialized: false,
    cookie: {
      httpOnly: true,
      maxAge: 14 * 24 * 60 * 60 * 1000, // expires in 14 days
      domain: dev ? 'localhost' : process.env.COOKIE_DOMAIN,
    },
  };

  if (!dev) {
    server.set('trust proxy', 1); // sets req.hostname, req.ip
    sessionOptions.cookie.secure = true; // sets cookie over HTTPS only
  }

  const sessionMiddleware = session(sessionOptions);
  server.use(sessionMiddleware);

  // await insertTemplates();

  setupGoogle({ server, ROOT_URL });
  setupGithub({ server, ROOT_URL });
  api(server);
  routesWithSlug({ server, app });

  stripeCheckoutCallback({ server });

  setupSitemapAndRobots({ server });

  server.get('*', (req, res) => {
    const url = URL_MAP[req.path];
    if (url) {
      app.render(req, res, url);
    } else {
      handle(req, res);
    }
  });

  server.listen(port, (err) => {
    if (err) throw err;
    logger.info(`> Ready on ${ROOT_URL}, ${dev}`);
  });
});
