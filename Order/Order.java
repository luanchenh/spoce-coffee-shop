package Order;

import java.util.Date;
import java.util.Scanner;
import NuocUong.DrinkingWater;

public class Order {

    private String orderID;
    private String customerPhone;
    private Date orderDate;
    private double totalAmount;
    private DrinkingWater car; // món đặt
    private String payment;

    public Order() {
    }

    public Order(String orderID, String customerPhone, Date orderDate, DrinkingWater cart, String payment) {
        this.orderID = orderID;
        this.customerPhone = customerPhone;
        this.orderDate = orderDate;
        this.cart = cart;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public DrinkingWater getCart() {
        return cart;
    }

    public void setCart(DrinkingWater cart) {
        this.cart = cart;
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

        this.orderDate = new Date(); 
        System.out.print("Nhập phương thức thanh toán (Tiền mặt/Chuyển khoản): ");
        this.payment = scanner.nextLine();

        System.out.println("Nhập thông tin giỏ hàng:");
        this.cart = new DrinkingWater();
        this.cart.setInfo();

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
        // cart.displayInfo();
    }

    public void modifyInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Cập nhật số điện thoại khách hàng (hiện tại: " + customerPhone + "): ");
        this.customerPhone = scanner.nextLine();
        System.out.print("Cập nhật phương thức thanh toán (hiện tại: " + payment + "): ");
        this.payment = scanner.nextLine();
        System.out.println("Cập nhật giỏ hàng:");
        // thiếu cập nhật giỏ hàng
        this.totalAmount = calculateTotal();
    }

    public double calculateTotal() {
        // cập nhật phương thức sau
        return 1.0;
    }
}

