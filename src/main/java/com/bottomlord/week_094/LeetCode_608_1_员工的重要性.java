package com.bottomlord.week_094;

import com.bottomlord.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2021/5/1 14:17
 */
public class LeetCode_608_1_员工的重要性 {
    public int getImportance(List<Employee> employees, int id) {
        if (employees == null || employees.size() == 0) {
            return 0;
        }

        Map<Integer, Integer> importanceMap = employees.stream().collect(Collectors.toMap(e -> e.id, e -> e.importance, (x,y) -> y));
        Map<Integer, List<Integer>> subOrdinatesMap = employees.stream().collect(Collectors.toMap(e -> e.id, e -> e.subordinates, (x,y) -> y));
        int[] ans = new int[1];
        dfs(id, subOrdinatesMap, importanceMap, ans);
        return ans[0];
    }

    private void dfs(Integer id, Map<Integer, List<Integer>> subOrdinatesMap, Map<Integer, Integer> importanceMap, int[] ans) {
        ans[0] += importanceMap.get(id);

        List<Integer> sList = subOrdinatesMap.get(id);
        if (sList == null) {
            return;
        }

        for (Integer sId : sList) {
            dfs(sId, subOrdinatesMap, importanceMap, ans);
        }
    }
}
