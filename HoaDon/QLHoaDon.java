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
import Utils.CreateTXT;
import Utils.Date;
import Utils.Function;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
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

    public ArrayList<HoaDon> getBillList() {
        return billList;
    }

    public void setBillList(ArrayList<HoaDon> billList) {
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
                        nv = (NhanVienThuNgan) nhanvien;
                        break;
                    }
                }
                Date billDate = new Date(arr[2], arr[3], arr[4]);
                KhachHang kh = null;
                for (KhachHang khachHang : qlKhachHang.customerList) {
                    if (khachHang.getCustomerID().equals(arr[6])) {
                        kh = khachHang;
                        break;
                    }
                }

                // ma nuoc,size,tp1;tp2,0;0;0;0
                for (int i = 10; i < arr.length; i++) {
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

                    ArrayList<String> ttList = new ArrayList<>();
                    for (String s : waterStatus) {
                        String statusStr = new String();
                        if (s.equals("0")) {
                            statusStr = "Không";
                        } else if (s.equals("1")) {
                            statusStr = "Ít";
                        } else if (s.equals("2")) {
                            statusStr = "Bình thường";
                        } else if (s.equals("3")) {
                            statusStr = "Nhiều";
                        }
                        ttList.add(statusStr);
                    }
                    order.getTrangThaiNuocUong().add(ttList);
                }

                HoaDon hd = new HoaDon(arr[0], kh, nv, billDate, order, arr[5], Double.parseDouble(arr[7]),
                        Double.parseDouble(arr[8]));
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
            System.out.println("\n");
        }
    }

    // Phương thức để thống kê doanh thu, số lượng đơn hàng, số lượng mỗi sản phẩm
    // toàn thời gian
    public void thongKeDoanhThuAll() {
        double tongDoanhThu = 0;
        for (HoaDon hd : this.billList) {
            tongDoanhThu += hd.getTongTien();
        }
        Function.clearScreen();
        System.out.println(
                "\t======================================================================================================");
        System.out.println(
                "\t|                                  Thống Kê Toàn Bộ Doanh Thu                                        |");
        // System.out.printf("\t| %-10s %-40s|%n", "", "Tổng doanh thu: ");
        // System.out.printf("\t%-10s %s%n", "", "Tổng đơn hàng bán được: " +
        // this.billList.size());
        System.out.printf("\t| %-30s %-67s |%n", "Tổng đơn hàng bán được:", this.billList.size());
        System.out.printf("\t| %-30s %-67s |%n", "Tổng doanh thu:", Function.formatMoney((int) tongDoanhThu + ""));
        System.out.println(
                "\t======================================================================================================");

        QLNuocUong qlNuocUong = new QLNuocUong();
        this.billList.clear();
        this.Init();
        qlNuocUong.Init();

        System.out.println(
                "\t|                           Tổng số lượng nước đã bán ra theo mặt hàng                               |");
        System.out.println(
                "\t======================================================================================================");
        System.out.printf("\t| %-16s | %-56s | %-20s |%n", "Mã nước", "Tên nước", "Số lượng");
        for (NuocUong nu : qlNuocUong.getWaterList()) {
            int count = 0;
            for (HoaDon hd : this.billList) {
                for (NuocUong nuHoaDon : hd.getThucDon().getDanhSachNuocUong()) {
                    if (nuHoaDon.getId().equals(nu.getId())) {
                        count++;
                    }
                }
            }
            System.out.printf("\t| %-16s | %-56s | %-20s |%n", nu.getId(), nu.getName(), count);
        }
        System.out.println(
                "\t======================================================================================================");
    }

    // Phương thức để thống kê doanh thu, số lượng đơn hàng, số lượng mỗi sản phẩm
    // theo thời gian
    public void thongKeDoanhThuTheoThang() {
        Scanner sc = new Scanner(System.in);
        String str;
        int monthSelect;
        int yearSelect;

        while (true) {
            while (true) {
                Function.clearScreen();
                System.out.print("\t=> Mời nhập tháng muốn bạn muốn xem thống kê: ");
                str = sc.nextLine();

                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống!");
                    continue;
                }

                if (!Function.isTrueNumber(str)) {
                    System.out.println("\tVui lòng nhập số!");
                    continue;
                }

                monthSelect = Integer.parseInt(str);
                if (!(monthSelect >= 1 && monthSelect <= 12)) {
                    System.out.println("\tVui lòng nhập từ 1 đến 12");
                    continue;
                }
                break;
            }

            while (true) {
                Function.clearScreen();
                System.out.print("\t=> Mời nhập năm muốn bạn muốn xem thống kê: ");
                str = sc.nextLine();

                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống!");
                    continue;
                }

                if (!Function.isTrueNumber(str)) {
                    System.out.println("\tVui lòng nhập số!");
                    continue;
                }

                yearSelect = Integer.parseInt(str);
                if (yearSelect < 0) {
                    System.out.println("\tNăm không được có giá trị âm!");
                    continue;
                }
                break;
            }

            double tongDoanhThu = 0;
            int tongHoadon = 0;
            for (HoaDon hd : this.billList) {
                if (hd.checkMonthAndYear(monthSelect, yearSelect)) {
                    tongDoanhThu += hd.getTongTien();
                    tongHoadon++;
                }
            }
            Function.clearScreen();
            if (tongHoadon == 0) {
                System.out.println("\tKhông có hóa đơn nào trong tháng " + monthSelect + " năm " + yearSelect + "!");
            } else {
                Function.clearScreen();
                System.out.println(
                        "\t======================================================================================================");
                System.out.println(
                        "\t|                                   Thống Kê Doanh Thu Theo Tháng                                    |");
                System.out.println(
                        "\t|====================================================================================================|");
                System.out.printf("\t| %-30s %-67s |%n", "Thời gian thống kê:", monthSelect + "/" + yearSelect);
                System.out.printf("\t| %-30s %-67s |%n", "Tổng đơn hàng bán được:", tongHoadon);
                System.out.printf("\t| %-30s %-67s |%n", "Tổng doanh thu:",
                        Function.formatMoney((int) tongDoanhThu + ""));
                System.out.println(
                        "\t======================================================================================================");
                QLNuocUong qlNuocUong = new QLNuocUong();
                this.billList.clear();
                this.Init();
                qlNuocUong.Init();

                System.out.println(
                        "\t|                           Tổng số lượng nước đã bán ra theo mặt hàng                               |");
                System.out.println(
                        "\t======================================================================================================");
                System.out.printf("\t| %-16s | %-56s | %-20s |%n", "Mã nước", "Tên nước", "Số lượng");
                for (NuocUong nu : qlNuocUong.getWaterList()) {
                    int count = 0;
                    for (HoaDon hd : this.billList) {
                        for (NuocUong nuHoaDon : hd.getThucDon().getDanhSachNuocUong()) {
                            if (nuHoaDon.getId().equals(nu.getId()) && hd.checkMonthAndYear(monthSelect, yearSelect)) {
                                count++;
                            }
                        }
                    }
                    System.out.printf("\t| %-16s | %-56s | %-20s |%n", nu.getId(), nu.getName(), count);
                }
                System.out.println(
                        "\t======================================================================================================");
            }
            break;
        }
    }

    public void printHoaDon() {
        this.Init();
        Scanner sc = new Scanner(System.in);
        String str;
        String valString;
        while (true) {
            Function.clearScreen();
            System.out.println("\t+------------------------------+----------+------------------------------+");
            System.out.println("\t|                         CHƯƠNG TRÌNH IN HOÁ ĐƠN                        |");
            System.out.println("\t+------------------------------+----------+------------------------------+");
            System.out.println("\t| 1. In hoá đơn theo mã                                                  |");
            System.out.println("\t| 2. In toàn bộ hoá đơn                                                  |");
            System.out.println("\t| 3. Quay lại                                                            |");
            System.out.println("\t+------------------------------------------------------------------------+");
            System.out.print("\tMời bạn chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống!");
                continue;
            }
            if (!Function.isTrueNumber(str)) {
                System.out.println("\tVui lòng nhập số!");
                continue;
            }
            switch (str) {
                case "1":
                    while (true) {
                        System.out.print("\tNhập mã hoá đơn muốn in: ");
                        valString = sc.nextLine();
                        if (Function.isEmpty(valString)) {
                            System.out.println("\tVui lòng không để trống!");
                            continue;
                        }
                        else {
                            boolean isExist = false;
                            for (HoaDon hd : this.billList) {
                                if (hd.getMaHoaDon().equals(valString)) {
                                    CreateTXT.createTextFile(hd);
                                    System.out.println("\tHoá đơn đã được in!");
                                    System.out.print("\tEnter để tiếp tục...");
                                    sc.nextLine();
                                    isExist = true;
                                    break;
                                }
                            }
                            if (!isExist) {
                                System.out.println("\tKhông tìm thấy hoá đơn có mã " + valString + "!");
                                System.out.print("\tEnter để tiếp tục...");
                                sc.nextLine();
                            }
                            break;
                        }
                    }
                    break;
                case "2":
                    for (HoaDon hd : this.billList) {
                        CreateTXT.createTextFile(hd);
                    }
                    break;
                case "3":
                    return;
                default:
                    System.out.println("\tVui lòng chọn từ 1 đến 3!");
                    break;
            }
        }
    }

    public void thongKeDoanhThuTrongMotNgay() {
        Scanner sc = new Scanner(System.in);
        String str;
        int ngay, thang, nam;

        // Xác định ngày hiện tại làm mặc định
        LocalDate today = LocalDate.now();
        ngay = today.getDayOfMonth();
        thang = today.getMonthValue();
        nam = today.getYear();
        Function.clearScreen();
        System.out.println("\n\t=== Thống kê doanh thu trong một ngày ===");

        // Nhập ngày nếu muốn
        System.out.print("\t=> Bạn có muốn nhập ngày cụ thể không? (y/n): ");
        String choice = sc.nextLine().trim();

        if (choice.equalsIgnoreCase("y")) {
            // Nhập ngày
            while (true) {
                System.out.print("\t=> Mời nhập ngày: ");
                str = sc.nextLine();

                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống!");
                    continue;
                }

                if (!Function.isTrueNumber(str)) {
                    System.out.println("\tVui lòng nhập số hợp lệ!");
                    continue;
                }

                ngay = Integer.parseInt(str);
                if (!(ngay >= 1 && ngay <= 31)) {
                    System.out.println("\tVui lòng nhập ngày từ 1 đến 31!");
                    continue;
                }
                break;
            }

            // Nhập tháng
            while (true) {
                System.out.print("\t=> Mời nhập tháng: ");
                str = sc.nextLine();

                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống!");
                    continue;
                }

                if (!Function.isTrueNumber(str)) {
                    System.out.println("\tVui lòng nhập số hợp lệ!");
                    continue;
                }

                thang = Integer.parseInt(str);
                if (!(thang >= 1 && thang <= 12)) {
                    System.out.println("\tVui lòng nhập tháng từ 1 đến 12!");
                    continue;
                }
                break;
            }

            // Nhập năm
            while (true) {
                System.out.print("\t=> Mời nhập năm: ");
                str = sc.nextLine();

                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống!");
                    continue;
                }

                if (!Function.isTrueNumber(str)) {
                    System.out.println("\tVui lòng nhập số hợp lệ!");
                    continue;
                }

                nam = Integer.parseInt(str);
                if (nam < 0) {
                    System.out.println("\tNăm không được có giá trị âm!");
                    continue;
                }
                break;
            }
        }

        double tongDoanhThu = 0;
        int tongHoaDon = 0;

        for (HoaDon hd : this.billList) {
            if (hd.checkDayAndMonthAndYear(ngay, thang, nam)) {
                tongDoanhThu += hd.getTongTien();
                tongHoaDon++;
            }
        }

        if (tongHoaDon == 0) {
            System.out.println("\tKhông có hóa đơn nào trong ngày " + ngay + " tháng " + thang + " năm " + nam + "!");
        } else {
            System.out.println(
                    "\t======================================================================================================");
            System.out.println(
                    "\t|                                   Thống Kê Doanh Thu Theo Ngày                                     |");
            System.out.println(
                    "\t|====================================================================================================|");
            System.out.printf("\t| %-30s %-67s |%n", "Thời gian thống kê:", ngay + "/" + thang + "/" + nam);
            System.out.printf("\t| %-30s %-67s |%n", "Tổng đơn hàng bán được:", tongHoaDon);
            System.out.printf("\t| %-30s %-67s |%n", "Tổng doanh thu:", Function.formatMoney((int) tongDoanhThu + ""));
            System.out.println(
                    "\t======================================================================================================");
            QLNuocUong qlNuocUong = new QLNuocUong();
            this.billList.clear();
            this.Init();
            qlNuocUong.Init();

            System.out.println(
                    "\t|                           Tổng số lượng nước đã bán ra theo mặt hàng                               |");
            System.out.println(
                    "\t======================================================================================================");
            System.out.printf("\t| %-16s | %-56s | %-20s |%n", "Mã nước", "Tên nước", "Số lượng");
            for (NuocUong nu : qlNuocUong.getWaterList()) {
                int count = 0;
                for (HoaDon hd : this.billList) {
                    if (hd.checkDayAndMonthAndYear(ngay, thang, nam)) {
                        for (NuocUong nuHoaDon : hd.getThucDon().getDanhSachNuocUong()) {
                            if (nuHoaDon.getId().equals(nu.getId())) {
                                count++;
                            }
                        }
                    }
                }
                System.out.printf("\t| %-16s | %-56s | %-20s |%n", nu.getId(), nu.getName(), count);
            }
            System.out.println(
                    "\t======================================================================================================");
        }
    }

    // thống kê doanh thu trong 1 tuần
    public void thongKeDoanhThuTrongMotTuan() {
        Scanner sc = new Scanner(System.in);
        String str;
        String ngay, thang, nam;
        System.out.println("\n=== Thống kê doanh thu trong một tuần ===");

        // Nhập ngày
        while (true) {
            System.out.print("\t=> Mời nhập ngày: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tVui lòng nhập số hợp lệ!");
                continue;
            }

            ngay = str;
            if (!(Integer.parseInt(ngay) >= 1 && Integer.parseInt(ngay) <= 31)) {
                System.out.println("\tVui lòng nhập ngày từ 1 đến 31!");
                continue;
            }
            break;
        }

        // Nhập tháng
        while (true) {
            System.out.print("\t=> Mời nhập tháng: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tVui lòng nhập số hợp lệ!");
                continue;
            }

            thang = str;
            if (!(Integer.parseInt(thang) >= 1 && Integer.parseInt(thang) <= 12)) {
                System.out.println("\tVui lòng nhập tháng từ 1 đến 12!");
                continue;
            }
            break;
        }

        // Nhập năm
        while (true) {
            System.out.print("\t=> Mời nhập năm: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tVui lòng nhập số hợp lệ!");
                continue;
            }

            nam = str;
            if (Integer.parseInt(nam) < 0) {
                System.out.println("\tNăm không được có giá trị âm!");
                continue;
            }
            break;
        }

        // Tính ngày bắt đầu và ngày kết thúc
        Date startDate = new Date(ngay, thang, nam);
        Date endDate = startDate.addDays(6); // Tính 7 ngày sau (0-index)

        double tongDoanhThu = 0;
        int tongHoaDon = 0;

        // Duyệt hóa đơn
        for (HoaDon hd : this.billList) {
            // LocalDate invoiceDate =
            // LocalDate.of(Integer.parseInt(hd.getNgayTaoHoaDon().getYear()),
            // Integer.parseInt(hd.getNgayTaoHoaDon().getMonth()),
            // Integer.parseInt(hd.getNgayTaoHoaDon().getDay()));

            // LocalDate localStartDate =
            // LocalDate.of(Integer.parseInt(startDate.getYear()),
            // Integer.parseInt(startDate.getMonth()),
            // Integer.parseInt(startDate.getDay()));
            // LocalDate localEndDate = LocalDate.of(Integer.parseInt(endDate.getYear()),
            // Integer.parseInt(endDate.getMonth()), Integer.parseInt(endDate.getDay()));

            // if ((invoiceDate.isEqual(localStartDate) ||
            // invoiceDate.isAfter(localStartDate)) &&
            // invoiceDate.isBefore(localEndDate.plusDays(1))) {
            // tongDoanhThu += hd.getTongTien();
            // tongHoaDon++;
            // }

            System.out.println(hd.getNgayTaoHoaDon());

        }

        if (tongHoaDon == 0) {
            System.out.println("\tKhông có hóa đơn nào từ " + startDate + " đến " + endDate + "!");
        } else {
            System.out.println(
                    "\t======================================================================================================");
            System.out.println(
                    "\t|                                   Thống Kê Doanh Thu Theo Tuần                                     |");
            System.out.println(
                    "\t|====================================================================================================|");
            System.out.printf("\t| %-30s %-67s |%n", "Thời gian thống kê:", startDate + " - " + endDate);
            System.out.printf("\t| %-30s %-67s |%n", "Tổng đơn hàng bán được:", tongHoaDon);
            System.out.printf("\t| %-30s %-67s |%n", "Tổng doanh thu:", Function.formatMoney((int) tongDoanhThu + ""));
            System.out.println(
                    "\t======================================================================================================");
        }
    }

    ////////////////
    ///
    public void thongKeDoanhThuTheoThoiGian() {
        Scanner sc = new Scanner(System.in);
        while (true) { 
            // System.out.println("\n\t=== Thống kê doanh thu theo thời gian ===");
            // System.out.println("\t1. Tính theo ngày");
            // System.out.println("\t2. Tính theo tháng");
            // System.out.print("\t=> Chọn kiểu thống kê: ");
            Function.clearScreen();
            System.out.println("\t==================================[ Menu Thống Kê ]=====================================");
            System.out.printf("\t| %-4s | %-77s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------------------|");

            System.out.printf("\t| %-4s | %-77s |%n", "1", "Thống kê theo ngày");
            System.out.printf("\t| %-4s | %-77s |%n", "2", "Thống kê theo tháng");
            System.out.printf("\t| %-4s | %-77s |%n", "3", "Quay lại menu chính");
            System.out.println("\t========================================================================================");
            System.out.print("\t=> Mời nhập lựa chọn: ");
            String choice = sc.nextLine().trim();
            // if (choice.equals("1")) {
            //     this.thongKeDoanhThuTrongMotNgay();
            // }
            // if (choice.equals("2")) {
            //     this.thongKeDoanhThuTheoThang();
            // }
            if (Function.isEmpty(choice)) {
                System.out.println("\tLựa chọn không được rỗng!");
                pause(sc);
                continue;
            }

            if (!Function.isTrueNumber(choice)) {
                System.out.println("\tLựa chọn phải là số!");
                pause(sc);
                continue;
            }

            switch (choice) {
                case "1":
                this.thongKeDoanhThuTrongMotNgay();
                pause(sc);
                break;

                case "2":
                this.thongKeDoanhThuTheoThang();
                pause(sc);
                break;

                case "3":
                break;

                default:
                System.out.println("\tLựa chọn không hợp lệ!");
                pause(sc);
                continue;
            }
            break;
        }
    }
    ///

    // Phương thức để in ra tổng số lượng đơn hàng bán được toàn thời gian
    public void inRaSoLuongDonHangBanDuoc() {
        System.out.println("\tTổng số lượng đơn hàng bán được: " + this.billList.size());
    }

    // Phương thức để in ra nhân viên bán được nhiều đơn hàng nhất toàn thời gian
    public void inRaNhanVienBanDuocNhieuDonNhat() {
        QLNhanVien ql = new QLNhanVien();
        ql.Init();
        NhanVienThuNgan nv = null;

        // tìm ra nhân viên thu ngân đầu tiên trong mảng
        for (Nhanvien nhanvien : ql.nhanVienList) {
            if (nhanvien instanceof NhanVienThuNgan) {
                nv = (NhanVienThuNgan) nhanvien;
                break;
            }
        }

        // duyệt hết mảng để tìm ra nhân viên thu ngân có nhiều đơn hàng nhất
        for (Nhanvien nhanvien : ql.nhanVienList) {
            if (nhanvien instanceof NhanVienThuNgan
                    && ((NhanVienThuNgan) nhanvien).getSoBillDaXuLy() > nv.getSoBillDaXuLy()) {
                nv = (NhanVienThuNgan) nhanvien;
            }
        }
        System.out.println(
                "\t=========================================================================================================");
        System.out.println(
                "\t|                                  Nhân viên bán được nhiều đơn nhất                                    |");
        System.out.println(
                "\t=========================================================================================================");
        System.out.printf("\t| %-21s %-79s |%n", "Mã nhân viên:", nv.getMaNhanVien());
        System.out.printf("\t| %-21s %-79s |%n", "Tên nhân viên:", nv.getTenNhanVien());
        System.out.printf("\t| %-20s %-79s |%n", "Số đơn hàng bán được:", nv.getSoBillDaXuLy());
        System.out.println(
                "\t=========================================================================================================");
    }

    // Phương thức để in ra trạng thái của các đơn hàng
    public void inRaTungTrangThaiHoaDon() {
        System.out.println("\t====================================================");
        System.out.printf("\t| %-10s | %-10s | %-20s |%n", "Mã hóa đơn", "Ngày lập đơn", "Trạng thái");
        for (HoaDon hd : this.billList) {
            hd.inRaTrangThai();
        }
        System.out.println("\t====================================================");
    }

    // Phương thức để in ra số lượng từng sản phẩm bán được
    public void inRaSoLuongTungMatHangBanDuoc() {
        QLHoaDon qlHoaDon = new QLHoaDon();
        QLNuocUong qlNuocUong = new QLNuocUong();
        qlHoaDon.Init();
        qlNuocUong.Init();

        System.out.println(
                "\t======================================================================================================");
        System.out.println(
                "\t|                           Tổng số lượng nước đã bán ra theo mặt hàng                               |");
        System.out.println(
                "\t======================================================================================================");
        System.out.printf("\t| %-16s | %-56s | %-20s |%n", "Mã nước", "Tên nước", "Số lượng");
        for (NuocUong nu : qlNuocUong.getWaterList()) {
            int count = 0;
            for (HoaDon hd : qlHoaDon.billList) {
                for (NuocUong nuHoaDon : hd.getThucDon().getDanhSachNuocUong()) {
                    if (nuHoaDon.getId().equals(nu.getId())) {
                        count++;
                    }
                }
            }
            System.out.printf("\t| %-16s | %-56s | %-20s |%n", nu.getId(), nu.getName(), count);
        }
        System.out.println(
                "\t======================================================================================================");
    }

    public void chuyenTrangThaiHoaDon() {
        Scanner sc = new Scanner(System.in);
        String str;
        boolean isDone = false;

        while (true) {
            Function.clearScreen();
            this.printBill();
            System.out.print("\t=> Nhập ID hóa đơn mà bạn muốn đổi trạng thái: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống!");
                continue;
            }

            for (HoaDon hd : this.billList) {
                if (hd.getMaHoaDon().equals(str)) {
                    hd.changeBillStatus();
                    this.writeAll();
                    isDone = true;
                    break;
                }
            }

            if (!isDone) {
                System.out.println("\tKhông tìm thấy hóa đơn có mã hóa đơn " + str);
            }

            break;
        }
    }

    public void resetList() {
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            Function.clearScreen();
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
                    this.billList.clear();
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

    public void findBill() {
        Scanner sc = new Scanner(System.in);
        String str;
        HoaDon hoaDon = null;

        while (true) {
            Function.clearScreen();
            System.out.print("\t=> Mời bạn nhập ID hóa đơn muốn tìm: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không bỏ trống!");
                continue;
            }

            for (HoaDon hd : this.billList) {
                if (hd.getMaHoaDon().equals(str)) {
                    hoaDon = hd;
                }
            }

            if (hoaDon == null) {
                System.out.println("\tKhông tìm thấy hóa đơn có ID " + str);
            } else {
                hoaDon.xuatThongTin();
            }
            break;
        }
    }

    public void menuQLHoaDon() {
        Scanner sc = new Scanner(System.in);
        String str;
        this.Init();

        while (true) {
            Function.clearScreen();
            // In tiêu đề
            System.out.println(
                    "\t==================================[ Menu Quản Lý Hóa Đơn ]==============================");

            // In tiêu đề các cột
            System.out.printf("\t| %-4s | %-77s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------------------|");

            // In danh sách các nhóm chức năng
            System.out.printf("\t| %-4s | %-77s |%n", "1", "In danh sách hóa đơn");
            System.out.printf("\t| %-4s | %-77s |%n", "2.", "Thống kê");
            System.out.printf("\t| %-4s | %-77s |%n", "3.", "Chuyển trạng thái và cập nhật hóa đơn");
            System.out.printf("\t| %-4s | %-77s |%n", "4.", "Tìm kiếm");
            System.out.printf("\t| %-4s | %-77s |%n", "5.", "Làm mới dữ liệu và màn hình");
            System.out.printf("\t| %-4s | %-77s |%n", "6.", "Thoát chương trình");

            // In dòng kẻ dưới cùng
            System.out.println(
                    "\t========================================================================================");
            System.out.print("\t[Manage] Nhập lựa chọn: ");
            str = sc.nextLine();

            switch (str) {
                case "1":
                    this.printBill();
                    pause(sc);
                    break;

                case "2":
                    this.menuThongKe(sc);
                    break;

                case "3":
                    this.menuChuyenTrangThaiCapNhat(sc);
                    break;

                case "4":
                    this.findBill();
                    pause(sc);
                    break;

                case "5":
                    this.menuLamMoi(sc);
                    break;

                case "6":
                    return;

                default:
                    System.out.println("\tLựa chọn không hợp lệ! Hãy thử lại.");
                    pause(sc);
                    break;
            }
        }
    }

    // Hiển thị menu thống kê
    private void menuThongKe(Scanner sc) {
        while (true) {
            Function.clearScreen();
            System.out.println("\t==================================[ Menu Thống Kê ]=====================================");
            System.out.printf("\t| %-4s | %-77s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------------------|");

            System.out.printf("\t| %-4s | %-77s |%n", "1", "Thống kê toàn bộ doanh thu");
            System.out.printf("\t| %-4s | %-77s |%n", "2", "Thống kê doanh thu theo thời gian");
            System.out.printf("\t| %-4s | %-77s |%n", "3", "Quay lại menu chính");
            System.out.println("\t========================================================================================");
            System.out.print("\t[Thống kê] Nhập lựa chọn: ");
            String str = sc.nextLine();

            switch (str) {
                case "1":
                    this.thongKeDoanhThuAll();
                    break;

                case "2":
                    this.thongKeDoanhThuTheoThoiGian();
                    break;

                case "3":
                    return;

                default:
                    System.out.println("\tLựa chọn không hợp lệ! Hãy thử lại.");
                    pause(sc);
                    break;
            }
        }
    }

    // Hiển thị menu chuyển trạng thái và cập nhật hóa đơn
    private void menuChuyenTrangThaiCapNhat(Scanner sc) {
        while (true) {
            Function.clearScreen();
    System.out.println("\t===========================[ Menu Chuyển Trạng Thái và Cập Nhật ]===============================");
    System.out.printf("\t| %-4s | %-85s |%n", "STT", "Chức năng");
    System.out.println(
        "\t|------|---------------------------------------------------------------------------------------|");
    System.out.printf("\t| %-4s | %-85s |%n", "1", "Chuyển trạng thái hóa đơn");
    System.out.printf("\t| %-4s | %-85s |%n", "2", "Cập nhật hóa đơn vào File từ danh sách");
    System.out.printf("\t| %-4s | %-85s |%n", "3", "Cập nhật hóa đơn vào danh sách từ File");
    System.out.printf("\t| %-4s | %-85s |%n", "4", "Quay lại menu chính");
    System.out.println("\t================================================================================================");

    System.out.print("\t[Chuyển trạng thái] Nhập lựa chọn: ");
            String str = sc.nextLine();

            switch (str) {
                case "1":
                    this.chuyenTrangThaiHoaDon();
                    pause(sc);
                    break;

                case "2":
                    this.writeAll();
                    pause(sc);
                    break;

                case "3":
                    this.billList.clear();
                    this.Init();
                    pause(sc);
                    break;

                case "4":
                    return;

                default:
                    System.out.println("\tLựa chọn không hợp lệ! Hãy thử lại.");
                    pause(sc);
                    break;
            }
        }
    }

    // Hiển thị menu làm mới dữ liệu và màn hình
    private void menuLamMoi(Scanner sc) {
        while (true) {
            Function.clearScreen();
System.out.println("\t============================[ Menu Làm Mới ]===========================");
System.out.printf("\t| %-4s | %-60s |%n", "STT", "Chức năng");
System.out.println(
    "\t|------|--------------------------------------------------------------|");
System.out.printf("\t| %-4s | %-60s |%n", "1", "Làm mới danh sách hóa đơn");
System.out.printf("\t| %-4s | %-60s |%n", "2", "Làm mới màn hình");
System.out.printf("\t| %-4s | %-60s |%n", "3", "Quay lại menu chính");
System.out.println("\t=======================================================================");
System.out.print("\t[Làm mới] Nhập lựa chọn: ");
String str = sc.nextLine();


            switch (str) {
                case "1":
                    this.resetList();
                    pause(sc);
                    break;

                case "2":
                    Function.clearScreen();
                    break;

                case "3":
                    return;

                default:
                    System.out.println("\tLựa chọn không hợp lệ! Hãy thử lại.");
                    pause(sc);
                    break;
            }
        }
    }

    // Dừng màn hình để người dùng đọc kết quả
    private void pause(Scanner sc) {
        System.out.print("\tNhấn Enter để tiếp tục...");
        sc.nextLine();
    }
    // public static void main(String[] args) {
    //     QLHoaDon hd = new QLHoaDon();
    //     hd.menuQLHoaDon();
    // }
    public void printHoaDonList() {
        System.out.println("\t+--------------------+------------------------------+");
        System.out.println("\t|     Mã Hóa Đơn     |    Trạng Thái Hóa Đơn        |");
        System.out.println("\t+--------------------+------------------------------+");
        for (HoaDon hoaDon : billList) {
            System.out.printf("\t| %-18s | %-28s |\n", hoaDon.getMaHoaDon(), hoaDon.getTrangThaiHoaDon());
            System.out.println("\t+--------------------+------------------------------+");
        }
    }
}
