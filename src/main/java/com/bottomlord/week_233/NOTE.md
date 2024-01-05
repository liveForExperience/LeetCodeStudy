# [LeetCode_1954_收集足够苹果的最小花园周长](https://leetcode.cn/problems/minimum-garden-perimeter-to-collect-enough-apples)
## 解法
### 思路
- 思考过程：
  - 通过推算得到周长为4时候的苹果个数分布如下：
    ```text
    6 5 4 3 4 5 6
    5 4 3 2 3 4 5
    4 3 2 1 2 3 4
    3 2 1 0 1 2 3
    4 3 2 1 2 3 4
    5 4 3 2 3 4 5
    6 5 4 3 4 5 6
    ```
  - 如果刨除最中间点的0，整个分布可以被平均分成相同的4个如下部分：
    ```text
    6 5 4
    5 4 3
    4 3 2
    3 2 1
    ```
  - 通过观察可以发现被拆分的这个部分，`3,2,1`这一行是1到`len`（边长，此处为3），其余的3行则是在第一行基础上每一行的元素都加`1...n`
  - 总和就可以通过如下2部分组合而成
    - 首先是第一行的总和，通过求和公式`Sn1=n(a1+an)/2`来求得
      - `n`：`len`
      - `a1`：1
      - `an`：`len`
    - 其次是每一行的总和，可以通过另一个等差数列求和公式`Sn2=n*a1+n(n-1)d/2`
      - `n`：`len + 1`
      - `a1`：`Sn1`
      - `d`：`len`
  - 那么边长为`len`范围内的所有苹果的数量就是`Sn2 * 4`
  - 所以苹果总数就是：
    - `((n(1 + n) / 2) * (n + 1) + (n + 1) * n * n / 2) * 4`
    - `((n + 1)*(n^2 + n + n^2) / 2) * 4`
    - `2n (n + 1)(2n + 1)`
  - 然后通过二分查找来找这个n值
- 算法过程：
  - 初始化二分查找的2个边界
    - `head`：`1`
    - `tail`：`100000`
  - 通过公式来判断`n`是否符合要求，并不断二分，最终确定第一个`2n (n + 1) (2n + 1) >= needApple`的n值
### 代码
```java
class Solution {
    public long minimumPerimeter(long neededApples) {
        long head = 1, tail = 100000, ans = -1;

        while (head <= tail) {
            long mid = head + (tail - head) / 2,
                    target = 2 * mid * (mid + 1) * (2 * mid + 1);

            if (target >= neededApples) {
                ans = mid * 8;
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }

        return ans;
    }
}
```