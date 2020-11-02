# LeetCode_321_拼接最大数
## 题目
给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。

求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。

说明: 请尽可能地优化你算法的时间和空间复杂度。

示例 1:
```
输入:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
输出:
[9, 8, 6, 5, 3]
```
示例 2:
```
输入:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
输出:
[6, 7, 6, 0, 4]
```
示例 3:
```
输入:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
输出:
[9, 8, 9]
```
## 解法
### 思路
- 确定两个数组的取值大小：从nums1的最小取值范围`max(k - nums2.len, 0)`到最大取值范围`min(nums1.len, k)`：
    - 如果k比nums2大，那么nums1至少取k - nums2的长度
    - 如果k比nums1小，那么nums1最多取k的长度
- 确定每组数组长度情况下的最大子序列：
    - 遍历数组
    - 如果当前数字比之前大，且还可以有舍去的数字个数，那就将前一个数组舍弃
    - 如果当前使用的数字个数超过了限定的长度，就不再增加数字，且累加已舍弃个数
- 获得两个当前长度组合下的最大子序列后，将两个子序列合并，合并过程中就是将最大值挨个拼接
- 在合并过程中，如果发现两个数字相等，需要比对它们剩余的数字组成的字符串谁更大，使用更大的那个字符串的第一个数字，否则，如果使用小的那个，那么接下来的值就可能取不到最大值了，比如
    - `02`和`05`，如果取前一个0，那么之后就只能在2和0之间取了，而其实最大值应该是先取第2个0，然后就在0和5之间取
- 最后将合并的数组和暂存的数组进行比较，并更新
### 代码
```java
class Solution {
        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        int[] ans = new int[k];
        if (len1 + len2 < k) {
            return ans;
        }

        for (int i = Math.max(k - len2, 0); i <= Math.min(len1, k); i++) {
            List<Integer> l1 = getLargestNum(nums1, i),
                          l2 = getLargestNum(nums2, k - i);

            int[] tmp = merge(l1, l2);

            if (isLarger(tmp, ans)) {
                ans = tmp;
            }
        }

        return ans;
    }

    private List<Integer> getLargestNum(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        int removeCount = nums.length - k;
        if (k == 0) {
            return ans;
        }

        for (int i = 0; i < nums.length; i++) {
            while (ans.size() > 0 && nums[i] > ans.get(ans.size() - 1) && removeCount > 0) {
                ans.remove(ans.size() - 1);
                removeCount--;
            }

            if (ans.size() < k) {
                ans.add(nums[i]);
            } else {
                removeCount--;
            }
        }

        return ans;
    }

    private int[] merge(List<Integer> a, List<Integer> b) {
        int[] ans = new int[a.size() + b.size()];
        int ia = 0, ib = 0;
        StringBuilder sba = new StringBuilder(), sbb = new StringBuilder();
        a.forEach(sba::append);
        b.forEach(sbb::append);

        for (int i = 0; i < ans.length; i++) {
            if (ia >= a.size()) {
                ans[i] = b.get(ib++);
            } else if (ib >= b.size()) {
                ans[i] = a.get(ia++);
            } else {
                if (sba.substring(ia).compareTo(sbb.substring(ib)) >= 0) {
                    ans[i] = a.get(ia++);
                } else {
                    ans[i] = b.get(ib++);
                }
            }
        }

        return ans;
    }

    private boolean isLarger(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                return true;
            } else if (a[i] < b[i]) {
                return false;
            }
        }

        return false;
    }
}
```