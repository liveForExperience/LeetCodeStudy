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