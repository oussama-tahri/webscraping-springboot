package com.scraper.webscraping.service;

import com.scraper.webscraping.model.Processing;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProcessService {

    Flux<Processing> getAllProcesses();

    Mono<Processing> getProcessById(String id);

    Mono<Processing> createProcess(Processing processing);

    Mono<Processing> updateProcess(String id, Processing updatedProcessing);

    Mono<Void> deleteProcess(String id);

    Flux<Processing> getActiveProcesses();
}