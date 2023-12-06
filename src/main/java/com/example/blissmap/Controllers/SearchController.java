package com.example.blissmap.Controllers;

import com.example.blissmap.Models.SearchResult;
import com.example.blissmap.Services.TomTomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final TomTomService tomTomService;

    public SearchController(TomTomService tomTomService) {
        this.tomTomService = tomTomService;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("/search")
    public String searchSpasBetter(@RequestParam("latitude") double latitude,
                             @RequestParam("longitude") double longitude,
                             @RequestParam("radius") int radius,
                             Model model) {
        List<SearchResult> searchResults = tomTomService.searchSpas(latitude, longitude, radius);

        model.addAttribute("searchResults", searchResults);

        return "results";
    }
}