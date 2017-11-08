"use strict";

(() => {

  const postPanel = $('.post-title');

  postPanel.hover((e) => {
    console.log('hover in');
    // $(e.target).removeClass('post-title').addClass('shown');
  }, (e) => {
    console.log('hover out');
    // $(e.target).removeClass('shown').addClass('post-title');
  });

  const hover = () => {

  }

})();