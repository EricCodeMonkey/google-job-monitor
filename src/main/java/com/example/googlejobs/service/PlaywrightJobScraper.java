package com.example.googlejobs.service;

import com.microsoft.playwright.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaywrightJobScraper {

    @Value("${monitor.url}")
    private String monitorUrl;

    public List<JobInfo> scrape() {

        List<JobInfo> result = new ArrayList<>();

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(true)
            );

            Page page = browser.newPage();

            page.navigate(monitorUrl);

            page.waitForTimeout(8000);

            Locator jobs = page.locator("a[href*='/jobs/results/']");

            int count = jobs.count();

            for (int i = 0; i < count; i++) {

                Locator item = jobs.nth(i);

                String title = item.innerText().trim();

                String href = item.getAttribute("href");

                if (href == null || href.isBlank()) {
                    continue;
                }

                if (!href.startsWith("http")) {
                    href = "https://www.google.com" + href;
                }

                JobInfo job = new JobInfo();
                job.setTitle(title);
                job.setUrl(href);

                result.add(job);
            }

            browser.close();
        }

        return result;
    }

    public static class JobInfo {

        private String title;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
