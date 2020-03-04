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

function saveDiary() {
  var postData = {
    "title": getValueByElementId("title"),
    "diary": getValueByElementId("text"),
    "feeling": 1//getValueByElementId("password"),
  };
  var postResponse = postModel("http://localhost:8080/restful/diary/save",
      postData);
  location.href = "homePage.html";
  alert(postResponse.data);
}

function paperMenu() {
  return paperMenu = {
    $window: $('#paper-window'),
    $paperFront: $('#paper-front'),
    $hamburger: $('.hamburger'),
    offset: 1800,
    pageHeight: $('#paper-front').outerHeight(),

    open: function () {
      this.$window.addClass('tilt');
      this.$hamburger.off('click');
      $('#container, .hamburger').on('click', this.close.bind(this));
      this.hamburgerFix(true);
      console.log('opening...');
    },
    close: function () {
      this.$window.removeClass('tilt');
      $('#container, .hamburger').off('click');
      this.$hamburger.on('click', this.open.bind(this));
      this.hamburgerFix(false);
      console.log('closing...');
    },
    updateTransformOrigin: function () {
      scrollTop = this.$window.scrollTop();
      equation = (scrollTop + this.offset) / this.pageHeight * 100;
      this.$paperFront.css('transform-origin', 'center ' + equation + '%');
    },
    //hamburger icon fix to keep its position
    hamburgerFix: function (opening) {
      if (opening) {
        $('.hamburger').css({
          position: 'absolute',
          top: this.$window.scrollTop() + 30 + 'px'
        });

      } else {
        setTimeout(function () {
          $('.hamburger').css({
            position: 'fixed',
            top: '30px'
          });

        }, 300);
      }
    },
    bindEvents: function () {
      this.$hamburger.on('click', this.open.bind(this));
      $('.close').on('click', this.close.bind(this));
      this.$window.on('scroll', this.updateTransformOrigin.bind(this));
    },

    tableAngular:function (){angular.module('sortTable', []).controller('mainCtrl', function () {
      var main = this;
      var postData = {};
      var postResponse = postModel("http://localhost:8080/restful/diary/getAllByUserId",
          postData);
      main.title = "My Writings";

      main.persons =postResponse.data;

      main.orderType = 'id';
      main.orderReverse = false;

      main.changeSortType = function (orderType) {
        if (main.orderType == orderType) {
          main.orderReverse = !main.orderReverse;
        } else {
          main.orderType = orderType;
        }
      };

      main.isSortType = function (orderType) {
        return main.orderType == orderType;
      };

      main.isOrderedReverse = function () {
        return !main.orderReverse;
      };
    })},

    init: function () {
      this.bindEvents();
      this.updateTransformOrigin();
      this.tableAngular();
    }
  };
}






