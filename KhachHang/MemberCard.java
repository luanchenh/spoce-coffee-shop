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

    private static int numOfMember = 0;

    public MemberCard() {
        this.cardID = "MB" + ++numOfMember;
        this.point = 0;
        this.birthday = new Date();
        this.startDate = new Date();
        this.startDate.getLocalDate();
        this.endDate = new Date(this.startDate.getDay(), this.startDate.getMonth(), Integer.toString(Integer.parseInt(this.startDate.getYear()) + 1));
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
        System.out.println("Thông tin thành viên");
        
        System.out.println("Thông tin ngày sinh của khách hàng");

        loop:
        while (true) {
            loop1:
            while (true) {
                System.out.print("Mời nhập ngày: ");
                str = sc.nextLine();

                if (Function.isEmpty(str)) {
                    System.out.println("Ngày sinh không được rỗng!");
                    continue loop1;
                }

                if (!Function.isTrueNumber(str)) {
                    System.out.println("Ngày sinh phải là số!");
                    continue loop1;
                }

                this.birthday.setDay(str);
                break loop1;
            }

            loop1:
            while (true) {
                System.out.print("Mời nhập tháng: ");
                str = sc.nextLine();

                if (Function.isEmpty(str)) {
                    System.out.println("Tháng sinh không được rỗng!");
                    continue loop1;
                }

                if (!Function.isTrueNumber(str)) {
                    System.out.println("Tháng sinh phải là số!");
                    continue loop1;
                }

                this.birthday.setMonth(str);
                break loop1;
            }

            loop1:
            while (true) {
                System.out.print("Mời nhập năm: ");
                str = sc.nextLine();

                if (Function.isEmpty(str)) {
                    System.out.println("Năm sinh không được rỗng!");
                    continue loop1;
                }

                if (!Function.isTrueNumber(str)) {
                    System.out.println("Năm sinh phải là số!");
                    continue loop1;
                }

                this.birthday.setYear(str);
                break loop1;
            }

            if (!this.birthday.isValidDate()) {
                System.out.println("Dữ liệu ngày không hợp lệ!");
                continue loop;
            } else {
                break loop;
            }
        }

        while (true) { 
            System.out.print("Nhập điểm tích lũy: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Lựa chọn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("Lựa chọn phải là số!");
                continue;
            }

            this.point = Integer.parseInt(str);
            break;
        }
    }

    @Override
    public void xuatThongTin() {
        if (this != null) {
            System.out.println("Mã thành viên: " + this.cardID);
            System.out.println("Ngày sinh: " + this.birthday.toString());
            System.out.println("Điểm tích lũy: " + this.point);
            System.out.println("Thời hạn đổi điểm: " + this.startDate.toString() + " - " + this.endDate.toString());
        }
    }
}
