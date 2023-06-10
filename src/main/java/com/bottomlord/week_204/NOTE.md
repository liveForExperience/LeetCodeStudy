# [LeetCode_2352_相等行列对](https://leetcode.cn/problems/equal-row-and-column-pairs/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> colMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            colMap.computeIfAbsent(grid[0][i], x -> new ArrayList<>()).add(i);
        }

        int count = 0;
        for (int row = 0; row < n; row++) {
            int start = grid[row][0];
            List<Integer> cols = colMap.get(start);
            if (cols == null) {
                continue;
            }
            
            for (Integer col : cols) {
                boolean flag = true;
                for (int j = 0; j < n; j++) {
                    if (grid[j][col] != grid[row][j]) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
```
# [LeetCode_2611_奶酪和老鼠](https://leetcode.cn/problems/mice-and-cheese/)
## 解法
### 思路
- 先算出第二只老鼠吃所有的奶酪的总得分
- 同时求出第一只老鼠吃当前奶酪与第二只老鼠吃的时候的得分差值
- 为了能得到最大值，其实就是求差值的最大值，差值越大，说明第一只老鼠吃的时候能得到更高的分数
- 然后对差值数组做降序排序
- 遍历排序后的差值数组，累加到之前的总和中作为结果
### 代码
```java
class Solution {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int sum = 0, n = reward1.length;
        Integer[] diffs = new Integer[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = reward1[i] - reward2[i];
            sum += reward2[i];
        }
        
        Arrays.sort(diffs, (x, y) -> y - x);

        for (int i = 0; i < k; i++) {
            sum += diffs[i];
        }
        
        return sum;
    }
}
```
# [LeetCode_2517_礼盒的最大甜蜜度](https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/)
## 解法
### 思路
- 遇到最小值求最大或者最大值求最小，可以直接反应使用二分查找
- 此处对答案，即差值的最小绝对值进行二分查找
- 初始确定答案的最大可能，即[0, max]，max是最小和最大元素差值的绝对值
- 然后在这个区间中进行二分查找，确定一个可能答案
  - 这个可能答案成立的条件：在数组中有至少k个差值大于等于当前值的元素对
  - 所以为了能够在O(N)时间复杂度下获取到这个判断结果，可以先将数组进行排序，然后依次遍历数组元素，将两两差值大于可能答案的元素对个数记录下来
  - 可以使用一个前置值变量，这个前置值代表了两两元素对的前一个较小元素，同时在统计完一对之后将较大值更新为较小值，继续判断
  - 该值可以初始化为int最小值的一半，这样方便计算
- 如果这个目标值成立，那么就往较大区间继续寻找，否则就往较小区间查找
- 注意：取中间值时为了防止java整型向下取整的特性，所以需要额外+1
### 代码
```java
class Solution {
    public int maximumTastiness(int[] prices, int k) {
        Arrays.sort(prices);
        int tail = prices[prices.length - 1] - prices[0], head = 0;
        
        while (head < tail) {
            int mid = (tail - head + 1) / 2 + head;
            
            if (check(prices, mid, k)) {
                head = mid;
            } else {
                tail = mid - 1;
            }
        }
        
        return head;
    }
    
    private boolean check(int[] prices, int target, int k) {
        int pre = Integer.MIN_VALUE / 2, count = 0;
        for (int price : prices) {
            if (price - pre >= target) {
                count++;
                pre = price;
            }
        }
        
        return count >= k;
    }
}
```