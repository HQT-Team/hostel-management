let form_1 = document.getElementById("form-item-input-1");
let form_2 = document.getElementById("form-item-input-2");
let form_3 = document.getElementById("form-item-input-3");
let form_4 = document.getElementById("form-item-input-4");
let form_5 = document.getElementById("form-item-input-5");
let form_6 = document.getElementById("form-item-input-6");
let form_7 = document.getElementById("form-item-input-7");
let submitButton = document.getElementById("form-item-submit");
let formUpdateRoommate = document.getElementById("form_update_roommate");
let check = true;

function showMessage(tagValue, messageError){
    let parentTag = tagValue.parentElement;
    let spanTag = parentTag.querySelector("span");
    spanTag.innerText = messageError;
}

function isRequire(tagNameToCheck, messageError){
    if (tagNameToCheck.value == null || tagNameToCheck.value === ""){
        showMessage(tagNameToCheck, messageError)
        check = false;
    }else{
        showMessage(tagNameToCheck, "")
        check = true;
    }
}

function isCccd(tagNameToCheck, messageError){
    if ((tagNameToCheck.value.length !== 12 && tagNameToCheck.value.length !== 9) && tagNameToCheck.value.length !== 0){
            showMessage(tagNameToCheck, messageError)
        check = false;
    }else if(tagNameToCheck.value.length === 12 || tagNameToCheck.value.length === 9){
        showMessage(tagNameToCheck, "")
        check = true;
    }
}

function isPhone(tagNameToCheck, messageError){
    const vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
    if (!(vnf_regex).test(tagNameToCheck.value) && tagNameToCheck.value.length !== 0) {
        showMessage(tagNameToCheck, messageError)
        check = false;
    }else if ((vnf_regex).test(tagNameToCheck.value) && tagNameToCheck.value != null){
        showMessage(tagNameToCheck, "")
        check = true;
    }
}

function isMail(tagNameToCheck, messageError){
    if (!tagNameToCheck.value.toLowerCase().match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/) && tagNameToCheck.value.length !== 0){
        showMessage(tagNameToCheck, messageError)
        check = false;
    }else if (tagNameToCheck.value.toLowerCase().match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/) && tagNameToCheck.value.length !== 0){
        showMessage(tagNameToCheck, "")
        check = true;
    }
}

submitButton.addEventListener("click" , () => {
    if (check){
        formUpdateRoommate.submit();
    }
})
