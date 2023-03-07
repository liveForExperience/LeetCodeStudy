package com.bottomlord.week_191;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-03-07 10:46:06
 */
public class LeetCode_1092_2 {
    public List<String> braceExpansionII(String expression) {
        Stack<Character> opStack = new Stack<>();
        Stack<TreeSet<String>> stack = new Stack<>();

        char[] cs = expression.toCharArray();
        for (int i = 0; i < cs.length;) {
            String token = getNextToken(expression, i);

            if (Objects.equals(token, "{")) {
                if (i == 0) {
                    opStack.push('{');
                } else {
                    if (cs[i - 1] != ',' && cs[i - 1] != '{') {
                        opStack.push('*');
                        opStack.push('{');
                    } else {
                        opStack.push('{');
                    }
                }
            } else if (Objects.equals(token, ",")) {
                while (!opStack.isEmpty() && opStack.peek() == '*') {
                    opStack.pop();
                    TreeSet<String> b = stack.pop(), a = stack.pop(), c = new TreeSet<>();
                    for (String as : a) {
                        for (String bs : b) {
                            c.add(as + bs);
                        }
                    }
                    stack.push(c);
                }
                opStack.push('+');
            } else if (Objects.equals(token, "}")) {
                while (!opStack.isEmpty()) {
                    char op = opStack.pop();
                    if (op == '{') {
                        break;
                    } else if (op == '+') {
                        TreeSet<String> b = stack.pop(), a = stack.pop();
                        a.addAll(b);
                        stack.push(a);
                    } else {
                        TreeSet<String> b = stack.pop(), a = stack.pop(), c = new TreeSet<>();
                        for (String as : a) {
                            for (String bs : b) {
                                c.add(as + bs);
                            }
                        }
                        stack.push(c);
                     }
                }
            } else {
                if (i != 0 && cs[i - 1] == '}') {
                    opStack.push('*');
                }

                stack.push(new TreeSet<>());
                stack.peek().add(token);
            }

            i += token.length();
        }

        while (!opStack.isEmpty()) {
            char op = opStack.pop();
            if (op == '+') {
                TreeSet<String> b = stack.pop(), a = stack.pop();
                a.addAll(b);
                stack.push(a);
            } else if (op == '*') {
                TreeSet<String> b = stack.pop(), a = stack.pop(), c = new TreeSet<>();
                for (String as : a) {
                    for (String bs : b) {
                        c.add(as + bs);
                    }
                }
                stack.push(c);
            }
        }

        if (stack.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(stack.pop());
    }

    private String getNextToken(String expression, int index) {
        StringBuilder sb = new StringBuilder();
        char[] cs = expression.toCharArray();
        for (int i = index; i < cs.length; i++) {
            if (cs[i] == '{') {
                if (sb.length() == 0) {
                    sb.append(cs[i]);
                }
                break;
            } else if (cs[i] == '}') {
                if (sb.length() == 0) {
                    sb.append(cs[i]);
                }
                break;
            } else if (cs[i] == ',') {
                if (sb.length() == 0) {
                    sb.append(cs[i]);
                }
                break;
            } else {
                sb.append(cs[i]);
            }
        }

        return sb.toString();
    }
}