# [LeetCode_518_零钱兑换II](https://leetcode.cn/problems/coin-change-ii)
## 解法
### 思路
动态规划：
- 涂格子
- `dp[i]`: 总金额为i的情况下的所有可能数
- 状态转义方程：`dp[i] += dp[i - coin]`，当前的金额是以某种硬币金额的选择累加出来的
- base case：`dp[0] = 1`，金额为0的可能数是1，也即什么硬币都不选
- 算法过程：
  - 创建数组`dp[amount + 1]`
  - 初始化数组：`dp[0] = 1`
  - 2层循环
    - 第一层遍历所有硬币
    - 第二层通过硬币的金额一层层的涂格子，第二层的遍历区间是`[coin, amount]`
  - 遍历结束后，返回`dp[amount]`即可
### 代码
```java
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
```
# [LeetCode_2580_统计将重叠区间合并成组的方案数](https://leetcode.cn/problems/count-ways-to-group-overlapping-ranges)
## 解法
### 思路
- 将有交集的区间进行合并，最终合并成一组没有交集的区间集合
- 合并的方式
  - 先对数组进行排序，排序规则是对区间起始值进行升序排序
  - 遍历排序后的数组
    - 内层先将当前元素的结尾变量初始化为变量`end`
    - 然后从当前元素的后一个开始遍历，遍历时判断当前元素是否小于等于end，如果是，说明有交集，然后更新`end`值为两者之间的最大值，然后继续遍历判断，直到越界或者不符合
- 得到区间总个数后，因为每个区间都可以放在任意一个组中，所以如果有`m`个区间，那么组合数就是`2 ^ m`
  - 那么实际在遍历合并区间的时候，可以同时暂存记录到的区间个数`m`和组合数`ans`，然后通过`ans = ans * 2`的方式等价替换掉`2 ^ "(m + 1)`的公式，同时通过取模保证数值不越界，得到答案`ans`
- 最终返回`ans`即可
### 代码
```java
class Solution {
    public int countWays(int[][] ranges) {
        int ans = 1, mod = 1000000007, n = ranges.length;
        Arrays.sort(ranges, Comparator.comparingInt(x -> x[0]));
        for (int i = 0; i < ranges.length;) {
            int end = ranges[i][1];
            while (i < n && ranges[i][0] <= end) {
                end = Math.max(end, ranges[i][1]);
                i++;
            }
            
            ans = ans * 2 % mod;
        }
        
        return ans;
    }
}
```
# [LeetCode_2980_元素和最小的山形三元组I](https://leetcode.cn/problems/minimum-sum-of-mountain-triplets-i)
## 解法
### 思路
- 初始化2个数组`lr`和`rl`，分别记录从左到右和从右到左，到达当前坐标`i`位置的区间最小值
- 遍历`nums`，基于当前坐标元素，与`lr`和`rl`相同坐标位置的元素进行比较，如果分别都大于2个数组中的对应元素，说明当前元素在左右区间都能找到一个比自身小的元素值，且这2个值分别都是最小的，然后将3个元素累加，用累加值与暂存的最小值进行比较，保留较小值
- 暂存的最小值可以初始化为int最大值，方便计算
### 代码
```java
class Solution {
    public int minimumSum(int[] nums) {
        int n = nums.length;
        int[] lr = new int[n], rl = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            lr[i] = min;
        }
        
        min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            rl[i] = min;
        }
        
        min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] > lr[i] && nums[i] > rl[i]) {
                min = Math.min(nums[i] + lr[i] + rl[i], min);
            }
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
```
# [LeetCode_2952_需要添加的硬币的最小数量](https://leetcode.cn/problems/minimum-number-of-coins-to-be-added)
## 解法
### 思路
- 假设现在需要构建的目标金额是`s`，且已经能够构造出`[0, s - 1]`区间内的所有元素
- 如果此时在`coins`数组中增加一个元素`x`，那么区间`[x, s + x - 1]`也可以构造出来了
- 如果此时：
  - x <= s，那么就可以构造出`[0, s + x - 1]`
  - x > s，那么现在只能构造出`[0, s - 1]`和`[x, s + x - 1]`，中间`[s, x - 1]`这个区间是断开的，此时就需要添加元素`s`，这样就能将区间`[0, s - 1]`扩展到`[0, 2 * s - 1]`，而此时的目标金额`s`就变成了`2 * s`
- 此时就需要进行下一轮的比较了，比较的内容从现在的`x`与`s`变成了`x`和`2 * s`，然后再根据如上的分支逻辑进行处理
### 代码
```java

```
# [LeetCode_331_验证二叉树的前序序列化](https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree)
## 解法
### 思路
- 初始化一个变量`cur`，变量对应`槽位`数
- 所谓`槽位`，就是
  - 假设现在开始遍历二叉树，那么就有一个对应根节点的槽位，需要被填充
  - 如果遍历到的字符串不是井号，那么消耗掉栈中的一个槽位，并额外压入2个槽位，代表一个槽位被当前值填充了，且因为不是空，所以会有2个新的槽位要被填充
  - 如果遍历到的字符串是井号，那么消耗掉栈中的一个槽位，代表当前这个槽位被空填充了
- 然后，将字符串根据`,`切分成数组
- 初始化`cur`为1，代表从根开始判断槽位了
- 遍历字符串数组，判断当前`cur`是不是已经是0了，如果是0了，说明现在已经没有槽位需要被填充，但因为现在又遍历到了一个新的元素，那么无论是什么元素，都和前序二叉树的结构不符合，就返回false
- 否则，就判断是否字符串为井号：
  - 是：`cur--`
  - 不是：`cur + 2 - 1 => cur++`
- 最后，遍历结束，看`cur`是否为0，如果不是说明结构和字符串不相符合，不是前序遍历的二叉树，否则就说明字符串正确反映二叉树结构，返回true。
### 代码
```java
class Solution {
    public boolean isValidSerialization(String preorder) {
        String[] strs = preorder.split(",");
        if (strs.length == 0) {
            return false;
        }

        int cur = 1;
        for (String str : strs) {
            if (cur <= 0) {
                return false;
            }
            
            if (Objects.equals(str, "#")) {
                cur--;
            } else {
                cur++;
            }
        }

        return cur == 0;
    }
}
```
# [LeetCode_2642_设计可以求最短路径的图类](https://leetcode.cn/problems/design-graph-with-shortest-path-calculator)
## 解法
### 思路

### 代码
```java

```