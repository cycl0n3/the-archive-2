// Loïc Bertrand
// Tetris recreation with p5.js

function getPieceInfo(num) {
  switch (num) {
  case 0:
    return {
      name: 'I',
      color: 'cyan',
      shape: [
        [0, 1, 0],
        [0, 1, 0],
        [0, 1, 0],
        [0, 1, 0]
      ]
    }
    break;
  case 1:
    return {
      name: 'O',
      color: 'yellow',
      shape: [
        [1, 1],
        [1, 1]
      ]
    }
    break;
  case 2:
    return {
      name: 'T',
      color: 'purple',
      shape: [
        [1, 1, 1],
        [0, 1, 0]
      ]
    }
    break;
  case 3:
    return {
      name: 'L',
      color: 'orange',
      shape: [
        [1, 0],
        [1, 0],
        [1, 1]
      ]
    }
    break;
  case 4:
    return {
      name: 'J',
      color: 'blue',
      shape: [
        [0, 1],
        [0, 1],
        [1, 1]
      ]
    }
    break;
  case 5:
    return {
      name: 'Z',
      color: 'red',
      shape: [
        [1, 1, 0],
        [0, 1, 1]
      ]
    }
    break;
  case 6:
    return {
      name: 'S',
      color: 'green',
      shape: [
        [0, 1, 1],
        [1, 1, 0]
      ]
    }
    break;
  default:
    console.error('random shape error');
    return undefined;
  }
}
