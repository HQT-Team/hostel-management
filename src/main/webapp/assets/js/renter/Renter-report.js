var select = document.getElementById("select");
var input = document.getElementById("form-input");
var submit = document.getElementById("Submit");
function showMessageError(data, x, mes) {
    if (x == 0) {
        let parent = data.parentElement;
        let tagmes = parent.querySelector("#mes-1");
        tagmes.innerText = mes;
        return false;
    } else {
        let parent = data.parentElement;
        let tagmes = parent.querySelector("#mes-2");
        tagmes.innerText = mes;
        return false;
    }
}

function showMessageSuccess(data, x) {
    if (x == 0) {
        let parent = data.parentElement;
        let tagmes = parent.querySelector("#mes-1");
        tagmes.innerText = '';
        return true;
    } else {
        let parent = data.parentElement;
        let tagmes = parent.querySelector("#mes-2");
        tagmes.innerText = '';
        return true;
    }
}


function checkEmpty(listinput) {
    var check = true;
    let x = 0;
    listinput.forEach(input => {
        input.value = input.value.trim();
        if (!input.value) {
            check = showMessageError(input, x, "Không được để trống!")
        } else {
            showMessageSuccess(input, x);
        }
        x++;
    });
    return check;
}

function validateDatetime(input) {
    const datetime = new Date(input);
    return (datetime instanceof Date && !isNaN(datetime.valueOf()));
}

// console.log(input.querySelector("p").innerText)
// input.querySelector("p").addEventListener("focus", (event) => {
//     console.log(input.innerText + "123")
//         let parent = input.parentElement;
//         let tagmes = parent.querySelector("#mes-2");
//         tagmes.innerText = "";
// })
// input.querySelector("p").addEventListener("blur", (event) => {
//     console.log(input.innerText + "123")
//     if (input.innerText == null){
//         let parent = input.parentElement;
//         let tagmes = parent.querySelector("#mes-2");
//         tagmes.innerText = "Không được để trống!";
//     }
// })


submit.addEventListener('click', function (e) {
    let check = true;
    e.preventDefault();
    // check = checkEmpty([select, input])
    if (check) {
        document.querySelector("#form-submit").submit();
    }
});
