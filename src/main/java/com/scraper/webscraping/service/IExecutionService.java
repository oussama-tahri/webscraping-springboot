package com.scraper.webscraping.service;


import com.scraper.webscraping.model.Processing;
import reactor.core.publisher.Mono;


public interface IExecutionService {

    Mono<String> executeProcess(Processing processing);
}