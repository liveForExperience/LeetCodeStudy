# LeetCode_1035_不相交的线
## 题目
我们在两条独立的水平线上按给定的顺序写下 A 和 B 中的整数。

现在，我们可以绘制一些连接两个数字 A[i] 和 B[j] 的直线，只要 A[i] == B[j]，且我们绘制的直线不与任何其他连线（非水平线）相交。

以这种方法绘制线条，并返回我们可以绘制的最大连线数。

示例 1：
```
输入：A = [1,4,2], B = [1,2,4]
输出：2
解释：
我们可以画出两条不交叉的线，如上图所示。
我们无法画出第三条不相交的直线，因为从 A[1]=4 到 B[2]=4 的直线将与从 A[2]=2 到 B[1]=2 的直线相交。
```
示例 2：
```
输入：A = [2,5,1,2,5], B = [10,5,2,1,5,2]
输出：3
```
示例 3：
```
输入：A = [1,3,7,1,7,5], B = [1,9,2,5,1]
输出：2
```
提示：
```
1 <= A.length <= 500
1 <= B.length <= 500
1 <= A[i], B[i] <= 2000
```
## 解法
### 思路
动态规划，等同于求最长公共子序列
- `dp[i][j]`：数组A的1到i和数组B的1到j范围内的最大不交叉线个数
- base case：`dp[0][0]`代表没有元素的状况，值为0
- 状态转移方程：`dp[i][j] = A[i] == B[j] ? dp[i][j] + 1 : Math.max(dp[i + 1][j], dp[i][j + 1])` 
- 嵌套循环所有的i和j的组合
- 最终返回`dp[A.len][B.len]`
### 代码
```java
class Solution {
    public int maxUncrossedLines(int[] A, int[] B) {
        int a = A.length, b = B.length;
        int[][] dp = new int[a + 1][b + 1];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                dp[i + 1][j + 1] = A[i] == B[j] ? dp[i][j] + 1 : Math.max(dp[i][j + 1], dp[i + 1][j]);
            }
        }
        return dp[a][b]; 
    }
}
```
# LeetCode_207_课程表
## 题目
现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？

示例 1:
```
输入: 2, [[1,0]] 
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
```
示例 2:
```
输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
```
说明:
```
输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
```
提示:
```
这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
拓扑排序也可以通过 BFS 完成。
```
## 解法
### 思路
图，bfs：
- 如果课程安排合理，可以完成所有课程，那么课程安排图可以组成一个`有向无环图`，所以对于一条边`(u,v)`，v的所有源点u都出现了，v才能出现
- 遍历课程的入度情况，生成入度表`in`
    - 数组下标对应课程对应的值
    - 下标值对应入度数
- 遍历`in`，查找值为0的下标，代表这些下标对应的课程已经没有前置需要学习的课程，可以开始学
- 将这个下标值放入队列
- 如果队列不为空，遍历这个队列：
    - 取出队列头元素暂存
    - 遍历`in`
        - 如果课程的前置元素不是这个暂存值就跳过
        - 否则，将暂存课程对应的入度数减1，并判断是否为0，如果是就将元素放入队列
    - 课程数-1，代表这门课被学习
- 循环结束后，判断课程数是否为0
### 代码
```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            in[prerequisite[0]]++;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                queue.offerFirst(i);
            }
        }

        while (!queue.isEmpty()) {
            int num = queue.pollFirst();
            numCourses--;

            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] != num) {
                    continue;
                }
                if (--in[prerequisite[0]] == 0) {
                    queue.offerFirst(prerequisite[0]);
                }
            }
        }

        return numCourses == 0;
    }
}
```
## 解法二
### 思路
图，dfs：
- 根据`prerequisites`生成正向的关系图`adjacency`（上完这节课之后可以上什么课）
- 生成数组`flag`记录当前课程是否被访问过，如果被访问过，说明生成了环图，不符合题目要求
- 递归过程：
    - 退出条件：
        - `flag == 1`：说明形成了闭环，返回false
        - `flag == -1`：说明已经遍历过，返回true
    - 循环遍历`adjacency[i]`，如果当前遍历的下标对应值为1，且递归返回值为false，返回false，说明这个课程会形成闭环
    - 循环结束，把当前节点i的值置为-1
    - 返回true
