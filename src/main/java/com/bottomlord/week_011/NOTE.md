# LeetCode_763_划分字母区间
## 题目
字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。

示例 1:
```
输入: S = "ababcbacadefegdehijhklij"
输出: [9,7,8]
解释:
划分结果为 "ababcbaca", "defegde", "hijhklij"。
每个字母最多出现在一个片段中。
像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
```
注意:
```
S的长度在[1, 500]之间。
S只包含小写字母'a'到'z'。
```
## 解法
### 思路
贪心算法
- 定义变量：
    - 起始坐标start
    - 结束坐标end
- 遍历字符数组，查找当前字符的下一个同样字符的下标
- 如果有，将该坐标作为end
- 如果没有，且当前字符的坐标超过end：
    - 将index - start + 1放入ans
    - 将end + 1 作为新的start
- 否则直接跳过
### 代码
```java
class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        char[] cs = S.toCharArray();
        int start = 0, end = 0;
        for (int i = 0; i < cs.length; i++) {
            int next = S.indexOf(cs[i], i + 1);
            if (next == -1 && i >= end) {
                ans.add(i - start + 1);
                start = i + 1;
            } else if (next > end) {
                end = next;
            }
        }
        return ans;
    }
}
```
# LeetCode_39_1_组合总和
## 题目
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：
```
所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
```
示例 1:
```
输入: candidates = [2,3,6,7], target = 7,
所求解集为:
[
  [7],
  [2,2,3]
]
```
示例 2:
```
输入: candidates = [2,3,5], target = 8,
所求解集为:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```
## 解法
### 思路
回溯算法
### 代码
```java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(ans, new ArrayList<>(), candidates, target,0, 0);
        return ans;
    }

    private void backTrack(List<List<Integer>> ans, List<Integer> list, int[] candidates, int target, int index,  int sum) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            backTrack(ans, list, candidates, target, i, sum + candidates[i]);
            list.remove(list.size() - 1);
        }
    }
}
```
# LeetCode_921_使括号有效的最少添加
## 题目
给定一个由 '(' 和 ')' 括号组成的字符串 S，我们需要添加最少的括号（ '(' 或是 ')'，可以在任何位置），以使得到的括号字符串有效。

从形式上讲，只有满足下面几点之一，括号字符串才是有效的：
```
它是一个空字符串，或者
它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
它可以被写作 (A)，其中 A 是有效字符串。
给定一个括号字符串，返回为使结果字符串有效而必须添加的最少括号数。
```
示例 1：
```
输入："())"
输出：1
```
示例 2：
```
输入："((("
输出：3
```
示例 3：
```
输入："()"
输出：0
```
示例 4：
```
输入："()))(("
输出：4
```
提示：
```
S.length <= 1000
S 只包含 '(' 和 ')' 字符。
```
## 解法
### 思路
- 定义两个变量：
    - left：记录左括号个数
    - rightH：记录无法被抵消的右括号个数
- 遍历字符数组
    - 是`(`就`left++`
    - 是`)`就判断`left>0`：
        - 是就`left--`
        - 不是就说明无法被抵消了，`rightH++`
- 最后返回两个变量的总和
### 代码
```java
class Solution {
    public int minAddToMakeValid(String S) {
        int rightH = 0, left = 0;
        char[] cs = S.toCharArray();
        for(int i = 0; i < cs.length; i++) {
            if (cs[i] == '(') {
                left++;
            } else {
                if (left > 0) {
                    left--;
                } else {
                    rightH++;
                }
            }
        }

        return left + rightH;
    }
}
```
# LeetCode_513_找树左下角的值
## 题目
给定一个二叉树，在树的最后一行找到最左边的值。

