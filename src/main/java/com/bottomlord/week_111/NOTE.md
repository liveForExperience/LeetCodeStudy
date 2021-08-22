# [LeetCode_1512_好数对的数目](https://leetcode-cn.com/problems/number-of-good-pairs/)
## 解法
### 思路
2层循环，外层确定i，内层确定j，判断并累加有效对
### 代码
```java
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
```
## 解法二
### 思路
- 初始化一个桶数组，大小为题目数组的最大值+1，这个数组用来统计遍历过程中遇到的元素对应的次数
- 初始化一个变量用来统计有效对
- 正向遍历数组，在遍历过程中，累加当前元素在桶数组中出现的次数，并在累加后对次数做+1操作
  - 累加到总数中是因为，当前元素一定比桶数组中统计的这些相同元素的坐标值更大，那么这些元素有多少个，就能和当前元素组成多少个有效对
  - 对桶数组对应数累加1，就是为下一个出现的相同元素计算做准备
- 遍历结束返回统计值
### 代码
```java
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int  ans = 0;
        int[] bucket = new int[101];
        for (int num : nums) {
            ans += bucket[num]++;
        }
        return ans;
    }
}
```