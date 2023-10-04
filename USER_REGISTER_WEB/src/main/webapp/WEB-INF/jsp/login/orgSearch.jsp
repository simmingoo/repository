<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<script>
	var commonLuvDataType = ${luvDataType};
	var commonLuvDataSttus = ${luvDataSttus};

	$(document).ready(function() {
		 
		if (!$("#common-org-search-table").data("kendoWindow")) {
			$("#common-org-search-table").kendoGrid({
				dataSource: {
					transport: {
						read: {
							url: "${contextPath}/login/orgSearch/list",
							dataType: "json",
							type: "POST",
							cache: false
						},
						parameterMap: function (data, type) {
							if(type = "read"){
								var values = data;
								
								if($("#common_org_orgnm_cond").val()) values["ORGNM"] = $("#common_org_orgnm_cond").val();
								if($("#common_org_porgnm_cond").val()) values["PORGNM"] = $("#common_org_porgnm_cond").val();
								
								return values;
							}
						}
					},
					schema: {
						data: "list", 				// records are returned in the "data" field of the response
						total: "totalRecords" 		// total number of records is in the "total" field of the response
					},
					serverPaging: true,
					serverSorting: false,
					serverFiltering: false
				},
				sortable: true,
				pageable: {
					pageSize: 5,
					
					buttonCount: 10
				},
	            selectable: "true",
				columns: [
		            { field: "orgKey", hidden: true },
		            { field: "pOrgkey", hidden: true },
					{ field: "orgNm", title: "<font style='font-size:14px;font-weight:bold;'>조직</font>"},
					{ field: "pOrgNm", title: "<font style='font-size:14px;font-weight:bold;'>상위조직</font>"},
					{ field: "orgType", title: "<font style='font-size:14px;font-weight:bold;'>구분</font>", template: "#= getCommonLuvNm('dataType',orgType) #"},
					{ field: "sttus", title: "<font style='font-size:14px;font-weight:bold;'>상태</font>", template: "#= getCommonLuvNm('sttusType',sttus) #"}
				]
			}).data("kendoGrid");
		}
		
		
		$( "#common_org_search_btn" ).click(function(e) {
			$("#common-org-search-table").data("kendoGrid").dataSource.page(1);
		});
		
		$( "#common-org-search-modal" ).modal();
		
		$( "#common-org-search-confirm" ).click(function(e) {
			var commonOrgSearchGrid = $("#common-org-search-table").data("kendoGrid");
			var selectedItem = commonOrgSearchGrid.dataItem(commonOrgSearchGrid.select());
        	if(typeof(selectedItem) !== 'undefined' && selectedItem !== null) {
            	//console.log(selectedItem.orgKey + "/" + selectedItem.orgNm);
            	var callBackOrgFnc = $("#callBackOrgFnc").val();
            	window.parent[callBackOrgFnc](selectedItem);	
        	}
        	$( "#common-org-search-modal" ).modal("hide");
		});
		
		$( "#common-org-search-modal" ).on('hide.bs.modal', function (e) {
			$(this).remove();
   		});
		
		$("#common_org_search_cond").find("input.form-control").keypress(function (e) {
			var key = e.which;
			if(key == 13) {  // the enter key code
				$("#common-org-search-table").data("kendoGrid").dataSource.page(1);
			}
		});
	});
	
	
    function getCommonLuvNm(lucType, luvId) {
    	var arrLuvData;
    	if(lucType == "dataType") {
    		arrLuvData = commonLuvDataType;
    	} else if (lucType == "sttusType") {
    		arrLuvData = commonLuvDataSttus;
    	}
    	for (var i = 0; i < arrLuvData.length; i++) {
    		if (arrLuvData[i].luvId == luvId) {
    			return arrLuvData[i].luvNm;
    		}
    	}
    }
    
</script>

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

<input type="hidden" id="callBackOrgFnc" value="${callBackFnc }" />

<!-- Modal for Organization Search -->
<div class="modal fade" id="common-org-search-modal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h5 class="modal-title" id="myModalLabel"><i class="fa fa-sitemap"></i> 조직 선택</h5>
            </div>
            <div class="modal-body">
               	<div class="panel panel-default" style="margin-top:5px;padding-bottom:-10px;">
					<div id="common_org_search_cond" class="panel-body">
						<div class="row">
							<div class="col-md-2" style="text-align: right;">
							    <p>조직 </p>
							</div>
							<div class="col-md-4" style="text-align: left;">
						        <div class="form-group">
						            <input id="common_org_orgnm_cond" class="form-control input-sm" />
						        </div>
							 </div>
							 <div class="col-md-2" style="text-align: right;">
							    <p>상위조직 </p>
							</div>
							<div class="col-md-4" style="text-align: left;">
						        <div class="form-group">
						            <input id="common_org_porgnm_cond" class="form-control input-sm" />
						        </div>
							 </div>
						</div>                
					</div>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-body">
						<div style="text-align: right; margin-bottom: 5px;">
					    	<button id="common_org_search_btn" type="button" class="btn btn-primary btn-sm" style="width:160px;"><i class="fa fa-search"></i> 검색</button>
						</div>
						<div id="common-org-search-table" class="ra-section"></div>
					</div>
				</div>
            </div>
            <div class="modal-footer" style="text-align: center;">
                <button id="common-org-search-confirm" type="button" name="confirm" class="btn btn-primary" style="width:120px;"><i class="fa fa-check"></i> 확인</button>
                <button type="button" name="cancel" class="btn btn-default" data-dismiss="modal" style="width:120px;"><i class="fa fa-times"></i> 취소</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
  


