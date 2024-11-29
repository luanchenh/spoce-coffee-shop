/**
 *
 */
package NuocUong;

import Utils.Function;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Coffee extends DrinkingWater {
    private Map<String, Integer> sizePrice;
    private boolean isColdIce;
    private boolean isHot;
    private boolean isMilk;

    private static int idCoffee = 0;
    private static String[] sizeThree = { "S", "M", "L" };
    private static String[] sizeFour = { "S", "M", "L", "XL" };

    // Constructor
    public Coffee() {
        idCoffee++;
        this.setIdWater("CF" + idCoffee);
        this.setNameWater("");
        this.sizePrice = new LinkedHashMap<>();
        this.isColdIce = false;
        this.isHot = false;
        this.isMilk = false;
    }

    public Coffee(Map<String, Integer> sizePrice, boolean isColdIce, boolean isHot, boolean isMilk) {
        this.sizePrice = sizePrice;
        this.isColdIce = isColdIce;
        this.isHot = isHot;
        this.isMilk = isMilk;
    }

    // Getter
    public Map<String, Integer> getSizePrice() {
        return sizePrice;
    }

    public boolean isColdIce() {
        return isColdIce;
    }

    public boolean isHot() {
        return isHot;
    }

    public boolean isMilk() {
        return isMilk;
    }

    // Setter
    public void setSizePrice(Map<String, Integer> sizePrice) {
        this.sizePrice = sizePrice;
    }

    public void setColdIce(boolean coldIce) {
        isColdIce = coldIce;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public void setMilk(boolean milk) {
        isMilk = milk;
    }

    @Override
    void setInfo() {
        Scanner sc = new Scanner(System.in);
        this.setIdWater("CF" + idCoffee);
        String str;
        int number = 0;
        // Thêm thông tin
        System.out.println("[SetInfo] Thêm thông tin cho [Coffee] có ID: " + this.getIdWater());
        while (true) {
            System.out.print("[Input] Nhập tên cà phê: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Tên cà phê không được để trống!");
            } else {
                if (Function.isTrueNumber(str)) {
                    System.out.println("[Warning] Tên cà phê không được là số!");
                } else {
                    this.setNameWater(Function.normalizeName(str));
                    break;
                }
            }
        }
        while (true) {
            System.out.println("[1]. Có 3 Size: S, M, L");
            System.out.println("[2]. Có 4 Size: S, M, L, XL");
            System.out.print("[Input] Chọn loại size: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Lựa chọn không được để trống!");
            } else {
                if (!Function.isTrueNumber(str)) {
                    System.out.println("[Warning] Lựa chọn phải là số!");
                } else {
                    if (Integer.parseInt(str) > 2 || Integer.parseInt(str) < 1) {
                        System.out.println("[Warning] Lựa chọn không hợp lệ!");
                    } else {
                        number = Integer.parseInt(str);
                        break;
                    }
                }
            }
        }
        if (number == 1) {
            for (int i = 0; i < sizeThree.length; i++) {
                while (true) {
                    System.out.print("[Input] Nhập giá tiền cho size " + sizeThree[i] + ": ");
                    str = sc.nextLine();
                    if (Function.isEmpty(str)) {
                        System.out.println("[Warning] Giá tiền của size " + sizeThree[i] + " không được để trống!");
                    } else {
                        if (!Function.isTrueNumber(str)) {
                            System.out.println("[Warning] Giá tiền của size " + sizeThree[i] + " phải là số!");
                        } else {
                            System.out.println("[Notice] Size " + sizeThree[i] + " có giá: " + Function.formatMoney(str));
                            number = Integer.parseInt(str);
                            this.sizePrice.put(sizeThree[i], number);
                            break;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < sizeFour.length; i++) {
                while (true) {
                    System.out.print("[Input] Nhập giá tiền cho size " + sizeFour[i] + ": ");
                    str = sc.nextLine();
                    if (Function.isEmpty(str)) {
                        System.out.println("[Warning] Giá tiền của size " + sizeFour[i] + " không được để trống!");
                    } else {
                        if (!Function.isTrueNumber(str)) {
                            System.out.println("[Warning] Giá tiền của size " + sizeFour[i] + " phải là số!");
                        } else {
                            System.out.println("[Notice] Size " + sizeFour[i] + " có giá: " + Function.formatMoney(str));
                            number = Integer.parseInt(str);
                            this.sizePrice.put(sizeFour[i], number);
                            break;
                        }
                    }
                }
            }
        }
        // Cà phê không có topping

        // Hỏi xem có phải cà phê có điều chỉnh đá không
        while (true) {
            System.out.println("[1]. Có đá");
            System.out.println("[2]. Không có đá");
            System.out.print("[Input] Chọn thể điều chỉnh đá hay không: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Lựa chọn không được để trống!");
            } else {
                if (!Function.isTrueNumber(str)) {
                    System.out.println("[Warning] Lựa chọn phải là số!");
                } else {
                    number = Integer.parseInt(str);
                    if (number > 2 || number < 1) {
                        System.out.println("[Warning] Lựa chọn không hợp lệ!");
                    } else {
                        if (number == 1) {
                            this.isColdIce = true;
                            break;
                        } else {
                            this.isColdIce = false;
                            break;
                        }
                    }
                }
            }
        }
        // Hỏi xem có phải cà phê có làm nóng được không
        while (true) {
            System.out.println("[1]. Có thể làm nóng");
            System.out.println("[2]. Không thể làm nóng");
            System.out.print("[Input] Chọn thể có thể làm nóng hay không: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Lựa chọn không được để trống!");
            } else {
                if (!Function.isTrueNumber(str)) {
                    System.out.println("[Warning] Lựa chọn phải là số!");
                } else {
                    number = Integer.parseInt(str);
                    if (number > 2 || number < 1) {
                        System.out.println("[Warning] Lựa chọn không hợp lệ!");
                    } else {
                        if (number == 1) {
                            this.isHot = true;
                            break;
                        } else {
                            this.isHot = false;
                            break;
                        }
                    }
                }
            }
        }
        // Hỏi xem có phải cà phê có sữa không
        while (true) {
            System.out.println("[1]. Có sữa");
            System.out.println("[2]. Không có sữa");
            System.out.print("[Input] Chọn thể có sữa hay không: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Lựa chọn không được để trống!");
            } else {
                if (!Function.isTrueNumber(str)) {
                    System.out.println("[Warning] Lựa chọn phải là số!");
                } else {
                    number = Integer.parseInt(str);
                    if (number > 2 || number < 1) {
                        System.out.println("[Warning] Lựa chọn không hợp lệ!");
                    } else {
                        if (number == 1) {
                            this.isMilk = true;
                            break;
                        } else {
                            this.isMilk = false;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("[Notice] Đã thêm thông tin cà phê thành công!");
    }

    @Override
    void displayInfo() {
        System.out.println("========== [Thông tin cà phê] ==========");
        System.out.println("[ID]: " + this.getIdWater());
        System.out.println("[Tên cà phê]: " + this.getNameWater());
        System.out.println("[Size và giá tiền]");
        for (Map.Entry<String, Integer> entry : this.sizePrice.entrySet()) {
            System.out.println("Size: " + entry.getKey() + " - Giá: " + Function.formatMoney(entry.getValue() + ""));
        }
        System.out.println("[Cà phê có thể điều chỉnh đá]: " + (this.isColdIce ? "Có" : "Không"));
        System.out.println("[Cà phê có thể làm nóng]: " + (this.isHot ? "Có" : "Không"));
        System.out.println("[Cà phê có sữa]: " + (this.isMilk ? "Có" : "Không"));
        System.out.println("=========================================");
    }

    @Override
    void modifyInfo() {
    }

    // Tạo chuỗi để lưu vào file
    public String madeString() {
        StringBuilder str = new StringBuilder();
        str.append(this.getIdWater()).append("|");
        str.append(this.getNameWater()).append("|");
        for (Map.Entry<String, Integer> entry : this.sizePrice.entrySet()) {
            str.append(entry.getValue()).append(",");
        }
        str.deleteCharAt(str.length() - 1);
        str.append("|");
        if (this.isColdIce) {
            str.append("1").append("|");
        } else {
            str.append("0").append("|");
        }
        if (this.isHot) {
            str.append("1").append("|");
        } else {
            str.append("0").append("|");
        }
        if (this.isMilk) {
            str.append("1");
        } else {
            str.append("0");
        }
        return str.toString();
    }

}
