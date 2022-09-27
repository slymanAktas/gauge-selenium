package uat.actions;

import org.openqa.selenium.By;
import uat.components.WebComponent;

public class CloseAction implements Action {
    private static final By xButton = By.xpath("//div[@class='modal-close']");

    @Override
    public void execIn(WebComponent component) {
        component.browser().clickToBy(xButton);
    }
}
