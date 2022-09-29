<%@ page import="dbController.dbConnection" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>와이파이 정보 구하기</h2>
    <%
        dbConnection dbController = new dbConnection();
        dbController.select();
    %>
    <nav>
        <ul>
            <li>홈</li>
            <li>위치 히스토리 목록</li>
            <li>Open API 와이파이 정보 가져오기</li>
        </ul>
    </nav>

    <div>
        <form action="getWifi.jsp" method="GET">
            <label for="lat">LAT : </label><input type="text" id="lat">
            <label for="lnt">LNT : </label><input type="text" id="lnt">

            <button type="button">내 위치 가져오기</button>
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
        </table>
    </div>
</body>
</html>