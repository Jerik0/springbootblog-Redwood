"use strict";

(() => {

  const postTitle = $('.post-title');

  postTitle.each(function() {
    let thisText = this.text;
    if(this.text.length > 15) {
      let replaceString = thisText.substring(18);
      let newPostTitle = thisText.replace(replaceString, "...");
      console.log('length is greater than 15!');
      console.log(replaceString);
      this.text = newPostTitle;
      console.log('title manager triggered!');
    }
  });

})();