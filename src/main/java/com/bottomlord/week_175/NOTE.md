# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_775_全局倒置与局部倒置](https://leetcode.cn/problems/global-and-local-inversions/)
## 解法
### 思路
- 全局倒置包含局部倒置
- 如果需要全局倒置=局部倒置个数，那么就需要关注全局倒置的非局部倒置个数，也就是不相邻的倒序是否存在，如果存在，那么这种情况也是一种全局倒置，这就导致和题目要求不符合
- 初始化一个max变量，赋值为arr[0]，然后从第3个元素开始遍历数组，每次都比较不包含前一个数组的区间中的最大值，如果比该最大值小，那么就符合非局部倒置的全局倒置情况，返回false
- 否则找不到的话，就返回true
### 代码
```java
class Solution {
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length, max = nums[0];
        for (int i = 2; i < n; i++) {
            if (nums[i] < max) {
                return false;
            }

            max = Math.max(max, nums[i - 1]);
        }
        return true;
    }
}
```
# [LeetCode_792_匹配子序列的单词数](https://leetcode.cn/problems/number-of-matching-subsequences/)
## 解法
### 思路
二分查找：
- 使用一个长度26的list数组，存储26个字母在字符串s中所在的坐标，坐标以升序排序
- 然后遍历words数组，在遍历其字符串元素，根据二分查找，在list数组中拿到当前元素坐标对应的字母在list中是否有比当前遍历到的s字符坐标更大的坐标
- 如果没有，word就不是子序列
### 代码
```java
class Solution {
        public int numMatchingSubseq(String s, String[] words) {
        List[] lists = new ArrayList[26];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList();
        }

        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            lists[c - 'a'].add(i);
        }

        int count = 0;
        for (String word : words) {
            int si = -1;
            boolean flag = true;
            char[] wcs = word.toCharArray();
            for (char c : wcs) {
                si = binarySearch(lists[c - 'a'], si);
                if (si == -1) {
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

    private static int binarySearch(List<Integer> list, int target) {
        int head = 0, tail = list.size();
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int num = list.get(mid);
            if (num <= target) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        if (head >= list.size()) {
            return -1;
        }

        return list.get(head);
    }
}
```
# [LeetCode_891_子序列宽度之和](https://leetcode.cn/problems/sum-of-subsequence-widths/)
## 解法
### 思路
- 题目只关注最大最小值，所以其实是否是子序列是不关注的
- 如果将数组进行排序
  - 对于之前的数字来说，这个数字就是最大值
  - 对于之后的数字来说，这个数字就是最小值
- 那么通过计算数字与之前的数字长度的2的整数次幂，或者是和之后的数字之间的整数次幂，就能得到所有子序列的个数
- 然后通过这些子序列的个数与当前值的乘积，就可以得到当前这个值作为最大值或者最小值的结果
- 是最大值就在结果中累加，是最小值就在结果中累减
- 因为序列的个数需要通过2的整数次幂来计算，而整数次幂的最大值是根据数组长度n来确定的，所以可以预先计算出0到n的2的整数次幂来
- 另外在mod的时候，因为之前计算中可能会得到负数，所以需要在最后是用`(count % mod + mod) % mod`的方式来计算（没懂为什么）
### 代码
```java
class Solution {
    public int sumSubseqWidths(int[] nums) {
        int mod = 1000000007, n = nums.length;
        Arrays.sort(nums);
        long count = 0;
        long[] pows = new long[n];
        pows[0] = 1;
        for (int i = 1; i < n; i++) {
            pows[i] = pows[i - 1] * 2 % mod;
        }

        for (int i = 0; i < nums.length; i++) {
            count += ((pows[i] - pows[n - 1 - i]) * nums[i]) % mod;
        }

        return (int) (count % mod + mod) % mod;
    }
}
```
# [LeetCode_799_香槟塔](https://leetcode.cn/problems/champagne-tower/)
## 解法
### 思路
动态规划：
- dp[i][j]：代表x为i，y为j的坐标的流量
- 状态转移方程： 
  - dp[i + 1][j] = (dp[i][j] - 1) / 2
  - dp[i + 1][j + 1] = (dp[i][j] - 1) / 2
  - 如果就被不大于1，不可能留给下一层的杯子
  - 而如果大于了1，那么留给下一层的就是减去1的值
  - 并且会留给左右两边的杯子，所以流下去的值就是`(dp[i][j] - 1) / 2`
### 代码
```java
class Solution {
    public double champagneTower(int p, int x, int y) {
        double[][] dp = new double[x + 2][x + 2];
        dp[0][0] = p;
        
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] <= 1) {
                    continue;
                }
                
                dp[i + 1][j] += (dp[i][j] - 1) / 2;
                dp[i + 1][j + 1] += (dp[i][j] - 1) / 2;
            }
        }
        
        return Math.min(dp[x][y], 1);
    }
}
```