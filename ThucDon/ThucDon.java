package ThucDon;
import NuocUong.NuocUong;
import Topping.Topping;
import Utils.*;
import java.util.ArrayList;
public class ThucDon implements IXuat {

    private ArrayList<NuocUong> danhSachNuocUong;
    private ArrayList<String> size;
    private ArrayList<ArrayList<String>> trangThaiNuocUong;
    private ArrayList<ArrayList<Topping>> danhSachTopping;

    public ThucDon() {
        this.danhSachNuocUong = new ArrayList<>();
        this.size = new ArrayList<>();
        this.trangThaiNuocUong = new ArrayList<>();
        this.danhSachTopping = new ArrayList<>();
    }

    public ArrayList<NuocUong> getDanhSachNuocUong() {
        return danhSachNuocUong;
    }

    public ArrayList<ArrayList<Topping>> getDanhSachTopping() {
        return danhSachTopping;
    }

    public ArrayList<String> getSize() {
        return this.size;
    }

    public ArrayList<ArrayList<String>> getTrangThaiNuocUong() {
        return this.trangThaiNuocUong;
    }

    @Override
    public void xuatThongTin() {
        // Danh sách nước uống và topping có size bằng nhau
        System.out.println("\t+====================[Danh sách Thực Đơn]====================+");
        for (int i = 0; i < this.danhSachNuocUong.size(); i++) {
            System.out.println("\t|------------------------------------------------------------|");
            System.out.println("\t| Món thức uống thứ " + (i + 1) + ":                                       |");
            // System.out.println("\t| Size: " + this.size.get(i));
            // System.out.println("\t| Nước uống: " + this.danhSachNuocUong.get(i).getName());
            // // In các trạng thái của nước uống
            // System.out.println("\t| Uống lạnh: "+ this.trangThaiNuocUong.get(i).get(0));
            // System.out.println("\t| Uống nóng: "+ this.trangThaiNuocUong.get(i).get(1));
            // System.out.println("\t| Có đường: "+ this.trangThaiNuocUong.get(i).get(2));
            // System.out.println("\t| Có sữa: "+ this.trangThaiNuocUong.get(i).get(3));
            System.out.printf("\t| %-20s %-37s |%n", "Nước uống:", this.danhSachNuocUong.get(i).getName());
            System.out.printf("\t| %-20s %-37s |%n", "Size:", this.size.get(i));
            System.out.printf("\t| %-20s %-37s |%n", "Uống đá:", this.trangThaiNuocUong.get(i).get(0));
            System.out.printf("\t| %-20s %-37s |%n", "Uống nóng:", this.trangThaiNuocUong.get(i).get(1));
            System.out.printf("\t| %-20s %-37s |%n", "Có đường:", this.trangThaiNuocUong.get(i).get(2));
            System.out.printf("\t| %-20s %-37s |%n", "Có sữa:", this.trangThaiNuocUong.get(i).get(3));
            if (!this.danhSachTopping.get(i).isEmpty()) {
                System.out.println("\t| Topping:                                                   |");
                for (int j = 0; j < this.danhSachTopping.get(i).size(); j++) {
                    // System.out.println("\t|   - " + this.danhSachTopping.get(i).get(j).getName());
                    System.out.printf("\t|   - %-54s |%n", this.danhSachTopping.get(i).get(j).getName());
                }
            }
            else {
                // System.out.println("\t| Không có topping");
                System.out.printf("\t| %-20s %-37s |%n", "Không có topping", "");
            }
            System.out.println("\t|------------------------------------------------------------|");
        }
    }

    public String makeStringFile() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.danhSachNuocUong.size(); i++) {
            str.append("\t+--------------------------------------------------------------------------+\n");
            str.append(String.format("\t| Món thức uống thứ %-54d |\n", (i + 1)));
            str.append("\t+--------------------------------------------------------------------------+\n");
            str.append(String.format("\t| Nước uống     : %-56s |\n", this.danhSachNuocUong.get(i).getName()));
            str.append(String.format("\t| Size          : %-56s |\n", this.size.get(i)));
            str.append(String.format("\t| Uống đá       : %-56s |\n", this.trangThaiNuocUong.get(i).get(0)));
            str.append(String.format("\t| Uống nóng     : %-56s |\n", this.trangThaiNuocUong.get(i).get(1)));
            str.append(String.format("\t| Có đường      : %-56s |\n", this.trangThaiNuocUong.get(i).get(2)));
            str.append(String.format("\t| Có sữa        : %-56s |\n", this.trangThaiNuocUong.get(i).get(3)));

            // Xử lý topping
            if (!this.danhSachTopping.get(i).isEmpty()) {
                str.append("\t| Topping       :                                                          |\n");
                for (int j = 0; j < this.danhSachTopping.get(i).size(); j++) {
                    str.append(String.format("\t|   - %-68s |\n", this.danhSachTopping.get(i).get(j).getName()));
                }
            } else {
                str.append(String.format("\t| %-72s |\n", "Không có topping"));
            }

            // Kết thúc món uống
            str.append("\t+--------------------------------------------------------------------------+\n");
        }

        return str.toString();
    }


    public String makeString() {
        StringBuilder str = new StringBuilder();
        // ID, Name, isHot, isCold, isSugar, isMilk, topping1; topping2; ... | ID, Name, isHot, isCold, isSugar, isMilk, topping1, topping2, ...
        for (int i = 0; i < this.danhSachNuocUong.size(); i++) {
            str.append(this.danhSachNuocUong.get(i).getId()).append(",");
            str.append(this.danhSachNuocUong.get(i).getName()).append(",");
            str.append(this.danhSachNuocUong.get(i).isHot()).append(",");
            str.append(this.danhSachNuocUong.get(i).isCold()).append(",");
            str.append(this.danhSachNuocUong.get(i).isSugar()).append(",");
            str.append(this.danhSachNuocUong.get(i).isMilk()).append(",");
            for (int j = 0; j < this.danhSachTopping.get(i).size(); j++) {
                str.append(this.danhSachTopping.get(i).get(j).getName());
                if (j != this.danhSachTopping.get(i).size() - 1) {
                    str.append(";");
                }
            }
            if (i != this.danhSachNuocUong.size() - 1) {
                str.append("|");
            }
        }
        return str.toString();

    }

    public static String makeStringWithStatus(NuocUong nu, String size, ArrayList<Topping> tpList, boolean wantHot, boolean wantCold, boolean wantSugar, boolean wantMilk) {
        StringBuilder str = new StringBuilder();
        str.append(nu.getId()).append(",");
        str.append(size).append(",");

        for (Topping tp : tpList) {
            if (tpList.indexOf(tp) == tpList.size() - 1) {
                str.append(tp.getId()).append(",");
            } else {
                str.append(tp.getId()).append(";");
            }
        }

        str.append(wantHot).append("|");
        str.append(wantCold).append("|");
        str.append(wantSugar).append("|");
        str.append(wantMilk).append("|");

        return str.toString();
    }

    public double tinhTongTien() {
        double tongTien = 0;
        for (int i=0; i<this.danhSachNuocUong.size(); i++) {
            tongTien += this.danhSachNuocUong.get(i).getSizePrice().get(this.size.get(i));

            for (Topping tp : this.danhSachTopping.get(i)) {
                tongTien += tp.getPrice();
            }
        }

        return tongTien;
    }
}
