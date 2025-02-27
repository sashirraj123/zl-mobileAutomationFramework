package com.zl.mobileAutomation.service;

import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class AuthService {
    public JSONObject fetchCredentials() throws IOException {
        URL url = new URL("http://localhost:8080/api/auth/credentials");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        Scanner scanner = new Scanner(url.openStream());
        StringBuilder inline = new StringBuilder();
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }
        scanner.close();

        return new JSONObject(inline.toString());
    }
}

