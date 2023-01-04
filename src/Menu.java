import java.util.Scanner;

public class Menu {

    public int choice(Scanner scanner) {
        int choice = -1;
        try {
            System.out.println("Enter your choice:");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Wrong format, re-enter");
        }
        return choice;
    }

    public void runMenuAll(Scanner scanner, AccountManager accountManager, Menu menu, BrandManager brandManager, ProductManager productManager) {
        do {
            System.out.println("~~~~~~~~~~~~~~~MENU~~~~~~~~~~~~~~~~");
            System.out.println("1. Login");
            System.out.println("2. Creat new Account");
            System.out.println("0. Exit");
            switch (choice(scanner)) {
                case 1:
                    accountManager.loginAccount(menu, scanner, brandManager, productManager, accountManager);
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

    public void runMenuUser(Scanner scanner, BrandManager brandManager, ProductManager productManager, AccountManager accountManager, Menu menu) {
        do {
            System.out.println("~~~~~~~~~~~~~~~MENU_USER~~~~~~~~~~~~~~~~");
            System.out.println("1. Display list of product");
            System.out.println("2. Display product by up price");
            System.out.println("2. Search product by name");
            System.out.println("3. Search product by price ");
            System.out.println("4. Search product by brand");
            System.out.println("5. Add product to cart");
            System.out.println("6. Purchase order");
            System.out.println("7. Logout");
            switch (choice(scanner)) {
                case 1:
                    productManager.displayProduct();
                    break;
                case 2:
                    productManager.displayByPriceUp();
                    break;
                case 3:
                    productManager.searchByName(scanner);
                    break;
                case 4:
                    productManager.searchByBrand(scanner);
                    break;
                case 5:
                    productManager.addToCart(scanner);
                    break;
                case 6:
                    break;
                case 7:
                    runMenuAll(scanner, accountManager, menu, brandManager, productManager);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Out of choice. Re-enter choice: 0~7");
            }
        }
        while (true);
    }

    public void runMenuAdmin(Scanner scanner, BrandManager brandManager, ProductManager productManager, AccountManager accountManager, Menu menu) {
        do {
            System.out.println("~~~~~~~~~~~~~~~MENU_ADMIN~~~~~~~~~~~~~~~~");
            System.out.println("1. Display list of brand");
            System.out.println("2. Add brand");
            System.out.println("3. Delete brand");
            System.out.println("4. Display list of product");
            System.out.println("5. Add product");
            System.out.println("6. Delete product by id");
            System.out.println("7. Update information of product");
            System.out.println("8. Read file bill");
            System.out.println("9. Write to file");
            System.out.println("10. Read to file");
            System.out.println("11. Logout");
            switch (choice(scanner)) {
                case 1:
                    brandManager.displayBrandManager();
                    break;
                case 2:
                    brandManager.addBrand(scanner);
                    break;
                case 3:
                    brandManager.deleteBrandById(scanner);
                    break;
                case 4:
                    productManager.displayProduct();
                    break;
                case 5:
                    productManager.addProduct(scanner);
                    break;
                case 6:
                    productManager.deleteProductById(scanner);
                    break;
                case 7:
                    productManager.updateProductById(scanner);
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    runMenuAll(scanner, accountManager, menu, brandManager, productManager);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Out of choice. Re-enter choice: 0~9");
            }
        }
        while (true);
    }
}
