// Call API
const request = axios.create({
  baseURL: "https://provinces.open-api.vn/api/",
});

const get = async (path, options = {}) => {
  const response = await request.get(path, options);
  return response.data;
};

// Handle render option UI
const renderSelect = (selectElement, datas, defaultOptionContent) => {
  const options = [`<option value="">${defaultOptionContent}</option>`];

  datas.forEach((value) => {
    options.push(
      `<option value='${value.code}-${value.name}'>${value.name}</option>`
    );
  });

  selectElement.innerHTML = options.join("");
};

// Handle get data
const getProvinces = (provinceElement) => {
  get("p").then((response) => {
    renderSelect(provinceElement, response, "Chọn tỉnh/thành phố");
  });
};

const getDistricts = (districtElement, provinceCode) => {
  const params = {
    depth: 2,
  };

  get(`p/${provinceCode}`, { params }).then((response) => {
    renderSelect(districtElement, response.districts, "Chọn quận/huyện");
  });
};

const getWards = (wardElement, districtCode) => {
  const params = {
    depth: 2,
  };

  get(`d/${districtCode}`, { params }).then((response) => {
    renderSelect(wardElement, response.wards, "Chọn phường/xã");
  });
};

// Run
const provinceElement = document.getElementById("hostel-province");
const districtElement = document.getElementById("hostel-district");
const wardElement = document.getElementById("hostel-ward");

getProvinces(provinceElement);

provinceElement.addEventListener("change", () => {
  if (provinceElement.value === "") {
    districtElement.innerHTML = "<option value=''>Chọn quận/huyện</option>";
    districtElement.setAttribute("disabled", "true");
  } else {
    districtElement.removeAttribute("disabled");
    const arrs = provinceElement.value.split("-");
    getDistricts(districtElement, Number(arrs[0]));
  }

  wardElement.innerHTML = "<option value=''>Chọn phường/xã</option>";
  wardElement.setAttribute("disabled", "true");
});

districtElement.addEventListener("change", () => {
  if (districtElement.value === "") {
    wardElement.innerHTML = "<option value=''>Chọn phường/xã</option>";
    wardElement.setAttribute("disabled", "true");
  } else {
    wardElement.removeAttribute("disabled");
    const arrs = districtElement.value.split("-");
    getWards(wardElement, Number(arrs[0]));
  }
});
