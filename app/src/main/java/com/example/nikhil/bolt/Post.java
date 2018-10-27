package com.example.nikhil.bolt;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

public class Post {
    String Location;
    String caption;
    double longitude,latitude;
    Bitmap img;


    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }



    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
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
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("location", Location);
        result.put("latitude", latitude);
        result.put("longitude", longitude);
        result.put("caption", caption);


        return result;
    }
}
