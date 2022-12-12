package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.exceptions.ExceptionApis;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CatalogService {
    final static Logger log = Logger.getLogger(String.valueOf(CatalogService.class));
    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;

    public CatalogService(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
    }


    @CircuitBreaker(name = "clientMovie", fallbackMethod = "findMovieFallBack")
    @Retry(name = "clientMovie")
    public List<?> getMovieByGenre(String genre) {
        ExceptionApis error;
        List response =  movieServiceClient.getMovieByGenre(genre);
        if (response.isEmpty()) {
            error = new ExceptionApis("No se encontraron peliculas del género seleccionado", HttpStatus.NOT_FOUND, ZonedDateTime.now());
            response.add(error);
        }

        else log.info("Consultando las peliculas del género " + genre);
        return response;
    }

    public List<?> findMovieFallBack(String genre, Throwable t) {
        ExceptionApis error;
        List response =  movieServiceClient.getMovieByGenre(genre);

        if (response.isEmpty()) {
            error = new ExceptionApis("No se encontraron peliculas del género seleccionado", HttpStatus.NOT_FOUND, ZonedDateTime.now());
            response.add(error);
        }

        else log.info("Retry de Movies");
        return response;
    }
    @CircuitBreaker(name= "clientSerie", fallbackMethod = "findSerieFallBack")
    @Retry(name = "clientSerie")
    public List<?> getSerieByGenre(String genre) {
        ExceptionApis error;
        List response = serieServiceClient.getSerieByGenre(genre);
        if (response.isEmpty()) {
            error = new ExceptionApis("No se encontraron series del género seleccionado", HttpStatus.NOT_FOUND, ZonedDateTime.now());
            response.add(error);
        }
        log.info("Consultando las series del género " + genre);
        return response;
    }
    public List<?> findSerieFallBack(String genre, Throwable t) {
        ExceptionApis error;
        List response = serieServiceClient.getSerieByGenre(genre);
        if (response.isEmpty()) {
            error = new ExceptionApis("No se encontraron series del genero seleccionado", HttpStatus.NOT_FOUND, ZonedDateTime.now());
            response.add(error);
        }
        log.info("Retry de Series");
        return response;
    }
}
