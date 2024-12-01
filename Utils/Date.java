/**
 *
 */
package Utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Date {
    private String day;
    private String month;
    private String year;

    public Date() {
    }

    public Date(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    // Getter - Setter

    public void setDay(String day) {
        this.day = day;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }
    public String getMonth() {
        return month;
    }
    public String getYear() {
        return year;
    }

    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập ngày: "); this.day = sc.nextLine().trim();
        System.out.print("Nhập tháng: "); this.month = sc.nextLine().trim();
        System.out.print("Nhập năm: "); this.year = sc.nextLine().trim();
        if (!Function.isEmpty(this.day) && !Function.isEmpty(this.month) && !Function.isEmpty(this.year) && Function.isTrueNumber(this.day) && Function.isTrueNumber(this.month) && Function.isTrueNumber(this.year)) {
            System.out.println("Nhập dữ liệu ngày/tháng/năm thành công !");
        }
        if (Function.isEmpty(this.day) || Function.isEmpty(this.month) || Function.isEmpty(this.year)) {
            System.out.println("Bạn chưa nhập 1 trong 3 dữ liệu");
            setInfo();
        }
        if (!Function.isTrueNumber(this.day) || !Function.isTrueNumber(this.month) || !Function.isTrueNumber(this.year)) {
            System.out.println("Dữ liệu phải là số");
            setInfo();
        }
        String result = this.toString();
        if (Function.isTrueDate(result) == false) {
            System.out.println("Nhập sai vui lòng nhập lại");
            setInfo();
        }
    }

    @Override
    public String toString() {
        return String.format("%02d", Integer.parseInt(this.day)) +"/"+
               String.format("%02d", Integer.parseInt(this.month)) +"/"+
               String.format("%04d", Integer.parseInt(this.year));
    }

    public int calcAge() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(Integer.parseInt(this.year), Integer.parseInt(this.month), Integer.parseInt(this.day));
        return (int)ChronoUnit.YEARS.between(date2, date1);
    }

    public boolean isValidDate() {
        int day = Integer.parseInt(this.day);
        int month = Integer.parseInt(this.month);
        int year = Integer.parseInt(this.year);

        int[] arrDay;

        boolean isLeapYear = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (isLeapYear) {
            arrDay = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        } else {
            arrDay = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        }

        if (!(month >= 1 && month <= 12)) {
            return false;
        }

        if (!(day >= 1 && day <= arrDay[month - 1])) {
            return false;
        }

        return true;
    }
}

