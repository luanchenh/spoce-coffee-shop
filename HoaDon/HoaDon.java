package HoaDon;

import ThucDon.ThucDon;
import Utils.Function;
import Utils.INhap;
import Utils.IXuat;

import java.io.File;
import java.util.Date;
import NhanVien.NhanVienThuNgan;
import java.util.Scanner;

@SuppressWarnings("resource")
public class HoaDon implements INhap, IXuat {
    private String maHoaDon;
    private NhanVienThuNgan nhanVien;
    private ThucDon thucDon;
    private Date ngayTaoHoaDon;
    private String trangThaiHoaDon;


    // Get mã tự động
    private static int countBill = 0;
    static {
        int totalBillCode = 0;
        File hoaDonFile = new File("../File/hoadon.txt");
        try(Scanner rd = new Scanner(hoaDonFile)) {
            while (rd.hasNextLine()) {
                totalBillCode++;
                rd.nextLine();
            }
        } catch (Exception e) {
            System.out.println("\tLỗi: "+ e.getMessage());
        }
        countBill = totalBillCode;
    }

    public HoaDon() {
        this.maHoaDon =  "HD" + (countBill++);
        this.thucDon = new ThucDon();
        this.ngayTaoHoaDon = new Date(); // Mặc định là ngày hiện tại
        this.nhanVien = new NhanVienThuNgan();
    }

    public HoaDon(String maHoaDon, NhanVienThuNgan nhanVien ,Date ngayTaoHoaDon, ThucDon thucDon, String trangThaiHoaDon) {
        this.maHoaDon = maHoaDon;
        this.nhanVien = nhanVien;
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
    public NhanVienThuNgan getNhanVien() {
        return nhanVien;
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
    public void setNhanVien(NhanVienThuNgan nhanVien) {
        this.nhanVien = nhanVien;
    }
    @Override
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;
        System.out.println("\tThông tin mã hóa đơn đã được tự động tạo: " + this.maHoaDon);

        System.out.println("\tNhập thông tin thực đơn:");
        this.thucDon.nhap();

        while(true) {
            System.out.println("\tChọn trạng thái hoá đơn");
            System.out.println("\t1. Đang xử lý");
            System.out.println("\t2. Đã làm xong");
            System.out.println("\t3. Huỷ đơn");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            }
            else {
                if (Function.isTrueNumber(str)) {
                    int number = Integer.parseInt(str);
                    if (number >= 1 && number <= 3) {
                        if (number == 1) {
                            this.trangThaiHoaDon = "Đang xử lý";
                            break;
                        }
                        else if (number == 2) {
                            this.trangThaiHoaDon = "Đã làm xong";
                            break;
                        }
                        else if (number == 3) {
                            this.trangThaiHoaDon = "Huỷ đơn";
                            break;
                        }
                    }
                    else {
                        System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                    }
                }
                else {
                    System.out.println("\tVui lòng nhập số !");
                }
            }
        }
        System.out.print("\tNhập trạng thái hóa đơn: ");
        this.trangThaiHoaDon = sc.nextLine();

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


