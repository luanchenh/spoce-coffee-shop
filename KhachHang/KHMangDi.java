package KhachHang;

import Utils.Address;
import Utils.Function;
import java.util.Scanner;

public class KHMangDi extends KhachHang {
    private Address diaChi;

    // Hàm khởi tạo phi tham số
    public KHMangDi() {
        super();
        this.diaChi = new Address();
    }

    // Hàm khởi tạo với tên khách hàng
    public KHMangDi(String customerName) {
        super(customerName);
        this.diaChi = new Address();
    }

    // Hàm khởi tạo với tên khách hàng, tình trạng thành viên, chi tiết thành viên, địa chỉ (Address)
    public KHMangDi(String customerName, boolean isMember, MemberCard memberCard, Address diaChi) {
        super(customerName, isMember, memberCard);
        this.diaChi = diaChi;
    }

    public KHMangDi(String customerID, String customerName, boolean isMember, MemberCard memberCard, Address diaChi) {
        super(customerID, customerName, isMember, memberCard);
        this.diaChi = diaChi;
    }

    // Getter
    public Address getAddress() {
        return this.diaChi;
    }

    // Setter
    public void setAddress(Address diaChi) {
        this.diaChi = diaChi;
    }

    // Hàm để nhập thông tin cho khách hàng mang đi
    @Override
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;

        System.out.print("\n\t[Notice] ID khách hàng hiện tại: " + this.customerID);
        while (true) { 
            System.out.print("\n\t=> Nhập tên khách hàng: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tTên khách hàng không được rỗng!\n");
                continue;
            }

            if (Function.isTrueNumber(str)) {
                System.out.println("\tTên khách hàng không được là số!\n");
                continue;
            }

