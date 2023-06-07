// Loïc Bertrand
// Clock of clocks with p5.js
// Inspired by this video : https://www.youtube.com/watch?v=ExkVIQ60ClM

// Vertical line
const VER = {
  hours: 1,
  min: 3
};
// Horizontal line
const HOR = {
  hours: 0,
  min: 2
};
// Top Right Corner of a square
const TRC = {
  hours: 1,
  min: 2
};
// Top Left Corner of a square
const TLC = {
  hours: 0,
  min: 1
};
// Bottom Right Corner of a square
const BRC = {
  hours: 2,
  min: 3
};
// Bottom Left Corner of a square
const BLC = {
  hours: 0,
  min: 3
};

const positions = [
  // Digit 0
  [
    TLC, HOR, HOR, HOR, TRC,
    VER, TLC, HOR, TRC, VER,
    VER, VER, HOR, VER, VER,
    VER, VER, HOR, VER, VER,
    VER, BLC, HOR, BRC, VER,
    BLC, HOR, HOR, HOR, BRC,
  ],
  // Digit 1
  [
    HOR, HOR, HOR, TLC, TRC,
    HOR, HOR, HOR, VER, VER,
    HOR, HOR, HOR, VER, VER,
    HOR, HOR, HOR, VER, VER,
    HOR, HOR, HOR, VER, VER,
    HOR, HOR, HOR, BLC, BRC,
  ],
  // Digit 2
  [
    TLC, HOR, HOR, HOR, TRC,
    BLC, HOR, HOR, TRC, VER,
    TLC, HOR, HOR, BRC, VER,
    VER, TLC, HOR, HOR, BRC,
    VER, BLC, HOR, HOR, TRC,
    BLC, HOR, HOR, HOR, BRC,
  ],
  // Digit 3
  [
    TLC, HOR, HOR, HOR, TRC,
    BLC, HOR, HOR, TRC, VER,
    TLC, HOR, HOR, BRC, VER,
    BLC, HOR, HOR, TRC, VER,
    TLC, HOR, HOR, BRC, VER,
    BLC, HOR, HOR, HOR, BRC,
  ],
  // Digit 4
  [
    TLC, TRC, HOR, TLC, TRC,
    VER, VER, HOR, VER, VER,
    VER, BLC, HOR, BRC, VER,
    BLC, HOR, HOR, TRC, VER,
    HOR, HOR, HOR, VER, VER,
    HOR, HOR, HOR, BLC, BRC,
  ],
  // Digit 5
  [
    TLC, HOR, HOR, HOR, TRC,
    VER, TLC, HOR, HOR, BRC,
    VER, BLC, HOR, HOR, TRC,
    BLC, HOR, HOR, TRC, VER,
    TLC, HOR, HOR, BRC, VER,
    BLC, HOR, HOR, HOR, BRC,
  ],
  // Digit 6
  [
    TLC, HOR, HOR, HOR, TRC,
    VER, TLC, HOR, HOR, BRC,
    VER, BLC, HOR, HOR, TRC,
    VER, TLC, HOR, TRC, VER,
    VER, BLC, HOR, BRC, VER,
    BLC, HOR, HOR, HOR, BRC,
  ],
  // Digit 7
  [
    TLC, HOR, HOR, HOR, TRC,
    BLC, HOR, HOR, TRC, VER,
    HOR, HOR, HOR, VER, VER,
    HOR, HOR, HOR, VER, VER,
    HOR, HOR, HOR, VER, VER,
    HOR, HOR, HOR, BLC, BRC,
  ],
  // Digit 8
  [
    TLC, HOR, HOR, HOR, TRC,
    VER, TLC, HOR, TRC, VER,
    VER, BLC, HOR, BRC, VER,
    VER, TLC, HOR, TRC, VER,
    VER, BLC, HOR, BRC, VER,
    BLC, HOR, HOR, HOR, BRC,
  ],
  // Digit 9
  [
    TLC, HOR, HOR, HOR, TRC,
    VER, TLC, HOR, TRC, VER,
    VER, BLC, HOR, BRC, VER,
    BLC, HOR, HOR, TRC, VER,
    TLC, HOR, HOR, BRC, VER,
    BLC, HOR, HOR, HOR, BRC,
  ],
]
