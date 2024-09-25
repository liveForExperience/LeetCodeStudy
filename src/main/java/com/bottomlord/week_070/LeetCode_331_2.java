package com.bottomlord.week_070;

/**
 * @author ChenYue
 * @date 2020/11/12 8:42
 */
public class LeetCode_331_2 {
    public boolean isValidSerialization(String preorder) {
        int slots = 1, len = preorder.length();
        for (int i = 0; i < len; i++) {
            if (preorder.charAt(i) == ',') {
                slots--;

                if (slots < 0) {
                    return false;
                }

                if (preorder.charAt(i - 1) != '#') {
                    slots += 2;
                }
            }
        }

        if (preorder.charAt(len - 1) == ',') {
            return false;
        }

        slots = preorder.charAt(len - 1) == '#' ? slots - 1 : slots + 1;
        return slots == 0;
    }
}
