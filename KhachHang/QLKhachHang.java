package KhachHang;

import Utils.Address;
import Utils.Date;
import Utils.Function;
import Utils.IXuat;
import Utils.Province;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class QLKhachHang implements IXuat {
    public ArrayList<KhachHang> customerList; // Array List để lưu các khách hàng

    // Phương thức khởi tạo phi tham số sẽ cấp phát bộ nhớ cho ArrayList
    public QLKhachHang() {
        this.customerList = new ArrayList<>();
    }

    // Phương thức khởi tạo với tham số là một Array List
    public QLKhachHang(ArrayList<KhachHang> customerList) {
        this.customerList = customerList;
    }

    // Phương thức để nhập dữ liệu từ file vào Array List
    public void init() {
        File customerFile = new File("../File/customer.txt");
        try (Scanner sc = new Scanner(customerFile)) {
            while (sc.hasNextLine()) {
                KhachHang kh = null;
                String str = sc.nextLine();
                String[] arr = str.split("\\|");
                MemberCard card = null;
                boolean status = false;
                if (arr[3].equals("1")) {
                    card = new MemberCard(arr[4], new Date(arr[5], arr[6], arr[7]), new Date(arr[8], arr[9], arr[10]), new Date(arr[11], arr[12], arr[13]), Integer.parseInt(arr[14]));
                    status = true;
                }

                if (arr[0].equals("0")) {
                    kh = new KHTaiCho(arr[1], arr[2], status, card);
                } else {
                    Address address;
                    if (status) {
                        address = new Address(arr[15], arr[16], arr[17], new Province(arr[18], arr[19]));
                    } else {
                        address = new Address(arr[4], arr[5], arr[6], new Province(arr[7], arr[8]));
                    }
                    kh = new KHMangDi(arr[1], arr[2], status, card, address);
                }

                this.customerList.add(kh);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Phương thức để ghi dữ liệu từ mảng vào file
    public void writeAll() {
        try (FileWriter writer = new FileWriter("../File/customer.txt", false)) {
            for (KhachHang kh : this.customerList) {
                writer.write(kh.makeString() + "\n");
            }
            writer.flush();
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Phương thức để thêm một khách hàng vào Array List
    public void addCustomer() {
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
            System.out.println("\n===== Thêm khách hàng thành công! =====\n");
            break;
        }
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

    // public void modifyCustomer() {
    //     Scanner sc = new Scanner(System.in);

    // }
    public static void main(String[] args) {
        QLKhachHang ql = new QLKhachHang();
        ql.init();
        ql.addCustomer();
        ql.writeAll();
        ql.xuatThongTin();
    }






}
