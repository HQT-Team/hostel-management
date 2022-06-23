function toast({ title = '', message = '', type = 'info', duration = '' }) {
    const mainToast = document.getElementById('toast');
    console.log(mainToast)
    if (mainToast) {
        const toast = document.createElement('div');

        // Auto remove toast
        const autoRemoveId = setTimeout(function () {
            mainToast.removeChild(toast);
        }, duration + 500);

        // Remove toast when clicked
        toast.onclick = function (e) {
            if (e.target.closest('.toast__close')) {
                mainToast.removeChild(toast);
                clearTimeout(autoRemoveId);
            }
        }

        const icons = {
            success: 'fa-solid fa-circle-check',
            info: 'fa-solid fa-circle-info',
            warning: 'fa-solid fa-circle-exclamation',
            error: 'fa-solid fa-circle-xmark',
        };

        const icon = icons[type];
        const delay = (duration / 1000).toFixed(2);

        toast.classList.add('toast', `toast--${type}`, 'd-flex');
        toast.style.animation = `slideInLeft ease .3s, fadeOut linear 1s ${delay}s forwards`;
        toast.innerHTML = `
                    <div class="toast__wrapper">
                      <div class="toast__icon">
                          <i class="${icon}"></i>
                      </div>
                      <div class="toast__body">
                          <h3 class="toast__title">${title}</h3>
                          <p class="toast__msg">${message}</p>
                      </div>
                      <div class="toast__close">
                          <i class="fa-solid fa-xmark"></i>
                      </div>
                      <span class="toast__countdown" style="animation: countdown ${delay}s linear forwards;"></span>
                    </div>
                `;
        mainToast.appendChild(toast);
    }
}