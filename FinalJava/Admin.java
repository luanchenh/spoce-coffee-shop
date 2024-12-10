package FinalJava;

/**
 *
 */

import KhachHang.QLKhachHang;
import Utils.Function;
import java.util.Scanner;

public class Admin {
    public static void Menu() {
        QLKhachHang qlKhachHang = new QLKhachHang();

        Scanner sc = new Scanner(System.in);
        String str;

        while (true) { 
            Function.clearScreen();
            System.out.println(
                    "\t======================================[Trang admin]========================================");
            System.out.printf("\t| %-87s |%n", "Bạn muốn quản lý gì ?");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Quản lý khách hàng");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Quản lý nhân viên");
            System.out.printf("\t| %-5s %-81s |%n", "3.", "Quản lý nước uống");
            System.out.printf("\t| %-5s %-81s |%n", "4.", "Quản lý topping");
            System.out.printf("\t| %-5s %-81s |%n", "5.", "Quản lý bàn");
            System.out.printf("\t| %-5s %-81s |%n", "6.", "Quản lý hóa đơn");
            System.out.printf("\t| %-5s %-81s |%n", "7.", "Quay về trang trước");
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
                qlKhachHang.menuQLKhachHang();
                continue;

                case "2":
                continue;

                case "3":
                continue;

                case "4":
                continue;

                case "5":
                continue;

                case "6":
                continue;

                case "7":
                break;

                default:
                System.out.println("\tVui lòng chọn từ 1 đến 7");
                continue;
            }
            break;
        }
    }

    public static void main(String[] args) {
        Menu();
    }
}
