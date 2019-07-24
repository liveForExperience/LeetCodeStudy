# LeetCode_206_反转链表
## 题目
反转一个单链表。

示例:
```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```
## 解法一
### 思路
在整个过程中，需要关注的是前后两个节点的，所以需有两个指针
- 一个是pre：记录当前节点需要指向的节点
- 一个是cur：记录当前节点
循环遍历整个链表：
- 初始化两个指针
   - pre = null，头节点的上一个节点是空
   - cur = head
- 从head开始遍历，循环条件是(cur！=null)，循环体内的逻辑：
   - 用一个临时指针next指向cur的next节点(先找到下一个节点，因为cur.next的指针之后会变更指向的对象)
   - cur的next指向pre(反转)
   - pre指向cur(为下一个循环做准备)
   - cur指向next(为下一个循环做准备)
### 代码
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
```
## 解法二
### 思路
递归处理反转
- 一层层下钻到最后1个元素
- 之后每一层返回的时候都返回这个元素
- 同时每一层返回时候，都处理这一层下钻时候当前节点和下一层节点之间的指针关系
   1. 自己的next的next指向自己
   2. 自己的next指向null(因为之后返回时候，在上面一层，自己的上一个会通过步骤1将next指向它)
### 代码
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode end = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return end;
    }
}
```
# LeetCode_521_最长特殊序列I
## 题目
给定两个字符串，你需要从这两个字符串中找出最长的特殊序列。最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。

子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。

输入为两个字符串，输出最长特殊序列的长度。如果不存在，则返回 -1。

示例 :
```
输入: "aba", "cdc"
输出: 3
解析: 最长特殊序列可为 "aba" (或 "cdc")
```
说明:
```
两个字符串长度均小于100。
字符串中的字符仅含有 'a'~'z'。
```
## 解法
### 思路
一开始以为很复杂，但最后发现其实就是看谁字符串长，谁就是最长的子序列，这题。。。
### 代码
```java
class Solution {
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return a.length() > b.length() ? a.length() : b.length();
    }
}
```
# LeetCode_762_二进制表示中质数个数置位
## 题目
给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。

（注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）

示例 1:
```
输入: L = 6, R = 10
输出: 4
解释:
6 -> 110 (2 个计算置位，2 是质数)
7 -> 111 (3 个计算置位，3 是质数)
9 -> 1001 (2 个计算置位，2 是质数)
10-> 1010 (2 个计算置位，2 是质数)
```
示例 2:
```
输入: L = 10, R = 15
输出: 5
解释:
10 -> 1010 (2 个计算置位, 2 是质数)
11 -> 1011 (3 个计算置位, 3 是质数)
12 -> 1100 (2 个计算置位, 2 是质数)
13 -> 1101 (3 个计算置位, 3 是质数)
14 -> 1110 (3 个计算置位, 3 是质数)
15 -> 1111 (4 个计算置位, 4 不是质数)
```
注意:
```
L, R 是 L <= R 且在 [1, 10^6] 中的整数。
R - L 的最大值为 10000。
```
## 解法
### 思路
- 通过num & (num - 1)可以把最低位的1转变为0的特性，循环处理num，并记录处理的次数
- 判断得到的次数是否为质数，并记录“是”的次数，并在循环结束返回
### 代码
```java
class Solution {
    public int countPrimeSetBits(int L, int R) {
        int ans = 0;
        for (int i = L; i <= R; i++) {
            int count = 0;
            int num = i;
            while (num > 0) {
                num = num & (num - 1);
                count++;
            }

            if (isPrime(count)) {
                ans++;
            }
        }

        return ans;
    }

    private boolean isPrime(int num) {
        if (num == 2) {
            return true;
        }

        if (num < 2 || num % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
```
# LeetCode_136_只出现一次的数字
## 题目
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:
```
输入: [2,2,1]
输出: 1
```
示例 2:
```
输入: [4,1,2,1,2]
输出: 4
```
## 解法
### 思路
- 数组排序
- 从第一个元素开始，步长为2，到倒数第二个元素为止，判断当前元素是否和后一个元素相等。

