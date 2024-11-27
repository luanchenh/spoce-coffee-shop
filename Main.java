import java.util.Scanner;
import Utils.Function;

public class Main {
    public static void moreNewLine() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

    public static void clearScreen() {
        // Đưa con trỏ về đầu màn hình và xóa màn hình
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String str;
        int choice = 0;
        boolean isExitSystem = false;
        int secondToExitSystem = 10;

        // In giao diện menu với khung bao quanh
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║            Chào mừng đến với Spoce Coffee Shop         ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║ 1. Bạn là Admin                                        ║");
        System.out.println("║ 2. Bạn là Customer                                     ║");
        System.out.println("║ 3. Thoát chương trình                                  ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");

        while (true) {
            System.out.print("Nhập lựa chọn của bạn: ");
            str = sc.nextLine();
            if (Function.isEmpty(str)) {
                System.out.println("❗ Bạn chưa nhập dữ liệu");
            } else {
                if (!Function.isTrueNumber(str)) {
                    System.out.println("❌ Bạn nhập chưa đúng định dạng. Định dạng là 1, 2, 3");
                } else {
                    choice = Integer.parseInt(str);
                    if (choice == 3) {
                        isExitSystem = true;
                        break;
                    }
                }
            }
        }

        if (isExitSystem) {
            while (secondToExitSystem > 0) {
                clearScreen();
                System.out.println("╔════════════════════════════════════════════════════════╗");
                System.out.println("║ Bạn sẽ thoát chương trình sau " + secondToExitSystem + "s║");
                System.out.println("╚════════════════════════════════════════════════════════╝");
                Thread.sleep(1000);
                secondToExitSystem--;
            }
        }
    }
}
