let success = document.querySelector(".payment-status");
// let waiting = document.querySelector(".payment-waiting");
if (success.innerText === "Đã thanh toán"){
document.getElementById("payment-button").style.display = "none";
}
else
document.getElementById("payment-button").style.display = "block";


let key = document.querySelector("#key").value;
console.log(key);
let notification = document.getElementById("notification-p");
if (key === "HandlerStatus(status=true, content=GD Thanh cong)") {
    notification.style.display = "block";
    setTimeout(() => {
        notification.style.display = "none";
    }, 2000);
}else {
    notification.style.display = "none";
}

