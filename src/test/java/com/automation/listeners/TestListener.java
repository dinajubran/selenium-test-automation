package com.automation.listeners;

import com.automation.utils.ExtentManager;
import com.automation.utils.ScreenshotUtils;
import com.automation.driver.DriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> testNode = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testNode.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testNode.get().log(Status.PASS, "Test Passed");
        clearTestNode();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testNode.get();
        test.fail(result.getThrowable());
        if (DriverManager.getDriver() != null) {
            try {
                ScreenshotUtils.ScreenshotEvidence screenshot = ScreenshotUtils.capture(
                        DriverManager.getDriver(), result.getMethod().getMethodName());
                test.addScreenCaptureFromBase64String(screenshot.base64(), "Failure screenshot");
                test.info("Screenshot file: " + screenshot.path());
                test.info("URL at failure: " + DriverManager.getDriver().getCurrentUrl());
            } catch (Exception exception) {
                test.warning("Could not capture failure evidence: " + exception.getMessage());
            }
        }
        clearTestNode();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testNode.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        clearTestNode();
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private void clearTestNode() {
        testNode.remove();
    }
}
