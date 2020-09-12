# LeetCode_256_粉刷房子
## 题目
假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。

当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。

例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。

注意：
```
所有花费均为正整数。
```
示例：
```
输入: [[17,2,17],[16,16,5],[14,3,19]]
输出: 10
解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
     最少花费: 2 + 5 + 3 = 10。
```
## 失败解法
### 失败原因
超时，时间复杂度过高
### 思路
自顶向下的递归：
- 把3种颜色对应成3种路径，模拟树的搜索过程
- 递归参数：
    - costs：数组集合
    - depth：树的深度
    - color：上一层为当前层挑选的颜色
- 返回各个路径作为根节点得子树的和的最小值
- 退出条件是找到叶子节点，也就是depth和costs.length - 1相等
### 代码
```java
class Solution {
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }

        return Math.min(recurse(costs, 0, 0), Math.min(recurse(costs, 0, 1), recurse(costs, 0, 2)));
    }

    private int recurse(int[][] costs, int depth, int color) {
        int total = costs[depth][color];
        if (depth == costs.length - 1) {
            return total;
        }

        if (color == 0) {
            total += Math.min(recurse(costs, depth + 1, 1), recurse(costs, depth + 1, 2));
        } else if (color == 1) {
            total += Math.min(recurse(costs, depth + 1, 0), recurse(costs, depth + 1, 2));
        } else {
            total += Math.min(recurse(costs, depth + 1, 1), recurse(costs, depth + 1, 0));
        }

        return total;
    }
}
```
## 解法
### 思路
在失败解法的基础上增加记忆化搜索
### 代码
```java
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        
        Integer[][] memo = new Integer[costs.length][3];
        return Math.min(recurse(costs, 0, 0, memo), Math.min(recurse(costs, 0, 1, memo), recurse(costs, 0, 2, memo)));
    }
    
    private int recurse(int[][] costs, int depth, int color, Integer[][] memo) {
        int total = costs[depth][color];
        if (depth == costs.length - 1) {
            memo[depth][color] = total;
            return total;
        }
        
        if (color == 0) {
            int green = memo[depth + 1][1] == null ? recurse(costs, depth + 1, 1, memo) : memo[depth + 1][1],
                blue = memo[depth + 1][2] == null ? recurse(costs, depth + 1, 2, memo) : memo[depth + 1][2];
            total += Math.min(green, blue);
        } else if (color == 1) {
            int red = memo[depth + 1][0] == null ? recurse(costs, depth + 1, 0, memo) : memo[depth + 1][0],
                blue = memo[depth + 1][2] == null ? recurse(costs, depth + 1, 2, memo) : memo[depth + 1][2];
            total += Math.min(red, blue);
        } else {
            int red = memo[depth + 1][0] == null ? recurse(costs, depth + 1, 0, memo) : memo[depth + 1][0],
                green = memo[depth + 1][1] == null ? recurse(costs, depth + 1, 1, memo) : memo[depth + 1][1];
            total += Math.min(red, green);
        }
        
        memo[depth][color] = total;
        return total;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][j]`：0到坐标i的楼，使用第j种颜色得到做的最小的颜料花费
- 状态转移方程：`dp[i][j] = costs[i][j] + min(dp[i - 1],[k], dp[i - 1][l])`
- base case：
    - `dp[0][j] = costs[0][0]`
    - `dp[0][k] = costs[0][1]`
    - `dp[0][l] = costs[0][2]`
- 最终结果`min(dp[len - 1][0], dp[len - 1][1], dp[len - 1][2])`
### 代码
```java
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        
        int len = costs.length;
        int[][] dp = new int[len][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        
        for (int i = 1; i < len; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        
        return Math.min(dp[len - 1][0], Math.min(dp[len - 1][1], dp[len - 1][2]));
    }
}
```
# LeetCode_259_较小的三数之和
## 题目
给定一个长度为 n 的整数数组和一个目标值 target，寻找能够使条件 nums[i] + nums[j] + nums[k] < target 成立的三元组  i, j, k 个数（0 <= i < j < k < n）。

