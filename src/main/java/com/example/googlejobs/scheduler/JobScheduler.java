package com.example.googlejobs.scheduler;

import com.example.googlejobs.service.GoogleJobService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {

    private final GoogleJobService service;

    public JobScheduler(GoogleJobService service) {
        this.service = service;
    }

    @Scheduled(fixedDelay = 3600000)
    public void monitor() {

        try {
            service.checkJobs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
