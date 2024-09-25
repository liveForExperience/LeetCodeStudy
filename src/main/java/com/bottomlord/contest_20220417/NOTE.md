# [Contest_1_计算字符串的数字和](https://leetcode-cn.com/contest/weekly-contest-289/problems/calculate-digit-sum-of-a-string/)
## 解法
### 思路
2层循环：
- 外层循环的退出条件是`s`的长度符合题目要求，小于等于k
- 内层用于处理当前字符串，将字符串根据k的长度切分后，计算每个k长度字符串的总和，再转成字符串
- 将上述内层切分出来的字符串重新拼接后，赋值给`s`，然后结束当前内层的循环
- 整个过程直到外层退出为止，返回`s`即可
### 代码
```java
class Solution{
    public String digitSum(String s, int k) {
        int n = s.length();
        if (n <= k) {
            return s;
        }

        while (s.length() > k) {
            int index = 0;
            StringBuilder sb = new StringBuilder();
            while (index < s.length()) {
                sb.append(getStr(index, s, k));
                index += k;
            }
            s = sb.toString();
        }

        return s;
    }

    private String getStr(int index, String str, int k) {
        String s = str.substring(index, index + Math.min(k, str.length() - index));
        char[] cs = s.toCharArray();
        int sum = 0;
        for (char c : cs) {
            sum += c - '0';
        }
        return Integer.toString(sum);
    }
}
```
## 解法二
### 思路
递归：
- 在解法一的基础上可以发现，内层循环的处理过程是一致的，可以通过递归简化代码
- 递归的退出条件就是字符串`s`长度不大于K
- 递归的处理过程和解法一的内部处理逻辑一致
### 代码
```java
class Solution {
    public String digitSum(String s, int k) {
        if (s.length() <= k) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length();) {
            int index = 0;
            int num = 0;
            while (index < k && i < s.length()) {
                num += s.charAt(i) - '0';
                index++;
                i++;
            }

            sb.append(num);
        }

        return digitSum(sb.toString(), k);
    }
}
```
# [Contest_2_完成所有任务需要的最少轮数](https://leetcode-cn.com/problems/minimum-rounds-to-complete-all-tasks/)
## 解法
### 思路
- 通过hash表统计除数字出现的个数
- 因为2作为因数小于3，所以得到的另一个因数一定更大，所以可以通过2作为除数得到的商，作为判断的上界，这个指针值代表2作为因数的个数
- 遍历过程中计算符合情况的最小值，因为2作为商的个数越多，那么总个数一定也会更多，原因是3作为除数一定能得到更小的商，所以遍历的方向就是向上
- 遍历过程中判断总值减去2作为因数的值后，能不能被3整除，如果可以就返回，否则就继续寻找
- 如果没有找到就返回初始的int最大值作为标记
- 遍历map的entry的过程中就通过如上的方法得到每个数的可能个数，如果得到的是int的最大值，就返回-1
- 累加遍历的值之后，循环结束，返回总值
### 代码
```java
class Solution {
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = count(entry.getValue());
            if (val == Integer.MAX_VALUE) {
                return -1;
            }

            ans += val;
        }

        return ans;
    }

    private int count(int val) {
        int c2 = val / 2, min = Integer.MAX_VALUE;
        for (int i = 0; i <= c2; i++) {
            int num = val - i * 2;
            if (num % 3 != 0) {
                continue;
            }

            int count = i + num / 3;

            if (count < min) {
                min = count;
                break;
            }
        }

        return min;
    }
}
```
## 解法二
### 思路
- 通过观察发现，被3除，只有3种可能
  - 整除
  - 余1
  - 余2
- 后两种情况都可以理解为有1个2
  - 3 + 1 = 2 + 2
  - 3 + 2
- 而真正导致返回-1的情况，其实只有当这个数字出现的个数是1的时候
- 所以解法就简单了
  - 通过map统计出数字出现的个数
  - 然后遍历map
  - 如果发现map统计值为1，那么就返回-1
  - 如果发现map统计值不是1，就通过` (num / 3) + (num % 3 >= 1 ? 1 : 0)`的公式得到个数然后累加
  - 循环结束后返回累加值即可
