package NuocUong;

import Utils.INhap;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Utils.Function;
import Topping.QLTopping;
import Topping.Topping;

@SuppressWarnings("resource")
public abstract class NuocUong implements INhap {
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
        // Khởi tạo số thứ tự ban đầu cho từng loại
        typeCounter.put("TS", 0);
        typeCounter.put("CF", 0);
        typeCounter.put("ST", 0);
        typeCounter.put("TC", 0);
    }
    // Đường dẫn File để get ID tự động cho nước uống
    File toppingFile = new File("../File/topping.txt");

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

    // ========================================[Getter - Setter]========================================
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


    // ========================================[Bolean Method]========================================
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

    // ========================================[Override Method]========================================
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
                System.out.print("Chọn loại đồ uống: ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("Vui lòng không để trống !");
                    continue;
                } else {
                    if (Function.isTrueNumber(str)) {
                        number = Integer.parseInt(str);
                        if (number < 1 || number > 4) {
                            System.out.println("Vui lòng chọn trong khoảng phạm vi từ 1 đến 4 !");
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
                        System.out.println("Vui lòng nhập số !");
                    }
                }
            }
        }
        System.out.println("[Notice] Loại đồ uống hiện tại: " + this.drinkType);
        while (true) {
            System.out.println("Bạn muốn nhập ID hay tạo ID tự động ?");
            System.out.println("1. [Tự động]");
            System.out.println("2. [Nhập ID]");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Vui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    if (str.equals("1")) {
                        if (typeCounter.containsKey(this.drinkType)) {
                            int currentCount = typeCounter.get(this.drinkType) + 1;
                            typeCounter.put(this.drinkType, currentCount);
                            this.id = this.drinkType + currentCount;
                            System.out.println("[Notice] ID đồ uống hiện tại: " + this.id);
                            break;
                        } else if (str.equals("2")) {
                            while (true) {
                                System.out.print("Nhập ID: ");
                                str = sc.nextLine();
                                if (Function.isEmpty(str)) {
                                    System.out.println("Vui lòng không để trống !");
                                } else {
                                    if (Function.isTrueNumber(str)) {
                                        this.id = str;
                                        System.out.println("[Notice] ID đồ uống hiện tại: " + this.id);
                                        break;
                                    }
                                    else {
                                        System.out.println("Vui lòng nhập số");
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Vui lòng nhập 1 hoặc 2 !");
                    }
                } else {
                    System.out.println("Vui lòng nhập số !");
                }
            }
        }
        while (true) {
            System.out.print("Nhập tên đồ uống: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Vui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    System.out.println("Tên đồ uống không được là số !");
                } else {
                    this.name = str;
                    break;
                }
            }
        }
        System.out.println("[Notice] Tên đồ uống hiện tại: " + this.name);
        while (true) {
            System.out.print("Đồ uống có uống lạnh được không ?");
            System.out.println("1. [Yes]: Có");
            System.out.println("2. [No]: Không");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Vui lòng không để trống !");
            } else {
                if (str.equals("1")) {
                    this.isCold = true;
                    break;
                } else if (str.equals("2")) {
                    this.isCold = false;
                    break;
                } else {
                    System.out.println("Vui lòng nhập Y hoặc N !");
                }
            }
        }
        System.out.println("[Notice] Đồ uống uống lạnh được không: " + this.isCold);
        while (true) {
            System.out.print("Đồ uống có uống nóng được không ?");
            System.out.println("1. [Yes]: Có");
            System.out.println("2. [No]: Không");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Vui lòng không để trống !");
            } else {
                if (str.equals("1")) {
                    this.isHot = true;
                    break;
                } else if (str.equals("2")) {
                    this.isHot = false;
                    break;
                } else {
                    System.out.println("Vui lòng nhập Y hoặc N !");
                }
            }
        }
        while (true) {
            System.out.println("Đồ uống có thêm sữa được không ?");
            System.out.println("1. [Yes]: Có");
            System.out.println("2. [No]: Không");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Vui lòng không để trống !");
            } else {
                if (str.equals("1")) {
                    this.isMilk = true;
                    break;
                } else if (str.equals("2")) {
                    this.isMilk = false;
                    break;
                } else {
                    System.out.println("Vui lòng nhập Y hoặc N !");
                }
            }
        }
        while (true) {
            System.out.println("Đồ uống có thêm đường được không ?");
            System.out.println("1. [Yes]: Có");
            System.out.println("2. [No]: Không");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Vui lòng không để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    System.out.println("Vui lòng nhập Y hoặc N !");
                } else {
                    if (str.equals("1")) {
                        this.isSugar = true;
                        break;
                    } else if (str.equals("2")) {
                        this.isSugar = false;
                        break;
                    } else {
                        System.out.println("Vui lòng nhập Y hoặc N !");
                    }
                }
            }
        }
        while (true) {
            System.out.println("Chọn sản phẩm có mấy loại size");
            System.out.println("1. [3 size]: S, M, L");
            System.out.println("2. [4 size]: S, M, L, XL");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Vui lòng không để trống !");
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
                    System.out.println("Vui lòng nhập 1 hoặc 2 !");
                }
            }
        }
        // Nhập giá cho từng size
        for (Map.Entry<String, Integer> entry : this.sizePrice.entrySet()) {
            while (true) {
                System.out.print("Nhập giá cho size " + entry.getKey() + ": ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("Vui lòng không để trống !");
                } else {
                    if (!Function.isTrueNumber(str)) {
                        System.out.println("Giá phải là số và phải lớn hơn 0");
                    } else {
                        number = Integer.parseInt(str);
                        this.sizePrice.put(entry.getKey(), number);
                        System.out.println("[Notice] Giá cho size " + entry.getKey() + " là: "
                                + Function.formatMoney(number + ""));
                        break;
                    }
                }
            }
        }
        // Hỏi và nhập topping
        // TP1|Trân châu trắng|3000
        while (true) {
            System.out.println("Loại đồ uống này có topping hay không ?");
            System.out.println("1. [Yes]: Có");
            System.out.println("2. [No]: Không");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Vui lòng không để trống !");
            } else {
                if (str.equals("1")) {
                    action = true;
                    break;
                } else if (str.equals("2")) {
                    action = false;
                    break;
                } else {
                    System.out.println("Vui lòng nhập 1 hoặc 2 !");
                }
            }
        }
        if (action) {
            while (true) {
                System.out.print("Nhập số lượng topping: ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("Vui lòng không để trống !");
                } else {
                    if (Function.isTrueNumber(str)) {
                        number = Integer.parseInt(str);
                        System.out.println("[Notice] Loại đồ uống " + this.drinkType + " có " + number + " topping");
                        action = false;
                        break;
                    } else {
                        System.out.println("Vui lòng nhập số và số phải lớn hơn 0");
                    }
                }
            }
        }

        if (number > 0) {
            QLTopping list = new QLTopping();
            list.Init();
            for (int i = 0; i < number; i++) {
                while (true) {
                    list.menuTable();
                    System.out.println("Nhập ID topping thứ " + (i + 1) + "(Nhập số): ");
                    str = sc.nextLine();
                    if (Function.isEmpty(str)) {
                        System.out.println("Vui lòng không để trống !");
                    }
                    else {
                        if (Function.isTrueNumber(str)) {
                            String result = "TP" + str;
                            for (Topping tp : list.getToppingList()) {
                                if (tp.getId().equals(result)) {
                                    this.topping.add(result);
                                    System.out.println("[Notice] Topping " + tp.getName() + " đã được thêm vào");
                                    break;
                                }
                            }
                            System.out.println("ID không tồn tại !");
                        }
                        else {
                            System.out.println("Vui lòng nhập số !");
                        }
                    }
                }
            }
        }

    }

    public boolean askYesNo(String luaChon) {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            System.out.println(luaChon);
            System.out.println("1. Có");
            System.out.println("2. Không");
            System.out.print("Nhập lựa chọn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Vui lòng không để trống!");
            } else if (!Function.isTrueNumber(str)) {
                System.out.println("Vui lòng nhập số 1 hoặc 2!");
            } else if (str.equals("1")) {
                return true;
            } else if (str.equals("2")) {
                return false;
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // Abstract method
    public void suaThongTin() {
        
    }




}
