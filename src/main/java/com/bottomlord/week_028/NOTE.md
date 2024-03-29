# LeetCode_816_模糊坐标
## 题目
我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。

原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。

最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。

示例 1:
```
输入: "(123)"
输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
示例 2:
输入: "(00011)"
输出:  ["(0.001, 1)", "(0, 0.011)"]
解释: 
0.0, 00, 0001 或 00.01 是不被允许的。
示例 3:
输入: "(0123)"
输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
示例 4:
输入: "(100)"
输出: [(10, 0)]
解释: 
1.0 是不被允许的。
```
提示:
```
4 <= S.length <= 12.
S[0] = "(", S[S.length - 1] = ")", 且字符串 S 中的其他元素都是数字。
```
## 解法
### 思路
将字符串分成两部分进行枚举：
- 过程是3重循环：
    - 外部循环确定切分字符串的位置
    - 在外层确定两个部分可能存在的所有字符串可能
    - 剩下两层用来嵌套组装成结果要求的字符串
- 外层寻找可能的过程中，需要特别处理的几种情况：
    - 字符串首个字符为0：
        - 如果长度为1，直接存
        - 如果尾字符不是0，可以在首个字符后加`.`
        - 如果尾字符是0，则不能称为有效数字，例如`01230`
    - 字符串首字符不是0：
        - 尾字符串是0，则不能加`.`，直接存
        - 尾字符串不是0，则需要遍历所有加`.`的可能并保存
### 代码
```java
class Solution {
    public List<String> ambiguousCoordinates(String S) {
        int len = S.length();
        List<String> ans = new ArrayList<>();

        for (int i = 1; i < len - 2; i++) {
            List<String> lefts = find(S.substring(1, i + 1));
            List<String> rights = find(S.substring(i + 1, len - 1));

            for (String left : lefts) {
                for (String right : rights) {
                    ans.add("(" + left + ", " + right + ")");
                }
            }
        }

        return ans;
    }

    private List<String> find(String str) {
        List<String> list = new ArrayList<>();
        if (str.charAt(0) == '0') {
            if (str.length() > 1) {
                if (str.charAt(str.length() - 1) != '0') {
                    list.add("0." + str.substring(1));
                }
            } else {
                list.add(str);
            }
        } else {
            if (str.length() == 1 || str.charAt(str.length() - 1) == '0') {
                list.add(str);
            } else {
                for (int i = 1; i < str.length(); i++) {
                    list.add(str.substring(0, i) + "." + str.substring(i));
                }
                list.add(str);
            }
        }
        return list;
    }
}
```
# LeetCode_433_最小基因变化
## 题目
一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。

假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。

例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。

与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。

现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。

注意:
```
起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
所有的目标基因序列必须是合法的。
假定起始基因序列与目标基因序列是不一样的。
```
示例 1:
```
start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

返回值: 1
```
示例 2:
```
start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

返回值: 2
```
示例 3:
```
start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

返回值: 3
```
## 解法
### 思路
bfs：
- 初始化变量:
    - `queue`：驱动bfs，将`start`压入
    - `set`：保存生成过的字符串，添加`start`
    - `level`：暂存改变字符串的次数，也就是bfs的层数
- 循环：
    - 退出条件：
        - 队列为空：返回-1，说明所有可能遍历过了，没有匹配的
        - 循环过程中变换成了要求的字符串，返回`level`
    - 过程：
        - 将每一层的所有字符串遍历一遍
        - 如果当前字符串和目标的相同，返回
        - 每个字符串的每个不同字符都依次替换成`A,T,G,C`，并判断是否符合基因库和是否已经生成过，如果都符合就放入队列
        - 进行下一个循环
