package com.demi.hdjassignment.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthApiController {

    /**
     * Health check API
     */
    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkAlive() {
        return "{\"status\": \"ALIVE\"}";
    }

}