示例 1:
```
输入:

    2
   / \
  1   3

输出:
1
```
示例 2:
```
输入:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

输出:
7
```
```
注意: 您可以假设树（即给定的根节点）不为 NULL。
```
## 解法
### 思路
bfs遍历，更新记录每一行的最左边的元素，当队列为空时，返回最后记录的那个元素的值
### 代码
```java
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int left = root.val;

        while (!queue.isEmpty()) {
            TreeNode leftNode = queue.poll();
            left = leftNode.val;
            int size = queue.size();

            if (leftNode.left != null) {
                queue.offer(leftNode.left);
            }

            if (leftNode.right != null) {
                queue.offer(leftNode.right);
            }
            
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return left;
    }
}
```
## 解法二
### 思路
dfs遍历，因为深度优先优先搜索左下，所以只要第一个下钻到新的一层的节点，一定是最下角的元素
### 代码
```java
class Solution {
    private int maxLevel = 0;
    private int result = 0;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 1);
        return result;
    }

    private void dfs(TreeNode node, int level) {
        if (node.left == null && node.right == null) {
            if (level > maxLevel) {
                maxLevel = level;
                result = node.val;
            }
            return;
        }
        
        if (node.left != null) {
            dfs(node.left, level + 1);
        }
        
        if (node.right != null) {
            dfs(node.right, level + 1);
        }
    }
}
```
# LeetCode_260_只出现一次的数字III
## 题目
给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。

示例 :
```
输入: [1,2,1,3,2,5]
输出: [3,5]
```
注意：
```
结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
```
## 解法
### 思路
- 排序
- 遍历，找到前后不一致的元素，记录
### 代码
```java
class Solution {
    public int[] singleNumber(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; ) {
            if (i + 1 == nums.length) {
                list.add(nums[i]);
                break;
            }
            
            if (nums[i] == nums[i + 1]) {
                i += 2;
            } else {
                list.add(nums[i]);
                i++;
            }
        }
        return new int[]{list.get(0), list.get(1)};
    }
}
```
## 解法二
### 思路
使用set来判断是否有重复，如果有就删除，遍历结束，把存在的两个数字返回
### 代码
```java
class Solution {
    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        
        Integer[] arr = set.toArray(new Integer[0]);
        return new int[]{arr[0], arr[1]};
    }
}
```
## 解法三
### 思路
- 位运算，因为有两个不重复的数字，所以通过异或操作最终会得到这两个数的异或值
- 通过将该异或值与其相反数相与，得到一个标记值，这个值只有一位是1，这个1是两个数异或后最小位上的1，代表这两个值在这一位上不同
- 然后再遍历数组，用标记值来与数组的值进行异或，这个标记值可以通过与遍历到的数`&=`来将两个不同的数区分，能且只能获得如下值：
    - 0
    -标记值
- 将这两个区分开的值记录下来返回
### 代码
```java
class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        
        xor &= -xor;
        int[] ans = new int[2];
        for (int num : nums) {
            if ((xor & num) == 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }
        
        return ans;
    }
}
```
# LeetCode_230_二叉搜索树中第k小的元素
## 题目
给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。

说明：
你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

示例 1:
```
输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 1
```
示例 2:
```
输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 3
```
进阶：
```
如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
```
## 解法
### 思路
- 中序遍历二叉搜索树，获得升序序列
- 遍历序列获得第k个元素返回
### 代码
```java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(list, root);
        return list.get(k - 1);
    }
    
    private void dfs(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        
        dfs(list, node.left);
        list.add(node.val);
        dfs(list, node.right);
    }
}
```
## 解法二
### 思路
在dfs过程中记录遍历的次数，当次数和k值相同时直接返回
### 代码
```java
class Solution {
    private int count = 0;
    private int val = 0;
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return val;
    }
    
    private void dfs(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, k);
        if (++count == k) {
            val = node.val;
            return;
        }
        dfs(node.right, k);
    }
}
```
# LeetCode_89_格雷编码
## 题目
格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。

给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。

