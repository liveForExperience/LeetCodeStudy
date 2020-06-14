# LeetCode_990_等式方程的可满足性
## 题目
给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。

只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 

示例 1：
```
输入：["a==b","b!=a"]
输出：false
解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
```
示例 2：
```
输出：["b==a","a==b"]
输入：true
解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
```
示例 3：
```
输入：["a==b","b==c","a==c"]
输出：true
```
示例 4：
```
输入：["a==b","b!=c","c==a"]
输出：false
```
示例 5：
```
输入：["c==c","b==d","x!=z"]
输出：true
```
提示：
```
1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] 和 equations[i][3] 是小写字母
equations[i][1] 要么是 '='，要么是 '!'
equations[i][2] 是 '='
```
## 解法
### 思路
并查集：
- 根据表达式初始化并查集
- 遍历表达式数组，当代表不等的字符串中的两个字母，在并查集中的结果相等时，返回false
- 否则返回true
### 代码
```java
class Solution {
    public boolean equationsPossible(String[] equations) {
        Dsu dsu = new Dsu(26);
        
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                int x = equation.charAt(0) - 'a';
                int y = equation.charAt(3) - 'a';
                
                dsu.union(x, y);
            }
        }
        
        for (String equation : equations) {
            if (equation.charAt(1) != '=') {
                int x = equation.charAt(0) - 'a';
                int y = equation.charAt(3) - 'a';
                
                if (dsu.find(x) == dsu.find(y)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private class Dsu {
        private int[] parent;
        
        public Dsu(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) { 
                parent[x] = find(parent[x]);
            }
            
            return parent[x];
        }
        
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    } 
}
```
# LeetCode_29_两数相除
## 题目
给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2

示例 1:
```
输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
```
示例 2:
```
输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2
```
提示：
```
被除数和除数均为 32 位有符号整数。
除数不为 0。
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
```
## 失败解法
### 原因
超时
### 思路
- 特例：
    - 被除数为0，结果为0
    - 被除数为int最小值，除数为-1，结果为int最大值
- 循环累减被除数，累加结果值
- 返回结果
### 代码
```java
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean positive = false;
        if ((dividend > 0 && divisor > 0) ||
            (dividend < 0 && divisor < 0)) {
            positive = true;
        }
        
        int result = 0;
        
        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);
        
        while (dividend <= divisor) {
            dividend -= divisor;
            result++;
        }
        
        return positive ? result : -result;
    }
}
```
## 解法
### 思路
在失败解法的基础上，最大化每次减去的值，从而降低时间复杂度
- 每次都减去divisor的2的整数次幂
### 代码
```java
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int result = 0;
        boolean positive = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0;

        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);

        while (dividend <= divisor) {
            int num = divisor;
            int count = 1;
            while (dividend - num <= num) {
                num <<= 1;
                count <<= 1;
            }

            dividend -= num;
            result += count;
        }
        
        return positive ? result : -result;
    }
}
```
# LeetCode_30_串联所有单词的子串
## 题目
给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。

注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。

示例 1：
```
输入：
  s = "barfoothefoobarman",
  words = ["foo","bar"]
输出：[0,9]
解释：
从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
输出的顺序不重要, [9,0] 也是有效答案。
```
示例 2：
```
输入：
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
输出：[]
```
## 解法
### 思路
hashmap：
- 遍历words，将字符串放入map中计数映射
    - key：word字符串
    - value：出现的次数
- 遍历字符串s：
    - 判断当前子串中出现单词是否在map中存在
    - 如果不存在直接跳过，开始判断下一个单词
    - 如果存在，将该单词放入新的map中进行计数，同时判断出现次数是否一致，如果超过就终止，说明不是
    - 遍历子串结束后，判断出现次数是否一致，如果一致就记录起始坐标，否则就跳过
