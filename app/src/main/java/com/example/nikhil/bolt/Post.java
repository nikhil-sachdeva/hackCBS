package com.example.nikhil.bolt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;
import java.util.Map;

public class Post {

    String caption;
    double longitude,latitude;
    String imgURI;




    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
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

        result.put("latitude", latitude);
        result.put("longitude", longitude);
        result.put("caption", caption);
        result.put("imgURI",imgURI);

        return result;
    }

    public String getImgURI() {
        return imgURI;
    }

    public void setImgURI(String imgURI) {
        this.imgURI = imgURI;
    }


}
