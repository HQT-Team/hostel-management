function loadReportNoReplyAsync(data, type) {

    const tbl = document.createElement("table");
    tbl.classList.add('content__table','table','table-bordered','table-striped');
    tbl.setAttribute('id', `report-table-${type}`);

    const tblHead = document.createElement("thead");
    tblHead.classList.add('content__thead');

    const trElement = document.createElement("tr");

    for (let i = 1; i <= 6; i++) {
        let thElement = document.createElement("th");
        thElement.classList.add('text-center');
        switch (i) {
            case 1:
                thElement.innerHTML = "Mã"
                break;
            case 2:
                thElement.innerHTML = "Loại"
                break;
            case 3:
                thElement.innerHTML = "Phòng số"
                break;
            case 4:
                thElement.innerHTML = "Khu trọ"
                break;
            case 5:
                thElement.innerHTML = "Người gửi"
                break;
            case 6:
                thElement.innerHTML = "Ngày gửi"
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
        for (let i = 1; i <= 6; i++) {
            let tdElement = document.createElement("td");
            switch (i) {
                case 1:
                    tdElement.classList.add('text-center');
                    let aElement = document.createElement('a');
                    aElement.href = `report-detail?reportId=${item.report.reportID}`;
                    aElement.innerHTML = `#RP${item.report.reportID}`;
                    tdElement.appendChild(aElement);
                    break;
                case 2:
                    tdElement.innerHTML = item.category.cateTitle;
                    tdElement.classList.add('text-center');
                    break;
                case 3:
                    tdElement.innerHTML = item.room.roomNumber;
                    tdElement.classList.add('text-center');
                    break;
                case 4:
                    tdElement.innerHTML = item.hostel.hostelName;
                    tdElement.classList.add('text-center');
                    break;
                case 5:
                    tdElement.innerHTML = item.renterInformation.fullname;
                    tdElement.classList.add('text-center');
                    break;
                case 6:
                    let dateArr = item.report.sendDate.split('-');
                    tdElement.innerHTML = `${dateArr[2].split(' ')[0]}/${dateArr[1]}/${dateArr[0]}`;
                    tdElement.classList.add('text-center');
                    break;
            }
            trElement.appendChild(tdElement);
        }
        tblBody.appendChild(trElement);
    });
    tbl.appendChild(tblBody);

    const reportContainer = document.querySelector(`#content__body-${type}`);
    reportContainer.innerHTML = "";
    reportContainer.appendChild(tbl);
    $(`#content__body-${type} table`).DataTable({
        "order": [],
    });
}

function loadListRoomsAsync(data, currentRoom, type) {
    const listRoomContainer = document.querySelector(`#filter__room-select-${type}`);

    listRoomContainer.innerHTML = "";

    let defaultOption = document.createElement('option');
    defaultOption.value = "";
    defaultOption.innerHTML = "Tất cả";
    if (currentRoom == "") {
        defaultOption.setAttribute("selected", "true");
    }
    listRoomContainer.appendChild(defaultOption);

    if (typeof data === 'object') {
        listRoomContainer.removeAttribute("disabled");

        data.forEach((item) => {
            let option = document.createElement('option');
            option.value = item.roomId;
            option.innerHTML = item.roomNumber;
            if (currentRoom == item.roomId) {
                option.setAttribute("selected", "true");
            }
            listRoomContainer.appendChild(option);
        });

    } else {
        listRoomContainer.setAttribute("disabled", "true");
    }

    $(`#filter__room-select-${type}`).select2();
}