示例：
```
输入: nums = [-2,0,1,3], target = 2
输出: 2 
解释: 因为一共有两个三元组满足累加和小于 2:
    [-2,0,1]
     [-2,0,3]
```
 ```
进阶：是否能在 O(n2) 的时间复杂度内解决？
```
## 解法
### 思路
3重循环遍历求解
### 代码
```java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int len = nums.length, ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] < target) {
                        ans++;
                    }
                }
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
二分查找：
- 将数组排序
- 先确定一个坐标`i`，遍历的范围是`[0, len - 2)`，空出2个位置给另2个值
- 确定`i`之后，用`target - nums[i]`获得一个`newTarget`，这样使得`3sum`计算降到了`2sum`
- 以`newTarget`为目标，同时从`i + 1`开始确定剩下两个数中较小的那个
- 而求第三个数的过程就交给二分查找完成，求的目标值就是`newTarget - nums[j]`
- 二分找到的坐标`k`，就是`nums[j] + nums[k] < newTarget`的最大区间，基于`i`的状态下能够得到的最多的`2sum`个数就是`k - j`
- 遍历`i`并累加`2sum`的个数，并作为结果在遍历结束后返回
- 在二分查找的时候，`mid`要故意向`tail`偏移，因为找的是离`target`最近的小于它的数，所以当发现mid坐标对应的值小于target，有可能这个就是离之最近的值，那么就使得head = mid的同时，让下一次mid的值靠右，那么新mid的值就会大于或等于target，此时再让tail = mid，就能使head == tail，从而获得这个目标坐标
### 代码
```java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int sum = 0, len = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < len - 2; i++) {
            int newTarget = target - nums[i];

            for (int j = i + 1; j < len - 1; j++) {
                int k = binarySearch(nums, j, newTarget - nums[j]);
                sum += k - j;
            }
        }

        return sum;
    }

    private int binarySearch(int[] nums, int start, int target) {
        int head = start, tail = nums.length - 1;
        while (head < tail) {
            int mid = head + (tail + 1 - head) / 2;

            if (nums[mid] < target) {
                head = mid;
            } else {
                tail = mid - 1;
            }
        }

        return head;
    }
}
```
# LeetCode_261_以图判树
## 题目
给定从 0 到 n-1 标号的 n 个结点，和一个无向边列表（每条边以结点对来表示），请编写一个函数用来判断这些边是否能够形成一个合法有效的树结构。

示例 1：
```
输入: n = 5, 边列表 edges = [[0,1], [0,2], [0,3], [1,4]]
输出: true
```
示例 2:
```
输入: n = 5, 边列表 edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
输出: false
注意：你可以假定边列表 edges 中不会出现重复的边。由于所有的边是无向边，边 [0,1] 和边 [1,0] 是相同的，因此不会同时出现在边列表 edges 中。
```
## 解法
### 思路
- 成为树的条件：
    - 连通分量为1
    - 图是无环图
- 用并查集合并所有边，如果边的两个点在同一个集合里，那说明有环，直接返回false
- 如果没有环，就做合并，并在初始的时候设置一个变量count，用来计算合并的次数，如果每次都合并，且合并次数为n-1，那么说明连通分量为1
### 代码
```java
class Solution {
    public boolean validTree(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) {
                return false;
            }
        }

        return dsu.count() == 1;
    }

    class DSU {
        private int[] parent;
        private int[] rank;
        private int count;

        private DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            count = n;
        }

        private int find(int x) {
            if (parent[x] != x) {
                return find(parent[x]);
            }
            return parent[x];
        }

        private boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return false;
            }

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootY] > rank[rootX]) {
                parent[rootX] = rootY;
            } else {
                parent[rootX] = rootY;
                rank[rootY]++;
            }
            
            count--;
            return true;
        }

        private int count() {
            return this.count;
        }
    }
}
```
# LeetCode_265_粉刷房子
## 题目
假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。

当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。

例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。

注意：
```
所有花费均为正整数。
```
示例：
```
输入: [[1,5,3],[2,9,4]]
输出: 5
解释: 将 0 号房子粉刷成 0 号颜色，1 号房子粉刷成 2 号颜色。最少花费: 1 + 4 = 5; 
     或者将 0 号房子粉刷成 2 号颜色，1 号房子粉刷成 0 号颜色。最少花费: 3 + 2 = 5. 
