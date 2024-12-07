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
                KhachHang kh;
                String str = sc.nextLine();
                String[] arr = str.split("\\|");
                // System.out.println(arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]+" "+arr[4]+" "+arr[5]+" "+arr[6]+" "+arr[7]+" "+arr[8]);
                MemberCard card = null;
                boolean status = false;
                if (arr[3].compareTo("1") == 0) { 
                    card = new MemberCard(arr[4], new Date(arr[5], arr[6], arr[7]), new Date(arr[8], arr[9], arr[10]), new Date(arr[11], arr[12], arr[13]), Integer.parseInt(arr[14]));
                    status = true;
                }

                if (arr[0].compareTo("0") == 0) {
                    kh = new KHTaiCho(arr[1], arr[2], status, card);
                } else {
                    Address address;
                    String[] addressArr;
                    if (status) {
                        addressArr = arr[15].split(",");
                        // System.out.println(address.getHouseNumer() + " " + address.getWardName() + " " + address.getCityName());
                    } else {
                        addressArr = arr[4].split(",");
                        // System.out.println(address.getHouseNumer() + " " + address.getWardName() + " " + address.getCityName());
                        // System.out.println(address.getProvince().getCityCode() + " " + address.getProvince().getCityName());
                    }
                    address = new Address(addressArr[0], addressArr[1], addressArr[2], new Province(addressArr[3], Province.getProvinceNameFromID(addressArr[3])));
                    // System.out.println(address.getHouseNumer() + " " + address.getWardName() + " " + address.getCityName());
                    // System.out.println(address.getProvince().getCityCode() + " " + address.getProvince().getCityName());
                    // System.out.println(card.getCardID()+" "+card.getBirthDay().toString()+" "+card.getStartDate().toString()+" "+card.getEndDate().toString()+ " "+card.getPoint());
                    // System.out.println(arr[1] + " " + arr[2] +" "+ status + " " + card.makeString() + " " + address.toString());
                    kh = new KHMangDi(arr[1], arr[2], status, card, address);
                    // System.out.println("test");
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
                        System.out.println("\tXóa khách hàng thành công!");
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
                        System.out.println("\tXóa khách hàng thành công!");
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
            System.out.println("\n\tBạn muốn sửa thông tin khách hàng theo ID hay theo tên?");
            System.out.println("\t1. ID");
            System.out.println("\t2. Tên khách hàng");
            // System.out.println("\n\t==========================================================================================");
            // System.out.printf("\t| %-87s |%n", "Bạn muốn sửa thông tin khách hàng theo ID hay theo tên?");
            // System.out.printf("\t| %-5s %-81s |%n", "1.", "ID");
            // System.out.printf("\t| %-5s %-81s |%n", "2.", "Tên khách hàng");
            // System.out.println("\t==========================================================================================");
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
                this.customerList.clear();
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

    public void listItem() {
        int dineInCustomer = 0;
        int takeAwayCustomer = 0;
        for (KhachHang kh : this.customerList) {
            if (kh instanceof KHMangDi) {
                takeAwayCustomer++;
            } else if (kh instanceof KHTaiCho) {
                dineInCustomer++;
            }
        }
        System.out.println("\tSố loại khách hàng: ");
        System.out.println("\t1. Khách hàng mang đi: " + takeAwayCustomer);
        System.out.println("\t2. Khách hàng uống tại chỗ: " + dineInCustomer);
    }

    public void findCustomer() {
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) { 
            System.out.println("\tTìm kiếm khách hàng theo: ");
            System.out.println("\t1. Tìm kiếm theo ID");
            System.out.println("\t2. Tìm kiếm theo Tên");
            System.out.println("\t3. Thoát");
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
                    System.out.print("\tNhập ID muốn tìm (bao gồm mã): ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tID nhập vào không được rỗng!");
                        continue;
                    }

                    KhachHang khachhang = null;
                    for (KhachHang kh : this.customerList) {
                        if (kh.getCustomerID().equals(str)) {
                            khachhang = kh;
                            break;
                        }
                    }
    
                    if (khachhang == null) {
                        System.out.println("\tKhông tìm thấy khách hàng nào có ID: " + str);
                    } else {
                        System.out.println("\tKết quả tìm kiếm: ");
                        khachhang.xuatThongTin();
                    }
                    
                    break;
                }
                break;

                case "2":
                while (true) { 
                    System.out.print("\tNhập tên muốn tìm: ");
                    str = sc.nextLine();
    
                    if (Function.isEmpty(str)) {
                        System.out.println("\tTên không được rỗng!");
                        continue;
                    }

                    if (Function.isTrueNumber(str)) {
                        System.out.println("\tTên không được là số!");
                        continue;
                    }

                    KhachHang khachhang = null;
                    for (KhachHang kh : this.customerList) {
                        if (kh.getCustomerName().equalsIgnoreCase(str)) {
                            khachhang = kh;
                            break;
                        }
                    }

                    if (khachhang == null) {
                        System.out.println("\tKhông tìm thấy khách hàng nào có tên: " + str);
                    } else {
                        System.out.println("\tKết quả tìm kiếm: ");
                        khachhang.xuatThongTin();
                    }
                    break;
                }
                break;

                case "3":
                System.out.println("\tThoát tìm kiếm!");
                Function.clearScreen();
                break;

                default:
                System.out.println("\tVui lòng chọn trong khoảng 1 đến 3 !");
                continue;
            }

            break;
        }
    }

    public void menuQLKhachHang() {
        Function.clearScreen();
        this.init(); // Khởi tạo dữ liệu
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            // In tiêu đề
            System.out.println(
                    "\t==============================[ Menu Quản Lý Khách Hàng ]===============================");

            // In tiêu đề các cột
            System.out.printf("\t| %-4s | %-77s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------------------|");

            // In danh sách các lựa chọn
            System.out.printf("\t| %-4s | %-77s |%n", "1", "In danh sách khách hàng");
            System.out.printf("\t| %-4s | %-77s |%n", "2", "Thêm một khách hàng (Tự động lưu vào File)");
            System.out.printf("\t| %-4s | %-77s |%n", "3", "Xoá một khách hàng (Tự động load vào File)");
            System.out.printf("\t| %-4s | %-77s |%n", "4", "Sửa thông tin khách hàng");
            System.out.printf("\t| %-4s | %-77s |%n", "5", "Cập nhật lại khách hàng vào File từ danh sách");
            System.out.printf("\t| %-4s | %-77s |%n", "6", "Cập nhật lại khách hàng vào danh sách từ File");
            System.out.printf("\t| %-4s | %-77s |%n", "7", "Làm mới danh sách khách hàng (Reset dữ liệu nhưng không load vào File)");
            System.out.printf("\t| %-4s | %-77s |%n", "8", "In ra số khách hàng từng loại");
            System.out.printf("\t| %-4s | %-77s |%n", "9", "Tìm kiếm khách hàng");
            System.out.printf("\t| %-4s | %-77s |%n", "10", "Làm mới màn hình");
            System.out.printf("\t| %-4s | %-77s |%n", "11", "Thoát chương trình quản lý");

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
                            this.xuatThongTin();
                        }
                        if (number == 2) {
                            this.addCustomer();
                        }
                        if (number == 3) {
                            this.removeCustomer();
                        }
                        if (number == 4) {
                            this.modifyCustomer();
                        }
                        if (number == 5) {
                            this.init();
                        }
                        if (number == 6) {
                            this.writeAll();;
                        }
                        if (number == 7) {
                            this.resetList();
                        }
                        if (number == 8) {
                            this.listItem();
                        }
                        if (number == 9) {
                            this.findCustomer();
                        }
                        if (number == 10) {
                            Function.clearScreen();
                        }
                        if (number == 11) {
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
        QLKhachHang ql = new QLKhachHang();
        ql.menuQLKhachHang();
    }






}
