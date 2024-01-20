import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Fish extends Thread{
    public static Random random = new Random();
    private int life;
    private int gender;
    private int X;
    private int Y;
    private int age;
    private long lastMeet;

    public Fish(int life, int gender, int x, int y) {
        this.life = life;
        this.gender = gender;
        this.X = x;
        this.Y = y;
        this.age = 0;
    }

    @Override
    public void run() {
        for (int i = 0; this.getAge() < this.getLife(); i++) {
            swim();
            this.incrementAge();
            FishService.meetIfPresent(this);
            try {
                Thread.sleep(Database.waitTime);
            } catch (InterruptedException e) {
//                System.out.println("Bu baliq yeb qo`yildi");
//                throw new RuntimeException(e);
            }
        }
        FishService.removeFish(this);
        System.out.println("        Tark etdi ::: " + this.getName());
        FishService.showStatistics();

    }


    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
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

    public long getLastMeet() {
        return lastMeet;
    }

    public void setLastMeet(long lastMeet) {
        this.lastMeet = lastMeet;
    }


    public void incrementAge() {
        this.age++;
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

    public void swim() {
//        int i = Utils.getTurn();
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        boolean isCorrectTurn = true;
        while (isCorrectTurn) {
            int index = random.nextInt(0, elements.size());
            int i = elements.get(index);
            switch (i) {
                case 1:                  //tepaga
                    if (getY() < Database.aqHeigth) {
                        incrementY();
                        isCorrectTurn = false;
                    }
                    break;
                case 2:                  //pastga
                    if (getY() > 0) {
                        decrementY();
                        isCorrectTurn = false;
                    }
                    break;
                case 3:                //o`ngga
                    if (getX() < Database.aqWidth) {
                        incrementX();
                        isCorrectTurn = false;
                    }
                    break;
                case 4:                 //chapga
                    if (getX() > 0) {
                        decrementX();
                        isCorrectTurn = false;
                    }
                    break;
            }
            elements.remove(index);
        }
    }

    @Override
    public String toString() {
        return "Fish{" +
                "name='" + getName() + '\'' +
                ", life=" + life +
                ", age=" + age +
                ", gender=" + gender +
                ", X=" + X +
                ", Y=" + Y +
                ", lastMeet=" + lastMeet +
                '}';
    }
}
