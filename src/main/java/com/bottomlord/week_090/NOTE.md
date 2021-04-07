# [LeetCode_190_颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/)
## 解法
### 思路
- 使用`10000000000000000000000000000000`和`1`两个掩码来确定颠倒二进制
- 使用`1`，不断左移来来确定当前位上是否是1，用或来判断
- 如果判断是1，那就通过`10000000000000000000000000000000`同步无符号右移的过程中，通过或来填充1
### 代码
```java
public class Solution {
    public int reverseBits(int n) {
        int mask = 1, time = 31;
        while (time-- > 0) {
            mask <<= 1;
        }

        int ans = 0, count = 1;
        time = 32;
        while (time-- > 0) {
            if ((count | n) == n) {
                ans |= mask;
            }
            mask >>>= 1;
            count <<= 1;
        }
        return ans;
    }
}
```
# [LeetCode_502_IPO](https://leetcode-cn.com/problems/ipo/)
## 失败解法
### 原因
超时，时间复杂度过高，树高k层，第t(t <= n)层的搜索都要检查t-1个节点
### 思路
记忆化+回溯
### 代码
```java
class Solution {
    private int ans;

    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        this.ans = W;
        k = Math.min(k, Profits.length);
        backTrack(k, W, Profits, Capital, new boolean[Profits.length]);
        return ans;
    }

    private void backTrack(int time, int w, int[] profits, int[] capital, boolean[] memo) {
        ans = Math.max(ans, w);
        if (time == 0) {
            return;
        }

        for (int i = 0; i < profits.length; i++) {
            if (memo[i] || w < capital[i]) {
                continue;
            }

            memo[i] = true;
            backTrack(time -  1, w + profits[i], profits, capital, memo);
            memo[i] = false;
        }
    }
}
```
## 解法
### 思路
贪心：
- 要的是k个项目的最大收益，所以每次判断是否可以做项目的情况下，取最大的收益项目即可
- 每次使用完一个项目以后，就将其的花费值设置为无限大，使得下次不能重复使用
- 每次都判断可以使用的项目中的最大值，然后累加，直到k和项目数两者之间的最小值的次数被遍历完
### 代码
```java
class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        boolean speedUp = true;
        int sum = W;
        for (int i = 0; i < Capital.length; i++) {
            if (W < Capital[i]) {
                speedUp = false;
                break;
            }
        }

        if (speedUp) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int i = 1; i <= Capital.length; i++) {
                queue.offer(Profits[i - 1]);
                
                if (i > k) {
                    queue.poll();
                }
            }
            
            return queue.stream().mapToInt(x -> x).sum() + W;
        }

        for (int i = 0; i < Math.min(k, Profits.length); i++) {
            int index = -1;

            for (int j = 0; j < Profits.length; j++) {
                if (W >= Capital[j]) {
                    if (index == -1 || Profits[index] < Profits[j]) {
                        index = j;
                    }
                }
            }
            
            if (index == -1) {
                break;
            }

            W += Profits[index];
            Capital[index] = Integer.MAX_VALUE;
        }
        
        return W;
    }
}
```
# [LeetCode_74_搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/)
## 解法
### 思路
记忆化+深度优先搜索
### 代码 
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[row - 1][col - 1]) {
            return false;
        }

        return dfs(matrix, 0, 0, row, col, target, new boolean[row][col]);
    }

    private boolean dfs(int[][] matrix, int r, int c, int row, int col, int target, boolean[][] memo) {
        if (r < 0 || r >= row || c < 0 || c >= col || memo[r][c]) {
            return false;
        }

        memo[r][c] = true;
        int pivot = matrix[r][c];
        if (pivot == target) {
            return true;
        }

        if (pivot > target) {
            return dfs(matrix, r, c - 1, row, col, target, memo) || dfs(matrix, r - 1, c, row, col, target, memo);
        } else {
            return dfs(matrix, r, c + 1, row, col, target, memo) || dfs(matrix, r + 1, c, row, col, target, memo);
        }
    }
}
```
# [LeetCode_505_迷宫II](https://leetcode-cn.com/problems/the-maze-ii/)
## 解法
### 思路
记忆化+bfs
### 代码
```java
class Solution {
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int row = maze.length, col = maze[0].length;

        Queue<Node> queue = new ArrayDeque<>();
        int[][] memo = new int[row][col];
        for (int[] arr : memo) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        memo[start[0]][start[1]] = 0;
        queue.offer(new Node(start[0], start[1], 0));
        int ans = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x, y = cur.y;

