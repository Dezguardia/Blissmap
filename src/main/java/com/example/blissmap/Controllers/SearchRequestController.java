package com.example.blissmap.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchRequestController {

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("/search")
    public String searchSpasPage() {
        return "search";
    }
}