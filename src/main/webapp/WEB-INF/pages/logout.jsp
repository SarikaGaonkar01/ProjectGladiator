<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page errorPage="ErrorPage.jsp" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
  <!--disabling back button  -->
  <script type="text/javascript">
   history.pushState(null,null,location.href);
   window.onpopstate=function()
   {
	   history.go(1);
	   window.location.href = "index-2.jsp";
   };
  </script>
<body>
  <div>
    <a href="index-2.jsp"><button type="submit" class="submit-btn1" autofocus>Back to Welcome Page</button></a>
  </div>
</body>
</html>