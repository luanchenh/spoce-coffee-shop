/**
 *
 */
package Topping;
import Utils.INhap;
import Utils.IXuat;
import Utils.Function;

public class Topping implements INhap, IXuat{
    private String id;
    private String name;
    private int price;

    private static int countID = 0;

    public Topping() {
        countID++;
        this.id = "TP" + countID;
        this.name = "";
        this.price = 0;
    }

    public Topping(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public void xuatThongTin() {
        System.out.println("ID Topping: "+ this.id);
        System.out.println("Tên Topping: "+ this.name);
        System.out.println("Giá tiền: "+ Function.formatMoney(this.price + ""));
    }

    @Override
    public void nhapThongTin() {
    }

    public String makeString() {
        return this.id +"|"+ this.name +"|"+ this.price;
    }

}
