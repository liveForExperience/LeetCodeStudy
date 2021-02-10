# [LeetCode_978_最长湍流子数组](https://leetcode-cn.com/problems/longest-turbulent-subarray/)
## 解法
### 思路
双指针
- 初始化左右指针`left`和`right`，用来定义一个符合题意的子数组窗口`[left, right]`，初始化两个指针都指向坐标为0的元素
- 定义一个暂存窗口最大值的变量`ans`，初始为1，原因是初始窗口`[0,0]`就是一个窗口长度为1的窗口
- 然后遍历真个数组，根据如下情况来更新窗口：
    - 在两个指针相等的情况下，类似起始状态，此时`right`肯定是要右移来扩大窗口大小的，但题目不要求相等的相邻元素存在于数组中，所以还要判断`left`和`left+1`元素是否相等，如果相等，`left`也跟着右移，相当于重新开始判断一个新的窗口
    - 在两个指针不相等的情况下：
        - 如果`right`和`right-1`以及和`right+1`组成符合题目要求的相邻元素组合，那么说明子数组窗口可以扩大，`right`右移，同时更新`ans`
        - 如果不能组成符合题意的元素了，那么说明以`left`为起始的子数组已经不能再扩大了，且之前的长度已经更新到了`ans`中，所以就让`left=right`来重置窗口，继续判断
### 代码
```java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int left = 0, right = 0, ans = 1, n = arr.length;
        
        while (right < n - 1) {
            if (left == right) {
                right++;
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
            } else {
                if ((arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) ||
                    (arr[right - 1] > arr[right] && arr[right] < arr[right + 1])) {
                    right++;
                } else {
                    left = right;
                }
            }
            
            ans = Math.max(ans, right - left + 1);
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][j]`：
    - `i`：对应湍流数组结尾坐标
    - `j`：对应`arr[i] < arr[i - 1]`还是`arr[i - 1] > arr[i]`，分别用0和1标识
- 状态转移方程：
    - 如果`arr[i] > arr[i - 1]`，则`dp[i][0] = dp[i - 1][1] + 1`
    - 如果`arr[i] < arr[i - 1]`，则`dp[i][1] = dp[i - 1][0] + 1`
- base case：
    - `dp[0][0] = 1`
    - `dp[0][1] = 1` 
- 最终结果：遍历dp数组找到最大值
### 代码
```java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][2];
        dp[0][0] = dp[0][1] = 1;

        int ans = 1;
        for (int i = 1; i < len; i++) {
            dp[i][0] = dp[i][1] = 1;
            if (arr[i - 1] < arr[i]) {
                dp[i][0] = dp[i - 1][1] + 1;
            } else if (arr[i - 1] > arr[i]) {
                dp[i][1] = dp[i - 1][0] + 1;
            }
            ans = Math.max(ans, dp[i][0]);
            ans = Math.max(ans, dp[i][1]);
        }

        return ans;
    }
}
```
## 解法三
### 思路
动态规划的状态转移只依赖当前因素和前一个因素的状态，不需要使用二维数组
### 代码
```java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length, pre1 = 1, pre0 = 1, ans = 1;
        
        for (int i = 1; i < len; i++) {
            int cur0 = 1, cur1 = 1;
            if (arr[i- 1] < arr[i]) {
                cur0 = pre1 + 1;
            } else if (arr[i - 1] > arr[i]) {
                cur1 = pre0 + 1;
            }
            
            pre0 = cur0;
            pre1 = cur1;
            ans = Math.max(ans, cur0);
            ans = Math.max(ans, cur1);
        }
        
        return ans;
    }
}
```
# [LeetCode_446_等差数列划分II子数组](https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_992_k个不同整数的子数组](https://leetcode-cn.com/problems/subarrays-with-k-different-integers/)
## 失败解法
### 原因
超时
### 思路
- 遍历数组，确定以遍历到的坐标i为右边界的子数组
- 然后基于i这个右边界，去寻找左边界
    - 最小满足K的子数组的左边界`index1`
    - 最大满足K的子数组的左边界`index2`
    - 这两个值的差相减+1就是当前以i为右边界的数组个数
