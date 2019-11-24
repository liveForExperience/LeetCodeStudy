# LeetCode_932_漂亮数组
## 
对于某些固定的 N，如果数组 A 是整数 1, 2, ..., N 组成的排列，使得：

对于每个 i < j，都不存在 k 满足 i < k < j 使得 A[k] * 2 = A[i] + A[j]。

那么数组 A 是漂亮数组。

给定 N，返回任意漂亮数组 A（保证存在一个）。

示例 1：
```
输入：4
输出：[2,1,4,3]
```
示例 2：
```
输入：5
输出：[3,1,2,5,4]
```
提示：
```
1 <= N <= 1000
```
## 解法
### 思路
- 通过观察可得，公式`A[k] * 2 = A[i] + A[j]`有如下特点：
    - 公式左边部分恒为偶数
    - 公式右边可分为`A[i]`和`A[j]`两部分相加，也就是`left`和`right`
    - 如果希望等式恒不相等，就需要右边部分为奇数，所以可以使`left`为奇数和`right`为偶数
- 分治：
    - 可以将数组对半分成左右两部分，以中间点`i`为分界，左边可以映射成`2 * i - 1`，右边映射成`2 * i`，从而填满整个N
    - 这个过程可以不断地递归分治，直到N为1，不能再分割了为止
    - 递归发生在两次循环的集合生成部分，可以理解为通过递归将N排列成左为奇数右为偶数的序列，然后通过公式再继续计算，从而生成上一层需要的序列
    - 还可以通过memo进行记忆化搜索
### 代码
```java
class Solution {
    public int[] beautifulArray(int N) {
        Map<Integer, int[]> memo = new HashMap<>();
        return recurse(N, memo);
    }

    private int[] recurse(int n, Map<Integer, int[]> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int[] ans = new int[n];

        if (n == 1) {
            ans[0] = 1;
        } else {
            int index = 0;
            for (int i : recurse((n + 1) / 2, memo)) {
                ans[index++] = 2 * i - 1;
            }

            for (int i : recurse(n / 2, memo)) {
                ans[index++] = 2 * i;
            }
        }

        memo.put(n, ans);
        return ans;
    }
}
```
# LeetCode_103_二叉树的锯齿形层次遍历
## 题目
给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
```
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回锯齿形层次遍历如下：

[
  [3],
  [20,9],
  [15,7]
]
```
## 解法
### 思路
- bfs遍历，生成list
- 将list中对应层进行倒置
- 返回结果
### 代码
```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int count = queue.size();
            
            List<Integer> inner = new ArrayList<>();
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                inner.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(inner);
        }

        boolean reserve = false;
        for (List<Integer> list : ans) {
            if (reserve) {
                Collections.reverse(list);
            }
            
            reserve = !reserve;
        }
        
        return ans;
    }
}
```
# LeetCode_648_单词替换
## 题目
在英语中，我们有一个叫做 词根(root)的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。

现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。

你需要输出替换之后的句子。

示例 1:
```
输入: dict(词典) = ["cat", "bat", "rat"]
sentence(句子) = "the cattle was rattled by the battery"
输出: "the cat was rat by the bat"
```
注:
```
输入只包含小写字母。
1 <= 字典单词数 <=1000
1 <=  句中词语数 <= 1000
1 <= 词根长度 <= 100
1 <= 句中词语长度 <= 1000
```
## 解法
### 思路
- 生成字典树：
    - 结构：
        - 节点指针存储字典中字符的所有可能的后一个字符
        - 节点若为dict值的最后一个字符，存整个dict元素值
    - 插入：
        - 将当前元素按字符依次放入字典树中
    - 查找：
        - 遍历单词字符
        - 从root节点开始i，如果next中包含该字符，就继续搜索，直到搜索到的节点的值非空
        - 否则如果next中不包含字符就返回原字符串
- 过程：
    - 使用两个指针，用来确定`sentences`中每个单词的头尾坐标
    - 使用字典出查询并返回符合题目要求的字符
    - 将单子和空格`append`进StringBuilder
    - 返回结果，注意取出左后一个空格
