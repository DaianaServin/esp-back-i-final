package com.dh.serie.service;

import com.dh.serie.event.NewSerieEventProducer;
import com.dh.serie.model.Serie;
import com.dh.serie.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {
    private final SerieRepository serieRepository;
    private final NewSerieEventProducer newSerieEventProducer;

    public SerieService(SerieRepository serieRepository, NewSerieEventProducer newSerieEventProducer) {
        this.serieRepository = serieRepository;
        this.newSerieEventProducer = newSerieEventProducer;
    }
    public Serie save(Serie serie){
        serieRepository.save(serie);
        newSerieEventProducer.execute(serie);
        return serie;
    }
    public List<Serie> getByGenre(String genre) {
        List<Serie> serie = serieRepository.findByGenre(genre);
        return serie;
    }
}
