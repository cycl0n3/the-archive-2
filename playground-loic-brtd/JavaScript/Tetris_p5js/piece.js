// Loïc Bertrand
// Tetris recreation with p5.js

class Piece {

  constructor() {
    const p = getPieceInfo(floor(random(7)));
    this.color = p.color;
    this.shape = p.shape;
    const xPos = round(cols / 2 - this.shape[0].length / 2);
    const yPos =  - this.shape.length - 1;
    this.pos = createVector(xPos, yPos);
  }

  move(xOff, yOff) {
    const savedPos = this.pos.copy();
    this.pos.add(xOff, yOff);
    if (collision(this.shape, this.pos)) {
      this.pos = savedPos;
      return false;
    }
    return true;
  }

  turn() {
    const rotated = rotateArrayCCW(this.shape);
    if (!collision(rotated, this.pos)) {
      this.shape = rotated;
    }
  }

  drop() {
    const rangeToCheck = {
      top: max(0, this.pos.y),
      bottom: min(rows - 1, this.pos.y + this.shape.length - 1)
    }
    for (let j = 0; j < this.shape.length; j++) {
      for (let i = 0; i < this.shape[0].length; i++) {
        if (this.shape[j][i] === 1) {
          const x = i + this.pos.x;
          const y = j + this.pos.y;
          board[y][x] = this.color;
        }
      }
    }
    return rangeToCheck;
  }

  show() {
    for (let j = 0; j < this.shape.length; j++) {
      for (let i = 0; i < this.shape[0].length; i++) {
        if (this.shape[j][i] === 1) {
          const x = i + this.pos.x;
          const y = j + this.pos.y;
          drawCube(x, y, this.color, scl);
        }
      }
    }
  }

}

function rotateArrayCCW(arr) {
  const rotated = create2DArray(arr[0].length, arr.length);
    for (let i = 0; i < arr[0].length; i++) {
      for (let j = 0; j < arr.length; j++) {
      rotated[i][j] = arr[j][arr[0].length - i - 1];
    }
  }
  return rotated;
}

function collision(shape, pos) {
  for (let y = 0; y < shape.length; y++) {
    for (let x = 0; x < shape[0].length; x++) {
      if (shape[y][x] === 1) {
        if (x + pos.x < 0 || x + pos.x > cols - 1 || y + pos.y > rows - 1
           || (y + pos.y >= 0 && board[y + pos.y][x + pos.x] !== undefined)) {
          return true;
        }
      }
    }
  }
  return false;
}
