# [LeetCode_415_字符串相加](https://leetcode.cn/problems/add-strings/)
## 解法
### 思路
- 模拟的时候如何设计算法很有讲究
- 从题目看，循环遍历两个字符串是肯定的
- 那么退出条件该如何设定？
  - 2个字符串没有遍历完肯定要继续循环
  - 字符与字符相加后，和大于9，有了进位，那也需要继续循环
- 循环内部的处理逻辑该如何设计？
  - 两个加数需要从字符转换为整型数字，然后通过运算获取
  - 当前位的和应该是`sum = a + b + carry`
  - 当前位额值应该是`num = sum % 10`
  - 进位应该是`carry = num / 10`
- 处理结束，返回字符串即可
### 代码
```java
class Solution {
    public String addStrings(String num1, String num2) {
        char[] cs1 = num1.toCharArray(), cs2 = num2.toCharArray();
        int i1 = cs1.length - 1, i2 = cs2.length - 1, carry = 0;
        StringBuilder ans = new StringBuilder();
        while (i1 >= 0 || i2 >= 0 || carry != 0) {
            int a = i1 >= 0 ? cs1[i1--] - '0' : 0,
                b = i2 >= 0 ? cs2[i2--] - '0' : 0;
            int num = a + b + carry;
            ans.insert(0, num % 10);
            carry = num / 10;
        }
        return ans.toString();
    }
}
```
# [LeetCode_1851_包含每个查询的最小区间](https://leetcode.cn/problems/minimum-interval-to-include-each-query/)
## 解法
### 思路
- 如果不对queries和intervals做任何处理，那么每次要确定一个query对应的答案，就需要完整遍历一次intervals，这样的时间复杂度是`O(N²)`
- 为了降低时间复杂度，可以通过对两个数组排序的方式来处理
  - 初始化一个填充了queries数组对应坐标的数组qis, 并以queries的元素值进行非降序排序
  - intervals以元素数组的第一个元素进行非降序排序
- 排序完成后，处理逻辑就变成了：
  - 初始化一个ans数组，长度为queries的长度，且用-1填充，从而免去无答案时为-1的后续赋值逻辑
  - 初始化一个对应intervals的坐标i
  - 初始化一个优先级队列queue，队列存储的是一个长度为3的数组元素
    - 第1个元素是intervals数组元素所对应区间的长度`intervals[i][1] - intervals[i][0] + 1`，用来做优先级排序的依据
    - 第2个元素是intervals数组元素的第1个元素
    - 第3个元素是intervals数组元素的第2个元素
    - 如上第2第3个元素是用来做queries对应答案是否匹配的依据
  - queue的比较逻辑：以数组元素的第1个元素非降序排序
  - 2层循环
    - 外层遍历qis数组，获取到非降序排序下的当前元素值`query = queries[qis[index]]`
    - 内层做如下的事：
      - 基于i坐标遍历intervals数组，将不大于query元素值的数组全部放入优先级队列中，并自增i，直到遇到大于query的i坐标元素位置。这
      - 遍历优先级队列，将堆顶元素中item[2]小于query的元素出队，因为这个item对应的区间不包含query，同时，因为query是升序的，所以如果当前item区间不满足当前query的要求，那么一定也不会满足之后的query元素
      - 然后查看当前的队列的堆顶元素，这个元素一定具备如下几个特点，从而使它是当前query的答案
        - 是堆中区间长度最短的
        - 起始坐标是不大于当前query的
      - 结尾坐标是不小于当前query的
  - 遍历结束后返回ans答案即可
### 代码
```java
class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = queries.length, i = 0;
        int[] ans = new int[n];
        Integer[] qis = new Integer[n];
        for (int k = 0; k < n; k++) {
            qis[k] = k;
        }

        Arrays.fill(ans, -1);
        Arrays.sort(qis, Comparator.comparingInt(x -> queries[x]));
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        for (int index = 0; index < n; index++) {
            int query = queries[qis[index]];
            while (i < intervals.length && intervals[i][0] <= query) {
                queue.offer(new int[]{intervals[i][1] - intervals[i][0] + 1, intervals[i][0], intervals[i][1]});
                i++;
            }

            while (!queue.isEmpty() && queue.peek()[2] < query) {
                queue.poll();
            }

            if (!queue.isEmpty()) {
                ans[qis[index]] = queue.peek()[0];
            }
        }

        return ans;
    }
}
```