import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menuAll(scanner);
    }

    public static void menuAll(Scanner scanner) {
        do {
            System.out.println("MENU");
            System.out.println("1. Enter to Menu 'Creat new Account'");
            System.out.println("2. Enter to Menu 'Login'");
            System.out.println("3. Enter to Save file");
            System.out.println("4. Enter to Read file");
            System.out.println("0. Exit");
            int choice = -1;
            try {
                System.out.println("Enter your choice: 0~4");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong format, re-enter");
            }
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Out of choice. Re-enter choice: 0~4");
            }
        }
        while (true);

    }

    public static void menuCreat() {
        System.out.println("1. Creat new account for user");
    }

    public static void menuLogin(Scanner scanner) {
        System.out.println("1. Login with user");
        System.out.println("2. Login with admin");
        System.out.println("0. Return to menuAll");
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Out of choice. Re-enter choice: 0~2");

            }
        }
        catch (NumberFormatException e) {
            System.out.println("Wrong format, re-enter");
        }
    }

    public static void menuUser(){
        System.out.println("1. Display all product");
        System.out.println("2. Search product by price");
        System.out.println("3. Search prodcut ");
    }

    public static void menuAdmin(){
    }
}