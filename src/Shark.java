import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Shark extends Thread {
    public static Random random = new Random();
    private int life;
    private int X;
    private int Y;
    private int age = 0;


    @Override
    public void run() {
        while (life > 0) {
            swim();
            try {
                Thread.sleep(Database.waitTime);
            } catch (InterruptedException e) {
                System.out.println("Shark woke up interruptly?????");
//                throw new RuntimeException(e);
            }
            eatIfPresent();
            life--;
            age++;
        }

        System.out.println("        Shark Killed (baliqlar qutuldi) ::: " + this);
    }


    private void eatIfPresent() {
        if (this.age < 3) return;
        String sharkStatusStr = this.toString();
        ArrayList<Fish> fishes = new ArrayList<>();
        for (Fish fish : Database.fishes) {
            if (fish.getX() != this.getX() || fish.getY() != this.getY()) continue;
            fish.interrupt();
            FishService.removeFish(fish);
            this.life += (fish.getLife() - fish.getAge());
            fishes.add(fish);
        }
        if (fishes.size() > 0) {
            System.out.println("   >>>>>> Some fishes eaten <<<<<<  ");
            fishes.forEach(System.out::println);
            System.out.println("   >>>>>> -------------------- <<<<<<  ");
            System.out.println("Shark old status ::: " + sharkStatusStr);
            System.out.println("Shark status ::: " + this);
            FishService.showStatistics();
        }

    }


    public void swim() {
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        boolean isCorrectTurn = true;
        while (isCorrectTurn) {
            int index = random.nextInt(0, elements.size());
            int i = elements.get(index);
            switch (i) {
                case 1:
                    if (getY() < Database.aqHeigth) {
                        incrementY();
                        isCorrectTurn = false;
                    }
                    break;
                case 2:
                    if (getY() > 0) {
                        decrementY();
                        isCorrectTurn = false;
                    }
                    break;
                case 3:
                    if (getX() < Database.aqWidth) {
                        incrementX();
                        isCorrectTurn = false;
                    }
                    break;
                case 4:
                    if (getX() > 0) {
                        decrementX();
                        isCorrectTurn = false;
                    }
                    break;
            }
            elements.remove(index);
        }
    }

    public void incrementX() {
        this.X++;
    }

    public void incrementY() {
        this.Y++;
    }

    public void decrementX() {
        this.X--;
    }

    public void decrementY() {
        this.Y--;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public void setLife(int lifeSize) {
        this.life = lifeSize;
    }

    @Override
    public String toString() {
        return "Shark{" +
                "life=" + life +
                ", X=" + X +
                ", Y=" + Y +
                '}';
    }


}
