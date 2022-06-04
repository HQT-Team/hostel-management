function hightlight(id) {
  if (id === "content__left--first-button") {
    document.getElementById("content__right").style.display = "inline-block";
    document.getElementById("content__img").style.display = "";
    document.getElementById("content__right-2").style.display = "none";
    document.querySelector(
      "#content__left--first-button"
    ).style.backgroundColor = "var(--primary-color)";
    document.querySelector("#content__left--first-button").style.color =
      "white";
    document.querySelector(
      "#content__left--second-button"
    ).style.backgroundColor = "";
    document.querySelector("#content__left--second-button").style.color = "";
    document.querySelector("#nav__title").innerHTML = "Trang Chủ";
  } else {
    document.getElementById("content__right").style.display = "none";
    document.getElementById("content__img").style.display = "none";
    document.getElementById("content__right-2").style.display = "inline-block";
    document.querySelector(
      "#content__left--second-button"
    ).style.backgroundColor = "var(--primary-color)";
    document.querySelector("#content__left--second-button").style.color =
      "white";
    document.querySelector("#content__left--first-button").style.color = "";
    document.querySelector(
      "#content__left--first-button"
    ).style.backgroundColor = "";
    document.querySelector("#nav__title").innerHTML = "Tài Khoản";
  }
}

hightlight("content__left--first-button");

function choose(butttonId, titleId) {
  if (butttonId === "button-1") {
    document.getElementById(butttonId).style.background = "var(--primary-color)";
    document.getElementById(titleId).style.color = "#ffffff";
    document.getElementById("button-2").style.background =
      "#ffffff";
    document.getElementById("button-2__title").style.color = "var(--primary-color)";
  } else {
    document.getElementById(butttonId).style.background = "var(--primary-color)";
    document.getElementById(titleId).style.color = "#ffffff";
    document.getElementById("button-1").style.background =
      "#ffffff";
    document.getElementById("button-1__title").style.color = "var(--primary-color)";
  }
}

var viewListAprroveButton = document.querySelector(".button-1");
function viewListAccountApprove() {
  document.querySelector(".table__approve-account").classList.remove("hide");
  document.querySelector(".table__view-account").classList.add("hide");
}
viewListAprroveButton.addEventListener("click", viewListAccountApprove);

var viewListHostelButton = document.querySelector(".button-2");
function viewListAccount() {
  document.querySelector(".table__view-account").classList.remove("hide");
  document.querySelector(".table__approve-account").classList.add("hide");
}
viewListHostelButton.addEventListener("click", viewListAccount);

const myModal = document.getElementById("myModal");
const myInput = document.getElementById("myInput");

myModal.addEventListener("shown.bs.modal", () => {
  myInput.focus();
});

function disablebtn() {
  var btnActive = document.querySelector("#activeBtn");
  btnActive.innerHTML = "Activated";
  btnActive.style.color = "gray";
}

function block_unlock_btn(btn) {
  if (btn == "btnBlock") {
    document.querySelector("#BlockForm").style.display = "none";
    document.querySelector("#UnlockForm").style.display = "block";
  } else {
    document.querySelector("#BlockForm").style.display = "block";
    document.querySelector("#UnlockForm").style.display = "none";
  }
}
