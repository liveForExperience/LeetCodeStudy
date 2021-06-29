package com.bottomlord.week_103;

/**
 * @author ChenYue
 * @date 2021/6/29 8:37
 */
public class LeetCode_1271_1_十六进制魔术数字 {
    public String toHexspeak(String num) {
        long lnum = Long.parseLong(num);
        StringBuilder sb = new StringBuilder();
        while (lnum != 0) {
            long index = lnum % 16;
            if (index < 10 && index > 1) {
                return "ERROR";
            }

            sb.insert(0, index == 0 ? 'O' : index == 1 ? 'I' : (char) ((index - 10) + 'A'));
            lnum /= 16;
        }

        return sb.toString();
    }
}
