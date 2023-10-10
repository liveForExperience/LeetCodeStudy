# [LeetCode_901_股票价格跨度](https://leetcode.cn/problems/online-stock-span)
## 解法
### 思路
- 根据题目设计的前提，next函数接受的参数都会且只会作为下一次调用时候的依据，所以不需要存储全部的历史数据
- 可以模拟一个单调栈，栈内存储的元素提供2种信息：
  - 价格，用于比较
  - 坐标，用于计算跨度
- 单调性基于`next函数入参值是否大于栈顶元素`这个判断条件
  - 如果大于，那么就丢弃
  - 如果不大于，就保留且不再继续处理
- 根据如上的处理逻辑，当next函数入参处理完栈内元素后，通过坐标差就能得到跨度，而因为坐标值无法通过函数入参提供，所以类中需要同时维护一个坐标属性，并在每次调用的时候进行自增
### 代码
```java
class StockSpanner {

    private int index;
    private LinkedList<int[]> stack;
    
    public StockSpanner() {
        this.index = 0;
        this.stack = new LinkedList<>();
    }

    public int next(int price) {
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            stack.poll();
        }
        
        if (stack.isEmpty()) {
            stack.push(new int[]{price, index++});
            return index;
        }
        
        int[] peek = stack.peek();
        stack.push(new int[]{price, index});
        return index++ - peek[1];
    }
}
```