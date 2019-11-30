# LeetCode_445_两数相加
## 题目
给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

进阶:
```
如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
```
示例:
```
输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
输出: 7 -> 8 -> 0 -> 7
```
## 解法
### 思路
- 两个链表分别倒置
- 从新链表的头指针开始遍历
- 两个不同链表的节点相加，暂存进位值，并使用各位生成结果链表的节点值
- 遍历过程中还需要再倒置以下新结果的链表
- 遍历结束后，查看进位是否为1，如果是就在结果链表前再加一个值为1的节点
### 代码
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = reserve(l1);
        ListNode r = reserve(l2);

        int carry = 0;
        ListNode cur = null, pre;
        while (l != null || r != null) {
            int lnum = l == null ? 0 : l.val;
            int rnum = r == null ? 0 : r.val;

            int val = lnum + rnum + carry;
            carry = val / 10;

            pre = cur;
            cur = new ListNode(val % 10);
            cur.next = pre;

            l = l == null ? null : l.next;
            r = r == null ? null : r.next;
        }

        if (carry == 1) {
            ListNode head = new ListNode(1);
            head.next = cur;
            return head;
        }
        
        return cur;
    }

    private ListNode reserve(ListNode node) {
        ListNode pre = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
```
## 解法二
### 思路
- 使用两个栈来保存两个链表的节点值
- 遍历两个栈，生成链表并反转
- 判断carry是否有值，如果有就在链表前加一个节点
### 代码
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> ls = getStack(l1);
        Stack<Integer> rs = getStack(l2);

        int carry = 0;
        ListNode cur = null, pre = null;
        while (!ls.isEmpty() || !rs.isEmpty()) {
            int val = carry;
            if (!ls.isEmpty()) {
                val += ls.pop();
            }
            if (!rs.isEmpty()) {
                val += rs.pop();
            }

            carry = val / 10;
            pre = cur;
            cur = new ListNode(val % 10);
            cur.next = pre;
        }

        if (carry == 1) {
            ListNode head = new ListNode(1);
            head.next = cur;
            return head;
        }
        
        return cur;
    }

    private Stack<Integer> getStack(ListNode node) {
        Stack<Integer> stack = new Stack<>();
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }
        return stack;
    }
}
```
# LeetCode_912_排序数组
## 题目
给定一个整数数组`nums`，将该数组升序排列。

示例 1：
```
输入：[5,2,3,1]
输出：[1,2,3,5]
```
示例 2：
```
输入：[5,1,1,2,0,0]
输出：[0,0,1,1,2,5]
```
提示：
```
1 <= A.length <= 10000
-50000 <= A[i] <= 50000
```
## 解法
### 思路
使用stream API
### 代码
```java
class Solution {
    public List<Integer> sortArray(int[] nums) {
        return Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
    }
}
```
## 解法二
### 思路
使用Arrays API
### 代码
```java
class Solution {
    public List<Integer> sortArray(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }
}
```
# LeetCode_495_提莫攻击
## 题目
在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄，他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。现在，给出提莫对艾希的攻击时间序列和提莫攻击的中毒持续时间，你需要输出艾希的中毒状态总时长。

你可以认为提莫在给定的时间点进行攻击，并立即使艾希处于中毒状态。

示例1:
```
输入: [1,4], 2
输出: 4
原因: 在第 1 秒开始时，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒钟结束。
在第 4 秒开始时，提莫再次攻击艾希，使得艾希获得另外 2 秒的中毒时间。
所以最终输出 4 秒。
```
示例2:
```
输入: [1,2], 2
输出: 3
原因: 在第 1 秒开始时，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒钟结束。
但是在第 2 秒开始时，提莫再次攻击了已经处于中毒状态的艾希。
由于中毒状态不可叠加，提莫在第 2 秒开始时的这次攻击会在第 3 秒钟结束。
所以最终输出 3。
```
注意：
```
你可以假定时间序列数组的总长度不超过 10000。
你可以假定提莫攻击时间序列中的数字和提莫攻击的中毒持续时间都是非负整数，并且不超过 10,000,000。
在真实的面试中遇到过这道题？
```
## 解法
### 思路
- 遍历数组
- 计算遍历到的元素`n`对应窗口的起始`start`和结束`end`，及间隔时间
    - `n`大于暂存的`end`，则直接暂存`start`和`end`，并计算两者的间隔
    - `n`小于等于暂存的`end`，则只更新`end`，并先计算老`end`和新`end`的间隔作为耗时
- 返回结果
### 代码
```java
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int end = -1, ans = 0;
        for (int time : timeSeries) {
            if (time > end) {
                ans += duration;
            } else {
                ans += time + duration - 1 - end;
            }
            end = time + duration - 1;
        }
        return ans;
    }
}
```
# LeetCode_17_电话号码的字母组合
## 题目
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

示例:
```
输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
```
## 解法
### 思路
- 生成电话号码与字母的map映射
- 递归：
    - 退出条件：数字对应的字符串长度为0，说明当前路径的所有可能性遍历完成，将生成的字符串放入list中
    - 通过当前层对应的数组，到map中寻找对应的所有字符串可能
    - 遍历字符串，递归到下一层，同时将数字字符串的第一个字符串截掉
### 代码
```java
class Solution {
    private Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (Objects.equals(digits, "")) {
            return ans;
        }
        backTrack("", digits, ans);
        return ans;
    }

    private void backTrack(String str, String digits, List<String> ans) {
        if (digits.length() == 0) {
            ans.add(str);
            return;
        }

        String digit = digits.substring(0, 1);
        String letters = phone.get(digit);

        for (int i = 0; i < letters.length(); i++) {
            String letter = letters.substring(i, i + 1);
            backTrack(str + letter, digits.substring(1), ans);
        }
    }
}
```
# LeetCode_646_最长数对链
## 题目
给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。

现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。

给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。

示例 :
```
输入: [[1,2], [2,3], [3,4]]
输出: 2
解释: 最长的数对链是 [1,2] -> [3,4]
```
注意：
```
给出数对的个数在 [1, 1000] 范围内。
```
## 解法
### 思路
贪心：
- 针对数组的第二个元素对矩阵进行排序
- 暂存第一个一维数组的第二个元素值`cur`
- 初始计数值`count`
- 循环：
    - 遍历矩阵的一维数组
    - 获得当前数组的第一个元素`num`，与`cur`进行比较
    - 如果`num`比`cur`大，说明符合题意，可以连接，`count++`
    - 将`cur`变更为当前数组的第二个元素
    - 继续循环
### 代码
```java
class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(o -> o[1]));
        int cur = pairs[0][1], count = 1;
        for (int i = 1; i < pairs.length; i++) {
            int num = pairs[i][0];
            if (num > cur) {
                count++;
                cur = pairs[i][1];
            }
        }
        return count;
    }
}
```
# LeetCode_725_分隔链表
## 题目
给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。

每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。

这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。

返回一个符合上述规则的链表的列表。

举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]

