# LeetCode_486_预测赢家
## 题目
给定一个表示分数的非负整数数组。 玩家1从数组任意一端拿取一个分数，随后玩家2继续从剩余数组任意一端拿取分数，然后玩家1拿，……。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。

给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。

示例 1:
```
输入: [1, 5, 2]
输出: False
解释: 一开始，玩家1可以从1和2中进行选择。
如果他选择2（或者1），那么玩家2可以从1（或者2）和5中进行选择。如果玩家2选择了5，那么玩家1则只剩下1（或者2）可选。
所以，玩家1的最终分数为 1 + 2 = 3，而玩家2为 5。
因此，玩家1永远不会成为赢家，返回 False。
```
示例 2:
```
输入: [1, 5, 233, 7]
输出: True
解释: 玩家1一开始选择1。然后玩家2必须从5和7中进行选择。无论玩家2选择了哪个，玩家1都可以选择233。
最终，玩家1（234分）比玩家2（12分）获得更多的分数，所以返回 True，表示玩家1可以成为赢家。
```
注意:
```
1 <= 给定的数组长度 <= 20.
数组里所有分数都为非负数且不会大于10000000。
如果最终两个玩家的分数相等，那么玩家1仍为赢家。
```
## 解法
### 思路
递归，穷举所有的可能性：
- 整个样本空间可以组成一颗二叉树，从底部往上返回取头或取尾两种可能中的最大值
- 返回的时候还要判断是1还是2取值
    - 如果是1取值，就是正的
    - 如果是2取值，就是负的
    - 需要注意返回的值与当前层选择的值相加后，要考虑到负数的情况，因为每一次选择都对于选择人的最优解，所以负数需要先变为整数取两数的最大值，然后再变回来
- 最终返回到根节点时，看值是否大于等于0
### 代码
```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return dfs(nums, 0, nums.length - 1, 1) >= 0;
    }

    private int dfs(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return turn * nums[start];
        }

        int left = turn * nums[start] + dfs(nums, start + 1, end, -turn);
        int right = turn * nums[end] + dfs(nums, start, end - 1, -turn);

        return turn * Math.max(turn * left, turn * right);
    }
}
```
## 解法二
### 思路
使用动态规划：
- `dp[i][j]`：代表`i`和`j`范围内的数组中，当前选手能够获得的最大分数差
- 状态转移方程：`dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])` 
- base case：`dp[i][i] = nums[i]`
- 最终返回：`dp[i][j] >= 0`
### 代码
```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] >= 0;
    }
}
```
## 优化代码
### 思路
解法二中，状态转移只和i行和i-1行有关，所以只使用一维就可以保存需要的状态
> 无法完全理解
### 代码
```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];

        System.arraycopy(nums, 0, dp, 0, len);

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j -1]);
            }
        }

        return dp[len - 1] >= 0;
    }
}
```
# LeetCode_357_计算各个位数不同的数字个数
## 题目
给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。

示例:
```
输入: 2
输出: 91 
解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
```
## 解法
### 思路
- 归纳：
    - 如果`n == 0`：那只有1种可能就是0
    - 如果`n > 10`：那么不可能有任何数是由不同数字组成的，所以可能的数字和10位时可能的数字是一样的
    - 如果`n > 0`且`n <= 10`：那么可能的数字就是`<=n`的所有位数的排列的和
- 所以可以定义动态规划：
    - `dp[n]`：代表参数为n的情况下，所有符合题目要求的可能值的总和
    - 状态转移方程：`dp[n] = dp[n - 1] + fun(n)`
    - `fun(n)`的定义：
        - 如果`n == 1`，返回9
        - 如果`n > 1`，返回`9 * P (n-1) (9)` 
    - base case：`dp[0] = 1`
    - 返回结果：`dp[n]`
### 代码
```java
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        
        int[] dp = new int[11];
        dp[0] = 1;
        
        n = Math.min(n, 10);
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + fun(i);
        }
        
        return dp[n];
    }
    
    private int fun(int n) {
        if (n == 1) {
            return 9;
        }
        
        int ans = 9, num = 9;
        for (int i = 1; i < n; i++) {
            ans *= num--;
        }
        
        return ans;
    }
}
```
# LeetCode_1031_两个非重叠子数组的最大和
## 题目
给出非负整数数组 A ，返回两个非重叠（连续）子数组中元素的最大和，子数组的长度分别为 L 和 M。（这里需要澄清的是，长为 L 的子数组可以出现在长为 M 的子数组之前或之后。）

