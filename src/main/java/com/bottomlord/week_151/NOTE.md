# [LeetCode_350_两个数组的交集II](https://leetcode.cn/problems/intersection-of-two-arrays-ii/)
## 解法
### 思路
二分查找
### 代码
```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int n1 = nums1.length, n2 = nums2.length, i2 = 0;
        for (int i = 0; i < n1; i++) {
            int num = nums1[i];
            int index = binarySearch(nums2, num, i2, n2 - 1);
            if (index != -1) {
                i2 = index + 1;
                list.add(nums2[index]);
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private int binarySearch(int[] arr, int target, int l, int r) {
        while (l <= r) {
            int mid = (l + r) >> 1;
            int num = arr[mid];

            if (num < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        if (l >= arr.length) {
            return -1;
        }

        return arr[l] == target ? l : -1;
    }
}
```
# [LeetCode_633_平方数之和](https://leetcode.cn/problems/sum-of-square-numbers/)
## 解法
### 思路
二分查找
### 代码
```java
class Solution {
    public boolean judgeSquareSum(int c) {
        for (long i = 0; i * i <= c; i++) {
            long l = i, r = c;

            while (l <= r) {
                long mid = (r - l) / 2 + l;
                long num = (long) i * i + mid * mid;

                if (num == c) {
                    return true;
                } else if (num < c) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return false;
    }
}
```
# [LeetCode_1254_统计封闭岛屿的数目](https://leetcode.cn/problems/number-of-closed-islands/)
## 解法
### 思路
dfs
- 如果搜索到边界，判断当前是否是海水，如果是，就直接返回错误，提前退出
- 其他情况就翻转陆地状态，不断搜索，直到无法继续搜索为止
### 代码
```java

```