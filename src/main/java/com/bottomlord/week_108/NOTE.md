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
class Solution {
  public int triangleNumber(int[] nums) {
    int n = nums.length, ans = 0;
    Arrays.sort(nums);
    for (int i = 0; i < n; i++) {
      for (int j = i - 1, k = 0; j > 0; j--) {
        while (k < j && nums[k] + nums[j] <= nums[i]) {
          k++;
        }

        ans += j - k;
      }
    }

    return ans;
  }
}
```
# [LeetCode_802_找到最终的安全状态](https://leetcode-cn.com/problems/find-eventual-safe-states/solution/)
## 解法
### 思路
dfs+三色标记
- 如果起始节点位于环内，或者能够到达一个环，就是不安全的
- 使用dfs搜索图，同时使用三色标记法对节点进行标记：
  - 0：未访问
  - 1：在递归栈中，或者在环
  - 2：搜索完毕是安全的
- 如果第一次遍历到该节点，就标记为1，代表在递归中
- 如果搜索过程中遇到标记为1的节点，说明有环，退出搜索，同时保留1的状态，使得下次从别的起始点开始搜索的时候能够直到这个节点是在环的
- 如果搜索结束没有遇到1，则说明无环，则将当前节点标记为黑色再返回
### 代码
```java
class Solution {
  public List<Integer> eventualSafeNodes(int[][] graph) {
    int n = graph.length;
    List<Integer> ans = new ArrayList<>();
    int[] color = new int[n];
    for (int i = 0; i < n; i++) {
      if (safe(graph, color, i)) {
        ans.add(i);
      }
    }

    return ans;
  }

