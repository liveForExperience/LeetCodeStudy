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