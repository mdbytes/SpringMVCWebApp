/*!
 * Start Bootstrap - Modern Business v5.0.7 (https://startbootstrap.com/template-overviews/modern-business)
 * Copyright 2013-2023 Start Bootstrap
 * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-modern-business/blob/master/LICENSE)
 */

// Enable drop down menus with hover only
const dropDownNavLinks = document.querySelectorAll(".nav-link.dropdown-toggle");
for (let link of dropDownNavLinks) {
    link.addEventListener("mouseover", function () {
        console.log("clicked");
        link.ariaExpanded = "true";
        link.classList.add("show");
        link.click();
    });
}

// Enable active class based on path
if (window.location.href.includes("events")) {
    document.querySelector("#eventsLink").classList.add("active");
} else if (window.location.href.includes("clubs")) {
    document.querySelector("#clubsLink").classList.add("active");
} else if (window.location.href.includes("admin")) {
    document.querySelector("#adminLink").classList.add("active");
} else if (window.location.href.includes("users")) {
    document.querySelector("#accountLink").classList.add("active");
} else if (window.location.href.includes("login")) {
    document.querySelector("#loginLink").classList.add("active");
} else if (window.location.href.includes("register")) {
    document.querySelector("#registerLink").classList.add("active");
} else {
    document.querySelector("#homeLink").classList.add("active");
}
