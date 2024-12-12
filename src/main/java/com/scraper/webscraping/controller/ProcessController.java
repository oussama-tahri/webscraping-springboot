package com.scraper.webscraping.controller;

import com.scraper.webscraping.model.Processing;
import com.scraper.webscraping.service.IProcessService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/processes")
@AllArgsConstructor
public class ProcessController {
    private IProcessService processService;

    @GetMapping
    public Flux<Processing> getAllProcesses() {
        return processService.getAllProcesses();
    }

    @GetMapping("/{id}")
    public Mono<Processing> getProcessById(@PathVariable String id) {
        return processService.getProcessById(id);
    }

    @PostMapping
    public Mono<Processing> createProcess(@RequestBody Processing processing) {
        return processService.createProcess(processing);
    }

    @PutMapping("/{id}")
    public Mono<Processing> updateProcess(@PathVariable String id, @RequestBody Processing processing) {
        return processService.updateProcess(id, processing);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProcess(@PathVariable String id) {
        return processService.deleteProcess(id);
    }

    @GetMapping("/active")
    public Flux<Processing> getActiveProcesses() {
        return processService.getActiveProcesses();
    }
}