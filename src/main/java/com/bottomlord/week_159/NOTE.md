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
