package FinalJava;

import java.io.File;
import java.util.Scanner;

import KhachHang.KHMangDi;
import KhachHang.KHTaiCho;
import KhachHang.KhachHang;
import KhachHang.MemberCard;
import Utils.Date;
import Utils.Function;
import NuocUong.QLNuocUong;
import Ban.QLBan;
import KhachHang.QLKhachHang;

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

    public static void writeNewUser() {

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
                            break;
                        } else if (number == 2) {
                            break;
                        } else if (number == 3) {
                            // Thoát
                            System.out.println("\tCảm ơn bạn đã sử dụng dịch vụ của chúng tôi !");
                            break;
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
        if (number == 1) {
            KhachHang temp = null;
        }
        if (number == 2) {
            Account account = new Account("1");
            account.nhapThongTin();
            System.out.println("\tĐăng ký thành công !");
            while (true) {
                System.out.println(
                        "\t===================================[Trang người dùng]====================================");
                System.out.printf("\t| %-87s |%n", "Chọn chức năng:");
                System.out.println("\t1. Người dùng dùng tại chỗ");
                System.out.println("\t2. Người dùng dùng mang đi");
                System.out.println("\t3. Thoát chương trình");
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
                                break;
                            } else if (number == 2) {
                                break;
                            } else if (number == 3) {
                                // Thoát
                                System.out.println("\tCảm ơn bạn đã sử dụng dịch vụ của chúng tôi !");
                                break;
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
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            Function.clearScreen();
            System.out.println(
                    "\t===================================[Trang người dùng]====================================");
            System.out.printf("\t| %-87s |%n", "Chọn chức năng:");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Đăng nhập tài khoản đã có");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Đăng ký nếu chưa có tài khoản");
            System.out.printf("\t| %-5s %-81s |%n", "3.", "Thoát chương trình");
            System.out.println(
                    "\t==========================================================================================");
            System.out.print("\tNhập lựa chọn của bạn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    int number = Integer.parseInt(str);
                    if (number >= 1 && number <= 3) {
                        if (number == 1) {
                            Account account = new Account("1");
                            account.Login();
                            IDLink = account.checkLogin();
                            if (IDLink != "") {
                                System.out.println("\tĐăng nhập thành công !");
                                KhachHang kh = getInfoCustomer();
                                if (kh != null) {
                                    kh.xuatThongTin();
                                } else {
                                    System.out.println("\tKhông tìm thấy khách hàng với ID: " + IDLink);
                                }
                                break;
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
                            // Thoát
                            System.out.println("\tCảm ơn bạn đã sử dụng dịch vụ của chúng tôi !");
                            break;
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
}
