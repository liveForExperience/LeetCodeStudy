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
# [LeetCode_2194_Excel表中某个范围内的单元格](https://leetcode-cn.com/problems/cells-in-a-range-on-an-excel-sheet/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public List<String> cellsInRange(String s) {
        List<String> ans = new ArrayList<>();
        int row1 = s.charAt(0) - 'A',
            col1 = s.charAt(1) - '0',
            row2 = s.charAt(3) - 'A',
            col2 = s.charAt(4) - '0';
        
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                ans.add(String.valueOf((char)(i + 'A')) + j);
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
优化
### 代码
```java
class Solution {
    public List<String> cellsInRange(String s) {
        List<String> ans = new ArrayList<>();
        for (int i = s.charAt(0); i <= s.charAt(3); i++) {
            for (int j = s.charAt(1); j <= s.charAt(4); j++) {
                ans.add(new String(new char[]{(char)i, (char)j}));
            }
        }
        return ans;
    }
}
```
# [LeetCode_798_得分最高的最小轮调](https://leetcode-cn.com/problems/smallest-rotation-with-highest-score/)
## 失败解法
### 原因
超时
### 思路
暴力
### 代码
```java
class Solution {
  public int bestRotation(int[] nums) {
    int len = nums.length;
    int[] indexes = new int[len];
    for (int i = 0; i < indexes.length; i++) {
      indexes[i] = i;
    }

    int max = 0, ans = 0;
    for (int time = 0; time < len; time++) {
      int count = doCount(nums, indexes);
      if (count > max) {
        max = count;
        ans = time;
      }
      arrIncrease(indexes);
    }

    return ans;
  }

  private int doCount(int[] nums, int[] indexes) {
    int len = nums.length, count = 0;
    for (int i = 0; i < len; i++) {
      if (nums[i] <= indexes[i]) {
        count++;
      }
    }

    return count;
  }

  private void arrIncrease(int[] nums) {
    int len = nums.length;
    for (int i = 0; i < nums.length; i++) {
      nums[i] = (nums[i] + len - 1) % len;
    }
  }
}
```
## 解法
### 思路
- 通过观察可以发现，左边是随着轮调逐次减1的
- 而坐标与值之间的关系有如下几种情况
  - 没有移动时，val <= i的，那么随着轮调，那么随着轮调，i会逐步减小，直到van > i之前，还是有count的
  - 没有移动时，val > i的，那么随着轮调，不会得到count值
  - 如上2种情况，count总值都不会+1，除非一种情况，那就是0转换为n-1时候，因为n-1一定是最大值，所以此时count总值一定会+1
- 所以根据如上情况，如果能统计除有count到没count的临界点，val == i的时候，那么统计这个点上有多少值，那么当轮调一次，这部分的count就应该被减去，同时因为轮调必定会发生0到n-1的转换，所以也需要在总值上+1
- 那么可以：
  - 将没有轮调的初始count值计算出来
  - 将临界点count值的集合计算出来，每一个坐标对应当前轮调步数对应的临界点count值
  - 得到临界点集合后，遍历这个集合，要从1开始，代表从第1次轮调的状态开始
  - 然后在初始值的基础上，不断减去当前轮调后，不再可以计算count值的个数，同时+1，这个1就是0转变为n-1时产生的count值1
  - 遍历过程中不断比较最大值，看看走哪第几次轮调的时候，count值是最大的，暂存这个最大值和坐标值直至轮调结束
