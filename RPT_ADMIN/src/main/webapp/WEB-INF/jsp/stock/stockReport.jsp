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
	    $("textarea").kendoEditor({
	        stylesheets: [
	            "/resources/css/editor.css",
	        ],
	        resizable: {
	            content: false,
	            toolbar: true
	        },
	        imageBrowser: {
	            transport: {
	                read: "/report/imagebrowser/read",
	                destroy: "/report/imagebrowser/destroy",
	                create: "/report/imagebrowser/createDirectory",
	                uploadUrl: "/report/imagebrowser/upload",
	                thumbnailUrl: "/report/zimagebrowser/thumbnail",
	                imageUrl: "/content/images/{0}"
	            }
	         }
	    });
	
	    
	    
	    var editor = $("textarea").data("kendoEditor");
	
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
   		<form action="${pageContext.request.contextPath}/stock/stockReportAct" method="POST" enctype="multipart/form-data" class="form-horizontal"data-toggle="validator" accept-charset="UTF-8">
			<div class="form-group">
				<label class="col-sm-3 control-label" for="stockId">종목</label>
				<div class="col-sm-6">
					<input id="stockId" name="stockId" type="hidden" value="${stockInfo.stockId }">
					${stockInfo.stockId }(${stockInfo.stockNm })
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="corpOverviewContent">기업개요</label>
				<div class="col-sm-6">
					<textarea id="corpOverviewContent" name="corpOverviewContent" rows="10" cols="30" style="width:100%; height:200px" aria-label="editor">
					</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="industryProperty">산업의특성</label>
				<div class="col-sm-6">
					<textarea id="industryProperty" name="industryProperty" rows="10" cols="30" style="width:100%; height:200px" aria-label="editor">
					</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="elementCompetition">경쟁요소</label>
				<div class="col-sm-6">
					<textarea id="elementCompetition" name="elementCompetition" rows="10" cols="30" style="width:100%; height:200px" aria-label="editor">
					</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="companyStatus">회사의현황</label>
				<div class="col-sm-6">
					<textarea id="companyStatus" name="companyStatus" rows="10" cols="30" style="width:100%; height:200px" aria-label="editor">
					</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="primaryProductSales">주요제품의 매출</label>
				<div class="col-sm-6">
					<input type="file" id="primaryProductSales" class="form-control input-sm form-control-dt10 fdc-default" name="primaryProductSales" style="display: initial;" value="">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="incomeStatementImagePath1">손익계산서 이미지</label>
				<div class="col-sm-6">
					<input type="file" id="incomeStatementImagePath1" class="form-control input-sm form-control-dt10 fdc-default" name="incomeStatementImagePath1" style="display: initial;" value="">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="incomeStatementImageContent1">손익계산서 이미지 설명</label>
				<div class="col-sm-6">
					<textarea id="incomeStatementImageContent1" name="incomeStatementImageContent1" rows="10" cols="30" style="width:100%; height:200px" aria-label="editor">
					</textarea>
				</div>
			</div>		
			<div class="form-group">
				<label class="col-sm-3 control-label" for="incomeStatementImagePath2">안정성 비율 이미지</label>
				<div class="col-sm-6">
					<input type="file" id="incomeStatementImagePath2" class="form-control input-sm form-control-dt10 fdc-default" name="incomeStatementImagePath2" style="display: initial;" value="">
				</div>
			</div>		
			<div class="form-group">
				<label class="col-sm-3 control-label" for="incomeStatementImageContent2">안정성 비율 이미지 설명</label>
				<div class="col-sm-6">
					<textarea id="incomeStatementImageContent2" name="incomeStatementImageContent2" rows="10" cols="30" style="width:100%; height:200px" aria-label="editor">
					</textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" id="sbmButton" type="submit">리포트 등록 <i class="fa fa-check spaceLeft"></i></button>
				</div>
			</div>
		</form>

</html>