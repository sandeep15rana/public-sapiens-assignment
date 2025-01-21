package com.news.group.NewsGroupApp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.news.group.NewsGroupApp.Service.NewsService;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/search")
    public Map<String, Object> getNews(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "12") int interval,
            @RequestParam(defaultValue = "hours") String unit) 
    {
    	if( keyword == null || keyword.isEmpty())
    	{
    		throw new IllegalArgumentException("KEYword cannot be empty!!");
    	}
        return newsService.getGroupedNews(keyword, interval, unit);
    }

    @PostMapping("/toggle-offline")
    public String toggleOffline(@RequestParam boolean mode) {
        newsService.setOfflineMode(mode);
        return "Offline mode is now " + (mode ? "enabled" : "disabled");
    }
}
