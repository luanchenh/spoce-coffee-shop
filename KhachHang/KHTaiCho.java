package KhachHang;

import Ban.Ban;
import Utils.Function;
import java.util.Scanner;

public class KHTaiCho extends KhachHang {
    private int dineInCustomer; // số lượng khách
    private Ban table; // bàn mà khách sẽ được xếp vào (mặc định là null)

    // hàm khởi tạo phi tham số
    public KHTaiCho() {
        super();
        this.table = null;
    }

    // hàm khởi tạo với tên khách hàng
    public KHTaiCho(String customerName) {
        super(customerName);
        this.table = null;
    }

    // hàm khởi tạo với tên khách hàng, trạng thái thành viên, thông tin thành viên, số lượng khách
    public KHTaiCho(String customerName, boolean isMember, MemberCard memberCard, int dineInCustomer) {
        super(customerName, isMember, memberCard);
        this.dineInCustomer = dineInCustomer;
        this.table = null;
    }

    public KHTaiCho(String customerID, String customerName, boolean isMember, MemberCard memberCard) {
        super(customerID, customerName, isMember, memberCard);
        this.dineInCustomer = 0;
        this.table = null;
    }

    // Getter
    public int getNumberOfCustomer() {
        return this.dineInCustomer;
    }

    public Ban getTable() {
        return this.table;
    }

    // Setter
    public void setNumberOfCustomer(int dineInCustomer) {
        this.dineInCustomer = dineInCustomer;
    }

    public void setTable(Ban table) {
        this.table = table;
    }

    // Hàm nhập thông tin cho khách hàng dùng tại chỗ
    @Override
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;

        System.out.println("[Notice] ID khách hàng hiện tại: " + this.customerID);
        // Nhập tên khách hàng
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

        // Nhập trạng thái thành viên và chi tiết thành viên
        while (true) { 
            System.out.println("\nTrạng thái thành viên");
            System.out.println("1. Khách hàng là thành viên");
            System.out.println("2. Khách hàng không là thành viên");
            System.out.print("Nhập trạng thái thành viên của khách hàng: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Lựa chọn không được rỗng!\n");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("Lựa chọn phải là số!\n");
                continue;
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
                System.out.println("Lựa chọn không hợp lệ!\n");
                continue;
            }

            break;
        }

        // Nhập số lượng khách
        while (true) { 
            System.out.print("\nNhập số lượng khách: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Số lượng khách không được rỗng!\n");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("Dữ liệu nhập vào phải là số!\n");
                continue;
            }

            if (Integer.parseInt(str) < 0) {
                System.out.println("Dữ liệu nhập vào không được âm!\n");
                continue;
            }

            this.dineInCustomer = Integer.parseInt(str);
            break;
        }
    
        // Tìm ra bàn phù hợp
        this.table = Ban.findTable(this.dineInCustomer);
    }

    // Hàm xuất thông tin của khách hàng dùng tại chỗ
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
        System.out.println("Số lượng khách dùng tại chỗ: " + this.dineInCustomer);
        if (this.table != null) {
            this.table.xuatThongTin();
        } else {
            System.out.println("Thông tin bàn: Chưa có bàn");
        }
        System.out.println();
    }

    // Hàm để sửa thông tin của khách hàng dùng tại chỗ
    @Override
    public void suaThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;

        loop2:
        while (true) { 
            System.out.println("\nSửa thông tin khách hàng dùng tại chỗ có ID: " + this.customerID);
            System.out.println("1. Tên khách hàng");
            System.out.println("2. Trạng thái thành viên");
            System.out.println("3. Số lượng khách");
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
                            this.isMember = false; // chuyển trạng thái thành viên về false tức là không phải thành viên
                            this.memberCard = null; // trỏ memberCard về null để hủy dữ liệu thành viên
                            MemberCard.numOfMember--; // cập nhật lại số lượng thành viên sau khi hủy thành viên
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
                                        System.out.println("\nSửa ngày tháng năm sinh");
                                        loop1:
                                        while (true) {
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
                            this.isMember = true; // chuyển trạng thái thành viên thành true
                            this.memberCard = new MemberCard(); // cấp phát bộ nhớ cho memberCard
                            this.memberCard.nhapThongTin(); // gọi hàm nhập thông tin cho biến memberCard
                            MemberCard.numOfMember++; // cập nhật lại số lượng thành viên sau khi thêm thành công 1 thành viên
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
                    System.out.print("\nNhập số lượng khách dùng tại chỗ: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("Số lượng khách không được rỗng\n");
                        continue;
                    }

                    if (!Function.isTrueNumber(str)) {
                        System.out.println("Số lượng khách phải là số!\n");
                        continue;
                    }

                    if (Integer.parseInt(str) < 0) {
                        System.out.println("Số lượng khách không được có giá trị âm!\n");
                        continue;
                    }

                    this.dineInCustomer = Integer.parseInt(str);
                    if (this.table != null) {
                        this.table.cancelTable();
                    }
                    this.table = Ban.findTable(this.dineInCustomer);
                    break;
                }
                break;

                case 4:
                break;

                default:
                System.out.println("Lựa chọn không hợp lệ!\n");
                continue;
            }

            break; // while
        }
    }

    @Override
    public String makeString() {
        StringBuilder str = new StringBuilder();
        str.append("0|"); // 0 đại diện cho khách hàng tại chỗ
        str.append(this.customerID).append("|");
        str.append(this.customerName).append("|");

        String memberStatus = this.isMember ? "1" : "0";
        str.append(memberStatus).append("|");
        if (this.isMember) {
            str.append(this.memberCard.makeString()).append("|");
        }
        return str.toString();
    }


}
