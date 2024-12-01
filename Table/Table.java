package Table;

import Utils.Function;
import java.util.Scanner;

public class Table {
    private String tableID; // ID của bàn được sinh tự động và có dạng B1, B2, B3,...
    private String tableName; // Tên của bàn. Ví dụ : Bàn ngoài trời, Bàn phòng máy lạnh,...
    private String tableType; // Loại bàn (số chỗ của bàn) có các giá trị 2, 4, 8
    private boolean tableStatus; // Trạng thái của bàn có các giá trị true (bàn đang trống), false (bàn đang được sử dụng)
    private static int numOfTable = 0; // Số lượng bàn trong cửa hàng

    public Table() {
        numOfTable++;
        this.tableID = "B" + numOfTable;
    }

    public Table(String tableName, String tableType) {
        numOfTable++;
        this.tableID = "B" + numOfTable;
        this.tableName = tableName;
        this.tableType = tableType;
        this.tableStatus = true;
    }

    public String getTableID() {
        return this.tableID;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getTableType() {
        return this.tableType;
    }

    public boolean getTableStatus() {
        return this.tableStatus;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public void setTableStatus(boolean tableStatus) {
        this.tableStatus = tableStatus;
    }

    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[SetInfo] Thêm thông tin cho [Bàn] có ID: " + this.tableID);

        // Nhập tên của bàn
        while (true) { 
            String str = sc.nextLine();
            System.out.print("[Input] Nhập tên cho bàn: ");

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Tên bàn không được rỗng!");
                continue;
            }

            if (Function.isTrueNumber(str)) {
                System.out.println("[Warning] Tên bàn không được là số!");
                continue;
            }

            this.tableName = Function.normalizeName(str);
            break;
        }


        // Nhập loại bàn (2, 4, 8 chỗ)
        while (true) {
            System.out.print("[Input] Nhập loại của bàn: ");
            System.out.println("[1]. 2 chỗ");
            System.out.println("[2]. 4 chỗ");
            System.out.println("[3]. 8 chỗ");
            String str = sc.nextLine();
            
            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Loại của bàn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("[Warning] Lựa chọn phải là số!");
                continue;
            }

            switch (str) {
                case "1":
                this.tableType = "2";
                break;

                case "2":
                this.tableType = "4";
                break;

                case "3":
                this.tableType = "8";
                break;

                default:
                System.out.println("[Warning] Lựa chọn không hợp lệ!");
            }
            break;
        }

