package com.bottomlord.week_085;

/**
 * @author ChenYue
 * @date 2021/2/23 8:43
 */
public class LeetCode_464_2 {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }

        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }

        Boolean[] states = new Boolean[1 << maxChoosableInteger - 1];
        return backTrack(states, 0, maxChoosableInteger, desiredTotal);
    }

    private boolean backTrack(Boolean[] states, int state, int maxChoosableInteger, int desiredTotal) {
        if (states[state] != null) {
            return states[state];
        }

        for (int i = 1; i <= maxChoosableInteger; i++) {
            int cur = 1 << (i - 1);
            if ((cur & state) == 0) {
                if (desiredTotal - i <= 0 || !backTrack(states, state | cur, maxChoosableInteger, desiredTotal - i)) {
                    states[state] = true;
                    return true;
                }
            }

        }

        states[state] = false;
        return false;
    }
}
