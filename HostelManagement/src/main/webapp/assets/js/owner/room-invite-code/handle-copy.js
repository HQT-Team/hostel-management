$(document).ready(function () {
    const copyToClipboard = (element) => {
        navigator.clipboard.writeText($(element).html().trim());
    };

    $("#invite-content__code").click(() => {
        copyToClipboard("#invite-code");
        $("#invite-copy__code").html("Đã sao chép");
        $("#invite-copy__link").html("Sao chép");
    });

    $("#invite-content__link").click(() => {
        copyToClipboard("#invite-link");
        $("#invite-copy__link").html("Đã sao chép");
        $("#invite-copy__code").html("Sao chép");
    });
});
