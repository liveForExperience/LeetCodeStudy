package com.bottomlord.week_070;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/11/10 9:05
 */
public class LeetCode_331_1_验证二叉树的前序序列化 {
    public boolean isValidSerialization(String preorder) {
        String[] elements = preorder.split(",");
        int slots = 1;
        for (String element : elements) {
            slots--;

            if (slots < 0) {
                return false;
            }

            if (!Objects.equals(element, "#")) {
                slots += 2;
            }
        }
        return slots == 0;
    }
}
