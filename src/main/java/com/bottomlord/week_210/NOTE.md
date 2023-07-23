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
# [LeetCode_874_模拟行走机器人](https://leetcode.cn/problems/walking-robot-simulation/)
## 解法
### 思路
- 使用hash表来存储obstacles数组中的障碍坐标
  - key：障碍的横坐标
  - value：障碍的横坐标对应的纵坐标的集合，HashSet
- 这题的难点是如何定义方向所对应的横纵坐标的移动值，根据题目中的要求，定义成如下的二维数组`{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}`
- 最后遍历commands数组来判断是左转还是右转或者向前，通过模拟的方式，在每次到达新的坐标时候，通过计算距离的平方和来更新最大值
- 遍历结束后返回结果即可
### 代码
```java
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] obstacle : obstacles) {
            map.computeIfAbsent(obstacle[0], x -> new HashSet<>()).add(obstacle[1]);
        }

        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0, x = 0, y = 0, max = 0;
        for (int command : commands) {
            if (command == -2) {
                dir = (dir + 3) % 4;
            } else if (command == -1) {
                dir = (dir + 1) % 4;
            } else {
                for (int i = 0; i < command; i++) {
                    int nx = x + dirs[dir][0], ny = y + dirs[dir][1];

                    if (map.containsKey(nx) && map.get(nx).contains(ny)) {
                        break;
                    }

                    x = nx;
                    y = ny;
                }

                max = Math.max(max, x * x + y * y);
            }
        }

        return max;
    }
}
```
# [LeetCode_1499_满足不等式的最大值](https://leetcode.cn/problems/max-value-of-equation/)
## 解法
### 思路
- 题目其实是求二元组之间第一个元素差值不大于k的情况下，公式`yi + yj + |xi - xj|`算出来的最大值，其中x为二元组的第一个元素，y为第二个元素，另`0 <= i < j <= n - 1`，且`xi < xj`
- 那么因为二元组数组是以x为升序的序列，且题目限制了最大值的取值范围是不大于`|xi - xj| <= k`，所以可以通过一个单调数组维护取值范围区间
- 区间确定以后，最大值的计算，通过变换公式能够看的更直观
  - `yi + yj + |xi - xj|` = `yi + yj + xj - xi` = `(yj + xj) + (yi - xi)`
  - 通过如上的变化，因为i < j，所以当我遍历二元组数组时，`yi - xi`这一部分的值一定是之前遍历过的元素，`yj + xj`可以理解成当前遍历到的元素
- 那么在通过队列维护出符合k范围的区间后，在区间内，之前遍历过的`yi - xi`越大，就越可能是答案
- 在处理单调队列中的元素的时候，基于当前遍历到的xj元素，与队首元素(基于队列先进先出的特性，以及数组x升序的单调性，队首一定是最小的那个元素)进行比较，将大于k的元素出队
- 然后这个队列中就全都是符合要求的区间元素了，此时，当前元素作为即将在下一个循环成为新的`(yi - xi)`比较对象的二元组，可以先与区间中之前留下的元素进行比较，将值小于当前二元组计算后的值的元素出队
- 因为出队的元素，对于之后的元素而言，差值又比当前元素二元组差值小，距离又更远（更可能不符合区间要求），所以没有任何影响
- 那么在如上处理之后，队列中的队首元素就一定是当前区间中差值最大的元素，所以在进行完区间整理后，可以把遍历到的当前二元组的和`xj + yj`与队首元素的`yi - xi`进行相加，再与暂存的最大值进行比较，取较大值，然后再通过当前二元组的差，将队列中小于当前二元组差值的老元素从队尾出队，这样就继续能够保证队首是差值最大的元素了
- 于是为了方便如上的处理，队列中的元素可以存储为长度为2的数组
  - 第一个元素为`x`
  - 第二个元素为`y - x`
- 遍历二元组数组时的处理逻辑就是：
  - 维护队列区间，使其所有元素符合`xj - xi <= k`，将不符合的元素出队
  - 将当前队首元素的`yi - xi`加上当前元素的`yj + xj`，并与暂存的max值比较，取较大值
  - 将队尾数组`yi - xi`不大于当前数组`yj - xj`的元素出队
