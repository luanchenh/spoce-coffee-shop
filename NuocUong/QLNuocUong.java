/**
 *
 */
package NuocUong;

import Utils.Function;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

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
        this.waterList.clear();
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
            // System.out.println("\tĐọc thành công !");
        } catch (Exception e) {
            System.out.println("Lỗi " + e.getMessage());
        }
    }

    public void writeFile() {
        String line;
        try (FileWriter writer = new FileWriter(waterFile, false)) { // Mở file ở chế độ ghi đè
            for (NuocUong nu : this.waterList) {
                line = nu.makeString();
                writer.append(line);
                writer.append(System.lineSeparator()); // Thêm dấu xuống hàng
            }
            System.out.println("\tCập nhật dữ liệu thành công !");
            writer.close();
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Thêm xoá sản phẩm
    // Xoá sản phẩm bằng ID
    public boolean removeByID(String ID) {
        Iterator<NuocUong> iterator = this.waterList.iterator();
        while (iterator.hasNext()) {
            NuocUong nu = iterator.next();
            if (nu.getId().equalsIgnoreCase(ID)) {
                iterator.remove(); // Xóa phần tử an toàn
                return true;
            }
        }
        return false;
    }

    public boolean removeByName(String name) {
        final String CONTAINS_NAME = name.toLowerCase();
        boolean isRemoved = false;

        // Duyệt danh sách và dùng Iterator để xóa an toàn
        Iterator<NuocUong> iterator = this.waterList.iterator();
        while (iterator.hasNext()) {
            NuocUong nu = iterator.next();
            if (nu.getName().toLowerCase().contains(CONTAINS_NAME)) {
                iterator.remove();
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    public void addNewWater() {
        Scanner sc = new Scanner(System.in);
        String str;
        NuocUong temp = null;
        while (true) {
            // System.out.println("\t1. Loại nước [Trà sữa]");
            // System.out.println("\t2. Loại nước [Coffee]");
            // System.out.println("\t3. Loại nước [Sinh tố]");
            // System.out.println("\t4. Loại nước [Nước trái cây]");
            // System.out.print("\tChọn loại nước: ");
            System.out.println("\t===================================================================================");
            System.out.printf("\t| %-60s %-18s |%n", "Mời bạn chọn loại nước", "");
            System.out.println("\t===================================================================================");
            System.out.printf("\t| %-10s %-68s |%n", "1.", "Trà sữa");
            System.out.printf("\t| %-10s %-68s |%n", "2.", "Coffee");
            System.out.printf("\t| %-10s %-68s |%n", "3.", "Sinh tố");
            System.out.printf("\t| %-10s %-68s |%n", "4.", "Nước trái cây");
            System.out.println("\t===================================================================================");
            System.out.print("\t=> Mời nhập lựa chọn: ");
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
        int number = 0;
        while (true) {
            System.out.println("\tCó các lựa chọn sau để xoá sản phẩm: ");
            System.out.println("\t1. Xoá theo ID");
            System.out.println("\t2. Xoá theo Tên (sẽ xoá từ đó có chứa chữ. Lưu ý trước khi chọn)");
            System.out.print("\t Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    number = Integer.parseInt(str);
                    if (number >= 1 && number <= 2) {
                        if (number == 1) {
                            number = 1;
                        }
                        if (number == 2) {
                            number = 2;
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
        // In ra danh sách để cho người dùng có thể xoá
        System.out.println("\tĐịnh dạng chuỗi: ID | TÊN | ISCOLD | ISHOT | ISMILK | ISSUGAR | SIZEPRICE | TOPPING ");
        for (NuocUong nu : this.waterList) {
            System.out.println("\t" + nu.makeString());
        }
        if (number == 1) {
            while (true) {
                System.out.print("\t Nhập ID muốn xoá (bao gồm mã loại sản phẩm): ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống !");
                } else {
                    if (this.removeByID(str)) {
                        System.out.println("\tXoá thành công sản phẩm có ID: " + str);
                        this.writeFile();
                        break;
                    } else {
                        System.out.println("\tHệ thống không tồn tại ID: " + str);
                        break;
                    }
                }
            }
        } else if (number == 2) {
            while (true) {
                System.out.print("\t Nhập tên hoặc chuỗi muốn xoá (sẽ xoá nếu sản phẩm chứa chuỗi này): ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống !");
                } else {
                    if (this.removeByName(str)) {
                        System.out.println("\tXoá thành công các sản phẩm có tên chứa: " + str);
                        this.writeFile();
                        break;
                    } else {
                        System.out.println("\tKhông tìm thấy sản phẩm nào có tên chứa: " + str);
                        break;
                    }
                }
            }
        }

    }

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
                            this.waterList.clear();
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

    public void listItem() {
        int totalCoffee = 0;
        int totalTraSua = 0;
        int totalSinhTo = 0;
        int totalNuocTraiCay = 0;
        for (NuocUong nu : this.waterList) {
            if (nu instanceof Coffee) {
                totalCoffee++;
            }
            if (nu instanceof TraSua) {
                totalTraSua++;
            }
            if (nu instanceof SinhTo) {
                totalSinhTo++;
            }
            if (nu instanceof NuocTraiCay) {
                totalNuocTraiCay++;
            }
        }
        // System.out.println("\tSố loại nước uống: ");
        // System.out.println("\t1. Coffee: " + totalCoffee);
        // System.out.println("\t2. Trà sữa: " + totalTraSua);
        // System.out.println("\t3. Sinh tố: " + totalSinhTo);
        // System.out.println("\t4. Nước trái cây: " + totalNuocTraiCay);

        Function.clearScreen();
        System.out.println("\t============================================================================");
        System.out.printf("\t| %-50s %-21s |%n", "Số loại nước uống", "");
        System.out.println("\t============================================================================");
        System.out.printf("\t| %-30s %-41s |%n", "Coffee:", totalCoffee);
        System.out.printf("\t| %-30s %-41s |%n", "Trà sữa:", totalTraSua);
        System.out.printf("\t| %-30s %-41s |%n", "Sinh tố:", totalSinhTo);
        System.out.printf("\t| %-30s %-41s |%n", "Nước trái cây:", totalNuocTraiCay);
        System.out.println("\t============================================================================");
    }

    public void findWater() {
        Scanner sc = new Scanner(System.in);
        String str;
        String result = "";
        NuocUong temp = null;
        while (true) {
            // System.out.println("\tTìm kiếm sản phẩm theo: ");
            // System.out.println("\t1. Tìm kiếm theo ID");
            // System.out.println("\t2. Tìm kiếm theo Tên");
            // System.out.println("\t3. Tìm kiếm theo giá");
            // System.out.println("\t4. Tìm kiếm theo loại");
            // System.out.println("\t5. Thoát");
            // System.out.print("\tNhập lựa chọn: ");
            Function.clearScreen();
            System.out.println("\t===================================================================================");
            System.out.printf("\t| %-60s %-18s |%n", "Bạn muốn tìm nước uống theo tiêu chí nào?", "");
            System.out.println("\t===================================================================================");
            System.out.printf("\t| %-10s %-68s |%n", "1.", "Tìm kiếm theo ID");
            System.out.printf("\t| %-10s %-68s |%n", "2.", "Tìm kiếm theo Tên");
            System.out.printf("\t| %-10s %-68s |%n", "3.", "Tìm kiếm theo giá");
            System.out.printf("\t| %-10s %-68s |%n", "4.", "Tìm kiếm theo loại");
            System.out.printf("\t| %-10s %-68s |%n", "5.", "Thoát");
            System.out.println("\t===================================================================================");
            System.out.print("\t=> Mời bạn nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    int number = Integer.parseInt(str);
                    if (number >= 1 && number <= 5) {
                        // Tìm kiếm theo ID
                        if (number == 1) {
                            System.out.print("\t=> Nhập ID muốn tìm (bao gồm mã): ");
                            str = sc.nextLine();
                            for (NuocUong nu : this.waterList) {
                                if (nu.getId().equalsIgnoreCase(str)) {
                                    temp = nu;
                                    result = temp.makeString();
                                    break;
                                }
                            }
                            if (Function.isEmpty(result)) {
                                System.out.println("\tKhông tìm thấy sản phẩm nào có ID: " + str);
                            } else {
                                Function.clearScreen();
                                System.out.println("\tKết quả tìm kiếm: ");
                                temp.xuatThongTin();
                                System.out.println("\tEnter để tiếp tục!");
                                str = sc.nextLine();
                            }
                        }
                        // Tìm kiếm theo Tên (Có thể rỗng)
                        if (number == 2) {
                            System.out.print("\t=> Nhập tên muốn tìm: ");
                            str = sc.nextLine();
                            for (NuocUong nu : this.waterList) {
                                if (nu.getName().equalsIgnoreCase(str)) {
                                    temp = nu;
                                    result = nu.makeString();
                                    break;
                                }
                            }
                            if (Function.isEmpty(result)) {
                                System.out.println("\tKhông tìm thấy sản phẩm nào có tên: " + str);
                            } else {
                                Function.clearScreen();
                                System.out.println("\tKết quả tìm kiếm: ");
                                temp.xuatThongTin();
                                System.out.println("\tEnter để tiếp tục!");
                                str = sc.nextLine();
                            }
                        }
                        // Tìm kiếm theo giá
                        if (number == 3) {
                            System.out.print("\t=> Nhập giá muốn tìm: ");
                            str = sc.nextLine();
                            for (NuocUong nu : this.waterList) {
                                if (nu.getSizePrice().containsValue(Integer.parseInt(str))) {
                                    Function.clearScreen();
                                    nu.xuatThongTin();
                                    System.out.println("\tEnter để tiếp tục!");
                                    str = sc.nextLine();
                                    result = nu.makeString();
                                }
                            }
                            if (Function.isEmpty(result)) {
                                System.out.println("\tKhông tìm thấy sản phẩm nào có giá: " + str);
                            }
                        }
                        // Tìm kiếm theo loại
                        if (number == 4) {
                            while (true) { 
                                                            // System.out.println("\tCác loại nước uống: ");
                            // System.out.println("\t1. Coffee mã loại [CF]");
                            // System.out.println("\t2. Trà sữa mã loại [TS]");
                            // System.out.println("\t3. Sinh tố mã loại [ST]");
                            // System.out.println("\t4. Nước trái cây mã loại [TC]");
                                Function.clearScreen();
                                System.out.println("\t===================================================================================");
                                System.out.printf("\t| %-60s %-18s |%n", "Các loại nước uống", "");
                                System.out.println("\t===================================================================================");
                                System.out.printf("\t| %-10s %-68s |%n", "1.", "Coffee mã loại [CF]");
                                System.out.printf("\t| %-10s %-68s |%n", "2.", "Trà sữa mã loại [TS]");
                                System.out.printf("\t| %-10s %-68s |%n", "3.", "Sinh tố mã loại [ST]");
                                System.out.printf("\t| %-10s %-68s |%n", "4.", "Nước trái cây mã loại [TC]");
                                System.out.println("\t===================================================================================");
                                System.out.print("\t=> Nhập loại muốn tìm: ");
                                str = sc.nextLine();

                                if (Function.isEmpty(str)) {
                                    System.out.println("\tVui lòng không để trống!");
                                    System.out.println("\tEnter để tiếp tục");
                                    str = sc.nextLine();
                                    continue;
                                }

                                if (!Function.isTrueNumber(str)) {
                                    System.out.println("\tVui lòng nhập số!");
                                    System.out.println("\tEnter để tiếp tục");
                                    str = sc.nextLine();
                                    continue;
                                }

                                String type = new String();
                                switch (str) {
                                    case "1":
                                        type = "CF";
                                        break;
                                    
                                    case "2":
                                        type = "TS";
                                        break;

                                    case "3":
                                        type = "ST";
                                        break;

                                    case "4":
                                        type = "TC";
                                        break;

                                    default:
                                        System.out.println("\tLựa chọn không hợp lệ!");
                                        System.out.println("\tEnter để tiếp tục");
                                        str = sc.nextLine();
                                        continue;
                                }
                                Function.clearScreen();
                                for (NuocUong nu : this.waterList) {
                                    if (nu.getDrinkType().equalsIgnoreCase(type)) {
                                        nu.xuatThongTin();
                                        result = nu.makeString();
                                    }
                                }
                                System.out.println("\tEnter để tiếp tục!");
                                str = sc.nextLine();
                                if (Function.isEmpty(result)) {
                                    System.out.println("\tKhông tìm thấy sản phẩm nào có loại: " + type);
                                }

                                break;
                            }
                        }
                        if (number == 5) {
                            System.out.println("\tThoát tìm kiếm !");
                            Function.clearScreen();
                            break;
                        }
                    } else {
                        System.out.println("\tVui lòng chọn trong khoảng 1 đến 4 !");
                    }
                } else {
                    System.out.println("\tVui lòng nhập số !");
                }
            }
        }
    }

    public void modifyInfo() {
        Scanner sc = new Scanner(System.in);
        String str;
        boolean action = false;
        this.inDanhSach();
        while (true) {
            System.out.print("\tNhập mã ID bạn muốn sửa: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                for (NuocUong nu : this.waterList) {
                    if (nu.getId().equalsIgnoreCase(str)) {
                        action = true;
                        nu.suaThongTin();
                        break;
                    }
                }
                if (!action) {
                    System.out.println("\tKhông tìm thấy ID: " + str);
                }
                break;
            }
        }
        // Ghi lại vào file
        this.writeFile();
    }

    public void inDanhSach() {
        for (NuocUong nu : this.waterList) {
            nu.xuatThongTin();
        }
    }

    public void inMenuNuocUong() {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            System.out.println("\t+--------------------------------------------------------------------------------+");
            System.out.println("\t|                                MENU NƯỚC UỐNG                                  |");
            System.out.println("\t+--------------------------------------------------------------------------------+");
            System.out.println("\t| 1. Coffee                                                                      |");
            System.out.println("\t| 2. Trà sữa                                                                     |");
            System.out.println("\t| 3. Sinh tố                                                                     |");
            System.out.println("\t| 4. Nước trái cây                                                               |");
            System.out.println("\t+--------------------------------------------------------------------------------+");
            System.out.print("\tChọn loại nước uống: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
                continue;
            }
            if (!Function.isTrueNumber(str)) {
                System.out.println("\tVui lòng nhập số !");
                continue;
            }

            int count = 0;
            switch (str) {
                case "1":
                    System.out.println(
                            "\t+------------+--------------------------------+----------------------+----------------------+----------------------+----------------------+");
                    System.out.printf("\t| %-10s | %-30s | %-20s | %-20s | %-20s | %-20s |\n",
                            "ID", "Tên", "Size S", "Size M", "Size L", "Size XL");
                    System.out.println(
                            "\t+------------+--------------------------------+----------------------+----------------------+----------------------+----------------------+");
                    for (NuocUong nu : this.waterList) {
                        if (nu.drinkType.equals("CF")) {
                            nu.menuTable();
                            count++;
                        }
                    }
                    if (count == 0) {
                        System.out.println("\tKhông có sản phẩm nào !");
                    }

                    break;
                case "2":
                    System.out.println(
                            "\t+------------+--------------------------------+----------------------+----------------------+----------------------+----------------------+");
                    System.out.printf("\t| %-10s | %-30s | %-20s | %-20s | %-20s | %-20s |\n",
                            "ID", "Tên", "Size S", "Size M", "Size L", "Size XL");
                    System.out.println(
                            "\t+------------+--------------------------------+----------------------+----------------------+----------------------+----------------------+");
                    for (NuocUong nu : this.waterList) {
                        if (nu.drinkType.equals("TS")) {
                            nu.menuTable();
                            count++;
                        }
                    }
                    if (count == 0) {
                        System.out.println("\tKhông có sản phẩm nào !");
                    }
                    break;
                case "3":
                    System.out.println(
                            "\t+------------+--------------------------------+----------------------+----------------------+----------------------+----------------------+");
                    System.out.printf("\t| %-10s | %-30s | %-20s | %-20s | %-20s | %-20s |\n",
                            "ID", "Tên", "Size S", "Size M", "Size L", "Size XL");
                    System.out.println(
                            "\t+------------+--------------------------------+----------------------+----------------------+----------------------+----------------------+");
                    for (NuocUong nu : this.waterList) {
                        if (nu.drinkType.equals("ST")) {
                            nu.menuTable();
                            count++;
                        }
                    }
                    if (count == 0) {
                        System.out.println("\tKhông có sản phẩm nào !");
                    }
                    break;
                case "4":
                    System.out.println(
                            "\t+------------+--------------------------------+----------------------+----------------------+----------------------+----------------------+");
                    System.out.printf("\t| %-10s | %-30s | %-20s | %-20s | %-20s | %-20s |\n",
                            "ID", "Tên", "Size S", "Size M", "Size L", "Size XL");
                    System.out.println(
                            "\t+------------+--------------------------------+----------------------+----------------------+----------------------+----------------------+");
                    for (NuocUong nu : this.waterList) {
                        if (nu.drinkType.equals("TC")) {
                            nu.menuTable();
                            count++;
                        }
                    }
                    if (count == 0) {
                        System.out.println("\tKhông có sản phẩm nào !");
                    }
                    break;
                default:
                    System.out.println("\tVui lòng chọn trong khoảng 1 đến 4 !");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            break;
        }
    }

    public void drinkFunctionMenu() {
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            Function.clearScreen();
            System.out.println("\t===============================[ Menu chức năng ]===========================");
            System.out.printf("\t| %-4s | %-65s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------|");
            System.out.printf("\t| %-4s | %-65s |%n", "1", "Tìm kiếm nước uống");
            System.out.printf("\t| %-4s | %-65s |%n", "2", "In ra nước uống từng loại");
            System.out.printf("\t| %-4s | %-65s |%n", "3", "Quay lại menu chính");
            System.out.println("\t============================================================================");
            System.out.print("\t=> Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được trống!");
                System.out.println("\tEnter để tiếp tục!");
                str = sc.nextLine();
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
                System.out.println("\tEnter để tiếp tục!");
                str = sc.nextLine();
                continue;
            }

            switch (str) {
                case "1":
                this.findWater();
                break;

                case "2":
                this.listItem();
                System.out.println("\tEnter để tiếp tục!");
                str = sc.nextLine();
                break;

                case "3":
                break;

                default:
                System.out.println("\tLựa chọn không hợp lệ!");
                System.out.println("\tEnter để tiếp tục!");
                str = sc.nextLine();
                continue;
            }

            break;
        }
    }

    public void addRemoveModifyMenu() {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            Function.clearScreen();
            System.out.println("\t============================[ Menu thêm/sửa/xóa ]===========================");
            System.out.printf("\t| %-4s | %-65s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------|");
            System.out.printf("\t| %-4s | %-65s |%n", "1", "Thêm một món nước (Tự động lưu vào File)");
            System.out.printf("\t| %-4s | %-65s |%n", "2", "Xoá một món nước (Tự động load vào File)");
            System.out.printf("\t| %-4s | %-65s |%n", "3", "Sửa thông tin nước uống");
            System.out.printf("\t| %-4s | %-65s |%n", "4", "Quay lại menu chính");
            System.out.println("\t============================================================================");
            System.out.print("\t=> Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được trống!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
                continue;
            }

            switch (str) {
                case "1":
                    this.addNewWater();
                    break;

                case "2":
                    this.removeWater();
                    break;

                case "3":
                    this.modifyInfo();
                    break;

                case "4":
                    break;

                default:
                    System.out.println("\tLựa chọn không hợp lệ! Hãy thử lại.");
                    System.out.println("\tEnter để tiếp tục!");
                    str = sc.nextLine();
                    continue;
            }
            break;
        }
    }

    public void updateMenu() {
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            Function.clearScreen();
            System.out.println("\t===============================[ Menu cập nhật ]============================");
            System.out.printf("\t| %-4s | %-65s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------|");
            System.out.printf("\t| %-4s | %-65s |%n", "1", "Cập nhật danh sách nước uống vào File");
            System.out.printf("\t| %-4s | %-65s |%n", "2", "Cập nhật lại danh sách nước uống từ File");
            System.out.printf("\t| %-4s | %-65s |%n", "3", "Làm mới danh sách nước uống (Reset dữ liệu)");
            System.out.printf("\t| %-4s | %-65s |%n", "4", "Quay về trang trước");
            System.out.println("\t============================================================================");
            System.out.print("\t=> Nhập lựa chọn: ");
            str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tLựa chọn không được trống!");
                continue;
            }

            if (!Function.isTrueNumber(str)) {
                System.out.println("\tLựa chọn phải là số!");
                continue;
            }

            switch (str) {
                case "1":
                this.writeFile();
                break;

                case "2":
                this.Init();
                break;

                case "3":
                this.clearList();
                break;

                case "4":
                break;

                default:
                continue;
            }

            break;
        }
    }

    public void menuQLNuocUong() {
        Function.clearScreen();
        this.Init(); // Khởi tạo dữ liệu
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            Function.clearScreen();
            // In tiêu đề
            System.out.println(
                    "\t===============================[ Menu Quản Lý Nước Uống ]===============================");

            // In tiêu đề các cột
            System.out.printf("\t| %-4s | %-77s |%n", "STT", "Chức năng");
            System.out.println(
                    "\t|------|-------------------------------------------------------------------------------|");

            // In danh sách các lựa chọn
            // System.out.printf("\t| %-4s | %-77s |%n", "1", "In danh sách nước uống");
            // System.out.printf("\t| %-4s | %-77s |%n", "2", "Thêm một sản phẩm nước uống (Tự động lưu vào File)");
            // System.out.printf("\t| %-4s | %-77s |%n", "3", "Xoá một sản phẩm nước uống (Tự động load vào File)");
            // System.out.printf("\t| %-4s | %-77s |%n", "4", "Sửa thông tin nước uống");
            // System.out.printf("\t| %-4s | %-77s |%n", "5", "Cập nhật lại sản phẩm vào File từ danh sách");
            // System.out.printf("\t| %-4s | %-77s |%n", "6", "Cập nhật lại sản phẩm vào danh sách từ File");
            // System.out.printf("\t| %-4s | %-77s |%n", "7",
            //         "Làm mới danh sách nước uống (Reset dữ liệu nhưng không load vào File)");
            // System.out.printf("\t| %-4s | %-77s |%n", "8",
            //         "In ra số sản phẩm từng loại (Kiểm tra số loại sản phẩm có trong danh sách)");
            // System.out.printf("\t| %-4s | %-77s |%n", "9", "Tìm kiếm sản phẩm");
            // System.out.printf("\t| %-4s | %-77s |%n", "10", "Làm mới màn hình");
            // System.out.printf("\t| %-4s | %-77s |%n", "11", "Thoát chương trình quản lý");

            System.out.printf("\t| %-4s | %-77s |%n", "1", "In danh sách nước uống");
            System.out.printf("\t| %-4s | %-77s |%n", "2", "Chức năng về nước uống");
            System.out.printf("\t| %-4s | %-77s |%n", "3", "Thêm/sửa/xóa thông tin nước uống");
            System.out.printf("\t| %-4s | %-77s |%n", "4", "Cập nhật và làm mới danh sách nước uống");
            System.out.printf("\t| %-4s | %-77s |%n", "5", "Làm mới màn hình");
            System.out.printf("\t| %-4s | %-77s |%n", "6", "Thoát chương trình quản lý");

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
                    if (number >= 1 && number <= 6) {
                        if (number == 1) {
                            this.inDanhSach();
                            System.out.print("\tEnter để tiếp tục");
                            str = sc.nextLine();
                        }
                        if (number == 2) {
                            this.drinkFunctionMenu();
                        }
                        if (number == 3) {
                            this.addRemoveModifyMenu();
                        }
                        if (number == 4) {
                            this.updateMenu();
                        }
                        if (number == 5) {
                            Function.clearScreen();
                        }
                        if (number == 6) {
                            break;
                        }
                    } else {
                        System.out.println("\tVui lòng chọn trong khoảng 1 đến 6 !");
                    }
                } else {
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
