package com.example.blissmap.Services;

import com.example.blissmap.Models.SearchResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TomTomService {

    @Value("${tomtom.api.key}")
    private String apiKey;

    private final String TOMTOM_API_BASE_URL = "https://api.tomtom.com/search/2/";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public TomTomService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<SearchResult> searchSpas(double latitude, double longitude, int radius) {
        String url = TOMTOM_API_BASE_URL + "search/bar.json?" +
                "lat=" + latitude +
                "&lon=" + longitude +
                "&radius=" + radius +
                "&categorySet=7315&view=Unified&relatedPois=off" +
                "&key=" + apiKey;

        String response = restTemplate.getForObject(url, String.class);

        return parseSearchResults(response);
    }

    private List<SearchResult> parseSearchResults(String response) {
        List<SearchResult> searchResults = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);

            // Assume the spa information is nested under 'results' in the response
            JsonNode resultsNode = root.path("results");

            for (JsonNode resultNode : resultsNode) {
                SearchResult searchResult = new SearchResult();
                searchResult.setName(resultNode.path("poi").path("name").asText());
                searchResult.setAddress(resultNode.path("address").path("freeformAddress").asText());
                searchResult.setLatitude(resultNode.path("position").path("lat").asText());
                searchResult.setLongitude(resultNode.path("position").path("lon").asText());
                //searchResult.setAddress(resultNode.path("address").asText());
                // Set other spa properties as neededs
                searchResults.add(searchResult);
            }

        } catch (IOException e) {
            // Handle exception (log or throw)
            e.printStackTrace();
        }

        return searchResults;
    }
}