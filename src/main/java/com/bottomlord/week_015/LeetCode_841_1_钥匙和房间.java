package com.bottomlord.week_015;

import java.util.List;

public class LeetCode_841_1_钥匙和房间 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] ans = new boolean[rooms.size()];
        dfs(0, rooms, ans);
        for (int i = 1; i < ans.length; i++) {
            if (!ans[i]) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int room, List<List<Integer>> list, boolean[] ans) {
        if (ans[room]) {
            return;
        }

        for (Integer key : list.get(room)) {
            ans[key] = true;
            dfs(key, list, ans);
        }
    }
}
