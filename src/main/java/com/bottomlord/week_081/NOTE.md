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
# [LeetCode_432_全O1的数据结构](https://leetcode-cn.com/problems/all-oone-data-structure/)
## 解法
### 思路
hash表+双向链表
- O1复杂度情况，通过key查找次数可以通过hash表来实现
- 关键是如何获取最大值和最小值。
- 可以用一个hash表，key是次数值，value是一个自定义的双向链表`dLinkedNode`
- 这个`dLinkedNode`中不仅有前后节点的指针，还维护当前次数对应的key的集合
- 当操作增减的时候，就是在这个双向链表上操作
    - 加，就找这个双向链表的后一个节点，看值一不一样，一样的话，就加到key的集合里，如果不一样，就在这两个节点之间新增一个节点
    - 减，和加的操作类似，只是方向相反
- 属性：
    - keyMap，存储key和次数的映射关系，在增减的时候维护
    - countMap，存储次数和双向链表节点的映射关系，也在增减的时候维护
### 代码
```java
class AllOne {
    private Map<String, Integer> keyMap;
    private Map<Integer, DoubleLinkedNode> valueMap;
    private DoubleLinkedNode head, tail;

    public AllOne() {
        this.keyMap = new HashMap<>();
        this.valueMap = new HashMap<>();
        this.head = new DoubleLinkedNode(0);
        this.tail = new DoubleLinkedNode(0);
        head.next = tail;
        tail.pre = head;
    }

    public void inc(String key) {
        if (!keyMap.containsKey(key)) {
            keyMap.put(key, 1);

            DoubleLinkedNode node;
            if (!valueMap.containsKey(1)) {
                node = new DoubleLinkedNode(1);
                valueMap.put(1, node);

                if (head.next == tail) {
                    head.next = node;
                    node.pre = head;
                    tail.pre = node;
                    node.next = tail;
                    node.addKey(key);
                    return;
                }

                node.pre = head;
                node.next = head.next;
                head.next = node;
                node.next.pre = node;
            } else {
                node = valueMap.get(1);
            }

            node.addKey(key);

            return;
        }

        int curValue = keyMap.get(key), incValue = curValue + 1;

        keyMap.put(key, incValue);
        valueMap.get(curValue).removeKey(key);

        DoubleLinkedNode curNode = valueMap.get(curValue);

        DoubleLinkedNode incNode;
        if (!valueMap.containsKey(incValue)) {
            incNode = new DoubleLinkedNode(incValue);
            incNode.pre = curNode;
            incNode.next = curNode.next;
            incNode.next.pre = incNode;
            incNode.pre.next = incNode;
        } else {
            incNode = valueMap.get(incValue);
        }

        if (curNode.isEmpty()) {
            valueMap.remove(curValue);
            curNode.pre.next = curNode.next;
            curNode.next.pre = curNode.pre;
        }

        valueMap.put(incValue, incNode);
        incNode.addKey(key);
    }

    public void dec(String key) {
        if (!keyMap.containsKey(key)) {
            return;
        }

        if (keyMap.get(key) == 1) {
            keyMap.remove(key);
            valueMap.get(1).removeKey(key);

            DoubleLinkedNode node = valueMap.get(1);
            if (node.isEmpty()) {
                valueMap.remove(node.val);
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }

            return;
        }

        int curValue = keyMap.get(key), desValue = curValue - 1;
        keyMap.put(key, desValue);
        DoubleLinkedNode curNode = valueMap.get(curValue);
        curNode.removeKey(key);

        DoubleLinkedNode decNode;
        if (!valueMap.containsKey(desValue)) {
            decNode = new DoubleLinkedNode(desValue);
            decNode.pre = curNode.pre;
            decNode.next = curNode;
            decNode.pre.next = decNode;
            decNode.next.pre = decNode;
        } else {
            decNode = valueMap.get(desValue);
        }

        if (curNode.isEmpty()) {
            curNode.pre.next = curNode.next;
            curNode.next.pre = curNode.pre;
            valueMap.remove(curValue);
        }

        valueMap.put(desValue, decNode);
        decNode.addKey(key);
    }

    public String getMaxKey() {
        return tail.pre == head ? "" : tail.pre.set.stream().findFirst().get();
    }

    public String getMinKey() {
        return head.next == tail ? "" : head.next.set.stream().findFirst().get();
    }

    static class DoubleLinkedNode {
        private int val;
        private Set<String> set;
        private DoubleLinkedNode pre, next;

        public DoubleLinkedNode(int val) {
            this.val = val;
            this.set = new HashSet<>();
        }

        private void addKey(String key) {
            this.set.add(key);
        }

        private void removeKey(String key) {
            this.set.remove(key);
        }

        private boolean isEmpty() {
            return this.set.isEmpty();
        }
    }
}
```
# LeetCode_436_寻找右区间
## 解法
### 思路
- 初始化两个小顶堆，元素为数组`[start,end,index]`，并分别以start和end值作为比较进行排序
- 初始化一个结果数组，长度为intervals的长度
- 以end为排序条件的，称之为endQueue，以start为排序条件的，称之为startQueue
- 遍历endQueue，以此动作作为驱动，检查startQueue的堆顶是否有`start`大于endQueue的`end`的元素，没有的话就弹出，直到找到为止
- 如果找不到，就ans的对应index位置放置-1，否则就放置`startQueue`的index值
- 注意：结果里记录的start数组是可以重复的
### 代码
```java
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        PriorityQueue<int[]> startQueue = new PriorityQueue<>(Comparator.comparingInt(x -> x[0])),
                             endQueue = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        int[] ans = new int[len];
        Arrays.fill(ans, -1);
        
        for (int i = 0; i < intervals.length; i++) {
            int[] arr = new int[3];
            arr[0] = intervals[i][0];
            arr[1] = intervals[i][1];
            arr[2] = i;

            startQueue.offer(arr);
            endQueue.offer(arr);
        }

        while (!endQueue.isEmpty()) {
            int[] endArr = endQueue.poll();
            while (!startQueue.isEmpty()) {
                int[] startArr = startQueue.peek();
                if (startArr[0] >= endArr[1]) {
                    ans[endArr[2]] = startArr[2];
                    break;
                }
                
                startQueue.poll();
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_1631_最小体力消耗路径](https://leetcode-cn.com/problems/path-with-minimum-effort/submissions/)
## 解法
### 思路
bfs+二分查找
- 因为消耗体力的范围是[0,999999]，所以可以通过二分法寻找那个最小的消耗值
- 而判断二分出来的值是否是合理的值，就可以通过bfs来遍历二维数组，来判断
    - 如果能遍历到右下角位置，说明当前体力消耗是合理值，那么就可以继续找有没有更小的消耗值
    - 如果能不能遍历到，说明当前体力消耗值太大了，那么就寻找更大的消耗值
### 代码
```java
class Solution {
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath(int[][] heights) {
        int start = 0, end = 999999, ans = 0, row = heights.length, col = heights[0].length;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{0, 0});
            
