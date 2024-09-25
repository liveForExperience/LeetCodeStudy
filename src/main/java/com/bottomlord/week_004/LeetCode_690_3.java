package com.bottomlord.week_004;

import com.bottomlord.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiveForExperience
 * @date 2019/8/4 14:24
 */
public class LeetCode_690_3 {
    public int getImportance(List<Employee> employees, int id) {
        int ans = 0;

        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }

        return dfs(map.get(id), map);
    }

    private int dfs(Employee e, Map<Integer, Employee> map) {
        if (e == null) {
            return 0;
        }

        if (e.subordinates == null || e.subordinates.size() == 0) {
            return e.importance;
        }

        int sum = e.importance;
        for (Integer id : e.subordinates) {
            sum += dfs(map.get(id), map);
        }

        return sum;
    }
}
