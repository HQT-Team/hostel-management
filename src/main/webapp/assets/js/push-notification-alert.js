function alertPushNoti({ message = "", duration = "" }) {
    const mainNoti = document.getElementById("push-noti");

    if (mainNoti) {
        const noti = document.createElement("div");

        // Auto remove noti
        const autoRemoveId = setTimeout(function () {
            mainNoti.removeChild(noti);
        }, duration + 500);

        // Remove noti when clicked
        noti.onclick = function (e) {
            if (e.target.closest(".push-noti__close")) {
                mainNoti.removeChild(noti);
                clearTimeout(autoRemoveId);
            }
        };

        const delay = (duration / 1000).toFixed(2);

        noti.classList.add("push-noti");
        noti.style.animation = `slideInRight ease .3s, fadeOut linear 2s ${delay}s forwards`;
        noti.innerHTML = `
            <div class="push-noti__wrapper">
              <h5 class="push-noti__title">Thông báo mới</h5>
              <div class="push-noti__close">
                <i class="fa-solid fa-xmark"></i>
              </div>
            </div>
            <div class="push-noti__wrapper">
              <div class="push-noti__icon">
                <i class="fa-solid fa-circle-info"></i>
              </div>
              <div class="push-noti__body">
                <p class="push-noti__msg">${message}</p>
                <p class="push-noti__time">vài giây trước</p>
              </div>
            </div>
            <div class="push-noti__dot"></div>
            <audio autoplay>
                <source src="noti-sound.mp3" type="audio/mp3">
            </audio>`;
        mainNoti.appendChild(noti);
    }
}
