package ThucDon;
import NuocUong.NuocUong;
import Topping.Topping;
import Utils.*;
import java.util.ArrayList;
public class ThucDon implements INhap, IXuat {
    private ArrayList<NuocUong> danhSachNuocUong;
    private ArrayList<Topping> danhSachTopping;

    public ThucDon() {
        this.danhSachNuocUong = new ArrayList<>();
        this.danhSachTopping = new ArrayList<>();
    }

    // public void nhap() {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.println("Nhập danh sách nước uống:");

    //     System.out.print("Số lượng nước uống: ");
    //     int soLuongNuoc = Integer.parseInt(scanner.nextLine());
    //     for (int i = 0; i < soLuongNuoc; i++) {
    //         NuocUong a = null;
    //     }

    //     System.out.println("Nhập danh sách topping:");

    //     System.out.print("Số lượng topping: ");
    //     int soLuongTopping = Integer.parseInt(scanner.nextLine());
    //     for (int i = 0; i < soLuongTopping; i++) {
    //         Topping a = null;
    //         a.createTopping();
    //         danhSachTopping.add(a);
    //     }
    // }

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

    @Override
    public void nhapThongTin() {
        //
    }

    @Override
    public void xuatThongTin() {
       //
    }
}
