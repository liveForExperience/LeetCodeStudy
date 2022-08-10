# [LeetCode_640_求解方程](https://leetcode.cn/problems/solve-the-equation/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public String solveEquation(String equation) {
        List<String> tokens = new ArrayList<>();
        Set<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("=");

        char[] cs = equation.toCharArray();

        for (int i = 0; i < cs.length; i++) {
            StringBuilder sb = new StringBuilder();
            while (i < cs.length && !operators.contains("" + cs[i])) {
                sb.append(cs[i]);
                i++;
            }

            if (sb.length() != 0) {
                tokens.add(sb.toString());
            }

            if (i < cs.length) {
                tokens.add("" + cs[i]);
            }
        }

        boolean meetEq = false;
        int x = 0, num = 0;
        String preOperator = "+";
        for (String token : tokens) {
            if (operators.contains(token)) {
                if (Objects.equals(token, "=")) {
                    meetEq = true;
                    preOperator = "+";
                    continue;
                }

                preOperator = token;
                continue;
            }

            if (!meetEq) {
                if (Objects.equals(preOperator, "+")) {
                    if (isX(token)) {
                        x += countX(token);
                    } else {
                        num -= getNum(token);
                    }
                } else {
                    if (isX(token)) {
                        x -= countX(token);
                    } else {
                        num += getNum(token);
                    }
                }
            } else {
                if (Objects.equals(preOperator, "+")) {
                    if (isX(token)) {
                        x -= countX(token);
                    } else {
                        num += getNum(token);
                    }
                } else {
                    if (isX(token)) {
                        x += countX(token);
                    } else {
                        num -= getNum(token);
                    }
                }
            }
        }

        if (x == 0) {
            return num != 0 ? "No solution" : "Infinite solutions";
        }
        num /= x;
        return "x=" + num;
    }

    private boolean isX(String token) {
        return token.contains("x");
    }

    private int getNum(String token) {
        return Integer.parseInt(token);
    }

    public int countX(String token) {
        if (token.length() == 1) {
            return 1;
        }
        return Integer.parseInt(token.substring(0, token.length() - 1));
    }
}
```