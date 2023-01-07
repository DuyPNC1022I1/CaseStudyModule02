package controller;

import model.Brand;
import model.Product;
import service.CRUD;

import java.util.ArrayList;
import java.util.Scanner;


public class BrandManager implements CRUD<Brand> {
    private ArrayList<Brand> brands;
    private int idDefault = 1;
    public FileManager fileManager = new FileManager<>();

    public BrandManager() {
        this.brands = new ArrayList<>();
        brands = (ArrayList<Brand>) fileManager.readToFile(fileManager.getFileBrand());
    }

    @Override
    public void display() {
        if (!brands.isEmpty()) {
            System.out.printf("-----------------------------%n");
            System.out.printf("       LIST BRAND            %n");
            System.out.printf("-----------------------------%n");
            System.out.printf("| %-3s | %-12s |%n", "ID", "NAME");
            for (int i = 0; i < brands.size(); i++) {
//                System.out.println(brands.get(i));
                System.out.printf("| %-3s | %-12s |%n", brands.get(i).getId(), brands.get(i).getName());
            }
        } else {
            System.out.println("Not exist Brand in list");
        }
    }

    @Override
    public Brand creatNew(Scanner scanner) {
        System.out.println("Enter information of new Brand");
        int id;
        if (brands.isEmpty()) {
            id = idDefault++;
        } else {
            id = brands.get(brands.size() - 1).getId() + 1;
        }
        System.out.println("Enter name of new Brand: ");
        String name = scanner.nextLine();
        return new Brand(id, name);
    }

    @Override
    public void add(Scanner scanner) {
        brands.add(creatNew(scanner));
        fileManager.writeToFile(fileManager.getFileBrand(), brands);
        display();
    }

    @Override
    public void delete(Scanner scanner) {

    }

    public void delete(Scanner scanner, ArrayList<Product> products) {
        int idToDelete = -1;
        int id = -1;
        boolean check = true;
        do {
            try {
                System.out.println("Enter id brand to delete");
                id = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format, re-enter");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out of index, re-enter");
            }
        }
        while (check);
        for (int i = 0; i < brands.size(); i++) {
            if (brands.get(i).getId() == id) {
                idToDelete = i;
            }
        }
        try {
            if (id == -1) {
                System.out.println("Not have product example this id");
                delete(scanner);
            } else {
                brands.remove(idToDelete);
                display();
                fileManager.writeToFile(fileManager.getFileBrand(), brands);
                //Xóa product theo brand
                int index = -1;
                do {
                    for (int i = 0; i < products.size(); i++) {
                        if (products.get(i).getBrand().getId() == idToDelete) {
                            index = i;
                            check = true;
                            break;
                        }
                    }
                    if (index != -1) {
                        products.remove(products.get(index));
                        fileManager.writeToFile(fileManager.getFileProduct(), products);
                    }
                }
                while (check) ;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of index");
        }
    }

    @Override
    public void update(Scanner scanner) {
    }

    public Brand getBrandById(int id) {
        for (int i = 0; i < brands.size(); i++) {
            if (brands.get(i).getId() == id) {
                return brands.get(i);
            }
        }
        return null;
    }
}