import javafx.scene.transform.Scale;

import java.util.ArrayList;
import java.util.Scanner;

public class BrandManager {
    private ArrayList<Brand> brandManager;
    private int idDefault = 0;

    public BrandManager() {
        this.brandManager = new ArrayList<>();
    }

    public void displayBrandManager() {
        if (!brandManager.isEmpty()) {
            for (int i = 0; i < brandManager.size(); i++) {
                System.out.println(brandManager.get(i));
            }
        } else {
            System.out.println("Not exist Brand in list");
        }
    }

    public Brand creatBrand(Scanner scanner) {
        System.out.println("Enter information of new Brand");
        int id;
        if (brandManager.isEmpty()) {
            id = idDefault++;
        } else {
            id = brandManager.get(brandManager.size() - 1).getId() + 1;
        }
        System.out.println("Enter name of new Brand");
        String name = scanner.nextLine();
        return new Brand(id, name);
    }

    public void addBrand(Scanner scanner) {
        brandManager.add(creatBrand(scanner));
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
        for (int i = 0; i < brandManager.size(); i++) {
            if (brandManager.get(i).getId() == id) {
                idToDelete = i;
            }
        }
        if (id == -1) {
            System.out.println("Not have product example this id");
            deleteBrandById(scanner);
        } else {
            brandManager.remove(idToDelete);
            displayBrandManager();
        }
    }

    public Brand getBrandById(int id) {
        for (int i = 0; i < brandManager.size(); i++) {
            if (brandManager.get(i).getId() == id) {
                return brandManager.get(i);
            }
        }
        return null;
    }
}
