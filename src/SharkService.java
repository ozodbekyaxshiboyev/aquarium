
public class SharkService {

    public static void newShark() {
        Shark shark = new Shark();
        shark.setY(Utils.getCordY());
        shark.setX(Utils.getCordX());
        shark.setLife(Utils.getLifeSize());
        System.out.println("      :::::::::: New shark ::: " + shark);
        shark.start();
    }

}
