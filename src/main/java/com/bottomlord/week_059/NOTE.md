# LeetCode_213_打家劫舍II
## 题目
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例1:
```
输入: [2,3,2]
输出: 3
解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
```
示例 2:
```
输入: [1,2,3,1]
输出: 4
解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。
```
## 解法
### 思路
动态规划：
- 因为首尾相连，所以可以将这个环形数组拆分成两部分：
    - 一个不包含头，只包含尾
    - 一个只包含头，不包含尾
- `dp[i]`：从0到i范围内，可以偷到的最大金额
- 状态转移方程：`dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])`：
    - 偷当前的，那么最大值就是`dp[i - 2] + nums[i]`
    - 不偷当前的，那么最大值就是`dp[i - 1]`
- base case：
    - `dp[0] = nums[0]`
    - `i > 0 && i <= 3`：`dp[i] = max(nums)`
- 返回结果：`dp[len - 1]`
### 代码
```java
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        if (len == 1) {
            return nums[0];
        }

        if (len <= 3) {
            Arrays.sort(nums);
            return nums[len - 1];
        }
        
        return Math.max(doRob(Arrays.copyOfRange(nums, 0, len - 1)), doRob(Arrays.copyOfRange(nums, 1, len)));
    }

    private int doRob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[len - 1];
    }
}
```
## 解法二
### 思路
通过状态转移方程发现，状态转移都是依赖前两个dp元素，所以可以通过临时变量来代替整个dp数组
### 代码
```java
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }

        return Math.max(doRob(Arrays.copyOfRange(nums, 0, len - 1)),
                        doRob(Arrays.copyOfRange(nums, 1, len)));
    }

    private int doRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for (int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }

        return cur;
    }
}
```
# LeetCode_214_最短回文串
## 题目
给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。

示例 1:
```
输入: "aacecaaa"
输出: "aaacecaaa"
```
示例 2:
```
输入: "abcd"
输出: "dcbabcd"
```
## 解法
### 思路
- 因为题目只允许在字符串头部插入字符，所以可以从字符串头部开始，找到当前字符串的最大回文串
- 找到字符串最大回文串的方法是:
    - 将原来字符串翻转，得到rev
    - 使用指针同时对应原字符串s和反转字符串rev的起始位置
    - 循环移动指针，循环次数为字符串长度len
    - 判断s从`0`到`n - i`区间得到的字符串是否与rev从`i`到结尾获得的字符串相等
    - i移动的距离，相当于不是s中回文串的字符的个数，所以最早找到两部分相等的坐标位置，就是需要增加字符最少的情况
- 找到后，就直接将rev从0到i区间的字符串拼接到s前面，获得最后的解
### 代码
```java
public class Solution {
    public String shortestPalindrome(String s) {
        int len = s.length();
        String rev = reserve(s);

        for (int i = 0; i < len; i++) {
            if (Objects.equals(s.substring(0, len - i), rev.substring(i))) {
                return rev.substring(0, i) + s;
            }
        }

        return "";
    }

    private String reserve(String s) {
        int head = 0, tail = s.length() - 1;
        char[] cs = s.toCharArray();
        while (head < tail) {
            char c = cs[head];
            cs[head] = cs[tail];
            cs[tail] = c;

            head++;
            tail--;
        }

        return new String(cs);
    }
}
```
## 解法二
### 思路
分治：
- 定义两个指针
- 相向遍历字符串`s`：
    - 如果相等，则同时相向移动
    - 如果不相等，则只移动尾部指针
    - 头部指针最终指向的坐标，其实代表的是，在当前`s`中，从左侧头部开始的，连续的，且能够和右侧开始的，非连续字符组成的最长回文串距离
    - 也就是说，头部指针停下的位置的右侧的字符串，就是必须在头部填充的需要被翻转的子字符串
- 每一层的分治递归，就是通过如上循环，找到肯定需要增加的最小的字符串
- 退出条件就是头部坐标结束的位置就是当前层字符串`s`结尾的位置，说明当前字符串就是回文串
- 然后就返回3部分内容：
    - 第1部分：当前层求得的必须在头部添加的，待翻转的字符串
    - 第2部分：当前层求得的，可能可以称为回文串的字符串部分，这部分要继续递归求解
    - 第3部分：当前层求得的必须在头部添加的，未翻转的字符串
