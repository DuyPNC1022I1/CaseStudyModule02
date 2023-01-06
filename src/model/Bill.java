package model;

import controller.CartManager;

import java.time.LocalDate;

public class Bill {
    private LocalDate date;
    private String customerName;

    private CartManager carts;
    private int totalPayment;

    public Bill() {
    }

    public Bill(LocalDate date, String customerName, CartManager carts, int totalPayment) {
        this.date = date;
        this.customerName = customerName;
        this.carts = carts;
        this.totalPayment = totalPayment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public CartManager getCarts() {
        return carts;
    }

    public void setCarts(CartManager carts) {
        this.carts = carts;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "date=" + date +
                ", customerName='" + customerName + '\'' +
                ", carts=" + carts +
                ", totalPayment=" + totalPayment +
                '}';
    }
}
