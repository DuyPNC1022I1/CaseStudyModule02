import java.util.Scanner;

public class Menu {
    public void runMenuAll(Scanner scanner, AccountManager accountManager, Menu menu) {
        do {
            System.out.println("~~~~~~~~~~~~~~~MENU~~~~~~~~~~~~~~~~");
            System.out.println("1. Login");
            System.out.println("2. Creat new Product.Account");
            System.out.println("0. Exit");
            int choice = -1;
            try {
                System.out.println("Enter your choice: 0~2");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong format, re-enter");
            }
            switch (choice) {
                case 1:
                    accountManager.loginAccount(menu, scanner);
                    break;
                case 2:
                    accountManager.addAccount(scanner);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Out of choice. Re-enter choice: 0~2");
            }
        }
        while (true);
    }

    public void runMenuUser() {
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

    public void runMenuAdmin() {
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
