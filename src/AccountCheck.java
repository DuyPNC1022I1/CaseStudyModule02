import java.util.regex.Pattern;

public class AccountCheck {

    //Check Name
    public boolean checkNull(String check) {
        if (check == null) {
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

    // Check password
    public boolean checkPassword(String password) {
        if (0 > password.length() && password.length() >= 15) {
            return false;
        }
        return true;
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

//    public User checkLogin(String email, String password) {
//        for (User user: UserDB.users) {
//            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
//                System.out.println("Login complete");
//                return user;
//            }
//        }
//        throw new NotFoundException("Email hoặc mật khẩu không chính xác");
//    }
//}
}
