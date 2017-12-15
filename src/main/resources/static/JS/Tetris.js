"use strict";

(() => {

  const screen = document.getElementById('screen');
  const context = screen.getContext('2d');

  context.scale(10, 10);

  //TODO Create shape objects (?)

  //TODO Create animation for powering on
  const powerOn = () => {

  };

  const matrix = [
      [0,0,0],
      [1,1,1],
      [0,1,0],
  ];

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

  const arena = createMatrix(20, 20);

  //Creates the playing arena
  function createMatrix(w, h) {
    const matrix = [];
    while (h--) { //While h is not 0, decrement h.
      matrix.push(new Array(w).fill(0)); //Add an array on each index of h, creating essentially a matrix.
    }
    return matrix;
  }

  function draw() {
    context.fillStyle = '#99d1d4';
    context.fillRect(0, 0, screen.width, screen.height);

    drawMatrix(arena, {x: 0, y: 0});
    drawMatrix(player.matrix, player.pos);
  }

  function drawMatrix(matrix, offset) {
    matrix.forEach((row, y) => {
      row.forEach((value, x) => {
        if (value !== 0) {
          context.fillStyle = '#373E42';
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
      player.pos.y = 0; //Start from the top again with another piece.
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

  const player = {
    pos: {x: 5, y: 0},
    matrix: matrix
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