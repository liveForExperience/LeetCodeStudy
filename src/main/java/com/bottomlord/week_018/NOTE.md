# LeetCode_1110_删点成林
## 题目
给出二叉树的根节点 root，树上每个节点都有一个不同的值。

如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。

返回森林中的每棵树。你可以按任意顺序组织答案。

示例：
```
输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
输出：[[1,2,null,4],[6],[7]]
```
提示：
```
树中的节点数最大为 1000。
每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
to_delete.length <= 1000
to_delete 包含一些从 1 到 1000、各不相同的值。
```
## 解法
### 思路
- 把to_delete数组转换成set，方便查找
- dfs遍历二叉树
    - 如果节点为null，返回null
    - 否则就继续递归搜索，并在当前层用左右子树指针接收递归返回的结果
    - 这里必须是后序遍历，先递归到叶子节点再开始判断，否则子树的一些需要删除的节点就没法被删除了，结果会有问题
    - 如果节点值为to_delete中的值，返回null，并将左右非空子树作为根节点，放入结果list中
- 最后返回结果list，但返回前需要判断根节点是否被删除，如果返回是null，必需过滤掉
### 代码
```java
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (Integer val : to_delete) {
            set.add(val);
        }
        
        TreeNode node = dfs(root, set, ans);
        if (node != null) {
            ans.add(node);
        }
        
        return ans;
    }

    private TreeNode dfs(TreeNode node, Set<Integer> toDelete, List<TreeNode> ans) {
        if (node == null) {
            return null;
        }

        node.left = dfs(node.left, toDelete, ans);
        node.right = dfs(node.right, toDelete, ans);

        if (toDelete.contains(node.val)) {
            if (node.left != null) {
                ans.add(node.left);
            }

            if (node.right != null) {
                ans.add(node.right);
            }

            return null;
        }
        return node;
    }
}
```
# LeetCode_983_最低票价
## 题目
在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。

火车票有三种不同的销售方式：
```
一张为期一天的通行证售价为 costs[0] 美元；
一张为期七天的通行证售价为 costs[1] 美元；
一张为期三十天的通行证售价为 costs[2] 美元。
通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
```
返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。

示例 1：
```
输入：days = [1,4,6,7,8,20], costs = [2,7,15]
输出：11
解释： 
例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
你总共花了 $11，并完成了你计划的每一天旅行。
```
示例 2：
```
输入：days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
输出：17
解释：
例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划： 
在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。
在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。 
你总共花了 $17，并完成了你计划的每一天旅行。
```
提示：
```
1 <= days.length <= 365
1 <= days[i] <= 365
days 按顺序严格递增
costs.length == 3
1 <= costs[i] <= 1000
```
## 解法
### 思路
动态规划：
- dp(i)：第i天到最后一天的最小票价总和
- 状态转移方程：dp(i) = Math.min(dp(i + 1) + cost[0], dp(i + 7) + cost[1], dp(i + 30) + cost[2])
- 递归返回最后的结果
- 需要记忆化搜索，否则会超时
### 代码
```java
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        Set<Integer> set = new HashSet<>();
        for (int day : days) {
            set.add(day);
        }
        return dp(0, costs, set, new Integer[366]);
    }

    private int dp(int day, int[] costs, Set<Integer> set, Integer[] memo) {
        if (day > 365) {
            return 0;
        }

        if (memo[day] != null) {
            return memo[day];
        }
        
        int ans;
        if (set.contains(day)) {
            ans = Math.min(dp(day + 1, costs, set, memo) + costs[0], dp(day + 7, costs, set, memo) + costs[1]);
            ans = Math.min(ans, dp(day + 30, costs, set, memo) + costs[2]);
        } else {
            ans = dp(day + 1, costs, set, memo);
        }
        
        memo[day] = ans;
        return ans;
    }
}
```
## 优化代码
### 思路
- 在动态规划的过程中，真正需要计算的是days中的元素代表的日期，所以不需要像解法一中一样碰到days中没有的日期就进行+1的递归，直接可以跳到下一个days的日期。
- dp[i]表示从i天到最后旅行计划的最小花费
- 状态转移方程是：dp(i) = min(dp(j1) + costs(0), dp(j7) + cost(1), dp(j30) + cost(2))
    - dp(j1)代表最小的下标满足 days[j1] > days[i] + 1
    - dp(j7)代表最小的下标满足 days[j7] > days[i] + 7
    - dp(j30)代表最小的下标满足 days[j30] > days[i] + 30
    - 也就意味着dp(i)是求三种(刚刚超过当前旅行日期的dp值 + 当前方案)的和中的最小值，我这个方案已经cover了这些需要旅行的日期了，那么下一个日期的最小值方案是多少？算一下，递归返回回来
