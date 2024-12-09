package NhanVien;

import Utils.INhap;
import Utils.IXuat;
import Utils.Address;
import Utils.Date;
import Utils.Function;
import Utils.INhap;
import Utils.IXuat;
import java.util.Scanner;

@SuppressWarnings({ "resource", "unused" })
public class NhanVienThuNgan extends Nhanvien implements INhap, IXuat {
    private int soBillDaXuLy;
    private double tongTienDaXuLy;
    private int soGioLamThem;

    public NhanVienThuNgan() {
        super("NVTN");
    }

    public NhanVienThuNgan(String loaiNhanVien) {
        super(loaiNhanVien);
    }

    public NhanVienThuNgan(String maNhanVien, String tenNhanVien, int tuoi, Date ngaySinh, Address diaChi,
            String loaiNhanVien, String caLamViec) {
        super(maNhanVien, tenNhanVien, tuoi, ngaySinh, diaChi, loaiNhanVien, caLamViec);
    }
    public NhanVienThuNgan(String maNhanVien, String tenNhanVien, int tuoi, Date ngaySinh, Address diaChi,
            String loaiNhanVien, String caLamViec, int soBillDaXuLy, double tongTienDaXuLy, int soGioLamThem) {
        super(maNhanVien, tenNhanVien, tuoi, ngaySinh, diaChi, loaiNhanVien, caLamViec);
        this.soBillDaXuLy = soBillDaXuLy;
        this.tongTienDaXuLy = tongTienDaXuLy;
        this.soGioLamThem = soGioLamThem;
    }

    public NhanVienThuNgan(int soBillDaXuLy, double tongTienDaXuLy, int soGioLamThem) {
        this.soBillDaXuLy = soBillDaXuLy;
        this.tongTienDaXuLy = tongTienDaXuLy;
        this.soGioLamThem = soGioLamThem;
    }

    public NhanVienThuNgan(String loaiNhanVien, int soBillDaXuLy, double tongTienDaXuLy, int soGioLamThem) {
        super(loaiNhanVien);
        this.soBillDaXuLy = soBillDaXuLy;
        this.tongTienDaXuLy = tongTienDaXuLy;
        this.soGioLamThem = soGioLamThem;
    }

    public int getSoBillDaXuLy() {
        return soBillDaXuLy;
    }

    public void setSoBillDaXuLy(int soBillDaXuLy) {
        this.soBillDaXuLy = soBillDaXuLy;
    }

    public double getTongTienDaXuLy() {
        return tongTienDaXuLy;
    }

    public void setTongTienDaXuLy(double tongTienDaXuLy) {
        this.tongTienDaXuLy = tongTienDaXuLy;
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
            System.out.print("\tNhập số đơn đã xử lý: ");
            str = sc.nextLine();
            if (Function.isTrueNumber(str)) {
                this.soBillDaXuLy = Integer.parseInt(str);
                break;
            } else {
                System.out.println("\tVui lòng nhập số hợp lệ !");
            }
        }
        System.out.println("\t[Notice] Số đơn đã xử lý: " + this.soBillDaXuLy);

        while (true) {
            System.out.print("\tNhập tổng tiền đã xử lý: ");
            str = sc.nextLine();
            if (Function.isTrueNumber(str)) {
                this.tongTienDaXuLy = Double.parseDouble(str);
                break;
            } else {
                System.out.println("\tVui lòng nhập số tiền hợp lệ !");
            }
        }
        System.out.println("\t[Notice] Tổng tiền đã xử lý: " + this.tongTienDaXuLy);

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

