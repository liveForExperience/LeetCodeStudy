# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_919_完全二叉树插入器](https://leetcode.cn/problems/complete-binary-tree-inserter/)
## 解法
### 思路
bfs+队列
- bfs遍历二叉树，将倒数第二层没有2个子节点，和最后一层的叶子节点，按照顺序放入队列中
- 插入二叉树时，就依次从队列中去除节点进行组装
  - 注意将有了2个子节点的父节点从队列中去除
  - 将新建的子节点放入队列尾部
### 代码
```java
class CBTInserter {

    private TreeNode root;
    private Queue<TreeNode> queue = new ArrayDeque<>();

    public CBTInserter(TreeNode root) {
        this.root = root;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                TreeNode node = q.poll();
                if (node == null) {
                    continue;
                }

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }

                if (node.left == null || node.right == null) {
                    queue.offer(node);
                }
            }
        }
    }

    public int insert(int val) {
        TreeNode node = new TreeNode(val);

        if (queue.isEmpty()) {
            this.root = node;
            return 0;
        }
        
        TreeNode parent = queue.peek();
        if (parent.left == null) {
            parent.left = node;
        } else if (parent.right == null) {
            parent.right = node;
            queue.poll();
        }
        
        queue.offer(node);
        return parent.val;
    }

    public TreeNode get_root() {
        return root;
    }
}
```
# [LeetCode_1331_数组序号转换](https://leetcode.cn/problems/rank-transform-of-an-array/)
## 解法
### 思路
- 建立数组中数字与坐标之间的映射关系
- 将数组去重
- 将去重后的数组进行排序
- 遍历排序后的去重数组
  - 获取当前排序后的元素值
  - 到映射关系中找到对应的坐标
  - 在结果数组中的对应坐标位置，将当前去重数组的坐标+1后放入到结果数组中，作为排序结果
- 遍历结束后，返回结果
### 代码
```java
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }

        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        
        int[] x = new int[set.size()];
        int index = 0;
        for (Integer num : set) {
            x[index++] = num;
        }
        
        Arrays.sort(x);

        int[] ans = new int[n];

        for (int i = 0; i < x.length; i++) {
            int num = x[i];
            List<Integer> indexes = map.get(num);
            for (Integer idx : indexes) {
                ans[idx] = i + 1;
            }
        }
        
        return ans;
    }
}
```