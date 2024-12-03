package Ban;

import Utils.INhap;
import Utils.IXuat;
import java.io.File;
import java.util.Scanner;
import Utils.Function;

public class Ban implements INhap, IXuat {
    private String tableID; // ID của bàn (B1, B2, B3)
    private int numOfCustomersOfTable; // Số lượng người bàn có thể chứa (2,4,8 chỗ)
    private boolean status; // Tình trạng bàn: true là có người ngồi, false là chưa có người ngồi

    private static int numOfTables = readNumberOfTablesFromFile(); // Số lượng bàn trong quán sẽ được đọc từ file

    // Phương thức khởi tạo phi tham số
    // ID của bàn sẽ được gán tự động và tình trạng bàn mặc định sẽ là chưa có người ngồi (false)
    public Ban() {
        this.tableID = "B" + ++numOfTables;
        this.status = false;
    }

    // Phương thức khởi tạo với số chỗ ngồi của bàn
    public Ban(int numOfCustomersOfTable) {
        this.tableID = "B" + ++numOfTables;
        this.numOfCustomersOfTable = numOfCustomersOfTable;
        this.status = false;
    }

    // Phương thức khởi tạo dùng lúc đọc từ file
    public Ban(String tableID, int numOfCustomersOfTable, boolean status) {
        this.tableID = tableID;
        this.numOfCustomersOfTable = numOfCustomersOfTable;
        this.status = status;
    }

    // Getter
    public String getTableID() {
        return this.tableID;
    }

    public int getCustomerPerTable() {
        return this.numOfCustomersOfTable;
    }

    public boolean getTableStatus() {
        return this.status;
    }

    // Setter
    public void setCustomerPerTable(int numOfCustomersOfTable) {
        this.numOfCustomersOfTable = numOfCustomersOfTable;
    }

    public void setTableStatus(boolean status) {
        this.status = status;
    }

    // Phương thức nhập thông tin bàn
    @Override
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;

        System.out.println("Nhập thông tin bàn có ID: " + this.tableID);
        while (true) { 
            System.out.println("Số chỗ ngồi của bàn");
            System.out.println("1. 2 chỗ");
            System.out.println("2. 4 chỗ");
            System.out.println("3. 8 chỗ");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Lựa chọn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("Lựa chọn phải là số!");
                continue;
            }

            switch (Integer.parseInt(str)) {
                case 1:
                this.numOfCustomersOfTable = 2;
                break;

                case 2:
                this.numOfCustomersOfTable = 4;
                break;

                case 3:
                this.numOfCustomersOfTable = 8;
                break;

                default:
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            break;
        }

        while (true) {
            System.out.println("Tình trạng của bàn");
            System.out.println("1. Đang trống");
            System.out.println("2. Đã có người ngồi");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Lựa chọn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("Lựa chọn phải là số!");
                continue;
            }

            switch (Integer.parseInt(str)) {
                case 1:
                this.status = false;
                break;

                case 2:
                this.status = true;
                break;

                default:
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }
            break;
        }
    }

    // Phương thức để xuất thông tin bàn
    @Override
    public void xuatThongTin() {
        System.out.println("===========================================================");
        System.out.println("ID của bàn: " + this.tableID);
        System.out.println("Số lượng chỗ ngồi: " + this.numOfCustomersOfTable);
        String tableStatus = this.status ? "Đã có người ngồi" : "Đang trống";
        System.out.println("Tình trạng: " + tableStatus);
    }


    // Phương thức để sửa thông tin của bàn
    public void suaThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            System.out.println("Sửa thông tin bàn có ID: " + this.tableID);
            System.out.println("1. Số lượng chỗ ngồi");
            System.out.println("2. Tình trạng");
            System.out.println("3. Quay lại");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Lựa chọn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("Lựa chọn phải là số!");
                continue;
            }

            switch (Integer.parseInt(str)) {
                case 2:
                while (true) { 
                    System.out.println("1. Đang trống");
                    System.out.println("2. Đã có người ngồi");
                    System.out.print("Nhập lựa chọn: ");
                    str = sc.nextLine();
        
                    if (Function.isEmpty(str)) {
                        System.out.println("Lựa chọn không được rỗng!");
                        continue;
                    }
        
                    if (!Function.isTrueNumber(str)) {
                        System.out.println("Lựa chọn phải là số!");
                        continue;
                    }

                    switch (Integer.parseInt(str)) {
                        case 1:
                        this.status = false;
                        break;

                        case 2:
                        this.status = true;
                        break;

                        default:
                        System.out.println("Lựa chọn không hợp lệ");
                        continue;
                    }
                    break;
                }
                break;

                case 1:
                while (true) {
                    System.out.println("1. 2 chỗ");
                    System.out.println("2. 4 chỗ");
                    System.out.println("3. 8 chỗ");
                    System.out.print("Nhập lựa chọn: ");
                    str = sc.nextLine();
        
                    if (Function.isEmpty(str)) {
                        System.out.println("Lựa chọn không được rỗng!");
                        continue;
                    }
        
                    if (!Function.isTrueNumber(str)) {
                        System.out.println("Lựa chọn phải là số!");
                        continue;
                    }

                    switch (Integer.parseInt(str)) {
                        case 1:
                        this.numOfCustomersOfTable = 2;
                        break;

                        case 2:
                        this.numOfCustomersOfTable = 4;
                        break;

                        case 3:
                        this.numOfCustomersOfTable = 8;
                        break;

                        default:
                        System.out.println("Lựa chọn không hợp lệ");
                        continue;
                    }
                    break;
                }
                break;

                case 3:
                break;

                default:
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            break; // while
        }
    }

    // Phương thức dùng để tạo chuỗi đúng định dạng để ghi vào file
    public String makeString() {
        String status = this.status ? "1" : "0";
        return this.tableID + "|" + this.numOfCustomersOfTable + "|" + status;
    }

    // Phương thức dùng để đọc số bàn có trong file
    public static int readNumberOfTablesFromFile() {
        File tableFile = new File("../File/table.txt");
        String str = null;
        try (Scanner sc = new Scanner(tableFile)) {
            while (sc.hasNextLine()) {
                str = sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Lỗi " + e.getMessage());
        }

        String[] arr = str.split("\\|");
        String result = arr[0].substring(1);
        return Integer.parseInt(result);
    }

    // Phương thức dùng để tạo chuỗi đúng định dạng để in ra màn hình
    public void printString() {
        String seats = this.numOfCustomersOfTable + " chỗ";
        String status;
        if (this.status) {
            status = "Trống";
        } else {
            status = "Đã có người ngồi";
        }
        System.out.printf("%-5s %-25s %-10s\n", this.tableID, seats, status);
    }
}
