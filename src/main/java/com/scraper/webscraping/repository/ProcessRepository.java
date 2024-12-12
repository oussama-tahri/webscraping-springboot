package com.scraper.webscraping.repository;

import com.scraper.webscraping.model.Processing;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProcessRepository extends ReactiveMongoRepository<Processing, String> {
    Flux<Processing> findByActiveTrue(); // Fetch only active processes
}
