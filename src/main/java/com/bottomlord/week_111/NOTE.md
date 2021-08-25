# [LeetCode_1512_好数对的数目](https://leetcode-cn.com/problems/number-of-good-pairs/)
## 解法
### 思路
2层循环，外层确定i，内层确定j，判断并累加有效对
### 代码
```java
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
```
## 解法二
### 思路
- 初始化一个桶数组，大小为题目数组的最大值+1，这个数组用来统计遍历过程中遇到的元素对应的次数
- 初始化一个变量用来统计有效对
- 正向遍历数组，在遍历过程中，累加当前元素在桶数组中出现的次数，并在累加后对次数做+1操作
  - 累加到总数中是因为，当前元素一定比桶数组中统计的这些相同元素的坐标值更大，那么这些元素有多少个，就能和当前元素组成多少个有效对
  - 对桶数组对应数累加1，就是为下一个出现的相同元素计算做准备
- 遍历结束返回统计值
### 代码
```java
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int  ans = 0;
        int[] bucket = new int[101];
        for (int num : nums) {
            ans += bucket[num]++;
        }
        return ans;
    }
}
```
# [LeetCode_1646_获取生成数组中的最大值](https://leetcode-cn.com/problems/get-maximum-in-generated-array/)
## 解法
### 思路
- 遍历数组，判断当前坐标的奇偶性
  - 偶数，当前坐标元素等于坐标除以2的元素
  - 奇数，当前坐标元素等于坐标处以2以及坐标除2+1的元素的和
- 遍历过程中，同时统计最大值
- 遍历结束后，返回最大值
### 代码
```java
class Solution {
    public int getMaximumGenerated(int n) {
        if (n <= 1) {
            return n;
        }

        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        
        int max = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[i / 2] + nums[i / 2 + 1];
            }
            
            max = Math.max(max, nums[i]);
        }
        
        return max;
    }
}
```
# [LeetCode_1518_换酒问题](https://leetcode-cn.com/problems/water-bottles/)
## 解法
### 思路
- 初始化变量：
  - drink：统计喝了多少瓶酒，初始为numBottle
  - emptyBottle：统计目前有的空瓶数量
- 设置循环
  - 退出条件，空瓶比`numExchange`的数字小，代表不能再兑换新酒了
  - 过程中：
    - drink累加当前兑换的新酒
    - emptyBottle累加兑换的新酒数，作为增加的酒瓶数，
    - 同时累减兑换时用掉的空瓶数
- 循环退出后，返回drink值
### 代码
```java
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int drink = numBottles, emptyBottles = numBottles;
        
        while (emptyBottles >= numExchange) {
            int newDrink = emptyBottles / numExchange;
            drink += newDrink;
            emptyBottles = emptyBottles + newDrink - newDrink * numExchange;
        }
        
        return drink;
    }
}
```
# [LeetCode_1523_在区间范围内统计奇数数目](https://leetcode-cn.com/problems/count-odd-numbers-in-an-interval-range/)
## 失败解法
### 原因
超时
### 思路
- 遍历统计
### 代码
```java
class Solution {
  public int countOdds(int low, int high) {
    int odd = 0;
    for (int i = low; i <= high; i++) {
      odd += i % 2 == 1 ? 1 : 0;
    }
    return odd;
  }
}
```
## 解法
### 思路
- `num = high - low + 1`算出包含的元素个数
  - 如果个数是奇数：
    - low是奇数，奇数个数是`num / 2 + 1`
    - log是偶数，奇数个数是`num / 2`
  - 如果个数是偶数，奇数个数是`num / 2`
### 代码
```java
class Solution {
    public int countOdds(int low, int high) {
        int num = high - low + 1;
        return num % 2 == 0 ? num / 2 : low % 2 == 1 ? num / 2 + 1 : num / 2;
    }
}
```
# [LeetCode_1528_1_重新排列字符串](https://leetcode-cn.com/problems/shuffle-string/)
## 解法
### 思路
- 初始化一个字符数组，用于暂存新的字符串
- 遍历字符串，根据indices数组指示，组成新的字符串
- 返回字符串
### 代码
```java
class Solution {
    public String restoreString(String s, int[] indices) {
        char[] cs = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            cs[indices[i]] = s.charAt(i);
        }
        return new String(cs);
    }
}
```
# [LeetCode_787_K站中转内最便宜的航班](https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/)
## 失败解法
### 原因
超时
### 思路
dfs
### 代码
```java
class Solution {
private int min = Integer.MAX_VALUE;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> mapping = new HashMap<>();
        for (int[] flight : flights) {
            mapping.computeIfAbsent(flight[0], x -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }

        dfs(src, dst, -1, k, 0, mapping);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private void dfs(int cur, int dst, int count, int k, int cost, Map<Integer, List<int[]>> mapping) {
        if (count > k) {
            return;
        }
        
        if (cur == dst) {
            min = Math.min(cost, min);
            return;
        }
        
        List<int[]> nexts = mapping.getOrDefault(cur, new ArrayList<>());
        
        for (int[] next : nexts) {
            dfs(next[0], dst, count + 1, k, cost + next[1], mapping);
        }
    }
}
```
## 解法
### 思路
动态规划：
- dp[t][i]：代表乘坐t次航班后到达城市i的最小花费
  - 题目中t的最大值应该是k+1，因为中转次数+1=乘坐航班数
  - dp数组的默认值设置为`INF = 10000 * 101 + 1`，代表无法到达的情况
  - INF的计算是基于最大的k值 + 1和最大的票价值的乘积 + 1获得
- base case：dp[0][src] = 0
  - 代表不坐航班，到达起始城市的花费就是0，因为不用坐航班
- 状态转移方程：
  - dp[t][i] = min(dp[t][i], dp[t - 1][j] + cost)
    - j是从可选航班中找到的能够到达城市i的起始城市
    - cost是从j到i的花费
- 过程：2层循环，分别确定t和i
  - 外层循环k+1次，确定t
  - 内层循环可选航班数组，确定第t次的时候，所有的状态
- 最终结果：遍历所有i = dst的情况，找到最小值
  - 如果最小值是INF，说明没法到达dst，返回-1
- 注意：这里不用int最大值来代表无法到达是因为，如果使用int最大值，会出现溢出状况
### 代码
```java
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INF = 10000 * 101 + 1;
        int[][] dp = new int[k + 2][n];
        for (int[] arr : dp) {
            Arrays.fill(arr, INF);
        }
        
        dp[0][src] = 0;
        
        for (int t = 1; t <= k + 1; t++) {
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                dp[t][i] = Math.min(dp[t][i], dp[t - 1][j] + cost);
            }
        }
        
        int min = INF;
        for (int i = 1; i <= k + 1; i++) {
            min = Math.min(min, dp[i][dst]);
        }
        
        return min == INF ? -1 : min;
    }
}
```
# [LeetCode_797_所有可能的路径](https://leetcode-cn.com/problems/all-paths-from-source-to-target/)
## 解法
### 思路
回溯
### 代码
```java
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(0, graph, new LinkedList<Integer>(){{this.add(0);}}, ans);
        return ans;
    }
    
    private void backTrack(int index, int[][] graph, LinkedList<Integer> list, List<List<Integer>> ans) {
        if (index == graph.length - 1) {
            ans.add(new ArrayList<>(list));
            return;
        }
        
        int[] arr = graph[index];
        for (Integer num : arr) {
            list.addLast(num);
            backTrack(num, graph, list, ans);
            list.removeLast();
        }
    }
}
```