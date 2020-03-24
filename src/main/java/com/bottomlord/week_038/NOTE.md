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