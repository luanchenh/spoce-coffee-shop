package Topping;

import java.util.Scanner;

import File.NuocUong.DrinkingWater;

public class Topping {
    private static int toppingCount = 0;
    private String idTopping;
    private String nameTopping;
    private double priceTopping;
    private String typeWater;


    public Topping() {
        toppingCount++;
        this.idTopping = "TP" + toppingCount;
    }

    public Topping(String nameTopping, double priceTopping, String typeWater) {
        this();
        this.nameTopping = nameTopping;
        this.priceTopping = priceTopping;
        this.typeWater = typeWater;

    }

    public String getIdTopping() {
        return idTopping;
    }

    public String getNameTopping() {
        return nameTopping;
    }

    public void setNameTopping(String nameTopping) {
        this.nameTopping = nameTopping;
    }

    public double getPriceTopping() {
        return priceTopping;
    }

    public void setPriceTopping(double priceTopping) {
        this.priceTopping = priceTopping;
    }

    public String getTypeWater() {
        return typeWater;
    }

    public void setTypeWater(String typeWater) {
        this.typeWater = typeWater;
    }

    

    public void setInfo(String nameTopping, double priceTopping, String typeWater ) {
        this.nameTopping = nameTopping;
        this.priceTopping = priceTopping;
        this.typeWater = typeWater;
    }

    public void displayTopping() {
        System.out.println("---Thông tin của món thêm (Topping):");
        System.out.println("ID Topping: " + this.idTopping);
        System.out.println("Tên món thêm: " + this.nameTopping);
        System.out.println("Giá: " + this.priceTopping + " VND");
        System.out.println("Loại nước phù hợp: " + this.typeWater);

    }
    

    public void modifyInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------SỬA THÔNG TIN CỦA MÓN THÊM------");
        
        while (true) {
            System.out.println("   Bạn có các lựa chọn:");
            System.out.println("     1: Sửa 1 thông tin");
            System.out.println("     2: Sửa tất cả thông tin");
            System.out.println("---------------------------------");
            System.out.print("Mời bạn nhập: ");
            String select = sc.nextLine();
    
            while (select.trim().isEmpty()) {
                System.out.println("Thông tin nhập không được rỗng");
                System.out.print("Mời bạn nhập lại: ");
                select = sc.nextLine();
            }
    
            if (select.equals("1")) {
                System.out.println("Chọn thông tin cần sửa:");
                System.out.println("1: Sửa tên món thêm");
                System.out.println("2: Sửa giá món thêm");
                System.out.println("3: Sửa loại nước phù hợp");
                System.out.print("Mời bạn nhập lựa chọn: ");
                String option = sc.nextLine();
    
                while (option.trim().isEmpty()) {
                    System.out.println("Thông tin nhập không được rỗng");
                    System.out.print("Mời bạn nhập lại: ");
                    option = sc.nextLine();
                }
    
                if (option.equals("1")) {
                    System.out.print("Nhập tên món thêm mới: ");
                    this.nameTopping = sc.nextLine();
                } else if (option.equals("2")) {
                    System.out.print("Nhập giá món thêm mới: ");
                    this.priceTopping = Double.parseDouble(sc.nextLine());
                } else if (option.equals("3")) {
                    System.out.print("Nhập loại nước phù hợp mới (Coffee, MilkTea, Smoothie, FruitJuice): ");
                    this.typeWater = sc.nextLine();
                } else {
                    System.out.println("Lựa chọn không hợp lệ.");
                }
                break;
    
            } else if (select.equals("2")) {
                System.out.print("Nhập tên món thêm mới: ");
                this.nameTopping = sc.nextLine();
                System.out.print("Nhập giá món thêm mới: ");
                this.priceTopping = Double.parseDouble(sc.nextLine());
                System.out.print("Nhập loại nước phù hợp mới (Coffee, MilkTea, Smoothie, FruitJuice): ");
                this.typeWater = sc.nextLine();

                break;
                
            } else {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
    }


