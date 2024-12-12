package com.scraper.webscraping.service;

import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserContext;
import com.scraper.webscraping.model.Processing;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class ExecutionServiceImpl implements IExecutionService {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionServiceImpl.class);

    @Override
    public Mono<String> executeProcess(Processing processing) {
        return Mono.fromCallable(() -> {
            logger.info("Executing process for URL: {}", processing.getUrl());
            try (Playwright playwright = Playwright.create()) {
                var browser = playwright.chromium().launch();
                var context = browser.newContext();
                var page = context.newPage();
                page.navigate(processing.getUrl());

                String result = switch (processing.getStrategy()) {
                    case XPATH -> {
                        logger.debug("Using XPath strategy for scraping.");
                        yield page.locator(processing.getSelector()).textContent();
                    }
                    case JS_SELECTOR -> {
                        logger.debug("Using JS Selector strategy for scraping.");
                        yield page.locator(processing.getSelector()).textContent();
                    }
                    case REGEX -> {
                        logger.debug("Using Regex strategy for scraping.");
                        yield page.content().replaceAll(processing.getSelector(), "");
                    }
                };

                browser.close();
                logger.info("Process completed for URL: {}", processing.getUrl());
                return result;
            } catch (Exception e) {
                logger.error("Error executing process for URL: {}", processing.getUrl(), e);
                throw new RuntimeException("Failed to execute process", e);
            }
        });
    }
}