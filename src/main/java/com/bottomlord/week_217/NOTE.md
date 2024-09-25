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
# [LeetCode_2594_修车的最少时间](https://leetcode.cn/problems/minimum-time-to-repair-cars)
## 解法
### 思路
- 题目要求的时间具有单调性：
  - 如果`m`时间可以修理完所有车子，那么大于`m`的时间一定都能修理好车子
  - 如果`m`时间不可以修理完所有车子，那么小于`m`的时间一定都不能修理好车子
- 具有单调性的t时间可以通过二分查找法来加快确定`t`的速度
    - 使用int最大值作为二分查找的上界，0作为下界，二分值t作为待判断的时间
    - `m`时间能够修好的车的数量`t`：`m = r * t * t` => `t = √m / r`
    - 然后用`m`求出所有修车工能够修好的车数，累加出来如果
      - 大于等于`cars`，那么上界就确定为`m`
      - 小于`cars`，那么下界就确定为`m + 1`
    - 直到下界大于等于上界为止
    - 返回下界作为结果
### 代码
```java
class Solution {
    private int cars;
    private int[] ranks, rankMap;

    public long repairCars(int[] ranks, int cars) {
        this.cars = cars;
        this.ranks = ranks;
        this.rankMap = new int[101];

        for (int rank : ranks) {
            rankMap[rank]++;
        }

        long head = 0, tail = Long.MAX_VALUE;
        while (head < tail) {
            long m = head + (tail - head) / 2;
            if (check(m)) {
                tail = m;
            } else {
                head = m + 1;
            }
        }

        return head;
    }

    private boolean check(long m) {
        long cnt = 0;
        for (int rank : ranks) {
            cnt += (long)Math.sqrt(m / rank * 1L);
            
            if (cnt >= cars) {
                return true;
            }
        }

        return false;
    }
}
```
## 解法二
### 思路
在解法一的基础上做一些加速：
- 发现`ranks`中的元素有重复值，这些元素用于计算`m`时间内可以修的车辆数字，所以可以通过乘法来加速计算。那么也就可以不再直接遍历`ranks`数组，而是对`ranks`数组中的元素进行桶计数，然后遍历这些桶，将桶中的个数作为乘数进行计算即可
- 在`check`方法中，如果`cnt`值已经大于等于`cars`了，那么就不需要继续计算，可以直接返回`true`
### 代码
```java
class Solution {
    private int cars;
    private int[] rankMap;

    public long repairCars(int[] ranks, int cars) {
        this.cars = cars;
        this.rankMap = new int[101];

        for (int rank : ranks) {
            rankMap[rank]++;
        }

        long head = 0, tail = Long.MAX_VALUE;
        while (head < tail) {
            long m = head + (tail - head) / 2;

            if (check(m)) {
                tail = m;
            } else {
                head = m + 1;
            }
        }

        return head;
    }

    private boolean check(long m) {
        long cnt = 0;
        for (int rank = 0; rank <= 100; rank++) {
            if (rankMap[rank] == 0) {
                continue;
            }
            
            cnt += (long) rankMap[rank] * (int)Math.sqrt(m / rank);
        }

        return cnt >= cars;
    }
}
```