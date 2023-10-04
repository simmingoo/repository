<%@ page contentType="text/html; charset=utf-8"%>
<% 
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	response.setHeader("Cache-Control", "no-cache");
	request.setCharacterEncoding("UTF-8");

	String resultMsg  = (String)request.getAttribute("resultMsg");

    out.println("<script>");
    out.println("alert('"+resultMsg+"');");
    out.println("window.close();");
    out.println("</script>");
%>