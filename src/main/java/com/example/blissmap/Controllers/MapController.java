package com.example.blissmap.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Pas utilisé c'était pour tester
@Controller
public class MapController {

    @Value("${tomtom.api.key}")
    private String tomTomApiKey;

    @GetMapping("/map")
    public String homePage(Model model) {
        model.addAttribute("apikey", tomTomApiKey);
        return "map";
    }
}