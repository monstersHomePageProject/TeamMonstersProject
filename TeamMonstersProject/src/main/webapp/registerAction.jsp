<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@ page import = "monsters.model.*" %>
<jsp:useBean id="member" class = "monsters.model.MemberDTO" scope = "page" />
<jsp:setProperty property="*" name="member" />
<jsp:useBean id="service" class = "monsters.model.MemberDAO" scope = "application" />
<jsp:setProperty property="user" name ="service" value = "<%= member %>" />
<%@ page import = "java.io.PrintWriter" %>

<html>
<head>
<meta charset="UTF-8">
<title>로그인 동작</title>
</head>
<body>
<%
	int result = service.register();
	if(result == 1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원가입이 완료되었습니다.')");
		script.println("location.href = 'login.html'");
		script.println("</script>");
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원가입에 실패했습니다.')");
		script.println("</script>");
	}
%>
</body>
</html>