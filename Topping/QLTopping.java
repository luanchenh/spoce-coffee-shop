/**
 *
 */
package Topping;

import Utils.Function;
import Utils.IXuat;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


@SuppressWarnings("resource")
public class QLTopping implements IXuat{
    private ArrayList<Topping> toppingList;

    File toppingFile = new File("./File/topping.txt");

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
            //System.out.println("Đọc thành công !");
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

    public Topping getToppingByID(String id) {
        Topping tp = null;
        for (Topping topping : this.toppingList) {
            if (topping.getId().equals(id)) {
                tp = topping;
                break;
            }
        }
        return tp;
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

    public void addTopping() {
        Topping tp = new Topping();
        tp.nhapThongTin();
        this.toppingList.add(tp);
        this.writeAll();
    }

    public void removeATopping() {
        this.menuTable();
        Scanner sc = new Scanner(System.in);
        String str;

        boolean isDone = false;

        while (true) {
            System.out.print("\n\t=> Nhập ID topping muốn xóa: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tID topping không được rỗng!");
                continue;
            }

            for (Topping tp : this.toppingList) {
                if (tp.getId().equalsIgnoreCase(str)) {
                    this.toppingList.remove(tp);
                    System.out.println("\tXóa topping thành công!");
                    isDone = true;
                    break;
                }
            }

            if (!isDone) {
                System.out.println("\tKhông tìm thấy topping!");
            } else {
                this.writeAll();
            }

            break;
        }
    }

    public void modifyTopping() {
        Scanner sc = new Scanner(System.in);
        String str;
        boolean isDone = false;

        this.menuTable();

        while (true) {
            System.out.print("\n\t=> Mời nhập ID topping: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tID topping không được rỗng!");
                continue;
            }

            for (Topping tp : this.toppingList) {
                if (tp.getId().equalsIgnoreCase(str)) {
                    tp.suaThongTin();
                    isDone = true;
                    break;
                }
            }

            if (!isDone) {
                System.out.println("\tKhông tìm thấy topping!");
            } else {
                this.writeAll();
            }

            break;
        }
    }

    public void findTopping() {
        Scanner sc = new Scanner(System.in);
        String str;
        Topping topping = null;

        while (true) {
            System.out.print("\n\tMời nhập ID topping: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tID topping không được rỗng");
                continue;
            }

            for (Topping tp : this.toppingList) {
                if (tp.getId().equalsIgnoreCase(str)) {
                    topping = tp;
                    break;
                }
            }

            if (topping == null) {
                System.out.println("\tKhông tìm thấy topping nào có ID: " + str);
            } else {
                System.out.println("\tKết quả tìm kiếm: ");
                topping.xuatThongTin();
            }

            break;
        }
    }

    public void resetList() {
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            System.out.println("\n\tBạn có chắc chắn muốn xoá toàn bộ danh sách không?");
            System.out.println("\t1. Có");
            System.out.println("\t2. Không");
            System.out.print("\t=> Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
                continue;
            }

            switch (str) {
                case "1":
                this.toppingList.clear();
                System.out.println("\tLàm mới danh sách thành công!");
                break;

                case "2":
                System.out.println("\tHủy bỏ làm mới danh sách!");
                break;

                default:
                System.out.println("\tLựa chọn không hợp lệ!");
                continue;
            }

            break;
        }
    }

    // ========================================[Menu topping table]========================================
    public void menuTable() {
        Function.clearScreen();
        System.out.println("\t=====================[Danh sách topping]=====================");
        System.out.printf("\t| %-10s | %-25s | %-16s |\n", "Mã", "Tên topping", "Giá (VND)");
        System.out.println("\t-------------------------------------------------------------");
        for (Topping tp : this.toppingList) {
            tp.menuInfo();
        }
        System.out.println("\t=============================================================");
    }


    // ========================================[Override Method]========================================
    @Override
    public void xuatThongTin() {
        for (Topping tp : this.toppingList) {
            tp.xuatThongTin();
        }
    }

    public void menuQLTopping() {
        Function.clearScreen();
        this.Init(); // Khởi tạo dữ liệu
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            // In tiêu đề
            Function.clearScreen();
            System.out.println(
                    "\t===============================[ Menu Quản Lý Nước Uống ]===============================");

            // In tiêu đề các cột
            System.out.printf("\t| %-4s | %-77s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------------------|");

            // In danh sách các lựa chọn
            System.out.printf("\t| %-4s | %-77s |%n", "1", "In danh sách topping");
            System.out.printf("\t| %-4s | %-77s |%n", "2", "Thêm một topping (Tự động lưu vào File)");
            System.out.printf("\t| %-4s | %-77s |%n", "3", "Xoá một topping (Tự động load vào File)");
            System.out.printf("\t| %-4s | %-77s |%n", "4", "Sửa thông tin topping");
            System.out.printf("\t| %-4s | %-77s |%n", "5", "Cập nhật lại topping vào File từ danh sách");
            System.out.printf("\t| %-4s | %-77s |%n", "6", "Cập nhật lại topping vào danh sách từ File");
            System.out.printf("\t| %-4s | %-77s |%n", "7", "Làm mới danh sách topping (Reset dữ liệu nhưng không load vào File)");
            System.out.printf("\t| %-4s | %-77s |%n", "8", "Tìm kiếm sản phẩm");
            System.out.printf("\t| %-4s | %-77s |%n", "9", "Làm mới màn hình");
            System.out.printf("\t| %-4s | %-77s |%n", "10", "Thoát chương trình quản lý");

            // In dòng kẻ dưới cùng
            System.out.println(
                    "\t========================================================================================");
            System.out.print("\t[Manage] Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    int number = Integer.parseInt(str);
                    if (number >= 1 && number <= 11) {
                        if (number == 1) {
                            this.menuTable();
                            System.out.println("\tEnter để tiếp tục!");
                            str = sc.nextLine();
                        }
                        if (number == 2) {
                            this.addTopping();
                        }
                        if (number == 3) {
                            this.removeATopping();
                        }
                        if (number == 4) {
                            this.modifyTopping();
                        }
                        if (number == 5) {
                            this.toppingList.clear();
                            this.Init();
                        }
                        if (number == 6) {
                            this.writeAll();
                        }
                        if (number == 7) {
                            this.resetList();
                        }
                        if (number == 8) {
                            this.findTopping();
                            System.out.println("\tEnter để tiếp tục!");
                            str = sc.nextLine();
                        }
                        if (number == 9) {
                            Function.clearScreen();
                        }
                        if (number == 10) {
                            System.out.println("\tThoát chương trình thành công !");
                            Function.clearScreen();
                            break;
                        }
                    } else {
                        System.out.println("\tVui lòng chọn trong khoảng 1 đến 11 !");
                    }
                } else {
                    System.out.println("\tVui lòng nhập số !");
                }
            }
        }
    }

    public static void main(String[] args) {
        QLTopping list = new QLTopping();
        list.Init();
        list.menuTable();
    }
}
