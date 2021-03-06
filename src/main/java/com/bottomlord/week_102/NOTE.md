# [LeetCode_401_二进制手表](https://leetcode-cn.com/problems/binary-watch/submissions/)
## 解法
### 思路
- 枚举所有0到59的10进制对应二进制数中为1的位的个数，做关联映射
- 2层循环，外层循环12个小时，内层循环60分钟，判断哪些时间的个数总和是目标值，如果是就转译为字符串
### 代码
```java
class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int i = 1; i < 60; i++) {
            int count = 1, j = i;
            while ((j &= (j - 1)) != 0) {
                count++;
            }
            
            map.put(i, count);
        }
        
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (map.get(i) + map.get(j) == turnedOn) {
                    String min = j < 10 ? "0" + j : "" + j;
                    ans.add(i + ":" + min);
                }
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_offer38_1_字符串的排列](https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/)
## 解法
### 思路
记忆化+回溯
### 代码
```java
class Solution {
    public String[] permutation(String s) {
        Set<String> ans = new HashSet<>();
        backTrack(s, new StringBuilder(), new HashSet<>(), ans);
        return ans.toArray(new String[0]);
    }

    private void backTrack(String s, StringBuilder sb, Set<Integer> memo, Set<String> ans) {
        if (memo.size() == s.length()) {
            ans.add(sb.toString());
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (memo.contains(i)) {
                continue;
            }

            int len = sb.length();
            sb.append(s.charAt(i));
            memo.add(i);
            backTrack(s, sb, memo, ans);
            memo.remove(i);
            sb.setLength(len);
        }
    }
}
```
## 解法二
### 思路
在解法一回溯的基础上，使用旋转的方式替换追加的方式来记录当前枚举的字符串状态，同时在回溯的过程中，在每一层用一个布尔数组来记录当前层是否用过遍历到的字符，如果用到了就跳过，否则就用当前遍历到的字符作为当前层的字符
### 代码
```java
class Solution {
    public String[] permutation(String s) {
        List<String> ans = new ArrayList<>();
        backTrack(s.toCharArray(), 0, ans);
        return ans.toArray(new String[0]);
    }
    
    private void backTrack(char[] cs, int index, List<String> ans) {
        if (index == cs.length - 1) {
            ans.add(new String(cs));
            return;
        }
        
        boolean[] used = new boolean[256];
        for (int i = index; i < cs.length; i++) {
            if (used[cs[i]]) {
                continue;
            }
            
            used[cs[i]] = true;
            swap(cs, index, i);
            backTrack(cs, index + 1, ans);
            swap(cs,index, i);
        }
    }
    
    private void swap(char[] cs, int x, int y) {
        char c = cs[x];
        cs[x] = cs[y];
        cs[y] = c;
    }
}
```
# [LeetCode_1243_数组变换](https://leetcode-cn.com/problems/array-transformation/)
## 解法
### 思路
模拟，2层循环：
- 外层依据状态变量判断是否结束
- 内层遍历数组，依据题目要求变更元素，每次只变更一次
- 如果内层一次都没有变更，就结束外层循环
- 注意需要复制原来的数组，每个元素的变化都是在遍历前的状态上做的变更，而不是边变化边变更。同时要注意每次外层循环的时候都要复制一次，否则会导致2个引用同时指向同一个数组，导致又变成边遍历边变化的情况
### 代码
```java
class Solution {
    public List<Integer> transformArray(int[] arr) {
        boolean flag = true;
        while (flag) {
            int count = 0;
            int[] copy = Arrays.copyOf(arr, arr.length);    
            for (int i = 1; i < arr.length - 1; i++) {
                if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) {
                    copy[i]++;
                    count++;
                }

                if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                    copy[i]--;
                    count++;
                }
            }
            
            arr = copy;
            flag = count != 0;
        }

        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }
}
```
# [LeetCode_offer15_二进制中1的个数](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/submissions/)
## 解法
### 思路
- 通过位运算消去最低位的1：`n & (n - 1)`
- 循环转变n，直到n为0为止，过程中计数
### 代码
```java
public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= n -1;
            count++;
        }
        return count;
    }
}
```
# [LeetCode_1252_奇数值单元格的数目](https://leetcode-cn.com/problems/cells-with-odd-values-in-a-matrix/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int[][] matrix = new int[m][n];
        for (int[] indic : indices) {
            for (int i = 0; i < m; i++) {
                matrix[i][indic[1]]++;
            }
            
            for (int i = 0; i < n; i++) {
                matrix[indic[0]][i]++;
            }
        }
        
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] % 2 == 1) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
```
## 解法二
### 思路
- 相较于解法一记录每一个元素，这里只记录哪些行和列被改变
- 记录的时候也只做异或1的计算，只记录是否增加奇数次的状态
- 最后行数为1的乘以行的个数，列数为1的乘以列的个数，两数相加就得到一个近似答案的值
- 但是如上的相加，获得的个数中，有一部分是要减去的，那就是行列的交叉点。因为行列各加1次，等于没有增加
- 那么就需要在行求出的总数和列求出的总数上减去这些点，那就相当于是统计的行数和统计的列数相乘组成的矩形面积，而这个面积分别在行和列的总数上减去，相当于减2次
    - `row * m + col * n - 2 * row * col`，m是行上的元素个数，n是列上的元素个数
