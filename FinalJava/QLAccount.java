/**
 *
 */
package FinalJava;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Utils.Function;


@SuppressWarnings("resource")
public class QLAccount {
    ArrayList<Account> listAccount = new ArrayList<Account>();


    public QLAccount() {
        listAccount = new ArrayList<Account>();
    }
    public QLAccount(ArrayList<Account> listAccount) {
        this.listAccount = listAccount;
    }

    public ArrayList<Account> getListAccount() {
        return listAccount;
    }
    public void setListAccount(ArrayList<Account> listAccount) {
        this.listAccount = listAccount;
    }

    public void Init() {
        File file = new File("./File/accounts.txt");
        if (!file.exists()) {
            return;
        }
        try(Scanner rd = new Scanner(file)) {
            while (rd.hasNextLine()) {
                String line = rd.nextLine();
                String[] parts = line.split("\\|");
                Account account = new Account(parts[0], parts[1], parts[2], parts[3]);
                listAccount.add(account);
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file: " + e);
        }
    }

    public void inThongTin() {
        System.out.println("\t+============================================================================================+");
        System.out.println("\t|                                      Danh sách tài khoản                                   |");
        System.out.println("\t+============================================================================================+");
        System.out.printf("\t| %-20s | %-20s | %-20s | %-20s |%n", "Tài khoản", "Mật khẩu", "Loại", "Đang liên kết với");
        for (Account account : listAccount) {
            account.xuatThongTin();
        }
    }
    public void resetPassword() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tên tài khoản: ");
        String username = scanner.nextLine();

        boolean accountFound = false;
        for (Account account : listAccount) {
            if (account.getUsername().equals(username)) {
                accountFound = true;

                System.out.print("\tNhập mật khẩu hiện tại: ");
                String currentPassword = scanner.nextLine();

                if (account.getPassword().equals(currentPassword)) {
                    System.out.print("\tNhập mật khẩu mới: ");
                    String newPassword = scanner.nextLine();

                    System.out.print("\tXác nhận lại mật khẩu mới: ");
                    String confirmPassword = scanner.nextLine();

                    if (newPassword.equals(confirmPassword)) {
                        account.setPassword(newPassword);
                        System.out.println("\tMật khẩu đã được đổi thành công!");
                        this.saveToFile();
                    } else {
                        System.out.println("\tMật khẩu xác nhận không khớp. Vui lòng thử lại.");
                    }
                } else {
                    System.out.println("\tMật khẩu hiện tại không đúng. Vui lòng thử lại.");
                }
                break;
            }
        }

        if (!accountFound) {
            System.out.println("\tTài khoản không tồn tại.");
        }
    }
    public void saveToFile() {
    File file = new File("./File/accounts.txt");
    try (PrintWriter writer = new PrintWriter(file)) {
        for (Account account : listAccount) {
            writer.println(account.getUsername() + "|" + account.getPassword() + "|" + account.getType() + "|" + account.getIDLink());
        }
    } catch (Exception e) {
        System.out.println("Lỗi ghi file: " + e);
    }
}


    public void Menu() {
        this.Init();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n\t===== QUẢN LÝ TÀI KHOẢN =====");
            System.out.println("\t1. Hiển thị thông tin tài khoản");
            System.out.println("\t2. Đổi mật khẩu");
            System.out.println("\t0. Thoát chương trình quản lí");
            System.out.print("\tNhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    inThongTin();
                    break;
                case 2:
                    resetPassword();
                    break;
                case 0:
                     System.out.println("\tThoát chương trình thành công !");
                    Function.clearScreen();
                            break;
                default:
                    System.out.println("\tLựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 0);
    }


    public static void main(String[] args) {
        QLAccount qlAccount = new QLAccount();
        qlAccount.Init();
        qlAccount.Menu();
    }
}