- 递归到days的最后一天，计算3中票价中的最低价，然后返回过程中再不断与前一个日期的dp进行比较，直到返回到第一个元素，货的最小值。
### 代码
```java
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int[] durations = new int[]{1, 7, 30};
        return dp(0, days, costs, durations, new Integer[days.length]);
    }

    private int dp(int index, int[] days, int[] costs, int[] durations, Integer[] memo) {
        if (index >= days.length) {
            return 0;
        }

        if (memo[index] != null) {
            return memo[index];
        }
        
        int tmp = index, ans = Integer.MAX_VALUE;
        for (int k = 0; k < 3; k++) {
            while (tmp < days.length && days[tmp] < days[index] + durations[k]) {
                tmp++;
            }
            
            ans = Math.min(ans, dp(tmp, days, costs, durations, memo) + costs[k]);
        }
        
        memo[index] = ans;
        return ans;
    }
}
```
# LeetCode_454_四数相加II
## 题目
给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

例如:
```
输入:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

输出:2

解释:
两个元组如下:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
```
## 解法
### 思路
把4个数分成两部分：
- A和B数组嵌套循环算出结果，放入哈希表，并计数
- C和D数组嵌套循环，计算两个元素的和的负值，如果在散列表中找到相应的key，就累加value
- 循环结束返回累加值
### 代码
```java
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        
        int ans = 0;
        for (int c : C) {
            for (int d : D) {
                int sum = -(c + d);
                ans += map.getOrDefault(sum, 0);
            }
        }
        
        return ans;
    }
}
```
# LeetCode_1140_石子游戏II
## 题目
亚历克斯和李继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。

亚历克斯和李轮流进行，亚历克斯先开始。最初，M = 1。

在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。

游戏一直持续到所有石子都被拿走。

假设亚历克斯和李都发挥出最佳水平，返回亚历克斯可以得到的最大数量的石头。

示例：
```
输入：piles = [2,7,9,4,4]
输出：10
解释：
如果亚历克斯在开始时拿走一堆石子，李拿走两堆，接着亚历克斯也拿走两堆。在这种情况下，亚历克斯可以拿到 2 + 4 + 4 = 10 颗石子。 
如果亚历克斯在开始时拿走两堆石子，那么李就可以拿走剩下全部三堆石子。在这种情况下，亚历克斯可以拿到 2 + 7 = 9 颗石子。
所以我们返回更大的 10。 
```
提示：
```
1 <= piles.length <= 100
1 <= piles[i] <= 10 ^ 4
```
## 解法
### 思路
动态规划：
- `dp(i,j)`：代表还剩第i堆到最后一堆石子的情况下，M为j时，可以获得最大石子数
- base case：
    - i >= n：代表石子已经拿完的情况，返回0
    - i + 2M >= n：代表这是最理智情况下，最后一次可以拿石子的状态，返回sum[i]，代表所有剩下石子的总和
- 状态转移方程：dp(i, M) = 所有`1 <= x <= 2M`情况下，sum[i] - dp(i + x, max(x, M))的最大值
- 使用一个memo进行记忆化搜索
### 代码
```java
class Solution {
    public int stoneGameII(int[] piles) {
        int len = piles.length;
        int[] sum = new int[len];
        Integer[][] memo = new Integer[len + 1][len + 1];
        sum[len - 1] = piles[len - 1];
        for (int i = piles.length - 2; i >= 0; i--) {
            sum[i] = sum[i + 1] + piles[i];
        }
        return dp(0, 1, sum, len, memo);
    }

    private int dp(int i, int j, int[] sum, int len, Integer[][] memo) {
        if (i >= len) {
            return 0;
        }

        if (i + 2 * j >= len) {
            return sum[i];
        }
        
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int best = 0;
        for (int k = 1; k <= 2 * j; k++) {
            best = Math.max(best, sum[i] - dp(i + k, Math.max(k, j), sum, len, memo));
        }
        
        memo[i][j] = best;
        return best;
    }
}
```
# LeetCode_684_冗余连接
## 题目
在本问题中, 树指的是一个连通且无环的无向图。

输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。

结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。

返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。

