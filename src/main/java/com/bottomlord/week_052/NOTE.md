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
# LeetCode_87_扰乱字符串
## 题目
给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。

下图是字符串 s1 = "great" 的一种可能的表示形式。

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。

例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
```
    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
```
同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
```
    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
```
给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。

示例 1:
```
输入: s1 = "great", s2 = "rgeat"
输出: true
```
示例 2:
```
输入: s1 = "abcde", s2 = "caebd"
输出: false
```
## 解法
### 思路
- 扰乱规则可以理解为：
    - 原字符串S1和扰乱字符串S2两者每次会被拆分成两部分，a和b
    - S1和S2的A和B两部分会有两种状态
        - `S1[a] = S2[a]`并且`S1[b] = S2[b]`（切分后每一部分都是一样的）
        - `S1[a] = S2[b]`并且`S1[b] = S2[a]`（切分后，两部分调换了位置后，互相相等）
- 动态规划：
    - 表达式：`dp[i][j][len]`
        - i：S1的起始坐标
        - j：S2的起始坐标
        - len：a和b两部分的和
        - 整个表达式代表：起始为`i`长度为`j - i`的字符串和起始为`j`长度为`len - (j - i)`的字符串的字符串是否为干扰或者完全相等
    - 初始状态：长度为1的字符串必须相等才能符合题目要求，枚举两个字符串，判断字符是否相等
    - 状态转移方程：
        - 先确定一个中间的划分点k，这个点一定是[1, len - 1]
        - 如果没有交换：`dp[i][j][len] = dp[i][j][k] && dp[i + k][j + k][len - k]`
        - 如果有交换：`dp[i][j][len] = dp[i][j + len - k][k] && dp[i + k][j][len - k]`
    - 返回结果`dp[0][0][len]`
### 代码
```java
class Solution {
    public boolean isScramble(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        if (l1 != l2) {
            return false;
        }
        
        if (l1 == 1) {
            return Objects.equals(s1, s2);
        }
        
        boolean[][][] dp = new boolean[l1][l1][l1 + 1];
        
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l1; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        
        for (int l = 2; l <= l1; l++) {
            for (int i1 = 0; i1 <= l1 - l; i1++) {
                for (int i2 = 0; i2 <= l2 - l; i2++) {
                    for (int k = 1; k < l; k++) {
                        if (dp[i1][i2][k] && dp[i1 + k][i2 + k][l - k]) {
                            dp[i1][i2][l] = true;
                            break;
                        }
                        
                        if (dp[i1][i2 + l - k][k] && dp[i1 + k][i2][l - k]) {
                            dp[i1][i2][l] = true;
                            break;
                        }
                    }
                }
            }
        }
        
        return dp[0][0][l1];
    }
}
```
# LeetCode_91_解码方法
## 题目
一条包含字母 A-Z 的消息通过以下方式进行了编码：
```
'A' -> 1
'B' -> 2
...
'Z' -> 26
给定一个只包含数字的非空字符串，请计算解码方法的总数。
```
示例 1:
```
输入: "12"
输出: 2
解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
```
示例 2:
```
输入: "226"
输出: 3
解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
```
## 失败解法
失败解法
### 思路
回溯
### 代码
```java
class Solution {
    public int numDecodings(String s) {
        Set<String> set = new HashSet<>();
        backTrack(s, new StringBuilder(), set);
        return set.size();
    }

    private void backTrack(String s, StringBuilder sb, Set<String> ans) {
        if (s.length() == 0) {
            ans.add(sb.toString());
            return;
        }

        sb.append((char)(s.charAt(0) + 'A' - 1));
        backTrack(s.substring(1), sb, ans);
        sb.deleteCharAt(sb.length() - 1);

        if (s.length() == 1) {
            return;
        }
        
        int num = (Integer.parseInt(s.substring(0, 2)));
        if (num < 1 || num > 26) {
            return;
        }
        
        sb.append((char)(num + 'A' - 1));
        backTrack(s.substring(2), sb, ans);
        sb.deleteCharAt(sb.length() - 1);
    }
}
```
## 解法
### 思路
动态规划：
- `dp[i]`：从`s[0, i]`字符串有多少种解码方式
- 初始化：`dp[0] = 1`
- 状态转移方程：
    - 如果可以和前`s[i]`和`s[i - 1]`可以组成[1,26]之间的数字，那么`dp[i] = dp[i - 1] + dp[i - 2]`。
    - 如果不可以，`dp[i] = dp[i - 1]`
- 返回结果：`dp[len - 1]`
### 代码
```java
class Solution {
    public int numDecodings(String s) {
        if (s == null) {
            return 0;
        }

        int len = s.length();
        if (len == 0) {
            return 0;
        }
        
        char[] cs = s.toCharArray();
        if (!isValid(cs[0] - '0')) {
            return 0;
        }
        
        if (len == 1) {
            int num = cs[0] - '0';
            return isValid(num) ? 1 : 0;
        }
        
        int[] dp = new int[len + 1];
        dp[1] = 1;
        dp[2] = (isValid(cs[1] - '0') ? dp[1] : 0) + (isValid(twoCharNum(cs[0], cs[1])) ? 1 : 0);
        
        for (int i = 3; i <= len; i++) {
            if (isValid(cs[i - 1] - '0')) {
                dp[i] = dp[i - 1];
            }
            
            if (isValid(cs[i - 2] - '0') && isValid(twoCharNum(cs[i - 2], cs[i - 1]))) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[len];
    }
    
    private boolean isValid(int num) {
        return num > 0 && num <= 26;
    }

    private int twoCharNum(char a, char b) {
        return 10 * (a - '0') + (b - '0');
    }
}
```
# LeetCode_97_交错字符串
## 题目
给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。

示例 1:
```
输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
输出: true
```
示例 2:
```
输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
输出: false
```
## 解法
### 思路
回溯
### 代码
```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }
        
        return backTrack(0, s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray());
    }

    private boolean backTrack(int i1, char[] cs1, int i2, char[] cs2, int i3, char[] cs3) {
        if (i3 == cs3.length) {
            return true;
        }
        
        boolean flag = false;
        if (i1 != cs1.length && cs1[i1] == cs3[i3]) {
            flag = backTrack(i1 + 1, cs1, i2, cs2, i3 + 1, cs3);
        }
        
        if (flag) {
            return true;
        }
        
        if (i2 != cs2.length && cs2[i2] == cs3[i3]) {
            return backTrack(i1, cs1, i2 + 1, cs2, i3 + 1, cs3);
        }
        
        return false;
    }
}
```