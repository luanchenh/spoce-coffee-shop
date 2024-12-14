package Ban;

import Utils.Function;
import Utils.INhap;
import Utils.IXuat;
import java.io.File;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Ban implements INhap, IXuat {
    private String tableID; // ID của bàn (B1, B2, B3)
    private int numOfCustomersOfTable; // Số lượng người bàn có thể chứa (2,4,8 chỗ)
    private boolean status; // Tình trạng bàn: true là có người ngồi, false là chưa có người ngồi
    private String tableView;

    public static int numOfTables = readNumberOfTablesFromFile(); // Số lượng bàn trong quán sẽ được đọc từ file

    // Phương thức khởi tạo phi tham số
    // ID của bàn sẽ được gán tự động và tình trạng bàn mặc định sẽ là chưa có người
    // ngồi (false)
    public Ban() {
        this.tableID = makeTableID();
        this.status = false;
    }

    // Phương thức khởi tạo với số chỗ ngồi của bàn
    public Ban(int numOfCustomersOfTable) {
        this.tableID = makeTableID();
        this.numOfCustomersOfTable = numOfCustomersOfTable;
        this.status = false;
    }

    // Phương thức khởi tạo dùng lúc đọc từ file
    public Ban(String tableID, int numOfCustomersOfTable, boolean status, String tableView) {
        this.tableID = tableID;
        this.numOfCustomersOfTable = numOfCustomersOfTable;
        this.status = status;
        this.tableView = tableView;
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

    public String getTableView() {
        return this.tableView;
    }

    // Setter
    public void setCustomerPerTable(int numOfCustomersOfTable) {
        this.numOfCustomersOfTable = numOfCustomersOfTable;
    }

    public void setTableStatus(boolean status) {
        this.status = status;
    }

    public void setTableView(String tableView) {
        this.tableView = tableView;
    }

    // Phương thức nhập thông tin bàn
    @Override
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;

        System.out.println("\tNhập thông tin bàn có ID: " + this.tableID);
        while (true) {
            System.out.println("\n\tSố chỗ ngồi của bàn");
            System.out.println("\t1. 2 chỗ");
            System.out.println("\t2. 4 chỗ");
            System.out.println("\t3. 8 chỗ");
            System.out.print("\t=> Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
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
                    System.out.println("\tLựa chọn không hợp lệ!");
                    continue;
            }

            break;
        }

        while (true) {
            System.out.println("\n\tTình trạng của bàn");
            System.out.println("\t1. Đang trống");
            System.out.println("\t2. Đã có người ngồi");
            System.out.print("\t=> Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
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
                    System.out.println("\tLựa chọn không hợp lệ!");
                    continue;
            }
            break;
        }

        while (true) {
            System.out.println("\n\tChọn view của bàn");
            System.out.println("\t1. View ban công");
            System.out.println("\t2. View trong nhà");
            System.out.println("\t3. View thư viện");
            System.out.print("\t=> Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được rỗng!");
                continue;
            }
            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
                continue;
            }
            switch (Integer.parseInt(str)) {
                case 1:
                    this.tableView = "Ban công";
                    break;
                case 2:
                    this.tableView = "Trong nhà";
                    break;
                case 3:
                    this.tableView = "Thư viện";
                    break;
                default:
                    System.out.println("\tLựa chọn không hợp lệ!");
                    continue;
            }
        }
    }

    // Phương thức để xuất thông tin bàn
    @Override
    public void xuatThongTin() {
        // System.out.println("Thông tin bàn");
        // System.out.println(" ID của bàn: " + this.tableID);
        // System.out.println(" Số lượng chỗ ngồi: " + this.numOfCustomersOfTable);
        // String tableStatus = this.status ? "Đã có người ngồi" : "Đang trống";
        // System.out.println(" Tình trạng: " + tableStatus);

        System.out.printf("\t| %-23s %-93s |%n", "Thông tin bàn", "");
        System.out.printf("\t|     %-19s %-93s |%n", "ID của bàn:", this.tableID);
        System.out.printf("\t|     %-19s %-93s |%n", "Số lượng chỗ ngồi:", this.numOfCustomersOfTable);
        String tableStatus = this.status ? "Đã có người ngồi" : "Đang trống";
        System.out.printf("\t|     %-19s %-93s |%n", "Tình trạng:", tableStatus);
        System.out.printf("\t|     %-19s %-93s |%n", "View của bàn:", this.tableView);
    }

    public void xuatThongTin1Ban() {
        System.out.println(
                "\t=========================================================================================================================");
        System.out.printf("\t| %-23s %-93s |%n", "ID của bàn:", this.tableID);
        System.out.printf("\t| %-23s %-93s |%n", "Số lượng chỗ ngồi:", this.numOfCustomersOfTable);
        String tableStatus = this.status ? "Đã có người ngồi" : "Đang trống";
        System.out.printf("\t| %-23s %-93s |%n", "Tình trạng:", tableStatus);
        System.out.printf("\t| %-23s %-93s |%n", "View của bàn:", this.tableView);
        System.out.println(
                "\t=========================================================================================================================");
    }

    // Phương thức để sửa thông tin của bàn
    public void suaThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            System.out.println("\n\tSửa thông tin bàn có ID: " + this.tableID);
            System.out.println("\t1. Số lượng chỗ ngồi");
            System.out.println("\t2. Tình trạng");
            System.out.println("\t3. View của bàn");
            System.out.println("\t4. Quay lại");
            System.out.print("\t=> Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
                continue;
            }

            switch (Integer.parseInt(str)) {
                case 2:
                    while (true) {
                        System.out.println("\n\tTình trạng bàn:");
                        System.out.println("\t1. Đang trống");
                        System.out.println("\t2. Đã có người ngồi");
                        System.out.print("\t=> Nhập lựa chọn: ");
                        str = sc.nextLine();

                        if (Function.isEmpty(str)) {
                            System.out.println("\tLựa chọn không được rỗng!");
                            continue;
                        }

                        if (!Function.isTrueNumber(str)) {
                            System.out.println("\tLựa chọn phải là số!");
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
                                System.out.println("\tLựa chọn không hợp lệ");
                                continue;
                        }
                        break;
                    }
                    break;

                case 1:
                    while (true) {
                        System.out.println("\n\tSố chỗ ngồi:");
                        System.out.println("\t1. 2 chỗ");
                        System.out.println("\t2. 4 chỗ");
                        System.out.println("\t3. 8 chỗ");
                        System.out.print("\t=> Nhập lựa chọn: ");
                        str = sc.nextLine();

                        if (Function.isEmpty(str)) {
                            System.out.println("\tLựa chọn không được rỗng!");
                            continue;
                        }

                        if (!Function.isTrueNumber(str)) {
                            System.out.println("\tLựa chọn phải là số!");
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
                                System.out.println("\tLựa chọn không hợp lệ");
                                continue;
                        }
                        break;
                    }
                    break;

                case 3:
                    while (true) {
                        System.out.println("\n\tChọn view của bàn");
                        System.out.println("\t1. View ban công");
                        System.out.println("\t2. View trong nhà");
                        System.out.println("\t3. View thư viện");
                        System.out.print("\t=> Nhập lựa chọn: ");
                        str = sc.nextLine();
                        if (Function.isEmpty(str)) {
                            System.out.println("\tLựa chọn không được rỗng!");
                            continue;
                        }
                        if (!Function.isTrueNumber(str)) {
                            System.out.println("\tLựa chọn phải là số!");
                            continue;
                        }
                        switch (Integer.parseInt(str)) {
                            case 1:
                                this.tableView = "Ban công";
                                break;
                            case 2:
                                this.tableView = "Trong nhà";
                                break;
                            case 3:
                                this.tableView = "Thư viện";
                                break;
                            default:
                                System.out.println("\tLựa chọn không hợp lệ!");
                                continue;
                        }
                        break;
                    }
                    break;

                case 4:
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
        return this.tableID + "|" + this.numOfCustomersOfTable + "|" + status + "|" + this.tableView;
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
        if (!this.status) {
            status = "Trống";
        } else {
            status = "Đã có người ngồi";
        }
        System.out.printf("\t| %-20s | %-25s | %-25s | %-25s |\n", this.tableID, seats, this.tableView, status);
    }

    // Phương thức dùng để tìm ra bàn phù hợp với số lượng khách đồng thời cập nhật
    // vào file
    public static Ban findTable(int numOfCustomers) {
        Ban table = null;
        QLBan ql = new QLBan();
        ql.Init();

        if (numOfCustomers >= 1 && numOfCustomers <= 2) {
            for (Ban ban : ql.tableList) {
                if (ban.getCustomerPerTable() == 2 && !ban.getTableStatus()) {
                    table = ban;
                    ql.tableList.get(ql.tableList.indexOf(ban)).setTableStatus(true);
                    break;
                }
            }

            if (table == null) {
                for (Ban ban : ql.tableList) {
                    if (ban.getCustomerPerTable() == 4 && !ban.getTableStatus()) {
                        table = ban;
                        ql.tableList.get(ql.tableList.indexOf(ban)).setTableStatus(true);
                        break;
                    }
                }
            }

            if (table == null) {
                for (Ban ban : ql.tableList) {
                    if (ban.getCustomerPerTable() == 8 && !ban.getTableStatus()) {
                        table = ban;
                        ql.tableList.get(ql.tableList.indexOf(ban)).setTableStatus(true);
                        break;
                    }
                }
            }
        } else if (numOfCustomers >= 3 && numOfCustomers <= 4) {
            for (Ban ban : ql.tableList) {
                if (ban.getCustomerPerTable() == 4 && !ban.getTableStatus()) {
                    table = ban;
                    ql.tableList.get(ql.tableList.indexOf(ban)).setTableStatus(true);
                    break;
                }
            }

            if (table == null) {
                for (Ban ban : ql.tableList) {
                    if (ban.getCustomerPerTable() == 8 && !ban.getTableStatus()) {
                        table = ban;
                        ql.tableList.get(ql.tableList.indexOf(ban)).setTableStatus(true);
                        break;
                    }
                }
            }
        } else if (numOfCustomers >= 5 && numOfCustomers <= 8) {
            for (Ban ban : ql.tableList) {
                if (ban.getCustomerPerTable() == 8 && !ban.getTableStatus()) {
                    table = ban;
                    ql.tableList.get(ql.tableList.indexOf(ban)).setTableStatus(true);
                    break;
                }
            }
        }

        ql.writeAll();
        return table;
    }

    // Phương thức dùng để hủy 1 bàn (chuyển trạng thái từ "đang được sử dụng" sang
    // "trống") và cập nhật vào file
    public void cancelTable() {
        QLBan ql = new QLBan();
        ql.Init();
        for (Ban ban : ql.tableList) {
            if (ban.getTableID().equals(this.tableID)) {
                ql.tableList.get(ql.tableList.indexOf(ban)).setTableStatus(false);
                break;
            }
        }
        ql.writeAll();
    }

    public static String makeTableID() {
        QLBan ql = new QLBan();
        ql.Init();
        int idNumber = 1;
        boolean isValid = false;

        while (!isValid) {
            boolean isSame = false;
            for (Ban ban : ql.tableList) {
                if (Function.getNumberFromTableID(ban.getTableID()) == idNumber) {
                    idNumber++;
                    isSame = true;
                    break;
                }
            }

            if (!isSame) {
                isValid = true;
            }
        }

        return "B" + idNumber;
    }
}
