package NhanVien;

import Utils.Address;
import java.time.LocalDate;
import java.util.Scanner;

public class NhanVienThuNgan extends Nhanvien {
    private int soBillDaXuLy;
    private double tongTienDaXuLy;
    private String caLamViec; // "Sáng", "Chiều", "Buổi tối"
    private int soGioLamThem;


    public NhanVienThuNgan() {
        super();
    }

    public NhanVienThuNgan(Address diaChi, int loaijNhanVien, String maNhanVien, LocalDate ngaySinh, String tenNhanVien,
                           int soBillDaXuLy, double tongTienDaXuLy, String caLamViec, int soGioLamThem) {
        super(diaChi, loaijNhanVien, maNhanVien, ngaySinh, tenNhanVien);
        this.soBillDaXuLy = soBillDaXuLy;
        this.tongTienDaXuLy = tongTienDaXuLy;
        this.caLamViec = caLamViec;
        this.soGioLamThem = soGioLamThem;
    }

    public int getSoBillDaXuLy() {
        return soBillDaXuLy;
    }

    public void setSoBillDaXuLy(int soBillDaXuLy) {
        this.soBillDaXuLy = soBillDaXuLy;
    }

    public double getTongTienDaXuLy() {
        return tongTienDaXuLy;
    }

    public void setTongTienDaXuLy(double tongTienDaXuLy) {
        this.tongTienDaXuLy = tongTienDaXuLy;
    }

    public String getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(String caLamViec) {
        this.caLamViec = caLamViec;
    }

    public int getSoGioLamThem() {
        return soGioLamThem;
    }

    public void setSoGioLamThem(int soGioLamThem) {
        this.soGioLamThem = soGioLamThem;
    }

    @Override
public void suaThongTin() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Sửa thông tin nhân viên thu ngân:");

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
        newAddress.setAddress(diaChiMoi);
        setDiaChi(newAddress);
    }

    System.out.print("Nhập số bill đã xử lý mới: ");
    int soBillMoi = Integer.parseInt(scanner.nextLine());
    if (soBillMoi >= 0) {
        setSoBillDaXuLy(soBillMoi);
    }

    System.out.print("Nhập tổng tiền đã xử lý mới: ");
    double tongTienMoi = Double.parseDouble(scanner.nextLine());
    if (tongTienMoi >= 0) {
        setTongTienDaXuLy(tongTienMoi);
    }

    System.out.print("Nhập ca làm việc mới (Sáng/Chiều/Buổi tối): ");
    String caMoi = scanner.nextLine();
    if (!caMoi.isEmpty()) {
        setCaLamViec(caMoi);
    }

    System.out.print("Nhập số giờ làm thêm mới: ");
    int gioLamThemMoi = Integer.parseInt(scanner.nextLine());
    if (gioLamThemMoi >= 0) {
        setSoGioLamThem(gioLamThemMoi);
    }

    System.out.println("Thông tin nhân viên đã được cập nhật.");
}

    @Override
    public double tinhLuong() {
        double luongLamThem = soGioLamThem * 20000;
        double tienThuong = 0;

        if (this.soBillDaXuLy > 100) {
            tienThuong += 100000;
        }

        // Phụ cấp tiền ăn tối nếu làm ca buổi tối
        if ("Buổi tối".equalsIgnoreCase(caLamViec)) {
            tienThuong += 20000;
        }

        return  luongLamThem + tienThuong;
    }
}
