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
            System.out.print("Nhập tên khách hàng: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Tên khách hàng không được rỗng!");
                continue;
            }

            if (Function.isTrueNumber(str)) {
                System.out.println("Tên khách hàng không được là số!");
                continue;
            }

            this.customerName = str;
            break;
        }

        // Nhập trạng thái thành viên và chi tiết thành viên
        while (true) { 
            System.out.println("Trạng thái thành viên");
            System.out.println("1. Khách hàng là thành viên");
            System.out.println("2. Khách hàng không là thành viên");
            System.out.print("Nhập trạng thái thành viên của khách hàng: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Trạng thái thành viên không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("Dữ liệu nhập vào phải là số!");
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
                System.out.println("Dữ liệu nhập không đúng!");
                continue;
            }

            break;
        }

        // Nhập số lượng khách
        while (true) { 
            System.out.print("Nhập số lượng khách: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("Số lượng khách không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("Dữ liệu nhập vào phải là số!");
                continue;
            }

            if (Integer.parseInt(str) < 0) {
                System.out.println("Dữ liệu nhập vào không được âm!");
                continue;
            }

            this.dineInCustomer = Integer.parseInt(str);
            break;
        }
    
        // Tìm ra bàn phù hợp
        // this.table = findTable(this.dineInCustomer);
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
            System.out.println("Thông tin bàn: Chưa có bàn phù hợp");
        }
    }

    // Hàm để sửa thông tin của khách hàng dùng tại chỗ
    @Override
    public void suaThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;

        loop2:
        while (true) { 
            System.out.println("Sửa thông tin khách hàng dùng tại chỗ có ID: " + this.customerID);
            System.out.println("1. Tên khách hàng");
            System.out.println("2. Trạng thái thành viên");
            System.out.println("3. Số lượng khách");
            System.out.println("4. Quay lại");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Lựa chọn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("Lựa chọn phải là số!");
                continue;
            }

            switch (Integer.parseInt(str)) {
                case 1:
                while (true) {
                    System.out.print("Nhập tên của khách hàng: ");
                    str = sc.nextLine();
                    if (Function.isEmpty(str)) {
                        System.out.println("Tên khách hàng không được rỗng!");
                        continue;
                    }
                    this.customerName = str.trim();
                    break;
                }
                break;

                case 2:
                if (this.isMember) {
                    while (true) {
                        System.out.println("1. Hủy thành viên");
                        System.out.println("2. Sửa thông tin thành viên");
                        System.out.print("Nhập lựa chọn: ");
                        str = sc.nextLine();

                        if (Function.isEmpty(str)) {
                            System.out.println("Lựa chọn không được rỗng!");
                            continue;
                        }
            
                        if (!Function.isTrueNumber(str)) {
                            System.out.println("Lựa chọn phải là số!");
                            continue;
                        }

                        switch (Integer.parseInt(str)) {
                            case 1:
                            this.isMember = false;
                            this.memberCard = null;
                            System.out.println("Đã hủy thành viên thành công!");
                            break;

                            case 2:
                            while (true) { 
                                System.out.println("Sửa thông tin thành viên");
                                System.out.println("1. Ngày tháng năm sinh");
                                System.out.println("2. Điểm tích lũy");
                                System.out.print("Nhập lựa chọn: ");
                                str = sc.nextLine();

                                if (Function.isEmpty(str)) {
                                    System.out.println("Lựa chọn không được rỗng!");
                                    continue;
                                }
                    
                                if (!Function.isTrueNumber(str)) {
                                    System.out.println("Lựa chọn phải là số!");
                                    continue;
                                }

                                switch (Integer.parseInt(str)) {
                                    case 1:
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
                            
                                            this.memberCard.getBirthDay().setDay(str);
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
                            
                                            this.memberCard.getBirthDay().setMonth(str);
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
                            
                                            this.memberCard.getBirthDay().setYear(str);
                                            break loop1;
                                        }
                            
                                        if (!this.memberCard.getBirthDay().isValidDate()) {
                                            System.out.println("Dữ liệu ngày không hợp lệ!");
                                            continue loop;
                                        } else {
                                            break loop;
                                        }
                                    }
                                    break;

                                    case 2:
                                    while (true) {
                                        System.out.print("Nhập điểm tích lũy: ");
                                        str = sc.nextLine();

                                        if (Function.isEmpty(str)) {
                                            System.out.println("Điểm tích lũy không được rỗng!");
                                            continue;
                                        }
                        
                                        if (!Function.isTrueNumber(str)) {
                                            System.out.println("Điểm tích lũy phải là số!");
                                            continue;
                                        }

                                        if (Integer.parseInt(str) < 0) {
                                            System.out.println("Điểm tích lũy không được có giá trị âm!");
                                            continue;
                                        }
                                        this.memberCard.setPoint(Integer.parseInt(str));
                                        break;
                                    }
                                    break;

                                    default: 
                                    System.out.println("Lựa chọn không hợp lệ!");
                                    continue;
                                }
                                break; // while
                            }
                            break; // switch

                            default:
                            System.out.println("Lựa chọn không hợp lệ!");
                            continue;
                        }
                        break; // while
                    }
                } else {
                    while (true) {
                        System.out.println("1. Đăng kí trở thành thành viên");
                        System.out.println("2. Quay lại");
                        System.out.print("Nhập lựa chọn: ");
                        str = sc.nextLine();

                        if (Function.isEmpty(str)) {
                            System.out.println("Lựa chọn không được rỗng!");
                            continue;
                        }
            
                        if (!Function.isTrueNumber(str)) {
                            System.out.println("Lựa chọn phải là số!");
                            continue;
                        }

                        switch (Integer.parseInt(str)) {
                            case 1:
                            this.isMember = true;
                            this.memberCard = new MemberCard();
                            this.memberCard.nhapThongTin();
                            System.out.println("Đăng kí thành viên thành công!");
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
                    System.out.print("Nhập số lượng khách dùng tại chỗ: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("Số lượng khách không được rỗng");
                        continue;
                    }

                    if (!Function.isTrueNumber(str)) {
                        System.out.println("Số lượng khách phải là số!");
                        continue;
                    }

                    if (Integer.parseInt(str) < 0) {
                        System.out.println("Số lượng khách không được có giá trị âm");
                        continue;
                    }

                    this.dineInCustomer = Integer.parseInt(str);
                    break;
                }
                break;

                case 4:
                break;

                default:
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            break; // while
        }
    }

    @Override
    public String makeString() {
        StringBuilder str = new StringBuilder();
        str.append("0|");
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
