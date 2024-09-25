# LeetCode_991_坏了的计算器
## 题目
在显示着数字的坏计算器上，我们可以执行以下两种操作：
```
双倍（Double）：将显示屏上的数字乘 2；
递减（Decrement）：将显示屏上的数字减 1 。
最初，计算器显示数字 X。
```
返回显示数字 Y 所需的最小操作数。

示例 1：
```
输入：X = 2, Y = 3
输出：2
解释：先进行双倍运算，然后再进行递减运算 {2 -> 4 -> 3}.
```
示例 2：
```
输入：X = 5, Y = 8
输出：2
解释：先递减，再双倍 {5 -> 4 -> 8}.
```
示例 3：
```
输入：X = 3, Y = 10
输出：3
解释：先双倍，然后递减，再双倍 {3 -> 6 -> 5 -> 10}.
```
示例 4：
```
输入：X = 1024, Y = 1
输出：1023
解释：执行递减运算 1023 次
```
提示：
```
1 <= X <= 10^9
1 <= Y <= 10^9
```
## 解法
### 思路
贪心算法：
- `Y > X`时，循环执行如下操作，并记录执行次数`count`：
    - 偶数：除2
    - 奇数：加1
- `Y <= X`时：`count + Y - X`，如果处理过后，Y少于X了，那么就计算相差的值，通过相加来补充
### 代码
```java
class Solution {
    public int brokenCalc(int X, int Y) {
        int count = 0;
        while (X < Y) {
            count++;
            if (Y % 2 == 1) {
                Y++;
            } else {
                Y /= 2;
            }
        }
        
        return count + X - Y;
    }
}
```
# LeetCode_153_寻找旋转排序数组中的最小值
## 题目
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

你可以假设数组中不存在重复元素。

示例 1:
```
输入: [3,4,5,1,2]
输出: 1
```
示例 2:
```
输入: [4,5,6,7,0,1,2]
输出: 0
```
## 解法
### 思路
- 暂存数组第一个值作为最小值
- 从第二个元素开始遍历数组
- 与上次循环暂存的值进行比较，因为是升序，如果小于上一个值，就直接返回当前值
- 暂存当前值，作为下一个循环的前值
- 如果没有，就返回第一个值
### 代码
```java
class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int first = nums[0], pre = first;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < pre) {
                return nums[i];
            }
            
            pre = nums[i];
        }
        
        return first;
    }
}
```
## 解法二
### 思路
二分搜索：
- 参数：
    - 头指针：`nums[0]`
    - 尾指针：`nums[n - 1]`
- 循环之前：
    - 如果数组长度为1，返回头指针
    - 尾指针大于头指针，说明是数组是升序，直接返回头指针元素
- 循环，条件为头指针小于等于尾指针：
    - 计算中间指针
    - 如果中间元素比后一个元素大，返回后一个元素
    - 如果中间元素比前一个元素小，返回中间元素
    - 如果中间元素大于头指针，头指针为中间下标 + 1
    - 如果中间元素小于头指针，尾指针为中间下标 - 1
### 代码
```java
class Solution {
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int head = 0, tail = nums.length - 1;

        if (nums[tail] > nums[head]) {
            return nums[head];
        }

        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            
            if (nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            
            if (nums[mid] < nums[head]) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }
        
        return -1;
    }
}
```
# LeetCode_740_删除与获得点数
## 题目
给定一个整数数组 nums ，你可以对它进行一些操作。

每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。

开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。

示例 1:
```
输入: nums = [3, 4, 2]
输出: 6
解释: 
删除 4 来获得 4 个点数，因此 3 也被删除。
之后，删除 2 来获得 2 个点数。总共获得 6 个点数。
```
示例 2:
```
输入: nums = [2, 2, 3, 3, 3, 4]
输出: 9
解释: 
删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。
之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
总共获得 9 个点数。
```
注意:
```
nums的长度最大为20000。
每个整数nums[i]的大小都在[1, 10000]范围内。
```
## 解法
### 思路
动态规划：
- 前提需要先进行计数排序，将元素个数统计出来放入数组bucket中，数组下标代表元素值
- `dp[i][j]`：i元素第j种情况下的最大点数，j只可能是0或1
- 状态转移方程，在遍历bucket过程中：
    - 如果前值不为0：
        - `dp[i][1] = dp[i - 1][0] + bucket[i] * i`
        - `dp[i][0] = max(dp[i - 1][1], dp[i - 1][0]` 
    - 如果前置为0：
        - `dp[i][1] = max(dp[i - 1][1], dp[i - 1][0]) + bucket[i] * i`
        - `dp[i][0] = max(dp[i - 1][0], dp[i - 1][1])`
