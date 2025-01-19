package com.news.group.NewsGroupApp.Service;

import java.util.List;

public class GroupedData {
    private String interval;
    private List<Article> articles;

    public GroupedData(String interval, List<Article> articles) {
        this.interval = interval;
        this.articles = articles;
    }

    // Getters and setters
    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
