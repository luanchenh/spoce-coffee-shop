package Table;

import Utils.Function;
import java.util.Scanner;

public class Table {
    private String tableID; // ID của bàn được sinh tự động và có dạng B1, B2, B3,...
    private String tablePosition; // Vị trí của bàn, có các giá trị A, B, C
    private String tableType; // Loại bàn (số chỗ của bàn) có các giá trị 2, 4, 8
    private String tableStatus; // Trạng thái của bàn có các giá trị true (bàn đang trống), false (bàn đang được sử dụng)
    public static int numOfTable = 0; // Số lượng bàn trong cửa hàng

    public Table() {
        numOfTable++;
        this.tableID = "B" + numOfTable;
    }

    public Table(String tablePosition, String tableType) {
        numOfTable++;
        this.tableID = "B" + numOfTable;
        this.tablePosition = tablePosition;
        this.tableType = tableType;
        this.tableStatus = "true";
    }

    public String getTableID() {
        return this.tableID;
    }

    public String getTablePosition() {
        return this.tablePosition;
    }

    public String getTableType() {
        return this.tableType;
    }

    public String getTableStatus() {
        return this.tableStatus;
    }

    public void setTablePosition(String tablePosition) {
        this.tablePosition = tablePosition;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }

    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------Nhập thông tin cho class Table------");

        // Nhập vị trí của bàn
        while (true) {
            System.out.println("---Nhập vị trí của bàn: ");
            System.out.println("1: khu A");
            System.out.println("2: khu B");
            System.out.println("3: khu C");
            String str = sc.nextLine();
            if (str.compareTo("") == 0) {
                System.out.println("Vị trí của bàn không được rỗng");
            } else {
                if (str.compareTo("1") == 0) {
                    this.tablePosition = "A";
                    break;
                } else if (str.compareTo("2") == 0) {
                    this.tablePosition = "B";
                    break;
                } else if (str.compareTo("3") == 0) {
                    this.tablePosition = "C";
                    break;
                }

                System.out.println("Vị trí bàn không đúng");
            }
        }

        // Nhập loại bàn (2, 4, 8 chỗ)
        while (true) {
            System.out.println("---Nhập loại của bàn: ");
            System.out.println("1: 2 chỗ");
            System.out.println("2: 4 chỗ");
            System.out.println("3: 8 chỗ");
            String str = sc.nextLine();
            if (str.compareTo("") == 0) {
                System.out.println("Loại của bàn không được rỗng");
            } else {
                if (str.compareTo("1") == 0) {
                    this.tableType = "2";
                    break;
                } else if (str.compareTo("2") == 0) {
                    this.tableType = "4";
                    break;
                } else if (str.compareTo("3") == 0) {
                    this.tableType = "8";
                    break;
                }

                System.out.println("Loại bàn không đúng");
            }
        }

