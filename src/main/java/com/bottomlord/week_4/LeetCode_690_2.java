package com.bottomlord.week_4;

import com.bottomlord.Employee;

import java.util.*;

/**
 * @author LiveForExperience
 * @date 2019/8/4 13:59
 */
public class LeetCode_690_2 {
    public int getImportance(List<Employee> employees, int id) {
        int ans = 0;

        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e: employees) {
            map.put(e.id, e);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(id);

        while (!queue.isEmpty()) {
            int len = queue.size();
            while (len-- > 0) {
                Integer eId = queue.poll();
                Employee employee = map.get(eId);

                if (employee == null) {
                    continue;
                }

                ans += employee.importance;

                for (Integer sId: employee.subordinates) {
                    queue.offer(sId);
                }
            }
        }

        return ans;
    }
}