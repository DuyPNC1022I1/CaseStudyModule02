import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
    private AccountCheck check;
    private ArrayList<Account> accounts;

    public AccountManager() {
        this.check = new AccountCheck();
        this.accounts = new ArrayList<>();
    }

    public Account creatAccount(Scanner scanner) {
        String userName = null;
        String pass = null;
        String email = null;
        String phoneNumber = null;
        boolean flag = true;
        System.out.println("Creat new account: ");
        System.out.println("Enter full name: ");
        String fullName = scanner.nextLine();

        do {
            System.out.println("Enter user name ( username > 0 character): ");
            userName = scanner.nextLine();
            if (check.checkNull(userName)) {
                System.out.println("Format wrong username, re-enter");
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getUserName().equals(userName)) {
                        System.out.println("Username already exist, re-enter");
                        flag = false;
                    }
                }
            }
        } while (!(check.checkNull(userName) && flag));

        do {
            System.out.println("Enter password (0 < password < 15 character): ");
            pass = scanner.nextLine();
            if (!(check.checkNull(pass) && check.checkPassword(pass))) {
                System.out.println("Format wrong password, re-enter");
            }
        } while (!(check.checkNull(pass) && check.checkPassword(pass)));

        do {
            System.out.println("Enter email: ");
            email = scanner.nextLine();
            if (!(check.checkNull(email) && check.checkPassword(email))) {
                System.out.println("Format wrong password, re-enter");
            }
        } while (!(check.checkNull(email) && check.checkEmail(email)));

        do {
            System.out.println("Enter phone number: ");
            phoneNumber = scanner.nextLine();
            if (!(check.checkNull(phoneNumber) && check.checkPassword(phoneNumber))) {
                System.out.println("Format wrong password, re-enter");
            }
        } while (!(check.checkNull(phoneNumber) && check.checkPhoneNumber(phoneNumber)));
        return new Account(userName, pass, fullName, email, phoneNumber, "User");
    }

    public void addAccount(Scanner scanner) {
        accounts.add(creatAccount(scanner));
    }


}


//Admin:
//creatAccount
//deleteAccount()
//checkLogin