        // Bàn khi mới tạo thì tình trạng bàn sẽ có giá trị true (Đang trống)
        this.tableStatus = "true";
    }

    public void displayInfo() {
        System.out.println("---Thông tin của bàn:");
        System.out.println("ID: " + this.tableID);
        System.out.println("Vị trí: " + "Khu " + this.tablePosition);
        System.out.println("Loại bàn: " + this.tableType + " chỗ");
        if (this.tableStatus.compareTo("true") == 0) {
            System.out.println("Tình trạng: Đang trống");
        } else if (this.tableStatus.compareTo("false") == 0) {
            System.out.println("Tình trạng: Đang được sử dụng");
        }
    }

    public void modifyInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------SỬA THÔNG TIN CỦA BÀN------");
        while (true) { 
            System.out.println("   Bạn có các lựa chọn:          ");
            System.out.println("     1: Sửa 1 thông tin          ");         
            System.out.println("     2: Sửa tất cả thông tin     ");
            System.out.println("     3: Quay lại                 ");
            System.out.println("---------------------------------");
            System.out.print("Mời bạn nhập:                    ");
            String select = sc.nextLine();

            while (Function.isEmpty(select)) {
                System.out.println("Thông tin nhập không được rỗng   ");
                System.out.print("Mời bạn nhập lại:                ");
                select = sc.nextLine();
            }

            while (select.compareTo("1") != 0 && select.compareTo("2") != 0 && select.compareTo("3") != 0) {
                System.out.println("Thông tin nhập không đúng        ");
                System.out.print("Mời bạn nhập lại:                ");
                select = sc.nextLine();
            }

            if (select.compareTo("1") == 0) {
                System.out.println("   Bạn có các lựa chọn:          ");
                System.out.println("     1: Vị trí bàn               ");   
                System.out.println("     2: Loại bàn                 ");   
                System.out.println("     3: Tình trạng bàn           ");   
                System.out.println("     4: Quay lại                 ");
                System.out.println("---------------------------------");
                System.out.print("Mời bạn nhập: ");
                select = sc.nextLine();

                while (Function.isEmpty(select)) {
                    System.out.println("Thông tin nhập không được rỗng   ");
                    System.out.print("Mời bạn nhập lại:                ");
                    select = sc.nextLine();
                }
        
                while (select.compareTo("1") != 0 && select.compareTo("2") != 0 && select.compareTo("3") != 0 && select.compareTo("4") != 0) {
                    System.out.println("Thông tin nhập không đúng        ");
                    System.out.print("Mời bạn nhập lại:                ");
                    select = sc.nextLine();
                }

                if (select.compareTo("1") == 0) {
                    System.out.println("---Nhập vị trí của bàn:          ");
                    System.out.println("1: khu A                         ");
                    System.out.println("2: khu B                         ");
                    System.out.println("3: khu C                         ");
                    System.out.println("---------------------------------");
                    System.out.print("Mời bạn nhập:                    ");
                    select = sc.nextLine();

                    while (Function.isEmpty(select)) {
                        System.out.println("Thông tin nhập không được rỗng   ");
                        System.out.print("Mời bạn nhập lại:                ");
                        select = sc.nextLine();
                    }

                    while (select.compareTo("1") != 0 && select.compareTo("2") != 0 && select.compareTo("3") != 0) {
                        System.out.println("Thông tin nhập không đúng        ");
                        System.out.print("Mời bạn nhập lại:                ");
                        select = sc.nextLine();
                    }

                    switch (select) {
                        case "1":
                        this.tablePosition = "A";
                        break;

                        case "2":
                        this.tablePosition = "B";
                        break;

                        case "3":
                        this.tablePosition = "C";
                        break;
                    }
                    break;
                } else if (select.compareTo("2") == 0) {
                    System.out.print("---Nhập loại của bàn:            ");
                    System.out.println("1: 2 chỗ                         ");
                    System.out.println("2: 4 chỗ                         ");
                    System.out.println("3: 8 chỗ                         ");
                    System.out.println("---------------------------------");
                    System.out.print("Mời bạn nhập:                    ");

                    while (Function.isEmpty(select)) {
                        System.out.println("Thông tin nhập không được rỗng   ");
                        System.out.print("Mời bạn nhập lại:                ");
                        select = sc.nextLine();
                    }
    
                    while (select.compareTo("1") != 0 && select.compareTo("2") != 0 && select.compareTo("3") != 0) {
                        System.out.println("Thông tin nhập không đúng        ");
                        System.out.print("Mời bạn nhập lại:                ");
                        select = sc.nextLine();
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
                } else if (select.compareTo("3") == 0) {
                    System.out.print("---Nhập tình trạng của bàn:      ");
                    System.out.println("1: Trống                         ");
                    System.out.println("2: Đang được sử dụng             ");
                    System.out.println("---------------------------------");
                    System.out.print("Mời bạn nhập:                    ");

                    while (Function.isEmpty(select)) {
                        System.out.println("Thông tin nhập không được rỗng   ");
                        System.out.print("Mời bạn nhập lại:                ");
                        select = sc.nextLine();
                    }
        
                    while (select.compareTo("1") != 0 && select.compareTo("2") != 0 && select.compareTo("3") != 0) {
                        System.out.println("Thông tin nhập không đúng        ");
                        System.out.print("Mời bạn nhập lại:                ");
                        select = sc.nextLine();
                    }
        
                    switch (select) {
                    case "1":
                    this.tableStatus = "true";
                    break;
        
                    case "2":
                    this.tableType = "false";
                    break;
                    }
                } else if (select.compareTo("4") == 0) {
                    continue;
                }
            }
        
        }
    }

    public static void main(String[] args) {
        Table t1 = new Table();
        System.out.println(t1.tablePosition);

        t1.modifyInfo();

        System.out.println(t1.tablePosition);

    }
}
