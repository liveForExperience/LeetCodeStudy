# [LeetCode_1678_设计Goal解析器](https://leetcode-cn.com/problems/goal-parser-interpretation/)
## 解法
### 思路
- 生成command字符串和结果字符串的映射关系
- 遍历字符串生成对应的结果字符串
- 遍历结束返回结果
### 代码
```java
class Solution {
    public String interpret(String command) {
        char[] cs = command.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cs.length;) {
            char c = cs[i];
            if ('G' == c) {
                sb.append(c);
                i++;
            } else {
                char nextC = command.charAt(++i);
                if (nextC == ')') {
                    sb.append("o");
                    i++;
                } else {
                    sb.append("al");
                    i += 3;
                }
            }
        }
        
        return sb.toString();
    }
}
```
## 解法二
### 思路
使用String的replaceAll这个Api
### 代码
```java
class Solution {
    public String interpret(String command) {
        return command.replaceAll("\\(\\)", "o").replaceAll("\\(al\\)", "al");
    }
}
```        
# [LeetCode_1684_统计一致字符串的数目](https://leetcode-cn.com/problems/count-the-number-of-consistent-strings/)
## 解法
### 思路
- 用set存储所有的allowed字符
- 遍历字符串和字符串的字符，查看是否有字符在set中不存在，如果没有就记录下来
- 遍历结束，返回存储的个数结果
### 代码
```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        Set<Character> set = new HashSet<>();
        for (char c : allowed.toCharArray()) {
            set.add(c);
        }

        int count = 0;
        for (String word : words) {
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if (!set.contains(c)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        return count;
    }
}
```
## 解法二
### 思路
使用32位的数字代替set来存储allowed的字母
### 代码
```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        int mask = 0;
        for (char c : allowed.toCharArray()) {
            int bit = c - '0';
            mask |= 1 << bit;
        }
        
        int count = 0;
        for (String word : words) {
            boolean flag = true;
            for (char c : word.toCharArray()) {
                int bit = c - '0';
                if (((1 << bit) | mask) != mask) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                count++;
            }
        }
        
        return count;
    }
}
```
# [LeetCode_300_最长递增子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)
## 解法
### 思路
动态规划：
- dp[i]：到第i个元素区间内，能够获取的最长递增子序列
- base case：dp[i] = 1
- 状态转移方程：dp[i] = max(dp[j]) + 1(i > j, nums[i] > nums[j])
- 最后返回dp数组中的最大值
### 代码
```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
```
# [LeetCode_673_最长递增子序列](https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/)
## 解法
### 思路
在第[300题](https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/)的基础上，状态转移的过程中，需要根据是否需要更新dp值来判断是否需要累加计数值，如果dp值更新了，说明找到了新的最长的递增序列，那么计数值就需要更新为该值，否则如果dp值相等，那说明又找到了同一个长度的序列，则累加
### 代码
```java
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, max = 1, ans = 0;
        int[] dp = new int[n], count = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = 0; j < n; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] < dp[j] + 1) {
                        count[i] = count[j];
                        dp[i] = dp[j] + 1;
                    } else if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }

            if (max < dp[i]) {
                max = dp[i];
                ans = count[i];
            } else if (max == dp[i]) {
                ans += count[i];
            }
        }

        return ans;
    }
}
```
# [LeetCode_52_最后一个单词的长度](https://leetcode-cn.com/problems/length-of-last-word/)
## 解法
### 思路
从字符串尾部开始遍历，找到第一个空格，然后将之前遍历到的单词返回
### 代码
```java
class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int count = 0;
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                break;
            }
            count++;
        }
        
        return count;
    }
}
```
# [LeetCode_725_分割链表](https://leetcode-cn.com/problems/split-linked-list-in-parts/)
## 解法
### 思路
模拟
- 计算出链表长度，从而计算出每段的基本长度，以及有多少段需要增加1
- 初始化结果数组
- 遍历链表，基于之前算出的值来生成每段的数组，需要在每段生成后断开节点间原来的指针连接
### 代码
```java
class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0, len = 0, left = 0;
        ListNode node = head;
        while (node != null) {
            n++;
            node = node.next;
        }

        len = n / k;
        left = n % k;

        node = head;
        ListNode[] ans = new ListNode[k];

        for (int i = 0; i < k; i++) {
            if (node == null) {
                ans[i] = null;
                continue;
            }

            ListNode pre = null;
            ans[i] = node;
            int curLen = len + (left-- > 0 ? 1 : 0);
            for (int j = 0; j < curLen; j++) {
                if (node == null) {
                    break;
                }
                pre = node;
                node = node.next;
            }
            
            if (pre != null) {
                pre.next = null;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_326_3的幂](https://leetcode-cn.com/problems/power-of-three/)
## 解法
### 思路
判断n是否能被3整除，如果可以就除以3继续判断，否则就判断当前值是否为1，如果不是就返回false，否则为true
### 代码
```java
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}
```
# [LeetCode_1688_比赛中的配对次数](https://leetcode-cn.com/problems/count-of-matches-in-tournament/)
## 解法
### 思路
模拟，循环中判断n的奇偶，然后对应变更n，直到n为1为止
### 代码
```java
class Solution {
    public int numberOfMatches(int n) {
        int count = 0;
        while (n != 1) {
            if(n % 2 == 0) {
                count += (n /= 2);
            } else {
                count += ((n /= 2) + 1);
            }
        }
        
        return count;
    }
}
```
# [LeetCode_1694_重新格式化电话号码](https://leetcode-cn.com/problems/reformat-phone-number/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public String reformatNumber(String number) {
        StringBuilder sb = new StringBuilder();
        for (char c : number.toCharArray()) {
            if (c == ' ' || c == '-') {
                continue;
            }

            sb.append(c);
        }

        int n = sb.length();
        int left = n % 3, block;

        if (left == 1) {
            block = n / 3 - 1;
            left = 4;
        } else {
            block = n / 3;
        }
        
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < block; i++) {
            for (int j = 0; j < 3; j++) {
                ans.append(sb.charAt(i * 3 + j));
            }
            ans.append('-');
        }
        
        if (left == 0) {
            ans.setLength(ans.length() - 1);
            return ans.toString();
        }
        
        if (left == 2) {
            ans.append(sb.charAt(sb.length() - 2)).append(sb.charAt(sb.length() - 1));
            return ans.toString();
        }
        
        return ans.append(sb.charAt(sb.length() - 4)).append(sb.charAt(sb.length() - 3))
                .append('-')
                .append(sb.charAt(sb.length() - 2)).append(sb.charAt(sb.length() - 1))
                .toString();
    }
}
```