package FinalJava;

import java.io.File;
import java.util.Scanner;

import KhachHang.KHMangDi;
import KhachHang.KHTaiCho;
import KhachHang.KhachHang;
import Utils.Function;

/**
 *
 */
public class NguoiDung {

    public static String IDLink = "";

    public static KhachHang getInfoCustomer() {
        KhachHang temp = null;
        File customerFile = new File("../File/customers.txt");
        try(Scanner rd = new Scanner(customerFile)) {
            while(rd.hasNextLine() && temp != null) {
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
        }
        catch(Exception e) {
            System.out.println("\tLỗi: " + e.getMessage());
        }
        return temp;

    }

    public static void choseMenu() {

    }

    public static void selectCustomer() {
        
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
            }
            else {
                if (Function.isTrueNumber(str)) {
                    int number = Integer.parseInt(str);
                    if (number >= 1 && number <= 3) {
                        if (number == 1) {
                            Account account = new Account("1");
                            account.Login();
                            IDLink = account.checkLogin();
                            if (IDLink != "") {
                                System.out.println("\tĐăng nhập thành công !");
                                choseMenu();
                                break;
                            }
                            else {
                                System.out.println("\tĐăng nhập thất bại, đăng nhập lại ! !");
                                try {
                                    Thread.sleep(2500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        else if (number == 2) {
                            Account account = new Account("1");
                            account.nhapThongTin();

                        }
                        else if (number == 3) {
                            // Thoát
                            System.out.println("\tCảm ơn bạn đã sử dụng dịch vụ của chúng tôi !");
                            break;
                        }
                        else {
                            System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                        }
                    }
                    else {
                        System.out.println("\tVui lòng chọn từ 1 đến 2 !");
                    }
                }
                else {
                    System.out.println("\tVui lòng nhập số !");
                }
            }
        }
    }
}