- base case：`dp[0][0] = 0`, `dp[0][1] = bucket[0]`
- 返回结果：`max(dp[10000][1], dp[10000][0])`
### 代码
```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        int[] bucket = new int[10001];
        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]]++;
        }

        int[][] dp = new int[10001][2];
        for (int i = 1; i < dp.length; i++) {
            int max = Math.max(dp[i - 1][1], dp[i - 1][0]);
            if (bucket[i - 1] > 0) {
                dp[i][1] = dp[i - 1][0] + bucket[i] * i;
            } else {
                dp[i][1] = max + bucket[i] * i;
            }
            dp[i][0] = max;
        }

        return Math.max(dp[10000][0], dp[10000][1]);
    }
}
```
## 解法二
### 思路
基于解法一，为0的元素不需要进行计算，且也不需要使用二维数组，因为每一个元素的不同状态的值都只取决于之前的元素不同状态的的值，所以只需要使用两个临时变量记录取值和不取值两种状态就可以了。
### 代码
```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        int[] bucket = new int[10001];
        for (int num : nums) {
            bucket[num]++;
        }
        
        int take = 0, avoid = 0, pre = -1;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > 0) {
                int max = Math.max(take, avoid);
                if (i - 1 == pre) {
                    take = avoid + bucket[i] * i;
                } else {
                    take = max + bucket[i] * i;
                }
                avoid = max;
                
                pre = i;
            }
        }
        
        return Math.max(take, avoid);
    }
}
```
# LeetCode_134_加油站
## 题目
在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。

你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。

如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

说明: 
```
如果题目有解，该答案即为唯一答案。
输入数组均为非空数组，且长度相同。
输入数组中的元素均为非负数。
```
示例 1:
```
输入: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

输出: 3

解释:
从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
因此，3 可为起始索引。
```
示例 2:
```
输入: 
gas  = [2,3,4]
cost = [3,4,3]

输出: -1

解释:
你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
因此，无论怎样，你都不可能绕环路行驶一周。
```
## 解法
### 思路
嵌套循环：
- 外层遍历所有可能的起始加油站：
    - 初始参数：
        - `len`：加油站数量
        - `tank`：暂存当前汽车的油量
        - `flag`：标志汽车是否已没油
        - `start`：标志是否是第一次经过起始加油站
- 内层开始模拟汽车运行的状态：
    - 每次先减去当前下标对应的`cost`
    - 判断是否已小于0，如果是就将标志反转并中断循环
    - 增加下一个汽油站的汽油数`gas`，使用`(j + 1) % len`来取后一个加油站的下标
    - 如果循环之后再次内层下标和外层下标相同，返回外层下标作为答案
### 代码
```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        for (int i = 0; i < len; i++) {
            int j = i, tank = gas[i];
            boolean flag = true, start = true;
            while (i != j || start) {
                if (start) {
                    start = false;
                }
                
                tank = tank - cost[j];
                if (tank < 0) {
                    flag = false;
                    break;
                }

                tank += gas[j = (j + 1) % len];
            }

            if (flag) {
                return i;
            }
        }

        return -1;
    }
}
```
## 解法二
### 思路
加油量和消耗量的关系：
- 如果加油站总量小于消耗量总量，则不可能走完全程
- 如果到达当前加油站的消耗大于当前加油站量和本身的油量，则不可能走完全程，而一旦发现这种情况，也意味着从起始到当前的所有加油站作为起始点，也都是不可能走完全程的
### 代码
```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length, total = 0, cur = 0, start = 0;
        for (int i = 0; i < len; i++) {
            total += gas[i] - cost[i];
            cur += gas[i] - cost[i];

            if (cur < 0) {
                start = i + 1;
                cur = 0;
            }
        }

        return total >= 0 ? start : -1;
    }
}
```
## 解法三
### 思路
将两个数组的同一下标位置的值进行计算，并连成一条折线，那么这个以油量差为y轴，下标为x轴的函数图形，y轴值最小的点，就是整个环绕一周的过程中的最低点，也就是转折点。如果汽油站总量不小于消耗总量，那么那个转折点就是可以环绕一周的点。
### 代码
```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length, total = 0, lowest = Integer.MAX_VALUE, index = 0;
        for (int i = 0; i < len; i++) {
            total += gas[i] - cost[i];

            if (total < lowest) {
                lowest = total;
                index = i;
            }
        }

        return total < 0 ? -1 : (index + 1) % len;
    }
}
```
# LeetCode_1094_拼车
## 题目
假设你是一位顺风车司机，车上最初有 capacity 个空座位可以用来载客。由于道路的限制，车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向，你可以将其想象为一个向量）。

这儿有一份行程计划表 trips[][]，其中 trips[i] = [num_passengers, start_location, end_location] 包含了你的第 i 次行程信息：
```
必须接送的乘客数量；
乘客的上车地点；
以及乘客的下车地点。
这些给出的地点位置是从你的 初始 出发位置向前行驶到这些地点所需的距离（它们一定在你的行驶方向上）。
```
请你根据给出的行程计划表和车子的座位数，来判断你的车是否可以顺利完成接送所用乘客的任务（当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false）。

