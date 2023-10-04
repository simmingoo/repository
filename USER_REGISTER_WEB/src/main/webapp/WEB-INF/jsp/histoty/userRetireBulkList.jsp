<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<script>

	$(document).ready(function() {
		 
		if (!$("#main_userretire_grid").data("kendoWindow")) {
			$("#main_userretire_grid").kendoGrid({
				noRecords: {
					template: "<br/><p><i class='fa fa-info-circle'></i> 등록된 사용자가 없습니다.</p>"
				},
				dataSource: {
					transport: {
						read: {
							url: "${contextPath}/userRetireBulk/list",
							dataType: "json",
							type: "POST",
							cache: false
						},
						parameterMap: function (data, type) {
							if(type = "read"){
								var values = data;
								values["REQID"] = $("#reqId").val();
								values["sort"] = JSON.stringify(values["sort"]);
								return values;
							}
						}
					},
					schema: {
						data: "list", 				// records are returned in the "data" field of the response
						total: "totalRecords" 		// total number of records is in the "total" field of the response
					},
					serverPaging: true,
					serverSorting: true,
					serverFiltering: false
				},
				sortable: true,
				pageable: {
					pageSize: 10,
					pageSizes: [5, 10, 20, 100],
					buttonCount: 10
				},
				selectable: true,
				resizable: true,
				columnMenu: true,
				columns: [
		            { field: "reqSubId", hidden: true },
					{ field: "usrLoginId", title: "<font style='font-size:14px;font-weight:bold;'>사번</font>"},
					{ field: "usrNm", title: "<font style='font-size:14px;font-weight:bold;'>이름</font>"},
					{ field: "endDt", title: "<font style='font-size:14px;font-weight:bold;'>종료일</font>", template: "#= getDate(endDt) #"}
				]
			}).data("kendoGrid");
		}
		
		
		$( "#main-approvehistory-search-modal" ).modal();
		
		$( "#main-approvehistory-search-modal" ).on('hide.bs.modal', function (e) {
			$(this).remove();
   		});
		
	});
	
	
    
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

<input type="hidden" id="reqId" value="${reqId }" />
<input type="hidden" id="reqType" value="${reqType }" />

<!-- Modal for Usranization Search -->
<div class="modal fade" id="main-approvehistory-search-modal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
				
				<div class="panel panel-default">
					<div class="panel-body">
						<div id="main_userretire_grid" class="ra-section"></div>
					</div>
				</div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
  


