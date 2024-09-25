# [LeetCode_2956_找到两个数组中的公共元素](https://leetcode.cn/problems/find-common-elements-between-two-arrays)
## 解法
### 思路
- 使用2个长度为101的数组`memo1`和`memo2`用于记录`nums1`和`nums2`数组中的元素的存在情况
- 分别遍历2个数组，然后将元素记录到`memo1`和`memo2`中
- 分别遍历2个数组，根据记录数组来判断是否计数，如果出现就累加1，否则就跳过
- 最终返回2个数组对应的记录数
### 代码
```java
class Solution {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        boolean[] memo1 = new boolean[101], memo2 = new boolean[101];
        for (int num : nums1) {
            memo1[num] = true;
        }

        for (int num : nums2) {
            memo2[num] = true;
        }

        int cnt1 = 0, cnt2 = 0;
        for (int num : nums1) {
            if (memo2[num]) {
                cnt1++;
            }
        }

        for (int num : nums2) {
            if (memo1[num]) {
                cnt2++;
            }
        }

        return new int[]{cnt1, cnt2};
    }
}
```
# [LeetCode_3112_访问消失节点的最少时间](https://leetcode.cn/problems/minimum-time-to-visit-disappearing-nodes)
## 解法
### 思路
- 先基于`edges`数组初始化邻接表
- 然后从0节点开始做bfs
- bfs过程中记录度过的时间
  - 如果第一次到达目标时，时间已经超过了`disappear`的对应坐标的时间，那么就记录-1
  - 否则就记录当前路径上消耗的路径值，如果再次得到相同坐标的路径值，那么就取相对小的值。
- bfs结束，返回结果
### 代码
```java

```