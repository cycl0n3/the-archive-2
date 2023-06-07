// Loïc Bertrand
// Tetris recreation with p5.js

const cols = 10;
const rows = 20;
const scl = 30;

let gameOver;
let score;
let level;
let speed;
let board;
let nextPiece;
let currentPiece;
let canvas2;

function setup() {
  createCanvas(cols * scl, rows * scl).parent('canvas-holder');
  canvas2 = createSecondCanvas();
  select('#reset').mousePressed(resetGame);
  frameRate(30);
  textFont('Consolas Black');
  resetGame();
}

function resetGame() {
  gameOver = false;
  score = 0;
  level = 1;
  speed = 30;
  updatePlayerInfo();
  board = create2DArray(rows, cols);
  nextPiece = new Piece();
  currentPiece = new Piece();
  drawNextPiece();
  loop();
}

function draw() {
  // Drawing
  background(230);
  for (let y = 0; y < rows; y++) {
    for (let x = 0; x < cols; x++) {
      const color = board[y][x];
      if (color) {
        drawCube(x, y, color, scl);
      }
    }
  }
  currentPiece.show();
  if (gameOver) {
    drawGameOver();
    noLoop();
  }

  // Game loop
  if (frameCount % speed === 0) {
    moveCurrentDown();
  }
  if (keyIsDown(DOWN_ARROW)) {
    moveCurrentDown();
  }
}

function keyPressed() {
  if (keyCode === RIGHT_ARROW) {
    currentPiece.move(1, 0);
  } else if (keyCode === LEFT_ARROW) {
    currentPiece.move(-1, 0);
  } else if (keyCode === UP_ARROW) {
    currentPiece.turn();
  }
}

function moveCurrentDown() {
  if (!currentPiece.move(0, 1)) {
    if (currentPiece.pos.y >= 0) {
      const range = currentPiece.drop();
      const removed = removeFullRows(range);
      updatePlayerInfo(removed);
      currentPiece = nextPiece;
      nextPiece = new Piece();
      drawNextPiece();
    } else {
      gameOver = true
    }
  }
}

function removeFullRows(range) {
  let removedLines = 0;
  for (let j = range.bottom; j >= range.top; j--) {
    let full = true;
    for (let i = 0; i < cols; i++) {
      if (board[j][i] === undefined) {
        full = false;
        break;
      }
    }
    if (full) {
      board.splice(j, 1);
      board.unshift(new Array(cols));
      removedLines++;
      j++;
    }
  }
  return removedLines;
}

function updatePlayerInfo(removedLines) {
  if (removedLines) {
    // Computation
    score += removedLines ** 2;
    level = floor(score / 10) + 1;
    speed = max(3, floor(map(level ** 2, 0, 100, 30, 3)));
  }
  // DOM update
  document.querySelector('#level').innerHTML = level;
  document.querySelector('#score').innerHTML = score;
}

function create2DArray(rows, cols) {
  const arr = new Array(rows);
  for (let j = 0; j < rows; j++) {
    arr[j] = new Array(cols);
  }
  return arr;
}

function createSecondCanvas() {
  const c = createGraphics(scl * 4, scl * 4);
  c.parent('next-piece-holder')
  c.noLoop();
  c.show();
  return c;
}
