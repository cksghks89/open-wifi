package dbController;

import history.LocationHistory;
import java.sql.*;
import java.util.ArrayList;

public class DbLocationHistoryControl {
    private final String PROJECT_PATH = "C:/Users/songkey2/Desktop/open-wifi";
    private final String DB_PATH = "src/main/resources/openwifi.sqlite";

    public ArrayList<LocationHistory> loadHistory(){
        ArrayList<LocationHistory> historyList = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+PROJECT_PATH+"/"+DB_PATH);

            String sql = "select * from LOCATION_HISTORY " +
                    "order by SEARCH_DATE desc " +
                    "limit 50;";

            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while(rs.next()){
                LocationHistory history = new LocationHistory();

                history.setX(Double.parseDouble(rs.getString("LAT")));
                history.setY(Double.parseDouble(rs.getString("LNT")));
                history.setDate(Date.valueOf(rs.getString("SEARCH_DATE")));
                history.setId(Integer.parseInt(rs.getString("ID")));

                historyList.add(history);
            }
            return historyList;
        } catch (SQLException e) {
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
    }

    public void insertHistory(LocationHistory history){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+PROJECT_PATH+"/"+DB_PATH);

            String sql = "insert into LOCATION_HISTORY (LAT, LNT, SEARCH_DATE) " +
                    "VALUES (?, ?, ?);";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(history.getX()));
            preparedStatement.setString(2, String.valueOf(history.getY()));
            preparedStatement.setString(3, String.valueOf(history.getDate()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
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
    }

    public void deleteHistory(int id){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+PROJECT_PATH+"/"+DB_PATH);

            String sql = "delete from LOCATION_HISTORY where id=?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(id));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
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
    }
}
