# Contest_1_独一无二的出现次数
## 题目
给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。

如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。

示例 1：
```
输入：arr = [1,2,2,1,1,3]
输出：true
解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
```
示例 2：
```
输入：arr = [1,2]
输出：false
```
示例 3：
```
输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
输出：true
```
提示：
```
1 <= arr.length <= 1000
-1000 <= arr[i] <= 1000
```
## 解法
### 思路
- 使用map计数
- 将map的values放入set去重
- 比较map和set的个数是否相等
### 代码
```java
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return new HashSet<>(map.values()).size() == map.size();
    }
}
```
# Contest_2_尽可能使字符串相等
## 题目
给你两个长度相同的字符串，s 和 t。

将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。

用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。

如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。

如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。

示例 1：
```
输入：s = "abcd", t = "bcdf", cost = 3
输出：3
解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
```
示例 2：
```
输入：s = "abcd", t = "cdef", cost = 3
输出：1
解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
```
示例 3：
```
输入：s = "abcd", t = "acde", cost = 0
输出：1
解释：你无法作出任何改动，所以最大长度为 1。
```
提示：
```
1 <= s.length, t.length <= 10^5
0 <= maxCost <= 10^6
s 和 t 都只含小写英文字母。
```
## 解法
### 思路
- 通过下标同时遍历两个字符串
- 遍历过程中，使用如下方程将计算出的字符之间的ASCII码差距放入dp数组
- `dp[i] = dp[i - 1] + Math.abs(s.charAt(i) - t.charAt(i));`
- 基于字符串下标，进行两层嵌套循环：
    - 外层下标`i`代表子串的起始下标
    - 内层下标`j`代表子串的结束下标
- 通过`f(i,j) = dp[j] - dp[i - 1]`来计算当前子串的cost，如果`cost < maxCost` ，记录`max = j - (i - 1)`
- 返回最大值max
### 代码
```java
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int len = s.length(), max = 0;
        int[] dp = new int[len];
        dp[0] = Math.abs(s.charAt(0) - t.charAt(0));
        if (dp[0] <= maxCost) {
            max = 1;
        }

        for (int i = 1; i < s.length(); i++) {
            dp[i] = dp[i - 1] + Math.abs(s.charAt(i) - t.charAt(i));
            if (dp[i] <= maxCost) {
                max = Math.max(max, i + 1);
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = i; j < dp.length; j++) {
                int cost = dp[j] - dp[i - 1];
                if (cost <= maxCost) {
                    max = Math.max(max, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return max;
    }
}
```
# Contest_3_删除字符串中所有相邻重复项II
## 题目
给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，并删除它们，使被删去的字符串的左侧和右侧连在一起。

你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。

在执行完所有删除操作后，返回最终得到的字符串。

本题答案保证唯一。

示例 1：
```
输入：s = "abcd", k = 2
输出："abcd"
解释：没有要删除的内容。
```
示例 2：
```
输入：s = "deeedbbcccbdaa", k = 3
输出："aa"
解释： 
先删除 "eee" 和 "ccc"，得到 "ddbbbdaa"
再删除 "bbb"，得到 "dddaa"
最后删除 "ddd"，得到 "aa"
```
示例 3：
```
输入：s = "pbbcggttciiippooaais", k = 2
输出："ps"
```
提示：
```
1 <= s.length <= 10^5
2 <= k <= 10^4
s 中只含有小写英文字母。
```
## 解法
### 思路
- 初始化变量count用来记录连续相同字符的个数，置为0
- 遍历字符数组
- 基于当前下标元素，与后一个下标的元素进行对比:
    - 如果不相同，就将当前元素append进sb对象中，并将count置为0
    - 如果相同，`count++`，如果`count == k - 1`说明下一个元素和之前遍历的连续k-1个元素将组成一组需要删除的子串，与k-1进行比较的原因是如果遍历到下一个元素，如果下一个元素和当前元素不同，就会进入另一个逻辑分支
