package uat.components;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uat.actions.Action;
import uat.browsers.Browser;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebComponent {

    protected Browser browser;
    protected String id;
    protected String cssSelector;
    protected String xpath;
    protected String className;

    public Browser browser() {
        return this.browser;
    }

    public String getCssSelector() {
        return cssSelector;
    }

    public String getXpath() {
        String localXpath = xpath;

        if (localXpath == null && id != null) {
            localXpath = "//*[@id='" + id + "']";
        }

        return localXpath;
    }

    public void run(Action action) {
        action.execIn(this);
    }
}
