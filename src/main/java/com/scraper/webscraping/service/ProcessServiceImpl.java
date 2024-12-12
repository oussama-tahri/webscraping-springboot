package com.scraper.webscraping.service;

import com.scraper.webscraping.model.Processing;
import com.scraper.webscraping.repository.ProcessRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Transactional
public class ProcessServiceImpl implements IProcessService {
    private ProcessRepository processRepository;

    @Override
    public Flux<Processing> getAllProcesses() {
        return processRepository.findAll();
    }

    @Override
    public Mono<Processing> getProcessById(String id) {
        return processRepository.findById(id);
    }

    @Override
    public Mono<Processing> createProcess(Processing processing) {
        return processRepository.save(processing);
    }

    @Override
    public Mono<Processing> updateProcess(String id, Processing updatedProcessing) {
        return processRepository.findById(id)
                .flatMap(existingProcess -> {
                    existingProcess.setName(updatedProcessing.getName());
                    existingProcess.setUrl(updatedProcessing.getUrl());
                    existingProcess.setStrategy(updatedProcessing.getStrategy());
                    existingProcess.setSelector(updatedProcessing.getSelector());
                    existingProcess.setSchedule(updatedProcessing.getSchedule());
                    existingProcess.setActive(updatedProcessing.isActive());
                    return processRepository.save(existingProcess);
                });
    }

    @Override
    public Mono<Void> deleteProcess(String id) {
        return processRepository.deleteById(id);
    }

    @Override
    public Flux<Processing> getActiveProcesses() {
        return processRepository.findByActiveTrue();
    }
}