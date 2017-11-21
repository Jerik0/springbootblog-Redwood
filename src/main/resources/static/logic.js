"use strict";

(() => {

  const postTitle = $('.post-title');
  const postTitleTimestamp = $('.post-title-timestamp');
  const userLinks = $('#topbar-list-right').get(0);
  const topBar = $('#topbar-container');

  //Check each post title. If title is longer than 18 chars, replace remaining chars with "..."
  postTitle.each(function() {
    let thisText = this.text;
    if(this.text.length > 15) {
      let replaceString = thisText.substring(15);
      this.text = thisText.replace(replaceString, "...");
    }
  });

  postTitleTimestamp.each(function() {
    let replaceString = this.innerHTML.substring(8);
    this.innerText = this.innerText.replace(replaceString, "");
  });

  //TEST LOGS
  // console.log(topBar.offset().top - $(document).scrollTop());
  console.log('ScrollTop() : ' + $(document).scrollTop());
  console.log('userLinks:');
  console.log(userLinks);
  console.log('===========');
  console.log('topBar: ');
  console.log(topBar);
  console.log('===========');

  //When topbar is no longer on screen, move user links to navbar.
  if((topBar.offset().top - $(window).scrollTop()) <= -85) {
    userLinks.css({
      "position": "absolute",
      "top": "100px"
    });
    console.log(userLinks.css());
    console.log('well, this log is working at least...');
  }

})();