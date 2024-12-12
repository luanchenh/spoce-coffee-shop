/**
 *
 */
package FinalJava;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
        File file = new File("../File/accounts.txt");
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
        
    }

    public void Menu() {
        inThongTin();
    }


    public static void main(String[] args) {
        QLAccount qlAccount = new QLAccount();
        qlAccount.Init();
        qlAccount.Menu();
    }
}
