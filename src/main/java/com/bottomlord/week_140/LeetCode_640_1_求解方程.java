package com.bottomlord.week_140;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-03-19 15:07:48
 */
public class LeetCode_640_1_求解方程 {
    public String solveEquation(String equation) {
        int leftX = 0, rightX = 0, leftNum = 0, rightNum = 0;
        Set<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("=");

        char[] cs = equation.toCharArray();
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < cs.length; i++) {
            StringBuilder sb = new StringBuilder();
            while (i < cs.length && !operators.contains("" + cs[i])) {
                sb.append(cs[i++]);
            }

            tokens.add(sb.toString());

            if (i < cs.length && cs[i] != ' ') {
                tokens.add("" + cs[i]);
            }
        }

        boolean findEq = false;
        String preOperator = null;
        for (String token : tokens) {
            if (isX(token)) {
                if (preOperator == null) {
                    if (findEq) {
                        rightX += countX(token);
                    } else {
                        leftX += countX(token);
                    }
                } else {
                    if (Objects.equals(preOperator, "+")) {
                        if (findEq) {
                            rightX += countX(token);
                        } else {
                            leftX += countX(token);
                        }
                    } else {
                        if (findEq) {
                            rightX -= countX(token);
                        } else {
                            leftX -= countX(token);
                        }
                    }
                }

                continue;
            }

            if (isEq(token)) {
                findEq = true;
                preOperator = null;
                continue;
            }

            if (isNum(token, operators)) {
                if (preOperator == null) {
                    if (findEq) {
                        rightNum += Integer.parseInt(token);
                    } else {
                        leftNum += Integer.parseInt(token);
                    }
                } else {
                    if (Objects.equals(preOperator, "+")) {
                        if (findEq) {
                            rightNum += Integer.parseInt(token);
                        } else {
                            leftNum += Integer.parseInt(token);
                        }
                    } else {
                        if (findEq) {
                            rightNum -= Integer.parseInt(token);
                        } else {
                            leftNum -= Integer.parseInt(token);
                        }
                    }
                }

                continue;
            }

            preOperator = token;
        }

        int xNum = leftX - rightX, num = rightNum - leftNum;
        if (xNum == 0) {
            return num == 0 ? "Infinite solutions" : "No solution";
        }

        return "x=" + (num / xNum);
    }

    private boolean isX(String token) {
        return token.contains("x");
    }

    private boolean isEq(String token) {
        return Objects.equals(token, "=");
    }

    private boolean isNum(String token, Set<String> operators) {
        return !operators.contains(token) && !token.contains("x");
    }

    private int countX(String token) {
        if (token.length() == 1) {
            return 1;
        }
        return Integer.parseInt(token.substring(0, token.length() - 1));
    }
}
