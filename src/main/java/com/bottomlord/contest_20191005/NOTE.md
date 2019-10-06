# Contest_1_三个有序数组的交集
## 题目
给出三个均为 严格递增排列 的整数数组 arr1，arr2 和 arr3。

返回一个由 仅 在这三个数组中 同时出现 的整数所构成的有序数组。

示例：
```
输入: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
输出: [1,5]
解释: 只有 1 和 5 同时在这三个数组中出现.
```
提示：
```
1 <= arr1.length, arr2.length, arr3.length <= 1000
1 <= arr1[i], arr2[i], arr3[i] <= 2000
```
## 解法
### 思路
通过map来计数：
- 遍历arr1，将数字放入map并计数1
- 遍历arr2，如果map中有遍历到的数字才计数为2
- 遍历arr3，如果map中有且值为2，则将该数字放入ans中
### 代码
```java
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : arr2) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int num : arr3) {
            if (map.containsKey(num)) {
                if (map.get(num) == 2) {
                    ans.add(num);
                }
            }
        }
        
        return ans;
    }
}
```
# Contest_2_查找两棵二叉搜索树之和
## 题目
给出两棵二叉搜索树，请你从两棵树中各找出一个节点，使得这两个节点的值之和等于目标值 Target。

如果可以找到返回 True，否则返回 False。

示例 1：
```
输入：root1 = [2,1,4], root2 = [1,0,3], target = 5
输出：true
解释：2 加 3 和为 5 。
```
示例 2：
```
输入：root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
输出：false
```
提示：
```
每棵树上最多有 5000 个节点。
-10^9 <= target, node.val <= 10^9
```
## 解法
### 思路
- 遍历两棵树，打印出所有的节点
- 嵌套循环打印出的两个list，如果有符合相加等于target的就返回true，否则就在循环结束后返回false
### 代码
```java
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        List<Integer> list1 = new ArrayList<>();
        dfs(root1, list1);
        
        List<Integer> list2 = new ArrayList<>();
        dfs(root2, list2);
        
        for (int num1 : list1) {
            for (int num2 : list2) {
                if (num1 + num2 == target) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
```
# Contest_3_步进数
## 题目
如果一个整数上的每一位数字与其相邻位上的数字的绝对差都是 1，那么这个数就是一个「步进数」。

例如，321 是一个步进数，而 421 不是。

给你两个整数，low 和 high，请你找出在 [low, high] 范围内的所有步进数，并返回 排序后 的结果。

示例：
```
输入：low = 0, high = 21
输出：[0,1,2,3,4,5,6,7,8,9,10,12,21]
```
提示：
```
0 <= low <= high <= 2 * 10^9
```
## 解法
### 思路
- 使用递归将从[1,9]之间的所有9个数字起始的步进数，在小于int最大值的条件基础上生成出来，放入set中
- 遍历set，将所有[low,high]之间的数字放入结果
- 将结果排序并返回
### 代码
```java
class Solution {
    private Set<Long> set = new HashSet<>();

    public List<Integer> countSteppingNumbers(int low, int high) {
        set.add(0L);
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            recurse(i);
        }

        for (long num : set) {
            if (num >= low && num <= high) {
                ans.add((int)num);
            }
        }

        Collections.sort(ans);
        return ans;
    }

    private void recurse(long num) {
        if (num > Integer.MAX_VALUE) {
            return;
        }
        set.add(num);
        long cur = num % 10;
        if (cur > 0) {
            recurse(num * 10 + cur - 1);
        }

        if (cur < 9) {
            recurse(num * 10 + cur + 1);
        }
    }
}
```
# Contest_4_验证回文字符串III
## 题目
给出一个字符串 s 和一个整数 k，请你帮忙判断这个字符串是不是一个「K 回文」。

所谓「K 回文」：如果可以通过从字符串中删去最多 k 个字符将其转换为回文，那么这个字符串就是一个「K 回文」。

示例：
```
输入：s = "abcdeca", k = 2
输出：true
解释：删除字符 “b” 和 “e”。
```
提示：
```
1 <= s.length <= 1000
s 中只含有小写英文字母
1 <= k <= s.length
```
## 解法
### 思路
使用动态规划：
- `dp[i][j]`存储的是从i到j长度的字符串中的最长回文子序列的长度
- 状态转移方程有3种情况，当前长度的字符串的长度取得就是三者的最大值：`dp[i][j] = Math.max(res1, res2, res3)`
    - i和j下标对应的字符不相等：
        - 可能左边的不对：`res1 = dp[i + 1][j]`
        - 可能右边的不对：`res2 = dp[i][j - 1]`
    - i和j下标对应的字符相等：`res3 = dp[i + 1][j - 1] + 2`，加2是因为当前i和j对应的字符也是算在回文序列中的
- 求dp的过程是一个递归，退出条件是：
    - i > j，不是字符串了，返回序列长度0
    - i == j，只有一个字符，返回序列长度1
    - 如果dp[i][j]已经计算过，就返回这个值
### 代码
```java
cclass Solution {
     public boolean isValidPalindrome(String s, int k) {
         int len = s.length();
         int[][] dp = new int[len][len];
         for (int i = 0; i < len; i++) {
             Arrays.fill(dp[i], -1);
         }
         int max = recurse(s, dp, 0, len - 1);
         return len - max <= k;
     }
 
     private int recurse(String s, int[][] dp, int i, int j) {
         if (i > j) {
             return 0;
         }
 
         if (i == j) {
             return 1;
         }
 
         if (dp[i][j] != -1) {
             return dp[i][j];
         }
         
         int max = Math.max(recurse(s, dp, i + 1, j), recurse(s, dp, i, j - 1));
         if (s.charAt(i) == s.charAt(j)) {
             max = Math.max(max, recurse(s, dp, i + 1, j - 1) + 2);
         }
         
         return dp[i][j] = max;
     }
 }
}
```