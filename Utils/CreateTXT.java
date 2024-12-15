/**
 *
 */
package Utils;

import java.io.File;
import java.io.FileWriter;
import HoaDon.HoaDon;
import HoaDon.QLHoaDon;
import ThucDon.ThucDon;

public class CreateTXT {

    public int countTXTFile() {
        int result = 0;
        File filePath = new File("../HoaDonTXT");
        File[] listFile = filePath.listFiles();
        for (File file : listFile) {
            if (file.isFile()) {
                result++;
            }
        }
        return result;
    }

    public static void createTextFile(HoaDon hoaDon) {
        String fileName = "../HoaDonTXT/HoaDon" + (new CreateTXT().countTXTFile() + 1) + ".txt";

        ThucDon thucDon = hoaDon.getThucDon();

        try (FileWriter writer = new FileWriter(fileName)) {
            // Header của hoá đơn
            writer.write("\t+-----------------+--------------------------------------+-----------------+\n");
            writer.write("\t|                             HOÁ ĐƠN BÁN HÀNG                             |\n");
            writer.write("\t+-----------------+--------------------------------------+-----------------+\n");

            // Thông tin chính
            writer.write(String.format("\t| %-30s: %-40s |\n", "Nhân viên tạo", hoaDon.getNhanVien().getTenNhanVien()));
            writer.write(String.format("\t| %-30s: %-40s |\n", "Tên khách hàng", hoaDon.getKhachHang().getCustomerName()));
            writer.write(String.format("\t| %-30s: %-40s |\n", "Mã hoá đơn", hoaDon.getMaHoaDon()));
            writer.write(String.format("\t| %-30s: %-40s |\n", "Ngày lập", hoaDon.getNgayTaoHoaDon()));
            writer.write(String.format("\t| %-30s: %-40s |\n", "Trạng thái hoá đơn: ", hoaDon.getTrangThaiHoaDon()));
            writer.write("\t+-----------------+--------------------------------------+-----------------+\n");
            writer.write("\t|                             THỰC ĐƠN BÁN HÀNG                            |\n");
            writer.write("\t+-----------------+--------------------------------------+-----------------+\n");
            writer.write(thucDon.makeStringFile());
            // Footer
            writer.write("\t+-----------------+--------------------------------------+-----------------+\n");
            writer.write(String.format("\t| %-30s: %-40s |\n", "Tổng tiền hoá đơn: ", Function.formatMoney(hoaDon.getTongTien() + "")));
            writer.write(String.format("\t| %-30s: %-40s |\n", "Trạng tiền khách đưa: ", Function.formatMoney(hoaDon.getTienKhachDua() +"")));
            writer.write(String.format("\t| %-30s: %-40s |\n", "Tiền thối lại: ", Function.formatMoney(hoaDon.getTienThua() +"")));

            writer.write("\t+-----------------+--------------------------------------+-----------------+\n");
            System.out.println("File hoá đơn đã được tạo: " + fileName);
        } catch (Exception e) {
            System.out.println("\tLỗi: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        QLHoaDon qlHoaDon = new QLHoaDon();
        qlHoaDon.Init();


        HoaDon hoaDon = qlHoaDon.getBillList().get(0);
        CreateTXT.createTextFile(hoaDon);
    }
}
