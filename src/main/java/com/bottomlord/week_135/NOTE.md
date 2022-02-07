# [LeetCode_1405_最长快乐字符串](https://leetcode-cn.com/problems/longest-happy-string/)
## 解法
### 思路
贪心
- 根据字符串个数非升序排序
- 根据题目要求循环拼接字符
- 循环结束返回拼接的字符串
### 代码
```java
class Solution {
    public String longestDiverseString(int a, int b, int c) {
        int[][] pairs = new int[][]{{a, 0}, {b, 1}, {c, 2}};
        StringBuilder sb = new StringBuilder();

        while (true) {
            Arrays.sort(pairs, (x, y) -> y[0] - x[0]);
            boolean hasNext = false;
            for (int[] pair : pairs) {
                if (pair[0] <= 0) {
                    continue;
                }

                char ch = (char)(pair[1] + 'a');
                int len = sb.length();
                if (len >= 2 &&
                    sb.charAt(len - 2) == ch &&
                    sb.charAt(len - 1) == ch) {
                    continue;
                }

                hasNext = true;
                sb.append(ch);
                pair[0]--;
                break;
            }

            if (!hasNext) {
                break;
            }
        }
        
        return sb.toString();
    }
}
```