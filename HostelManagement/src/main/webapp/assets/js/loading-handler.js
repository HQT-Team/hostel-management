const loader = document.getElementById('preloader');

window.addEventListener('load', () => {
    loader.setAttribute('closing', '');
    loader.addEventListener('animationend', () => {
        document.body.classList.remove('over-flow-hidden');
        loader.style.display = 'none';
    }, { once: true });
});