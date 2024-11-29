import Table.Table;
import java.util.ArrayList;

public class QLBan {
    public ArrayList<Table> tableList;

    public QLBan() {
        this.tableList = new ArrayList<>();
    }

    public void addATable() {
        Table table = new Table();
        table.setInfo();
        this.tableList.add(table);
        System.out.println("Đã thêm bàn vào danh sách");
    }

    public void findTableForCustomers(int numOfCustomer) {
        int customers = numOfCustomer;
        int current8SeatTable = 0;
        int current4SeatTable = 0;
        int current2SeatTable = 0;

        for (Table table : this.tableList) {
            if (table.getTableType().compareTo("8") == 0 && table.getTableStatus().compareTo("true") == 0 && customers >= 8) {
                current8SeatTable++;
                customers = customers - 8;
            }
        }

        for (Table table : this.tableList) {
            if (table.getTableType().compareTo("4") == 0 && table.getTableStatus().compareTo("true") == 0 && customers >= 4) {
                current4SeatTable++;
                customers = customers - 4;
            }
        }

        for (Table table : this.tableList) {
            if (table.getTableType().compareTo("2") == 0 && table.getTableStatus().compareTo("true") == 0 && customers != 0) {
                current2SeatTable++;
                if (customers >= 2) {
                    customers = customers - 2;
                } else {
                    customers = 0;
                }
            }
        }

        if (customers == 0) {
            System.out.println(current2SeatTable + " 2 chỗ, " + current4SeatTable + " 4 chỗ, " + current8SeatTable + " 8 chỗ");
        } else {
            System.out.println("Không đủ bàn");
        }
    }

    public static void main(String[] args) {
        QLBan ql = new QLBan();
        ql.addATable();
        ql.addATable();
        ql.addATable();
        ql.addATable();
        ql.findTableForCustomers(9);
    }
}
