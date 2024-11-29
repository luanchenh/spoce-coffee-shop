/**
 *
 */
package NuocUong;

import Utils.Function;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Smoothie extends DrinkingWater{
    private Map<String, Integer> sizePrice;
    private ArrayList<String> topping;
    private boolean isMilk;
    private boolean isSugar;

    private static int idSmoothie = 0;
    private static String[] sizeThree = { "S", "M", "L" };
    private static String[] sizeFour = { "S", "M", "L", "XL" };

    // Constructor
    public Smoothie() {
        idSmoothie++;
        this.setIdWater("SM" + idSmoothie);
        this.setNameWater("");
        this.sizePrice = new LinkedHashMap<>();
        this.topping = new ArrayList<>();
        this.isMilk = false;
        this.isSugar = false;
    }

    public Smoothie(String idWater, String nameWater, Map<String, Integer> sizePrice, ArrayList<String> topping, boolean isMilk, boolean isSugar) {
        this.setIdWater(idWater);
        this.setNameWater(nameWater);
        this.sizePrice = sizePrice;
        this.topping = topping;
        this.isMilk = isMilk;
        this.isSugar = isSugar;
    }

    // Getter
    public Map<String, Integer> getSizePrice() {
        return sizePrice;
    }
    public ArrayList<String> getTopping() {
        return topping;
    }
    public boolean isMilk() {
        return isMilk;
    }
    public boolean isSugar() {
        return isSugar;
    }

    // Setter
    public void setSizePrice(Map<String, Integer> sizePrice) {
        this.sizePrice = sizePrice;
    }
    public void setTopping(ArrayList<String> topping) {
        this.topping = topping;
    }
    public void setMilk(boolean isMilk) {
        this.isMilk = isMilk;
    }
    public void setSugar(boolean isSugar) {
        this.isSugar = isSugar;
    }


    @Override
    void setInfo() {
        Scanner sc = new Scanner(System.in);
        this.setIdWater("SM" + idSmoothie);
        String str;
        int number = 0;
        // Thêm thông tin
        System.out.println("[SetInfo] Thêm thông tin cho [Sinh tố] có ID: " + this.getIdWater());
        while (true) {
            System.out.print("[Input] Nhập tên sinh tố: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("[Warning] Tên sinh tố không được để trống!");
            } else {
                if (Function.isTrueNumber(str)) {
                    System.out.println("[Warning] Tên sinh tố không được là số!");
                }
                else {
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
        // Thêm topping
        boolean isHasTopping = false;
        while (true) {
            System.out.println("[1]. Có topping");
            System.out.println("[2]. Không có topping");
            System.out.print("[Input] Chọn có topping hay không: ");
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
                            isHasTopping = true;
                            break;
                        } else {
                            isHasTopping = false;
                            break;
                        }
                    }
                }
            }
        }
        if (isHasTopping) {
            File file = new File("../File/Topping.txt");
            Map<String, String> toppingList = new LinkedHashMap<>();
            try (Scanner scFile = new Scanner(file)) {
                while (scFile.hasNextLine()) {
                    String[] data = scFile.nextLine().split("\\|");
                    if (data.length == 3) { // Đảm bảo mỗi dòng có 3 phần: ID, tên, và giá
                        toppingList.put(data[0], data[1]);
                    } else {
                        System.out.println("[Cảnh báo] Dòng dữ liệu không đúng định dạng: " + scFile.nextLine());
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("[Lỗi] Không tìm thấy file: " + e.getMessage());
            }

            // In ra danh sách topping
            int index = 1;
            System.out.println("========== [Danh sách topping] ==========");
            System.out.printf("%-5s %-15s %-15s%n", "STT", "Mã Topping", "Tên Topping"); // Header
            System.out.println("-----------------------------------------");

            for (Map.Entry<String, String> entry : toppingList.entrySet()) {
                System.out.printf("%-5d %-15s %-15s%n", index++, entry.getKey(), entry.getValue());
            }
            System.out.println("=========================================");
            // Thêm topping
            while (true) {
                System.out.print("[Input] Nhập số lượng topping: ");
                str = sc.nextLine();
                if (Function.isEmpty(str)) {
                    System.out.println("[Warning] Số lượng topping không được để trống!");
                }
                else {
                    if (!Function.isTrueNumber(str)) {
                        System.out.println("[Warning] Số lượng topping phải là số!");
                    }
                    else {
                        if (Integer.parseInt(str) > toppingList.size() || Integer.parseInt(str) < 1) {
                            System.out.println("[Warning] Số lượng topping không hợp lệ!");
                        }
                        else {
                            number = Integer.parseInt(str);
                            break;
                        }
                    }
                }
            }
            // Có được số lượng topping
            for (int i = 0; i < number; i++) {
                while (true) {
                    System.out.print("[Input] Nhập mã topping thứ " + (i + 1) + ": ");
                    str = sc.nextLine();
                    if (Function.isEmpty(str)) {
                        System.out.println("[Warning] Mã topping không được để trống!");
                    }
                    else {
                        if (!toppingList.containsKey(str)) {
                            System.out.println("[Warning] Mã topping không tồn tại!");
                        }
                        else {
                            // Kiểm tra mã topping đã tồn tại chưa
                            if (this.topping.contains(str)) {
                                System.out.println("[Warning] Topping đã tồn tại!");
                            }
                            else {
                                // Thêm cái mã topping vào danh sách topping
                                this.topping.add(str);
                                break;
                            }
                        }
                    }
                }
            }
        }
        // Hỏi có điều chỉnh được sữa đặc
        while (true) {
            System.out.println("[1]. Có sữa đặc ");
            System.out.println("[2]. Không có sữa đặc");
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
        // Hỏi có điều chỉnh được đường không
        while (true) {
            System.out.println("[1]. Có đường");
            System.out.println("[2]. Không có đường");
            System.out.print("[Input] Chọn thể điều chỉnh đường hay không: ");
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
                            this.isSugar = true;
                            break;
                        } else {
                            this.isSugar = false;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("[Notice] Đã thêm thông tin sinh tố thành công!");
    }
    @Override
    void displayInfo() {
        System.out.println("========== [Thông tin sinh tố] ==========");
        System.out.println("[ID]: " + this.getIdWater());
        System.out.println("[Tên sinh tố]: " + this.getNameWater());
        System.out.println("[Size và giá tiền]");
        for (Map.Entry<String, Integer> entry : this.sizePrice.entrySet()) {
            System.out.println("Size: " + entry.getKey() + " - Giá: " + Function.formatMoney(entry.getValue() + ""));
        }
        System.out.println("[Topping]");
        if (this.topping.size() == 0) {
            System.out.println("Không có topping");
        } else {
            for (int i = 0; i < this.topping.size(); i++) {
                System.out.println("Topping " + (i + 1) + ": " + this.topping.get(i));
            }
        }
        System.out.println("[Sinh tố có thể điều chỉnh sữa đặc]: " + (this.isMilk ? "Có" : "Không"));
        System.out.println("[Sinh tố có thể điều chỉnh đường]: " + (this.isSugar ? "Có" : "Không"));
        System.out.println("=========================================");
    }
    @Override
    void modifyInfo() {
    }

    public String madeString() {
        StringBuilder str = new StringBuilder();
        str.append(this.getIdWater()).append("|");
        str.append(this.getNameWater()).append("|");
        for (Map.Entry<String, Integer> entry : this.sizePrice.entrySet()) {
            str.append(entry.getValue()).append(",");
        }
        str.deleteCharAt(str.length() - 1);
        str.append("|");
        if (this.topping.size() < 1) {
            str.append("null").append("|");
        }
        else {
            for (String topping : this.topping) {
                str.append(topping).append(",");
            }
        }
        str.deleteCharAt(str.length() - 1);
        str.append("|");
        if (this.isMilk) {
            str.append("1").append("|");
        } else {
            str.append("0").append("|");
        }
        if (this.isSugar) {
            str.append("1");
        } else {
            str.append("0");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Smoothie smoothie = new Smoothie();
        smoothie.setInfo();
        smoothie.displayInfo();
        System.out.println(smoothie.madeString());
    }


}
