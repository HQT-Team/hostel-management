var form = document.getElementById("form")
var form_item = document.getElementById("form-item")
var input_1 = document.getElementById("form-item-input-1")
var input_2 = document.getElementById("form-item-input-2")
var input_3 = document.getElementById("form-item-input-3")
var input_4 = document.getElementById("form-item-input-4")
var input_5 = document.getElementById("form-item-input-5")
var input_6 = document.getElementById("form-item-input-6")
var input_7 = document.getElementById("form-item-input-7")
var submit = document.getElementById("form-item-submit")

function showMessageError(input, x) {

    let parent = input.parentElement;
    let str = "#mes-" + x;
    let span = parent.querySelector(str);
    span.innerText = "Không được để trống!";
    return false;

}

function showMessageSuccess(input, x) {

    let parent = input.parentElement;
    let str = "#mes-" + x;
    let span = parent.querySelector(str);
    span.innerText = "";

}

function isValidDate(dateString) {
    // First check for the pattern
    var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
    if (!(date_regex.test(dateString.value))) {
        return false;
    }
    // Parse the date parts to integers
    var parts = dateString.value.split("/");
    var day = parseInt(parts[1], 10);
    var month = parseInt(parts[0], 10);
    var year = parseInt(parts[2], 10);

    // Check the ranges of month and year
    if (year < 1000 || year > 3000 || month == 0 || month > 12)
        return false;

    var monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    // Adjust for leap years
    if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
        monthLength[1] = 29;

    // Check the range of the day
    return day > 0 && day <= monthLength[month - 1];
};

const validateEmail = (email) => {
    return String(email.value)
        .toLowerCase()
        .match(
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
};

function checkEmpty(list) {
    let check = true
    let x = 1;
    list.forEach(input => {
        // if (x == 3) {
        //     x = 4;
        // }
        input.value = input.value.trim();
        if (!input.value) {
            check = showMessageError(input, x);
        }
        else {
            showMessageSuccess(input, x);
        }
        x++;
    });
    return check
}



submit.addEventListener('click', function () {
    let check = true;
    check = checkEmpty([input_1, input_2, input_3, input_4, input_5, input_6, input_7])
    if (document.getElementById("mes-3").value == undefined) {
        console.log(document.getElementById("mes-3"));
        var check_date = isValidDate(input_3)
        if (!check_date) {
            let parent = input_3.parentElement;
            let span = parent.querySelector("#mes-3");
            span.innerText = "Vui lòng nhập đúng ngày sinh!";
        } else {
            let parent = input_3.parentElement;
            let span = parent.querySelector("#mes-3");
            span.innerText = "";
        }
    }
    var check_email = validateEmail(input_2)
    if (!check_email) {
        let parent = input_2.parentElement;
        let span = parent.querySelector("#mes-2");
        span.innerText = "Vui lòng nhập đúng định dạng ...@gmail.com";
    } else {
        let parent = input_2.parentElement;
        let span = parent.querySelector("#mes-2");
        span.innerText = "";
    }
    if (!check_email) {
        check = check_email;
    }
    if (!check_date) {
        check = check_date;
    }
    if (check) {
        document.getElementById("form").submit();
    }
})

