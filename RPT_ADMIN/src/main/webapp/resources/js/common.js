var previousMainTab;

$(function() {
  $.ajaxSetup({
    beforeSend: function(xmlHttpRequest) {
      xmlHttpRequest.setRequestHeader("AJAX", "true");
    },
    error: function(xhr, textStatus, errorThrown) {
      if(xhr.status == 901) {
        window.location.href = getContextRootPath();
      } else {
        alert("작업 중 에러가 발생했습니다. 관리자에게 문의해주세요. \n"+"Status: " + xhr.status + "; Error message: " + errorThrown);
      }
    }
  });
  
  $("a.sideMenu").click(function(e) {
      e.preventDefault();
      e.stopPropagation();
  
      if($(this).attr("href") != "#") {
        //var id = "TAB-" + $(this).text().replace(/ /g, '');
    	var id = "TAB-" + $(this).attr("id");
        var title = $(this).text();
        var path = $(this).attr("href");
        var icon = $(this).find("input").val();
        //console.log("ID:" + id + ", TITLE:" + title + ", PATH:" + path + ", ICON:" + icon);
        appendTab(id, title, path, icon);
      }
  });
  
  $("#mainTab > ul.nav-tabs-main").click(function(e) {
	 var currentActiveTabId = $("#mainTab > ul.nav-tabs-main").find("li.tab-head-main.active").attr("id");
	 var selectedTabId = e.target.parentElement.id;
	 var selectedElementTitle = e.target.title;
	 if(selectedElementTitle != "close" && selectedTabId != currentActiveTabId) {
		 previousMainTab = $("#mainTab > ul.nav-tabs-main").find("li.tab-head-main.active").attr("id");
		 //console.log("TAB CLICK ==> previousMainTab: " + previousMainTab);
	 }
  });
  

	 // showMask를 클릭시 작동하며 검은 마스크 배경과 레이어 팝업을 띄웁니다.
     $('.showMask').click(function(e){
         // preventDefault는 href의 링크 기본 행동을 막는 기능입니다.
         e.preventDefault();
         wrapWindowByMask();
     });

     // 닫기(close)를 눌렀을 때 작동합니다.
     $('.window .closeBtn').click(function (e) {
         e.preventDefault();
         $('.mask, .window').hide();
     });

     // 뒤 검은 마스크를 클릭시에도 모두 제거하도록 처리합니다.
     $('.mask').click(function () {
         $(this).hide();
         $('.window').hide();
     });
  
  var fileTarget = $('.filebox .upload-hidden'); 
  fileTarget.on('change', function(){ // 값이 변경되면 
  	if(window.FileReader){ // modern browser 
  		var filename = $(this)[0].files[0].name; 
  	} else { // old IE var 
  		filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
  	} // 추출한 파일명 삽입 
  	$(this).siblings('.upload-name').val(filename); 
  });
  
  $(document).on("click",".closeTab", function(e) {
    e.preventDefault();
    e.stopPropagation();
    removeTab($(this));
  });
});

function appendTab(id, title, path, icon) {
	if($("#mainTab").find("#" + id).size() <= 0) {
		previousMainTab = $("#mainTab > ul.nav-tabs-main").find("li.tab-head-main.active").attr("id");
	    //console.log("MENU CLICK ==> previousMainTab: " + previousMainTab);
		$("#mainTab > ul.nav-tabs-main").find("li.tab-head-main.active").removeClass("active");
		$("#mainTab > div.tab-content-main").find("div.tab-pane-main.active").removeClass("in active");
		
		var tab = '<li id="th-'+ id + '" class="active tab-head-main"><a href="#' + id + '" data-toggle="tab"><i class="fa ' + icon + '"></i>&nbsp;&nbsp;' + title + '&nbsp;&nbsp;<i class="fa fa-times closeTab"></i></a></li>';
		var contents = '<div class="tab-pane tab-pane-main fade in active" id="' + id + '"></div>';
		
		$("#mainTab").find("ul.nav-tabs-main").append(tab);
		$("#mainTab").find("div.tab-content-main").append(contents);
		
		$("#" + id).load(path);
	} else {
		var currentActiveTabId = $("#mainTab > ul.nav-tabs-main").find("li.tab-head-main.active").attr("id");
		if(("th-" + id) != currentActiveTabId) {
			previousMainTab = currentActiveTabId;
			$("#mainTab > ul.nav-tabs-main").find("li.tab-head-main.active").removeClass("active");
			$("#mainTab > div.tab-content-main").find("div.tab-pane-main.active").removeClass("in active");
			$("#mainTab").find("#th-" + id).addClass("active");
			$("#mainTab").find("#" + id).addClass("in active");
		}
	}
};

