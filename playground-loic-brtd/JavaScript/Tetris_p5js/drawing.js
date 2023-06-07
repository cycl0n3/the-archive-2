// Loïc Bertrand
// Tetris recreation with p5.js

function drawCube(posX, posY, color, scl, ctx) {
  const gap = scl / 4;
  const radius = 3;
  const x = posX * scl;
  const y = posY * scl;

  if (!ctx) {
    ctx = window;
  }

  ctx.noStroke();
  ctx.fill(color);
  ctx.rect(x, y, scl, scl, radius);
  ctx.fill(0, 40);
  ctx.rect(x + gap, y + gap, scl - 2 * gap, scl - 2 * gap);
  // Left shadow
  ctx.fill(0, 80);
  ctx.quad(x, y, x + gap, y + gap, x + gap, y + scl - gap, x, y + scl);
  // Bottom shadow
  ctx.fill(0, 60);
  ctx.quad(x, y + scl, x + gap, y + scl - gap, x + scl - gap, y + scl - gap, x + scl, y + scl);
}

function drawNextPiece() {
  canvas2.clear();
  canvas2.translate(canvas2.width / 2 - nextPiece.shape[0].length * scl * 0.4,
    canvas2.height / 2 - nextPiece.shape.length * scl * 0.4)
  for (let j = 0; j < nextPiece.shape.length; j++) {
    for (let i = 0; i < nextPiece.shape[0].length; i++) {
      if (nextPiece.shape[j][i] === 1) {
        drawCube(i, j, nextPiece.color, scl * 0.8, canvas2);
      }
    }
  }
  canvas2.redraw();
}

function drawGameOver() {
  textAlign(CENTER, CENTER);
  textSize(40);
  strokeWeight(8);
  stroke(255);
  text("Game Over", width / 2, height / 2);
  noStroke();
  fill(20);
  text("Game Over", width / 2, height / 2);
}
