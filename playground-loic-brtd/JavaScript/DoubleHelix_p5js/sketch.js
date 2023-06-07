function setup() {
  createCanvas(400, 400);
}

function draw() {
  background(0);
  translate(width / 2, height / 2);
  rotate(-0.005 * frameCount);

  colorMode(HSB);
  strokeWeight(6);
  noFill();

  stroke(frameCount % 360, 100, 100, 0.7);
  paintHelix(120, 0);
  stroke((frameCount + 50) % 360, 100, 100, 0.7);
  paintHelix(120, PI);
}

function paintHelix(radius, offset) {
  const n = 150; // number of vertices
  const k = 6; // number of waves
  const a = 12; // wave amplitude

  beginShape();

  for (let i = 0; i < n; i++) {
    const angle = i * (TWO_PI / n); // 0..TWO_PI
    const rotation = (i + frameCount) % n; // 0..n

    const wave = sin(angle * k + offset) * a; // 0..a
    const fade = pow(
      (rotation < n / 2) ?
      (rotation) / (n / 2) :
      (n - rotation) / (n / 2),
      2); // 0..1
    const delta = wave * fade;

    // vertex position
    const x = (radius + delta) * sin(angle);
    const y = (radius + delta) * cos(angle);
    vertex(x, y);
  }

  endShape(CLOSE);
}