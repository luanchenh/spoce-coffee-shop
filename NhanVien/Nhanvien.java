package NhanVien;

import Utils.Address;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Utils.Function;
import Utils.Date;
import Utils.Address;

@SuppressWarnings("resource")
public abstract class NhanVien {
    protected String maNhanVien;
    protected String tenNhanVien;
    protected int tuoi;
    protected Date ngaySinh;
    protected Address diaChi;
    protected String loaiNhanVien;
    protected String caLamViec;

    private static final Map<String, Integer> typeCounter = new HashMap<>();

    static {
        int totalNhanVienPhaChe = 0;
        int totalNhanVienThuNgan = 0;
        int totalNhanVienQuanLy = 0;

        /*
         * NVPC: Nhân viên pha chế
         * NVTN: Nhân viên thu ngân
         * NVQL: Nhân viên quản lý
         */

        // File
        File nhanVienFile = new File("../nhanvien.txt");
        try (Scanner rd = new Scanner(nhanVienFile)) {
            while (rd.hasNextLine()) {
                String line = rd.nextLine();
                if (line.isEmpty())
                    continue;
                // Tách chuỗi theo dấu "|"
                String[] parts = line.split("\\|");
                if (parts.length > 0) {
                    String prefix = parts[0].substring(0, 4).toUpperCase(); // Lấy 4 ký tự đầu tiên
                    int number = Integer.parseInt(parts[0].substring(4)); // Lấy số thứ tự
                    if (prefix.equals("NVPC")) {
                        totalNhanVienPhaChe = Math.max(totalNhanVienPhaChe, number);
                    } else if (prefix.equals("NVTN")) {
                        totalNhanVienThuNgan = Math.max(totalNhanVienThuNgan, number);
                    } else if (prefix.equals("NVQL")) {
                        totalNhanVienQuanLy = Math.max(totalNhanVienQuanLy, number);
                    } else {
                        System.out.println("\tLỗi: Loại nhân viên không hợp lệ: " + prefix);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("\tLỗi khi đọc file nhanvien.txt: " + e.getMessage());
        }

        // Khởi tạo số thứ tự ban đầu cho từng loại nhân viên
        typeCounter.put("NVPC", totalNhanVienPhaChe);
        typeCounter.put("NVTN", totalNhanVienThuNgan);
        typeCounter.put("NVQL", totalNhanVienQuanLy);
    }

    // Constructor không tham số
    public NhanVien() {
        this.diaChi = new Address();
        this.ngaySinh = new Date();
    }

    // Constructor biết loại nhân viên
    public NhanVien(String loaiNhanVien) {
        this.diaChi = new Address();
        this.ngaySinh = new Date();
        /*
         * protected String maNhanVien;
         * protected String tenNhanVien;
         * protected Date ngaySinh;
         * protected Address diaChi;
         * protected String loaiNhanVien;
         * protected String caLamViec;
         */
        this.tenNhanVien = "";
        this.loaiNhanVien = loaiNhanVien;
        this.caLamViec = "";
        this.tuoi = 0;

        // Tăng số thứ tự cho loại nhân viên
        if (typeCounter.containsKey(loaiNhanVien)) {
            int currentCount = typeCounter.get(loaiNhanVien) + 1;
            typeCounter.put(loaiNhanVien, currentCount);
            this.maNhanVien = loaiNhanVien + currentCount;
        } else {
            this.maNhanVien = "UNKNOWN";
        }
    }

    // Constructor có tham số
    public NhanVien(String maNhanVien, String tenNhanVien, int tuoi, Date ngaySinh, Address diaChi, String loaiNhanVien,
            String caLamViec) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.tuoi = tuoi;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.loaiNhanVien = loaiNhanVien;
        this.caLamViec = caLamViec;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(String caLamViec) {
        this.caLamViec = caLamViec;
    }

    public Address getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(Address diaChi) {
        this.diaChi = diaChi;
    }

    public String getLoaiNhanVien() {
        return loaiNhanVien;
    }

    public void setLoaiNhanVien(String loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }

    // Phương thức nhập thông tin nhân viên
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;
        int number = 0;

        if (this.loaiNhanVien.equals("")) {
            while (true) {
                for (Map.Entry<String, Integer> entry : typeCounter.entrySet()) {
                    if (entry.getKey().equals("NVPC")) {
                        System.out.println("1. [Nhân viên pha chế] " + entry.getKey() + "-" + entry.getValue());
                    } else if (entry.getKey().equals("NVTN")) {
                        System.out.println("2. [Nhân viên thu ngân] " + entry.getKey() + "-" + entry.getValue());
                    } else if (entry.getKey().equals("NVQL")) {
                        System.out.println("3. [Nhân viên quản lý] " + entry.getKey() + "-" + entry.getValue());
                    }
                }
                System.out.print("\tChọn loại nhân viên: ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống !");
                    continue;
                } else {
                    if (Function.isTrueNumber(str)) {
                        number = Integer.parseInt(str);
                        if (number < 1 || number > 3) {
                            System.out.println("\tVui lòng chọn trong khoảng phạm vi từ 1 đến 3 !");
                        } else {
                            if (number == 1) {
                                this.loaiNhanVien = "NVPC";
                            } else if (number == 2) {
                                this.loaiNhanVien = "NVTN";
                            } else if (number == 3) {
                                this.loaiNhanVien = "NVQL";
                            }
                            break;
                        }
                    } else {
                        System.out.println("\tVui lòng nhập số !");
                    }
                }
            }
        }
        System.out.println("\t[Notice] Loại nhân viên hiện tại: " + this.loaiNhanVien);
        int currentCount = typeCounter.get(this.loaiNhanVien) + 1;
        typeCounter.put(this.loaiNhanVien, currentCount);
        this.maNhanVien = this.loaiNhanVien + currentCount;
        System.out.println("\t[Notice] Mã nhân viên hiện tại: " + this.maNhanVien);

        while (true) {
            System.out.print("\tNhập tên nhân viên: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    System.out.println("\tTên nhân viên không được là số !");
                } else {
                    this.tenNhanVien = str;
                    break;
                }
            }
        }
        System.out.println("\t[Notice] Tên nhân viên hiện tại: " + this.tenNhanVien);

        this.ngaySinh.setInfo();

    }

    public void suaThongTin() {

    }

    public abstract double tinhLuong();

}
