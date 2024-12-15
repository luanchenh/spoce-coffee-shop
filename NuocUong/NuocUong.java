package NuocUong;

import Topping.QLTopping;
import Topping.Topping;
import Utils.Function;
import Utils.INhap;
import Utils.IXuat;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("resource")
public abstract class NuocUong implements INhap, IXuat {
    protected String id;
    protected String name;
    protected Map<String, Integer> sizePrice;
    protected ArrayList<String> topping;
    protected String drinkType; // [TS, CF, ST, TC] - [Trà sữa, Cà phê, Sinh tố, Nước trái cây]
    protected boolean isCold;
    protected boolean isHot;
    protected boolean isMilk;
    protected boolean isSugar;

    // Map để quản lý số thứ tự theo loại nước uống
    private static final Map<String, Integer> typeCounter = new HashMap<>();
    static {
        int totalTS = 0;
        int totalCF = 0;
        int totalST = 0;
        int totalTC = 0;

        File waterFile = new File("../File/water.txt");
        try (Scanner rd = new Scanner(waterFile)) {
            while (rd.hasNextLine()) { // Đọc từng dòng trong file
                String line = rd.nextLine();
                if (line.isEmpty())
                    continue; // Bỏ qua dòng rỗng

                // Tách chuỗi theo ký tự phân cách "|"
                String[] parts = line.split("\\|");
                if (parts.length > 0) {
                    String value = parts[0].substring(0, 2).toUpperCase();
                    int number = Integer.parseInt(parts[0].substring(2));
                    if (value.equals("TS")) {
                        totalTS = Math.max(totalTS, number);
                    } else if (value.equals("CF")) {
                        totalCF = Math.max(totalCF, number);
                    } else if (value.equals("ST")) {
                        totalST = Math.max(totalST, number);
                    } else if (value.equals("TC")) {
                        totalTC = Math.max(totalTC, number);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file water.txt: " + e.getMessage());
        }

        // Khởi tạo số thứ tự ban đầu cho từng loại
        typeCounter.put("TS", totalTS);
        typeCounter.put("CF", totalCF);
        typeCounter.put("ST", totalST);
        typeCounter.put("TC", totalTC);

        // Debug: In ra kết quả để kiểm tra
        // System.out.println("Đếm loại đồ uống:");
        // System.out.println("TS: " + totalTS);
        // System.out.println("CF: " + totalCF);
        // System.out.println("ST: " + totalST);
        // System.out.println("TC: " + totalTC);
    }

    // Constructor không tham số
    public NuocUong() {
        this.sizePrice = new HashMap<>();
        this.topping = new ArrayList<>();
        this.id = "";
        this.name = "";
        this.drinkType = "";
        this.isCold = false;
        this.isHot = false;
        this.isMilk = false;
        this.isSugar = false;
    }

    // Constructor biết được loại nước uống
    public NuocUong(String drinkType) {
        this.sizePrice = new HashMap<>();
        this.topping = new ArrayList<>();
        this.name = "";
        this.drinkType = drinkType;
        this.isCold = false;
        this.isHot = false;
        this.isMilk = false;
        this.isSugar = false;

        // Tăng số thứ tự cho loại nước uống
        if (typeCounter.containsKey(drinkType)) {
            int currentCount = typeCounter.get(drinkType);
            typeCounter.put(drinkType, currentCount);
            this.id = drinkType + currentCount; // Tạo ID theo loại
        } else {
            this.id = "UNKNOWN"; // Trường hợp loại không hợp lệ
        }
    }

    // Constructor biết full tham số
    public NuocUong(String id, String name, Map<String, Integer> sizePrice, ArrayList<String> topping, String drinkType,
            boolean isCold, boolean isHot, boolean isMilk, boolean isSugar) {
        this.id = id;
        this.name = name;
        this.sizePrice = sizePrice;
        this.topping = topping;
        this.drinkType = drinkType;
        this.isCold = isCold;
        this.isHot = isHot;
        this.isMilk = isMilk;
        this.isSugar = isSugar;
    }

    // ========================================[Getter -
    // Setter]========================================
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getSizePrice() {
        return sizePrice;
    }

    public ArrayList<String> getTopping() {
        return topping;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public boolean isCold() {
        return isCold;
    }

    public boolean isHot() {
        return isHot;
    }

    public boolean isMilk() {
        return isMilk;
    }

    public boolean isSugar() {
        return isSugar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSizePrice(Map<String, Integer> sizePrice) {
        this.sizePrice = sizePrice;
    }

    public void setTopping(ArrayList<String> topping) {
        this.topping = topping;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public void setCold(boolean isCold) {
        this.isCold = isCold;
    }

    public void setHot(boolean isHot) {
        this.isHot = isHot;
    }

    public void setMilk(boolean isMilk) {
        this.isMilk = isMilk;
    }

    public void setSugar(boolean isSugar) {
        this.isSugar = isSugar;
    }

    // ========================================[Bolean
    // Method]========================================
    // Kiểm tra thêm được hay xoá được sản phẩm hay không
    public boolean addTopping(String idTopping) {
        this.topping.add(idTopping);
        return true;
    }

    public boolean removeTopping(String idTooping) {
        // Kiểm tra coi mã topping có tồn tại không
        if (this.topping.contains(idTooping)) {
            this.topping.remove(idTooping);
            return true;

        } else
            return false;
    }

    // ========================================[Override
    // Method]========================================
    @Override
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        String str;
        int number = 0;
        boolean action;
        if (this.drinkType.equals("")) {
            while (true) {
                for (Map.Entry<String, Integer> entry : typeCounter.entrySet()) {
                    if (entry.getKey().equals("TS")) {
                        System.out.println("1. [Trà sữa] " + entry.getKey() + "-" + entry.getValue());
                    } else if (entry.getKey().equals("CF")) {
                        System.out.println("2. [Cà phê] " + entry.getKey() + "-" + entry.getValue());
                    } else if (entry.getKey().equals("ST")) {
                        System.out.println("3. [Sinh tố] " + entry.getKey() + "-" + entry.getValue());
                    } else if (entry.getKey().equals("TC")) {
                        System.out.println("4. [Nước trái cây] " + entry.getKey() + "-" + entry.getValue());
                    }
                }
                System.out.print("\tChọn loại đồ uống: ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống !");
                    continue;
                } else {
                    if (Function.isTrueNumber(str)) {
                        number = Integer.parseInt(str);
                        if (number < 1 || number > 4) {
                            System.out.println("\tVui lòng chọn trong khoảng phạm vi từ 1 đến 4 !");
                        } else {
                            if (number == 1) {
                                this.drinkType = "TS";
                            } else if (number == 2) {
                                this.drinkType = "CF";
                            } else if (number == 3) {
                                this.drinkType = "ST";
                            } else if (number == 4) {
                                this.drinkType = "TC";
                            }
                            break;
                        }
                    } else {
                        System.out.println("\tVui lòng nhập số !");
                    }
                }
            }
        }
        System.out.println("\t[Notice] Loại đồ uống hiện tại: " + this.drinkType);
        int currentCount = typeCounter.get(this.drinkType) + 1;
        typeCounter.put(this.drinkType, currentCount);
        this.id = this.drinkType + currentCount;
        System.out.println("\t[Notice] ID đồ uống hiện tại: " + this.id);

        while (true) {
            System.out.print("\tNhập tên đồ uống: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    System.out.println("\tTên đồ uống không được là số !");
                } else {
                    this.name = Function.normalizeName(str);
                    break;
                }
            }
        }
        System.out.println("\t[Notice] Tên đồ uống hiện tại: " + this.name);
        while (true) {
            System.out.println("\tĐồ uống có điều chỉnh lạnh được không ?");
            System.out.println("\t1. [Yes]: Có");
            System.out.println("\t2. [No]: Không");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (str.equals("1")) {
                    this.isCold = true;
                    break;
                } else if (str.equals("2")) {
                    this.isCold = false;
                    break;
                } else {
                    System.out.println("\tVui lòng nhập 1 hoặc 2 !");
                }
            }
        }
        while (true) {
            System.out.println("\tĐồ uống có uống nóng được không ?");
            System.out.println("\t1. [Yes]: Có");
            System.out.println("\t2. [No]: Không");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (str.equals("1")) {
                    this.isHot = true;
                    break;
                } else if (str.equals("2")) {
                    this.isHot = false;
                    break;
                } else {
                    System.out.println("\tVui lòng nhập 1 hoặc 2 !");
                }
            }
        }
        while (true) {
            System.out.println("\tĐồ uống có điều chỉnh sữa được không ?");
            System.out.println("\t1. [Yes]: Có");
            System.out.println("\t2. [No]: Không");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (str.equals("1")) {
                    this.isMilk = true;
                    break;
                } else if (str.equals("2")) {
                    this.isMilk = false;
                    break;
                } else {
                    System.out.println("\tVui lòng nhập 1 hoặc 2 !");
                }
            }
        }
        while (true) {
            System.out.println("\tĐồ uống có điều chỉnh đường được không ?");
            System.out.println("\t1. [Yes]: Có");
            System.out.println("\t2. [No]: Không");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (str.equals("1")) {
                    this.isSugar = true;
                    break;
                } else if (str.equals("2")) {
                    this.isSugar = false;
                    break;
                } else {
                    System.out.println("\tVui lòng nhập 1 hoặc 2 !");
                }
            }
        }
        while (true) {
            System.out.println("\tChọn sản phẩm có mấy loại size");
            System.out.println("\t1. [3 size]: S, M, L");
            System.out.println("\t2. [4 size]: S, M, L, XL");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (str.equals("1")) {
                    this.sizePrice.put("S", 0);
                    this.sizePrice.put("M", 0);
                    this.sizePrice.put("L", 0);
                    break;
                } else if (str.equals("2")) {
                    this.sizePrice.put("S", 0);
                    this.sizePrice.put("M", 0);
                    this.sizePrice.put("L", 0);
                    this.sizePrice.put("XL", 0);
                    break;
                } else {
                    System.out.println("\tVui lòng nhập 1 hoặc 2 !");
                }
            }
        }
        // Nhập giá cho từng size
        for (Map.Entry<String, Integer> entry : this.sizePrice.entrySet()) {
            while (true) {
                System.out.print("\tNhập giá cho size " + entry.getKey() + ": ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống !");
                } else {
                    if (!Function.isTrueNumber(str)) {
                        System.out.println("\tGiá phải là số và phải lớn hơn 0");
                    } else {
                        number = Integer.parseInt(str);
                        this.sizePrice.put(entry.getKey(), number);
                        System.out.println("\t[Notice] Giá cho size " + entry.getKey() + " là: "
                                + Function.formatMoney(number + ""));
                        break;
                    }
                }
            }
        }
        // Hỏi và nhập topping
        // TP1|Trân châu trắng|3000
        while (true) {
            System.out.println("\tLoại đồ uống này có topping hay không ?");
            System.out.println("\t1. [Yes]: Có");
            System.out.println("\t2. [No]: Không");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (str.equals("1")) {
                    action = true;
                    break;
                } else if (str.equals("2")) {
                    action = false;
                    break;
                } else {
                    System.out.println("\tVui lòng nhập 1 hoặc 2 !");
                }
            }
        }
        if (action) {
            while (true) {
                System.out.print("\tNhập số lượng topping: ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("\tVui lòng không để trống !");
                } else {
                    if (Function.isTrueNumber(str)) {
                        number = Integer.parseInt(str);
                        // Lấy số lượng topping hiện có trong file
                        File file = new File("../File/topping.txt");
                        int count = 0;
                        try (Scanner rd = new Scanner(file)) {
                            while (rd.hasNextLine()) {
                                String line = rd.nextLine();
                                if (line.isEmpty())
                                    continue;
                                count++;
                            }
                        } catch (Exception e) {
                            System.out.println("Lỗi khi đọc file topping.txt: " + e.getMessage());
                        }
                        if (number > count) {
                            System.out.println("\tSố lượng topping không được lớn hơn số lượng topping hiện có !");
                        } else if (number == 0) {
                            System.out.println("\tSố lượng topping không được bằng 0 !");
                        } else {
                            System.out.println(
                                    "\t[Notice] Loại đồ uống " + this.drinkType + " có " + number + " topping");
                            break;
                        }
                    } else {
                        System.out.println("\tVui lòng nhập số và số phải lớn hơn 0");
                    }
                }
            }
        }

        if (number > 0 && action == true) {
            QLTopping list = new QLTopping();
            list.Init();
            for (int i = 0; i < number; i++) {
                boolean idFound = false; // Biến kiểm tra nếu ID đã được tìm thấy
                while (true) {
                    list.menuTable();
                    System.out.println("\tNhập ID topping thứ " + (i + 1) + "(Nhập số): ");
                    str = sc.nextLine();
                    if (Function.isEmpty(str)) {
                        System.out.println("\tVui lòng không để trống !");
                    } else {
                        if (Function.isTrueNumber(str)) {
                            String result = "TP" + str;
                            for (Topping tp : list.getToppingList()) {
                                if (tp.getId().equals(result)) {
                                    this.topping.add(result);
                                    System.out.println("\t[Notice] Topping " + tp.getName() + " đã được thêm vào");
                                    idFound = true; // Đã tìm thấy ID hợp lệ
                                    break; // Thoát khỏi vòng lặp `for`
                                }
                            }
                            if (idFound) {
                                break; // Thoát khỏi vòng lặp `while` nếu ID đã được thêm vào
                            } else {
                                System.out.println("\tID không tồn tại !");
                            }
                        } else {
                            System.out.println("\tVui lòng nhập số !");
                        }
                    }
                }
            }
        }

    }

    @Override
    public void xuatThongTin() {
        System.out.println(
                "\t+=========================================================================================================================+");
        System.out.println(
                "\t|                                                Thông tin sản phẩm                                                       |");
        System.out.println(
                "\t+------------+---------------+--------------------------------+--------------+--------------+--------------+--------------+");

        // In thông tin chính của sản phẩm
        System.out.printf("\t| %-10s | %-13s | %-30s | %-12s | %-12s | %-12s | %-12s |\n",
                "ID", "Loại", "Tên", "Chỉnh đá", "Chỉnh nóng", "Chỉnh sữa", "Chỉnh đường");
        System.out.println(
                "\t+------------+---------------+--------------------------------+--------------+--------------+--------------+--------------+");

        // In các giá trị của sản phẩm
        String drinkTypeLabel = this.drinkType.equals("CF") ? "Cà phê"
                : this.drinkType.equals("TS") ? "Trà sữa"
                        : this.drinkType.equals("ST") ? "Sinh tố"
                                : this.drinkType.equals("TC") ? "Nước trái cây" : "Không xác định";

        System.out.printf("\t| %-10s | %-13s | %-30s | %-12s | %-12s | %-12s | %-12s |\n",
                this.id, drinkTypeLabel, this.name, (this.isCold ? "Có" : "Không"),
                (this.isHot ? "Có" : "Không"), (this.isMilk ? "Có" : "Không"), (this.isSugar ? "Có" : "Không"));
        System.out.println(
                "\t+------------+---------------+--------------------------------+--------------+--------------+--------------+--------------+");

        // In bảng giá theo size
        System.out.println(
                "\t| Bảng giá:                                                                                                               |");
        System.out.println(
                "\t+------------+---------------+------------+------------+------------+------------+------------+------------+----------+---+");
        System.out.print("\t| ");
        // for (String size : sizePrice.keySet()) {
        // System.out.print(String.format("%-10s | ", size));
        // }
        ArrayList<String> keySet = new ArrayList<>(sizePrice.keySet());
        for (int i = 0; i < keySet.size(); i++) {
            switch (i) {
                case 0:
                    System.out.print(String.format("%-10s | ", keySet.get(i)));
                    break;

                case 1:
                    System.out.print(String.format("%-13s | ", keySet.get(i)));
                    break;

                case 2:
                    System.out.print(String.format("%-10s | ", keySet.get(i)));
                    break;

                case 3:
                    System.out.print(String.format("%-10s | ", keySet.get(i)));
                    break;
            }
        }
        if (this.sizePrice.size() == 4) {
            System.out.print("                                                                 |");
        } else {
            System.out.print("                                                                              |");
        }
        System.out.println();
        System.out.print("\t| ");
        ArrayList<Integer> valueSet = new ArrayList<>(sizePrice.values());
        for (int i = 0; i < keySet.size(); i++) {
            switch (i) {
                case 0:
                    System.out.print(String.format("%-10s | ", valueSet.get(i)));
                    break;

                case 1:
                    System.out.print(String.format("%-13s | ", valueSet.get(i)));
                    break;

                case 2:
                    System.out.print(String.format("%-10s | ", valueSet.get(i)));
                    break;

                case 3:
                    System.out.print(String.format("%-10s | ", valueSet.get(i)));
                    break;
            }
        }
        if (this.sizePrice.size() == 4) {
            System.out.print("                                                                 |");
        } else {
            System.out.print("                                                                              |");
        }
        System.out.println();
        System.out.println(
                "\t+------------+---------------+------------+------------+------------+------------+------------+------------+----------+---+");

        // In danh sách topping
        System.out.println(
                "\t| Danh sách topping:                                                                                                      |");
        if (topping.isEmpty()) {
            System.out.printf("\t| %-119s |\n", "Không có topping nào.");
        } else {
            String toppings = String.join(", ", this.topping); // Nối tất cả topping thành một chuỗi
            System.out.printf("\t| %-119s |\n", toppings);
        }

        System.out.println(
                "\t+=========================================================================================================================+");
        System.out.println();
    }

    public void menuTable() {
        if (this.sizePrice.size() == 3) {
            // Kiểm tra từng key và lấy giá trị
            String sizeS = this.sizePrice.containsKey("S") ? Function.formatMoney(this.sizePrice.get("S").toString())
                    : "N/A";
            String sizeM = this.sizePrice.containsKey("M") ? Function.formatMoney(this.sizePrice.get("M").toString())
                    : "N/A";
            String sizeL = this.sizePrice.containsKey("L") ? Function.formatMoney(this.sizePrice.get("L").toString())
                    : "N/A";

            System.out.printf("\t| %-10s | %-30s | %-20s | %-20s | %-20s |                      |\n",
                    this.getId(), this.getName(), sizeS, sizeM, sizeL);
            System.out.println(
                    "\t+------------+--------------------------------+----------------------+----------------------+----------------------+----------------------+");

        } else if (this.sizePrice.size() == 4) {
            // Kiểm tra từng key và lấy giá trị
            String sizeS = this.sizePrice.containsKey("S") ? Function.formatMoney(this.sizePrice.get("S").toString())
                    : "N/A";
            String sizeM = this.sizePrice.containsKey("M") ? Function.formatMoney(this.sizePrice.get("M").toString())
                    : "N/A";
            String sizeL = this.sizePrice.containsKey("L") ? Function.formatMoney(this.sizePrice.get("L").toString())
                    : "N/A";
            String sizeXL = this.sizePrice.containsKey("XL") ? Function.formatMoney(this.sizePrice.get("XL").toString())
                    : "N/A";

            System.out.printf("\t| %-10s | %-30s | %-20s | %-20s | %-20s | %-20s |\n",
                    this.getId(), this.getName(), sizeS, sizeM, sizeL, sizeXL);
            System.out.println(
                    "\t+------------+--------------------------------+----------------------+----------------------+----------------------+----------------------+");
        }
    }

    // Abstract method
    public void suaThongTin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\t===============================[Sửa thông tin sản phẩm]===============================");
        String str;
        while (true) {
            System.out.println(
                    "\t1. Sửa tên sản phẩm\n\t2. Sửa giá của Size\n\t3. Sửa các trạng thái sản phẩm (boolean)\n\t4. Sửa danh sách topping");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Vui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    int number = Integer.parseInt(str);
                    if (number >= 1 && number <= 4) {
                        if (number == 1) {
                            if (this.suaTenSanPham(sc, str)) {
                                System.out.println("\tTên mới của sản phẩm là: " + this.name);
                            } else {
                                System.out.println("\tSửa tên sản phẩm thất bại !");
                            }
                        }
                        if (number == 2) {
                            System.out.println("\tHiện tại sản phẩm có: " + this.sizePrice.size() + " loại size");
                            System.out.print("\tCác loại Size: ");
                            for (Map.Entry<String, Integer> entry : this.sizePrice.entrySet()) {
                                System.out.print(entry.getKey() + " ");
                            }
                            System.out.println();
                            if (this.suaGiaSize(sc)) {
                                System.out.println("\tSửa giá cho size thành công");
                            } else {
                                System.out.println("\tSửa giá cho Size thất bại !");
                            }
                        }
                        if (number == 3) {
                            if (this.suaTrangThai()) {
                                System.out.println("\tSửa trạng thái sản phẩm thành công !");
                            } else {
                                System.out.println("\tSửa trạng thái sản phẩm thất bại !");
                            }
                        }
                        if (number == 4) {
                            this.modifyToppingList();
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
    }

    public boolean suaTenSanPham(Scanner sc, String str) {
        boolean updated = false;
        while (true) {
            System.out.print("\tNhập tên mới cho sản phẩm: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    System.out.println("\tTên đồ uống không được là số !");
                } else {
                    this.name = str;
                    updated = true;
                    break;
                }
            }
        }
        return updated;
    }

    public void modifyToppingList() {
        Scanner sc = new Scanner(System.in);
        QLTopping list = new QLTopping();
        list.Init();
        String str;
        int number = 0;
        QLTopping qlTopping = new QLTopping();
        qlTopping.Init();
        while (true) {
            System.out.println("\tBạn muốn thêm hay xoá topping ?");
            System.out.println("\t1. Thêm topping");
            System.out.println("\t2. Xoá topping");
            System.out.println("\t3. Xoá tất cả topping");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println(("\tVui lòng không để trống !"));
            } else {
                if (Function.isTrueNumber(str)) {
                    number = Integer.parseInt(str);
                    if (number >= 1 && number <= 3) {
                        if (number == 1) {
                            while (true) {
                                list.menuTable();
                                System.out.print("\tNhập ID topping cần thêm: ");
                                str = sc.nextLine();
                                if (Function.isEmpty(str)) {
                                    System.out.println("\tVui lòng không để trống !");
                                } else {
                                    if (Function.isTrueNumber(str)) {
                                        String result = "TP" + str;
                                        for (Topping tp : list.getToppingList()) {
                                            if (tp.getId().equals(result)) {
                                                this.topping.add(result);
                                                System.out.println(
                                                        "\t[Notice] Topping " + tp.getName() + " đã được thêm vào");
                                                break;
                                            }
                                        }
                                        break;
                                    } else {
                                        System.out.println("\tVui lòng nhập số !");
                                    }
                                }
                            }
                            break;
                        }
                        if (number == 2) {
                            while (true) {
                                System.out.println("\tDanh sách topping hiện tại: ");
                                for (String tp : this.topping) {
                                    for (Topping topping : qlTopping.getToppingList()) {
                                        if (topping.getId().equals(tp)) {
                                            System.out.println("\t" + topping.getId() + " - " + topping.getName());
                                        }
                                    }
                                }
                                System.out.print("\tNhập ID topping cần xoá: ");
                                str = sc.nextLine();
                                if (Function.isEmpty(str)) {
                                    System.out.println("\tVui lòng không để trống !");
                                } else {
                                    if (Function.isTrueNumber(str)) {
                                        String result = "TP" + str;
                                        if (this.topping.contains(result)) {
                                            this.topping.remove(result);
                                            System.out.println("\t[Notice] Topping " + result + " đã được xoá");
                                        } else {
                                            System.out.println("\tID không tồn tại trong danh sách topping !");
                                        }
                                        break;
                                    } else {
                                        System.out.println("\tVui lòng nhập số !");
                                    }
                                }
                            }
                            break;
                        }
                        if (number == 3) {
                            this.topping.clear();
                            System.out.println("\t[Notice] Đã xoá tất cả topping !");
                            break;
                        }
                    } else {
                        System.out.println("\tVui lòng nhập trong khoảng 1 đến 3 !");
                    }
                }
            }
        }

    }

    public boolean suaGiaSize(Scanner sc) {
        boolean updated = false; // Biến để kiểm tra nếu có cập nhật giá thành công

        // Hiển thị menu cho người dùng chọn
        while (true) {
            System.out.println("\t1. Chọn size để sửa");
            System.out.println("\t2. Sửa tất cả size");
            System.out.print("\tNhập lựa chọn: ");
            String str = sc.nextLine();

            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    int action = Integer.parseInt(str);
                    if (action == 1) {
                        // Chọn sửa một size cụ thể
                        System.out.print("\tNhập tên size cần sửa: ");
                        String sizeToEdit = sc.nextLine();

                        if (this.sizePrice.containsKey(sizeToEdit)) {
                            while (true) {
                                System.out.print("\tNhập giá mới cho size " + sizeToEdit + ": ");
                                str = sc.nextLine();
                                if (Function.isEmpty(str)) {
                                    System.out.println("\tVui lòng không để trống !");
                                } else {
                                    if (!Function.isTrueNumber(str)) {
                                        System.out.println("\tGiá phải là số và phải lớn hơn 0");
                                    } else {
                                        int number = Integer.parseInt(str);
                                        if (number > 0) {

                                            this.sizePrice.put(sizeToEdit, number);
                                            System.out.println("\t[Notice] Giá mới cho size " + sizeToEdit + " là: "
                                                    + Function.formatMoney(number + ""));
                                            updated = true;
                                            break;
                                        } else {
                                            System.out.println("\tGiá phải lớn hơn 0 !");
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.println("\tSize không tồn tại trong danh sách.");
                        }
                    } else if (action == 2) {
                        // Sửa tất cả size
                        for (Map.Entry<String, Integer> entry : this.sizePrice.entrySet()) {
                            while (true) {
                                System.out.print("\tNhập giá mới cho size " + entry.getKey() + ": ");
                                str = sc.nextLine();
                                if (Function.isEmpty(str)) {
                                    System.out.println("\tVui lòng không để trống !");
                                } else {
                                    if (!Function.isTrueNumber(str)) {
                                        System.out.println("\tGiá phải là số và phải lớn hơn 0");
                                    } else {
                                        int number = Integer.parseInt(str);
                                        if (number > 0) {
                                            // Cập nhật giá mới cho size
                                            this.sizePrice.put(entry.getKey(), number);
                                            System.out.println("\t[Notice] Giá mới cho size " + entry.getKey() + " là: "
                                                    + Function.formatMoney(number + ""));
                                            updated = true;
                                            break;
                                        } else {
                                            System.out.println("\tGiá phải lớn hơn 0 !");
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("\tVui lòng nhập trong khoảng 1 đến 2 !");
                    }
                    break; // Thoát khỏi vòng lặp chính sau khi xử lý xong lựa chọn
                } else {
                    System.out.println("\tVui lòng nhập số !");
                }
            }
        }

        return updated; // Trả về true nếu đã cập nhật giá thành công, false nếu không
    }

    public boolean askYesNo() {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            System.out.println("\t1. [Yes]: Có");
            System.out.println("\t2. [No]: Không");
            System.out.print("\tNhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (str.equals("1")) {
                    return true;
                } else if (str.equals("2")) {
                    return false;
                } else {
                    System.out.println("\tVui lòng nhập 1 hoặc 2 !");
                }
            }
        }
    }

    public boolean suaTrangThai() {
        Scanner sc = new Scanner(System.in);
        boolean updated = false;
        while (true) {
            System.out.println("\t Bạn muốn sửa trạng thái nào của sản phẩm ?");
            System.out.println("\t1. Sửa trạng thái có thể chỉnh đá");
            System.out.println("\t2. Sửa trạng thái có thể chỉnh nóng");
            System.out.println("\t3. Sửa trạng thái có thể chỉnh sữa");
            System.out.println("\t4. Sửa trạng thái có thể chỉnh đường");
            System.out.print("\tNhập lựa chọn: ");
            String str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tVui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    int number = Integer.parseInt(str);
                    if (number >= 1 && number <= 4) {
                        if (number == 1) {
                            while (true) {
                                System.out.println(
                                        "\tTrạng thái hiện tại của chỉnh đá: " + (this.isCold ? "Có" : "Không"));
                                if (this.askYesNo()) {
                                    this.isCold = !this.isCold;
                                    System.out.println(
                                            "\t[Notice] Trạng thái chỉnh đá: " + (this.isCold ? "Có" : "Không"));
                                    updated = true;
                                }
                                break;
                            }
                        }
                        if (number == 2) {
                            while (true) {
                                System.out.println(
                                        "\tTrạng thái hiện tại của chỉnh nóng: " + (this.isHot ? "Có" : "Không"));
                                if (this.askYesNo()) {
                                    this.isHot = !this.isHot;
                                    System.out.println(
                                            "\t[Notice] Trạng thái chỉnh nóng: " + (this.isHot ? "Có" : "Không"));
                                    updated = true;
                                }
                                break;
                            }
                        }
                        if (number == 3) {
                            while (true) {
                                System.out.println(
                                        "\tTrạng thái hiện tại của chỉnh sữa: " + (this.isMilk ? "Có" : "Không"));
                                if (this.askYesNo()) {
                                    this.isMilk = !this.isMilk;
                                    System.out.println(
                                            "\t[Notice] Trạng thái chỉnh sữa: " + (this.isMilk ? "Có" : "Không"));
                                    updated = true;
                                }
                                break;
                            }
                        }
                        if (number == 4) {
                            while (true) {
                                System.out.println(
                                        "\tTrạng thái hiện tại của chỉnh đường: " + (this.isSugar ? "Có" : "Không"));
                                if (this.askYesNo()) {
                                    this.isSugar = !this.isSugar;
                                    System.out.println(
                                            "\t[Notice] Trạng thái chỉnh đường: " + (this.isSugar ? "Có" : "Không"));
                                    updated = true;
                                }
                                break;
                            }
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
        return updated;
    }

    // Tạo chuỗi để lưu file
    public String makeString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id).append("|");
        sb.append(this.name).append("|");
        sb.append(this.isCold).append("|");
        sb.append(this.isHot).append("|");
        sb.append(this.isMilk).append("|");
        sb.append(this.isSugar).append("|");
        for (Map.Entry<String, Integer> entry : this.sizePrice.entrySet()) {
            sb.append(entry.getValue()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("|");
        if (this.topping.size() > 0) {
            for (String tp : this.topping) {
                sb.append(tp).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public void showMenu() {
        System.out.println("\t Tên sản phẩm: " + this.name);
        // Giá theo size
        for (Map.Entry<String, Integer> entry : this.sizePrice.entrySet()) {
            System.out.println("\t\t" + entry.getKey() + ": " + Function.formatMoney(entry.getValue() + ""));
        }
    }

    public void printToppingOfDrink() {
        QLTopping ql = new QLTopping();
        ql.Init();
        System.out.println("\t======================[Danh sách topping]====================");
        System.out.printf("\t| %-10s | %-25s | %-16s |\n", "Mã", "Tên topping", "Giá (VND)");
        System.out.println("\t=============================================================");
        for (String tpID : this.topping) {
            ql.getToppingByID(tpID).menuInfo();
        }
        System.out.println("\t=============================================================");
    }
}
