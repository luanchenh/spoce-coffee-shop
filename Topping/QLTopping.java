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

    public void clearList() {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            // System.out.println("\tBạn có chắc chắn muốn xoá toàn bộ danh sách không ?");
            // System.out.println("\t1. Có");
            // System.out.println("\t2. Không");
            // System.out.print("\tNhập lựa chọn: ");
            System.out.println("\t===================================================================================");
            System.out.printf("\t| %-60s %-18s |%n", "Bạn có chắc chắn muốn xoá toàn bộ danh sách không ?", "");
            System.out.println("\t===================================================================================");
            System.out.printf("\t| %-10s %-68s |%n", "1.", "Có");
            System.out.printf("\t| %-10s %-68s |%n", "2.", "Không");
            System.out.println("\t===================================================================================");
            System.out.print("\t=> Mời nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    int number = Integer.parseInt(str);
                    if (number >= 1 && number <= 2) {
                        if (number == 1) {
                            this.toppingList.clear();
                            System.out.println("\tXoá danh sách thành công !");
                            break;
                        }
                        if (number == 2) {
                            System.out.println("\tHủy bỏ xoá danh sách !");
                            break;
                        }
                    } else {
                        System.out.println("\tVui lòng chọn trong khoảng 1 đến 2 !");
                    }
                } else {
                    System.out.println("\tVui lòng nhập số !");
                }
            }
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
            // System.out.println("\n\tBạn có chắc chắn muốn xoá toàn bộ danh sách không?");
            // System.out.println("\t1. Có");
            // System.out.println("\t2. Không");
            // System.out.print("\t=> Nhập lựa chọn: ");
            System.out.println("\t===================================================================================");
            System.out.printf("\t| %-60s %-18s |%n", "Bạn có chắc chắn muốn xoá toàn bộ danh sách không ?", "");
            System.out.println("\t===================================================================================");
            System.out.printf("\t| %-10s %-68s |%n", "1.", "Có");
            System.out.printf("\t| %-10s %-68s |%n", "2.", "Không");
            System.out.println("\t===================================================================================");
            System.out.print("\t=> Mời nhập lựa chọn: ");
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

    public void toppingFunctionMenu() {
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            Function.clearScreen();
            System.out.println("\t===============================[ Menu chức năng ]===========================");
            System.out.printf("\t| %-4s | %-65s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------|");
            System.out.printf("\t| %-4s | %-65s |%n", "1", "Tìm kiếm topping");
            System.out.printf("\t| %-4s | %-65s |%n", "2", "Quay về menu chính");
            System.out.println("\t============================================================================");
            System.out.print("\t=> Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được trống!");
                System.out.println("\tEnter để tiếp tục!");
                str = sc.nextLine();
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
                System.out.println("\tEnter để tiếp tục!");
                str = sc.nextLine();
                continue;
            }

            switch (str) {
                case "1":
                this.findTopping();
                System.out.println("\tEnter để tiếp tục!");
                str = sc.nextLine();
                break;

                case "2":
                break;

                default:
                System.out.println("\tLựa chọn không hợp lệ!");
                System.out.println("\tEnter để tiếp tục!");
                str = sc.nextLine();
                continue;
            }

            break;
        }
    }

    public void addRemoveModifyMenu() {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            Function.clearScreen();
            System.out.println("\t============================[ Menu thêm/sửa/xóa ]===========================");
            System.out.printf("\t| %-4s | %-65s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------|");
            System.out.printf("\t| %-4s | %-65s |%n", "1", "Thêm một topping (Tự động lưu vào File)");
            System.out.printf("\t| %-4s | %-65s |%n", "2", "Xoá một topping (Tự động load vào File)");
            System.out.printf("\t| %-4s | %-65s |%n", "3", "Sửa thông tin topping");
            System.out.printf("\t| %-4s | %-65s |%n", "4", "Quay lại menu chính");
            System.out.println("\t============================================================================");
            System.out.print("\t=> Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được trống!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
                continue;
            }

            switch (str) {
                case "1":
                    this.addTopping();
                    break;

                case "2":
                    this.removeATopping();
                    break;

                case "3":
                    this.modifyTopping();
                    break;

                case "4":
                    break;

                default:
                    System.out.println("\tLựa chọn không hợp lệ! Hãy thử lại.");
                    System.out.println("\tEnter để tiếp tục!");
                    str = sc.nextLine();
                    continue;
            }
            break;
        }
    }

    public void updateMenu() {
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            Function.clearScreen();
            System.out.println("\t===============================[ Menu cập nhật ]============================");
            System.out.printf("\t| %-4s | %-65s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------|");
            System.out.printf("\t| %-4s | %-65s |%n", "1", "Cập nhật danh sách topping vào File");
            System.out.printf("\t| %-4s | %-65s |%n", "2", "Cập nhật lại danh sách topping từ File");
            System.out.printf("\t| %-4s | %-65s |%n", "3", "Làm mới danh sách topping (Reset dữ liệu)");
            System.out.printf("\t| %-4s | %-65s |%n", "4", "Quay về trang trước");
            System.out.println("\t============================================================================");
            System.out.print("\t=> Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được trống!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
                continue;
            }

            switch (str) {
                case "1":
                this.writeAll();
                break;

                case "2":
                this.Init();
                break;

                case "3":
                this.resetList();
                break;

                case "4":
                break;

                default:
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
                    "\t=================================[ Menu Quản Lý Topping ]===============================");

            // In tiêu đề các cột
            System.out.printf("\t| %-4s | %-77s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------------------|");

            // In danh sách các lựa chọn
            // System.out.printf("\t| %-4s | %-77s |%n", "1", "In danh sách topping");
            // System.out.printf("\t| %-4s | %-77s |%n", "2", "Thêm một topping (Tự động lưu vào File)");
            // System.out.printf("\t| %-4s | %-77s |%n", "3", "Xoá một topping (Tự động load vào File)");
            // System.out.printf("\t| %-4s | %-77s |%n", "4", "Sửa thông tin topping");
            // System.out.printf("\t| %-4s | %-77s |%n", "5", "Cập nhật lại topping vào File từ danh sách");
            // System.out.printf("\t| %-4s | %-77s |%n", "6", "Cập nhật lại topping vào danh sách từ File");
            // System.out.printf("\t| %-4s | %-77s |%n", "7", "Làm mới danh sách topping (Reset dữ liệu nhưng không load vào File)");
            // System.out.printf("\t| %-4s | %-77s |%n", "8", "Tìm kiếm sản phẩm");
            // System.out.printf("\t| %-4s | %-77s |%n", "9", "Làm mới màn hình");
            // System.out.printf("\t| %-4s | %-77s |%n", "10", "Thoát chương trình quản lý");

            System.out.printf("\t| %-4s | %-77s |%n", "1", "In danh sách topping");
            System.out.printf("\t| %-4s | %-77s |%n", "2", "Chức năng về topping");
            System.out.printf("\t| %-4s | %-77s |%n", "3", "Thêm/sửa/xóa thông tin topping");
            System.out.printf("\t| %-4s | %-77s |%n", "4", "Cập nhật và làm mới danh sách topping");
            System.out.printf("\t| %-4s | %-77s |%n", "5", "Làm mới màn hình");
            System.out.printf("\t| %-4s | %-77s |%n", "6", "Thoát chương trình quản lý");

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
                    if (number >= 1 && number <= 6) {
                        if (number == 1) {
                            this.menuTable();
                            System.out.println("\tEnter để tiếp tục!");
                            str = sc.nextLine();
                        }
                        if (number == 2) {
                            this.toppingFunctionMenu();
                        }
                        if (number == 3) {
                            this.addRemoveModifyMenu();
                        }
                        if (number == 4) {
                            this.updateMenu();
                        }
                        if (number == 5) {
                            Function.clearScreen();
                        }
                        if (number == 6) {
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