- 遍历结束，返回暂存的max即可，这个max可以初始化为int最小值
- 因为题目保证一定会有符合`|xi - xj| <= k`的元素对，所以不用担心特殊情况
- 同时，因为要对队尾元素做出队操作，所以队列可以选用双端队列
### 代码
```java
class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<int[]> deque  = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        for (int[] point : points) {
            int x = point[0], y = point[1];
            while (!deque.isEmpty() && deque.peekFirst()[0] < x - k) {
                deque.pollFirst();
            }
            
            if (!deque.isEmpty()) {
                max = Math.max(max, x + y + deque.peekFirst()[1]);
            }
            
            while (!deque.isEmpty() && y - x >= deque.peekLast()[1]) {
                deque.pollLast();
            }
            deque.offerLast(new int[]{x, y - x});
        }
        return max;
    }
}
```
# [LeetCode_42_接雨水](https://leetcode.cn/problems/trapping-rain-water/)
## 解法
### 思路
- 题目是典型的单调栈例题，通过维护一个单调递减的栈来获得题目要求的接水量的值
- 通过观察题目可以发现
  - 至少需要3个相邻坐标才能形成一个可能承接水的洼地
  - 并且这3个坐标对应的元素值a、b、c之间的关系一定是：`a > b && c > b`
- 那么可以推论出，如果在遍历坐标的时候，发现遍历到的所有元素都是单调递减的，或者换个说法，也即c总是小于b，那么就不符合如上可以承接水的组合要求，所以就可以不需要处理这样的组合，需要继续遍历
- 什么时候停止遍历呢？当出现`c > b`的时候，也就是可能符合承接水的组合形态的时候
- 这个时候要做的事情就是找所有符合情况的a。什么是符合情况？
  - 首先需要和b、c组成`a > b && b < c`的组合状态
  - 其次因为这种组合状态因为栈中保留的历史坐标元素高度，可能有很多个，所以要依次去判断是否符合如上说到的组合状态，并在计算完体积后，将这个元素弹出栈，因为这个坐标已经不会再参与到之后的体积计算中了，至于原因，会在后面计算中描述
  - 还有就是，还需要满足`a <= c`，这个要求的原因是：当`a > c`时，会做积水的体积计算，也即上面说的，这个b的坐标已经没有意义了，同时c和a高度差所产生的积水体积也没有了意义（因为计算过了），于是就可以将`c`的高度简化为`c - c`，也即0，而a的高度简化为`a - c`，那么这种组合就是无法积水的。又因为单调栈的单调递减性，所以当第一次遇到`a > c`的情况，循环找a并计算积水的动作就可以停止了，因为栈中其他的元素肯定也大于c
- 那么如何计算符合`a > b && b < c`状态下的积水值呢？
  - 首先要确定计算的体积是哪一部分，因为有b的存在，且a和c之间是可能有高低差的，所以其实真正能积水的部分应该是
    - 长：a和c之间的距离
    - 高：`min(a, c) - b`的高度
  - 确定好该选择哪部分体积计算后，直接通过长 * 高 * 1（这个宽是默认的）得到体积即可，然后累加到累加值中
- 于是处理逻辑就是：
  - 初始化一个栈，为了简化逻辑处理，预先压入坐标0
  - 初始化一个累加值变量ans
  - 从坐标1开始遍历数组
  - 比较当前遍历到的坐标元素对应的高度与栈顶元素的高低
    - 如果大于栈顶元素，也即符合`c > b`的状态，那么将这个b坐标弹出，并记录这个坐标的对应高度
    - 然后判断栈中是否还有元素，如果没有了，则将当前坐标压入栈后继续遍历，因为这说明当前组合只有2个元素，无法接水
    - 然后计算当前栈顶元素a，和弹出栈的那个元素b以及当前元素c对应的高度所能计算出的体积，计算公式就是如上所推导的：`(min(height[a], height[c]) - height[b]) * (c - a - 1)`，然后将值累加到ans中
  - 最后将当前坐标压入栈中，继续遍历数组
  - 循环结束后，返回累加值ans作为结果即可
  - 无论栈中是否还有元素，这时候剩下的元素是无法再得到更多可能的积水了的，所以无需处理
### 代码
```java
class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int ans = 0;

        for (int i = 1; i < height.length; i++) {
            while (height[i] > height[stack.peek()]) {
                int b = stack.pop();

                if (stack.isEmpty()) {
                    break;
                }
                
                int a = stack.peek();
                ans += (Math.min(height[a], height[i]) - height[b]) * (i - a - 1);
            }

            stack.push(i);
        }
        
        return ans;
    }
}
```