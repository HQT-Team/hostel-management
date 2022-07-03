var input_1 = document.getElementById("input-1")
var input_2 = document.getElementById("input-2")
var input_3 = document.getElementById("input-3")
var input_4 = document.getElementById("input-4")
var input_5 = document.getElementById("input-5")
var input_6 = document.getElementById("input-6")
var submit = document.getElementById("submit-1")
var list = [input_1, input_2, input_3, input_4, input_5, input_6]
//validate function
function checkName(input, length) {
    const regex = /\d/
    if (input.length <= length && !regex.test(input)) {
        return true
    }
    return false;
}

function checkEmail(input) {
    const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    return regex.test(input)
}

function checkDate(dateString) {
    var date_regex = /^(0[1-9]|1\d|2\d|3[01])\-(0[1-9]|1[0-2])\-(19|20)\d{2}$/;
    if (!(date_regex.test(dateString))) {
        console.log("false 1")
        return false;
    }
    var parts = dateString.split(/[-]/);
    var year = parseInt(parts[2], 10);
    var month = parseInt(parts[1], 10);
    var day = parseInt(parts[0], 10);
    const str = year + "-" + month + "-" + day;
    const date1 = new Date()
    const date2 = new Date(str)
    if (date1 < date2) {
        return false
    }
    if (year < 1000 || year > 3000 || month == 0 || month > 12)
        return false;

    var monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
        monthLength[1] = 29;
    return day > 0 && day <= monthLength[month - 1];
}

function checkPhone(input) {
    const regex = /((09|03|07|08|05|02)+([0-9]{8,9})\b)/g;
    if (!(regex).test(input)) {
        return false
    }
    return true
}

function checkNotEmpty(input) {
    return !input.length == 0
}
function checkCCCD(input) {
    const regex = /\d{12}/
    if (input.length == 12 && regex.test(input)) {
        return true
    }
    return false
}

//manage check
function check(list) {
    list.forEach(input => {
        input.value = input.value.trim()
        if (checkNotEmpty(input.value)) {
            let parent = input.parentElement
            let span = parent.querySelector("span")
            span.innerText = ''
        } else {
            let parent = input.parentElement
            let span = parent.querySelector("span")
            span.innerText = 'Không được để trống!'
        }
    });
}


//test
submit.addEventListener('click', function () {
    var check = true
    if (input_2.value) {
        var value = checkEmail(input_2.value)
        if (!value) {
            check = false
            input_2.parentElement.querySelector("#mes-2").innerText = "Vui Lòng Nhập Đúng Định Dạng Email (abc@gmail.com)"
        } else {
            input_2.parentElement.querySelector("#mes-2").innerText = ""
        }
    } else {
        input_2.parentElement.querySelector("#mes-2").innerText = ""
    }

    if (input_3.value) {
        var value = checkDate(input_3.value)
        if (!value) {
            check = false
            input_3.parentElement.querySelector("#mes-3").innerText = "Vui Lòng Nhập Đúng Định Dạng Ngày (dd-mm-yyyy)"
        } else {
            input_3.parentElement.querySelector("#mes-3").innerText = ""
        }
    } else {
        input_3.parentElement.querySelector("#mes-3").innerText = ""
    }


    if (input_4.value) {
        var value = checkPhone(input_4.value)
        if (!value) {
            check = false
            input_4.parentElement.querySelector("#mes-4").innerText = "Vui Lòng Nhập Đúng Định Dạng Số Điện Thoại!"
        } else {
            input_4.parentElement.querySelector("#mes-4").innerText = ""
        }
    } else {
        input_4.parentElement.querySelector("#mes-4").innerText = ""
    }

    if (input_6.value) {
        var value = checkPhone(input_6.value)
        if (!value) {
            check = false
            input_6.parentElement.querySelector("#mes-6").innerText = "Vui Lòng Nhập Đúng Căn Cước Công Dân!"
        } else {
            input_6.parentElement.querySelector("#mes-6").innerText = ""
        }
    } else {
        input_6.parentElement.querySelector("#mes-6").innerText = ""
    }



    var count = 0
    list.forEach(input => {
        checkNotEmpty(input.value)
        if (!checkNotEmpty(input.value)) {
            ++count
        }
        if (count == 6) {
            check = false
        }
    })


    if (check) {
        document.getElementById("myForm").submit()
    }
})
