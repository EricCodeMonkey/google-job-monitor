package com.example.googlejobs.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class NotificationService {

    @Value("${server-chan.send-key}")
    private String sendKey;

    public void send(String title, String url) {

        try {

            String api = "https://sctapi.ftqq.com/" + sendKey + ".send";

            String text = URLEncoder.encode(
                    "Google 广州新职位",
                    StandardCharsets.UTF_8
            );

            String desp = URLEncoder.encode(
                    "职位：" + title + "\n\n" + url,
                    StandardCharsets.UTF_8
            );

            String finalUrl = api + "?title=" + text + "&desp=" + desp;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(finalUrl))
                    .GET()
                    .build();

            HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
