package uat.lifecycle;

import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.ExecutionContext;
import uat.context.TestContext;

public class Before {

    @BeforeScenario
    public void setUp(ExecutionContext context) {
        initTestContext(context);

    }

    public void initTestContext(ExecutionContext testInfo) {
        TestContext context = TestContext.init(testInfo.getCurrentSpecification().getName(), testInfo.getCurrentScenario().getName());
        // This line will be invoked after jvm stopped.(It means,  after the test is finished somehow )
        // Shutdown hooks are initialized but not-started threads. They start when when JVM shutdown triggers
        Runtime.getRuntime().addShutdownHook(new Thread(context::whenTestStoppedByJvm, "shutdown-thread-when-test-stopping-somehow"));
    }
}
