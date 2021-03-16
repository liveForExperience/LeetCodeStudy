package com.bottomlord.week_088;

import java.util.Random;

/**
 * @author ChenYue
 * @date 2021/3/16 8:32
 */
public class Robot {
    public boolean move() {
        Random r = new Random();
        return r.nextInt() % 2 == 0;
    }

    public void turnLeft() {

    };

    public void turnRight() {

    }

    public void clean() {

    }
}
