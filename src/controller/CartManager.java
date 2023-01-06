package controller;

import model.Cart;
import model.Product;
import service.CRUD;
import java.util.ArrayList;
import java.util.Scanner;
import service.Check;

public class CartManager implements CRUD<Cart> {
    private ArrayList<Cart> carts;

    public CartManager() {
        carts = new ArrayList<>();
    }

    @Override
    public void display() {
        if (!carts.isEmpty()) {
            for (int i = 0; i < carts.size(); i++) {
                System.out.println(carts.get(i));
            }
        } else {
            System.out.println("Cart is empty");
        }

    }

    public Cart creatNew(Scanner scanner, ArrayList<Product> products) {
        Check check = new Check();
        int quantity = 0;
        boolean flag = false;
        String name = "";
        int price = 0;
        do {
            try {
                System.out.println("Enter name of product to buy: ");
                name = scanner.nextLine();
                if (check.checkEmpty(name)) {
                    for (int i = 0; i < products.size(); i++) {
                        if (products.get(i).getName().toUpperCase().contains(name.toUpperCase())) {
                            price = (int) products.get(i).getPrice();
                            flag = false;
                            break;
                        } else {
                            flag = true;
                        }
                    }
                    if (flag) {
                        System.out.println("Not have the same product, re-enter");
                    }
                } else {
                    System.out.println("Wrong format, re-enter");
                }
            } catch (NumberFormatException e) {
                System.out.println("Format wrong, re-enter");
            }
        } while (flag);

        do {
            try {
                System.out.println("Enter quantity of product to buy");
                quantity = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Format wrong, re-enter");
                flag = true;
            }
        } while (flag);
        return new Cart(name, price, quantity);
    }

    public void add(Scanner scanner, ArrayList<Product> products) {
        int indexBuy = -1;
        Cart cart = creatNew(scanner, products);
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().toUpperCase().contains(cart.getProduct().toUpperCase())) {
                indexBuy = i;
                break;
            }
        }
        if (indexBuy != -1) {
            if (products.get(indexBuy).getQuantity() > 0) {
                carts.add(cart);
                display();
                products.get(indexBuy).setQuantity(products.get(indexBuy).getQuantity() - cart.getQuantity());
            }
            else {
                System.out.println("This product is out of stock, please choose different product");
                creatNew(scanner);
                add(scanner);
            }
        }
        else {
            System.out.println("Not have the same product, re-enter");
        }

    }

    public void toPay(Scanner scanner) {
        int totalPay = 0;
        ProductManager productManager = new ProductManager();
        System.out.println("Enter your choose: ");
        System.out.println("1. To pay your cart!");
        System.out.println("2. Return to display product");
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose) {
            case 1:
                display();
                for (int i = 0; i < carts.size(); i++) {
                    totalPay += carts.get(i).getQuantity()*carts.get(i).getPrice();
                }
                System.out.println("Total Payment is: " + totalPay + " VND");
                System.out.println("");
                break;
            case 2:
                productManager.display();
                break;
            default:
                System.out.println("Out of choice, re-enter");
        }
    }

    @Override
    public Cart creatNew(Scanner scanner) {
        return null;
    }
    @Override
    public void add(Scanner scanner) {
    }

    @Override
    public void delete(Scanner scanner) {

    }

    @Override
    public void update(Scanner scanner) {

    }
}