- 判断初始字符串长度和sb的长度是否相等：
    - 相等说明已经没有需要删除的子串，可以直接返回
    - 不相等说明可能存在需要删除的子串，递归继续处理
### 代码
```java
class Solution {
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() - 1) {
                sb.append(s, i - count, i + 1);
                continue;
            }

            if (s.charAt(i) == s.charAt(i + 1)) {
                count++;
                if (count == k - 1) {
                    count = 0;
                    i++;
                }
            } else {
                sb.append(s, i - count, i + 1);
                count = 0;
            }
        }

        if (sb.length() == s.length()) {
            return s;
        } else {
            return removeDuplicates(sb.toString(), k);
        }
    }
}
```
## 解法二
### 思路
- 初始化一个栈，栈元素为一个长度为2的数组，第一个元素存字符，第二个元素存连续次数
- 遍历字符数组:
    - 如下情况就往栈里放字符：
        - 栈为空
        - 栈顶元素字符与当前字符不同
        - 栈顶元素字符相同，但小于k - 1
    - 如果当前元素与栈顶元素相同，且栈顶元素的次数为k-1，则将该元素弹出
- 遍历结束，将元素弹出并insert入sb中
### 代码
```java
class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<int[]> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(new int[]{c, 1});
            } else {
                int[] arr = stack.peek();
                if (arr[0] == c ) {
                    if (arr[1] == k - 1) {
                        stack.pop();
                    } else {
                        stack.peek()[1]++;
                    }
                } else {
                    stack.push(new int[]{c, 1});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            int[] arr = stack.pop();
            char c = (char)arr[0];
            int len = arr[1];
            for (int i = 0; i < len; i++) {
                sb.insert(0, c);
            }
        }
        return sb.toString();
    }
}
```
# Contest_4_穿过迷宫的最少移动次数
## 题目
你还记得那条风靡全球的贪吃蛇吗？

我们在一个 n*n 的网格上构建了新的迷宫地图，蛇的长度为 2，也就是说它会占去两个单元格。蛇会从左上角（(0, 0) 和 (0, 1)）开始移动。我们用 0 表示空单元格，用 1 表示障碍物。蛇需要移动到迷宫的右下角（(n-1, n-2) 和 (n-1, n-1)）。

每次移动，蛇可以这样走：
```
如果没有障碍，则向右移动一个单元格。并仍然保持身体的水平／竖直状态。
如果没有障碍，则向下移动一个单元格。并仍然保持身体的水平／竖直状态。
如果它处于水平状态并且其下面的两个单元都是空的，就顺时针旋转 90 度。蛇从（(r, c)、(r, c+1)）移动到 （(r, c)、(r+1, c)）。
如果它处于竖直状态并且其右面的两个单元都是空的，就逆时针旋转 90 度。蛇从（(r, c)、(r+1, c)）移动到（(r, c)、(r, c+1)）。
```
返回蛇抵达目的地所需的最少移动次数。

如果无法到达目的地，请返回 -1。

示例 1：
```
输入：grid = [[0,0,0,0,0,1],
               [1,1,0,0,1,0],
               [0,0,0,0,1,1],
               [0,0,1,0,1,0],
               [0,1,1,0,0,0],
               [0,1,1,0,0,0]]
输出：11
解释：
一种可能的解决方案是 [右, 右, 顺时针旋转, 右, 下, 下, 下, 下, 逆时针旋转, 右, 下]。
```
示例 2：
```
输入：grid = [[0,0,1,1,1,1],
               [0,0,0,0,1,1],
               [1,1,0,0,0,1],
               [1,1,1,0,0,1],
               [1,1,1,0,0,1],
               [1,1,1,0,0,0]]
输出：9
```
提示：
```
2 <= n <= 100
0 <= grid[i][j] <= 1
蛇保证从空单元格开始出发。
```
## 解法
### 思路

### 代码
```java

```