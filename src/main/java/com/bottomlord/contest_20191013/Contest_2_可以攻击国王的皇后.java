package com.bottomlord.contest_20191013;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Contest_2_可以攻击国王的皇后 {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] queen : queens) {
            recurse(queen[0], queen[1] + 1, 0, 1, queens, king, ans, queen);
            recurse(queen[0], queen[1] - 1, 0, -1, queens, king, ans, queen);
            recurse(queen[0] + 1, queen[1], 1, 0, queens, king, ans, queen);
            recurse(queen[0] - 1, queen[1], -1, 0, queens, king, ans, queen);
            recurse(queen[0] + 1, queen[1] + 1, 1, 1, queens, king, ans, queen);
            recurse(queen[0] - 1, queen[1] + 1, -1, 1, queens, king, ans, queen);
            recurse(queen[0] + 1, queen[1] - 1, 1, -1, queens, king, ans, queen);
            recurse(queen[0] - 1, queen[1] - 1, -1, -1, queens, king, ans, queen);
        }

        return ans;
    }

    private void recurse(int x, int y, int dx, int dy, int[][] queens, int[] kings, List<List<Integer>> ans, int[] queen) {
        if (x >= 8 || x < 0 || y >= 8 || y < 0) {
            return;
        }

        for (int[] q : queens) {
            if (q[0] == x && q[1] == y) {
                return;
            }
        }

        if (kings[0] == x && kings[1] == y) {
            List<Integer> list = new ArrayList<>();
            list.add(queen[0]);
            list.add(queen[1]);
            ans.add(list);
            return;
        }

        recurse(x + dx, y + dy, dx, dy, queens, kings, ans, queen);
    }
}
