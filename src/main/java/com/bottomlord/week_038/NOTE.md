# Interview_0401_节点间通路
## 题目
节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。

示例1:
```
 输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 输出：true
```
示例2:
```
 输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
 输出 true
```
提示：
```
节点数量n在[0, 1e5]范围内。
节点编号大于等于 0 小于 n。
图中可能存在自环和平行边。
```
## 解法
### 思路
- 根据二维数组生成基于hash表的有向图
- 搜索有向图，查看是否存在通路
### 代码
```java
class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, Set<Integer>> map = new HashMap<>(n);
        for (int[] arr : graph) {
            Set<Integer> set = map.getOrDefault(arr[0], new HashSet<>());
            set.add(arr[1]);
            map.put(arr[0], set);
        }
        
        return dfs(map, start, target);
    }
    
    private boolean dfs(Map<Integer, Set<Integer>> map, int start, int target) {
        if (!map.containsKey(start)) {
            return false;
        }
        
        boolean ans = false;
        for (int num : map.get(start)) {
            if (num == target) {
                return true;
            }
            
            ans |= dfs(map, num, target);
        }
        
        return ans;
    }
}
```
## 优化代码
### 思路
- 增加记忆化搜索
- 使用list代替set
### 代码
```java
class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>(n);
        for (int[] arr : graph) {
            List<Integer> set = map.getOrDefault(arr[0], new ArrayList<>());
            set.add(arr[1]);
            map.put(arr[0], set);
        }

        boolean[] visited = new boolean[n + 1];
        return dfs(map, visited, start, target);
    }

    private boolean dfs(Map<Integer, List<Integer>> map, boolean[] visited, int start, int target) {
        if (!map.containsKey(start)) {
            return false;
        }

        List<Integer> list = map.get(start);
        if (list.contains(target)) {
            return true;
        }

        visited[start] = true;
        for (int num : list) {
            if (!visited[num] && dfs(map, visited, num, target)) {
                return true;
            }
        }

        return false;
    }
}
```
# Interview_0402_最小高度树
## 题目
给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。

示例:
```
给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

          0 
         / \ 
       -3   9 
       /   / 
     -10  5 
```
## 解法
### 思路
- 将升序数组从中点开始分割成左右两部分
- 将中点作为当前层根节点
- 左右两部分作为根节点的左右子树
- 退出条件是数组起始坐标大于等于结尾坐标，结尾坐标设置为数组长度，使得中点在计算时可得到相对大的数
### 代码
```java
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        return build(nums, 0, nums.length);
    }
    
    private TreeNode build(int[] nums, int start, int end) {
        if (start >= end) {
            return null;
        }
        
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = build(nums, start, mid);
        node.right = build(nums, mid + 1, end);
        return node;
    }
}
```
# Interview_0403_特定深度节点链表
## 题目
给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。

示例：
```
输入：[1,2,3,4,5,null,7,8]

        1
       /  \ 
      2    3
     / \    \ 
    4   5    7
   /
  8

输出：[[1],[2,3],[4,5,7],[8]]
```
## 解法
### 思路
bfs
### 代码
```java
class Solution {
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return new ListNode[0];
        }

        List<ListNode> ansList = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(tree);
        
        while (!queue.isEmpty()) {
            int count = queue.size();
            ListNode head = new ListNode(-1), pre = head;
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                
                ListNode cur = new ListNode(node.val);
                pre.next = cur;
                pre = cur;
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            ansList.add(head.next);
        }
        
        return ansList.toArray(new ListNode[0]);
    }
}
```
## 解法二
### 思路
dfs
### 代码
```java
class Solution {
    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> list = new ArrayList<>();
        dfs(list, tree, 0);
        return list.toArray(new ListNode[0]);
    }

    private void dfs(List<ListNode> list, TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        if (depth >= list.size()) {
            list.add(new ListNode(node.val));
        } else {
            ListNode listNode = list.get(depth);
            while (listNode.next != null) {
                listNode = listNode.next;
            }
            
            listNode.next = new ListNode(node.val);
        }
        
        depth++;
        dfs(list, node.left, depth);
        dfs(list, node.right, depth);
    }
}
```
# Interview_1716_按摩师
## 题目
一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。

