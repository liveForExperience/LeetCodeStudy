# [LeetCode_743_网络延迟时间](https://leetcode-cn.com/problems/network-delay-time/)
## 解法
### 思路
bfs
- 通过map构建有向图，图内容包含边的出度顶点和耗时
- 初始化一个map，用来记录到达某个顶点的最小耗时映射关系
- 通过优先级队列进行bfs搜索，排序规则为耗时越短的顶点越靠前，也就是以耗时为标准的小顶堆，初始放入k顶点，耗时定为0
- 每次搜索到一个顶点，就放入map中进行记录，然后基于当前搜索到的顶点，到图中找到出度，进行下一步的bfs，在放入队列时，将当前顶点的耗时与图中记录的耗时相加，作为该出度的整体耗时，因为是小顶堆，所以该耗时一定是所有路径中最小的
- 在搜索过程中，因为只要记录最小的耗时，所以将搜索到的顶点到map中进行判断，如果已经记录过，就抛弃
- 搜索结束后，查看map中的元素个数与题目中给出的是否一样多，如果不是就是-1，否则就返回map中的耗时的最大值
### 代码
```java
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> edges = new HashMap<>();
        for (int[] time : times) {
            edges.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        Map<Integer, Integer> memo = new HashMap<>();
        queue.offer(new int[]{k, 0});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (memo.containsKey(node[0])) {
                continue;
            }

            memo.put(node[0], node[1]);

            if (edges.containsKey(node[0])) {
                for (int[] edge : edges.get(node[0])) {
                    queue.offer(new int[]{edge[0], edge[1] + node[1]});
                }
            }
        }

        return memo.size() == n ? Collections.max(memo.values()) : -1;
    }
}
```
# [LeetCode_581_最短无序连续子数组](https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/)
## 解法
### 思路
- 正向遍历数组，寻找最大值：
  - 如果遍历过程是升序的（包含等于最大值的情况），那就不断更新最大值
  - 如果遍历中发现小于最大值的元素，就将其坐标暂存，记为不符合升序规则的最右侧元素
  - 如上过程一直到遍历结束
- 反向遍历数组，寻找最小值，探索过程和寻找最大值一致，目的是找到不符合升序规则的最左侧元素
- 最终返回最左和最右元素之间定义的区间的长度
### 代码
```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length, end = -1, max = nums[0], start = -1, min = nums[n - 1];
        for (int i = 1; i < n; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                end = i;
            }
        }
        
        if (end == -1) {
            return 0;
        }
        
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= min) {
                min = nums[i];
            } else {
                start = i;
            }
        }
        
        return end - start + 1;
    }
}
```
# [LeetCode_1426_数元素](https://leetcode-cn.com/problems/counting-elements/)
## 解法
### 思路
- set统计后计数
### 代码
```java
class Solution {
    public int countElements(int[] arr) {
        Set<Integer> set = new HashSet<>();

        for (int num : arr) {
            set.add(num);
        }
        
        int ans = 0;
        for (int num : arr) {
            if (set.contains(num + 1)) {
                ans++;
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
使用数组替换解法一的set
### 代码
```java
class Solution {
    public int countElements(int[] arr) {
        boolean[] bucket = new boolean[1002];
        for (int num : arr) {
            bucket[num] = true;
        }
        
        int ans = 0;
        for (int num : arr) {
            if (bucket[num + 1]) {
                ans++;
            }
        }
        return ans;
    }
}
```
# [LeetCode_1427_字符串的左右移](https://leetcode-cn.com/problems/perform-string-shifts/)
## 解法
### 思路
- 遍历shift，中和所有的操作，最后得出一个最终的移动结果
- 根据移动要求移动字符串s得到结果
### 代码
```java
class Solution {
    public String stringShift(String s, int[][] shift) {
        int l = 0, r = 0;
        for (int[] sh : shift) {
            if (sh[0] == 0) {
                l += sh[1];
            } else {
                r += sh[1];
            }
        }

        int direction = 0, move = Math.abs(l - r) % s.length();
        if (l == r) {
            return s;
        } else if (r > l) {
            direction = 1;
        }

        StringBuilder sb = new StringBuilder();
        if (direction == 0) {
            for (int i = move; i < s.length(); i++) {
                sb.append(s.charAt(i));
            }

            for (int i = 0; i < move; i++) {
                sb.append(s.charAt(i));
            }
        } else {
            for (int i = s.length() - move; i < s.length(); i++) {
                sb.append(s.charAt(i));
            }

            for (int i = 0; i < s.length() - move; i++) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}
```
# [LeetCode_611_有效三角形的个数](https://leetcode-cn.com/problems/valid-triangle-number/)
## 解法
### 思路
- 排序
- 嵌套遍历+二分
### 代码
```java
class Solution {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int head = j, tail = n - 1;
                while (head <= tail) {
                    int mid = head + (tail - head) / 2;
                    if (nums[mid] >= nums[i] + nums[j]) {
                        tail = mid - 1;
                    } else {
                        head = mid + 1;
                    }
                }
                
                if (head > j) {
                    ans += head - j - 1;
                }
            }
        }
        return ans;
    }
}
```
## 解法二
### 思路
排序+双指针
- 对数组排序，使数组升序排列
- 3层循环，最外层确定三角形边的较大边
- 内层循环确定次大边，同时在确定次大边的同时，也确定最小边的值
- 最小边的值一定是从0开始递增，直到小于次大边的最大值，在这个过程中，如果遇到不满足条件的就进行递增，直到第一个满足条件的最小边，然后计算次小边和最小边之间的距离，得到在当前较大边和次大边确定的条件下，有多少种可能
- 循环结束，返回累加的结果
### 代码
```java

```