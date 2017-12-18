"use strict";

(() => {

  const screen = document.getElementById('screen');
  const context = screen.getContext('2d');

  context.scale(10, 10);

  //TODO Create shape objects (?)

  //TODO Create animation for powering on
  const powerOn = () => {

  };

  function arenaSweep() {
    outer: for(let y = arena.length - 1; y > 0; --y) {
      for(let x = 0; x < arena[y].length; ++x) {
        if(arena[y][x] === 0) {
          continue outer;
        }
      }

      const row = arena.splice(y, 1)[0].fill(0);
      arena.unshift(row);
      ++y;
    }
  }

  function collide(arena, player) {
    const [m, o] = [player.matrix, player.pos]; //Create an array using the player's shape and position

    for(let y = 0; y < m.length; ++y) { //Iterate over the length of the player
      for(let x = 0; x < m[y].length; ++x) { //Iterate over the height of the player
        if(m[y][x] !== 0 && (arena[y + o.y] && arena[y + o.y][x + o.x]) !== 0) { //AND the position it's in is inside the arena
          return true;
        }
      }
    }
    return false;
  }

  function createPiece(type) {
    switch(type) {
      case "T": {
        return [
            [0,0,0],
            [1,1,1],
            [0,1,0],
        ]
      } break;
      case "O": {
        return [
            [2,2],
            [2,2]
        ]
      } break;
      case "L": {
        return [
            [0,3,0],
            [0,3,0],
            [0,3,3]
        ]
      } break;
      case "J": {
        return [
          [0,4,0],
          [0,4,0],
          [4,4,0],
        ]
      } break;
      case "S": {
        return [
          [0,5,5],
          [5,5,0],
          [0,0,0]
        ]
      } break;
      case "Z": {
        return [
            [6,6,0],
            [0,6,6],
            [0,0,0]
        ]
      } break;
      case "I": {
        return [
            [0,7,0,0],
            [0,7,0,0],
            [0,7,0,0],
            [0,7,0,0]
        ]
      }
    }
  }

  //Creates the playing arena
  function createMatrix(w, h) {
    const matrix = [];
    while (h--) { //While h is not 0, decrement h.
      matrix.push(new Array(w).fill(0)); //Add an array on each index of h, creating a matrix.
    }
    return matrix;
  }

  function draw() {
    context.fillStyle = '#69615b';
    context.fillRect(0, 0, screen.width, screen.height);

    drawMatrix(arena, {x: 0, y: 0});
    drawMatrix(player.matrix, player.pos);
  }

  function drawMatrix(matrix, offset) {
    matrix.forEach((row, y) => {
      row.forEach((value, x) => {
        if (value !== 0) {
          context.fillStyle = colors[value];
          context.fillRect(
              x + offset.x,
              y + offset.y,
              1,
              1
          );
        }
      })
    });
  }

  //Places the player shape into the arena array
  function merge(arena, player) {
    player.matrix.forEach((row, y) => {
      row.forEach((value, x) => {
        if (value !== 0) {
          arena[y + player.pos.y][x + player.pos.x] = value;
        }
      });
    });
  }

  function playerDrop() {
    player.pos.y++;
    if(collide(arena, player)) { //If player collides with another piece,
      player.pos.y--; //Stay where it is
      merge(arena, player); //Add its position to the arena (empty space)
      playerReset(); //Start from the top again with another piece.
      arenaSweep();
    }
    dropCounter = 0;
  }

  //Detects if the player is colliding with the walls of the screen and will prevent further movement, otherwise move that direction.
  function playerMove(dir) {
    player.pos.x += dir;
    if(collide(arena, player)) {
      player.pos.x -= dir;
    }
  }

  function playerReset() {
    const pieces = 'ILJTOSZ';
    player.matrix = createPiece(pieces[pieces.length * Math.random() | 0]);
    player.pos.y = 0;
    player.pos.x = (arena[0].length / 2 | 0) - (player.matrix.length / 2 | 0);
  }

  function playerRotate(dir) {
    const pos = player.pos.x;
    let offset = 1;

    rotate(player.matrix, dir);

    while(collide(arena, player)) { //Check for collision with walls
      player.pos.x += offset; //If next to a wall, set the player's x position + or - depending on the wall it hits
      offset = -(offset + (offset > 0 ? 1 : -1));
      if(offset > player.matrix[0].length) {
        rotate(player.matrix, -dir);
        player.pos.x = pos;
        return;
      }
    }
  }

  //TODO Change how rotation works: use arrays of pre-defined shapes instead of this algorithm.
  function rotate(matrix, dir) {
    for(let y = 0; y < matrix.length; ++y) { //Iterate over entire player shape
      for(let x = 0; x < y; ++x) {
        //Transpose the shape from rows to columns.
        [
            matrix[x][y],
            matrix[y][x],
        ] = [
            matrix[y][x],
            matrix[x][y],
        ];

        //Reverse the columns.
        if(dir > 0) {
          matrix.forEach((row => row.reverse()));
        } else {
          matrix.reverse();
        }

      }
    }
  }

  //Interval variables
  let dropCounter = 0;
  let dropInterval = 1000;
  let lastTime = 0;

  //Updates the screen recursively to show the new position of the generated shape.
  function update(time = 0) {
    let deltaTime = time - lastTime;
    lastTime = time;

    dropCounter += deltaTime;

    //Once the dropCounter reaches 1000, calls playerDrop().
    if(dropCounter > dropInterval) {
      playerDrop();
    }

    draw();
    requestAnimationFrame(update);
  }

  const colors = [
      null,
      '#ff2e47',
      '#9fffb4',
      '#1e51e2',
      '#edff0b',
      '#ff8ab7',
      '#effff8',
      '#53ffef'
  ];

  const arena = createMatrix(20, 20);

  const player = {
    pos: {x: 5, y: 0},
    matrix: createPiece('T')
  };

  //Switch for detecting player movement.
  document.addEventListener('keydown', event => {
    switch(event.keyCode) {
      case 39:
        event.preventDefault();
        console.log("right");
        playerMove(1);
        break;
      case 37:
        event.preventDefault();
        console.log("left");
        playerMove(-1);
        break;
      case 40:
        event.preventDefault();
        console.log("drop");
        playerDrop();
         break;
      case 81:
        event.preventDefault();
        playerRotate(-1);
        break;
      case 87:
        event.preventDefault();
        playerRotate(1);
        break;
      default:
        console.log("Default works");
    }
  });

  update();

})();