### 代码
```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] adjacency = new int[numCourses][numCourses];
        int[] flag = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            adjacency[prerequisite[1]][prerequisite[0]] = 1;
        }

        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, flag, adjacency)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int i, int[] flag, int[][] adjacency) {
        if (flag[i] == 1) {
            return true;
        }

        if (flag[i] == -1) {
            return false;
        }

        flag[i] = 1;
        for (int j = 0; j < adjacency.length; j++) {
            if (adjacency[i][j] == 1 && dfs(j, flag, adjacency)) {
                return true;
            }
        }

        flag[i] = -1;
        return false;
    }
}
```
## 优化代码
### 思路
使用动态数组代替数组，减少查询的次数
### 代码
```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        
        int[] flag = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            adjacency.get(prerequisite[1]).add(prerequisite[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, flag, adjacency)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int i, int[] flag, List<List<Integer>> adjacency) {
        if (flag[i] == 1) {
            return true;
        }

        if (flag[i] == -1) {
            return false;
        }

        flag[i] = 1;
        for (int j : adjacency.get(i)) {
            if (dfs(j, flag, adjacency)) {
                return true;
            }
        }

        flag[i] = -1;
        return false;
    }
}
```
# LeetCode_1276_不浪费原料的汉堡值作方法
## 题目
圣诞活动预热开始啦，汉堡店推出了全新的汉堡套餐。为了避免浪费原料，请你帮他们制定合适的制作计划。

给你两个整数 tomatoSlices 和 cheeseSlices，分别表示番茄片和奶酪片的数目。不同汉堡的原料搭配如下：
```
巨无霸汉堡：4 片番茄和 1 片奶酪
小皇堡：2 片番茄和 1 片奶酪
请你以 [total_jumbo, total_small]（[巨无霸汉堡总数，小皇堡总数]）的格式返回恰当的制作方案，使得剩下的番茄片 tomatoSlices 和奶酪片 cheeseSlices 的数量都是 0。
```
如果无法使剩下的番茄片 tomatoSlices 和奶酪片 cheeseSlices 的数量为 0，就请返回 []。

示例 1：
```
输入：tomatoSlices = 16, cheeseSlices = 7
输出：[1,6]
解释：制作 1 个巨无霸汉堡和 6 个小皇堡需要 4*1 + 2*6 = 16 片番茄和 1 + 6 = 7 片奶酪。不会剩下原料。
```
示例 2：
```
输入：tomatoSlices = 17, cheeseSlices = 4
输出：[]
解释：只制作小皇堡和巨无霸汉堡无法用光全部原料。
```
示例 3：
```
输入：tomatoSlices = 4, cheeseSlices = 17
输出：[]
解释：制作 1 个巨无霸汉堡会剩下 16 片奶酪，制作 2 个小皇堡会剩下 15 片奶酪。
```
示例 4：
```
输入：tomatoSlices = 0, cheeseSlices = 0
输出：[0,0]
```
示例 5：
```
输入：tomatoSlices = 2, cheeseSlices = 1
输出：[0,1]
```
提示：
```
0 <= tomatoSlices <= 10^7
0 <= cheeseSlices <= 10^7
```
## 解法
### 思路
二元一次方程：
```math
4x + 2y = tomatoSlices
x + y = cheeseSlices
```
解：
```math
x = 1/2 * tomatoSlices - cheeseSlices
y = 2 * cheeseSlices - 1/2 * tomatoSlices
```
因为x,y >= 0且x,y属于自然数，所以：
```math
tomatoSlices = 2k, k∈N
tomatoSlices >= 2 * cheeseSlices
4 * cheeseSlices >= tomatoSlices
```
只要入参不符合如上3个条件，则返回`[]`
### 代码
```java
class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        if (tomatoSlices % 2 != 0 || tomatoSlices < 2 * cheeseSlices || 4 * cheeseSlices < tomatoSlices) {
            return Collections.emptyList();
        }
        return new ArrayList<Integer>(){{add(tomatoSlices / 2 - cheeseSlices);add(2 * cheeseSlices - tomatoSlices / 2);}};
    }
}
```
# LeetCode_264_丑数II
## 题目
编写一个程序，找出第 n 个丑数。

