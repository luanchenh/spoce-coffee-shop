package HoaDon;

import NhanVien.NhanVienThuNgan;
import NhanVien.Nhanvien;
import NhanVien.QLNhanVien;
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
        File billFile = new File("../File/hoadon.txt");
        try (Scanner sc = new Scanner(billFile)) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] arr = str.split("\\|");
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Phương thức dùng để ghi toàn bộ hóa đơn vào file
    public void writeAll() {
        try (FileWriter writer = new FileWriter("../File/hoadon.txt", false)) {
            for (HoaDon bill : this.billList) {
                // writer.write(bill.makeString()).append("\n");
            }
            writer.flush();
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
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
}