从形式上看，返回最大的 V，而 V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) 并满足下列条件之一：
```
0 <= i < i + L - 1 < j < j + M - 1 < A.length, 或
0 <= j < j + M - 1 < i < i + L - 1 < A.length.
```
示例 1：
```
输入：A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
输出：20
解释：子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
```
示例 2：
```
输入：A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
输出：29
解释：子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。
```
示例 3：
```
输入：A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
输出：31
解释：子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。
```
提示：
```
L >= 1
M >= 1
L + M <= A.length <= 1000
0 <= A[i] <= 1000
```
## 解法
### 思路
动态规划：
- 数组根据L和M可以划分为2个窗口
- `dp[i][j]`，j分为4种情况：
    - 0：起始到`i - 1`的范围，连续个数为L
    - 1：起始到`i - 1`的范围，连续个数为M
    - 2：`i`到结尾的范围，连续个数为L
    - 3：`i`到结尾的范围，连续个数为M   
- 状态转移方程：`dp[i][j] = dp[i][j] - dp[i - L][j] + A[i]`
- base case：
    - `j == 0`：`dp[L - 1][j] = sum(0, L - 1)` 
    - `j == 1`：`dp[M - 1][j] = sum(0, M - 1)`
    - `j == 2`：`dp[L][j] = sum(L, A.length)`
    - `j == 3`：`dp[M][j] = sum(M, A.length)`
- 返回结果：计算如下两种情况下的最大值
    - `j == 0`和`j == 2`
    - `j == 1`和`j == 3`
### 代码
```java
class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[][] dp = new int[A.length][4];
        int len = A.length, pre = 0, max = 0;
        for (int i = 0; i < L; i++) {
            pre += A[i];
        }
        max = pre;
        dp[L - 1][0] = pre;
        for (int i = L; i < len; i++) {
            pre -= A[i - L];
            pre += A[i];
            max = Math.max(pre, max);
            dp[i][0] = max;
        }

        pre = 0;
        for (int i = 0; i < M; i++) {
            pre += A[i];
        }
        max = pre;
        dp[M - 1][1] = pre;
        for (int i = M; i < len; i++) {
            pre -= A[i - M];
            pre += A[i];
            max = Math.max(max, pre);
            dp[i][1] = max;
        }

        pre = 0;
        for (int i = len - 1; i >= len - L; i--) {
            pre += A[i];
        }
        max = pre;
        dp[len - L][2] = pre;
        for (int i = len - L - 1; i >= 0; i--) {
            pre -= A[i + L];
            pre += A[i];
            max = Math.max(max, pre);
            dp[i][2] = max;
        }

        pre = 0;
        for (int i = len - 1; i >= len - M; i--) {
            pre += A[i];
        }
        max = pre;
        dp[len - M][3] = pre;
        for (int i = len - M - 1; i >= 0; i--) {
            pre -= A[i + M];
            pre += A[i];
            max = Math.max(max, pre);
            dp[i][3] = max;
        }

        int ans = 0;
        for (int i = L; i <= len - M; i++) {
            ans = Math.max(ans, dp[i - 1][0] + dp[i][3]);
        }

        for (int i = M; i <= len - L; i++) {
            ans = Math.max(ans, dp[i - 1][1] + dp[i][2]);
        }

        return ans;
    }
}
```
# LeetCode_1019_链表中的下一个更大节点
## 题目
给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。

每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0 。

返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。

注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。

示例 1：
```
输入：[2,1,5]
输出：[5,5,0]
```
示例 2：
```
输入：[2,7,4,3,5]
输出：[7,0,5,5,0]
```
示例 3：
```
输入：[1,7,5,1,9,2,5,1]
输出：[7,9,9,9,0,5,0,0]
```
提示：
```
对于链表中的每个节点，1 <= node.val <= 10^9
给定列表的长度在 [0, 10000] 范围内
```
## 解法
### 思路
暴力解法：
- 遍历链表，计算链表长度，生成结果数组`ans`
- 双层循环：
    - 外层遍历整个链表
    - 内层从遍历到的链表开始，比较与外层链表节点的值，如果遇到更大的值就记录在`ans`中，否则返回0
