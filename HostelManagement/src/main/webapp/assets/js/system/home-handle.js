$(document).ready(function () {
  AOS.init();

  document.addEventListener("click", function (event) {
    const navMobileBtn = $("#home-navbar__mobile-btn");
    const navActions = $("#navbar-mobile");

    // If user clicks inside the element, do nothing
    if (event.target.closest("#home-navbar__mobile-btn")) {
      if (navActions.css("display") === "block") {
        navActions.css("display", "none");
        navMobileBtn.removeClass("active");
      } else {
        navActions.css("display", "block");
        navMobileBtn.addClass("active");
      }
    } else {
      // If user clicks outside the element, hide it!
      navActions.css("display", "none");
      navMobileBtn.removeClass("active");
    }

    if (event.target.closest("#navbar-mobile")) {
      navMobileBtn.addClass("active");
      navActions.css("display", "block");
    }
  });
});
