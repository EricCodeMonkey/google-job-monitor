package com.example.googlejobs.service;

import com.example.googlejobs.model.JobEntity;
import com.example.googlejobs.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoogleJobService {

    private final PlaywrightJobScraper scraper;
    private final JobRepository repository;
    private final NotificationService notificationService;

    public GoogleJobService(
            PlaywrightJobScraper scraper,
            JobRepository repository,
            NotificationService notificationService
    ) {
        this.scraper = scraper;
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public void checkJobs() {

        List<PlaywrightJobScraper.JobInfo> jobs = scraper.scrape();

        for (PlaywrightJobScraper.JobInfo job : jobs) {

            boolean exists = repository.existsById(job.getUrl());

            if (!exists) {

                JobEntity entity = new JobEntity();
                entity.setUrl(job.getUrl());
                entity.setTitle(job.getTitle());
                entity.setDiscoveredAt(LocalDateTime.now());

                repository.save(entity);

                notificationService.send(
                        job.getTitle(),
                        job.getUrl()
                );

                System.out.println("发现新职位: " + job.getTitle());
            }
        }
    }
}
