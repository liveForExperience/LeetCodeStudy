package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-19 21:09:34
 */
public class LeetCode_1678_2 {
    public String interpret(String command) {
        return command.replaceAll("\\(\\)", "o").replaceAll("\\(al\\)", "al");
    }
}
