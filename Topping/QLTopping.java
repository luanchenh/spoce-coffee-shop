/**
 *
 */
package Topping;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Utils.IXuat;

public class QLTopping implements IXuat{
    private ArrayList<Topping> toppingList;

    File toppingFile = new File("../File/topping.txt");

    public QLTopping() {
        this.toppingList = new ArrayList<>();
    }

    public QLTopping(ArrayList<Topping> toppingList) {
        this.toppingList = toppingList;
    }

    // Init để đọc file lưu dữ liệu vào đây
    public void Init() {
        try(Scanner rd = new Scanner(toppingFile)) {
            while (rd.hasNextLine()) {
                String line = rd.nextLine();
                String[] toppingSplit = line.split("\\|");
                if (toppingSplit.length == 3) {
                    String id = toppingSplit[0];
                    String name = toppingSplit[1];
                    String price = toppingSplit[2];

                    Topping newTopping = new Topping(id, name, Integer.parseInt(price));
                    this.toppingList.add(newTopping);
                }
            }
            System.out.println("Đọc thành công !");
        } catch(Exception e) {
            System.out.println("Lỗi "+ e.getMessage());
        }
    }

    public boolean addNewTopping(Topping topping) {
        this.toppingList.add(topping);
        return true;
    }

    public boolean removeTopping(Topping topping) {
        for (Topping tp : this.toppingList) {
            if (tp.getId().equals(topping.getId())) {
                this.toppingList.remove(topping);
                return true;
            }
        }
        return false;
    }





    // Menu table
    public void menuTable() {
        System.out.println("====================[Danh sách topping]====================");
        System.out.printf("%-5s %-25s %-10s\n", "Mã", "Tên topping", "Giá (VND)\n");
        for (Topping tp : this.toppingList) {
            tp.menuInfo();
        }
    }


    // Override
    @Override
    public void xuatThongTin() {
        for (Topping tp : this.toppingList) {
            tp.xuatThongTin();
        }
    }

    public static void main(String[] args) {
        QLTopping list = new QLTopping();
        list.Init();
        list.menuTable();
    }
}
