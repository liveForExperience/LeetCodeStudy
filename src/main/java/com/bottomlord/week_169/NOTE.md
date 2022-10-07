# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_927_三等分](https://leetcode.cn/problems/three-equal-parts/)
## 解法
### 思路
- 求出1的个数，看是否能被3整除
- 求出最后一部分的后导零的个数
- 根据后导零的个数，求出前两部分的合适的分割点，也就是第一部分的后导零位置，如果凑不齐后导零，就说明无法成功切分
### 代码
```java
class Solution {
    public int[] threeEqualParts(int[] arr) {
        int count = 0, n = arr.length;
        for (int num : arr) {
            count += num;
        }

        if (count == 0) {
            return new int[]{0, n - 1};
        }

        if (count % 3 != 0) {
            return new int[]{-1, -1};
        }

        int len = count / 3, first = -1,
                second = -1, third = -1,
                cur = 0;

        for (int i = 0; i < n; i++) {
            int num = arr[i];

            if (num == 0) {
                continue;
            }

            if (cur == 0) {
                first = i;
            } else if (cur == len) {
                second = i;
            } else if (cur == 2 * len) {
                third = i;
            }

            cur++;
        }

        int suffix = n - third;

        if (second - first < suffix ||
            third - second < suffix) {
            return new int[]{-1, -1};
        }

        for (int i = first, j = second, k = third; k < n; i++, j++, k++) {
            if (arr[i] != arr[j] ||
                arr[j] != arr[k]) {
                return new int[]{-1, -1};
            }
        }

        return new int[]{first + suffix - 1, second + suffix};
    }
}
```