### 代码
```java
class Solution {
    private class TrieNode {
        private String val;
        private Map<Character, TrieNode> next;

        public TrieNode() {
            next = new HashMap<>();
        }
    }

    private TrieNode root = new TrieNode();

    private void insert(String str) {
        TrieNode node = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!node.next.containsKey(c)) {
                node.next.put(c, new TrieNode());
            }
            node = node.next.get(c);
        }
        node.val = str;
    }

    private String get(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (node.val != null) {
                return node.val;
            }

            if (node.next.containsKey(c)) {
                node = node.next.get(c);
            } else {
                return word;
            }
        }
        
        return word;
    }

    public String replaceWords(List<String> dict, String sentence) {
        for (String str : dict) {
            insert(str);
        }
        
        int i = 0, j = 0, len = sentence.length();
        StringBuilder sb = new StringBuilder();
        while (j < len) {
            while (j < len && sentence.charAt(j) != ' ') {
                j++;
            }
            
            sb.append(get(sentence.substring(i, j))).append(" ");
            
            i = ++j;
        }
        
        return sb.toString().substring(0, sb.length() - 1);
    }
}
```
# LeetCode_769_最多能完成排序的块
## 题目
数组arr是[0, 1, ..., arr.length - 1]的一种排列，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。

我们最多能将数组分成多少块？

示例 1:
```
输入: arr = [4,3,2,1,0]
输出: 1
解释:
将数组分成2块或者更多块，都无法得到所需的结果。
例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
```
示例 2:
```
输入: arr = [1,0,2,3,4]
输出: 4
解释:
我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
```
注意:
```
arr 的长度在 [1, 10] 之间。
arr[i]是 [0, 1, ..., arr.length - 1]的一种排列。
```
## 解法
### 思路
- 每一块的最后一个元素的下标一定和块中的最大值相同
- 参数：
    - `max`：最大值
    - `count`：块的数量
- 过程：
    - 遍历数组
    - `max`和当前元素比较，生成最大值
    - 如果`max`和当前元素下标相同，`count++`
- 结果：返回`count`
### 代码
```java
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int count = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                count++;
            }
        }
        return count;
    }
}
```
# LeetCode_森林中的兔子
## 题目
森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。

返回森林中兔子的最少数量。

示例:
```
输入: answers = [1, 1, 2]
输出: 5
解释:
两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
设回答了 "2" 的兔子为蓝色。
此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。

输入: answers = [10, 10, 10]
输出: 11

输入: answers = []
输出: 0
```
说明:
```
answers 的长度最大为1000。
answers[i] 是在 [0, 999] 范围内的整数。
```
## 解法
### 思路
- 如果元素是0，代表没有其他颜色和自己一样，ans++
- 如果元素是1，代表有一只兔子和自己颜色一样，所以2个元素1的值为1组，ans++
- 遍历数组，使用map累计所有元素的出现次数
- 算出当前元素所出现的合理的组数，代表如果元素key的val大于了key + 1，代表有另外一组别的颜色也是相同个数的兔子，需要把那部分的值补全
    - 如果val可以被`key + 1`整除，则直接累加val，代表不需要补充
    - 如果不能被整除，`ans = val + key + 1 - val % (key + 1)`
- 累加后得到结果
### 代码
```java
class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }
        
        for (int key : map.keySet()) {
            if (key == 0) {
                ans += map.get(key);
            } else {
                int val = map.get(key);
                int num = val % (key + 1);
                ans += num == 0 ? val : val + key + 1 - num;
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
- 将数组元素排序
- 使用指针来记录当前元素的下标
- 遍历过程中：
    - 将当前元素累加到结果中
    - 然后开始嵌套循环，目的是将当前元素值num + 1个的元素过滤掉，这些被过滤掉的元素代表是相同颜色的兔子
    - 嵌套循环的退出条件是：
        - 超出数组边界
        - 超出num+ 1的值
        - 元素值变了
- 最后返回结果
### 代码
```java
class Solution {
    public int numRabbits(int[] answers) {
        Arrays.sort(answers);
        int ans = 0;
        for (int i = 0 ; i < answers.length; i++) {
            int num = answers[i], start = i;
            ans += num + 1;
            while (i < answers.length && answers[i] == num && i - start < num + 1) {
                i++;
            }
            i--;
        }
        return ans;
    }
}
```
# LeetCode_946_验证栈序列
## 题目
给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。

示例 1：
```
输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
输出：true
解释：我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
```
示例 2：
```
输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
输出：false
解释：1 不能在 2 之前弹出。
```
提示：
```
0 <= pushed.length == popped.length <= 1000
0 <= pushed[i], popped[i] < 1000
pushed 是 popped 的排列。
```
## 解法
### 思路
- 使用一个栈
- 遍历两个数组
    - 先将`pushed`的元素放入栈中
    - 过程中使用`pop`指针来代表弹出了几个元素
    - 在嵌套循环： 
        - 如果栈不为空，且栈顶元素和`poped`的第`pop`个元素相等，就弹栈
        - 否则就退出循环
- 遍历结束，返回`pop == poped.length`，如果不相等意味着有几个元素没法通过弹栈获得
### 代码
```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int pop = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[pop]) {
                stack.pop();
                pop++;
            }
        }
        
        return pop == popped.length;
    }
}
```
# LeetCode_655_输出二叉树
## 题目
在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
```
行数 m 应当等于给定二叉树的高度。
列数 n 应当总是奇数。
根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。然而，如果两个子树都为空则不需要为它们留出任何空间。
每个未使用的空间应包含一个空的字符串""。
使用相同的规则输出子树。
```
示例 1:
```
输入:
     1
    /
   2
