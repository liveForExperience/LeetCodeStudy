# [LeetCode_540_有序数组中的单一元素](https://leetcode-cn.com/problems/single-element-in-a-sorted-array/)
## 解法
### 思路
- 两个一样的数字抑或后是0
- 基于如上特性，遍历数组并抑或元素
- 遍历结束，不停抑或后得到的值就是单一元素
### 代码
```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int num = 0;
        for (int i : nums) {
            num ^= i;
        }
        return num;
    }
}
```
# [LeetCode_offerII41_滑动窗口的平均值](https://leetcode-cn.com/problems/qIsx9U/)
## 解法
### 思路
队列存储
### 代码
```java
class MovingAverage {

    private Queue<Integer> queue;
    private int size;
    private int average;
    
    public MovingAverage(int size) {
        this.queue = new ArrayDeque<>();
        this.size = size;
    }

    public double next(int val) {
        queue.offer(val);
        if (queue.size() > size) {
            queue.poll();
        }

        int sum = 0;
        for (Integer num : queue) {
            sum += num;
        }
        
        return sum * 1D / queue.size();
    }
}
```
## 解法二
### 思路
在解法一的基础上优化next函数算法
- 不再每次都遍历一次队列求sum
- 暂存sum，然后再暂存值上做处理，如果size超了，就删除队头元素，加入队尾元素，然后再求平均值
### 代码
```java
class MovingAverage {

    private Queue<Integer> queue;
    private int size;
    private int sum;

    public MovingAverage(int size) {
        this.queue = new ArrayDeque<>();
        this.size = size;
    }

    public double next(int val) {
        queue.offer(val);
        sum += val;
        if (queue.size() > size) {
            assert !queue.isEmpty();
            sum -= queue.poll();
        }
        return sum * 1D / queue.size();
    }
}
```