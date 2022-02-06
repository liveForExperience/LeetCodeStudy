# [LeetCode_2154_将找到的值乘以2](https://leetcode-cn.com/problems/keep-multiplying-found-values-by-two/)
## 解法
### 思路
- 初始化一个布尔变量
- 2层循环，外层依赖布尔变量做退出判断
- 外层开始时先使布尔值变为false
- 内层遍历数组找original，如果找到就将布尔值变成true，并且将original变成2倍
- 直到外层结束，返回original值
### 代码
```java
class Solution {
    public int findFinalValue(int[] nums, int original) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int num : nums) {
                if (num == original) {
                    original = original * 2;
                    flag = true;
                    break;
                }
            }
        }

        return original;
    }
}
```
# [LeetCode_LCP44_开幕式烟火](https://leetcode-cn.com/problems/sZ59z6/)
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    public int numColor(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        dfs(root, set);
        return set.size();
    }
    
    private void dfs(TreeNode node, Set<Integer> set) {
        if (node == null) {
            return;
        }
        
        set.add(node.val);
        dfs(node.left, set);
        dfs(node.right, set);
    }
}
```
# [LeetCode_LCS01_下载插件](https://leetcode-cn.com/problems/Ju9Xwi/)
## 解法
### 思路
- 下载的时间复杂度是O(1)，扩容的时间复杂度是O(log2)
- 所以选择先进行扩容，再一次性下载
### 代码
```java
class Solution {
    public int leastMinutes(int n) {
       return (int)Math.ceil(Math.log(n) / Math.log(2)) + 1;
    }
}
```
# [LeetCode_LCS02_完成一半题目](https://leetcode-cn.com/problems/WqXACV/)
## 解法
### 思路
- 桶计数算出知识点的重复次数
- 对出现次数进行排序
- 从大到小循环累减知识点个数，直到N被消耗完，处理过程中记录循环的个数
### 代码
```java
class Solution {
    public int halfQuestions(int[] questions) {
        int[] bucket = new int[1001];
        for (int question : questions) {
            bucket[question]++;
        }

        Arrays.sort(bucket);
        int n = questions.length / 2, ans = 0;

        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == 0) {
                continue;
            }

            n -= bucket[i];
            ans++;

            if (n <= 0) {
                break;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_1414_和为K的最少斐波那契数字数目](https://leetcode-cn.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/)
## 解法
### 思路
递归
### 代码
```java
class Solution {
    private int count = 0;

    public int findMinFibonacciNumbers(int k) {
        recuse(k, new HashSet<>());
        return count;
    }

    private void recuse(int k, Set<Integer> set) {
        int fab = fab(k, set);
        count++;
        if (fab == k) {
            return;
        }

        if (set.contains(k - fab)) {
            count++;
            return;
        }

        recuse(k - fab, set);
    }

    private int fab(int k, Set<Integer> set) {
        int x = 0, z = 0, y = 1;
        while (z <= k) {
            set.add(y);
            z = x + y;
            x = y;
            y = z;
        }

        return x;
    }
}
```
# [LeetCode_offerII01_整数除法](https://leetcode-cn.com/problems/xoh6Oh/)
## 解法
### 思路
二分+递归
### 代码
```java
class Solution {
    public int divide(int a, int b) {
        long x = a, y = b;
        int sign = (x > 0) ^ (y > 0) ? -1 : 1;
        x = x < 0 ? -x : x;
        y = y < 0 ? -y : y;
        long r = (cal(x, y) * sign);
        return r == 2147483648L ? 2147483647 : (int)r;
    }

    private long cal(long a, long b) {
        if (a < b) {
            return 0L;
        }

        long count = 0, bit = 1, c = b;
        while (a > 0) {
            if (a - c < 0) {
                break;
            }
            a -= c;
            c <<= 1;
            count += bit;
            bit <<= 1;
        }

        return count + cal(a, b);
    }
}
```
# [LeetCode_1219_黄金矿工](https://leetcode-cn.com/problems/path-with-maximum-gold/)
## 解法
### 思路
回溯
### 代码
```java
class Solution {
    private final int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    
    public int getMaximumGold(int[][] grid) {
        int max = 0, row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                
                max = Math.max(max, backTrack(grid, row, col, i, j, 0, new boolean[row][col]));
            }
        }
        
        return max;
    }

    private int backTrack(int[][] grid, int row, int col, int x, int y, int pre, boolean[][] memo) {
        if (x < 0 || x >= row || y < 0 || y >= col || memo[x][y] || grid[x][y] == 0) {
            return pre;
        }
        
        memo[x][y] = true;
        pre += grid[x][y];
        int curMax = pre;
        for (int[] direction : directions) {
            int newX = x + direction[0], newY = y + direction[1];
            curMax = Math.max(curMax, backTrack(grid, row, col, newX, newY, pre, memo));
        }
        memo[x][y] = false;
        
        return curMax;
    }
}
```