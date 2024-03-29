package com.example.blissmap.Controllers;

import com.example.blissmap.Models.SearchResult;
import com.example.blissmap.Services.SearchService;
import com.example.blissmap.Services.TomTomService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SearchController {

    @Value("${tomtom.api.key}")
    private String tomtomApiKey;

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("/performSearch")
    public String searchSpasBetter(@RequestParam("latitude") double latitude,
                                   @RequestParam("longitude") double longitude,
                                   @RequestParam("radius") int radius,
                                   Model model) {
        List<SearchResult> searchResults = searchService.searchSpas(latitude, longitude, radius);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("tomtomApiKey", tomtomApiKey);
        return "results";
    }
}