### 代码
```java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return ans;
        }

        int wordLen = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            Map<String, Integer> tmp = new HashMap<>();
            int num = 0;
            while (num < wordNum) {
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                
                if (!map.containsKey(word)) {
                    break;
                }
                
                tmp.put(word, tmp.getOrDefault(word, 0) + 1);
                if (tmp.get(word) > map.get(word)) {
                    break;
                }
                
                num++;
            }
            
            if (num == wordNum) {
                ans.add(i);
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
在解法一的基础上，优化循环比较子字符串的逻辑。将比较的过程分成3种情况：
- 完全匹配：将子串移动一个单词的距离
- 子串中出现不相同的单词：从该单词之后重新开始比较
- 子串中的单词都匹配，但是数目不相同：从起始为止开始减去累加的值，直到个数与要求相同
- 优化的地方：
    - 每次移动的步长为单词长度
    - 通过后两种出现的可能逻辑，加速了判断的过程，减少了重复判断
### 代码
```java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }

        int wordsNum = words.length;
        if (wordsNum == 0) {
            return Collections.emptyList();
        }

        int wordLen = words[0].length();

        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        Map<String, Integer> memo = new HashMap<>();
        for (int i = 0; i < wordLen; i++) {
            int start = i, index = i;
            while (start < s.length() - wordLen * wordsNum + 1) {
                while (index < start + wordLen * wordsNum) {
                    String word = s.substring(index, index + wordLen);
                    memo.put(word, memo.getOrDefault(word, 0) + 1);
                    index += wordLen;
                    if (!map.containsKey(word)) {
                        start = index;
                        memo.clear();
                        break;
                    }

                    if (memo.get(word) > map.get(word)) {
                        while (memo.get(word) > map.get(word)) {
                            String headWord = s.substring(start, start + wordLen);
                            memo.put(headWord, memo.get(headWord) - 1);
                            start += wordLen;
                        }
                        break;
                    }
                }

                if (index == start + wordLen * wordsNum) {
                    ans.add(start);
                    String word = s.substring(start, start + wordLen);
                    memo.put(word, memo.get(word) - 1);
                    start += wordLen;
                }
            }
            
            memo.clear();
        }
        
        return ans;
    }
}
```
# LeetCode_31_下一个排列
## 题目
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。
```
以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
```
## 解法
### 思路
- 从数组尾部向前遍历，找到第一组长度为2，相邻的升序子序列nums[i]和nums[i - 1]
- 将子序列nums[i - 1]与它右边数中，最小的比它大的数进行交换，再将nums[i]及到数组结尾的所有数转换成升序
### 代码
```java
class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i >= 1 && nums[i] <= nums[i - 1]) {
            i--;
        }
        
        if (i >= 1) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i - 1]) {
                j--;
            }
            
            swap(i - 1, j, nums);
        }
        
        reserve(i, nums);
    }
    
    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    private void reserve(int i, int[] nums) {
        int j = nums.length - 1;
        while (i < j) {
            swap(i, j, nums);
            i++;
            j--;
        }
    }
}
```
# LeetCode_32_最长有效括号
## 题目
给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

示例 1:
```
输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"
```
示例 2:
```
输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"
```
## 失败解法
### 原因
超时，时间复杂度O(N^2)
### 思路
栈
- 两层循环，确定字符串子串的范围
    - 外层确定子串的起始位置
    - 内层确定长度为偶数，也就是步长为2
- 内层使用栈：
    - 当字符为`(`时，压入栈
    - 当字符为`)`时，判断栈顶是否为`(`，如果时，弹出栈
    - 其他情况判断当前字符串为非法的字符串子串
- 内层遍历完一次以后，如果是合法的，比较一次字符串长度
### 代码
```java
class Solution {
    public int longestValidParentheses(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j+=2) {
                if (isValid(s.substring(i, j))) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        
        return ans;
    }
    
    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (!stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        
        return stack.isEmpty();
    }
}
```
## 解法
### 思路
动态规划：
- `dp[i]`：dp中的第i个下标对应字符串第i个字符，dp数组代表以当前字符为结尾的字符串的最大合规子串
- 初始化：所有元素为0
- 状态转移方程：
    - `s[i]`对应元素为`(`，一定不是合规字符串，不考虑
    - `s[i]`对应元素为`)`：
        - 如果`s[i - 1]`为`(`，那么这两个字符串可以组成一个合规字符串，长度为2，同时再考虑`dp[i - 2]`的值，将这组之前的合规字符串长度考虑进去就可以了，`dp[i] = i >= 2 ? dp[i - 2] : 0 + 2`
        - 如果`s[i - 1]`为`)`，那么如果是合规的字符串，可以划分成几部分：
            - `s[i - 1]`组成的合规字符串，长度就是`dp[i - 1]`
            - `s[i]`和`s[i - dp[i - 1] - 1]`两个字符组成的合规字符串，如果组成了就是2
            - `s[i - dp[i - 1] - 2]`为结尾的合规子串长度，`dp[i - dp[i - 1] - 2]`
### 代码
```java
class Solution {
    public int longestValidParentheses(String s) {
        int len = s.length(), ans = 0;
        int[] dp = new int[len];

        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                
                ans = Math.max(dp[i], ans);
            }
        }
        
        return ans;
    }
}
```
# LeetCode_37_解数独
## 题目
编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：
```
数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
空白格用 '.' 表示。
```
Note:
```
给定的数独序列只包含数字 1-9 和字符 '.' 。
你可以假设给定的数独只有唯一解。
给定数独永远是 9x9 形式的。
```
## 解法
### 思路
回溯，有状态类
- 定义3个记忆集合：
    - rowMemo：记录当前行使用的数字
    - colMemo：记录当前列使用的数字
    - boxMemo：记录当前小方块中使用的数字
- 回溯过程：
    - 从0行0列开始：
    - 如果当前方块为`.`，开始循环9个数字，判断当前数字是否可以放置
        - 如果可以，放置数字，继续递归
        - 如果不可以，继续循环数字
    - 递归返回后，如果solve变量为flase，说明当前选择无法最终获得结果，将记忆集合中的数字去除，进行下一个数字选择
    - 如果当前方块为数字，且solve为false，直接开始递归
### 代码
```java
class Solution {
    private char[][] board;
    private int[][] rowMemo, colMemo, boxMemo;
    private boolean solve;

