# [LeetCode_2848_与车相交的点](https://leetcode.cn/problems/points-that-intersect-with-cars)
## 解法
### 思路
- 为了快速的记录区间坐标被车辆占用，可以使用差分思路来处理
- 遍历所有的区间二维数组`nums`，根据每个区间的结束坐标`end`得到最大坐标值`maxEnd`，利用该值得到差分数组`diff`的长度，即`maxEnd + 2`。
- 再次遍历区间数组`nums`，根据区间`interval`，在`diff`数组中记录差分值，即`diff[interval[0]]++`，`diff[interval[1] + 1]--`。
- 通过如上方式的处理后，初始化一个暂存变量`cur`，用来统计当前坐标的差分值，如果`cur`大于0，则代表当前坐标至少被1个区间所覆盖
- 遍历`diff`数组，累加当前的差分值到`cur`上，再判断`cur`是否大于0，如果是就累加1到结果值`ans`，代表当前坐标被覆盖
- 遍历结束后返回`ans`即可
### 代码
```java
class Solution {
    public int numberOfPoints(List<List<Integer>> nums) {
        int maxEnd = 0;
        for (List<Integer> interval : nums) {
            maxEnd = Math.max(maxEnd, interval.get(1));
        }
        
        int[] diff = new int[maxEnd + 2];
        for (List<Integer> interval : nums) {
            diff[interval.get(0)]++;
            diff[interval.get(1) + 1]--;
        }
        
        int cur = 0, ans = 0;
        for (int d : diff) {
            cur += d;
            ans = ans + (cur > 0 ? 1 : 0);
        }
        
        return ans;
    }
}
```