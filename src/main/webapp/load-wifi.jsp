<%@ page import="api.SeoulOpenWifi" %>
<%@ page import="api.WifiInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dbController.DbOpenWifiInfoControl" %><%--
  Created by IntelliJ IDEA.
  User: songkey2
  Date: 2022-09-27
  Time: 오후 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
  <%
    System.out.println("load start");
    SeoulOpenWifi api = new SeoulOpenWifi();
    ArrayList<WifiInfo> wifiList = new ArrayList<>();
    wifiList = api.getWifiList();

    DbOpenWifiInfoControl db = new DbOpenWifiInfoControl();
    db.deleteWifiInfoTable();
    db.insertWifiInfo(wifiList);
    %>
  <h2><%=wifiList.size()%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h2>

  <p><a href="index.jsp">홈 으로 가기</a></p>

</body>
</html>
