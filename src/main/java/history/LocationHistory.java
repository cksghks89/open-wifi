package history;

import java.time.LocalDateTime;

public class LocationHistory {
    private double x;
    private double y;
    private LocalDateTime date;
    private int id;

    public LocationHistory(){}

    public LocationHistory(double x, double y, LocalDateTime date) {
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

    public void setDate(LocalDateTime date) {
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
    public LocalDateTime getDate() {
        return date;
    }
}
