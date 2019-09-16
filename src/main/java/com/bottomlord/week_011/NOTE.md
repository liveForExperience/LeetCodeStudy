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