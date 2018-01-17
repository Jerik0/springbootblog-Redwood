"use strict";

(() => {

  //Game Center Functionality==================
  const gameScriptSrc = $('#gamescripts');

  //TODO when 'change game' is clicked, gameScriptSrc's src attribute uses different JS.
  //===========================================

  //Check each post title. If title is longer than 15 chars, replace remaining chars with "..."
  $('.post-title').each(function() {
    let thisText = this.text;
    if(this.text.length > 15) {
      let replaceString = thisText.substring(15);
      this.text = thisText.replace(replaceString, "...");
    }
  });

  //This giant block of code is for the hamburger menu. These classes use animations.
  $('#forum-hamburger').click(function() {
    const userLinks = $('#topbar-list-right');
    if($(this).hasClass('rotate')) {
      $(this).removeClass('rotate');
      $(this).addClass('unrotate');
      $(this).removeClass('stick-right');
      userLinks.removeClass('drop-menu');
      userLinks.addClass('retract-menu');
      userLinks.css('top','-340px');
    } else if($(this).hasClass('unrotate')) {
      $(this).removeClass('unrotate');
      $(this).addClass('rotate');
      $(this).addClass('stick-right');
      userLinks.removeClass('retract-menu');
      userLinks.addClass('drop-menu');
      userLinks.css('top','10px');
    } else {
      $(this).addClass('rotate');
      $(this).addClass('stick-right');
      userLinks.addClass('drop-menu');
      userLinks.css('top','10px');
    }
  });

  //Make forum navbar stick to top of page when scrolling down.
  window.onscroll = function() {addSticky()};

  function addSticky() {
    const topBar = document.getElementById('topbar-wrapper');
    if(window.pageYOffset >= (topBar.offsetHeight * 2)) {
      topBar.classList.add('sticky');
      $('.wrapper').css('margin-top', topBar.offsetHeight);
    } else {
      topBar.classList.remove('sticky');
      $('.wrapper').css('margin-top', '');
    }
  }

  //Cut off extra characters on timestamps
  $('.post-title-timestamp').each(function() {
    let replaceString;
    if(this.innerHTML.substring(1,2) === "/") {
      replaceString = this.innerHTML.substring(7);
    } else {
      replaceString = this.innerHTML.substring(8);
    }
    this.innerText = this.innerText.replace(replaceString, "");
  });

  // If no user (thus no comment-submit form), change submit form bg to none.
  if($('#user-status').text() == "false") {
    console.log('No user!');
    $('#comment-submit-bg').css('border', 'none');
  }

  //==========FILESTACK UPLOADING===========
  $('#upload-button').click(function() {
    const client = filestack.init('A5qa4IdqgTea0Y1rOX5qkz');

    const addLink = function(file) {
      $('.user-image-display').src = file.url;
      $('#temp-display').src = file.url;
    };

    client.pick({
      accept: 'image/*',
      fromSources:  ['local_file_system','facebook','googledrive','instagram','dropbox','imagesearch','webcam',],
      maxSize: 1024*2024,
      maxFiles: 3,
      // transformations: { crop: true },
    }).then(
    //TODO This function needs to display the image that the user has chosen immediately, instead of just setting the input's val to the url.
    function(result) {
      const fileUrl = result.filesUploaded[0].url;
      $('#imageUrl').val(fileUrl);
      result.filesUploaded.forEach(function(file) {
        addLink(file)
      });
    });
  });

})();