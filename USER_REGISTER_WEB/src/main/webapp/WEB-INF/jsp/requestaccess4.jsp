<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<script type="text/javascript">
	document.formAgent4.submit();
</script>

<style type="text/css">
	.tab-pane .tab-pane-main .fade .in .active {
   	 	height: 1000px;
	}
</style>


<div style="height:1000px;">
	<form name="formAgent4" method="post" action="../user/usr" target="requestaccess4" >
		<input type="hidden" name="usrkey" id="usrkey" value="${usrKey }" />
	</form>
	
	<iframe name="requestaccess4" width="100%" height="100%" framespacing="0" marginheight="0" frameborder="0" marginwidth="0" scrolling="auto"></iframe>
</div>
