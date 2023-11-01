# [LeetCode_275_H指数II](https://leetcode.cn/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts)
## 解法
### 思路
- 思考过程：
  - `citations`数组在本题中是默认升序排序的，而题目要求解的h值在求解过程中同时具备单调性，值越大，对应要查找的元素坐标值也就越大
  - 那么在如上状态下就可以通过二分查找的方式来解这个问题
- 算法过程：
  - 定义二分的头尾指针，对应`citations`数组的坐标值`h`
  - 二分后获取中间值，该值作为坐标找到对应的值是论文的引用次数
  - 然后用元素总个数减去坐标值，就能得到大于`h`的论文个数
    - 如果`h < n - mid`，说明引用次数太小，需要将搜索区间缩小到右侧
    - 如果`h > n - mid`，说明引用次数足够大，这个值的右侧不需要再搜索，将搜索区间缩小到左侧
    - 如果`h == n - mid`，说明引用次数足够大，和`h > n - mid`代表的方式一致
  - 搜索结束后，head值对应的能够满足引用次数的最小坐标值，用`n`减去该值就是结果值
### 代码
```java
class Solution {
    public int hIndex(int[] citations) {
        int head = 0, n = citations.length, tail = n - 1;

        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            
            int h = citations[mid];
            if (h < n - mid) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return n - head;
    }
}
```