示例 1:
```
输入: 2
输出: [0,1,3,2]
解释:
00 - 0
01 - 1
11 - 3
10 - 2

对于给定的 n，其格雷编码序列并不唯一。
例如，[0,2,3,1] 也是一个有效的格雷编码序列。

00 - 0
10 - 2
11 - 3
01 - 1
```
示例 2:
```
输入: 0
输出: [0]
解释: 我们定义格雷编码序列必须以 0 开头。
     给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
     因此，当 n = 0 时，其格雷编码序列为 [0]。
```
## 解法
### 思路
- n = 0：[0]
- n = 1：[0,1]
- n = 2：[00,01,11,10]
- n = 3：[000,001,011,010,110,111,101,100]
由上述内容归纳可以得到，从n=1开始：
- n的排列个数是n-1的2倍
- 除去最高位，一半是n-1的排列，另一半是n-1排列的镜像
- n-1排列的内容的最高位是0，镜像的最高位是1
### 代码
```java
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<Integer>(){{add(0);}};
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = ans.size() - 1; j >= 0; j--) {
                ans.add(head + ans.get(j));
            }
            head <<= 1;
        }
        return ans;
    }
}
```
# LeetCode_537_复数乘法
## 题目
给定两个表示复数的字符串。

返回表示它们乘积的字符串。注意，根据定义 i2 = -1 。

示例 1:
```
输入: "1+1i", "1+1i"
输出: "0+2i"
解释: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
```
示例 2:
```
输入: "1+-1i", "1+-1i"
输出: "0+-2i"
解释: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。 
```
注意:
```
输入字符串不包含额外的空格。
输入字符串将以 a+bi 的形式给出，其中整数 a 和 b 的范围均在 [-100, 100] 之间。输出也应当符合这种形式。
```
## 解法
### 思路
复数相乘的关系可以演算为：
```math
(a + bi) * (c + di) = ac + i^2bd + i(bc + ad) = ac - bd + i(bc + ad)
```
### 代码
```java
class Solution {
    public String complexNumberMultiply(String a, String b) {
        String regex = "\\+|i";
        String[] as = a.split(regex);
        String[] bs = b.split(regex);

        int ax = Integer.parseInt(as[0]);
        int ay = Integer.parseInt(as[1]);
        int bx = Integer.parseInt(bs[0]);
        int by = Integer.parseInt(bs[1]);

        return ax * bx - ay * by + "+" + (ax * by + bx * ay) + "i";
    }
}
```
## 优化代码
### 思路
使用操作字符数组下标，替代正则
### 代码
```java
class Solution {
    public String complexNumberMultiply(String a, String b) {
        int ai = a.indexOf("+");
        int ax = Integer.parseInt(a.substring(0, ai));
        int ay = Integer.parseInt(a.substring(ai + 1, a.indexOf("i")));

        int bi = b.indexOf("+");
        int bx = Integer.parseInt(b.substring(0, bi));
        int by = Integer.parseInt(b.substring(bi + 1, b.indexOf("i")));

        return ax * bx - ay * by + "+" + (ax * by + bx * ay) + "i";
    }
}
```
# LeetCode_877_石子游戏
## 题目
亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。

游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。

亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。

假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。

示例：
```
输入：[5,3,4,5]
输出：true
解释：
亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
```
提示：
```
2 <= piles.length <= 500
piles.length 是偶数。
1 <= piles[i] <= 500
sum(piles) 是奇数。
```
## 解法
### 思路
动态规划，在二维 dpdp 的基础上使用元组分别存储两个人的博弈结果，难点在确定状态转移方程：
- dp[i][j]的含义：
    - i：数组的起始下标
    - j：数组的结尾下标
    - dp[i][j]中保存一个元组`(fir, sec)`：
        - fir表示先手选择的值
        - sec表示后手选择的值
- 确定状态转移方程：
    - 状态：
        - 起始下标start：`0 <= start < piles.length`
        - 结束下标end：`i <= end < piles.length`
        - 轮到的人
    - 可以做的选择：
        - 左边的石堆
        - 右边的石堆
    - 状态转移方程：
        - dp[i][j].fir：`piles[i] + dp(i + 1, j).sec`和`pilis[j] + dp(i, j - 1).sec`之间的最大值对应的下标的元素
        - dp[i][j].sec：
            - 如果先手选择的是左：dp[i + 1][j].fir
            - 如果先手选择的是右：dp[i][j - 1].fir
    - 最基本情况：起始和结束位置相同，说明只有一个石堆可以选择，所以先手就是这个石堆，后手为0。
