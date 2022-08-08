function loadNotificationAsync(data, hostels) {
    const notiContainer = document.querySelector('#list-notifications-container');

    const tbl = document.createElement("table");
    tbl.classList.add('content__table','table','table-bordered','table-striped');
    tbl.setAttribute('id', 'notification-table-render');

    const tblHead = document.createElement("thead");
    tblHead.classList.add('content__thead');

    const trElement = document.createElement("tr");

    for (let i = 1; i <= 4; i++) {
        let thElement = document.createElement("th");
        thElement.classList.add('text-center');
        switch (i) {
            case 1:
                thElement.innerHTML = "Mã"
                break;
            case 2:
                thElement.innerHTML = "Tiêu đề"
                break;
            case 3:
                thElement.innerHTML = "Ngày gửi"
                break;
            case 4:
                thElement.innerHTML = "Khu trọ"
                break;
        }
        trElement.appendChild(thElement);
    }
    tblHead.appendChild(trElement);
    tbl.appendChild(tblHead);

    const tblBody = document.createElement("tbody");
    tblBody.classList.add('content__tbody');

    data.forEach((item) => {
        const trElement = document.createElement("tr");
        for (let i = 1; i <= 4; i++) {
            let tdElement = document.createElement("td");
            switch (i) {
                case 1:
                    tdElement.classList.add('text-center');
                    let aElement = document.createElement('a');
                    aElement.href = `owner-review-notification?notification_id=${item.notification_id}`;
                    aElement.innerHTML = `#NF${item.notification_id}`;
                    tdElement.appendChild(aElement);
                    break;
                case 2:
                    let bElement = document.createElement('a');
                    bElement.href = `owner-review-notification?notification_id=${item.notification_id}`;
                    bElement.innerHTML = `${item.title}`;
                    tdElement.appendChild(bElement);
                    break;
                case 3:
                    let dateArr = item.createDate.split('-');
                    tdElement.innerHTML = `${dateArr[2].split(' ')[0]}/${dateArr[1]}/${dateArr[0]}`;
                    tdElement.classList.add('text-center');
                    break;
                case 4:
                    tdElement.innerHTML = hostels[item.hostel_id].hostelName;
                    tdElement.classList.add('text-center');
                    break;
            }
            trElement.appendChild(tdElement);
        }
        tblBody.appendChild(trElement);
    });
    tbl.appendChild(tblBody);

    notiContainer.innerHTML = "";
    notiContainer.appendChild(tbl);
    $('#list-notifications-container table').DataTable({
        "order": [],
    });
}