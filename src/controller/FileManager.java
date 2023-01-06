package controller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager<E> {
    private String fileProduct = "D:\\Module 02\\CaseStudyMD2\\src\\database\\FileProduct";
    private String fileBrand = "D:\\Module 02\\CaseStudyMD2\\src\\database\\FileBrand";
    private String fileAccount = "D:\\Module 02\\CaseStudyMD2\\src\\database\\FileAccount";

    public FileManager() {
    }

    public String getFileProduct() {
        return fileProduct;
    }

    public void setFileProduct(String fileProduct) {
        this.fileProduct = fileProduct;
    }

    public String getFileBrand() {
        return fileBrand;
    }

    public void setFileBrand(String fileBrand) {
        this.fileBrand = fileBrand;
    }

    public String getFileAccount() {
        return fileAccount;
    }

    public void setFileAccount(String fileAccount) {
        this.fileAccount = fileAccount;
    }

    public List<E> readToFile(String nameFile) {
        List<E> list = new ArrayList<>();
        try {
            FileInputStream input = new FileInputStream(nameFile);
            if (input.available() > 0) {
                ObjectInputStream inputStream = new ObjectInputStream(input);
                list = (List<E>) inputStream.readObject();
                inputStream.close();
                input.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void writeToFile(String nameFile, List<E> list) {
        try {
            File file = new File(nameFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream output = new FileOutputStream(nameFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(output);
            outputStream.writeObject(list);
            outputStream.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
