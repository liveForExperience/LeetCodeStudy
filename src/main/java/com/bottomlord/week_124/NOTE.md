# [LeetCode_384_打乱数组](https://leetcode-cn.com/problems/shuffle-an-array/)
## 解法
### 思路
使用random函数暴力解
### 代码
```java
class Solution {
    int[] origin;
    public Solution(int[] nums) {
        this.origin = nums;
    }

    public int[] reset() {
        return origin;
    }

    public int[] shuffle() {
        List<Integer> list = new ArrayList<>();
        for (int num : origin) {
            list.add(num);
        }

        int[] nums = new int[origin.length];
        Random ran = new Random();
        for (int i = 0; i < origin.length; i++) {
            int index = ran.nextInt(list.size());
            nums[i] = list.get(index);
            list.remove(index);
        }

        return nums;
    }
}
```
## 解法二
### 思路
- 解法一中，shuffle函数里，对存储数组的list的remove操作，时间复杂度较高，可以对这一操作进行优化
- 可以在remove的过程中，将使用的数字放置到list的顶部，然后随机就从之后的坐标中选择
### 代码
```java
class Solution {
    int[] origin;
    public Solution(int[] nums) {
        this.origin = nums;
    }

    public int[] reset() {
        return origin;
    }

    public int[] shuffle() {
        int[] copy = new int[origin.length], nums = new int[origin.length];
        System.arraycopy(origin, 0, copy, 0, origin.length);

        Random ran = new Random();
        for (int i = 0; i < origin.length; i++) {
            int j = ran.nextInt(origin.length - i);
            nums[i] = copy[i + j];
            int tmp = copy[i];
            copy[i] = copy[i + j];
            copy[i + j] = tmp;
        }

        return nums;
    }
}
```