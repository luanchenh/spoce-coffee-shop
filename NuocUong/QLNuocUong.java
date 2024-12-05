/**
 *
 */
package NuocUong;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class QLNuocUong {
    ArrayList<NuocUong> waterList;

    File waterFile = new File("../File/water.txt");

    public QLNuocUong() {
        this.waterList = new ArrayList<>();
    }

    public QLNuocUong(ArrayList<NuocUong> waterList) {
        this.waterList = waterList;
    }





}
