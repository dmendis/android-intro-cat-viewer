package com.m2d2apps.catviewer.models;

import java.util.ArrayList;
import java.util.List;

public class PhotosList {
    ArrayList<Photo> data;

    public PhotosList() {
        data = new ArrayList<Photo>();
    }

    public ArrayList<Photo> getPhotos() {
        return data;
    }

    public void addPhoto(Photo photo) {
        data.add(photo);
    }
}
