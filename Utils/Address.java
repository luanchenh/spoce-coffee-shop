/**
 *
 */
package Utils;

import java.util.Scanner;

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
        System.out.print("Nhập số nhà: "); this.houseNumer = sc.nextLine().trim();
        System.out.print("Nhập phường/xã: "); this.wardName = sc.nextLine().trim();
        System.out.print("Nhập thành quận/huyện: "); this.cityName = sc.nextLine().trim();
        this.province.setInfo();

        if (Function.isEmpty(cityName) || Function.isEmpty(wardName) || Function.isEmpty(cityName)) {
            System.out.println("Dữ liệu nhập còn thiếu sót, nhập lại dữ liệu !");
            setInfo();
        }
        else {
            System.out.println("Nhập dữ liệu địa chỉ thành công !");
        }
    }
    public String appendString() {
        return this.houseNumer +";"+ this.wardName +";"+ this.cityName +";"+ this.province.getCityCode();
    }
    @Override
    public String toString() {
        return "Số nhà: "+ this.houseNumer +", Phường: "+ this.wardName +", Quận: "+ this.cityName +", Tỉnh: "+ this.province.getCityName();
    }

}
