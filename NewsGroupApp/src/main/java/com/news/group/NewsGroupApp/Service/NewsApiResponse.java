package com.news.group.NewsGroupApp.Service;

import java.util.List;

public class NewsApiResponse {
    private List<Article> articles;

    // Getters and setters
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
