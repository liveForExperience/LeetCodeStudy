package com.bottomlord.contest_20220508;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-05-08 10:29:07
 */
public class Contest_3_1_统计打字方案数 {
    public int countTexts(String pressedKeys) {
        int len = pressedKeys.length();
        if (len <= 1) {
            return len;
        }

        Map<Character, Integer> board = new HashMap<>();
        board.put('2', 3);
        board.put('3', 3);
        board.put('4', 3);
        board.put('5', 3);
        board.put('6', 3);
        board.put('8', 3);
        board.put('7', 4);
        board.put('9', 4);

        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        char[] cs = pressedKeys.toCharArray();

        StringBuilder sb = new StringBuilder().append(cs[0]);
        for (int i = 1; i < cs.length; i++) {
            if (cs[i] == cs[i - 1]) {
                sb.append(cs[i]);
            } else {
                list.add(sb.toString());
                sb = new StringBuilder().append(cs[i]);
            }
        }
        list.add(sb.toString());

        long ans = 1;
        for (String s : list) {
            ans = (ans * count(s.length(), board.get(s.charAt(0)), new HashMap<>())) % 1000000007;
        }

        return (int) ans;
    }

    private static long count(int n, int len, Map<Integer, Long> memo) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return 0;
        }

        long count = 0;
        for (int i = 1; i <= len; i++) {
            int left = n - i;
            if (memo.containsKey(left)) {
                count += memo.get(left);
            } else {
                long num = count(left, len, memo);
                memo.put(left, num);
                count += num;
            }

            count %= 1000000007;
        }

        return count;
    }
}
