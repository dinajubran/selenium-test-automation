package com.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentManager {
    private static final ExtentReports EXTENT = createReport();

    private ExtentManager() {}

    public static ExtentReports getInstance() {
        return EXTENT;
    }

    private static ExtentReports createReport() {
        String reportPath = System.getProperty("user.dir") + "/reports/index.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

        spark.config().setReportName("Automation Test Results");
        spark.config().setDocumentTitle("Test Execution Report");
        spark.config().setTheme(Theme.STANDARD);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        return extent;
    }
}
