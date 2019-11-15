# LeetCode_1130_叶值的最小代价生成树
## 题目
给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
```
每个节点都有 0 个或是 2 个子节点。
数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。（知识回顾：如果一个节点有 0 个子节点，那么该节点为叶节点。）
每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
```
示例：
```
输入：arr = [6,2,4]
输出：32
解释：
有两种可能的树，第一种的非叶节点的总和为 36，第二种非叶节点的总和为 32。

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4
```
提示：
```
2 <= arr.length <= 40
1 <= arr[i] <= 15
答案保证是一个 32 位带符号整数，即小于 2^31。
```
## 解法
### 思路
动态规划：
- 题目中需要使用到的概念
    - `maxValue[i][j]`：数组i到j的范围内的最大值，用来作为左右子树中被用来计算生成代价的因子
    - `dp[i][j]`：数组i到j的范围内，最小生成成代价的值
- 初始化：
    - `maxValue[i][i]`：
        - 通过嵌套循环，确定`maxValue`的i和j，定义好边界
        - 计算这个范围内的所有元素中的最大值，所以还需要再进行一层循环
        - 获得最大值后，存入`maxValue[i][j]`中
    - `dp[i][j]`：
        - `dp[i][i]`：都赋值为0，因为存的是非叶子节点最大值乘积的总和，`dp[i][i]`代表了叶子节点，所以是0
        - `dp[i][j]`：全部赋值为int的最大值最为初始值
- 状态转移方程：`dp[i][j] = dp[i][k] + dp[k + 1][j] + maxValue[i][k] * maxValue[k + 1][j]`(k代表了数组的中间值，也意味着左右子树的分隔)，左右子树的dp值代表左右子树的最小代价总和，再加上当前节点的值，它们是通过左右子树中的最大节点的和生成的。得到的结果和当前的节点的dp值进行比较，取最小值，这样不断更新。
- 结果返回：`dp[0][len - 1]`
### 代码
```java
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int len = arr.length;
        int[][] maxValue = new int[len][len], dp = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int max = 0;
                for (int k = i; k <= j; k++) {
                    max = Math.max(max, arr[k]);
                }
                maxValue[i][j] = max;
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < len; i++) {
            dp[i][i] = 0;
        }

        for (int range = 1; range < len; range++) {
            for (int start = 0; start < len - range; start++) {
                for (int mid = start; mid < start + range; mid++) {
                    dp[start][start + range] = Math.min(dp[start][start + range], dp[start][mid] + dp[mid + 1][start + range] + maxValue[start][mid] * maxValue[mid + 1][start + range]);
                }
            }
        }

        return dp[0][len - 1];
    }
}
```
## 解法二
### 思路
单调栈
### 代码
```java

```
# LeetCode_1026_节点与其祖先之间的最大差值
## 题目
给定二叉树的根节点 root，找出存在于不同节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。

（如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）

示例：
```
输入：[8,3,10,1,6,null,14,null,null,4,7,13]
输出：7
解释： 
我们有大量的节点与其祖先的差值，其中一些如下：
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
```
## 解法
### 思路
前序dfs遍历：
- 和当前节点比较，计算路径中的最大和最小值
- 计算差值，和对象属性进行比较，获得最大差值
- 递归左右子树继续计算
### 代码
```java
class Solution {
    private int ans = 0;
    public int maxAncestorDiff(TreeNode root) {
        dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return ans;
    }
    
    private void dfs(TreeNode node, int max, int min) {
        if (node == null) {
            return;
        }
        
        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        ans = Math.max(ans, max - min);
        
        dfs(node.left, max, min);
        dfs(node.right, max, min);
    }
}
```
# LeetCode_756_金字塔转换矩阵
## 题目
现在，我们用一些方块来堆砌一个金字塔。 每个方块用仅包含一个字母的字符串表示，例如 “Z”。

使用三元组表示金字塔的堆砌规则如下：

(A, B, C) 表示，“C”为顶层方块，方块“A”、“B”分别作为方块“C”下一层的的左、右子块。当且仅当(A, B, C)是被允许的三元组，我们才可以将其堆砌上。

初始时，给定金字塔的基层 bottom，用一个字符串表示。一个允许的三元组列表 allowed，每个三元组用一个长度为 3 的字符串表示。

如果可以由基层一直堆到塔尖返回true，否则返回false。

示例 1:
```
输入: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
输出: true
解析:
可以堆砌成这样的金字塔:
    A
   / \
  D   E
 / \ / \
X   Y   Z

因为符合('X', 'Y', 'D'), ('Y', 'Z', 'E') 和 ('D', 'E', 'A') 三种规则。
```
示例 2:
```
输入: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
输出: false
解析:
无法一直堆到塔尖。
注意, 允许存在三元组(A, B, C)和 (A, B, D) ，其中 C != D.
```
注意：
```
bottom 的长度范围在 [2, 8]。
allowed 的长度范围在[0, 200]。
方块的标记字母范围为{'A', 'B', 'C', 'D', 'E', 'F', 'G'}。
```
## 解法
### 思路
递归回溯：
- 遍历`allowed`生成map：
    - key为字符串的前两个字符拼接
    - value存储字符串的最后一个字符，通过位来存储7个字符，通过相加来代表有几个标记字母
- 回溯：
    - 参数：
        - 生成的map，用来通过每一层的每两个字符组成的前缀，获取对应的value
        - next：代表通过map获取的value组成的下一层的字符串
        - cur：代表当前层的字符串，起始是bottom
        - index：代表坐标，通过坐标来判断当前层的两两组合是否已经遍历完，同时也可以判断如果`index == cur.len - 1`，说明递归可以结束了
