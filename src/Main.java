import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menuAll(scanner);
    }

    public static void menuAll(Scanner scanner) {
        do {
            System.out.println("~~~~~~~~~~~~~~~MENU~~~~~~~~~~~~~~~~");
            System.out.println("1. Login");
            System.out.println("2. Creat new Account");
            System.out.println("0. Exit");
            int choice = -1;
            try {
                System.out.println("Enter your choice: 0~2");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong format, re-enter");
            }
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    break;
                case 2:
                    break;

                default:
                    System.out.println("Out of choice. Re-enter choice: 0~4");
            }
        }
        while (true);
    }

    public static void menuUser() {
        System.out.println("~~~~~~~~~~~~~~~MENU_USER~~~~~~~~~~~~~~~~");
        System.out.println("1. Display list of product");
        System.out.println("2. Display product by up price");
        System.out.println("2. Search product by name");
        System.out.println("3. Search product by price ");
        System.out.println("4. Search product by brand");
        System.out.println("5. Add product to cart");
        System.out.println("6. Purchase order");
        System.out.println("7. Logout");
    }

    public static void menuAdmin() {
        System.out.println("~~~~~~~~~~~~~~~MENU_ADMIN~~~~~~~~~~~~~~~~");
        System.out.println("1. Display list of brand");
        System.out.println("2. Add brand");
        System.out.println("3. Delete brand");
        System.out.println("4. Display list of product");
        System.out.println("5. Add product");
        System.out.println("6. Delete product");
        System.out.println("7. Update information of product");
        System.out.println("8. Read file bill");
        System.out.println("9. Logout");
    }
}