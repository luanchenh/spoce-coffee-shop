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
        String str;
        while (true) {
            while (true) {
                System.out.print("\tNhập ngày: ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống !");
                }
                else {
                    if (Function.isTrueNumber(str)) {
                        this.day = str;
                        break;
                    }
                    else {
                        System.out.println("\tDữ liệu ngày phải là số !");
                    }
                }
            }
            while (true) {
                System.out.print("\tNhập tháng: ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống !");
                }
                else {
                    if (Function.isTrueNumber(str)) {
                        this.month = str;
                        break;
                    }
                    else {
                        System.out.println("\tDữ liệu tháng phải là số !");
                    }
                }
            }
            while (true) {
                System.out.print("\tNhập năm: ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống !");
                }
                else {
                    if (Function.isTrueNumber(str)) {
                        this.year = str;
                        break;
                    }
                    else {
                        System.out.println("\tDữ liệu năm phải là số !");
                    }
                }
            }
            if (Function.isTrueDate(this.toString())) {
                return;
            }
            else {
                System.out.println("\tDữ liệu nhập chưa đúng !");
            }

        }
    }

    @Override
    public String toString() {
        return String.format("%02d", Integer.parseInt(this.day)) +"/"+
               String.format("%02d", Integer.parseInt(this.month)) +"/"+
               String.format("%04d", Integer.parseInt(this.year));
    }

    public void getLocalDate() {
        LocalDate now = LocalDate.now();
        this.day = Integer.toString(now.getDayOfMonth());
        this.month = Integer.toString(now.getMonthValue());
        this.year = Integer.toString(now.getYear());
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

    public String makeString() {
        return this.day + "|" + this.month + "|" + this.year;
    }
}

