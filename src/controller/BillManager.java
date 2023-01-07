package controller;

import model.Bill;
import model.Cart;
import service.CRUD;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BillManager implements CRUD {
    private ArrayList<Bill> bills;

    public BillManager() {
        this.bills = new ArrayList<>();
    }


    @Override
    public void display() {
        if (!bills.isEmpty()) {
            for (int i = 0; i < bills.size(); i++) {
                System.out.println(bills.get(i));
            }
        }
        else {
            System.out.println("Bill is empty");
        }
    }

    public void displayBillUser(Scanner scanner, ArrayList<Cart> carts, CartManager cartManager) {
        System.out.println(creatNew(scanner, carts, cartManager));
        System.out.printf("-------------------------------------%n");
        System.out.printf("          BILL INFORMATION           %n");
        System.out.printf("-------------------------------------%n");
        System.out.printf("| %-15s | %-12s |%-10s | %-15s |%-10s |%n", "DATE", "CUSTOMER NAME", "PRODUCT", "PRICE(USD)", "QUANTITY");
        System.out.printf("| %-15s | %-12s |%-10s | %-15s |%-10s |%n", "DATE", "CUSTOMER NAME", "PRODUCT", "PRICE(USD)", "QUANTITY");
        System.out.printf("-------------------------------------%n");
        System.out.printf(" %-20s |%n","TOTAL PAYMENT(USD)");

    }

    public Bill creatNew(Scanner scanner, ArrayList<Cart> carts, CartManager cartManager) {
        LocalDate date =java.time.LocalDate.now();
        String name = "";
        int totalPay = 0;
        if (cartManager.toPay(scanner)) {
            System.out.println("Enter to your bill info: ");
            System.out.println("Enter your name: ");
            name = scanner.nextLine();
            for (int i = 0; i < carts.size(); i++) {
                totalPay += carts.get(i).getQuantity() * carts.get(i).getPrice();
            }
            return new Bill(date, name, cartManager, totalPay);
        }
        else {
            return null;
        }

    }
    @Override
    public Object creatNew(Scanner scanner) {
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
