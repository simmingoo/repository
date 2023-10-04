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

	$(document).ready(function () {
	    $("#editor").kendoEditor({
	        stylesheets: [
	            "/resources/css/editor.css",
	        ],
	        resizable: {
	            content: false,
	            toolbar: true
	        },
	        imageBrowser: {
	            transport: {
	                read: "imagebrowser/read",
	                destroy: "imagebrowser/destroy",
	                create: "imagebrowser/createDirectory",
	                uploadUrl: "imagebrowser/upload",
	                thumbnailUrl: "imagebrowser/thumbnail",
	                imageUrl: "/content/images/{0}"
	            }
	         }
	    });
	
	    var editor = $("#editor").data("kendoEditor");
	
	    var setValue = function () {
	        editor.value($("#value").val());
	    };
	
	    $("#get").click(function () {
	        alert(editor.value());
	    });
	
	    $("#set").click(setValue);
	});
</script>

<style type="text/css">
	.col-sm-6{
		padding-top:7px;
	}
</style>
</head>
<body>
	<form action="report_regist_form" method="POST" class="form-horizontal"data-toggle="validator">
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">종목</label>
			<div class="col-sm-6">
				<input id="stockId" name="stockNm" type="hidden" value="">
				삼성전자(008000)
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">시가총액</label>
			<div class="col-sm-6">
				10000(억)
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">추천일종가(원)</label>
			<div class="col-sm-6">
				10000(억)
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">적정시가총액(억원)</label>
			<div class="col-sm-6">
				<input class="form-control" id="reqUserId" class="form-control input-sm form-control-dt10 fdc-default" name="reqUserId" type="text" style="display: initial;" value="" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">기대수익률(%)</label>
			<div class="col-sm-6">
				<input class="form-control" id="reqUserId" class="form-control input-sm form-control-dt10 fdc-default" name="reqUserId" type="text" style="display: initial;" value="" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">영업이익(억원)</label>
			<div class="col-sm-6">
				10000(억)
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">예상실적(억원)</label>
			<div class="col-sm-6">
				10000(억)
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">EPS성장률</label>
			<div class="col-sm-6">
				-22
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">PER</label>
			<div class="col-sm-6">
				15.33
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">업종PER</label>
			<div class="col-sm-6">
				259.99
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">PBR</label>
			<div class="col-sm-6">
				1.80
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">PSR</label>
			<div class="col-sm-6">
				1.73
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">발행주식수</label>
			<div class="col-sm-6">
				13,123,000
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">유통주식수/비율</label>
			<div class="col-sm-6">
				50%
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">52주최저가</label>
			<div class="col-sm-6">
				50%
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">52주최고가</label>
			<div class="col-sm-6">
				50%
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="stockId">주요사업</label>
			<div class="col-sm-6">
				<textarea id="editor" rows="10" cols="30" style="width:100%; height:200px" aria-label="editor">
				</textarea>
			</div>
		</div>
		
	</form>
</body>
</html>