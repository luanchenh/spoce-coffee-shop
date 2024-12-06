/**
 *
 */
package NuocUong;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Utils.Function;
@SuppressWarnings("resource")
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
                if (waterSplit.length >= 7) {
                    String id = waterSplit[0];
                    String name = waterSplit[1];
                    NuocUong water = null;
                    String drinkType = id.substring(0, 2); // Lấy 2 ký tự đầu tiên để so sánh

                    switch (drinkType) {
                        case "CF":
                            water = new Coffee();
                            break;
                        case "TC":
                            water = new NuocTraiCay();
                            break;
                        case "TS":
                            water = new TraSua();
                            break;
                        case "ST":
                            water = new SinhTo();
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
                    if (waterSplit.length > 7) {
                        for (String str : waterSplit[7].split(",")) {
                            toppingList.add(str);
                        }
                    }
                    water.setTopping(toppingList);

                    this.waterList.add(water);
                }
            }
            System.out.println("\tĐọc thành công !");
        } catch (Exception e) {
            System.out.println("Lỗi " + e.getMessage());
        }
    }

    public void addNewWater() {
        Scanner sc = new Scanner(System.in);
        String str;
        NuocUong temp = null;
        while (true) {
            System.out.println("\t1. Loại nước [Trà sữa]");
            System.out.println("\t2. Loại nước [Coffee]");
            System.out.println("\t3. Loại nước [Sinh tố]");
            System.out.println("\t4. Loại nước [Nước trái cây]");
            System.out.print("\tChọn loại nước: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Vui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    int number = Integer.parseInt(str);
                    if (number >= 1 && number <= 4) {
                        if (number == 1) {
                            temp = new TraSua();
                        } else if (number == 2) {
                            temp = new Coffee();
                        } else if (number == 3) {
                            temp = new SinhTo();
                        } else if (number == 4) {
                            temp = new NuocTraiCay();
                        }
                        break;
                    } else {
                        System.out.println("\tVui lòng nhập trong khoảng 1 đến 4 !");
                    }
                } else {
                    System.out.println("\tVui lòng nhập số !");
                }
            }
        }
        if (temp != null) {
            temp.nhapThongTin();
            this.waterList.add(temp);
            String line = temp.makeString();
            System.out.println("\tThêm thành công sản phẩm!");
            try (FileWriter writer = new FileWriter(waterFile, true)) { // Mở file ở chế độ append
                writer.append(line);
                writer.append(System.lineSeparator()); // Thêm dòng mới sau mỗi sản phẩm
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }

    }

    public void removeWater() {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            System.out.println("\tCó các lựa chọn sau để xoá sản phẩm: ");
            System.out.println("\t1. Xoá theo ID");
            System.out.println("\t2. Xoá theo Tên (Kiểm tra từ đó có chứa. Lưu ý trước khi chọn)");
            System.out.print("\t Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            }
        }
        // In ra danh sách để cho người dùng có thể xoá
        System.out.println("\tĐịnh dạng chuỗi: ID | TÊN | ISCOLD | ISHOT | ISMILK | ISSUGAR | SIZEPRICE | TOPPING ");
        for (NuocUong nu : this.waterList) {
            System.out.println("\t"+ nu.makeString());
        }

    }
    public void inDanhSach() {
        for (NuocUong nu : this.waterList) {
            nu.xuatThongTin();
        }
    }

    public void menuQLNuocUong() {
        Function.clearScreen();
        this.Init();
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            // In tiêu đề
            System.out.println(
                    "\t===============================[ Menu Quản Lý Nước Uống ]===============================");

            // In tiêu đề các cột
            System.out.printf("\t| %-4s | %-77s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------------------|");

            // In danh sách các lựa chọn
            System.out.printf("\t| %-4s | %-77s |%n", "1", "In danh sách nước uống");
            System.out.printf("\t| %-4s | %-77s |%n", "2", "Thêm một sản phẩm nước uống (Tự động lưu vào File)");
            System.out.printf("\t| %-4s | %-77s |%n", "3", "Xoá một sản phẩm nước uống (Tự động load vào File)");
            System.out.printf("\t| %-4s | %-77s |%n", "4", "Sửa thông tin nước uống");
            System.out.printf("\t| %-4s | %-77s |%n", "5", "Cập nhật lại sản phẩm vào File từ danh sách");
            System.out.printf("\t| %-4s | %-77s |%n", "6", "Cập nhật lại sản phẩm vào danh sách từ File");
            System.out.printf("\t| %-4s | %-77s |%n", "7", "Làm mới danh sách nước uống (Reset dữ liệu nhưng không load vào File)");
            System.out.printf("\t| %-4s | %-77s |%n", "8", "In ra số sản phẩm từng loại (Kiểm tra số loại sản phẩm có trong danh sách)");
            System.out.printf("\t| %-4s | %-77s |%n", "9", "Tìm kiếm sản phẩm");
            System.out.printf("\t| %-4s | %-77s |%n", "10", "Làm mới màn hình");
            System.out.printf("\t| %-4s | %-77s |%n", "11", "Thoát chương trình quản lý");

            // In dòng kẻ dưới cùng
            System.out.println(
                    "\t========================================================================================");
            System.out.print("\t[Manage] Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            }
            else {
                if (Function.isTrueNumber(str)) {
                    int number = Integer.parseInt(str);
                    if (number >= 1 && number <= 11) {
                        if (number == 1) {
                            this.inDanhSach();
                        }
                        if (number == 2) {
                            this.addNewWater();
                        }
                        if (number == 3) {
                            this.removeWater();
                        }
                        if (number == 10) {
                            Function.clearScreen();
                        }
                        if (number == 11) {
                            System.out.println("\tThoát chương trình thành công !");
                            Function.clearScreen();
                            break;
                        }
                    }
                    else {
                        System.out.println("\tVui lòng chọn trong khoảng 1 đến 11 !");
                    }
                }
                else {
                    System.out.println("\tVui lòng nhập số !");
                }
            }
        }

    }

    public static void main(String[] args) {
        QLNuocUong list = new QLNuocUong();
        list.Init();
        list.menuQLNuocUong();
    }

}
