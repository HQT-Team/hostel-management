<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi" id="top">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon" />

    <!-- Title -->
    <title>Trang chủ</title>

    <!-- Bootstrap 5.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Vendor CSS Files -->
    <link href="./assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="./assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="./assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
    <link href="./assets/vendor/aos/aos.css" rel="stylesheet">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- AOS JS -->
    <link rel="stylesheet" href="./assets/js/aos_js/dist/aos.css">
    <script src="./assets/js/aos_js/dist/aos.js"></script>

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/system_style/home_style/home.css">
    <link rel="stylesheet" href="./assets/css/system_style/home_style/home2.css">
</head>

<body class="position-relative over-flow-hidden">
<!-- Preloader -->
<div id="preloader">
    <div class="dots">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>
<!-- End Preloader -->

<!-- Navbar -->
<div class="home-navbar">
    <div class="container navbar-pc">
        <div class="row">
            <div class="col-3">
                <!-- Logo -->
                <div class="home-navbar__logo">
                    <a href="#top" class="home-navbar__logo-link">
                        <img class="home-navbar__logo-img" src="./assets/images/logos/logo.png" alt="Logo">
                    </a>
                </div>
            </div>
            <div class="col-9">
                <!-- Actions -->
                <div class="home-navbar__actions">
                    <div id="" class="home-navbar__links">
                        <div class="home-navbar__actions-group">
                            <a href="#hostel-owner"
                               class="home-navbar__actions-link home-navbar__actions-link--owner">
                                Chủ trọ
                            </a>
                        </div>

                        <div class="home-navbar__actions-group">
                            <a href="#renter"
                               class="home-navbar__actions-link home-navbar__actions-link--renter">
                                Người thuê
                            </a>
                        </div>

                        <div class="home-navbar__actions-group">
                            <a href="#about" class="home-navbar__actions-link home-navbar__actions-link--renter">
                                Về chúng tôi
                            </a>
                        </div>

                        <div class="home-navbar__actions-group">
                            <a href="loginPage"
                               class="home-navbar__actions-link home-navbar__actions-link--login">
                                Đăng nhập
                            </a>
                        </div>

                        <div class="home-navbar__actions-group">
                            <button class="home-navbar__actions-link home-navbar__actions-link--register">
                                Đăng ký
                                <div class="register-modal">
                                    <a href="registerPage" class="register-owner">Chủ trọ</a>
                                    <a href="renter-register-page" class="register-renter">Người thuê</a>
                                </div>
                            </button>
                        </div>
                    </div>
                    <div id="home-navbar__mobile-btn" class="home-navbar__mobile-btn">
                        <i class="fa-solid fa-bars"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Menu mobile -->
    <div id="navbar-mobile" class="container navbar-mobile">
        <div class="navbar-mobile-group">
            <div class="navbar-mobile-group-header">Đăng ký</div>
            <a href="registerPage" class="navbar-mobile-link navbar-mobile-link--owner">Chủ trọ</a>
            <a href="#" class="navbar-mobile-link navbar-mobile-link--renter">Người thuê</a>
        </div>
        <div class="navbar-mobile-group">
            <a href="loginPage" class="navbar-mobile-link navbar-mobile-link--login">Đăng nhập</a>
        </div>
    </div>
</div>
<!-- End Navbar -->

<!-- Breadcrumbs -->
<div class="breadcrumbs">
    <div class="page-header d-flex align-items-center"
         style="background-image: url('./assets/images/system/page-header.jpg');">
        <div class="container position-relative">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-6 text-center">
                    <h2>Hệ Thống Quản Lí Trọ HQT V4.0</h2>
                    <h3 class="mt-4 mb-4" style="color: rgba(255, 255, 255, 0.8);">
                        HQT Team tự hào là doanh nghiệp hàng đầu trong tư vấn, nghiên cứu và phát triển các giải
                        pháp cho doanh nghiệp. Chúng tôi tự tin có thể mang đến cho bạn một bộ phần mềm quản lý nhà
                        trọ hoàn toàn giống như chúng tôi đã quảng cáo.
                    </h3>
                    <p>
                        Chúng tôi tự tin để đưa ra điều khoản hoàn tiền 100% trong vòng 30 ngày và không có bất kì
                        một câu hỏi nào thêm
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Breadcrumbs -->

<!-- Banner -->
<div id="hostel-owner" class="banner banner-hostel-owner-wrapper">
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-6">
                <div class="banner-left">
                    <div class="banner-title" data-aos="fade-right" data-aos-duration="1000">
                        Quản lý toàn bộ khu trọ và phòng trọ ở cùng một nơi
                    </div>
                    <div class="banner-subtitle" data-aos="fade-right" data-aos-duration="1500">
                        HQT-Hostel là một nền tảng cung cấp giải pháp quản lý thuận tiện, thông minh,...
                    </div>
                    <a href="registerPage" class="banner-link mb-5 mb-lg-9" data-aos="fade-up"
                       data-aos-duration="2000">
                        Trở thành chủ nhà Ngay
                    </a>
                </div>
            </div>
            <div class="col-12 col-lg-6">
                <div class="banner-right">
                    <img src="./assets/images/banners/hostel-1.png" alt="Hostel owner banner" class="banner-image"
                         data-aos="flip-down" data-aos-duration="2000">
                </div>
            </div>
        </div>
    </div>
