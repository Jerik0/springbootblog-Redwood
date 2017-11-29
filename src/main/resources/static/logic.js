"use strict";

(() => {

  const postTitle = $('.post-title');
  const postTitleTimestamp = $('.post-title-timestamp');
  const userLinks = $('#topbar-list-right');
  const topBar = $('#topbar-container');
  const navBar = $('#nav-bar');
  const navContainer = $('#nav-items-container');
  const topBarItems = $('.topbar-item');
  const userLinksContainer = $('#user-links-container');
  const userStatus = $('#user-status').text();
  const uploadButton = $('#upload-button');
  const imageUrlInput = $('#imageUrl');
  const userImage = $('.user-image-display');

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

  //When topbar is no longer on screen, fix navbar to top of screen.
  $(window).scroll(function() {
    if(topBar.offset().top - $(window).scrollTop() <= -83) {
      navBar.addClass('sticky');
      $('#logout-btn').css("position", "relative");
      topBarItems.appendTo(userLinksContainer);
    }

    if(topBar.offset().top - $(window).scrollTop() > -83) {
      topBarItems.appendTo(userLinks);
      navBar.removeClass('sticky');
    }
  });

  console.log(userStatus);
  // If theres not a user (thus no comment-submit form), change submit form bg to none.
  if(userStatus == "false") {
    console.log('No user!');
    $('#comment-submit-bg').css('border', 'none');
  }

  //==========FILESTACK UPLOADING===========

  uploadButton.click(function() {
    const client = filestack.init('A5qa4IdqgTea0Y1rOX5qkz');

    const addLink = function(file) {
      userImage.src = file.url;
      // links.appendChild(link);
    };

    client.pick({
      accept: 'image/*',
      fromSources:  ['local_file_system','facebook','googledrive','instagram','dropbox','imagesearch','webcam',],
      maxSize: 1024*2024,
      maxFiles: 3,
    }).then(
        function(result) {
          const fileUrl = result.filesUploaded[0].url;
          imageUrlInput.val(fileUrl);
          result.filesUploaded.forEach(function(file) {
            addLink(file)
          });
        });
  });

})();