/**
 *
 */
package FinalJava;

import java.io.File;
import java.util.Scanner;

import Utils.Function;
import Utils.INhap;


@SuppressWarnings("resource")
public class Account implements INhap {
    private String username;
    private String password;
    private String type;
    private String IDLink;

    public Account() {
        this.username = "";
        this.password = "";
        this.type = ""; // 1 là khách hàng 2 là admin
        this.IDLink = ""; // Mã
    }

    public Account(String username, String password, String type, String IDLink) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.IDLink = IDLink;
    }

    public Account(String type) {
        this.type = type;
        this.username = "";
        this.password = "";
        this.IDLink = "";
    }

    // Hàm check tài khoản tồn tại
    public boolean checkValidAccount() {
        boolean check = true;
        File accountFile = new File("../File/accounts.txt");
        try (Scanner rd = new Scanner(accountFile)) {
            while (rd.hasNextLine()) {
                String line = rd.nextLine();
                String[] parts = line.split("\\|");
                if (parts[2].equals(this.type) && parts[0].equals(this.username)) {
                    check = false;
                }
            }
        } catch (Exception e) {
            System.out.println("\tLỗi: " + e.getMessage());
        }
        return check;
    }

    @Override
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;
        if (this.type == "") {
            while (true) {
                System.out.println("\tLoại tài khoản: ");
                System.out.println("\t1. Tài khoản người dùng (Customer)");
                System.out.println("\t2. Tài khoản quản trị viên");
                System.out.println("\t3. Thoát");
                System.out.print("\tNhập lựa chọn: ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống !");
                } else {
                    if (Function.isTrueNumber(str)) {
                        int number = Integer.parseInt(str);
                        if (number >= 1 && number <= 3) {
                            if (number == 1) {
                                this.type = "1";
                                break;
                            } else if (number == 2) {
                                this.type = "2";
                                break;
                            } else if (number == 3) {
                                return;
                            } else {
                                System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                            }
                        }
                    } else {
                        System.out.println("\tVui lòng nhập số !");
                    }
                }
            }
        }
        while (true) {
            // Nhập tài khoản và mật khẩu
            while (true) {
                System.out.print("\tNhập tài khoản: ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống !");
                } else {
                    this.username = str;
                    break;
                }
            }
            while (true) {
                System.out.print("\tNhập mật khẩu: ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống !");
                } else {
                    this.password = str;
                    break;
                }
            }
            if (this.checkValidAccount()) {
                break;
            }
            else {
                System.out.println("\tTài khoản đã tồn tại !");
            }
        }
        // ID link là mã khách hàng không nhập mà tự thêm vào
    }

    public void Login() {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            System.out.print("\tNhập tài khoản: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                this.username = str;
                break;
            }
        }
        while (true) {
            System.out.print("\tNhập mật khẩu: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                this.password = str;
                break;
            }
        }
    }

    public String checkLogin() {
        String IDLink = "";
        File accountFile = new File("../File/accounts.txt");
        try (Scanner rd = new Scanner(accountFile)) {
            while (rd.hasNextLine()) {
                String line = rd.nextLine();
                String[] parts = line.split("\\|");
                if (parts[0].equals(this.username) && parts[1].equals(this.password)) {
                    IDLink = parts[3];
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("\tLỗi: " + e.getMessage());
        }
        return IDLink;
    }

    public String makeString() {
        return this.username + "|" + this.password + "|" + this.type + "|" + this.IDLink;
    }

}
