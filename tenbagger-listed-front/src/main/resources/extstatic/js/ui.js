// 하위 브라우저 호환
document.createElement("article");
document.createElement("section");
document.createElement("aside");
document.createElement("nav");
document.createElement("header");
document.createElement("footer");
document.createElement("main");

// IE 하위 브라우저에 .ie_old 클래스 추가 => 프로젝트에 따라 변경될 수 있습니다.
getInternetExplorerVersion();
function getInternetExplorerVersion() {
	var rv = -1; // Return value assumes failure.
	if (navigator.appName != "Microsoft Internet Explorer"){
		return;
	}
	else {
		var ua = navigator.userAgent;
		var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
		if (re.exec(ua) != null)
			rv = parseFloat(RegExp.$1);
	}
	if(rv < 11){ // IE브라우저 8버전 이하 시 ie_old 추가
		rv = 10;
		$("html").addClass("ie_old");
	}
	$("html").addClass("ie"+rv);
}

$(function(){ 
	//셀랙트 박스 세팅
	for (var i =0; i < $(".slct").length; i++) {
		var target = $(".slct").eq(i);
		if($.trim(target.find(".slct_tit").text()) === "")
			target.find(".slct_tit").text(target.find(".slct_cont .select").text());
		
	}
	$(".slct_tit").on("click", function (e) {
		if($(this).parent().hasClass("on") === true) {
			$(this).parent().removeClass("on");
		}else {
			$(".slct").removeClass("on");
			$(this).parent().addClass("on");
		}
	});
	$(".slct_cont li").on("click", function(){
		$(this).parents(".slct").find(".slct_tit").text($(this).text());
		$(this).parent().find("li").removeClass("select");
		$(this).addClass("select");
		$(this).parents(".slct").removeClass("on");
	});
	$(document).on("click", function(e){
		if(e.target.className === "slct" || e.target.className === "slct_tit" || e.target.className === "slct_cont") {return false;}
		$(".slct").removeClass("on");
	});
	//lnb
	$(".lnb .menu").on("click", function(e){
		e.preventDefault();
		if($(this).hasClass("on") === true) {
			$(this).removeClass("on");
		}else {
			$(".lnb .menu").removeClass("on");
			$(this).addClass("on");
		}
	});
	$(".pop .btn_close").on("click", function(e){
		e.preventDefault();
		$(this).parents(".pop").hide();
	});
	$(".pop .fnClose").on("click", function(e){
		e.preventDefault();
		$(this).parents(".pop").hide();
	});
	//faq
	$(".cs .tbl_list .btn_txt").on("click", function(e){
		e.preventDefault();
		console.log("---");
		if(	$(this).parents(".tr_tit").hasClass("on") === true) {
			$(this).parents(".tr_tit").removeClass("on");
			$(this).text("펼치기");
		}else {
			$(".tr_tit").removeClass("on");
			$(".tr_tit .btn_txt").text("펼치기");
			$(this).parents(".tr_tit").addClass("on");
			$(this).text("닫기");
		}
		// e.stopPropagation()
	});
});
$(window).resize(function(){
});