### 代码
```java
class Solution {
    public String shortestPalindrome(String s) {
        int len = s.length();
        int head = 0;
        for (int tail = len - 1; tail >= 0; tail--) {
            if (Objects.equals(s.charAt(head), s.charAt(tail))) {
                head++;
            }
        }
        
        if (head == len) {
            return s;
        }

        StringBuilder sb = new StringBuilder(s.substring(head));
        sb.reverse();
        return sb.append(shortestPalindrome(s.substring(0, head))).append(s.substring(head)).toString();
    }
}
```
# LeetCode_218_天际线问题
## 题目
城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。现在，假设您获得了城市风光照片（图A）上显示的所有建筑物的位置和高度，请编写一个程序以输出由这些建筑物形成的天际线（图B）。

每个建筑物的几何信息用三元组 [Li，Ri，Hi] 表示，其中 Li 和 Ri 分别是第 i 座建筑物左右边缘的 x 坐标，Hi 是其高度。可以保证 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX 和 Ri - Li > 0。您可以假设所有建筑物都是在绝对平坦且高度为 0 的表面上的完美矩形。

例如，图A中所有建筑物的尺寸记录为：[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] 。

输出是以 [ [x1,y1], [x2, y2], [x3, y3], ... ] 格式的“关键点”（图B中的红点）的列表，它们唯一地定义了天际线。关键点是水平线段的左端点。请注意，最右侧建筑物的最后一个关键点仅用于标记天际线的终点，并始终为零高度。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。

例如，图B中的天际线应该表示为：[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]。

说明:
```
任何输入列表中的建筑物数量保证在 [0, 10000] 范围内。
输入列表已经按左 x 坐标 Li  进行升序排列。
输出列表必须按 x 位排序。
输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
```
## 解法
### 思路
分治：
- 思想：
    - 定义基本问题
    - 将问题分解成子问题并递归求解
    - 合并子问题为基本问题
- 分治过程：
    - 退出条件：
        - `buildings`长度为0时，返回空list
        - `buildings`长度为1时，返回单一元素的天际线
    - 递归分治，将天际线分成`left`和`right`两部分(即前`n/2`和后`n/2`)求解并返回
    - 合并两部分：
        - 定义变量：
            - `lLen``rLen`：左右天际线的长度
            - `li``ri`：遍历左右天际线的游标，初始为0
            - `lh``rh`：左右天际线当前遍历到的高度
        - 过程:
            - 如果`li < lLen && ri < rLen`，说明两个天际线有重叠的可能，极端情况是两个天际线完全分开，那么在接下来的处理过程中，左边的天际线会被先处理完
            - 移动天际线对应的游标，比较游标对应的天际线x值大小：
                - `lx < rx`：处理左边的天际线
                - `lx > rx`：处理右边的天际线
                - `lx == rx`：此时就是天际线重叠的情况，同时处理两个天际线
            - 处理天际线的过程:
                - 计算当前要处理的天际线的y轴高度，与另一个天际线暂存的高度作比较，取最大值作为当前这个x轴对应的y轴
                - 然后根据处理的天际线，向右移动1位对应的游标
                - 同时记录这个x和y轴的值，作为当前可能要记录的点
            - 需要记录的情况：
                - 是第一个点
                - 和上一个点的y值不同，如果相同，就代表它们在同一条平行于x轴的直线上，这样的一个线段，只需要取最左边的值，所以之后的值就不需要
            - 在如上`li < lLen && ri < rLen`的循环跳出后，处理剩下没有被处理的天际线，就比如极端情况，两个天际线没有交集，那么循环中就会处理左边的天际线，然后跳出循环后，就会处理剩下的右边的天际线
            - 最后返回结果集合
