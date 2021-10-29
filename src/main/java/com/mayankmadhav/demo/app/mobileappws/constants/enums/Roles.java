package com.mayankmadhav.demo.app.mobileappws.constants.enums;

public enum Roles {
    USER, EMPLOYEE, ADMIN, MANAGER;

    public static boolean isValid(String role) {
        for (Roles r : Roles.values()) {
            if (r.toString().equals(role)) {
                return true;
            }
        }
        return false;
    }

}
