/**
 *
 */
package NuocUong;
import Utils.Function;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Coffee extends DrinkingWater{
    private int price;
    private String size;

    public Coffee() {
        super();
        this.price = 0;
        this.size = "";
    }
    public Coffee(String nameWater, int price, String size) {
        super(nameWater);
        this.price = price;
        this.size = size;
    }

    // Getter - Setter
    public int getPrice() {
        return price;
    }
    public String getSize() {
        return size;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        String str;
        System.out.println("-------Nhập thông tin cho class Coffee-------");
        while (true) {
            System.out.print("---Nhập giá tiền của Coffee: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Bạn chưa nhập thông tin");
            }
            else {
                if (!Function.isTrueNumber(str)) {
                    System.out.println("Bạn chưa nhập đúng định dạng. Định dạng là số");
                }
                else {
                    this.price = Integer.parseInt(str);
                    break;
                }
            }
        }
        while (true) {
            System.out.println("----->Chọn loại size<-----\n1.Size M\n2.Size L\n3.Size XL");
            System.out.print("Nhập loại size: ");
        }
    }
    @Override
    public String toString() {
        return super.toString() +"\n"
              +"Giá nước uống: "+ this.price;
    }

}
