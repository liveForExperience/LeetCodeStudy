# [LeetCode_1905_统计子岛屿](https://leetcode.cn/problems/count-sub-islands/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_732_我的日程安排III](https://leetcode.cn/problems/my-calendar-iii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_875_爱吃香蕉的珂珂](https://leetcode.cn/problems/koko-eating-bananas/)
## 解法
### 思路
- 通过观察可以发现，速度与时间的关系时线性的，所以求解速度过程中呈现单调性，可以通过二分查找来找到这个最小的速度
- 最小的速度一定是1，最大的速度，可以设置为最大的堆的香蕉个数
- 然后二分查找最小的，满足h小时内可以吃完香蕉的速度
### 代码
```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0;
        for (int pile : piles) {
            r = Math.max(pile, r);
        }

        int ans = 0;
        while (l <= r) {
            int mid = (r - l) / 2 + l;

            int time = getTime(piles, mid);

            if (time <= h) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    private int getTime(int[] piles, int speed) {
        int time = 0;
        for (int pile : piles) {
            time += (pile + speed - 1) / speed;
        }
        return time;
    }
}
```