### 代码
```java
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        int level = 0;

        queue.offer(start);
        bankSet.add(start);
        
        char[] genes = new char[]{'A', 'T', 'G', 'C'};

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                String str = queue.poll();
                if (str == null) {
                    continue;
                }

                if (Objects.equals(str, end)) {
                    return level;
                }

                for (int i = 0; i < str.length(); i++) {
                    char[] cs = str.toCharArray();
                    for (int j = 0; j < 4; j++) {
                        if (cs[i] != genes[j]) {
                            cs[i] = genes[j];
                        }

                        String tmp = new String(cs);

                        if (!set.contains(tmp) && bankSet.contains(tmp)) {
                            set.add(tmp);
                            queue.offer(tmp);
                        }
                    }
                }
            }

            level++;
        }

        return -1;
    }
}
```
# LeetCode_60_第k个排列
## 题目
给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
```
"123"
"132"
"213"
"231"
"312"
"321"
给定 n 和 k，返回第 k 个排列。
```
说明：
```
给定 n 的范围是 [1, 9]。
给定 k 的范围是[1,  n!]。
```
示例 1:
```
输入: n = 3, k = 3
输出: "213"
```
示例 2:
```
输入: n = 4, k = 9
输出: "2314"
```
## 解法
### 思路
回溯：
- 将整个排列组合定义成一个多叉树，每一层都是n!个节点，而每个子树都是(n - 1)!个节点
- 先通过遍历生成阶乘的每一位对应的值，生成数组`arr[n + 1]`
- 初始化一个布尔数组，用来保存使用过的数字，因为在每一层的dfs递归中，都会从1开始从小到大循环所有数的可能，所以如果这个数被使用过，那么就用这个布尔数组来标识
- 然后开始dfs
    - 递归的时候可以通过层数，到`arr`中找到对应的组合数
    - 开始从1开始从小到大循环所有排列的可能
        - 如果布尔数组中使用过当前数，就跳过
        - 如果小于`k`，就将排列数从`k`中减掉，继续当前层的循环
        - 如果大于等于`k`，说明这个子树中有第`k`个数，层数加1递归下去，同时将当前选择的数的布尔值标记为真，以及将当前值放入list中
    - 退出条件就是层数与n值相同，代表所有的排列都已经找过了
- 将list中的值按顺序拼接，因为是从最高位开始搜索的。
### 代码
```java
class Solution {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i<= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        boolean[] memo = new boolean[n + 1];
        List<Integer> list = new ArrayList<>();
        dfs(0, list, n, k, factorial, memo);
        
        StringBuilder sb = new StringBuilder();
        for (Integer num : list) {
            sb.append(num);
        }
        return sb.toString();
    }

    private void dfs(int index, List<Integer> list, int n, int k, int[] factorial, boolean[] memo) {
        if (index == n) {
            return;
        }
        
        int count = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (memo[i]) {
                continue;
            }
            
            if (k > count) {
                k -= count;
                continue;
            }
            
            memo[i] = true;
            list.add(i);
            dfs(index + 1, list, n, k, factorial, memo);
        }
    }
}
```
## 解法二
### 思路
使用链表模拟dfs的过程：
- 生成阶乘数组`factorial`
- 将所有n个数按从小到大的顺序放入链表`list`中
- 循环n次，从`n - 1`开始递减循环到0
    1. 计算`k / factorial[i]`的商，这个商相当于代表了k能容纳几个`factorial[i]`的阶乘数
    2. 通过商到`list`中找对应的元素作为当前位的值累加到字符串中，并把这个值从`list`中删掉
    3. 将k减去`商 * factorial[i]`，相当于当前循环走完了`商 * factorial[i]`步，还剩`k - 商 * factorial[i]`步，需要下个循环继续算
- 注意，需要在开头对k进行减1，原因是这个解法中的k其实可以代表从第一个`[1-n]`的序列开始到第k个元素需要走的步数，所以是`k - 1`
### 代码
```java
class Solution {
    public String getPermutation(int n, int k) {
        k--;
        
        int[] factorial = new int[n];
        factorial[0] = 1;

        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factorial[i];
            sb.append(list.remove(index));
            k -= index * factorial[i];
        }
        
        return sb.toString();
    }
}
```
# LeetCode_394_字符串解码
## 题目
给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

