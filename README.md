# public-sapiens-assignment


# Introduction

The News Search Microservice provides a search interface for fetching news articles from an external NewsAPI based on a keyword. It also supports grouping the results based on different time intervals (e.g., hours, days, weeks) and offers offline mode functionality where mock data is provided when the external API is unavailable.

# Design Pattern Used

1. Singleton Pattern:
Used for managing the toggle for offline mode. This ensures that there is only one instance of the service handling the offline state throughout the application lifecycle.

2. Strategy Pattern:
Used to handle different grouping strategies for news articles based on the provided interval. This allows us to easily change the logic for grouping articles in the future.

3. Factory Pattern:
The Factory pattern is used to generate appropriate data processing strategies based on the time interval chosen (e.g., hour, day, week).

4. Facade Pattern:
The News API client acts as a facade, abstracting the complexity of external API calls and providing a simplified interface for the rest of the application.

5. Observer Pattern:
Used for toggling offline mode, ensuring that any changes to the state of offline mode can notify all relevant components.


# System Flow

The system follows the following flow:

User Input:

The user submits a request with a keyword, and optionally, a time interval and unit.
Processing:

The service first checks if offline mode is enabled.
If online mode is enabled, the service makes an API call to the NewsAPI to fetch the news articles.
If offline mode is enabled, mock data is returned.
Grouping:

Based on the time interval and unit, the news articles are grouped accordingly using the Strategy Pattern.


# API Endpoint

# POST http://localhost:8080/api/news/toggle-offline?mode=false

# Success Response

"Offline mode is now disabled"

# GET http://localhost:8080/api/news/search?keyword=apple&interval=12&unit=hour

# Success Response

{
    "groupedData": [
        {
            "interval": "12 hour",
            "articles": [
                {
                    "title": "Apple TV Plus is free to stream this weekend",
                    "description": "Apple is offering its Apple TV Plus streaming service for free from January 4th through 5th.",
                    "publishedAt": "2024-12-30T16:05:24"
                },
                {
                    "title": "Masayoshi Son Bet Billions on the iPhone—3 Years Before It Existed",
                    "description": "How SoftBank CEO Masayoshi Son convinced Steve Jobs to make the deal of the century.",
                    "publishedAt": "2025-01-16T11:00:00"
                }
            ]
        }
    ],
    "keyword": "apple"
}


# Technologies Used

1. Spring Boot: For building the microservice.
2. Angular: For frontend Application
3. NewsAPI: For fetching relevant news articles.
4. Postman: For testing the API.

