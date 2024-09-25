package com.bottomlord.week_096;

/**
 * @author ChenYue
 * @date 2021/5/13 8:27
 */
public class LeetCode_800_1_相似的RGB颜色 {
    public String similarRGB(String color) {
        int[] nums = new int[]{0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77,
                               0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff};

        String[] strs = new String[]{"00", "11", "22", "33", "44", "55", "66", "77",
                                    "88", "99", "aa", "bb", "cc", "dd", "ee", "ff"};

        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < color.length(); i += 2) {
            int target = Integer.parseInt(color.substring(i, i + 2), 16);
            int min = Integer.MAX_VALUE;
            String ansSubStr = "";
            for (int j = 0; j < nums.length; j++) {
                int diff = Math.abs(target - nums[j]);
                if (diff < min) {
                    min = diff;
                    ansSubStr = strs[j];
                }
            }

            sb.append(ansSubStr);
        }

        return sb.toString();
    }
}
