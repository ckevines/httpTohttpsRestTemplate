package com.example.resttemplateclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiClientService {

    private final RestTemplate restTemplate;
    private final String apiServerUrl;

    public ApiClientService(RestTemplate restTemplate,
                            @Value("${api.server.url}") String apiServerUrl) {
        this.restTemplate = restTemplate;
        this.apiServerUrl = apiServerUrl;
    }

    public String callHttpsApi() {
        String url = apiServerUrl + "/test";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}