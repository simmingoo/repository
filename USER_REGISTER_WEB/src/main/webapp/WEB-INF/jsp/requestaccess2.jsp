<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<script type="text/javascript">
	document.formAgent.submit();
	function setUsrField(selectedItem) {
		var reqTypeVal = $("#requestaccess2").contents().find('input[name="reqType"]:checked').val();
		if(reqTypeVal == 'E'){
			$("#requestaccess2").contents().find( "#editUsrLoginId" ).val(selectedItem.usrLoginId);
			$("#requestaccess2").contents().find( "#editUsrNm" ).val(selectedItem.usrNm);
		}else if(reqTypeVal == 'R'){
			$("#requestaccess2").contents().find( "#retireUsrLoginId" ).val(selectedItem.usrLoginId);
			$("#requestaccess2").contents().find( "#retireUsrNm" ).val(selectedItem.usrNm);
		}
	}

	function setApprUsrField(selectedItem) {
			$("#requestaccess2").contents().find("#apprUserId").val(selectedItem.usrLoginId);
			$("#requestaccess2").contents().find("#apprUserNm").val(selectedItem.usrNm + "(" + selectedItem.orgNm + ")");
	}
</script>

<style type="text/css">
	.tab-pane .tab-pane-main .fade .in .active {
   	 	height: 1000px;
	}
</style>


<div style="height:1000px;">
	<form name="formAgent" method="post" action="../user/userAddBulkView" target="requestaccess2" >
		<input type="hidden" name="usrkey" id="usrkey" value="${usrKey }" />
	</form>
	
	<iframe id="requestaccess2" name="requestaccess2" width="100%" height="100%" framespacing="0" marginheight="0" frameborder="0" marginwidth="0" scrolling="auto"></iframe>
</div>
