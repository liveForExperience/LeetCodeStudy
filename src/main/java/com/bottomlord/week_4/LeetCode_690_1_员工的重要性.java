package com.bottomlord.week_4;

import com.bottomlord.Employee;

import java.util.*;

/**
 * @author LiveForExperience
 * @date 2019/8/4 13:33
 */
public class LeetCode_690_1_员工的重要性 {
    public int getImportance(List<Employee> employees, int id) {
        int ans = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(id);

        while (!queue.isEmpty()) {
            int len = queue.size();
            while (len-- > 0) {
                Integer eId = queue.poll();
                Employee employee = null;

                Iterator<Employee> iterator = employees.iterator();
                while (iterator.hasNext()) {
                    Employee e = iterator.next();
                    if (Objects.equals(e.id, eId)) {
                        employee = e;
                        iterator.remove();
                        break;
                    }
                }

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