        // Bàn khi mới tạo thì tình trạng bàn sẽ có giá trị true (Đang trống)
        this.tableStatus = true;
    }

    public void displayInfo() {
        System.out.println("========== [Thông tin bàn] ==========");
        System.out.println("[ID]: " + this.tableID);
        System.out.println("[Tên bàn]: " + this.tableName);
        System.out.println("[Loại bàn]: " + this.tableType + " chỗ");
        if (this.tableStatus) {
            System.out.println("[Tình trạng]: Đang trống");
        } else {
            System.out.println("[Tình trạng]: Đang được sử dụng");
        }
    }

    public void modifyInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("========== [Sửa thông tin bàn] ==========");
        loop:
        while (true) { 
            System.out.println("[1]: Sửa 1 thông tin");         
            System.out.println("[2]: Sửa tất cả thông tin");
            System.out.println("[3]: Quay lại");
            System.out.print("[Input] Mời bạn chọn: ");
            String select = sc.nextLine();

            if (Function.isEmpty(select)) {
                System.out.println("[Warning] Lựa chọn không được rỗng!");
                continue loop;
            }

            if (select.compareTo("1") != 0 && select.compareTo("2") != 0 && select.compareTo("3") != 0) {
                System.out.println("[Warning] Lựa chọn không hợp lệ!");
                continue loop;
            }

            if (select.compareTo("1") == 0) {
                loop1:
                while (true) {
                    System.out.println("[1]: Tên bàn");   
                    System.out.println("[2]: Loại bàn");   
                    System.out.println("[3]: Tình trạng bàn");   
                    System.out.println("[4]: Quay lại");
                    System.out.print("[Input] Mời bạn chọn: ");
                    select = sc.nextLine();

                    if (Function.isEmpty(select)) {
                        System.out.println("[Warning] Lựa chọn không được rỗng!");
                        continue loop1;
                    }
            
                    if (select.compareTo("1") != 0 && select.compareTo("2") != 0 && select.compareTo("3") != 0 && select.compareTo("4") != 0) {
                        System.out.println("[Warning] Lựa chọn không hợp lệ!");
                        continue loop1;
                    }

                    if (select.compareTo("1") == 0) {
                        loop2:
                        while (true) {
                            System.out.print("[Input] Mời bạn nhập: ");
                            select = sc.nextLine();

                            if (Function.isEmpty(select)) {
                                System.out.println("[Warning] Tên bàn không được rỗng!");
                                continue loop2;
                            }

                            this.tableName = Function.normalizeName(select);
                            break loop;
                        }
                    } else if (select.compareTo("2") == 0) {
                        loop2:
                        while (true) {
                            System.out.println("[1]: 2 chỗ");
                            System.out.println("[2]: 4 chỗ");
                            System.out.println("[3]: 8 chỗ");
                            System.out.print("[Input] Mời bạn nhập: ");
                            select = sc.nextLine();

                            if (Function.isEmpty(select)) {
                                System.out.println("[Warning] Lựa chọn không được rỗng!");
                                continue loop2;
                            }
            
                            if (select.compareTo("1") != 0 && select.compareTo("2") != 0 && select.compareTo("3") != 0) {
                                System.out.println("[Warning] Lựa chọn không hợp lệ!");
                                continue loop2;
                            }
            
                            switch (select) {
                                case "1":
                                this.tableType = "2";
                                break;
            
                                case "2":
                                this.tableType = "4";
                                break;
            
                                case "3":
                                this.tableType = "8";
                                break;
                            }
                            break loop;
                        }
                    } else if (select.compareTo("3") == 0) {
                        loop2:
                        while (true) {
                            System.out.println("[1]: Trống");
                            System.out.println("[2]: Đang được sử dụng");
                            System.out.print("[Input] Mời bạn nhập: ");
                            select = sc.nextLine();

                            while (Function.isEmpty(select)) {
                                System.out.println("[Warning] Lựa chọn không được rỗng!");
                                continue loop2;
                            }
                
                            while (select.compareTo("1") != 0 && select.compareTo("2") != 0 && select.compareTo("3") != 0) {
                                System.out.println("[Warning] Lựa chọn không hợp lệ!");
                                continue loop2;
                            }
                
                            switch (select) {
                                case "1":
                                this.tableStatus = true;
                                break;
                    
                                case "2":
                                this.tableStatus = false;
                                break;
                            }
                            break loop;
                        }
                    } else if (select.compareTo("4") == 0) {
                        continue loop;
                    }
                }
            } else if (select.compareTo("2") == 0) {
                loop1:
                while (true) {
                    System.out.print("[Input] Mời bạn nhập tên bàn: ");
                    select = sc.nextLine();

                    if (Function.isEmpty(select)) {
                        System.out.println("[Warning] Tên bàn không được rỗng!");
                        continue loop1;
                    }

                    this.tableName = Function.normalizeName(select);
                    break loop1;
                }

                loop1:
                while (true) {
                    System.out.println("[1]: 2 chỗ");
                    System.out.println("[2]: 4 chỗ");
                    System.out.println("[3]: 8 chỗ");
                    System.out.print("[Input] Mời bạn nhập loại bàn: ");
                    select = sc.nextLine();

                    if (Function.isEmpty(select)) {
                        System.out.println("[Warning] Lựa chọn không được rỗng!");
                        continue loop1;
                    }
    
                    if (select.compareTo("1") != 0 && select.compareTo("2") != 0 && select.compareTo("3") != 0) {
                        System.out.println("[Warning] Lựa chọn không hợp lệ!");
                        continue loop1;
                    }
    
                    switch (select) {
                        case "1":
                        this.tableType = "2";
                        break;
    
                        case "2":
                        this.tableType = "4";
                        break;
    
                        case "3":
                        this.tableType = "8";
                        break;
                    }
                    break loop1;
                }

                loop1:
                while (true) { 
                    System.out.println("[1]: Trống");
                    System.out.println("[2]: Đang được sử dụng");
                    System.out.print("[Input] Mời bạn nhập: ");
                    select = sc.nextLine();

                    while (Function.isEmpty(select)) {
                        System.out.println("[Warning] Lựa chọn không được rỗng!");
                        continue loop1;
                    }
        
                    while (select.compareTo("1") != 0 && select.compareTo("2") != 0 && select.compareTo("3") != 0) {
                        System.out.println("[Warning] Lựa chọn không hợp lệ!");
                        continue loop1;
                    }
        
                    switch (select) {
                        case "1":
                        this.tableStatus = true;
                        break;
            
                        case "2":
                        this.tableStatus = false;
                        break;
                    }
                    break loop1;
                }

                break loop;
            } else if (select.compareTo("3") == 0) {
                break loop;
            }
        }
    }

    public static void main(String[] args) {
        Table t1 = new Table();
        System.out.println(t1.tableName);

        t1.modifyInfo();

        System.out.println(t1.tableName);
    }
}
