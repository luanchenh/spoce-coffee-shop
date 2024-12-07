package NhanVien;

import Utils.Address;
import Utils.Function;

import java.util.Scanner;
import Utils.INhap;
import Utils.IXuat;
import Utils.Date;

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
    public NhanVienPhaChe(String maNhanVien, String tenNhanVien, int tuoi, Date ngaySinh, Address diaChi, String loaiNhanVien, String caLamViec, int soDonDaPhaChe, int soGioLamThem) {
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
    // Override Method

    @Override
    public void tinhLuong() {
    }
    @Override
    public void xuatThongTin() {

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
        System.out.println("\t8. Thoát");
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
}


