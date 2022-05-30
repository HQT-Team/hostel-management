
function hightlight(id, color) {
    var count = 1;
    if (id === "content__left--first-button") {
        document.getElementById("nav__center--title").innerHTML = "Trang Chủ";
        document.getElementById("content__right").style.display = 'inline-block';
        document.getElementById("content__img").style.display = '';
        document.getElementById("content__right-2").style.display = 'none';
        document.getElementById("content__left--second-button").style.backgroundColor = "#ffffff";
        var property = document.getElementById(id);
        if (count == 0) {
            property.style.backgroundColor = color1;
            count = 1;
        }
        else {
            property.style.backgroundColor = color;
            count = 0;
        }
    } else {
        document.getElementById("nav__center--title").innerHTML = "Tài Khoản";
        document.getElementById("content__right").style.display = 'none';
        document.getElementById("content__img").style.display = 'none';
        document.getElementById("content__right-2").style.display = 'inline-block';
        document.getElementById("content__left--first-button").style.backgroundColor = "#ffffff";
        var property = document.getElementById(id);
        if (count == 0) {
            property.style.backgroundColor = color1;
            count = 1;
        }
        else {
            property.style.backgroundColor = color;
            count = 0;
        }
    }

}


function choose(butttonId, titleId) {
    if (butttonId === 'button-1') {
        document.getElementById(butttonId).style.background = "#ffffff";
        document.getElementById(titleId).style.color = "var(--primary-color)";
        document.getElementById('button-2').style.background = "var(--primary-color)";
        document.getElementById('button-2__title').style.color = "#ffffff";
    } else {
        document.getElementById(butttonId).style.background = "#ffffff";
        document.getElementById(titleId).style.color = "var(--primary-color)";
        document.getElementById('button-1').style.background = "var(--primary-color)";
        document.getElementById('button-1__title').style.color = "#ffffff";
    }



}


var viewListAprroveButton = document.querySelector('.button-1');
function viewListAccountApprove() {
    document.querySelector('.table__approve-account').classList.remove('hide');
    document.querySelector('.table__view-account').classList.add('hide');
}
viewListAprroveButton.addEventListener('click', viewListAccountApprove)



var viewListHostelButton = document.querySelector('.button-2');
function viewListAccount() {
    document.querySelector('.table__view-account').classList.remove('hide');
    document.querySelector('.table__approve-account').classList.add('hide');
}
viewListHostelButton.addEventListener('click', viewListAccount)


const myModal = document.getElementById('myModal')
const myInput = document.getElementById('myInput')

myModal.addEventListener('shown.bs.modal', () => {
    myInput.focus()
})





function disablebtn() {
    var btnActive = document.querySelector("#activeBtn");
    btnActive.innerHTML = 'Activated';
    btnActive.style.color = 'gray';
}




function block_unlock_btn(btn) {
    if (btn == 'btnBlock') {
        document.querySelector("#BlockForm").style.display = "none";
        document.querySelector("#UnlockForm").style.display = "block";
    } else {
        document.querySelector("#BlockForm").style.display = "block";
        document.querySelector("#UnlockForm").style.display = "none";
    }
}


