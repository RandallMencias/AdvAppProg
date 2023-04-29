package com.example.proyecto_6;

import androidx.annotation.NonNull;

public class Nasa {
    private String title;
    private String http;
    private String url;

    public Nasa(String title, String http, String url) {
        this.title = title;
        this.http = http;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nasa{" +
                "title='" + title + '\'' +
                ", http='" + http + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