function removeTab(element) {
	var ulElement = element.closest( "ul" );
	var isActive = element.closest( "li" ).hasClass("active");
	var hrefValue = element.closest( "a" ).attr("href");
	var tabContentsId = hrefValue.substring(1, hrefValue.length);
	element.closest( "li" ).remove();
	$('#' + tabContentsId).remove();
	
	if(isActive && ulElement.find("li").size() > 0) {
		var previousMainTabHrefValue = $("#" + previousMainTab).find("a:first").attr("href");
		if (typeof previousMainTabHrefValue !== 'undefined') {
			$('#' + previousMainTab).addClass("active");
			var previousMainTabTabContentsId = previousMainTabHrefValue.substring(1, previousMainTabHrefValue.length);
			$('#' + previousMainTabTabContentsId).addClass("in active");
		} else {
			var nextElement = ulElement.find("li:last");
			nextElement.addClass("active");
			var nextHrefValue = nextElement.find("a:first").attr("href");
			var nextTabContentsId = nextHrefValue.substring(1, nextHrefValue.length);
			$('#' + nextTabContentsId).addClass("in active");
		}
	}
}

function removeTabById(id) {
	var ulElement = $('#th-' + id).closest( "ul" );
	
	$('#th-' + id).remove();
	$('#' + id).remove();
	
	if(ulElement.find("li").size() > 0) {
		var previousMainTabHrefValue = $("#" + previousMainTab).find("a:first").attr("href");
		if (typeof previousMainTabHrefValue !== 'undefined') {
			$('#' + previousMainTab).addClass("active");
			var previousMainTabTabContentsId = previousMainTabHrefValue.substring(1, previousMainTabHrefValue.length);
			$('#' + previousMainTabTabContentsId).addClass("in active");
		} else {
			var nextElement = ulElement.find("li:last");
			nextElement.addClass("active");
			var nextHrefValue = nextElement.find("a:first").attr("href");
			var nextTabContentsId = nextHrefValue.substring(1, nextHrefValue.length);
			$('#' + nextTabContentsId).addClass("in active");
		}
	}
}

function removeAllTab() {
	$("#mainTab > ul.nav-tabs-main").empty();
	$("#mainTab > div.tab-content-main").empty();
}

function renderingGridUsrForm(data) {
	var html = "";
    var positionIndex = 0;
	//console.log(data);
	
	for(var i = 0; i < data.length; i++) {
	    var jsonObj = data[i];
	    
	    if(jsonObj.visible=="N") {
	    	html += '<input type="hidden" name="' + jsonObj.fdcNm + '" value="' + jsonObj.value + '" />';
	    } else {
    	    if(positionIndex%2==0) {
   	    		html += '<div class="row">';
    	    }
    	    
    	    if(jsonObj.required=="Y") {
    			html += '<div class="col-md-2" style="text-align: right;"><p><strong class="text-danger">* </strong>'+ jsonObj.fdcLabel + '</p></div>'
    		} else {
    			html += "<div class='col-md-2' style='text-align: right;'><p>" + jsonObj.fdcLabel + "</p></div>"
    		}
    	    html += '<div class="col-md-3" style="text-align: left;">';
	    	if(jsonObj.fieldType.name == "DT01") {
	    		html += '<div class="form-group"><input type="text" name="' + jsonObj.fdcNm + '" class="form-control input-sm" value="' + jsonObj.value + '" /></div>';
	    	} else if(jsonObj.fieldType.name == "DT02") {
	    		html += '<div class="form-group"><textarea name="' + jsonObj.fdcNm + '" class="form-control input-sm" value="' + jsonObj.value + '"></textarea></div>';
	    	} else if(jsonObj.fieldType.name == "DT03") {
	    		html += '<div class="form-group"><input type="password" name="' + jsonObj.fdcNm + '" class="form-control input-sm" value="' + jsonObj.value + '" /></div>';
	    	} else if(jsonObj.fieldType.name == "DT04") {
	    		html += '<div class="form-group"><input type="text" name="' + jsonObj.fdcNm + '" class="form-control input-sm" value="' + jsonObj.value + '" disabled="disabled"/></div>';
	    	} else if(jsonObj.fieldType.name == "DT06") {
	    		var jsonTypeValues = jsonObj.fieldType.values;
	    		html += '<div class="form-group"><select name="' + jsonObj.fdcNm + '" class="form-control input-sm" >';
	    		for (key in jsonTypeValues) {
	    		    if (jsonTypeValues.hasOwnProperty(key)) {
	    		        //console.log(key + " = " + jsonTypeValues[key]);
	    		        html += "<option value='" + key + "'>" + jsonTypeValues[key] + "</option>";
	    		    }
	    		} 
	    		html += "</select></div>";
	    	} else if(jsonObj.fieldType.name == "DT08") {
	    		html += '<div class="form-group"><input type="checkbox" value="checked"></div>';
	    	} else {
	    		html += '<div class="form-group"><p name="' + jsonObj.fdcNm + '"></p>' + jsonObj.value + '</div>';
	    	}
	    	
	    	if(positionIndex%2==0) {
    	    	html += "</div>";
    	    	if(i== data.length-1) {
    	    		html += "</div>";
    	    	}
    	    } else {
    	    	html += "</div></div>";
    	    }
	    	positionIndex++;
	    }
	}

	return html;
};

