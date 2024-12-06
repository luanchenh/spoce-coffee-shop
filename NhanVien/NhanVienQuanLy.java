package NhanVien;

import Utils.Address;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import HoaDon.HoaDon;

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

    public void themNhanVien(Nhanvien nhanVien) {
        danhSachNhanVien.add(nhanVien);
    }

    public void xoaNhanVien(String maNhanVien) {
        danhSachNhanVien.removeIf(nv -> nv.getMaNhanVien().equals(maNhanVien));
    }

    public List<Nhanvien> danhSachNhanVien() {
        return danhSachNhanVien;
    }

    public int soLuongNhanVien() {
        return danhSachNhanVien.size();
    }
    // chưa khởi tạo class HoaDon
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
    //Hàm tính thưởng tự định nghĩa
    public double tinhThuong() {
        double doanhThu = tinhTongDoanhThu();
        if (doanhThu > 50000000) {
            return 1000000; // Thưởng 1 triệu nếu doanh thu > 50 triệu
        } else if (doanhThu > 30000000) {
            return 500000; // Thưởng 500k nếu doanh thu > 30 triệu
        }
        return 0;
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

    }

    @Override
    public double tinhLuong() {
        double luongCoBan = 7000000;
        return luongCoBan + tinhThuong();
    }
}
