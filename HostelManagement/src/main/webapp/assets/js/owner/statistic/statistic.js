//get selection
var selectHostel = document.getElementById("select-hostel");
var selectYear = document.getElementById("select-year");
var selectQuater = document.getElementById("quater");

//get Chart
var chart = document.getElementById("chart");
var chartwithquater = document.getElementById("chartwithquater");
var myBarChart = document.getElementById("myBarChart");
var myLineChart = document.getElementById("myLineChart");
var chartwithyear = document.getElementById("chartwithyear");
var myBarChartWithYear = document.getElementById("myBarChartWithYear");
var myLineChartWithYear = document.getElementById("myLineChartWithYear");

//Get form search
var form = document.getElementById("form-search");
if (selectQuater.value == "quater_1"){
    var listMonth = ["Tháng 1", "Tháng 2", "Tháng 3"];
    chartwithyear.style.display = "none";
    console.log("123");
}
if (selectQuater.value == "quater_2"){
    var listMonth = ["Tháng 4", "Tháng 5", "Tháng 6"];
    chartwithyear.style.display = "none";
    console.log("456");
}
if (selectQuater.value == "quater_3"){
    var listMonth = ["Tháng 7", "Tháng 8", "Tháng 9"];
    chartwithyear.style.display = "none";
    // newBarChartWithQuater();
    // newLineChartWithQuater();
}
if (selectQuater.value == "quater_4"){
    var listMonth = ["Tháng 10", "Tháng 11", "Tháng 12"];
    chartwithyear.style.display = "none";
    // newBarChartWithQuater();
    // newLineChartWithQuater();
}
if (selectQuater.value == "all-quater"){
    chartwithquater.style.display = "none";
    chartwithyear.style.display = "block";
    var listMonth = ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4","Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"];
    // newBarChartWithYear();
    // newLineChartWithYear();
}

//Char bar with quater
function newBarChartWithQuater(list, listMonthTmp) {
    var canvasElement = document.getElementById("myBarChart");
    var ctx = canvasElement.getContext("2d");
    new Chart("myBarChart", {
        type: "bar",
        data: {
            labels: [...listMonthTmp],
            datasets: [
                {
                    label: "Doanh Thu (VND)",
                    data: [...list],
                    backgroundColor: [
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                    ],
                },
            ],
        },

    });
}

//chart bar with year
function newBarChartWithYear(list) {
    var canvasElement = document.getElementById("myBarChartWithYear");
    var ctx = canvasElement.getContext("2d");
    new Chart("myBarChartWithYear", {
        type: "bar",
        data: {
            labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4","Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
            datasets: [
                {
                    label: "Doanh Thu (VND)",
                    data: [...list],
                    backgroundColor: [
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                        "rgba(75, 192, 192, 0.8)",
                    ],
                },
            ],
        },
    });
}

//chart line with quater
function newLineChartWithQuater(listCreate, listCancel, listMonthTmp) {
    var canvasElement = document.getElementById("myLineChart");
    var ctx = canvasElement.getContext("2d");
    new Chart("myLineChart", {
        type: "line",
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero:true,
                        min: 0,
                        max: 500
                    }
                }]
            }
        },
        data: {
            labels: [...listMonthTmp],
            datasets: [
                {
                    label: "Trả Phòng",
                    data: [...listCancel],
                    borderColor: "red",
                    fill: false,
                },
                {

                    label: "Thuê Phòng",
                    data: [...listCreate],
                    borderColor: "green",
                    fill: false,
                },
            ],
        },

    });
}

//chart line with year
function newLineChartWithYear(listCreate, listCancel) {
    var canvasElement = document.getElementById("myLineChartWithYear");
    var ctx = canvasElement.getContext("2d");
    new Chart("myLineChartWithYear", {
        type: "line",
        data: {
            labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4","Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
            datasets: [
                {
                    label: "Trả Phòng",
                    data: [...listCancel],
                    borderColor: "red",
                    fill: false,
                },
                {

                    label: "Thuê Phòng",
                    data: [...listCreate],
                    borderColor: "green",
                    fill: false,
                },
            ],
        },
        options: {
            legend: { display: false },
        },
    });
}

// First render UI
// var renderNumber = document.getElementById("render-number");
// if (selectHostel.value != null && selectYear.value != null && selectQuater.value != null){
//     form.submit();
// }

//handel change search
selectHostel.addEventListener("change" , () => {
    form.submit();
})
selectYear.addEventListener("change" , () => {
    form.submit();
})
selectQuater.addEventListener("change" , () => {
    form.submit();
})
