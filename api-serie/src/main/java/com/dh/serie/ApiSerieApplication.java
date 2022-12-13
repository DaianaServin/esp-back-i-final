package com.dh.serie;

import com.dh.serie.model.Serie;
import com.dh.serie.repository.SerieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiSerieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSerieApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(SerieRepository repository) {
		return (args) -> {
			if (!repository.findAll().isEmpty()) {
				return;
			}

			repository.save(new Serie(null, "Serie 1", "Terror", null));
			repository.save(new Serie(null, "Serie 2", "Terror", null));
			repository.save(new Serie(null, "Serie 3", "Comedia", null));
			repository.save(new Serie(null, "Serie 4", "Ficcion", null));
		};
	}

}
