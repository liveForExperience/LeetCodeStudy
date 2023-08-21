package com.bottomlord.week_215;

/**
 * @author chen yue
 * @date 2023-08-21 10:45:02
 */
public class LeetCode_2337_1_移动片段得到字符串 {
    public boolean canChange(String start, String target) {
        int n = start.length(), i = 0, j = 0;
        while (i < n || j < n) {
            while (i < n && start.charAt(i) == '_') {
                i++;
            }

            while (j < n && target.charAt(j) == '_') {
                j++;
            }

            if (i < n && j >= n) {
                return false;
            }

            if (i >= n) {
                while (j < n) {
                    if (target.charAt(j++) != '_') {
                        return false;
                    }
                }

                return true;
            }

            char ci = start.charAt(i), cj = target.charAt(j);
            if (ci != cj) {
                return false;
            }

            if (ci == 'L' && i < j) {
                return false;
            }

            if (ci == 'R' && i > j) {
                return false;
            }

            i++;
            j++;
        }

        return true;
    }

    public static void main(String[] args) {
        LeetCode_2337_1_移动片段得到字符串 t = new LeetCode_2337_1_移动片段得到字符串();
        t.canChange("_L", "LL");
    }
}
