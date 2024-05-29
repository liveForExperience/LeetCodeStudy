# [LeetCode_2951_找出峰值](https://leetcode.cn/problems/find-the-peaks)
## 解法
### 思路
- 从数组坐标为1的元素开始遍历，终止坐标为`n - 1`（`n`为数组长度）
- 将遍历到的元素与前后元素进行比较，如果严格大于前后元素，则将该元素对应的坐标在答案列表`ans`中
- 遍历结束后，返回`ans`即可
### 代码
```java
class Solution {
    public List<Integer> findPeaks(int[] mountain) {
        int n = mountain.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```