### 代码
```java
class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int[] row = new int[m], col = new int[n];
        for (int[] indic : indices) {
            row[indic[0]] ^= 1;
            col[indic[1]] ^= 1;
        }
        
        int rowNum = 0, colNum = 0;
        for (int num : row) {
            if (num == 1) {
                rowNum++;
            }
        }
        
        for (int num : col) {
            if (num == 1) {
                colNum++;
            }
        }
        
        return rowNum * n + colNum * m - 2 * rowNum * colNum;
    }
}
```
# [LeetCode_149_直线上最多的点数](https://leetcode-cn.com/problems/max-points-on-a-line/)
## 解法
### 思路
- 如果两个点在同一直线上，那么这两个点一定符合`y = ax + b`的公式
- 如果将2个y相减就得到了`y1 - y2 = a(x1 - x2)`，进而推得`a = (y1 - y2) / (x1 - x2)`，所以两个点的差的比值一定是相等的
- 所以可以统计比值对应的个数，从而可以获得一条线上的最大值
- 注意x或y相减为0的时候的特殊情况，double会保留正负值，需要做统一处理
### 代码
```java
class Solution {
    public int maxPoints(int[][] points) {
        int len = points.length;
        if (len < 3) {
            return len;
        }

        int ans = 2;
        for (int i = 0; i < len; i++) {
            Map<Double, Integer> map = new HashMap<>();
            int max = 0;
            for (int j = i + 1; j < len; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];

                Double num = y == 0 ? Double.POSITIVE_INFINITY : x == 0 ? 0 : 1D * x / y;
                map.put(num, map.getOrDefault(num, 0) + 1);
                max = Math.max(max, map.get(num));
            }

            ans = Math.max(max + 1, ans);
        }

        return ans;
    }
}
```
# [LeetCode_1260_二维网络迁移](https://leetcode-cn.com/problems/shift-2d-grid/)
## 解法
### 思路
- 遍历二维数组放入一维数组中
- 按规则重新拷贝出新数组
- 遍历放入对应结果中
### 代码
```java
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int row = grid.length, col = grid[0].length, sum = row * col;
        int[] arr = new int[sum];

        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[index++] = grid[i][j];
            }
        }

        int move = k % sum;
        int[] newArr = new int[sum];
        System.arraycopy(arr, sum - move, newArr, 0, move);
        System.arraycopy(arr, 0, newArr, move, sum - move);
        
        List<List<Integer>> ans = new ArrayList<>();
        index = 0;
        for (int i = 0; i < row; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                list.add(newArr[index++]);
            }
            ans.add(list);
        }
        return ans;
    }
}
```
## 解法二
### 思路
- 新建新的数组
- 依赖坐标模拟移动的动作
  - 横坐标：判断移动了多少个row
  - 纵坐标：判断移动了多少个单元格
### 代码
```java
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int row = grid.length, col = grid[0].length;
        int[][] ans = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans[(i + (j + k) / col) % row][(j + k) % col] = grid[i][j];
            }
        }
        
        List list = Arrays.asList(ans);
        return list;
    }
}
```
# [LeetCode_752_打开转盘锁](https://leetcode-cn.com/problems/open-the-lock/)
## 解法
### 思路
bfs
### 代码
```java
class Solution {
    public int openLock(String[] deadends, String target) {
if (Objects.equals("0000", target)) {
            return 0;
        }
        
        int step = 0;
        Set<String> memo = new HashSet<>(), dead = Arrays.stream(deadends).collect(Collectors.toSet());
        if (dead.contains("0000")) {
            return -1;
        }
        
        Queue<String> queue = new ArrayDeque<>();
        queue.offer("0000");

        while (!queue.isEmpty()) {
            int size = queue.size();

            step++;
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                for (String newStatus : get(status)) {
                    if (Objects.equals(newStatus, target)) {
                        return step;
                    }

                    if (memo.contains(newStatus) || dead.contains(newStatus)) {
                        continue;
                    }

                    queue.offer(newStatus);
                    memo.add(newStatus);
                }
            }
        }

        return -1;
    }

    private List<String> get(String status) {
        List<String> list = new ArrayList<>(8);
        for (int i = 0; i < 4; i++) {
            add(list, status, i, this::getPre);
            add(list, status, i, this::getNext);
        }
        return list;
    }
    
    private char getPre(char c) {
        return c == '0' ? '9' : (char)(c - 1);
    }
    
    private char getNext(char c) {
        return c == '9' ? '0' : (char)(c + 1);
    }
    
    private void add(List<String> list, String status, int index, Function<Character, Character> function) {
        char[] cs = status.toCharArray();
        cs[index] = function.apply(cs[index]);
        list.add(new String(cs));
    }
}
```
# [LeetCode_773_滑动谜题](https://leetcode-cn.com/problems/sliding-puzzle/)
## 解法
### 思路
bfs
### 代码
```java
class Solution {
    int[][] neighbour = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public int slidingPuzzle(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }

        String status = sb.toString();
        if (Objects.equals(status, "123450")) {
            return 0;
        }

        int step = 0;
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(status);

        Set<String> memo = new HashSet<>();
        memo.add(status);

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                List<String> ss = next(s);
                for (String str : ss) {
                    if (Objects.equals(str, "123450")) {
                        return step;
                    }

                    if (memo.contains(str)) {
                        continue;
                    }

                    queue.offer(str);
                    memo.add(str);
                }
            }
        }

        return -1;
    }

    private List<String> next(String status) {
        List<String> next = new ArrayList<>();
        char[] cs = status.toCharArray();
        int index = status.indexOf('0');
        int[] poses = neighbour[index];
        for (int pos : poses) {
            swap(cs, pos, index);
            next.add(new String(cs));
            swap(cs, pos, index);
        }

        return next;
    }

    private void swap(char[] cs, int x, int y) {
        char c = cs[x];
        cs[x] = cs[y];
        cs[y] = c;
    }
}
```
# [LeetCode_909_蛇梯棋](https://leetcode-cn.com/problems/snakes-and-ladders/)
## 解法
### 思路
- 将二维数组降成一维
- bfs获取结果
  - 因为有穿梭的机制，所以不能直接通过获取size来搜索，而是通过map来记录坐标和步数的映射关系
  - 每次搜索循环就是统计基于当前节点的后面6步分别是什么坐标，是否在映射关系中已经存在，如果存在就说明有穿梭的情况，直接跳过，不再搜索