### 代码
```java
class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        Pair[][] dp = new Pair[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }

        for (int i = 0; i < n; i++) {
            dp[i][i].fir = piles[i];
            dp[i][i].sec = 0;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = l + i - 1;
                int left = piles[i] + dp[i + 1][j].sec;
                int right = piles[j] + dp[i][j - 1].sec;

                if (left > right) {
                    dp[i][j].fir = left;
                    dp[i][j].sec = dp[i + 1][j].fir;
                } else {
                    dp[i][j].fir = right;
                    dp[i][j].sec = dp[i][j - 1].fir;
                }
            }
        }

        Pair ans = dp[0][n - 1];
        return ans.fir - ans.sec > 0;
    }

    class Pair {
        int fir;
        int sec;

        public Pair(int fir, int sec) {
            this.fir = fir;
            this.sec = sec;
        }
    }
}
```
## 解法二
### 思路
题目的前提是石子的总数是奇数的，将问题缩小到4堆石子，先手的人拿到第1堆必定能拿到第3堆，同理第4堆必定能拿到第2堆，而31或42组合有必定有一个组合是大于另一个的，所以先手的人一定能拿到最大的值。
### 代码
```java
class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}
```
# LeetCode_289_生命游戏
## 题目
根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。

给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
```
如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
```
示例:
```
输入: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
输出: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
```
进阶:
```
你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
```
## 解法
### 思路
遍历两次二维数组
- 第一次给当前数组打上标记
- 第二次根据标记重新设置0和1
### 代码
```java
class Solution {
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                judge(board, i, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = board[i][j] == 1 || board[i][j] == -2 ? 1 : 0;
            }
        }
    }

    private void judge(int[][] board, int x, int y) {
        int left = Math.max(0, y - 1);
        int right = Math.min(board[x].length - 1, y + 1);
        int top = Math.max(0, x - 1);
        int bottom = Math.min(board.length - 1, x + 1);
        int count = 0;
        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                count = board[i][j] == 1 || board[i][j] == -1 ? count + 1 : count;
            }
        }

        board[x][y] = board[x][y] == 1 ? (count == 3 || count == 4 ? 1 : -1) : (count == 3 ? -2 : 0);
    }
}
```
# LeetCode_114_二叉树展开为链表
## 题目
给定一个二叉树，原地将它展开为链表。

例如，给定二叉树
```
    1
   / \
  2   5
 / \   \
3   4   6
```
将其展开为：
```
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
```
## 解法
### 思路
新的链表的节点顺序等同于二叉树的先序遍历，转换的过程：
- 将右子树指向左子树
- 原来的右子树接在左子树的最右叶子节点后
- 从新的右子树的根节点出发，继续如上的逻辑，直到新的右子树为null
### 代码
```java
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        if (root.left != null) {
            TreeNode tmp = root.right;
            root.right = root.left;
            root.left = null;

            TreeNode lowestRight = dfs(root.right);
            lowestRight.right = tmp;    
        }
        
        flatten(root.right);
    }
    
    private TreeNode dfs(TreeNode node) {
        if (node.right == null) {
            return node;
        }
        
        return dfs(node.right);
    }
}
```
# LeetCode_1104_二叉树寻路
## 题目
在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。

如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；

而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。

给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。

