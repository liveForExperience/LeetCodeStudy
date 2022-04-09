package com.bottomlord.week_143;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2022-04-09 14:04:09
 */
public class LeetCode_780_1_到达终点 {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }

        if (tx == sx && ty == sy) {
            return true;
        } else if (tx == sx && (ty > sy && (ty - sy) % sx == 0)) {
            return true;
        } else if (ty == sy && (tx > sx && (tx - sx) % sy == 0)) {
            return true;
        } else {
            return false;
        }
    }
}
