package com.example.blissmap.Controllers;

import com.example.blissmap.Models.SearchResult;
import com.example.blissmap.Services.TomTomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SearchController {

    private final TomTomService tomTomService;

    public SearchController(TomTomService tomTomService) {
        this.tomTomService = tomTomService;
    }

    @RequestMapping("/performSearch")
    public String searchSpasBetter(@RequestParam("latitude") double latitude,
                                   @RequestParam("longitude") double longitude,
                                   @RequestParam("radius") int radius,
                                   Model model) {
        List<SearchResult> searchResults = tomTomService.searchSpas(latitude, longitude, radius);
        model.addAttribute("searchResults", searchResults);
        return "results";
    }
}