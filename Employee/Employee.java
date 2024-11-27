/**
 *
 */
package Employee;

import Utils.Date;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import Utils.Function;
import Utils.Address;


@SuppressWarnings("resource")
public abstract class Employee {
    private String id;
    private String name;
    private String gender;
    private String age;
    private String numberPhone;
    private String email;
    // Element Class
    private Date birth;
    private Address address;


    private static int maSoNhanVien = 0;

    public Employee() {
        this.id = "NV" + maSoNhanVien++;
        this.birth = new Date();
        this.address = new Address();
    }
    public Employee(String name, String gender, String numberPhone, String email, Date birth, Address address) {
        this.id = "NV" + maSoNhanVien++;
        this.name = name;
        this.gender = gender;
        this.numberPhone = numberPhone;
        this.email = email;
        this.birth = birth;
        this.address = address;
    }

    // Getter - Setter
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Address getAddress() {
        return address;
    }
    public Date getBirth() {
        return birth;
    }
    public String getEmail() {
        return email;
    }
    public String getGender() {
        return gender;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getNumberPhone() {
        return numberPhone;
    }

    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        String str;
        System.out.println("-------Nhập thông tin cơ bản của nhân viên-------");
        // Mã nhân viên tư phát sinh
        System.out.println("---Nhân viên bạn đang cài đặt thông tin có mã nhân viên là: NV"+ maSoNhanVien);
        this.id = "NV" + maSoNhanVien;
        // Nhập tên
        while (true) {
            System.out.print("---Nhập họ và tên của nhân viên: ");
            str = sc.nextLine().trim();
            if (Function.isEmpty(str)) {
                System.out.println("Bạn chưa nhập thông tin tên vui lòng nhập lại");
            }
            else {
                this.name = Function.normalizeName(str);
                break;
            }
        }
        // Nhập giới tính
        while (true) {
            System.out.println("Có 2 loại giới tính, bạn vui lòng chọn");
            System.out.println("1. Nam");
            System.out.println("2. Nữ");
            System.out.print("---Vui lòng nhập mã giới tính bạn muốn chọn: ");
            str = sc.nextLine().trim();
            if (Function.isTrueNumber(str)) {
                int number = Integer.parseInt(str);
                if (number > 2 || number < 1) System.out.println("Bạn đã nhập ngoài phạm vi cho phép !");
                else {
                    if (number == 1) this.gender = "Nam";
                    else this.gender = "Nữ";
                    break;
                }
            }
            else System.out.println("Dữ liệu nhập chưa đúng !");
        }
        // Nhập số điện thoại
        while (true) {
            System.out.print("---Nhập số điện thoại nhân viên: ");
            str = sc.nextLine().trim();
            if (str.length() == 10) {
                if (Function.isTrueNumber(str)) {
                    if (str.charAt(0) == '0') {
                        this.numberPhone = str;
                        break;
                    }
                    else System.out.println("Đầu số điện thoại phải là số 0");
                }
                else System.out.println("Dữ liệu nhập chưa đúng !");
            }
            else System.out.println("Bạn đã nhập ngoài phạm vi cho phép !");
        }
        // Nhập email
        while (true) {
            System.out.print("---Nhập email của nhân viên: ");
            // Kiêm tra lại hàm này vẫn chưa check dấu được
            str = sc.nextLine().trim();
            if (Function.isTrueEmail(str) && !Function.hasVietnameseAccent(str)) {
                this.email = str;
                break;
            }
            else System.out.println("Dữ liệu nhập chưa đúng hoặc có dấu !");
        }
        // Nhập ngày tháng năm sinh
        System.out.println("---Nhập ngày tháng năm sinh của nhân viên");
        this.birth.setInfo();
        LocalDate birthDate = LocalDate.of(Integer.parseInt(birth.getYear()), Integer.parseInt(birth.getMonth()), Integer.parseInt(birth.getDay()));
        LocalDate currentDate = LocalDate.now();
        this.age = String.valueOf(Period.between(birthDate, currentDate).getYears());
        System.out.println("---Tuổi nhân viên của bạn là: "+ this.age);
        // Nhập địa chỉ
        System.out.println("---Nhập địa chỉ của nhân viên");
        this.address.setInfo();

        System.out.println("-------Nhập thông tin cơ bản cho nhân viên thành công !-------");
    }

    public String appendString() {
        return this.id +"|"+ this.name +"|"+ this.gender.equals("Nam") +"|"+ this.getBirth() +"|"+ this.age +"|"
            + this.numberPhone +"|"+ this.email +"|"+ this.getAddress();
    }

    @Override
    public String toString() {
        return "-------------------------------------------------------------\n"
              +"Mã số nhân viên: "+ this.id +"\n"
              +"Họ tên nhân viên: "+ this.name +"\n"
              +"Giới tính: "+ this.gender +"\n"
              +"Ngày tháng năm sinh: "+ this.birth +"\n"
              +"Tuổi: "+ this.age +"\n"
              +"Số điện thoại: "+ this.numberPhone +"\n"
              +"Email: "+ this.email +"\n"
              +"Địa chỉ: "+ this.address;
    }
    abstract double tinhLuongThang();

}