</div>
<div id="renter" class="banner banner-renter-wrapper">
    <div class="bg-holder" style="background-image: url('./assets/images/background/shape.png');"></div>
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-6">
                <div class="banner-right">
                    <img src="./assets/images/banners/hostel-2.png" alt="Hostel owner banner" class="banner-image"
                         data-aos="flip-up" data-aos-duration="1500">
                </div>
            </div>
            <div class="col-12 col-lg-6">
                <div class="banner-left">

                    <div class="banner-title mt-5 mt-lg-0" data-aos="fade-down-left" data-aos-duration="1500">
                        Kết nối với chủ nhà nhanh chóng và phản hồi sự cố kịp thời
                    </div>
                    <div class="banner-subtitle" data-aos="zoom-in" data-aos-duration="1000">
                        HQT-Hostel là một nền tảng cho phép người thuê dễ dàng kết nối với
                        chủ trọ, xem thông tin và hàng loạt tính năng mới
                    </div>
                    <a href="renter-register-page" class="banner-link renter-link" data-aos="fade-up" data-aos-duration="1000">
                        Trở thành người thuê Ngay
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- #main -->
<main id="main">
    <!-- ======= About Us Section ======= -->
    <section id="about" class="about">
        <div class="container" data-aos="fade-up">
            <div class="row gy-4">
                <div class="col-lg-6 position-relative align-self-start order-lg-last order-first">
                    <img src="./assets/images/system/about.jpg" class="img-fluid" alt="About Team Image">
                    <a href="https://www.youtube.com/watch?v=tgrrWxvZ64E" class="glightbox play-btn"></a>
                </div>
                <div class="col-lg-6 content order-last  order-lg-first">
                    <h3>Về chúng tôi</h3>
                    <p class="mt-5">
                        Từ HQT Team, công ty có 1 năm kinh nghiệm trong phát triển phần mềm cho thị trường Việt Nam.
                        35+ dự án phần mềm quản lý bất động sản, căn hộ cho thuê, nhà trọ từ các tập đoàn BĐS đến
                        các cá nhân chuyên viên.
                        Đội ngũ kết hợp giữa kinh nghiệm và sức trẻ, tham vọng và cái tâm với nghề.
                    </p>
                    <ul>
                        <li data-aos="fade-up" data-aos-delay="100">
                            <i class="bi bi-diagram-3"></i>
                            <div>
                                <h5>Tích hợp mọi thứ trong 1 ứng dụng</h5>
                                <p>
                                    Quản lý cả phòng trọ, thống kê tài chính, ghi điện nước thao tác cực dễ
                                </p>
                            </div>
                        </li>
                        <li data-aos="fade-up" data-aos-delay="200">
                            <i class="bi bi-fullscreen-exit"></i>
                            <div>
                                <h5>Tiện lợi, nhanh chóng và hiệu quả</h5>
                                <p>
                                    Phần mềm quản lý trọ này giúp tối giản 80% công việc của bạn
                                </p>
                            </div>
                        </li>
                        <li data-aos="fade-up" data-aos-delay="300">
                            <i class="bi bi-broadcast"></i>
                            <div>
                                <h5>Tính năng phần mềm quản lý nhà trọ</h5>
                                <p>
                                    Không giới hạn phòng cho thuê, ghi điện tiện lợi, quản lý nhiều khu trọ ở nhiều
                                    vị trí, thông báo thu tiền hằng tháng, ...
                                </p>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
    <!-- End About Us Section -->

    <!-- ======= Stats Counter Section ======= -->
    <section id="stats-counter" class="stats-counter pt-0">
        <div class="container" data-aos="fade-up">

            <div class="row gy-4">
                <div class="col-lg-3 col-md-6">
                    <div class="stats-item text-center w-100 h-100">
                            <span data-purecounter-start="0" data-purecounter-end="232" data-purecounter-duration="1"
                                  class="purecounter"></span>
                        <p>Khách hàng</p>
                    </div>
                </div>
                <!-- End Stats Item -->

                <div class="col-lg-3 col-md-6">
                    <div class="stats-item text-center w-100 h-100">
                            <span data-purecounter-start="0" data-purecounter-end="1" data-purecounter-duration="1"
                                  class="purecounter"></span>
                        <p>Dự án</p>
                    </div>
                </div>
                <!-- End Stats Item -->

                <div class="col-lg-3 col-md-6">
                    <div class="stats-item text-center w-100 h-100">
                            <span data-purecounter-start="0" data-purecounter-end="1453" data-purecounter-duration="1"
                                  class="purecounter"></span>
                        <p>Số giờ hỗ trợ</p>
                    </div>
                </div>
                <!-- End Stats Item -->

                <div class="col-lg-3 col-md-6">
                    <div class="stats-item text-center w-100 h-100">
                            <span data-purecounter-start="0" data-purecounter-end="5" data-purecounter-duration="1"
                                  class="purecounter"></span>
                        <p>Nhân viên</p>
                    </div>
                </div>
                <!-- End Stats Item -->

            </div>

        </div>
    </section>
    <!-- End Stats Counter Section -->

    <!-- ======= Our Team Section ======= -->
    <section id="team" class="team pt-0">
        <div class="container" data-aos="fade-up">

            <div class="section-header">
                <span>Đội ngũ chúng tôi</span>
                <h2>Đội ngũ chúng tôi</h2>
            </div>

            <div class="row" data-aos="fade-up" data-aos-delay="100">
                <div class="col-lg-2 col-md-6 d-flex m-auto">
                    <div class="member">
                        <img src="https://avatars.githubusercontent.com/u/77594830?v=4" class="img-fluid" alt="">
                        <div class="member-content">
                            <h4>Hoàng Đăng Khoa</h4>
                            <span>Web Development</span>
                            <p>
                                Xin chào, tôi tên là Khoa! Rất hân hạnh được làm việc với mọi người!
                            </p>
                            <div class="social">
                                <a href="https://twitter.com/khoahd7621" target="_blank"><i
                                        class="bi bi-twitter"></i></a>
                                <a href="https://www.facebook.com/khoahd7621" target="_blank"><i
                                        class="bi bi-facebook"></i></a>
                                <a href="https://www.instagram.com/khoahd7621" target="_blank"><i
                                        class="bi bi-instagram"></i></a>
                                <a href="https://github.com/khoahd7621" target="_blank"><i
                                        class="bi bi-github"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Team Member -->

                <div class="col-lg-2 col-md-6 d-flex m-auto">
                    <div class="member">
                        <img src="https://avatars.githubusercontent.com/u/79690773?v=4" class="img-fluid" alt="">
                        <div class="member-content">
                            <h4>Hồ Hải Nam</h4>
                            <span>Web Development</span>
                            <p>
                                Xin chào, tôi tên là Nam! Rất hân hạnh được làm việc với mọi người!
                            </p>
                            <div class="social">
                                <a href="https://www.facebook.com/nam.hohai.hi" target="_blank"><i
                                        class="bi bi-facebook"></i></a>
                                <a href="https://github.com/HaiNam-FoodBoy" target="_blank"><i
                                        class="bi bi-github"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Team Member -->

                <div class="col-lg-2 col-md-6 d-flex m-auto">
                    <div class="member">
                        <img src="https://avatars.githubusercontent.com/u/54205370?v=4" class="img-fluid" alt="">
                        <div class="member-content">
                            <h4>Nguyễn Tấn Lộc</h4>
                            <span>Web Developer</span>
                            <p>
                                Xin chào, tôi tên là Lộc! Rất hân hạnh được làm việc với mọi người!
                            </p>
                            <div class="social">
                                <a href="https://www.facebook.com/ngntanlocc" target="_blank"><i
                                        class="bi bi-facebook"></i></a>
                                <a href="https://github.com/ngntanloc" target="_blank"><i
                                        class="bi bi-github"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Team Member -->

                <div class="col-lg-2 col-md-6 d-flex m-auto">
                    <div class="member">
                        <img src="https://avatars.githubusercontent.com/u/87026966?v=4" class="img-fluid" alt="">
                        <div class="member-content">
                            <h4>Thái Thành Phát</h4>
                            <span>Web Developer</span>
                            <p>
                                Xin chào, tôi tên là Phát! Rất hân hạnh được làm việc với mọi người!
                            </p>
                            <div class="social">
                                <a href="https://twitter.com/69King0" target="_blank"><i
                                        class="bi bi-twitter"></i></a>
                                <a href="https://www.facebook.com/ttphats01" target="_blank"><i
                                        class="bi bi-facebook"></i></a>
                                <a href="https://www.instagram.com/ttphats/" target="_blank"><i
                                        class="bi bi-instagram"></i></a>
                                <a href="https://github.com/ttphats" target="_blank"><i
                                        class="bi bi-github"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Team Member -->

                <div class="col-lg-2 col-md-6 d-flex m-auto">
                    <div class="member">
                        <img src="./assets/images/system/nam.jpg" class="img-fluid" alt="">
                        <div class="member-content">
                            <h4>Trần Hoài Nam</h4>
                            <span>Web Developer</span>
                            <p>
                                Xin chào, tôi tên là Nam! Rất hân hạnh được làm việc với mọi người!
                            </p>
                            <div class="social">
                                <a href="https://www.facebook.com/profile.php?id=100011914069133" target="_blank"><i
                                        class="bi bi-facebook"></i></a>
                                <a href="https://github.com/namth1606" target="_blank"><i
                                        class="bi bi-github"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Team Member -->

            </div>

        </div>
    </section>
    <!-- End Our Team Section -->

    <!-- ======= Testimonials Section ======= -->
    <section id="testimonials" class="testimonials">
        <div class="container">

            <div class="slides-1 swiper" data-aos="fade-up">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <div class="testimonial-item">
                            <img src="./assets/images/testimonials/testimonials-1.jpg" class="testimonial-img"
                                 alt="">
                            <h3>Anh Hải</h3>
                            <h4>Quản lý 100 phòng trọ</h4>
                            <div class="stars">
                                <i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i
                                    class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i
                                    class="bi bi-star-fill"></i>
                            </div>
                            <p>
                                <i class="bi bi-quote quote-icon-left"></i>
                                Trước đây việc quản lý của tôi cần thêm 2 nhân viên để phụ trách. Giờ đây mình tôi
                                có thể làm tất cả với phần mềm quản lý nhà trọ. Hiện tại tôi thoải mái làm những
                                việc khác. Cám ơn HQT Hostel Management!
                                <i class="bi bi-quote quote-icon-right"></i>
                            </p>
                        </div>
                    </div>
                    <!-- End testimonial item -->

                    <div class="swiper-slide">
                        <div class="testimonial-item">
                            <img src="./assets/images/testimonials/testimonials-2.jpg" class="testimonial-img"
                                 alt="">
                            <h3>Chị Phượng</h3>
                            <h4>Quản lý 30 phòng trọ</h4>
                            <div class="stars">
                                <i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i
                                    class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i
                                    class="bi bi-star-fill"></i>
                            </div>
                            <p>
                                <i class="bi bi-quote quote-icon-left"></i>
                                Tôi không rành về máy móc, công nghệ. Nhưng tôi dễ dàng ứng dụng quản lý trọ HQT
                                Hostel Management này. Thật sự dễ nhìn và dễ quản lý cho bất cứ ai! Tôi thật sự rất
                                hài lòng và đã đăng ký gói Timelife để tiết kiệm lâu dài.
                                <i class="bi bi-quote quote-icon-right"></i>
                            </p>
                        </div>
                    </div>
                    <!-- End testimonial item -->

                    <div class="swiper-slide">
                        <div class="testimonial-item">
                            <img src="./assets/images/testimonials/testimonials-4.jpg" class="testimonial-img"
                                 alt="">
                            <h3>Anh Thịnh</h3>
                            <h4>Quản lý chung cư mini</h4>
                            <div class="stars">
                                <i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i
                                    class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i
                                    class="bi bi-star-fill"></i>
                            </div>
                            <p>
                                <i class="bi bi-quote quote-icon-left"></i>
                                Lúc đầu tôi chỉ dùng thử vì tò mò. Nhưng khi thấy được sự tiện lợi về cách quản lý
                                cũng như có thể giám sát ngay trên điện thoại. Tôi đã đăng ký ngay 5 năm để sử dụng.
                                <i class="bi bi-quote quote-icon-right"></i>
                            </p>
                        </div>
                    </div>
                    <!-- End testimonial item -->

                    <div class="swiper-slide">
                        <div class="testimonial-item">
                            <img src="./assets/images/testimonials/testimonials-3.jpg" class="testimonial-img"
                                 alt="">
                            <h3>Chị Hà</h3>
                            <h4>Quản lý chuỗi BĐS cho thuê</h4>
                            <div class="stars">
                                <i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i
                                    class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i
                                    class="bi bi-star-fill"></i>
                            </div>
                            <p>
                                <i class="bi bi-quote quote-icon-left"></i>
                                Số lượng lớn BĐS cho thuê khiến tôi làm việc liên tục và không có thời gian nghỉ
                                ngơi. Nhờ HQT Hostel Management mà giờ đây tôi tự tin tăng số lượng bất động sản cho
                                thuê và làm chủ thời gian của mình!
                                <i class="bi bi-quote quote-icon-right"></i>
                            </p>
                        </div>
                    </div>
                    <!-- End testimonial item -->

                    <div class="swiper-slide">
                        <div class="testimonial-item">
                            <img src="./assets/images/testimonials/testimonials-5.jpg" class="testimonial-img"
                                 alt="">
                            <h3>Anh Tùng</h3>
                            <h4>Quản lý chuỗi nhà trọ</h4>
                            <div class="stars">
                                <i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i
                                    class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i
                                    class="bi bi-star-fill"></i>
                            </div>
                            <p>
                                <i class="bi bi-quote quote-icon-left"></i>
                                Hình ảnh vỗ cùng trực quan và dễ quản lý. Tôi rất hài lòng với tính năng ghi tiền
                                điện nước bằng hình ảnh. Tôi đã không còn phải sử dụng giấy bút và excel phức tạp để
                                quản lý nữa. Một phần mềm hoàn hảo!
                                <i class="bi bi-quote quote-icon-right"></i>
                            </p>
                        </div>
                    </div>
                    <!-- End testimonial item -->
                </div>
                <div class="swiper-pagination"></div>
            </div>
        </div>
    </section>
    <!-- End Testimonials Section -->

    <!-- ======= Frequently Asked Questions Section ======= -->
    <section id="faq" class="faq">
        <div class="container" data-aos="fade-up">

            <div class="section-header">
                <span>Các câu hỏi thường gặp</span>
                <h2>Các câu hỏi thường gặp</h2>

            </div>

            <div class="row justify-content-center" data-aos="fade-up" data-aos-delay="200">
                <div class="col-lg-10">

                    <div class="accordion accordion-flush" id="faqlist">

                        <div class="accordion-item">
                            <h3 class="accordion-header">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#faq-content-1">
                                    <i class="bi bi-question-circle question-icon"></i>
                                    Phần mềm quản lý nhà trọ có tương tác được với người thuê nhà không?
                                </button>
                            </h3>
                            <div id="faq-content-1" class="accordion-collapse collapse" data-bs-parent="#faqlist">
                                <div class="accordion-body">
                                    Với phần mềm quản lý khách thuê HQT Hostel Management bạn sẽ dễ dàng quản lý và
                                    tương tác với khách hàng ngay trên app. Dễ dàng thông báo tiền tháng và giao
                                    tiếp khách bằng tin nhắn ngay trên App.
                                </div>
                            </div>
                        </div>
                        <!-- # Faq item-->

                        <div class="accordion-item">
                            <h3 class="accordion-header">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#faq-content-2">
                                    <i class="bi bi-question-circle question-icon"></i>
                                    Tôi quản lý nhiều nhà trọ ở nhiều khu vực cần sử dụng mấy tài khoản trên APP
                                    quản lý trọ này?
                                </button>
                            </h3>
                            <div id="faq-content-2" class="accordion-collapse collapse" data-bs-parent="#faqlist">
                                <div class="accordion-body">
                                    Chỉ với 1 tài khoản và 1 gói đăng ký duy nhất, bạn có thể dễ dàng quản lý số
                                    lượng phòng theo gói mà bạn đang ký, không phân biệt khu vực địa lý.
                                </div>
                            </div>
                        </div>
                        <!-- # Faq item-->

                        <div class="accordion-item">
                            <h3 class="accordion-header">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#faq-content-3">
                                    <i class="bi bi-question-circle question-icon"></i>
                                    Nếu bảng giá phần mềm quản lý nhà cho thuê thay đổi tôi có phải trả thêm tiền
                                    không?
                                </button>
                            </h3>
                            <div id="faq-content-3" class="accordion-collapse collapse" data-bs-parent="#faqlist">
                                <div class="accordion-body">
                                    Bạn chỉ phải trả tiền cho gói mà bạn đăng ký trong lần đầu tiên. Mọi nâng cấp và
                                    cải tiến từ phần mềm bạn sẽ không phải chịu thêm chi phí nào khác.
                                </div>
                            </div>
                        </div>
                        <!-- # Faq item-->
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- End Frequently Asked Questions Section -->
</main>
<!-- End #main -->

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row wrapper">
            <div class="col-12 mb-5">
                <div class="row">
                    <div class="col-6 col-md-3">
                        <div class="footer-header">
                            <img class="footer-logo" src="./assets/images/logos/logo.png" alt="Logo">
                        </div>
                    </div>
                    <div class="col-6 col-md-3">
                        <div class="footer-header">Đường dẫn nhanh</div>
                        <ul class="footer-list">
                            <li class="footer-item"><a href="#about" class="footer-link">Về chúng tôi</a></li>
                            <li class="footer-item"><a href="#faq" class="footer-link">FAQ</a></li>
                            <li class="footer-item">
                                <a class="footer-link" data-bs-toggle="modal"
                                   data-bs-target="#terms-of-service-modal" style="cursor: pointer;">
                                    Điều khoản dịch vụ
                                </a>
                            </li>
                            <li class="footer-item">
                                <a class="footer-link" data-bs-toggle="modal"
                                   data-bs-target="#security-policy-modal" style="cursor: pointer;">
                                    Chính sách bảo mật
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-6 col-md-3">
                        <div class="footer-header">Hỗ trợ</div>
                        <ul class="footer-list">
                            <li class="footer-item">
                                <a href="https://slack.com/" class="footer-link" target="_blank">
                                    Cộng đồng Slack
                                </a>
                            </li>
                            <li class="footer-item"><a class="footer-link">Tài liệu hỗ trợ</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md-3">
                        <div class="footer-header">Liên hệ</div>
                        <ul class="footer-list">
                            <li class="footer-item">support.hqthms@gmail.com</li>
                            <li class="footer-item">+84(999) 123456</li>
                            <li class="footer-item footer-socials">
                                <a href="https://www.facebook.com/hqtsoftware" target="_blank" class="footer-link">
                                    <i class="footer-icon fa-brands fa-facebook-square"></i>
                                </a>
                                <a href="https://github.com/HQT-Team" target="_blank" class="footer-link">
                                    <i class="footer-icon fa-brands fa-github-square"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-12">
                <div class="copyright-wrapper d-flex justify-content-center pt-5 pb-5">
                    <div class="copyright-content">© 2022 HQT Team. All rights reserved.</div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- End Footer -->

