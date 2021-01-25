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