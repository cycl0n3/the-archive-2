class Board {

  constructor(n) {
    this.n = n;
    this.vector = new Array(n * n);
    for (let i = 0; i < this.vector.length - 1; i++) {
      this.vector[i] = i + 1;
    }
    this.vector[this.vector.length - 1] = 0;
  }

  onChange(func) {
    this.onChangeFunc = func;
  }

  get(i, j) {
    if (i < 0 || i >= this.n || j < 0 || j >= this.n) {
      return null;
    }
    const index = j * this.n + i;
    return this.vector[index];
  }

  set(i, j, value) {
    if (i < 0 || i >= this.n || j < 0 || j >= this.n) {
      console.error("Board index out of bounds (Board.set)");
      return;
    }
    const index = j * this.n + i;
    this.vector[index] = value;
  }

  move(x, y) {
    const value = this.get(x, y);
    if (value != 0) {
      const move = this.canMove(x, y);
      if (move != null) {
        this.swap(x, y, x + move.x, y + move.y);
      }
    }
  }

  canMove(x, y) {
    if (this.get(x, y - 1) == 0) return Move.UP;
    if (this.get(x + 1, y) == 0) return Move.RIGHT;
    if (this.get(x, y + 1) == 0) return Move.DOWN;
    if (this.get(x - 1, y) == 0) return Move.LEFT;
    return null;
  }

  swap(x1, y1, x2, y2) {
    const temp = this.get(x2, y2);
    this.set(x2, y2, this.get(x1, y1));
    this.set(x1, y1, temp);
    if (this.onChangeFunc) this.onChangeFunc();
    this.lastMove = {
      from: {
        x: x1,
        y: y1
      },
      to: {
        x: x2,
        y: y2
      }
    }
  }

  isSolved() {
    for (let i = 0; i < this.vector.length - 1; i++) {
      if (this.vector[i] != i + 1) return false;
    }
    return this.vector[this.vector.length - 1] == 0;
  }

  up() {
    const empty = this.getEmptyPos();
    if (this.get(empty.x, empty.y + 1) != null) {
      this.swap(empty.x, empty.y, empty.x, empty.y + 1);
      return true;
    }
    return false;
  }

  down() {
    const empty = this.getEmptyPos();
    if (this.get(empty.x, empty.y - 1) != null) {
      this.swap(empty.x, empty.y, empty.x, empty.y - 1);
      return true;
    }
    return false;
  }

  left() {
    const empty = this.getEmptyPos();
    if (this.get(empty.x + 1, empty.y) != null) {
      this.swap(empty.x, empty.y, empty.x + 1, empty.y);
      return true;
    }
    return false;
  }

  right() {
    const empty = this.getEmptyPos();
    if (this.get(empty.x - 1, empty.y) != null) {
      this.swap(empty.x, empty.y, empty.x - 1, empty.y);
      return true;
    }
    return false;
  }

  randomMove() {
    const emptyPos = this.getEmptyPos();
    const moves = shuffle(MOVES);
    for (const move of moves) {
      const destX = emptyPos.x + move.x;
      const destY = emptyPos.y + move.y;
      if (this.get(destX, destY) != null) {
        this.swap(emptyPos.x, emptyPos.y, destX, destY);
        break;
      }
    }
  }

  shuffle(n) {
    const emptyPos = this.getEmptyPos();
    while (n > 0) {
      const move = random(MOVES);
      const destX = emptyPos.x + move.x;
      const destY = emptyPos.y + move.y;
      if (this.get(destX, destY) != null) {
        this.swap(emptyPos.x, emptyPos.y, destX, destY);
        n--;
      }
    }
  }

  getEmptyPos() {
    for (let y = 0; y < this.n; y++) {
      for (let x = 0; x < this.n; x++) {
        if (this.get(x, y) == 0) return {
          x,
          y
        };
      }
    }
    return null;
  }

  print() {
    let str = "";
    for (let j = 0; j < this.n; j++) {
      for (let i = 0; i < this.n; i++) {
        str += (this.get(i, j) + " ").padEnd(3);
      }
      str += "\n";
    }
    console.log(str);
  }
}