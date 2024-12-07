/**
 *
 */
package Utils;

import java.util.Scanner;
import Utils.Function;

@SuppressWarnings("resource")
public class Address {
    private String houseNumer;
    private String wardName;
    private String cityName;
    private Province province;

    public Address() {
        this.province = new Province();
    }
    public Address(String houseNumer, String wardName, String cityName, Province province) {
        this.houseNumer = houseNumer;
        this.wardName = wardName;
        this.cityName = cityName;
        this.province = province;
    }

    // Getter -Setter
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public void setHouseNumer(String houseNumer) {
        this.houseNumer = houseNumer;
    }
    public void setProvince(Province province) {
        this.province = province;
    }
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
    public String getCityName() {
        return cityName;
    }
    public String getHouseNumer() {
        return houseNumer;
    }
    public Province getProvince() {
        return province;
    }
    public String getWardName() {
        return wardName;
    }

    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        String str;
        // Nhập số nhà
        while (true) {
            System.out.print("\tNhập số nhà: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tDữ liệu không được để trống !");
            } else {
                if (Function.isTrueNumber(str)) {
                    this.houseNumer = str;
                    break;
                } else {
                    System.out.println("\tDữ liệu nhập vào không phải là số !");
                }
            }
        }
        while (true) {
            System.out.print("\tNhập tên phường: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tDữ liệu không được để trống !");
            } else {
                this.wardName = str;
                break;
            }
        }
        while (true) {
            System.out.print("\tNhập quận: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("\tDữ liệu không được để trống !");
            } else {
                this.cityName = str;
                break;
            }
        }

        this.province.setInfo();
    }

    public String makeString() {
        return this.houseNumer +","+ this.wardName +","+ this.cityName +","+ this.province.getCityCode();
    }
    @Override
    public String toString() {
        return "Số nhà: "+ this.houseNumer +", Phường: "+ this.wardName +", Quận: "+ this.cityName +", Tỉnh: "+ this.province.getCityName();
    }


}
