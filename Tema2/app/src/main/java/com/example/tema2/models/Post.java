package com.example.tema2.models;

public class Post extends Model{
    private int id;
    private int userId;
    private String title;
    private String content;

    public Post(ModelType modelType, int id, int userId, String title, String content) {
        super(modelType);
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
