package NhanVien;

import Utils.Address;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NhanVienQuanLy extends Nhanvien {
    private List<Nhanvien> danhSachNhanVien;
    private List<HoaDon> danhSachHoaDon;// chưa tạo hóa đơn

    public NhanVienQuanLy() {
        super();
        this.danhSachNhanVien = new ArrayList<>();
        this.danhSachHoaDon = new ArrayList<>();
    }

    public NhanVienQuanLy(Address diaChi, int loaijNhanVien, String maNhanVien, LocalDate ngaySinh, String tenNhanVien) {
        super(diaChi, loaijNhanVien, maNhanVien, ngaySinh, tenNhanVien);
        this.danhSachNhanVien = new ArrayList<>();
        this.danhSachHoaDon = new ArrayList<>();
    }

    // Thêm nhân viên vào danh sách quản lý
    public void themNhanVien(Nhanvien nhanVien) {
        danhSachNhanVien.add(nhanVien);
    }

    // Xóa nhân viên khỏi danh sách quản lý
    public void xoaNhanVien(String maNhanVien) {
        danhSachNhanVien.removeIf(nv -> nv.getMaNhanVien().equals(maNhanVien));
    }

    // Lấy danh sách nhân viên
    public List<Nhanvien> danhSachNhanVien() {
        return danhSachNhanVien;
    }

    // Lấy số lượng nhân viên
    public int soLuongNhanVien() {
        return danhSachNhanVien.size();
    }

    // Lấy danh sách hóa đơn (giả sử lớp HoaDon có sẵn)
    public List<HoaDon> danhSachHoaDon() {
        return danhSachHoaDon;
    }

    // Tính tổng doanh thu
    public double tinhTongDoanhThu() {
        double tongDoanhThu = 0;
        for (HoaDon hoaDon : danhSachHoaDon) {
            tongDoanhThu += hoaDon.getTongTien(); // Giả sử lớp HoaDon có phương thức getTongTien
        }
        return tongDoanhThu;
    }

    // Tính thưởng dựa trên doanh thu
    public double tinhThuong() {
        double doanhThu = tinhTongDoanhThu();
        if (doanhThu > 500000000) {
            return 1000000; // Thưởng 1 triệu nếu doanh thu > 500 triệu
        } else if (doanhThu > 300000000) {
            return 500000; // Thưởng 500k nếu doanh thu > 300 triệu
        }
        return 0; // Không thưởng nếu doanh thu <= 300 triệu
    }

    // Thống kê và báo cáo
    public void thongKeBaoCao() {
        System.out.println("=== Báo cáo thống kê ===");
        System.out.println("Số lượng nhân viên quản lý: " + soLuongNhanVien());
        System.out.println("Danh sách nhân viên:");
        for (Nhanvien nv : danhSachNhanVien) {
            System.out.println("- Mã: " + nv.getMaNhanVien() + ", Tên: " + nv.getTenNhanVien());
        }
        System.out.println("Số lượng hóa đơn: " + danhSachHoaDon.size());
        System.out.println("Tổng doanh thu: " + tinhTongDoanhThu() + " VND");
        System.out.println("Thưởng: " + tinhThuong() + " VND");
    }

    @Override
    public void suaThongTin() {
        // Logic sửa thông tin quản lý nếu cần
    }

    @Override
    public double tinhLuong() {
        double luongCoBan = 7000000; // Giả sử lương cơ bản là 7 triệu
        return luongCoBan + tinhThuong(); // Lương cơ bản + thưởng doanh thu
    }
}
