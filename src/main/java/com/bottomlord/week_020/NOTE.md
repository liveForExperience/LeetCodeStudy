# LeetCode_932_漂亮数组
## 
对于某些固定的 N，如果数组 A 是整数 1, 2, ..., N 组成的排列，使得：

对于每个 i < j，都不存在 k 满足 i < k < j 使得 A[k] * 2 = A[i] + A[j]。

那么数组 A 是漂亮数组。

给定 N，返回任意漂亮数组 A（保证存在一个）。

示例 1：
```
输入：4
输出：[2,1,4,3]
```
示例 2：
```
输入：5
输出：[3,1,2,5,4]
```
提示：
```
1 <= N <= 1000
```
## 解法
### 思路
- 通过观察可得，公式`A[k] * 2 = A[i] + A[j]`有如下特点：
    - 公式左边部分恒为偶数
    - 公式右边可分为`A[i]`和`A[j]`两部分，也就是`left`和`right`
    - 如果希望等式恒不相等，就需要右边部分为奇数，所以可以使`left`为奇数和`right`为偶数
- 分治：
    - 可以将数组对半分成左右两部分，一中间点k为分界，左边可以映射成`2 * k - 1`，右边映射成`2 * k`，从而填满整个N
    - 这个过程可以不断地递归分治，直到N为1，不能再分割了为止
    - 还可以通过memo进行记忆化搜索
### 代码
```java
class Solution {
    public int[] beautifulArray(int N) {
        Map<Integer, int[]> memo = new HashMap<>();
        return recurse(N, memo);
    }

    private int[] recurse(int n, Map<Integer, int[]> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int[] ans = new int[n];

        if (n == 1) {
            ans[0] = 1;
        } else {
            int index = 0;
            for (int i : recurse((n + 1) / 2, memo)) {
                ans[index++] = 2 * i - 1;
            }

            for (int i : recurse(n / 2, memo)) {
                ans[index++] = 2 * i;
            }
        }

        memo.put(n, ans);
        return ans;
    }
}
```