var tds = document.querySelectorAll("td");
var table = document.querySelector(".table-content");
var content = document.querySelector(".content");
if (tds.length == 0) {
    table.style.display = "none";
    let h1 = document.createElement("h1");
    h1.innerText = "Bạn chưa có hoá đơn nào!";
    Object.assign(h1.style, {
        color: "red",
        textAlign: "center",
        marginTop: "50px",
    });
    content.appendChild(h1);
} else {
    tds.forEach((td) => {
        if (td.innerText == "Chưa Thanh Toán") {
            td.style.color = "red";
        } else if (td.innerText == "Đã Thanh Toán") {
            td.style.color = "blue";
        }
    });
}
