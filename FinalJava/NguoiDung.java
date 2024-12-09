package FinalJava;

import Ban.Ban;
import Ban.QLBan;
import HoaDon.HoaDon;
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
    static {
        qlNuocUong.Init();
        qlBan.Init();
        qlKhachHang.Init();
        qlTopping.Init();
        qlNhanVien.Init();
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
    //     NhanVienThuNgan temp = null;
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
                    Thread.sleep(2500);
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
                                        temp = (KHTaiCho)kh;
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

                                    while (true) {
                                        boolean isWantDrink = true;
                                        ThucDon order = new ThucDon();
                                        int count = 0;

                                        while (isWantDrink) {
                                            Function.clearScreen();
                                            qlNuocUong.inDanhSach();
                                            System.out.print("\tNhập ID nước bạn muốn đặt: ");
                                            str = sc.nextLine();

                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tVui lòng không để trống !");
                                                try {
                                                    Thread.sleep(2500);
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
                                                count++;
                                            }

                                            boolean isWantTopping;
                                            while (true) {
                                                Function.clearScreen();
                                                System.out.println(
                                                    "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                System.out.printf("\t| %-87s |%n", "Bạn có muốn thêm topping cho món nước " + nuocuong.getId() + " không?");
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
                                                        Thread.sleep(2500);
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
                                                        System.out.println("\t Vui lòng nhập mã topping!");
                                                        continue;
                                                    } else {
                                                        tplist.add(tp);
                                                        System.out.println("\tĐã thêm topping thành công!");
                                                    }

                                                    while (true) {
                                                        Function.clearScreen();
                                                        System.out.println(
                                                            "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                        System.out.printf("\t| %-87s |%n", "Bạn có muốn thêm topping nào nữa không?");
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
                                                                Thread.sleep(2500);
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
                                                    "\t=============================[Chức năng người Dùng tại chỗ]===============================");
                                                System.out.printf("\t| %-87s |%n", "Bạn có muốn đặt thêm món nước nào nữa không?");
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
                                                        Thread.sleep(2500);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    continue;
                                                }
                                                break;
                                            }
                                        }

                                        NhanVienThuNgan nvtmp = null;
                                        for (Nhanvien nv : qlNhanVien.nhanVienList) {
                                            if (nv instanceof NhanVienThuNgan) {
                                                nvtmp = (NhanVienThuNgan)nv;
                                                break;
                                            }
                                        }

                                        HoaDon hoaDon = new HoaDon(temp, nvtmp, order);

                                        hoaDon.xuatThongTin();
                                        try {
                                            Thread.sleep(2500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        // xử lí phần chọn nước
                                        break;
                                    }
                                } else {
                                    System.out.println("\tKhông còn bàn trống!");
                                    try {
                                        Thread.sleep(2500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                System.out.println("\tĐăng nhập thất bại, đăng nhập lại ! !");
                                try {
                                    Thread.sleep(2500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else if (number == 2) {
                            Account account = new Account("1");
                            account.nhapThongTin();

                        } else if (number == 3) {
                            System.out.println("\tCảm ơn bạn đã sử dụng dịch vụ của chúng tôi !");
                            break;
                        } else {
                            System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                            try {
                                Thread.sleep(2500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("\tVui lòng nhập số !");
                    try {
                        Thread.sleep(2500);
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

        loop:
        while (true) {
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
                loop1:
                while (true) {
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

                            for (KhachHang kh : qlKhachHang.customerList) {
                                if (kh.getCustomerID().equals(str)) {
                                    temp = kh;
                                    break;
                                }
                            }

                            if (temp == null) {
                                System.out.println("\tKhông tìm thấy khách hàng!");
                                try {
                                    Thread.sleep(2500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                continue loop1;
                            } else {
                                while (true) {
                                    Function.clearScreen();
                                    qlNuocUong.inDanhSach();
                                    System.out.print("\tNhập ID nước bạn muốn đặt: ");
                                    str = sc.nextLine();

                                    if (Function.isEmpty(str)) {
                                        System.out.println("\tVui lòng không để trống !");
                                        try {
                                            Thread.sleep(2500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        continue;
                                    }

                                    // xử lí phần chọn nước
                                    break;
                                }
                            }

                            break;
                        }
                        break;

                        case "2":
                        while (true) {
                            KhachHang temp = new KHMangDi();
                            temp.nhapThongTin();
                            qlKhachHang.customerList.add(temp);
                            qlKhachHang.writeAll();

                            while (true) {
                                Function.clearScreen();
                                qlNuocUong.inDanhSach();
                                System.out.print("\tNhập ID nước bạn muốn đặt: ");
                                str = sc.nextLine();

                                if (Function.isEmpty(str)) {
                                    System.out.println("\tVui lòng không để trống !");
                                    try {
                                        Thread.sleep(2500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    continue;
                                }

                                // xử lí phần chọn nước
                                break;
                            }
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