示例:
```
s = "3[a]2[bc]", 返回 "aaabcbc".
s = "3[a2[c]]", 返回 "accaccacc".
s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
```
## 解法
### 思路
栈：
初始化变量：
    - 栈`strs`：用来保存下次`[`出现时已经出现过的字符串，栈顶的元素不需要被作为下一个中括号中被乘的内容，但需要在下次的一组`[`和`]`出现后与被乘后的字符串拼接
    - 栈`nums`：用来暂存转换成倍数的数字
    - StringBuilder(内存优化)变量`str`，这个变量保存的是下次碰到`]`时需要被`num`乘的字符串部分
    - int变量`num`，在出现第一个`[`的时候放入`nums`中，在最近一个`]`出现时弹出
- 遍历字符数组
    - 如果是字母就累加到`ans`中
    - 如果是数字就转为int，并加到`num`中
    - 如果是`[`，将`str`和`num`放入对应的栈中，同时变量重置
    - 如果是`]`，将`strs`和`nums`出栈，并拼接字符串`strs`的栈顶 + `num` * `str`
- 遍历结束，返回`str.toString()`
### 代码
```java
class Solution {
    public String decodeString(String s) {
        Stack<String> strs = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        StringBuilder str = new StringBuilder();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') {
                strs.push(str.toString());
                nums.push(num);
                num = 0;
                str = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int time = nums.pop();
                for (int i = 0; i < time; i++) {
                    tmp.append(str);
                }
                str = new StringBuilder((strs.isEmpty() ? "" : strs.pop()) + tmp);
            } else if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            } else {
                str.append(c);
            }
        }
        return str.toString();
    }
}
```
## 解法二
### 思路
递归：
- 与栈的解题思路类似，区别在于：
    - 通过`[`和`]`作为递归的进入和退出条件
    - 不用栈而是用一个变量`num`来记录倍数
- 在遍历字符串时：
    - 如果遇到数字，就记录`num`
    - 如果遇到字符，就拼接到`str`
    - 如果遇到`]`，将当前拼接的`str`和当前`]`的下标返回
    - 如果遇到`[`
        - 进入递归
        - 在这个逻辑分支中获取递归返回的字符串以及`[...]`的结尾下标
        - 通过返回的字符串和暂存的`num`来拼接出`num[...]`组合代表的内容
- 遍历结束，返回`str`
### 代码
```java
class Solution {
    public String decodeString(String s) {
        return dfs(s, 0)[0];
    }

    private String[] dfs(String s, int index) {
        int num = 0;
        StringBuilder str = new StringBuilder();

        while (index < s.length()){
            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (Character.isLetter(c)) {
                str.append(c);
            } else if ('[' == c) {
                String[] returns = dfs(s, index + 1);
                index = Integer.parseInt(returns[1]);
                while (num > 0) {
                    str.append(returns[0]);
                    num--;
                }
            } else {
                return new String[]{str.toString(), String.valueOf(index)};
            }
            index++;
        }

        return new String[]{str.toString()};
    }
}
```
# LeetCode_718_最长重复子数组
## 题目
给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

示例 1:
```
输入:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
输出: 3
解释: 
长度最长的公共子数组是 [3, 2, 1]。
```
说明:
```
1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100
```
## 解法
### 思路
动态规划：
- `dp[i][j]`：代表从`A[i]`和`B[j]`开始的这一组元素，有多少个相同的字符
- base case：`dp[A.length][B.length] = 0`，这个值方便动态转移方程从两个数组最后的元素开始计算，如果它们相等，就是`0 + 1 = 1`
- 状态转移方程：如果`A[i] == B[j]`的情况下，`dp[i][j] = dp[i + 1][j + 1] + 1`，代表这一对元素组合开始的重复字符串长度等于后一组的长度 + 1
- 过程：嵌套循环遍历所有A和B数组元素的组合可能，起始是从两个数组的最后个元素开始i遍历
### 代码
```java
class Solution {
    public int findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length + 1][B.length + 1];
        int ans = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = B.length - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
}
```
# LeetCode_477_汉明距离总和
## 题目
两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。

