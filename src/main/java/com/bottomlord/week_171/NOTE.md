# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_904_水果成篮](https://leetcode.cn/problems/fruit-into-baskets/)
## 解法
### 思路
滑动窗口
- 使用map来记录当前窗口中出现的水果及对应的个数
- 初始化滑动窗口的左右指针
  - 通过每次移动一下右指针来确定最大值
  - 通过调整左指针来保证当前最大的长度值
- 滑动结束，返回滑动过程中更新的最大值
### 代码
```java
class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;

        for (int l = 0, r = 0; r < fruits.length; r++) {
            int fruit = fruits[r];
            map.put(fruit, map.getOrDefault(fruit, 0) + 1);

            if (map.size() > 2) {
                while (l <= r && map.size() > 2) {
                    map.put(fruits[l], map.get(fruits[l]) - 1);
                    if (map.get(fruits[l]) == 0) {
                        map.remove(fruits[l]);
                    }
                    l++;
                }
            }

            max = Math.max(max, r - l + 1);
        }
        
        return max;
    }
}
```