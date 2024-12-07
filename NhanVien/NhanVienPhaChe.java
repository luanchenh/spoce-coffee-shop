package NhanVien;

import Utils.Address;
import Utils.Function;

import java.util.Scanner;
import Utils.INhap;
import Utils.IXuat;
import Utils.Date;

@SuppressWarnings("resource")
public class NhanVienPhaChe extends NhanVien implements INhap, IXuat {
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
        
    }
}

