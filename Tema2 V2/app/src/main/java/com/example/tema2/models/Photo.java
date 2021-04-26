package com.example.tema2.models;

public class Photo {
    private long albumId;
    private long id;
    private String title;
    private String url;
    private String thumbUrl;

    public Photo() {
    }

    public Photo(long id, long albumId, String title, String url, String thumbUrl){
        this.id=id;
        this.albumId=albumId;
        this.title = title;
        this.thumbUrl = thumbUrl;
        this.url=url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getUrl() {
        return url;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}