### 代码
```java
class Solution {
    private String str = "ABCDEFG";
    private int[] nums = {1,2,4,8,16,32,64};
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : allowed) {
            int num = nums[str.charAt(2) - 'A'];
            String key = str.substring(0, 2);
            map.put(key, map.getOrDefault(key, 0) + num);
        }

        return backTrace(map, "", bottom, 0);
    }

    private boolean backTrace(Map<String, Integer> map, String next, String cur, int index) {
        if (index == cur.length() - 1) {
            return cur.length() == 1 || backTrace(map, "", next, 0);
        }

        String key = cur.substring(index, index + 2);
        if (!map.containsKey(key)) {
            return false;
        }

        int value = map.get(key);
        for (int i = 0; i < 7 ; i++) {
            if ((value >> i & 1) == 1) {
                next += str.charAt(i);
                boolean flag = backTrace(map, next, cur, index + 1);
                if (flag) {
                    return true;
                }
                next = next.substring(0, next.length() - 1);
            }
        }
        return false;
    }
}
```
# LeetCode_399_除法求值
## 题目
给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。

示例 :
```
给定 a / b = 2.0, b / c = 3.0
问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
```
输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。

基于上述例子，输入如下：
```
equations(方程式) = [ ["a", "b"], ["b", "c"] ],
values(方程式结果) = [2.0, 3.0],
queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
```
## 解法
### 思路
- 构建一个有向图
- 比值就是节点之间的权重，需要注意的是来回的权重是不同的，互为倒数。
- 使用一个`Map<String, Map<String, double>>`来构建这个有向图
- 对queries中的元素进行遍历，查找在图中是否包含两个节点，如果不包含，这个元素对应的结果就是`-0.1`
- 之后进行dfs：
    - 参数：
        - 被除数：origin
        - 除数：target
        - 图：map
        - memo：set
    - 退出条件：origin == target，说明找到了最后的节点，返回权重1
    - 过程：
        - 将origin放入set中，代表当前节点已经访问过
        - 从map中找到`origin`的相邻节点进行遍历
        - 如果set中存在，就跳过
        - 否则就递归到下一个节点
        - 如果返回不是`-1`，代表递归结果找到了节点，就把当前到下一个节点的权重值与返回值相乘，返回到上一个节点去
        - 否则说明这个节点的相邻节点找不到目标节点，返回`-1`
### 代码
```java
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> list = equations.get(i);
            String origin = list.get(0);
            String target = list.get(1);

            Map<String, Double> innerMapA = map.get(origin);
            if (innerMapA == null) {
                innerMapA = new HashMap<>();
                innerMapA.put(target, values[i]);
                map.put(origin, innerMapA);
            } else {
                innerMapA.put(target, values[i]);
            }

            Map<String, Double> innerMapB = map.get(target);
            if (innerMapB == null) {
                innerMapB = new HashMap<>();
                innerMapB.put(origin, 1 / values[i]);
                map.put(target, innerMapB);
            } else {
                innerMapB.put(origin, 1 / values[i]);
            }
        }

        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String origin = query.get(0);
            String target = query.get(1);

            if (!map.containsKey(origin) || !map.containsKey(target)) {
                ans[i] = -1.0;
                continue;
            }

            ans[i] = dfs(origin, target, map, new HashSet<>());
        }
        return ans;
    }

    private double dfs(String origin, String target, Map<String, Map<String, Double>> map, Set<String> set) {
        if (Objects.equals(origin, target)) {
            return 1.0;
        }

        set.add(origin);

        Map<String, Double> innerMap = map.get(origin);
        for (Map.Entry<String, Double> entry : innerMap.entrySet()) {
            if (set.contains(entry.getKey())) {
                continue;
            }

            double value = dfs(entry.getKey(), target, map, set);
            if (value != -1.0) {
                return value * entry.getValue();
            }
        }

        return -1.0;
    }
}
```
# LeetCode_343_整数拆分
## 题目
给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

示例 1:
```
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
```
示例 2:
```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
说明: 你可以假设 n 不小于 2 且不大于 58。
```
## 解法
### 思路
记忆化递归：
- 退出条件：`n == 2`，代表值再分都是等于1，返回1
- 过程：
    - 从缓存中查找是否有相同的n
    - 遍历所有拆分的可能值，
    - 定义最大值max
    - 和被减数n相减，获得差，继续递归
    - 返回值和当前的拆分值相乘，乘积和最大值比较，取相对大值max
    - 记忆最大值并返回最大值max
- 要注意：下钻后，值本身不拆分也可以视为是一个解，所以返回时需要和`i * (n - i)`本身进行比较来比大小
### 代码
```java
class Solution {
    public int integerBreak(int n) {
        return rescue(n, new HashMap<>());
    }
    
    private int rescue(int n, Map<Integer, Integer> memo) {
        if (n <= 1) {
            return n;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int max = 0;
        for (int i  = 1; i < n; i++) {
            max = Math.max(max, Math.max(i * rescue(n - i, memo), i * (n - i)));
        }
        
        memo.put(n, max);
        return max;
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i]：数字为i时可以得到的题目要求的最大值
- base case：dp[2] = 1，根据题意可得2被拆分后为`1 * 1`
- 状态转移方程：dp[i] = max(dp[i], max(k * dp[i - k], k * (i - k)))
- 返回：dp[n]
### 代码
```java
class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }
        
        return dp[n];
    }
}
```