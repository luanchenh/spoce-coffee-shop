package KhachHang;

import java.util.ArrayList;
import java.util.Scanner;
import Utils.Function;
import Utils.IXuat;

public class QLKhachHang implements IXuat {
    ArrayList<KhachHang> customerList; // Array List để lưu các khách hàng

    // Phương thức khởi tạo phi tham số sẽ cấp phát bộ nhớ cho ArrayList
    public QLKhachHang() {
        this.customerList = new ArrayList<>();
    }

    // Phương thức khởi tạo với tham số là một Array List
    public QLKhachHang(ArrayList<KhachHang> customerList) {
        this.customerList = customerList;
    }

    // Phương thức để thêm một khách hàng vào Array List
    public boolean addCustomer() {
        Scanner sc = new Scanner(System.in);
        String str;
        KhachHang kh = null;

        while (true) { 
            System.out.println("Chọn loại khách hàng");
            System.out.println("1. Khách hàng mang đi");
            System.out.println("2. Khách hàng dùng tại chỗ");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Lựa chọn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("Lựa chọn phải là số!");
                continue;
            }

            if (Integer.parseInt(str) < 0) {
                System.out.println("Lựa chọn không được có giá trị âm!");
                continue;
            }

            switch (Integer.parseInt(str)) {
                case 1:
                kh = new KHMangDi();
                break;

                case 2:
                kh = new KHTaiCho();
                break;

                default:
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            kh.nhapThongTin();
            this.customerList.add(kh);
            break;
        }
        return true;
    }

    // Phương thức để xóa một khách hàng khỏi Array List
    public boolean removeCustomer(KhachHang customerToBeRemoved) {
        for (KhachHang kh : this.customerList) {
            if (kh.getCustomerID().compareTo(customerToBeRemoved.getCustomerID()) == 0) {
                this.customerList.remove(kh);
                return true;
            }
        }
        return false;
    }

    // Phương thức để in ra các khách hàng trong mảng
    @Override
    public void xuatThongTin() {
        for (KhachHang kh : this.customerList) {
            kh.xuatThongTin();
        }
    }

    public static void main(String[] args) {
        QLKhachHang ql = new QLKhachHang();
        ql.addCustomer();
        ql.customerList.get(0).suaThongTin();
        ql.xuatThongTin();
    }






}