            boolean[] memo = new boolean[row * col];
            memo[0] = true;
            
            while (!queue.isEmpty()) {
                int[] height = queue.poll();

                for (int i = 0; i < directions.length; i++) {
                    int r = height[0] + directions[i][0], c = height[1] + directions[i][1];
                    if (r >= 0 && r < row && c >= 0 && c < col && !memo[r * col + c] && Math.abs(heights[r][c] - heights[height[0]][height[1]]) <= mid) {
                        queue.offer(new int[]{r, c});
                        memo[r * col + c] = true;
                    }
                }
            }
            
            if (memo[row * col - 1]) {
                end = mid - 1;
                ans = mid;
            } else {
                start = mid + 1;
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
并查集
- 将所有点与点之间的关系，转换为边，然后存储在一个集合中，边记录了两个顶点的坐标及对应边的权值
- 然后根据集合元素的权值，对集合进行排序
- 然后遍历这个集合，将集合元素中的两个顶点在并查集中关联，同时检查是否左上和右下的顶点已经关联在一起，如果是，就停止遍历，同时，当前使用到的权值就是最小的权值
### 代码
```java
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length, col = heights[0].length;

        int[][] arrs = new int[row * (col - 1) + col * (row - 1)][3];
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int id = i * col + j;
                if (i > 0) {
                    arrs[index++] = new int[]{id - col, id, Math.abs(heights[i][j] - heights[i - 1][j])};
                }

                if (j > 0) {
                    arrs[index++] = new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])};
                }
            }
        }

        quickSort(arrs);
        UF uf = new UF(row * col);

        for (int[] arr : arrs) {
            int x = arr[0], y = arr[1], v = arr[2];
            uf.union(x, y);
            if (uf.isConnected(0, row * col - 1)) {
                return v;
            }
        }

        return 0;
    }

    private void quickSort(int[][] arr) {
        doQuickSort(arr, 0, arr.length - 1);
    }

    private void doQuickSort(int[][] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int partition = partition(arr, start, end);

        doQuickSort(arr, start, partition - 1);
        doQuickSort(arr, partition + 1, end);
    }

    private int partition(int[][] arr, int start, int end) {
        int[] pivot = arr[start];

        while (start < end) {
            while (start < end && arr[end][2] >= pivot[2]) {
                end--;
            }
            arr[start] = arr[end];

            while (start < end && arr[start][2] <= pivot[2]) {
                start++;
            }
            arr[end] = arr[start];
        }

        arr[start] = pivot;
        return start;
    }

    static class UF {
        private int[] parents;

        public UF(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public void union(int x, int y) {
            parents[find(x)] = find(y);
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
}
```
# [LeetCode_778_水位上升的游泳池中游泳](https://leetcode-cn.com/problems/swim-in-rising-water/)
## 解法
### 思路
bfs+二分查找
- 二分查找的范围是平台高度，也就是`[0,N*N-1]`
- 通过bfs判断当前中值是否可以走到右下角
### 代码
```java
public class Solution {

    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int swimInWater(int[][] grid) {
        int n = grid.length, start = grid[0][0], end = n * n - 1, ans = n * n - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{0, 0});
            
            boolean[] memo = new boolean[n * n];
            memo[0] = true;
            
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                
                for(int[] direction: directions){
                    int r = arr[0] + direction[0], c = arr[1] + direction[1];
                    
                    if (r >= 0 && r < n && c >= 0 && c < n && !memo[n * r + c] && grid[r][c] <= mid) {
                        queue.offer(new int[]{r, c});
                        memo[r * n + c] = true;
                    }
                }
            }
            
            if (memo[n * n - 1]) {
                end = mid - 1;
                ans = mid;
            } else {
                start = mid + 1;
            }
        }
        
        return ans;
    }

}
```
## 解法二
### 思路
并查集
- 将二维数组看作一个无向图，遍历这个二维数组，统计相邻顶点的坐标及之间的最大值，放入一个集合中
- 对这个集合进行排序
- 遍历集合，将集合元素根据顶点坐标进行关联，并判断左上和右下是否已经关联，如果是，当前平台高度就是结果
### 代码
```java
public class Solution {
public int swimInWater(int[][] grid) {
        int n = grid.length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                if (i > 0) {
                    list.add(new int[]{index - n, index, Math.max(grid[i - 1][j], grid[i][j])});
                }

                if (j > 0) {
                    list.add(new int[]{index - 1, index, Math.max(grid[i][j - 1], grid[i][j])});
                }
            }
        }

        list.sort(Comparator.comparingInt(x -> x[2]));
        
        UF uf = new UF(n * n);
        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i)[0], y = list.get(i)[1], v = list.get(i)[2];
            uf.union(x, y);
            if (uf.isConnected(0, n * n - 1)) {
                return v;
            }
        }
        
        return n * n - 1;
    }
    
    static class UF {
        private int[] parents;
        
        public UF(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n; i++) {
                this.parents[i] = i;
            }
        }
        
        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            
            return parents[x];
        }
        
        public void union(int x, int y) {
            parents[find(x)] = find(y);
        }
        
        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    } 

}
```