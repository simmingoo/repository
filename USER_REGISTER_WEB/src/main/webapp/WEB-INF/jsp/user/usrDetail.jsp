<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<div class="modal fade" id="main-approvehistory-search-modal" role="dialog">
	<div id="usr_detail_tab_contents_${usrKey }" class="panel-body">
		<div class="tab-content">
			<div class="tab-pane fade in active" id="profile-${usrKey }">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default" style="margin-top:12px;">
						<div class="panel-heading">
	                		<button id="save_user_profile_${usrKey }" type="button" class="btn btn-primary btn-xs"><i class="fa fa-save"></i> <spring:message code="common.btn.save" text="수정" /></button>
	                	<!-- 	<button id="retire_user_profile_${usrKey }" type="button" class="btn btn-primary btn-xs"><i class="fa fa-minus-square-o"></i> <spring:message code="common.btn.save" text="퇴사" /></button> -->
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3" style="text-align:center;">
									<div class="picture-grid" style="padding-top:5px;">
	          							<address>
			                                <br><strong>${usrNm} (${usrLoginId}) / ${orgNm}</strong>
	                               			<br><p>${usrObj.EMAIL}</p>
			                            </address>
									</div>
								</div>
								<div class="form-group col-md-9" style="text-align:center;">
									<form id="admin_usrDetail_profileform_${usrKey}" data-toggle="validator" role="form">
										<div id="usr_profile_grid_${usrKey }">
											<input type="hidden" id="usrKey" name="usrKey" value="${usrKey} "/>
											<input type="hidden" id="usrLoginId" name="usrLoginId" value="${usrLoginId} "/>
											<div class="row">
												<div class="col-md-2" style="text-align: right;">
													<p>
														전화번호
													</p>
												</div>
												<div class="col-md-3" style="text-align: left;">
													<div class="form-group">
														<input class="form-control input-sm fdc-default" id="phone" name="phone" type="text" style="display: initial" value="${phone }">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-2" style="text-align: right;">
													<p>
														휴대폰번호
													</p>
												</div>
												<div class="col-md-3" style="text-align: left;">
													<div class="form-group">
														<input class="form-control input-sm fdc-default" id="cellPhone" name="cellPhone" type="text" style="display: initial" value="${cellPhone}">
													</div>
												</div>
											</div>
											<!-- 
											<div class="row">
												<div class="col-md-2" style="text-align: right;">
													<p>
														유효기간 시작일
													</p>
												</div>
												<div class="col-md-3" style="text-align: left;">
													<div class="form-group">
														<input class="form-control input-sm fdc-default dlgt-dt dlgt-start" id="startDt" name="startDt" type="text" style="display: initial" value="${startDt}">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-2" style="text-align: right;">
													<p>
														유효기간 종료일
													</p>
												</div>
												<div class="col-md-3" style="text-align: left;">
													<div class="form-group">
														<input class="form-control input-sm fdc-default dlgt-dt dlgt-end" id="endDt" name="endDt" type="text" style="display: initial" value="${endDt}">
													</div>
												</div>
											</div>
											 -->
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