示例 1：
```
输入： [1,2,3,1]
输出： 4
解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
```
示例 2：
```
输入： [2,7,9,3,1]
输出： 12
解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
```
示例 3：
```
输入： [2,1,4,5,3,1,1,3]
输出： 12
解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
```
## 解法
### 思路
动态规划：
- `dp[i][j]`：
    - i代表前i个预约
    - j取值范围是0和1，0代表第i个预约不接，1代表第i个预约接
- 状态转移方程：
    - `dp[i][1] = dp[i][0] + nums[i]`
    - `dp[i][0] = max(dp[i][0],dp[i][1]`
- 初始化：`dp[0][1] = nums[i]`
### 代码
```java
class Solution {
    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int len = nums.length;
        
        int[][] dp = new int[len][2];
        dp[0][1] = nums[0];
        
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }
}
```
## 解法二
### 思路
使用两个变量代替二维数组
### 代码
```java
class Solution {
    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int dp0 = 0, dp1 = nums[0], len = nums.length;
        for (int i = 1; i < len; i++) {
            int tDp0 = Math.max(dp1, dp0);
            int tDp1 = dp0 + nums[i];
            
            dp0 = tDp0;
            dp1 = tDp1;
        }
        
        return Math.max(dp0, dp1);
    }
}
```
# Interview_0404_检查平衡性
## 题目
实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。

示例 1:
```
给定二叉树 [3,9,20,null,null,15,7]
    3
   / \
  9  20
    /  \
   15   7
返回 true 。
```
示例 2:
```
给定二叉树 [1,2,2,3,3,null,null,4,4]
      1
     / \
    2   2
   / \
  3   3
 / \
4   4
返回false 。
```
## 解法
### 思路
两层dfs，从上往下判断：
- 内层dfs获得当前节点得左右子树的最长长度
- 外层dfs确定内层dfs的起始节点，并根据返回的左右子树长度判断是否是平衡
### 代码
```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int left = dfs(root.left), right = dfs(root.right);
        if (Math.abs(left - right) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left), right = dfs(node.right);

        return Math.max(left, right) + 1;
    }
}
```
## 解法二
### 思路
dfs从底向上判断：
- 从树的叶子节点开始获取左右子树的长度
- 判断左右子树的长度是否是平衡的
- 如果不是就返回-1，用作标识
### 代码
```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int left = dfs(node.left), right = dfs(node.right);
        if (left == -1 || right == -1) {
            return -1;
        }
        
        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;
    }
}
```
# Interview_0405_合法二叉搜索树
## 题目
实现一个函数，检查一棵二叉树是否为二叉搜索树。

示例1:
```
输入:
    2
   / \
  1   3
输出: true
```
示例2:
```
输入:
    5
   / \
  1   4
    / \
   3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
    根节点的值为 5 ，但是其右子节点值为 4 。
```
## 解法
### 思路
- 获取中序遍历序列
- 检查是否升序
### 代码
```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        
        return true;
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
```
## 解法二
### 思路
- 二叉搜索树：
    - 左子树的值范围的上界是其根结点的值
    - 右子树的值范围的下界是其根节点的值
- 过程：
    - 递归过程中判断当前值是否在上下界之间
    - 如果递归左子树，上界定为当前节点的值
    - 如果递归右子树，下界定位当前节点的值
### 代码
```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        
        return node.val < max && node.val > min && 
                dfs(node.left, min, node.val) && 
                dfs(node.right, node.val, max);
    }
}
```
# Interview_0406_后继者
## 题目
设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。

如果指定节点没有对应的“下一个”节点，则返回null。

示例 1:
```
输入: root = [2,1,3], p = 1

  2
 / \
1   3

输出: 2
```
示例 2:
```
输入: root = [5,3,6,2,4,null,null,1], p = 6

      5
     / \
    3   6
   / \
  2   4
 /   
1

输出: null
```
## 解法
### 思路
- dfs中序遍历获取序列
- 遍历序列，找到后继
### 代码
```java
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        
        List<TreeNode> list = new ArrayList<>();
        inOrder(root, list);
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == p) {
                return i == list.size() - 1 ? null : list.get(i + 1);
            }
        }
        
        return null;
    }
    
    private void inOrder(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        
        inOrder(node.left, list);
        list.add(node);
        inOrder(node.right, list);
    }
}
```
## 解法二
### 思路
- 不使用额外的数据结构
- 利用二叉搜索树的特性， 递归比较当前节点和p节点的值
    - 如果小于等于p，p在当前节点的右子树，目标节点也在右子树，所以往右子树递归
    - 如果大于p，说明要么：
        - p和目标节点还在当前节点的左子树里
        - 要么p就是当前节点的父节点，当前节点的就是目标值，而在这种情况下，当前节点的左子树一定是空
