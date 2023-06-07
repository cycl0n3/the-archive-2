let board;
let n, unit;
let solved = false;
let shuffling = false;

function setup() {
  const controls = select('#controls');
  const sizeInput = select('#sizeInput');
  const newBoardBtn = select('#newBoardBtn');
  const createNewBoard = () => {
    shuffling = false;
    n = constrain(sizeInput.value(), MIN_SIZE, MAX_SIZE);
    sizeInput.value(n);
    init();
  }
  sizeInput.changed(createNewBoard);
  newBoardBtn.mousePressed(createNewBoard);

  const side = min(windowWidth, windowHeight - controls.height);
  createCanvas(side, side).parent("container");
  noLoop();
  textAlign(CENTER, CENTER);
  textFont("monospace");

  createNewBoard();
}

function setChangeEvent() {
  board.onChange(() => {
    solved = board.isSolved();
    redraw();
  });
}

function init() {
  unit = width / n;
  board = new Board(n);
  shuffleBoard();
}

function shuffleBoard() {
  shuffling = true;
  redraw();

  if (n < 7) {
    setChangeEvent();
    aux(n * n * 12);
  } else {
    aux(n * n * 8);
  }

  function aux(moves) {
    if (moves > 0 && shuffling == true) {
      board.randomMove();
      setTimeout(() => aux(moves - 1));
    } else {
      shuffling = false;
      redraw();
      setChangeEvent();
    }
  }
}

function draw() {
  background(220);
  textSize(unit * 0.45);
  for (let j = 0; j < n; j++) {
    for (let i = 0; i < n; i++) {
      const value = board.get(i, j);
      fill(value == 0 ? 0 : 255);
      rect(i * unit, j * unit, unit, unit);
      fill(0);
      text(value, (i + 0.5) * unit, (j + 0.5) * unit);
    }
  }
  if (solved || shuffling) {
    background(0, 200);
    fill(255);
    textSize(height * 0.1);
    if (shuffling)
      text("SHUFFLING...", width / 2, height / 2);
    else if (solved)
      text("SOLVED!", width / 2, height / 2);
  }
}

function mousePressed() {
  const x = floor(mouseX / unit);
  const y = floor(mouseY / unit);
  board.move(x, y);
}

// function keyPressed() {
//   switch (keyCode) {
//     case UP_ARROW:
//       board.up();
//       break;
//     case RIGHT_ARROW:
//       board.right();
//       break;
//     case DOWN_ARROW:
//       board.down();
//       break;
//     case LEFT_ARROW:
//       board.left();
//       break;
//     default:
//       break;
//   }
// }