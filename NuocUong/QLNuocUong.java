/**
 *
 */
package NuocUong;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class QLNuocUong {
    ArrayList<NuocUong> waterList;

    File waterFile = new File("../File/water.txt");

    public QLNuocUong() {
        this.waterList = new ArrayList<>();
    }

    public QLNuocUong(ArrayList<NuocUong> waterList) {
        this.waterList = waterList;
    }

    // Getter - Setter
    public ArrayList<NuocUong> getWaterList() {
        return waterList;
    }

    public void Init() {
        try (Scanner rd = new Scanner(waterFile)) {
            while (rd.hasNextLine()) {
                String line = rd.nextLine();
                String[] waterSplit = line.split("\\|");
                if (waterSplit.length == 8) {
                    String id = waterSplit[0];
                    String name = waterSplit[1];
                    NuocUong water = null;
                    String drinkType = id.substring(0, 2); // Lấy 2 ký tự đầu tiên để so sánh

                    switch (drinkType) {
                        case "CF":
                            water = new Coffee("CF");
                            break;
                        case "TC":
                            water = new NuocTraiCay("TC");
                            break;
                        case "TS":
                            water = new TraSua("TS");
                            break;
                        case "ST":
                            water = new SinhTo("ST");
                            break;
                        default:
                            System.out.println("Loại nước uống không hợp lệ: " + drinkType);
                            continue;
                    }

                    // Cài đặt các thuộc tính khác của đối tượng
                    water.setId(id);
                    water.setName(name);
                    water.setDrinkType(drinkType);
                    water.setCold(Boolean.parseBoolean(waterSplit[2]));
                    water.setHot(Boolean.parseBoolean(waterSplit[3]));
                    water.setMilk(Boolean.parseBoolean(waterSplit[4]));
                    water.setSugar(Boolean.parseBoolean(waterSplit[5]));

                    Map<String, Integer> sizePriceMap = new HashMap<>();
                    String[] sizePrice = waterSplit[6].split(",");
                    if (sizePrice.length == 3) {
                        sizePriceMap.put("S", Integer.parseInt(sizePrice[0]));
                        sizePriceMap.put("M", Integer.parseInt(sizePrice[1]));
                        sizePriceMap.put("L", Integer.parseInt(sizePrice[2]));
                    } else if (sizePrice.length == 4) {
                        sizePriceMap.put("S", Integer.parseInt(sizePrice[0]));
                        sizePriceMap.put("M", Integer.parseInt(sizePrice[1]));
                        sizePriceMap.put("L", Integer.parseInt(sizePrice[2]));
                        sizePriceMap.put("XL", Integer.parseInt(sizePrice[3]));
                    }
                    water.setSizePrice(sizePriceMap);

                    ArrayList<String> toppingList = new ArrayList<>();
                    for (String str : waterSplit[7].split(",")) {
                        toppingList.add(str);
                    }
                    water.setTopping(toppingList);

                    this.waterList.add(water);
                }
            }
            System.out.println("Đọc thành công !");
        } catch (Exception e) {
            System.out.println("Lỗi " + e.getMessage());
        }
    }



    public static void main(String[] args) {
        QLNuocUong list = new QLNuocUong();
        list.Init();
        for (NuocUong nu : list.getWaterList()) {
            nu.xuatThongTin();
        }
    }




}
