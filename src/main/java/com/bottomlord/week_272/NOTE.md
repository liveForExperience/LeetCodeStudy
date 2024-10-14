# [LeetCode_2374_边积分最高的节点](https://leetcode.cn/problems/node-with-highest-edge-score)
## 解法
### 思路
- 使用一个数组`bucket`来累加边积分，累加的方式是通过坐标`i`遍历数组`edge`
  - 坐标值`edge[i]`作为`bucket`的坐标
  - 坐标`i`作为累加的值
- 使用2个变量，分别记录当前最大边积分的值`max`，以及最大边积分对应的坐标`maxi`
- 在遍历过程中，通过比较`max`来判断是否要更新`max`和`maxi`
  - 如果`bucket[edge[i]] > max`：更新`max`，并更新`maxi`
  - 如果`bucket[edge[i]] == max`：取`i`与`maxi`之间的最小值作为新的`maxi`
- 需要注意的是，累加过程中，如果使用int作为声明`max`的类型，在极端用例中会遇到溢出的问题，所以要是用long类型来声明
- 遍历结束后返回`maxi`即可
### 代码
```java
class Solution {
    public int edgeScore(int[] edges) {
        int maxi = edges.length;
        long max = -1;
        long[] bucket = new long[edges.length];
        for (int i = 0; i < edges.length; i++) {
            bucket[edges[i]] += i;
            if (bucket[edges[i]] > max) {
                max = bucket[edges[i]];
                maxi = edges[i];
            } else if (bucket[edges[i]] == max) {
                maxi = Math.min(edges[i], maxi);
            }
        }
        return maxi;
    }
}
```
# [LeetCode_2535_数组元素和与数字和的绝对差](https://leetcode.cn/problems/difference-between-element-sum-and-digit-sum-of-an-array)
## 解法
### 思路
- 遍历数组，累加元素与元素数位和之间的差值
- 遍历结束后，将累加值求绝对值返回即可
### 代码
```java
class Solution {
    public int differenceOfSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += (num - sumDigit(num));
        }
        return Math.abs(sum);
    }
    
    private int sumDigit(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
```