<%@ page import="dbController.DbLocationHistoryControl" %>
<%@ page import="history.LocationHistory" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: songkey2
  Date: 2022-09-27
  Time: 오후 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    <table>
        <thead>
            <tr>
                <td>ID</td>
                <td>LAT</td>
                <td>LNT</td>
                <td>조회 일자</td>
                <td>비고</td>
            </tr>
        </thead>
        <tbody>
            <%
                DbLocationHistoryControl db = new DbLocationHistoryControl();
                ArrayList<LocationHistory> history = new ArrayList<>();

                history = db.loadHistory();

                for(int i = 0; i < history.size(); i++){
            %>
                    <tr id="<%=i%>">
                        <td><%=history.get(i).getId()%></td>
                        <td><%=history.get(i).getX()%></td>
                        <td><%=history.get(i).getY()%></td>
                        <td><%=history.get(i).getDate()%></td>
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
