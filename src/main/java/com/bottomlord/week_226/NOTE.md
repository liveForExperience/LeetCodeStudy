# [LeetCode_318_最大单词长度乘积](https://leetcode.cn/problems/maximum-product-of-word-lengths)
## 解法
### 思路
- 思考过程： 
  - 题目要求计算和比较字符串长度的乘积最大值，相乘的两个字符串不能有公共字母，因为题目限制了字符串中只能出现小写字母，所以可以通过一个32位的整数存储26位小写字母在字符串中的出现情况，从而判断是否有重复
  - 重复的判断逻辑也很简单，就是通过2个32位整数的2进制位进行相与操作，如果`与`之后的结果是0，那么就说明没有公共字母
  - 然后2层循环遍历数组，根据字母的出现情况判断是否可以将2个字符串的长度相乘，如果可以就得到相乘的结果后和暂存的最大值进行比较即可
- 算法过程：
  - 初始化int数组`arr`，用来存储每个字符串字母出现情况的统计值
  - 初始化变量`ans`，初始值为0，用来作为成绩最大值的暂存变量
  - 循环一次数组，迭代并计算每个元素字符串的字母出现情况，记录到`arr`对应坐标中
  - 再次2层循环数组，两两判断是否有重复字母，判断方式`(arr[i] & arr[j]) == 0`，如果判断结果为没有重复字母，则相乘后与暂存`ans`进行比较并更新为较大值
  - 2层循环结束，返回`ans`座位结果
### 代码
```java
class Solution {
    public int maxProduct(String[] words) {
        int n = words.length, index = 0;
        int[] arr = new int[n];
        for (String word : words) {
            arr[index++] = mask(word);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((arr[i] & arr[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }

        return ans;
    }

    private int mask(String s) {
        char[] cs = s.toCharArray();
        int mask = 0;
        for (char c : cs) {
            mask |= 1 << (c - 'a');
        }
        return mask;
    }
}
```
# [LeetCode_2300_咒语和药水的成功对数](https://leetcode.cn/problems/successful-pairs-of-spells-and-potions)
## 解法
### 思路
- 思考过程：
  - 题目要求的是`spells`数组中的每一个元素，在`options`数组中能找到多少个相乘后可以大于`success`的元素个数
  - 因为`spells`元素的值大于0，所以乘积与`options`元素值成单调性
  - 那么如果将`options`数组进行排序之后，当在`options`数组中找到最小的那个与`spells`元素相乘后大于等`success`的元素，则该元素之后的所有元素也都是符合要求的
  - 那么就可以按照思路，将`options`排序后，通过二分查找找到那个最小的元素，就能快速得到符合要求的元素个数
- 算法过程：
  - 初始化`spells`数组的长度为`m`，`options`数组的长度为`n`
  - 初始化一个长度为`m`的答案数组`ans`
  - 对`options`数组正向排序
  - 遍历`spells`数组，基于每个遍历的元素，在`options`数组中通过二分查找找到最小的相乘大于等于`success`的坐标`index`
  - 然后通过`n - index`得到元素个数，然后存储`ans`数组对应的坐标中
  - 遍历结束后返回`ans`即可
### 代码
```java
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int m = spells.length, n = potions.length;
        int[] ans = new int[m];
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            ans[i] = n - binarySearch(potions, spells[i], success);
        }
        
        return ans;
    }
    
    private int binarySearch(int[] arr, int x, long target) {
        int head = 0, tail = arr.length - 1;
        
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            long cur = (long) arr[mid] * x;
            
            if (cur < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        
        return head;
    }
}
```
# [LeetCode_2258_逃离火灾](https://leetcode.cn/problems/escape-the-spreading-fire)
## 解法
### 思路
- 思考过程：
  - 题目要求的是从起始位置到终点，能在起点待的最晚时间
  - 因为题目中有火势蔓延的情况，也就意味着每个火焰可以到达的位置都会有一个火焰到达的时间`x`
  - 如果等待时间为`t`，人员到达某个位置的最短时间是`y`，那么实际人员到达该位置的时间就是`t + y`，只要这个时间小于`x`，就说明人员可以到达该位置而不用担心火势
  - 所以就可以先做一次bfs，将每个火焰可以到达的位置的到达时间记录下来
  - 然后因为等待时间是呈单调性的（即如果`t`为最晚的等待时间，那么所有小于该时间的值都一定可以使人员到达终点，而所有大于该时间的值都一定不能到达），那么就可以通过二分查找的方式查找这个`t`值
  - 查找的方式就是通过从起点开始的bfs，带上查找的`t`值来判断到达终点的路径上的时间是否符合`t + y < x`
- 算法过程：
  - 初始化二维数组`matrix`，为了在之后判断是否能走通的计算过程中方便计算，可以将其初始化为int最大值
  - 从火源开始bfs，计算火势到达位置的时间，记录在`matrix`中
  - 然后初始化二分的头尾指针
    - 头：0
    - 尾：`matrix`矩阵的长度和元素长度的乘积
  - 初始化一个变量`ans`，用来暂存可能正确的等待时间，初始值为-1，代表不可能的状态
  - 通过判断确定二分查找的位置是否符合，判断的方式就是通过从起点开始的bfs
    - 如果符合，就将头指针设置为mid + 1，同时记录目前符合要求的时间到`ans`变量上
    - 如果不符合，就将尾指针设置为mid - 1
  - 二分查找结束后，如果`ans`大于等于初始的尾坐标值，那么就返回`10^9`，说明无论等待多长时间都可以到达终点
### 代码
```java
class Solution {
    private int[][] grid, matrix, dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
    private int m, n;
    public int maximumMinutes(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.matrix = new int[m][n];
        this.grid = grid;
        for (int[] arr : matrix) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        bfs();

        int head = 0, tail = m * n, ans = -1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if (check(mid)) {
                ans = mid;
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return ans >= m * n ? 1000000000 : ans;
    }

    private void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] memo = new boolean[m][n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int t = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int x = arr[0], y = arr[1];
                for (int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] != 0 || memo[nx][ny]) {
                        continue;
                    }

                    memo[nx][ny] = true;
                    matrix[nx][ny] = t;
                    queue.offer(new int[]{nx, ny});
                }
            }
            t++;
        }
    }

    private boolean check(int stay) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] memo = new boolean[m][n];
        queue.offer(new int[]{0, 0});
        memo[0][0] = true;

        int t = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();

            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int x = arr[0], y = arr[1];

                for (int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx == m - 1 && ny == n - 1 && matrix[nx][ny] >= t + stay) {
                        return true;
                    }

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == 2 || memo[nx][ny] || matrix[nx][ny] <= t + stay) {
                        continue;
                    }

                    memo[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }

            t++;
        }

        return false;
    }
}
```