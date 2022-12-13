package com.dh.catalog.service;

import com.dh.catalog.model.Movie;
import com.dh.catalog.model.Serie;
import com.dh.catalog.repository.MovieRepositoryMongo;
import com.dh.catalog.repository.SerieRepositoryMongo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CatalogOfflineService {

    private final MovieRepositoryMongo movieRepositoryMongo;
    private final SerieRepositoryMongo serieRepositoryMongo;

    public CatalogOfflineService(MovieRepositoryMongo movieRepositoryMongo, SerieRepositoryMongo serieRepositoryMongo) {
        this.movieRepositoryMongo = movieRepositoryMongo;
        this.serieRepositoryMongo = serieRepositoryMongo;
    }

    public List<Movie> findMovieByGenre(String genre) {
        List<Movie> allMovies = movieRepositoryMongo.findAll();
        List<Movie> genreMovies = new ArrayList<>();
        for (Movie movie : allMovies) {
            if (movie.getGenre().equals(genre)) {
                genreMovies.add(movie);
            }
        }
        log.info("Consultando la peliculas del género " + genre + "en la base de datos catálogo");
        return genreMovies;
    }

    public List<Serie> findSerieByGenre(String genre) {
        List<Serie> allSeries = serieRepositoryMongo.findAll();
        List<Serie> genreSeries = new ArrayList<>();
        for (Serie serie : allSeries) {
            if (serie.getGenre().equals(genre)) {
                genreSeries.add(serie);
            }
        }
        log.info("Consultando las series del género " + genre + "en la base de datos de catálogo");
        return genreSeries;


    }
}