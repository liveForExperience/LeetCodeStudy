# contest_20190924_1_猜数字
## 题目
小A 和 小B 在玩猜数字。小B 每次从 1, 2, 3 中随机选择一个，小A 每次也从 1, 2, 3 中选择一个猜。他们一共进行三次这个游戏，请返回 小A 猜对了几次？

输入的guess数组为 小A 每次的猜测，answer数组为 小B 每次的选择。guess和answer的长度都等于3。

示例 1：
```
输入：guess = [1,2,3], answer = [1,2,3]
输出：3
解释：小A 每次都猜对了。
```
示例 2：
```
输入：guess = [2,2,3], answer = [3,2,1]
输出：1
解释：小A 只猜对了第二次。
```
限制：
```
guess的长度 = 3
answer的长度 = 3
guess的元素取值为 {1, 2, 3} 之一。
answer的元素取值为 {1, 2, 3} 之一。
```
## 解法
### 思路
遍历数组的下标比较两个数组相同下标的元素是否相同，并计数返回
### 代码
```java
class Solution {
    public int game(int[] guess, int[] answer) {
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            if (guess[i] == answer[i]) {
                ans++;
            }
        }
        return ans;
    }
}
```
# contest_20190924_2_分式化简
## 题目
有一个同学在学习分式。他需要将一个连分数化成最简分数，你能帮助他吗？

连分数是形如上图的分式。在本题中，所有系数都是大于等于0的整数。

输入的cont代表连分数的系数（cont[0]代表上图的a0，以此类推）。返回一个长度为2的数组[n, m]，使得连分数的值等于n / m，且n, m最大公约数为1。

示例 1：
```
输入：cont = [3, 2, 0, 2]
输出：[13, 4]
解释：原连分数等价于3 + (1 / (2 + (1 / (0 + 1 / 2))))。注意[26, 8], [-13, -4]都不是正确答案。
```
示例 2：
```
输入：cont = [0, 0, 3]
输出：[3, 1]
解释：如果答案是整数，令分母为1即可。
限制：

cont[i] >= 0
1 <= cont的长度 <= 10
cont最后一个元素不等于0
答案的n, m的取值都能被32位int整型存下（即不超过2 ^ 31 - 1）。
```
## 解法
### 思路
- 定义两个基本变量：
    - 分子：`numerator`，初始化为1
    - 分母：`denominator`，初始化为数组最后的元素
- 从数组的最后一个元素开始向数组头部开始遍历
- 遍历过程:
    - 每一层的公式是`cont[i - 1] + 1 / cont[i] `可以计算成为`cont[i]cont[i - 1] + 1 / cont[i]`
    - 而上面推导的值又会在下一层以倒数的形式呈现，所以在当前循环就需要将上式计算为`cont[i] / cont[i]cont[i - 1] + 1`
    - 因为`denominator`初始化是等价于`cont[i]`的，`1`等价于`numerator`的初始值，所以可以推得`denominator / denominator * cont[i - 1] + numerator`
    - 根据公式，`numerator`赋值为`denominator`，`denominator` 赋值为 `denominator * cont[i - 1] + numerator`
    - 然后对`numerator`和`denominator`求最大公约数化简
- 遍历结束后因为分子分母在循环体中进行了一次颠倒，用来给下一次循环处理，但最终结果不需要颠倒，所以返回时以倒数的形式返回
### 代码
```java
class Solution {
    public int[] fraction(int[] cont) {
        int len = cont.length, n = 1, d = cont[len - 1];
        for(int i = len - 2; i >= 0; i--) {
            n ^= d;
            d ^= n;
            n ^= d;
            
            d = n * cont[i] + d;
            
            int gcd = gcd(n, d);
            
            n /= gcd;
            d /= gcd;
        }
        
        return new int[]{d, n};
    }
    
    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
```
# contest_20190924_3_机器人大冒险
## 题目
力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
```
U: 向y轴正方向移动一格
R: 向x轴正方向移动一格。
不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
```
给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。

