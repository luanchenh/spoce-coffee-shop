package NhanVien;

import Utils.Address;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import Utils.Function;
import Utils.Date;

@SuppressWarnings("resource")
public abstract class Nhanvien {
    protected String maNhanVien;
    protected String tenNhanVien;
    protected int tuoi;
    protected Date ngaySinh;
    protected Address diaChi;
    protected String loaiNhanVien;
    protected String caLamViec;

    // Tình trạng đang làm hay dã nghỉ
    protected boolean dangLam = true;

    private static int countNhanVien = 0;

    static {

        File nhanVienFile = new File("./File/nhanvien.txt"); // Sửa lại đường dẫn nếu cần
        try (Scanner rd = new Scanner(nhanVienFile)) {
            while (rd.hasNextLine()) {
                String line = rd.nextLine();
                if (line.isEmpty())
                    continue;
                String[] parts = line.split("\\|");
                countNhanVien = Math.max(countNhanVien, Integer.parseInt(parts[0].substring(2)));
            }
        } catch (Exception e) {
            System.out.println("\tLỗinhanvien.txt: " + e.getMessage());
        }

    }

    // Constructor không tham số
    public Nhanvien() {
        this.diaChi = new Address();
        this.ngaySinh = new Date();
        this.loaiNhanVien = "";
        this.maNhanVien = "";
        this.tenNhanVien = "";
        this.tuoi = 0;
        this.caLamViec = "";
    }

    // Constructor biết loại nhân viên
    // NVPC - NVTN - NVQL
    public Nhanvien(String loaiNhanVien) {
        this.diaChi = new Address();
        this.ngaySinh = new Date();
        this.tenNhanVien = "";
        this.tuoi = 0;
        this.caLamViec = "";
        this.loaiNhanVien = loaiNhanVien;

        // Get ID nhân viên tạm thời
        this.maNhanVien = "NV" + (++countNhanVien);
    }

    // Constructor biết full tham số
    public Nhanvien(String maNhanVien, String tenNhanVien, int tuoi, Date ngaySinh, Address diaChi, String loaiNhanVien,
            String caLamViec) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.tuoi = tuoi;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.loaiNhanVien = loaiNhanVien;
        this.caLamViec = caLamViec;
    }

    // Getter - Setter
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public int getTuoi() {
        return tuoi;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public Address getDiaChi() {
        return diaChi;
    }

    public String getLoaiNhanVien() {
        return loaiNhanVien;
    }

    public String getCaLamViec() {
        return caLamViec;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setDiaChi(Address diaChi) {
        this.diaChi = diaChi;
    }

    public void setLoaiNhanVien(String loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }

    public void setCaLamViec(String caLamViec) {
        this.caLamViec = caLamViec;
    }

    // Method
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;
        int number = 0;

        if (this.loaiNhanVien == null || this.loaiNhanVien.equals("")) { // Sửa lỗi NullPointerException
            while (true) {
                System.out.println("\tTổng số nhân viên hiện tại: " + countNhanVien);
                System.out.println("\t1. [Nhân viên pha chế] NVPC");
                System.out.println("\t2. [Nhân viên thu ngân] NVTN");
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
                            System.out.println("\t[Notice] Loại nhân viên hiện tại: " + this.loaiNhanVien);
                            this.maNhanVien = "NV" + (++countNhanVien);
                            System.out.println("\t[Notice] Mã nhân viên hiện tại: " + this.maNhanVien);
                            break;
                        }
                    } else {
                        System.out.println("\tVui lòng nhập số !");
                    }
                }
            }
        } else {
            System.out.println("\t[Notice] Loại nhân viên hiện tại: " + this.loaiNhanVien);
            System.out.println("\t[Notice] Mã nhân viên hiện tại: " + this.maNhanVien);
        }

        while (true) {
            System.out.print("\tNhập tên nhân viên: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    System.out.println("\tTên nhân viên không được là số !");
                } else {
                    this.tenNhanVien = Function.normalizeName(str);
                    break;
                }
            }
        }
        System.out.println("\t[Notice] Tên nhân viên hiện tại: " + this.tenNhanVien);

        // Nhập tính tuổi dựa vào ngày sinh
        this.ngaySinh.setInfo();
        String birthDateString = String.format("%04d-%02d-%02d", Integer.parseInt(this.ngaySinh.getYear()),
                Integer.parseInt(this.ngaySinh.getMonth()),
                Integer.parseInt(this.ngaySinh.getDay()));
        LocalDate birtDate = LocalDate.parse(birthDateString);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birtDate, currentDate);
        this.tuoi = period.getYears();
        System.out.println("\t[Notice] Tuổi nhân viên hiện tại: " + this.tuoi);
        this.diaChi.setInfo();
        System.out.println("\t[Notice] Địa chỉ nhân viên hiện tại: " + this.diaChi.toString());
        // Đã có loại nhân viên
        // Ca làm việc: MORNING AFTERNOON EVENING
        while (true) {
            System.out.println("\tChọn ca làm việc của nhân viên");
            System.out.println("\t1. [Buổi sáng] - MORNING");
            System.out.println("\t2. [Buổi chiều] - AFTERNOON");
            System.out.println("\t3. [Buổi tối] - EVENING");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    number = Integer.parseInt(str);
                    if (number >= 1 && number <= 3) {
                        if (number == 1) {
                            this.caLamViec = "MORNING";
                        } else if (number == 2) {
                            this.caLamViec = "AFTERNOON";
                        } else if (number == 3) {
                            this.caLamViec = "EVENING";
                        }
                        break;
                    } else {
                        System.out.println("\tVui lòng chọn từ 1 đến 3 !");
                    }
                } else {
                    System.out.println("\tVui lòng nhập số !");
                }
            }
        }
        System.out.println("\t[Notice] Ca làm việc nhân viên hiện tại: " + this.caLamViec);

    }

    public void xuatThongTin() {

        // In thông tin chính của nhân viên

        String loaiNhanVienLabel = this.loaiNhanVien.equals("NVPC") ? "Nhân viên pha chế"
                : this.loaiNhanVien.equals("NVTN") ? "Nhân viên thu ngân"
                        : this.loaiNhanVien.equals("NVQL") ? "Nhân viên quản lý" : "Không xác định";
        System.out.printf("\t| %-12s | %-27s | %-20s | %-17s | %-12s | %-14s |\n",
                this.maNhanVien, this.tenNhanVien, loaiNhanVienLabel, this.ngaySinh.toString(), this.tuoi,
                this.caLamViec);
        System.out.println(
                "\t+--------------+-----------------------------+----------------------+-------------------+--------------+----------------+");
        // System.out.println("\tĐịa chỉ");
        // System.out.println("\t" + this.diaChi.toString());
    }

    public void thongTinChiTiet() {
    }

    public String makeString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.maNhanVien).append("|");
        sb.append(this.tenNhanVien).append("|");
        sb.append(this.tuoi).append("|");
        sb.append(this.ngaySinh).append("|");
        sb.append(this.diaChi.makeString()).append("|");
        sb.append(this.loaiNhanVien).append("|");
        sb.append(this.caLamViec);
        return sb.toString();
    }

    abstract void tinhLuong();

    abstract void modifyInfo();

}
