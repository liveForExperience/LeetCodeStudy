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