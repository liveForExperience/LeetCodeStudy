package com.bottomlord.week_002;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/21 19:08
 */
public class LeetCode_1078_1_Bigram分词 {
    public String[] findOcurrences(String text, String first, String second) {
        String str = first + " " + second + " ";
        List<String> ans = new ArrayList<>();
        while (text.contains(str)) {
            text = text.substring(text.indexOf(str) + str.length());
            if (text.contains(" ")) {
                ans.add(text.substring(0, text.indexOf(" ")));
            } else {
                ans.add(text);
            }
        }
        return ans.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String a = "alice is a good girl she is a good student";
        LeetCode_1078_1_Bigram分词 test = new LeetCode_1078_1_Bigram分词();
        test.findOcurrences(a, "a", "good");
    }
}
