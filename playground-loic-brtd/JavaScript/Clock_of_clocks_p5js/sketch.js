// Loïc Bertrand
// Clock of clocks with p5.js
// Inspired by this video : https://www.youtube.com/watch?v=ExkVIQ60ClM

const initCount = 60; // Frames of initialization
const smoothness = 0.2; // Clock hands movement speed

let scl; // Size of one clock
let clock; // Big digital clock
let darkTheme = false;

function setup() {
  scl = windowWidth / 40;
  createCanvas(scl * 34, scl * 6 + 2).parent('canvas-holder');
  frameRate(30);
  ellipseMode(CORNER);
  clock = new DigitalClock(0, 0);
  const checkbox = document.querySelector('#theme');
  checkbox.addEventListener('change', toggleDarkTheme);
  toggleDarkTheme();
}

function draw() {
  clear();
  clock.show();
  clock.update(smoothness);
}

function toggleDarkTheme() {
  darkTheme = !darkTheme;
  document.body.style.backgroundColor = darkTheme ? '#111' : '#eee';
  const metaThemeColor = document.querySelector("meta[name=theme-color]");
  metaThemeColor.setAttribute("content", darkTheme ? '#111' : '#eee');
}
