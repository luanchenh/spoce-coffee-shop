/**
 *
 */
package FinalJava;

import Ban.Ban;
import Ban.QLBan;
import HoaDon.HoaDon;
import HoaDon.QLHoaDon;
import Utils.Function;
import java.util.Scanner;


@SuppressWarnings({ "resource", "unused" })
public class TraBan {


    public static QLBan qlBan = new QLBan();
    public static QLHoaDon qlHoaDon = new QLHoaDon();

    static {
        qlBan.Init();
        qlHoaDon.Init();
    }

    public static void sectionOne() {
        Function.clearScreen();
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            System.out.println(
                    "\t======================================[Trả Bàn]========================================");
            System.out.printf("\t| %-87s |%n", "Bạn muốn chức năng gì gì ?");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Nhập mã bàn");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Quay về trang trước");
            System.out.println(
                    "\t===========================================================================================");
            System.out.print("\tNhập lựa chọn của bạn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tVui lòng nhập số !");
                continue;
            }

            switch (str) {
                case "1":
                    while (true) {
                        qlBan.printTableList();
                        System.out.print("\tNhập mã bàn: ");
                        String value = sc.nextLine();
                        if (Function.isEmpty(value)) {
                            System.out.println("\tVui lòng không để trống!");
                            continue;
                        }
                        else {
                            // Đã nhập
                            boolean isExist = false;
                            for (Ban ban : qlBan.tableList) {
                                if (ban.getTableID().equals(value)) {
                                    isExist = true;
                                    ban.changeTableStatus();
                                    qlBan.writeAll();
                                    System.out.print("\tNhấn Enter để tiếp tục...");
                                    sc.nextLine();
                                    break;
                                }
                            }
                            if (isExist == false) {
                                System.out.println("\tKhông tìm thấy mã bàn!");
                                System.out.print("\tNhấn Enter để tiếp tục...");
                                sc.nextLine();
                                continue;
                            }
                            break;
                        }
                    }
                    break;

                case "2":
                    break;

                default:
                    System.out.println("\tVui lòng chọn từ 1 đến 2");
                    break;
            }
            break;
        }

    }

    public static void sectionTwo() {
        Scanner sc = new Scanner(System.in);
        String str;
        String value;
        while (true) {
            Function.clearScreen();
            System.out.println(
                    "\t======================================[Trả món]========================================");
            System.out.printf("\t| %-87s |%n", "Bạn muốn chức năng gì gì ?");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Nhập mã hoá đơn");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Quay về trang trước");
            System.out.println(
                    "\t===========================================================================================");
            System.out.print("\tNhập lựa chọn của bạn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tVui lòng nhập số !");
                continue;
            }

            switch (str) {
                case "1":
                    while (true) {
                        qlHoaDon.printHoaDonList();
                        System.out.print("\tNhập mã hoá đơn: ");
                        value = sc.nextLine();
                        if (Function.isEmpty(value)) {
                            System.out.println("\tVui lòng không để trống!");
                            continue;
                        }
                        else {
                            // Đã nhập
                            boolean isExist = false;
                            for (HoaDon hd : qlHoaDon.billList) {
                                if (hd.getMaHoaDon().equals(value)) {
                                    isExist = true;
                                    hd.changeBillStatus();
                                    qlHoaDon.writeAll();
                                    System.out.print("\tNhấn Enter để tiếp tục...");
                                    sc.nextLine();
                                    break;
                                }
                            }
                            if (isExist == false) {
                                System.out.println("\tKhông tìm thấy mã hoá đơn!");
                                System.out.print("\tNhấn Enter để tiếp tục...");
                                sc.nextLine();
                                continue;
                            }
                            break;
                        }
                    }
                    break;

                case "2":
                    break;

                default:
                    System.out.println("\tVui lòng chọn từ 1 đến 2");
                    break;
            }
            break;
        }
    }

    public static void Menu() {

        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            Function.clearScreen();
            System.out.println(
                    "\t======================================[Trả Bàn/Bill]========================================");
            System.out.printf("\t| %-87s |%n", "Bạn muốn trả gì ?");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Trả bàn (Khách hàng ra về trả bản)");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Trả món (Thay đổi bill - đã làm xong bill cho khách)");
            System.out.printf("\t| %-5s %-81s |%n", "3.", "Quay về trang trước");
            System.out.println(
                    "\t===========================================================================================");
            System.out.print("\tNhập lựa chọn của bạn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tVui lòng nhập số !");
                continue;
            }

            switch (str) {
                case "1":
                    sectionOne();
                    continue;

                case "2":
                    sectionTwo();
                    continue;
                case "3":
                    break;

                default:
                    System.out.println("\tVui lòng chọn từ 1 đến 8");
                    continue;
            }
            break;
        }
    }

}
