Validator({
  form: "#register-form",
  formGroupSelector: ".form-group",
  errorSelector: ".form-message",
  rules: [
    Validator.isRequired("#fullname", "Vui lòng nhập tên đầy đủ của bạn"),
    Validator.isRequired("#username", "Vui lòng nhập tên tài khoản"),
    Validator.isRequired("#cccd", "Vui lòng nhập số CMND hoặc CCCD của bạn"),
    Validator.isCCCD("#cccd"),
    Validator.isRequired("#email", "Vui lòng nhập email của bạn"),
    Validator.isEmail("#email"),
    Validator.minLength("#password", 6),
    Validator.isRequired("#confirm-password"),
    Validator.isConfirmed(
      "#confirm-password",
      function () {
        return document.querySelector("#register-form #password").value;
      },
      "Mật khẩu nhập lại không chính xác"
    ),
  ],
  // onSubmit: function(data) {
  //   axios({
  //     method: 'post',
  //     url: 'handle-register',
  //     data: data
  //   })
  // }
});
