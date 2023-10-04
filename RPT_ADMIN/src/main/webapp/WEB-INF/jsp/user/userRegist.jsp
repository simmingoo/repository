<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp"%>
<html lang="en">
<head>
<%@ include file="/resources/common/meta.jsp"%>
<meta charset="UTF-8">
<title>RPT ADMIN</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.11.2.min.js'/>"></script>
  <!-- Bootstrap CSS -->
  <link href="<c:url value='/resources/bootstrap/bower_components/bootstrap/dist/css/bootstrap.min.css'/>" rel="stylesheet">
  <link href="<c:url value='/resources/bootstrap/bower_components/metisMenu/dist/metisMenu.min.css'/>" rel="stylesheet">
  <link href="<c:url value='/resources/bootstrap/dist/css/timeline.css'/>" rel="stylesheet">
  <link href="<c:url value='/resources/bootstrap/dist/css/sb-admin-2.css'/>" rel="stylesheet">
  <link href="<c:url value='/resources/bootstrap/bower_components/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">
  <link href="<c:url value='/resources/js/fancytree/skin/ui.fancytree.css'/>" rel="stylesheet" type="text/css" />
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
  <!-- Kendo UI CSS -->
  <!-- 
  <link href="<c:url value='/resources/kendoui/styles/kendo.common.min.css'/>" rel="stylesheet" type="text/css" />
  <link href="<c:url value='/resources/kendoui/styles/kendo.common-bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
  <link href="<c:url value='/resources/kendoui/styles/kendo.bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
  <link href="<c:url value='/resources/kendoui/styles/kendo.dataviz.min.css'/>" rel="stylesheet" type="text/css" />
  <link href="<c:url value='/resources/kendoui/styles/kendo.dataviz.bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
   -->
  <link href="<c:url value='/resources/kendoui/styles/kendo.common.min-v2017.css'/>" rel="stylesheet" type="text/css" />
  <link href="<c:url value='/resources/kendoui/styles/kendo.common-bootstrap.min-v2017.css'/>" rel="stylesheet" type="text/css" />
  <link href="<c:url value='/resources/kendoui/styles/kendo.bootstrap.min-v2017.css'/>" rel="stylesheet" type="text/css" />
  <link href="<c:url value='/resources/kendoui/styles/kendo.dataviz.min-v2017.css'/>" rel="stylesheet" type="text/css" />
  <link href="<c:url value='/resources/kendoui/styles/kendo.dataviz.bootstrap.min-v2017.css'/>" rel="stylesheet" type="text/css" />
   
  <!-- tooltipster CSS -->
  <link href="<c:url value='/resources/js/tooltipster/tooltipster.bundle.min.css'/>" rel="stylesheet">
  
  <link href="<c:url value='/resources/css/common.css'/>" rel="stylesheet">
  
  
  <script type="text/javascript" src="<c:url value='/resources/js/ui/jquery-ui-1.10.4.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery.validate.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery.form.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/bootstrap-filestyle.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/bootstrap/bower_components/bootstrap/dist/js/bootstrap.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/bootstrap/bower_components/metisMenu/dist/metisMenu.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/bootstrap/dist/js/sb-admin-2.js'/>"></script>
  <!-- JSZIP for Excel Export -->
  <script type="text/javascript" src="<c:url value='/resources/js/jszip/jszip.min.js'/>"></script>
  <!-- 
  <script type="text/javascript" src="<c:url value='/resources/js/kendo.all.min.js'/>"></script>
   -->
  <script type="text/javascript" src="<c:url value='/resources/js/kendo.all.min-v2017.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/common.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/messages/kendo.messages.ko-KR.min.js'/>"></script>
  
  <!-- tooltipster -->
  <script type="text/javascript" src="<c:url value='/resources/js/tooltipster/tooltipster.bundle.min.js'/>"></script>

<link href="<c:url value='/resources/css/jquery-confirm.min.css'/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-confirm.min.js'/>"></script>

