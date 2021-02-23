package com.bottomlord.week_085;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/2/23 8:37
 */
public class LeetCode_464_1_我能赢吗 {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }

        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }

        int[] state = new int[maxChoosableInteger + 1];

        return backTrack(desiredTotal, state, new HashMap<>());
    }

    private boolean backTrack(int desiredTotal, int[] state, Map<String, Boolean> memo) {
        String key = Arrays.toString(state);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        for (int i = 1; i < state.length; i++) {
            if (state[i] == 0) {
                state[i] = 1;
                if (desiredTotal - i <= 0 || !backTrack(desiredTotal - i, state, memo)) {
                    memo.put(key, true);
                    state[i] = 0;
                    return true;
                }
                state[i] = 0;
            }
        }

        memo.put(key, false);
        return false;
    }
}
