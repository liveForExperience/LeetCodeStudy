package com.bottomlord.week_076;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/12/24 8:47
 */
public class LeetCode_403_1_青蛙过河 {
    public boolean canCross(int[] stones) {
        if (stones[1] - stones[0] != 1) {
            return false;
        }

        return recurse(0, 1, stones, new HashMap<>());
    }

    private boolean recurse(int preIndex, int curIndex, int[] stone, Map<String, Boolean> memo) {
        if (curIndex == stone.length - 1) {
            return true;
        }

        if (curIndex >= stone.length) {
            return false;
        }

        if (memo.containsKey(preIndex + " " + curIndex)) {
            return memo.get(preIndex + " " + curIndex);
        }

        int diff = stone[curIndex] - stone[preIndex];
        for (int i = curIndex + 1; i < stone.length; i++) {
            int curDiff = stone[i] - stone[curIndex];
            if (curDiff < diff - 1) {
                continue;
            }

            if (curDiff == diff - 1 || curDiff == diff || curDiff == diff + 1) {
                boolean curResult = recurse(curIndex, i, stone, memo);
                memo.put(curDiff + " " + i, curResult);

                if (curResult) {
                    return true;
                }
            }

            if (curDiff > diff + 1) {
                break;
            }
        }

        memo.put(preIndex + " " + curIndex, false);
        return false;
    }
}
