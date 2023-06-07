// Loïc Bertrand
// Clock of clocks with p5.js
// Inspired by this video : https://www.youtube.com/watch?v=ExkVIQ60ClM

class Clock {

  constructor(x = 0, y = 0) {
    this.corner = createVector(x, y);
    this.center = this.corner.copy().add(scl / 2, scl / 2);
    this.hoursAngle = 0;
    this.minAngle = PI;
    this.hoursSize = scl * .42;
    this.minSize = scl * .37;
  }

  show() {
    fill(darkTheme ? 0 : 255);
    noStroke();
    ellipse(this.corner.x, this.corner.y, scl, scl);
    stroke(darkTheme ? 255 : 0);
    strokeWeight(scl / 10);
    line(
      this.center.x,
      this.center.y,
      this.center.x + this.hoursSize * cos(this.hoursAngle),
      this.center.y + this.hoursSize * sin(this.hoursAngle));
    line(
      this.center.x,
      this.center.y,
      this.center.x + this.minSize * cos(this.minAngle),
      this.center.y + this.minSize * sin(this.minAngle));
  }

  set(h, m, amount = 1) {
    if (frameCount > this.corner.x / scl) {
      this.hoursAngle = lerp(this.hoursAngle, h * HALF_PI, amount);
      this.minAngle = lerp(this.minAngle, m * HALF_PI, amount);
    }
  }

}
