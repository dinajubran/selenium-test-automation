package com.automation.config;

import com.automation.utils.ConfigReader;

import java.time.Duration;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public record FrameworkConfig(
        String loginUrl,
        String adminUsername,
        String adminPassword,
        String browser,
        boolean headless,
        Duration timeout) {

    public static FrameworkConfig load() {
        List<String> missing = new ArrayList<>();
        String loginUrl = required("LOGIN_URL", missing);
        String username = required("ADMIN_USERNAME", missing);
        String password = required("ADMIN_PASSWORD", missing);

        if (!missing.isEmpty()) {
            throw new IllegalStateException("Missing required configuration: " + String.join(", ", missing));
        }

        loginUrl = validateHttpUrl(loginUrl);

        int timeoutSeconds = ConfigReader.getInt("TIMEOUT_SECONDS", 30);
        if (timeoutSeconds <= 0) {
            throw new IllegalArgumentException("TIMEOUT_SECONDS must be greater than zero");
        }

        return new FrameworkConfig(
                loginUrl,
                username,
                password,
                ConfigReader.getOrDefault("BROWSER", "chrome"),
                ConfigReader.getBoolean("HEADLESS", true),
                Duration.ofSeconds(timeoutSeconds));
    }

    private static String required(String key, List<String> missing) {
        String value = ConfigReader.get(key);
        if (value == null || value.isBlank()) {
            missing.add(key);
        }
        return value;
    }

    private static String validateHttpUrl(String value) {
        try {
            URI uri = new URI(value.trim());
            String scheme = uri.getScheme();
            if (scheme == null
                    || !(scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https"))
                    || uri.getHost() == null) {
                throw new IllegalArgumentException(
                        "LOGIN_URL must be a complete URL beginning with http:// or https://: " + value);
            }
            return uri.toString();
        } catch (URISyntaxException exception) {
            throw new IllegalArgumentException("LOGIN_URL is not a valid URL: " + value, exception);
        }
    }
}
