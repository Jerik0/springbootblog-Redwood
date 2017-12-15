"use strict";

// (() => {

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

  //Interval variables
  let dropCounter = 0;
  let dropInterval = 1000;
  let lastTime = 0;

  //Updates the screen recursively to show the new position of the generated shape.
  function update(time = 0) {
    let deltaTime = time - lastTime;
    lastTime = time;

    dropCounter += deltaTime;

    //Once the dropCounter reaches 1000, moves the player's Y position down by one, and repeats.
    if(dropCounter > dropInterval) {
      player.pos.y++;
      dropCounter = 0;
    }

    draw();
    requestAnimationFrame(update);
  }

  const player = {
    pos: {x: 5, y: 0},
    matrix: matrix
  };

  function playerDrop() {
    player.pos.y++;
    if(collide(arena, player)) { //If player collides with another piece,
      player.pos.y--; //Stay where it is
      merge(arena, player); //Add its position to the arena (empty space)
      player.pos.y = 0; //Start from the top again with another piece.
    }
    dropCounter = 0;
  }

  document.addEventListener('keydown', event => {
    switch(event.keyCode) {
      case 39:
        event.preventDefault();
        console.log("right");
        player.pos.x++;
        break;
      case 37:
        event.preventDefault();
        console.log("left");
        player.pos.x--;
        break;
      case 40:
        event.preventDefault();
        console.log("drop");
        playerDrop();
         break;
      default:
        console.log("Default works");
    }
  });

  update();

// })();