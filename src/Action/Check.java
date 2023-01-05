package Action;

import java.util.regex.Pattern;

public class Check {
    public boolean checkEmpty(String check) {
        if (check.equals("")) {
            return false;
        }
        return true;
    }

    public boolean checkEmail(String email) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return patternMatches(email,regexPattern);
    }
    public boolean patternMatches(String email, String regexPattern) {
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
