// Loïc Bertrand
// Clock of clocks with p5.js
// Inspired by this video : https://www.youtube.com/watch?v=ExkVIQ60ClM

class DigitalClock {

  constructor(x = 0, y = 0) {
    // Time digits
    this.digits = [];
    this.digits.push(new Digit(x + scl * 0, y));
    this.digits.push(new Digit(x + scl * 5, y));
    this.digits.push(new Digit(x + scl * 12, y));
    this.digits.push(new Digit(x + scl * 17, y));
    this.digits.push(new Digit(x + scl * 24, y));
    this.digits.push(new Digit(x + scl * 29, y));
    // Columns
    this.columns = [];
    this.columns.push(new Column(x + scl * 10, y));
    this.columns.push(new Column(x + scl * 22, y));
  }

  update(amount = 1) {
    const d = new Date();
    const h = d.getHours();
    const m = d.getMinutes();
    const s = d.getSeconds();
    // Update hours
    if (frameCount < initCount || (m === 0 && s <= 1)) {
      this.digits[0].set(floor(h / 10), amount);
      this.digits[1].set(h % 10, amount);
    }
    // Update minutes
    if (frameCount < initCount || s <= 1) {
      this.digits[2].set(floor(m / 10), amount);
      this.digits[3].set(m % 10, amount);
    }
    // Update seconds
    const secUnitsDigit = s % 10;
    if (frameCount < initCount || secUnitsDigit <= 1) {
      this.digits[4].set(floor(s / 10), amount);
    }
    this.digits[5].set(secUnitsDigit, amount);
    // Update columns
    if (frameCount < initCount) {
      for (const c of this.columns) {
        c.update(amount);
      }
    }
  }

  show() {
    for (const d of this.digits) {
      d.show();
    }
    for (const c of this.columns) {
      c.show();
    }
  }

}
