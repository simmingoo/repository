<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp"%>
<html lang="en">
<head>
<%@ include file="/resources/common/meta.jsp"%>
<title>RPT ADMIN</title>

<head>
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

<style>
.k-grid td{
    white-space: nowrap;
    text-overflow: ellipsis;
}
.main-users-sttus-st02 {
    color: #e51400
}
</style>


<script>

	$(document).ready(function() {
		var mainStocksGrid;
		
		if (!$("#stocksTable").data("kendoWindow")) {
					mainStocksGrid = $("#stocksTable").kendoGrid({
						noRecords: {
							template: "<br/><p><i class='fa fa-info-circle'></i> <spring:message code='common.fail.search' text='검색된 정보가 없습니다.' /></p>"
						},
						dataSource: {
							transport: {
								read: {
									url: "${contextPath}/stock/stockSearch/list",
									dataType: "json",
									type: "POST",
									cache: false
								},
								parameterMap: function (data, type) {
									if(type = "read"){
										$("#main_stocks_report_btn").attr("disabled", "disabled");
										
										var values = data;
										values["sort"] = JSON.stringify(values["sort"]);
										values["stockId"] = $("#stockId").val();
										values["stockNm"] = $("#stockNm").val();
										values["stockType"] = $("#stockType").val();
										values["stockReportYn"] = $("#stockReportYn").val();
										
										return values;
									}
								}
							},
							schema: {
								data: "list", 				// records are returned in the "data" field of the response
								total: "totalRecords", 		// total number of records is in the "total" field of the response
							},
							serverPaging: true,
							serverSorting: true
						},
						sortable: {
							mode: "multiple"
						},
						pageable: {
							pageSize: 10,
							pageSizes: [5, 10, 20, 100],
							buttonCount: 10
						},
						reorderable: true,
						//persistSelection: true,
						selectable: true,
						resizable: true,
						columnMenu: true,
						change: onChangeStock,
						columns: [
							{ field: "stockId", title: "<font style='font-size:14px;font-weight:bold;'>종목코드</font>"},
							{ field: "stockNm", title: "<font style='font-size:14px;font-weight:bold;'>종목명</font>"},
							{ field: "stockType", title: "<font style='font-size:14px;font-weight:bold;'>종목타입</font>"},
							{ field: "stockReportYn", title: "<font style='font-size:14px;font-weight:bold;'>리보트 보유 여부</font>"}
						]
					}).data("kendoGrid");
					
					var options = localStorage["STOCKMAIN"];
	                if (options) {
	                	mainStocksGrid.setOptions(JSON.parse(options));
	                }
		};
		
		$("#main_stocks_refresh_btn").click(function(e) {
			mainStocksGrid.dataSource.filter({});
			var options = localStorage["STOCKMAIN"];
            if (options) {
            	mainStocksGrid.setOptions(JSON.parse(options));
            }
			mainStocksGrid.dataSource.read();
		});
		
		$( "#main_stocks_report_btn" ).click(function(e) {
			var selectedItem = mainStocksGrid.dataItem(mainStocksGrid.select());
			$("#popStockId").val(selectedItem.stockId)
			
			var reportPop = document.popForm;
			var url="/stock/stockReport";

			 window.open("" ,"popForm", "width=892,height=1080,location=no,resizable=yes");  
			 
			 reportPop.action =url; 
			 reportPop.method="post";
			 reportPop.target="popForm";
			 reportPop.submit();

		});
		
		
		
		$( "#main_stocks_search_btn" ).click(function(e) {
			mainStocksGrid.dataSource.page(1);
		});
		
		$( "#stockType" ).change(function(e) {
			mainStocksGrid.dataSource.page(1);
		});
		
		$( "#stockReportYn" ).change(function(e) {
			mainStocksGrid.dataSource.page(1);
		});
		
		$('input').keyup(function(e) {
		    if (e.keyCode == 13)mainStocksGrid.dataSource.page(1);    
		});
	}); // end-of-document
	
	function onChangeStock(arg) {
		$("#main_stocks_report_btn").removeAttr("disabled");
	}
</script>
<body>
	<form action="/stock/stockReport" name="popForm" method="post">
		<input type="hidden" name="popStockId" id="popStockId">
	</form>
</body>


<div class="panel panel-default" style="margin-top:12px;">
	<div class="panel-body">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default" style="margin-top: 12px;">
					<div id="track_request_all_process_search_cond" class="panel-body">
						<div class="row">
							<div class="col-md-2" style="text-align: right;">
								<label>종목 코드 </label>
							</div>
							<div class="col-md-3" style="text-align: left;">
								<div class="form-group">
									<input id="stockId" class="form-control input-sm search" />
								</div>
							</div>
							<div class="col-md-2" style="text-align: right;">
								<label> 종목명 </label>
							</div>
							<div class="col-md-3" style="text-align: left;">
								<div class="form-group">
									<input id="stockNm" class="form-control input-sm search" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2" style="text-align: right;">
								<label>종목 타입 </label>
							</div>
							<div class="col-md-3" style="text-align: left;">
								<div class="form-group">
									<select id="stockType" name="stockType" class="form-control">
										<option value="">전체</option>
										<option value="일반주">일반주</option>
										<option value="가치주">가치주</option>
										<option value="차트주">차트주</option>
									</select>
								</div>
							</div>
							<div class="col-md-2" style="text-align: right;">
								<label> 리포트 보유 여부 </label>
							</div>
							<div class="col-md-3" style="text-align: left;">
								<div class="form-group">
									<select id="stockReportYn" name="stockReportYn" class="form-control">
										<option value="">전체</option>
										<option value="리포트 보유">리포트 보유</option>
										<option value="리포트 미 보유">리포트 미 보유</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
       <div class="row">
			<div class="col-md-6" style="text-align: left;">
				<p>
					<button id="main_stocks_refresh_btn" type="button" class="btn btn-primary btn-sm" ><i class="fa fa-refresh"></i> <spring:message code="common.btn.refresh" text="새로고침" /></button>
                 	<button id="main_stocks_report_btn" type="button" class="btn btn-primary btn-sm" ><i class="fa fa-edit"></i> <spring:message code="common.detail" text="리포트 작성" /></button>
               </p>
			</div>
			<div class="col-md-6" style="text-align: right;">
				<p>
	            	<button id="main_stocks_search_btn" type="button" class="btn btn-primary btn-sm" style="width:120px;" ><i class="fa fa-search"></i> <spring:message code="common.btn.search" text="검색" /></button>
	            </p>
			</div>
		</div>
		<div id="stocksTable" class="ra-section"></div>
	</div>
</div>

