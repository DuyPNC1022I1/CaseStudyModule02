import java.util.regex.Pattern;

public class AccountCheck {
    public boolean checkEmpty(String check) {
        if (check.equals("")) {
            return false;
        }
        return true;
    }

    //Check email
    public boolean checkEmail(String email) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return patternMatches(email,regexPattern);
    }
    public boolean patternMatches(String email, String regexPattern) {
        return Pattern.compile(regexPattern).
                matcher(email).
                matches();
    }

    //Check phone number
    public boolean checkPhoneNumber(String phoneNumber) {
        String regexPattern = "^0\\d{9,10}$";
        return patternMatchesPhone(phoneNumber,regexPattern);
    }
    public boolean patternMatchesPhone(String phoneNumber, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(phoneNumber)
                .matches();
    }
}
