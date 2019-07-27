package com.bottomlord.week_3;

/**
 * @author LiveForExperience
 * @date 2019/7/27 20:46
 */
public class LeetCode_985_1_查询后的偶数和 {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] ans = new int[queries.length];
        int total = 0;
        for (int num : A) {
            if (num % 2 == 0) {
                total += num;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int val = queries[i][0];
            int index = queries[i][1];
            int aNum = A[index];

            A[index] += val;

            if ((val & 1) == 0 && (aNum & 1) == 0) {
                total += val;
            } else if ((val & 1) == 1 && (aNum & 1) == 1) {
                total += (aNum + val);
            } else if ((val & 1) == 1 && (aNum & 1) == 0) {
                total -= aNum;
            }

            ans[i] = total;
        }

        return ans;
    }

    public static void main(String[] args) {
        LeetCode_985_1_查询后的偶数和 test = new LeetCode_985_1_查询后的偶数和();
        test.sumEvenAfterQueries(new int[]{1,2,3,4}, new int[][]{{1,0},{-3,1},{-4,0},{2,3}});
    }
}
