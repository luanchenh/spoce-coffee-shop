/**
 *
 */
package NuocUong;

import java.util.Scanner;
import Utils.Function;
// Tắt cảnh báo cảnh báo cho các thuộc tính mình không dùng
@SuppressWarnings("resource")
public class DrinkingWater  {
    private String idWater;
    private String nameWater;

    private static int id = 0;

    public DrinkingWater() {
        this.idWater = "NU" + id++;
        this.nameWater = "";
    }
    public DrinkingWater(String nameWater) {
        this.idWater = "NU" + id++;
        this.nameWater = nameWater;
    }

    // Getter - Setter
    public String getIdWater() {
        return idWater;
    }
    public String getNameWater() {
        return nameWater;
    }

    public void setIdWater(String idWater) {
        this.idWater = idWater;
    }
    public void setNameWater(String nameWater) {
        this.nameWater = nameWater;
    }

    // Setinfo
    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        String str;
        System.out.println("-------Nhập thông tin cơ bản cho class DrinkingWater-------");
        // Mã nước uống tự phát sinh
        System.out.println("---Nước uống bạn đang cài đặt có thông tin mã nước uống là: NU"+ id);
        this.idWater = "NU" + id;
        while (true) {
            System.out.println("---Tên nước uống; ");
            str = sc.nextLine().trim();
            if (Function.isEmpty(str)) {
                System.out.println("Bạn chưa nhập thông tin");
            }
            else {
                this.nameWater = Function.normalizeName(str);
                break;
            }
        }
        System.out.println("-------Nhập thông tin idWater và nameWater thành công !-------");

    }

    @Override
    public String toString() {
        return "-------------------------------------------------------------\n"
              +"Mã nước: "+ this.idWater +"\n"
              +"Tên nước uống: "+ this.nameWater +"\n";
    }

}