### 代码
```java
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode left = inorderSuccessor(root.left, p);
            return left == null ? root : left;
        }
    }
}
```
# Interview_0408_首个公共祖先
## 题目
设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。

例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
```
    3
   / \
  5   1
 / \ / \
6  2 0  8
  / \
 7   4
```
示例 1:
```
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输入: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
```
示例 2:
```
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
```
说明:
```
所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉树中。
```
## 解法
### 思路
dfs：
- 退出条件：
    - 如果当前节点为空，返回空
    - 如果当前节点是两个目标节点中的一个，那么就返回当前节点，返回也代表了，如果当前节点就是一个公共祖先节点，那么其实其他搜索路径就碰不到目标节点了，而当前节点子树中的目标节点也不需要搜索了，可以理解成一种剪枝
- 递归求左右子树返回的节点
- 如果返回的左子树为空，说明左子树中没有目标节点，返回右子树，因为无论右子树中是否有目标节点，直接返回都是正确的结果
- 同理，如果右子树为空，那么代表左子树就是有目标节点的
- 如果如上两种条件都不符合，那么就代表左右子树都找到了目标节点，将当前节点返回就可以了
### 代码
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        
        if (root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left == null) {
            return right;
        }
        
        if (right == null) {
            return left;
        }
        
        return root;
    }
}
```
# Interview_0409_二叉搜索树序列
## 题目
从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。给定一个由不同节点组成的二叉树，输出所有可能生成此树的数组。

示例:
```
给定如下二叉树

        2
       / \
      1   3
返回:

[
   [2,1,3],
   [2,3,1]
]
```
## 解法
### 思路
回溯：
- 根据题意，遍历数组生成树的过程中，必须要现有根节点，才能有左右子树，其他顺序可以随意变换
- 过程：
    - 初始化一个队列存储queue，初始化存储根节点
    - 初始化一个动态数组path，用来暂存探索路径中的节点值
    - 初始化一个动态数组ans，用来存储所有的可能路径
    - dfs搜索树
    - 退出条件：
        - 当前节点为空，此时生成一个空的list放入ans中
        - queue为空，代表已经没有待探索的节点，将当前传入的path生成一个新的list放入ans中
    - 将当前节点的左右子节点放入queue
    - 遍历queue，开始回溯，在循环体中：
        - 将节点从queue中弹出
        - 将节点值放入path
        - 将当前节点作为下一层的根节点递，基于queue生成新的list，继续递归下去
        - 返回时将节点放回queue中
        - 从path中将当前节点值去除
### 代码
```java
class Solution {
    public List<List<Integer>> BSTSequences(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            ans.add(new LinkedList<>());
            return ans;
        }

        LinkedList<Integer> path = new LinkedList<>();
        path.add(root.val);

        backTrack(root, new LinkedList<>(), path, ans);
        return ans;
    }

    private void backTrack(TreeNode node, LinkedList<TreeNode> queue, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            queue.add(node.left);
        }

        if (node.right != null) {
            queue.add(node.right);
        }

        if (queue.isEmpty()) {
            ans.add(new LinkedList<>(path));
        }

        for (int i = 0; i < queue.size(); i++) {
            TreeNode tmp = queue.remove(i);
            path.add(tmp.val);
            backTrack(tmp, new LinkedList<>(queue), path, ans);
            queue.add(i, tmp);
            path.removeLast();
        }
    }
}
```
# Interview_0410_检查子树
## 题目
检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。

如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。

示例1:
```
 输入：t1 = [1, 2, 3], t2 = [2]
 输出：true
