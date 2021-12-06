# [LeetCode_1913_两个数对之间的最大乘积差](https://leetcode-cn.com/problems/maximum-product-difference-between-two-pairs/)
## 解法
### 思路
- 数组排序，获取前2个和后2个元素
- 计算乘积差返回
### 代码
```java
class Solution {
    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - 1] * nums[nums.length - 2] - nums[0] * nums[1];
    }
}
```
## 解法二
### 思路
桶排序降低时间复杂度
### 代码
```java
class Solution {
    public int maxProductDifference(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();

        int[] bucket = new int[max + 1];
        for (int num : nums) {
            bucket[num]++;
        }

        int count = 0;
        int a = 0, b = 0, c = 0, d = 0;
        for (int i = 0; i < bucket.length;) {
            if (bucket[i] > 0) {
                bucket[i]--;
                count++;

                if (count == 1) {
                    a = i;
                } else {
                    b = i;
                }
            } else {
                i++;
            }

            if (count == 2) {
                break;
            }
        }

        for (int i = bucket.length - 1; i >= 0;) {
            if (bucket[i] > 0) {
                bucket[i]--;
                count++;

                if (count == 3) {
                    c = i;
                } else if (count == 4) {
                    d = i;
                }
            } else {
                i--;
            }

            if (count == 4) {
                break;
            }
        }

        return c * d - a * b;
    }
}
```