import java.util.Scanner;

/**
 * Hiển thị bảng hệ thống nước uống
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // In tiêu đề bảng
        System.out.println("\t==========================================================================================");
        System.out.printf("\t| %-87s |%n", "HỆ THỐNG NƯỚC UỐNG SPOCE COFFEE SHOP");
        System.out.println("\t==========================================================================================");

        // In các chức năng
        System.out.printf("\t| %-87s |%n", "Chọn chức năng:");
        System.out.printf("\t| %-5s %-81s |%n", "1.", "Quản lý nhân viên");
        System.out.printf("\t| %-5s %-81s |%n", "2.", "Quản lý khách hàng");

        // Đóng bảng
        System.out.println("\t==========================================================================================");
    }
}