丑数就是只包含质因数 2, 3, 5 的正整数。

示例:
```
输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
```
说明:  
```
1 是丑数。
n 不超过1690。
```
## 失败解法
### 失败原因
超时
### 思路
- 使用两个set分别存储丑数和非丑数
- 从1开始遍历数字，判断当前数字能否被2、3、5整除，如果可以就继续计算，直到当前数被除为1
- 将丑数和非丑数在循环结束时放入相应的set中
- 在循环判断时，判断set中是否有对应的数，起到缓存的作用
- 最后当丑数值和`n`相同时，返回当前元素
### 代码
```java
class Solution {
    public int nthUglyNumber(int n) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> no = new HashSet<>();
        int num = 0;
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(5);

        for (int i = 0; true; i++) {
            int tmp = i;
            boolean flag = true;
            while (tmp > 0) {
                if (set.contains(tmp)) {
                    break;
                }

                if (no.contains(tmp)) {
                    flag = false;
                    break;
                }

                if (tmp % 2 == 0) {
                    tmp /= 2;
                    continue;
                }

                if (tmp % 3 == 0) {
                    tmp /= 3;
                    continue;
                }

                if (tmp % 5 == 0) {
                    tmp /= 5;
                    continue;
                }

                flag = false;
                break;
            }

            if (flag) {
                num++;
                if (num == n) {
                    return i;
                }
                set.add(i);
            } else {
                no.add(i);
            }
        }
    }
}
```
## 解法
### 思路
- 因为丑数通过2、3、5组成，所以可以通过3个指针来代表能被2、3、5整除的数，初始化3个指针对应值为0
- 在n个丑数的数组中，每个数都可能被3个中的任意一个或多个元素指向
- 初始化一个动态数组，且令第一个元素为1
- 令代表3个数的指针同时指向这个下标对应的元素，同时用index来代表当前指向的是第几个丑数
- 然后开始比较3个数与当前丑数的乘积最小的一个，然后令这个数为后一个丑数
- 同时使用的那个因数对应的下标就移动一格，代表当前因数和下一个丑数元素形成可能的丑数可能，用来在下一次循环中进行比较
- 然后index自增，直到index和n值相同
### 代码
```java
class Solution {
    public int nthUglyNumber(int n) {
        List<Integer> list = new ArrayList<Integer>(){{add(1);}};
        int i2 = 0, i3 = 0, i5  =0, index = 1;
        while (index++ < n) {
            int num = Math.min(list.get(i2) * 2, Math.min(list.get(i3) * 3, list.get(i5) * 5));
            list.add(num);

            if (list.get(i2) * 2 == num) {
                i2++;
            }

            if (list.get(i3) * 3 == num) {
                i3++;
            }

            if (list.get(i5) * 5 == num) {
                i5++;
            }
        }

        return list.get(list.size() - 1);
    }
}
```
## 优化代码
### 思路
使用数组代替动态数组
### 代码
```java
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0, index = 0;
        
        while (++index < n) {
            int num = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
            dp[index] = num;
            if (num == dp[i2] * 2) {
                i2++;
            }
            
            if (num == dp[i3] * 3) {
                i3++;
            }
            
            if (num == dp[i5] * 5) {
                i5++;
            }
        }
        
        return dp[n - 1];
    }
}
```