package NhanVien;

import Utils.Address;
import java.time.LocalDate;
import java.util.Scanner;

public abstract class Nhanvien {
    private String maNhanVien;
    private String tenNhanVien;
    private LocalDate ngaySinh;
    private Address diaChi;
    private int loaiNhanVien;
    private String caLamViec;
    private String chiNhanh; // Thêm thuộc tính chi nhánh

    public Nhanvien() {
        this.diaChi = new Address();
    }

    public Nhanvien(Address diaChi, int loaiNhanVien, String maNhanVien, LocalDate ngaySinh, String tenNhanVien, String chiNhanh, String caLamViec) {
        this.diaChi = diaChi;
        this.loaiNhanVien = loaiNhanVien;
        this.maNhanVien = maNhanVien;
        this.ngaySinh = ngaySinh;
        this.tenNhanVien = tenNhanVien;
        this.chiNhanh = chiNhanh;
        this.caLamViec = caLamViec;  // Khởi tạo ca làm việc
    }
    
    // Getter và Setter cho chi nhánh
    public String getChiNhanh() {
        return chiNhanh;
    }

    public void setChiNhanh(String chiNhanh) {
        this.chiNhanh = chiNhanh;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public String getCaLamViec() {
        return caLamViec;
    }
    
    public void setCaLamViec(String caLamViec) {
        this.caLamViec = caLamViec;
    }
    
    public Address getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(Address diaChi) {
        this.diaChi = diaChi;
    }

    public int getLoaiNhanVien() {
        return loaiNhanVien;
    }

    public void setLoaiNhanVien(int loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }
    public void nhapThongTin() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Nhập mã nhân viên: ");
        this.maNhanVien = scanner.nextLine();
    
        System.out.print("Nhập tên nhân viên: ");
        this.tenNhanVien = scanner.nextLine();
    
        System.out.print("Nhập ngày sinh (yyyy-mm-dd): ");
        this.ngaySinh = LocalDate.parse(scanner.nextLine());
    
        System.out.print("Nhập địa chỉ: ");
        this.diaChi = new Address(); 
        this.diaChi.setInfo(); 
        
        System.out.print("Nhập loại nhân viên: ");
        this.loaiNhanVien = Integer.parseInt(scanner.nextLine());
    
        System.out.print("Nhập chi nhánh: ");
        this.chiNhanh = scanner.nextLine();
    
        System.out.print("Nhập ca làm việc: ");
        this.caLamViec = scanner.nextLine();  // Nhập ca làm việc
    }
    
    @Override
    public void suaThongTin() {
        Scanner scanner = new Scanner(System.in);
    
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
    
        System.out.print("Nhập ca làm việc mới: ");
        String caLamViecMoi = scanner.nextLine();
        if (!caLamViecMoi.isEmpty()) {
            setCaLamViec(caLamViecMoi);  // Cập nhật ca làm việc
        }
    
        System.out.println("Thông tin nhân viên đã được cập nhật.");
    }
    
    public abstract double tinhLuong();
}
