package FinalJava;

import Ban.Ban;
import Ban.QLBan;
import KhachHang.KHMangDi;
import KhachHang.KHTaiCho;
import KhachHang.KhachHang;
import KhachHang.QLKhachHang;
import NuocUong.QLNuocUong;
import Utils.Function;
import java.io.File;
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
    static {
        qlNuocUong.Init();
        qlBan.Init();
        qlKhachHang.Init();
    }

    public static KhachHang getInfoCustomer() {
        KhachHang temp = null;
        File customerFile = new File("../File/customers.txt");
        try (Scanner rd = new Scanner(customerFile)) {
            while (rd.hasNextLine() && temp != null) {
                String line = rd.nextLine();
                String[] parts = line.split("\\|");
                if (parts[1].equals(IDLink)) {
                    if (parts[0].equals("1")) {
                        temp = new KHMangDi();
                    }
                    if (parts[0].equals("0")) {
                        temp = new KHTaiCho();
                    }

                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("\tLỗi: " + e.getMessage());
        }
        return temp;

    }

    // public static void choseMenu() {
    //     Scanner sc = new Scanner(System.in);
    //     String str;

    //     while (true) {
    //         Function.clearScreen();
    //         qlNuocUong.inDanhSach();
    //         System.out.print("\tNhập ID nước bạn muốn đặt: ");
    //         str = sc.nextLine();

    //         if (Function.isEmpty(str)) {
    //             System.out.println("\tVui lòng không để trống !");
    //             continue;
    //         }

    //         // xử lí phần chọn nước
    //         break;
    //     }
    // }

    public static void selectCustomer() {

    }

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
                                    temp.setTable(Ban.findTable(temp.getNumberOfCustomer()));
                                    break;
                                }

                                if (temp.getTable() == null) {
                                    System.out.println("\tKhông có bàn phù hợp!");

                                } else {
                                    while (true) {
                                        Function.clearScreen();
                                        qlNuocUong.inDanhSach();
                                        System.out.println("\tBàn của bạn có ID: " + temp.getTable().getTableID());
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
        String str;
        int number = 0;
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

                        } else if (number == 2) {
                            selectTwo();

                        } else if (number == 3) {
                            // Thoát
                            System.out.println("\tCảm ơn bạn đã sử dụng dịch vụ của chúng tôi !");
                            return;
                        } else {
                            System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                        }
                    } else {
                        System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                    }
                } else {
                    System.out.println("\tVui lòng nhập số !");
                }
            }
        }
    }

    public static void main(String[] args) {

        Menu();
    }
}
