
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
public class TomTomService implements SearchService {

    @Value("${tomtom.api.key}")
    private String apiKey;

    private final String TOMTOM_API_BASE_URL = "https://api.tomtom.com/search/2/";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public TomTomService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<SearchResult> searchSpas(double latitude, double longitude, int radius) {
        // Catégorie des spas
        String category = "9378005";
        // L'url, basée sur la page de l'API
        String url = TOMTOM_API_BASE_URL + "search/.json?" +
                "lat=" + latitude +
                "&lon=" + longitude +
                "&radius=" + radius +
                "&categorySet=" + category +
                "&view=Unified&relatedPois=off" +
                "&key=" + apiKey;

        String response = restTemplate.getForObject(url, String.class);

        return parseSearchResults(response);
    }

    private List<SearchResult> parseSearchResults(String response) {
        List<SearchResult> searchResults = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);

            JsonNode resultsNode = root.path("results");
            // Crée une instance de SearchResult par résultat retourné par l'API
            for (JsonNode resultNode : resultsNode) {
                SearchResult searchResult = new SearchResult();
                searchResult.setName(resultNode.path("poi").path("name").asText());
                searchResult.setAddress(resultNode.path("address").path("freeformAddress").asText());
                searchResult.setLatitude(resultNode.path("position").path("lat").asDouble());
                searchResult.setLongitude(resultNode.path("position").path("lon").asDouble());
                searchResults.add(searchResult);
            }

        } catch (IOException e) {
            // Handle exception (log or throw)
            e.printStackTrace();
        }

        return searchResults;
    }
}