示例 1：
```
输入: 
root = [1, 2, 3], k = 5
输出: [[1],[2],[3],[],[]]
解释:
输入输出各部分都应该是链表，而不是数组。
例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.next.next.next = null。
第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
```
示例 2：
```
输入: 
root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
解释:
输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
```
提示:
```
root 的长度范围： [0, 1000].
输入的每个节点的大小范围：[0, 999].
k 的取值范围： [1, 50].
```
## 解法
### 思路
- 定义参数：
    - 每段最短的长度`len`
    - 长度多1的段数`left`
    - 链表遍历指针`node`
- 过程：
    - 遍历链表获得链表长度
    - 计算`len`和`left`
    - 嵌套循环
    - 外层循环k次，生成k段
    - 内层遍历链表，遍历次数为最短长度 + `left-- > 0 ? 1: 0`
    - 生成一段链表，放入list中
### 代码
```java
class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int count = 0;
        ListNode cur = root;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        int len = count / k, left = count % k;

        ListNode[] ans = new ListNode[k];

        cur = root;
        for (int i = 0; i < k; i++) {
            ListNode head = new ListNode(0), write = head;
            for (int j = 0; j < len + (i < left ? 1 : 0); j++) {
                write = write.next = new ListNode(cur.val);
                cur = cur.next;
            }
            ans[i] = head.next;
        }

        return ans;
    }
}
```
## 解法二
### 思路
在解法一的基础上，将链表直接截断，而不是重新创建链表。
- 注意在内层循环时，因为没有使用头指针，所以每一段的循环次数需要-1
### 代码
```java
class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int count = 0;
        ListNode node = root;
        while (node != null) {
            count++;
            node = node.next;
        }
        
        int len = count / k, left = count % k;
        ListNode[] ans = new ListNode[k];
        
        node = root;
        for (int i = 0; i < k; i++) {
            ListNode head = node;
            for (int j = 0; j < len + (i < left ? 0 : -1); j++) {
                if (node != null) {
                    node = node.next;
                }
            }
            
            if (node != null) {
                ListNode pre = node;
                node = node.next;
                pre.next = null;
            }
            
            ans[i] = head;
        }
        
        return ans;
    }
}
```
# LeetCode_652_寻找重复的子树
## 题目
给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。

两棵树重复是指它们具有相同的结构以及相同的结点值。

示例 1：
```
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
```
下面是两个重复的子树：
```
      2
     /
    4
```
和
```
    4
```
因此，你需要以列表的形式返回上述重复子树的根结点。
## 解法
### 思路
- 参数：
    - `map`：保存路径中的节点的序列化值
    - `ans`：结果list
- dfs递归求得每一个节点及子树的序列化值
- 在返回过程中
    - 查看`map`中是否已经存在，且是第一次发现重复，就将节点放入`ans`
    - 记录当前得到的序列化值
### 代码
```java
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        dfs(root, new HashMap<>(), ans);
        return ans;
    }

    private String dfs(TreeNode node, Map<String, Integer> map, List<TreeNode> ans) {
        if (node == null) {
            return "";
        }
        
        String key = node.val + "," + dfs(node.left, map, ans) + "," + dfs(node.right, map, ans);
        
        if (map.containsKey(key) && map.get(key) == 1) {
            ans.add(node);
        }
        
        map.put(key, map.getOrDefault(key, 0) + 1);
        return key;
    }
}
```
## 优化代码
### 思路
- 使用int替换String作为返回值，所以退出条件就使用hashcode
- 因为返回后都要put一个`count + 1`，所以就先先put再判断是否`count == 1`
- 这样也就不需要再同时判断`contains`和`count == 1`了
### 代码
```java
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        dfs(root, new HashMap<>(), ans);
        return ans;
    }

    private int dfs(TreeNode node, Map<Integer, Integer> map, List<TreeNode> ans) {
        if (node == null) {
            return "#".hashCode();
        }

        String key = node.val + "," + dfs(node.left, map, ans) + "," + dfs(node.right, map, ans);

        int uid = key.hashCode();
        map.put(uid, map.getOrDefault(uid, 0) + 1);
        
        if (map.get(uid) == 2) {
            ans.add(node);
        }
        
        return uid;
    }
}
```