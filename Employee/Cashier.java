package Employee;


import java.util.Scanner;

import Order.Order;
import Utils.Address;
import Utils.Date;

public class Cashier extends Employee {
    public Cashier(){
        super();
    }

    public Cashier(String name, String gender, String numberPhone, String email, Date birth, Address address) {
       super(name, gender, numberPhone, email, birth, address);
    }

    public void setInfo(){
        super.setInfo();
    }

    public String toString(){
        return super.toString();
    }
    public void createOrder() {
        Scanner scanner = new Scanner(System.in);
    //     System.out.println("=== Lập hóa đơn ===");
    //     System.out.print("mã hóa đơn: ");
    //     String orderID = scanner.nextLine();
    //     System.out.print("tên khách hàng: ");
    //     String customerName = scanner.nextLine();
    //     System.out.print("tổng tiền: ");
    //     double totalAmount = scanner.nextDouble();

    //     System.out.println("hóa đơn");
    //     System.out.println("Mã hóa đơn: " + orderID);
    //     System.out.println("Tên khách hàng: " + customerName);
    //     System.out.println("Tổng tiền: " + totalAmount);
            Order order = new Order();
    }
     
    public double tinhLuongThang(){
        // cập nhật phương thức sau
        return 1.0;
    }
}
