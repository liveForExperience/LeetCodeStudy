# [LeetCode_LCP30_魔塔游戏](https://leetcode.cn/problems/p0NxJO)
## 解法
### 思路
- 思考过程：
  - 从头到尾遍历的话，累加遍历到的数字，就可以得到一个状态：现在是否已经累加到了负值
    - 如果是，就需要把已经遍历到的负值搬到数组最后，其实也就可以理解成去除掉，然后判断是否还是负值，循环往复
  - 如果要实现这个状态下的操作，就需要做2件事
    - 遍历和累加遍历到的元素
    - 将遍历到的负数存在一个集合中
  - 为了最小化搬移的次数，那么每次要往数组后面放的就应该是最小的那个负数，为了快速的获取到这个排序的负数列表，就可以用小顶堆来存储
- 算法过程：
  - 初始化变量`sum`，用于存储累加的生命值
  - 初始化变量`cur`，用于记录当前生命值的状态，如果该值小于零，就需要触发移动的逻辑
  - 初始化变量`cnt`，用于记录移动的次数
  - 初始化一个优先级队列`queue`，用于存储遍历到的负数
  - 遍历`nums`数组
    - 将遍历到的元素`num`累加到`sum`和`cur`上
    - 判断`num`是否是负数，如果是，就添加到`queue`中
    - 判断`cur`是否小于0，如果是
      - 就将`queue`顶部的元素弹出，并加强从`cur`中减去
      - 并累加`cnt`
  - 遍历结束后，判断`sum`是否小于0，如果是就返回-1，否则返回`cnt`
### 代码
```java
class Solution {
    public int magicTower(int[] nums) {
        long sum = 0, cur = 0;
        int cnt = 0;
        Queue<Long> queue = new PriorityQueue<>();
        for (int num : nums) {
            sum += num;
            cur += num;
            
            if (num < 0) {
                queue.offer((long)num);
            }

            if (cur < 0) {
                cnt++;
                cur -= queue.poll();
            }
        }
        
        return sum < 0 ? -1 : cnt;
    }
}
```