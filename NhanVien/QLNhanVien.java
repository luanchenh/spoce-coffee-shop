package NhanVien;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import NuocUong.Coffee;
import NuocUong.NuocTraiCay;
import NuocUong.NuocUong;
import NuocUong.QLNuocUong;
import NuocUong.SinhTo;
import NuocUong.TraSua;
import Utils.Address;
import Utils.Function;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class QLNhanVien {
    public ArrayList<Nhanvien> nhanVienList;
    File EmployeeFile = new File("../File/nhanvien.txt");

    public QLNhanVien() {
        this.nhanVienList = new ArrayList<>();
    }

    public QLNhanVien(ArrayList<Nhanvien> nhanVienList) {
        this.nhanVienList = nhanVienList;
    }

    public void Init() {
        this.nhanVienList.clear();
        try (Scanner rd = new Scanner(EmployeeFile)) { // Sửa lại tên biến từ nhanVienFile thành EmployeeFile
            while (rd.hasNextLine()) {
                String line = rd.nextLine();
                String[] nhanVienSplit = line.split("\\|");

                if (nhanVienSplit.length >= 9) { // Kiểm tra độ dài của dữ liệu
                    String maNhanVien = nhanVienSplit[0];
                    String tenNhanVien = nhanVienSplit[1];
                    int tuoi = Integer.parseInt(nhanVienSplit[2]);
                    String ngaySinh = nhanVienSplit[3]; // Ngày sinh, chưa chuyển thành đối tượng Date
                    String diaChi = nhanVienSplit[4];
                    String loaiNhanVien = nhanVienSplit[5];
                    String caLamViec = nhanVienSplit[6];
                    int soBillDaXuLy = Integer.parseInt(nhanVienSplit[7]);
                    int soLyNuocPhaChe = nhanVienSplit[8].equals("") ? 0 : Integer.parseInt(nhanVienSplit[8]);
                    int soGioLamThem = Integer.parseInt(nhanVienSplit[9]);

                    Nhanvien nhanVien = null;
                    // Phân biệt loại nhân viên dựa trên mã
                    switch (loaiNhanVien) {
                        case "NVTN":
                            LocalDate dateOfBirth = LocalDate.parse(ngaySinh, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            nhanVien = new NhanVienThuNgan();
                            break;
                        case "NVPC":
                            dateOfBirth = LocalDate.parse(ngaySinh, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            nhanVien = new NhanVienPhaChe();
                            break;
                        default:
                            System.out.println("Loại nhân viên không hợp lệ: " + loaiNhanVien);
                            continue;
                    }

                    this.nhanVienList.add(nhanVien);
                }
            }
            System.out.println("\tĐọc thành công dữ liệu nhân viên từ file!");
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    public void writeFile() {
        String line;
        try (FileWriter writer = new FileWriter(EmployeeFile, false)) { 
            for (Nhanvien nv : this.nhanVienList) {
                line = nv.makeString(); // Gọi phương thức makeString() của đối tượng nhân viên
                writer.append(line);
                writer.append(System.lineSeparator()); 
            }
            System.out.println("\tCập nhật dữ liệu thành công !");
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    // Xóa nhân viên bằng ID
public boolean removeByID(String ID) {
    Iterator<Nhanvien> iterator = this.nhanVienList.iterator();
    while (iterator.hasNext()) {
        Nhanvien nv = iterator.next();
        if (nv.getMaNhanVien().equalsIgnoreCase(ID)) {
            iterator.remove(); // Xóa phần tử an toàn
            return true;
        }
    }
    return false;
}
// Xóa nhân viên theo tên
public boolean removeByName(String name) {
    final String CONTAINS_NAME = name.toLowerCase();
    boolean isRemoved = false;

    // Duyệt danh sách và dùng Iterator để xóa an toàn
    Iterator<Nhanvien> iterator = this.nhanVienList.iterator();
    while (iterator.hasNext()) {
        Nhanvien nv = iterator.next();
        if (nv.getTenNhanVien().toLowerCase().contains(CONTAINS_NAME)) {
            iterator.remove();
            isRemoved = true;
        }
    }

    return isRemoved;
}
// thêm nhân viên
public void addNewNhanVien() {
    Scanner sc = new Scanner(System.in);
    String str;
    Nhanvien temp = null;
    
    while (true) {
        System.out.println("\t1. Loại nhân viên [Thu ngân] (NVTN)");
        System.out.println("\t2. Loại nhân viên [Pha chế] (NVPC)");
        System.out.print("\tChọn loại nhân viên: ");
        str = sc.nextLine();
        
        if (Function.isEmpty(str)) { 
            System.out.println("Vui lòng không để trống!");
        } else {
            if (Function.isTrueNumber(str)) {
                int number = Integer.parseInt(str);
                if (number == 1) {
                    temp = new NhanVienThuNgan();
                } else if (number == 2) {
                    temp = new NhanVienPhaChe();
                } else {
                    System.out.println("\tVui lòng nhập trong khoảng 1 đến 2!");
                    continue;
                }
                break;
            } else {
                System.out.println("\tVui lòng nhập số!");
            }
        }
    }

    if (temp != null) {
        temp.nhapThongTin();
        if (temp instanceof NhanVienThuNgan) {
            System.out.print("Số bill đã xử lý: ");
            ((NhanVienThuNgan) temp).setSoBillDaXuLy(Integer.parseInt(sc.nextLine()));

            System.out.print("Tổng tiền đã xử lý: ");
            ((NhanVienThuNgan) temp).setTongTienDaXuLy(Double.parseDouble(sc.nextLine()));

            System.out.print("Số giờ làm thêm: ");
            ((NhanVienThuNgan) temp).setSoGioLamThem(Integer.parseInt(sc.nextLine()));
        } else if (temp instanceof NhanVienPhaChe) {
            System.out.print("Số đơn đã pha chế: ");
            ((NhanVienPhaChe) temp).setSoDonDaPhaChe(Integer.parseInt(sc.nextLine()));

            System.out.print("Số giờ làm thêm: ");
            ((NhanVienPhaChe) temp).setSoGioLamThem(Integer.parseInt(sc.nextLine()));
        }

        // Thêm nhân viên vào danh sách
        this.nhanVienList.add(temp);

        // Tạo chuỗi để lưu vào file
        String line = temp.makeString();
        System.out.println("\tThêm thành công nhân viên!");

        // Ghi thông tin nhân viên vào file
        try (FileWriter writer = new FileWriter(EmployeeFile, true)) { 
            writer.append(line);
            writer.append(System.lineSeparator());
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
// xóa nhân viên
public void removeNhanVien() {
    Scanner sc = new Scanner(System.in);
    String str;
    int number = 0;

    while (true) {
        System.out.println("\tCó các lựa chọn sau để xoá nhân viên: ");
        System.out.println("\t1. Xoá theo ID");
        System.out.println("\t2. Xoá theo Tên (sẽ xoá từ đó có chứa chữ. Lưu ý trước khi chọn)");
        System.out.print("\tNhập lựa chọn: ");
        str = sc.nextLine();
        
        if (Function.isEmpty(str)) {
            System.out.println("\tVui lòng không để trống!");
        } else {
            if (Function.isTrueNumber(str)) {
                number = Integer.parseInt(str);
                if (number >= 1 && number <= 2) {
                    break;
                } else {
                    System.out.println("\tVui lòng nhập trong khoảng 1 đến 2!");
                }
            } else {
                System.out.println("\tVui lòng nhập số!");
            }
        }
    }

    System.out.println("\tĐịnh dạng chuỗi: ID | TÊN | TUỔI | NGÀY SINH | ĐỊA CHỈ | LOẠI NHÂN VIÊN | CA LÀM VIỆC ");
    for (Nhanvien nv : this.nhanVienList) {
        System.out.println("\t" + nv.makeString());
    }

    if (number == 1) {
        while (true) {
            System.out.print("\tNhập ID muốn xoá: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống!");
            } else {
                if (this.removeByID(str)) {
                    System.out.println("\tXoá thành công nhân viên có ID: " + str);
                    this.writeFile();
                    break;
                } else {
                    System.out.println("\tKhông tìm thấy nhân viên có ID: " + str);
                    break;
                }
            }
        }
    } else if (number == 2) {
        while (true) {
            System.out.print("\tNhập tên hoặc chuỗi muốn xoá (sẽ xoá nếu tên nhân viên chứa chuỗi này): ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống!");
            } else {
                if (this.removeByName(str)) {
                    System.out.println("\tXoá thành công các nhân viên có tên chứa: " + str);
                    this.writeFile();
                    break;
                } else {
                    System.out.println("\tKhông tìm thấy nhân viên nào có tên chứa: " + str);
                    break;
                }
            }
        }
    }
}
// xóa toàn bộ danh sách
public void clearList() {
    Scanner sc = new Scanner(System.in);
    String str;
    while (true) {
        System.out.println("\tBạn có chắc chắn muốn xoá toàn bộ danh sách không ?");
        System.out.println("\t1. Có");
        System.out.println("\t2. Không");
        System.out.print("\tNhập lựa chọn: ");
        str = sc.nextLine();
        if (Function.isEmpty(str)) {
            System.out.println("\tVui lòng không để trống !");
        } else {
            if (Function.isTrueNumber(str)) {
                int number = Integer.parseInt(str);
                if (number >= 1 && number <= 2) {
                    if (number == 1) {
                        this.nhanVienList.clear();
                        System.out.println("\tXoá danh sách thành công !");
                        break;
                    }
                    if (number == 2) {
                        System.out.println("\tHủy bỏ xoá danh sách !");
                        break;
                    }
                } else {
                    System.out.println("\tVui lòng chọn trong khoảng 1 đến 2 !");
                }
            } else {
                System.out.println("\tVui lòng nhập số !");
            }
        }
    }
}
// đếm số nhân viên
public void listItem() {
        int nhanVienPhaChe= 0;
        int nhanVienThuNgan = 0;

        for (Nhanvien nv : this.nhanVienList) {
            if (nv instanceof NhanVienPhaChe) {
                nhanVienPhaChe++;
            }
            if (nv instanceof NhanVienThuNgan) {
                nhanVienThuNgan++;
            }
        }
        System.out.println("\tSố nhân viên đang làm việc: ");
        System.out.println("\t1. Nhân viên thu nhân: " + nhanVienThuNgan);
        System.out.println("\t2. Nhân viên pha chế: " + nhanVienPhaChe);
    }

    // tìm nhân viên
    public void findNhanVien() {
        Scanner sc = new Scanner(System.in);
        String str;
        String result = "";
        Nhanvien temp = null;
    
        while (true) {
            System.out.println("\tTìm kiếm nhân viên theo: ");
            System.out.println("\t1. Tìm kiếm theo ID");
            System.out.println("\t2. Tìm kiếm theo Tên");
            System.out.println("\t3. Tìm kiếm theo Loại nhân viên");
            System.out.println("\t4. Tìm kiếm theo Ca làm việc");
            System.out.println("\t5. Thoát");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();
    
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống!");
            } else {
                if (Function.isTrueNumber(str)) {
                    int number = Integer.parseInt(str);
                    if (number >= 1 && number <= 5) {
                        // Tìm kiếm theo ID
                        if (number == 1) {
                            System.out.print("\tNhập ID muốn tìm: ");
                            str = sc.nextLine();
                            for (Nhanvien nv : this.nhanVienList) {
                                if (nv.getMaNhanVien().equalsIgnoreCase(str)) {
                                    temp = nv;
                                    result = temp.makeString();
                                    break;
                                }
                            }
                            if (Function.isEmpty(result)) {
                                System.out.println("\tKhông tìm thấy nhân viên nào có ID: " + str);
                            } else {
                                System.out.println("\tKết quả tìm kiếm: ");
                                temp.xuatThongTin();
                            }
                        }
                        // Tìm kiếm theo Tên
                        if (number == 2) {
                            System.out.print("\tNhập tên muốn tìm: ");
                            str = sc.nextLine();
                            for (Nhanvien nv : this.nhanVienList) {
                                if (nv.getTenNhanVien().equalsIgnoreCase(str)) {
                                    temp = nv;
                                    result = nv.makeString();
                                    break;
                                }
                            }
                            if (Function.isEmpty(result)) {
                                System.out.println("\tKhông tìm thấy nhân viên nào có tên: " + str);
                            } else {
                                System.out.println("\tKết quả tìm kiếm: ");
                                temp.xuatThongTin();
                            }
                        }
                        // Tìm kiếm theo Loại nhân viên
                        if (number == 3) {
                            System.out.print("\tNhập loại nhân viên (NVPC, NVTN): ");
                            str = sc.nextLine();
                            for (Nhanvien nv : this.nhanVienList) {
                                if (nv.getLoaiNhanVien().equalsIgnoreCase(str)) {
                                    System.out.println("\tTìm kiếm thêm theo thuộc tính của loại nhân viên " + str + ": ");
                                    System.out.println("\t1. Tìm kiếm theo Ca làm việc");
                                    System.out.println("\t2. Tìm kiếm theo Số đơn pha chế (NVPC)");
                                    System.out.println("\t3. Tìm kiếm theo Số tiền thu ngân (NVTN)");
                                    System.out.print("\tNhập lựa chọn: ");
                                    String choice = sc.nextLine();
                                    if (Function.isTrueNumber(choice)) {
                                        int choiceNum = Integer.parseInt(choice);
                                        if (choiceNum == 1) {
                                            // Tìm kiếm theo ca làm việc
                                            System.out.print("\tNhập ca làm việc (MORNING, EVENING, AFTERNOON): ");
                                            String caLamViec = sc.nextLine();
                                            if (nv.getCaLamViec().equalsIgnoreCase(caLamViec)) {
                                                nv.xuatThongTin();
                                            }
                                        } else if (choiceNum == 2 && nv instanceof NhanVienPhaChe) {
                                            // Tìm kiếm theo số đơn pha chế
                                            System.out.print("\tNhập số đơn pha chế muốn tìm: ");
                                            String soDon = sc.nextLine();
                                            NhanVienPhaChe phaChe = (NhanVienPhaChe) nv;
                                            if (String.valueOf(phaChe.getSoDonDaPhaChe()).equals(soDon)) {
                                                phaChe.xuatThongTin();
                                            }
                                        } else if (choiceNum == 3 && nv instanceof NhanVienThuNgan) {
                                            // Tìm kiếm theo số tiền thu ngân
                                            System.out.print("\tNhập số tiền thu ngân muốn tìm: ");
                                            String soTien = sc.nextLine();
                                            NhanVienThuNgan thuNgan = (NhanVienThuNgan) nv;
                                            if (String.valueOf(thuNgan.getTongTienDaXuLy()).equals(soTien)) {
                                                thuNgan.xuatThongTin();
                                            }
                                        } else {
                                            System.out.println("\tVui lòng chọn trong khoảng 1 đến 3!");
                                        }
                                    }
                                }
                            }
                        }
                        // Tìm kiếm theo Ca làm việc
                        if (number == 4) {
                            System.out.print("\tNhập ca làm việc (MORNING, EVENING, AFTERNOON): ");
                            str = sc.nextLine();
                            for (Nhanvien nv : this.nhanVienList) {
                                if (nv.getCaLamViec().equalsIgnoreCase(str)) {
                                    nv.xuatThongTin();
                                    result = nv.makeString();
                                }
                            }
                            if (Function.isEmpty(result)) {
                                System.out.println("\tKhông tìm thấy nhân viên nào có ca làm việc: " + str);
                            }
                        }
                        if (number == 5) {
                            System.out.println("\tThoát tìm kiếm!");
                            Function.clearScreen();
                            break;
                        }
                    } else {
                        System.out.println("\tVui lòng chọn trong khoảng 1 đến 5!");
                    }
                } else {
                    System.out.println("\tVui lòng nhập số!");
                }
            }
        }
    }
    // sửa thông tin nhân viên
    public void modifyInfo() {
        Scanner sc = new Scanner(System.in);
        String str;
        boolean action = false;
        this.xuat();
        while (true) {
            System.out.print("\tNhập mã ID nhân viên bạn muốn sửa: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                // Duyệt qua danh sách nhân viên để tìm ID
                for (Nhanvien nv : this.nhanVienList) {
                    if (nv.getMaNhanVien().equalsIgnoreCase(str)) {
                        action = true;
                        nv.modifyInfo(); 
                        break;
                    }
                }
                if (!action) {
                    System.out.println("\tKhông tìm thấy nhân viên nào có ID: " + str);
                }
                break;
            }
        }
        // Ghi lại vào file
        this.writeFile();
    }
    public void xuat() {
        for (Nhanvien nv : this.nhanVienList) {
            nv.xuatThongTin();
        }
    }
    public void menuQLNhanVien() {
        Function.clearScreen();
        this.Init(); // Khởi tạo dữ liệu
        Scanner sc = new Scanner(System.in);
        String str;
    
        while (true) {
            // In tiêu đề
            System.out.println(
                    "\t===============================[ Menu Quản Lý Nhân Viên ]===============================");
    
            // In tiêu đề các cột
            System.out.printf("\t| %-4s | %-77s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------------------|");
    
            // In danh sách các lựa chọn
            System.out.printf("\t| %-4s | %-77s |%n", "1", "In danh sách nhân viên");
            System.out.printf("\t| %-4s | %-77s |%n", "2", "Thêm một nhân viên mới");
            System.out.printf("\t| %-4s | %-77s |%n", "3", "Xoá một nhân viên");
            System.out.printf("\t| %-4s | %-77s |%n", "4", "Sửa thông tin nhân viên");
            System.out.printf("\t| %-4s | %-77s |%n", "5", "Cập nhật lại danh sách nhân viên vào File");
            System.out.printf("\t| %-4s | %-77s |%n", "6", "Cập nhật lại danh sách từ File");
            System.out.printf("\t| %-4s | %-77s |%n", "7", "Làm mới danh sách nhân viên (Reset dữ liệu)");
            System.out.printf("\t| %-4s | %-77s |%n", "8", "Tìm kiếm nhân viên");
            System.out.printf("\t| %-4s | %-77s |%n", "9", "In ra số lượng nhân viên từng loại");
            System.out.printf("\t| %-4s | %-77s |%n", "10", "Làm mới màn hình");
            System.out.printf("\t| %-4s | %-77s |%n", "11", "Thoát chương trình quản lý");
    
            // In dòng kẻ dưới cùng
            System.out.println(
                    "\t========================================================================================");
            System.out.print("\t[Manage] Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    int number = Integer.parseInt(str);
                    if (number >= 1 && number <= 11) {
                        if (number == 1) {
                            this.xuat();
                        }
                        if (number == 2) {
                            this.addNewNhanVien();
                        }
                        if (number == 3) {
                            this.removeNhanVien();
                        }
                        if (number == 4) {
                            this.modifyInfo();
                        }
                        if (number == 5) {
                            this.Init();
                        }
                        if (number == 6) {
                            this.writeFile();
                        }
                        if (number == 7) {
                            this.clearList();
                        }
                        if (number == 8) {
                            this.findNhanVien();
                        }
                        if (number == 9) {
                            this.listItem();
                        }
                        if (number == 10) {
                            Function.clearScreen();
                        }
                        if (number == 11) {
                            System.out.println("\tThoát chương trình thành công !");
                            Function.clearScreen();
                            break;
                        }
                    } else {
                        System.out.println("\tVui lòng chọn trong khoảng 1 đến 11 !");
                    }
                } else {
                    System.out.println("\tVui lòng nhập số !");
                }
            }
        }
    }
     public static void main(String[] args) {
        QLNhanVien list = new QLNhanVien();
        list.Init();
        list.menuQLNhanVien();
    }


}
