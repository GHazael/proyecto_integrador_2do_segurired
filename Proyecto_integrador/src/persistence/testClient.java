package persistence;

public class testClient {
    public static void main(String[] args) {
        Clients newc = new Clients();
        newc.connect();
        newc.setUserId(5);
        newc.setCalle("Allende");
        newc.setColonia("Emiliano Zapata");
        newc.setEstado("Nayarit");
        newc.setMunicipio("Tepic");
        newc.save();
        newc.disconnect();

    }
}