### 代码
```java
class Solution {
    public int minimumRounds(int[] tasks) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            if (val == 1) {
                return -1;
            }
            sum += count(val);
        }

        return sum;
    }

    private int count(int num) {
        return (num / 3) + (num % 3 >= 1 ? 1 : 0);
    }
}
```
# [Contest_3_转角路径的乘积中最多能有几个尾随零](https://leetcode-cn.com/problems/maximum-trailing-zeros-in-a-cornered-path/)
## 解法
### 思路
前缀和
- 统计相乘后0的个数，其实就是统计所有乘数中2和5作为因数出现了多少个，而两者出现个数的最小值就是0的个数，将该值记为count
- 通过观察题目可以知道，如果将二维数组中某一个坐标作为拐点，那么以该拐点为出发点就会有如下4种可能情况：
    - 上+拐点+左
    - 上+拐点+右
    - 下+拐点+左
    - 下+拐点+右
- 而上下左右的count值，为了在遍历拐点过程中快速得到，可以通过预处理前缀和来获得
- 所以整个算法过程就可分成
    - 预处理出，以2、5作为因数的个数的前缀和
    - 遍历拐点，通过前缀和，求出拐点4种情况中的最大值
    - 然后比较和更新结果值max，在遍历结束后返回max即可
### 代码
```java
class Solution {
    public int maxTrailingZeros(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] rs2 = new int[row + 1][col], rs5 = new int[row + 1][col],
                cs2 = new int[row][col + 1], cs5 = new int[row][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                int num = grid[i - 1][j - 1], c2 = count(num, 2), c5 = count(num, 5);
                rs2[i][j - 1] = rs2[i - 1][j - 1] + c2;
                rs5[i][j - 1] = rs5[i - 1][j - 1] + c5;
                cs2[i - 1][j] = cs2[i - 1][j - 1] + c2;
                cs5[i - 1][j] = cs5[i - 1][j - 1] + c5;
            }
        }

        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int u2 = rs2[i + 1][j], u5 = rs5[i + 1][j];
                int l2 = cs2[i][j], l5 = cs5[i][j];
                int d2 = rs2[row][j] - rs2[i][j], d5 = rs5[row][j] - rs5[i][j];
                int r2 = cs2[i][col] - cs2[i][j + 1], r5 = cs5[i][col] - cs5[i][j + 1];
                
                max = Math.max(max, Math.min(u2 + l2, u5 + l5));
                max = Math.max(max, Math.min(u2 + r2, u5 + r5));
                max = Math.max(max, Math.min(d2 + l2, d5 + l5));
                max = Math.max(max, Math.min(d2 + r2, d5 + r5));
            }
        }
        return max;
    }

    private int count(int num, int target) {
        int count = 0;
        while (num > 0) {
            if (num % target != 0) {
                break;
            }
            num /= target;
            count++;
        }
        
        return count;
    }
}
```
# [Contest_4_相邻字符不同的最长路径](https://leetcode-cn.com/problems/longest-path-with-different-adjacent-characters/)
## 解法
### 思路
dfs
- 通过parent数组构建邻接表
- 通过dfs计算每一层的根节点与最大的至多2个子树的总和
- 因为值没有负数，所以在遍历子树统计的过程中，不需要遍历所有单个子树的最大值，直接记录之前的最大子树值即可
- 类变量暂存当前递归过程中找到的最大结果值，这个结果值在每次循环子树的过程中，通过当前循环子树的值+历史最大子树的值来进行比较和更新，留下较大值即可
- dfs过程结束后，返回类变量即可
### 代码
```java
class Solution {
    private List<Integer>[] g;
    private int n, ans;
    private String s;
    public int longestPath(int[] parent, String s) {
        this.n = parent.length;
        this.s = s;
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        
        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }

        dfs(0);
        return ans + 1;
    }
    
    private int dfs(int parent) {
        List<Integer> list = g[parent];
        int maxLen = 0;
        for (Integer child : list) {
            int len = dfs(child) + 1;
            
            if (s.charAt(parent) != s.charAt(child)) {
                ans = Math.max(ans, maxLen + len);
                maxLen = Math.max(len, maxLen);
            }
        }
        
        return maxLen;
    }
}
```