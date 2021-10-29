# [LeetCode_240_搜索二维矩阵II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/)
## 解法
### 思路
dfs+记忆化
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length, c = matrix[0].length;
        return dfs(matrix, r , c, 0, 0, target, new boolean[r][c]);
    }
    
    private boolean dfs(int[][] matrix, int r, int c, int x, int y, int target, boolean[][] memo) {
        if (x < 0 || x >= r || y < 0 || y >= c || matrix[x][y] > target || memo[x][y]) {
            return false;
        }
        
        if (matrix[x][y] == target) {
            return true;
        }
        
        memo[x][y] = true;
        
        return dfs(matrix, r, c, x + 1, y, target, memo) ||
                dfs(matrix, r, c, x, y + 1, target, memo);
    }
}
```
## 解法二
### 思路
dfs+记忆化+减枝
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length, c = matrix[0].length;
        return dfs(matrix, r , c, 0, 0, target, new boolean[r][c]);
    }

    private boolean dfs(int[][] matrix, int r, int c, int x, int y, int target, boolean[][] memo) {
        if (x < 0 || x >= r || y < 0 || y >= c || matrix[x][y] > target || memo[x][y]) {
            return false;
        }

        if (matrix[x][y] == target || matrix[x][c - 1] == target || matrix[r - 1][y] == target) {
            return true;
        }

        memo[x][y] = true;

        boolean result = false;

        if (matrix[r - 1][y] > target) {
            result = dfs(matrix, r, c, x + 1, y, target, memo);
        }

        if (result) {
            return true;
        }

        return dfs(matrix, r, c, x, y + 1, target, memo);
    }
}
```
## 解法三
### 思路
- 从矩阵的右上角开始搜索
- 在这个位置的左边，所有元素都比它小，所以如果此时该值比target小，那么这一行就不用考虑了，所以就下移
- 此外，如果当前元素比target大，那么他的左边就可能存在目标值，所以就左移
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length,
            r = 0, c = col - 1;
        
        while (r < row && c >= 0) {
            int num = matrix[r][c];
            if (num == target) {
                return true;
            } else if (num > target) {
                c--;
            } else {
                r++;
            }
        }
        
        return false;
    }
}
```
# [LeetCode_496_下一个更大元素I](https://leetcode-cn.com/problems/next-greater-element-i/)
## 解法
### 思路
单调栈：
- 遇到求某个数之后最大值的情况，第一反应就是单调栈
- 准备一个map，用来记录答案
- 准备一个stack，用来做单调栈运算的容器
- 从数组结尾开始向前遍历，如果栈中不为空，且栈顶元素比当前元素大，则弹出栈顶元素，并做循环判断，直到不符合情况为止
- 出栈操作结束后，记录当前元素和栈顶元素的映射关系，如果栈为空，就记录-1，同时将当前遍历到的元素压入栈中
- 遍历结束后，一次遍历nums1中的元素，并放入一个结果数组中返回
### 代码
```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> mapping = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            int num = nums2[i];
            while (!stack.isEmpty() && num > stack.peek()) {
                stack.pop();
            }

            mapping.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = mapping.get(nums1[i]);
        }

        return ans;
    }
}
```
# [LeetCode_301_删除无效的括号](https://leetcode-cn.com/problems/remove-invalid-parentheses/)
## 解法
### 思路
dfs+减枝
- 首先确定有效括号的含义，其含义就是整个字符串的左、右括号数量相等，且任何时刻从左到右计算，左括号都不少于右括号
- 然后遍历字符串，计算计算出左右括号需要删除的最少括号数
- 最后进行dfs深度搜索
  - 搜索过程中在每个字符出现时，有2种情况
    - 删除当前字符
    - 不删除当前字符
  - 选择删除的时候，又要对应记录是删除了那种字符，是左还是右括号
  - 在遍历同时需要注意需要减枝的几种情况
    - 如果选择不删除当前字符，而该字符已不是有效括号，则终止之后的遍历
    - 如果当前剩余的字符数小于需要删除的最小字符数，那么终止之后的遍历
    - 如果当前字符和之前的字符相同，那么就不用再做删减的动作了，因为当前的删减和前一个相同字符的删减，得到的字符都是一样的，所以无需再多做一次删减的动作，直接跳过删减操作即可，但还是要记录当前字符保留的情况，所以需要对当前字符的进行计数，并判断是否已经不符合规则要求
### 代码
```java
class Solution {
public List<String> removeInvalidParentheses(String s) {
        int lr = 0, rr = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                lr++;
            } else if (s.charAt(i) == ')'){
                if (lr == 0) {
                    rr++;
                } else {
                    lr--;
                }
            }
        }

        List<String> ans = new ArrayList<>();
        dfs(s, 0, 0, 0, lr, rr, ans);
        return ans;
    }

    private void dfs(String s, int index, int lc, int rc, int lr, int rr, List<String> ans) {
        if (lr == 0 && rr == 0) {
            if (isValid(s)) {
                ans.add(s);
            }

            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (i != index && s.charAt(i) == s.charAt(i - 1)) {
                if (s.charAt(i) == '(') {
                    lc++;
                }

                if (s.charAt(i) == ')') {
                    rc++;
                }



                if (lc < rc) {
                  return;
                }

                continue;
            }

            if (lr + rr > s.length() - i) {
                return;
            }

            if (lr > 0 && s.charAt(i) == '(') {
                dfs(s.substring(0, i) + s.substring(i + 1), i, lc, rc, lr - 1, rr, ans);
            }

            if (rr > 0 && s.charAt(i) == ')') {
                dfs(s.substring(0, i) + s.substring(i + 1), i, lc, rc, lr, rr - 1, ans);
            }

            if (s.charAt(i) == '(') {
                lc++;
            }

            if (s.charAt(i) == ')') {
                rc++;
            }

            if (lc < rc) {
                return;
            }
        }
    }

    private boolean isValid(String s) {
        int lc = 0, rc = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                lc++;
            } else if (s.charAt(i) == ')'){
                rc++;
            }

            if (lc < rc) {
                return false;
            }
        }

        return lc == rc;
    }
}
```
# [LeetCode_231_2的幂](https://leetcode-cn.com/problems/power-of-two/)
## 解法
### 思路
- 循环判断是否能被2整除，并缩小2倍，直到为1
- 需要特判为0的情况
### 代码
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }

        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_46_全排列](https://leetcode-cn.com/problems/permutations-ii/solution/)
## 解法
### 思路
回溯
- 使用布尔数组记录当前元素是否已经使用
- 回溯也是基于布尔数组进行的更新
- 防重复的策略则是：
  - 首先将数组进行排序，保证相同的数字放在一起
  - 然后在回溯的过程中，保证相同元素的获取顺序是从左到右依次拿取的，这样就能保证这些相同数字只能组成通过一种方式获取到
### 代码
```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backTrack(nums, new boolean[nums.length], new LinkedList<>(), ans);
        return ans;
    }

    private void backTrack(int[] nums, boolean[] memo, LinkedList<Integer> arr, List<List<Integer>> ans) {
        if (arr.size() == nums.length) {
            ans.add(new ArrayList<>(arr));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (memo[i] || (i > 0 && nums[i] == nums[i - 1] && !memo[i - 1])) {
                continue;
            }
            
            arr.addLast(nums[i]);
            memo[i] = true;
            backTrack(nums, memo, arr, ans);
            memo[i] = false;
            arr.removeLast();
        }
    }
}
```
# [LeetCode_869_重新排序得到2的幂](https://leetcode-cn.com/problems/reordered-power-of-2/)
## 解法
### 思路
回溯
### 代码
```java
class Solution {
public boolean reorderedPowerOf2(int n) {
        List<Integer> nums = new ArrayList<>();
        while (n != 0) {
            nums.add(n % 10);
            n /= 10;
        }
        return dfs(nums, 0, 0, new boolean[nums.size()]);
    }
    
