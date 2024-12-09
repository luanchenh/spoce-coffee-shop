/**
 *
 */
package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Province {
    private String provinceCode;
    private String provinceName;


    public Province() {
    }

    public Province(String provinceCode) {
        this.provinceCode = provinceCode;
        // Get tên từ file
        File cityFile = new File("./File/tinhthanh.txt");
        try(Scanner rd = new Scanner(cityFile)) {
            while (rd.hasNextLine()) {
                String line = rd.nextLine();
                String[] parts = line.split("\\|");
                if (parts[0].equals(this.provinceCode)) {
                    this.provinceName = parts[1];
                    break;
                }
            }
        }
        catch(Exception e) {
            System.out.println("Lỗi: "+ e.getMessage());
        }
    }
    // Getter - Setter
    public Province(String cityCode, String cityName) {
        this.provinceCode = cityCode;
        this.provinceName = cityName;
    }

    public void setCityCode(String cityCode) {
        this.provinceCode = cityCode;
    }
    public void setCityName(String cityName) {
        this.provinceName = cityName;
    }
    public String getCityCode() {
        return provinceCode;
    }
    public String getCityName() {
        return provinceName;
    }

    public void printInfoProvince() {
        File cityFile = new File("../File/tinhthanh.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(cityFile))) {
            int breakLine = 0;
            String line;
            System.out.println("\t+=========================================================================================================================+");
            System.out.printf("\t| %-120s|%n", "Chọn tỉnh thành");
            System.out.printf("\t| ");
            while ((line = reader.readLine()) != null) {
                breakLine++;
                if (breakLine % 5 == 0) {
                    System.out.printf("%-20s|", line); // Căn phải với chiều dài 25 ký tự
                } else {
                    System.out.printf("%-25s", line); // Căn trái với chiều dài 25 ký tự
                }
                if (breakLine % 5 == 0) System.out.printf("\n\t| ");
            }
            System.out.printf("%-45s|", "");
            System.out.println();
            System.out.println("\t+=========================================================================================================================+");
        } catch (Exception e) {
            System.out.println("Lỗi: "+ e.getLocalizedMessage());
        }
    }
    public String getInfoProvince(int number) {
        File cityFile = new File("../File/tinhthanh.txt");
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(cityFile))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] index = line.split("\\|");
                if (Integer.parseInt(index[0]) == number) {
                    result = line;
                    break;
                }
            }
        }
        catch (Exception e) {
            System.out.println("\tLỗi: "+ e.getLocalizedMessage());
        }
        return result;
    }
    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        printInfoProvince();
        while (true) {
            System.out.print("\t=> Vui lòng nhập mã tỉnh bạn muốn chọn: ");
            String str = sc.nextLine().trim();
            if (Function.isTrueNumber(str)) {
                int number = Integer.parseInt(str);
                if (number > 0 && number < 64) {
                    String result[] = getInfoProvince(number).split("\\|");
                    this.provinceCode = result[0];
                    this.provinceName = result[1];
                    break;
                }
                else System.out.println("\tBạn đã nhập ngoài phạm vi cho phép !");
            }
            else System.out.println("\tDữ liệu nhập chưa đúng !");
        }
        System.out.println("\tChọn tỉnh thành thành công");
    }

    @Override
    public String toString() {
        return "Mã tỉnh: "+ this.provinceCode +", Tên tỉnh thành: "+ this.provinceName;
    }

    public String makeString() {
        return this.provinceCode + "|" + this.provinceName;
    }

    public static String getProvinceNameFromID(String provinceID) {
        String result = null;
        File cityFile = new File("../File/tinhthanh.txt");
        try (Scanner sc = new Scanner(cityFile)) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] arr = str.split("\\|");
                if (arr[0].equals(provinceID)) {
                    result = arr[1];
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        return result;
    }
}
