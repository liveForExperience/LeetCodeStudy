# [LeetCode_880_索引处的解码字符串]()
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1653_使字符串平衡的最少删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced/)
## 解法
### 思路
- 题目要求所有a的坐标都要比所有b的坐标小
- 如果将数组切分成2部分，将分割线的左侧的b全部删除，将分割线右侧的a全部删除，就能达到题目要求的平衡状态
- 可以预先通过统计b的前缀和统计a的后缀数组，来优化遍历数组时候的重复计算
### 代码
```java
class Solution {
    public int minimumDeletions(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, preB = 0, sufA = 0;
        int[] preBs = new int[n], sufAs = new int[n];
        for (int i = 0; i < n; i++) {
            preBs[i] = preB + (cs[i] == 'b' ? 1 : 0);
            preB = preBs[i];
        }
        
        for (int i = n - 1; i >= 0; i--) {
            sufAs[i] = sufA + (cs[i] == 'a' ? 1 : 0);
            sufA = sufAs[i];
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cs.length; i++) {
            ans = Math.min(ans, preBs[i] + sufAs[i] - 1);
        }
        return ans;
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i]:以i为字符串结尾，最少需要删除的字符个数
- countB:到目前为止出现的b的个数
- 状态转移方程：
  - cs[i] == 'a'：dp[i] = max(dp[i - 1] + 1, b)
    - dp[i - 1] + 1表示将当前a删除，从而使s[0, i]与s[0, i-1]保持一致，成为平衡的
    - b表示，表示不保留所有的b，将b全部删除
  - cs[i] == 'b'：dp[i] = dp[i - 1]且b++
- 初始状态:
  - dp[0] = 0，无论是什么字符串，因为只有1个，所有无需任何操作就是平衡的
  - b = cs[i] == 'b' ? 1 : 0
- 结果：dp[n - 1]
### 代码
```java
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length(), b = s.charAt(0) == 'b' ? 1 : 0;
        char[] cs = s.toCharArray();
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (cs[i] == 'a') {
                dp[i] = Math.min(dp[i - 1] + 1, b);
            } else {
                dp[i] = dp[i - 1];
                b++;
            }
        }
        
        return dp[n - 1];
    }
}
```
## 解法三
### 思路
基于解法二，发现所有状态都只依赖前一个元素，所以讲dp数组压缩成变量dp
### 代码
```java
class Solution {
    public int minimumDeletions(String s) {
int dp = 0, b = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') {
                dp = Math.min(dp + 1, b);
            } else {
                b++;
            }
        }
        
        return dp;
    }
}
```
# [LeetCode_1092_花括号展开II](https://leetcode.cn/problems/brace-expansion-ii/)
## 解法
### 思路
- 表达式可以区分成3种情况：
  - 没有括号的单独内容，这种内容可以直接放入结果中
  - 有括号，括号中包含逗号的内容，这些内容相当于多个单独元素形成的列表
  - 括号与其他表达式之间没有间隔，这个时候，括号内用逗号分割的元素需要循环地与其他表达式的内容拼接处理
- 通过观察发现这个问题一定是一个重复子问题，可以通过递归，不断简化表达式获得可能的元素
- 而且发现，只要表达式中包含了括号，那么这个表达式就必须继续被处理，最终能够被记录的一定是没有括号的表达式
- 递归的过程中
  - 可以去找表达式中的右括号，如果存在，就说明当前表达式还需要处理，否则就可以放入一个去重的集合中
  - 如果还有括号，那么就把这个表达式区分成3个部分
    - 括号左侧，没有括号的字母部分，可能包含逗号
    - 括号内部，内部可能包含括号和逗号
    - 括号右侧，可能包含逗号和括号和字母等内容
  - 将括号内部通过逗号拆分，然后循环内部元素与左右内容进行拼接，完成情况3的处理，然后继续递归
### 代码
```java
class Solution {
    private TreeSet<String> set = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        rescue(expression);
        return new ArrayList<>(set);
    }

    private void rescue(String expression) {
        int ri = expression.indexOf("}");
        if (ri == -1) {
            set.add(expression);
            return;
        }

        int li = ri - 1;
        for (int i = li; i >= 0; i--) {
            if (expression.charAt(i) == '{') {
                li = i;
                break;
            }
        }

        String left = expression.substring(0, li),
               mid = expression.substring(li + 1, ri),
               right = expression.substring(ri + 1);
        String[] midFactors = mid.split(",");

        for (String midFactor : midFactors) {
            rescue(left + midFactor + right);
        }
    }
}
```
# [LeetCode_2578_最小和分割](https://leetcode.cn/problems/split-with-minimum-sum/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int splitNum(int num) {
        int[] arr = new int[10];
        while (num > 0) {
            arr[num % 10]++;
            num /= 10;
        }

        int a = 0, b = 0;
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                if (flag) {
                    a = a * 10 + i;
                } else {
                    b = b * 10 + i;
                }
                
                flag = !flag;
            }
        }

        return a + b;
    }
}
```