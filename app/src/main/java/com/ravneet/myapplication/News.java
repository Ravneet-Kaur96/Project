package com.ravneet.myapplication;

import java.io.Serializable;

public class News implements Serializable {
    public int image;
    public String title;
    public String url;

    public News() {
    }

    public News(int image, String title, String url) {
        this.image = image;
        this.title = title;
        this.url = url;
    }
}