计算一个数组中，任意两个数之间汉明距离的总和。

示例:
```
输入: 4, 14, 2

输出: 6

解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
所以答案为：
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
```
注意:
```
数组中元素的范围为从 0到 10^9。
数组的长度不超过 10^4。
```
## 失败解法
### 失败原因
超时
### 思路
- 嵌套循环遍历所有两两数字的组合
- 两两异或
- 求异或值中为1的位的个数，通过`(num - 1) & num`将`num`的最低位1清零，直到`num == 0`，计算清零次数
- 累加每个循环中计算出的次数
### 代码
```java
class Solution {
    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int num = nums[i] ^ nums[j];
                while (num != 0) {
                    num = (num - 1) & num;
                    ans++;
                }
            }
        }
        
        return ans;
    }
}
```
## 解法
### 思路
- 可以通过分别计算数的每一位上1和0的个数，然后通过`num1 * num0`来计算所有数字之间的汉明距离
- 通过`num & 1`来计算最低位上的值是1还是0
### 代码
```java
class Solution {
    public int totalHammingDistance(int[] nums) {
        int ans = 0, n = nums.length;
        int[] arr = new int[32];

        for (int num : nums) {
            int index = 0;
            while (num > 0) {
                arr[index] += num & 1;
                num >>= 1;
                index++;
            }
        }
        
        for (int num : arr) {
            ans += num * (n - num);
        }
        
        return ans;
    }
}
```
# LeetCode_611_有效三角形的个数
## 题目
给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。

示例 1:
```
输入: [2,2,3,4]
输出: 3
解释:
有效的组合是: 
2,3,4 (使用第一个 2)
2,3,4 (使用第二个 2)
2,2,3
```
注意:
```
数组长度不超过1000。
数组里整数的范围为 [0, 1000]。
```
## 解法
### 思路
- 三角形三条边需要满足：`a + b > c && a + c > b && b + c > a`
- 使用枚举，三层循环判断所有元素组合，并计数
### 代码
```java
class Solution {
    public int triangleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int a = nums[i], b = nums[j], c = nums[k];
                    if (a + b > c && a + c > b && b + c > a) {
                        ans++;
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
二分查找：
- 将数组排序，满足二分查找的前提条件
- 因为从下到大排序，所以只要满足`a + b > c`，则`a + c > b`和`b + c > a`的条件一定满足
- 使用两层循环确定`a,b`
- 通过二分法找到`c`的位置
- 记录`a,b,c`对应的下标`i,j,k`
- 那么满足`a + b > c`中c的可能个数就是`k - j`个
- 将个数累加后，在循环结束后返回
### 代码
```java
class Solution {
    public int triangleNumber(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int c = nums[i] + nums[j], head = j + 1, tail = nums.length - 1;
                while (head <= tail) {
                    int mid = head + (tail - head) / 2;
                    if (nums[mid] >= c) {
                        tail = mid - 1;
                    } else {
                        head = mid + 1;
                    }
                }

                ans += head - j - 1;
            }
        }
        
        return ans;
    }
}
```
## 解法三
### 思路
使用双指针
- 和二分法中的方法一样，先排序数组，使得只需考虑`a + b > c`
- `a,b,c`同样对应数组下标`i,j,k` 
- 双指针代表的是`j,k`
- 一重循环来确定`i`，即三条边的第一条边
- `j`的初始值为`i + 1`，`k`的初始值为`i + 2`
- 然后在内层的嵌套循环，获取`j`和`k`，判断`b`和`c`与`a`是否匹配要求，如果符合就先移动`k`，然后累加可能值。 
- 这个可能值其实就是在i和k确定的前提下，j的可能个数
- 在`k`不符合要求后，再递增`j`，直到所有可能都不符合
- 需要注意考虑`a == 0`的情况，因为在这种情况下`a + b > c`恒不成立
### 代码
```java
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int k = i + 2;

            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k++;
                    ans += k - j - 1;
                }
            }
        }
        
        return ans;
    }
}
```