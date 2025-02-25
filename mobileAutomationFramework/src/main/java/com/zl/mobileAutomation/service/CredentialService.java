package com.zl.mobileAutomation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class CredentialService {

    public Credentials getCredentials() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = new ClassPathResource("data/testdata.json").getInputStream();
            return objectMapper.readValue(inputStream, Credentials.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load testdata.json", e);
        }
    }
}