package dbController;

import apiController.WifiInfo;

import java.sql.*;
import java.util.ArrayList;

public class DbOpenWifiInfoControl {
    private final String PROJECT_PATH = "C:/Users/songkey2/Desktop/open-wifi";
    private final String DB_PATH = "src/main/resources/openwifi.sqlite";

    public ArrayList<WifiInfo> getWifiInfo(String lat, String lnt){
        ArrayList<WifiInfo> rtnWifiList = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + PROJECT_PATH + "/" + DB_PATH);
            System.out.println("Open database successfully");

            String sql = "select (" +
                    "    6371 * acos(cos(radians(?)) " +
                    "        * cos(radians(lat)) " +
                    "        * cos(radians(lnt) - radians(?)) " +
                    "        + sin(radians(?)) * sin(radians(lat))) " +
                    "           ) as distance, " +
                    "    WIFI_INFO.* " +
                    "from WIFI_INFO " +
                    "group by distance " +
                    "order by distance " +
                    "limit 20;";

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, lnt);
            preparedStatement.setString(2, lat);
            preparedStatement.setString(3, lnt);

            rs = preparedStatement.executeQuery();

            while(rs.next()){
                WifiInfo curInfo = new WifiInfo();
                curInfo.setDistance(Double.parseDouble(rs.getString("distance")));
                curInfo.setMgrNo(rs.getString("MGR_NO"));
                curInfo.setWrdofc(rs.getString("WRDOFC"));
                curInfo.setMainName(rs.getString("MAIN_NM"));
                curInfo.setAdres1(rs.getString("ADRES1"));
                curInfo.setAdres2(rs.getString("ADRES2"));
                curInfo.setInstlFloor(rs.getString("INSTL_FLOOR"));
                curInfo.setInstlTy(rs.getString("INSTL_TY"));
                curInfo.setInstlMby(rs.getString("INSTL_MBY"));
                curInfo.setSvcSe(rs.getString("SVC_SE"));
                curInfo.setCmcwr(rs.getString("CMCWR"));
                curInfo.setCnstcYear(rs.getString("CNSTC_YEAR"));
                curInfo.setInOutDoor(rs.getString("INOUT_DOOR"));
                curInfo.setRemars3(rs.getString("REMARS3"));
                curInfo.setLat(Double.parseDouble(rs.getString("LAT")));
                curInfo.setLnt(Double.parseDouble(rs.getString("LNT")));
                curInfo.setWorkDttm(rs.getString("WORK_DTTM"));

                rtnWifiList.add(curInfo);
            }

            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return rtnWifiList;
    }

    public boolean insertWifiInfo(ArrayList<WifiInfo> wifiList){

        // DB class load
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + PROJECT_PATH + "/" + DB_PATH);
            System.out.println("Open database successfully");

            // auto commit false 로 설정하지 않으면 데이터 삽입속도가 매우 느려짐
            // 각각의 데이터 insert 구문마다 트랜잭션을 발생시키기 때문
            connection.setAutoCommit(false);

            for(int i = 0; i < wifiList.size(); i++){
                WifiInfo curInfo = wifiList.get(i);

                String sql = "insert into WIFI_INFO " +
                        "(" +
                        "MGR_NO, " +
                        "WRDOFC, " +
                        "MAIN_NM, " +
                        "ADRES1, " +
                        "ADRES2, " +
                        "INSTL_FLOOR, " +
                        "INSTL_TY, " +
                        "INSTL_MBY, " +
                        "SVC_SE, " +
                        "CMCWR, " +
                        "CNSTC_YEAR, " +
                        "INOUT_DOOR, " +
                        "REMARS3, " +
                        "LAT, " +
                        "LNT, " +
                        "WORK_DTTM" +
                        ") " +
                        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, curInfo.getMgrNo());
                preparedStatement.setString(2, curInfo.getWrdofc());
                preparedStatement.setString(3, curInfo.getMainName());
                preparedStatement.setString(4, curInfo.getAdres1());
                preparedStatement.setString(5, curInfo.getAdres2());
                preparedStatement.setString(6, curInfo.getInstlFloor());
                preparedStatement.setString(7, curInfo.getInstlTy());
                preparedStatement.setString(8, curInfo.getInstlMby());
                preparedStatement.setString(9, curInfo.getSvcSe());
                preparedStatement.setString(10, curInfo.getCmcwr());
                preparedStatement.setString(11, curInfo.getCnstcYear());
                preparedStatement.setString(12, curInfo.getInOutDoor());
                preparedStatement.setString(13, curInfo.getRemars3());
                preparedStatement.setString(14, String.valueOf(curInfo.getLat()));
                preparedStatement.setString(15, String.valueOf(curInfo.getLnt()));
                preparedStatement.setString(16, curInfo.getWorkDttm());

                int affected = preparedStatement.executeUpdate();
                if(affected == 1){
                    System.out.println(i+1 + "data insert success");
                }else{
                    System.out.println(i+1 + "data insert fail");
                }
            }

            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    private double gpsDistance(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;
        double dist = Math.sin(this.deg2rad(lat1)) * Math.sin(this.deg2rad(lat2))
                + Math.cos(this.deg2rad(lat1)) * Math.cos(this.deg2rad(lat2)) * Math.cos(this.deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344;

        return dist;
    }
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
