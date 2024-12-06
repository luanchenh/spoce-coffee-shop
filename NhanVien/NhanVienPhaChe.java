package NhanVien;

import Utils.Address;
import Utils.Function;
import java.time.LocalDate;
import java.util.Scanner;

public class NhanVienPhaChe extends Nhanvien {
    private int soDonDaPhaChe;
    private String caLamViec; // "Sáng", "Chiều", "Buổi tối"
    private int soGioLamThem;

    public NhanVienPhaChe() {
        super();
    }

    public NhanVienPhaChe(Address diaChi, int loaiNhanVien, String maNhanVien, LocalDate ngaySinh, String tenNhanVien, String chiNhanh, 
                          int soDonDaPhaChe, String caLamViec, int soGioLamThem) {
        super(diaChi, loaiNhanVien, maNhanVien, ngaySinh, tenNhanVien, chiNhanh, caLamViec); 
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


@Override
public void suaThongTin() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Sửa thông tin nhân viên pha chế:");

    // Sửa mã nhân viên
    while (true) {
        System.out.print("Nhập mã nhân viên mới: ");
        String maNhanVienMoi = scanner.nextLine();

        if (Function.isEmpty(maNhanVienMoi)) {
            System.out.println("Mã nhân viên không được để trống!");
            continue;
        }
        setMaNhanVien(maNhanVienMoi);
        break;
    }

    // Sửa tên nhân viên
    while (true) {
        System.out.print("Nhập tên nhân viên mới: ");
        String tenNhanVienMoi = scanner.nextLine();
        if (Function.isEmpty(tenNhanVienMoi)) {
            System.out.println("Tên nhân viên không được để trống!");
            continue;
        }
        setTenNhanVien(tenNhanVienMoi);
        break;
    }

    // Sửa ngày sinh nhân viên
    while (true) {
        System.out.print("Nhập ngày sinh mới (yyyy-MM-dd): ");
        String ngaySinhMoi = scanner.nextLine();
        if (Function.isEmpty(ngaySinhMoi)) {
            System.out.println("Ngày sinh không được để trống!");
            continue;
        }
        try {
            setNgaySinh(LocalDate.parse(ngaySinhMoi));
            break;
        } catch (Exception e) {
            System.out.println("Định dạng ngày sinh không hợp lệ! Vui lòng nhập lại theo định dạng (yyyy-MM-dd).");
        }
    }

    // Sửa địa chỉ nhân viên
    while (true) {
        System.out.print("Nhập địa chỉ mới: ");
        String diaChiMoi = scanner.nextLine();
        if (Function.isEmpty(diaChiMoi)) {
            System.out.println("Địa chỉ không được để trống!");
            continue;
        }
        Address newAddress = new Address();
        newAddress.setInfo();
        setDiaChi(newAddress);
        break;
    }

    // Sửa số đơn đã pha chế
    while (true) {
        System.out.print("Nhập số đơn đã pha chế mới: ");
        String soDonMoiStr = scanner.nextLine();
        if (Function.isEmpty(soDonMoiStr)) {
            System.out.println("Số đơn không được để trống!");
            continue;
        }
        if (!Function.isTrueNumber(soDonMoiStr)) {
            System.out.println("Số đơn phải là một số hợp lệ!");
            continue;
        }
        int soDonMoi = Integer.parseInt(soDonMoiStr);
        if (soDonMoi >= 0) {
            setSoDonDaPhaChe(soDonMoi);
            break;
        } else {
            System.out.println("Số đơn pha chế không được âm!");
        }
    }

    // Sửa ca làm việc
    while (true) {
        System.out.print("Nhập ca làm việc mới (Sáng/Chiều/Buổi tối): ");
        String caMoi = scanner.nextLine();
        if (Function.isEmpty(caMoi)) {
            System.out.println("Ca làm việc không được để trống!");
            continue;
        }
        setCaLamViec(caMoi);
        break;
    }

    // Sửa số giờ làm thêm
    while (true) {
        System.out.print("Nhập số giờ làm thêm mới: ");
        String gioLamThemMoiStr = scanner.nextLine();
        if (Function.isEmpty(gioLamThemMoiStr)) {
            System.out.println("Số giờ làm thêm không được để trống!");
            continue;
        }
        if (!Function.isTrueNumber(gioLamThemMoiStr)) {
            System.out.println("Số giờ làm thêm phải là một số hợp lệ!");
            continue;
        }
        int gioLamThemMoi = Integer.parseInt(gioLamThemMoiStr);
        if (gioLamThemMoi >= 0) {
            setSoGioLamThem(gioLamThemMoi);
            break;
        } else {
            System.out.println("Số giờ làm thêm không được âm!");
        }
    }

    System.out.println("Thông tin nhân viên đã được cập nhật.");
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
}