输出:
[["", "1", ""],
 ["2", "", ""]]
```
示例 2:
```
输入:
     1
    / \
   2   3
    \
     4
输出:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
```
示例 3:
```
输入:
      1
     / \
    2   5
   / 
  3 
 / 
4 
输出:
[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
注意: 二叉树的高度在范围 [1, 10] 中。
```
## 解法
### 思路
- 获取树的深度`depth`
- 根据深度获得数组每一行的列数`2 ^ depth + 1`
- 根据列数生成整个矩阵数组，使用空字符串填充
- 递归：
    - 参数：
        - `start`：起始坐标
        - `end`：终止坐标
        - `depth`：深度
    - 退出条件：
        - 节点为空
        - `start > end`
    - 过程：
        - 根据`start`和`end`计算插入的中点
        - 循环当前矩阵行的列，遇到中点时将当前节点值替换
        - 递归下一层：
            - 左：`start = start`，`end = mid - 1`
            - 右：`start = mid + 1`，`end = end`
- 返回矩阵 
### 代码
```java
class Solution {
    public List<List<String>> printTree(TreeNode root) {
        int depth = getDepth(root), len = 0, count = depth;

        while (count-- > 0) {
            len = len * 2 + 1;
        }
        
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < depth; i++) {
            List<String> list = new ArrayList<>(len);
            for (int j = 0; j < len; j++) {
                list.add("");
            }
            ans.add(list);
        }

        dfs(root, 0, len - 1, 0, ans);
    
        return ans;
    }

    private int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
    }

    private void dfs(TreeNode node, int start, int end, int depth, List<List<String>> ans) {
        if (node == null || start > end) {
            return;
        }
        
        int mid = start + (end - start) / 2;
        ans.get(depth).set(mid, Integer.toString(node.val));
        
        dfs(node.left, start, mid - 1, depth + 1, ans);
        dfs(node.right, mid + 1, end, depth + 1, ans);
    }
}
```
# LeetCode_565_数组嵌套
## 题目
索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到并返回最大的集合S，S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。

假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。

示例 1:
```
输入: A = [5,4,0,3,1,6,2]
输出: 4
解释: 
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

其中一种最长的 S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
```
注意:
```
N是[1, 20,000]之间的整数。
A中不含有重复的元素。
A中的元素大小在[0, N-1]之间。
```
## 解法
### 思路
- 变量：
    - 最大值变量`ans`
- 嵌套遍历：
    - 外层遍历数组
        - 初始化个数变量`count`，用来记录当前元素开始，可以嵌套的个数
        - 初始化变量`num`，用来记录上一个元素的值，如果遍历到的元素和最开始的元素相同就终止循环
        - 内层循环结束后，将`ans`和`count`比较，取最大值暂存为`ans`
    - 内层遍历；
        - 使`num = nums[num]`，`count++`
        - 如果`num == nums[num]`就终止循环

### 代码
```java
class Solution {
    public int arrayNesting(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0, num = nums[i];
            do {
                num = nums[num];
                count++;
            } while (num != nums[i]);
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
```
## 解法二
### 思路
在解法一的基础上，如果外层循环的元素，在之前的循环的过程中已经被访问过，那么它一定不会得到最长的解，所以记录这些被访问过的元素就可以减少重复的访问操作。
### 代码
```java
class Solution {
    public int arrayNesting(int[] nums) {
        boolean[] visited = new boolean[nums.length + 1];
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int count = 0, num = nums[i];
                do {
                    num = nums[num];
                    count++;
                    visited[num] = true;
                } while (num != nums[i]);
                
                ans = Math.max(ans, count);
            }
        }
        
        return ans;
    }
}
```
## 解法三
### 思路
观察解法二可得，如果嵌套过程中出现重复元素，那么必定是这个嵌套的所有可能被循环完了，而每一个不同的循环个数结果都代表一组完全不同的元素组合。所以可以直接在`nums`数组上进行修改，将访问过的数字转换成一个题目范围外的数字，即可起到类似解法二`visited`类似的作用。
### 代码
```java
class Solution {
    public int arrayNesting(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int count = 0, num = nums[i];
                while (nums[num] != Integer.MAX_VALUE) {
                    int index = num;
                    num = nums[num];
                    count++;
                    nums[index] = Integer.MAX_VALUE;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }
}
```