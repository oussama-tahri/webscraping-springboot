package com.scraper.webscraping.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@AllArgsConstructor
public class SchedulerServiceImpl implements ISchedulerService {

    private IProcessService processService;
    private IExecutionService executionService;

    // Scheduling scraping task every 1 hour
    @Scheduled(cron = "0 0 * * * *")  // Executes every hour at the top of the hour
    @Transactional
    @Override
    public void scheduledScrapingTasks() {
        processService.getActiveProcesses()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(processing -> {
                    Mono<String> result = executionService.executeProcess(processing);
                    result.subscribe(content -> {
                        // Handle the scraped content
                        System.out.println("Scraped Content: " + content);
                    });
                })
                .subscribe();
    }
}
