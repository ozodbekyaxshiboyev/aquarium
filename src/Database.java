import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    public static Set<Fish> fishes = ConcurrentHashMap.newKeySet();
    public static int minLife = 10;
    public static int maxLife = 20;
    public static int aqWidth = 5;
    public static int aqHeigth = 3;
    public static int canMeet = 2;
    public static int maxCount = 30;
    public static int beginMinCount = 6;
    public static int beginMaxCount = 8;
    public static long waitTime = 2000;

}
