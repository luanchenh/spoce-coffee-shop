/**
 *
 */
package NuocUong;
import java.util.Scanner;
import Utils.Function;

@SuppressWarnings("unused") // Dùng để không cảnh báo các attribute chưa được sử dụng trong class

public class NuocUong {
    private String id;
    private String name;

    public NuocUong() {

    }
    public NuocUong(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @SuppressWarnings("resource") // Dùng để không cảnh báo lớp Scanner chưa được giải phóng bộ nhớ
    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            System.out.print("Nhập id sản phẩm: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Bạn chưa nhập thông tin vui lòng nhập lại !");
            }
            else {
                this.id = str;
                break;
            }
        }
        while (true) {
            System.out.print("Nhập tên sản phẩm: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("Bạn chưa nhập thông tin sản phẩm vui lòng nhập lại !");
            }
            else {
                this.name = str;
                break;
            }
        }
        System.out.println("-------Nhập thông tin cơ bản cho Nước uống thành công !-------");
    }
}