package com.bottomlord.week_104;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * @author ChenYue
 * @date 2021/7/5 8:15
 */
public class LeetCode_726_1_原子的数量 {
    private String formula;
    private int i, n;

    public String countOfAtoms(String formula) {
        this.formula = formula;
        this.i = 0;
        this.n = formula.length();

        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new HashMap<>());

        while (i < n) {
            if (formula.charAt(i) == '(') {
                i++;
                stack.push(new HashMap<>());
            } else if (formula.charAt(i) == ')') {
                i++;
                int num = parseNum();
                Map<String, Integer> popMap = stack.pop(), topMap = stack.peek();
                for (Map.Entry<String, Integer> entry : popMap.entrySet()) {
                    String atom = entry.getKey();
                    topMap.put(atom, topMap.getOrDefault(atom, 0) + entry.getValue() * num);
                }
            } else {
                String atom = parseAtom();
                int num = parseNum();
                Map<String, Integer> topMap = stack.peek();
                topMap.put(atom, topMap.getOrDefault(atom, 0) + num);
            }
        }

        TreeMap<String, Integer> treeMap = new TreeMap<>(stack.peek());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            sb.append(entry.getKey());
            if (entry.getValue() > 1) {
                sb.append(entry.getValue());
            }
        }
        return sb.toString();
    }

    private int parseNum() {
        if (i == n || !Character.isDigit(formula.charAt(i))) {
            return 1;
        }

        int num = 0;
        while (i < n && Character.isDigit(formula.charAt(i))) {
            num = num * 10 + (formula.charAt(i) - '0');
            i++;
        }
        return num;
    }

    private String parseAtom() {
        StringBuilder sb = new StringBuilder();
        sb.append(formula.charAt(i++));
        while (i < n && Character.isLowerCase(formula.charAt(i))) {
            sb.append(formula.charAt(i++));
        }
        return sb.toString();
    }
}
