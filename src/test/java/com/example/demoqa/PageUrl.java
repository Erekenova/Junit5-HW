package com.example.demoqa;

public class PageUrl {
    private String endPoint;

    public PageUrl(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public PageUrl setEndPoint(String endPoint) {
        this.endPoint = endPoint;
        return this;
    }
}
