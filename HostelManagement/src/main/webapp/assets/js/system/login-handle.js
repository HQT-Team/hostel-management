Validator({
  form: "#login-form",
  formGroupSelector: ".form-group",
  errorSelector: ".form-message",
  rules: [
    Validator.isRequired("#username", "Vui lòng nhập tài khoản"),
    Validator.isRequired("#password", "Vui lòng nhập mật khẩu"),
  ],
});