- 官方题目应该是机翻的，题目解释可以看这个[题解](https://leetcode-cn.com/problems/snakes-and-ladders/solution/java-bfskan-bu-dong-ti-xi-lie-by-quruiji-k21l/)
### 代码
```java
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] nums = new int[n * n + 1];
        boolean right = true;
        int index = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = right ? 0 : n - 1;  right ? j < n : j >= 0; j += right ? 1 : -1) {
                nums[index++] = board[i][j];
            }
            right = !right;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);

        while (!queue.isEmpty()) {
            int num = queue.poll();
            int step = map.get(num);
            if (num == n * n) {
                return step;
            }
            
            for (int i = 1; i <= 6; i++) {
                int nextNum = num + i;
                if (nextNum <= 0 || nextNum > n * n) {
                    continue;
                }

                if (nums[nextNum] != -1) {
                    nextNum = nums[nextNum];
                }
                
                if (map.containsKey(nextNum)) {
                    continue;
                }
                
                queue.offer(nextNum);
                map.put(nextNum, step + 1);
            }
        }
        
        return -1;
    }
}
```
# [LeetCode_1266_访问所有点的最小时间](https://leetcode-cn.com/problems/minimum-time-visiting-all-points/)
## 失败解法
### 原因
超时
### 思路
bfs
### 代码
```java
class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        int step = 0, index = 0, n = points.length;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(points[index]);
        Set<String> memo = new HashSet<>();
        memo.add(points[index][0] + ":" + points[index][1]);
        index++;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                boolean find = false;
                if (arr[0] == points[index][0] && arr[1] == points[index][1]) {
                    index++;
                    if (index == n) {
                        return step;
                    }
                    queue.clear();
                    memo.clear();
                    find = true;
                }

                for (int[] direction : directions) {
                    int[] newArr = new int[]{arr[0] + direction[0], arr[1] + direction[1]};
                    String memoKey = newArr[0] + ":" + newArr[1];
                    if (memo.contains(memoKey)) {
                        continue;
                    }

                    queue.offer(newArr);
                    memo.add(memoKey);
                }

                if (find) {
                    break;
                }
            }

            step++;
        }

        return -1;
    }
}
``` 
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int index = 0, n = points.length, step = 0, row = points[0][0], col = points[0][1];
        
        while (index < n) {
            if (row == points[index][0] && col == points[index][1]) {
                index++;
            }
            
            if (index == n) {
                break;
            }
            
            step++;
            
            if (row < points[index][0]) {
                row++;
            } else if (row > points[index][0]) {
                row--;
            }
            
            if (col < points[index][1]) {
                col++;
            } else if (col > points[index][1]) {
                col--;
            }
        }
        
        return step;
    }
}
```
## 解法二
### 思路
- 在解法一的基础上将每次移动的过程通过数学计算获得，通过观察可以发现，两个点的耗时就是横与横和纵与纵2个距离中的最大值
- 遍历数组计算点与点之间的距离然后累加即可
### 代码
```java
class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int sec = 0;
        for (int i = 0; i < points.length - 1; i++) {
            sec += getSec(points[i], points[i + 1]);
        }
        return sec;
    }
    
    private int getSec(int[] cur, int[] next) {
        int row = Math.abs(cur[0] - next[0]),
            col = Math.abs(cur[1] - next[1]);
        
        return Math.max(row, col);
    }
}
```