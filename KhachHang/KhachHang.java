package KhachHang;

import Utils.Function;
import java.io.File;
import java.util.Scanner;

public abstract class KhachHang {
    protected String customerID;
    protected String customerName;
    protected boolean isMember;
    protected MemberCard memberCard;

    public static int numOfCustomer = getNumberOfCustomerFromFile();

    // Hàm khởi tạo phi tham số
    public KhachHang() {
        this.customerID = makeCustomerID();
        this.isMember = false;
        this.memberCard = null;
    }

    // Hàm khởi tạo với tên khách hàng
    public KhachHang(String customerName) {
        this.customerName = makeCustomerID();
        this.isMember = false;
        this.memberCard = null;
    }

    // Hàm khởi tạo với tên khách hàng, trạng thái thành viên và thông tin thành viên (MemberCard)
    public KhachHang(String customerName, boolean isMember, MemberCard memberCard) {
        this.customerID = makeCustomerID();
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

    // Phương thức dùng để hủy gói thành viên
    public void cancelMembership() {
        if (this.isMember) {
            this.isMember = false;
            this.memberCard = null;
            MemberCard.numOfMember--;
            System.out.println("\tHủy thành viên thành công!");
        } else {
            System.out.println("\tKhách hàng hiện không phải là thành viên!");
        }
    }

    // Phương thức dùng để đọc số lượng khách hàng từ file
    public static int getNumberOfCustomerFromFile() {
        File customerFile = new File("../File/customer.txt");
        int num = 0;
        String str;
        try (Scanner sc = new Scanner(customerFile)) {
            while (sc.hasNextLine()) {
                str = sc.nextLine();
                String[] parts = str.split("\\|");
                String idValue = parts[1];
                num = Math.max(num, Integer.parseInt(idValue.substring(2)));
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        return num;
    }

    public static String makeCustomerID() {
        QLKhachHang ql = new QLKhachHang();
        ql.init();
        int idNumber = 1;
        boolean isValid = false;

        while (!isValid) {
            boolean isSame = false;
            for (KhachHang kh : ql.customerList) {
                if (Function.getNumberFromCustomerID(kh.customerID) == idNumber) {
                    isSame = true;
                    idNumber++;
                    break;
                }
            }

            if (!isSame) {
                isValid = true;
            }
        }

        return "KH" + idNumber;
    }
}
