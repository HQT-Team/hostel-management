$(document).ready(function () {
  document.addEventListener("click", function (event) {
    const navNotificationBtn = $("#nav-notification-btn");
    const notificationList = $("#notification-list");
    const navProfileBtn = $("#nav-profile-btn");
    const navProfileDropdown = $("#nav-profile-dropdown");

    // If user clicks inside the element, do nothing
    if (event.target.closest("#nav-notification-btn")) {
      if (notificationList.css("display") === "block") {
        notificationList.css("display", "none");
        navNotificationBtn.removeClass("active");
      } else {
        notificationList.css("display", "block");
        navNotificationBtn.addClass("active");
      }
    } else {
      // If user clicks outside the element, hide it!
      notificationList.css("display", "none");
      navNotificationBtn.removeClass("active");
    }

    if (event.target.closest("#nav-profile-btn")) {
      if (navProfileDropdown.css("display") === "block") {
        navProfileDropdown.css("display", "none");
        navProfileBtn.removeClass("active");
      } else {
        navProfileDropdown.css("display", "block");
        navProfileBtn.addClass("active");
      }
    } else {
      // If user clicks outside the element, hide it!
      navProfileDropdown.css("display", "none");
      navProfileBtn.removeClass("active");
    }

    if (event.target.closest("#notification-list")) {
      if (!navNotificationBtn.hasClass("active")) {
        navNotificationBtn.addClass("active");
      }
      notificationList.css("display", "block");
    }
    if (event.target.closest("#nav-profile-dropdown")) {
      if (!navProfileBtn.hasClass("active")) {
        navProfileBtn.addClass("active");
      }
      navProfileDropdown.css("display", "block");
    }

    // Sidebar Btn
    const menuSidebarBtn = $("#menu-sidebar-btn");
    const mainSideBar = $("#main-side-bar");

    if (event.target.closest("#menu-sidebar-btn")) {
      if (mainSideBar.css("display") === "block") {
        menuSidebarBtn.removeClass("active");
        mainSideBar.css("display", "none");
      } else {
        menuSidebarBtn.addClass("active");
        mainSideBar.css("display", "block");
      }
    } else {
      menuSidebarBtn.removeClass("active");
      mainSideBar.css("display", "none");
    }
  });
});