    private boolean dfs(List<Integer> nums, int count, int num, boolean[] memo) {
        if (count == nums.size()) {
            return isValid(num);
        }
        
        for (int i = 0; i < nums.size(); i++) {
            if (memo[i]) {
                continue;
            }

            if (num == 0 && nums.get(i) == 0) {
                continue;
            }
            
            num = num * 10 + nums.get(i);
            memo[i] = true;
            boolean result = dfs(nums, count + 1, num, memo);
            if (result) {
                return true;
            }
            num /= 10;
            memo[i] = false;
        }
        
        return false;
    }

    private boolean isValid(int num) {
        if (num == 0) {
            return false;
        }

        while (num != 1) {
            if (num % 2 != 0) {
                return false;
            }

            num /= 2;
        }

        return true;
    }
}
```
## 解法二
### 思路
- 因为是2的整数次幂，且范围是10的9次方，所以在该范围里，就有32个数的可能
- 通过对这32个数的拆分，并对每一个位上的数字做计数统计，就知道了这32个数的统计数组
- 然后拿者32个数组与当前n所组成的计数数组作比较，如果有相等的，就是2的整数次幂
### 代码
```java
class Solution {
    public boolean reorderedPowerOf2(int n) {
        int[] arr = initArr(n);
        for (int i = 0; i < 31; i++) {
            if (Arrays.equals(arr, initArr(1 << i))) {
                return true;
            }
        }
        
        return false;
    }
    
    private int[] initArr(int n) {
        int[] arr = new int[10];
        while (n != 0) {
            arr[n % 10]++;
            n /= 10;
        }
        return arr;
    }
}
```
# [LeetCode_335_路径交叉](https://leetcode-cn.com/problems/self-crossing/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_282_给表达式添加运算符]()
## 解法
### 思路

### 代码
```java

```