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

        System.out.println("[Notice] ID khách hàng hiện tại: " + this.customerID);
        while (true) { 
            System.out.print("\nNhập tên khách hàng: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Tên khách hàng không được rỗng!\n");
                continue;
            }

            if (Function.isTrueNumber(str)) {
                System.out.println("Tên khách hàng không được là số!\n");
                continue;
            }

            this.customerName = str;
            break;
        }

        loop:
        while (true) { 
            System.out.println("\nTrạng thái thành viên");
            System.out.println("1. Khách hàng là thành viên");
            System.out.println("2. Khách hàng không là thành viên");
            System.out.print("Nhập trạng thái thành viên của khách hàng: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Trạng thái thành viên không được rỗng!\n");
                continue loop;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("Dữ liệu nhập vào phải là số!\n");
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
                System.out.println("Dữ liệu nhập không đúng!\n");
                continue loop;
            }

            break loop;
        }

        System.out.println("\nĐịa chỉ của khách hàng");
        while (true) {
            System.out.print("Nhập số nhà và tên đường: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Số nhà và tên đường không được rỗng!\n");
                continue;
            }

            this.diaChi.setHouseNumer(str);
            break;
        }

        while (true) {
            System.out.print("\nNhập phường/xã: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Phường/xã không được rỗng!\n");
                continue;
            }

            this.diaChi.setWardName(str);
            break;
        }

        while (true) {
            System.out.print("\nNhập quận/huyện: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Quận/huyện không được rỗng!\n");
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
        System.out.println("===========================================================");
        System.out.println("ID khách hàng: " + this.customerID);
        System.out.println("Tên khách hàng: " + this.customerName);
        String memberStatus = this.isMember ? "Là thành viên" : "Không là thành viên";
        System.out.println("Trạng thái thành viên: " + memberStatus);
        if (this.memberCard != null) {
            this.memberCard.xuatThongTin();
        }
        System.out.println("Địa chỉ: " + this.diaChi.toString());
        System.out.println();
    }

    // Hàm để sửa thông tin khách hàng
    @Override
    public void suaThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;

        loop2:
        while (true) { 
            System.out.println("\nSửa thông tin khách hàng mang đi có ID: " + this.customerID);
            System.out.println("1. Tên khách hàng");
            System.out.println("2. Trạng thái thành viên");
            System.out.println("3. Địa chỉ");
            System.out.println("4. Quay lại");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Lựa chọn không được rỗng!\n");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("Lựa chọn phải là số!\n");
                continue;
            }

            switch (Integer.parseInt(str)) {
                case 1:
                while (true) {
                    System.out.print("\nNhập tên của khách hàng: ");
                    str = sc.nextLine();
                    if (Function.isEmpty(str)) {
                        System.out.println("Tên khách hàng không được rỗng!\n");
                        continue;
                    }
                    this.customerName = str.trim();
                    break;
                }
                break;

                case 2:
                if (this.isMember) {
                    while (true) {
                        System.out.println("\nTrạng thái thành viên:");
                        System.out.println("1. Hủy thành viên");
                        System.out.println("2. Sửa thông tin thành viên");
                        System.out.print("Nhập lựa chọn: ");
                        str = sc.nextLine();

                        if (Function.isEmpty(str)) {
                            System.out.println("Lựa chọn không được rỗng!\n");
                            continue;
                        }
            
                        if (!Function.isTrueNumber(str)) {
                            System.out.println("Lựa chọn phải là số!\n");
                            continue;
                        }

                        switch (Integer.parseInt(str)) {
                            case 1:
                            this.isMember = false; // chuyển trạng thái thành viên về false sau khi hủy
                            this.memberCard = null; // trỏ về null để hủy thông tin thành viên
                            MemberCard.numOfMember--; // giảm số lượng thành viên đi một sau khi hủy thành viên
                            System.out.println("Đã hủy thành viên thành công!\n");
                            break;

                            case 2:
                            while (true) { 
                                System.out.println("\nSửa thông tin thành viên");
                                System.out.println("1. Ngày tháng năm sinh");
                                System.out.println("2. Điểm tích lũy");
                                System.out.print("Nhập lựa chọn: ");
                                str = sc.nextLine();

                                if (Function.isEmpty(str)) {
                                    System.out.println("Lựa chọn không được rỗng!\n");
                                    continue;
                                }
                    
                                if (!Function.isTrueNumber(str)) {
                                    System.out.println("Lựa chọn phải là số!\n");
                                    continue;
                                }

                                switch (Integer.parseInt(str)) {
                                    case 1:
                                    loop:
                                    while (true) {
                                        loop1:
                                        while (true) {
                                            System.out.println("\nSửa ngày tháng năm sinh");
                                            System.out.print("Mời nhập ngày: ");
                                            str = sc.nextLine();
                            
                                            if (Function.isEmpty(str)) {
                                                System.out.println("Ngày sinh không được rỗng!\n");
                                                continue loop1;
                                            }
                            
                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("Ngày sinh phải là số!\n");
                                                continue loop1;
                                            }
                            
                                            this.memberCard.getBirthDay().setDay(str);
                                            break loop1;
                                        }
                            
                                        loop1:
                                        while (true) {
                                            System.out.print("\nMời nhập tháng: ");
                                            str = sc.nextLine();
                            
                                            if (Function.isEmpty(str)) {
                                                System.out.println("Tháng sinh không được rỗng!\n");
                                                continue loop1;
                                            }
                            
                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("Tháng sinh phải là số!\n");
                                                continue loop1;
                                            }
                            
                                            this.memberCard.getBirthDay().setMonth(str);
                                            break loop1;
                                        }
                            
                                        loop1:
                                        while (true) {
                                            System.out.print("\nMời nhập năm: ");
                                            str = sc.nextLine();
                            
                                            if (Function.isEmpty(str)) {
                                                System.out.println("Năm sinh không được rỗng!\n");
                                                continue loop1;
                                            }
                            
                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("Năm sinh phải là số!\n");
                                                continue loop1;
                                            }
                            
                                            this.memberCard.getBirthDay().setYear(str);
                                            break loop1;
                                        }
                            
                                        if (!this.memberCard.getBirthDay().isValidDate()) {
                                            System.out.println("Dữ liệu ngày không hợp lệ!\n");
                                            continue loop;
                                        } else {
                                            break loop;
                                        }
                                    }
                                    break;

                                    case 2:
                                    while (true) {
                                        System.out.print("\nNhập điểm tích lũy: ");
                                        str = sc.nextLine();

                                        if (Function.isEmpty(str)) {
                                            System.out.println("Điểm tích lũy không được rỗng!\n");
                                            continue;
                                        }
                        
                                        if (!Function.isTrueNumber(str)) {
                                            System.out.println("Điểm tích lũy phải là số!\n");
                                            continue;
                                        }

                                        if (Integer.parseInt(str) < 0) {
                                            System.out.println("Điểm tích lũy không được có giá trị âm!\n");
                                            continue;
                                        }
                                        this.memberCard.setPoint(Integer.parseInt(str));
                                        break;
                                    }
                                    break;

                                    default: 
                                    System.out.println("Lựa chọn không hợp lệ!\n");
                                    continue;
                                }
                                break; // while
                            }
                            break; // switch

                            default:
                            System.out.println("Lựa chọn không hợp lệ!\n");
                            continue;
                        }
                        break; // while
                    }
                } else {
                    while (true) {
                        System.out.println("\nSửa thông tin thành viên");
                        System.out.println("1. Đăng kí trở thành thành viên");
                        System.out.println("2. Quay lại");
                        System.out.print("Nhập lựa chọn: ");
                        str = sc.nextLine();

                        if (Function.isEmpty(str)) {
                            System.out.println("Lựa chọn không được rỗng!\n");
                            continue;
                        }
            
                        if (!Function.isTrueNumber(str)) {
                            System.out.println("Lựa chọn phải là số!\n");
                            continue;
                        }

                        switch (Integer.parseInt(str)) {
                            case 1:
                            this.isMember = true; // chuyển trạng thái thành viên về true sau khi đăng kí thành viên
                            this.memberCard = new MemberCard(); // cấp phát vùng nhớ membercard để lưu thông tin thành viên
                            this.memberCard.nhapThongTin(); // gọi hàm để nhập thông tin thành viên
                            MemberCard.numOfMember++; // sau đó cập nhật số lượng thành viên tăng lên 1 sau khi đăng kí thành viên
                            System.out.println("Đăng kí thành viên thành công!\n");
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
                    System.out.print("\nNhập số nhà và tên đường: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("Số nhà và tên đường không được rỗng!\n");
                        continue;
                    }

                    this.diaChi.setHouseNumer(str);
                    break;
                }

                while (true) {
                    System.out.print("\nNhập phường/xã: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("Phường/xã không được rỗng!\n");
                        continue;
                    }

                    this.diaChi.setWardName(str);
                    break;
                }

                while (true) {
                    System.out.print("\nNhập quận/huyện: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("Quận/huyện không được rỗng!\n");
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


