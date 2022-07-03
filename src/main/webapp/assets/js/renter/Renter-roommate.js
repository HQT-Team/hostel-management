var td = document.querySelector("td");
if (td === "" || td === null) {
    var h1 = document.createElement("h1");
    h1.innerText = "Bạn chưa có bạn cùng phòng!";
    Object.assign(h1.style, { color: "red", textAlign: "center" });
    document.querySelector(".content").appendChild(h1);
}