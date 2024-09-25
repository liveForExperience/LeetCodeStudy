package com.bottomlord.week_094;

import com.bottomlord.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2021/5/1 14:42
 */
public class LeetCode_608_3 {
    public int getImportance(List<Employee> employees, int id) {
        return dfs(id, employees.stream().collect(Collectors.toMap(e -> e.id, e -> e, (x, y) -> y)));
    }

    private int dfs(Integer id, Map<Integer, Employee> map) {
        Employee e = map.get(id);

        int sum = e.importance;

        if (e.subordinates == null) {
            return sum;
        }

        for (Integer sId : e.subordinates) {
            sum += dfs(sId, map);
        }
        return sum;
    }
}
