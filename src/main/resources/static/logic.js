"use strict";

(() => {

  const postTitle = $('.post-title');

  //Check each post title. If title is longer than 18 chars, replace remaining chars with "..."
  postTitle.each(function() {
    let thisText = this.text;
    if(this.text.length > 15) {
      let replaceString = thisText.substring(18);
      let newPostTitle = thisText.replace(replaceString, "...");
      console.log('length is greater than 18!');
      console.log(replaceString);
      this.text = newPostTitle;
      console.log('title manager triggered!');
    }
  });

})();