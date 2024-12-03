package KhachHang;

import Utils.INhap;
import Utils.IXuat;

public abstract class KhachHang implements INhap, IXuat {
    protected String customerID;
    protected String customerName;
    protected boolean isMember;
    protected MemberCard memberCard;

    protected static int numOfCustomer = 0;

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

}
