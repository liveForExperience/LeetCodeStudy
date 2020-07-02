# LeetCode_215_数组中的第K个最大元素
## 题目
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:
```
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```
示例 2:
```
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
```
说明:
```
你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
```
## 解法
### 思路
基于快排
- 快排在确定一个元素的位置时，左右两边的元素虽然没有排序，但已经被确定的分成了两部分
- 然后只根据index对于K的位置：
    - 如果index < k，那么就在index的右边继续搜
    - 如果index > k，那么就在index的左边继续搜
    - 如果index == k，就直接返回
### 代码
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSearch(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSearch(int[] nums, int start, int end, int index) {
        int pivot = partition(nums, start, end);

        if (pivot == index) {
            return nums[pivot];
        } else {
            return pivot < index ? quickSearch(nums, pivot + 1, end, index) : quickSearch(nums, start, pivot - 1, index);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int num = nums[start];
        
        while (start < end) {
            while (start < end && nums[end] >= num) {
                end--;
            }
            nums[start] = nums[end];
            
            while (start < end && nums[start] <= num) {
                start++;
            }
            nums[end] = nums[start];
        }
        
        nums[start] = num;
        return start;
    }
}
```
## 解法二
### 思路
大顶堆
### 代码
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int num : nums) {
            queue.offer(num);
        }

        for (int i = 0; i < k - 1; i++) {
            queue.poll();
        }
        
        return queue.peek();
    }
}
```
## 解法三
### 思路
手写堆实现
### 代码
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int[] arr = new int[nums.length + 1];
        System.arraycopy(nums, 0, arr, 1, nums.length);
        build(arr);

        int heapSize = arr.length;
        for (int i = arr.length - 1; i >= arr.length - k + 1; i--) {
            swap(arr, 1, i);
            heapSize--;
            heapfiy(arr, 1, heapSize);
        }

        return arr[1];
    }

    private void build(int[] arr) {
        for (int i = arr.length / 2; i > 0; i--) {
            heapfiy(arr, i, arr.length);
        }
    }

    private void heapfiy(int[] arr, int index, int len) {
        int largest = index, left = index * 2, right = index * 2 + 1;

        if (left < len && arr[largest] < arr[left]) {
            largest = left;
        }

        if (right < len && arr[largest] < arr[right]) {
            largest = right;
        }

        if (largest != index) {
            swap(arr, index, largest);
            heapfiy(arr, largest, len);
        }
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
```
# LeetCode_81_最大矩形
## 题目
给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

示例:
```
输入:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
输出: 6
```
## 解法
### 思路
降低维度：
- 三层遍历：
    - 最外层确定矩阵的行
    - 中间层确定矩阵的列，并在这一层中计算当前行中，以当前列为结尾的最大宽度
    - 最内层：
        - 以中间层确定的最大宽度为基础
        - 从当前行开始，遍历当前列的所有行，并计算遍历到的所有最大宽度中的最小值，这个值就是从起始行到现在行可以用的最大宽度
        - 然后用计算出来的宽度乘以两行的间隔行数，获得矩阵的面积
        - 再用面积与暂存的面积最大值作比较，判断是否要更新
- 遍历结束，返回暂存的面积最大值
### 代码
```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }

        int col = matrix[0].length, ans = 0;
        int[][] dp = new int[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == '1') {
                    dp[r][c] = c == 0 ? 1 : dp[r][c - 1] + 1;

                    int width = dp[r][c];
                    for (int i = r; i >= 0; i--) {
                        width = Math.min(width, dp[i][c]);
                        ans = Math.max(width * (r - i + 1), ans);
                    }
                }
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
- 计算每一列上1的个数，如果连续就累加，如果断开就从0开始
- 同时将每一行看作一个二维的柱状图，每一列上的累加值就是一个柱高度
- 使用栈来求每一行的柱状图中能够生成的最大矩形面积：
    - 将柱状图的横坐标作为矩形的两条平行边
    - 而另外两条平行边则由横坐标范围内矩阵的最小值确定
    - 所以反过来想，当你开始计算矩形面积的时候，意味着那些高的矩阵的值是用不到的
    - 如果在遍历这个柱状图的值时，使用一个栈：
        - 当前元素比栈顶元素大或等于的时候，将元素下标压入栈中：此时栈中的元素一定是升序升序排列的，栈顶的元素是最大值
        - 当栈不为空且当前元素比栈顶元素小时，就开始计算比当前元素大的柱状体能生成的最大面积：
            - 弹出栈顶元素，获取宽度，或者可以称为柱状图当前柱状体高度
            - 再获取高度：
                - 如果此时栈已经空了，那么当前高度就是遍历到的坐标值(坐标是从0初始，计算的宽度是在遍历到的坐标的左边，所以值一样)
                - 如果此时栈没空，那么宽度就是遍历到的高度到下一个栈顶元素坐标之间的距离
