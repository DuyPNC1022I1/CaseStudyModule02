package service;

import java.util.Scanner;

public interface CRUD <E> {
    void display();
    E creatNew(Scanner scanner);

    void add(Scanner scanner);
    void delete(Scanner scanner);
    void update(Scanner scanner);

}
