package HoaDon;

import ThucDon.ThucDon;
import Utils.INhap;
import Utils.IXuat;
import java.util.Date;
import java.util.Scanner;

public class HoaDon implements INhap, IXuat {
    private static int count = 1;
    private String maHoaDon;
    private ThucDon thucDon;
    private Date ngayTaoHoaDon;
    private String trangThaiHoaDon;

    public HoaDon() {
        this.maHoaDon =  "HD" + (count++);
        this.thucDon = new ThucDon();
        this.ngayTaoHoaDon = new Date(); // Mặc định là ngày hiện tại
    }

    public HoaDon(String maHoaDon, Date ngayTaoHoaDon, ThucDon thucDon, String trangThaiHoaDon) {
        this.maHoaDon = maHoaDon;
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.thucDon = thucDon;
        this.trangThaiHoaDon = trangThaiHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public ThucDon getThucDon() {
        return thucDon;
    }

    public void setThucDon(ThucDon thucDon) {
        this.thucDon = thucDon;
    }

    public Date getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public void setNgayTaoHoaDon(Date ngayTaoHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
    }

    public String getTrangThaiHoaDon() {
        return trangThaiHoaDon;
    }

    public void setTrangThaiHoaDon(String trangThaiHoaDon) {
        this.trangThaiHoaDon = trangThaiHoaDon;
    }

    @Override
    public void nhapThongTin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Thông tin mã hóa đơn đã được tự động tạo: " + this.maHoaDon);

        System.out.println("Nhập thông tin thực đơn:");
        this.thucDon.nhap();

        System.out.print("Nhập trạng thái hóa đơn: ");
        this.trangThaiHoaDon = scanner.nextLine();

        this.ngayTaoHoaDon = new Date(); 
    }
    public double getTongTien(){
        return 1;
    }
    
    @Override
    public void xuatThongTin() {
        System.out.println("========================================");
        System.out.println("           THÔNG TIN HÓA ĐƠN            ");
        System.out.println("========================================");
        System.out.printf("Mã hóa đơn        : %s%n", this.maHoaDon);
        System.out.printf("Ngày tạo hóa đơn  : %s%n", this.ngayTaoHoaDon);
        System.out.printf("Trạng thái hóa đơn: %s%n", this.trangThaiHoaDon);
        System.out.println("----------------------------------------");
        System.out.println("         CHI TIẾT THỰC ĐƠN              ");
        System.out.println("----------------------------------------");
        this.thucDon.xuat(); //xuất
        System.out.println("========================================");
        System.out.println("         CẢM ƠN QUÝ KHÁCH!             ");
        System.out.println("========================================");
    }
}


