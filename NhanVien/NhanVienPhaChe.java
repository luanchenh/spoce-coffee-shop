package NhanVien;

import Utils.Address;
import Utils.Function;
import java.time.LocalDate;
import java.util.Scanner;
import Utils.INhap;
import Utils.IXuat;
import Utils.Date;

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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập thông tin nhân viên pha chế:");

        super.nhapThongTin();
        System.out.print("Nhập số đơn đã pha chế: ");
        setSoDonDaPhaChe(Integer.parseInt(scanner.nextLine()));

        System.out.print("Nhập số giờ làm thêm: ");
        setSoGioLamThem(Integer.parseInt(scanner.nextLine()));

        System.out.println("Nhập thông tin nhân viên hoàn tất.");
    }





    // Override Method
    @Override
    public void suaThongTin() {

    }

    @Override
    public double tinhLuong() {
        double luongLamThem = soGioLamThem * 20000;

        double tienThuong = (soDonDaPhaChe > 50) ? 100000 : 0;
        if ("Buổi tối".equalsIgnoreCase(super.getCaLamViec())) {
            tienThuong += 20000;
        }

        return luongLamThem + tienThuong;
    }
    @Override
    public void xuatThongTin() {

    }
}

