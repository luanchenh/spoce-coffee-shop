/**
 *
 */
package Topping;

import java.io.File;
import java.io.FileWriter;
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

    // Getter
    public ArrayList<Topping> getToppingList() {
        return toppingList;
    }


    // ========================================[Xử lí File]========================================
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
            rd.close();
        } catch(Exception e) {
            System.out.println("Lỗi "+ e.getMessage());
        }
    }

    public void writeAll() {
        try(FileWriter writer = new FileWriter(toppingFile, false)) {
            for (Topping tp : this.toppingList) {
                writer.append(tp.makeString() +"\n");
            }
            writer.flush();
        } catch(Exception e) {
            System.out.println("Lỗi: "+ e.getMessage());
        }
    }


    // ========================================[Function]========================================
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

    public boolean IDtoppingInList(Topping topping) {
        for (Topping tp : this.toppingList) {
            if (tp.getId().equals(topping.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean toppingInList(Topping topping) {
        for (Topping tp : this.toppingList) {
            if (tp.equals(topping)) {
                return true;
            }
        }
        return false;
    }


    public void writeToppingIntoFile(Topping topping) {
        if (!IDtoppingInList(topping)) {
            try(FileWriter writer = new FileWriter(toppingFile, true)) {
                if (topping != null) {
                    String line = topping.makeString();
                    writer.append(line +"\n");
                    if (addNewTopping(topping)) {
                        System.out.println("Thêm topping mới vào thành công");
                    }
                    else {
                        System.out.println("Thêm topping mới thất bại");
                    }
                }
            } catch (Exception e) {
                System.out.println("Lỗi: "+ e.getMessage());
            }
        }
        else {
            System.out.println("Topping đã tồn tại !");
        }
    }







    // ========================================[Menu topping table]========================================
    public void menuTable() {
        System.out.println("====================[Danh sách topping]====================");
        System.out.printf("%-5s %-25s %-10s\n", "Mã", "Tên topping", "Giá (VND)\n");
        for (Topping tp : this.toppingList) {
            tp.menuInfo();
        }
    }


    // ========================================[Override Method]========================================
    @Override
    public void xuatThongTin() {
        for (Topping tp : this.toppingList) {
            tp.xuatThongTin();
        }
    }

    public static void main(String[] args) {
        QLTopping list = new QLTopping();
        list.Init();
        Topping a = new Topping();
        a.nhapThongTin();
        list.writeToppingIntoFile(a);
        list.menuTable();
    }
}