<!-- Scroll Icon -->
<a href="#" class="scroll-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>
<!-- End Scroll Icon -->

<!-- Terms of service Modal -->
<div class="modal fade" id="terms-of-service-modal" tabindex="-1" aria-labelledby="terms-of-service-modalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="terms-of-service-modalLabel">
                    Điều khoản dịch vụ
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h2> 1. Điều khoản </h2>

                <p> Bằng cách truy cập Trang web này, có thể truy cập từ hqthostel.com, bạn đồng ý bị ràng buộc bởi
                    những
                    Điều khoản và Điều kiện Sử dụng Trang web và đồng ý rằng bạn chịu trách nhiệm về thỏa thuận với
                    bất kỳ luật địa phương hiện hành nào. Nếu bạn không đồng ý với bất kỳ điều khoản nào trong số
                    này, bạn bị cấm
                    truy cập trang web này. Các tài liệu trong Trang web này được bảo vệ bởi bản quyền và
                    luật thương hiệu. </p>

                <h2> 2. Giấy phép sử dụng </h2>

                <p> Giấy phép được cấp để tải xuống tạm thời một bản sao của tài liệu trên Trang web của Nhóm HQT
                    cho
                    cá nhân, phi thương mại chỉ xem tạm thời. Đây là việc cấp giấy phép, không phải chuyển nhượng
                    của tiêu đề và theo giấy phép này, bạn không được: </p>

                <ul>
                    <li> Sửa đổi hoặc sao chép tài liệu; </li>
                    <li> Sử dụng tài liệu cho bất kỳ mục đích thương mại nào hoặc để trưng bày công khai; </li>
                    <li> Cố gắng thiết kế đối chiếu bất kỳ phần mềm nào có trên Trang web của Nhóm HQT; </li>
                    <li> Xóa mọi bản quyền hoặc các ký hiệu độc quyền khác khỏi tài liệu; hoặc </li>
                    <li> Chuyển tài liệu cho người khác hoặc "nhân bản" tài liệu trên bất kỳ máy chủ nào khác.
                    </li>
                </ul>

                <p> Điều này sẽ cho phép Nhóm HQT chấm dứt khi vi phạm bất kỳ hạn chế nào trong số này. Trên
                    chấm dứt, quyền xem của bạn cũng sẽ bị chấm dứt và bạn nên hủy mọi
                    tài liệu thuộc sở hữu của bạn cho dù nó là định dạng in hay điện tử. Các Điều khoản Dịch vụ này
                    đã được tạo với sự trợ giúp của <a href="https://www.termsofservicegenerator.net"> Điều khoản
                        của
                        Trình tạo dịch vụ </a>. </p>

                <h2> 3. Tuyên bố từ chối trách nhiệm </h2>

                <p> Tất cả các tài liệu trên Trang web của Nhóm HQT đều được cung cấp "nguyên trạng". Nhóm HQT không
                    bảo đảm, có thể
                    nó được thể hiện hoặc ngụ ý, do đó phủ nhận tất cả các bảo đảm khác. Hơn nữa, Nhóm HQT không
                    không đưa ra bất kỳ tuyên bố nào liên quan đến độ chính xác hoặc độ tin cậy của việc sử dụng các
                    tài liệu
                    trên Trang web của nó hoặc liên quan đến các tài liệu đó hoặc bất kỳ trang web nào được liên kết
                    với Trang web này. </p>

                <h2> 4. Hạn chế </h2>

                <p> Nhóm HQT hoặc các nhà cung cấp của nhóm sẽ không chịu trách nhiệm về bất kỳ thiệt hại nào sẽ
                    phát sinh với
                    sử dụng hoặc không thể sử dụng các tài liệu trên Trang web của Nhóm HQT, ngay cả khi Nhóm HQT
                    hoặc người ủy quyền
                    đại diện của Trang web này đã được thông báo, bằng miệng hoặc bằng văn bản, về khả năng
                    chấn thương. Một số quyền tài phán không cho phép giới hạn đối với các bảo đảm ngụ ý hoặc các
                    giới hạn của
                    trách nhiệm đối với những thiệt hại ngẫu nhiên, những giới hạn này có thể không áp dụng cho bạn.
                </p>

                <h2> 5. Bản sửa đổi và Errata </h2>

                <p> Các tài liệu xuất hiện trên Trang web của Nhóm HQT có thể bao gồm kỹ thuật, kiểu chữ hoặc
                    lỗi chụp ảnh. Nhóm HQT sẽ không cam kết rằng bất kỳ tài liệu nào trong Trang web này là
                    chính xác, đầy đủ hoặc hiện tại. Nhóm HQT có thể thay đổi các tài liệu có trên Trang web của
                    mình tại
                    bất cứ lúc nào mà không cần thông báo. HQT Team không cam kết cập nhật tài liệu. </p>

                <h2> 6. Liên kết </h2>

                <p> Nhóm HQT chưa xem xét tất cả các trang web được liên kết với Trang web của mình và không chịu
                    trách nhiệm về
                    nội dung của bất kỳ trang web được liên kết nào như vậy. Sự hiện diện của bất kỳ liên kết nào
                    không có nghĩa là đã được HQT chứng thực
                    Nhóm của trang web. Người dùng tự chịu rủi ro khi sử dụng bất kỳ trang web được liên kết nào.
                </p>

                <h2> 7. Sửa đổi Điều khoản Sử dụng của Trang web </h2>

                <p> Nhóm HQT có thể sửa đổi các Điều khoản Sử dụng này cho Trang web của mình bất kỳ lúc nào mà
                    không cần thông báo trước. Bằng cách sử dụng
                    Trang web này, bạn đồng ý bị ràng buộc bởi phiên bản hiện tại của các Điều khoản và Điều kiện
                    này
                    sử dụng. </p>

                <h2> 8. Quyền riêng tư của bạn </h2>

                <p> Vui lòng đọc Chính sách Bảo mật của chúng tôi. </p>

                <h2> 9. Luật điều chỉnh </h2>

                <p> Bất kỳ khiếu nại nào liên quan đến Trang web của Nhóm HQT sẽ chịu sự điều chỉnh của luật pháp vn
                    mà không liên quan đến
                    xung đột với các quy định của pháp luật. </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<!-- End Terms of service Modal -->

