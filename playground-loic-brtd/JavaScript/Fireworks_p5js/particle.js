class Particle {
  
  constructor(x, y) {
    this.radius = random(8, 16);
    this.lifespan = 0;
    this.color = random(colors);
    this.pos = createVector(x, y);
    this.vel = createVector();
    this.acc = p5.Vector.random2D();
    this.acc.setMag(random(1, 8));
  }
  
  show() {
    fill(this.color);
    noStroke();
    ellipse(this.pos.x, this.pos.y, this.radius);
  }
  
  evolve() {
    this.lifespan++;
    this.radius -= this.lifespan * 0.01;
    this.acc.add(gravity);
    this.vel.add(this.acc);
    this.pos.add(this.vel);
    this.acc.setMag(0);
  }
  
  isDone() {
    return this.radius <= 0.01;
  }
  
}