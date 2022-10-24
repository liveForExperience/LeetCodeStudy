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
# [LeetCode_901_股票价格跨度](https://leetcode.cn/problems/online-stock-span/)
## 解法
### 思路
单调栈：
- 初始化一个坐标属性，用于记录next函数调用的次数
- 初始化一个栈数据结构，栈中存储一个二元组，分别记录股票价格和坐标
- next函数被调用的时候，依次将栈顶比price入参小的元素依次出栈，直到遇到比price大的元素位置，然后统计index之间的距离，得到当前price的结果返回
### 代码
```java
class StockSpanner {

    private int index;
    private Stack<int[]> stack;

    public StockSpanner() {
        this.index = 0;
        this.stack = new Stack<>();
    }

    public int next(int price) {
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            stack.pop();
        }
        
        if (stack.isEmpty()) {
            stack.push(new int[]{price, index++});
            return index;
        }
        
        int[] arr = stack.peek();
        stack.push(new int[]{price, index});
        
        return index++ - arr[1];
    }
}
```