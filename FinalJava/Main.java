package FinalJava;

import Utils.Function;
import java.io.File;
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
            Function.clearScreen();
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
            System.out.print("\t=> Nhập lựa chọn của bạn: ");
            str = sc.nextLine();
            
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không bỏ trống!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tVui lòng nhập số!");
                continue;
            }

            switch (str) {
                case "1":
                NguoiDung.Menu();
                continue;

                case "2":
                String username;
                String password;
                while (true) {
                    while (true) {
                        System.out.print("\t=> Mời bạn nhập tài khoản: ");
                        username = sc.nextLine();
    
                        if (Function.isEmpty(username)) {
                            System.out.println("\tTài khoản không được rỗng!");
                            continue;
                        }
    
                        break;
                    }

                    while (true) {
                        System.out.print("\t=> Mời bạn nhập mật khẩu: ");
                        password = sc.nextLine();
    
                        if (Function.isEmpty(password)) {
                            System.out.println("\tMật khẩu không được rỗng!");
                            continue;
                        }
    
                        break;
                    }

                    File accountFile = new File("../File/accounts.txt");
                    boolean isFound = false;
                    try (Scanner sf = new Scanner(accountFile)) {
                        while (sf.hasNextLine()) {
                            str = sf.nextLine();
                            String[] arr = str.split("\\|");
                            if (username.equals(arr[0]) && password.equals(arr[1])) {
                                isFound = true;
                                if (arr[2].equals("2")) {
                                    System.out.println("\tĐăng nhập thành công!");
                                    try {
                                        Thread.sleep(2500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Admin.Menu();
                                    break;
                                } else {
                                    System.out.println("\tTài khoản không có quyền quản trị!");
                                    try {
                                        Thread.sleep(2500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                            }
                        }
                        if (!isFound) {
                            System.out.println("\tTài khoản hoặc mật khẩu không đúng!");
                        }
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        System.out.println("Lỗi: " + e.getMessage());
                    }
                    break;
                }
                continue;

                case "3":
                break;

                default:
                System.out.println("\tVui lòng nhập từ 1 đến 3!");
                continue;
            }
            break;
        }
    }
}
