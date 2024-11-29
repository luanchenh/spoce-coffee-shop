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
        this.customerID = "KH" + numOfCustomer++;
        this.birthDay = new Date();
        this.address = new Address();
    }

    public Customer(String customerName, Date birthday, Address address) {
        this.customerID = "KH" + numOfCustomer++;
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
            String str = sc.nextLine();
            System.out.print("[Input] Nhập tên khách hàng: ");

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
            String str = sc.nextLine();
            System.out.println("[Input] Nhập ngày sinh khách hàng: ");

            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Ngày sinh khách hàng không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("[Warning] Ngày sinh khách hàng phải là số!");
                continue;
            }
        }
    }
}
