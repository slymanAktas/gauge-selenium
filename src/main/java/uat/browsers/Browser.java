package uat.browsers;

import io.qameta.allure.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.ScreenshotException;
import uat.pages.Page;
import uat.visitors.Visitor;

import static org.openqa.selenium.OutputType.BYTES;
import static uat.browsers.Browsers.runBrowser;
import static uat.browsers.Browsers.runDefault;
import static uat.configs.Config.*;
import static uat.utils.FileUtils.writeFile;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Browser extends SeleniumBrowser {

    private Page page;
    private Visitor visitor;

    public abstract void initInGrid();
    public abstract void initInLocal();

    @Attachment(value = "Fail screenshot", type = "image/png")
    public byte[] takeScreenshot(String scrFilename) {
        byte[] byteArray = new byte[0];
        try {
            byteArray = ((TakesScreenshot) webDriver).getScreenshotAs(BYTES);
            writeFile(scrFilename, byteArray);
        } catch (ScreenshotException sse) {
            log.error("Taking screenshot has been failed, " + sse);
        }
        return byteArray;
    }

    public final Browser changePage(Page page) {
        this.page = page;
        this.page.setBrowser(this);
        return this;
    }

    public static Browser openThe(Page page, String browserName) {
        Browser browser = browserName == null ? runDefault() : runBrowser(browserName);
        return browser.open(page);
    }

    private void initBrowser() {
        if (ISREMOTE) {
            log.info("Open remote {" + DEFAULT_BROWSER_NAME + "} models.browser at {" + SELENIUM_GRID_HUB_URL + "} grid environment");
            initInGrid();
        } else {
            log.info("Open local {" + DEFAULT_BROWSER_NAME + "} models.browser");
            initInLocal();
        }
    }

    private Browser open(Page page) {
        initBrowser();
        this.page = page;
        this.page.setBrowser(this);
        webDriver.get(page.getUrl());
        waitForAjax();
        return this;
    }

    public void close() {
        webDriver.quit();
    }

    public Browser type(By by, String text) {
        type(findElement(by), text, true);
        return this;
    }

    public void type(WebElement element, String text, boolean clear) {
        highlightElement(element);
        scrollToElement(element);
        if (clear) element.clear();
        element.sendKeys(text);
    }

    public void clickToBy(By by) {
        try {
            clickTo(visibilityWait(30, findElement(by)));
        } catch (StaleElementReferenceException sere) {
            log.error("Stale element exception has been thrown, will try again. " + sere);
            wait(1);
            clickToBy(by);
        }
    }

    public void clickTo(WebElement element) {
        highlightElement(element);
        scrollUntilElement(element);
        waitForClickableOf(30, element);
        element.click();
        waitForDialog();
    }

    public void highlightElement(WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.background = 'yellow';", element);
    }

    public void scrollUntilElement(By by){
        scrollUntilElement(findElement(by));
    }

    public void scrollUntilElement(WebElement element) {
        visibilityWait(30, element);
        int marginToBottom = getDocumentHeight().intValue() - element.getLocation().getY();
        int margin = marginToBottom < 250 ? 0 : -250;

        scrollUntilElement(element, margin);
    }

    public void scrollUntilElement(WebElement element, int margin) {
        visibilityWait(30, element);
        js("arguments[0].scrollIntoView(true);" + "scrollBy(0, " + margin + ");", element);
    }

    public Object js(String script) {
        return js(script, null);
    }

    public Object js(String script, WebElement arg) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Object response = "";
        try {
            response = js.executeScript(script, arg);
        } catch (WebDriverException wde) {
            log.error("Cannot execute script, " + wde);
        }

        return response;
    }

    public Long getDocumentHeight() {
        try {
            return (Long) js("return document.body.scrollHeight;");
        } catch (ClassCastException cce) {
            return 0l;
        }
    }

    public void scrollTo(WebElement element) {
        visibilityWait(30, element);
        scrollTo(element, 250);
    }

    private void scrollTo(WebElement element, int margin) {
        scrollTo(element.getLocation().x, element.getLocation().y - margin);
    }

    public void scrollTo(int x, int y) {
        js("scrollTo(" + x + "," + y + ");");
    }

    void scrollToElement(WebElement element) {
        scrollTo(element);
    }

    public <P extends Page> Browser goTo(P page) {
        this.page = page;
        this.page.setBrowser(this);
        webDriver.navigate().to(page.getUrl());
        waitForDialog();
        return this;
    }
}