示例 1：
```
输入：command = "URR", obstacles = [], x = 3, y = 2
输出：true
解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
```
示例 2：
```
输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2
输出：false
解释：机器人在到达终点前会碰到(2, 2)的障碍物。
```
示例 3：
```
输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2
输出：true
解释：到达终点后，再碰到障碍物也不影响返回结果。
```
限制：
```
2 <= command的长度 <= 1000
command由U，R构成，且至少有一个U，至少有一个R
0 <= x <= 1e9, 0 <= y <= 1e9
0 <= obstacles的长度 <= 1000
obstacles[i]不为原点或者终点
```
## 解法
### 思路
- 对障碍物的二维数组生成一个`Map<Integer, Set<Integer>>`映射，x坐标为key，y坐标放入set中
- 从原点开始根据字符来判断是x++还是y++
- 整个过程需要不断循环字符数组来进行，所以是while(true)嵌套for循环
- 退出循环的三种条件：
    1. x或y轴超出了终点的坐标，false
    2. 遇到了障碍，false
    3. 遇到终点，true
### 代码
```java
class Solution {
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        char[] commands = command.toCharArray();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] obstacle : obstacles) {
            int ox = obstacle[0];
            int oy = obstacle[1];

            if (map.containsKey(ox)) {
                map.get(ox).add(oy);
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(oy);
                map.put(ox, set);
            }
        }

        int lx = 0, ly = 0;
        while (true) {
            for (char c : commands) {
                if (c == 'U') {
                    ly++;
                } else if (c == 'R') {
                    lx++;
                }

                if (lx == x && ly == y) {
                    return true;
                }
                
                if (lx > x || ly > y) {
                    return false;
                }
                
                if (map.containsKey(lx)) {
                    if (map.get(lx).contains(ly)) {
                        return false;
                    }
                }
            }
        }
    }
}
```
# contest_20190924_4_覆盖
## 题目
你有一块棋盘，棋盘上有一些格子已经坏掉了。你还有无穷块大小为1 * 2的多米诺骨牌，你想把这些骨牌不重叠地覆盖在完好的格子上，请找出你最多能在棋盘上放多少块骨牌？这些骨牌可以横着或者竖着放。

输入：n, m代表棋盘的大小；broken是一个b * 2的二维数组，其中每个元素代表棋盘上每一个坏掉的格子的位置。

输出：一个整数，代表最多能在棋盘上放的骨牌数。

示例 1：
```
输入：n = 2, m = 3, broken = [[1, 0], [1, 1]]
输出：2
解释：我们最多可以放两块骨牌：[[0, 0], [0, 1]]以及[[0, 2], [1, 2]]。（见下图）
```
示例 2：
```
输入：n = 3, m = 3, broken = []
输出：4
解释：下图是其中一种可行的摆放方式
```
限制：
```
1 <= n <= 8
1 <= m <= 8
0 <= b <= n * m
```
## 解法
### 思路
回溯算法：
- 从[0,0]开始进行回溯递归，每一个单元格进行判断时都有三种情况
    - 可以横着放
    - 可以竖着放
    - 横竖都不能放
- 三种情况递归后返回记录的骨牌数，求三者的最大值并返回
- 递归时候的退出条件：
    - 如果行数超出边界，返回计数结果，说明已经遍历结束了
    - 如果列数超出边界，说明改行遍历结束了，需要换行
    - 如果当前单元格已被占用或者是坏的，换到下一列
### 代码
```java
class Solution {
    public int domino(int n, int m, int[][] broken) {
        if (broken.length == 0) {
            return n * m / 2;
        }

        int[][] grid = new int[n][m];
        for (int[] b : broken) {
            grid[b[0]][b[1]] = 2;
        }

        return backTrack(grid, 0, 0, 0);
    }

    private int backTrack(int[][] grid, int r, int c, int count) {
        if (r >= grid.length) {
            return count;
        }

        if (c >= grid[r].length) {
            return backTrack(grid, r + 1, 0, count);
        }

        if (grid[r][c] != 0) {
            return backTrack(grid, r, c + 1, count);
        }

        int count1 = 0;
        if (c + 1 < grid[r].length && grid[r][c + 1] == 0) {
            grid[r][c] = grid[r][c + 1] = 1;
            count1 = backTrack(grid, r, c + 2, count + 1);
            grid[r][c] = grid[r][c + 1] = 0;
        }

        int count2 = 0;
        if (r + 1 < grid.length && grid[r + 1][c] == 0) {
            grid[r][c] = grid[r + 1][c] = 1;
            count2 = backTrack(grid, r, c + 1, count + 1);
            grid[r][c] = grid[r + 1][c] = 0;
        }

        int count3 = 0;
        if (count1 == 0 && count2 == 0) {
            count3 = backTrack(grid, r, c + 1, count);
        }
        
        return Math.max(count3, Math.max(count1, count2));
    }
}
```
# contest_20190924_5_发LeetCoin
## 题目
力扣决定给一个刷题团队发LeetCoin作为奖励。同时，为了监控给大家发了多少LeetCoin，力扣有时候也会进行查询。

