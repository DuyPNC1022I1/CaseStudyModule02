import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        AccountManager accountManager = new AccountManager();
        BrandManager brandManager = new BrandManager();
        ProductManager productManager = new ProductManager(brandManager);

        menu.runMenuAll(scanner, accountManager, menu, brandManager, productManager);
    }
}