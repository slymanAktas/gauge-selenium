package uat.browsers.drivers.chrome;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import uat.browsers.Browser;
import uat.browsers.remote.RemoteDriver;

public abstract class Chrome extends Browser {
    public abstract ChromeOptions getOptions(boolean isHeadless);

    @Override
    public void initInLocal() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(getOptions(false));
        System.setProperty("webdriver.chrome.silentOutput", "true"); // İgnore ChromeDriver warnings
        webDriver.manage().window().maximize();
    }

    @Override
    public void initInGrid() {
        webDriver = RemoteDriver.create(getOptions(true));
    }
}