示例 1：
```
输入：trips = [[2,1,5],[3,3,7]], capacity = 4
输出：false
```
示例 2：
```
输入：trips = [[2,1,5],[3,3,7]], capacity = 5
输出：true
```
示例 3：
```
输入：trips = [[2,1,5],[3,5,7]], capacity = 3
输出：true
```
示例 4：
```
输入：trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
输出：true
```
提示：
```
你可以假设乘客会自觉遵守 “先下后上” 的良好素质
trips.length <= 1000
trips[i].length == 3
1 <= trips[i][0] <= 100
0 <= trips[i][1] < trips[i][2] <= 1000
1 <= capacity <= 100000
```
## 解法
### 思路
- 遍历数组`trips`：
    - 求得路程的最大值`max`
- 根据最大值初始化数组`nums`
- 遍历数组`trips`：
    - 根据每一个元素的起始和结束位置在`nums`的位置上加上乘客数，终点不计数
- 在遍历`nums`，如果有任意元素的值大于`capacity`，返回false，否则就返回true
### 代码
```java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int max = 0;
        for (int[] trip : trips) {
            max = Math.max(max, trip[2]);
        }
        int[] nums = new int[max + 1];
        
        for (int[] trip : trips) {
            for (int i = trip[1]; i < trip[2]; i++) {
                nums[i] += trip[0];
            }
        }
        
        for (int num : nums) {
            if (num > capacity) {
                return false;
            }
        }
        
        return true;
    }
}
```
# LeetCode_1006_笨阶乘
## 题目
通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。

相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。

例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。

另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。

实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。

示例 1：
```
输入：4
输出：7
解释：7 = 4 * 3 / 2 + 1
```
示例 2：
```
输入：10
输出：12
解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
```
提示：
```
1 <= N <= 10000
-2^31 <= answer <= 2^31 - 1  （答案保证符合 32 位整数。）
```
## 解法
### 思路
使用栈：
- 使用一个队列，两个栈：
    - `num`：栈，用来存进行乘除法运算后的结果和其余数字
    - `num2`：栈，用来存第二次单独运算加减法的数字
    - `operator`：队列，用来存运算符号
- 生成一个list`operators`用来存放`*`、`/`、`+`、`-`
- 根据输入，循环生成递减的数字
    - 如果是`*`、`/`就直接将栈中的元素弹出，通过运算符号进行计算
    - 如果是`+`、`-`就将数字和符号同时压入`num`和`operator`中
- 循环`num`，将数字弹出并压入`num2`中，使第二次加减法的顺序正确
- 循环`num2`直到剩下一个元素，循环中进行相应的加减法运算
- 返回`num2`中的剩余元素
### 代码
```java
class Solution {
    public int clumsy(int N) {
        Stack<Integer> num = new Stack<>();
        Queue<Character> operator = new ArrayDeque<>();

        char[] operators = new char[]{'*', '/', '+', '-'};
        for (int i = 0; i < N - 1; i++) {
            operator.offer(operators[i % 4]);
        }

        for (int i = N; i >= 1; i--) {
            if (i == N) {
                num.push(i);
            } else {
                Character op = operator.poll();
                if (op == null) {
                    return 0;
                }

                if (op == '*') {
                    num.push(num.pop() * i);
                    continue;
                }

                if (op == '/') {
                    num.push(num.pop() / i);
                    continue;
                }

                if (op == '+' || op == '-') {
                    operator.offer(op);
                    num.push(i);
                }
            }
        }

        Stack<Integer> num2 = new Stack<>();
        while (!num.isEmpty()) {
            num2.push(num.pop());
        }

        while (num2.size() > 1) {
            int first = num2.pop();
            int second = num2.pop();
            Character op = operator.poll();
            if (op == null) {
                return 0;
            }

            if ('+' == op) {
                num2.push(first + second);
            }

            if ('-' == op) {
                num2.push(first - second);
            }
        }

        return num2.pop();
    }
}
```
## 解法二
### 思路
数学：
- 乘除部分的结果是
```math
(k + 2) * (k + 1) / k = k + 3 - 2 / k
```
- 因为除法结果使截取整数部分，所以上式在`k > 3`的情况下，`2 / k = 0`，所以可以直接约去
- 而之后的加减部分就可容易推得：
```math
(k + 3) - (k + 2) * (k + 1) / k = (k + 3) - (k + 3) = 0
```
- 所以整个公式就只需要考虑头上的乘除部分，以及最后的，当k > 5的时候就考虑`(N - 1) % 4`的值：
    - 0：`N + 1` + `2 - 1` = `N + 2` = ` N + 2`
    - 1：`N + 1` + `3 - 2 * 1`  = `N + 2`
    - 2：`N + 1` + `4 - 3 * 2 / 1` = `N - 1`
    - 3：`N + 1` + `5 - 4 * 3 / 2 + 1` = `N + 1`
### 代码
```java
class Solution {
    public int clumsy(int N) {
        if (N <= 4) {
            return new int[]{1,2,6,7}[N - 1];
        } else {
            return N + new int[]{2, 2, -1, 1}[(N - 1) % 4];
        }
    }
}
```