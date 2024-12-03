package NhanVien;

import Utils.Address;
import java.time.LocalDate;
import java.util.Scanner;

public class NhanVienPhaChe extends Nhanvien {
    private int soDonDaPhaChe;
    private String caLamViec; // "Sáng", "Chiều", "Buổi tối"
    private int soGioLamThem;

    public NhanVienPhaChe() {
        super();
    }

    public NhanVienPhaChe(Address diaChi, int loaijNhanVien, String maNhanVien, LocalDate ngaySinh, String tenNhanVien,
                          int soDonDaPhaChe, String caLamViec, int soGioLamThem) {
        super(diaChi, loaijNhanVien, maNhanVien, ngaySinh, tenNhanVien);
        this.soDonDaPhaChe = soDonDaPhaChe;
        this.caLamViec = caLamViec;
        this.soGioLamThem = soGioLamThem;
    }

    public int getSoDonDaPhaChe() {
        return soDonDaPhaChe;
    }

    public void setSoDonDaPhaChe(int soDonDaPhaChe) {
        this.soDonDaPhaChe = soDonDaPhaChe;
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
        System.out.println("Sửa thông tin nhân viên pha chế:");

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

        System.out.print("Nhập số đơn đã pha chế mới: ");
        int soDonMoi = Integer.parseInt(scanner.nextLine());
        if (soDonMoi >= 0) {
            setSoDonDaPhaChe(soDonMoi);
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

        double tienThuong = (soDonDaPhaChe > 50) ? 100000 : 0;
        if ("Buổi tối".equalsIgnoreCase(caLamViec)) {
            tienThuong += 20000;
        }

        return luongLamThem + tienThuong;
    }
}

