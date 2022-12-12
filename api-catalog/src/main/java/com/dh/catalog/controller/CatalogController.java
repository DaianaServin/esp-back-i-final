package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.Movie;
import com.dh.catalog.model.Serie;
import com.dh.catalog.service.CatalogOfflineService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dh.catalog.service.CatalogService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;
    private final CatalogService catalogService;
    private final CatalogOfflineService catalogOfflineService;

    public CatalogController(CatalogService catalogService, MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient, CatalogOfflineService catalogOfflineService){
        this.catalogService = catalogService;
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
        this.catalogOfflineService = catalogOfflineService;
    }
    @GetMapping("/movies/{genre}")
    ResponseEntity<List<?>> getMovieGenre(@PathVariable String genre) throws Exception {
        return ResponseEntity.ok().body(catalogService.getMovieByGenre(genre));
    }
    @GetMapping("/series/{genre}")
    ResponseEntity<List<?>> getSerieGenre(@PathVariable String genre) throws Exception {
        return ResponseEntity.ok().body(catalogService.getSerieByGenre(genre));
    }
    @GetMapping("/offline/movies/{genre}")
    ResponseEntity<List<Movie>> getMoviesGenreOffline(@PathVariable String genre){
        return ResponseEntity.ok().body(catalogOfflineService.findMovieByGenre(genre));
    }

    @GetMapping("/offline/series/{genre}")
    ResponseEntity<List<Serie>> getSeriesOffine(@PathVariable String genre){
        return ResponseEntity.ok().body(catalogOfflineService.findSerieByGenre(genre));
    }
}
