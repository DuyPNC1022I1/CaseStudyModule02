import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
    private AccountCheck check;
    private ArrayList<Account> accounts;

    public AccountManager() {
        this.check = new AccountCheck();
        this.accounts = new ArrayList<>();
        accounts.add(new Account("admin", "123", "admin", "admin@gmail.com", "0999999999", "admin"));
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
            if (check.checkEmpty(userName)) {
                for (int i = 0; i < accounts.size(); i++) {
                    if (!accounts.get(i).getUserName().equals(userName)) {
                        flag = false;
                    }
                }
                if (flag) {
                    System.out.println("Username already exist, re-enter");
                }
            } else {
                System.out.println("Format wrong username, re-enter");
            }
        } while (!check.checkEmpty(userName) || flag);

        do {
            System.out.println("Enter password (0 character < password < 15 character): ");
            pass = scanner.nextLine();
            if (!check.checkEmpty(pass)) {
                System.out.println("Format wrong password, re-enter");
            }
        } while (!check.checkEmpty(pass));

        do {
            System.out.println("Enter email: ");
            email = scanner.nextLine();
            if (!check.checkEmail(email)) {
                System.out.println("Format wrong email, re-enter");
            }
        } while (!check.checkEmail(email));

        do {
            System.out.println("Enter phone number: ");
            phoneNumber = scanner.nextLine();
            if (!check.checkPhoneNumber(phoneNumber)) {
                System.out.println("Format wrong password, re-enter");
            }
        } while (!check.checkPhoneNumber(phoneNumber));

        System.out.println("Creat new account complete!!!");
        return new Account(userName, pass, fullName, email, phoneNumber);
    }

    public void addAccount(Scanner scanner) {

        accounts.add(creatAccount(scanner));
    }

    public void deleteAccount(Scanner scanner) {
        int index = -1;
        System.out.println("Enter userName of Product.Account to delete");
        String userNameToDelete = scanner.nextLine();
        if (check.checkEmpty(userNameToDelete)) {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getUserName().equals(userNameToDelete)) {
                    index = i;
                }
            }
            if (index != -1) {
                accounts.remove(accounts.get(index));
                System.out.println("Delete success!!!");
            } else {
                System.out.println("Not have the same userName to delete");
            }
        } else {
            System.out.println("Not have the same userName to delete");
        }
    }

    public void loginAccount(Menu menu, Scanner scanner, BrandManager brandManager, ProductManager productManager, AccountManager accountManager) {
        boolean flag = true;
        //Check login
        System.out.println("Enter userName");
        String userName = scanner.nextLine();
        System.out.println("Enter password");
        String pass = scanner.nextLine();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUserName().equals(userName) && accounts.get(i).getPassWord().equals(pass)) {
                System.out.println("Login complete!!!");
                flag = false;
            }
        }
        if (!flag) {
            if (userName.equals("admin")) {
                menu.runMenuAdmin(scanner, brandManager, productManager, accountManager, menu);
            } else {
                menu.runMenuUser(scanner, brandManager, productManager, accountManager, menu);
            }
        } else {
            System.out.println("Login fail. Please re-enter!!!");
            loginAccount(menu, scanner, brandManager, productManager, accountManager);
        }
    }
}
