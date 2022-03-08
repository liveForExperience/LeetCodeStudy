# [LeetCode_593_有效的正方形](https://leetcode-cn.com/problems/valid-square/)
## 解法
### 思路
- 一个有效正方形的判断条件：4条边相等且对角线相等
- 枚举所有的4个点的排列状态，然后判断是否有任意一个排列能够组成正方形
- 枚举的个数是4!，所以是有限的
- 枚举的方式则通过回溯来处理
### 代码
```java
class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = new int[][]{p1, p2, p3, p4};

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (checkDup(p[i], p[j])) {
                    return false;
                }
            }
        }

        return backTrack(p, 0);
    }

    private boolean checkDup(int[] x, int[] y) {
        return x[0] == y[0] && x[1] == y[1];
    }

    private boolean backTrack(int[][] arr, int index) {
        if (index == 4) {
            return check(arr);
        }

        boolean result = false;
        for (int i = index; i < 4; i++) {
            swap(arr, index, i);
            result |= backTrack(arr, index + 1);
            swap(arr, index, i);
        }
        return result;
    }

    private boolean check(int[][] arr) {
        return dist(arr[0], arr[1]) > 0 &&
                dist(arr[0], arr[1]) == dist(arr[1], arr[2]) &&
                dist(arr[1], arr[2]) == dist(arr[2], arr[3]) &&
                dist(arr[2], arr[3]) == dist(arr[3], arr[0]) &&
                dist(arr[0], arr[2]) == dist(arr[1], arr[3]);
    }

    private void swap(int[][] arr, int x, int y) {
        int[] tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    private int dist(int[] x, int[] y) {
        return (x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]);
    }
}
```
# [LeetCode_2055_蜡烛之间的盘子](https://leetcode-cn.com/problems/plates-between-candles/)
## 失败解法
### 原因
超时
### 思路
暴力
### 代码
```java
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int len = queries.length;
        int[] ans = new int[len];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = count(s, queries[i][0], queries[i][1]);
        }

        return ans;
    }

    private int count(String s, int left, int right) {
        int l = left - 1, r = right + 1;
        for (int i = left; i <= right; i++) {
            if (s.charAt(i) == '|') {
                l = i;
                break;
            }
        }

        for (int i = right; i >= left; i--) {
            if (s.charAt(i) == '|') {
                r = i;
                break;
            }
        }

        if (l == left - 1 || r == right + 1 || l >= r) {
            return 0;
        }

        int count = 0;
        for (int i = l; i <= r; i++) {
            if (s.charAt(i) == '*') {
                count++;
            }
        }

        return count;
    }
}
```
## 解法
### 思路
- 遍历数组candies，从左到右找到蜡烛的坐标
- 初始化两个数组，这两个数组的作用：
  - 一个数组left用来记录以当前坐标为左边界，右边（包括当前坐标）最近的一个蜡烛的坐标位置
  - 一个数组right用来记录以当前坐标为右边界，左边（包括当前坐标）最近的一个蜡烛的坐标位置
- 初始化数组sums，这个数组用来记录盘子的前缀和，为了方便计算，数组长度在candies基础上+1
- 最后遍历queries数组
  - 根据数组元素的第一和第二元素，在left和right中找到左右边界的蜡烛位置
  - 在根据left和right的位置，在sums中找到盘子的个数
### 代码
```java
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int len = s.length();
        int[] left = new int[len],
              right = new int[len],
              sums = new int[len + 1];

        char[] cs = s.toCharArray();
        int pre = -1, count = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '|') {
                pre = i;
            }

            right[i] = pre;

            if (cs[i] == '*') {
                count++;
            }

            sums[i + 1] = count;
        }

        pre = cs.length;
        for (int i = cs.length - 1; i >= 0; i--) {
            if (cs[i] == '|') {
                pre = i;
            }

            left[i] = pre;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = left[queries[i][0]], r = right[queries[i][1]];
            if (l >= r) {
                ans[i] = 0;
            } else {
                ans[i] = sums[r] - sums[l];
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_2190_数组中紧跟key之后出现最频繁的数字](https://leetcode-cn.com/problems/most-frequent-number-following-key-in-an-array/https://leetcode-cn.com/problems/most-frequent-number-following-key-in-an-array/)
## 解法
### 思路
暴力
### 代码
```java
class Solution {
    public int mostFrequent(int[] nums, int key) {
        int max = 0, ans = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != key) {
                continue;
            }
            
            if (i + 1 == nums.length) {
                continue;
            }
            
            int target = nums[i + 1], count = 1;
            for (int j = i + 2; j < nums.length; j++) {
                if (nums[j] == target && nums[j - 1] == key) {
                    count++;
                }
            }
            
            if (count > max) {
                max = count;
                ans = target;
            }
        }
        
        return ans;
    }
}
```