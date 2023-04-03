# [LeetCode_957_N天后的牢房](https://leetcode.cn/problems/prison-cells-after-n-days/)
## 失败解法
### 原因
超时
### 思路
暴力模拟
### 代码
```java
class Solution {
    public int[] prisonAfterNDays(int[] cells, int n) {
        int[] pre = cells;
        while (n-- > 0) {
            int[] cur = new int[8];
            for (int i = 0; i < 8; i++) {
                if (i == 0 || i == 7) {
                    cur[i] = 0;
                    continue;
                }
                
                if (pre[i - 1] != pre[i + 1]) {
                    cur[i] = 0;
                } else {
                    cur[i] = 1;
                }
            }
            
            pre = cur;
        }
        
        return pre;
    }
}
```
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1053_交换一次的先前排列](https://leetcode.cn/problems/previous-permutation-with-one-swap/)
## 解法
### 思路
- 从低位开始找到第一个和前一个元素组成升序的序列
- 然后从当前位置向前，找到比当前元素小的最大元素（因为之前是降序，所以也就是尽可能的低位）
- 需要注意，在内层找到升序后，开始向前查找时候，因为题目要求的是最大的小于arr序列的数，所以如果出现[3,1,1,3]这样的序列，最高位的3应该和第一个1交换，而不能同第二个1交换，所以还需要在遍历过程中记录最大值，当出现相等的值时候，不使用后续找到的坐标
### 代码
```java
class Solution {
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                boolean find = false;
                int index = -1, maxValue = 0;
                for (int j = i + 1; j < n; j++) {
                    if (arr[i] > arr[j] && arr[j] > maxValue) {
                        find = true;
                        maxValue = arr[j];
                        index = j;
                    }
                }

                if (find) {
                    int tmp = arr[index];
                    arr[index] = arr[i];
                    arr[i] = tmp;
                    return arr;
                }
            }
        }
        return arr;
    }
}
```