
const colors = ['#F44336', '#9C27B0', '#FFC107', '#00BCD4', '#8BC34A'];
const gravity = new p5.Vector(0, 0.1);

let fireworks = [];

function setup() {
  createCanvas(windowWidth, windowHeight);
  background(0);
}

function draw() {
  background(0, 80);
  for (let f of fireworks) {
    f.evolve();
    f.show();
  }
  fireworks = fireworks.filter(f => !f.isDone());
}

function mousePressed() {
  if (fireworks.length < 10) {
    fireworks.push(new Firework(mouseX, mouseY));
  }
}