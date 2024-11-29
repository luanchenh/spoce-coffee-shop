package Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import File.NuocUong.DrinkingWater;
import NuocUong.QLDrinking;
import Topping.Topping;

public class Order {

    private String orderID;
    private String customerPhone;
    private LocalDate orderDate;
    private QLDrinking drinkList;
    private double totalAmount;
    private ArrayList<Topping> topping;  // List of Topping items
    private String payment;

    public Order() {
        this.drinkList = new QLDrinking();
        this.topping = new ArrayList<Topping>();  // Initialize the topping list
    }

    public Order(String orderID, String customerPhone, Date orderDate, QLDrinking drinkList, ArrayList<Topping> topping, String payment) {
        this.orderID = orderID;
        this.customerPhone = customerPhone;
        this.orderDate = LocalDate.now();
        this.drinkList = drinkList;
        this.topping = topping;
        this.payment = payment;
        this.totalAmount = calculateTotal();
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public QLDrinking getDrinkList() {
        return drinkList;
    }

    public void setDrinkList(QLDrinking drinkList) {
        this.drinkList = drinkList;
        this.totalAmount = calculateTotal();
    }

    public ArrayList<Topping> getTopping() {
        return topping;
    }

    public void setTopping(ArrayList<Topping> topping) {
        this.topping = topping;
        this.totalAmount = calculateTotal();
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập mã đơn hàng: ");
        this.orderID = scanner.nextLine();

        System.out.print("Nhập số điện thoại khách hàng: ");
        this.customerPhone = scanner.nextLine();

        this.orderDate = LocalDate.now();
        System.out.print("Nhập phương thức thanh toán (Tiền mặt/Chuyển khoản): ");
        this.payment = scanner.nextLine();

        System.out.println("Nhập thông tin giỏ hàng:");
        this.drinkList = new QLDrinking();
        this.drinkList.setInfo();  // Giả sử setInfo() là phương thức nhập thông tin giỏ hàng

        this.totalAmount = calculateTotal();
    }

    public void displayInfo() {
        System.out.println("=== Thông tin đơn hàng ===");
        System.out.println("Mã đơn hàng: " + orderID);
        System.out.println("Số điện thoại khách hàng: " + customerPhone);
        System.out.println("Ngày đặt hàng: " + orderDate);
        System.out.println("Phương thức thanh toán: " + payment);
        System.out.println("Tổng giá trị: " + totalAmount);

        System.out.println("Thông tin giỏ hàng:");
        drinkList.displayInfo();  // Giả sử displayInfo() là phương thức hiển thị thông tin giỏ hàng

        System.out.println("Thông tin topping:");
        if (topping != null && !topping.isEmpty()) {
            for (Topping top : topping) {
                top.displayTopping();  // Hiển thị thông tin các topping trong giỏ hàng
            }
        } else {
            System.out.println("Không có topping trong đơn hàng.");
        }
    }

    public void modifyInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Cập nhật số điện thoại khách hàng (hiện tại: " + customerPhone + "): ");
        this.customerPhone = scanner.nextLine();
        
        System.out.print("Cập nhật phương thức thanh toán (hiện tại: " + payment + "): ");
        this.payment = scanner.nextLine();

        System.out.println("Cập nhật giỏ hàng:");
        this.drinkList.setInfo();  
        this.totalAmount = calculateTotal();  
    }

    public double calculateTotal() {
        double total = 0.0;

       
        for (DrinkingWater drink : drinkList.getDrinks()) {
            total += drink.getPrice();  
        }

        // Tính thêm các topping nếu có
        for (Topping toppingItem : topping) {
            total += toppingItem.getPriceTopping();  
        }

        return total;
    }
}
