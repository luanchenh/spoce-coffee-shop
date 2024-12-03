package NhanVien;

import Utils.Address;
import java.time.LocalDate;

public abstract  class Nhanvien {
    private String maNhanVien;
    private String tenNhanVien;
    private LocalDate ngaySinh;
    private Address diaChi;
    private int loaijNhanVien;

    public Nhanvien() {
        this.diaChi = new Address();
    }

    public Nhanvien(Address diaChi, int loaijNhanVien, String maNhanVien, LocalDate ngaySinh, String tenNhanVien) {
        this.diaChi = diaChi;
        this.loaijNhanVien = loaijNhanVien;
        this.maNhanVien = maNhanVien;
        this.ngaySinh = ngaySinh;
        this.tenNhanVien = tenNhanVien;
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

    public Address getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(Address diaChi) {
        this.diaChi = diaChi;
    }

    public int getLoaijNhanVien() {
        return loaijNhanVien;
    }

    public void setLoaijNhanVien(int loaijNhanVien) {
        this.loaijNhanVien = loaijNhanVien;
    }

    public abstract void suaThongTin();
    public abstract double tinhLuong();
    
}
