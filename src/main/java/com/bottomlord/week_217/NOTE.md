# [LeetCode_207_课程表](https://leetcode.cn/problems/course-schedule/)
## 解法
### 思路
拓扑排序
### 代码
```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] edges = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }

        int[] inDegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            edges[prerequisite[1]].add(prerequisite[0]);
            inDegrees[prerequisite[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int out = queue.poll();
            numCourses--;

            for (Integer in : edges[out]) {
                inDegrees[in]--;

                if (inDegrees[in] == 0) {
                    queue.offer(in);
                }
            }
        }

        return numCourses == 0;
    }
}
```
# [LeetCode_210_课程表II](https://leetcode.cn/problems/course-schedule-ii)
## 解法
### 思路
拓扑排序时同时记录队头元素
### 代码
```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses], inDegrees = new int[numCourses];
        List<Integer>[] edges = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int[] prerequisite : prerequisites) {
            edges[prerequisite[1]].add(prerequisite[0]);
            inDegrees[prerequisite[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            int out = queue.poll();
            ans[index++] = out;
            numCourses--;

            for (Integer in : edges[out]) {
                inDegrees[in]--;

                if (inDegrees[in] == 0) {
                    queue.offer(in);
                }
            }
        }

        return numCourses == 0 ? ans : new int[0];
    }
}
```