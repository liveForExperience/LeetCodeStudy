package com.bottomlord.week_134;

/**
 * @author chen yue
 * @date 2022-02-01 11:04:19
 */
public class LeetCode_LCS01_1_下载插件 {
    public int leastMinutes(int n) {
       return (int)Math.ceil(Math.log(n) / Math.log(2)) + 1;
    }
}
