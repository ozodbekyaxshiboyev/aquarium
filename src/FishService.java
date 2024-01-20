
public class FishService {

    public static void createFishes() {
        int startFishesCount = Utils.getStartFishesCount();
        for (int i = 0; i < startFishesCount; i++) {
            newFish();
        }
    }


    public synchronized static void meetIfPresent(Fish fish) {  //aslida butun aquariumni emas qaysidir point(X,Y) ni bloklash kerak
                                                        // va baliqlarni to`xtati turish kerak menimcha
        if (fish.isInterrupted()) return;
        if (fishesCount() >= Database.maxCount) return;                              // akvariumga sig`adimi
        if (fish.getAge() < Database.canMeet) return;                                // balog`atga yetganmi
        for (Fish other : Database.fishes) {
            if (fish.isInterrupted()) return;
            if (fish.getName().equals(other.getName())) continue;

            if (fish.getX() != other.getX() || fish.getY() != other.getY()) continue; //same place
            if (fish.getGender() != other.getGender()) continue;                       //another gender
            if (other.getAge() < Database.canMeet) continue;                           // enough age

            long now = System.currentTimeMillis();
            if (other.getLastMeet() + Database.waitTime > now) {
                System.out.println("          " + other.getName() + " - boshqa bilan yaqinda kurishgan :(:(:(");
                System.out.println("                               Hozirgi vaqt   :: " + now);
                System.out.println("                               Kurishgan vaqt :: " + other.getLastMeet());
                System.out.println("                                  Ushbu baliq :: " + fish.getName());
                continue;                                                               // didn`t meet now, this may issue
            }

            fish.setLastMeet(now);
            other.setLastMeet(now);
            newFish(fish, other);
            break;
        }
    }


    private static void newFish(Fish fish1, Fish other) {
        Fish fish = new Fish(
                Utils.getLifeSize(),
                Utils.getGenderType(),
                Utils.getCordX(),
                Utils.getCordY());
        fish.start();
        addFish(fish);
        System.out.println(":):):) Baliqcha to`g`ildi: " + fish + " | ota-onasi ( " + fish1.getName() + " va " + other.getName() + " )");
        showStatistics();
    }

    private static void newFish() {
        Fish fish = new Fish(
                Utils.getLifeSize(),
                Utils.getGenderType(),
                Utils.getCordX(),
                Utils.getCordY());
        fish.start();
        addFish(fish);
        System.out.println(":):):) Baliqcha to`g`ildi: " + fish);
        showStatistics();
    }

    public synchronized static void addFish(Fish fish) {
        Database.fishes.add(fish);
    }

    public synchronized static void removeFish(Fish fish) {
        Database.fishes.remove(fish);
    }


    public synchronized static int fishesCount() {
        return Database.fishes.size();
    }

    public synchronized static long getGenderCount(int gender) {
        return Database.fishes
                .stream()
                .filter(fish -> fish.getGender() == gender)
                .count();
    }

    public synchronized static void showStatistics() {
//        showAsGUI();
        System.out.println("Fishes count::: " + fishesCount());
        System.out.println("Male fishes count::: " + getGenderCount(1));
        System.out.println("Female fishes count::: " + getGenderCount(0));
        System.out.println("          ::::::     ");
    }

    public synchronized static void showAsGUI() {
        int width = Database.aqWidth;
        int heigth = Database.aqHeigth;
        for (int i = heigth - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                System.out.print(getAsStr(getFishCountInCord(j, i)));
            }
            System.out.println();
        }
    }

    public static int getFishCountInCord(int x, int y) {
        int count = 0;
        for (Fish fish : Database.fishes) {
            if (fish.getX() == x && fish.getY() == y) count++;
        }
        return count;
    }

    public static String getAsStr(int count) {
        switch (count) {
            case 0 -> {
                return " - ";
            }
            case 1 -> {
                return " 1 ";
            }
            case 2 -> {
                return " 2 ";
            }
            case 3 -> {
                return " 3 ";
            }
            case 4 -> {
                return " 4 ";
            }
            case 5 -> {
                return " 5 ";
            }
            case 6 -> {
                return " 6 ";
            }
            case 7 -> {
                return " 7 ";
            }
            case 8 -> {
                return " 8 ";
            }
            default -> {
                return " 9 ";
            }
        }
    }


}
