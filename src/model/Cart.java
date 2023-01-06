package model;

public class Cart {
    private String product;
    private int price;
    private int quantity;

    public Cart(String product, int price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart {" +
                "product='" + product + '\'' +
                ", price=" + price + " VND" +
                ", quantity=" + quantity +
                '}';
    }
}
