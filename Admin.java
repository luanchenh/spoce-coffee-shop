import java.io.File;
import java.util.Scanner;

import Utils.Function;

/**
 *
 */

 @SuppressWarnings("resource")
public class Admin {
    public class AdminResgister {
        private String userName;
        private String passWord;
        @SuppressWarnings("unused")
        private boolean quanLy;

        File adminFile = new File("../File/admin.txt");
        public AdminResgister() {
            this.userName = "";
            this.passWord = "";
            this.quanLy = false;
        }

        public AdminResgister(String userName, String passWord, boolean quanLy) {
            this.userName = userName;
            this.passWord = passWord;
            this.quanLy = false;
        }

        public void createAccount() {
            Scanner sc = new Scanner(System.in);
            String str;
            boolean action = true;
            while(true) {
                while (true) {
                    System.out.println("\tNhập tài khoản: ");
                    str = sc.nextLine();
                    if (Function.isEmpty(str)) {
                        System.out.println("\tVui lòng không để trống !");
                    }
                    else {
                        this.userName = str;
                        break;
                    }
                }
                while (true) {
                    System.out.println("\tNhập mật khẩu: ");
                    str = sc.nextLine();
                    if (Function.isEmpty(str)) {
                        System.out.println("\tVui lòng không để trống !");
                    }
                    else {
                        this.passWord = str;
                        break;
                    }
                }
                while (true) {
                    System.out.println("\tLà Manage hay Contributor");
                    System.out.println("\t1. Manage - Quản lý");
                    System.out.println("\t2. Contributor - Cộng tác viên/Nhân viên");
                    System.out.print("\tNhập lựa chọn: ");
                    str = sc.nextLine();
                    if (Function.isEmpty(str)) {
                        System.out.println("\tVui lòng không để trống !");
                    }
                    else {
                        if (Function.isTrueNumber(str)) {
                            int number = Integer.parseInt(str);
                            if (number >= 1 && number <= 2) {
                                if (number == 1) {
                                    this.quanLy = true;
                                }
                                if (number == 2) {
                                    this.quanLy = false;
                                }
                                break;
                            }
                            else {
                                System.out.println("\tVui lòng chọn số từ 1 đến 2 !");
                            }
                        }
                        else {
                            System.out.println("\tVui lòng nhập số !");
                        }
                    }
                }
                // Kiểm tra sự tồn tại của tài khoản và mật khẩu
                while (true) {
                    try(Scanner rd = new Scanner(adminFile)) {
                        while (rd.hasNextLine() ) {
                            String line = rd.nextLine();
                            String[] parts = line.split("\\|");
                            // taikhoan|matkhau|quanly(true/false)
                            if (parts[0].toUpperCase().equals(this.userName)) {
                                action = false;
                            }
                        }
                        if (action) {
                            System.out.println("\tĐăng ký thành công !");
                            break;
                        }
                        else {
                            System.out.println("\tTài khoản đã tồn tại !");
                        }
                    }
                    catch(Exception e) {
                        System.out.println("\tLỗi: "+ e.getMessage());
                    }
                }
            }
        }


    }
}
