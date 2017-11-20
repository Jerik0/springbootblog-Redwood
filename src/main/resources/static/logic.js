"use strict";

(() => {

  const postTitle = $('.post-title');
  const postTitleTimestamp = $('.post-title-timestamp');

  //Check each post title. If title is longer than 18 chars, replace remaining chars with "..."
  postTitle.each(function() {
    let thisText = this.text;
    if(this.text.length > 15) {
      let replaceString = thisText.substring(15);
      this.text = thisText.replace(replaceString, "...");
    }
  });

  postTitleTimestamp.each(function() {
    console.log(this.innerText);
    let replaceString = this.innerHTML.substring(8);
    console.log(replaceString);
    this.innerText = this.innerText.replace(replaceString, "");
  });

})();