### 代码
```java
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        int len = 0, index = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        
        int[] ans = new int[len];
        node = head;
        while (node != null) {
            int val = node.val, larger = 0;
            ListNode innerNode = node.next;
            while (innerNode != null) {
                if (innerNode.val > val) {
                    larger = innerNode.val;
                    break;
                }
                innerNode = innerNode.next;
            }
            
            ans[index++] = larger;
            node = node.next;
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
单调栈：
- 遍历链表生成list
- 初始化栈`stack`和结果数组`ans`
- 遍历list，如果`stack`不为空，且栈顶所存的索引值小于当前遍历到的值，弹栈，并将当前值放入`ans`中下标为弹出栈元素的位置，并继续用循环判断
    - 如上的逻辑代表，我遍历到的值，看看栈顶元素是否比当前值小，小就意味着当前值是这个下标元素对应的符合题目要求的值
    - 而能够只看栈顶就能确定是的原因是：每次都会执行将小于当前值的元素弹栈的动作，那么最终留在栈中的就都是比当前值大的元素了，所以这个栈就是一个从底开始单调递减的栈
    - 既然是单调递减，那么只要遍历到的值比栈顶元素大，就天然可以不断的弹栈来完成题目的要求
- 如果不再符合如上条件，退出了内部循环，就将当前值额索引压入栈中
### 代码
```java
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        
        Stack<Integer> stack = new Stack<>();
        int len = list.size();
        int[] ans = new int[len];
        
        for (int i = 0; i < len; i++) {
            int val = list.get(i);
            
            while (!stack.isEmpty() && list.get(stack.peek()) < val) {
                ans[stack.pop()] = val;
            }
            
            stack.push(i);
        }
        
        return ans;
    }
}
```
## 优化代码
### 思路
使用数组代替栈实现一个单调栈，逻辑和解法二基本一致。
### 代码
```java
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }

        int[] stack = new int[2 * len], ans = new int[len];
        int index = 0, i = 0;
        node = head;
        while (node != null) {
            while (index != 0 && stack[index - 2] < node.val) {
                ans[stack[--index]] = node.val;
                index--;
            }

            stack[index++] = node.val;
            stack[index++] = i;

            i++;
            node = node.next;
        }

        return ans;
    }
}
```
# LeetCode_1268_搜索推荐系统
## 题目
给你一个产品数组 products 和一个字符串 searchWord ，products  数组中每个产品都是一个字符串。

请你设计一个推荐系统，在依次输入单词 searchWord 的每一个字母后，推荐 products 数组中前缀与 searchWord 相同的最多三个产品。如果前缀相同的可推荐产品超过三个，请按字典序返回最小的三个。

请你以二维列表的形式，返回在输入 searchWord 每个字母后相应的推荐产品的列表。

示例 1：
```
输入：products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
输出：[
["mobile","moneypot","monitor"],
["mobile","moneypot","monitor"],
["mouse","mousepad"],
["mouse","mousepad"],
["mouse","mousepad"]
]
解释：按字典序排序后的产品列表是 ["mobile","moneypot","monitor","mouse","mousepad"]
输入 m 和 mo，由于所有产品的前缀都相同，所以系统返回字典序最小的三个产品 ["mobile","moneypot","monitor"]
输入 mou， mous 和 mouse 后系统都返回 ["mouse","mousepad"]
```
示例 2：
```
输入：products = ["havana"], searchWord = "havana"
输出：[["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
```
示例 3：
```
输入：products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
输出：[["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
```
示例 4：
```
输入：products = ["havana"], searchWord = "tatiana"
输出：[[],[],[],[],[],[],[]]
```
提示：
```
1 <= products.length <= 1000
1 <= Σ products[i].length <= 2 * 10^4
products[i] 中所有的字符都是小写英文字母。
1 <= searchWord.length <= 1000
searchWord 中所有字符都是小写英文字母。
在真实的面试中遇到过这道题？
```
## 解法
### 思路
字典树：
- 初始化字典树节点：
    - `boolean end`：叶子节点标识
    - `String product`：保存的字符串
    - `TrieNode[] children`：保存子节点
    - `int count`：重复字符串的个数
- 遍历`products`字符串数组，为每个字符串初始化为字典树
- 循环`searchWord`的长度次数：
    - 将`searchWord`根据遍历到的下标进行0到小标的截取，将截取的字符串放入字典树中查找
    - 先遍历到截取字符串的节点位置
    - 然后从这个节点开始dfs遍历，将遍历到的字符串放入list中
    - 遍历结束后将list放入结果list`ans`中
- 外层循环结束后将`ans`返回
### 代码
```java

```