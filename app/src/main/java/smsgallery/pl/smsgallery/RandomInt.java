package smsgallery.pl.smsgallery;

import java.util.Random;

public class RandomInt {
    Random r = new Random();
    public Integer RandomInt(int Low,int High){
        return High - r.nextInt(High-Low);
    }
}
