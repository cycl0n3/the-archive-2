const MIN_SIZE = 2;
const MAX_SIZE = 10;

const Move = {
  UP: {
    x: 0,
    y: -1,
    desc: "UP"
  },
  RIGHT: {
    x: +1,
    y: 0,
    desc: "RIGHT"
  },
  DOWN: {
    x: 0,
    y: +1,
    desc: "DOWN"
  },
  LEFT: {
    x: -1,
    y: 0,
    desc: "LEFT"
  }
}
const MOVES = Object.values(Move);