示例 1：
```
输入: [[1,2], [1,3], [2,3]]
输出: [2,3]
解释: 给定的无向图为:
  1
 / \
2 - 3
```
示例 2：
```
输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
输出: [1,4]
解释: 给定的无向图为:
5 - 1 - 2
    |   |
    4 - 3
```
注意:
```
输入的二维数组大小在 3 到 1000。
二维数组中的整数在1到N之间，其中N是输入数组的大小。
```
## 解法
### 思路
并查集：
- 如果是树，所有节点都会指向同一个根节点
- 在遍历二维数组过程中，记录每一个并查集结果不一致的边，同时将两个节点进行合并
- 遍历结束，返回最后一个并查集结果不一致的
### 代码
```java
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(edges.length);
        int[] ans = new int[2];

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];

            if (dsu.find(x - 1) == dsu.find(y - 1)) {
                ans[0] = x;
                ans[1] = y;
            } else {
                dsu.union(x - 1, y - 1);
            }
        }

        return ans;
    }
}

class DSU {
    public int[] parent;

    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; ++i)
            parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}
```
# LeetCode_609_在系统中查找重复文件
## 题目

## 解法
### 思路
- 遍历字符串数组，使用hash表记录路径、文件名和文件内容
- hash表的key存储文件内容，value存储一个list，保存路径和文件名
- 遍历结束后再遍历hash表的entrySet，如果value长度大于1，就把value内容放入结果list中
### 代码
```java
class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] factors = path.split(" ");
            String path1 = factors[0];
            for (int i = 1; i < factors.length; i++) {
                String factor = factors[i];
                String file = factor.substring(0, factor.indexOf('('));
                String content = factor.substring(factor.indexOf('('), factor.indexOf(')'));
                String wholePath = path1 + "/" + file;

                List<String> valueList = map.get(content);
                if (valueList == null) {
                    valueList = new ArrayList<>();
                    valueList.add(wholePath);
                } else {
                    valueList.add(wholePath);
                }
                
                map.put(content, valueList);
            }
        }
        
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                ans.add(entry.getValue());
            }
        }
        
        return ans;
    }
}
```
# LeetCode_676_实现一个魔法字典
## 题目
实现一个带有buildDict, 以及 search方法的魔法字典。

对于buildDict方法，你将被给定一串不重复的单词来构建一个字典。

对于search方法，你将被给定一个单词，并且判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。

示例 1:
```
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False
```
注意:
```
你可以假设所有输入都是小写字母 a-z。
为了便于竞赛，测试所用的数据量很小。你可以在竞赛结束后，考虑更高效的算法。
请记住重置MagicDictionary类中声明的类变量，因为静态/类变量会在多个测试用例中保留。 请参阅这里了解更多详情。
```
## 解法
### 思路
- 遍历字符串数组，使用hash表存储存储，`key`为字符串长度，`value`为长度一样的字符串list
- `search`时：
    - 如果hash表中没有相同长度的字符串，返回false
    - 如果长度相同，查看是否有字符差异为1个之内的字符串，如果有就返回true
    - 如果遍历完list没有找到，就返回false
### 代码
```java
class MagicDictionary {
    private Map<Integer, List<String>> map;
    public MagicDictionary() {
        map = new HashMap<>();
    }

    public void buildDict(String[] dict) {
        for (String word : dict) {
            map.computeIfAbsent(word.length(), x -> new ArrayList<>()).add(word);
        }
    }

    public boolean search(String word) {
        if (!map.containsKey(word.length())) {
            return false;
        }

        for (String letter : map.get(word.length())) {
            int count = 0;
            for (int i = 0; i < word.length(); i++) {
                if (letter.charAt(i) != word.charAt(i)) {
                    if (++count > 1) {
                        break;
                    }
                }
            }

            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
```
# LeetCode_75_颜色分类
## 题目
给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:
```
不能使用代码库中的排序函数来解决这道题。
```
示例:
```
输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
```
进阶：
```
一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
你能想出一个仅使用常数空间的一趟扫描算法吗？
```
## 解法
### 思路
遍历两次数组：
- 第一次记录3种颜色的个数
- 第二次根据个数重新对数组赋值
### 代码
```java
class Solution {
    public void sortColors(int[] nums) {
       int[] counts = new int[3];
        for (int num : nums) {
            counts[num]++;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < counts.length; j++) {
                if (counts[j] > 0) {
                    nums[i] = j;
                    counts[j]--;
                    break;
                }
            }
        } 
    }
}
```
## 解法二
### 思路
- 使用的变量：
    - head：起始从0开始
    - tail：起始从n-1开始
    - cur：起始从0开始
- 过程：
    - 循环数组，退出条件为`cur > tail`
    - 如果`cur`指向的元素为0，就和`head`交换，同时`head++`，同时为了保证`cur`不会落后于`head`，`cur`也需要自增
    - 如果`cur`指向的元素为2，就和`tail`交换，同时`tail--`
    - 如果`cur`指向的元素为1，就`cur++`
