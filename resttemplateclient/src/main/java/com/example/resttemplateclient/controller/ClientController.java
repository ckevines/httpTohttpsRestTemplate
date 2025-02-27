package com.example.resttemplateclient.controller;

import com.example.resttemplateclient.service.ApiClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private final ApiClientService apiClientService;

    public ClientController(ApiClientService apiClientService) {
        this.apiClientService = apiClientService;
    }

    @GetMapping("/call-https-api")
    public String callHttpsApi() {
        return apiClientService.callHttpsApi();
    }
}