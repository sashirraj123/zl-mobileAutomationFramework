package com.zl.mobileAutomation;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zl.mobileAutomation.service.CredentialService;
import com.zl.mobileAutomation.service.Credentials;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CredentialService credentialService;

    public AuthController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping("/credentials")
    public Credentials getMockCredentials() {
        return credentialService.getCredentials();
    }
}