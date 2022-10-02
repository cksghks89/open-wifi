<%@ page import="dbController.DbOpenWifiInfoControl" %>
<%@ page import="api.WifiInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>와이파이 정보 구하기</h2>

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

    <div>
        <form method="GET" action="index.jsp" onsubmit="return validateForm()">
            <label for="lat">LAT : </label><input type="text" name="lat" id="lat">
            <label for="lnt">LNT : </label><input type="text" name="lnt" id="lnt">

            <button type="button" onclick=setLocation()>내 위치 가져오기</button>
            <input type="submit" value="근처 WIFI 정보 보기">
        </form>
    </div>
    <br>
    <div>
        <table>
            <thead>
                <tr>
                    <td>거리(Km)</td>
                    <td>관리번호</td>
                    <td>자치구</td>
                    <td>와이파이명</td>
                    <td>도로명주소</td>
                    <td>상세주소</td>
                    <td>설치위치(층)</td>
                    <td>설치유형</td>
                    <td>설치기관</td>
                    <td>서비스구분</td>
                    <td>망종류</td>
                    <td>설치년도</td>
                    <td>실내외 구분</td>
                    <td>WIFI 접속환경</td>
                    <td>X좌표</td>
                    <td>Y좌표</td>
                    <td>작업일자</td>
                </tr>
            </thead>
            <tbody>
                <%
                    // reqest의 get 데이터가 있으면 진행
                    // 없다면 위치 정보를 입력한 후에 조회 문구 출력.
                    String lat = request.getParameter("lat");
                    String lnt = request.getParameter("lnt");

                    if(lat == null || lnt == null || lat.equals("") || lnt.equals("")){
                        %>
                        <tr>
                            <td colspan="17">위치정보를 입력한 후에 조회해 주세요.</td>
                        </tr>
                        <%
                    }else{
                        DbOpenWifiInfoControl db = new DbOpenWifiInfoControl();
                        ArrayList<WifiInfo> wifiList = db.getWifiInfo(lat, lnt);

                        for(int i = 0; i < wifiList.size(); i++){
                        %>
                            <tr>
                                <td><%= wifiList.get(i).getDistance()%></td>
                                <td><%=wifiList.get(i).getMgrNo()%></td>
                                <td><%=wifiList.get(i).getWrdofc()%></td>
                                <td><%=wifiList.get(i).getMainName()%></td>
                                <td><%= wifiList.get(i).getAdres1()%></td>
                                <td><%=wifiList.get(i).getAdres2()%></td>
                                <td><%=wifiList.get(i).getInstlFloor()%></td>
                                <td><%=wifiList.get(i).getInstlTy()%></td>
                                <td><%=wifiList.get(i).getInstlMby()%></td>
                                <td><%=wifiList.get(i).getSvcSe()%></td>
                                <td><%=wifiList.get(i).getCmcwr()%></td>
                                <td><%=wifiList.get(i).getCnstcYear()%></td>
                                <td><%=wifiList.get(i).getInOutDoor()%></td>
                                <td><%=wifiList.get(i).getRemars3()%></td>
                                <td><%=wifiList.get(i).getLat()%></td>
                                <td><%=wifiList.get(i).getLnt()%></td>
                                <td><%=wifiList.get(i).getWorkDttm()%></td>
                            </tr>
                    <%
                        }
                    }
                %>

            </tbody>
        </table>
    </div>
    <script src="indexControl.js"></script>
</body>
</html>
