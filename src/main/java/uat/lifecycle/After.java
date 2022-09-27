package uat.lifecycle;

import com.thoughtworks.gauge.AfterScenario;
import uat.context.TestContext;

public class After {

    @AfterScenario
    public void tearDown(){
        TestContext.closeBrowsers();
        TestContext.remove();
    }
}
