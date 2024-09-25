package com.bottomlord.week_094;

import com.bottomlord.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2021/5/1 14:33
 */
public class LeetCode_608_2 {
    public int getImportance(List<Employee> employees, int id) {
        if (employees == null || employees.size() == 0) {
            return 0;
        }

        return dfs(id,
                employees.stream().collect(Collectors.toMap(e -> e.id, e -> e.importance, (x, y) -> y)),
                employees.stream().collect(Collectors.toMap(e -> e.id, e -> e.subordinates, (x, y) -> y)));
    }

    private int dfs(Integer id, Map<Integer, Integer> iMap, Map<Integer, List<Integer>> sMap) {
        int sum = iMap.getOrDefault(id, 0);

        List<Integer> sList = sMap.getOrDefault(id, new ArrayList<>());
        for (Integer sId : sList) {
            sum += dfs(sId, iMap, sMap);
        }

        return sum;
    }
}
