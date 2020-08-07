# LeetCode_1130_叶值的最小代价生成树
## 题目
给你一个正整数数组arr，考虑所有满足以下条件的二叉树：
```
每个节点都有 0 个或是 2 个子节点。
数组arr中的值与树的中序遍历中每个叶节点的值一一对应。（知识回顾：如果一个节点有 0 个子节点，那么该节点为叶节点。）
每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个32 位整数。
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
答案保证是一个 32 位带符号整数，即小于2^31。
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
给定二叉树的根节点root，找出存在于不同节点A 和B之间的最大值 V，其中V = |A.val - B.val|，且A是B的祖先。

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

初始时，给定金字塔的基层bottom，用一个字符串表示。一个允许的三元组列表allowed，每个三元组用一个长度为 3 的字符串表示。

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
bottom 的长度范围在[2, 8]。
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
给出方程式A / B = k, 其中A 和B 均为代表字符串的变量，k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回-1.0。

示例 :
```
给定a / b = 2.0, b / c = 3.0
问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
返回[6.0, 0.5, -1.0, 1.0, -1.0 ]
```
输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)，其中equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。返回vector<double>类型。

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
给定一个正整数n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

示例 1:
```
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
```
示例2:
```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 ×3 ×4 = 36。
说明: 你可以假设n不小于 2 且不大于 58。
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
# LeetCode_80_删除排序数组中的重复项
## 题目
给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

示例1:
```
给定 nums = [1,1,1,2,2,3],

函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。

你不需要考虑数组中超出新长度后面的元素。
```
示例2:
```
给定 nums = [0,0,1,1,1,1,2,3,3],

函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为0, 0, 1, 1, 2, 3, 3 。

你不需要考虑数组中超出新长度后面的元素。
```
说明:
```
为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
  print(nums[i]);
}
```
## 解法
### 思路
- 参数：
    - 下标`index`：游标指针用来遍历数组
    - 计数值`count`：用来记录重复的数字个数
    - 数组长度`len`：作为结果，同时也和`index`一起确定退出条件
- 过程：
    - 遍历数组，从下标1开始(也就是第2个元素)，并判断当前元素和前一个元素是否相等
        - 相等，count值就加1，从0开始加，每加1代表重复个数多了1，所以当为2的时候，代表重复的个数为2，也就是有3个相同的值了：
            - 查看count是否为2：
                - 是：就要进行元素的移动，将当前多出的元素一次次的交换到当前数组长度`len`的最后，同时`len--`，代表新的数组长度不包括最后这个被移动过来的重复元素了。因为移动完了多出来的这个元素，所以要`count--`
                - 不是，代表当前元素是合理的，index++，继续循环
        - 不相等：则count要置为0，代表计数值刷新了，当前这个元素没有重复值，也就是只有1个
    - 最后返回len作为结果
### 代码
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int index = 1, count = 0, len = nums.length;
        while (index < len) {
            if (nums[index] == nums[index - 1]) {
                count++;
                if (count >= 2) {
                    int tmpIndex = index;
                    while (tmpIndex < len - 1) {
                        int tmp = nums[tmpIndex];
                        nums[tmpIndex] = nums[tmpIndex + 1];
                        nums[tmpIndex + 1] = tmp;
                        tmpIndex++;
                    }
                    len--;
                    count--;
                } else {
                    index++;
                }
            } else {
                count = 0;
                index++;
            }
        }

        return len;
    }
}
```
# LeetCode_143_重排链表
## 题目
```
给定一个单链表L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
```
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例1:
```
给定链表 1->2->3->4, 重新排列为 1->4->2->3.
```
示例 2:
```
给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
```
## 解法
### 思路
- 参数：
    - `pre`：后半部分头节点的前一个节点
    - `fast`：快指针
    - `slow`：慢指针
- 过程：
    - 快慢指针找到链表中点，将`secondHead`置于链表尾部
    - 将链表后半部分倒置
    - 遍历两个链表，依次穿插着连接:
        - 循环过程中保存两个链表节点的next副本，用来迭代使用
        - 同时将两个部分的当前节点间隔着串在一起
        - 退出条件是，第一部分的节点为空
### 代码
```java
class Solution {
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head, P = null;