该刷题团队的管理模式可以用一棵树表示：
```
团队只有一个负责人，编号为1。除了该负责人外，每个人有且仅有一个领导（负责人没有领导）；
不存在循环管理的情况，如A管理B，B管理C，C管理A。
```
力扣想进行的操作有以下三种：
```
给团队的一个成员（也可以是负责人）发一定数量的LeetCoin；
给团队的一个成员（也可以是负责人），以及他/她管理的所有人（即他/她的下属、他/她下属的下属，……），发一定数量的LeetCoin；
查询某一个成员（也可以是负责人），以及他/她管理的所有人被发到的LeetCoin之和。
```
输入：
```
N表示团队成员的个数（编号为1～N，负责人为1）；
leadership是大小为(N - 1) * 2的二维数组，其中每个元素[a, b]代表b是a的下属；
operations是一个长度为Q的二维数组，代表以时间排序的操作，格式如下：
operations[i][0] = 1: 代表第一种操作，operations[i][1]代表成员的编号，operations[i][2]代表LeetCoin的数量；
operations[i][0] = 2: 代表第二种操作，operations[i][1]代表成员的编号，operations[i][2]代表LeetCoin的数量；
operations[i][0] = 3: 代表第三种操作，operations[i][1]代表成员的编号；
```
输出：
```
返回一个数组，数组里是每次查询的返回值（发LeetCoin的操作不需要任何返回值）。由于发的LeetCoin很多，请把每次查询的结果模1e9+7 (1000000007)。
```
示例 1：
```
输入：N = 6, leadership = [[1, 2], [1, 6], [2, 3], [2, 5], [1, 4]], operations = [[1, 1, 500], [2, 2, 50], [3, 1], [2, 6, 15], [3, 1]]
输出：[650, 665]
解释：团队的管理关系见下图。
第一次查询时，每个成员得到的LeetCoin的数量分别为（按编号顺序）：500, 50, 50, 0, 50, 0;
第二次查询时，每个成员得到的LeetCoin的数量分别为（按编号顺序）：500, 50, 50, 0, 50, 15.
```
限制：
```
1 <= N <= 50000
1 <= Q <= 50000
operations[i][0] != 3 时，1 <= operations[i][2] <= 5000
```
## 解法
### 思路
- 使用map存储生成的node节点
- 遍历leadership生成树
- 遍历operation进行逻辑处理：
    - 操作1：从map中获取节点，并将其父节点进行更新
    - 操作2：从map中获取节点，递归更新其子节点，结束后更新其父节点
    - 操作3：从map中获取节点，取模后放入结果中
- 遍历结果并放入数组中返回
### 代码
```java
class Solution {
    public int[] bonus(int n, int[][] leadership, int[][] operations) {
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i + 1, new Node(i + 1));
        }

        for (int[] arr : leadership) {
            Node node = map.get(arr[1]);
            Node parent = map.get(arr[0]);

            node.parent = parent;
            if (parent.children == null) {
                parent.children = new ArrayList<>();
            }

            parent.children.add(node);
        }

        List<Integer> list = new ArrayList<>();
        for (int[] operation : operations) {
            Node node = map.get(operation[1]);
            if (operation[0] == 1) {
                node.sum += operation[2];
                while (node.parent != null) {
                    node.parent.sum += operation[2];
                    node = node.parent;
                }
            } else if (operation[0] == 2) {
                int sum = operate(node, operation[2]);
                while (node.parent != null) {
                    node.parent.sum += sum;
                    node = node.parent;
                }
            } else {
                list.add((node.sum % 1000000007));
            }
        }
        
        int index = 0;
        int[] ans = new int[list.size()];
        for (int num : list) {
            ans[index++] = num;
        }
        return ans;
    }

    private int operate(Node node, int num) {
        if (node == null) {
            return 0;
        }

        int sum = num;

        if (node.children != null) {
            for (Node child : node.children) {
                sum += operate(child, num);
            }
        }

        node.sum += sum;
        return sum;
    }
    
    class Node {
    public int val;
    public Node parent;
    public int sum;
    public List<Node> children;

    public Node(){}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
}
```