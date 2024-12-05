package NuocUong;

import Utils.IXuat;
import java.util.Map;

public class Coffee extends NuocUong implements IXuat {

    public Coffee(String drinkType) {
        super(drinkType);
    }


    @Override
    public void xuatThongTin() {
        System.out.println("=======================================");
        System.out.println("Thông tin sản phẩm nước uống:");
        System.out.println("ID: " + this.id);
        System.out.println("Tên đồ uống: " + this.name);
        System.out.println("Loại đồ uống: " + this.drinkType);
        System.out.println("Có thể uống lạnh: " + (this.isCold ? "Có" : "Không"));
        System.out.println("Có thể uống nóng: " + (this.isHot ? "Có" : "Không"));
        System.out.println("Có sữa: " + (this.isMilk ? "Có" : "Không"));
        System.out.println("Có đường: " + (this.isSugar ? "Có" : "Không"));

        System.out.println("\nBảng giá theo size:");
        for (Map.Entry<String, Integer> entry : sizePrice.entrySet()) {
            System.out.println("- Size " + entry.getKey() + ": " + entry.getValue() + " VND");
        }

        System.out.println("\nDanh sách topping:");
        if (topping.isEmpty()) {
            System.out.println("Không có topping nào.");
        } else {
            for (String tp : topping) {
                System.out.println("- " + tp);
            }
        }
        System.out.println("=======================================");
    }
}
