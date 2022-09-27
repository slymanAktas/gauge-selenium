package uat.ui.login;

import com.thoughtworks.gauge.Step;
import org.junit.jupiter.api.Assertions;
import uat.pages.LoginPage;
import uat.visitors.masterpass.MasterpassUser;
import uat.visitors.masterpass.MasterpassUserPool;

public class LoginSteps {
    private MasterpassUser masterpassUser;

    @Step("Get anonymous masterpass user")
    public void getAnonymousVisitor() {
        masterpassUser = MasterpassUserPool.anonymous();
    }

    @Step("Get masterpass user email as <suleyman.aktas@n11.com> and password as <Passw0rd>")
    public void getVisitor(String email, String password) {
        masterpassUser = MasterpassUserPool.with(email, password);
    }


    @Step("Logged in N11")
    public void loggedIn() {
        masterpassUser.open(new LoginPage()).login();
    }

    @Step("Check user can logged in")
    public void canLoggedIn() {
        Assertions.assertTrue(masterpassUser.isOnline());
    }
}