    public void solveSudoku(char[][] board) {
        this.board = board;
        this.rowMemo = new int[9][10];
        this.colMemo = new int[9][10];
        this.boxMemo = new int[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    put(Integer.parseInt(String.valueOf(board[i][j])), i, j);
                }
            }
        }

        backTrack(0, 0);
    }

    private void backTrack(int row, int col) {
        if (solve) {
            return;
        }
        
        if (board[row][col] == '.') {
            for (int num = 1; num <= 9; num++) {
                if (couldPut(num, row, col)) {
                    put(num, row, col);
                    putNext(row, col);

                    if (!solve) {
                        remove(num, row, col);
                    }
                }
            }
        } else {
            putNext(row, col);
        }
    }

    private boolean couldPut(int num, int row, int col) {
        return rowMemo[row][num] + colMemo[col][num] + boxMemo[row / 3 * 3 + col / 3][num] == 0;
    }

    private void put(int num, int row, int col) {
        rowMemo[row][num]++;
        colMemo[col][num]++;
        boxMemo[row / 3 * 3 + col / 3][num]++;
        board[row][col] = (char) (num + '0');
    }

    private void remove(int num, int row, int col) {
        rowMemo[row][num]--;
        colMemo[col][num]--;
        boxMemo[row / 3 * 3 + col / 3][num]--;
        board[row][col] = '.';
    }

    private void putNext(int row, int col) {
        if (row == 8 && col == 8) {
            solve = true;
        } else {
            if (col == 8) {
                backTrack(row + 1, 0);
            } else {
                backTrack(row, col + 1);
            }
        }
    }
}
```
# LeetCode_1300_转变数组后最接近目标值的数组和
## 题目
给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。

如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。

请注意，答案不一定是 arr 中的数字。

示例 1：
```
输入：arr = [4,9,3], target = 10
输出：3
解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
```
示例 2：
```
输入：arr = [2,3,5], target = 10
输出：5
```
示例 3：
```
输入：arr = [60864,25176,27249,21296,20204], target = 56803
输出：11361
```
提示：
```
1 <= arr.length <= 10^4
1 <= arr[i], target <= 10^5
```
## 解法
### 思路
枚举：
- 求数组和
- 如果和小于等于target，返回数组最大值
- 求数组平均值avg
- 求以当前平均值为value的和sum
- 在sum < target的情况下循环，目的是逼近最优解：
    - 求avg + 1情况下的值
    - 如果该值大于等于target，就和之前的sum进行比较，如果绝对值更小，就取该值，否则取另一个值
    - 如果还是比target小，就继续逼近
### 代码
```java
class Solution {
    public int findBestValue(int[] arr, int target) {
        int len = arr.length, sum = 0, max = Integer.MIN_VALUE;
        for (int num : arr) {
            sum += num;
            max = Math.max(max, num);
        }
        
        if (sum <= target) {
            return max;
        }
        
        int avg = target / len;
        sum = sum(arr, avg);
        
        while (sum < target) {
            int tmp = sum(arr, avg + 1);
            if (tmp >= target) {
                return target - sum <= tmp - target ? avg : avg + 1;
            }
            sum = tmp;
            avg++;
        }
        
        return 0;
    }
    
    private int sum(int[] arr, int avg) {
        int sum = 0;
        for (int num : arr) {
            sum += Math.min(num, avg);
        }
        return sum;
    }
}
```