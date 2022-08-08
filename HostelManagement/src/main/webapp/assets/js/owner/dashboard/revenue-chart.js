let revenueChart = document.getElementById("revenueChart").getContext("2d");
// var date = new Date();
// var month = date.getMonth();
// var months = [month - 6, month - 5, month - 4, month -3 , month -2 , month -1];
let revenueStatisticChart = new Chart(revenueChart, {
  type: "bar", // Bar, horizontalBar, pie, line, doughnut, radar, polarArea
  data: {
    labels: [...listMonth],
    datasets: [
      {
        data: [...listMoney],
        backgroundColor: [
          "#eaf5ff",
          "#eaf5ff",
          "#eaf5ff",
          "#eaf5ff",
          "#eaf5ff",
          "#3b89fc",
        ],
      },
    ],
  },
  options: {
    plugins: {
      title: {
        display: true,
        text: "Biểu đồ thống kê",
        color: "#000",
        font: {
          size: 26,
        },
        padding: {
          top: 10,
          bottom: 30,
        },
      },
      legend: {
        display: false,
      },
    },
    scales: {
      xAxis: {
        grid: {
          display: false,
        },
      },
      yAxis: {
        grid: {
          display: false,
        },
      },
    },
  },
});