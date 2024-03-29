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
# [LeetCode_1700_无法吃午餐的学生数量](https://leetcode-cn.com/problems/number-of-students-unable-to-eat-lunch/)
## 解法
### 思路
- 遍历学生数组，计算出1和0的个数
- 遍历三明治数组，根据当前元素的值来累减1或0的个数
- 如果当前三明治元素的值对应的个数为0，则返回剩下的2个统计值的和
### 代码
```java
class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int one = 0, zero = 0;
        for (int student : students) {
            if (student == 0) {
                zero++;
            } else {
                one++;
            }
        }
        
        for (int sandwich : sandwiches) {
            if (sandwich == 0) {
                if (zero == 0) {
                    return one;
                }
                
                zero--;
            } else {
                if (one == 0) {
                    return zero;
                }
                
                one--;
            }
        }
        
        return 0;
    }
}
```
# [LeetCode_1704_判断字符串的两半是否相等]()
## 解法
### 思路
- 生成原因字符的set
- 将字符串拆分成2部分
- 遍历2个字符串，统计各个部分的原因字母个数
- 遍历结束判断两部分的个数是否相等
### 代码
```java
class Solution {
    public boolean halvesAreAlike(String s) {
        Set<Character> set = new HashSet<>();
        char[] cs = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (char c : cs) {
            set.add(c);
        }

        int half = s.length() / 2;
        String first = s.substring(0, half), second = s.substring(half, s.length());
        int count = 0;
        for (int i = 0; i < half; i++) {
            if (set.contains(first.charAt(i))) {
                count++;
            }
            
            if (set.contains(second.charAt(i))) {
                count--;
            }
        }
        
        return count == 0;
    }
}
```
## 解法二
### 思路
使用数组替换解法一的set
### 代码
```java
class Solution {
    public boolean halvesAreAlike(String s) {
        int[] memo = new int['z' + 1];
        for (char c : new char[]{'a', 'e', 'i', 'o', 'u'}) {
            memo[c] = memo[c - 32] = 1;
        }

        return count(0, s.length() / 2, s, memo) ==
                count(s.length() / 2, s.length(), s, memo);
    }

    private int count(int start, int end, String str, int[] memo) {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (memo[str.charAt(i)] == 1) {
                count++;
            }
        }
        return count;
    }
}
```
# [LeetCode_430_扁平化多级双向链表](https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/)
## 解法
### 思路
- 扁平化的目的是，有child优先child，child连完之后再连next
- dfs搜索，过程中将前后节点做绑定，并断开child指针，并按照如上策略进行连接
- 过程中入参应是2个参数，一个是pre指针，一个是cur指针，通过2个指针的关系来进行绑定
- 初始搜索的时候需要设置一个fake head
### 代码
```java
class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Node start = new Node();
        dfs(start, head);
        start.next.prev = null;
        return start.next;
    }

    private Node dfs(Node pre, Node cur) {
        if (cur == null) {
            return pre;
        }

        Node next = cur.next;
        pre.next = cur;
        cur.prev = pre;

        Node child = dfs(cur, cur.child);

        cur.child = null;

        return dfs(child, next);
    }

    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
```
# [LeetCode_1708_长度为K](https://leetcode-cn.com/problems/largest-subarray-length-k/)
## 解法
### 思路
模拟
- 遍历所有子数组可能，记录起始坐标，依次比较，哪个数组大就记录该起始坐标
### 代码
```java
class Solution {
    public int[] largestSubarray(int[] nums, int k) {
        int n = nums.length, start = 0;
        for (int i = 1; i < n - k + 1; i++) {
            for (int j = 0; j < k; j++) {
                if (nums[start + j] > nums[i + j]) {
                    break;
                } else if (nums[start + j] < nums[i + j]) {
                    start = i;
                    break;
                }
            }
        }
        
        int[] ans = new int[k];
        System.arraycopy(nums, start, ans, 0, k);
        return ans;
    }
}
```
# [LeetCode_583_两个字符串的删除操作](https://leetcode-cn.com/problems/delete-operation-for-two-strings/)
## 解法
### 思路
求出最长公共子序列后计算
### 代码
```java
class Solution {
  public int minDistance(String word1, String word2) {
    int len1 = word1.length(), len2 = word2.length();
    int[][] dp = new int[len1 + 1][len2 + 1];
    for (int i = 1; i <= len1; i++) {
      for (int j = 1; j <= len2; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return len1 + len2 - 2 * dp[len1][len2];
  }
}
```
# [LeetCode_1143_最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/)
## 解法
### 思路
二维动态规划：
- dp[i][j]：两个字符串长度i和j前缀情况下，最长的公共子序列
- base case：如果某个字符串的长度为0，那么他们的公共子序列长度也一定是0
- 状态转移方程：
  - s[i] == s[j]：dp[i - 1][j - 1] + 1
  - s[i] != s[j]：max(dp[i - 1][j], dp[i][j - 1])
### 代码
```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[len1][len2];
    }
}
```
# [LeetCode_1716_计算力扣银行的钱](https://leetcode-cn.com/problems/calculate-money-in-leetcode-bank/)
## 解法
### 思路
模拟+等差数列公式
### 代码
```java
class Solution {
    public int totalMoney(int n) {
        int start = 1, day = 1, sum = 0, 
            week = n / 7, left = n % 7;
        
        for (int i = 0; i < week; i++) {
            sum += start++ * 7 + 21;
        }
        
        return sum + left * (left - 1) / 2 + left * start;
    }
}
```
# [LeetCode_371_两整数之和](https://leetcode-cn.com/problems/sum-of-two-integers/)
## 解法
### 思路
- 抑或：得到不进位的相加结果
- 与：得到需要进位的位，也就是是1的位
- 只要与的结果不是0，就不断的处理进位相加的过程
- 一开始是a和b的相加，之后就是xor和and的相加
### 代码
```java
class Solution {
    public int getSum(int a, int b) {
        int xor = a ^ b, and = a & b, tmp;

        while (and != 0) {
            and <<= 1;
            tmp = xor;
            xor ^= and;
            and &= tmp;
        }

        return xor;
    }
}
```
# [LeetCode_1725_可以形成最大正方形的矩形数目](https://leetcode-cn.com/problems/number-of-rectangles-that-can-form-the-largest-square/)
## 解法
### 思路
- 求出二维数组中每个1维数组中的最小值之间的最大值的个数
### 代码
```java
class Solution {
    public int countGoodRectangles(int[][] rectangles) {
        int max = Integer.MIN_VALUE, count = 0;
        for (int[] arr : rectangles) {
            int min = Math.min(arr[0], arr[1]);
            
            if (min > max) {
                count = 1;
                max = min;
            } else if (min == max) {
                count++;
            }
        }
        
        return count;
    }
}
```