package com.example.seraphine.model;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

/**
 * Location class to get a precise location of an input user and default doctor
 * @author Vinh Truong Canh Thanh
 */

public class Location {
    String address;

    /**
     * Contructor
     * @author Vinh Truong Canh Thanh
     */

    public Location(){}
    public Location(String address) {
        this.address = address;
    }

    /**
     * Getter and Setter
     * @author Vinh Truong Canh Thanh
     */

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Checking equal function
     * @author Vinh Truong Canh Thanh
     * @return boolean
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(getAddress(), location.getAddress());
    }

    /**
     * Hash code for security enhance to protect user private
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public int hashCode() {
        return Objects.hash(getAddress());
    }

    /**
     * To String function
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                '}';
    }

    /**
     * Convert input user / doctor address into longitude and latitude
     * @author Vinh Truong Canh Thanh
     */

    public Point locationConverter() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> location_raw = restTemplate.exchange("https://nominatim.openstreetmap.org/?addressdetails=1&q="+this.address+"&format=json&limit=1",
                HttpMethod.GET, null, String.class );

        String location_detail = location_raw.getBody();
        location_detail = location_detail.replace("\"address\":{", "");
        location_detail = location_detail.replace("[","");
        location_detail = location_detail.replace("]","");
        location_detail = location_detail.replace("}}","");
        location_detail = location_detail.replace("{","");

        List<String> test = new ArrayList<String>();
        String[] list = location_detail.split(",\"");
        Point p = new Point();

        for (int i = 0; i < list.length; i++) {

            String j = list[i].replace("\"", "");
            list[i] = j;

            String[] list1 = list[i].split(":");


            if (list1[0].equals("lat")) {
                test.add(list1[1]);
            }
            if (list1[0].equals("lon")) {

                test.add(list1[1]);
            }
            if (list1[0].equals("place_id")) {

                test.add(list1[1]);
            }
            if (list1[0].equals("country")) {

                test.add(list1[1]);
            }
        }
        p.setLongitude(Double.parseDouble(test.get(2)));
        p.setLatitude(Double.parseDouble(test.get(1)));

        return p;
    }

    /**
     * Calculate distance between user and doctor based on Haversin's formula
     * @author Vinh Truong Canh Thanh
     */

    public double distanceCalculator(Point p_user) {
        return this.locationConverter().calculateDistance(p_user);
    }
}
