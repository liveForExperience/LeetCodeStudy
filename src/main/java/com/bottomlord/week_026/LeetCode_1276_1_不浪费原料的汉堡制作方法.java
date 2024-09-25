package com.bottomlord.week_026;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/1/1 12:03
 */
public class LeetCode_1276_1_不浪费原料的汉堡制作方法 {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        if (tomatoSlices % 2 != 0 || tomatoSlices < 2 * cheeseSlices || 4 * cheeseSlices < tomatoSlices) {
            return Collections.emptyList();
        }
        return new ArrayList<Integer>(){{add(tomatoSlices / 2 - cheeseSlices);add(2 * cheeseSlices - tomatoSlices / 2);}};
    }
}
