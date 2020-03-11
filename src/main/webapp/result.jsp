<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<body>
<center>
<h1>
    Invoke Error Results
</h1>
<%
Integer result = (Integer) request.getAttribute("result");
out.println("<br>Result = <br><br>"+result.toString()+"<br>");

%>
</body>
</html>
