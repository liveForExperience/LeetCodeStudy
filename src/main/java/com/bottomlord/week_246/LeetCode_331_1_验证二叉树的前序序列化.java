package com.bottomlord.week_246;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2024-03-31 20:05:10
 */
public class LeetCode_331_1_验证二叉树的前序序列化 {
    public boolean isValidSerialization(String preorder) {
        String[] strs = preorder.split(",");
        if (strs.length == 0) {
            return false;
        }

        int cur = 1;
        for (String str : strs) {
            if (cur <= 0) {
                return false;
            }

            if (Objects.equals(str, "#")) {
                cur--;
            } else {
                cur++;
            }
        }

        return cur == 0;
    }
}
