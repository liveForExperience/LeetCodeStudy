# [LeetCode_458_可怜的小猪](https://leetcode-cn.com/problems/poor-pigs/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_995_K连续位的最小翻转次数](https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/)
## 解法
### 思路
- 对某个子数组翻转2次等于没有翻转，所以所有子数组在求解过程中，应只翻转1次
- 而对于任意K位子数组的翻转，无论翻转动作先后，都不影响最后的结果，所以可以从坐标0的位置开始
- 然后按照如下策略，从0开始进行翻转：
    - 遍历数组
    - 遇到为0的坐标值就进行翻转，并移动
    - 遇到为1的坐标值不反转，移动
    - 直接移动的原因是，对于固定的K位子数组，翻转动作是唯一的，而从这个题目可以看出，这个K位子数组可以看作是一个滑动窗口，以当前坐标为起始坐标，这个滑动窗口只可以被翻转一次
- 统计翻转的次数，如果全部为1，则返回翻转的次数，否则返回-1
- 1和0的翻转动作可以通过`^= 1`模拟
### 代码
```java
class Solution {
    public int minKBitFlips(int[] A, int K) {
        int len = A.length, count = 0;

        for (int i = 0; i <= len - K; i++) {
            if (A[i] == 1) {
                continue;
            }

            for (int j = i; j < i + K; j++) {
                A[j] ^= 1;
            }
            count++;
        }
        
        return Arrays.stream(A).sum() == len ? count : -1;
    }
}
```
## 解法二
### 思路
- 解法一中的时间复杂度为`O(NK)`，如果可以减少模拟过程中翻转K个元素的动作，就能降低复杂度
- 如果可以统计出一个元素需要翻转的次数，就能直到当前元素的最终状态
- 而统计翻转次数可以通过差分的思想，用`diff[i]`来代表`A[i]`和`A[i-1]`之间的差值，一次翻转也就是变更了窗口的起始坐标`diff[l]`和结尾坐标的后一个坐标`diff[r + 1]`的值，`diff[l]`在累加，`diff[r + 1]`在累减
- 使用一个变量`count`来统计变化量，该值初始化为0
- 在遍历数组`A`的过程中，判断`A[i] + count`是否为偶数，就能知道当前值在作为窗口起始坐标前是什么值了，然后判断是否需要做翻转，从而更新`diff`数组的值并统计更新次数
- 如果`r + 1 >= len`，说明无法再翻转，此时返回-1
### 代码
```java
class Solution {
    public int minKBitFlips(int[] A, int K) {
        int len = A.length, count = 0, ans = 0;
        int[] diff = new int[len + 2];

        for (int i = 0; i < len; i++) {
            count += diff[i + 1];
            if ((A[i] + count) % 2 == 0) {
                if (i + K > len) {
                    return -1;
                }

                diff[i + 1]++;
                diff[i + K + 1]--;
                count++;
                ans++;
            }
        }

        return ans;
    }
}
```
# [LeetCode_1004_最大连续1的个数](https://leetcode-cn.com/problems/max-consecutive-ones-iii/)
## 解法
### 思路
- 题目的要求可以简化为：以坐标R为结尾，找到以L为起始，包含不超过K个0的最大区间。
- 为了快速确定区间中有多少个0，可以将整个区间的元素值翻转，1变为0，0变为1，然后求前缀和，记录为sum数组，这样就能通过`sum[r] - sum[l - 1]`快速求得0的个数
- 算法过程：
    - 外层迭代确定R的坐标
    - 内层迭代确定0到R区间中的L
    - 判断的公式：`sum[r] - sum[l - 1] <= K`，可以转化为：`sum[l - 1] >= sum[r] - K`，于是就相当于求最小的`sum[l - 1]`
    - 因为整个数组都是以0和1组成，所以前缀和数组`sum`一定是递增区间，那么通过二分就能找到这个最小值`sum[l - 1]`
    - 因为公式中求得是`sum[l - 1]`，为了规避坐标0的特殊情况，将`sum`数组记录的值整体向右移动一位，空出`sum[0]`的位置方便计算
### 代码
```java
class Solution {
    public int longestOnes(int[] A, int K) {
        int len = A.length, ans = 0;

        int[] sum = new int[len + 1];
        for (int i = 1; i < len + 1; i++) {
            sum[i] = sum[i - 1] + (1 - A[i - 1]);
        }

        for (int r = 0; r < len ; r++) {
            int l = binarySearch(sum[r + 1] - K, sum);
            ans = Math.max(ans, r - l  + 1);
        }

        return ans;
    }

    private int binarySearch(int target, int[] sum) {
        int l = 0, r = sum.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (sum[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}
```
## 解法二
### 思路
- 这其实是一道滑动窗口题目，原因是：
    - 前一个解法中，通过0和1的转换，然后记录前缀和来判断当前子数组是否符合题目要求
    - 那么子数组是否能符合就取决于r和l的前缀和的差是否大于K
    - 而因为数组是由0和1组成，所以前缀和一定是单调递增的，那么其实就不需要再专门维护一个前缀和数组了，只需要通过两个变量在窗口的移动过程中累加就可以了
- 算法过程：
    - 初始化左右窗口坐标的前缀和变量lsum和rsum
    - 主动移动r坐标遍历整个数组
    - 累加rsum
    - 内层循环被动移动l坐标，判断`lsum < rsum - K`
    - 因为`rsum - lsum`代表的是`l+1`和`r`这个窗口，所以需要在判断l和r区间时，先不累加当前l坐标的值，然后判断`rsum - lsum > k`是否成立，如果成立代表当前窗口不符合题目要求，还需要被动移动l坐标，直到第一个符合的情况出现，而这个时候，判断的其实就是没有加上当前l坐标值的lsum，所以可以直接使用r和l计算窗口长度
    - 当内层循环退出后，说明找到了尽可能大小的l坐标了，更新窗口大小，取最大值
### 代码
```java
class Solution {
    public int longestOnes(int[] A, int K) {
        int len = A.length, l = 0, lsum = 0, rsum = 0, ans = 0;
        for (int r = 0; r < len; r++) {
            rsum += 1 - A[r];
            while (rsum - lsum > K) {
                lsum += 1 - A[l];
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }
}
```