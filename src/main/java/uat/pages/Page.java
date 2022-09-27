package uat.pages;

import lombok.Data;
import uat.components.WebComponent;

@Data
public abstract class Page extends WebComponent {
    private String url;

    public boolean login() { throw new IllegalStateException("Page doesn't support login activity");}

    public boolean logout() {
        throw new IllegalStateException("Page doesn't support logout activity");
    }

}
