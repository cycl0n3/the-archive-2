// Loïc Bertrand
// Clock of clocks with p5.js
// Inspired by this video : https://www.youtube.com/watch?v=ExkVIQ60ClM

class Column {

  constructor(x = 0, y = 0) {
    this.corner = createVector(x, y);
    this.clocks = [];
    for (let j = 0; j < 6; j++) {
      for (let i = 0; i < 2; i++) {
        this.clocks.push(new Clock(x + i * scl, y + j * scl));
      }
    }
  }

  update(amount) {
    this.clocks[2].set(TLC.min, TLC.hours, amount);
    this.clocks[3].set(TRC.min, TRC.hours, amount);
    this.clocks[4].set(BLC.min, BLC.hours, amount);
    this.clocks[5].set(BRC.min, BRC.hours, amount);
    this.clocks[6].set(TLC.min, TLC.hours, amount);
    this.clocks[7].set(TRC.min, TRC.hours, amount);
    this.clocks[8].set(BLC.min, BLC.hours, amount);
    this.clocks[9].set(BRC.min, BRC.hours, amount);
  }

  show() {
    for (const c of this.clocks) {
      c.show();
    }
  }

}
