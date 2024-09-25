package com.bottomlord.week_113;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2021-09-06 10:32:16
 */
public class LeetCode_1598_1_文件夹操作日志搜集器 {
    public int minOperations(String[] logs) {
        int path = 0;
        for (String log : logs) {
            if (Objects.equals(log, "../")) {
                if (path != 0) {
                    path--;
                }
            } else if (Objects.equals(log, "./")) {

            } else {
                path++;
            }
        }

        return Math.abs(path);
    }
}
