public class Main {
    public static void main(String[] args) {
        start();

    }


    public static void start() {
        System.out.println("====== AQUARIUM =======");
        FishService.createFishes();
        SharkService.newShark();
    }
}