function getValiationRules(data) {
	rules = {};
	
	for(var i = 0; i < data.length; i++) {
		var jsonObj = data[i];
		if(jsonObj.visible!="N" && jsonObj.required=="Y") {
			rule = {};
			rule["required"] = true;
			if(jsonObj.dataType!="CT02" && jsonObj.dataType!="CT03" && jsonObj.dataType!="CT04") {
				rule["maxlength"] = jsonObj.dataLen;
			}
			if(jsonObj.dataType=="CT02") {
				rule["digits"] = true;
			}
			rules[jsonObj.fdcNm] = rule;
		}
	}
	
	// default
	rules["justification"] = {
			required : true
	}
	
	return rules;
};

function getValidationMessages(data) {
	messages = {};
	
	for(var i = 0; i < data.length; i++) {
		var jsonObj = data[i];
		if(jsonObj.visible!="N" && jsonObj.required=="Y") {
			message = {};
			message["required"] = "필수로입력하세요";
			if(jsonObj.dataType!="CT02" && jsonObj.dataType!="CT03" && jsonObj.dataType!="CT04") {
				message["maxlength"] = "최대 {0}글자이하입니다";
			}
			if(jsonObj.dataType=="CT02") {
				message["digits"] = "숫자만입력하세요";
			}
			messages[jsonObj.fdcNm] = message;
		}
	}
	
	// default
	messages["justification"] = {
			required : "필수로입력하세요"
	}
	
	return messages;
};

Map = function(){
	 this.map = new Object();
};  

Map.prototype = {   
    put : function(key, value){   
        this.map[key] = value;
    },   
    get : function(key){   
        return this.map[key];
    },
    containsKey : function(key){    
     return key in this.map;
    },
    containsValue : function(value){    
     for(var prop in this.map){
      if(this.map[prop] == value) return true;
     }
     return false;
    },
    isEmpty : function(key){    
     return (this.size() == 0);
    },
    clear : function(){   
     for(var prop in this.map){
      delete this.map[prop];
     }
    },
    remove : function(key){    
     delete this.map[key];
    },
    keys : function(){   
        var keys = new Array();   
        for(var prop in this.map){   
            keys.push(prop);
        }   
        return keys;
    },
    values : function(){   
     var values = new Array();   
        for(var prop in this.map){   
         values.push(this.map[prop]);
        }   
        return values;
    },
    size : function(){
      var count = 0;
      for (var prop in this.map) {
        count++;
      }
      return count;
    }
};

function displayLuvNm(luvId, lucList) {
  var luvNm = '';

  lucList.some(function (value, index, array) {
    var compare = array[index].code == luvId;
    if(compare) {
      luvNm = array[index].name;
    }
    return compare;
  });
  
  return luvNm;
}

// 비어 있는 지 검사
function isEmpty(strText) {
  if(( strText == null) || (strText == "") || isBlank(strText)) {
    return false;
  }
  return true;
}

// 공백 문자만 포함된 경우 true
function isBlank(strText) {
  for(var i = 0; i < strText.length ; i++ ){
    var c = strText.charAt(i);
    
    if((c != ' ') && (c != '\n') && (c != '\et')) {
      return false;
    }
  }
  return true;
}

// 연속문자 검사
function sequenceCharCheck(str, limit) {
    var o, d, p, n = 0, l = limit == null ? 4 : limit;
    for (var i = 0; i < str.length; i++) {
        var c = str.charCodeAt(i);
        if (i > 0 && (p = o - c) > -2 && p < 2 && (n = p == d ? n + 1 : 0) > l - 3) return false;
        d = p, o = c;
    }
    return true;
}

function getContextRootPath() {
  return '/' + window.location.pathname.split('/')[1];
}

function wrapWindowByMask(){
    // 화면의 높이와 너비를 변수로 만듭니다.
    var maskHeight = $(document).height();
    var maskWidth = $(window).width();

    // 마스크의 높이와 너비를 화면의 높이와 너비 변수로 설정합니다.
    $('.mask').css({'width':maskWidth,'height':maskHeight});

    // fade 애니메이션 : 1초 동안 검게 됐다가 80%의 불투명으로 변합니다.
    $('.mask').fadeIn(1000);
    $('.mask').fadeTo("slow",0.8);

    // 레이어 팝업을 가운데로 띄우기 위해 화면의 높이와 너비의 가운데 값과 스크롤 값을 더하여 변수로 만듭니다.
    var left = ( $(window).scrollLeft() + ( $(window).width() - $('.window').width()) / 2 );
    var top = ( $(window).scrollTop() + ( $(window).height() - $('.window').height()) / 2 );

    // css 스타일을 변경합니다.
    $('.window').css({'left':left,'top':top, 'position':'absolute'});

    // 레이어 팝업을 띄웁니다.
    $('.window').show();
}