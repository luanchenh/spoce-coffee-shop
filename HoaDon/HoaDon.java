package HoaDon;

import KhachHang.KhachHang;
import NhanVien.NhanVienThuNgan;
import ThucDon.ThucDon;
import Utils.Date;
import Utils.Function;
import Utils.IXuat;
import java.io.File;
import java.util.Scanner;

@SuppressWarnings({ "resource", "unused" })
public class HoaDon implements IXuat {
    private String maHoaDon;
    private NhanVienThuNgan nhanVien;
    private KhachHang khachHang;
    private ThucDon thucDon;
    private Date ngayTaoHoaDon;
    private String trangThaiHoaDon;
    private double tongTien;
    private double tienKhachDua;
    private double tienThua;


    // Get mã tự động
    private static int countBill = 0;
    static {
        int totalBillCode = 0;
        File hoaDonFile = new File("../File/hoadon.txt");
        try (Scanner rd = new Scanner(hoaDonFile)) {
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
        this.ngayTaoHoaDon.getLocalDate();
        this.nhanVien = new NhanVienThuNgan();
    }

    public HoaDon(String maHoaDon, KhachHang khachHang, NhanVienThuNgan nhanVien ,Date ngayTaoHoaDon, ThucDon thucDon, String trangThaiHoaDon, double tongTien, double tienKhachDua) {
        this.maHoaDon = maHoaDon;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.thucDon = thucDon;
        this.trangThaiHoaDon = trangThaiHoaDon;
        this.tongTien = tongTien;
        this.tienKhachDua = tienKhachDua;
        this.tienThua = tienKhachDua - this.tongTien;
    }

    public HoaDon(KhachHang khachHang, NhanVienThuNgan nhanVien, ThucDon thucDon, double tongTien, double tienKhachDua) {
        this.maHoaDon =  "HD" + (countBill++);
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.thucDon = thucDon;
        this.ngayTaoHoaDon = new Date();
        this.ngayTaoHoaDon.getLocalDate();
        this.trangThaiHoaDon = "Đang xử lý";
        this.tongTien = tongTien;
        this.tienKhachDua = tienKhachDua;
        this.tienThua = this.tienKhachDua - this.tongTien;
    }

    public HoaDon(KhachHang khachHang, NhanVienThuNgan nhanVien) {
        this.maHoaDon =  "HD" + (countBill++);
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.ngayTaoHoaDon = new Date();
        this.ngayTaoHoaDon.getLocalDate();
        this.trangThaiHoaDon = "Đang xử lý";
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



    // @Override
    // public void nhapThongTin() {
    //     Scanner sc = new Scanner(System.in);
    //     String str;
    //     System.out.println("\tThông tin mã hóa đơn đã được tự động tạo: " + this.maHoaDon);

    //     System.out.println("\tNhập thông tin thực đơn:");
    //     this.thucDon.nhap();

    //     while(true) {
    //         System.out.println("\tChọn trạng thái hoá đơn");
    //         System.out.println("\t1. Đang xử lý");
    //         System.out.println("\t2. Đã làm xong");
    //         System.out.println("\t3. Huỷ đơn");
    //         System.out.print("\tNhập lựa chọn: ");
    //         str = sc.nextLine();
    //         if (Function.isEmpty(str)) {
    //             System.out.println("\tVui lòng không để trống !");
    //         }
    //         else {
    //             if (Function.isTrueNumber(str)) {
    //                 int number = Integer.parseInt(str);
    //                 if (number >= 1 && number <= 3) {
    //                     if (number == 1) {
    //                         this.trangThaiHoaDon = "Đang xử lý";
    //                         break;
    //                     }
    //                     else if (number == 2) {
    //                         this.trangThaiHoaDon = "Đã làm xong";
    //                         break;
    //                     }
    //                     else if (number == 3) {
    //                         this.trangThaiHoaDon = "Huỷ đơn";
    //                         break;
    //                     }
    //                 }
    //                 else {
    //                     System.out.println("\tVui lòng chọn từ 1 đến 3 !");
    //                 }
    //             }
    //             else {
    //                 System.out.println("\tVui lòng nhập số !");
    //             }
    //         }
    //     }
    //     System.out.print("\tNhập trạng thái hóa đơn: ");
    //     this.trangThaiHoaDon = sc.nextLine();

    //     this.ngayTaoHoaDon = new Date();
    // }
    // public double getTongTien(){
    //     return 1;
    // }

    @Override
    public void xuatThongTin() {
        System.out.println("\t==============================================================");
        System.out.println("\t                      THÔNG TIN HÓA ĐƠN                       ");
        System.out.println("\t==============================================================");
        System.out.printf("\tNhân viên tạo      : %s%n", this.nhanVien.getTenNhanVien());
        System.out.printf("\tMã hóa đơn         : %s%n", this.maHoaDon);
        System.out.printf("\tNgày tạo hóa đơn   : %s%n", this.ngayTaoHoaDon);
        System.out.printf("\tTrạng thái hóa đơn : %s%n", this.trangThaiHoaDon);
        System.out.println("\tTên khách hàng     : " + this.khachHang.getCustomerName());
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t                      CHI TIẾT THỰC ĐƠN                       ");
        System.out.println("\t--------------------------------------------------------------");
        this.thucDon.xuatThongTin(); //xuất
        System.out.println("\t==============================================================");
        System.out.printf("\tTổng tiền         : %.0f VNĐ%n", this.tongTien);
        System.out.printf("\tTiền khách đưa    : %.0f VNĐ%n", this.tienKhachDua);
        System.out.printf("\tTiền thừa         : %.0f VNĐ%n", this.tienThua);

        System.out.println("\t                      CẢM ƠN QUÝ KHÁCH!                       ");
        System.out.println("\t==============================================================");
    }

    public String makeString() {
        StringBuilder str = new StringBuilder();
        str.append(this.maHoaDon).append("|");
        str.append(this.nhanVien.getMaNhanVien()).append("|");
        str.append(this.ngayTaoHoaDon.makeString()).append("|");
        str.append(this.trangThaiHoaDon).append("|");
        str.append(this.khachHang.getCustomerID()).append("|");
        str.append(this.tongTien).append("|");
        str.append(this.tienKhachDua).append("|");
        str.append(this.tienThua).append("|");

        for (int i=0; i<this.thucDon.getDanhSachNuocUong().size(); i++) {
            str.append(this.thucDon.getDanhSachNuocUong().get(i).getId()).append(",");

            str.append(this.thucDon.getSize().get(i)).append(",");

            if (this.thucDon.getDanhSachTopping().get(i).isEmpty()) {
                str.append(",");
            } else {
                for (int j=0; j<this.thucDon.getDanhSachTopping().get(i).size(); j++) {
                    if (this.thucDon.getDanhSachTopping().get(i).indexOf(this.thucDon.getDanhSachTopping().get(i).get(j)) == this.thucDon.getDanhSachTopping().get(i).size() - 1) {
                        str.append(this.thucDon.getDanhSachTopping().get(i).get(j).getId()).append(",");
                    } else {
                        str.append(this.thucDon.getDanhSachTopping().get(i).get(j).getId()).append(";");
                    }
                }
            }

            for (int j=0; j<this.thucDon.getTrangThaiNuocUong().get(i).size(); j++) {
                if (j == 3) {
                    str.append(this.thucDon.getTrangThaiNuocUong().get(i).get(j) ? "1" : "0").append("|");
                } else {
                    str.append(this.thucDon.getTrangThaiNuocUong().get(i).get(j) ? "1" : "0").append(";");
                }
            }
        }
        // CÒN LƯU NƯỚC UỐNG VÀ TOPPING KHÁCH HÀNG CHỌN

        return str.toString();
    }

    public void inRaTrangThai() {
        System.out.printf("\t| %-10s %-10s %20s |%n", this.maHoaDon, this.ngayTaoHoaDon.toString(), this.trangThaiHoaDon);
    }

    public void changeBillStatus() {
        Scanner sc = new Scanner(System.in);
        String str;

        switch (this.trangThaiHoaDon) {
            case "Đang xử lý":
            while (true) { 
                Function.clearScreen();
                System.out.println("\tTrạng thái hóa đơn hiện tại: " + this.trangThaiHoaDon);
                System.out.println("\tCác trạng thái có thể chuyển");
                System.out.println("\t1. Đã làm xong");
                System.out.println("\t2. Đã hủy");
                System.out.print("\tMời bạn chọn trạng thái chuyển đổi: ");
                str = sc.nextLine();

                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống!");
                    continue;
                }

                if (!Function.isTrueNumber(str)) {
                    System.out.println("\tVui lòng nhập số!");
                    continue;
                }

                switch (str) {
                    case "1":
                    this.trangThaiHoaDon = "Đã làm xong";
                    System.out.println("\tĐã chuyển trạng thái thành công!");
                    break;

                    case "2":
                    this.trangThaiHoaDon = "Huỷ đơn";
                    System.out.println("\tĐã chuyển trạng thái thành công!");
                    break;

                    default:
                    System.out.println("\tVui lòng chọn từ 1 đến 2!");
                    System.out.println("\tEnter để tiếp tục!");
                    str = sc.nextLine();
                    continue;
                }
                break;
            }
            break;

            default:
            System.out.println("\tTrạng thái hóa đơn hiện tại không thể thay đổi!");
            System.out.println("\tEnter để tiếp tục!");
            str = sc.nextLine();
            break;
        }
    }
}