            for (int[] dir : dirs) {
                int nextX = x + dir[0], nextY = y + dir[1], count = 0;
                while (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && maze[nextX][nextY] != 1) {
                    nextX += dir[0];
                    nextY += dir[1];
                    count++;
                }

                if (count == 0) {
                    continue;
                }

                nextX -= dir[0];
                nextY -= dir[1];
                count += cur.count;
                
                if (count <= memo[nextX][nextY] && count < ans) {
                    memo[nextX][nextY] = count;
                    if (nextX == destination[0] && nextY == destination[1]) {
                        ans = count;
                        break;
                    } else {
                        queue.offer(new Node(nextX, nextY, count));
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private class Node {
        private int count;
        private int x;
        private int y;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
```
# [LeetCode_90_子集](https://leetcode-cn.com/problems/subsets-ii/)
## 解法
### 思路
- 先排序，使得组成的子集按升序排列，这样方便做记忆化处理
- 回溯+记忆化
### 代码
```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        Set<String> memo = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            backTrack(nums, i, ans, new LinkedList<>(), memo, new StringBuilder());
        }
        
        return ans;
    }
    
    private void backTrack(int[] nums, int index, List<List<Integer>> ans, LinkedList<Integer> list, Set<String> memo, StringBuilder sb) {
        if (!memo.contains(sb.toString())) {
            memo.add(sb.toString());
            ans.add(new ArrayList<>(list));
        }

        if (index >= nums.length) {
            return;
        }
        
        for (int i = index; i < nums.length; i++) {
            int len = sb.length();
            sb.append("#").append(nums[i]);
            if (memo.contains(sb.toString())) {
                sb.setLength(len);
                continue;
            }
            
            list.add(nums[i]);
            backTrack(nums, i + 1, ans, list, memo, sb);
            list.removeLast();
            sb.setLength(len);
        }
    } 
}
```
## 解法二
### 思路
- 解法一中使用字符串hash来判断是否有重复的子集
- 但其实，当序列被排序后，真正导致出现重复的情况是，连续相同的值在同一层被带入下一层，这样就会导致生成的list的序列出现重复，为了避免这种情况，就可以在同一层中确保相同的值只下钻一次，这样就ok了
### 代码
```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(nums, 0, ans, new LinkedList<>());
        return ans;
    }

    private void backTrack(int[] nums, int index, List<List<Integer>> ans, LinkedList<Integer> list) {
        ans.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            backTrack(nums, i + 1, ans, list);
            list.removeLast();

            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }
}
```
# [LeetCode_1006_笨阶乘](https://leetcode-cn.com/problems/clumsy-factorial/)
## 解法
### 思路
模拟过程：
- 初始化一个栈num用来存储操作数
- 初始化一个队列operators，用来存储操作符，将4个运算符依次放入队列中
- 然后开始倒叙遍历N
    - 如果是N，也就是第一个数，就直接将其压入num
    - 否则就需要将操作符出队做判断
        - 是乘除，就直接弹出栈顶元素与当前值做运算
            - 除法：出栈的数除以当前数
        - 是加减发，就直接将操作符和数字直接分别压入队列和栈中
        - 是空，是不应该出现的情况，用返回0来代替
- 处理完一次枚举N个数的过程后，初始化一个栈，用来处理剩下的需要加减的值
- 将剩下的num的值出栈再入栈到新的栈里
- 然后仿造之前的操作将栈里剩下的值处理完
- 最后栈中只剩下1个数值的时候，就返回该值作为结果
- 注意：压入的操作符比操作数少1个
### 代码
```java
class Solution {
    public int clumsy(int N) {
        Stack<Integer> nums1 = new Stack<>();
        Queue<Character> operators = new ArrayDeque<>();
        char[] operator = new char[]{'*', '/', '+', '-'};
        for (int i = 0; i < N - 1; i++) {
            operators.offer(operator[i % 4]);
        }

        for (int i = N; i >= 1; i--) {
            if (i == N) {
                nums1.push(i);
            } else {
                Character op = operators.poll();
                if (op == null) {
                    return 0;
                }

                if (op == '*') {
                    nums1.push(nums1.pop() * i);
                }

                if (op == '/') {
                    nums1.push(nums1.pop() / i);
                }

                if (op == '+' || op == '-') {
                    nums1.push(i);
                    operators.offer(op);
                }
            }
        }

        Stack<Integer> nums2 = new Stack<>();
        while (!nums1.isEmpty()) {
            nums2.push(nums1.pop());
        }

        while (nums2.size() > 1) {
            Integer first = nums2.pop(), second = nums2.pop();
            
            Character op = operators.poll();
            if (op == null) {
                return 0;
            }

            if (op == '+') {
                nums2.push(first + second);
            }

            if (op == '-') {
                nums2.push(first - second);
            }
        }
        
        return nums2.pop();
    }
}
```
# [Interview_17_21_直方图的水量](https://leetcode-cn.com/problems/volume-of-histogram-lcci/)
## 解法
### 思路
- 决定当前区块能存储多少水，取决于该区块左右两边最高的区块
- 头尾区块一定没有水
- 通过两个数组，记录从左到右，和从右到左，高度最大值的变化
- 然后再遍历依次区块数组，用左右2边的最小值与当前区块相减，获得水量，然后累加
- 如果区块数组长度小于3，则直接返回0即可
### 代码
```java
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        if (len < 3) {
            return 0;
        }

        int[] leftMax = new int[len], rightMax = new int[len];
        leftMax[0] = height[0];
        rightMax[len - 1] = height[len - 1];
        
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        
        int ans = 0;
        for (int i = 1; i < len - 1; i++) {
            ans += Math.max(Math.min(leftMax[i - 1], rightMax[i + 1]) - height[i], 0); 
        }
        
        return ans;
    }
}
```
# LeetCode_1143_最长公共子序列
## 解法
### 思路
动态规划
- dp[i][j]：代表字符串text1的0-i个坐标中与字符串text2字符串0-j坐标中能够组成相等的最长子串
- 状态转移方程：
    - 如果text1[i] == text[j]，`dp[i][j] = dp[i- 1][j - 1] + 1`
    - 如果不相等，`dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])`
- 为了方便计算，坐标从1开始，初始状态由坐标0代替
### 代码
```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < len2; i++) {
            dp[0][i] = 0;
        }
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[len1][len2];
    }
}
```