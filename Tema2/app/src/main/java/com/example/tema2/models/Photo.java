package com.example.tema2.models;

public class Photo {
    private int id;
    private int albumId;
    private String title;
    private String photoUrl;
    private String thumbUrl;

    public Photo(int id, int albumId, String title, String photoUrl, String thumbUrl) {
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.photoUrl = photoUrl;
        this.thumbUrl = thumbUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
