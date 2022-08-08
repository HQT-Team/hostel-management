function loadRoomAsync(datas, hostels) {
    const tbl = document.createElement("table");
    tbl.classList.add('content__table','table','table-bordered','table-striped');
    tbl.setAttribute('id', 'room-table');

    const tblHead = document.createElement("thead");

    const trElement = document.createElement("tr");

    for (let i = 1; i <= 3; i++) {
        let thElement = document.createElement("th");
        thElement.classList.add('text-center');
        switch (i) {
            case 1:
                thElement.innerHTML = "Khu trọ"
                break;
            case 2:
                thElement.innerHTML = "Phòng số"
                break;
            case 3:
                thElement.innerHTML = "Trạng thái"
                break;
        }
        trElement.appendChild(thElement);
    }
    tblHead.appendChild(trElement);
    tbl.appendChild(tblHead);

    const tblBody = document.createElement("tbody");

    datas.forEach((item) => {
        const trElement = document.createElement("tr");
        for (let i = 1; i <= 3; i++) {
            let tdElement = document.createElement("td");
            switch (i) {
                case 1:
                    let aElement = document.createElement('a');
                    aElement.href = `detailHostel?hostelID=${item.roomId}`;
                    aElement.innerHTML = `${hostels[item.roomId].hostelName}`;
                    aElement.classList.add('content__tbody-hostel-link');
                    tdElement.appendChild(aElement);
                    break;
                case 2:
                    let bElement = document.createElement('a');
                    bElement.href = `roomDetail?roomID=${item.roomId}&hostelID=${item.hostelId}`;
                    bElement.innerHTML = `${item.roomNumber}`;
                    bElement.classList.add('content__tbody-room-link');
                    tdElement.appendChild(bElement);
                    break;
                case 3:
                    if (item.roomStatus == 0) {
                        tdElement.innerHTML = 'Đã cho thuê';
                        tdElement.classList.add('content__tbody-status', 'no');
                    } else if (item.roomStatus == 1) {
                        tdElement.innerHTML = 'Sẵn sàng cho thuê';
                        tdElement.classList.add('content__tbody-status', 'yes');
                    } else if (item.roomStatus == -1) {
                        tdElement.innerHTML = 'Đang tiến hành làm hợp đồng';
                        tdElement.classList.add('content__tbody-status', 'wait');
                    }
                    break;
            }
            trElement.appendChild(tdElement);
        }
        tblBody.appendChild(trElement);
    });
    tbl.appendChild(tblBody);

    const roomContainer = document.querySelector('#list-rooms-container');
    roomContainer.innerHTML = "";
    roomContainer.appendChild(tbl);
    $('#list-rooms-container table').DataTable({
        "order": [],
    });
}