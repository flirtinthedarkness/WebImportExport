<%--
  Created by IntelliJ IDEA.
  User: Fenyr_Allen
  Date: 2018-10-10
  Time: 09:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath();
    request.setAttribute("basePath", basePath);
%>