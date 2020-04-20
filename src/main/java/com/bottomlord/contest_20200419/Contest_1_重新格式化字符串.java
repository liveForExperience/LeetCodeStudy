package com.bottomlord.contest_20200419;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/4/19 10:41
 */
public class Contest_1_重新格式化字符串 {
    public String reformat(String s) {
        List<Character> nums = new ArrayList<>(), alps = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                nums.add(c);
            } else {
                alps.add(c);
            }
        }

        if (Math.abs(nums.size() - alps.size()) > 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        if (nums.size() > alps.size()) {
            sb.append(nums.get(0));
            for (int i = 0; i < alps.size(); i++) {
                sb.append(alps.get(i));
                sb.append(nums.get(i + 1));
            }
        } else if (nums.size() < alps.size()) {
            sb.append(alps.get(0));
            for (int i = 0; i < nums.size(); i++) {
                sb.append(nums.get(i));
                sb.append(alps.get(i + 1));
            }
        } else {
            for (int i = 0; i < nums.size(); i++) {
                sb.append(nums.get(i));
                sb.append(alps.get(i));
            }
        }

        return sb.toString();
    }
}
