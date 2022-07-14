<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <!-- Favicon -->
  <link
          rel="icon"
          href="./assets/images/favicon/favicon.png"
          type="image/x-icon"
  />

  <!-- Title -->
  <title>Tổng quan</title>

  <!-- Link Bootstrap !important -->
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous"
  />
  <script
          src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.min.js"
          integrity="sha512-sW/w8s4RWTdFFSduOTGtk4isV1+190E/GghVffMA9XczdJ2MDzSzLEubKAs5h0wzgSJOQTRYyaz73L3d6RtJSg=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer"
  ></script>
  <!-- Core CSS -->
  <link rel="stylesheet" href="./assets/css/core_style/core.css"/>

  <!-- Link your own CSS here -->
  <link
          rel="stylesheet"
          href="./assets/css/hostel_owner_style/statistic/style.css"
  />

  <!-- Simple Datatable CSS -->
  <link
          href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css"
          rel="stylesheet"
          type="text/css"
  />
</head>

<body>
<input type="hidden" name="render-number" value="" id="render-number"/>
<div class="app">
  <!-- Navbar -->
  <div class="main-nav bg-white">
    <div class="container">
      <div class="row main-nav-body">
        <div class="col-3 col-lg-3 col-xl-3 col-xxl-2">
          <div class="main-nav__logo">
            <a href="" class="main-nav__logo-link">
              <img
                      class="main-nav__logo-img"
                      src="./assets/images/logos/logo.png"
                      alt="Logo"
              />
            </a>
          </div>
        </div>
        <div class="col-9 col-lg-9 col-xl-9 col-xxl-10 wrapper">
          <div class="main-nav__label">
            <h3 class="title">Thống Kê</h3>
          </div>
          <div class="main-nav__action">
            <div id="nav-notification-btn" class="notification">
              <i class="notification__icon fa-solid fa-bell"></i>

              <!-- Remove class "active" when don't have any new notification -->
              <span class="notification__warning active"
              ><i class="fa-solid fa-exclamation"></i
              ></span>
            </div>
            <div id="nav-profile-btn" class="profile">
              <div class="profile__infor">
                <h3 class="infor__name">Nguyễn Lâm Thảo Tâm</h3>
                <span class="infor__role">Chủ phòng trọ</span>
              </div>
              <div class="profile__avatar">
                <img
                        class="avatar__img"
                        src="./assets/images/avatars/user-avatar.jpg"
                        alt="User avatar image"
                />
              </div>
            </div>
            <div id="menu-sidebar-btn" class="menu-sidebar-btn">
              <i class="fa-solid fa-bars"></i>
            </div>
          </div>
        </div>

        <!-- List notification -->
        <div id="notification-list" class="notification__list">
          <div class="notification__list-header">
            <h3>Thông báo</h3>
            <a href="">Xem tất cả</a>
          </div>
          <ul class="notification__list-items">
            <!-- If this notification has been read, please add more class "readed" -->
            <li class="notification__item">
              <a href="">
                <div class="marker"></div>
                <div class="notification__item-img">
                  <i
                          class="notification__item-icon fa-solid fa-circle-exclamation"
                  ></i>
                </div>
                <div class="notification__item-info">
                  <div class="notification__item-name">
                    <div class="room">Phòng số 11</div>
                    <div class="hostel">
                      <span>Khu trọ:</span> Nova land
                    </div>
                  </div>
                  <div class="notification__item-content">
                    Anh ơi bóng đèn trong nhà vệ sinh phòng em bị hư, mong
                    anh sớm qua sửa chữa giúp em với ạ!
                  </div>
                  <div class="notification__item-time">18 tiếng trước</div>
                </div>
              </a>
            </li>
            <li class="notification__item">
              <a href="">
                <div class="marker"></div>
                <div class="notification__item-img">
                  <i
                          class="notification__item-icon report fa-solid fa-circle-exclamation"
                  ></i>
                </div>
                <div class="notification__item-info">
                  <div class="notification__item-name">
                    <div class="room">Phòng số 11</div>
                    <div class="hostel">
                      <span>Khu trọ:</span> Nova land
                    </div>
                  </div>
                  <div class="notification__item-content">
                    Anh ơi bóng đèn trong nhà vệ sinh phòng em bị hư, mong
                    anh sớm qua sửa chữa giúp em với ạ!
                  </div>
                  <div class="notification__item-time">18 tiếng trước</div>
                </div>
              </a>
            </li>
            <li class="notification__item readed">
              <a href="">
                <div class="marker"></div>
                <div class="notification__item-img">
                  <i
                          class="notification__item-icon success fa-solid fa-circle-exclamation"
                  ></i>
                </div>
                <div class="notification__item-info">
                  <div class="notification__item-name">
                    <div class="room">Phòng số 11</div>
                    <div class="hostel">
                      <span>Khu trọ:</span> Nova land
                    </div>
                  </div>
                  <div class="notification__item-content">
                    Anh ơi bóng đèn trong nhà vệ sinh phòng em bị hư, mong
                    anh sớm qua sửa chữa giúp em với ạ!
                  </div>
                  <div class="notification__item-time">18 tiếng trước</div>
                </div>
              </a>
            </li>
            <li class="notification__item">
              <a href="">
                <div class="marker"></div>
                <div class="notification__item-img">
                  <i
                          class="notification__item-icon fa-solid fa-circle-exclamation"
                  ></i>
                </div>
                <div class="notification__item-info">
                  <div class="notification__item-name">
                    <div class="room">Phòng số 11</div>
                    <div class="hostel">
                      <span>Khu trọ:</span> Nova land
                    </div>
                  </div>
                  <div class="notification__item-content">
                    Anh ơi bóng đèn trong nhà vệ sinh phòng em bị hư, mong
                    anh sớm qua sửa chữa giúp em với ạ!
                  </div>
                  <div class="notification__item-time">18 tiếng trước</div>
                </div>
              </a>
            </li>
            <li class="notification__item">
              <a href="">
                <div class="marker"></div>
                <div class="notification__item-img">
                  <i
                          class="notification__item-icon fa-solid fa-circle-exclamation"
                  ></i>
                </div>
                <div class="notification__item-info">
                  <div class="notification__item-name">
                    <div class="room">Phòng số 11</div>
                    <div class="hostel">
                      <span>Khu trọ:</span> Nova land
                    </div>
                  </div>
                  <div class="notification__item-content">
                    Anh ơi bóng đèn trong nhà vệ sinh phòng em bị hư, mong
                    anh sớm qua sửa chữa giúp em với ạ!
                  </div>
                  <div class="notification__item-time">18 tiếng trước</div>
                </div>
              </a>
            </li>
          </ul>
        </div>

        <!-- Profile dropdown -->
        <div id="nav-profile-dropdown" class="profile__actions">
          <a href="" class="action__view-profile-link">
            <div class="action__image">
              <img
                      src="./assets/images/avatars/user-avatar.jpg"
                      alt=""
              />
            </div>
            <div class="action__content">
              <div class="title">Nguyễn Lâm Thảo Tâm</div>
              <span class="subtitle">Xem trang cá nhân của bạn</span>
            </div>
          </a>
          <div class="spacer"></div>
          <a href="" class="action__logout">
            <div class="action__image">
              <i class="fa-solid fa-arrow-right-from-bracket"></i>
            </div>
            <div class="action__content">
              <div class="title">Đăng xuất</div>
            </div>
          </a>
        </div>
      </div>
    </div>
  </div>
  <!-- side bar -->
  <div class="row">
    <div class="side-bar" style="width: 20%">
      <div id="main-side-bar" class="side-bar pt-5">
        <div class="group-option">
          <a href="" class="group-option__link">
            <i class="group-option__icon fa-solid fa-gauge-high"></i>
            <div class="group-option__label">Tổng quan</div>
          </a>
        </div>
        <div class="group-option">
          <a href="./hostel.html" class="group-option__link">
            <i class="group-option__icon fa-solid fa-hotel"></i>
            <div class="group-option__label">Khu trọ</div>
          </a>
        </div>
        <div class="group-option active">
          <a href="" class="group-option__link">
            <i class="group-option__icon fa-solid fa-door-open"></i>
            <div class="group-option__label">Phòng trọ</div>
          </a>
        </div>
        <div class="group-option">
          <a href="" class="group-option__link">
            <i
                    class="group-option__icon fa-solid fa-envelope-open-text"
            ></i>
            <div class="group-option__label">Thông báo</div>
          </a>
        </div>
        <div class="group-option">
          <a href="" class="group-option__link">
            <i
                    class="group-option__icon fa-solid fa-triangle-exclamation"
            ></i>
            <div class="group-option__label">Báo cáo</div>
          </a>
        </div>
        <div class="group-option">
          <a href="" class="group-option__link">
            <i
                    class="group-option__icon fa-solid fa-triangle-exclamation"
            ></i>
            <div class="group-option__label">Thống kê</div>
          </a>
        </div>
      </div>
    </div>
    <!-- your content here -->
    <div class="content" style="width: 80%">
      <div class="statistic-content">
        <div class="title"><h2>Thống Kê</h2></div>
        <form action="statisticServlet" method="POST" id="form-search">
          <div class="search">
            <div class="search-hostel">
              <select name="select-hostel" id="select-hostel">
                <c:forEach items="${requestScope.listHostel}" var="hostel">
                  <option value="${hostel.getHostelName()}"
                          id="${hostel.getHostelName()}">${hostel.getHostelName()}</option>
                </c:forEach>
              </select>
            </div>
            <div class="search-year">
              <select name="select-year" id="select-year">
                <c:forEach items="${requestScope.listYear}" var="year">
                  <option value="${year}" id="${year}">${year}</option>
                </c:forEach>
              </select>
            </div>
            <div class="search-quater">
              <input type="hidden" value=""/>
              <select name="select-quater" id="quater">
                <option id="quater_1" value="quater_1">Quý 1</option>
                <option id="quater_2" value="quater_2">Quý 2</option>
                <option id="quater_3" value="quater_3">Quý 3</option>
                <option id="quater_4" value="quater_4">Quý 4</option>
                <option id="all-quater" value="all-quater">Tất cả</option>
              </select>
            </div>
          </div>
        </form>
        <div class="money">
          <div class="income">
            <div>
              <h3>${requestScope.totalMoney} VND</h3>
              <p>Thu nhập</p>
            </div>
          </div>
          <div class="expense">
            <div>
              <h3>${requestScope.expenseMoney} VND</h3>
              <p>Chi phí</p>
            </div>
          </div>
          <div class="revenue">
            <div>
              <h3>${requestScope.revenueMoney} VND</h3>
              <p>Doanh thu</p>
            </div>
          </div>
        </div>
        <div class="chart" id="chart">
          <div class="chartwithquater" id="chartwithquater">
            <div>
              <canvas id="myBarChart"></canvas>
            </div>
            <div>
              <canvas id="myLineChart"></canvas>
            </div>
          </div>
          <div class="chartwithyear" id="chartwithyear">
            <div>
              <canvas id="myBarChartWithYear"></canvas>
            </div>
            <div>
              <canvas id="myLineChartWithYear"></canvas>
            </div>
          </div>
        </div>
        <div class="summary">
          <div class="top-revenue">
            <h3>Báo Cáo</h3>
            <table class="content-table">
              <thead>
              <tr>
                <th>Số lượng</th>
                <th>Tỷ lệ phản hồi</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <th>${requestScope.listReport.size()}</th>
                <th>${requestScope.rate}%</th>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="room-infor">
            <h3>Phòng Trọ</h3>
            <p><strong>Đang cho thuê:</strong> ${requestScope.numberEmpty}</p>
            <p><strong>Phòng Trống:</strong> ${requestScope.numberRenting}</p>
            <p><strong>Đang làm hợp đồng:</strong> ${requestScope.numberContract}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- Footer -->
  <footer>
    <div class="container">
      <div class="row">
        <div class="col-12">
          <div class="copyright-wrapper d-flex justify-content-center">
            <div class="copyright-content">
              © 2022 HQT Team. All rights reserved.
            </div>
          </div>
        </div>
      </div>
    </div>
  </footer>
