package uat.actions;

import uat.components.WebComponent;

@FunctionalInterface
public interface Action {
    static Action close(){return new CloseAction();}

    void execIn(WebComponent component);
}