- 遍历结束，返回坐标值
### 代码
```java
public class Solution {
  public int bestRotation(int[] nums) {
    int len = nums.length, val = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] <= i) {
        val++;
      }
    }

    int[] steps = new int[len];
    for (int i = 0; i < steps.length; i++) {
      if (nums[i] <= i) {
        steps[i - nums[i]]++;
      } else {
        steps[len - (nums[i] - i)]++;
      }
    }

    int max = val, ans = 0;
    for (int i = 1; i < nums.length; i++) {
      val = val - steps[i - 1] + 1;
      if (val > max) {
        max = val;
        ans = i;
      }
    }

    return ans;
  }
}
```
# [LeetCode_2049_统计最高分的节点数目](https://leetcode-cn.com/problems/count-nodes-with-the-highest-score/)
## 解法
### 思路
- 根据parents生成邻接表，通过该邻接表可以做到遍历
- 遍历所有要去除边的节点
- 在该节点计算2大部分数据
  - 所有子树的个数
  - 自己的父节点所在子树的个数：`count = n - 1 - sum(child_count)`
- 将这些总数相乘后，查看是否和当前的最大值相等，如果相等就累加个数，否则就更新最大值，并把个数更新为1
- 注意：
  - 相乘值可能溢出，用64位整数
  - 使用记忆化搜索进行减枝
### 代码
```java
class Solution {
    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < parents.length; i++) {
            graph[parents[i]].add(i);
        }

        long max = 0;
        int ans = 0;
        Integer[] memo = new Integer[n];
        for (int i = 0; i < n; i++) {
            long count = dfs(graph, i, n, memo);
            if (count == max)  {
                ans++;
            } else if (count > max) {
                ans = 1;
                max = count;
            }
        }

        return ans;
    }

    private long dfs(List<Integer>[] graph, int node, int n, Integer[] memo) {
        List<Integer> children = graph[node];

        long ans = 1, count = 0;
        for (Integer child : children) {
            Integer m = memo[child];
            int childCount;
            if (m == null) {
               childCount = innerDfs(graph, child, memo);
               memo[child] = childCount;
            } else {
                childCount = m;
            }
            
            ans *= childCount;
            count += childCount;
        }

        if (node != 0) {
            ans *= (n - 1 - count);
        }

        return ans;
    }

    private int innerDfs(List<Integer>[] graph, int node, Integer[] memo) {
        int count = 1;

        List<Integer> children = graph[node];
        for (Integer child : children) {
            Integer m = memo[child];
            int childCount;
            if (m == null) {
                childCount = innerDfs(graph, child, memo);
                memo[child] = childCount;
            } else {
                childCount = m;
            }
            
            count += childCount;
        }

        return count;
    }
}
```
## 解法二
### 思路
简化解法一的代码
- 其实在内层dfs的时候，就已经可以同时去计算去除边的情况了
- 在dfs递归返回的过程中依次判断所有的节点
- 这样代码就可以减少很大一部分
### 代码
```java
class Solution {
    private int ans = 0, n;
    private long max = 0;
    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            graph[parents[i]].add(i);
        }
        
        dfs(graph, 0);
        
        return ans;
    }
    
    private int dfs(List<Integer>[] graph, int node) {
        List<Integer> children = graph[node];
        long cur = 1;
        int count = 0;
        for (Integer child : children) {
            int childCount = dfs(graph, child);
            cur *= childCount;
            count += childCount;
        }
        
        if (node != 0) {
            cur *= (n - count - 1);
        }
        
        if (max == cur) {
            ans++;
        } else if (max < cur) {
            max = cur;
            ans = 1;
        }
        
        return count + 1;
    }
}
```
# [LeetCode_393_UTF8编码验证](https://leetcode-cn.com/problems/utf-8-validation/)
## 解法
### 思路
位运算模拟
### 代码
```java
class Solution {
    public boolean validUtf8(int[] data) {
        int count = 0, mask1 = (1 << 7), mask2 = (1 << 6);
        for (int num : data) {
            if (count == 0) {
                if ((num & mask1) == 0) {
                    continue;
                }

                while ((num & mask1) != 0) {
                    count++;
                    num <<= 1;
                }

                if (count > 4 || count == 1) {
                    return false;
                }
            } else {
                if (!((num & mask1) != 0 && (num & mask2) == 0)) {
                    return false;
                }
            }

            count--;
        }

        return count == 0;
    }
}
```