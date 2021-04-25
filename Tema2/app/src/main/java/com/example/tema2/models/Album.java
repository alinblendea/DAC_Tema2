package com.example.tema2.models;

import java.util.ArrayList;

public class Album extends Model{
    private int id;
    private int userId;
    private String title;
    private ArrayList<Photo> photos;

    public Album(ModelType modelType, int id, int userId, String title) {
        super(modelType);
        this.id = id;
        this.userId = userId;
        this.title = title;
        photos = new ArrayList<Photo>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }
}