<div class="modal fade" id="usrdetail_save_profile_check_dlg_${usrKey }" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h5 class="modal-title" id="myModalLabel"><i class="fa fa-check"></i> <spring:message code="common.btn.ok" text="2확인" /></h5>
            </div>
            <div class="modal-body">
               	<p class="text-info"><i class="fa fa-comments-o"></i> <spring:message code="common.confirm.save" arguments="사용자 프로파일을 " text="2사용자 프로파일을 저장하시겠습니까?" /></p>
            </div>
            <div class="modal-footer">
                <button id = "usrdetail_save_profile_check_btn_${usrKey }" type="button" class="btn btn-primary"><spring:message code="common.btn.ok" text="2확인" /></button>
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="common.btn.cancel" text="2취소" /></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="usrdetail_save_profile_result_${usrKey }" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h5 class="modal-title" id="myModalLabel"><i class="glyphicon glyphicon-info-sign"></i> <spring:message code="conmon.result" text="2결과" /></h5>
            </div>
            <div class="modal-body">
               	<p id="usrdetail_save_profile_result_message_${usrKey }"> </p>
            </div>
            <div class="modal-footer">
                <button id = "usrdetail_save_profile_result_confirm_button_${usrKey }" type="button" class="btn btn-primary"><spring:message code="common.btn.ok" text="2확인" /></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="usrdetail_update_warning_${usrKey }" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h5 class="modal-title" id="myModalLabel"><i class="glyphicon glyphicon-check"></i> <spring:message code="common.btn.ok" text="2확인" /></h5>
            </div>
            <div class="modal-body">
               	<p class="text-danger"><i class="fa fa-warning"></i> <spring:message code="common.msg.noChange" text="2변경사항이 없습니다." /></p>
               	<div style="display:none;"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal"><spring:message code="common.btn.ok" text="2확인" /></button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="account_edit_result_${usrKey }" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h5 class="modal-title" id="myModalLabel"><i class="glyphicon glyphicon-check"></i> <spring:message code="conmon.result" text="2결과" /></h5>
            </div>
            <div class="modal-body">
               	<p></p>
            </div>
            <div class="modal-footer">
                <button id = "account_edit_result_confirm_button_${usrKey }" type="button" class="btn btn-primay"><spring:message code="common.btn.ok" text="2확인" /></button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="usrdetail_edit_adminrole_org_${usrKey }" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h5 class="modal-title"></h5>
            </div>
            <div class="modal-body">
               	<div class="adminroledetail"></div>
               	<small class="text-primary">( <i class='fa fa-comments-o'></i> <spring:message code="common.msg.orgRequired" text="2하나 이상의 조직이 존재하여야 합니다." /> )</small>
            </div>
            <div class="modal-footer">
                <button id = "usrdetail_edit_adminrole_org_confirm_button_${usrKey }" type="button" class="btn btn-primary" disabled="disalbed"><spring:message code="common.btn.submit" text="2제출" /></button>
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="common.btn.cancel" text="2취소" /></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="usrdetail_dlgtor_new_${usrKey }" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h5 class="modal-title"><i class="fa fa-check-square-o"></i> <spring:message code="common.delegation" text="2권한위임" /></h5>
            </div>
            <div class="modal-body">
            	<form id="usrdetail_delegationform_${usrKey }" data-toggle="validator" role="form">
            	<div>
	            	<div class="row row-admr">
						<div class="col-md-2" style="text-align: left;">
						    <input type="checkbox" />
						    <span style="margin-left:5px;"><spring:message code="common.reqAdRoleDlgt" text="2관리역할 위임" /> </span>
						</div>
						<div class="col-md-2" style="text-align: left;">
					        <div class="form-group input-group">
								<input type="text" name="admrdlgteenm" class="form-control input-sm" required readonly />
			    				<input type="hidden" name="admrdlgteekey" />
			    				<span class="input-group-btn">
			    					<button class="btn btn-default btn-sm" type="button"><i class="fa fa-search"></i></button>
			    				</span>
	    					</div>
						</div>
						<div class="col-md-1" style="text-align: right;">
						    <div class="form-group">
								<select name="periodtype" class="form-control input-sm">
									<option value="DLGT-TEMPORARY" selected><spring:message code="common.temporary" text="2임시" /></option>
									<option value="DLGT-NOLIMIT"><spring:message code="common.noLimit" text="2상시" /></option>
								</select>
							</div>
						</div>
						
						<div class="col-md-1" style="text-align: right;">
						    <p><spring:message code="common.startDate" text="2시작일" />  </p>
						</div>
						<div class="col-md-2" style="text-align: left;">
							<div class="form-group">
								<input type="text" class="dlgt-dt dlgt-start" style="width: 100%;" />
							</div>
						</div>
						<div class="col-md-1" style="text-align: right;">
						    <p><spring:message code="common.endDate" text="2종료일" />  </p>
						</div>
						<div class="col-md-2" style="text-align: left;">
							<div class="form-group">
								<input type="text" class="dlgt-dt dlgt-end" style="width: 100%;" />
							</div>
						</div>
					</div>
					
					<div class="row row-apvp">
						<div class="col-md-2" style="text-align: left;">
							<input type="checkbox" />
						    <span style="margin-left:5px;"><spring:message code="common.apprAuthDlgt" text="2승인권한 위임" /> </span>
						</div>
						<div class="col-md-2" style="text-align: left;">
					        <div class="form-group input-group">
								<input type="text" name="apvpdlgteenm" class="form-control input-sm" required  readonly />
			    				<input type="hidden" name="apvpdlgteekey" />
			    				<span class="input-group-btn">
			    					<button class="btn btn-default btn-sm" type="button"><i class="fa fa-search"></i></button>
			    				</span>
	    					</div>
						</div>
						<div class="col-md-1" style="text-align: right;">
						    <div class="form-group">
								<select name="periodtype" class="form-control input-sm">
									<option value="DLGT-TEMPORARY" selected><spring:message code="common.temporary" text="2임시" /></option>
									<option value="DLGT-NOLIMIT"><spring:message code="common.noLimit" text="2상시" /></option>
								</select>
							</div>
						</div>
						<div class="col-md-1" style="text-align: right;">
						    <p><spring:message code="common.startDate" text="2시작일" />  </p>
						</div>
						<div class="col-md-2" style="text-align: left;">
							<div class="form-group">
								<input type="text" class="dlgt-dt dlgt-start" style="width: 100%;" />
							</div>
						</div>
						<div class="col-md-1" style="text-align: right;">
						    <p><spring:message code="common.endDate" text="2종료일" />  </p>
						</div>
						<div class="col-md-2" style="text-align: left;">
							<div class="form-group">
								<input type="text" class="dlgt-dt dlgt-end" style="width: 100%;" />
							</div>
						</div>
					</div>
            	</div>
            	</form>
            </div>
            <div class="modal-footer">
                <button type="button" name="confirm" class="btn btn-primary"><i class="fa fa-check"></i> <spring:message code="common.btn.ok" text="2확인" /></button>
                <button type="button" name="cancel" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> <spring:message code="common.btn.cancel" text="2취소" /></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="usrdetail_dlgtor_new_check_${usrKey }" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h5 class="modal-title"><i class="fa fa-check-square-o"></i> <spring:message code="common.btn.ok" text="2확인" /></h5>
            </div>
            <div class="modal-body">
            	<p><i class="fa fa-comments"></i> <spring:message code="common.confirm.save" arguments="추가 또는 변경된 권한위임을" text="2추가 또는 변경된 권한위임을 저장하시겠습니까?" /></p>
            	<input type="hidden" name="ADMR" />
            	<input type="hidden" name="APVP" />
            </div>
            <div class="modal-footer">
                <button type="button" name="confirm" class="btn btn-primary"><i class="fa fa-check"></i> <spring:message code="common.btn.ok" text="2확인" /></button>
                <button type="button" name="cancel" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> <spring:message code="common.btn.cancel" text="2취소" /></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="usrdetail_dlgtor_end_${usrKey }" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h5 class="modal-title"><i class="fa fa-check-square-o"></i> <spring:message code="common.btn.ok" text="2확인" /></h5>
            </div>
            <div class="modal-body">
            	<div>
	            	<p><i class="fa fa-comments"></i> 선택된 권한위임을 종료하시겠습니까?</p>
            	</div>
            </div>
            <div class="modal-footer">
                <button type="button" name="confirm" class="btn btn-primary"><i class="fa fa-check"></i> <spring:message code="common.btn.ok" text="2확인" /></button>
                <button type="button" name="cancel" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> <spring:message code="common.btn.cancel" text="2취소" /></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="usrdetail_dlgtor_result_${usrKey }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"><i class="fa fa-info-circle"></i> <spring:message code="common.result1" text="2처리결과" /></h5>
			</div>
			<div class="modal-body">
				<p id="usrdetail_dlgtor_result_message_${usrKey }"></p>
			</div>
			<div class="modal-footer">
				<button name="confirm" type="button" class="btn btn-primary"><spring:message code="common.btn.ok" text="2확인" /></button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="usrdetail_dlgtor_select_warn_${usrKey }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"><i class="fa fa-info-circle"></i> <spring:message code="common.confirm.warn" text="2경고" /></h5>
			</div>
			<div class="modal-body">
				<p class="danger"> <spring:message code="enduser.myinfo.msg.dlgtSelect" text="2위임할 새로운 항목이 선택되지 않았습니다." /></p>
			</div>
			<div class="modal-footer">
				<button name="confirm" type="button" class="btn btn-primary" data-dismiss="modal"><spring:message code="common.btn.ok" text="2확인" /></button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="usrdetail_progress_dlg" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
            	<div style="text-align:center;">
					<h2><i class="fa fa-refresh fa-spin"></i> <spring:message code="common.inproceed" text="2처리중..." /></h2>
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
          width: 1000px; /* New width for default modal */
        }
    }
    @media screen and (min-width: 992px) {
        .modal-lg {
          width: 1200px; /* New width for large modal */
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

	var usrKey = '${usrKey}';

	$("#save_user_profile_" + usrKey ).click(function(e) {
		data = $('#admin_usrDetail_profileform_'+usrKey).serialize();
		$.ajax({
			url : '${contextPath}/user/userEditAction',
			type : 'POST',
			data : data,
			datatype : "JSON",
			success : function(obj) {
				var data = JSON.parse(obj);
				if(data.isError == false){
					$.alert({
						title: '사용자 계정 변경 결과',
						content: '성공되었습니다.',
					});
					$("#main-approvehistory-search-modal").modal("hide");
				}else {
					$.alert({
						title: '사용자 계정 변경 결과',
						content: data.message,
					});
					$("#main-approvehistory-search-modal").modal("hide");
				}
			}
		});
	});

	$("#retire_user_profile_" + usrKey ).click(function(e) {
		data = $('#admin_usrDetail_profileform_'+usrKey).serialize();
		$.ajax({
			url : '${contextPath}/user/userRetireAction',
			type : 'POST',
			data : data,
			datatype : "JSON",
			success : function(obj) {
				var data = JSON.parse(obj);
				if(data.isError == false){
					$.alert({
						title: '사용자 계정 퇴사 처리 결과',
						content: '성공되었습니다.',
					});
					$("#main-approvehistory-search-modal").modal("hide");
				}else {
					$.alert({
						title: '사용자 계정 퇴사 처리 결과',
						content: data.message,
					});	
					$("#main-approvehistory-search-modal").modal("hide");
				}
			}
		});
	});

	if (!$("input.dlgt-start").data("kendoDatePicker")
			|| !$("input.dlgt-end").data("kendoDatePicker")) {
		var now = new Date();
		var mainApvpDlgtStart = $("input.dlgt-start").kendoDatePicker({
			value: $("#startDt").val(),
   	        format: "yyyyMMdd",
   	        change: function() {
   	        	var endDateObj = $("input.dlgt-end").data("kendoDatePicker");
       	    	var startDate = this.value(),
       	        endDate = endDateObj.value();
       	        if (startDate) {
       	            startDate = new Date(startDate);
       	            startDate.setDate(startDate.getDate());
       	         	endDateObj.min(startDate);
       	        } else if (endDate) {
       	        	this.max(new Date(endDate));
       	        } else {
       	            endDate = new Date();
       	            this.max(endDate);
       	         	endDateObj.min(endDate);
       	        }
       	    }
   	    }).data("kendoDatePicker");
		now.setDate(now.getDate()+365);
		var mainApvpDlgtEnd = $("input.dlgt-end").kendoDatePicker({
   	    	value: $("#endDt").val(),
   	        format: "yyyyMMdd",
   	        change: function() {
   	        	var startDateObj = $("input.dlgt-start").data("kendoDatePicker");
       	        var endDate = this.value(),
       	        startDate = startDateObj.value();

       	        if (endDate) {
       	            endDate = new Date(endDate);
       	            endDate.setDate(endDate.getDate());
       	         	startDateObj.max(endDate);
       	        } else if (startDate) {
       	        	this.min(new Date(startDate));
       	        } else {
       	            endDate = new Date();
       	         	startDateObj.max(endDate);
       	            this.min(endDate);
       	        }
       	    }
   	    }).data("kendoDatePicker");
		
	}
	
	
});
	
</script>