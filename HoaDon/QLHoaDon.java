package HoaDon;

import KhachHang.KhachHang;
import KhachHang.QLKhachHang;
import NhanVien.NhanVienThuNgan;
import NhanVien.Nhanvien;
import NhanVien.QLNhanVien;
import NuocUong.NuocUong;
import NuocUong.QLNuocUong;
import ThucDon.ThucDon;
import Topping.QLTopping;
import Topping.Topping;
import Utils.Date;
import Utils.Function;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


@SuppressWarnings({ "resource", "unused" })
public class QLHoaDon {
    public ArrayList<HoaDon> billList;

    public QLHoaDon() {
        this.billList = new ArrayList<>();
    }

    public QLHoaDon(ArrayList<HoaDon> billList) {
        this.billList = billList;
    }

    // Phương thức để đọc toàn bộ hóa đơn từ File về Array list
    public void Init() {
        QLNhanVien qlNhanVien = new QLNhanVien();
        QLKhachHang qlKhachHang = new QLKhachHang();
        QLNuocUong qlNuocUong = new QLNuocUong();
        QLTopping qlTopping = new QLTopping();
        qlNhanVien.Init();
        qlKhachHang.Init();
        qlNuocUong.Init();
        qlTopping.Init();

        File billFile = new File("../File/hoadon.txt");
        try (Scanner sc = new Scanner(billFile)) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] arr = str.split("\\|");
                NhanVienThuNgan nv = null;
                ThucDon order = new ThucDon();
                for (Nhanvien nhanvien : qlNhanVien.nhanVienList) {
                    if (nhanvien.getMaNhanVien().equals(arr[1])) {
                        nv = (NhanVienThuNgan)nhanvien;
                        break;
                    }
                }
                Date billDate = new Date(arr[2],arr[3],arr[4]);
                KhachHang kh = null;
                for (KhachHang khachHang : qlKhachHang.customerList) {
                    if (khachHang.getCustomerID().equals(arr[6])) {
                        kh = khachHang;
                        break;
                    }
                }

                // ma nuoc,size,tp1;tp2,0;0;0;0
                for (int i=10; i<arr.length; i++) {
                    String[] waterDetailArr = arr[i].split(",");
                    String[] toppingList = waterDetailArr[2].split(";");
                    String[] waterStatus = waterDetailArr[3].split(";");
                    
                    NuocUong nu = null;
                    for (NuocUong nuocUong : qlNuocUong.getWaterList()) {
                        if (nuocUong.getId().equals(waterDetailArr[0])) {
                            nu = nuocUong;
                        }
                    }
                    order.getDanhSachNuocUong().add(nu); // nuoc

                    order.getSize().add(waterDetailArr[1]); // size

                    ArrayList<Topping> tpList = new ArrayList<>();
                    for (String s : toppingList) {
                        for (Topping tp : qlTopping.getToppingList()) {
                            if (tp.getId().equals(s)) {
                                tpList.add(tp);
                                break;
                            }
                        }
                    }
                    order.getDanhSachTopping().add(tpList);

                    ArrayList<Boolean> ttList = new ArrayList<>();
                    for (String s : waterStatus) {
                        Boolean flag = s.equals("1");
                        ttList.add(flag);
                    }
                    order.getTrangThaiNuocUong().add(ttList);
                }

