# [LeetCode_526_优美的排列](https://leetcode-cn.com/problems/beautiful-arrangement/)
## 解法
### 思路
回溯：
- 用一个可变二维数组来存储每个坐标对应的符合优美排列的数字
  - 2层循环
  - 外层确定坐标
  - 内层确定坐标元素
  - 判断内外层的值是否能组成优美排列，如果可以就把内层值放在二维数组对应的list中
- 用一个记忆化数组来记录每次回溯时候的对应数字的选择状态
- 回溯：
  - 参数包含：坐标，记忆化数组，可变二维数组
  - 退出条件：坐标超过n
  - 回溯依赖记忆化数组中对应坐标的状态
  - 过程：循环当前坐标的所有可选元素，这些元素从二位可变数组中获取，然后根据记忆化数组判断这个元素是否已经用过，如果没有就标记并递归，返回的时候再重置该元素状态
  - 如果坐标超过n，那么也说明之前n个元素都符合了优美排列的要求，返回1，不断返回不断累加，最终返回所有的排列值
### 代码
```java
class Solution {
public int countArrangement(int n) {
        List<Integer>[] dict = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            dict[i] = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    dict[i].add(j);
                }
            }
        }

        return backTrack(1, n, new boolean[n + 1], dict);
    }

    private int backTrack(int index, int n, boolean[] memo, List<Integer>[] dict) {
        if (index > n) {
            return 1;
        }

        List<Integer> list = dict[index];
        int count = 0;
        for (int num : list) {
            if (!memo[num]) {
                memo[num] = true;
                count += backTrack(index + 1, n, memo, dict);
                memo[num] = false;
            }
        }

        return count;
    }
}
```