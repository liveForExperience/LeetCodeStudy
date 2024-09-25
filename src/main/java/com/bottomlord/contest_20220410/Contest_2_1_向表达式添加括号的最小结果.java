package com.bottomlord.contest_20220410;

/**
 * @author chen yue
 * @date 2022-04-10 10:40:54
 */
public class Contest_2_1_向表达式添加括号的最小结果 {
    private int min;
    private String ans;
    public String minimizeResult(String expression) {
        int plusIndex = expression.indexOf("+");
        int left = plusIndex - 1, right = plusIndex + 1;

        min = Integer.parseInt(expression.substring(0, plusIndex)) + Integer.parseInt(expression.substring(plusIndex));
        ans = "(" + expression + ")";

        dfs(left, right, expression, plusIndex);

        return ans;
    }

    private void dfs(int left, int right, String expression, int plusIndex) {
        if (left < 0 || right >= expression.length()) {
            return;
        }

        int lt = left == 0 ? 1 : Integer.parseInt(expression.substring(0, left)),
                rt = right == expression.length() - 1 ? 1 : Integer.parseInt(expression.substring(right + 1)),
                lp = Integer.parseInt(expression.substring(left, plusIndex)),
                rp = Integer.parseInt(expression.substring(plusIndex + 1, right + 1));

        int sum = (lp + rp) * lt * rt;

        if (sum < min) {
            min = sum;
            ans = left == 0 && right == expression.length() - 1 ? expression :
                    expression.substring(0, left) + "(" + expression.substring(left, right + 1)
                            + ")" + expression.substring(right + 1);
        }

        dfs(left - 1, right, expression, plusIndex);
        dfs(left, right + 1, expression, plusIndex);
    }
}