### 代码
```java
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int len = buildings.length;
        if (len == 0) {
            return new ArrayList<>();
        }

        if (len == 1) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Arrays.asList(buildings[0][0], buildings[0][2]));
            ans.add(Arrays.asList(buildings[0][1], 0));
            return ans;
        }

        List<List<Integer>> leftBuildings = getSkyline(Arrays.copyOfRange(buildings, 0, len / 2));
        List<List<Integer>> rightBuildings = getSkyline(Arrays.copyOfRange(buildings, len / 2, len));

        return merge(leftBuildings, rightBuildings);
    }

    private List<List<Integer>> merge(List<List<Integer>> leftBuildings, List<List<Integer>> rightBuildings) {
        int lLen = leftBuildings.size(), rLen = rightBuildings.size();
        int li = 0, ri = 0;
        List<List<Integer>> output = new ArrayList<>();
        int lh = 0, rh = 0;
        while (li < lLen && ri < rLen) {
            int lx = leftBuildings.get(li).get(0), rx = rightBuildings.get(ri).get(0);
            
            List<Integer> cp;
            if (lx < rx) {
                lh = leftBuildings.get(li).get(1);
                cp = Arrays.asList(lx, Math.max(lh, rh));
                li++;
            } else if (lx > rx) {
                rh = rightBuildings.get(ri).get(1);
                cp = Arrays.asList(rx, Math.max(lh, rh));
                ri++;
            } else {
                lh = leftBuildings.get(li).get(1);
                rh = rightBuildings.get(ri).get(1);
                cp = Arrays.asList(lx, Math.max(lh, rh));
                li++;
                ri++;
            }

            if (output.size() == 0 || !Objects.equals(output.get(output.size() - 1).get(1), cp.get(1))) {
                output.add(cp);
            }
        }

        while (li < lLen) {
            output.add(leftBuildings.get(li));
            li++;
        }
        
        while (ri < rLen) {
            output.add(rightBuildings.get(ri));
            ri++;
        }

        return output;
    }
}
```
# LeetCode_220_存在重复元素III
## 题目
在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。

如果存在则返回 true，不存在返回 false。

示例 1:
```
输入: nums = [1,2,3,1], k = 3, t = 0
输出: true
```
示例 2:
```
输入: nums = [1,0,1,1], k = 1, t = 2
输出: true
```
示例 3:
```
输入: nums = [1,5,9,1,5,9], k = 2, t = 3
输出: false
```
## 解法
### 思路
- 2层循环：
    - 外层确定起始坐标，循环长度位`len - k`
    - 内层确定对比坐标，循环长度位`k`
- 循环体内比较元素值大小，判断是否符合题目要求
- 注意：计算时可能出现int溢出，比较时做一下long类型的强转
### 代码
```java
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= k && i + j < len; j++) {
                if (Math.abs((long)nums[i] - (long)nums[i + j]) <= (long)t) {
                    return true;
                }
            }
        }

        return false;
    }
}
```
## 解法二
### 思路
维护二叉搜索树
- 遍历数组
- 如果是空树就插入元素
- 如果不是空树，就取数：
    - 比当前遍历到的元素大的最小值，判断差值是否小于t
    - 比当前遍历到的严肃小的最大值，判断差值是否大于t
- 如果如上两种情况是真，就说明找到了符合要求的元素，直接返回true
- 如果都不符合，就将当前元素插入树中
- 判断树的大小，如果大于k，就将最早的元素删除
- 通过已有的TreeSet来实现这个二叉搜索树
### 代码
```java

```
# LeetCode_223_矩形面积
## 题目
在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。

每个矩形由其左下顶点和右上顶点坐标表示，如图所示。

Rectangle Area

示例:
```
输入: -3, 0, 3, 4, 0, -1, 9, 2
输出: 45
说明: 假设矩形面积不会超出 int 的范围。
```
## 解法
### 思路
- 定义两个矩形X和Y
- 确定ABCD对应的是最左边的矩形
- 计算两个矩形的总面积
- 判断是否不相交，如果是就直接返回总面积
- 计算相交的上下左右的线段
- 将总面积减去相交面积，并返回
### 代码
```java
class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (A > E) {
            return computeArea(E, F, G, H, A, B, C, D);
        }

        int total = Math.abs(A - C) * Math.abs(B - D) + Math.abs(E - G) * Math.abs(F - H);
        if (B >= H || D <= F || C <= E) {
            return total;
        }

        int up = Math.min(D, H), down = Math.max(B, F), right = Math.min(C, G);
        return total - Math.abs(up - down) * Math.abs(E - right);
    }
}
```
# LeetCode_679_24点游戏
## 题目
你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。

