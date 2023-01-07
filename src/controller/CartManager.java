package controller;

import model.Cart;
import model.Product;
import service.CRUD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import service.Check;

public class CartManager implements Serializable {
    private ArrayList<Cart> carts;

    public CartManager() {
        carts = new ArrayList<>();
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
    }

    public void display() {
        if (!carts.isEmpty()) {
            System.out.printf("--------------------------------------------%n");
            System.out.printf("                   YOUR CART                %n");
            System.out.printf("--------------------------------------------%n");
            System.out.printf("| %-10s | %-15s |%-10s |%n", "PRODUCT", "PRICE(USD)", "QUANTITY");
            for (int i = 0; i < carts.size(); i++) {
                System.out.printf("| %-10s | %-15s |%-10s |%n"
                        , carts.get(i).getProduct(), carts.get(i).getPrice(), carts.get(i).getQuantity());
            }
        } else {
            System.out.println("Your cart is empty");
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
                flag = true;
            } catch (NumberFormatException e) {
                System.out.println("Format wrong, re-enter");
            }
        } while (!flag);
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
            } else {
                System.out.println("This product is out of stock, please choose different product");
                creatNew(scanner, products);
            }
        } else {
            System.out.println("Not have the same product, re-enter");
        }

    }

    public boolean toPay(Scanner scanner, ProductManager productManager) {
        int totalPay = 0;
        boolean check = true;
        System.out.println("Enter your choose: [0~2] ");
        System.out.println("1. To pay your cart!");
        System.out.println("2. Cancel");
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose) {
            case 1:
                display();
                for (int i = 0; i < carts.size(); i++) {
                    totalPay += carts.get(i).getQuantity() * carts.get(i).getPrice();
                }
                System.out.printf("--------------------------------------------%n");
                System.out.printf("TOTAL PAYMENT(USD): %-20s %n", totalPay);
                System.out.printf("--------------------------------------------%n");
                break;
            case 2:
                productManager.display();
                check = false;
                break;
            default:
                System.out.println("Out of choice, re-enter");
        }
        return check;
    }
}