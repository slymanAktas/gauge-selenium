package uat.visitors.masterpass;

import uat.context.TestContext;

public class MasterpassUserPool {
    private MasterpassUserPool() {
    }

    public static MasterpassUser with(String email, String password) {
        MasterpassUser masterpassUser = new MasterpassUser(email, password);
        TestContext.get().addVisitor(masterpassUser);
        return masterpassUser;
    }

    public static MasterpassUser anonymous() {
        return with("", "");
    }
}
