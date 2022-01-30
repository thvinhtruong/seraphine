package com.example.seraphine.model;

import java.util.Objects;

public class Point {
    private double longitude;
    private double latitude;

    public Point(){}

    public Point(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return Double.compare(point.getLongitude(), getLongitude()) == 0 && Double.compare(point.getLatitude(), getLatitude()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLongitude(), getLatitude());
    }


    public double calculateDistance(Point p) {
        // use Haversin formula
        double delta_p1 = ((p.getLongitude() - this.longitude)*Math.PI)/180;
        double delta_p2 = ((p.getLatitude() - this.latitude)*Math.PI)/180;
        double res = Math.pow(Math.sin(delta_p2/2), 2) + Math.cos(this.latitude* Math.PI/180) * Math.cos(p.getLatitude() * Math.PI/180)*Math.pow(Math.sin(delta_p1/2), 2);
        double res_kilometer = 6378.8 * (2 * Math.asin(Math.sqrt(res)));
        return res_kilometer;
    }


}
