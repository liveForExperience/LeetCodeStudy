# [LeetCode_335_路径交叉](https://leetcode-cn.com/problems/self-crossing/)
## 题目
给定一个含有 n 个正数的数组 x。从点 (0,0) 开始，先向北移动 x[0] 米，然后向西移动 x[1] 米，向南移动 x[2] 米，向东移动 x[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。

编写一个 O(1) 空间复杂度的一趟扫描算法，判断你所经过的路径是否相交。

示例 1:
```
┌───┐
│   │
└───┼──>
    │

输入: [2,1,1,2]
输出: true 
```
示例 2:
```
┌──────┐
│      │
│
│
└────────────>

输入: [1,2,3,4]
输出: false 
```
示例 3:
```
┌───┐
│   │
└───┼>

输入: [1,1,1,1]
输出: true 
```
## 解法
### 思路
会相交的情况：
- 假设最后一条边是i，边的长度是`[i]`
- 移动次数大于3
- 移动4次相交的条件：
    - `[i - 2] <= [i]`
    - `[i - 1] <= [i - 3]`
- 移动5次相交的条件：
    - 如果最后4次的状态和移动四次的状态相同，那么就会相交
    - 如果`[i - 1] == [i - 3]`，且`[i - 2] <= [i - 4]`
- 移动6次及以上相交的条件：
    - 最后4次的状态和移动4次相交的状态相同
    - `[i - 2]`比`[i - 4]`长，且`[i - 1]` + `[i - 5]`比`[i - 3]`长，导致如果相交，就是和`i - 5`相交，这种情况的条件就是：
        - `[i - 1]` + `[i - 5]` >= `[i - 3]`
        - `[i - 2]` + `[i]` >= `[i - 4]`
        - `[i - 4]` > `[i - 2]`
        - `[i - 3]` > `[i - 1]`
#### 代码
```java
class Solution {
    public boolean isSelfCrossing(int[] x) {
        if (x.length <= 3) {
            return false;
        }

        for (int i = 3; i < x.length; i++) {
            if (x[i] >= x[i - 2] && x[i - 1] <= x[i - 3]) {
                return true;
            }

            if (i >= 4 && x[i - 1] == x[i - 3] && x[i] + x[i - 4] >= x[i - 2]) {
                return true;
            }

            if (i >= 5 && x[i] + x[i - 4] >= x[i - 2] && x[i - 5] + x[i - 1] >= x[i - 3] && x[i - 4] < x[i - 2] && x[i] < x[3]) {
                return true;
            }
        }

        return false;
    }
}
```
# [LeetCode_LCP08_剧情触发时间](https://leetcode-cn.com/problems/ju-qing-hong-fa-shi-jian/)
## 题目
在战略游戏中，玩家往往需要发展自己的势力来触发各种新的剧情。一个势力的主要属性有三种，分别是文明等级（C），资源储备（R）以及人口数量（H）。在游戏开始时（第 0 天），三种属性的值均为 0。

随着游戏进程的进行，每一天玩家的三种属性都会对应增加，我们用一个二维数组 increase 来表示每天的增加情况。这个二维数组的每个元素是一个长度为 3 的一维数组，例如 [[1,2,1],[3,4,2]] 表示第一天三种属性分别增加 1,2,1 而第二天分别增加 3,4,2。

所有剧情的触发条件也用一个二维数组 requirements 表示。这个二维数组的每个元素是一个长度为 3 的一维数组，对于某个剧情的触发条件 c[i], r[i], h[i]，如果当前 C >= c[i] 且 R >= r[i] 且 H >= h[i] ，则剧情会被触发。

根据所给信息，请计算每个剧情的触发时间，并以一个数组返回。如果某个剧情不会被触发，则该剧情对应的触发时间为 -1 。

示例 1：
```
输入： increase = [[2,8,4],[2,5,0],[10,9,8]] requirements = [[2,11,3],[15,10,7],[9,17,12],[8,1,14]]

输出: [2,-1,3,-1]

解释：

初始时，C = 0，R = 0，H = 0

第 1 天，C = 2，R = 8，H = 4

第 2 天，C = 4，R = 13，H = 4，此时触发剧情 0

第 3 天，C = 14，R = 22，H = 12，此时触发剧情 2

剧情 1 和 3 无法触发。
```
示例 2：
```
输入： increase = [[0,4,5],[4,8,8],[8,6,1],[10,10,0]] requirements = [[12,11,16],[20,2,6],[9,2,6],[10,18,3],[8,14,9]]

输出: [-1,4,3,3,3]
```
示例 3：
```
输入： increase = [[1,1,1]] requirements = [[0,0,0]]

输出: [0]
```
限制：
```
1 <= increase.length <= 10000
1 <= requirements.length <= 100000
0 <= increase[i] <= 10
0 <= requirements[i] <= 100000
```
## 解法
### 思路
- 复制requirements数组copy，在坐标3位置上增加一个当前元素的坐标
- 排序copy，按第一个元素升序排列
- 初始化一个队列queue和一个栈stack
- 先判断为0的情况下，有没有特殊情况的requirement
- 然后开始遍历increase，用sum数组累加，并判断当前queue中头部的部分元素是否符合requirement的要求，判断的范围就是requirement的第一个元素是否小于等于sum的第一个元素
- 如果sum的所有元素符合requirements的要求，就出队，并根据坐标3记录在ans数组中，记录的值是当前increase的坐标 + 1
- 如果不符合，就压入stack中，等当前sum的可判断范围遍历结束后，将栈中的元素弹出并压回队列头部
- increase遍历结束后，返回ans
### 代码
```java
class Solution {
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int[] ans = new int[requirements.length];
        Arrays.fill(ans, -1);

        int[][] requirementsCopy = new int[requirements.length][4];
        for (int i = 0; i < requirements.length; i++) {
            requirementsCopy[i][0] = requirements[i][0];
            requirementsCopy[i][1] = requirements[i][1];
            requirementsCopy[i][2] = requirements[i][2];
            requirementsCopy[i][3] = i;
        }

        Arrays.sort(requirementsCopy, Comparator.comparingInt(x -> x[0]));

        int[] sum = new int[]{0, 0, 0};
        LinkedList<int[]> queue = new LinkedList<>();
        Stack<int[]> stack = new Stack<>();
        for (int[] arr : requirementsCopy) {
            queue.offer(arr);
        }

        while (!queue.isEmpty() && queue.getFirst()[0] <= sum[0]) {
            int[] arr = queue.pollFirst();
            if (arr[0] <= sum[0] && arr[1] <= sum[1] && arr[2] <= sum[2]) {
                ans[arr[3]] = 0;
            } else {
                stack.push(arr);
            }
        }

        while (!stack.isEmpty()) {
            queue.offerFirst(stack.pop());
        }

        for (int i = 0; i < increase.length; i++) {
            sum[0] += increase[i][0];
            sum[1] += increase[i][1];
            sum[2] += increase[i][2];

            while (!queue.isEmpty() && queue.getFirst()[0] <= sum[0]) {
                int[] arr = queue.pollFirst();
                if (arr[0] <= sum[0] && arr[1] <= sum[1] && arr[2] <= sum[2]) {
                    ans[arr[3]] = i + 1;
                } else {
                    stack.push(arr);
                }
            }

            while (!stack.isEmpty()) {
                queue.offerFirst(stack.pop());
            }
        }

        return ans;
    }
}
```
