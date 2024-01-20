import java.util.Random;

public class Utils {
    public static Random random = new Random();


    public static int getStartFishesCount() {
        return random.nextInt(Database.beginMinCount, Database.beginMaxCount + 1);
    }

    public static int getLifeSize() {
        return random.nextInt(Database.minLife, Database.maxLife + 1);
    }

    public static int getGenderType() {
        return random.nextInt(0, 2);
    }

    public static int getCordX() {
        return random.nextInt(0, Database.aqWidth);
    }

    public static int getCordY() {
        return random.nextInt(0, Database.aqHeigth);
    }

    public static int getTurn() {
        return random.nextInt(0, 5);
    }
}
