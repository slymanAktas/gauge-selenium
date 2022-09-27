package uat.browsers;

import uat.browsers.drivers.chrome.ChromeWeb;
import uat.browsers.drivers.chrome.ChromeWithProxy;
import uat.browsers.drivers.firefox.Firefox;
import uat.configs.Config;

public class Browsers {
    private Browsers() {
    }

    private static String defaultBrowserName() {
        return Config.DEFAULT_BROWSER_NAME;
    }

    public static Browser run(BrowserType browserType) {
        Browser browser;

        switch (browserType) {
            case CHROME:
                browser = new ChromeWeb();
                break;

            case CHROME_PROXY:
                browser = new ChromeWithProxy();
                break;

            case FIREFOX:
            default:
                browser = new Firefox();
                break;
        }

        return browser;
    }

    public static Browser runDefault() {
        return run(BrowserType.byName(defaultBrowserName()));
    }

    public static Browser runBrowser(String browser) {
        return run(BrowserType.byName(browser));
    }
}