- 累加这些值就是结果
### 代码
```java
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        Set<Integer> memo = new HashSet<>();
        int len = A.length, ans = 0;

        for (int i = 0; i < len; i++) {
            int index1 = i;
            while (index1 >= 0) {
                memo.add(A[index1]);
                if (memo.size() == K) {
                    break;
                }
                index1--;
            }
            
            int index2 = index1;
            while (index2 > 0) {
                if (memo.contains(A[index2 - 1])) {
                    index2--;
                } else {
                    break;
                }
            }
            
            if (memo.size() == K) {
                ans += index1 - index2 + 1;
            }
            
            memo.clear();
        }

        return ans;
    }
}
```
## 解法
### 思路
滑动窗口：
- 核心逻辑：
    - `最多K个不同整数的子数组个数 - 最多k-1个不同整数的子数组个数 = K个不同整数的子数组个数`
    - 所以问题就变成了，求不同元素个数不大于K的子数组个数，和不同元素不大于k-1的子数组个数，然后将他们相减就能得出答案
- 定义变量：
    - `ans`：记录数组个数
    - `windowCOunt`：记录当前窗口不同元素的个数
    - `right`：窗口的右边界，驱动窗口移动，带动记录`windowCount`数
    - `left`：窗口的左边界，被动移动，用来确保窗口符合要求
    - `count[]`：数组，用来统计元素在窗口中出现的个数，通过`count[i] == 0`来判断是否出现新的不同元素
- 求最长子数组的过程：
    - 退出条件：`right < len`
    - 在`count[]`中统计出现个数
    - 根据`count[i] == 0`判断当前窗口是否有新的元素出现，有的话就增加`windowCount`
    - 循环判断`windowCount`是否大于题目要求的K，如果是，就开始移动`left`，以确保当前窗口重新恢复到符合题意的状态
    - 移动`left`的过程中，不断累减`count`中的值
    - 同时判断`count[left] == 0`，说明`left`的移动使得窗口的不同元素个数和K相同了，当前窗口重新恢复到符合题意的状态
    - 然后根据`right`和`left`的差+1来确定新生成的数组个数，累加到ans上。这样计算的原因是，新增加的连续数组，起始就是以新进来的元素为右边界的所有可能的连续数组，这个数组个数就是整个窗口的长度
    - 循环结束后，返回count
### 代码
```java
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return helper(A, K) - helper(A, K - 1);
    }
    
    private int helper(int[] a, int k) {
        int len = a.length, ans = 0, left = 0, right = 0, windowCount = 0;
        int[] count = new int[len + 1];
        
        while (right < len) {
            if (count[a[right]] == 0) {
                windowCount++;
            }
            count[a[right]]++;
            
            while (left <= right && windowCount > k) {
                count[a[left]]--;
                if (count[a[left]] == 0) {
                    windowCount--;
                }
                left++;
            }
            
            ans += right - left + 1;
            right++;
        }
        
        return ans;
    }
}
```
# LeetCode_567_字符串的排列
## 解法
### 思路
滑动窗口
- 这题相当于求s2的子数组，且子数组的长度为s1的长度，然后再比较s2的子数组字符个数和s1的字符个数
- 遍历s2模拟窗口移动，每次都更新新增和减去的字符个数，然后与s1的个数进行匹配
- 判断的规则是，比较字符出现的个数是否相等
### 代码
```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) {
            return false;
        }

        int[] count1 = new int[26], count2 = new int[26];
        
        for (char c : s1.toCharArray()) {
            count1[c - 'a']++;
        }
        
        for (int i = 0; i < len1; i++) {
            count2[s2.charAt(i) - 'a']++;
        }

        if (fit(count1, count2)) {
            return true;
        }
        
        for (int i = len1 - 1; i < len2 - 1; i++) {
            count2[s2.charAt(i + 1) - 'a']++;
            count2[s2.charAt(i - len1 + 1) - 'a']--;

            if (fit(count1, count2)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean fit(int[] c1, int[] c2) {
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        
        return true;
    }
}
```