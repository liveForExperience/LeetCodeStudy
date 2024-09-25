# [LeetCode_350_两个数组的交集II](https://leetcode.cn/problems/intersection-of-two-arrays-ii/)
## 解法
### 思路
二分查找
### 代码
```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int n1 = nums1.length, n2 = nums2.length, i2 = 0;
        for (int i = 0; i < n1; i++) {
            int num = nums1[i];
            int index = binarySearch(nums2, num, i2, n2 - 1);
            if (index != -1) {
                i2 = index + 1;
                list.add(nums2[index]);
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private int binarySearch(int[] arr, int target, int l, int r) {
        while (l <= r) {
            int mid = (l + r) >> 1;
            int num = arr[mid];

            if (num < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        if (l >= arr.length) {
            return -1;
        }

        return arr[l] == target ? l : -1;
    }
}
```
# [LeetCode_633_平方数之和](https://leetcode.cn/problems/sum-of-square-numbers/)
## 解法
### 思路
二分查找
### 代码
```java
class Solution {
    public boolean judgeSquareSum(int c) {
        for (long i = 0; i * i <= c; i++) {
            long l = i, r = c;

            while (l <= r) {
                long mid = (r - l) / 2 + l;
                long num = (long) i * i + mid * mid;

                if (num == c) {
                    return true;
                } else if (num < c) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return false;
    }
}
```
# [LeetCode_1254_统计封闭岛屿的数目](https://leetcode.cn/problems/number-of-closed-islands/)
## 解法
### 思路
dfs
- 这套题比较常规
- 逻辑过程
- 遍历grid二维数组，如果当前坐标元素是0，那么开始做dfs搜索，搜索的返回值是布尔值，代表当前开始搜索的水域是否符合题目要求，如果符合就累加1
- 在dfs的过程中，将搜索过的坐标元素翻转成1，因为搜索过的坐标无需再搜索，同时可以通过翻转成1，方便后续逻辑凭借这个值进行减枝
- 搜索的退出条件即：
  - 当前坐标元素为0的同时，坐标在任意的边界位置，这就代表当前水域是不符合题目要求的，返回false作为结果
- 在常规搜索逻辑中
  - 首先初始化一个变量ans，初始值为true
  - 基于当前坐标，搜索4个可能搜索方向，通过元素是否为1来进行减枝，并将搜索结果与当前ans值相与，获得结果
  - 四个方向必须全部搜索完毕，目的是将整个可能水域都探索完，并完成翻转，方便下一个可能水域的搜索
  - 最后将判断的结果返回到递归的上一层即可
### 代码
```java
class Solution {
    private int[][] grid;
    private int r, c;
    private final int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public int closedIsland(int[][] grid) {
        this.r = grid.length;
        this.c = grid[0].length;
        this.grid = grid;
        int ans = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }

                if (dfs(i, j)) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private boolean dfs(int x, int y) {
        if ((x == 0 || x == r - 1 || y == 0 || y == c - 1) && grid[x][y] == 0) {
            return false;
        }

        grid[x][y] = 1;
        boolean ans = true;
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx < 0 || nx >= r || ny < 0 || ny >= c || grid[nx][ny] == 1) {
                continue;
            }
            
            ans &= dfs(nx, ny);
        }
        
        return ans;
    }
}
```
# [LeetCode_1855_下标对中的最大距离](https://leetcode.cn/problems/maximum-distance-between-a-pair-of-values/)
## 解法
### 思路
二分查找
### 代码
```java
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            int index = binarySearch(nums2, nums1[i], i, nums2.length - 1);

            if (index == -1) {
                continue;
            }

            ans = Math.max(index - i, ans);
        }

        return ans;
    }

    private int binarySearch(int[] arr, int target, int l, int r) {
        while (l <= r) {
            int mid = (r - l) / 2 + l;

            int num = arr[mid];

            if (num >= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        if (l - 1 >= arr.length || l - 1 < 0) {
            return -1;
        }

        if (arr[l - 1] < target) {
            return -1;
        }

        return l - 1;
    }
}
```