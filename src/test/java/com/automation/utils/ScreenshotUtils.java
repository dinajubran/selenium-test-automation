package com.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public final class ScreenshotUtils {
    private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");

    private ScreenshotUtils() {}

    public static ScreenshotEvidence capture(WebDriver driver, String testName) throws IOException {
        if (!(driver instanceof TakesScreenshot screenshotDriver)) {
            throw new IllegalArgumentException("Driver does not support screenshots");
        }

        String safeName = testName.replaceAll("[^a-zA-Z0-9._-]", "_");
        Path directory = Path.of("screenshots");
        Files.createDirectories(directory);
        Path destination = directory.resolve(safeName + "-" + TIMESTAMP.format(LocalDateTime.now()) + ".png");
        byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
        Files.write(destination, screenshot);

        return new ScreenshotEvidence(
                destination.toAbsolutePath(),
                Base64.getEncoder().encodeToString(screenshot));
    }

    public record ScreenshotEvidence(Path path, String base64) {}
}
