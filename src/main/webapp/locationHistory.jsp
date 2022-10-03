<%@ page import="dbController.DbLocationHistoryControl" %>
<%@ page import="history.LocationHistory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: songkey2
  Date: 2022-09-27
  Time: 오후 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="tableStyle.scss">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<%
    String id = request.getParameter("id");
    if(id != null && !id.equals("")){
        DbLocationHistoryControl db = new DbLocationHistoryControl();
        db.deleteHistory(Integer.parseInt(id));
    }
%>
    <h2>위치 히스토리 목록</h2>
    <nav>
        <ul>
            <li>
                <a href="index.jsp">홈</a>
            </li>
            <li>
                <a href="locationHistory.jsp">위치 히스토리 목록</a>
            </li>
            <li>
                <a href="load-wifi.jsp" id="loadBtn">Open API 와이파이 정보 가져오기</a>
            </li>
        </ul>
    </nav>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>LAT</th>
                <th>LNT</th>
                <th>조회 일자</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>
            <%
                DbLocationHistoryControl db = new DbLocationHistoryControl();
                ArrayList<LocationHistory> history;

                history = db.loadHistory();

                for(int i = 0; i < history.size(); i++){
            %>
                    <tr id="<%=history.get(i).getId()%>">
                        <td><%=history.get(i).getId()%></td>
                        <td><%=history.get(i).getX()%></td>
                        <td><%=history.get(i).getY()%></td>
                        <td><%=history.get(i).getDate().format(DateTimeFormatter.ofPattern("yyyy-M-dd HH:mm:ss"))%></td>
                        <td><button>삭제</button></td>
                    </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <script src="locHistoryJs.js"></script>
</body>
</html>
