package com.dh.catalog.repository;

import com.dh.catalog.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepositoryMongo extends MongoRepository<Serie,Long> {
}
