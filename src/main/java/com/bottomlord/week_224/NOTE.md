# [LeetCode_1155_掷骰子等于目标和的方法数](https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum)
## 解法
### 思路
动态规划：
- dp[i][j]：
  - `i`代表第`i`次掷骰子
  - `j`代表前`i`次掷骰子后的总和为`j`
  - `dp[i][j]`代表`i`次掷骰子之后得到总和为`j`时的所有组合次数
- 状态转移方程：`dp[i][j] += dp[i - 1][j - x], x ∈ [1, k]`
- base case：`dp[1][x] = 1, x ∈ [1, k]`
- 返回结果：`dp[n][target]`
- 算法过程：
  - 3层循环
    - 第1层从2开始遍历到n次，模拟掷骰子
    - 第2层遍历所有上一次计算得到的可能总和，范围在`[i - 1, target - (n - i + 1)]`
    - 第3层遍历骰子所有可能的面值
  - 循环内容套用状态转移方程进行计算
  - 遍历结束后返回`dp[n][target]`
### 代码
```java
class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 1; i <= Math.min(k, target); i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int preSum = i - 1; preSum <= target - (n - i + 1); preSum++) {
                for (int curValue = 1; curValue <= k; curValue++) {
                    int total;
                    if ((total = preSum + curValue) > target) {
                        break;
                    }

                    dp[i][total] += dp[i - 1][preSum];
                    dp[i][total] %= 1000000007;
                }
            }
        }

        return dp[n][target];
    }
}
```
# [LeetCode_2698_求一个整数的惩罚数](https://leetcode.cn/problems/find-the-punishment-number-of-an-integer)
## 解法
### 思路
- 思考过程：
  - 回溯
- 算法过程：
  - 遍历`[1,n]`范围内的所有数，对每个数做回溯，判断是否是能够达到题目要求的数字`i`，如果符合就累加平方
  - 定义回溯函数，返回值为布尔值，返回当前`i`是否能满足题目要求的状态
    - `index`入参控制回溯切分字符串的位置，然后通过计算来判断是否符合题目要求
    - `sum`入参控制每个可能搜索路径的暂定总和
    - `s`入参是当前`i`坐标平方的字符串形式
    - `target`是当前`i * i`的值
  - 回溯过程中从`index`开始，遍历所有可能坐标，切分字符串，转换成数字后累加到`sum`上
  - 通过递归，搜索到可能路径的叶子结点，判断`sum`是否与`target`（目标值，也即`i * i`）一致，如果一致就返回true，否则返回false
  - 循环过程中，如果递归返回true，则直接中断循环遍历，直接返回true
  - 如果循环结束还没有返回true，则返回false，说明当前路径的所有叶子结点都不能满足题目要求
### 代码
```java
class Solution {
  public int punishmentNumber(int n) {
    int sum = 0;
    for (int i = 1; i <= n; i++) {
      int pow = i * i;
      if (backTrack(0, Integer.toString(pow), 0, i)) {
        sum += pow;
      }
    }

    return sum;
  }

  private boolean backTrack(int index, String s, int sum, int target) {
    if (index == s.length()) {
      return sum == target;
    }

    for (int i = index; i < s.length(); i++) {
      int cur = Integer.parseInt(s.substring(index, i + 1));

      if (backTrack(i + 1, s, sum + cur, target)) {
        return true;
      }
    }

    return false;
  }
}
```
## 解法二
### 思路
- 解法一中通过将平方转换成字符串后，通过string的API来处理题目要求的切分的动作，但实际可以通过除法和取模来获取一个整数切分后的2部分：
  - `151 / 10 = 15`
  - `151 % 10 = 1`
- 通过如上的方式将字符串的转换开销省略掉
- 另外，因为没有了字符串的处理，所以解法一中的坐标也就不需要了，题目看的是一个整数的平方在切分后，通过相加是否能得到整数本身
  - 题目要求代表，整个算法包括了2部分，平方`pow`和整数`target`
- 那么当`pow`被切分后，将一部分从`target`减去后，实际求解的过程是不变的，只是`pow`和`target`的值变化了
  - `pow` => `partB`，`pow = partA concat partB`
  - `target` => `target - partA`
- 所以这就变成了递归，而递归的退出条件：
  - `pow < target`，因为`partA <= pow && partB <= pow`，所以，这时候当前路径一定不可能得到`target`，返回false
  - `pow == target`，则说明当前路径是可行的，返回true即可
- 逻辑主体与解法一不变，但具体实现和入参做了变更，因为不需要字符串变换的开销，整体速度肯定是变快的。
### 代码
```java
class Solution {
    public int punishmentNumber(int n) {
        int sum = 0;
        for (int target = 1; target <= n; target++) {
            int pow = target * target;
            if (backTrack(pow, target)) {
                sum += pow;
            }
        }

        return sum;
    }

    private boolean backTrack(int pow, int target) {
        if (pow < target) {
            return false;
        }
        
        if (pow == target) {
            return true;
        }

        int mask = 10;
        while (pow >= mask && pow % mask <= target) {
            if (backTrack(pow / mask, target - (pow % mask))) {
                return true;
            }
            
            mask *= 10;
        }

        return false;
    }
}
```
# [LeetCode_1465_切割后面积最大的蛋糕](https://leetcode.cn/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts)
## 解法
### 思路
- 思考过程：
  - 看图可知，2根相邻的横线和2根相邻的竖线，形成的矩形就是题目要求的蛋糕
  - 基于这4根线的横坐标和纵坐标，就能够求出这个矩形的面积
  - 因为题目要求的是最大面积，实际就是枚举这些矩形，然后比较并得到其中最大的部分
  - 那么要求这个题目，就可以对横坐标和纵坐标进行升序排序，遍历所有横坐标或纵坐标相邻之间间距，得到最大的间距，然后相乘，得到最大值即可
- 算法过程：
  - 对横坐标和纵坐标升序排序
  - 分别遍历2个数组，得到数值最大的间距
  - 返回相乘并取模后的结果
  - 需要注意：
    - 因为边缘部分不需要切2刀，所以需要再特判边缘部分的间距
    - 数据范围过大，32位整数会发生溢出，需要先转成64位后再转回
### 代码
```java
class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        return (int) ((long) calMax(horizontalCuts, h) * calMax(verticalCuts, w) % 1000000007);
    }

    public int calMax(int[] arr, int board) {
        int res = 0, pre = 0;
        for (int cur : arr) {
            res = Math.max(cur - pre, res);
            pre = cur;
        }
        return Math.max(res, board - pre);
    }
}
```