<!-- Security Policy Modal -->
<div class="modal fade" id="security-policy-modal" tabindex="-1" aria-labelledby="security-policy-modalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="security-policy-modalLabel">
                    Chính sách bảo mật
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p><strong>Chính sách Bảo mật cho Trang web của Tôi</strong></p>

                <p> Trang web của tôi một trong những ưu tiên chính của chúng tôi là quyền riêng tư của khách truy
                    cập. Tài liệu Chính sách Bảo mật này bao gồm các loại thông tin được Trang web của tôi thu thập
                    và ghi lại cũng như cách chúng tôi sử dụng thông tin đó. </p>

                <p> Nếu bạn có thêm câu hỏi hoặc yêu cầu thêm thông tin về Chính sách quyền riêng tư của chúng tôi,
                    vui lòng liên hệ với chúng tôi. </p>

                <p> Chính sách bảo mật này chỉ áp dụng cho các hoạt động trực tuyến của chúng tôi và có giá trị đối
                    với khách truy cập vào trang web của chúng tôi liên quan đến thông tin mà họ đã chia sẻ và /
                    hoặc thu thập trong & Trang web của tôi. Chính sách này không áp dụng cho bất kỳ thông tin nào
                    được thu thập ngoại tuyến hoặc qua các kênh khác ngoài trang web này. </p>

                <p> Bằng cách sử dụng trang web của chúng tôi, bạn đồng ý với Chính sách Bảo mật của chúng tôi và
                    đồng ý với các điều khoản của nó. Chính sách Bảo mật này đã được tạo bằng
                    <a href="https://www.privacypolicygenerator.info/">
                        <span> Trình tạo Chính sách Bảo mật </span>
                    </a> có sẵn từ
                    <a href="https://www.privacypolicygenerator.info/">
                        <span> https://www.privacypolicygenerator.info/ </span>
                    </a>
                </p>

                <p> <strong> Thông tin chúng tôi thu thập </strong> </p>

                <p> Thông tin cá nhân mà bạn được yêu cầu cung cấp và lý do tại sao bạn được yêu cầu cung cấp sẽ
                    được làm rõ cho bạn tại thời điểm chúng tôi yêu cầu bạn cung cấp thông tin cá nhân của mình.
                </p>

                <p> Nếu bạn liên hệ trực tiếp với chúng tôi, chúng tôi có thể nhận được thông tin bổ sung về bạn như
                    tên, địa chỉ email, số điện thoại, nội dung thư và/hoặc tệp đính kèm mà bạn có thể gửi cho
                    chúng tôi và bất kỳ thông tin nào khác mà bạn có thể chọn cung cấp.</p>

                <p> Khi bạn đăng ký Tài khoản, chúng tôi có thể yêu cầu thông tin liên hệ của bạn, bao gồm các mục
                    như tên, tên công ty, địa chỉ, địa chỉ email và số điện thoại. </p>

                <p> <strong> Cách chúng tôi sử dụng thông tin của bạn </strong> </p>

                <p> Chúng tôi sử dụng thông tin thu thập được theo nhiều cách khác nhau, bao gồm: </p>

                <ul>
                    <li> Cung cấp, vận hành và duy trì trang web của chúng tôi </li>
                    <li> Cải thiện, cá nhân hóa và mở rộng trang web của chúng tôi </li>
                    <li> Hiểu và phân tích cách bạn sử dụng trang web của chúng tôi </li>
                    <li> Phát triển các sản phẩm, dịch vụ, tính năng và chức năng mới </li>
                    <li> Liên lạc với bạn, trực tiếp hoặc thông qua một trong các đối tác của chúng tôi, bao gồm cả
                        dịch vụ khách hàng, để cung cấp cho bạn các bản cập nhật và thông tin khác liên quan đến
                        trang web cũng như cho các mục đích tiếp thị và quảng cáo </li>
                    <li> Gửi cho bạn email </li>
                    <li> Tìm và ngăn chặn gian lận </li>
                    <li> Tệp nhật ký </li>
                </ul>

                <p> Trang web của tôi tuân theo quy trình chuẩn sử dụng tệp nhật ký. Các tệp này ghi nhật ký khách
                    truy cập khi họ truy cập các trang web. Tất cả các công ty lưu trữ đều làm điều này và một phần
                    của phân tích dịch vụ lưu trữ. Thông tin được thu thập bởi các tệp nhật ký bao gồm địa chỉ giao
                    thức internet (IP), loại trình duyệt, Nhà cung cấp dịch vụ Internet (ISP), dấu ngày và giờ, các
                    trang giới thiệu/thoát và có thể cả số lần nhấp chuột. Chúng không được liên kết với bất kỳ
                    thông tin nào có thể nhận dạng cá nhân. Mục đích của thông tin là để phân tích xu hướng, quản lý
                    trang web, theo dõi chuyển động của người dùng trên trang web và thu thập thông tin nhân khẩu
                    học. </p>

                <p> <strong> Cookie và Biểu tượng web </strong> </p>

                <p> Giống như bất kỳ trang web nào khác, Trang web của tôi sử dụng "cookie". Các cookie này được sử
                    dụng để lưu trữ thông tin bao gồm sở thích của khách truy cập và các trang trên trang web mà
                    khách đã truy cập hoặc đã truy cập. Thông tin được sử dụng để tối ưu hóa trải nghiệm của người
                    dùng bằng cách tùy chỉnh nội dung trang web của chúng tôi dựa trên loại trình duyệt của khách
                    truy cập và/hoặc thông tin khác. </p>

                <p> <strong>Cookie</strong> </p>

                <p> Google là một trong những nhà cung cấp bên thứ ba trên trang web của chúng tôi. Nó cũng sử dụng
                    cookie, được gọi là cookie DART, để phân phát quảng cáo cho khách truy cập trang web của chúng
                    tôi dựa trên lượt truy cập của họ vào www.website.com và các trang web khác trên internet. Tuy
                    nhiên, khách truy cập có thể chọn từ chối việc sử dụng cookie DART bằng cách truy cập Chính sách
                    bảo mật của mạng nội dung và quảng cáo của Google tại URL sau:
                    <a href="https://policies.google.com/technologies/ads">
                        <span>https://policies.google.com/technologies/ads</span>
                    </a>
                </p>

                <p> Một số nhà quảng cáo trên trang web của chúng tôi có thể sử dụng cookie và báo hiệu web. Các đối
                    tác quảng cáo của chúng tôi được liệt kê dưới đây. Mỗi đối tác quảng cáo của chúng tôi đều có
                    Chính sách Bảo mật của riêng họ đối với các chính sách của họ về dữ liệu người dùng. Để truy cập
                    dễ dàng hơn, chúng tôi đã liên kết siêu liên kết với Chính sách quyền riêng tư của họ bên dưới.
                </p>

                <ul>
                    <li> Google: <a href="https://policies.google.com/technologies/ads"> <span class="s1">
                                    https://policies.google.com/technologies/ads </span> </a> </li>
                </ul>
                <p> <strong> Chính sách Bảo mật của Đối tác Quảng cáo </strong> </p>

                <p> Bạn có thể tham khảo danh sách này để tìm Chính sách Bảo mật cho từng đối tác quảng cáo của &
                    Trang web của tôi. </p>

                <p> Các máy chủ quảng cáo hoặc mạng quảng cáo của bên thứ ba sử dụng các công nghệ như cookie,
                    JavaScript hoặc Biểu tượng web được sử dụng trong các quảng cáo và liên kết tương ứng của chúng
                    xuất hiện trên & Trang web của tôi, được gửi trực tiếp đến trình duyệt của người dùng. Họ tự
                    động nhận địa chỉ IP của bạn khi điều này xảy ra. Những công nghệ này được sử dụng để đo lường
                    hiệu quả của các chiến dịch quảng cáo của họ và/hoặc để cá nhân hóa nội dung quảng cáo mà bạn
                    thấy trên các trang web mà bạn truy cập. </p>

                <p> Lưu ý rằng Trang web của tôi không có quyền truy cập hoặc kiểm soát các cookie được các nhà
                    quảng cáo bên thứ ba sử dụng. </p>

                <p> <strong> Chính sách quyền riêng tư của bên thứ ba </strong> </p>

                <p> Chính sách Bảo mật của Trang web của tôi không áp dụng cho các nhà quảng cáo hoặc trang web
                    khác. Do đó, chúng tôi khuyên bạn nên tham khảo Chính sách Bảo mật tương ứng của các máy chủ
                    quảng cáo của bên thứ ba này để biết thêm thông tin chi tiết. Nó có thể bao gồm các thực hành và
                    hướng dẫn của họ về cách chọn không tham gia các tùy chọn nhất định. Bạn có thể tìm thấy danh
                    sách đầy đủ các Chính sách Bảo mật này và các liên kết của chúng tại đây: Liên kết Chính sách
                    Bảo mật. </p>

                <p> Bạn có thể chọn tắt cookie thông qua các tùy chọn trình duyệt riêng lẻ của mình. Để biết thêm
                    thông tin chi tiết về quản lý cookie với các trình duyệt web cụ thể, bạn có thể tìm thấy thông
                    tin này tại các trang web tương ứng của trình duyệt. <a
                            href="https://www.privacypolicygenerator.info/privacy-policy-cookies-clauses/"> <span
                            class="s1"> Cookie là gì? </span> </a> </p>

                <p> <strong> Chính sách Bảo mật CCPA (Không bán Thông tin Cá nhân của Tôi) </strong> </p>

                <p> Theo CCPA, trong số các quyền khác, người tiêu dùng California có quyền: </p>

                <ul>
                    <li> Yêu cầu doanh nghiệp thu thập dữ liệu cá nhân của người tiêu dùng tiết lộ các danh mục và
                        phần dữ liệu cá nhân cụ thể mà doanh nghiệp đã thu thập về người tiêu dùng. </li>
                    <li> Yêu cầu doanh nghiệp xóa mọi dữ liệu cá nhân về người tiêu dùng mà doanh nghiệp đã thu
                        thập. </li>
                    <li> Yêu cầu doanh nghiệp bán dữ liệu cá nhân của người tiêu dùng, không bán dữ liệu cá nhân của
                        người tiêu dùng. </li>
                </ul>

                <p> Nếu bạn đưa ra yêu cầu, chúng tôi có một tháng để trả lời bạn. Nếu bạn muốn thực hiện bất kỳ
                    quyền nào trong số này, vui lòng liên hệ với chúng tôi. </p>

                <p> <strong> Chính sách quyền riêng tư của GDPR (Quyền bảo vệ dữ liệu) </strong> </p>

                <p> Chúng tôi muốn đảm bảo rằng bạn nhận thức đầy đủ về tất cả các quyền bảo vệ dữ liệu của mình.
                    Mọi người dùng được hưởng những điều sau đây: </p>

                <ul>
                    <li> Quyền truy cập: Bạn có quyền yêu cầu bản sao dữ liệu cá nhân của mình. Chúng tôi có
                        thể tính cho bạn một khoản phí nhỏ cho dịch vụ này. </li>
                    <li> Quyền sửa chữa: Bạn có quyền yêu cầu chúng tôi chỉnh sửa bất kỳ thông tin nào mà
                        bạn cho là không chính xác. Bạn cũng có quyền yêu cầu chúng tôi hoàn thiện thông tin mà bạn
                        cho là chưa đầy đủ. </li>
                    <li> Quyền xóa: Bạn có quyền yêu cầu chúng tôi xóa dữ liệu cá nhân của bạn, trong một số
                        điều kiện nhất định. </li>
                    <li> Quyền hạn chế xử lý: Bạn có quyền yêu cầu chúng tôi hạn chế việc xử lý dữ liệu cá
                        nhân của bạn, trong một số điều kiện nhất định. </li>
                    <li> Quyền phản đối việc xử lý: Bạn có quyền phản đối việc chúng tôi xử lý dữ liệu cá
                        nhân của bạn, trong một số điều kiện nhất định. </li>
                    <li> Quyền đối với tính khả chuyển của dữ liệu: Bạn có quyền yêu cầu chúng tôi chuyển dữ
                        liệu mà chúng tôi đã thu thập được cho một tổ chức khác hoặc trực tiếp cho bạn theo một số
                        điều kiện nhất định. </li>
                </ul>

                <p> Nếu bạn đưa ra yêu cầu, chúng tôi có một tháng để trả lời bạn. Nếu bạn muốn thực hiện bất kỳ
                    quyền nào trong số này, vui lòng liên hệ với chúng tôi. </p>

                <p> <strong> Thông tin dành cho trẻ em </strong> </p>

                <p> Một phần ưu tiên khác của chúng tôi là tăng cường bảo vệ trẻ em khi sử dụng Internet. Chúng tôi
                    khuyến khích cha mẹ và người giám hộ quan sát, tham gia và/hoặc giám sát và hướng dẫn hoạt
                    động trực tuyến của họ. </p>

                <p> Trang web của tôi không cố ý thu thập bất kỳ Thông tin nhận dạng cá nhân nào từ trẻ em dưới 13
                    tuổi. Nếu bạn nghĩ rằng con bạn đã cung cấp loại thông tin này trên trang web của chúng tôi,
                    chúng tôi đặc biệt khuyến khích bạn liên hệ với chúng tôi ngay lập tức và chúng tôi sẽ cố gắng
                    hết sức nỗ lực xóa ngay những thông tin đó khỏi hồ sơ của chúng tôi. </p>

                <p> <strong> Câu hỏi</strong></p>
                <p> Nếu bạn có bất kỳ câu hỏi nào về Chính sách Bảo mật này, vui lòng liên hệ với chúng tôi. </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<!-- End Security Policy Modal -->

<!-- Script Bootstrap !important -->
<script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<!-- Jquery -->
<script src="./assets/js/jquery-3.5.1.min.js"></script>
<!-- Vendor JS Files -->
<script src="./assets/vendor/purecounter/purecounter_vanilla.js"></script>
<script src="./assets/vendor/glightbox/js/glightbox.min.js"></script>
<script src="./assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="./assets/vendor/aos/aos.js"></script>
<script src="./assets/vendor/php-email-form/validate.js"></script>
<!-- Link your script here -->
<script src="./assets/js/system/home-handle.js"></script>
<script src="./assets/js/system/home-handle-2.js"></script>
<!-- Loader -->
<script src="./assets/js/loading-handler.js"></script>
</body>

</html>