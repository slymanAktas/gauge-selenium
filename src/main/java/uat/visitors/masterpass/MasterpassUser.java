package uat.visitors.masterpass;

import uat.visitors.Visitor;

public class MasterpassUser extends Visitor<MasterpassUser> {
    public MasterpassUser(String email, String password) {
        super(email, password);
    }
}
