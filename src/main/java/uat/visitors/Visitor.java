package uat.visitors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import uat.browsers.Browser;
import uat.context.TestContext;
import uat.pages.Page;

@Data
@NoArgsConstructor
@AllArgsConstructor
//public class Visitor<V extends Visitor> {
public class Visitor<V extends Visitor> {
    private Browser browser;
    private String email;
    private String password;
    private String userName;
    private boolean isOnline;
    private TestContext testContext;

    public Visitor(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void closeBrowser() {browser.close();}

    public <P extends Page> V open(P page) {
        return open(page, null);
    }

    public <P extends Page> V open(P page, String browserName) {
        this.browser = Browser.openThe(page, browserName);
        return (V) this;
    }

    public Page nowLookingAt() {
        return browser.getPage();
    }

    public Visitor login() {
        browser.setVisitor(this);
        isOnline = browser.getPage().login();
        return this;
    }
}
