package NhanVien;

import Utils.Address;
import Utils.Date;
import Utils.Function;
import Utils.INhap;
import Utils.IXuat;
import java.util.Scanner;

@SuppressWarnings("resource")
public class NhanVienPhaChe extends Nhanvien implements INhap, IXuat {
    private int soDonDaPhaChe;
    private int soGioLamThem;

    public NhanVienPhaChe(String nhanVienType) {
        this.loaiNhanVien = nhanVienType;
    }

    // Constructor không tham số
    public NhanVienPhaChe() {
        super("NVPC");
    }

    // Constructor có tham số
    public NhanVienPhaChe(String maNhanVien, String tenNhanVien, int tuoi, Date ngaySinh, Address diaChi,
            String loaiNhanVien, String caLamViec, int soDonDaPhaChe, int soGioLamThem) {
        super(maNhanVien, tenNhanVien, tuoi, ngaySinh, diaChi, loaiNhanVien, caLamViec);
        this.soDonDaPhaChe = soDonDaPhaChe;
        this.soGioLamThem = soGioLamThem;
    }

    public int getSoDonDaPhaChe() {
        return soDonDaPhaChe;
    }

    public void setSoDonDaPhaChe(int soDonDaPhaChe) {
        this.soDonDaPhaChe = soDonDaPhaChe;
    }

    public int getSoGioLamThem() {
        return soGioLamThem;
    }

    public void setSoGioLamThem(int soGioLamThem) {
        this.soGioLamThem = soGioLamThem;
    }

    @Override
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        super.nhapThongTin();
        String str;
        while (true) {
            System.out.print("\tNhập số đơn đã pha chế: ");
            str = sc.nextLine();
            if (Function.isTrueNumber(str)) {
                this.soDonDaPhaChe = Integer.parseInt(str);
                break;
            } else {
                System.out.println("\tVui lòng nhập số hợp lệ !");
            }
        }
        System.out.println("\t[Notice] Số đơn đã pha chế: " + this.soDonDaPhaChe);
        while (true) {
            System.out.print("\tNhập số giờ làm thêm: ");
            str = sc.nextLine();
            if (Function.isTrueNumber(str)) {
                this.soGioLamThem = Integer.parseInt(str);
                break;
            } else {
                System.out.println("\tVui lòng nhập số hợp lệ !");
            }
        }
        System.out.println("\t[Notice] Số giờ làm thêm: " + this.soGioLamThem);

