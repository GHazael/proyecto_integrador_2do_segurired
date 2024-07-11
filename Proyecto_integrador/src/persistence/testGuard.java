package persistence;

import java.util.List;

public class testGuard {
    public static void main(String[] args) {
        Guards newG = new Guards();

        newG.connect();
//        newG.updateField("HORAS_LABORADAS",4,3);
//        List<Guards> x = newG.getAllGuards("SELECT * from GUARDS;");
//        for (Guards g : x) {
//            System.out.println(g.getUserId());
//        }
        newG.setUserId(6);
        newG.saveGuard();
        newG.disconnect();
    }
}
