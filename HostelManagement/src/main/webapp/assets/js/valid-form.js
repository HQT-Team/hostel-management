// Đối tượng Validator
function Validator(options) {
  function getParent(element, selector) {
    while (element.parentElement) {
      if (element.parentElement.matches(selector)) {
        return element.parentElement;
      }
      element = element.parentElement;
    }
  }

  var selectorRules = {};

  // Hàm thực hiện validate
  function Validate(inputElement, rule) {
    // var errorElement = getParent(inputElement, '.form-group');
    var errorElement = getParent(
        inputElement,
        options.formGroupSelector
    ).querySelector(options.errorSelector);
    var errorMessage;

    // Lấy ra các rules của selector
    var rules = selectorRules[rule.selector];

    // Lặp qua từng rule & kiểm tra
    // Nếu có lỗi thì dừng việc kiểm tra
    for (var i = 0; i < rules.length; ++i) {
      switch (inputElement.type) {
        case "checkbox":
        case "radio":
          errorMessage = rules[i](
              formElement.querySelector(rule.selector + ":checked")
          );
          break;
        default:
          errorMessage = rules[i](inputElement.value);
          break;
      }
      if (errorMessage) break;
    }

    if (errorMessage) {
      errorElement.innerText = errorMessage;
      getParent(inputElement, options.formGroupSelector).classList.add(
          "invalid"
      );
    } else {
      errorElement.innerText = "";
      getParent(inputElement, options.formGroupSelector).classList.remove(
          "invalid"
      );
    }

    return !errorMessage;
  }

  // Lấy element của form cần validate
  var formElement = document.querySelector(options.form);
  if (formElement) {
    // Khi submit form
    formElement.onsubmit = function (e) {
      e.preventDefault();

      var isFormValid = true;

      // Thực hiện lặp qua từng rule và validate
      options.rules.forEach(function (rule) {
        var inputElement = formElement.querySelector(rule.selector);
        var isValid = Validate(inputElement, rule);
        if (!isValid) {
          isFormValid = false;
        }
      });

      if (isFormValid) {
        // trường hợp submit với javascript
        if (typeof options.onSubmit === "function") {
          var enableInputs = formElement.querySelectorAll(
              "[name]:not([disabled])"
          );
          var formValues = Array.from(enableInputs).reduce(function (
                  values,
                  input
              ) {
                switch (input.type) {
                  case "radio":
                    values[input.name] = formElement.querySelector(
                        'input[name="' + input.name + '"]:checked'
                    ).value;
                    break;
                  case "checkbox":
                    if (!input.matches(":checked")) {
                      if (!values[input.name]) {
                        values[input.name] = "";
                      }
                      return values;
                    }

                    if (!Array.isArray(values[input.name])) {
                      values[input.name] = [];
                    }

                    values[input.name].push(input.value);
                    break;
                  case "file":
                    values[input.name] = input.files;
                    break;
                  default:
                    values[input.name] = input.value;
                    break;
                }

                return values;
              },
              {}); // Convert NodeList => Array
          options.onSubmit(formValues);
        } else {
          // Trường hợp submit với hành vi mặc định
          formElement.submit();
        }
      }
    };

    // Lặp qua mỗi rule và xử lý (lắng nghe sự kiện blur, input, ...)
    options.rules.forEach(function (rule) {
      // Lưu lại các rules cho mỗi input

      //
      if (Array.isArray(selectorRules[rule.selector])) {
        selectorRules[rule.selector].push(rule.test);
      } else {
        selectorRules[rule.selector] = [rule.test];
      }

      //selectorRules[rule.selector] = rule.test;

      var inputElements = formElement.querySelectorAll(rule.selector);

      Array.from(inputElements).forEach(function (inputElement) {
        if (inputElement) {
          // Xứ lý trường hợp blur khỏi input
          inputElement.onblur = function () {
            // value: inputElement.value
            // test func: rule.test
            Validate(inputElement, rule);
          };

          // Xử lý mỗi khi người dùng nhập vào input
          inputElement.oninput = function () {
            var errorElement = getParent(
                inputElement,
                options.formGroupSelector
            ).querySelector(options.errorSelector);
            errorElement.innerText = "";
            getParent(inputElement, options.formGroupSelector).classList.remove(
                "invalid"
            );
          };
        }
      });
    });
  }
}

