package controller;

import model.Bill;
import java.time.LocalDate;
import java.util.Scanner;

public class BillManager  {
    public void displayBillUser(Scanner scanner, CartManager cartManager, ProductManager productManager) {
        try {
            Bill bill = creatNew(scanner, cartManager, productManager);
            System.out.printf("-------------------------------------------%n");
            System.out.printf("                     BILL                  %n");
            System.out.printf("-------------------------------------------%n");
            System.out.printf("Date:  %-15s                    |%n", bill.getDate());
            System.out.printf("Customer: %-12s                   |%n", bill.getCustomerName());
            System.out.printf("|%-10s | %-15s |%-10s |%n", "PRODUCT", "PRICE(USD)", "QUANTITY");
            for (int i = 0; i < cartManager.getCarts().size(); i++) {
                System.out.printf("|%-10s | %-15s |%-10s |%n", cartManager.getCarts().get(i).getProduct(),
                        cartManager.getCarts().get(i).getPrice(), cartManager.getCarts().get(i).getQuantity());
            }
            System.out.printf("-------------------------------------------%n");
            System.out.printf("TOTAL PAYMENT(USD): %-20s  |%n", bill.getTotalPayment());
            System.out.printf("-------------------------------------------%n");
        }
        catch (NullPointerException e){
            System.out.println("Creat bill false! Not have the product in cart");
        }
    }

    public Bill creatNew(Scanner scanner, CartManager cartManager, ProductManager productManager) {
        LocalDate date =java.time.LocalDate.now();
        String name = "";
        int totalPay = 0;
        if (!cartManager.toPay(scanner, productManager)) {
            System.out.println("Enter to your bill info: ");
            System.out.println("Enter your name: ");
            name = scanner.nextLine();
            for (int i = 0; i < cartManager.getCarts().size(); i++) {
                totalPay += cartManager.getCarts().get(i).getQuantity() * cartManager.getCarts().get(i).getPrice();
            }
            return new Bill(date, name, cartManager, totalPay);
        }
        else {
            return null;
        }
    }
}