            this.customerName = str;
            break;
        }

        loop:
        while (true) { 
            System.out.println("\n\t==========================================================================================");
            // System.out.println("\tTrạng thái thành viên");
            // System.out.println("1. Khách hàng là thành viên");
            // System.out.println("2. Khách hàng không là thành viên");
            System.out.printf("\t| %-87s |%n", "Chọn trạng thái thành viên:");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Khách hàng là thành viên");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Khách hàng không là thành viên");
            System.out.println("\t==========================================================================================");
            System.out.print("\t=> Nhập trạng thái thành viên của khách hàng: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tTrạng thái thành viên không được rỗng!");
                continue loop;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tDữ liệu nhập vào phải là số!");
                continue loop;
            }

            int select = Integer.parseInt(str);
            switch (select) {
                case 1:
                this.isMember = true;
                if (this.memberCard == null) {
                    this.memberCard = new MemberCard();
                    this.memberCard.nhapThongTin();
                } else {
                    this.memberCard.nhapThongTin();
                }
                break;

                case 2:
                this.isMember = false;
                break;

                default:
                System.out.println("\tDữ liệu nhập không đúng!");
                continue loop;
            }

            break loop;
        }

        System.out.println("\n\tĐịa chỉ của khách hàng");
        while (true) {
            System.out.print("\t=> Nhập số nhà và tên đường: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tSố nhà và tên đường không được rỗng!");
                continue;
            }

            this.diaChi.setHouseNumer(str);
            break;
        }

        while (true) {
            System.out.print("\n\t=> Nhập phường/xã: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tPhường/xã không được rỗng!");
                continue;
            }

            this.diaChi.setWardName(str);
            break;
        }

        while (true) {
            System.out.print("\n\t=> Nhập quận/huyện: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tQuận/huyện không được rỗng!");
                continue;
            }

            this.diaChi.setCityName(str);
            break;
        }

        this.diaChi.getProvince().setInfo();
    }

    // Hàm để xuất thông tin khách hàng mang đi
    @Override
    public void xuatThongTin() {
        System.out.println("\t========================================================================================================================");
        System.out.printf("\t| %-23s %-93s |%n", "ID Khách Hàng:", this.customerID);
        System.out.printf("\t| %-23s %-93s |%n", "Tên khách hàng:", this.customerName);
        String memberStatus = this.isMember ? "Là thành viên" : "Không là thành viên";
        System.out.printf("\t| %-23s %-93s |%n", "Trạng thái thành viên:", memberStatus);
        if (this.memberCard != null) {
            this.memberCard.xuatThongTin();
        }
        // System.out.println("Địa chỉ: " + this.diaChi.toString());
        System.out.printf("\t| %-23s %-93s |%n", "Địa chỉ:", this.diaChi.toString());
        System.out.println("\t========================================================================================================================");
        System.out.println();
    }

    // Hàm để sửa thông tin khách hàng
    @Override
    public void suaThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;

        loop2:
        while (true) { 
            // System.out.println("\nSửa thông tin khách hàng mang đi có ID: " + this.customerID);
            // System.out.println("1. Tên khách hàng");
            // System.out.println("2. Trạng thái thành viên");
            // System.out.println("3. Địa chỉ");
            // System.out.println("4. Quay lại");
            System.out.println("\n\t==========================================================================================");
            System.out.printf("\t| %-87s |%n", "Sửa thông tin khách hàng mang đi có ID: " + this.customerID);
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Tên khách hàng");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Trạng thái thành viên");
            System.out.printf("\t| %-5s %-81s |%n", "3.", "Địa chỉ");
            System.out.printf("\t| %-5s %-81s |%n", "4.", "Quay lại");
            System.out.println("\t==========================================================================================");
            System.out.print("\t=> Nhập lựa chọn: ");
            str = sc.nextLine();
            System.out.println();
            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
                continue;
            }

            switch (Integer.parseInt(str)) {
                case 1:
                while (true) {
                    System.out.print("\n\t=> Nhập tên của khách hàng: ");
                    str = sc.nextLine();
                    if (Function.isEmpty(str)) {
                        System.out.println("\tTên khách hàng không được rỗng!");
                        continue;
                    }
                    this.customerName = str.trim();
                    break;
                }
                break;

                case 2:
                if (this.isMember) {
                    while (true) {
                        // System.out.println("\nTrạng thái thành viên:");
                        // System.out.println("1. Hủy thành viên");
                        // System.out.println("2. Sửa thông tin thành viên");
                        System.out.println("\n\t==========================================================================================");
                        System.out.printf("\t| %-87s |%n", "Trạng thái thành viên:");
                        System.out.printf("\t| %-5s %-81s |%n", "1.", "Hủy thành viên");
                        System.out.printf("\t| %-5s %-81s |%n", "2.", "Sửa thông tin thành viên");
                        System.out.println("\t==========================================================================================");
                        System.out.print("\t=> Nhập lựa chọn: ");
                        str = sc.nextLine();

                        if (Function.isEmpty(str)) {
                            System.out.println("\tLựa chọn không được rỗng!");
                            continue;
                        }
            
                        if (!Function.isTrueNumber(str)) {
                            System.out.println("\tLựa chọn phải là số!");
                            continue;
                        }

                        switch (Integer.parseInt(str)) {
                            case 1:
                            this.isMember = false; // chuyển trạng thái thành viên về false sau khi hủy
                            this.memberCard = null; // trỏ về null để hủy thông tin thành viên
                            MemberCard.numOfMember--; // giảm số lượng thành viên đi một sau khi hủy thành viên
                            System.out.println("\tĐã hủy thành viên thành công!");
                            break;

                            case 2:
                            while (true) { 
                                // System.out.println("\n\tSửa thông tin thành viên");
                                // System.out.println("\t1. Ngày tháng năm sinh");
                                // System.out.println("\t2. Điểm tích lũy");
                                System.out.println("\n\t==========================================================================================");
                                System.out.printf("\t| %-87s |%n", "Sửa thông tin thành viên");
                                System.out.printf("\t| %-5s %-81s |%n", "1.", "Ngày tháng năm sinh");
                                System.out.printf("\t| %-5s %-81s |%n", "2.", "Điểm tích lũy");
                                System.out.println("\t==========================================================================================");
                                System.out.print("\t=> Nhập lựa chọn: ");
                                str = sc.nextLine();

                                if (Function.isEmpty(str)) {
                                    System.out.println("\tLựa chọn không được rỗng!");
                                    continue;
                                }
                    
                                if (!Function.isTrueNumber(str)) {
                                    System.out.println("\tLựa chọn phải là số!");
                                    continue;
                                }

                                switch (Integer.parseInt(str)) {
                                    case 1:
                                    loop:
                                    while (true) {
                                        loop1:
                                        while (true) {
                                            System.out.println("\n\tSửa ngày tháng năm sinh");
                                            System.out.print("\t=> Mời nhập ngày: ");
                                            str = sc.nextLine();
                            
                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tNgày sinh không được rỗng!");
                                                continue loop1;
                                            }
                            
                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("\tNgày sinh phải là số!\n");
                                                continue loop1;
                                            }
                            
                                            this.memberCard.getBirthDay().setDay(str);
                                            break loop1;
                                        }
                            
                                        loop1:
                                        while (true) {
                                            System.out.print("\n\t=> Mời nhập tháng: ");
                                            str = sc.nextLine();
                            
                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tTháng sinh không được rỗng!");
                                                continue loop1;
                                            }
                            
                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("\tTháng sinh phải là số!");
                                                continue loop1;
                                            }
                            
                                            this.memberCard.getBirthDay().setMonth(str);
                                            break loop1;
                                        }
                            
                                        loop1:
                                        while (true) {
                                            System.out.print("\n\t=> Mời nhập năm: ");
                                            str = sc.nextLine();
                            
                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tNăm sinh không được rỗng!");
                                                continue loop1;
                                            }
                            
                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("\tNăm sinh phải là số!");
                                                continue loop1;
                                            }
                            
                                            this.memberCard.getBirthDay().setYear(str);
                                            break loop1;
                                        }
                            
                                        if (!this.memberCard.getBirthDay().isValidDate()) {
                                            System.out.println("\tDữ liệu ngày không hợp lệ!");
                                            continue loop;
                                        } else {
                                            break loop;
                                        }
                                    }
                                    break;

                                    case 2:
                                    while (true) {
                                        System.out.print("\n\t=> Nhập điểm tích lũy: ");
                                        str = sc.nextLine();

                                        if (Function.isEmpty(str)) {
                                            System.out.println("\tĐiểm tích lũy không được rỗng!");
                                            continue;
                                        }
                        
                                        if (!Function.isTrueNumber(str)) {
                                            System.out.println("\tĐiểm tích lũy phải là số!");
                                            continue;
                                        }

                                        if (Integer.parseInt(str) < 0) {
                                            System.out.println("\tĐiểm tích lũy không được có giá trị âm!");
                                            continue;
                                        }
                                        this.memberCard.setPoint(Integer.parseInt(str));
                                        break;
                                    }
                                    break;

                                    default: 
                                    System.out.println("\tLựa chọn không hợp lệ!");
                                    continue;
                                }
                                break; // while
                            }
                            break; // switch

                            default:
                            System.out.println("\tLựa chọn không hợp lệ!");
                            continue;
                        }
                        break; // while
                    }
                } else {
                    while (true) {
                        // System.out.println("\nSửa thông tin thành viên");
                        // System.out.println("1. Đăng kí trở thành thành viên");
                        // System.out.println("2. Quay lại");
                        System.out.println("\n\t==========================================================================================");
                        System.out.printf("\t| %-87s |%n", "Trạng thái thành viên:");
                        System.out.printf("\t| %-5s %-81s |%n", "1.", "Đăng kí trở thành thành viên");
                        System.out.printf("\t| %-5s %-81s |%n", "2.", "Quay lại");
                        System.out.println("\t==========================================================================================");
                        System.out.print("\t=> Nhập lựa chọn: ");
                        str = sc.nextLine();

                        if (Function.isEmpty(str)) {
                            System.out.println("\tLựa chọn không được rỗng!");
                            continue;
                        }
            
                        if (!Function.isTrueNumber(str)) {
                            System.out.println("\tLựa chọn phải là số!");
                            continue;
                        }

                        switch (Integer.parseInt(str)) {
                            case 1:
                            this.isMember = true; // chuyển trạng thái thành viên về true sau khi đăng kí thành viên
                            this.memberCard = new MemberCard(); // cấp phát vùng nhớ membercard để lưu thông tin thành viên
                            this.memberCard.nhapThongTin(); // gọi hàm để nhập thông tin thành viên
                            MemberCard.numOfMember++; // sau đó cập nhật số lượng thành viên tăng lên 1 sau khi đăng kí thành viên
                            System.out.println("\tĐăng kí thành viên thành công!");
                            break; // switch

                            case 2:
                            continue loop2;
                            
                            default:
                            continue;
                        }
                        break; // while
                    }
                }
                break;

                case 3:
                while (true) {
                    System.out.print("\t=> Nhập số nhà và tên đường: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tSố nhà và tên đường không được rỗng!\n");
                        continue;
                    }

                    this.diaChi.setHouseNumer(str);
                    break;
                }

                while (true) {
                    System.out.print("\n\t=> Nhập phường/xã: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tPhường/xã không được rỗng!");
                        continue;
                    }

                    this.diaChi.setWardName(str);
                    break;
                }

                while (true) {
                    System.out.print("\n\t=> Nhập quận/huyện: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tQuận/huyện không được rỗng!");
                        continue;
                    }

                    this.diaChi.setCityName(str);
                    break;
                }

                this.diaChi.getProvince().setInfo();
                break;

                case 4:
                break loop2;

                default:
                continue;
            }

            break loop2; // while
        }
    }

    @Override
    public String makeString() {
        StringBuilder str = new StringBuilder();
        str.append("1|"); // 1 đại diện cho khách hàng mang đi
        str.append(this.customerID).append("|");
        str.append(this.customerName).append("|");

        String memberStatus = this.isMember ? "1" : "0";
        str.append(memberStatus).append("|");
        if (this.isMember) {
            str.append(this.memberCard.makeString()).append("|");
        }

        str.append(this.diaChi.makeString());
        return str.toString();
    }
}


