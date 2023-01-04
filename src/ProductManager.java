import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ProductManager {
    private ArrayList<Product> productManager;
    private BrandManager brandManager;
    int idDefault = 0;

    public ProductManager(BrandManager brandManager) {
        this.productManager = new ArrayList<>();
        this.brandManager = brandManager;
    }

    public void displayProduct() {
        if (productManager.isEmpty()) {
            System.out.println("Not exist Product in this list");
        } else {
            for (int i = 0; i < productManager.size(); i++) {
                System.out.println(productManager.get(i));
            }
        }
    }

    public Product creatProduct(Scanner scanner) {
        int id;
        double price = 0;
        int quantity = 0;
        boolean check = true;
        System.out.println("Enter to new product");
        if (productManager.isEmpty()) {
            id = idDefault++;
        } else {
            id = productManager.get(productManager.size() - 1).getId() + 1;
        }
        System.out.println("Enter name of product: ");
        String name = scanner.nextLine();
        do {
            try {
                System.out.println("Enter price of product: ");
                price = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter quantity of product: ");
                quantity = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format, re-enter");
            }
        }
        while (check);
        System.out.println("Choose brand of product: ");
        Brand brand = choiceBrand(scanner);
        return new Product(id, name, price, quantity, brand);
    }

    public Brand choiceBrand(Scanner scanner) {
        Brand brand = null;
        int id = 0;
        boolean check = true;
        do {
            try {
                System.out.println("Enter id to choice brand:");
                id = Integer.parseInt(scanner.nextLine());
                check = false;
                brand = brandManager.getBrandById(id);
            } catch (NumberFormatException e) {
                System.out.println("Wrong format, re-enter");
            }
        }
        while (check);
        if (brand != null) {
            return brand;
        } else {
            System.out.println("Not have the same id of brand, re-enter ");
            return choiceBrand(scanner);
        }
    }

    public void addProduct(Scanner scanner) {
        productManager.add(creatProduct(scanner));
        displayProduct();
    }

    public int searchId(Scanner scanner) {
        if (!productManager.isEmpty()) {
            int id = -1;
            int index = -1;
            boolean flag = true;
            boolean check = true;
            do {
                try {
                    System.out.println("Enter id: ");
                    id = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Wrong format, re-enter!");
                }
            }
            while (flag);
            for (int i = 0; i < productManager.size(); i++) {
                if (productManager.get(i).getId() == id) {
                    index = i;
                    check = false;
                }
            }
            if (check) {
                System.out.println("Not have the same id, re-enter!");
                searchId(scanner);
            }
            return index;
        } else {
            return -1;
        }
    }

    public void deleteProductById(Scanner scanner) {
        int indexDelete = searchId(scanner);
        if (indexDelete != -1) {
            for (int i = 0; i < productManager.size(); i++) {
                if (productManager.get(i).getId() == searchId(scanner)) {
                    indexDelete = i;
                }
            }
            productManager.remove(indexDelete);
            displayProduct();
        } else {
            System.out.println("Delete fail because 'Not exist Product in list'");
        }
    }

    public void updateProductById(Scanner scanner) {
        int indexUpdate = searchId(scanner);
        if (indexUpdate != -1) {
            for (int i = 0; i < productManager.size(); i++) {
                if (productManager.get(i).getId() == indexUpdate) {
                    boolean check = true;
                    do {
                        try {
                            System.out.println("Enter id to update: ");
                            int id = Integer.parseInt(scanner.nextLine());
                            productManager.get(i).setId(id);
                            System.out.println("Enter name to update: ");
                            String name = scanner.nextLine();
                            productManager.get(i).setName(name);
                            System.out.println("Enter price to update: ");
                            double price = Double.parseDouble(scanner.nextLine());
                            productManager.get(i).setPrice(price);
                            System.out.println("Enter quantity to update: ");
                            int quantity = Integer.parseInt(scanner.nextLine());
                            productManager.get(i).setQuantity(quantity);
                            System.out.println("Enter brand to update: ");
                            Brand brand = choiceBrand(scanner);
                            productManager.get(i).setBrand(brand);
                            check = false;
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong format, re-enter!!!");
                        }
                    }
                    while (check);
                }
            }
            displayProduct();
        } else {
            System.out.println("Update fail because 'Not exist Product in list'");
        }
    }

    public void searchByName(Scanner scanner) {
        if (!productManager.isEmpty()) {
            System.out.println("Enter name search: ");
            String name = scanner.nextLine();
            boolean flag = true;
            for (int i = 0; i < productManager.size(); i++) {
                if (productManager.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                    System.out.println(productManager.get(i));
                    flag = false;
                }
            }
            if (flag) {
                System.out.println("Not have the same name of product, re-enter!");
            }
        } else {
            System.out.println("Search fail because 'Not exist Product in list'");
        }
    }

    public void searchByPrice(Scanner scanner) {
        if (!productManager.isEmpty()) {
            boolean flag = true;
            boolean check = true;
            double priceLower = 0;
            double priceUpper = 0;
            do {
                try {
                    System.out.println("Enter price to search: ");
                    System.out.println("Enter price upper: ");
                    priceUpper = Double.parseDouble(scanner.nextLine());
                    System.out.println("Enter price lower: ");
                    priceLower = Double.parseDouble(scanner.nextLine());
                    check = false;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong format, re-enter");
                }
            }
            while (check);
            System.out.println("Product you need to find is: ");
            for (int i = 0; i < productManager.size(); i++) {
                if ((productManager.get(i).getPrice() >= priceLower) && (productManager.get(i).getPrice() <= priceUpper)) {
                    System.out.println(productManager.get(i));
                    flag = false;
                }
            }
            if (flag) {
                System.out.println("Not have the same price of product");
            }
        } else {
            System.out.println("Search fail because 'Not exist Product in list'");
        }
    }

    //Hiển thị sản phẩm theo giá tăng dần
    public void displayByPriceUp() {
        System.out.println("List product by up price: ");
        Collections.sort(productManager, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPrice() > o2.getPrice() ? 1 : -1;
            }
        });
        displayProduct();
    }

    public void searchByBrand(Scanner scanner) {
        if (!productManager.isEmpty()) {
            System.out.println("Enter choice brand to search: ");
            Brand brandSearch = choiceBrand(scanner);
            boolean flag = true;
            for (int i = 0; i < productManager.size(); i++) {
                if (productManager.get(i).getBrand().equals(brandSearch)) {
                    System.out.println(productManager.get(i));
                    flag = false;
                }
            }
            if (flag) {
                System.out.println("Not have the same brand of product!");
            }
        } else {
            System.out.println("Search fail because 'Not exist Product in list'");
        }
    }

    //Mua, thêm vào giỏ hàng
    public void addToCart(Scanner scanner) {
        ArrayList<Product> cart = new ArrayList<>();
        double totalPayment = 0; //Tổng tiền thanh toán
        System.out.println("Enter name of product to buy: ");
        String nameToBuy = scanner.nextLine();
        int indexBuy = -1;
        for (int i = 0; i < productManager.size(); i++) {
            if (productManager.get(i).getName().toUpperCase().contains(nameToBuy.toUpperCase())) {
                indexBuy = i;
            }
        }
        if ((indexBuy != -1)) {
            if (productManager.get(indexBuy).getQuantity() != 0) {
                cart.add(productManager.get(indexBuy)); //Thêm sản phẩm vào giỏ hàng user
                productManager.get(indexBuy).setQuantity(productManager.get(indexBuy).getQuantity() - 1);
                displayProduct();
                System.out.println("Now, your cart is: ");
                for (int i = 0; i < cart.size(); i++) {
                    System.out.println(cart.get(i));
                }
                System.out.println("Total payment is: ");
                for (int i = 0; i < cart.size(); i++) {
                    totalPayment += cart.get(i).getQuantity() * cart.get(i).getPrice();
                }
            }
            else {
                System.out.println("This product is out of stock, please choose different product");
                addToCart(scanner);
            }
        } else {
            System.out.println("Not have the same name!, re-enter");
            addToCart(scanner);
        }
    }

    //Ghi file
    public void writeToFile(String nameFile) {
        try {
            FileOutputStream out = new FileOutputStream(nameFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(productManager);
            outputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Đọc file
}
