package FinalJava;

import java.util.Scanner;

/**
 * Hiển thị bảng hệ thống nước uống
 */

@SuppressWarnings("resource")
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            // In tiêu đề bảng
            System.out.println(
                    "\t==========================================================================================");
            System.out.printf("\t| %-87s |%n", "HỆ THỐNG NƯỚC UỐNG SPOCE COFFEE SHOP");
            System.out.println(
                    "\t==========================================================================================");

            // In các chức năng
            System.out.printf("\t| %-87s |%n", "Chọn chức năng:");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Người dùng (Khách hàng)");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Quản trị viên (Admin)");
            System.out.printf("\t| %-5s %-81s |%n", "3.", "Thoát chương trình");

            // Đóng bảng
            System.out.println(
                    "\t==========================================================================================");
            System.out.print("\tNhập lựa chọn của bạn: ");
            str = sc.nextLine();
            System.out.println(str);
        }
    }
}
