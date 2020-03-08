# Contest_1_生成每种字符都是奇数个的字符串
## 题目
给你一个整数 n，请你返回一个含 n 个字符的字符串，其中每种字符在该字符串中都恰好出现 奇数次 。

返回的字符串必须只含小写英文字母。如果存在多个满足题目要求的字符串，则返回其中任意一个即可。

示例 1：
```
输入：n = 4
输出："pppz"
解释："pppz" 是一个满足题目要求的字符串，因为 'p' 出现 3 次，且 'z' 出现 1 次。当然，还有很多其他字符串也满足题目要求，比如："ohhh" 和 "love"。
```
示例 2：
```
输入：n = 2
输出："xy"
解释："xy" 是一个满足题目要求的字符串，因为 'x' 和 'y' 各出现 1 次。当然，还有很多其他字符串也满足题目要求，比如："ag" 和 "ur"。
```
示例 3：
```
输入：n = 7
输出："holasss"
```
提示：
```
1 <= n <= 500
```
## 解法
### 思路
判断n的奇偶
- 奇数就生成`n`个`a`
- 偶数就生成`n - 1`个`a`和1个`b`
### 代码
```java
class Solution {
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if ((n & 1) == 0) {
            for (int i = 0; i < n - 1; i++) {
                sb.append('a');
            }
            sb.append('b');
        } else {
            for (int i = 0; i < n; i++) {
                sb.append('a');
            }
        }
        
        return sb.toString();
    }
}
```
# Contest_2_灯泡开关III
## 题目
房间中有 n 枚灯泡，编号从 1 到 n，自左向右排成一排。最初，所有的灯都是关着的。

在 k  时刻（ k 的取值范围是 0 到 n - 1），我们打开 light[k] 这个灯。

灯的颜色要想 变成蓝色 就必须同时满足下面两个条件：
```
灯处于打开状态。
排在它之前（左侧）的所有灯也都处于打开状态。
请返回能够让 所有开着的 灯都 变成蓝色 的时刻 数目 。
```
示例 1：
```
输入：light = [2,1,3,5,4]
输出：3
解释：所有开着的灯都变蓝的时刻分别是 1，2 和 4 。
```
示例 2：
```
输入：light = [3,2,4,1,5]
输出：2
解释：所有开着的灯都变蓝的时刻分别是 3 和 4（index-0）。
```
示例 3：
```
输入：light = [4,1,2,3]
输出：1
解释：所有开着的灯都变蓝的时刻是 3（index-0）。
第 4 个灯在时刻 3 变蓝。
```
示例 4：
```
输入：light = [2,1,4,3,6,5]
输出：3
```
示例 5：
```
输入：light = [1,2,3,4,5,6]
输出：6
```
提示：
```
n == light.length
1 <= n <= 5 * 10^4
light 是 [1, 2, ..., n] 的一个排列。
```
## 解法
### 思路
遍历数组：
- 统计当前遍历到的最大值
- 统计遍历到的元素个数
- 如果最大值和元素个数相等就计一次数
### 代码
```java
class Solution {
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if ((n & 1) == 0) {
            for (int i = 0; i < n - 1; i++) {
                sb.append('a');
            }
            sb.append('b');
        } else {
            for (int i = 0; i < n; i++) {
                sb.append('a');
            }
        }
        
        return sb.toString();
    }
}
```
# Contest_3_通知所有员工所需的时间
## 题目
公司里有 n 名员工，每个员工的 ID 都是独一无二的，编号从 0 到 n - 1。公司的总负责人通过 headID 进行标识。

在 manager 数组中，每个员工都有一个直属负责人，其中 manager[i] 是第 i 名员工的直属负责人。对于总负责人，manager[headID] = -1。题目保证从属关系可以用树结构显示。

公司总负责人想要向公司所有员工通告一条紧急消息。他将会首先通知他的直属下属们，然后由这些下属通知他们的下属，直到所有的员工都得知这条紧急消息。

第 i 名员工需要 informTime[i] 分钟来通知它的所有直属下属（也就是说在 informTime[i] 分钟后，他的所有直属下属都可以开始传播这一消息）。

