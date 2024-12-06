package KhachHang;

import java.io.File;
import java.util.Scanner;

public abstract class KhachHang {
    protected String customerID;
    protected String customerName;
    protected boolean isMember;
    protected MemberCard memberCard;

    protected static int numOfCustomer = getNumberOfCustomerFromFile();

    public static int getNumberOfCustomerFromFile() {
        File customerFile = new File("../File/customer.txt");
        String str = null;
        try (Scanner sc = new Scanner(customerFile)) {
            while (sc.hasNextLine()) {
                str = sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        int num;
        if (str == null) {
            num = 0;
        } else {
            String[] arr = str.split("\\|");
            num = Integer.parseInt(arr[1].substring(2));
        }

        return num;
    }

    // Hàm khởi tạo phi tham số
    public KhachHang() {
        this.customerID = "KH" + ++numOfCustomer;
        this.isMember = false;
        this.memberCard = null;
    }

    // Hàm khởi tạo với tên khách hàng
    public KhachHang(String customerName) {
        this.customerName = customerName;
        this.isMember = false;
        this.memberCard = null;
    }

    // Hàm khởi tạo với tên khách hàng, trạng thái thành viên và thông tin thành viên (MemberCard)
    public KhachHang(String customerName, boolean isMember, MemberCard memberCard) {
        this.customerID = "KH" + ++numOfCustomer;
        this.customerName = customerName;
        this.isMember = isMember;
        this.memberCard = memberCard;
    }

    public KhachHang(String customerID, String customerName, boolean isMember, MemberCard memberCard) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.isMember = isMember;
        this.memberCard = memberCard;
    }

    // Getter
    public String getCustomerID() {
        return this.customerID;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public boolean IsMember() {
        return this.isMember;
    }

    public MemberCard getMemberCard() {
        return this.memberCard;
    }

    // Setter
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setMemberStatus(boolean isMember) {
        this.isMember = isMember;
    }

    public void setMemberCard(MemberCard memberCard) {
        this.memberCard = memberCard;
    }

    // Phương thức trừu tượng để sửa thông tin
    public abstract void suaThongTin();

    // Phương thức trừu tượng để nhập thông tin
    public abstract void nhapThongTin();

    // Phương thức trừu tượng để xuất thông tin
    public abstract void xuatThongTin();

    // Phương thức trừu tượng để tạo chuỗi ghi vào file
    public abstract String makeString();
}
