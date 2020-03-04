function postModel(url, requestData) {
  var responseModel = "";
  $.ajax({
    url:url,
    type: 'POST',
    contentType: 'application/json',
    crossDomain: true,
    dataType: 'json',
    async: false,
    data: JSON.stringify(requestData),
    success: function (response) {
      responseModel = response;
    }
  });
  return responseModel;
}

function getValueByElementId(elementId) {
  return document.getElementById(elementId).value;
}

function login() {
  var postData = {
    "username": getValueByElementId("username"),
    "password": getValueByElementId("password")
  };
  var postResponse = postModel("http://localhost:8080/restful/login/check",
      postData);
  if (postResponse.data !== null) {
    location.href = "homePage.html"
  } else {
    $('#form').addClass('up');
    $('#form').addClass('wrong-entry');
    setTimeout(function () {
      $('#form').removeClass('wrong-entry');
    }, 3000);
    setTimeout(function () {
      $('#form').removeClass('up')
    }, 1000);

  }
}

function createAccount() {
  if (getValueByElementId("passwordCreateAccount") === getValueByElementId(
      "passwordAgain")) {
    var postData = {
      "name": getValueByElementId("nameCreateAccount"),
      "username": getValueByElementId("usernameCreateAccount"),
      "password": getValueByElementId("passwordCreateAccount"),
      "birthDate": getValueByElementId("birthDay")
    };
    var postResponse = postModel("http://localhost:8080/restful/user/create",
        postData);
    alert(postResponse.data);
  } else {
    alert("Passwords must be the same!!");
  }

}

function changePassword() {
  if (getValueByElementId("newPasswordAgain") === getValueByElementId(
      "newPassword")) {
    var postData = {
      "newPassword": getValueByElementId("newPassword"),
      "username": getValueByElementId("usernameChangePassword"),
      "oldPassword": getValueByElementId("passwordChange")
    };
    var postResponse = postModel(
        "http://localhost:8080/restful/user/changePassword",
        postData);
    alert(postResponse.data);
  } else {
    alert("Passwords must be the same!!");
  }
}