返回通知所有员工这一紧急消息所需要的 分钟数 。

示例 1：
```
输入：n = 1, headID = 0, manager = [-1], informTime = [0]
输出：0
解释：公司总负责人是该公司的唯一一名员工。
```
示例 2：
```
输入：n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
输出：1
解释：id = 2 的员工是公司的总负责人，也是其他所有员工的直属负责人，他需要 1 分钟来通知所有员工。
上图显示了公司员工的树结构。
```
示例 3：
```
输入：n = 7, headID = 6, manager = [1,2,3,4,5,6,-1], informTime = [0,6,5,4,3,2,1]
输出：21
解释：总负责人 id = 6。他将在 1 分钟内通知 id = 5 的员工。
id = 5 的员工将在 2 分钟内通知 id = 4 的员工。
id = 4 的员工将在 3 分钟内通知 id = 3 的员工。
id = 3 的员工将在 4 分钟内通知 id = 2 的员工。
id = 2 的员工将在 5 分钟内通知 id = 1 的员工。
id = 1 的员工将在 6 分钟内通知 id = 0 的员工。
所需时间 = 1 + 2 + 3 + 4 + 5 + 6 = 21 。
```
示例 4：
```
输入：n = 15, headID = 0, manager = [-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6], informTime = [1,1,1,1,1,1,1,0,0,0,0,0,0,0,0]
输出：3
解释：第一分钟总负责人通知员工 1 和 2 。
第二分钟他们将会通知员工 3, 4, 5 和 6 。
第三分钟他们将会通知剩下的员工。
```
示例 5：
```
输入：n = 4, headID = 2, manager = [3,3,-1,2], informTime = [0,0,162,914]
输出：1076
```
提示：
```
1 <= n <= 10^5
0 <= headID < n
manager.length == n
0 <= manager[i] < n
manager[headID] == -1
informTime.length == n
0 <= informTime[i] <= 1000
如果员工 i 没有下属，informTime[i] == 0 。
题目 保证 所有员工都可以收到通知。
```
## 解法
### 思路
- 生成上级坐标与员工坐标的映射关系
- 递归：
    - 查询上级所需的传递时间
    - 遍历所有相同上级的坐标，将当前坐标作为下一层的上级坐标继续递归
    - 返回需要传递的时间
    - 将遍历返回的所有传递时间进行比较，获得最大值
    - 最大值和上级时间相加， 作为当前层结果返回
### 代码
```java
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            List<Integer> list = map.getOrDefault(manager[i], new ArrayList<>());
            list.add(i);
            map.put(manager[i], list);
        }
        return bfs(headID, map, informTime);
    }

    private int bfs(int headId, Map<Integer, List<Integer>> map, int[] inforTime) {
        int time = 0, headTime = inforTime[headId];
        
        List<Integer> list = map.getOrDefault(headId, new ArrayList<>());
        for (int index : list) {
            time = Math.max(bfs(index, map, inforTime), time);
        }

        return headTime + time;
    }
}
```
# Contest_4_T秒后青蛙的位置
## 题目
给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：
```
在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。
青蛙无法跳回已经访问过的顶点。
如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
无向树的边用数组 edges 描述，其中 edges[i] = [fromi, toi] 意味着存在一条直接连通 fromi 和 toi 两个顶点的边。
```
返回青蛙在 t 秒后位于目标顶点 target 上的概率。

示例 1：
```
输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
输出：0.16666666666666666 
解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，然后第 2 秒 有 1/2 的概率跳到顶点 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。 
```
示例 2：
```
输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
输出：0.3333333333333333
解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7 。 
```
示例 3：
```
输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 20, target = 6
输出：0.16666666666666666
```
提示：
```
1 <= n <= 100
edges.length == n-1
edges[i].length == 2
1 <= edges[i][0], edges[i][1] <= n
1 <= t <= 50
1 <= target <= n
与准确值误差在 10^-5 之内的结果将被判定为正确。
```
## 解法
### 思路

### 代码
```java

```