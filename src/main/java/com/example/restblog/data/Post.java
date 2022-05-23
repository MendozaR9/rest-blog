package com.example.restblog.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Post {
    private long id;
    private  String title;
    private String content;
@JsonIgnoreProperties("posts")
    private User user ;

    public Post(long id, String title, String content , User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }


    public Post() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }
}
