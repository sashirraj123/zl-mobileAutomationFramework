package com.zl.mobileAutomation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockDataController {

    @GetMapping("/mockdata")
    public String getMockData() {
        return "{ \"key\": \"value\" }";
    }
}
