package controller;

import service.CRUD;
import model.Account;
import service.Menu;
import service.Check;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager implements CRUD<Account> {
    private final Check check;
    private ArrayList<Account> accounts;

    public FileManager fileManager = new FileManager<>();

    public AccountManager() {
        this.check = new Check();
        this.accounts = new ArrayList<>();
        accounts = (ArrayList<Account>) fileManager.readToFile(fileManager.getFileAccount());
        accounts.add(new Account("admin", "123", "admin", "admin@gmail.com", "0999999999", "admin"));
    }

    @Override
    public void display() {
        if (accounts.isEmpty()) {
            System.out.println("Not exist Product in this list");
        } else {
            for (int i = 0; i < accounts.size(); i++) {
                if (!accounts.get(i).getUserName().equals("admin")) {
                    System.out.println(accounts.get(i));
                }
            }
        }
    }

    @Override
    public Account creatNew(Scanner scanner) {
        String username = null;
        String pass = null;
        String email = null;
        String phoneNumber = null;
        boolean flag = true;
        System.out.println("Creat new account: ");
        System.out.println("Enter full name: ");
        String fullName = scanner.nextLine();
        do {
            System.out.println("Enter username [> 0 character]: ");
            username = scanner.nextLine();
            if (check.checkUsername(username)) {
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getUserName().equals(username)) {
                        flag = false;
                        System.out.println("Username already exist, re-enter");
                        break;
                    }
                    else {
                        flag = true;
                    }
                }
//                if (!flag) {
//                    System.out.println("Username already exist, re-enter");
//                }
            } else {
                System.out.println("Format wrong username, re-enter");
            }
        } while (!check.checkUsername(username) || !flag);
        do {
            System.out.println("Enter password [0 -> 15 character]: ");
            pass = scanner.nextLine();
            if (!check.checkEmpty(pass)) {
                System.out.println("Format wrong password, re-enter");
            }
        } while (!check.checkEmpty(pass));
        do {
            System.out.println("Enter email [Example: abc@abc]: ");
            email = scanner.nextLine();
            if (!check.checkEmail(email)) {
                System.out.println("Format wrong email, re-enter");
            }
            else {
                for (int i = 0; i < accounts.size(); i++) {
                    if(accounts.get(i).getEmail().equals(email)) {
                        flag = false;
                    }
                    else {
                        flag = true;
                    }
                }
                if (!flag) {
                    System.out.println("Email already exist, re-enter");
                }
            }
        } while (!check.checkEmail(email) || !flag);
        do {
            System.out.println("Enter phone number [Example: 0*********]: ");
            phoneNumber = scanner.nextLine();
            if (!check.checkPhoneNumber(phoneNumber)) {
                System.out.println("Format wrong password, re-enter");
            }
        } while (!check.checkPhoneNumber(phoneNumber));
        System.out.println("Creat new account complete!!!");
        return new Account(username, pass, fullName, email, phoneNumber);
    }


    @Override
    public void add(Scanner scanner) {
        accounts.add(creatNew(scanner));
        fileManager.writeToFile(fileManager.getFileAccount(), accounts);
    }

    @Override
    public void delete(Scanner scanner) {
        int index = -1;
        System.out.println("Enter username of Account to delete");
        String usernametodelete = scanner.nextLine();
        if (check.checkEmpty(usernametodelete)) {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getUserName().equals(usernametodelete)) {
                    index = i;
                }
            }
            if (index != -1) {
                accounts.remove(accounts.get(index));
                System.out.println("Delete success!!!");
                fileManager.writeToFile(fileManager.getFileAccount(), accounts);
            } else {
                System.out.println("Not have the same username to delete");
            }
        } else {
            System.out.println("Not have the same username to delete");
        }
    }

    @Override
    public void update(Scanner scanner) {
    }

    public void loginAccount(Scanner scanner) {
        Menu menu = new Menu();
        boolean flag = true;
        //Check login
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String pass = scanner.nextLine();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUserName().equals(username) && accounts.get(i).getPassword().equals(pass)) {
                System.out.println("Login complete!!!");
                flag = false;
                break;
            }
        }
        if (!flag) {
            if (username.equals("admin")) {
                menu.runMenuAdmin();
            } else {
                menu.runMenuUser();
            }
        } else {
            System.out.println("Login fail. Please re-enter!!!");
            loginAccount(scanner);
        }
    }
}