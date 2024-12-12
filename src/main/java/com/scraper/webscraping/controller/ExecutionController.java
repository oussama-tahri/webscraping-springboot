package com.scraper.webscraping.controller;

import com.scraper.webscraping.service.ExecutionServiceImpl;
import com.scraper.webscraping.service.ProcessServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/execution")
@AllArgsConstructor
public class ExecutionController {
    private ExecutionServiceImpl executionService;
    private ProcessServiceImpl processService;

    @PostMapping("/{id}")
    public Mono<String> executeProcess(@PathVariable String id) {
        return processService.getProcessById(id)
                .flatMap(executionService::executeProcess);
    }
}

