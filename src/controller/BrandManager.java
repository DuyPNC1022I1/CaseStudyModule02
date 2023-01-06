package controller;

import model.Brand;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class BrandManager implements Serializable {
    private ArrayList<Brand> brands;
    private int idDefault = 0;
    public FileManager fileManager = new FileManager<>();

    public BrandManager() {
        this.brands = new ArrayList<>();
        brands = (ArrayList<Brand>) fileManager.readToFile(fileManager.getFileBrand());
    }

    public void displayBrandManager() {
        if (!brands.isEmpty()) {
            for (int i = 0; i < brands.size(); i++) {
                System.out.println(brands.get(i));
            }
        } else {
            System.out.println("Not exist Brand in list");
        }
    }

    public Brand creatBrand(Scanner scanner) {
        System.out.println("Enter information of new Brand");
        int id;
        if (brands.isEmpty()) {
            id = idDefault++;
        } else {
            id = brands.get(brands.size() - 1).getId() + 1;
        }
        System.out.println("Enter name of new Brand");
        String name = scanner.nextLine();
        return new Brand(id, name);
    }

    public void addBrand(Scanner scanner) {
        brands.add(creatBrand(scanner));
        fileManager.writeToFile(fileManager.getFileBrand(), brands);
        displayBrandManager();
    }

    public void deleteBrandById(Scanner scanner) {
        int idToDelete = -1;
        int id = -1;
        boolean check = true;
        do {
            try {
                System.out.println("Enter id to delete");
                id = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format, re-enter");
            }
        }
        while (check);
        for (int i = 0; i < brands.size(); i++) {
            if (brands.get(i).getId() == id) {
                idToDelete = i;
            }
        }
        if (id == -1) {
            System.out.println("Not have product example this id");
            deleteBrandById(scanner);
        } else {
            brands.remove(idToDelete);
            displayBrandManager();
            fileManager.writeToFile(fileManager.getFileBrand(), brands);
        }
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