注意如果单独的那个元素是最大的时候，需要避免数组越界。
### 代码
```java
class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }

        return nums[nums.length - 1];
    }
}
```
## 解法二
### 思路
- 遍历数组nums，使用map记录每个数字出现的次数
- 遍历map键值对，找到值为1的数并返回
### 代码
```java
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length / 2 + 1);
        for (int num: nums) {
            if (map.containsKey(num)) {
                map.computeIfPresent(num, (k, v) -> v+=1);
            } else {
                map.put(num, 1);
            }
        }
        
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() < 2) {
                return entry.getKey();
            }
        }
        
        return -1;
    }
}
```
## 优化代码
### 思路
在解法二中记数时候，其实可以通过把出现两次的数字从map中去掉的方法，省去最后遍历的过程，同时可以只是用set。
### 代码
```java
class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length / 2 + 1);
        for (int num: nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }

        return set.iterator().next();
    }
}
```
## 解法三
### 思路
使用异或的特性
### 代码
```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}
```
# LeetCode_575_分糖果
## 题目
给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。

示例 1:
```
输入: candies = [1,1,2,2,3,3]
输出: 3
解析: 一共有三种种类的糖果，每一种都有两个。
     最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
```
示例 2 :
```
输入: candies = [1,1,2,3]
输出: 2
解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
```
注意:
```
数组的长度为[2, 10,000]，并且确定为偶数。
数组中数字的大小在范围[-100,000, 100,000]内。
```
## 解法一
### 思路
妹妹可以分到的糖果数是总数的一半，而可以分到分到的糖果的种类数则可以分为两种情况：
- 糖果种类数大于等于总数的一半，那么就是总数一半
- 糖果总数小于总数，那就是种类总数

所以可以用set去重得到种类数，且如果在遍历过程中超过总数一半，就直接返回总数一半的值。
### 代码
```java
class Solution {
    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();
        
        int len = candies.length / 2;
        for (int num : candies) {
            set.add(num);
            if (set.size() >= len) {
                return len;
            }
        }
        
        return set.size();
    }
}
```
## 优化代码
### 思路
使用桶的思路，替换set，节省拆装箱等的时间
### 代码
```java
class Solution {
    public int distributeCandies(int[] candies) {
        int min = candies[0], max = candies[0];
        for (int i = 1; i < candies.length; i++) {
            min = Math.min(min, candies[i]);
            max = Math.max(max, candies[i]);
        }
        
        boolean[] buckets = new boolean[max - min + 1];
        for (int num : candies) {
            buckets[num - min] = true;
        }
        
        int count = 0;
        int len = candies.length / 2;
        for (boolean b: buckets) {
            if (b) {
                count++;
            }
            
            if (count >= len) {
                return len;
            }
        }
        
        return count;
    }
}
```
# LeetCode_1122_数组的相对排序
## 题目
给你两个数组，arr1 和 arr2，

arr2 中的元素各不相同
arr2 中的每个元素都出现在 arr1 中
对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

示例：
```
输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
输出：[2,2,2,1,4,3,3,9,6,7,19]
```
提示：
```
arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
arr2 中的元素 arr2[i] 各不相同
arr2 中的每个元素 arr2[i] 都出现在 arr1 中
```
## 解法
### 思路
- 新建一个ans数组
- 对arr1使用桶放置的方法，遍历arr1的过程中对出现的数字计数
- 遍历arr2，然后在ans中按照顺序和桶中的元素个数进行放置，同时对桶元素做--
- 最后遍历一遍桶，将(桶下标+min值)作为数，元素作为个数放入ans中
- 返回ans数组
### 代码
```java
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
       int min = arr1[0], max = arr1[0];
        for (int i = 1; i < arr1.length; i++) {
            min = Math.min(arr1[i], min);
            max = Math.max(arr1[i], max);
        }

        int[] buckets = new int[max - min + 1];
        for (int num : arr1) {
            buckets[num - min]++;
        }

        int[] ans = new int[arr1.length];
        int index = 0;
        for (int num : arr2) {
            while (buckets[num - min]-- > 0) {
                ans[index++] = num;
            }
        }

        for (int i = 0; i < buckets.length; i++) {
            while (buckets[i]-- > 0) {
                ans[index++] = i + min;
            }
        }

        return ans;
    }
}
```
# LeetCode_669_修剪二叉搜索树
## 题目
给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。

