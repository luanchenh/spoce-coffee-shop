package ThucDon;
import java.util.ArrayList;
import java.util.Scanner;
public class ThucDon implements Inhap, Ixuat {
    private ArrayList<NuocUong> danhSachNuocUong;
    private ArrayList<Topping> danhSachTopping;

    public ThucDon() {
        this.danhSachNuocUong = new ArrayList<>();
        this.danhSachTopping = new ArrayList<>();
    }


    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập danh sách nước uống:");

        System.out.print("Số lượng nước uống: ");
        int soLuongNuoc = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < soLuongNuoc; i++) {
            System.out.print("Tên nước uống " + (i + 1) + ": ");
            String tenNuoc = scanner.nextLine();
            System.out.print("Giá nước uống " + (i + 1) + ": ");
            double giaNuoc = Double.parseDouble(scanner.nextLine());
            danhSachNuocUong.add(new NuocUong(tenNuoc, giaNuoc));
        }

        System.out.println("Nhập danh sách topping:");

        System.out.print("Số lượng topping: ");
        int soLuongTopping = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < soLuongTopping; i++) {
            System.out.print("Tên topping " + (i + 1) + ": ");
            String tenTopping = scanner.nextLine();
            System.out.print("Giá topping " + (i + 1) + ": ");
            double giaTopping = Double.parseDouble(scanner.nextLine());
            danhSachTopping.add(new Topping(tenTopping, giaTopping));
        }
    }

    public void xuat() {
        System.out.println("=== Thực đơn ===");
        System.out.println("Danh sách nước uống:");
        for (NuocUong nuoc : danhSachNuocUong) {
            System.out.println(nuoc);
        }

        System.out.println("Danh sách topping:");
        for (Topping topping : danhSachTopping) {
            System.out.println(topping);
        }
    }

    public void themNuocUong(NuocUong nuoc) {
        danhSachNuocUong.add(nuoc);
    }

    public void themTopping(Topping topping) {
        danhSachTopping.add(topping);
    }

    public ArrayList<NuocUong> getDanhSachNuocUong() {
        return danhSachNuocUong;
    }

    public ArrayList<Topping> getDanhSachTopping() {
        return danhSachTopping;
    }
}
