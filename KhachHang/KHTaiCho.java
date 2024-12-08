package KhachHang;

import Ban.Ban;
import Utils.Function;
import java.util.Scanner;

@SuppressWarnings("resource")
public class KHTaiCho extends KhachHang {
    private int dineInCustomer;
    private Ban table;

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

        System.out.println("\n\t[Notice] ID khách hàng hiện tại: " + this.customerID);
        // Nhập tên khách hàng
        while (true) {
            System.out.print("\t=> Nhập tên khách hàng: ");
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

        // Nhập trạng thái thành viên và chi tiết thành viên
        while (true) {
            System.out.println("\n\t==========================================================================================");
            System.out.printf("\t| %-87s |%n", "Chọn trạng thái thành viên:");
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Khách hàng là thành viên");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Khách hàng không là thành viên");
            System.out.println("\t==========================================================================================");
            System.out.print("\t=> Nhập trạng thái thành viên của khách hàng: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
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
                System.out.println("\tLựa chọn không hợp lệ!");
                continue;
            }

            break;
        }

        // Nhập số lượng khách
        while (true) {
            System.out.print("\n\t=> Nhập số lượng khách: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tSố lượng khách không được rỗng!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tDữ liệu nhập vào phải là số!");
                continue;
            }

            if (Integer.parseInt(str) < 0) {
                System.out.println("\tDữ liệu nhập vào không được âm!");
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
        // System.out.println("ID khách hàng: " + this.customerID);
        // System.out.println("Tên khách hàng: " + this.customerName);
        // String memberStatus = this.isMember ? "Là thành viên" : "Không là thành viên";
        // System.out.println("Trạng thái thành viên: " + memberStatus);
        // if (this.memberCard != null) {
        //     this.memberCard.xuatThongTin();
        // }
        // System.out.println("Số lượng khách dùng tại chỗ: " + this.dineInCustomer);
        // if (this.table != null) {
        //     this.table.xuatThongTin();
        // } else {
        //     System.out.println("Thông tin bàn: Chưa có bàn");
        // }
        // System.out.println();

        System.out.println("\t========================================================================================================================");
        System.out.printf("\t| %-23s %-93s |%n", "ID Khách Hàng:", this.customerID);
        System.out.printf("\t| %-23s %-93s |%n", "Tên khách hàng:", this.customerName);
        String memberStatus = this.isMember ? "Là thành viên" : "Không là thành viên";
        System.out.printf("\t| %-23s %-93s |%n", "Trạng thái thành viên:", memberStatus);
        if (this.memberCard != null) {
            this.memberCard.xuatThongTin();
        }
        System.out.printf("\t| %-23s %-93s |%n", "Số lượng khách:", this.dineInCustomer);
        if (this.table != null) {
            this.table.xuatThongTin();
        } else {
            System.out.printf("\t| %-23s %-93s |%n", "Thông tin bàn:", "Chưa có bàn");
        }
        System.out.println("\t========================================================================================================================");
        System.out.println();
    }

    // Hàm để sửa thông tin của khách hàng dùng tại chỗ
    @Override
    public void suaThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;

        loop2:
        while (true) {
            // System.out.println("\nSửa thông tin khách hàng dùng tại chỗ có ID: " + this.customerID);
            // System.out.println("1. Tên khách hàng");
            // System.out.println("2. Trạng thái thành viên");
            // System.out.println("3. Số lượng khách");
            // System.out.println("4. Quay lại");
            System.out.println("\n\t==========================================================================================");
            System.out.printf("\t| %-87s |%n", "Sửa thông tin khách hàng dùng tại chỗ có ID: " + this.customerID);
            System.out.printf("\t| %-5s %-81s |%n", "1.", "Tên khách hàng");
            System.out.printf("\t| %-5s %-81s |%n", "2.", "Trạng thái thành viên");
            System.out.printf("\t| %-5s %-81s |%n", "3.", "Số lượng khách");
            System.out.printf("\t| %-5s %-81s |%n", "4.", "Quay lại");
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
                            this.isMember = false; // chuyển trạng thái thành viên về false tức là không phải thành viên
                            this.memberCard = null; // trỏ memberCard về null để hủy dữ liệu thành viên
                            MemberCard.numOfMember--; // cập nhật lại số lượng thành viên sau khi hủy thành viên
                            System.out.println("\tĐã hủy thành viên thành công!\n");
                            break;

                            case 2:
                            while (true) {
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
                                        System.out.println("\n\tSửa ngày tháng năm sinh");
                                        loop1:
                                        while (true) {
                                            System.out.print("\t=> Mời nhập ngày: ");
                                            str = sc.nextLine();

                                            if (Function.isEmpty(str)) {
                                                System.out.println("\tNgày sinh không được rỗng!");
                                                continue loop1;
                                            }

                                            if (!Function.isTrueNumber(str)) {
                                                System.out.println("\tNgày sinh phải là số!");
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
                            this.isMember = true; // chuyển trạng thái thành viên thành true
                            this.memberCard = new MemberCard(); // cấp phát bộ nhớ cho memberCard
                            this.memberCard.nhapThongTin(); // gọi hàm nhập thông tin cho biến memberCard
                            MemberCard.numOfMember++; // cập nhật lại số lượng thành viên sau khi thêm thành công 1 thành viên
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
                    System.out.print("\n\t=> Nhập số lượng khách dùng tại chỗ: ");
                    str = sc.nextLine();

                    if (Function.isEmpty(str)) {
                        System.out.println("\tSố lượng khách không được rỗng");
                        continue;
                    }

                    if (!Function.isTrueNumber(str)) {
                        System.out.println("\tSố lượng khách phải là số!");
                        continue;
                    }

                    if (Integer.parseInt(str) < 0) {
                        System.out.println("\tSố lượng khách không được có giá trị âm!");
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
                System.out.println("\tLựa chọn không hợp lệ!");
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
            str.append(this.memberCard.makeString());
        }
        else {
            str.append("null");
        }
        return str.toString();
    }


}
