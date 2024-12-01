package Customer;

import Utils.Address;
import Utils.Date;
import Utils.Function;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Customer {
    private String customerID;
    private String customerName;
    private Date birthDay;
    private int age;
    private Address address;
    
    private static int numOfCustomer = 0;

    public Customer() {
        this.customerID = "KH" + ++numOfCustomer;
        this.birthDay = new Date();
        this.address = new Address();
    }

    public Customer(String customerName, Date birthday, Address address) {
        this.customerID = "KH" + ++numOfCustomer;
        this.customerName = customerName;
        this.birthDay = birthday;
        this.age = birthday.calcAge();
        this.address = address;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public Date getBirthDay() {
        return this.birthDay;
    }

    public int getAge() {
        return this.age;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[SetInfo] Thêm thông tin cho [Khách hàng] có ID: " + this.customerID);

        while (true) { 
            System.out.print("[Input] Nhập tên khách hàng: ");
            String str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Tên khách hàng không được rỗng!");
                continue;
            }
            
            if (Function.isTrueNumber(str)) {
                System.out.println("[Warning] Tên khách hàng không được là số!");
                continue;
            }

            this.customerName = Function.normalizeName(str);
            break;
        }

        while (true) { 
            System.out.print("[Input] Nhập ngày sinh khách hàng: ");
            String str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Ngày sinh khách hàng không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("[Warning] Ngày sinh khách hàng phải là số!");
                continue;
            }

            this.birthDay.setDay(str);
            break;
        }

        while (true) { 
            System.out.print("[Input] Nhập tháng sinh khách hàng: ");
            String str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Tháng sinh khách hàng không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("[Warning] Tháng sinh khách hàng phải là số!");
                continue;
            }

            this.birthDay.setMonth(str);
            break;
        }

        while (true) { 
            System.out.print("[Input] Nhập năm sinh khách hàng: ");
            String str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Năm sinh khách hàng không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("[Warning] Năm sinh khách hàng phải là số!");
                continue;
            }

            this.birthDay.setYear(str);
            break;
        }

        this.age = this.birthDay.calcAge();

        while (true) { 
            System.out.print("[Input] Nhập số nhà của khách hàng: ");
            String str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Số nhà khách hàng không được rỗng!");
                continue;
            }

            this.address.setHouseNumer(str);
            break;
        }

        while (true) { 
            System.out.print("[Input] Nhập phường/xã của khách hàng: ");
            String str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Phường/xã của khách hàng không được rỗng!");
                continue;
            }

            this.address.setWardName(str.trim());
            break;
        }

        while (true) { 
            System.out.print("[Input] Nhập quận/huyện của khách hàng: ");
            String str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Quận/huyện của khách hàng không được rỗng!");
                continue;
            }

            this.address.setCityName(str.trim());
            break;
        }

        this.address.getProvince().setInfo();
    }

    public void displayInfo() {
        System.out.println("========== [Thông tin khách hàng] ==========");
        System.out.println("[ID]: " + this.customerID);
        System.out.println("[Tên khách hàng]: " + this.customerName);
        System.out.println("[Ngày sinh]: " + this.birthDay.toString());
        System.out.println("[Tuổi]: " + this.age);
        System.out.println("[Địa chỉ]: " + this.address.toString());
    }

    public static void main(String[] args) {
        Customer a = new Customer();
        a.setInfo();
        a.displayInfo();
    }
}