```
示例2:
```
 输入：t1 = [1, null, 2, 4], t2 = [3, 2]
 输出：false
```
提示：
```
树的节点数目范围为[0, 20000]。
```
## 解法
### 思路
两层递归：
- 外层同时遍历T1和T2
    - 退出条件：T1为空时
        - 如果T2也为空，返回true
        - 如果T2不为空，返回false
    - 过程：
        - 进入内层递归，判断从T1的当前节点开始，判断T2是否为T1的子树
        - 继续递归判断左右子树
        - 3个步骤之间以短路或连接
- 内层同时遍历T1和T2：
    - 退出条件：
        - T1和T2同时为空时返回true
        - T1和T2不同时为空时候返回false
    - 过程：
        - 判断当前连个节点值是否相等
        - 递归左右子树
        - 3个步骤用短路与连接
### 代码
```java
class Solution {
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2 == null;
        }
        
        return dfs(t1, t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }
    
    private boolean dfs(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        
        if (t1 == null || t2 == null) {
            return false;
        }
        
        return t1.val == t2.val && dfs(t1.left, t2.left) && dfs(t1.right, t2.right);
    }
}
```
# Interview_0412_求和路径
## 题目
给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。

示例:
```
给定如下二叉树，以及目标和sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:

3
解释：和为 22的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
```
提示：
```
节点总数 <= 10000
```
## 解法
### 思路
嵌套dfs
- 外层确定其实节点
- 内层搜索所有路径
- 每一层判断当前节点与累加值是否与sum相等
- 因为有负值，所以获得sum后还可以继续遍历
### 代码
```java
class Solution {
    private int ans;
    public int pathSum(TreeNode root, int sum) {
        dfs1(root,sum);
        return ans;
    }

    private void dfs1(TreeNode node, int sum) {
        if (node == null) {
            return;
        }

        dfs2(node, sum, 0);
        dfs1(node.left, sum);
        dfs1(node.right, sum);
    }

    private void dfs2(TreeNode node, int sum, int tmp) {
        if (node == null) {
            return;
        }

        tmp += node.val;
        if (tmp == sum) {
            ans++;
        }

        dfs2(node.left, sum, tmp);
        dfs2(node.right, sum, tmp);
    }
}
```
## 解法二
### 思路
前缀+dfs：
- 使用一个数组来存储已经搜索过的路径，下标值对应树的深度
- 使用一个变量来记录当前搜索的深度
- 递归过程中，遍历记录的已经遍历过的路径值，累加，查看是否有匹配sum的值，如果有就累加。代表以当前节点为重点的路径中，能够找到的所有可能路径的个数之和
- 最终返回当前的累加值和左右子树递归后返回的值之和
### 代码
```java
class Solution {
    public int pathSum(TreeNode root, int sum) {
        return dfs(root, new int[1000], 0, sum);
    }

    private int dfs(TreeNode node, int[] arr, int depth, int sum) {
        if (node == null) {
            return 0;
        }

        int val = 0, cur = 0;
        arr[depth] = node.val;

        for (int i = depth; i >= 0; i--) {
            val += arr[i];
            if (val == sum) {
                cur++;
            }
        }

        return cur + dfs(node.left, arr, depth + 1, sum) + dfs(node.right, arr, depth + 1, sum);
    }
}
```
# Interview_0501_插入
## 题目
插入。给定两个32位的整数N与M，以及表示比特位置的i与j。编写一种方法，将M插入N，使得M从N的第j位开始，到第i位结束。假定从j位到i位足以容纳M，也即若M = 10 011，那么j和i之间至少可容纳5个位。例如，不可能出现j = 3和i = 2的情况，因为第3位和第2位之间放不下M。

示例1:
```
 输入：N = 10000000000, M = 10011, i = 2, j = 6
 输出：N = 10001001100
```
示例2:
```
 输入： N = 0, M = 11111, i = 0, j = 4
 输出：N = 11111
