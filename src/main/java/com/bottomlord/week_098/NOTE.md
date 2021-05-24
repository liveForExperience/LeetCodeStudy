# [LeetCode_664_奇怪的打印机](https://leetcode-cn.com/problems/strange-printer/)
## 解法
### 思路
动态规划：
- 观察到的特征：
    - 如果只有1种单词，例“a”，那就只需要1次打印
    - 如果有2种单词，例“ab”，那就需要2次打印
    - 如果字符子串2头的字符相同，例“aba”，那也需要2次打印
    - 如果字符子串2头的字符不相同，例“abab”，那就需要3次打印，也就是在情况3的基础上选择一个子串+1
- `dp[i][j]`：i和j区间内需要打印的最少次数
- base case：单个字符的打印次数为1
- 状态转移方程：
    - 如果i和j对应的字符相同：`dp[i][j] = dp[i][j - 1]`
    - 如果i和j对应的字符不相同：`就找这个区间中两两组合的最优解`
- 返回结果：`dp[0][n - 1]`
### 代码
```java
class Solution {
    public int strangePrinter(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = j - i + 1;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }
        
        return dp[0][len - 1];
    }
}
```
# [LeetCode_1064_不动点](https://leetcode-cn.com/problems/fixed-point/)
## 解法
### 思路
遍历一次搞定
### 代码
```java
class Solution {
    public int fixedPoint(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}
```
# [LeetCode_1065_字符串的索引对](https://leetcode-cn.com/problems/index-pairs-of-a-string/)
## 解法
### 思路
使用String的Api-indexof
### 代码
```java
class Solution {
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> list = new ArrayList<>();
        for (String word : words) {
            int index = -1;
            do {
                index = text.indexOf(word, index);
                if (index != -1) {
                    list.add(new int[]{index, index + word.length() - 1});
                    index++;
                }
            } while (index != -1);
        }
        
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i][0] = list.get(i)[0];
            ans[i][1] = list.get(i)[1];
        }

        Arrays.sort(ans, (x, y) -> {
            if (x[0] == y[0]) {
                return x[1] - y[1];
            }
            return x[0] - y[0];
        });
        return ans;
    }
}
```