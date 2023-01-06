package service;

import controller.AccountManager;
import controller.BrandManager;
import controller.CartManager;
import controller.ProductManager;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    AccountManager accountManager = new AccountManager();
    BrandManager brandManager = new BrandManager();
    ProductManager productManager = new ProductManager(brandManager);
    CartManager cartManager = new CartManager();

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

    public void runMenuAll() {
        do {
            System.out.println("~~~~~~~~~~~~~~~~~~MENU~~~~~~~~~~~~~~~~~~");
            System.out.println("1. Login");
            System.out.println("2. Creat new Account");
            System.out.println("0. Exit");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            switch (choice(scanner)) {
                case 1:
                    accountManager.loginAccount(scanner);
                    break;
                case 2:
                    accountManager.add(scanner);
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
        do {
            System.out.println("~~~~~~~~~~~~~~~MENU_USER~~~~~~~~~~~~~~~~");
            System.out.println("1. Display list of product");
            System.out.println("2. Display product by up price");
            System.out.println("3. Display product by brand");
            System.out.println("4. Search product by name");
            System.out.println("5. Search product by price ");
            System.out.println("6. Add product to cart");
            System.out.println("7. Pay your cart");
            System.out.println("8. Logout");
            System.out.println("0. Exit");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            switch (choice(scanner)) {
                case 1:
                    productManager.display();
                    break;
                case 2:
                    productManager.displayByPriceUp();
                    break;
                case 3:
                    productManager.displayByBrand(scanner);
                    break;
                case 4:
                    productManager.searchByName(scanner);
                    break;
                case 5:
                    productManager.searchByPrice(scanner);
                    break;
                case 6:
                    cartManager.add(scanner, productManager.getProducts());
                    break;
                case 7:
                    cartManager.toPay(scanner);
                    break;
                case 8:
                    runMenuAll();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Out of choice. Re-enter choice: 0~7");
            }
        }
        while (true);
    }

    public void runMenuAdmin() {
        do {
            System.out.println("~~~~~~~~~~~~~~~MENU_ADMIN~~~~~~~~~~~~~~~~");
            System.out.println("1. Display account");
            System.out.println("2. Delete account");
            System.out.println("3. Display list of brand");
            System.out.println("4. Add brand");
            System.out.println("5. Delete brand");
            System.out.println("6. Display list of product");
            System.out.println("7. Add product");
            System.out.println("8. Delete product by id");
            System.out.println("9. Update information of product");
            System.out.println("10. Logout");
            System.out.println("0. Exit");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            switch (choice(scanner)) {
                case 1:
                    accountManager.display();
                    break;
                case 2:
                    accountManager.delete(scanner);
                    break;
                case 3:
                    brandManager.displayBrandManager();
                    break;
                case 4:
                    brandManager.addBrand(scanner);
                    break;
                case 5:
                    brandManager.deleteBrandById(scanner);
                    break;
                case 6:
                    productManager.display();
                    break;
                case 7:
                    productManager.add(scanner);
                    break;
                case 8:
                    productManager.delete(scanner);
                    break;
                case 9:
                    productManager.update(scanner);
                    break;
                case 10:
                    runMenuAll();
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