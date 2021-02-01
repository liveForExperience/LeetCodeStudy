# [LeetCode_888_公平的糖果棒交换](https://leetcode-cn.com/problems/fair-candy-swap/)
## 解法
### 思路
hash表+迭代
### 代码
```java
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int a = Arrays.stream(A).sum(), b = Arrays.stream(B).sum(), diff = a - b;
        Set<Integer> set = new HashSet<>();
        for (int num : A) {
            set.add(num);
        }

        for (int num : B) {
            if (set.contains(diff / 2 + num)) {
                return new int[]{diff / 2 + num, num};
            }
        }

        throw new RuntimeException();
    }
}
```