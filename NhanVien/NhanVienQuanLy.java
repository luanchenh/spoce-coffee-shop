package NhanVien;

import java.util.Scanner;

import Utils.Address;
import Utils.Date;
import Utils.Function;
import Utils.INhap;
import Utils.IXuat;

public class NhanVienQuanLy extends Nhanvien implements INhap, IXuat {

    public NhanVienQuanLy() {
        super("NVQL");
    }

    public NhanVienQuanLy(String loaiNhanVien) {
        super(loaiNhanVien);
    }

    public NhanVienQuanLy(String maNhanVien, String tenNhanVien, int tuoi, Date ngaySinh, Address diaChi, String loaiNhanVien, String caLamViec) {
        super(maNhanVien, tenNhanVien, tuoi, ngaySinh, diaChi, loaiNhanVien, caLamViec);
    }

    @Override
    void tinhLuong() {
        // Nhân viên quản lí không có lương cứng
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
    public void thongTinChiTiet() {
        System.out.printf("\t| %-12s | %-27s | %-20s | %-17s | %-80s |\n", this.getMaNhanVien(), this.getTenNhanVien(), this.getNgaySinh().toString(), this.getCaLamViec(), this.getDiaChi().toString());
    }

    @Override
    public void nhapThongTin() {
        super.nhapThongTin();
    }

    @Override
    public String makeString() {
        return super.makeString();
    }
}