示例 1:
```
输入: 
    1
   / \
  0   2

  L = 1
  R = 2

输出: 
    1
      \
       2
```
示例 2:
```
输入: 
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3
  
输出: 
      3
     / 
   2   
  /
 1
```
## 解法
### 思路
dfs递归方式
- 退出条件：进入当前层的节点为空
- 处理逻辑：
   - 如果当前节点的值小于L，说明该节点左边的所有节点也不符合规则，返回下钻右子树后的结果
   - 如果当前节点的只大于R，说明该节点右边的所有节点也不符合规则，返回下钻左子树后的结果
   - 如果在L和R的范围内，就新建一个当前节点值得节点，并返回这个节点给上一层，在返回前，同时下钻它的左右子树去构建新树
- 最后返回根节点
### 代码
```java
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }

        if (root.val < L) {
            return trimBST(root.right, L, R);
        }
        
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }

        TreeNode node = new TreeNode(root.val);
        node.left = trimBST(root.left, L, R);
        node.right = trimBST(root.right, L, R);
        return node;
    }
}
```
# LeetCode_463_岛屿的周长
## 题目
给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。

网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。

岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。

示例 :
```
输入:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

输出: 16
```
## 解法
### 思路
岛屿的每一边有两种情况可以算作是周长的一部分：
- 数组边界
- 0

所以过程如下：
- 遍历这个二维数组
- 在元素为1的时候看它的上下左右是否符合如上的情况，如果是就计数+1
- 返回计数值
### 代码
```java
class Solution {
    public int islandPerimeter(int[][] grid) {
        int count = 0;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (i - 1 < 0 || grid[i - 1][j] == 0) {
                        count++;
                    }
                    
                    if (i + 1 > grid.length - 1 || grid[i + 1][j] == 0) {
                        count++;
                    }
                    
                    if (j - 1 < 0 || grid[i][j - 1] == 0) {
                        count++;
                    }
                    
                    if (j + 1 > grid[i].length - 1 || grid[i][j + 1] == 0) {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
}
```
# LeetCode_806_写字符串需要的行数
## 题目
我们要把给定的字符串 S 从左到右写到每一行上，每一行的最大宽度为100个单位，如果我们在写某个字母的时候会使这行超过了100 个单位，那么我们应该把这个字母写到下一行。我们给定了一个数组 widths ，这个数组 widths[0] 代表 'a' 需要的单位， widths[1] 代表 'b' 需要的单位，...， widths[25] 代表 'z' 需要的单位。

现在回答两个问题：至少多少行能放下S，以及最后一行使用的宽度是多少个单位？将你的答案作为长度为2的整数列表返回。

示例 1:
```
输入: 
widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = "abcdefghijklmnopqrstuvwxyz"
输出: [3, 60]
解释: 
所有的字符拥有相同的占用单位10。所以书写所有的26个字母，
我们需要2个整行和占用60个单位的一行。
```
示例 2:
```
输入: 
widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = "bbbcccdddaaa"
输出: [2, 4]
解释: 
除去字母'a'所有的字符都是相同的单位10，并且字符串 "bbbcccdddaa" 将会覆盖 9 * 10 + 2 * 4 = 98 个单位.
最后一个字母 'a' 将会被写到第二行，因为第一行只剩下2个单位了。
所以，这个答案是2行，第二行有4个单位宽度。
```
注:
```
字符串 S 的长度在 [1, 1000] 的范围。
S 只包含小写字母。
widths 是长度为 26的数组。
widths[i] 值的范围在 [2, 10]。
```
## 解法
### 思路
1. 初始化：
   - 结果数组ans，长度为2
   - 行数line，初始1
   - 当前行单位数word，初始0
