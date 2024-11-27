package Employee;

import Order.Order;
import Utils.Address;
import Utils.Date;

public class Bartender extends Employee {
    public Order currentOrder;
    public Bartender(){
        super();
        this.currentOrder = new Order();
    }
      public Bartender(String name, String gender, String numberPhone, String email, Date birth, Address address, Order currentOder) {
       super(name, gender, numberPhone, email, birth, address);
       this.currentOrder = currentOder;
    }

    

    
    public double tinhLuongThang(){

        //Cập nhật phương thức tính lương sau
        return 1.0;
    }
    public Order getCurrentOrder() {
        return currentOrder;
    }
    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }
}