        System.out.println("\t[Notice] Nhập thông tin nhân viên thu ngân hoàn tất.");
    }

    @Override
    public void xuatThongTin() {
        super.xuatThongTin();
        // System.out.println("\tSố bill đã xử lý: " + this.soBillDaXuLy);
        // System.out.println("\tTổng tiền đã xử lý: " + Function.formatMoney(String.valueOf(this.tongTienDaXuLy)));
        // System.out.println("\tSố giờ làm thêm: " + this.soGioLamThem);
    }
    @Override
    public void thongTinChiTiet() {
        System.out.println("\t+----------------------------------------------------------------------+");
        System.out.println("\t|                          Thông Tin Chi Tiết                         |");
        System.out.println("\t+----------------------------------------------------------------------+");
        System.out.printf("\t| %-30s: %-40s |\n", "Mã nhân viên", this.maNhanVien);
        System.out.println("\t+----------------------------------------------------------------------+");
        System.out.printf("\t| %-30s: %-70s |\n", "Địa chỉ", this.diaChi.toString());
        System.out.println("\t+----------------------------------------------------------------------+");
        System.out.printf("\t| %-30s: %-15d |\n", "Số bill đã xử lý", this.soBillDaXuLy);
        System.out.printf("\t| %-30s: %-15s |\n", "Tổng tiền đã xử lý", Function.formatMoney(String.valueOf(this.tongTienDaXuLy)));
        System.out.printf("\t| %-30s: %-15d |\n", "Số giờ làm thêm", this.soGioLamThem);
        System.out.println("\t+----------------------------------------------------------------------+");
    }
    
    
    


    public void addGioLamThem(int soGioLamThem) {
        this.soGioLamThem += soGioLamThem;
    }

    @Override
    public void tinhLuong() {
        System.out.println("\tLương căn bản: "+ Function.formatMoney("1500000"));
        // Nếu ca tối phụ cấp 500000 còn không thì hiện 0
        System.out.println("\tPhụ cấp ca: " + (this.caLamViec.equals("EVENING") ? Function.formatMoney("500000") : Function.formatMoney("0")));
        // Nếu số giờ làm thêm lớn hơn 0 thì tính tiền làm thêm
        if (this.soGioLamThem > 0) {
            System.out.println("\tTiền làm thêm: " + Function.formatMoney(String.valueOf(this.soGioLamThem * 50000)));
        } else {
            System.out.println("\tTiền làm thêm: " + Function.formatMoney("0"));
        }
        // Tính tổng lương
        double tongLuong = 1500000 + (this.caLamViec.equals("EVENING") ? 500000 : 0) + (this.soGioLamThem * 50000);
        System.out.println("\tTổng lương: " + Function.formatMoney(String.valueOf(tongLuong)));
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
            System.out.println("\t6. Số bill đã xử lý");
            System.out.println("\t7. Tổng tiền đã xử lý");
            System.out.println("\t8. Số giờ làm thêm");
            System.out.println("\t9. Thêm giờ làm thêm");
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
                        System.out.print("\tNhập số bill đã xử lý mới: ");
                        str = sc.nextLine();
                        if (Function.isTrueNumber(str)) {
                            this.soBillDaXuLy = Integer.parseInt(str);
                            System.out.println("\t[Notice] Số bill đã xử lý đã được cập nhật.");
                        } else {
                            System.out.println("\tVui lòng nhập số!");
                        }
                        break;
                    case 7:
                        System.out.print("\tNhập tổng tiền đã xử lý mới: ");
                        str = sc.nextLine();
                        if (Function.isTrueNumber(str)) {
                            this.tongTienDaXuLy = Double.parseDouble(str);
                            System.out.println("\t[Notice] Tổng tiền đã xử lý đã được cập nhật.");
                        } else {
                            System.out.println("\tVui lòng nhập số!");
                        }
                        break;
                    case 8:
                        System.out.print("\tNhập số giờ làm thêm mới: ");
                        str = sc.nextLine();
                        if (Function.isTrueNumber(str)) {
                            this.soGioLamThem = Integer.parseInt(str);
                            System.out.println("\t[Notice] Số giờ làm thêm đã được cập nhật.");
                        } else {
                            System.out.println("\tVui lòng nhập số!");
                        }
                        break;
                    case 9:
                        System.out.print("\tNhập số giờ làm thêm: ");
                        str = sc.nextLine();
                        if (Function.isTrueNumber(str)) {
                            this.soGioLamThem += Integer.parseInt(str);
                            System.out.println("\t[Notice] Số giờ làm thêm đã được cập nhật.");
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
        sb.append("|").append(this.soBillDaXuLy).append("|");
        sb.append(this.soGioLamThem).append("|");
        sb.append(this.tongTienDaXuLy);
        return sb.toString();
    }
}
