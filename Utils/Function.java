/**
 *
 */
package Utils;

import java.util.regex.Pattern;

public class Function {

    public static boolean isEmpty(String str) {
        String value = str.trim();
        return value.equals("");
    }

    public static boolean isTrueNumber(String number) {
        String value = number.trim();
        return value.matches("[0-9]+");
    }
    public static boolean isDoubleNumber(String number) {
        String value = number.trim();
        return value.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isHasSpace(String str) {
        String value = str.trim();
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == ' ') {
                return true;
            }
        }
        return false;
    }

    public static boolean isTrueEmail(String email) {
        String value = email.trim();
        String emailRegex = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, value);
    }

    public static boolean isTrueDate(String date) {
        String value = date.trim();
        String[] index = value.split("/");
        if (index.length < 2)
            return false;
        else {
            if (isTrueNumber(index[0]) && isTrueNumber(index[1]) && isTrueNumber(index[2])) {
                int day = Integer.parseInt(index[0]);
                int month = Integer.parseInt(index[1]);
                int year = Integer.parseInt(index[2]);

                // Kiểm tra
                // Do năm nay là 2024 nên độ tuổi về hưu sẽ là 52 tuổi 8 tháng (làm tròn 53
                // tuổi)
                // Nên tuổi có thể đi làm sẽ trong khoảng 1972 đến 2008
                if (year > 1972 && year < 2008) {
                    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
                            || month == 12) {
                        if (day < 0 || day > 31)
                            return false;
                    } else if (month == 4 || month == 6 || month == 0 || month == 11) {
                        if (day < 0 || day > 30)
                            return false;
                    } else if (month == 2) {
                        if (year % 4 == 0) {
                            if (day < 0 || day > 29)
                                return false;
                        } else {
                            if (day < 0 || day > 28)
                                return false;
                        }
                    } else
                        return false;
                } else
                    return false;
            } else
                return false;
        }
        return true;
    }

    public static String normalizeName(String name) {
        String value = name.trim();
        String result = "";
        String[] arrName = value.split("\\s+");
        for (String str : arrName) {
            result += str.toUpperCase().charAt(0);
            for (int i = 1; i < str.length(); i++) {
                result += str.toLowerCase().charAt(i);
            }
            result += " ";
        }
        return result.trim();
    }

    public static boolean hasVietnameseAccent(String str) {
        String value = str.trim();
        String regex = "^[a-zA-Z]+$";
        return value != null && value.matches(regex);

    }

    public static String formatMoney(String money) {
        String value = money.trim();
        String result = "";
        int count = 0;
        for (int i = value.length() - 1; i >= 0; i--) {
            result = value.charAt(i) + result;
            count++;
            if (count % 3 == 0 && i != 0) {
                result = "." + result;
            }
        }
        return result + " VNĐ";
    }

}