### 代码
```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length, col = matrix[0].length, ans = 0;
        int[] dp = new int[col];

        for (char[] chars : matrix) {
            for (int c = 0; c < col; c++) {
                dp[c] = chars[c] == '1' ? dp[c] + 1 : 0;
            }

            ans = Math.max(ans, getMaxRectangle(dp));
        }

        return ans;
    }

    private int getMaxRectangle(int[] dp) {
        int max = 0, len = dp.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= len; i++) {
            int cur = i == len ? -1 : dp[i];
            while (!stack.isEmpty() && dp[stack.peek()] > cur) {
                int index = stack.pop();
                int width = dp[index];

                if (stack.isEmpty()) {
                    max = Math.max(width * i, max);
                } else {
                    max = Math.max(width * (i - stack.peek() - 1), max);
                }
            }

            stack.push(i);
        }
        return max;
    }
}
```
## 解法三
### 思路
- 计算矩形的三个要素：
    - 高度h：遍历行数来确定高度
    - 宽度的左起点l：以0为起点，以当前点为终点，能找到的最右的0的位置
    - 宽度的右起点r：以当前点为起点，以当前行结尾元素为终点，能找到的最左的0的位置
- 通过计算`h * (r - l)`获得矩形面积
- 动态规划：
    - 定义三个dp：
        - hDp[i]：当前行第i列向第0行趋近的最大的高度  
        - lDp[i]：当前行第i列向第0列趋近过程中，最远能搜索到的为1的坐标，这个坐标再与之前遍历过的所有行的当前列的dp比较，找到最大值（离当前坐标最近的1）作为当前值
        - rDp[i]：当前行第i列向第`n - 1`列趋近过程中，最远能搜索到的为1的坐标，这个坐标再与之前遍历过的所有行的当前列的dp比较，找到最小值（离当前坐标最近的1）作为当前值
    - 初始化：
        - `lDp[i] = 0`
        - `rDp[i] = n - 1`
    - 状态转移方程：
        - `hDp[i] = matrix[r][i] == '1' ? hDp[i] + 1 : 0` 
        - lDp[i]:
            - matrix[r][i] == '1'：`lDp[i] = max(lDp[i], curL)`
            - matrix[r][i] != '1'：`lDp[i] = 0; curL = i + 1)`（如果矩阵值不为1，就不可能形成矩形，dp恢复成默认的0，是为了使下一行判断时从新开始确定这个左边界）
        - rDp[i]：
            - matrix[r][i] == '1'：`rDp[i] = min(rDp[i], curR)`
            - matrix[r][i] != '1'：`rDp[i] = n - 1;curL = i - 1`（如果矩阵值不为1，置为(n - 1)的目的和处理lDP时一致
    - 返回结果：每确定完一行，就遍历当前行的每一列，获得对应的三个dp中的数的乘积，更新暂存的最大值，并最终返回
### 代码
```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length, col = matrix[0].length, ans = 0;
        int[] hDp = new int[col],
              lDp = new int[col],
              rDp = new int[col];

        Arrays.fill(rDp, col - 1);

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                hDp[c] = matrix[r][c] == '1' ? hDp[c] + 1 : 0;
            }

            int curL = 0;
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == '1') {
                    lDp[c] = Math.max(curL, lDp[c]);
                } else {
                    lDp[c] = 0;
                    curL = c + 1;
                }
            }

            int curR = col - 1;
            for (int c = col - 1; c >= 0; c--) {
                if (matrix[r][c] == '1') {
                    rDp[c] = Math.min(curR, rDp[c]);
                } else {
                    rDp[c] = col;
                    curR = c - 1;
                }
            }

            for (int c = 0; c < col; c++) {
                ans = Math.max(ans, hDp[c] * (rDp[c] - lDp[c] + 1));
            }
        }

        return ans;
    }
}
```