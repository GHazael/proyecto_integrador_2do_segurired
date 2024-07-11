package persistence;

public class testUsers {
    public static void main(String[] args) {
        Users testUser = new Users();
        testUser.connect();
        testUser.setName("Jorge");
        testUser.setPassword("123456");
        testUser.setRole("GUARD");
        testUser.setF_surename("Rodrigues");
        testUser.setM_surename("Perez");
        testUser.setActive(true);
        testUser.setUsername("willy");
        testUser.save();
        testUser.disconnect();

        testUser.show();
    }
}