2. 遍历字符串的字符数组,通过字符到widget数组中找单位数
3. 判断当前是否>=100
4. 如果>=100就line+1
   - \> 100 则word更新为当前字符所占单位数
   - == 100 则word更新为0
5. 将line放入ans[0]，word放入ans[1]，并返回
### 代码
```java
class Solution {
    public int[] numberOfLines(int[] widths, String S) {
        int[] ans = new int[2];
        char[] cs = S.toCharArray();

        int line = 0;
        int word = 0;
        for (char i = 0; i < cs.length; i++) {
            int num = word + widths[cs[i] - 'a'];
            if (num == 100) {
                line++;
                word = 0;
                continue;
            }

            if (num > 100) {
                line++;
                word = widths[cs[i] - 'a'];
                continue;
            }
            
            word = num;
        }

        ans[0] = line + 1;
        ans[1] = word;
        return ans;
    }
}
```
# LeetCode_107_二叉树的层次遍历II
## 题目
给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
```
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其自底向上的层次遍历为：

[
  [15,7],
  [9,20],
  [3]
]
```
## 解法一
### 思路
bfs，为了满足从底部到根排序方式，用一个栈暂存每一层的结果，然后再循环弹栈放入list中
### 代码
```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Stack<List<Integer>> stack = new Stack<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();

            List<Integer> list = new ArrayList<>(count);
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
                list.add(node.val);
            }
            
            stack.push(list);
        }
        
        while (!stack.isEmpty()) {
            ans.add(stack.pop());    
        }
        
        return ans;
    }
}
```
## 优化代码
### 思路
使用Collections.reserve()方法来实现stack做的事情，提升了1秒
### 代码
```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();

            List<Integer> list = new ArrayList<>(count);
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
                list.add(node.val);
            }

            ans.add(list);
        }

        Collections.reverse(ans);
        return ans; 
    }
}
```
## 解法二
### 思路
dfs递归
- 递归参数：
    - 结果ans
    - 代表树层数的level
    - 当前节点node
- 退出条件是节点为null
- 每一层的处理逻辑：
    - 如果level大于list的size，说明来到新的一层，需要新声明一个list，加入ans
    - 把当前节点的val加入到level所对应的list中
    - 往左右节点下钻
- 返回最终结果ans
### 代码
```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        recurse(ans, 0, root);
        Collections.reverse(ans);
        return ans;
    }

    private void recurse(List<List<Integer>> ans, int level, TreeNode node) {
        if (node == null) {
            return;
        }

        if (level >= ans.size()) {
            List<Integer> list = new ArrayList<>();
            ans.add(list);
        }

        ans.get(level).add(node.val);

        recurse(ans, level + 1, node.left);
        recurse(ans, level + 1, node.right);
    }
}
```
# LeetCode_876_链表的中间节点
## 题目
给定一个带有头结点 head 的非空单链表，返回链表的中间结点。

如果有两个中间结点，则返回第二个中间结点。

示例 1：
```
输入：[1,2,3,4,5]
输出：此列表中的结点 3 (序列化形式：[3,4,5])
返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
注意，我们返回了一个 ListNode 类型的对象 ans，这样：
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
```
示例 2：
```
输入：[1,2,3,4,5,6]
输出：此列表中的结点 4 (序列化形式：[4,5,6])
由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
```
提示：
```
给定链表的结点数介于 1 和 100 之间。
```
## 解法一
### 思路
- 遍历链表
- 将当前的链表节点放入一个有序的、随机查询时间复杂度为O(1)的数据结构
- 返回数据结构中其长度一半位置存放的链表节点
### 代码
```java
class Solution {
    public ListNode middleNode(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        
        return list.get(list.size() / 2);
    }
}
```
## 解法二
### 思路
使用快慢游标，快游标步长为2，慢游标步长为1，当快游标遍历到最后一个或倒数第二个节点时，返回慢游标
### 代码
```java
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }
}
```