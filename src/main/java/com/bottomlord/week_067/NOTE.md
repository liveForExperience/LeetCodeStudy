# LeetCode_306_累加数
## 题目
累加数是一个字符串，组成它的数字可以形成累加序列。

一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。

给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。

说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。

示例 1:
```
输入: "112358"
输出: true 
解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
```
示例 2:
```
输入: "199100199"
输出: true 
解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
进阶:
你如何处理一个溢出的过大的整数输入?
```
## 解法
### 思路
回溯+剪枝
### 代码
```java
class Solution {
    public boolean isAdditiveNumber(String num) {
        return backTrack(num, 0, 0, 0, 0);
    }

    private boolean backTrack(String num, int index, long pre, long sum, int count) {
        if (index == num.length()) {
            return count > 2;
        }

        for (int i = index; i < num.length(); i++) {
            long cur = getNum(num, index, i);

            if (cur < 0) {
                continue;
            }

            if (count >= 2 && cur != sum) {
                continue;
            }
            
            if (backTrack(num, i + 1, cur, pre + cur, count + 1)) {
                return true;
            }
        }
        
        return false;
    }

    private long getNum(String num, int l, int r) {
        if (l < r && num.charAt(l) == '0') {
            return -1;
        }

        long ans = 0;
        while (l <= r) {
            ans = 10 * ans + (num.charAt(l++) - '0');
        }
        return ans;
    }
}
```
# LeetCode_307_区域和检索-数组可修改
## 题目
给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。

示例:
```
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
```
说明:
```
数组仅可以在 update 函数下进行修改。
你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
```
## 解法
### 思路
暴力，查询一次遍历一次
### 代码
```java
class NumArray {
    private int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
    }

    public void update(int i, int val) {
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for (int index = i; index <= j; index++) {
            sum += nums[index];
        }
        return sum;
    }
}
```
## 解法二
### 思路
前缀和，更新一次，维护一次前缀和
### 代码
```java
class NumArray {
    private int[] nums;
    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        this.nums = new int[nums.length];
        this.nums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            this.nums[i] = this.nums[i - 1] + nums[i];
        }
    }

    public void update(int i, int val) {
        int diff = i == 0 ? -(nums[0] - val) : -(nums[i] - nums[i - 1] - val);
        for (int index = i; index < nums.length; index++) {
            nums[index] += diff;
        }
    }

    public int sumRange(int i, int j) {
        return i == 0 ? nums[j] : nums[j] - nums[i - 1];
    }
}
```
## 解法三
### 思路
线段树：
- 构建线段树
    - 从下往上构建，坐标i的值等于`2i` + `2i + 1`
    - 坐标1的值就是整个数组元素的和
    - 初始化时，原数组长度n，则构建一个新数组，长度为2n
    - 新数组的最后n个元素作为线段树的叶子节点
    - 然后从第`n-1`个元素开始从下往上构建
- 更新线段树
    - 更新线段树，就是更新线段树的叶子节点i
    - 确定更新的叶子节点是左子树还是右子树，根据左右子树，确定另一半
    - 而它们的父节点就是`i / 2`
    - 重复如上过程，直到`i == 0`
- 查询范围之和
    - 初始化两个指针，指向要求区域在线段树中对应的叶子节点
    - 在向上搜索过程中：
        - 如果左指针指向的是右子树，那这个子树的值无法通过父节点的值获得，需要单独累加，同时将左指针移动到右边的一组左右子树上
        - 同理，右指针如果指向的是一个左子树，那么也无法通过父节点单独获得该值，需要单独累加，并向左移动右指针
        - 处理完特殊情况后，通过指针/2，模拟向上搜索的动作，并重复如上操作
        - 直到左指针大于右指针，这种情况发生在找到最后父节点后，并因为指针相等，必定触发某一个指针的累加和移动动作，从而完成真个范围值的累加
    - 从叶子节点开始向上搜索，直到两个指针相遇
### 代码
```java
class NumArray {
    private int[] tree;
    private int n;
    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[2 * n];
        for (int i = 0, j = n; j < 2 * n; i++, j++) {
            tree[j] = nums[i];
        }

        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    public void update(int i, int val) {
        i += n;
        tree[i] = val;
        while (i > 0) {
            int l = i % 2 == 0 ? i : i - 1,
                r = i % 2 == 0 ? i + 1 : i;
            i /= 2;
            tree[i] = tree[l] + tree[r];
        }
    }

    public int sumRange(int i, int j) {
        int l = i += n, r = j += n;
        int sum = 0;
        while (l <= r) {
            if (l % 2 == 1) {
                sum += tree[l++];
            }

            if (r % 2 == 0) {
                sum += tree[r--];
            }

            l /= 2;
            r /= 2;
        }

        return sum;
    }
}
```