### 代码
```java
class Solution {
        public void sortColors(int[] nums) {
        int head = 0, cur = 0, tail = nums.length - 1;
        while (cur <= tail) {
            if (nums[cur] == 0) {
                swap(nums, head, cur);
                cur++;
                head++;
            } else if (nums[cur] == 2) {
                swap(nums, tail, cur);
                tail--;
            } else {
                cur++;   
            }
        }
    }
    
    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
```
# LeetCode_86_分隔链表
## 题目
给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。

你应当保留两个分区中每个节点的初始相对位置。

示例:
```
输入: head = 1->4->3->2->5->2, x = 3
输出: 1->2->2->4->3->5
```
## 解法
### 思路
- 使用2个节点指针：
    - 一个指向小于目标数的元素
    - 一个指向大于目标数的元素
- 遍历链表，根据与目标数的关系指向不同的指针
- 最终将2个指针依次连接
### 代码
```java
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode lessStart = new ListNode(0), less = lessStart, moreStart = new ListNode(0), more = moreStart;
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                more.next = head;
                more = more.next;
            }
            
            head = head.next;
        }
        
        more.next = null;
        
        less.next = moreStart.next;
        
        return lessStart.next;
    }
}
```
# LeetCode_1006_检查替换后的词是否有效
## 题目
给定有效字符串 "abc"。

对于任何有效的字符串 V，我们可以将 V 分成两个部分 X 和 Y，使得 X + Y（X 与 Y 连接）等于 V。（X 或 Y 可以为空。）那么，X + "abc" + Y 也同样是有效的。

例如，如果 S = "abc"，则有效字符串的示例是："abc"，"aabcbc"，"abcabc"，"abcabcababcc"。无效字符串的示例是："abccba"，"ab"，"cababc"，"bac"。

如果给定字符串 S 有效，则返回 true；否则，返回 false。

示例 1：
```
输入："aabcbc"
输出：true
解释：
从有效字符串 "abc" 开始。
然后我们可以在 "a" 和 "bc" 之间插入另一个 "abc"，产生 "a" + "abc" + "bc"，即 "aabcbc"。
```
示例 2：
```
输入："abcabcababcc"
输出：true
解释：
"abcabcabc" 是有效的，它可以视作在原串后连续插入 "abc"。
然后我们可以在最后一个字母之前插入 "abc"，产生 "abcabcab" + "abc" + "c"，即 "abcabcababcc"。
```
示例 3：
```
输入："abccba"
输出：false
```
示例 4：
```
输入："cababc"
输出：false
```
提示：
```
1 <= S.length <= 20000
S[i] 为 'a'、'b'、或 'c'
```
## 解法
### 思路
通过不断删除`abc`，直到不能删除为止，如果是空字符串就是true，否则就是false
### 代码
```java
class Solution {
    public boolean isValid(String S) {
        while (S.contains("abc")) {
            S = S.replaceAll("abc", "");
        }
        return Objects.equals(S, "");
    }
}
```
## 解法二
### 思路
使用栈：
- 遍历字符串，将非`c`字符放入栈中
- 如果遇到的是`c`：
    - 查看栈中是否已有2个元素，如果没有，就说明不是标准字符串，返回false
    - 依次弹栈，如果不是`b`和`a`，就说明不是标准字符串，返回false
    - 遍历结束后，如果栈大小不是0，说明整个字符串不是`x`+`abc`+`y`这样组合，返回false
    - 其他情况返回true
### 代码
```java
class Solution {
    public boolean isValid(String S) {
        char[] cs = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        
        for (char c : cs) {
            if (c != 'c') {
                stack.push(c);
            } else {
                if (stack.size() < 2) {
                    return false;
                } else {
                    char c1 = stack.pop();
                    if (c1 != 'b') {
                        return false;
                    }
                    
                    char c2 = stack.pop();
                    if (c2 != 'a') {
                        return false;
                    }
                }
            }
        }
        
        return stack.size() == 0;
    }
}
```
## 优化代码
### 思路
使用数组代替栈，使用下标index来模拟栈的弹和压的动作
### 代码
```java
class Solution {
    public boolean isValid(String S) {
        char[] cs = S.toCharArray(), stack = new char[S.length()];
        int index = 0;
        
        for (char c : cs) {
            if (c != 'c') {
                stack[index++] = c;
            } else {
                if (index < 2) {
                    return false;
                } else {
                    if (stack[--index] != 'b') {
                        return false;
                    }
                    
                    if (stack[--index] != 'a') {
                        return false;
                    }
                }
            }
        }
        
        return index == 0;
    }
}
```