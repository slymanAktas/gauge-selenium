package uat.context;


import uat.visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

public class TestContext {
    public static final ThreadLocal<TestContext> contexts = new ThreadLocal<>();
    private TestContext initializedContext;

    private List<Visitor> visitors = new ArrayList<>();
    private String specName;
    private String scenarioName;

    public TestContext(String specName, String scenarioName) {
        this.specName = specName;
        this.scenarioName = scenarioName;
    }

    public static TestContext init(String testClassName, String testMethodName) {
        TestContext context = new TestContext(testClassName, testMethodName);
        contexts.set(context);
        context.setContexts(context);
        return context;
    }

    private void setContexts(TestContext initializedContext) {
        this.initializedContext = initializedContext;
    }

    private TestContext getContext() {
        return this.initializedContext;
    }

    public String getScenarioName() {
        return this.scenarioName;
    }

    public String getSpecName() {
        return this.specName;
    }

    public List<Visitor> getVisitors() {
        return this.visitors;
    }

    public void addVisitor(Visitor visitor) {
        visitor.setTestContext(getContext());
        visitors.add(visitor);
    }

    public static TestContext get() {
        return contexts.get();
    }

    public static void remove() {
        contexts.remove();
    }

    public void whenTestStoppedByJvm() {
        // Do what you want when test stopped by jvm
    }

    public static void closeBrowsers() {
        if (TestContext.get() != null) {
            for (Visitor visitor : TestContext.get().getVisitors()) {
                if (visitor != null && visitor.getBrowser() != null) {
                    visitor.closeBrowser();
                }
            }
        }
    }
}
