# [LeetCode_3133_数组最后一个元素的最小值](https://leetcode.cn/problems/minimum-array-end)
## 解法
### 思路
- 保留x上为1的位不变的情况下，为0位被修改时，第n小的那个修改方案的值。因为x自身也是一种情况，所以实际就是第n-1小的值。
- 因为n-1这个值，自身就代表有n-1种01排列状态，所以问题就相当于把n-1放入到x为0的位里。
- 所以这是一个1层循环，使用2个坐标来处理填充的逻辑：
    - 坐标i：用来确定x在二进制表示时的位数，从最低位开始
    - 坐标j：用来确定`n - 1`在二进制表示时的位数，从最低位开始
    - 循环的主体是遍历i
        - 当i对应的二进制是1的时候，不处理
        - 当i对应的二进制是0的时候，如果此时j对应的二进制是1，那么使用或运算将1填充到x中，否则只累加j坐标
    - 循环的判断条件则是看`n-1`的所有有效位是否全部处理过
- 循环结束后，返回被填充后的x值即可
### 代码
```java
class Solution {
    public long minEnd(int n, int x) {
        int m = n - 1, i = 0, j = 0;
        long ans = x;
        while ((m >> j) > 0) {
            if ((ans >> i & 1) == 0) {
                ans |= (long)(m >> j & 1) << i;
                j++;
            }
            
            i++;
        }
        
        return ans;
    }
}
```
# [LeetCode_3145_大数组元素的乘积](https://leetcode.cn/problems/find-products-of-elements-of-big-array)
## 解法
### 思路

### 代码
```java

```