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