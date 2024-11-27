/**
 *
 */
package NuocUong;

public class MilkTea extends DrinkingWater {
    private int price;
    private String size;

    public MilkTea() {
        this.price = 0;
        this.size = "";
    }

    public MilkTea(int price, String size) {
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
}
