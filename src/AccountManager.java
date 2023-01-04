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

        System.out.println("Creat account complete!!!");
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

    public void loginAccount(Menu menu, Scanner scanner) {
        boolean flag = true;
        String userName = null;
        String pass = null;
        do {
            System.out.println("Enter userName: ");
            userName = scanner.nextLine();
            if (check.checkEmpty(userName)) {
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getUserName().equals(userName)) {
                        flag = false;
                        break;
                    } else {
                        System.out.println("Product.Account not exist");
                    }
                }
            } else {
                System.out.println("Format wrong userName, re-enter!");
            }
        }
        while ((!check.checkEmpty(userName)) || flag);

        do {
            System.out.println("Enter password: ");
            pass = scanner.nextLine();
            if (check.checkEmpty(pass)) {
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getPassWord().equals(pass)) {
                        flag = false;
                        break;
                    } else {
                        System.out.println("Password false, re-enter");
                        flag = true;
                    }
                }
            } else {
                System.out.println("Format wrong password");
            }
        }
        while ((!check.checkEmpty(pass)) || flag);

        //Check login
        System.out.println("Login complete!!!");
        if (userName.equals("admin")) {
            menu.runMenuAdmin();
        } else {
            menu.runMenuUser();
        }
    }
}
