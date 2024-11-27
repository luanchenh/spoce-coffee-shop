/**
 *
 */
package NuocUong;
import Utils.Function;
import java.util.Scanner;


public class Coffee  extends DrinkingWater{
    private int price;
    private String isSuger;
    private String isColdIce;

    public Coffee() {
        super();
        this.price = 0;
        this.isSuger = "";
        this.isColdIce = "";
    }
    public Coffee(String nameWater, int price, String isSuger, String isColdIce) {
        super(nameWater);
        this.price = price;
        this.isSuger = isSuger;
        this.isColdIce = isColdIce;
    }

    // Getter - Setter
    public int getPrice() {
        return price;
    }
    public String getIsSuger() {
        return isSuger;
    }
    public String getIsColdIce() {
        return isColdIce;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void setIsSuger(String isSuger) {
        this.isSuger = isSuger;
    }
    public void setIsColdIce(String isColdIce) {
        this.isColdIce = isColdIce;
    }

    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        String str;
        System.out.println("-------Nhập thông tin cho class Coffee-------");
        while (true) {
            System.out.print("---Nhập giá tiền của Coffee: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Bạn chưa nhập thông tin vui lòng nhập lại");
            }
            else {
                if (!Function.isTrueNumber(str)) {
                    System.out.println("Bạn chưa nhập đúng định dạng");
                }
                else {
                    this.price = Integer.parseInt(str);
                }
            }
        }
    }

}
