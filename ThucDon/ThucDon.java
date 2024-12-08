package ThucDon;
import NuocUong.NuocUong;
import Topping.Topping;
import Utils.*;
import java.util.ArrayList;
public class ThucDon implements IXuat {

    private ArrayList<NuocUong> danhSachNuocUong;
    private ArrayList<ArrayList<Topping>> danhSachTopping;

    public ThucDon() {
        this.danhSachNuocUong = new ArrayList<>();
        this.danhSachTopping = new ArrayList<>();
    }

    public ArrayList<NuocUong> getDanhSachNuocUong() {
        return danhSachNuocUong;
    }

    public ArrayList<ArrayList<Topping>> getDanhSachTopping() {
        return danhSachTopping;
    }

    @Override
    public void xuatThongTin() {
        // Danh sách nước uống và topping có size bằng nhau
        System.out.println("\t+====================[Danh sách Thực Đơn]====================+");
        for (int i = 0; i < this.danhSachNuocUong.size(); i++) {
            System.out.println("\t|------------------------------------------------------------|");
            System.out.println("\t| Món thức uống thứ " + (i + 1) + ":");
            System.out.println("\t| Nước uống: " + this.danhSachNuocUong.get(i).getName());
            // In các trạng thái của nước uống
            System.out.println("\t| Uống nóng: "+ this.danhSachNuocUong.get(i).isHot());
            System.out.println("\t| Uống lạnh: "+ this.danhSachNuocUong.get(i).isCold());
            System.out.println("\t|Có đường: "+ this.danhSachNuocUong.get(i).isSugar());
            System.out.println("\t|Có sữa: "+ this.danhSachNuocUong.get(i).isMilk());
            if (this.danhSachTopping.get(i).size() > 0) {
                System.out.println("\t| Topping: ");
                for (int j = 0; j < this.danhSachTopping.get(i).size(); j++) {
                    System.out.println("\t|   - " + this.danhSachTopping.get(i).get(j).getName());
                }
            }
            else {
                System.out.println("\t| Không có topping");
            }
            System.out.println("\t|------------------------------------------------------------|");
        }
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
}
