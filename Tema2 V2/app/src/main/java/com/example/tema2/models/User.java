package com.example.tema2.models;

import java.util.ArrayList;

public class User extends Model{
    private long id;
    private String name;
    private String user;
    private String email;
    private ArrayList<Post> posts;
    private ArrayList<Album> albums;
    private boolean postsVisible;

    public User(ModelType modelType) {
        super(modelType);
    }

    public User(long id, String name, String emailAddress){
        super(ModelType.USER);
        this.id=id;
        this.name=name;
        this.email=emailAddress;
        postsVisible=false;
        posts=new ArrayList<Post>();
        albums=new ArrayList<Album>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean arePostsVisible() {
        return postsVisible;
    }

    public void setPostsVisible(boolean postsVisible) {
        this.postsVisible = postsVisible;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return user;
    }

    public String getEmailAddress() {
        return email;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setUsername(String username) {
        this.user = username;
    }

    public void setEmailAddress(String emailAddress) {
        this.email = emailAddress;
    }

    public boolean isPostsVisible() {
        return postsVisible;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
}
