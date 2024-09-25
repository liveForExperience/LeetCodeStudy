# [LeetCode_1601_最多可达成的换楼请求数](https://leetcode-cn.com/problems/maximum-number-of-achievable-transfer-requests/)
## 解法
### 思路
- 因为requests的长度有限，导致可能的状态也是有限的，所以可以使用二进制来表示requests的选择状态，然后枚举所有的状态，找到符合题目要求的最多情况
- 计算状态中选择个数，可以用`i & (i - 1)`能够消除最低位1的特性来计算
### 代码
```java
class Solution {
    public int maximumRequests(int n, int[][] requests) {
        int m = requests.length, ans = 0;
        for (int i = 0; i < (1 << m); i++) {
            int count = count(i);
            if (count <= ans) {
                continue;
            }

            if (check(i, requests, n)) {
                ans = count;
            }
        }

        return ans;
    }

    private boolean check(int state, int[][] requests, int n) {
        int m = requests.length, sum = 0;
        int[] bucket = new int[n];
        for (int i = 0; i < m; i++) {
            if (((state >> i) & 1) == 1) {
                int x = requests[i][0], y = requests[i][1];
                if (++bucket[x] == 1) {
                    sum++;
                }
                
                if (--bucket[y] == 0) {
                    sum--;
                }
            }
        }

        return sum == 0;
    }

    private int count(int state) {
        int count = 0;
        while (state != 0) {
            state &= (state - 1);
            count++;
        }

        return count;
    }
}
```
## 解法二
### 思路
dfs+回溯
### 代码
```java
class Solution {
    public int maximumRequests(int n, int[][] requests) {
        return dfs(requests, 0, new int[n], 0);
    }

    private int dfs(int[][] requests, int index, int[] bucket, int count) {
        if (index == requests.length) {
            if (check(bucket)) {
                return count;
            } else {
                return 0;
            }
        }

        int ans = dfs(requests, index + 1, bucket, count);

        int x = requests[index][0], y = requests[index][1];
        bucket[x]--;
        bucket[y]++;
        ans = Math.max(dfs(requests, index + 1, bucket, count + 1), ans);
        bucket[x]++;
        bucket[y]--;
        
        return ans;
    }

    private boolean check(int[] bucket) {
        for (int num : bucket) {
            if (num != 0) {
                return false;
            }
        }

        return true;
    }
}
```
# [LeetCode_6_Z字形变换](https://leetcode-cn.com/problems/zigzag-conversion/)
## 解法
### 思路
- 将z字形的1竖和紧接着的1斜组成一组cycle，将整个字符串拆解成n组
- 拼接新的字符串时，按照行数进行遍历，而内层：
  - 如果是第一行或者最后一行，则字符串按照cycle间隔在z字形字符串中遍历拼接
  - 如果是中间的若干行，则一组中这一行会出现2个字符串，第一个字符串和上面的规则一样，第二个字符串按照`j + cycle - i`的规则可以定位
