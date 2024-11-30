package Customer;

import Utils.Date;
import Utils.Function;
import java.util.Scanner;

public class RewardPoint {
    private Date date;
    private int point;

    public RewardPoint() {
        this.date = new Date();
    }

    public RewardPoint(Date date, int point) {
        this.date = date;
        this.point = point;
    }

    public Date getDate() {
        return this.date;
    }

    public int getPoint() {
        return this.point;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        String str;
        System.out.println("[SetInfo] Thêm thông tin điểm");

        while (true) {
            System.out.print("[Input] Nhập ngày tích điểm đầu tiên: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Ngày tích điểm không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("[Warning] Ngày tích điểm phải là số!");
                continue;
            }

            if (!Function.isNotNegative(Integer.parseInt(str))) {
                System.out.println("[Warning] Ngày tích điểm không được âm!");
                continue;
            }

            this.date.setDay(str);
            break;
        }

        while (true) {
            System.out.print("[Input] Nhập tháng tích điểm đầu tiên: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Tháng tích điểm không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("[Warning] Tháng tích điểm phải là số!");
                continue;
            }

            if (!Function.isNotNegative(Integer.parseInt(str))) {
                System.out.println("[Warning] Tháng tích điểm không được âm!");
                continue;
            }

            this.date.setMonth(str);
            break;
        }

        while (true) {
            System.out.print("[Input] Nhập năm tích điểm đầu tiên: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Năm tích điểm không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("[Warning] Năm tích điểm phải là số!");
                continue;
            }

            if (!Function.isNotNegative(Integer.parseInt(str))) {
                System.out.println("[Warning] Năm tích điểm không được âm!");
                continue;
            }

            this.date.setYear(str);
            break;
        }

        while (true) { 
            System.out.print("[Input] Nhập số điểm: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Số điểm không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("[Warning] Số điểm phải là số!");
                continue;
            }

            if (!Function.isNotNegative(Integer.parseInt(str))) {
                System.out.println("[Warning] Số điểm không được âm!");
                continue;
            }

            this.point = Integer.parseInt(str);
            break;
        }
    }
}
