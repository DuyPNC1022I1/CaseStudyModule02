package controller;

import service.CRUD;
import model.Product;
import model.Brand;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ProductManager implements CRUD<Product> {
    private ArrayList<Product> products;
    private BrandManager brandManager;
    public FileManager fileManager = new FileManager<>();
    int idDefault = 0;

    public ProductManager() {
    }

    public ProductManager(BrandManager brandManager) {
        products = new ArrayList<>();
        products = (ArrayList<Product>) fileManager.readToFile(fileManager.getFileProduct());
        this.brandManager = brandManager;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public void display() {
        if (products.isEmpty()) {
            System.out.println("Not exist Product in this list");
        } else {
            System.out.printf("-----------------------------------------------------------------%n");
            System.out.printf("                             LIST PRODUCT                        %n");
            System.out.printf("-----------------------------------------------------------------%n");
            System.out.printf("| %-3s | %-10s | %-15s | %-10s | %-11s |%n", "ID", "NAME", "PRICE (USD)", "QUANTITY", "BRAND");
            for (int i = 0; i < products.size(); i++) {
                System.out.printf("| %-3s | %-10s | %-15s | %-10s | %-11s |%n",
                        products.get(i).getId(), products.get(i).getName(), products.get(i).getPrice()
                        , products.get(i).getQuantity(), products.get(i).getBrand().getName());
            }
        }
    }

    @Override
    public Product creatNew(Scanner scanner) {
        int id;
        int price = 0;
        int quantity = 0;
        String name = "";
        boolean check = true;
        System.out.println("Enter to creat new product");
        if (products.isEmpty()) {
            id = idDefault++;
        } else {
            id = products.get(products.size() - 1).getId() + 1;
        }
        do {
            System.out.println("Enter name of product: ");
            name = scanner.nextLine();
            if (!name.equals("")) {
                check = false;
            } else {
                System.out.println("Re-enter: name of product > 0 character");
            }
        }
        while (check);
        do {
            try {
                System.out.println("Enter price of product: ");
                price = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter quantity of product: ");
                quantity = Integer.parseInt(scanner.nextLine());
                check = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format, re-enter");
            }
        }
        while (!check);
        System.out.println("Choose brand of product: ");
        brandManager.display();
        Brand brand = choiceBrand(scanner);
        fileManager.writeToFile(fileManager.getFileProduct(), products);
        return new Product(id, name, price, quantity, brand);
    }

    @Override
    public void add(Scanner scanner) {
        products.add(creatNew(scanner));
        fileManager.writeToFile(fileManager.getFileProduct(), products);
        display();
    }

    @Override
    public void delete(Scanner scanner) {
        int indexDelete = searchId(scanner);
        if (indexDelete != -1) {
            products.remove(indexDelete);
            fileManager.writeToFile(fileManager.getFileProduct(), products);
            display();
        } else {
            System.out.println("Delete fail because 'Not exist Product in list'");
        }
    }

    @Override
    public void update(Scanner scanner) {
        int indexUpdate = searchId(scanner);
        if (indexUpdate != -1) {
            boolean check = true;
            String name = "";
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == indexUpdate) {
                    indexUpdate = i;
                    break;
                }
            }
            do {
                try {
                    do {
                        System.out.println("Enter name to update: ");
                        name = scanner.nextLine();
                        if (!name.equals("")) {
                            check = false;
                        } else {
                            System.out.println("Re-enter: name of product > 0 character");
                        }
                    }
                    while (check);
                    products.get(indexUpdate).setName(name);
                    System.out.println("Enter price to update: ");
                    int price = Integer.parseInt(scanner.nextLine());
                    products.get(indexUpdate).setPrice(price);
                    System.out.println("Enter quantity to update: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    products.get(indexUpdate).setQuantity(quantity);
                    System.out.println("Enter idBrand to update: ");
                    brandManager.display();
                    Brand brand = choiceBrand(scanner);
                    products.get(indexUpdate).setBrand(brand);
                    fileManager.writeToFile(fileManager.getFileProduct(), products);
                    display();
                    check = true;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong format, re-enter!!!");
                }
            }
            while (!check);
        } else {
            System.out.println("Update fail because 'Not exist Product in list'");
        }
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

    public int searchId(Scanner scanner) {
        if (!products.isEmpty()) {
            int id = -1;
            int index = -1;
            boolean flag = true;
            boolean check = true;
            do {
                try {
                    System.out.println("Enter id: ");
                    id = Integer.parseInt(scanner.nextLine());
                    flag = false;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong format, re-enter!");
                }
            }
            while (flag);
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == id) {
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

    public void searchByName(Scanner scanner) {
        if (!products.isEmpty()) {
            System.out.println("Enter name search: ");
            String name = scanner.nextLine();
            boolean flag = true;
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                    System.out.println(products.get(i));
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
        if (!products.isEmpty()) {
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
            System.out.println("model.Product you need to find is: ");
            for (int i = 0; i < products.size(); i++) {
                if ((products.get(i).getPrice() >= priceLower) && (products.get(i).getPrice() <= priceUpper)) {
                    System.out.println(products.get(i));
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

    public void displayByPriceUp() {
        System.out.println("List product by up price: ");
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPrice() > o2.getPrice() ? 1 : -1;
            }
        });
        display();
    }

    public void displayByBrand(Scanner scanner) {
        System.out.println("List product by brand: ");
        brandManager.display();
        Brand brand = choiceBrand(scanner);
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == brand.getId()) {
                System.out.println(products.get(i));
            }
        }
    }
}