        while (fast != null && fast.next != null) {
            P = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (slow != null && P != null) {
            P.next = null;
            ListNode pre = null;

            while (slow != null) {
                ListNode next = slow.next;
                slow.next = pre;
                pre = slow;
                slow = next;
            }

            ListNode start = head;
            while (start != null) {
                ListNode firstNext = start.next;
                ListNode secondNext = pre.next;

                start.next = pre;
                if (firstNext != null) {
                    pre.next = firstNext;
                }

                start = firstNext;
                pre = secondNext;
            }
        }
    }
}
```
# LeetCode_553_最优除法
## 题目
给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如，[2,3,4] -> 2 / 3 / 4 。

但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式。你的表达式不应该含有冗余的括号。

示例：
```
输入: [1000,100,10,2]
输出: "1000/(100/10/2)"
解释:
1000/(100/10/2) = 1000/((100/10)/2) = 200
但是，以下加粗的括号 "1000/((100/10)/2)" 是冗余的，
因为他们并不影响操作的优先级，所以你需要返回 "1000/(100/10/2)"。
```
其他用例:
```
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2
```
说明:
```
输入数组的长度在 [1, 10] 之间。
数组中每个元素的大小都在 [2, 1000] 之间。
每个测试用例只有一个最优除法解。
```
## 解法
### 思路
记忆化搜索：
- 将数组分成左右两部分，求得符合题意的最大值的过程就是左边为最大值，右边为最小值
- 通过递归的方式求解左右部分的最优解
- 定义一个类`R`用来封装最大值`max`，最大值的字符串`maxStr`，最小值`min`，最小值字符串`minStr`，并用来返回最为递归的返回值
- 参数：
    - 起始坐标：`start`
    - 结束坐标：`end`
    - 数组：`nums`
    - 记忆缓存：`memo[][]`
- 递归：
    - 退出条件：
        - `start`和`end`相等，说明当前元素只有一个，不能分为两部分，此时就返回一个R
        - `memo`中有值就直接返回
    - 过程:
        - 初始化R，并初始化最大值`max`和最小值`min`
        - 从`start`开始遍历，将数组分成`[start, i]`和`[i + 1, end]`，递归求解左边部分`left`和右边部分`right`的R值
        - 如果`left.max / right.min`的小于`R.min`，或者大于的最大值`R.max`，就更新R值
        - 更新过程中，字符串需要判断`start`和`end`的是否差1，也就是是否只有2个元素，如果是，就不需要在right部分增加括号
        - 循环结束后，返回R
- 返回：R的maxStr
### 代码
```java
class Solution {
    public String optimalDivision(int[] nums) {
        R[][] memo = new R[nums.length + 1][nums.length + 1];
        R r = recurse(nums, 0, nums.length - 1, memo);
        return r.maxStr;
    }

    private R recurse(int[] nums, int start, int end, R[][] memo) {
        if (memo[start][end] != null) {
            return memo[start][end];
        }

        if (start == end) {
            R r = new R();
            r.max = nums[start];
            r.min = nums[start];
            r.maxStr = "" + nums[start];
            r.minStr = "" + nums[start];
            memo[start][end] = r;
            return r;
        }

        R r = new R();
        r.max = Double.MIN_VALUE;
        r.min = Double.MAX_VALUE;
        r.maxStr = r.minStr = "";

        for (int i = start; i < end; i++) {
            R left = recurse(nums, start, i, memo);
            R right = recurse(nums, i + 1, end, memo);

            if (r.max < (left.max / right.min)) {
                r.max = left.max / right.min;
                r.maxStr = left.maxStr + "/" + (i + 1 != end ? "(" : "") + right.minStr + (i + 1 != end ? ")" : "");
            }

            if (r.min > (left.min / right.max)) {
                r.min = left.min / right.max;
                r.minStr = left.minStr + "/" + (i + 1 != end ? "(" : "") + right.maxStr + (i + 1 != end ? ")" : "");
            }
        }

        memo[start][end] = r;
        return r;
    }

    private class R {
        private double max;
        private double min;
        private String maxStr;
        private String minStr;
    }
}
```