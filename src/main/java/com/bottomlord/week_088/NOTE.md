# [LeetCode_488_祖玛游戏](https://leetcode-cn.com/problems/zuma-game/)
## 解法
### 思路
回溯
- 暂存手中有的球与球数量的映射
- 暂存一个全局变量result，用来暂存回溯搜索过程中比较获得的次数最小值
- 回溯：
    - 参数：
        - 字符串的StringBuilder
        - 当前处理次数step
    - 过程：
        - 退出条件：sb长度为0，代表所有元素处理完
        - 剪枝：当前处理次数step大于暂存的最小次数，直接淘汰
        - 核心：
            - 外层循环，指针i定义要处理的起始字符
            - 内层定义另一个指针j，初始化为i，然后判断之后与i字符相同的连续字符个数
                - 如果字符只有1个，且手上的对应求数大于等于2，则代表这个颜色可以处理，就将该状态备份后，处理这个字符串可能需要自动消除的字符，然后step+1，继续递归和回溯
                - 如果字符有2个：
                    - 考虑直接插相同颜色的球，使之消除，进而也进入迭代自动消除的函数
                    - 考虑将不同的颜色插入到2个相同颜色中，然后直接递归搜索
- 递归结束后，返回全局变量，返回时判断值是否是初始值，如果是就是返回-1，否则就是直接返回
### 代码
```java
class Solution {
    private int ans;
    private int[] bucket;

    public int findMinStep(String board, String hand) {
        ans = Integer.MAX_VALUE;
        bucket = new int[26];
        for (int i = 0; i < hand.length(); i++) {
            bucket[hand.charAt(i) - 'A']++;
        }

        backTrack(new StringBuilder(board), 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void backTrack(StringBuilder sb, int step) {
        if (step >= ans) {
            return;
        }

        if (sb.length() == 0) {
            ans = step;
            return;
        }

        for (int i = 0; i < sb.length(); i++) {
            int j = i;
            while (j + 1 < sb.length() && sb.charAt(j) == sb.charAt(j + 1)) {
                j++;
            }

            char c = sb.charAt(i);
            if (i == j && bucket[c - 'A'] >= 2) {
                StringBuilder cur = new StringBuilder(sb);
                cur.insert(i, c);
                eliminate(cur);
                bucket[c - 'A']--;
                backTrack(cur, step + 1);
                bucket[c - 'A']++;
            } else if (j - i == 1) {
                if (bucket[c - 'A'] >= 1) {
                    StringBuilder cur = new StringBuilder(sb);
                    cur.insert(i, c);
                    eliminate(cur);
                    bucket[c - 'A']--;
                    backTrack(cur, step + 1);
                    bucket[c - 'A']++;
                    continue;
                }

                for (int k = 0; k < bucket.length; k++) {
                    if (bucket[k] == 0 || k == (c - 'A')) {
                        continue;
                    }

                    StringBuilder cur = new StringBuilder(sb);
                    cur.insert(i + 1, (char) (k + 'A'));
                    bucket[k]--;
                    backTrack(cur, step + 1);
                    bucket[k]++;
                }
            }
        }
    }

    private void eliminate(StringBuilder sb) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < sb.length(); i++) {
                int j = i;
                while (j + 1 < sb.length() && sb.charAt(j) == sb.charAt(j + 1)) {
                    j++;
                }

                if (j - i >= 2) {
                    flag = true;
                    sb.delete(i, j + 1);
                }
            }
        }
    }
}
```