                HoaDon hd = new HoaDon(arr[0], kh, nv, billDate, order, arr[5], Double.parseDouble(arr[7]), Double.parseDouble(arr[8]));
                this.billList.add(hd);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Phương thức dùng để ghi toàn bộ hóa đơn vào file
    public void writeAll() {
        try (FileWriter writer = new FileWriter("../File/hoadon.txt", false)) {
            for (HoaDon bill : this.billList) {
                writer.write(bill.makeString() + "\n");
            }
            writer.flush();
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void printBill() {
        System.out.println("\tThông tin toàn bộ hóa đơn:");
        for (HoaDon hd : this.billList) {
            hd.xuatThongTin();
            System.out.println();
        }
    }

    // Phương thức để thống kê doanh thu, số lượng đơn hàng, số lượng mỗi sản phẩm toàn thời gian
    public void thongKeDoanhThuAll() {
        System.out.println("\tThống kê:");
        System.out.printf("\t%-10s %s%n", "", "Tổng doanh thu: ");
        System.out.printf("\t%-10s %s%n", "", "Tổng đơn hàng bán được: " + this.billList.size());
        System.out.printf("\t%-10s %s%n", "", "Số lượng mỗi sản phẩm bán được: ");
    }

    // Phương thức để thống kê doanh thu, số lượng đơn hàng, số lượng mỗi sản phẩm theo thời gian
    public void thongKeDoanhThuTheoThoiGian() {
        Scanner sc = new Scanner(System.in);
        String str;
        Date startDate = new Date();
        Date endDate = new Date();

        while (true) {
            while (true) {
                while (true) {
                    System.out.print("\n\t=> Nhập ngày bắt đầu: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tNgày bắt đầu không được rỗng!");
                        continue;
                    }

                    if (!Function.isTrueNumber(str)) {
                        System.out.println("\tNgày bắt đầu phải là số!");
                        continue;
                    }

                    startDate.setDay(str);
                    break;
                }

                while (true) {
                    System.out.print("\n\t=> Nhập tháng bắt đầu: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tTháng bắt đầu không được rỗng!");
                        continue;
                    }

                    if (!Function.isTrueNumber(str)) {
                        System.out.println("\tTháng bắt đầu phải là số!");
                        continue;
                    }

                    startDate.setMonth(str);
                    break;
                }

                while (true) {
                    System.out.print("\n\t=> Nhập năm bắt đầu: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tNăm bắt đầu không được rỗng!");
                        continue;
                    }

                    if (!Function.isTrueNumber(str)) {
                        System.out.println("\tNăm bắt đầu phải là số!");
                        continue;
                    }

                    startDate.setYear(str);
                    break;
                }

                if (startDate.isValidDate()) {
                    break;
                } else {
                    System.out.println("\tThời gian bắt đầu không hợp lệ!");
                }
            }

            while (true) {
                while (true) {
                    System.out.print("\n\t=> Nhập ngày kết thúc: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tNgày kết thúc không được rỗng!");
                        continue;
                    }

                    if (!Function.isTrueNumber(str)) {
                        System.out.println("\tNgày kết thúc phải là số!");
                        continue;
                    }

                    endDate.setDay(str);
                    break;
                }

                while (true) {
                    System.out.print("\n\t=> Nhập tháng kết thúc: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tTháng kết thúc không được rỗng!");
                        continue;
                    }

                    if (!Function.isTrueNumber(str)) {
                        System.out.println("\tTháng kết thúc phải là số!");
                        continue;
                    }

                    endDate.setMonth(str);
                    break;
                }

                while (true) {
                    System.out.print("\n\t=> Nhập năm kết thúc: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tNăm kết thúc không được rỗng!");
                        continue;
                    }

                    if (!Function.isTrueNumber(str)) {
                        System.out.println("\tNăm kết thúc phải là số!");
                        continue;
                    }

