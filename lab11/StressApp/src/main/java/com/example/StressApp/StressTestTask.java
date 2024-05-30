package com.example.StressApp;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class StressTestTask {

    private static final String SERVICE_URL = "http://localhost:8666/api/v1/books/1";
    private static final int NUM_REQUESTS = 100;
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;

    @Value("${jwt.token}")
    private String jwtToken;

    public StressTestTask() {
        this.executorService = Executors.newFixedThreadPool(10);
        this.restTemplate = new RestTemplate();
    }

    public void runStressTest() {
        System.out.println("Running stress test...");

        for (int i = 0; i < NUM_REQUESTS; i++) {
            executorService.submit(this::sendRequest);
            System.out.println("Progress: " + (i + 1) + "/" + NUM_REQUESTS);
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        System.out.println("Stress test completed.");
    }

    private void sendRequest() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(jwtToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(SERVICE_URL, HttpMethod.GET, entity, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Request successful.");
            } else {
                System.err.println("Request failed: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Request failed: " + e.getMessage());
        }
    }
}
