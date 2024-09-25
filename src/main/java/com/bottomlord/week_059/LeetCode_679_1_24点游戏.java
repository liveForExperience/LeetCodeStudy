package com.bottomlord.week_059;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2020/8/22 23:04
 */
public class LeetCode_679_1_24点游戏 {
    private static final int TARGET = 24;
    private static final double EPSILON = 1e-6;
    private static final int ADD = 0, MULTI = 1, SUBTRACT = 2, DIVIDE = 3;
    public boolean judgePoint24(int[] nums) {
        return doJudge(Arrays.stream(nums).mapToDouble(x -> x).boxed().collect(Collectors.toList()));
    }

    private boolean doJudge(List<Double> list) {
        if (list.size() == 1) {
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }

                List<Double> tmp = new LinkedList<>();
                for (int k = 0; k < list.size(); k++) {
                    if (k != i && k != j) {
                        tmp.add(list.get(k));
                    }
                }

                for (int k = 0; k < 4; k++) {
                    if (k < 2 && i > j) {
                        continue;
                    }

                    if (k == ADD) {
                        tmp.add(list.get(i) + list.get(j));
                    } else if (k == MULTI) {
                        tmp.add(list.get(i) * list.get(j));
                    } else if (k == SUBTRACT) {
                        tmp.add(list.get(i) - list.get(j));
                    } else {
                        if (list.get(j) < EPSILON) {
                            continue;
                        }

                        tmp.add(list.get(i) / list.get(j));
                    }

                    if (doJudge(tmp)) {
                        return true;
                    }

                    tmp.remove(tmp.size() - 1);
                }
            }
        }

        return false;
    }
}
