package com.taskapi.taskapi.clases.service;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.taskapi.taskapi.clases.dtos.LogLevel;
import com.taskapi.taskapi.clases.dtos.LogRequest;

@Service
public class LogService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendLog(String method, String path, int status) {

        LogRequest log = new LogRequest();

        log.setMessage(method + " " + path + " -> " + status);

        if(status >= 400) {
            log.setLogLevel(LogLevel.ERROR);
        } else {
            log.setLogLevel(LogLevel.INFO);
        }

        // ID de tu aplicación en LogHub
        log.setAppId(2L);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        // API KEY de LogHub
        headers.set("X-API-KEY", "d2efcca4-1bff-4253-aa57-e578ef2110c1");

        HttpEntity<LogRequest> request =
                new HttpEntity<>(log, headers);

        restTemplate.postForEntity(
                "http://localhost:8080/logs",
                request,
                String.class
        );
    }
}
