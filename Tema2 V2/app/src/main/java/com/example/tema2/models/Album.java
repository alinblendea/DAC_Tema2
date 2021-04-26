package com.example.tema2.models;

import java.util.ArrayList;

public class Album extends Model{
    private long id;
    private long userId;
    private String title;
    private ArrayList<Photo> photos;

    public Album(ModelType modelType) {
        super(modelType);
    }

    public Album(long id, long userId, String title) {
        super(ModelType.ALBUM);
        this.userId = userId;
        this.id=id;
        this.title=title;
        photos = new ArrayList<Photo>();
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }
}

