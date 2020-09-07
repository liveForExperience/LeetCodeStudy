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