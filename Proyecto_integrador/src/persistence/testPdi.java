package persistence;

public class testPdi {
    public static void main(String[] args) {
        PDI pdi = new PDI();
        pdi.connect();

        pdi.setClientId(1);
        pdi.setLatitud(20.678420878500873);
        pdi.setLongitud(-103.34204875121281);
        pdi.save();

        pdi.disconnect();
    }
}
