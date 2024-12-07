package NhanVien;

import HoaDon.HoaDon;
import Utils.Address;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NhanVienQuanLy extends Nhanvien {
    private List<Nhanvien> danhSachNhanVien;
    private List<HoaDon> danhSachHoaDon;// chưa tạo hóa đơn

    public NhanVienQuanLy() {
        super();
        this.danhSachNhanVien = new ArrayList<>();
        this.danhSachHoaDon = new ArrayList<>();
    }

    public NhanVienQuanLy(Address diaChi, int loaiNhanVien, String maNhanVien, LocalDate ngaySinh, String tenNhanVien, String chiNhanh) {
        super(diaChi, loaiNhanVien, maNhanVien, ngaySinh, tenNhanVien, chiNhanh);
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
        System.out.println(
                "\t===========================[ Báo Cáo Thống Kê Nhân Viên Quản Lý ]===========================");
        System.out.printf("\t| %-4s | %-50s | %-20s |%n", "STT", "Thông tin", "Chi tiết");
        System.out.println(
                "\t|------|----------------------------------------------------|----------------------|");

        System.out.printf("\t| %-4s | %-50s | %-20d |%n", "1", "Số lượng nhân viên quản lý", soLuongNhanVien());
        System.out.printf("\t| %-4s | %-50s |%n", "2", "Danh sách nhân viên:");
        int stt = 1;
        for (Nhanvien nv : danhSachNhanVien) {
            System.out.printf("\t|      | %-4s. %-45s |%n", stt++, "Mã: " + nv.getMaNhanVien() + ", Tên: " + nv.getTenNhanVien());
        }
        System.out.printf("\t| %-4s | %-50s | %-20d |%n", "3", "Số lượng hóa đơn", danhSachHoaDon.size());
        System.out.printf("\t| %-4s | %-50s | %-20.2f |%n", "4", "Tổng doanh thu (VND)", tinhTongDoanhThu());
        System.out.printf("\t| %-4s | %-50s | %-20.2f |%n", "5", "Thưởng (VND)", tinhThuong());
        System.out.println(
                "\t===========================================================================================");
    }
    
    @Override
    public void suaThongTin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sửa thông tin nhân viên quản lý:");
    
        System.out.print("Nhập mã nhân viên mới: ");
        String maNhanVienMoi = scanner.nextLine();
        if (!maNhanVienMoi.isEmpty()) {
            setMaNhanVien(maNhanVienMoi);
        }
    
        System.out.print("Nhập tên nhân viên mới: ");
        String tenNhanVienMoi = scanner.nextLine();
        if (!tenNhanVienMoi.isEmpty()) {
            setTenNhanVien(tenNhanVienMoi);
        }
    
        System.out.print("Nhập ngày sinh mới (yyyy-MM-dd): ");
        String ngaySinhMoi = scanner.nextLine();
        if (!ngaySinhMoi.isEmpty()) {
            setNgaySinh(LocalDate.parse(ngaySinhMoi));
        }
    
        System.out.print("Nhập địa chỉ mới: ");
        String diaChiMoi = scanner.nextLine();
        if (!diaChiMoi.isEmpty()) {
            Address newAddress = new Address(); 
            newAddress.setInfo(); 
            setDiaChi(newAddress);
        }
    
        System.out.print("Nhập chi nhánh mới: ");
        String chiNhanhMoi = scanner.nextLine();
        if (!chiNhanhMoi.isEmpty()) {
            setChiNhanh(chiNhanhMoi);
        }
    
        System.out.println("Thông tin nhân viên quản lý đã được cập nhật.");
    }
    public Nhanvien timNhanVienTheoMa(String maNhanVien) {
        for (Nhanvien nv : danhSachNhanVien) {
            if (nv.getMaNhanVien().equals(maNhanVien)) {
                return nv;
            }
        }
        return null; // Nếu không tìm thấy
    }
    public void capNhatThongTinNhanVien(String maNhanVien) {
        Nhanvien nv = timNhanVienTheoMa(maNhanVien);
        if (nv != null) {
            nv.suaThongTin();
        } else {
            System.out.println("Không tìm thấy nhân viên với mã: " + maNhanVien);
        }
    }
    
    public double tinhTongLuongNhanVien() {
        double tongLuong = 0;
        for (Nhanvien nv : danhSachNhanVien) {
            if (nv instanceof NhanVienQuanLy) {
                tongLuong += ((NhanVienQuanLy) nv).tinhLuong(); 
            }
        }
        return tongLuong;
    }
        
    public Map<String, List<Nhanvien>> phanLoaiNhanVienTheoCaLamViec() {
    Map<String, List<Nhanvien>> map = new HashMap<>();
    for (Nhanvien nv : danhSachNhanVien) {
        String caLamViec = nv.getCaLamViec(); // Ví dụ: "Sáng", "Chiều", "Tối"
        map.putIfAbsent(caLamViec, new ArrayList<>());
        map.get(caLamViec).add(nv);
    }
    return map;
}

public Map<String, List<Nhanvien>> phanLoaiNhanVienTheoChucVu() {
    Map<String, List<Nhanvien>> map = new HashMap<>();
    for (Nhanvien nv : danhSachNhanVien) {
        String chucVu = nv.getLoaiNhanVien() == 1 ? "Pha chế" : "Quản lý"; // Giả sử `1` là Pha chế, `2` là Quản lý
        map.putIfAbsent(chucVu, new ArrayList<>());
        map.get(chucVu).add(nv);
    }
    return map;
}

    @Override
    public double tinhLuong() {
        double luongCoBan = 7000000;
        return luongCoBan + tinhThuong();
    }
}
