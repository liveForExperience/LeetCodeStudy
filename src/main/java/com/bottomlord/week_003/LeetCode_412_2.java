package com.bottomlord.week_003;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/24 22:06
 */
public class LeetCode_412_2 {
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            String str = "";
            if (i % 3 == 0) {
                str += "Fizz";
            }

            if (i % 5 == 0) {
                str += "Buzz";
            }

            if ("".equals(str)) {
                str += Integer.toString(i);
            }

            ans.add(str);
        }

        return ans;
    }
}