示例 1:
```
输入: [4, 1, 8, 7]
输出: True
解释: (8-4) * (7-1) = 24
```
示例 2:
```
输入: [1, 2, 1, 2]
输出: False
```
注意:
```
除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
```
## 解法
### 思路
回溯
### 代码
```java
public class Solution {
    private static final int TARGET = 24;
    private static final double EPSILON = 1e-6;
    private static final int ADD = 0, MULTI = 1, SUBTRACT = 2, DIVIDE = 3;
    public boolean judgePoint24(int[] nums) {
        return doJudge(Arrays.stream(nums).mapToDouble(x -> x).boxed().collect(Collectors.toList()));
    }

    private boolean doJudge(List<Double> list) {
        if (list.size() == 1) {
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }

                List<Double> tmp = new LinkedList<>();
                for (int k = 0; k < list.size(); k++) {
                    if (k != i && k != j) {
                        tmp.add(list.get(k));
                    }
                }

                for (int k = 0; k < 4; k++) {
                    if (k < 2 && i > j) {
                        continue;
                    }

                    if (k == ADD) {
                        tmp.add(list.get(i) + list.get(j));
                    } else if (k == MULTI) {
                        tmp.add(list.get(i) * list.get(j));
                    } else if (k == SUBTRACT) {
                        tmp.add(list.get(i) - list.get(j));
                    } else {
                        if (list.get(j) < EPSILON) {
                            continue;
                        }

                        tmp.add(list.get(i) / list.get(j));
                    }

                    if (doJudge(tmp)) {
                        return true;
                    }

                    tmp.remove(tmp.size() - 1);
                }
            }
        }

        return false;
    }
}
```
# LeetCode_224_基本计算器
## 题目
实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。

示例 1:
```
输入: "1 + 1"
输出: 2
```
示例 2:
```
输入: " 2-1 + 2 "
输出: 3
```
示例 3:
```
输入: "(1+(4+5+2)-3)+(6+8)"
输出: 23
```
## 解法
### 思路
逆序遍历字符串，栈求解
- 暂存变量：
    - 操作数`operand`，用来辅助计算连续的数字，记录暂时的低位值
    - 位数`n`，用来记录位数
- 逆序遍历字符串
    - 如果是空字符，跳过
    - 如果是数字，通过位数`n`和当前数字，计算操作数`operand`，并累加`n`
    - 如果不是数字：
        - 清零`operand`和`n`
        - 如果是`(`，说明一个完整的括号出现：
            - 计算括号中的值
            - 并弹出之前的最后一个`)`
            - 将计算的结果压入栈中
        - 如果不是`(`，就把字符压入栈中
- 遍历结束后，看位数是否为0，如果不是，说明还有不包含括号的操作数和操作符需要处理，计算获得结果并返回
### 代码
```java
class Solution {
    public int calculate(String s) {
        int operand = 0, n = 0;
        Stack<Object> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                operand = (int) Math.pow(10, n) * (int)(c - '0') + operand;
                n++;
            } else {
                if (n != 0) {
                    stack.push(operand);
                    n = 0;
                    operand = 0;
                }

                if (c == '(') {
                    int result = doCal(stack);
                    stack.pop();
                    stack.push(result);
                } else {
                    stack.push(c);
                }
            }
        }

        if (n != 0) {
            stack.push(operand);
        }

        return doCal(stack);
    }

    private int doCal(Stack<Object> stack) {
        int result = 0;

        if (!stack.isEmpty()) {
            result = (int)stack.pop();
        }

        while (!stack.isEmpty() && (char)stack.peek() != ')') {
            char c = (char) stack.pop();

            if (c == '+') {
                result += (int) stack.pop();
            } else if (c == '-') {
                result -= (int) stack.pop();
            }
        }

        return result;
    }
}
```