                    endDate.setYear(str);
                    break;
                }

                if (endDate.isValidDate()) {
                    break;
                } else {
                    System.out.println("\tThời gian kết thúc không hợp lệ!");
                }
            }
        }
    }

    // Phương thức để in ra tổng số lượng đơn hàng bán được toàn thời gian
    public void inRaSoLuongDonHangBanDuoc() {
        System.out.println("\tTổng số lượng đơn hàng bán được: " + this.billList.size());
    }

    // Phương thức để in ra nhân viên bán được nhiều đơn hàng nhất toàn thời gian
    public void inRaNhanVienBanDuocNhieuDonNhat() {
        QLNhanVien ql = new QLNhanVien();
        NhanVienThuNgan nv = null;

        // tìm ra nhân viên thu ngân đầu tiên trong mảng
        for (Nhanvien nhanvien : ql.nhanVienList) {
            if (nhanvien instanceof NhanVienThuNgan) {
                nv = (NhanVienThuNgan)nhanvien;
                break;
            }
        }

        // duyệt hết mảng để tìm ra nhân viên thu ngân có nhiều đơn hàng nhất
        for (Nhanvien nhanvien : ql.nhanVienList) {
            if (nhanvien instanceof NhanVienThuNgan && ((NhanVienThuNgan)nhanvien).getSoBillDaXuLy() > nv.getSoBillDaXuLy()) {
                nv = (NhanVienThuNgan)nhanvien;
            }
        }

        System.out.println("\tNhân viên bán được nhiều đơn nhất:");
        System.out.println("\t========================================================================================================");
        System.out.printf("\t| %-20s %-80s |%n", "Mã nhân viên:", nv.getMaNhanVien());
        System.out.printf("\t| %-20s %-80s |%n", "Tên nhân viên:", nv.getTenNhanVien());
        System.out.printf("\t| %-20s %-80s |%n", "Số đơn hàng bán được:", nv.getSoBillDaXuLy());
        System.out.println("\t========================================================================================================");
    }

    // Phương thức để in ra trạng thái của các đơn hàng
    public void inRaTungTrangThaiHoaDon() {
        System.out.println("\t==================================================");
        System.out.printf("\t| %-10s %-10s %20s |%n", "Mã hóa đơn", "Ngày lập đơn", "Trạng thái");
        for (HoaDon hd : this.billList) {
            hd.inRaTrangThai();
        }
        System.out.println("\t==================================================");
    }

    // Phương thức để in ra số lượng từng sản phẩm bán được
    public void inRaSoLuongTungMatHangBanDuoc() {
        //
    }

    public void menuQLHoaDon() {
        Function.clearScreen();
        this.Init();
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            Function.clearScreen();
            // In tiêu đề
            System.out.println("\t==================================[ Menu Quản Lý Hóa Đơn ]==================================");

            // In tiêu đề các cột
            System.out.printf("\t| %-4s | %-77s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------------------|");

            // In danh sách các lựa chọn
            System.out.printf("\t| %-4s | %-77s |%n", "1", "In danh sách hóa đơn");
            System.out.printf("\t| %-4s | %-77s |%n", "2", "Thống kê toàn bộ doanh thu");
            System.out.printf("\t| %-4s | %-77s |%n", "3", "Thống kê doanh thu theo thời gian");
            System.out.printf("\t| %-4s | %-77s |%n", "4", "In ra số lượng đơn hàng đã bán được");
            System.out.printf("\t| %-4s | %-77s |%n", "5", "In ra nhân viên bán được nhiều đơn nhất");
            System.out.printf("\t| %-4s | %-77s |%n", "6", "In ra trạng thái từng hóa đơn");
            System.out.printf("\t| %-4s | %-77s |%n", "7", "In ra số lượng từng mặt hàng bán ");
            System.out.printf("\t| %-4s | %-77s |%n", "8", "Chuyển trạng thái hóa đơn");
            System.out.printf("\t| %-4s | %-77s |%n", "9", "Cập nhật lại hóa đơn vào File từ danh sách");
            System.out.printf("\t| %-4s | %-77s |%n", "10", "Cập nhật lại hóa đơn vào danh sách từ File");
            System.out.printf("\t| %-4s | %-77s |%n", "11", "Làm mới danh sách hóa đơn (Reset dữ liệu nhưng không load vào File)");
            System.out.printf("\t| %-4s | %-77s |%n", "13", "Tìm kiếm hóa đơn");
            System.out.printf("\t| %-4s | %-77s |%n", "14", "Làm mới màn hình");
            System.out.printf("\t| %-4s | %-77s |%n", "115", "Thoát chương trình quản lý");

            // In dòng kẻ dưới cùng
            System.out.println("\t========================================================================================");
            System.out.print("\t[Manage] Nhập lựa chọn: ");
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
                this.printBill();
                System.out.println("\tEnter để tiếp tục!");
                str = sc.nextLine();
                continue;

                case "2":
                this.thongKeDoanhThuAll();
                System.out.println("\tEnter để tiếp tục!");
                str = sc.nextLine();
                continue;

                case "3":
                //this.removeTable();
                continue;

                case "4":
                //this.modifyTable();
                continue;

                case "5":
                //this.writeAll();
                continue;

                case "6":
                //this.Init();
                continue;

                case "7":
                //this.resetList();
                continue;

                case "8":
                //this.countTable();
                System.out.println("\tEnter để tiếp tục!");
                str = sc.nextLine();
                continue;

                case "9":
                //this.findTable();
                System.out.println("\tEnter để tiếp tục!");
                str = sc.nextLine();
                continue;

                case "10":
                Function.clearScreen();
                continue;

                case "11":
                Function.clearScreen();
                break;

                default:
                System.out.println("\tLựa chọn không hợp lệ!");
                continue;
            }
            break;
        }
    }
}