```
## 解法
### 思路
位运算：
- 生成一个i到j位为1的值t
- 将该值取反，使i到j为0
- 将该值与N相与，使N的i到j位是0
- 将M左移i位，与N相或，完成插入
### 代码
```java
class Solution {
    public int insertBits(int N, int M, int i, int j) {
        int t = 0;
        for (int k = i; k <= j; k++) {
            t |= (1 << k);
        }
        
        N &= ~t;
        return N | (M << i);
    }
}
```
# Interview_0502_二进制数转字符串
## 题目
二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字不在0和1之间，或者无法精确地用32位以内的二进制表示，则打印“ERROR”。

示例1:
```
 输入：0.625
 输出："0.101"
```
示例2:
```
 输入：0.1
 输出："ERROR"
 提示：0.1无法被二进制准确表示
```
提示：
```
32位包括输出中的"0."这两位。
```
## 解法
### 思路
如果数字为0.625,使用二进制表示的过程
1. 0.625 * 2 = 1.25
2. 1.3 - 1 = 0.25 => 1
3. 0.25 * 2 = 0.5 => 10
4. 0.5 * 2 = 1
5. 1 - 1 = 0 => 101
### 代码
```java
class Solution {
    public String printBin(double num) {
        StringBuilder ans = new StringBuilder("0.");
        int time = 0;
        while (num != 0 && time <= 32) {
            num *= 2;
            if (num >= 1) {
                num--;
                ans.append("1");
            } else {
                ans.append("0");
            }
            time++;
        }
        
        return time > 32 ? "ERROR" : ans.toString();
    }
}
```
# Interview_0503_翻转数位
## 题目
给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。

示例 1：
```
输入: num = 1775(110111011112)
输出: 8
```
示例 2：
```
输入: num = 7(01112)
输出: 4
```
## 解法
### 思路
嵌套循环：
- 外层从目标值的第一位开始遍历
- 内层从该位开始计算最长的1长度：
    - 第一次遇到0时可以转为1继续累加
    - 并记录这一位0的位置，下次外层的开始位置从该位开始
- 返回遍历中得到的最大值
### 代码
```java
class Solution {
    public int reverseBits(int num) {
        int[] bits = new int[32];
        int t = 1;
        for (int i = 0; i < 32; i++) {
            if ((num & (t << i)) != 0) {
                bits[i] = 1;
            } else {
                bits[i] = 0;
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0, life = 1;
            for (int j = i; j < 32; j++) {
                if (bits[j] == 1) {
                    count++;
                } else if (life == 1) {
                    count++;
                    life--;
                } else {
                    break;
                }
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }
}
```
## 解法二
### 思路
- 初始化两个变量：
    - pre：记录遇到0的时候，前一段连续1的长度
    - cur：记录当前连续1的长度，如果遇到0时候，将当前记录的值，减去上一段的值(包括被翻转的0)，因为只能有一个0可以被翻转成1，所以被题目视为有效的连续1的长度其实可以被分成2部分，当遇到第2个0时，就去掉前一部分，第二部分可以继续累加
    - cur的初始值为1，因为有一个0是可以被翻转的
- 遍历32位数字从低到高的每一位
    - 遇到1就累加，并将得到的值和最大值比较，取较大值
    - 遇到0的时候
        - 就先将累加值减去上一段pre的长度，作为继续累加的值
        - 再将这个继续累加的部分，作为下一次遇到0时候的pre
### 代码
```java
class Solution {
    public int reverseBits(int num) {
        int time = 32, cur = 1, pre = 0, ans = 0;

        while (time-- > 0) {
            if ((num & 1) == 0) {
                cur = cur - pre;
                pre = cur;
            }

            ans = Math.max(ans, cur);
            num >>= 1;
            cur++;
        }

        return ans;
    }
}
```
# Interview_0506_整数转换
## 题目
整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。

示例1:
```
 输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
 输出：2
```
示例2:
```
 输入：A = 1，B = 2
 输出：2
```
提示:
```
A，B范围在[-2147483648, 2147483647]之间
```
## 解法
### 思路
- 先异或，获得不同的1的个数
- 通过`x &(x - 1)`不断地消去低位的1，直到为0为止，并计算循环的次数
### 代码
```java
class Solution {
    public int convertInteger(int A, int B) {
        int xor = A ^ B, count = 0;
        while (xor != 0) {
            xor &= (xor - 1);
            count++;
        }
        return count;
    }
}
```
# LeetCode_820_单词的压缩编码
## 题目
给定一个单词列表，我们将这个列表编码成一个索引字符串S与一个索引列表 A。

例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。

对于每一个索引，我们可以通过从字符串 S中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。

那么成功对给定单词列表进行编码的最小字符串长度是多少呢？

示例：
```
输入: words = ["time", "me", "bell"]
输出: 10
说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
```
提示：
```
1 <= words.length<= 2000
1 <=words[i].length<= 7
每个单词都是小写字母 。
```
## 解法
### 思路
- 如果某一个单词是另一个单词的后缀，那么这个单词就不需要出现在生成的压缩编码中
- 将单词序列初始化为set集合，用来快速判定当前单词的某一个后缀可能是否在所有单词中存在
- 嵌套循环：
    - 外层遍历这个单词序列
    - 内层遍历当前遍历到的单词，从第二个字符开始，查看当前这个后缀是否在set中存在
    - 如果存在就将这个与后缀相同的单词从set中删除
- 遍历set集合，累加单词长度，且因为每个单词后面都需要一个`#`作为提示，所以还要再加1
- 返回累加值
### 代码
```java
class Solution {
    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                set.remove(word.substring(i));
            }
        }

        int ans = 0;
        for (String word : set) {
            ans += word.length() + 1;
        }
        return ans;
    }
}
```
## 解法二
### 思路
字典树：
- 初始化一个字典树的根节点
- 因为找的是后缀，所以字典树的节点根据单词的倒序顺序进行插入，这样后缀单词会被包含在对应路径中
- 遍历单词数组
- 根据倒序将当前单词插入到字典树中，并记录当前节点被使用的次数，当使用次数为0时，代表是叶子节点
- 生成当前单词在字典树中的节点后，将当前单词的第一个单词对应的节点放入一个map中，这个节点可能是叶子节点，也可能不是
- 最后遍历map，将节点中使用次数为0的叶子节点对应的单词长度，累加，同时还要加上`#`代表的长度1
- 将累加值返回
### 代码
```java
class Solution {
    public int minimumLengthEncoding(String[] words) {
        TrieNode root = new TrieNode();
        Map<TrieNode, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            TrieNode cur = root;
            for (int j = words[i].length() - 1; j >= 0; j--) {
                cur = cur.get(words[i].charAt(j));
            }
            map.put(cur, i);
        }
        
        int ans = 0;
        for (TrieNode node : map.keySet()) {
            if (node.count == 0) {
                ans += words[map.get(node)].length() + 1;
            }
        }
        
        return ans;
    }


    private class TrieNode {
        private TrieNode[] children;
        private int count;

        public TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }

        public TrieNode get(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TrieNode();
                count++;
            }

            return children[c - 'a'];
        }
    }
}
```
# Interview_0504_下一个数
## 题目
下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。

示例1:
```
 输入：num = 2（或者0b10）
 输出：[4, 1] 或者（[0b100, 0b1]）
```
示例2:
```
 输入：num = 1
 输出：[2, -1]
```
提示:
```
num的范围在[1, 2147483647]之间；
如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
```
## 解法
### 思路
暴力：
- 算出当前值的1的个数
- 从当前值+1和-1分别遍历，找到和当前值的1的个数相同的值
- 但如果输入值是Integer.MAX_VALUE，那会超时
### 代码
```java
class Solution {
    public int[] findClosedNumbers(int num) {
        int count = count1(num);
        int[] ans = new int[]{-1, -1};
        int large =  num + 1;
        while (large >= 1) {
            if (count == count1(large)) {
                ans[0] = large;
                break;
            }

            large++;
        }

        int small = num - 1;
        while (small >= 1) {
            if (count == count1(small)) {
                ans[1] = small;
                break;
            }

            small--;
        }

        return ans;
    }

    private int count1(int num) {
        int count = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num >>= 1;
        }

        return count;
    }
}
```
## 解法二
### 思路
- 比当前值大，从低位开始，找到第一组`01`，将其转换为`10`，且将其之前的所有1移到相对低位上
- 比当前值小，从地位开始，找到第一组`10`，将其转换成`01`，并将其之前的所有1移到`01`前的相对高位
### 代码
```java
class Solution {
    public int[] findClosedNumbers(int num) {
        String str = Integer.toBinaryString(num);
        int len = str.length(), count = 0, index = -1;
        char[] small = str.toCharArray();
        int[] ans = new int[]{-1, -1};

        for (int i = len - 1; i > 0; i--) {
            if (small[i] == '0' && small[i - 1] == '1') {
                 small[i] = '1';
                 small[i - 1] = '0';
                 index = i + 1;
                 break;
            }

            if (small[i] == '1') {
                count++;
            }
        }

        if (index != -1) {
            for (; index < len; index++) {
                if (count-- > 0) {
                    small[index] = '1';
                } else {
                    small[index] = '0';
                }
            }
            ans[1] = Integer.parseInt(new String(small), 2);
            index = -1;
        }

        char[] large = ("0" + str).toCharArray();
        count = 0;
        for (int i = len; i > 0; i--) {
            if (large[i] == '1' && large[i - 1] == '0') {
                large[i] = '0';
                large[i - 1] = '1';
                index = i + 1;
                break;
            }

            if (large[i] == '1') {
                count++;
            }
        }

        if (index != -1) {
            for (int i = len; i >= index; i--) {
                if (count-- > 0) {
                    large[i] = '1';
                } else {
                    large[i] = '0';
                }
            }

            long largeNum = Long.parseLong(new String(large), 2);

            if (largeNum <= Integer.MAX_VALUE) {
                ans[0] = (int) largeNum;
            }
        }

        return ans;
    }
}
```
# Interview_0507_配对交换
## 题目
配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。

示例1:
```
 输入：num = 2（或者0b10）
 输出 1 (或者 0b01)
```
示例2:
```
 输入：num = 3
 输出：3
```
提示:
```
num的范围在[0, 2^30 - 1]之间，不会发生整数溢出。
```
## 解法
### 思路
- `0x55555555`代表了：`01010101010101010101010101010101`，可以用这个数与`num`相与，获得奇数上的值
- `0xaaaaaaaa`代表了：`10101010101010101010101010101010`，可以用这个数与`num`相与，获得偶数上的值
- 然后分别将奇数的值左移，偶数的值右移，再将移动后的值相或，就能得到结果
### 代码
```java
class Solution {
    public int exchangeBits(int num) {
        return ((num & 0x55555555) << 1) | ((num & 0xaaaaaaaa) >>> 1);
    }
}
```
# LeetCode_1162_地图分析
## 题目
你现在手里有一份大小为N x N 的『地图』（网格）grid，上面的每个『区域』（单元格）都用0和1标记好了。其中0代表海洋，1代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。

我们这里说的距离是『曼哈顿距离』（Manhattan Distance）：(x0, y0) 和(x1, y1)这两个区域之间的距离是|x0 - x1| + |y0 - y1|。

如果我们的地图上只有陆地或者海洋，请返回-1。

示例 1：
```
输入：[[1,0,1],[0,0,0],[1,0,1]]
输出：2
解释： 
海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
```
示例 2：
```
输入：[[1,0,0],[0,0,0],[0,0,0]]
输出：4
解释： 
海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
```
提示：
```
1 <= grid.length == grid[0].length<= 100
grid[i][j] 不是 0 就是 1
```
## 解法
### 思路
bfs：
- 将所有陆地节点放入队列作为第一层节点
- 每一次都将从陆地节点出发的新节点是海洋的部分放入队列
- 记录层数，直到队列中没有节点为止
- 最后一层就是最远的距离
### 代码
```java
class Solution {
    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int row = grid.length, col = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        if (queue.isEmpty() || queue.size() == row * col) {
            return -1;
        }

        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int ans = -1;
        while (!queue.isEmpty()) {
            ans++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int x = arr[0], y = arr[1];

                for (int[] direction : directions) {
                    int nx = x + direction[0], ny = y + direction[1];
                    if (nx >= 0 && nx < row && ny >= 0 && ny < col && grid[nx][ny] == 0) {
                        grid[nx][ny] = 1;
                        queue.offer(new int[]{nx, ny});
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
动态规划：
- `dp[i][j]`：横坐标i，纵坐标j的位置上，离出发陆地最大的距离
- 初始化：将所有陆地设置为0，并将海洋设置为int最大值，这样在状态转移过程中可以通过递增并和原有值比较，取最小值的方式来计算距离
- 状态转移方程：从左上到右下和从右上到左下
    - `dp[i][j] = min(dp[i][j], dp[i - 1][j] + 1)`
    - `dp[i][j] = min(dp[i][j], dp[i + 1][j] + 1)`
    - `dp[i][j] = min(dp[i][j], dp[i][j - 1] + 1)`
    - `dp[i][j] = min(dp[i][j], dp[i][j + 1] + 1)`
### 代码
```java
class Solution {
    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = grid[i][j] == 0 ? Integer.MAX_VALUE : 0;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != 0) {
                    continue;
                }

                if (i - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1 < 0 ? Integer.MAX_VALUE : dp[i - 1][j] + 1);
                }

                if (j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1 < 0 ? Integer.MAX_VALUE : dp[i][j - 1] + 1);
                }
            }
        }

        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    continue;
                }

                if (i + 1 < row) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1 < 0 ? Integer.MAX_VALUE : dp[i + 1][j] + 1);
                }

                if (j + 1 < col) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1 < 0 ? Integer.MAX_VALUE : dp[i][j + 1] + 1);
                }
            }
        }
        
        int ans = -1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    ans = Math.max(dp[i][j], ans);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```
# Interview_0508_绘制直线
## 题目
绘制直线。有个单色屏幕存储在一个一维数组中，使得32个连续像素可以存放在一个 int 里。屏幕宽度为w，且w可被32整除（即一个 int 不会分布在两行上），屏幕高度可由数组长度及屏幕宽度推算得出。请实现一个函数，绘制从点(x1, y)到点(x2, y)的水平线。

给出数组的长度 length，宽度 w（以比特为单位）、直线开始位置 x1（比特为单位）、直线结束位置 x2（比特为单位）、直线所在行数 y。返回绘制过后的数组。

示例1:
```
 输入：length = 1, w = 32, x1 = 30, x2 = 31, y = 0
 输出：[3]
 说明：在第0行的第30位到第31为画一条直线，屏幕表示为[0b000000000000000000000000000000011]
