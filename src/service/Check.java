package service;

import java.util.regex.Pattern;

public class Check {
    public boolean checkEmpty(String check) {
        if (check.equals("")) {
            return false;
        }
        return true;
    }

    public boolean checkUsername(String username) {
        String regexPattern = "^[a-zA-Z0-9]+$";
        return Pattern.compile(regexPattern).
                matcher(username).
                matches();
    }

    public boolean checkEmail(String email) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern).
                matcher(email).
                matches();
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        String regexPattern = "^0\\d{9,10}$";
        return Pattern.compile(regexPattern)
                .matcher(phoneNumber)
                .matches();
    }
}
