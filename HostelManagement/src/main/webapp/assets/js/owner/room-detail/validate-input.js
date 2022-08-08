// Update room infor
Validator({
    form: "#update-room-infor-form",
    formGroupSelector: ".form-group",
    errorSelector: ".form-message",
    rules: [
        Validator.isRequired("#update-room-infor__room-number"),
        Validator.isInteger("#update-room-infor__room-number"),
        Validator.minNumber(
            "#update-room-infor__room-number",
            1,
            `Vui lòng nhập số phòng tối thiểu là ${1}`
        ),
        Validator.isRequired("#update-room-infor__room-capacity"),
        Validator.isInteger("#update-room-infor__room-capacity"),
        Validator.minNumber(
            "#update-room-infor__room-capacity",
            1,
            `Vui lòng nhập số lượng tối thiểu là ${1}`
        ),
        Validator.isRequired("#update-room-infor__room-area"),
        Validator.minNumber(
            "#update-room-infor__room-area",
            1,
            `Vui lòng nhập diện tích tối thiểu là ${1}`
        ),
    ],
});

// Update consume
Validator({
    form: "#update-consume-modal-form",
    formGroupSelector: ".form-group",
    errorSelector: ".form-message",
    rules: [
        Validator.isRequired("#form-update-consume__electric"),
        Validator.isInteger("#form-update-consume__electric"),
        // Validator.isHigher(
        //     "#form-update-consume__electric",
        //     function () {
        //         return document.querySelector(
        //             "#update-consume-modal-form #form-update-consume__electric-old"
        //         ).value;
        //     },
        //     "Vui lòng nhập số điện mới lớn hơn số cũ"
        // ),
        Validator.isRequired("#form-update-consume__water"),
        Validator.isInteger("#form-update-consume__water"),
        // Validator.isHigher(
        //     "#form-update-consume__water",
        //     function () {
        //         return document.querySelector(
        //             "#update-consume-modal-form #form-update-consume__water-old"
        //         ).value;
        //     },
        //     "Vui lòng nhập số nước mới lớn hơn số cũ"
        // ),
    ],
});

// Add member
Validator({
    form: "#add-new-member-form",
    formGroupSelector: ".form-group",
    errorSelector: ".form-message",
    rules: [
        Validator.isRequired("#add-new-member__name"),
        Validator.isRequired("#add-new-member__dob"),
        Validator.isRequired("#add-new-member__phone"),
        Validator.isViePhoneNumber("#add-new-member__phone"),
        Validator.isRequired("#add-new-member__email"),
        Validator.isRequired("#add-new-member__cccd"),
        Validator.isCCCD("#add-new-member__cccd"),
        Validator.isRequired("#add-new-member__address"),
        Validator.isRequired("#add-new-member__parent-name"),
        Validator.isRequired("#add-new-member__parent-phone"),
        Validator.isViePhoneNumber("#add-new-member__parent-phone"),
    ],
});

// Update member
Validator({
    form: "#update-member-infor-form",
    formGroupSelector: ".form-group",
    errorSelector: ".form-message",
    rules: [
        Validator.isRequired("#update-member-infor__name"),
        Validator.isRequired("#update-member-infor__dob"),
        Validator.isRequired("#update-member-infor__phone"),
        Validator.isViePhoneNumber("#update-member-infor__phone"),
        Validator.isRequired("#update-member-infor__email"),
        Validator.isRequired("#update-member-infor__cccd"),
        Validator.isCCCD("#update-member-infor__cccd"),
        Validator.isRequired("#update-member-infor__address"),
        Validator.isRequired("#update-member-infor__parent-name"),
        Validator.isRequired("#update-member-infor__parent-phone"),
        Validator.isViePhoneNumber("#update-member-infor__parent-phone"),
    ],
});

// Reset password
Validator({
    form: "#reset-password-form",
    formGroupSelector: ".form-group",
    errorSelector: ".form-message",
    rules: [
        Validator.isRequired("#reset-password-input"),
        Validator.minLength("#reset-password-input", 6),
        Validator.isRequired("#reset-confirm-password-input"),
        Validator.isConfirmed(
            "#reset-confirm-password-input",
            function () {
                return document.querySelector("#reset-password-input").value;
            },
            "Mật khẩu nhập lại không chính xác"
        ),
    ],
});
