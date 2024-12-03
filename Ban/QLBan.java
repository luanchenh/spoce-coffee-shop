package Ban;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import Utils.IXuat;

public class QLBan implements IXuat {
    ArrayList<Ban> tableList;

    public QLBan() {
        this.tableList = new ArrayList<>();
    }

    public QLBan(ArrayList<Ban> tableList) {
        this.tableList = tableList;
    }

    // Phương thức dùng để đọc dữ liệu bàn từ tập tin và ghi vào mảng quản lí bàn
    public void init() {
        File tableFile = new File("../File/table.txt");
        try (Scanner sc = new Scanner(tableFile)) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] arr = str.split("\\|");
                Ban ban = null;
                if (arr.length == 3) {
                    boolean status;
                    if (arr[2].compareTo("0") == 0) {
                        status = false;
                    } else {
                        status = true;
                    }
                    ban = new Ban(arr[0], Integer.parseInt(arr[1]), status);
                    this.tableList.add(ban);
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Lỗi " + e.getMessage());
        }
    }

    // Phương thức dùng để ghi tất cả đối tượng Bàn trong Array List vào file
    public void writeAll() {
        try (FileWriter writer = new FileWriter("../File/table.txt", false)) {
            for (Ban ban : this.tableList) {
                writer.append(ban.makeString() + "\n");
            }
            writer.flush();
        } catch (Exception e) {
            System.out.println("Lỗi " + e.getMessage());
        }
    }

    // Phương thức dùng để thêm 1 bàn mới vào Array List
    public boolean addTable() {
        Ban ban = new Ban();
        ban.nhapThongTin();
        this.tableList.add(ban);
        return true;
    }

    // Phương thức dùng để xóa 1 bàn ra khỏi Array List
    public boolean removeTable(Ban tableToBeRemoved) {
        for (Ban table : this.tableList) {
            if (table.getTableID().compareTo(tableToBeRemoved.getTableID()) == 0) {
                this.tableList.remove(table);
                return true;
            }
        }
        return false;
    }

    // Phương thức để kiểm tra ID bàn đã tồn tại hay chưa (Nếu đã tồn tại thì trả về true, chưa có thì trả về false)
    public boolean checkTableID(String tableID) {
        for (Ban table : this.tableList) {
            if (table.getTableID().compareTo(tableID) == 0) {
                return true;
            }
        }
        return false;
    }

    // Phương thức dùng để in ra danh sách bàn
    public void printTableList() {
        System.out.println("====================[Danh sách bàn]====================");
        System.out.printf("%-5s %-25s %-10s\n", "Mã bàn", "Số chỗ ngồi", "Tình trạng\n");
        for (Ban table : this.tableList) {
            table.printString();
        }
    }

    @Override
    public void xuatThongTin() {
        for (Ban table : this.tableList) {
            table.xuatThongTin();
        }
    }

    public static void main(String[] args) {
        QLBan ql = new QLBan();
        ql.init();
        ql.addTable();
        ql.xuatThongTin();
        ql.writeAll();
    }
}