        System.out.println("\t[Notice] Nhập thông tin nhân viên pha chế hoàn tất.");
    }

    public void addGioLamThem(int soGioLamThem) {
        this.soGioLamThem += soGioLamThem;
    }

    public void addDonDaPhaChe(int soDonDaPhaChe) {
        this.soDonDaPhaChe += soDonDaPhaChe;
    }

    // Override Method

    @Override
    public void tinhLuong() {
        int luongCoBan = 2000000;
        int phuCapCa = this.caLamViec.equals("EVENING") ? 500000 : 0;
        int thuongSoDon = this.soDonDaPhaChe > 100 ? 1000000 : 0;
        int thuongSoGio = this.soGioLamThem * 50000;
        int tongLuong = luongCoBan + phuCapCa + thuongSoDon + thuongSoGio;
        String loaiNhanVien = "Nhân viên pha chế";

        System.out.println(
                "\t+--------------+-----------------------------+----------------------+-------------------+---------------------------+-------------------+");
        System.out.printf(
                "\t| %-12s | %-27s | %-20s | %-17s | %-25s | %-17s |\n",
                "Mã nhân viên", "Loại nhân viên", "Lương cơ bản", "Phụ cấp ca", "Tiền giờ làm thêm", "Tổng lương");
        System.out.println(
                "\t+--------------+-----------------------------+----------------------+-------------------+---------------------------+-------------------+");
        System.out.printf(
                "\t| %-12s | %-27s | %-20s | %-17s | %-25s | %-17s |\n",
                this.maNhanVien, loaiNhanVien, Function.formatMoney(String.valueOf(luongCoBan)),
                Function.formatMoney(String.valueOf(phuCapCa)), Function.formatMoney(String.valueOf(thuongSoGio)),
                Function.formatMoney(String.valueOf(tongLuong)));
        System.out.println(
                "\t+--------------+-----------------------------+----------------------+-------------------+---------------------------+-------------------+");
    }

    @Override
    void modifyInfo() {
        Scanner sc = new Scanner(System.in);
        String str;
        int number;

        while (true) {
            System.out.println("\tBạn muốn sửa thông tin nào?");
            System.out.println("\t1. Tên nhân viên");
            System.out.println("\t2. Tuổi");
            System.out.println("\t3. Ngày sinh");
            System.out.println("\t4. Địa chỉ");
            System.out.println("\t5. Ca làm việc");
            System.out.println("\t6. Số đơn đã pha chế");
            System.out.println("\t7. Số giờ làm thêm");
            System.out.println("\t8. Thêm số giờ làm thêm");
            System.out.println("\t9. Thêm số đơn đã pha chế");
            System.out.println("\t10. Thoát");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống!");
            } else if (Function.isTrueNumber(str)) {
                number = Integer.parseInt(str);
                switch (number) {
                    case 1:
                        System.out.print("\tNhập tên nhân viên mới: ");
                        str = sc.nextLine();
                        if (!Function.isEmpty(str)) {
                            this.tenNhanVien = str;
                            System.out.println("\t[Notice] Tên nhân viên đã được cập nhật.");
                        } else {
                            System.out.println("\tTên không được để trống!");
                        }
                        break;
                    case 2:
                        System.out.print("\tNhập tuổi mới: ");
                        str = sc.nextLine();
                        if (Function.isTrueNumber(str)) {
                            this.tuoi = Integer.parseInt(str);
                            System.out.println("\t[Notice] Tuổi nhân viên đã được cập nhật.");
                        } else {
                            System.out.println("\tVui lòng nhập số!");
                        }
                        break;
                    case 3:
                        System.out.println("\tNhập ngày sinh mới:");
                        this.ngaySinh.setInfo();
                        System.out.println("\t[Notice] Ngày sinh đã được cập nhật.");
                        break;
                    case 4:
                        System.out.println("\tNhập địa chỉ mới:");
                        this.diaChi.setInfo();
                        System.out.println("\t[Notice] Địa chỉ đã được cập nhật.");
                        break;
                    case 5:
                        System.out.println("\tChọn ca làm việc mới:");
                        System.out.println("\t1. MORNING");
                        System.out.println("\t2. AFTERNOON");
                        System.out.println("\t3. EVENING");
                        System.out.print("\tNhập lựa chọn: ");
                        str = sc.nextLine();
                        if (Function.isTrueNumber(str)) {
                            number = Integer.parseInt(str);
                            if (number == 1) {
                                this.caLamViec = "MORNING";
                            } else if (number == 2) {
                                this.caLamViec = "AFTERNOON";
                            } else if (number == 3) {
                                this.caLamViec = "EVENING";
                            } else {
                                System.out.println("\tLựa chọn không hợp lệ!");
                                continue;
                            }
                            System.out.println("\t[Notice] Ca làm việc đã được cập nhật.");
                        } else {
                            System.out.println("\tVui lòng nhập số!");
                        }
                        break;
                    case 6:
                        System.out.print("\tNhập số đơn đã pha chế mới: ");
                        str = sc.nextLine();
                        if (Function.isTrueNumber(str)) {
                            this.soDonDaPhaChe = Integer.parseInt(str);
                            System.out.println("\t[Notice] Số đơn đã pha chế đã được cập nhật.");
                        } else {
                            System.out.println("\tVui lòng nhập số!");
                        }
                        break;
                    case 7:
                        System.out.print("\tNhập số giờ làm thêm mới: ");
                        str = sc.nextLine();
                        if (Function.isTrueNumber(str)) {
                            this.soGioLamThem = Integer.parseInt(str);
                            System.out.println("\t[Notice] Số giờ làm thêm đã được cập nhật.");
                        } else {
                            System.out.println("\tVui lòng nhập số!");
                        }
                        break;
                    case 8:
                        System.out.print("\tNhập số giờ làm thêm cần thêm: ");
                        str = sc.nextLine();
                        if (Function.isTrueNumber(str)) {
                            this.soGioLamThem += Integer.parseInt(str);
                            System.out.println("\t[Notice] Số giờ làm thêm đã được cập nhật.");
                        } else {
                            System.out.println("\tVui lòng nhập số!");
                        }
                        break;
                    case 9:
                        System.out.print("\tNhập số đơn đã pha chế cần thêm: ");
                        str = sc.nextLine();
                        if (Function.isTrueNumber(str)) {
                            this.soDonDaPhaChe += Integer.parseInt(str);
                            System.out.println("\t[Notice] Số đơn đã pha chế đã được cập nhật.");
                        } else {
                            System.out.println("\tVui lòng nhập số!");
                        }
                        break;
                    case 10:
                        System.out.println("\tThoát chỉnh sửa.");
                        return;
                    default:
                        System.out.println("\tLựa chọn không hợp lệ!");
                }
            } else {
                System.out.println("\tVui lòng nhập số!");
            }
        }
    }

    @Override
    public String makeString() {
        StringBuilder sb = new StringBuilder(super.makeString()); // Gọi makeString của lớp cha
        sb.append("|").append(this.soDonDaPhaChe).append("|");
        sb.append(this.soGioLamThem);
        return sb.toString();
    }

    @Override
    public void xuatThongTin() {
        super.xuatThongTin();
        // System.out.println("\tSố đơn đã pha chế: " + this.soDonDaPhaChe);
        // System.out.println("\tSố giờ làm thêm: " + this.soGioLamThem);
    }

    @Override
    public void thongTinChiTiet() {
        System.out.printf(
                "\t| %-12s | %-27s | %-20s | %-100s |\n",
                this.getMaNhanVien(), this.getSoDonDaPhaChe(),
                this.getSoGioLamThem(), this.getDiaChi());
    }

}
