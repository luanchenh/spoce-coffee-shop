package KhachHang;

import Utils.Date;
import Utils.Function;
import Utils.INhap;
import Utils.IXuat;
import java.util.Scanner;

@SuppressWarnings("resource")
public class MemberCard implements INhap, IXuat {
    private String cardID;
    private Date birthday;
    private Date startDate;
    private Date endDate;
    private int point;

    public static int numOfMember = getNumberOfMemberFromFile();

    public static int getNumberOfMemberFromFile() {
        QLKhachHang ql = new QLKhachHang();
        ql.init();
        int members = 0;
        for (KhachHang kh : ql.customerList) {
            if (kh.IsMember()) {
                members++;
            }
        }

        return members;
    }

    public MemberCard() {
        this.cardID = "MB" + ++numOfMember;
        this.point = 0;
        this.birthday = new Date();
        this.startDate = new Date();
        this.startDate.getLocalDate();
        this.endDate = new Date(this.startDate.getDay(), this.startDate.getMonth(), Integer.toString(Integer.parseInt(this.startDate.getYear()) + 1));
    }

    public MemberCard(String cardID, Date birthday, Date startDate, Date endDate, int point) {
        this.cardID = cardID;
        this.birthday = birthday;
        this.startDate = startDate;
        this.endDate = endDate;
        this.point = point;
    }

    public String getCardID() {
        return this.cardID;
    }

    public Date getBirthDay() {
        return this.birthday;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public int getPoint() {
        return this.point;
    }

    public void setBirthDay(Date birthday) {
        this.birthday = birthday;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void addPoint(int addingPoint) {
        this.point += addingPoint;
    }

    public void subPoint(int subPoint) {
        this.point -= subPoint;
    }

    @Override
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;
        System.out.println("\n\t[Notice] Thông tin thành viên");

        

        loop:
        while (true) {
            System.out.println("\tThông tin ngày sinh của khách hàng");
            loop1:
            while (true) {
                System.out.print("\t=> Mời nhập ngày: ");
                str = sc.nextLine();

                if (Function.isEmpty(str)) {
                    System.out.println("\tNgày sinh không được rỗng!\n");
                    continue loop1;
                }

                if (!Function.isTrueNumber(str)) {
                    System.out.println("\tNgày sinh phải là số!\n");
                    continue loop1;
                }

                this.birthday.setDay(str);
                break loop1;
            }

            loop1:
            while (true) {
                System.out.print("\n\t=> Mời nhập tháng: ");
                str = sc.nextLine();

                if (Function.isEmpty(str)) {
                    System.out.println("\tTháng sinh không được rỗng!\n");
                    continue loop1;
                }

                if (!Function.isTrueNumber(str)) {
                    System.out.println("\tTháng sinh phải là số!\n");
                    continue loop1;
                }

                this.birthday.setMonth(str);
                break loop1;
            }

            loop1:
            while (true) {
                System.out.print("\n\t=> Mời nhập năm: ");
                str = sc.nextLine();

                if (Function.isEmpty(str)) {
                    System.out.println("\tNăm sinh không được rỗng!\n");
                    continue loop1;
                }

                if (!Function.isTrueNumber(str)) {
                    System.out.println("\tNăm sinh phải là số!\n");
                    continue loop1;
                }

                this.birthday.setYear(str);
                break loop1;
            }

            if (!this.birthday.isValidDate()) {
                System.out.println("\tDữ liệu ngày không hợp lệ!\n");
                continue loop;
            } else {
                break loop;
            }
        }

        while (true) {
            System.out.print("\n\t=> Nhập điểm tích lũy: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được rỗng!\n");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!\n");
                continue;
            }

            this.point = Integer.parseInt(str);
            break;
        }
    }

    // Phương thức dùng để tích điểm từ giá trị đơn hàng
    public void point(int money) {
        int points = money / 1000;
        this.point = points;
    }

    @Override
    public void xuatThongTin() {
        // System.out.println("Mã thành viên: " + this.cardID);
        // System.out.println("Ngày sinh: " + this.birthday.toString());
        // System.out.println("Điểm tích lũy: " + this.point);
        // System.out.println("Thời hạn đổi điểm: " + this.startDate.toString() + " - " + this.endDate.toString());
        System.out.printf("\t| %-23s %-93s |%n", "Mã thành viên:", this.cardID);
        System.out.printf("\t| %-23s %-93s |%n", "Ngày sinh:", this.birthday.toString());
        System.out.printf("\t| %-23s %-93s |%n", "Điểm tích lũy:", this.point);
        System.out.printf("\t| %-23s %-93s |%n", "Thời hạn đổi điểm:", this.startDate.toString() + " - " + this.endDate.toString());
    }

    // Phương thức tạo chuỗi để ghi vào file
    public String makeString() {
        return this.cardID + "|" + this.birthday.getDay() + "|" + this.birthday.getMonth() + "|" + this.birthday.getYear() + "|" + this.startDate.getDay() + "|" + this.startDate.getMonth() + "|" + this.startDate.getYear() + "|" + this.endDate.getDay() + "|" + this.endDate.getMonth() + "|" + this.endDate.getYear() + "|" + this.point;
    }
}