  private boolean safe(int[][] graph, int[] color, int x) {
    if (color[x] > 0) {
      return color[x] == 2;
    }

    color[x] = 1;
    for (int a : graph[x]) {
      if (!safe(graph, color, a)) {
        return false;
      }
    }

    color[x] = 2;
    return true;
  }
}
```
# [LeetCode_847_访问所有节点的最短路径](https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes/)
## 解法
### 思路
bfs+记忆化搜索
- 搜索过程中使用三元数组来记录：
  - 当前节点的编号
  - 二进制数组，对应所有节点被搜索的状态，全部搜索完成就是2的n次方-1
  - 累加的路径总和
- 在所有时候使用记忆化搜索的方式避免重复搜索，记录的内容就是当前节点和当前状态
- 因为是bfs，所以第一个搜索到的全部搜索过的状态，就是题目要求的答案，直接返回即可
### 代码
```java
class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length, done = (1 << n) - 1, ans = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] memo = new boolean[n][done + 1];
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, 1 << i, 0});
            memo[i][1 << i] = true;
        }

        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int x = tuple[0], mask = tuple[1], dist = tuple[2];
            
            if (mask == done) {
                ans = dist;
                break;
            }
            
            int[] next = graph[x];
            for (int y : next) {
                int maskY = mask | 1 << y;
                if (!memo[y][maskY]) {
                    queue.offer(new int[]{y, maskY, dist + 1});
                    memo[y][maskY] = true;
                }
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_457_环形数组是否存在循环](https://leetcode-cn.com/problems/circular-array-loop/)
## 解法
### 思路
- 理解题目
  - 从任意一个元素开始移动，如果在移动过程中所有遇到的元素都是同样符号，而且能够最终回到起始的节点就说明有循环
  - 在遍历过程中，如果遇到符号和起始的节点不同，或者遍历的元素个数超过了数组的长度，就说明循环不存在
- 模拟
  - 循环遍历所有元素节点，作为可能的循环的起始点
  - 每一次判断时候，暂存起始的节点元素，起始节点的符号
  - 移动的时候需要考虑收尾相连的情况，而且方向有正反，所以移动的方程是：`next = ((cur + num) % n + n) % n`
  - 移动的时候需要判断
    - 遍历次数是否超过数组长度，如果是，返回false
    - 当前元素是否与起始元素符号相同，如果是，返回false
    - 如果元素和起始元素相同，返回true
### 代码
```java
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (check(nums, i)) {
                return true;
            }
        }

        return false;
    }

    private boolean check(int[] nums, int start) {
        int cur = start, k = 0, n = nums.length;
        boolean flag = nums[cur] > 0;

        while (k < n) {
            int next = ((cur + nums[cur]) % n + n) % n;
            if (flag && nums[next] < 0) {
                return false;
            }

            if (!flag && nums[next] > 0) {
                return false;
            }

            if (next == start) {
                return k > 0;
            }

            cur = next;
            k++;
        }

        return false;
    }
}
```
## 解法二
### 思路
- 从解法一中可以发现，搜索判断的时候，很多路径是被重复检查的
- 所以可以用一个记忆数组记录当前节点是否被访问过，记忆数组中记录是第几轮被搜索过的，这样如果当前节点在记忆数组中有值，而且值代表的轮数和当前轮数相同，那就是有循环了，如果不是，那就说明之前已经处理过直接返回false
- 需要注意：和解法一不同，在判断k的退出条件时，要等k>n时才退出，因为在解法二时，需要通过memo来判断是否出现循环，所以可能需要n+1次遍历来判断，因为可能只在最后2个元素出现了循环
### 代码
```java
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n + 1];
        for (int i = 0, idx = 1; i < n; i++, idx++) {
            if (memo[i] > 0) {
                continue;
            }

            int k = 0, cur = i;
            boolean flag = nums[cur] > 0;
            while (k <= n) {
                int next = ((cur + nums[cur]) %  n + n) % n;
                if (cur == next) {
                    break;
                }

                if (flag && nums[next] < 0) {
                    break;
                }

                if (!flag && nums[next] > 0) {
                    break;
                }

                if (memo[next] > 0) {
                    if (memo[next] != idx) {
                        break;
                    }

                    return true;
                }

                k++;
                cur = next;
                memo[next] = idx;
            }
        }

        return false;
    }
}
```
# [LeetCode_1137_第N个泰波那契数](https://leetcode-cn.com/problems/n-th-tribonacci-number/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
  public int tribonacci(int n) {
    if (n <= 1) {
      return n;
    }

    if (n == 2) {
      return 1;
    }

    int a1 = 0, a2 = 1, a3 = 1;
    for (int i = 3; i<= n; i++) {
      int a4= a1 + a2 + a3;
      a1 = a2;
      a2 = a3;
      a3 = a4;
    }

    return a3;
  }
}
```
## 解法二
### 思路
状态转移方程
### 代码
```java
class Solution {
  public int tribonacci(int n) {
    if (n <= 1) {
      return n;
    }

    if (n == 2) {
      return 1;
    }

    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 1;

    for (int i = 3; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
    }

    return dp[n];
  }
}
```
## 解法二
### 思路
记忆化递归
### 代码
```java
class Solution {
    public int tribonacci(int n) {
        if (n <= 1) {
            return n;
        }
        
        if (n == 2) {
            return 1;
        }

        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0);
        memo.put(1, 1);
        memo.put(2, 1);
        return doTri(n - 1, memo) + doTri(n - 2, memo) + doTri(n - 3, memo);
    }

    private int doTri(int n, Map<Integer, Integer> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int num = doTri(n - 1, memo) + doTri(n - 2, memo) + doTri(n - 3, memo);
        memo.put(n, num);

        return num;
    }
}
```
# [LeetCode_1436_旅行终点站](https://leetcode-cn.com/problems/destination-city/submissions/)
## 解法
### 思路
- 遍历paths
  - 将目的地放入set集合中，记录所有的城市
  - 将起始点作为key放入map，目的地放入作为值的list中
- 遍历set，找到map中不存在的city直接返回即可
### 代码
```java
class Solution {
    public String destCity(List<List<String>> paths) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> cities = new HashSet<>();
        for (List<String> path : paths) {
            cities.add(path.get(1));
            map.computeIfAbsent(path.get(0), x -> new ArrayList<>()).add(path.get(1));
        }
        
        for (String city : cities) {
            if (!map.containsKey(city)) {
                return city;
            }
        }
        
        return "";
    }
}
```
## 解法二
### 思路
- 不需要用map存目的地集合，直接一个set存起始地，一个set存目的地
- 遍历目的地，找到起始地中没有的元素即可
### 代码
```java
class Solution {
    public String destCity(List<List<String>> paths) {
        Set<String> depart = new HashSet<>(),
                    dest = new HashSet<>();
        
        for (List<String> path : paths) {
            depart.add(path.get(0));
            dest.add(path.get(1));
        }
                
        for (String city : dest) {
            if (!depart.contains(city)) {
                return city;
            }
        }
        
        return null;
    }
}
```