function handleCountDown({ targetDate }) {
    // Set the date we're counting down to
    const countDownDate = new Date(targetDate).getTime();

    // Update the count down every 1 second
    const x = setInterval(() => {
        // Get today's date and time
        const today = new Date();
        const date = `${today.getFullYear()}-${
            today.getMonth() + 1
        }-${today.getDate()}`;
        const time = `${today.getHours()}:${today.getMinutes()}:${today.getSeconds()}`;
        const datetime = `${date} ${time}`;
        const now = new Date(datetime);

        // Find the distance between now and the count down date
        const distance = countDownDate - now;

        // Time calculations for days, hours, minutes and seconds
        // const days = Math.floor(distance / (1000 * 60 * 60 * 24));
        // const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        const seconds = Math.floor((distance % (1000 * 60)) / 1000);

        // Display the result in the element with id="demo"
        $("#count-down").html(
            `${minutes < 10 ? "0" + minutes : minutes}:${
                seconds < 10 ? "0" + seconds : seconds
            }`
        );

        // If the count down is finished, write some text
        if (distance <= 0) {
            clearInterval(x);
            $("#count-down").html("00:00");
            $("#invite-status").html("Vô hiệu hóa");
            $("#invite-status").removeClass("invite-countdown-success");
            $("#invite-recreate").removeClass("d-none");
        }
    }, 1000);
}
