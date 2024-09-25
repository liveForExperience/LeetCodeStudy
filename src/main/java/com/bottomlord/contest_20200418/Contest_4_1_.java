package com.bottomlord.contest_20200418;

/**
 * @author ChenYue
 * @date 2020/4/18 16:27
 */
public class Contest_4_1_ {
    int min = Integer.MAX_VALUE;
    public int minJump(int[] jump) {
        min = jump.length;
        dfs(jump, 0, 0);
        return min;
    }

    private void dfs(int[] jump, int num, int count) {
        if (count >= min || num < 0) {
            return;
        }

        if (num >= jump.length) {
            min = count;
        }

        if (jump[num] == 0) {
            return;
        }

        dfs(jump, num - jump[num], count + 1);
        dfs(jump, num + jump[num], count + 1);
    }
}
