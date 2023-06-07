class Firework {
  
  constructor(x, y) {
    this.particles = [];
    for (let i = 0; i < 80; i++) {
      this.particles.push(new Particle(x, y));
    }
  }
  
  show() {
    for (let p of this.particles) {
      p.show();
    }
  }
  
  evolve() {
    for (let p of this.particles) {
      p.evolve();
    }
    this.particles = this.particles.filter(p => !p.isDone());
  }
  
  isDone() {
    return this.particles.length == 0;
  }
  
}