package jbm2.model.util;

import java.awt.Point;
import java.util.Random;

public class Util {
    private static Random random;
    
    public static Point getPuntoRandom(Point max){
        Point p = new Point();
        
        random = new Random();
        
        p.x = random.nextInt(max.x+1);
        p.y = random.nextInt(max.y+1);
        
        return p;
    }
}
