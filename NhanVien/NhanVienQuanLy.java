package NhanVien;

import HoaDon.HoaDon;
import Utils.Address;
import Utils.INhap;
import Utils.IXuat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("unused")
public class NhanVienQuanLy extends Nhanvien implements INhap, IXuat {
    private List<Nhanvien> danhSachNhanVien;
    private List<HoaDon> danhSachHoaDon;
    private String chiNhanh; // CN1, CN2, CN3

    public NhanVienQuanLy() {

    }

    @Override
    public void xuatThongTin() {

    }

    @Override
    public void tinhLuong() {

    }

    @Override
    void modifyInfo() {

    }



    // Thống kê và báo cáo
    // public void thongKeBaoCao() {
    //     System.out.println(
    //             "\t===========================[ Báo Cáo Thống Kê Nhân Viên Quản Lý ]===========================");
    //     System.out.printf("\t| %-4s | %-50s | %-20s |%n", "STT", "Thông tin", "Chi tiết");
    //     System.out.println(
    //             "\t|------|----------------------------------------------------|----------------------|");

    //     System.out.printf("\t| %-4s | %-50s | %-20d |%n", "1", "Số lượng nhân viên quản lý", soLuongNhanVien());
    //     System.out.printf("\t| %-4s | %-50s |%n", "2", "Danh sách nhân viên:");
    //     int stt = 1;
    //     for (Nhanvien nv : danhSachNhanVien) {
    //         System.out.printf("\t|      | %-4s. %-45s |%n", stt++, "Mã: " + nv.getMaNhanVien() + ", Tên: " + nv.getTenNhanVien());
    //     }
    //     System.out.printf("\t| %-4s | %-50s | %-20d |%n", "3", "Số lượng hóa đơn", danhSachHoaDon.size());
    //     System.out.printf("\t| %-4s | %-50s | %-20.2f |%n", "4", "Tổng doanh thu (VND)", tinhTongDoanhThu());
    //     System.out.printf("\t| %-4s | %-50s | %-20.2f |%n", "5", "Thưởng (VND)", tinhThuong());
    //     System.out.println(
    //             "\t===========================================================================================");
    // }

}
