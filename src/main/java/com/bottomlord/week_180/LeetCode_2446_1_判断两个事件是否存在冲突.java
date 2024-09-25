package com.bottomlord.week_180;

/**
 * @author chen yue
 * @date 2022-12-25 19:59:19
 */
public class LeetCode_2446_1_判断两个事件是否存在冲突 {
    public boolean haveConflict(String[] event1, String[] event2) {
        int s1 = getTime(event1[0]), e1 = getTime(event1[1]),
                s2 = getTime(event2[0]), e2 = getTime(event2[1]);

        return (s2 >= s1 && s2 <= e1) || (e2 >= s1 && e2 <= e1) ||
                (s1 >= s2 && s1 <= e2) || (e1 >= s2 && e1 <= e2);
    }

    private int getTime(String time) {
        String[] factors = time.split(":");
        return Integer.parseInt(factors[0]) * 60 + Integer.parseInt(factors[1]);
    }
}
