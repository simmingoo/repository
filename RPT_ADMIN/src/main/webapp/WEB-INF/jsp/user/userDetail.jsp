<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

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
		
		if (!$("#userStockTable").data("kendoWindow")) {
					mainUsersGrid = $("#userStockTable").kendoGrid({
						noRecords: {
							template: "<br/><p><i class='fa fa-info-circle'></i> <spring:message code='common.fail.search' text='검색된 정보가 없습니다.' /></p>"
						},
						dataSource: {
							transport: {
								read: {
									url: "${contextPath}/user/userStock/list",
									dataType: "json",
									type: "POST",
									cache: false
								},
								parameterMap: function (data, type) {
									if(type = "read"){
										var values = data;
										values["sort"] = JSON.stringify(values["sort"]);
										values["userId"] = '${userDetail.userId}';
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
						//change: onUsrChange,
						//dataBound: onUsrDataBound,
						resizable: true,
						columnMenu: true,
						columns: [
							{ field: "stockId", title: "<font style='font-size:14px;font-weight:bold;'>종목코드</font>"},
							{ field: "stockNm", title: "<font style='font-size:14px;font-weight:bold;'>종목명</font>"}
						]
					}).data("kendoGrid");
					
					var options = localStorage["USRSTOCKMAIN"];
	                if (options) {
	                	mainUsersGrid.setOptions(JSON.parse(options));
	                }
		};
		
		$("#main_users_refresh_btn").click(function(e) {
			mainUsersGrid.dataSource.filter({});
			var options = localStorage["USRSTOCKMAIN"];
            if (options) {
            	mainUsersGrid.setOptions(JSON.parse(options));
            }
			mainUsersGrid.dataSource.read();
		});
		
	}); // end-of-document
</script>
<div class="modal fade" id="main-approvehistory-search-modal" role="dialog">
	<div id="usr_detail" class="panel-body">
		<div class="tab-content">
			<div class="tab-pane fade in active" id="profile">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default" style="margin-top:12px;">
						<div class="panel-body">
							<div class="row">
								<div class="form-group col-md-9" style="text-align:center;">
									<form id="admin_usrDetail_profileform_${usrKey}" data-toggle="validator" role="form">
										<div id="usr_profile_grid_${usrKey }">
											<input type="hidden" id="usrKey" name="usrKey" value="${usrKey} "/>
											<div class="row">
												<div class="col-md-6" style="text-align: left;">
													<p>
														사용자 정보 : ${userDetail.userNm} (${userDetail.userId})
													</p>
												</div>
											</div>
											<div class="row">
												<div class="col-md-9" style="text-align: left;">
													<p>
														<c:choose>
															<c:when test="${userDetail.loginType eq 'kakao' }">
																로그인 타입 (카카오 로그인)		
															</c:when>
															<c:when test="${userDetail.loginType eq 'naver' }">
																로그인 타입 (네이버 로그인)		
															</c:when>
															<c:when test="${userDetail.loginType eq 'apple' }">
																로그인 타입 (애플 로그인)		
															</c:when>
															<c:when test="${userDetail.loginType eq 'normal' }">
																로그인 타입 (일반가입)		
															</c:when>
														</c:choose>
													</p>
												</div>
											</div>
											<div class="row">
												<div class="col-md-9" style="text-align: left;">
													<p>
														<c:choose>
															<c:when test="${userDetail.userType eq 'free' }">
																사용자 타입 (무료 사용자)		
															</c:when>
															<c:when test="${userDetail.userType eq 'charge' }">
																사용자 타입 (유료 사용자)		
															</c:when>
														</c:choose>
													</p>
												</div>
											</div>
											<div class="row">
												<div class="col-md-9" style="text-align: left;">
													<p>
														마지막 로그인 시간 (${userDetail.lastLoginTime })
													</p>
												</div>
											</div>
											<div id="userStockTable" class="ra-section"></div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
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
        .modal-md {
          width: 500px; /* New width for default modal */
        }
    }
    @media screen and (min-width: 500px) {
        .modal-lg {
          width: 500px; /* New width for large modal */
        }
    }
    
    .current_bizrole {
        color: #333333;
    }
    .avail_bizrole {
        color: #3e6792;
    }
    
    .itrsc-tobe-revoke {
	    color: red
	}
	.itrsc-tobe-grant {
	    color: #337ab7
	}
	.itrsc-rule-grant {
	    color: #A4A4A4
	}
	
    .big-checkbox {width: 30px; height: 30px;}
    
    .admin-dlgt-sttus-st02 {
    	color: #A4A4A4
	}
</style>
                        
<script>
$(document).ready(function() {
	$( "#main-approvehistory-search-modal" ).modal();
	
	$( "#main-approvehistory-search-modal" ).on('hide.bs.modal', function (e) {
		$(this).remove();
	});
});
	
</script>