</div>

<!-- Script Bootstrap !important -->

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"
></script>
<!-- JQuery -->
<script
        src="./assets/js/jquery-3.5.1.min.js"
        type="text/javascript"
></script>
<!-- Link your script here -->
<script src="./assets/js/handle-main-navbar.js"></script>
<!-- Chart JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.min.js"></script>
<script src="./assets/js/owner/statistic/statistic.js"></script>
<script>
  if (${requestScope.hostelName eq null ? false : true}) {
    document.getElementById("${requestScope.hostelName}").setAttribute("selected", "selected");
  }
  if (${requestScope.year eq null ? false : true}) {
    document.getElementById("${requestScope.year}").setAttribute("selected", "selected");
  }
  if (${requestScope.quater eq null ? false : true}) {
    document.getElementById("${requestScope.quater}").setAttribute("selected", "selected");
  }
  var listCancel = [${requestScope.contractCancelRate1}, ${requestScope.contractCancelRate2}, ${requestScope.contractCancelRate3}];
  var listCreate = [${requestScope.contractCreateRate1}, ${requestScope.contractCreateRate2}, ${requestScope.contractCreateRate3}];
  var list = [${requestScope.totalMoneyMonth1}, ${requestScope.totalMoneyMonth2}, ${requestScope.totalMoneyMonth3}];
  let listMonthtmp = [1, 2, 3];
  if (${requestScope.quater eq "quater_1"})
    listMonthtmp = [1, 2, 3];
  if (${requestScope.quater eq "quater_2"})
    listMonthtmp = [4, 5, 6];
  if (${requestScope.quater eq "quater_3"})
    listMonthtmp = [7, 8, 9];
  if (${requestScope.quater eq "quater_4"})
    listMonthtmp = [10, 11, 12];
  newBarChartWithQuater(list, listMonthtmp);
  newLineChartWithQuater(listCreate, listCancel, listMonthtmp);
  if (${requestScope.quater eq "all-quater"}) {
    var list = [
      ${requestScope.month_1},
      ${requestScope.month_2},
      ${requestScope.month_3},
      ${requestScope.month_4},
      ${requestScope.month_5},
      ${requestScope.month_6},
      ${requestScope.month_7},
      ${requestScope.month_8},
      ${requestScope.month_9},
      ${requestScope.month_10},
      ${requestScope.month_11},
      ${requestScope.month_12}
    ];
    var listCreate = [
      ${requestScope.createMonth_1},
      ${requestScope.createMonth_2},
      ${requestScope.createMonth_3},
      ${requestScope.createMonth_4},
      ${requestScope.createMonth_5},
      ${requestScope.createMonth_6},
      ${requestScope.createMonth_7},
      ${requestScope.createMonth_8},
      ${requestScope.createMonth_9},
      ${requestScope.createMonth_10},
      ${requestScope.createMonth_11},
      ${requestScope.createMonth_12}
    ];
    var listCancel = [
      ${requestScope.cancelMonth_1},
      ${requestScope.cancelMonth_2},
      ${requestScope.cancelMonth_3},
      ${requestScope.cancelMonth_4},
      ${requestScope.cancelMonth_5},
      ${requestScope.cancelMonth_6},
      ${requestScope.cancelMonth_7},
      ${requestScope.cancelMonth_8},
      ${requestScope.cancelMonth_9},
      ${requestScope.cancelMonth_10},
      ${requestScope.cancelMonth_11},
      ${requestScope.cancelMonth_12}
    ];
    chartwithquater.style.display = "none";
    chartwithyear.style.removeProperty("display");
    newBarChartWithYear(list);
    newLineChartWithYear(listCreate, listCancel);
  }
</script>
</body>
</html>