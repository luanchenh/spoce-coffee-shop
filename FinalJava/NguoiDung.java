package FinalJava;

import Ban.Ban;
import Ban.QLBan;
import HoaDon.HoaDon;
import HoaDon.QLHoaDon;
import KhachHang.KHMangDi;
import KhachHang.KHTaiCho;
import KhachHang.KhachHang;
import KhachHang.MemberCard;
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
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 */

@SuppressWarnings("resource")
public class NguoiDung {

    public static String IDLink = "";
    // Các QLy
    public static QLNuocUong qlNuocUong = new QLNuocUong();
    public static QLBan qlBan = new QLBan();
    public static QLKhachHang qlKhachHang = new QLKhachHang();
    public static QLTopping qlTopping = new QLTopping();
    public static QLNhanVien qlNhanVien = new QLNhanVien();
    public static QLHoaDon qlHoaDon = new QLHoaDon();
    static {
        qlNuocUong.Init();
        qlBan.Init();
        qlKhachHang.Init();
        qlTopping.Init();
        qlNhanVien.Init();
        qlHoaDon.Init();
    }

    public static KhachHang getInfoCustomer() {
        KhachHang temp = null;
        MemberCard memberCard = null;
        boolean status = false;
        File customerFile = new File("../File/customer.txt");
        try (Scanner rd = new Scanner(customerFile)) {
            while (rd.hasNextLine()) {
                String line = rd.nextLine();
                String[] parts = line.split("\\|");
                if (parts[1].equals(IDLink)) {
                    if (parts[3].equals("1")) {
                        memberCard = new MemberCard(parts[4], new Date(parts[5], parts[6], parts[7]),
                                new Date(parts[8], parts[9], parts[10]), new Date(parts[11], parts[12], parts[13]),
                                Integer.parseInt(parts[14]));
                        status = true;
                    }
                    if (parts[0].equals("1")) {
                        temp = new KHMangDi(parts[1], parts[2], status, memberCard);
                    }
                    if (parts[0].equals("0")) {
                        temp = new KHTaiCho(parts[1], parts[2], status, memberCard);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("\tLỗi: " + e.getMessage());
        }
        return temp;
    }

    // public static NhanVienThuNgan getInfoNhanVien() {
    // NhanVienThuNgan temp = null;
    // }

    public static void selectCustomer() {

    }

    @SuppressWarnings("unused")
    public static void selectOne() {
        Scanner sc = new Scanner(System.in);
        String str;
        int number = 0;
        int soluongkhach = 0;

        while (true) {
            Function.clearScreen();
            System.out.println(
                    "\t=============================[Chức năng người Dùng tại chỗ]===============================");
            System.out.printf("\t| %-87s |%n", "Chọn chức năng (Cần đăng nhập để chọn bàn)");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Đăng nhập tài khoản đã có");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Đăng ký nếu chưa có tài khoản");
            System.out.printf("\t| %-5s %-81s |%n", "3.", "Quay lại trang trước");
            System.out.println(
                    "\t==========================================================================================");
            System.out.print("\tNhập lựa chọn của bạn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                if (Function.isTrueNumber(str)) {
                    number = Integer.parseInt(str);
                    if (number >= 1 && number <= 3) {
                        if (number == 1) {
                            Account account = new Account("1");
                            account.Login();
                            IDLink = account.checkLogin();
                            if (IDLink != "") {
                                System.out.println("\tĐăng nhập thành công !");
                                KHTaiCho temp = null;

                                for (KhachHang kh : qlKhachHang.customerList) {
                                    if (kh.getCustomerID().equals(IDLink)) {
                                        temp = (KHTaiCho) kh;
                                    }
                                }

                                Function.clearScreen();
                                temp.xuatThongTin();

                                while (true) {
                                    System.out.print("\tNhập số lượng khách: ");
                                    str = sc.nextLine();

                                    if (Function.isEmpty(str)) {
                                        System.out.println("\tVui lòng không để trống !");
                                        continue;
                                    }

                                    if (!Function.isTrueNumber(str)) {
                                        System.out.println("\tVui lòng nhập số !");
                                        continue;
                                    }

                                    temp.setNumberOfCustomer(Integer.parseInt(str));
                                    break;
                                }

                                boolean isDone = qlBan.printEmptyTable();
                                Ban tmp = null;
                                if (isDone) {
                                    while (true) {
                                        System.out.print("\tNhập ID bàn bạn muốn chọn: ");
                                        str = sc.nextLine();

                                        if (Function.isEmpty(str)) {
                                            System.out.println("\tVui lòng không để trống !");
                                            continue;
                                        }

                                        tmp = qlBan.getTableByID(str);
                                        if (tmp == null) {
                                            System.out.println("\tID không đúng, vui lòng nhập lại!");
                                            continue;
                                        }
                                        break;
                                    }

                                    HoaDon hd = null;
                                    loop2: while (true) {
                                        NhanVienThuNgan nvtmp = null;
                                        ArrayList<NhanVienThuNgan> nvtnList = new ArrayList<>();
                                        for (Nhanvien nv : qlNhanVien.nhanVienList) {
                                            if (nv instanceof NhanVienThuNgan) {
                                                nvtnList.add((NhanVienThuNgan) nv);
                                            }
                                        }

                                        Random rd = new Random();
                                        nvtmp = nvtnList.get(rd.nextInt(nvtnList.size() - 0));

                                        boolean isWantDrink = true;
                                        ThucDon order = new ThucDon();

                                        while (isWantDrink) {
                                            // boolean wantHot = false, wantCold = false, wantSugar = false, wantMilk =
                                            // false;

                                            Function.clearScreen();
                                            qlNuocUong.inDanhSach();
                                            System.out.print("\tNhập ID nước bạn muốn đặt: ");
                                            str = sc.nextLine();

                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tVui lòng không để trống !");
                                                try {
                                                    Thread.sleep(1500);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                continue;
                                            }

                                            NuocUong nuocuong = null;
                                            for (NuocUong drink : qlNuocUong.getWaterList()) {
                                                if (drink.getId().equals(str)) {
                                                    nuocuong = drink;
                                                    break;
                                                }
                                            }

                                            if (nuocuong == null) {
                                                System.out.println("\tID không đúng, vui lòng nhập lại!");
                                                continue;
                                            } else {
                                                order.getDanhSachNuocUong().add(nuocuong);
                                            }

                                            // chọn size nước uống
                                            while (true) {
                                                int countOption = 1;

                                                Function.clearScreen();
                                                System.out.println(
                                                        "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                System.out.printf("\t| %-87s |%n",
                                                        "Mời bạn chọn size cho món nước " + nuocuong.getId());
                                                for (String key : nuocuong.getSizePrice().keySet()) {
                                                    System.out.printf("\t| %-5s %-81s |%n", countOption++ + ".", key);
                                                }
                                                System.out.println(
                                                        "\t==========================================================================================");
                                                System.out.print("\tNhập lựa chọn của bạn: ");
                                                str = sc.nextLine();

                                                if (Function.isEmpty(str)) {
                                                    System.out.println("\tVui lòng không để trống !");
                                                    continue;
                                                }

                                                if (!Function.isTrueNumber(str)) {
                                                    System.out.println("\tVui lòng nhập số !");
                                                    continue;
                                                }

                                                if (countOption - 1 == 3) {
                                                    switch (str) {
                                                        case "1":
                                                            order.getSize().add("S");
                                                            break;

                                                        case "2":
                                                            order.getSize().add("L");
                                                            break;

                                                        case "3":
                                                            order.getSize().add("M");
                                                            break;

                                                        default:
                                                            System.out.println("\tVui lòng chọn từ 1 đến 3!");
                                                            continue;
                                                    }
                                                } else if (countOption - 1 == 4) {
                                                    switch (str) {
                                                        case "1":
                                                            order.getSize().add("S");
                                                            break;

                                                        case "2":
                                                            order.getSize().add("M");
                                                            break;

                                                        case "3":
                                                            order.getSize().add("L");
                                                            break;

                                                        case "4":
                                                            order.getSize().add("XL");
                                                            break;

                                                        default:
                                                            System.out.println("\tVui lòng chọn từ 1 đến 4!");
                                                            continue;
                                                    }
                                                }
                                                break;
                                            }

                                            // yêu cầu nóng, đá, sữa, đường
                                            ArrayList<Boolean> trangThai = new ArrayList<>(); // mảng lưu trạng thái của
                                                                                              // món nước hiện tại
                                            trangThai.add(false); // đá
                                            trangThai.add(false); // nóng
                                            trangThai.add(false); // đường
                                            trangThai.add(false); // sữa
                                            loop: while (true) {
                                                Function.clearScreen();
                                                System.out.println(
                                                        "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                System.out.printf("\t| %-87s |%n", "Bạn có yêu cầu gì cho món nước "
                                                        + nuocuong.getId() + " không?");
                                                System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                System.out.println(
                                                        "\t==========================================================================================");
                                                System.out.print("\tNhập lựa chọn của bạn: ");
                                                str = sc.nextLine();

                                                if (Function.isEmpty(str)) {
                                                    System.out.println("\tVui lòng không để trống !");
                                                    continue;
                                                }

                                                if (!Function.isTrueNumber(str)) {
                                                    System.out.println("\tVui lòng nhập số !");
                                                    continue;
                                                }

                                                switch (str) {
                                                    case "1":
                                                        loop1: while (true) {
                                                            Function.clearScreen();
                                                            System.out.println(
                                                                    "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                            System.out.printf("\t| %-87s |%n",
                                                                    "Bạn muốn yêu cầu gì cho món nước "
                                                                            + nuocuong.getId() + "?");
                                                            System.out.printf("\t| %-5s %-81s |%n", "1.", "Đá");
                                                            System.out.printf("\t| %-5s %-81s |%n", "2.", "Nóng");
                                                            System.out.printf("\t| %-5s %-81s |%n", "3.", "Đường");
                                                            System.out.printf("\t| %-5s %-81s |%n", "4.", "Sữa");
                                                            System.out.printf("\t| %-5s %-81s |%n", "5.",
                                                                    "Quay về trang trước");
                                                            System.out.println(
                                                                    "\t==========================================================================================");
                                                            System.out.print("\tNhập lựa chọn của bạn: ");
                                                            str = sc.nextLine();

                                                            if (Function.isEmpty(str)) {
                                                                System.out.println("\tVui lòng không để trống !");
                                                                continue;
                                                            }

                                                            if (!Function.isTrueNumber(str)) {
                                                                System.out.println("\tVui lòng nhập số !");
                                                                continue;
                                                            }

                                                            switch (str) {
                                                                case "1":
                                                                    if (nuocuong.isCold()) {
                                                                        trangThai.set(0, true);
                                                                    } else {
                                                                        System.out.println(
                                                                                "\tMón nước này không được thêm đá!");
                                                                        try {
                                                                            Thread.sleep(1500);
                                                                        } catch (InterruptedException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        continue;
                                                                    }
                                                                    break;

                                                                case "2":
                                                                    if (nuocuong.isHot()) {
                                                                        trangThai.set(1, true);
                                                                    } else {
                                                                        System.out.println(
                                                                                "\tMón nước này không được uống nóng!");
                                                                        try {
                                                                            Thread.sleep(1500);
                                                                        } catch (InterruptedException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        continue;
                                                                    }
                                                                    break;

                                                                case "3":
                                                                    if (nuocuong.isSugar()) {
                                                                        trangThai.set(2, true);
                                                                    } else {
                                                                        System.out.println(
                                                                                "\tMón nước này không được bỏ đường!");
                                                                        try {
                                                                            Thread.sleep(1500);
                                                                        } catch (InterruptedException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        continue;
                                                                    }
                                                                    break;

                                                                case "4":
                                                                    if (nuocuong.isMilk()) {
                                                                        trangThai.set(3, true);
                                                                    } else {
                                                                        System.out.println(
                                                                                "\tMón nước này không được thêm sữa!");
                                                                        try {
                                                                            Thread.sleep(1500);
                                                                        } catch (InterruptedException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        continue;
                                                                    }
                                                                    break;

                                                                case "5":
                                                                    continue loop;

                                                                default:
                                                                    System.out.println("\tVui lòng chọn từ 1 đến 5!");
                                                                    continue;
                                                            }

                                                            while (true) {
                                                                Function.clearScreen();
                                                                System.out.println(
                                                                        "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                                System.out.printf("\t| %-87s |%n",
                                                                        "Bạn còn yêu cầu gì thêm cho món nước "
                                                                                + nuocuong.getId() + " không?");
                                                                System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                                System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                                System.out.println(
                                                                        "\t==========================================================================================");
                                                                System.out.print("\tNhập lựa chọn của bạn: ");
                                                                str = sc.nextLine();

                                                                if (Function.isEmpty(str)) {
                                                                    System.out.println("\tVui lòng không để trống !");
                                                                    continue;
                                                                }

                                                                if (!Function.isTrueNumber(str)) {
                                                                    System.out.println("\tVui lòng nhập số !");
                                                                    continue;
                                                                }

                                                                switch (str) {
                                                                    case "1":
                                                                        continue loop1;

                                                                    case "2":
                                                                        break;

                                                                    default:
                                                                        System.out
                                                                                .println("\tVui lòng chọn từ 1 đến 2!");
                                                                        try {
                                                                            Thread.sleep(1500);
                                                                        } catch (InterruptedException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        continue;
                                                                }
                                                                break;
                                                            }

                                                            break;
                                                        }
                                                        break;

                                                    case "2":
                                                        break;

                                                    default:
                                                        System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                        try {
                                                            Thread.sleep(1500);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        continue;
                                                }

                                                break;
                                            }
                                            order.getTrangThaiNuocUong().add(trangThai);

                                            boolean isWantTopping;
                                            while (true) {
                                                Function.clearScreen();
                                                System.out.println(
                                                        "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                System.out.printf("\t| %-87s |%n",
                                                        "Bạn có muốn thêm topping cho món nước " + nuocuong.getId()
                                                                + " không?");
                                                System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                System.out.println(
                                                        "\t==========================================================================================");
                                                System.out.print("\tNhập lựa chọn của bạn: ");
                                                str = sc.nextLine();

                                                if (Function.isEmpty(str)) {
                                                    System.out.println("\tVui lòng không để trống !");
                                                    continue;
                                                }

                                                if (!Function.isTrueNumber(str)) {
                                                    System.out.println("\tVui lòng nhập số !");
                                                    continue;
                                                }

                                                switch (str) {
                                                    case "1":
                                                        isWantTopping = true;
                                                        break;

                                                    case "2":
                                                        isWantTopping = false;
                                                        break;

                                                    default:
                                                        System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                                                        try {
                                                            Thread.sleep(1500);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        continue;
                                                }

                                                break;
                                            }

                                            ArrayList<Topping> tplist = new ArrayList<>();
                                            while (isWantTopping) {
                                                Function.clearScreen();
                                                if (nuocuong.getTopping().size() == 0) {
                                                    System.out.println("\tNước uống này không được thêm topping!");
                                                    System.out.print("\tEnter để tiếp tục!");
                                                    str = sc.nextLine();
                                                    break;
                                                }
                                                nuocuong.printToppingOfDrink();
                                                while (true) {
                                                    System.out.print("\tNhập ID topping bạn muốn đặt: ");
                                                    str = sc.nextLine();

                                                    if (Function.isEmpty(str)) {
                                                        System.out.println("\t Vui lòng không để trống!");
                                                        continue;
                                                    }

                                                    Topping tp = qlTopping.getToppingByID(str);
                                                    if (tp == null) {
                                                        System.out.println("\t Vui lòng nhập đúng mã topping!");
                                                        continue;
                                                    } else {
                                                        tplist.add(tp);
                                                        System.out.println("\tĐã thêm topping thành công!");
                                                    }

                                                    while (true) {
                                                        Function.clearScreen();
                                                        System.out.println(
                                                                "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                        System.out.printf("\t| %-87s |%n",
                                                                "Bạn có muốn thêm topping nào nữa không?");
                                                        System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                        System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                        System.out.println(
                                                                "\t==========================================================================================");
                                                        System.out.print("\tNhập lựa chọn của bạn: ");
                                                        str = sc.nextLine();

                                                        if (Function.isEmpty(str)) {
                                                            System.out.println("\tVui lòng không để trống !");
                                                            continue;
                                                        }
                                                        if (!Function.isTrueNumber(str)) {
                                                            System.out.println("\tVui lòng nhập số !");
                                                            continue;
                                                        }

                                                        switch (str) {
                                                            case "1":
                                                                isWantTopping = true;
                                                                break;

                                                            case "2":
                                                                isWantTopping = false;
                                                                break;

                                                            default:
                                                                System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                                try {
                                                                    Thread.sleep(1500);
                                                                } catch (InterruptedException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                continue;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }

                                            }
                                            order.getDanhSachTopping().add(tplist);
                                            Function.clearScreen();
                                            order.xuatThongTin();
                                            while (true) {
                                                // Function.clearScreen();
                                                System.out.println(
                                                        "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                System.out.printf("\t| %-87s |%n",
                                                        "Bạn có muốn đặt thêm món nước nào nữa không?");
                                                System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                System.out.println(
                                                        "\t==========================================================================================");
                                                System.out.print("\tNhập lựa chọn của bạn: ");
                                                str = sc.nextLine();

                                                if (Function.isEmpty(str)) {
                                                    System.out.println("\tVui lòng không để trống !");
                                                    continue;
                                                }
                                                if (!Function.isTrueNumber(str)) {
                                                    System.out.println("\tVui lòng nhập số !");
                                                    continue;
                                                }

                                                switch (str) {
                                                    case "1":
                                                        isWantDrink = true;
                                                        break;

                                                    case "2":
                                                        isWantDrink = false;
                                                        break;

                                                    default:
                                                        System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                        try {
                                                            Thread.sleep(1500);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        continue;
                                                }
                                                break;
                                            }
                                        }

                                        while (true) {
                                            Function.clearScreen();
                                            order.xuatThongTin();
                                            System.out.println(
                                                    "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                            System.out.printf("\t| %-87s |%n", "Mời bạn xác nhận đơn hàng");
                                            System.out.printf("\t| %-5s %-81s |%n", "1.", "Xác nhận");
                                            System.out.printf("\t| %-5s %-81s |%n", "2.", "Không xác nhận");
                                            System.out.println(
                                                    "\t==========================================================================================");
                                            System.out.print("\tNhập lựa chọn của bạn: ");
                                            str = sc.nextLine();

                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tVui lòng không để trống !");
                                                continue;
                                            }
                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("\tVui lòng nhập số !");
                                                continue;
                                            }

                                            switch (str) {
                                                case "1":
                                                    double tongTien = order.tinhTongTien();
                                                    double tienKhachDua;
                                                    while (true) {
                                                        // System.out.println("\tSố tiền phải trả là: " + tongTien);
                                                        System.out.printf("\tSố tiền phải trả là:  %.0f VNĐ%n",
                                                                tongTien);
                                                        System.out.print("\tMời nhập số tiền khách đưa: ");
                                                        str = sc.nextLine();

                                                        if (Function.isEmpty(str)) {
                                                            System.out.println("\tVui lòng không để trống!");
                                                            continue;
                                                        }

                                                        if (!Function.isTrueNumber(str)) {
                                                            System.out.println("\tVui lòng nhập số!");
                                                            continue;
                                                        }

                                                        if (Double.parseDouble(str) < tongTien) {
                                                            System.out.println(
                                                                    "\tQuý khách đưa không đủ tiền. Vui lòng nhập lại!");
                                                            continue;
                                                        }

                                                        tienKhachDua = Double.parseDouble(str);
                                                        break;
                                                    }

                                                    hd = new HoaDon(temp, nvtmp, order, tongTien, tienKhachDua);
                                                    nvtmp.setSoBillDaXuLy(nvtmp.getSoBillDaXuLy() + 1);
                                                    nvtmp.setTongTienDaXuLy(nvtmp.getTongTienDaXuLy() + tongTien);
                                                    if (temp.IsMember()) {
                                                        temp.getMemberCard().point((int) tongTien);
                                                    }
                                                    break;

                                                case "2":
                                                    continue loop2;

                                                default:
                                                    System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                    try {
                                                        Thread.sleep(1500);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    continue;
                                            }
                                            break;
                                        }

                                        break;
                                    }

                                    hd.xuatThongTin();
                                    qlHoaDon.billList.add(hd);
                                    qlHoaDon.writeAll();
                                    qlNhanVien.writeFile();
                                    qlKhachHang.writeAll();
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    str = sc.nextLine();
                                    // xử lí phần chọn nước
                                    break;
                                } else {
                                    System.out.println("\tKhông còn bàn trống!");
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                System.out.println("\tĐăng nhập thất bại, đăng nhập lại ! !");
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else if (number == 2) {
                            Account account = new Account("1"); // tạo account với loại khách hàng (1)
                            account.nhapThongTin(); // sau đó nhập thông tin account bao gồm tk, mk, mã kh tự cấp phát

                            KHTaiCho temp = null;

                            qlKhachHang.Init();
                            for (KhachHang kh : qlKhachHang.customerList) {
                                if (kh.getCustomerID().equals(account.getIDLink())) {
                                    temp = (KHTaiCho) kh;
                                }
                            }

                            Function.clearScreen();
                            temp.xuatThongTin();

                            boolean isDone = qlBan.printEmptyTable();
                            Ban tmp = null;
                            if (isDone) {
                                while (true) {
                                    System.out.print("\tNhập ID bàn bạn muốn chọn: ");
                                    str = sc.nextLine();

                                    if (Function.isEmpty(str)) {
                                        System.out.println("\tVui lòng không để trống !");
                                        continue;
                                    }

                                    tmp = qlBan.getTableByID(str);
                                    if (tmp == null) {
                                        System.out.println("\tID không đúng, vui lòng nhập lại!");
                                        continue;
                                    }
                                    break;
                                }

                                HoaDon hd = null;
                                loop2: while (true) {
                                    NhanVienThuNgan nvtmp = null;
                                    ArrayList<NhanVienThuNgan> nvtnList = new ArrayList<>();
                                    for (Nhanvien nv : qlNhanVien.nhanVienList) {
                                        if (nv instanceof NhanVienThuNgan) {
                                            nvtnList.add((NhanVienThuNgan) nv);
                                        }
                                    }

                                    Random rd = new Random();
                                    nvtmp = nvtnList.get(rd.nextInt(nvtnList.size() - 0));

                                    boolean isWantDrink = true;
                                    ThucDon order = new ThucDon();

                                    while (isWantDrink) {
                                        // boolean wantHot = false, wantCold = false, wantSugar = false, wantMilk =
                                        // false;

                                        Function.clearScreen();
                                        qlNuocUong.inDanhSach();
                                        System.out.print("\tNhập ID nước bạn muốn đặt: ");
                                        str = sc.nextLine();

                                        if (Function.isEmpty(str)) {
                                            System.out.println("\tVui lòng không để trống !");
                                            try {
                                                Thread.sleep(1500);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            continue;
                                        }

                                        NuocUong nuocuong = null;
                                        for (NuocUong drink : qlNuocUong.getWaterList()) {
                                            if (drink.getId().equals(str)) {
                                                nuocuong = drink;
                                                break;
                                            }
                                        }

                                        if (nuocuong == null) {
                                            System.out.println("\tID không đúng, vui lòng nhập lại!");
                                            continue;
                                        } else {
                                            order.getDanhSachNuocUong().add(nuocuong);
                                        }

                                        // chọn size nước uống
                                        while (true) {
                                            int countOption = 1;

                                            Function.clearScreen();
                                            System.out.println(
                                                    "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                            System.out.printf("\t| %-87s |%n",
                                                    "Mời bạn chọn size cho món nước " + nuocuong.getId());
                                            for (String key : nuocuong.getSizePrice().keySet()) {
                                                System.out.printf("\t| %-5s %-81s |%n", countOption++ + ".", key);
                                            }
                                            System.out.println(
                                                    "\t==========================================================================================");
                                            System.out.print("\tNhập lựa chọn của bạn: ");
                                            str = sc.nextLine();

                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tVui lòng không để trống !");
                                                continue;
                                            }

                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("\tVui lòng nhập số !");
                                                continue;
                                            }

                                            if (countOption - 1 == 3) {
                                                switch (str) {
                                                    case "1":
                                                        order.getSize().add("S");
                                                        break;

                                                    case "2":
                                                        order.getSize().add("L");
                                                        break;

                                                    case "3":
                                                        order.getSize().add("M");
                                                        break;

                                                    default:
                                                        System.out.println("\tVui lòng chọn từ 1 đến 3!");
                                                        continue;
                                                }
                                            } else if (countOption - 1 == 4) {
                                                switch (str) {
                                                    case "1":
                                                        order.getSize().add("S");
                                                        break;

                                                    case "2":
                                                        order.getSize().add("M");
                                                        break;

                                                    case "3":
                                                        order.getSize().add("L");
                                                        break;

                                                    case "4":
                                                        order.getSize().add("XL");
                                                        break;

                                                    default:
                                                        System.out.println("\tVui lòng chọn từ 1 đến 4!");
                                                        continue;
                                                }
                                            }
                                            break;
                                        }

                                        // yêu cầu nóng, đá, sữa, đường
                                        ArrayList<Boolean> trangThai = new ArrayList<>(); // mảng lưu trạng thái của món
                                                                                          // nước hiện tại
                                        trangThai.add(false); // đá
                                        trangThai.add(false); // nóng
                                        trangThai.add(false); // đường
                                        trangThai.add(false); // sữa
                                        loop: while (true) {
                                            Function.clearScreen();
                                            System.out.println(
                                                    "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                            System.out.printf("\t| %-87s |%n",
                                                    "Bạn có yêu cầu gì cho món nước " + nuocuong.getId() + " không?");
                                            System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                            System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                            System.out.println(
                                                    "\t==========================================================================================");
                                            System.out.print("\tNhập lựa chọn của bạn: ");
                                            str = sc.nextLine();

                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tVui lòng không để trống !");
                                                continue;
                                            }

                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("\tVui lòng nhập số !");
                                                continue;
                                            }

                                            switch (str) {
                                                case "1":
                                                    loop1: while (true) {
                                                        Function.clearScreen();
                                                        System.out.println(
                                                                "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                        System.out.printf("\t| %-87s |%n",
                                                                "Bạn muốn yêu cầu gì cho món nước " + nuocuong.getId()
                                                                        + "?");
                                                        System.out.printf("\t| %-5s %-81s |%n", "1.", "Đá");
                                                        System.out.printf("\t| %-5s %-81s |%n", "2.", "Nóng");
                                                        System.out.printf("\t| %-5s %-81s |%n", "3.", "Đường");
                                                        System.out.printf("\t| %-5s %-81s |%n", "4.", "Sữa");
                                                        System.out.printf("\t| %-5s %-81s |%n", "5.",
                                                                "Quay về trang trước");
                                                        System.out.println(
                                                                "\t==========================================================================================");
                                                        System.out.print("\tNhập lựa chọn của bạn: ");
                                                        str = sc.nextLine();

                                                        if (Function.isEmpty(str)) {
                                                            System.out.println("\tVui lòng không để trống !");
                                                            continue;
                                                        }

                                                        if (!Function.isTrueNumber(str)) {
                                                            System.out.println("\tVui lòng nhập số !");
                                                            continue;
                                                        }

                                                        switch (str) {
                                                            case "1":
                                                                if (nuocuong.isCold()) {
                                                                    trangThai.set(0, true);
                                                                } else {
                                                                    System.out.println(
                                                                            "\tMón nước này không được thêm đá!");
                                                                    try {
                                                                        Thread.sleep(1500);
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    continue;
                                                                }
                                                                break;

                                                            case "2":
                                                                if (nuocuong.isHot()) {
                                                                    trangThai.set(1, true);
                                                                } else {
                                                                    System.out.println(
                                                                            "\tMón nước này không được uống nóng!");
                                                                    try {
                                                                        Thread.sleep(1500);
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    continue;
                                                                }
                                                                break;

                                                            case "3":
                                                                if (nuocuong.isSugar()) {
                                                                    trangThai.set(2, true);
                                                                } else {
                                                                    System.out.println(
                                                                            "\tMón nước này không được bỏ đường!");
                                                                    try {
                                                                        Thread.sleep(1500);
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    continue;
                                                                }
                                                                break;

                                                            case "4":
                                                                if (nuocuong.isMilk()) {
                                                                    trangThai.set(3, true);
                                                                } else {
                                                                    System.out.println(
                                                                            "\tMón nước này không được thêm sữa!");
                                                                    try {
                                                                        Thread.sleep(1500);
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    continue;
                                                                }
                                                                break;

                                                            case "5":
                                                                continue loop;

                                                            default:
                                                                System.out.println("\tVui lòng chọn từ 1 đến 5!");
                                                                continue;
                                                        }

                                                        while (true) {
                                                            Function.clearScreen();
                                                            System.out.println(
                                                                    "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                            System.out.printf("\t| %-87s |%n",
                                                                    "Bạn còn yêu cầu gì thêm cho món nước "
                                                                            + nuocuong.getId() + " không?");
                                                            System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                            System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                            System.out.println(
                                                                    "\t==========================================================================================");
                                                            System.out.print("\tNhập lựa chọn của bạn: ");
                                                            str = sc.nextLine();

                                                            if (Function.isEmpty(str)) {
                                                                System.out.println("\tVui lòng không để trống !");
                                                                continue;
                                                            }

                                                            if (!Function.isTrueNumber(str)) {
                                                                System.out.println("\tVui lòng nhập số !");
                                                                continue;
                                                            }

                                                            switch (str) {
                                                                case "1":
                                                                    continue loop1;

                                                                case "2":
                                                                    break;

                                                                default:
                                                                    System.out.println("\tVui lòng chọn từ 1 đến 2!");
                                                                    try {
                                                                        Thread.sleep(1500);
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    continue;
                                                            }
                                                            break;
                                                        }

                                                        break;
                                                    }
                                                    break;

                                                case "2":
                                                    break;

                                                default:
                                                    System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                    try {
                                                        Thread.sleep(1500);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    continue;
                                            }

                                            break;
                                        }
                                        order.getTrangThaiNuocUong().add(trangThai);

                                        boolean isWantTopping;
                                        while (true) {
                                            Function.clearScreen();
                                            System.out.println(
                                                    "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                            System.out.printf("\t| %-87s |%n", "Bạn có muốn thêm topping cho món nước "
                                                    + nuocuong.getId() + " không?");
                                            System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                            System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                            System.out.println(
                                                    "\t==========================================================================================");
                                            System.out.print("\tNhập lựa chọn của bạn: ");
                                            str = sc.nextLine();

                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tVui lòng không để trống !");
                                                continue;
                                            }

                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("\tVui lòng nhập số !");
                                                continue;
                                            }

                                            switch (str) {
                                                case "1":
                                                    isWantTopping = true;
                                                    break;

                                                case "2":
                                                    isWantTopping = false;
                                                    break;

                                                default:
                                                    System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                                                    try {
                                                        Thread.sleep(1500);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    continue;
                                            }

                                            break;
                                        }

                                        ArrayList<Topping> tplist = new ArrayList<>();
                                        while (isWantTopping) {
                                            Function.clearScreen();
                                            if (nuocuong.getTopping().size() == 0) {
                                                System.out.println("\tNước uống này không được thêm topping!");
                                                System.out.print("\tEnter để tiếp tục!");
                                                str = sc.nextLine();
                                                break;
                                            }
                                            nuocuong.printToppingOfDrink();
                                            while (true) {
                                                System.out.print("\tNhập ID topping bạn muốn đặt: ");
                                                str = sc.nextLine();

                                                if (Function.isEmpty(str)) {
                                                    System.out.println("\t Vui lòng không để trống!");
                                                    continue;
                                                }

                                                Topping tp = qlTopping.getToppingByID(str);
                                                if (tp == null) {
                                                    System.out.println("\t Vui lòng nhập đúng mã topping!");
                                                    continue;
                                                } else {
                                                    tplist.add(tp);
                                                    System.out.println("\tĐã thêm topping thành công!");
                                                }

                                                while (true) {
                                                    Function.clearScreen();
                                                    System.out.println(
                                                            "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                    System.out.printf("\t| %-87s |%n",
                                                            "Bạn có muốn thêm topping nào nữa không?");
                                                    System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                    System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                    System.out.println(
                                                            "\t==========================================================================================");
                                                    System.out.print("\tNhập lựa chọn của bạn: ");
                                                    str = sc.nextLine();

                                                    if (Function.isEmpty(str)) {
                                                        System.out.println("\tVui lòng không để trống !");
                                                        continue;
                                                    }
                                                    if (!Function.isTrueNumber(str)) {
                                                        System.out.println("\tVui lòng nhập số !");
                                                        continue;
                                                    }

                                                    switch (str) {
                                                        case "1":
                                                            isWantTopping = true;
                                                            break;

                                                        case "2":
                                                            isWantTopping = false;
                                                            break;

                                                        default:
                                                            System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                            try {
                                                                Thread.sleep(1500);
                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            continue;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }

                                        }
                                        order.getDanhSachTopping().add(tplist);
                                        order.xuatThongTin();

                                        while (true) {
                                            // Function.clearScreen();
                                            System.out.println(
                                                    "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                            System.out.printf("\t| %-87s |%n",
                                                    "Bạn có muốn đặt thêm món nước nào nữa không?");
                                            System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                            System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                            System.out.println(
                                                    "\t==========================================================================================");
                                            System.out.print("\tNhập lựa chọn của bạn: ");
                                            str = sc.nextLine();

                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tVui lòng không để trống !");
                                                continue;
                                            }
                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("\tVui lòng nhập số !");
                                                continue;
                                            }

                                            switch (str) {
                                                case "1":
                                                    isWantDrink = true;
                                                    break;

                                                case "2":
                                                    isWantDrink = false;
                                                    break;

                                                default:
                                                    System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                    try {
                                                        Thread.sleep(1500);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    continue;
                                            }
                                            break;
                                        }
                                    }

                                    while (true) {
                                        Function.clearScreen();
                                        order.xuatThongTin();
                                        System.out.println(
                                                "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                        System.out.printf("\t| %-87s |%n", "Mời bạn xác nhận đơn hàng");
                                        System.out.printf("\t| %-5s %-81s |%n", "1.", "Xác nhận");
                                        System.out.printf("\t| %-5s %-81s |%n", "2.", "Không xác nhận");
                                        System.out.println(
                                                "\t==========================================================================================");
                                        System.out.print("\tNhập lựa chọn của bạn: ");
                                        str = sc.nextLine();

                                        if (Function.isEmpty(str)) {
                                            System.out.println("\tVui lòng không để trống !");
                                            continue;
                                        }
                                        if (!Function.isTrueNumber(str)) {
                                            System.out.println("\tVui lòng nhập số !");
                                            continue;
                                        }

                                        switch (str) {
                                            case "1":
                                                double tongTien = order.tinhTongTien();
                                                double tienKhachDua;
                                                while (true) {
                                                    // System.out.println("\tSố tiền phải trả là: " + tongTien);
                                                    System.out.printf("\tSố tiền phải trả là:  %.0f VNĐ%n", tongTien);
                                                    System.out.print("\tMời nhập số tiền khách đưa: ");
                                                    str = sc.nextLine();

                                                    if (Function.isEmpty(str)) {
                                                        System.out.println("\tVui lòng không để trống!");
                                                        continue;
                                                    }

                                                    if (!Function.isTrueNumber(str)) {
                                                        System.out.println("\tVui lòng nhập số!");
                                                        continue;
                                                    }

                                                    if (Double.parseDouble(str) < tongTien) {
                                                        System.out.println(
                                                                "\tQuý khách đưa không đủ tiền. Vui lòng nhập lại!");
                                                        continue;
                                                    }

                                                    tienKhachDua = Double.parseDouble(str);
                                                    break;
                                                }

                                                hd = new HoaDon(temp, nvtmp, order, tongTien, tienKhachDua);
                                                nvtmp.setSoBillDaXuLy(nvtmp.getSoBillDaXuLy() + 1);
                                                nvtmp.setTongTienDaXuLy(nvtmp.getTongTienDaXuLy() + tongTien);
                                                if (temp.IsMember()) {
                                                    temp.getMemberCard().point((int) tongTien);
                                                }
                                                break;

                                            case "2":
                                                continue loop2;

                                            default:
                                                System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                try {
                                                    Thread.sleep(1500);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                continue;
                                        }
                                        break;
                                    }

                                    break;
                                }

                                hd.xuatThongTin();
                                qlHoaDon.billList.add(hd);
                                qlHoaDon.writeAll();
                                qlNhanVien.writeFile();
                                qlKhachHang.writeAll();
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                str = sc.nextLine();
                                // xử lí phần chọn nước
                                break;
                            } else {
                                System.out.println("\tKhông còn bàn trống!");
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        } else if (number == 3) {
                            System.out.println("\tCảm ơn bạn đã sử dụng dịch vụ của chúng tôi !");
                            break;
                        } else {
                            System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("\tVui lòng nhập số !");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public static void selectTwo() {
        Scanner sc = new Scanner(System.in);
        String str;

        loop: while (true) {
            Function.clearScreen();
            System.out.println(
                    "\t=============================[Chức năng người Dùng mang đi]===============================");
            System.out.printf("\t| %-87s |%n", "Chọn chức năng");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Bắt đầu đặt món");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Quay lại trang trước");
            System.out.println(
                    "\t==========================================================================================");
            System.out.print("\tNhập lựa chọn của bạn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tVui lòng nhập số !");
                continue;
            }

            switch (str) {
                case "1":
                    loop1: while (true) {
                        Function.clearScreen();
                        System.out.println(
                                "\t=============================[Chức năng người Dùng mang đi]===============================");
                        System.out.printf("\t| %-87s |%n", "Bạn đã từng mua hàng ở Spoce Coffee Shop chưa?");
                        System.out.printf("\t| %-5s %-81s |%n", "1.", "Rồi");
                        System.out.printf("\t| %-5s %-81s |%n", "2.", "Chưa từng");
                        System.out.printf("\t| %-5s %-81s |%n", "3.", "Quay lại trang trước");
                        System.out.println(
                                "\t==========================================================================================");
                        System.out.print("\tNhập lựa chọn của bạn: ");
                        str = sc.nextLine();

                        if (Function.isEmpty(str)) {
                            System.out.println("\tVui lòng không để trống !");
                            continue;
                        }

                        if (!Function.isTrueNumber(str)) {
                            System.out.println("\tVui lòng nhập số !");
                            continue;
                        }

                        switch (str) {
                            case "1":
                                while (true) {
                                    System.out.print("\tNhập mã khách hàng của bạn: ");
                                    str = sc.nextLine();

                                    if (Function.isEmpty(str)) {
                                        System.out.println("\tVui lòng không để trống !");
                                        continue;
                                    }

                                    if (Function.isTrueNumber(str)) {
                                        System.out.println("\tVui lòng nhập chữ !");
                                        continue;
                                    }

                                    KhachHang temp = null;

                                    qlKhachHang.customerList.clear();
                                    qlKhachHang.Init();
                                    for (KhachHang kh : qlKhachHang.customerList) {
                                        if (kh.getCustomerID().equals(str) && kh instanceof KHMangDi) {
                                            temp = kh;
                                            break;
                                        }
                                    }

                                    if (temp == null) {
                                        System.out.println("\tKhông tìm thấy khách hàng!");
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        continue loop1;
                                    } else {
                                        Function.clearScreen();
                                        temp.xuatThongTin();

                                        System.out.print("\tEnter để tiếp tục!");
                                        str = sc.nextLine();

                                        HoaDon hd = null;
                                        loop22: while (true) {
                                            NhanVienThuNgan nvtmp = null;
                                            ArrayList<NhanVienThuNgan> nvtnList = new ArrayList<>();
                                            for (Nhanvien nv : qlNhanVien.nhanVienList) {
                                                if (nv instanceof NhanVienThuNgan) {
                                                    nvtnList.add((NhanVienThuNgan) nv);
                                                }
                                            }

                                            Random rd = new Random();
                                            nvtmp = nvtnList.get(rd.nextInt(nvtnList.size() - 0));

                                            boolean isWantDrink = true;
                                            ThucDon order = new ThucDon();

                                            while (isWantDrink) {
                                                // boolean wantHot = false, wantCold = false, wantSugar = false,
                                                // wantMilk = false;

                                                Function.clearScreen();
                                                qlNuocUong.inDanhSach();
                                                System.out.print("\tNhập ID nước bạn muốn đặt: ");
                                                str = sc.nextLine();

                                                if (Function.isEmpty(str)) {
                                                    System.out.println("\tVui lòng không để trống !");
                                                    try {
                                                        Thread.sleep(1500);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    continue;
                                                }

                                                NuocUong nuocuong = null;
                                                for (NuocUong drink : qlNuocUong.getWaterList()) {
                                                    if (drink.getId().equals(str)) {
                                                        nuocuong = drink;
                                                        break;
                                                    }
                                                }

                                                if (nuocuong == null) {
                                                    System.out.println("\tID không đúng, vui lòng nhập lại!");
                                                    continue;
                                                } else {
                                                    order.getDanhSachNuocUong().add(nuocuong);
                                                }

                                                // chọn size nước uống
                                                while (true) {
                                                    int countOption = 1;

                                                    Function.clearScreen();
                                                    System.out.println(
                                                            "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                    System.out.printf("\t| %-87s |%n",
                                                            "Mời bạn chọn size cho món nước " + nuocuong.getId());
                                                    for (String key : nuocuong.getSizePrice().keySet()) {
                                                        System.out.printf("\t| %-5s %-81s |%n", countOption++ + ".",
                                                                key);
                                                    }
                                                    System.out.println(
                                                            "\t==========================================================================================");
                                                    System.out.print("\tNhập lựa chọn của bạn: ");
                                                    str = sc.nextLine();

                                                    if (Function.isEmpty(str)) {
                                                        System.out.println("\tVui lòng không để trống !");
                                                        continue;
                                                    }

                                                    if (!Function.isTrueNumber(str)) {
                                                        System.out.println("\tVui lòng nhập số !");
                                                        continue;
                                                    }

                                                    if (countOption - 1 == 3) {
                                                        switch (str) {
                                                            case "1":
                                                                order.getSize().add("S");
                                                                break;

                                                            case "2":
                                                                order.getSize().add("L");
                                                                break;

                                                            case "3":
                                                                order.getSize().add("M");
                                                                break;

                                                            default:
                                                                System.out.println("\tVui lòng chọn từ 1 đến 3!");
                                                                continue;
                                                        }
                                                    } else if (countOption - 1 == 4) {
                                                        switch (str) {
                                                            case "1":
                                                                order.getSize().add("S");
                                                                break;

                                                            case "2":
                                                                order.getSize().add("M");
                                                                break;

                                                            case "3":
                                                                order.getSize().add("L");
                                                                break;

                                                            case "4":
                                                                order.getSize().add("XL");
                                                                break;

                                                            default:
                                                                System.out.println("\tVui lòng chọn từ 1 đến 4!");
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                }

                                                // yêu cầu nóng, đá, sữa, đường
                                                ArrayList<Boolean> trangThai = new ArrayList<>(); // mảng lưu trạng thái
                                                                                                  // của món nước hiện
                                                                                                  // tại
                                                trangThai.add(false); // đá
                                                trangThai.add(false); // nóng
                                                trangThai.add(false); // đường
                                                trangThai.add(false); // sữa
                                                loop20: while (true) {
                                                    Function.clearScreen();
                                                    System.out.println(
                                                            "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                    System.out.printf("\t| %-87s |%n", "Bạn có yêu cầu gì cho món nước "
                                                            + nuocuong.getId() + " không?");
                                                    System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                    System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                    System.out.println(
                                                            "\t==========================================================================================");
                                                    System.out.print("\tNhập lựa chọn của bạn: ");
                                                    str = sc.nextLine();

                                                    if (Function.isEmpty(str)) {
                                                        System.out.println("\tVui lòng không để trống !");
                                                        continue;
                                                    }

                                                    if (!Function.isTrueNumber(str)) {
                                                        System.out.println("\tVui lòng nhập số !");
                                                        continue;
                                                    }

                                                    switch (str) {
                                                        case "1":
                                                            loop21: while (true) {
                                                                Function.clearScreen();
                                                                System.out.println(
                                                                        "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                                System.out.printf("\t| %-87s |%n",
                                                                        "Bạn muốn yêu cầu gì cho món nước "
                                                                                + nuocuong.getId() + "?");
                                                                System.out.printf("\t| %-5s %-81s |%n", "1.", "Đá");
                                                                System.out.printf("\t| %-5s %-81s |%n", "2.", "Nóng");
                                                                System.out.printf("\t| %-5s %-81s |%n", "3.", "Đường");
                                                                System.out.printf("\t| %-5s %-81s |%n", "4.", "Sữa");
                                                                System.out.printf("\t| %-5s %-81s |%n", "5.",
                                                                        "Quay về trang trước");
                                                                System.out.println(
                                                                        "\t==========================================================================================");
                                                                System.out.print("\tNhập lựa chọn của bạn: ");
                                                                str = sc.nextLine();

                                                                if (Function.isEmpty(str)) {
                                                                    System.out.println("\tVui lòng không để trống !");
                                                                    continue;
                                                                }

                                                                if (!Function.isTrueNumber(str)) {
                                                                    System.out.println("\tVui lòng nhập số !");
                                                                    continue;
                                                                }

                                                                switch (str) {
                                                                    case "1":
                                                                        if (nuocuong.isCold()) {
                                                                            trangThai.set(0, true);
                                                                        } else {
                                                                            System.out.println(
                                                                                    "\tMón nước này không được thêm đá!");
                                                                            try {
                                                                                Thread.sleep(1500);
                                                                            } catch (InterruptedException e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                            continue;
                                                                        }
                                                                        break;

                                                                    case "2":
                                                                        if (nuocuong.isHot()) {
                                                                            trangThai.set(1, true);
                                                                        } else {
                                                                            System.out.println(
                                                                                    "\tMón nước này không được uống nóng!");
                                                                            try {
                                                                                Thread.sleep(1500);
                                                                            } catch (InterruptedException e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                            continue;
                                                                        }
                                                                        break;

                                                                    case "3":
                                                                        if (nuocuong.isSugar()) {
                                                                            trangThai.set(2, true);
                                                                        } else {
                                                                            System.out.println(
                                                                                    "\tMón nước này không được bỏ đường!");
                                                                            try {
                                                                                Thread.sleep(1500);
                                                                            } catch (InterruptedException e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                            continue;
                                                                        }
                                                                        break;

                                                                    case "4":
                                                                        if (nuocuong.isMilk()) {
                                                                            trangThai.set(3, true);
                                                                        } else {
                                                                            System.out.println(
                                                                                    "\tMón nước này không được thêm sữa!");
                                                                            try {
                                                                                Thread.sleep(1500);
                                                                            } catch (InterruptedException e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                            continue;
                                                                        }
                                                                        break;

                                                                    case "5":
                                                                        continue loop20;

                                                                    default:
                                                                        System.out
                                                                                .println("\tVui lòng chọn từ 1 đến 5!");
                                                                        continue;
                                                                }

                                                                while (true) {
                                                                    Function.clearScreen();
                                                                    System.out.println(
                                                                            "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                                    System.out.printf("\t| %-87s |%n",
                                                                            "Bạn còn yêu cầu gì thêm cho món nước "
                                                                                    + nuocuong.getId() + " không?");
                                                                    System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                                    System.out.printf("\t| %-5s %-81s |%n", "2.",
                                                                            "Không");
                                                                    System.out.println(
                                                                            "\t==========================================================================================");
                                                                    System.out.print("\tNhập lựa chọn của bạn: ");
                                                                    str = sc.nextLine();

                                                                    if (Function.isEmpty(str)) {
                                                                        System.out
                                                                                .println("\tVui lòng không để trống !");
                                                                        continue;
                                                                    }

                                                                    if (!Function.isTrueNumber(str)) {
                                                                        System.out.println("\tVui lòng nhập số !");
                                                                        continue;
                                                                    }

                                                                    switch (str) {
                                                                        case "1":
                                                                            continue loop21;

                                                                        case "2":
                                                                            break;

                                                                        default:
                                                                            System.out.println(
                                                                                    "\tVui lòng chọn từ 1 đến 2!");
                                                                            try {
                                                                                Thread.sleep(1500);
                                                                            } catch (InterruptedException e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                            continue;
                                                                    }
                                                                    break;
                                                                }

                                                                break;
                                                            }
                                                            break;

                                                        case "2":
                                                            break;

                                                        default:
                                                            System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                            try {
                                                                Thread.sleep(1500);
                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            continue;
                                                    }

                                                    break;
                                                }
                                                order.getTrangThaiNuocUong().add(trangThai);

                                                boolean isWantTopping;
                                                while (true) {
                                                    Function.clearScreen();
                                                    System.out.println(
                                                            "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                    System.out.printf("\t| %-87s |%n",
                                                            "Bạn có muốn thêm topping cho món nước " + nuocuong.getId()
                                                                    + " không?");
                                                    System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                    System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                    System.out.println(
                                                            "\t==========================================================================================");
                                                    System.out.print("\tNhập lựa chọn của bạn: ");
                                                    str = sc.nextLine();

                                                    if (Function.isEmpty(str)) {
                                                        System.out.println("\tVui lòng không để trống !");
                                                        continue;
                                                    }

                                                    if (!Function.isTrueNumber(str)) {
                                                        System.out.println("\tVui lòng nhập số !");
                                                        continue;
                                                    }

                                                    switch (str) {
                                                        case "1":
                                                            isWantTopping = true;
                                                            break;

                                                        case "2":
                                                            isWantTopping = false;
                                                            break;

                                                        default:
                                                            System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                                                            try {
                                                                Thread.sleep(1500);
                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            continue;
                                                    }

                                                    break;
                                                }

                                                ArrayList<Topping> tplist = new ArrayList<>();
                                                while (isWantTopping) {
                                                    Function.clearScreen();
                                                    if (nuocuong.getTopping().size() == 0) {
                                                        System.out.println("\tNước uống này không được thêm topping!");
                                                        System.out.print("\tEnter để tiếp tục!");
                                                        str = sc.nextLine();
                                                        break;
                                                    }
                                                    nuocuong.printToppingOfDrink();
                                                    while (true) {
                                                        System.out.print("\tNhập ID topping bạn muốn đặt: ");
                                                        str = sc.nextLine();

                                                        if (Function.isEmpty(str)) {
                                                            System.out.println("\t Vui lòng không để trống!");
                                                            continue;
                                                        }

                                                        Topping tp = qlTopping.getToppingByID(str);
                                                        if (tp == null) {
                                                            System.out.println("\t Vui lòng nhập đúng mã topping!");
                                                            continue;
                                                        } else {
                                                            tplist.add(tp);
                                                            System.out.println("\tĐã thêm topping thành công!");
                                                        }

                                                        while (true) {
                                                            Function.clearScreen();
                                                            System.out.println(
                                                                    "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                            System.out.printf("\t| %-87s |%n",
                                                                    "Bạn có muốn thêm topping nào nữa không?");
                                                            System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                            System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                            System.out.println(
                                                                    "\t==========================================================================================");
                                                            System.out.print("\tNhập lựa chọn của bạn: ");
                                                            str = sc.nextLine();

                                                            if (Function.isEmpty(str)) {
                                                                System.out.println("\tVui lòng không để trống !");
                                                                continue;
                                                            }
                                                            if (!Function.isTrueNumber(str)) {
                                                                System.out.println("\tVui lòng nhập số !");
                                                                continue;
                                                            }

                                                            switch (str) {
                                                                case "1":
                                                                    isWantTopping = true;
                                                                    break;

                                                                case "2":
                                                                    isWantTopping = false;
                                                                    break;

                                                                default:
                                                                    System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                                    try {
                                                                        Thread.sleep(1500);
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    continue;
                                                            }
                                                            break;
                                                        }
                                                        break;
                                                    }

                                                }
                                                order.getDanhSachTopping().add(tplist);

                                                while (true) {
                                                    Function.clearScreen();
                                                    System.out.println(
                                                            "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                    System.out.printf("\t| %-87s |%n",
                                                            "Bạn có muốn đặt thêm món nước nào nữa không?");
                                                    System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                    System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                    System.out.println(
                                                            "\t==========================================================================================");
                                                    System.out.print("\tNhập lựa chọn của bạn: ");
                                                    str = sc.nextLine();

                                                    if (Function.isEmpty(str)) {
                                                        System.out.println("\tVui lòng không để trống !");
                                                        continue;
                                                    }
                                                    if (!Function.isTrueNumber(str)) {
                                                        System.out.println("\tVui lòng nhập số !");
                                                        continue;
                                                    }

                                                    switch (str) {
                                                        case "1":
                                                            isWantDrink = true;
                                                            break;

                                                        case "2":
                                                            isWantDrink = false;
                                                            break;

                                                        default:
                                                            System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                            try {
                                                                Thread.sleep(1500);
                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            continue;
                                                    }
                                                    break;
                                                }
                                            }

                                            while (true) {
                                                Function.clearScreen();
                                                order.xuatThongTin();
                                                System.out.println(
                                                        "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                System.out.printf("\t| %-87s |%n", "Mời bạn xác nhận đơn hàng");
                                                System.out.printf("\t| %-5s %-81s |%n", "1.", "Xác nhận");
                                                System.out.printf("\t| %-5s %-81s |%n", "2.", "Không xác nhận");
                                                System.out.println(
                                                        "\t==========================================================================================");
                                                System.out.print("\tNhập lựa chọn của bạn: ");
                                                str = sc.nextLine();

                                                if (Function.isEmpty(str)) {
                                                    System.out.println("\tVui lòng không để trống !");
                                                    continue;
                                                }
                                                if (!Function.isTrueNumber(str)) {
                                                    System.out.println("\tVui lòng nhập số !");
                                                    continue;
                                                }

                                                switch (str) {
                                                    case "1":
                                                        double tongTien = order.tinhTongTien();
                                                        double tienKhachDua;
                                                        while (true) {
                                                            // System.out.println("\tSố tiền phải trả là: " + tongTien);
                                                            System.out.printf("\tSố tiền phải trả là:  %.0f VNĐ%n",
                                                                    tongTien);
                                                            System.out.print("\tMời nhập số tiền khách đưa: ");
                                                            str = sc.nextLine();

                                                            if (Function.isEmpty(str)) {
                                                                System.out.println("\tVui lòng không để trống!");
                                                                continue;
                                                            }

                                                            if (!Function.isTrueNumber(str)) {
                                                                System.out.println("\tVui lòng nhập số!");
                                                                continue;
                                                            }

                                                            if (Double.parseDouble(str) < tongTien) {
                                                                System.out.println(
                                                                        "\tQuý khách đưa không đủ tiền. Vui lòng nhập lại!");
                                                                continue;
                                                            }

                                                            tienKhachDua = Double.parseDouble(str);
                                                            break;
                                                        }

                                                        hd = new HoaDon(temp, nvtmp, order, tongTien, tienKhachDua);
                                                        nvtmp.setSoBillDaXuLy(nvtmp.getSoBillDaXuLy() + 1);
                                                        nvtmp.setTongTienDaXuLy(nvtmp.getTongTienDaXuLy() + tongTien);
                                                        if (temp.IsMember()) {
                                                            temp.getMemberCard().point((int) tongTien);
                                                        }
                                                        break;

                                                    case "2":
                                                        continue loop22;

                                                    default:
                                                        System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                        try {
                                                            Thread.sleep(1500);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        continue;
                                                }
                                                break;
                                            }

                                            break;
                                        }

                                        hd.xuatThongTin();
                                        qlHoaDon.billList.add(hd);
                                        qlHoaDon.writeAll();
                                        qlNhanVien.writeFile();
                                        qlKhachHang.writeAll();
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        str = sc.nextLine();
                                    }

                                    break;
                                }
                                break;

                            case "2":
                                while (true) {
                                    KhachHang temp = new KHMangDi();
                                    temp.nhapThongTin();
                                    qlKhachHang.customerList.clear();
                                    qlKhachHang.Init();
                                    qlKhachHang.customerList.add(temp);
                                    qlKhachHang.writeAll();

                                    Function.clearScreen();
                                    temp.xuatThongTin();

                                    System.out.print("\tEnter để tiếp tục!");
                                    str = sc.nextLine();

                                    HoaDon hd = null;
                                    loop22: while (true) {
                                        NhanVienThuNgan nvtmp = null;
                                        ArrayList<NhanVienThuNgan> nvtnList = new ArrayList<>();
                                        for (Nhanvien nv : qlNhanVien.nhanVienList) {
                                            if (nv instanceof NhanVienThuNgan) {
                                                nvtnList.add((NhanVienThuNgan) nv);
                                            }
                                        }

                                        Random rd = new Random();
                                        nvtmp = nvtnList.get(rd.nextInt(nvtnList.size() - 0));

                                        boolean isWantDrink = true;
                                        ThucDon order = new ThucDon();

                                        while (isWantDrink) {
                                            // boolean wantHot = false, wantCold = false, wantSugar = false, wantMilk =
                                            // false;

                                            Function.clearScreen();
                                            qlNuocUong.inDanhSach();
                                            System.out.print("\tNhập ID nước bạn muốn đặt: ");
                                            str = sc.nextLine();

                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tVui lòng không để trống !");
                                                try {
                                                    Thread.sleep(1500);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                continue;
                                            }

                                            NuocUong nuocuong = null;
                                            for (NuocUong drink : qlNuocUong.getWaterList()) {
                                                if (drink.getId().equals(str)) {
                                                    nuocuong = drink;
                                                    break;
                                                }
                                            }

                                            if (nuocuong == null) {
                                                System.out.println("\tID không đúng, vui lòng nhập lại!");
                                                continue;
                                            } else {
                                                order.getDanhSachNuocUong().add(nuocuong);
                                            }

                                            // chọn size nước uống
                                            while (true) {
                                                int countOption = 1;

                                                Function.clearScreen();
                                                System.out.println(
                                                        "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                System.out.printf("\t| %-87s |%n",
                                                        "Mời bạn chọn size cho món nước " + nuocuong.getId());
                                                for (String key : nuocuong.getSizePrice().keySet()) {
                                                    System.out.printf("\t| %-5s %-81s |%n", countOption++ + ".", key);
                                                }
                                                System.out.println(
                                                        "\t==========================================================================================");
                                                System.out.print("\tNhập lựa chọn của bạn: ");
                                                str = sc.nextLine();

                                                if (Function.isEmpty(str)) {
                                                    System.out.println("\tVui lòng không để trống !");
                                                    continue;
                                                }

                                                if (!Function.isTrueNumber(str)) {
                                                    System.out.println("\tVui lòng nhập số !");
                                                    continue;
                                                }

                                                if (countOption - 1 == 3) {
                                                    switch (str) {
                                                        case "1":
                                                            order.getSize().add("S");
                                                            break;

                                                        case "2":
                                                            order.getSize().add("L");
                                                            break;

                                                        case "3":
                                                            order.getSize().add("M");
                                                            break;

                                                        default:
                                                            System.out.println("\tVui lòng chọn từ 1 đến 3!");
                                                            continue;
                                                    }
                                                } else if (countOption - 1 == 4) {
                                                    switch (str) {
                                                        case "1":
                                                            order.getSize().add("S");
                                                            break;

                                                        case "2":
                                                            order.getSize().add("M");
                                                            break;

                                                        case "3":
                                                            order.getSize().add("L");
                                                            break;

                                                        case "4":
                                                            order.getSize().add("XL");
                                                            break;

                                                        default:
                                                            System.out.println("\tVui lòng chọn từ 1 đến 4!");
                                                            continue;
                                                    }
                                                }
                                                break;
                                            }

                                            // yêu cầu nóng, đá, sữa, đường
                                            ArrayList<Boolean> trangThai = new ArrayList<>(); // mảng lưu trạng thái của
                                                                                              // món nước hiện tại
                                            trangThai.add(false); // đá
                                            trangThai.add(false); // nóng
                                            trangThai.add(false); // đường
                                            trangThai.add(false); // sữa
                                            loop20: while (true) {
                                                Function.clearScreen();
                                                System.out.println(
                                                        "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                System.out.printf("\t| %-87s |%n", "Bạn có yêu cầu gì cho món nước "
                                                        + nuocuong.getId() + " không?");
                                                System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                System.out.println(
                                                        "\t==========================================================================================");
                                                System.out.print("\tNhập lựa chọn của bạn: ");
                                                str = sc.nextLine();

                                                if (Function.isEmpty(str)) {
                                                    System.out.println("\tVui lòng không để trống !");
                                                    continue;
                                                }

                                                if (!Function.isTrueNumber(str)) {
                                                    System.out.println("\tVui lòng nhập số !");
                                                    continue;
                                                }

                                                switch (str) {
                                                    case "1":
                                                        loop21: while (true) {
                                                            Function.clearScreen();
                                                            System.out.println(
                                                                    "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                            System.out.printf("\t| %-87s |%n",
                                                                    "Bạn muốn yêu cầu gì cho món nước "
                                                                            + nuocuong.getId() + "?");
                                                            System.out.printf("\t| %-5s %-81s |%n", "1.", "Đá");
                                                            System.out.printf("\t| %-5s %-81s |%n", "2.", "Nóng");
                                                            System.out.printf("\t| %-5s %-81s |%n", "3.", "Đường");
                                                            System.out.printf("\t| %-5s %-81s |%n", "4.", "Sữa");
                                                            System.out.printf("\t| %-5s %-81s |%n", "5.",
                                                                    "Quay về trang trước");
                                                            System.out.println(
                                                                    "\t==========================================================================================");
                                                            System.out.print("\tNhập lựa chọn của bạn: ");
                                                            str = sc.nextLine();

                                                            if (Function.isEmpty(str)) {
                                                                System.out.println("\tVui lòng không để trống !");
                                                                continue;
                                                            }

                                                            if (!Function.isTrueNumber(str)) {
                                                                System.out.println("\tVui lòng nhập số !");
                                                                continue;
                                                            }

                                                            switch (str) {
                                                                case "1":
                                                                    if (nuocuong.isCold()) {
                                                                        trangThai.set(0, true);
                                                                    } else {
                                                                        System.out.println(
                                                                                "\tMón nước này không được thêm đá!");
                                                                        try {
                                                                            Thread.sleep(1500);
                                                                        } catch (InterruptedException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        continue;
                                                                    }
                                                                    break;

                                                                case "2":
                                                                    if (nuocuong.isHot()) {
                                                                        trangThai.set(1, true);
                                                                    } else {
                                                                        System.out.println(
                                                                                "\tMón nước này không được uống nóng!");
                                                                        try {
                                                                            Thread.sleep(1500);
                                                                        } catch (InterruptedException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        continue;
                                                                    }
                                                                    break;

                                                                case "3":
                                                                    if (nuocuong.isSugar()) {
                                                                        trangThai.set(2, true);
                                                                    } else {
                                                                        System.out.println(
                                                                                "\tMón nước này không được bỏ đường!");
                                                                        try {
                                                                            Thread.sleep(1500);
                                                                        } catch (InterruptedException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        continue;
                                                                    }
                                                                    break;

                                                                case "4":
                                                                    if (nuocuong.isMilk()) {
                                                                        trangThai.set(3, true);
                                                                    } else {
                                                                        System.out.println(
                                                                                "\tMón nước này không được thêm sữa!");
                                                                        try {
                                                                            Thread.sleep(1500);
                                                                        } catch (InterruptedException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        continue;
                                                                    }
                                                                    break;

                                                                case "5":
                                                                    continue loop20;

                                                                default:
                                                                    System.out.println("\tVui lòng chọn từ 1 đến 5!");
                                                                    continue;
                                                            }

                                                            while (true) {
                                                                Function.clearScreen();
                                                                System.out.println(
                                                                        "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                                System.out.printf("\t| %-87s |%n",
                                                                        "Bạn còn yêu cầu gì thêm cho món nước "
                                                                                + nuocuong.getId() + " không?");
                                                                System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                                System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                                System.out.println(
                                                                        "\t==========================================================================================");
                                                                System.out.print("\tNhập lựa chọn của bạn: ");
                                                                str = sc.nextLine();

                                                                if (Function.isEmpty(str)) {
                                                                    System.out.println("\tVui lòng không để trống !");
                                                                    continue;
                                                                }

                                                                if (!Function.isTrueNumber(str)) {
                                                                    System.out.println("\tVui lòng nhập số !");
                                                                    continue;
                                                                }

                                                                switch (str) {
                                                                    case "1":
                                                                        continue loop21;

                                                                    case "2":
                                                                        break;

                                                                    default:
                                                                        System.out
                                                                                .println("\tVui lòng chọn từ 1 đến 2!");
                                                                        try {
                                                                            Thread.sleep(1500);
                                                                        } catch (InterruptedException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        continue;
                                                                }
                                                                break;
                                                            }

                                                            break;
                                                        }
                                                        break;

                                                    case "2":
                                                        break;

                                                    default:
                                                        System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                        try {
                                                            Thread.sleep(1500);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        continue;
                                                }

                                                break;
                                            }
                                            order.getTrangThaiNuocUong().add(trangThai);

                                            boolean isWantTopping;
                                            while (true) {
                                                Function.clearScreen();
                                                System.out.println(
                                                        "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                System.out.printf("\t| %-87s |%n",
                                                        "Bạn có muốn thêm topping cho món nước " + nuocuong.getId()
                                                                + " không?");
                                                System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                System.out.println(
                                                        "\t==========================================================================================");
                                                System.out.print("\tNhập lựa chọn của bạn: ");
                                                str = sc.nextLine();

                                                if (Function.isEmpty(str)) {
                                                    System.out.println("\tVui lòng không để trống !");
                                                    continue;
                                                }

                                                if (!Function.isTrueNumber(str)) {
                                                    System.out.println("\tVui lòng nhập số !");
                                                    continue;
                                                }

                                                switch (str) {
                                                    case "1":
                                                        isWantTopping = true;
                                                        break;

                                                    case "2":
                                                        isWantTopping = false;
                                                        break;

                                                    default:
                                                        System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                                                        try {
                                                            Thread.sleep(1500);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        continue;
                                                }

                                                break;
                                            }

                                            ArrayList<Topping> tplist = new ArrayList<>();
                                            while (isWantTopping) {
                                                Function.clearScreen();
                                                if (nuocuong.getTopping().size() == 0) {
                                                    System.out.println("\tNước uống này không được thêm topping!");
                                                    System.out.print("\tEnter để tiếp tục!");
                                                    str = sc.nextLine();
                                                    break;
                                                }
                                                nuocuong.printToppingOfDrink();
                                                while (true) {
                                                    System.out.print("\tNhập ID topping bạn muốn đặt: ");
                                                    str = sc.nextLine();

                                                    if (Function.isEmpty(str)) {
                                                        System.out.println("\t Vui lòng không để trống!");
                                                        continue;
                                                    }

                                                    Topping tp = qlTopping.getToppingByID(str);
                                                    if (tp == null) {
                                                        System.out.println("\t Vui lòng nhập đúng mã topping!");
                                                        continue;
                                                    } else {
                                                        tplist.add(tp);
                                                        System.out.println("\tĐã thêm topping thành công!");
                                                    }

                                                    while (true) {
                                                        Function.clearScreen();
                                                        System.out.println(
                                                                "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                        System.out.printf("\t| %-87s |%n",
                                                                "Bạn có muốn thêm topping nào nữa không?");
                                                        System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                        System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                        System.out.println(
                                                                "\t==========================================================================================");
                                                        System.out.print("\tNhập lựa chọn của bạn: ");
                                                        str = sc.nextLine();

                                                        if (Function.isEmpty(str)) {
                                                            System.out.println("\tVui lòng không để trống !");
                                                            continue;
                                                        }
                                                        if (!Function.isTrueNumber(str)) {
                                                            System.out.println("\tVui lòng nhập số !");
                                                            continue;
                                                        }

                                                        switch (str) {
                                                            case "1":
                                                                isWantTopping = true;
                                                                break;

                                                            case "2":
                                                                isWantTopping = false;
                                                                break;

                                                            default:
                                                                System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                                try {
                                                                    Thread.sleep(1500);
                                                                } catch (InterruptedException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                continue;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }

                                            }
                                            order.getDanhSachTopping().add(tplist);

                                            while (true) {
                                                Function.clearScreen();
                                                System.out.println(
                                                        "\t=============================[Chức năng người Dùng mang đi]===============================");
                                                System.out.printf("\t| %-87s |%n",
                                                        "Bạn có muốn đặt thêm món nước nào nữa không?");
                                                System.out.printf("\t| %-5s %-81s |%n", "1.", "Có");
                                                System.out.printf("\t| %-5s %-81s |%n", "2.", "Không");
                                                System.out.println(
                                                        "\t==========================================================================================");
                                                System.out.print("\tNhập lựa chọn của bạn: ");
                                                str = sc.nextLine();

                                                if (Function.isEmpty(str)) {
                                                    System.out.println("\tVui lòng không để trống !");
                                                    continue;
                                                }
                                                if (!Function.isTrueNumber(str)) {
                                                    System.out.println("\tVui lòng nhập số !");
                                                    continue;
                                                }

                                                switch (str) {
                                                    case "1":
                                                        isWantDrink = true;
                                                        break;

                                                    case "2":
                                                        isWantDrink = false;
                                                        break;

                                                    default:
                                                        System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                        try {
                                                            Thread.sleep(1500);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        continue;
                                                }
                                                break;
                                            }
                                        }

                                        while (true) {
                                            Function.clearScreen();
                                            order.xuatThongTin();
                                            System.out.println(
                                                    "\t=============================[Chức năng người Dùng mang đi]===============================");
                                            System.out.printf("\t| %-87s |%n", "Mời bạn xác nhận đơn hàng");
                                            System.out.printf("\t| %-5s %-81s |%n", "1.", "Xác nhận");
                                            System.out.printf("\t| %-5s %-81s |%n", "2.", "Không xác nhận");
                                            System.out.println(
                                                    "\t==========================================================================================");
                                            System.out.print("\tNhập lựa chọn của bạn: ");
                                            str = sc.nextLine();

                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tVui lòng không để trống !");
                                                continue;
                                            }
                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("\tVui lòng nhập số !");
                                                continue;
                                            }

                                            switch (str) {
                                                case "1":
                                                    double tongTien = order.tinhTongTien();
                                                    double tienKhachDua;
                                                    while (true) {
                                                        // System.out.println("\tSố tiền phải trả là: " + tongTien);
                                                        System.out.printf("\tSố tiền phải trả là:  %.0f VNĐ%n",
                                                                tongTien);
                                                        System.out.print("\tMời nhập số tiền khách đưa: ");
                                                        str = sc.nextLine();

                                                        if (Function.isEmpty(str)) {
                                                            System.out.println("\tVui lòng không để trống!");
                                                            continue;
                                                        }

                                                        if (!Function.isTrueNumber(str)) {
                                                            System.out.println("\tVui lòng nhập số!");
                                                            continue;
                                                        }

                                                        if (Double.parseDouble(str) < tongTien) {
                                                            System.out.println(
                                                                    "\tQuý khách đưa không đủ tiền. Vui lòng nhập lại!");
                                                            continue;
                                                        }

                                                        tienKhachDua = Double.parseDouble(str);
                                                        break;
                                                    }

                                                    hd = new HoaDon(temp, nvtmp, order, tongTien, tienKhachDua);
                                                    nvtmp.setSoBillDaXuLy(nvtmp.getSoBillDaXuLy() + 1);
                                                    nvtmp.setTongTienDaXuLy(nvtmp.getTongTienDaXuLy() + tongTien);
                                                    if (temp.IsMember()) {
                                                        temp.getMemberCard().point((int) tongTien);
                                                    }
                                                    break;

                                                case "2":
                                                    continue loop22;

                                                default:
                                                    System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                                                    try {
                                                        Thread.sleep(1500);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    continue;
                                            }
                                            break;
                                        }

                                        break;
                                    }

                                    hd.xuatThongTin();
                                    qlHoaDon.billList.add(hd);
                                    qlHoaDon.writeAll();
                                    qlNhanVien.writeFile();
                                    qlKhachHang.writeAll();
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    str = sc.nextLine();
                                    break;
                                }
                                break;

                            case "3":
                                continue loop;

                            default:
                                System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                                continue;
                        }

                        break;
                    }
                    break;

                case "2":
                    break;

                default:
                    System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                    continue;
            }

            break;
        }
    }

    public static void Menu() {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        String str;
        while (true) {
            Function.clearScreen();
            System.out.println(
                    "\t===================================[Trang người dùng]====================================");
            System.out.printf("\t| %-87s |%n", "Bạn là người dùng gì ?");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Dùng tại chỗ");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Dùng mang đi");
            System.out.printf("\t| %-5s %-81s |%n", "3.", "Thoát chương trình");
            System.out.println(
                    "\t==========================================================================================");
            System.out.print("\tNhập lựa chọn của bạn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    number = Integer.parseInt(str);
                    if (number >= 1 && number <= 3) {
                        if (number == 1) {
                            selectOne();
                            continue;
                        } else if (number == 2) {
                            selectTwo();
                            continue;
                        } else if (number == 3) {
                            // Thoát
                            System.out.println("\tCảm ơn bạn đã sử dụng dịch vụ của chúng tôi !");
                            break;
                        } else {
                            System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                            continue;
                        }
                    } else {
                        System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                        continue;
                    }
                } else {
                    System.out.println("\tVui lòng nhập số !");
                    continue;
                }
            }
        }
    }

    public static void main(String[] args) {
        Menu();
    }
}
