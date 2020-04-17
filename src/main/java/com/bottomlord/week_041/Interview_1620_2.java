package com.bottomlord.week_041;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/4/17 12:54
 */
public class Interview_1620_2 {
    public List<String> getValidT9Words(String num, String[] words) {
        int[] bucket = new int[26];
        bucket[0] = bucket[1] = bucket[2] = 2;
        bucket[3] = bucket[4] = bucket[5] = 3;
        bucket[6] = bucket[7] = bucket[8] = 4;
        bucket[9] = bucket[10] = bucket[11] = 5;
        bucket[12] = bucket[13] = bucket[14] = 6;
        bucket[15] = bucket[16] = bucket[17] = bucket[18] = 7;
        bucket[19] = bucket[20] = bucket[21] = 8;
        bucket[22] = bucket[23] = bucket[24] = bucket[25] = 9;

        List<String> ans = new ArrayList<>();

        for (String word : words) {
            boolean flag = true;
            for (int i = 0; i < word.length(); i++) {
                if (bucket[word.charAt(i) - 'a'] != num.charAt(i) - '0') {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans.add(word);
            }
        }

        return ans;
    }
}
