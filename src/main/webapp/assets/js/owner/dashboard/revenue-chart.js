let revenueChart = document.getElementById("revenueChart").getContext("2d");

let revenueStatisticChart = new Chart(revenueChart, {
  type: "bar", // Bar, horizontalBar, pie, line, doughnut, radar, polarArea
  data: {
    labels: [
      "Tháng 9",
      "Tháng 10",
      "Tháng 11",
      "Tháng 12",
      "Tháng 1",
      "Tháng 2",
    ],
    datasets: [
      {
        data: [85000000, 92330000, 81230000, 112240000, 96500000, 88250000],
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
