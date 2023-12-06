package com.example.blissmap.Services;

import com.example.blissmap.Models.SearchResult;

import java.util.List;

public interface SearchService {
    List<SearchResult> searchSpas(double latitude, double longitude, int radius);
}