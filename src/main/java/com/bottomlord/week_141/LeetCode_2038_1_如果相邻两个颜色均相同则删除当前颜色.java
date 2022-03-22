package com.bottomlord.week_141;

/**
 * @author chen yue
 * @date 2022-03-22 09:23:28
 */
public class LeetCode_2038_1_如果相邻两个颜色均相同则删除当前颜色 {
    public boolean winnerOfGame(String colors) {
        char[] cs = colors.toCharArray();
        int aNum = 0, bNum = 0;
        for (int i = 0; i < cs.length;) {
            char c = cs[i];
            int count = 0;
            while (i < cs.length && c == cs[i]) {
                count++;
                i++;
            }

            if (c == 'A') {
                aNum += Math.max(count - 2, 0);
            } else {
                bNum += Math.max(count - 2, 0);
            }
        }

        return aNum > bNum;
    }
}
