package com.scraper.webscraping.model;

import com.scraper.webscraping.enums.Strategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "processes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Processing {
    @Id
    private String id;
    private String name;      // Process name
    private String url;       // Website URL to scrape
    private Strategy strategy;  // "xpath", "regex", or "js_selector"
    private String selector;  // Value for the scraping strategy
    private String schedule;  // Scheduling executions
    private boolean active;   // Status to enable/disable a process


    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