// Định nghĩa rules
// Nguyên tắc của các rules:
// 1. Khi có lỗi => Trả ra message lỗi
// 2. Khi hợp lệ => Không trả ra cái gì cả (undefined)
Validator.isRequired = function (selector, message) {
  return {
    selector: selector,
    test: function (value) {
      return value ? undefined : message || "Vui lòng nhập trường này";
    },
  };
};

Validator.isEmail = function (selector, message) {
  return {
    selector: selector,
    test: function (value) {
      var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
      return regex.test(value)
          ? undefined
          : message || "Vui lòng nhập email hợp lệ";
    },
  };
};

Validator.isUsername = function (selector, message) {
  return {
    selector: selector,
    test: function (value) {
      var regex = /^(?=[a-zA-Z0-9._]{6,20}$)(?!.*[_.]{2})[^_.].*[^_.]$/;
      return regex.test(value)
          ? undefined
          : message || "Vui lòng nhập tài khoản hợp lệ";
    },
  };
};

Validator.isCCCD = function (selector, message) {
  return {
    selector: selector,
    test: function (value) {
      var cmndRegex = /^[0-9]{9}$/;
      var cccdRegex = /^[0-9]{12}$/;
      return cmndRegex.test(value) || cccdRegex.test(value)
          ? undefined
          : message || "Vui lòng nhập CMND/CCCD hợp lệ";
    },
  };
};

Validator.minLength = function (selector, min, message) {
  return {
    selector: selector,
    test: function (value) {
      return value.length >= min
          ? undefined
          : message || `Vui lòng nhập tối thiểu ${min} ký tự`;
    },
  };
};

Validator.maxLength = function (selector, max, message) {
  return {
    selector: selector,
    test: function (value) {
      return value.length <= max
          ? undefined
          : message || `Vui lòng nhập tối đa ${max} ký tự`;
    },
  };
};

Validator.isConfirmed = function (selector, getConfirmValue, message) {
  return {
    selector: selector,
    test: function (value) {
      return value === getConfirmValue()
          ? undefined
          : message || "Giá trị nhập vào không chính xác";
    },
  };
};

Validator.isGreaterDate = function (selector, getEndDateValue, message) {
  return {
    selector: selector,
    test: function (value) {
      var d1 = new Date(value);
      var d2 = new Date(getEndDateValue());
      return d1 >= d2
          ? undefined
          : message || "Giá trị nhập vào không chính xác";
    },
  };
}

Validator.isViePhoneNumber = function (selector, message) {
  return {
    selector: selector,
    test: function (value) {
      var regex =
          /^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$/;
      return regex.test(value)
          ? undefined
          : message || "Vui lòng nhập đúng định dạng số điện thoại";
    },
  };
};

Validator.isInteger = function (selector, message) {
  return {
    selector: selector,
    test: function (value) {
      var regex = /^\d+$/;
      return regex.test(value)
          ? undefined
          : message || "Vui lòng nhập đúng định dạng số nguyên";
    },
  };
};

Validator.isHigher = function (selector, getConfirmValue, message) {
  return {
    selector: selector,
    test: function (value) {
      return value > getConfirmValue()
          ? undefined
          : message || "Giá trị nhập vào phải lớn hơn giá trị trước đó";
    },
  };
};

Validator.minNumber = function (selector, min, message) {
  return {
    selector: selector,
    test: function (value) {
      return value >= min
          ? undefined
          : message || `Vui lòng nhập số tối thiểu lớn hơn hoặc bằng ${min}`;
    },
  };
};

Validator.maxNumber = function (selector, max, message) {
  return {
    selector: selector,
    test: function (value) {
      return value <= max
          ? undefined
          : message || `Vui lòng nhập số tối đa bé hơn hoặc bằng ${max}`;
    },
  };
};
