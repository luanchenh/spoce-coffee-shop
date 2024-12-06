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
            System.out.println("\n\t==========================================================================================");
            // System.out.println("\tChọn loại khách hàng");
            // System.out.println("1. Khách hàng mang đi");
            // System.out.println("2. Khách hàng dùng tại chỗ");
            System.out.printf("\t| %-87s |%n", "Chọn loại khách hàng:");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Khách hàng mang đi");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Khách hàng dùng tại chỗ");
            System.out.println("\t==========================================================================================");
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

            if (Integer.parseInt(str) < 0) {
                System.out.println("\tLựa chọn không được có giá trị âm!");
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
                System.out.println("\tLựa chọn không hợp lệ!");
                continue;
            }

            kh.nhapThongTin();
            this.customerList.add(kh);
            this.writeAll();
            System.out.println("\tThêm khách hàng thành công!");
            break;
        }
    }

    // Phương thức để xóa một khách hàng khỏi Array List
    public void removeCustomer() {
        this.xuatThongTin();
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            System.out.println("\n\t==========================================================================================");
            System.out.printf("\t| %-87s |%n", "Bạn muốn xóa khách hàng theo ID hay theo tên?");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "ID");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Tên khách hàng");
            System.out.println("\t==========================================================================================");
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
                while (true) { 
                    boolean isDone = false;
                    System.out.print("\n\t=> Mời bạn nhập ID khách hàng: ");
                    str = sc.nextLine();
    
                    if (Function.isEmpty(str)) {
                        System.out.println("\tID không được rỗng!");
                        continue;
                    }
    
                    for (KhachHang kh : this.customerList) {
                        if (kh.getCustomerID().equals(str)) {
                            this.customerList.remove(kh);
                            isDone = true;
                            break;
                        }
                    }
    
                    if (!isDone) {
                        System.out.println("\tKhông tìm thấy khách hàng!\n");
                        continue;
                    } else {
                        this.writeAll();
                    }
                    break;
                }
                break;

                case "2":
                while (true) { 
                    boolean isDone = false;
                    System.out.print("\n\t=> Mời bạn nhập tên khách hàng: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tTên khách hàng không được rỗng!");
                        continue;
                    }

                    if (Function.isTrueNumber(str)) {
                        System.out.println("\tTên khách hàng không được là số!");
                        continue;
                    }

                    str = Function.normalizeName(str);

                    for (KhachHang kh : this.customerList) {
                        if (kh.getCustomerName().equals(str)) {
                            this.customerList.remove(kh);
                            isDone = true;
                            break;
                        }
                    }

                    if (!isDone) {
                        System.out.println("\tKhông tìm thấy khách hàng!");
                        continue;
                    } else {
                        this.writeAll();
                    }

                    break;
                }
                break;

                default:
                System.out.println("\tLựa chọn không hợp lệ!");
                continue;
            }

            break;
        }
    }

    // Phương thức để in ra các khách hàng trong mảng
    @Override
    public void xuatThongTin() {
        for (KhachHang kh : this.customerList) {
            kh.xuatThongTin();
        }
    }

    // Hàm để sửa thông tin khách hàng
    public void modifyCustomer() {
        Scanner sc = new Scanner(System.in);
        String str;

        this.xuatThongTin();

        while (true) {
            // System.out.println("\nBạn muốn sửa thông tin khách hàng theo ID hay theo tên?");
            // System.out.println("1. ID");
            // System.out.println("2. Tên khách hàng");
            System.out.println("\n\t==========================================================================================");
            System.out.printf("\t| %-87s |%n", "Bạn muốn sửa thông tin khách hàng theo ID hay theo tên?");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "ID");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Tên khách hàng");
            System.out.println("\t==========================================================================================");
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
                while (true) { 
                    boolean isDone = false;
                    System.out.print("\n\t=> Mời bạn nhập ID khách hàng: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tID không được rỗng!");
                        continue;
                    }

                    for (KhachHang kh : this.customerList) {
                        if (kh.getCustomerID().equals(str)) {
                            kh.suaThongTin();
                            isDone = true;
                            break;
                        }
                    }

                    if (!isDone) {
                        System.out.println("\tKhông tìm thấy khách hàng!\n");
                        continue;
                    } else {
                        this.writeAll();
                    }

                    break;
                }
                break;

                case "2":
                while (true) { 
                    boolean isDone = false;
                    System.out.print("\n\t=> Mời bạn nhập tên khách hàng: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tTên khách hàng không được rỗng!");
                        continue;
                    }

                    if (Function.isTrueNumber(str)) {
                        System.out.println("\tTên khách hàng không được là số!");
                        continue;
                    }

                    str = Function.normalizeName(str);

                    for (KhachHang kh : this.customerList) {
                        if (kh.getCustomerName().equals(str)) {
                            kh.suaThongTin();
                            isDone = true;
                            break;
                        }
                    }

                    if (!isDone) {
                        System.out.println("\tKhông tìm thấy khách hàng!");
                        continue;
                    } else {
                        this.writeAll();
                    }

                    break;
                }
                break;

                default:
                System.out.println("\tLựa chọn không hợp lệ!");
                continue;
            }

            break;
        }
    }


    
    public static void main(String[] args) {
        QLKhachHang ql = new QLKhachHang();
        ql.init();
        ql.removeCustomer();
    }






}
