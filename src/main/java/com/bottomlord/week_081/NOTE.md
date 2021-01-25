# [LeetCode_428_序列化和反序列化N叉树](https://leetcode-cn.com/problems/serialize-and-deserialize-n-ary-tree/)
## 解法
### 思路
- 序列化：
    - dfs遍历节点，并用sb来记录遍历到的值
    - 在下探继续递归的时候，需要再追加sb一个用来记录子节点有多少个的数值
    - 数值与数值之间用` `间隔
- 反序列化：
    - 将字符串按` `拆分成字符串数组
    - 使用一个全局变量i作为辅助参数，用来记录递归到的字符串坐标
    - 反序列化的规律就是，字符串数组`i`位置的是当前层的root，`i+1`位置是当前层的子节点数量 
### 代码
```java
class Codec {
    private int i = 0;
    public String serialize(Node root) {
        return root != null ? encode(root) : null;
    }

    public Node deserialize(String data) {
        return data != null ? decode(data.split(" ")) : null;
    }

    private String encode(Node node) {
        if (node == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(node.val).append(" ");
        sb.append(node.children.size()).append(" ");

        for (Node child : node.children) {
            sb.append(encode(child));
        }

        return sb.toString();
    }

    private Node decode(String[] arr) {
        if (i == arr.length) {
            return null;
        }

        Node root = new Node(Integer.parseInt(arr[i++]), new ArrayList<>());
        int childrenCount = Integer.parseInt(arr[i++]);

        for (int i = 0; i < childrenCount; i++) {
            Node node = decode(arr);
            root.children.add(node);
        }

        return root;
    }
}
```
## 解法二
### 思路
将函数改成无状态的，去掉全局变量i
### 代码
```java
class Codec {
    public String serialize(Node root) {
        return root != null ? encode(root) : null;
    }

    public Node deserialize(String data) {
        return data != null ? decode(data.split(" "), new Counter()) : null;
    }

    private String encode(Node node) {
        if (node == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(node.val).append(" ");
        sb.append(node.children.size()).append(" ");

        for (Node child : node.children) {
            sb.append(encode(child));
        }

        return sb.toString();
    }

    private Node decode(String[] arr, Counter counter) {
        if (counter.get() == arr.length) {
            return null;
        }
        
        Node root = new Node(Integer.parseInt(arr[counter.add()]), new ArrayList<>());
        int count = Integer.parseInt(arr[counter.add()]);
        
        for (int i = 0; i < count; i++) {
            Node node = decode(arr, counter);
            root.children.add(node);
        }
        
        return root;
    }
}

class Counter {
    private int count;
    
    public int add() {
        return this.count++;
    }
    
    public int get() {
        return this.count;
    }
}
```
# [LeetCode_431_将N叉树编码成二叉树](https://leetcode-cn.com/problems/encode-n-ary-tree-to-binary-tree/)
## 解法
### 思路
- Node转TreeNode：
    - 将每个节点的children节点转换成TreeNode，并依次作为右子树节点连接起来
    - 将这些兄弟节点的头节点，作为左子树节点连接到父节点上
- TreeNode转Node：
    - dfs遍历，左子树返回一个list，作为children，右子树遍历放入兄弟list中
### 代码
```java
class Codec {
    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        Queue<TreeNode> treeQueue = new ArrayDeque<>();
        TreeNode rootTreeNode = new TreeNode(root.val);
        treeQueue.offer(rootTreeNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            TreeNode treeNode = treeQueue.poll();

            if (node == null || treeNode == null) {
                continue;
            }

            List<Node> children = node.children;
            if (children == null || children.size() == 0) {
                continue;
            }

            TreeNode fakeTreeNode = new TreeNode(0);
            TreeNode pre = fakeTreeNode;

            for (Node child : children) {
                TreeNode childTreeNode = new TreeNode(child.val);
                if (child.children != null && child.children.size() != 0) {
                    queue.offer(child);
                    treeQueue.offer(childTreeNode);
                }

                pre.right = childTreeNode;
                pre = childTreeNode;
            }

            treeNode.left = fakeTreeNode.right;
        }

        return rootTreeNode;
    }

    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }

        return dfs(root, null);
    }

    private Node dfs(TreeNode treeNode, List<Node> bros) {
        if (treeNode == null) {
            return null;
        }

        Node node = new Node(treeNode.val);

        if (bros != null) {
            bros.add(node);
        }

        List<Node> children = new ArrayList<>();
        dfs(treeNode.left, children);
        node.children = children;

        dfs(treeNode.right, bros);

        return node;
    }
}
```