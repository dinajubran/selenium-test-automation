package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    protected WebElement waitAndFind(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement element = waitAndFind(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitAndFind(locator).getText();
    }


    private Select getDropdown(WebElement element) {
        return new Select(element);
    }

    public void selectByVisibleText(WebElement element, String text) {
        getDropdown(element).selectByVisibleText(text);
    }

//    public void selectByValue(By locator, String value) {
//        getDropdown(locator).selectByValue(value);
//    }
//
//    public void selectByIndex(By locator, int index) {
//        getDropdown(locator).selectByIndex(index);
//    }

    public WebElement getElementByClassAndOrder(By by, int order) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        List<WebElement> elements = driver.findElements(by);
        if (elements.isEmpty()) {
            throw new NoSuchElementException("No elements found");
        }
        return elements.get(order);
    }
}
