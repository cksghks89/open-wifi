package history;

import java.util.Date;

public class LocationHistory {
    private double x;
    private double y;
    private Date date;
    private int id;

    public LocationHistory(){}

    public LocationHistory(double x, double y, Date date) {
        this.x = x;
        this.y = y;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }
}