### 代码
```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        int cycle = 2 * numRows - 2, len = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; i + j < len; j += cycle) {
                sb.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycle - i < len) {
                    sb.append(s.charAt(j + cycle - i));
                }
            }
        }
        return sb.toString();
    }
}
```
# [LeetCode_564_寻找最近的回文数](https://leetcode-cn.com/problems/find-the-closest-palindrome/)
## 失败解法
### 原因
超时
### 思路
暴力判断
### 代码
```java
class Solution {
  public String nearestPalindromic(String n) {
    long len = 1;
    while (true) {
      String left = String.valueOf(Long.parseLong(n) - len);
      if (check(left)) {
        return left;
      }

      String right = String.valueOf(Long.parseLong(n) + len);
      if (check(right)) {
        return right;
      }

      len++;
    }
  }

  private boolean check(String n) {
    int l = 0, r = n.length() - 1;
    while (l < r) {
      if (n.charAt(l++) != n.charAt(r--)) {
        return false;
      }
    }
    return true;
  }
}
```
## 解法
### 思路
- 是最接近的回文串，也就表示该串应该和原数尽量一致，所以可以理解成将原数的左半边做镜像拼接，或者右半边做镜像拼接， 是可以得到一个尽可能接近的数的
- 在考虑是否需要右半边时发现，尽管题目要求回文数字尽量选择小的，但是如果右半边的镜像比左半边小，拼接后，就会导致这个值一定比左半边镜像拼接后和原数的差值大，因为高位变化后，值变化也就更大，所以只需要考虑左半边的镜像拼接
- 然后还需要考虑例如，10这样的数字，他的符合题目要求的回文数字是9，会需要进行退位，同理可以推测，也会有需要进位的情况，在观察后发现，无论退还是进，在处理后的第一个值一定是回文的，例如9999或者100001，所以可以把这两种情况根据字符串的长度预先准备好
- 在处理镜像拼接的时候，左半边的最低位的+1和-1生成的数可能反而会比原数镜像拼接更接近原数，这个和右边与翻转后的左边值之间的大小有关，例如12399，他的回文数12321和12421，肯定是12421更接近，所以左半边的值也需要做+1和-1的操作，相当于翻转后的左半边做了进位和退位
- 处理逻辑则是把上面的几种情况全部罗列之后，依次比较差值，找到符合题目要求的数值并返回
### 代码
```java
class Solution {
    public String nearestPalindromic(String n) {
        long target = Long.parseLong(n), ans = -1;
        List<Long> candidates = getCandidates(n);
        for (Long candidate : candidates) {
            if (target == candidate) {
                continue;
            }

            if (ans == -1 ||
                Math.abs(candidate - target) < Math.abs(ans - target) ||
                (Math.abs(candidate - target) == Math.abs(ans - target) && candidate < target)) {
                ans = candidate;
            }
        }

        return String.valueOf(ans);
    }

    private List<Long> getCandidates(String n) {
        int len = n.length();
        List<Long> candidates = new ArrayList<>();
        candidates.add((long)Math.pow(10, len - 1) - 1);
        candidates.add((long)Math.pow(10, len) + 1);

        long prefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long num = prefix - 1; num <= prefix + 1; num++) {
            StringBuilder sb = new StringBuilder();
            String candidatePrefix = String.valueOf(num);
            sb.append(candidatePrefix);
            StringBuilder suffixSb = new StringBuilder(candidatePrefix).reverse();
            String suffix = suffixSb.substring(len & 1);
            sb.append(suffix);
            candidates.add(Long.parseLong(sb.toString()));
        }

        return candidates;
    }
}
```
# [LeetCode_2185_统计包含给定前缀的字符串](https://leetcode-cn.com/problems/counting-words-with-a-given-prefix/)
## 解法
### 思路
- 模拟
- 注意pref比word长的情况
### 代码
```java
class Solution {
    public int prefixCount(String[] words, String pref) {
        int ans = 0;
        for (String word : words) {
            if (match(word, pref)) {
                ans++;
            }
        }
        return ans;
    }
    
    private boolean match(String word, String pref) {
        int len = pref.length();
        if (len > word.length()) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            if (word.charAt(i) != pref.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_2104_子数组范围和](https://leetcode-cn.com/problems/sum-of-subarray-ranges/)
## 解法
### 思路
dfs
- 注意是连续的非空子数组
### 代码
```java
class Solution {
    private long ans = 0;

    public long subArrayRanges(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            dfs(nums, i, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        return ans;
    }

    private void dfs(int[] nums, int index, int max, int min) {
        if (index >= nums.length) {
            return;
        }

        max = Math.max(max, nums[index]);
        min = Math.min(min, nums[index]);
        ans += max - min;
        
        dfs(nums, index + 1, max, min);
    }
}
```
# [LeetCode_2100_适合打劫银行的日子](https://leetcode-cn.com/problems/find-good-days-to-rob-the-bank/solution/gua-he-da-jie-yin-xing-de-ri-zi-by-leetc-z6r1/)
## 解法
### 思路
动态规划：
- 2个dp数组，分别记录i
  - 左边连续非递增序列的个数
  - 右边连续非递减序列的个数
- 状态转移方程：
  - left[i] = nums[i] <= nums[i - 1] ? left[i - 1] + 1 : 0
  - right[i] = nums[i] <= nums[i + 1] ? right[i + 1] + 1 : 0
### 代码
```java
class Solution {
  public List<Integer> goodDaysToRobBank(int[] security, int time) {
    int len = security.length;
    int[] left = new int[len], right = new int[len];
    for (int i = 1; i < len; i++) {
      if (security[i] <= security[i - 1]) {
        left[i] = left[i - 1] + 1;
      }
    }

    for (int i = len - 2; i >= 0; i--) {
      if (security[i] <= security[i + 1]) {
        right[i] = right[i + 1] + 1;
      }
    }

    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < len; i++) {
      if (left[i] >= time && right[i] >= time) {
        ans.add(i);
      }
    }

    return ans;
  }
}
```