```
## 解法
### 思路
动态规划：
- `dp[i][j]`：第i号房子使用第j种颜色时，区间`[0,i]`的最小花费
- base case：`dp[0][j] = costs[0][j]`
- 状态转移方程：`dp[i][j] = costs[i][j] + min(dp[i - 1][j])`
- 返回结果：`min(dp[i][j])`
- 过程：
    - 嵌套遍历，外层遍历i，内层遍历j
    - 外层遍历时：
        - `i == 0`：处理base case
        - `i == len - 1`：额外比较结果值
        - 其他情况处理中层循环
    - 中层循环：遍历所有颜色j的可能
    - 内层循环：遍历所有颜色，当与j相同时跳过，其他情况则处理状态转移方程
        
### 代码
```java
class Solution {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }

        int k = costs[0].length;
        int[][] dp = new int[n][k];
        System.arraycopy(costs[0], 0, dp[0], 0, k);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int min = Integer.MAX_VALUE;
                for (int m = 0; m < k; m++) {
                    if (m == j) {
                        continue;
                    }
                    
                    min = Math.min(min, dp[i - 1][m]);
                }
                
                dp[i][j] = costs[i][j] + min;
            }
        }

        return Arrays.stream(dp[n - 1]).min().getAsInt();
    }
}
```
## 解法二
### 思路
- 要将时间复杂度降低，就需要考虑状态转移时候是否能将遍历两次的颜色状态降到遍历1次，又因为颜色是常数，所以时间复杂度就可以降到`O(KN)`
- 解法一中，每一号房子都要遍历使用j颜色时，其他dp颜色的最小值
- 但其实当前状态只取决于2种情况：
    - 当前使用的颜色和上一号房子花费最小值使用的颜色不同，那么就直接用当前花费加上上一层的最小值即可
    - 当前使用的颜色和上一号房子花费最小值使用的颜色相同，那么就应该使用第二小的值
- 所以如上所述，只要记录每一层的最小和次小两个值，且同时记录对应的颜色坐标，就能直接获得目标状态
### 代码
```java
class Solution {
    public int minCostII(int[][] costs) {
        int row = costs.length;
        if (row == 0) {
            return 0;
        }

        int col = costs[0].length;
        if (col == 0) {
            return 0;
        }
        
        int one = Integer.MAX_VALUE, oneIndex = 0,
            two = Integer.MAX_VALUE,
            ans = Integer.MAX_VALUE;

        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(i == 0) {
                    dp[i][j] = costs[i][j];
                    if(row == 1) {
                        ans = Math.min(ans, dp[i][j]);
                    }
                    continue;
                }
                
                if (j != oneIndex) {
                    dp[i][j] = Math.min(dp[i][j], one + costs[i][j]);
                } else {
                    dp[i][j] = Math.min(dp[i][j], two + costs[i][j]);
                }
                
                if (i == row - 1) {
                    ans = Math.min(ans, dp[i][j]);
                }
            }
            
            one = Integer.MAX_VALUE; 
            oneIndex = 0;
            two = Integer.MAX_VALUE;
            
            for (int j = 0; j < col; j++) {
                if (dp[i][j] < one) {
                    one = dp[i][j];
                    oneIndex = j;
                }
            }
            
            for (int j = 0; j < col; j++) {
                if (j != oneIndex && dp[i][j] < two) {
                    two = dp[i][j];
                }
            }
        }
        
        return ans;
    }
}
```
# LeetCode_266_回文排列
## 题目
给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。

示例 1：
```
输入: "code"
输出: false
```
示例 2：
```
输入: "aab"
输出: true
```
示例 3：
```
输入: "carerac"
输出: true
```
## 解法
### 思路
- 使用数组记录字符出现的个数，如果只有一个或没有字符出现奇数次，这个就是回文串
- 先遍历确定数组的长度，长度基于字符串字符的最大值 + 1
- 然后遍历字符串，记录出现的次数
- 最后遍历数组，找到奇数的个数
- 返回奇数是否小于等于1
### 代码
```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int len = 0;
        for (char c : s.toCharArray()) {
            len = Math.max(len, c);
        }

        int[] bucket = new int[len + 1];
        for (char c : s.toCharArray()) {
            bucket[c]++;
        }

        int odd = 0;
        for (int num : bucket) {
            if (num % 2 == 1) {
                odd++;
            }
        }

        return odd <= 1;
    }
}
```