示例 1：
```
输入：label = 14
输出：[1,3,4,14]
```
示例 2：
```
输入：label = 26
输出：[1,2,6,10,26]
```
提示：
```
1 <= label <= 10^6
```
## 解法
### 思路
- label的父节点是值为`label / 2`的节点的镜像节点
- 每一层的最大值是`Math.pow(2, level) - 1`，通过label第一次小于等于这个值来确定level
- 镜像通过当前层最大值和最小值的和sum减去label来获得，进而上一层就等于`(sum - label) / 2`来确定
- 通过递归来确定路径，`level = 1`来决定退出
### 代码
```java
class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        int level = 1;
        while (Math.pow(2, level) - 1 < label) {
            level+= 1;
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(label);
        dfs(ans, level, label);
        return ans;
    }

    private void dfs(List<Integer> list, int level, int label) {
        if (level == 1) {
            return;
        }

        int sum = (int)Math.pow(2, level - 1) + (int)Math.pow(2, level) - 1;
        int num = (sum - label) / 2;
        list.add(0, num);
        dfs(list, level - 1, num);
    }
}
```
# LeetCode_1111_1_有效括号的嵌套深度
## 题目
有效括号字符串 仅由 "(" 和 ")" 构成，并符合下述几个条件之一：
```
空字符串
连接，可以记作 AB（A 与 B 连接），其中 A 和 B 都是有效括号字符串
嵌套，可以记作 (A)，其中 A 是有效括号字符串
```
类似地，我们可以定义任意有效括号字符串 s 的 嵌套深度 depth(S)：
```
s 为空时，depth("") = 0
s 为 A 与 B 连接时，depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是有效括号字符串
s 为嵌套情况，depth("(" + A + ")") = 1 + depth(A)，其中 A 是有效括号字符串
例如：""，"()()"，和 "()(()())" 都是有效括号字符串，嵌套深度分别为 0，1，2，而 ")(" 和 "(()" 都不是有效括号字符串。
```
给你一个有效括号字符串 seq，将其分成两个不相交的子序列 A 和 B，且 A 和 B 满足有效括号字符串的定义（注意：A.length + B.length = seq.length）。

现在，你需要从中选出 任意 一组有效括号字符串 A 和 B，使 max(depth(A), depth(B)) 的可能取值最小。

返回长度为 seq.length 答案数组 answer ，选择 A 还是 B 的编码规则是：如果 seq[i] 是 A 的一部分，那么 answer[i] = 0。否则，answer[i] = 1。即便有多个满足要求的答案存在，你也只需返回 一个。

示例 1：
```
输入：seq = "(()())"
输出：[0,1,1,1,1,0]
```
示例 2：
```
输入：seq = "()(())()"
输出：[0,0,0,1,1,0,1,1]
```
提示：
```
1 <= text.size <= 10000
```
## 解法
### 思路
- 将相邻的左括号和右括号属于不同的部分，即可降低嵌套的深度
- 通过与标识数字`flag = 1`进行异或的方式来区分是左括号还是右括号
    - 遇到左括号flag先与1异或，再赋值：
        - 如果之前也是左括号，那么先异或就会使当前flag成为前一个元素的xor前的值
        - 如果之前是右括号，因为右括号是先赋值再异或，所以当前flag再一次异或后就和前一个元素一致了
    - 右括号先赋值，再与1异或，
### 代码
```java
class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int[] ans = new int[seq.length()];
        int flag = 1;
        for (int i = 0; i < seq.length(); i++) {
            if ('(' == seq.charAt(i)) {
                flag ^= 1;
                ans[i] = flag;
            } else {
                ans[i] = flag;
                flag ^= 1;
            }
        }
        return ans;
    }
}
```
# LeetCode_48_旋转图像
## 题目
给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。

说明：

你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:
```
给定 matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
```
示例 2:
```
给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
```
## 解法
### 思路
- 一层层的旋转
- 使用头尾指针代表每一层的起始和结尾,当头超过尾代表旋转结束
- 使用level代表第几层
- 后一个元素的x坐标是前一个元素的y坐标
- 有一个元素的y坐标是行或列的长度-前一个元素的x坐标
### 代码
```java
class Solution {
    public void rotate(int[][] matrix) {
        int head = 0, tail = matrix[0].length - 1, level = 0, len = tail;
        while (head < tail) {
            for (int i = head; i < tail; i++) {
                int x = level, y = i, old = matrix[x][y], now;
                for (int time = 0; time < 4; time++) {
                    int newX = y, newY = len - x;
                    now = matrix[newX][newY];
                    matrix[newX][newY] = old;
                    old = now;
                    x = newX;
                    y = newY;
                }
            }
            head++;
            tail--;
            level++;
        }
    }
}
```