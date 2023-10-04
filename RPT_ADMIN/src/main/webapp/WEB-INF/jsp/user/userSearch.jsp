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

	function getLoginType(arg) {
		if(arg == 'kakao')return '카카오로그인';
		if(arg == 'naver')return '네이버로그인';
		if(arg == 'google')return '구글로그인';
		if(arg == 'apple')return '애플로그인';
		if(arg == 'normal')return '일반가입';
	}
	
	function getUserType(arg) {
		if(arg == 'free')return '무료사용자';
		if(arg == 'charge')return '유료사용자';
	}
	$(document).ready(function() {
		var mainUsersGrid;
		
		if (!$("#usersTable").data("kendoWindow")) {
					mainUsersGrid = $("#usersTable").kendoGrid({
						noRecords: {
							template: "<br/><p><i class='fa fa-info-circle'></i> <spring:message code='common.fail.search' text='검색된 정보가 없습니다.' /></p>"
						},
						dataSource: {
							transport: {
								read: {
									url: "${contextPath}/user/userSearch/list",
									dataType: "json",
									type: "POST",
									cache: false
								},
								parameterMap: function (data, type) {
									if(type = "read"){
										$("#main_users_open_btn").attr("disabled", "disabled");
										
										var values = data;
										values["sort"] = JSON.stringify(values["sort"]);
										values["userId"] = $("#userId").val();
										values["userNm"] = $("#userNm").val();
										values["loginType"] = $("#loginType").val();
										values["userType"] = $("#userType").val();
										
										
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
						change: onUsrChange,
						dataBound: onUsrDataBound,
						resizable: true,
						columnMenu: true,
						columns: [
							{ field: "userId", title: "<font style='font-size:14px;font-weight:bold;'>아이디</font>"},
							{ field: "userNm", title: "<font style='font-size:14px;font-weight:bold;'>이름</font>"},
							{ field: "loginType", title: "<font style='font-size:14px;font-weight:bold;'>로그인타입</font>",template:"#= getLoginType(loginType) #"},
							{ field: "userType", title: "<font style='font-size:14px;font-weight:bold;'>사용자타입</font>",template:"#= getUserType(userType) #"},
							{ field: "lastLoginTime", title: "<font style='font-size:14px;font-weight:bold;'>최종로그인시간</font>"}
						]
					}).data("kendoGrid");
					
					var options = localStorage["USRMAIN"];
	                if (options) {
	                	mainUsersGrid.setOptions(JSON.parse(options));
	                }
		};
		
		$("#main_users_refresh_btn").click(function(e) {
			mainUsersGrid.dataSource.filter({});
			var options = localStorage["USRMAIN"];
            if (options) {
            	mainUsersGrid.setOptions(JSON.parse(options));
            }
			mainUsersGrid.dataSource.read();
		});
		
		$( "#main_users_open_btn" ).click(function(e) {
			var selectedItem = mainUsersGrid.dataItem(mainUsersGrid.select());
			$("#main-user-detail-window").load("${contextPath}/user/userSearch/detail", {userId : selectedItem.userId});
			
		});
		
		$( "#main_users_search_btn" ).click(function(e) {
			mainUsersGrid.dataSource.page(1);
		});
		
		$('input').keyup(function(e) {
		    if (e.keyCode == 13)mainUsersGrid.dataSource.page(1);    
		});
		
		$( "#loginType" ).change(function(e) {
			mainUsersGrid.dataSource.page(1);    
		});
		
		$( "#userType" ).change(function(e) {
			mainUsersGrid.dataSource.page(1);    
		});
		
		function onUsrChange(arg) {
			var selected = $.map(this.select(), function(item) {
				return $(item).text();
			});
			//console.log("Selected: " + selected.length + " item(s), [" + selected.join(", ") + "]");
			var usrGrid = $("#usersTable").data("kendoGrid");
			var selectedItem = usrGrid.dataItem(usrGrid.select());
			
			$("#main_users_open_btn").removeAttr("disabled");
			if(selectedItem.STTUS == "ST01") {
				$("#main_users_delete_btn").removeAttr("disabled");
			} else {
				$("#main_users_delete_btn").attr("disabled", "disabled");
			}
		}
		
		function onUsrDataBound(arg) {
			//console.log("Grid data bound");
			$("#main_users_open_btn").attr("disabled", "disabled");
			$("#main_users_refresh_btn").removeAttr("disabled");
			$("#main_users_search_btn").removeAttr("disabled");
			
			var dataView = this.dataSource.view();
	        for (var i = 0; i < dataView.length; i++) {
	            if (dataView[i].STTUS=="ST02") {
	                var uid = dataView[i].uid;
	                this.tbody.find("tr[data-uid=" + uid + "]").addClass("main-users-sttus-st02");  //alarm's in my style and we call uid for each row
	            }
	        }
		}
		
		
	}); // end-of-document
</script>
<div id="main-user-detail-window"></div>
<div class="panel panel-default" style="margin-top:12px;">
	<div class="panel-body">
	
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default" style="margin-top: 12px;">
					<div id="track_request_all_process_search_cond" class="panel-body">
						<div class="row">
							<div class="col-md-2" style="text-align: right;">
								<label><spring:message code='common.prcssDfntName' text='아이디' /> </label>
							</div>
							<div class="col-md-3" style="text-align: left;">
								<div class="form-group">
									<input id="userId" class="form-control input-sm" />
								</div>
							</div>
							<div class="col-md-2" style="text-align: right;">
								<label><spring:message code='enduser.register.justification' text='이름' /> </label>
							</div>
							<div class="col-md-3" style="text-align: left;">
								<div class="form-group">
									<input id="userNm" class="form-control input-sm" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2" style="text-align: right;">
								<label><spring:message code='common.sttus' text='로그인타입' /> </label>
							</div>
							<div class="col-md-3" style="text-align: left;">
								<div class="form-group">
									<select id="loginType" class="form-control">
										<option value="" selected>전체</option>
										<option value="kakao">카카오</option>
										<option value="naver">네이버</option>
										<option value="google">구글</option>
										<option value="apple">애플</option>
									</select>
								</div>
							</div>
							<div class="col-md-2" style="text-align: right;">
								<label><spring:message code="common.term" text="사용자타입" /> </label>
							</div>
							<div class="col-md-3" style="text-align: left;">
								<div class="form-group">
									<select id="userType" class="form-control">
										<option value="" selected>전체</option>
										<option value="free">무료</option>
										<option value="charge">유료</option>
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
					<button id="main_users_refresh_btn" type="button" class="btn btn-primary btn-sm" disabled="disabled"><i class="fa fa-refresh"></i> <spring:message code="common.btn.refresh" text="새로고침" /></button>
                 	<button id="main_users_open_btn" type="button" class="btn btn-primary btn-sm" disabled="disabled"><i class="fa fa-edit"></i> <spring:message code="common.detail" text="상세" /></button>
               </p>
			</div>
			<div class="col-md-6" style="text-align: right;">
				<p>
	            	<button id="main_users_search_btn" type="button" class="btn btn-primary btn-sm" style="width:120px;" disabled="disabled"><i class="fa fa-search"></i> <spring:message code="common.btn.search" text="검색" /></button>
	            </p>
			</div>
		</div>
		<div id="usersTable" class="ra-section"></div>
	</div>
</div>


<style type="text/css">
    @media screen and (min-width: 768px) {
        .modal-dialog {
          width: 850px; /* New width for default modal */
        }
        .modal-sm {
          width: 450px; /* New width for small modal */
        }
    }
    @media screen and (min-width: 992px) {
        .modal-lg {
          width: 1200px; /* New width for large modal */
        }
    }
</style>