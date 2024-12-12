/**
 *
 */
package Topping;
import Utils.Function;
import Utils.INhap;
import Utils.IXuat;
import java.io.File;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Topping implements INhap, IXuat{
    private String id;
    private String name;
    private int price;

    private static int countID = readIDTopping();

    public static int readIDTopping() {
        int result = 0;
        String lastLine = "";
        File toppingFile = new File("./File/topping.txt");
        try(Scanner rd = new Scanner(toppingFile)) {
            while(rd.hasNextLine()) {
                lastLine = rd.nextLine();
            }
        } catch(Exception e) {
            System.out.println("Lỗi: "+ e.getMessage());
        }
        String[] lineSplit = lastLine.split("\\|");
        result = Integer.parseInt(lineSplit[0].substring(2));
        return result;
    }
    public Topping() {
        countID++;
        this.id = "TP" + countID;
        this.name = "";
        this.price = 0;
    }

    public Topping(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter - Setter
    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getPrice() {
        return this.price;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }


    // ========================================[Override Method]========================================
    @Override
    public void xuatThongTin() {
        // System.out.println("\t===========================================================");
        // System.out.println("ID Topping: "+ this.id);
        // System.out.println("Tên Topping: "+ this.name);
        // System.out.println("Giá tiền: "+ Function.formatMoney(this.price + ""));

        System.out.println("\t============================================================");
        System.out.printf("\t| %-20s %-35s |%n", "ID Topping:", this.id);
        System.out.printf("\t| %-20s %-35s |%n", "Tên Topping:", this.name);
        System.out.printf("\t| %-20s %-35s |%n", "Giá Tiền:", Function.formatMoney(this.price + ""));
        System.out.println("\t============================================================");
    }

    @Override
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;
        int number;
        System.out.println("\t[Notice] ID topping hiện tại: " + this.id);
        while (true) {
            System.out.print("\tNhập tên topping: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            }
            else {
                if (Function.isTrueNumber(str)) {
                    System.out.println("\tTên topping không được là số !");
                }
                else {
                    this.name = str;
                    break;
                }
            }
        }
        System.out.println("\t[Notice] Tên topping hiện tại: "+ this.name);
        while (true) {
            System.out.print("\tNhập giá của topping: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            }
            else {
                if (!Function.isTrueNumber(str)) {
                    System.out.println("\tGiá phải là số !");
                }
                else {
                    number = Integer.parseInt(str);
                    if (Function.isTrueNumber(str)) {
                        number = Integer.parseInt(str);
                        this.price = number;
                        break;
                    }
                }
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Cùng tham chiếu
        if (obj == null || getClass() != obj.getClass()) return false; // Kiểm tra null và cùng lớp

        Topping other = (Topping) obj;
        return this.name.equals(other.name) && this.price == other.price && this.id == other.id; // So sánh các thuộc tính
    }

    // ========================================[Function]========================================
    //tạo topping
    public Topping createTopping() {
        Topping tp = new Topping();
        Scanner sc = new Scanner(System.in);
        String str;
        int number;
        while (true) {
            System.out.print("\tNhập ID của topping: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống");
            } else {
                if (Function.isTrueNumber(str)) {
                    tp.setId("TP"+ str);
                    break;
                } else {
                    System.out.println("\tID bạn nhập phải là số !");
                }
            }
        }
        System.out.println("\tID bạn vừa nhập là: "+ tp.getId());
        while (true) {
            System.out.print("\tNhập tên topping: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            }
            else {
                if (Function.isTrueNumber(str)) {
                    System.out.println("\tTên topping không được là số !");
                }
                else {
                    tp.setName(str);;
                    break;
                }
            }
        }
        System.out.println("\t[Notice] Tên topping hiện tại: "+ tp.getName());
        while (true) {
            System.out.print("\tNhập giá của topping: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            }
            else {
                if (!Function.isTrueNumber(str)) {
                    System.out.println("\tGiá phải là số !");
                }
                else {
                    number = Integer.parseInt(str);
                    if (Function.isTrueNumber(str)) {
                        number = Integer.parseInt(str);
                        tp.setPrice(number);
                        break;
                    }
                }
            }
        }
        System.out.println("\t[Notice] Giá của topping hiện tại là: "+ Function.formatMoney(tp.getPrice() + ""));
        return tp;
    }

    // Hàm sửa thông tin
    public void suaThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            System.out.println("\n\tSửa thông tin topping có ID: " + this.id);
            System.out.println("\t1. Tên topping");
            System.out.println("\t2. Giá topping");
            System.out.println("\t3. Quay lại");
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

            switch (Integer.parseInt(str)) {
                case 1:
                while (true) {
                    System.out.print("\t=> Mời nhập tên topping: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tTên topping không được rỗng!");
                        continue;
                    }

                    this.name = str;

                    break;
                }
                break;

                case 2:
                while (true) {
                    System.out.print("\t=> Mời nhập giá topping: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tGiá topping không được rỗng!");
                        continue;
                    }

                    if (!Function.isTrueNumber(str)) {
                        System.out.println("\tGiá topping phải là số!");
                        continue;
                    }

                    this.price = Integer.parseInt(str);
                    break;
                }
                break;

                case 3:
                break;

                default:
                System.out.println("\tLựa chọn không hợp lệ!");
                continue;
            }

            break; // while
        }
    }


    // Tạo chuỗi để lưu file
    public String makeString() {
        return this.id +"|"+ this.name +"|"+ this.price;
    }
    // Hàm để hiện thông tin theo fomat khi in trong menu
    public void menuInfo() {
        //System.out.printf("\t%-5s %-25s %-10s\n", this.id, Function.normalizeName(this.name), Function.formatMoney(this.price + ""));
        System.out.printf("\t| %-10s | %-25s | %-16s |\n", this.id, Function.normalizeName(this.name), Function.formatMoney(this.price + ""));
    }

}
