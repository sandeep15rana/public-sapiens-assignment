package com.news.group.NewsGroupApp.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class NewsService {

    @Value("${newsapi.url}")
    private String apiUrl;

    @Value("${newsapi.key}")
    private String apiKey;

    private boolean offlineMode;

    private final RestTemplate restTemplate;

    public NewsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setOfflineMode(boolean mode) {
        this.offlineMode = mode;
    }

    public Map<String, Object> getGroupedNews(String keyword, int interval, String unit) {
        if (offlineMode) {
            return mockNewsData();
        }

        List<Article> articles = fetchArticlesFromApi(keyword);

        List<GroupedData> groupedData = groupArticlesByTimeInterval(articles, interval, unit);

        Map<String, Object> results = new HashMap<>();
        results.put("keyword", keyword);
        results.put("groupedData", groupedData);
        return results;
    }

    private List<Article> fetchArticlesFromApi(String keyword) {
        String url = apiUrl + "?q=" + keyword + "&apiKey=" + apiKey;

        NewsApiResponse response = restTemplate.getForObject(url, NewsApiResponse.class);

        return response != null ? response.getArticles() : new ArrayList<>();
    }

    private List<GroupedData> groupArticlesByTimeInterval(List<Article> articles, int interval, String unit) {
        Map<String, List<Article>> grouped = new HashMap<>();
        
        for (Article article : articles) {
            LocalDateTime articleTime = article.getPublishedAt();
            String intervalKey = getIntervalKey(articleTime, interval, unit);
            grouped.computeIfAbsent(intervalKey, k -> new ArrayList<>()).add(article);
        }

        List<GroupedData> groupedData = new ArrayList<>();
        for (Map.Entry<String, List<Article>> entry : grouped.entrySet()) {
            groupedData.add(new GroupedData(entry.getKey(), entry.getValue()));
        }
        return groupedData;
    }

    private String getIntervalKey(LocalDateTime articleTime, int interval, String unit) {
        return interval + " " + unit;  // For example, 12 hours
    }

    private Map<String, Object> mockNewsData() {
        Map<String, Object> mockResults = new HashMap<>();
        mockResults.put("message", "Offline mode is enabled. Showing mock data.");
        mockResults.put("groupedData", List.of("Mock article 1", "Mock article 2"));
        return mockResults;
    }
}
