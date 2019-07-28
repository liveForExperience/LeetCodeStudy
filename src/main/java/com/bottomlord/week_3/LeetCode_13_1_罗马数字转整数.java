package com.bottomlord.week_3;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/28 11:01
 */
public class LeetCode_13_1_罗马数字转整数 {
    public int romanToInt(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('I', ' ');
        map.put('V', 'I');
        map.put('X', 'I');
        map.put('L', 'X');
        map.put('C', 'X');
        map.put('D', 'C');
        map.put('M', 'C');

        Map<Character, Integer> romanNumMap = new HashMap<>();
        romanNumMap.put('I', 1);
        romanNumMap.put('V', 5);
        romanNumMap.put('X', 10);
        romanNumMap.put('L', 50);
        romanNumMap.put('C', 100);
        romanNumMap.put('D', 500);
        romanNumMap.put('M', 1000);

        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()) {
            stack.push(c);
        }

        int total = 0;
        while (!stack.isEmpty()) {
            char romanNum = stack.pop();

            total += romanNumMap.get(romanNum);
            if (stack.isEmpty()) {
                break;
            }

            char nextRomanNum = stack.peek();
            if (map.get(romanNum).equals(nextRomanNum)) {
                stack.pop();
                total -= romanNumMap.get(nextRomanNum);
            }
        }
        return total;
    }
}
