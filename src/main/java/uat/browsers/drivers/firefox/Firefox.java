package uat.browsers.drivers.firefox;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import uat.browsers.Browser;
import uat.browsers.remote.RemoteDriver;

import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;
import static uat.configs.Config.platform;

public class Firefox extends Browser {

    private static FirefoxOptions getOptions() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.http", "localhost");
        profile.setPreference("network.proxy.http_port", 1080);

        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(PLATFORM_NAME, platform);
        options.setCapability("firefox_profile", profile);
        return options;
    }

    @Override
    public void initInLocal() {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver(getOptions());
    }

    @Override
    public void initInGrid() {
        webDriver = RemoteDriver.create(getOptions());
    }
}