```
示例2:
```
 输入：length = 3, w = 96, x1 = 0, x2 = 95, y = 0
 输出：[-1, -1, -1]
```
## 解法
### 思路
题目很奇怪，尽量理解：
- length代表一行的int个数
- w代表了一行的bit位数，`w = length * 32`
- x1和x2代表当前行为1的位的坐标
- y代表所在行
### 代码
```java
class Solution {
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] ans = new int[length];
        int offset = y * w / 32;
        int head = x1 / 32 + offset;
        int rear = x2 / 32 + offset;
        for (int i = head; i <= rear; i++) {
            ans[i] = -1;
        }
        ans[head] &= -1 >>> (x1 % 32);
        ans[rear] &= Integer.MIN_VALUE >> (x2 % 32);
        return ans;
    }
}
```
# Interview_0801_三步问题
## 题目
三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。

示例1:
```
 输入：n = 3 
 输出：4
 说明: 有四种走法
```
示例2:
```
 输入：n = 5
 输出：13
```
提示:
```
n范围在[1, 1000000]之间
```
## 解法
### 思路
动态规划：
- `dp[i]`：第i级台阶有多少种可能
- 状态转移方程：`dp[i] = dp[i - 1] + dp[i - 2] + dp[i -3]`
- 初始化：`dp[0] = 0; dp[1] = 1; dp[2] = 2; dp[3] = 4`
### 代码
```java
class Solution {
    public int waysToStep(int n) {     
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
            dp[i] = (dp[i] + dp[i - 3]) % 1000000007;
        }
        
        return dp[n];
    }
}
```
## 解法二
### 思路
变量替代数组
### 代码
```java
class Solution {
    public int waysToStep(int n) {     
        if (n < 3) {
            return n;
        }

        if (n == 3) {
            return 4;
        }

        int a = 1, b = 2, c = 4;

        for (int i = 4; i <= n; i++) {
            int ans = a;
            ans = (ans + b) % 1000000007;
            ans = (ans + c) % 1000000007;

            a = b;
            b = c;
            c = ans;
        }

        return c;
    }
}
```