# LeetCode_275_H指数II
## 题目
给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照 升序排列 。编写一个方法，计算出研究者的 h 指数。

h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）"

示例:
```
输入: citations = [0,1,3,5,6]
输出: 3 
解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
     由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。

说明:

如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
```
进阶：
```
这是 H 指数 的延伸题目，本题中的 citations 数组是保证有序的。
你可以优化你的算法到对数时间复杂度吗？
```
## 解法
### 思路
解法和274相同
### 代码
```java
class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length, i = 0;
        while (i < len && citations[len - 1 - i] > i) {
            i++;
        }
        
        return i;
    }
}
```
## 解法二
### 思路
二分查找：
- 找到h指数的最理想状态：`citations[i] == len - i`，说明达到引用次数的论文数与引用次数完全相等，如果引用数再大一点，就没有足够的论文了
- 找到h指数次理想的状态：`citations[i] < len - i`，说明当前拥有该引用数的论文比引用数多，那么可能存在更大的引用数，但是结果更大的引用数对应的论文数不够了，那么就以当前值为结果
- 综上所述，可以将这个寻找的过程转换成二分查找：
    - 计算中间值`mid`，判断`citations[mid]`与`len - mid`之间的关系
        - 如果相等，直接返回`len - mid`
        - 如果小于，则找右边，可能还有更大值
        - 如果大于，则找左边，当前引用数过大了
- 最终如果循环内没有找到，则返回次优解，也就是`len - mid`
### 代码
```java
class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int mid, head = 0, tail = len - 1;

        while (head <= tail) {
            mid = head + (tail - head) / 2;

            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return len - head;
    }
}
```
# LeetCode_968_监控二叉树
## 题目
给定一个二叉树，我们在树的节点上安装摄像头。

节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。

计算监控树的所有节点所需的最小摄像头数量。

示例 1：
```
输入：[0,0,null,0,0]
输出：1
解释：如图所示，一台摄像头足以监控所有节点。
```
示例 2：
```
输入：[0,0,null,0,null,0,null,null,0]
输出：2
解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
```
提示：
```
给定树的节点数的范围是 [1, 1000]。
每个节点的值都是 0。
```
## 解法
### 思路
贪心算法：
- 如果叶子节点上放camera，能够监控到自身和父节点
- 如果叶子节点的父节点放camera，能够监控到自身，叶子节点，叶子节点的兄弟节点
- 第二种方法好于第一种
- 所以从底部向上，只给叶子节点的父节点设置camera，然后删除被覆盖的节点
- 继续之前的逻辑
- 过程：
- dfs
- 如果当前节点为叶子节点，设置为0返回
- 如果当前节点的左右子节点有叶子节点，camera计数，并返回1
- 如果当前节点的子节点没有叶子节点，且有设置camera的节点，返回2，代表已经被监控
- 最后返回camera数量，同时判断root节点是否是0，如果是，说明root还需要设置camera来监控自己
### 代码
```java
class Solution {
    int camera = 0;
    public int minCameraCover(TreeNode root) {
        return (dfs(root) == 0 ? 1 : 0) + camera;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) {
            return 2;
        }
        
        int left = dfs(node.left), right = dfs(node.right);
        
        if (left == 0 || right == 0) {
            camera++;
            return 1;
        }
        
        return left == 1 || right == 1 ? 2 : 0;
    }
}
```
# LeetCode_276_栅栏涂色
## 题目
有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，每个栅栏柱可以用其中一种颜色进行上色。

你需要给所有栅栏柱上色，并且保证其中相邻的栅栏柱 最多连续两个 颜色相同。然后，返回所有有效涂色的方案数。

注意:
```
n 和k 均为非负的整数。
```
示例:
```
输入: n = 3，k = 2
输出: 6
解析: 用 c1 表示颜色 1，c2 表示颜色 2，所有可能的涂色方案有:

            柱 1    柱 2   柱 3     
 -----      -----  -----  -----       
   1         c1     c1     c2 
   2         c1     c2     c1 
   3         c1     c2     c2 
   4         c2     c1     c1  
   5         c2     c1     c2
   6         c2     c2     c1
```
## 解法
### 思路
动态规划：
- `dp[n]`：第n个栅栏可以涂色的可能个数
- 状态转移方程：
    - `dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1)`
    - `dp[i - 1] * (k - 1)`：和前一个栅栏不是同一个颜色的可能状态
    - `dp[i - 2] * (k - 1)`：和前一个栅栏是同一个颜色，则和前2个栅栏不是同一个颜色，这样情况的颜色可能状态
    - 如上两个当前栅栏可以涂成的颜色，组成的可能个数
- base case：
    - `dp[1] = k` 
    - `dp[2] = k * k`
- 返回结果：`dp[n]`
### 代码
```java
class Solution {
    public int numWays(int n, int k) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                dp[i] = k;
            } else if (i == 2) {
                dp[i] = dp[i - 1] * k;
            } else {
                dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1);
            }

        }

        return dp[n];
    }
}
```