<script type="text/javascript">
	$(document).ready(function() {
		
		$('#sbmButton').click(function() {
			if($("#userId").val() == '') {
				alert("ID를 입력 해주세요.")
				$("#userId").focus();
				return;
			}
			
			if($("#userNm").val() == '') {
				alert("이름을 입력 해주세요.")
				$("#userNm").focus();
				return;
			}
			
			if($("#password").val() == '') {
				alert("패스워드를 입력 해주세요.")
				$("#password").focus();
				return;
			}
			
			if(!$("#isUserCheck").val()) {
				alert("ID 중복체크를 진행해 주세요.");
				$("#userId").focus();
				return;
			}
			
			data = $('#user_regist_form').serialize();
			$.ajax({
				url : '${contextPath}/user/userRegistAct',
				type : 'POST',
				data : data,
				datatype : "JSON",
				success : function(obj) {
					var data = JSON.parse(obj);
					if(data.isError == false){
						$.alert({
							title: '사용자 등록 결과',
							content: '등록되었습니다.',
						});	
					}else {
						$.alert({
							title: '사용자 등록 결과',
							content: data.message,
						});	
					}
					
				}
			});
			
		});
		
		$('#checkUsrBtn').click(function() {
			if($("#userId").val() == '') {
				alert("ID를 입력 해주세요.")
				$("#userId").focus();
				return;
			}
			
			data = $('#user_regist_form').serialize();
			$.ajax({
				url : '${contextPath}/user/userCheck',
				type : 'POST',
				data : data,
				datatype : "JSON",
				success : function(obj) {
					var data = JSON.parse(obj);
					if(data.isError == false){
						$.alert({
							title: '사용자ID 중복 체크 결과',
							content: data.message,
						});	
						$("#isUserCheck").val(true);
					}else {
						$.alert({
							title: '사용자ID 중복 체크 결과',
							content: data.message,
						});	
					}
					
				}
			});
			
		});
		
	});
</script>
</head>
<body>
	<input type="hidden" id="isUserCheck" value="">
	
	<article class="container">
	<div class="row" style="margin-left: 120px; margin-right: 120px;">
		<div class="col-lg-12">
			<div class="panel panel-default" style="margin-top: 12px;">
				<div class="panel-body" style="text-align: center;">
					<h3>
						<i class="fa fa-edit"></i> 사용자 ID 등록 신청
					</h3>
				</div>
			</div>
		</div>
	</div>
	<form action="" id="user_regist_form" name="user_regist_form" method="POST" class="form-horizontal"data-toggle="validator">
		<div class="form-group">
			<label class="col-sm-3 control-label" for="userId">사용자ID</label>
			<div class="col-sm-6">
				<input id="userId" class="form-control" name="userId" type="text" style="display: initial;width: 70%" value="">
				<button class="btn btn-primary" id="checkUsrBtn" type="button">ID 중복체크<i class="fa fa-mail-forward spaceLeft"></i></button>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="userNm">사용자명</label>
			<div class="col-sm-6">
				<input id="userNm" class="form-control" name="userNm" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="password">패스워드</label>
			<div class="col-sm-6">
				<input id="password" class="form-control" name="password" type="password" value="">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-3 control-label" for="loginType">로그인타입</label>
			<div class="col-sm-6">
				<select name="loginType" id="loginType"  class="form-control">
					<option value="kakao">카카오로그인</option>
					<option value="naver">네이버로그인</option>
					<option value="google">구글로그인</option>
					<option value="apple">애플로그인</option>
					<option value="normal">일반가입</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="userType">사용자타입</label>
			<div class="col-sm-6">
				<select name="userType" id="userType"  class="form-control">
					<option value="free">무료사용자</option>
					<option value="charge">유료사용자</option>
				</select>
			</div>
		</div>
		<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" id="sbmButton" type="button">등록<i class="fa fa-check spaceLeft"></i></button>
				</div>
		</div>
	</form>
	</article>
</body>
</html>