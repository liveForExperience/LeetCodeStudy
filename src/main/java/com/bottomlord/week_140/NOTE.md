# [LeetCode_599_两个列表的最小索引总和](https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String> ans = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.computeIfAbsent(list1[i], x -> new ArrayList<>()).add(i);
        }

        for (int i = 0; i < list2.length; i++) {
            map.computeIfAbsent(list2[i], x -> new ArrayList<>()).add(i);
        }

        int min = Integer.MAX_VALUE;
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() != 2) {
                continue;
            }
            
            int count = entry.getValue().get(0) + entry.getValue().get(1);
            
            if (count < min) {
                ans.clear();
                ans.add(entry.getKey());
                min = count;
            } else if (count == min) {
                ans.add(entry.getKey());
            }
        }
        
        return ans.toArray(new String[0]);
    }
}
```
# [LeetCode_2044_1_统计按位或能得到最大值的子集数目](https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets/)
## 解法
### 思路
回溯+计数
### 代码
```java
class Solution {
    int max = Integer.MIN_VALUE, ans = 0;
    public int countMaxOrSubsets(int[] nums) {
        backTrack(nums, 0, 0);
        return ans;
    }
    
    private void backTrack(int[] nums, int start, int num) {
        if (start >= nums.length) {
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            int cur = num | nums[i];
            if (cur > max) {
                max = cur;
                ans = 1;
            } else if (cur == max) {
                ans++;
            }
            
            backTrack(nums, i + 1, cur);
        }
    }
}
```
# [LeetCode_2200_找出数组中的所有K近邻下标](https://leetcode-cn.com/problems/find-all-k-distant-indices-in-an-array/)
## 解法
### 思路
- 先找到key对应的坐标index
- 遍历所有index，根据k获取窗口头尾坐标
- 遍历头尾坐标，将结果存储在与nums一样长度的布尔数组中，设置为true进行标记
- 遍历布尔数组，将为true的嘴硬坐标放入结果列表中，并在循环结束后返回该列表
### 代码
```java
class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                indexes.add(i);
            }
        }

        boolean[] arr = new boolean[nums.length];
        for (Integer index : indexes) {
            int head = Math.max(0, index - k),
                tail = Math.min(nums.length - 1, index + k);
            
            for (int i = head; i <= tail; i++) {
                arr[i] = true;
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                ans.add(i);
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_432_全O1的数据结构](https://leetcode-cn.com/problems/all-oone-data-structure/)
## 解法
### 思路
- 2个map+一个双向链表
  - keyMap存储key对应的个数
  - valueMap存储个数对应的双向链表节点，这个节点里保存个数对应的key的集合
  - 双向链表：有一个head，一个tail，从head开始到tail，按照升序的顺序排列
- inc：
  - 判断是不是新的key，如果是
    - 在keyMap里新增
    - 在valueMap里找1对应的节点
      - 不存在就新增，然后key放进去，node放进valueMap
      - 存在就直接key放进去
  - 不是新的key，那就找key对应的原个数，并计算新个数
  - 通过原个数，找到valueMap里的节点，删除当前的key
  - 通过新个数，在valueMap里找节点
    - 如果没找到，就新增node，把key放进去，并基于oldNode添加到双向链表中
    - 如果找到，就把key放进去
  - 如果oldNode空了，就把oldNode从双向链表，valueMap中删除
- dec：思路和inc类似
- getMaxKey：
  - 如果是空链表，就返回空字符串，如果不是，就返回tail的前一个节点的任意字符串
- getMinKey：
  - 如果是空链表，就返回空字符串，如果不是，就返回head的后一个节点的任意字符串
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
                node.addKey(key);
                valueMap.put(1, node);

                if (head.next == tail) {
                    head.next = node;
                    node.pre = head;
                    node.next = tail;
                    tail.pre = node;
                    return;
                }

                node.pre = head;
                node.next = head.next;
                head.next = node;
                node.next.pre = node;
                return;
            }

            node = valueMap.get(1);
            node.addKey(key);
            return;
        }

        int oldValue = keyMap.get(key), curValue = oldValue + 1;
        keyMap.put(key, curValue);
        DoubleLinkedNode oldNode = valueMap.get(oldValue);
        oldNode.removeKey(key);

        DoubleLinkedNode curNode;
        if (!valueMap.containsKey(curValue)) {
            curNode = new DoubleLinkedNode(curValue);
            valueMap.put(curValue, curNode);
            oldNode.next.pre = curNode;
            curNode.pre = oldNode;
            curNode.next = oldNode.next;
            oldNode.next = curNode;
        }

        curNode = valueMap.get(curValue);
        curNode.addKey(key);

        if (oldNode.isEmpty()) {
            oldNode.pre.next = oldNode.next;
            oldNode.next.pre = oldNode.pre;
            valueMap.remove(oldValue);
        }
    }

    public void dec(String key) {
        if (!keyMap.containsKey(key)) {
            return;
        }

        int oldValue = keyMap.get(key);
        if (oldValue == 1) {
            keyMap.remove(key);
            DoubleLinkedNode oldNode = valueMap.get(1);
            oldNode.removeKey(key);

            if (oldNode.isEmpty()) {
                oldNode.pre.next = oldNode.next;
                oldNode.next.pre = oldNode.pre;
                valueMap.remove(1);
            }

            return;
        }

        int curValue = oldValue - 1;
        keyMap.put(key, curValue);
        DoubleLinkedNode oldNode = valueMap.get(oldValue);
        oldNode.removeKey(key);

        DoubleLinkedNode curNode;
        if (!valueMap.containsKey(curValue)) {
            curNode = new DoubleLinkedNode(curValue);
            curNode.next = oldNode;
            curNode.pre = oldNode.pre;
            curNode.next.pre = curNode;
            curNode.pre.next = curNode;
        } else {
            curNode = valueMap.get(curValue);
        }

        curNode.addKey(key);

        if (oldNode.isEmpty()) {
            oldNode.pre.next = oldNode.next;
            oldNode.next.pre = oldNode.pre;
            valueMap.remove(oldValue);
        }

        valueMap.put(curValue, curNode);
    }

    public String getMaxKey() {
        return head.next == tail ? "" : tail.pre.set.stream().findFirst().get();
    }

    public String getMinKey() {
        return tail.pre == head ? "" : head.next.set.stream().findFirst().get();
    }

    private class DoubleLinkedNode {
        private final Integer val;
        private final Set<String> set;
        private DoubleLinkedNode pre, next;

        public DoubleLinkedNode(Integer val) {
            this.val = val;
            this.set = new HashSet<>();
        }

        public boolean isEmpty() {
            return this.set.isEmpty();
        }

        public void addKey(String key) {
            this.set.add(key);
        }

        public void removeKey(String key) {
            this.set.remove(key);
        }
    }
}
```
# [LeetCode_720_词典中的最长单词]()
## 解法
### 思路
字典树
### 代码
```java
class Solution {
    public String longestWord(String[] words) {
        TrieTree tree = new TrieTree();
        for (String word : words) {
            tree.insert(word);
        }
        
        String ans = "";
        for (String word : words) {
            if (tree.search(word)) {
                if (word.length() > ans.length() || (word.length() == ans.length() && word.compareTo(ans) < 0)) {
                    ans = word;
                }
            }
        }
        
        return ans;
    }

    private static class TrieTree {
        private TrieNode root;
        public TrieTree() {
            this.root = new TrieNode(' ');
        }

        public void insert(String word) {
            char[] cs = word.toCharArray();
            TrieNode node = root;
            for (char c : cs) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode(c);
                }
                
                node = node.children[c - 'a'];
            }
            
            node.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode node = root;
            char[] cs = word.toCharArray();

            for (char c : cs) {
                if (node.children[c - 'a'] == null || !node.children[c - 'a'].isEnd) {
                    return false;
                }
                
                node = node.children[c - 'a'];
            }
            
            return node.isEnd;
        }
    }

    private static class TrieNode {
        private char c;
        private boolean isEnd;
        private TrieNode[] children;

        public TrieNode(char c) {
            this.c = c;
            this.children = new TrieNode[26];
        }
    }
}
```
# [LeetCode_2043_简易银行系统](https://leetcode-cn.com/problems/simple-bank-system/)
## 解法
### 思路
模拟
### 代码
```java
class Bank {
    private long[] balance;
    private int len;
    public Bank(long[] balance) {
        this.balance = balance;
        this.len = balance.length;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 > len || account2 > len) {
            return false;
        }

        if (balance[account1 - 1] >= money) {
            balance[account1 - 1] -= money;
            balance[account2 - 1] += money;
            return true;
        } else {
            return false;
        }
    }

    public boolean deposit(int account, long money) {
        if (account > len) {
            return false;
        }

        balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account > len || balance[account - 1] < money) {
            return false;
        }
        
        balance[account - 1] -= money;
        return true;
    }
}
```
# [LeetCode_606_根据二叉树创建字符串](https://leetcode-cn.com/problems/construct-string-from-binary-tree/)
## 解法
### 思路
dfs
- 需要注意，如果left为空，但是right不为空，那么left需要有一个空的空格
### 代码
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
  public String tree2str(TreeNode root) {
    if (root == null) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    sb.append(root.val);
    String left = tree2str(root.left);
    if (!Objects.equals(left, "")) {
      sb.append("(").append(left).append(")");
    }

    String right = tree2str(root.right);
    if (!Objects.equals(right, "")) {
      if (Objects.equals(left, "")) {
        sb.append("(").append(")");
      }
      sb.append("(").append(right).append(")");
    }

    return sb.toString();
  }
}
```
## 解法二
### 思路
优化代码，减少StringBuilder的new次数
### 代码
```java
class Solution {
  public String tree2str(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    dfs(root, sb);
    return sb.substring(1, sb.length() - 1).toString();
  }

  private void dfs(TreeNode node, StringBuilder sb) {
    if (node == null) {
      return;
    }

    sb.append("(");

    sb.append(node.val);

    if (node.left != null) {
      dfs(node.left, sb);
    }

    if (node.right != null) {
      if (node.left == null) {
        sb.append("(").append(")");
      }

      dfs(node.right, sb);
    }

    sb.append(")");
  }
}
```
# [LeetCode_616_给字符串添加加粗标签](https://leetcode-cn.com/problems/add-bold-tag-in-string/)
## 解法
### 思路
字典树
- 字典树节点中存储一个深度值，代表当前节点对应字符串s的坐标值，用于方便定位
- 然后就是遍历s生成树
- 遍历words找到对应的节点，并判断从该节点出发是否能生成word，如果能就记录在boolean数组中
- 最后遍历boolean数组，生成字符串
### 代码
```java
class Solution {
    public String addBoldTag(String s, String[] words) {
        int len = s.length();
        boolean[] bucket = new boolean[len];
        char[] cs = s.toCharArray();
        TrieTree tree = new TrieTree();
        tree.insert(s);

        for (String word : words) {
            List<TrieNode> nodes = tree.listNode(word.charAt(0));
            for (TrieNode node : nodes) {
                if (tree.isWord(node, word)) {
                    for (int i = node.depth; i < node.depth + word.length(); i++) {
                        bucket[i] = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bucket.length;) {
            int count = 0;
            StringBuilder curSb = new StringBuilder();
            while (i < bucket.length && bucket[i]) {
                count++;
                curSb.append(cs[i++]);
            }

            if (count == 0) {
                sb.append(cs[i]);
                i++;
            } else {
                sb.append("<b>").append(curSb).append("</b>");
            }
        }

        return sb.toString();
    }

    private class TrieTree {
        private TrieNode root;
        public TrieTree() {
            this.root = new TrieNode(' ', -1);
        }

        public void insert(String s) {
            TrieNode node = root;
            char[] cs = s.toCharArray();
            for (int i = 0; i < cs.length; i++) {
                node = node.addChild(cs[i], i);
            }
        }

        public List<TrieNode> listNode(char c) {
            List<TrieNode> ans = new ArrayList<>();
            Queue<TrieNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TrieNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                if (node.c == c) {
                    ans.add(node);
                }

                for (TrieNode child : node.children) {
                    if (child == null) {
                        continue;
                    }

                    queue.offer(child);
                }
            }

            return ans;
        }

        public boolean isWord(TrieNode node, String word) {
            if (word == null || word.length() == 0) {
                return true;
            }

            if (node.c != word.charAt(0)) {
                return false;
            }

            char[] cs = word.toCharArray();
            for (int i = 1; i < cs.length; i++) {
                char c = cs[i];
                if (node.children[c] == null) {
                    return false;
                }

                node = node.children[c];
            }

            return true;
        }
    }

    private class TrieNode {
        private char c;
        private int depth;
        private TrieNode[] children;

        public TrieNode(char c, int depth) {
            this.c = c;
            this.depth = depth;
            this.children = new TrieNode[123];
        }

        public TrieNode addChild(char c, int depth) {
            if (this.children[c] != null) {
                return this.children[c];
            }

            this.children[c] = new TrieNode(c, depth);
            return this.children[c];
        }
    }
}
```
## 解法二
### 思路
不用字典树，直接3层遍历
### 代码
```java
class Solution {
    public String addBoldTag(String s, String[] words) {
        int n = s.length();
        boolean[] bucket = new boolean[n];
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            for (String word : words) {
                boolean flag = true;
                for (int j = 0; j < word.length(); j++) {
                    if (j + i >= n || cs[i + j] != word.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                
                if (!flag) {
                    continue;
                }
                
                for (int j = 0; j < word.length(); j++) {
                    bucket[i + j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bucket.length;) {
            int count = 0;
            StringBuilder curSb = new StringBuilder();
            while (i < bucket.length && bucket[i]) {
                count++;
                curSb.append(cs[i++]);
            }
            
            if (count == 0) {
                sb.append(cs[i++]);
            } else {
                sb.append("<b>").append(curSb).append("</b>");
            }
        }
        
        return sb.toString();
    }
}
```
# [LeetCode_624_数组列表中的最大距离](https://leetcode-cn.com/problems/maximum-distance-in-arrays/)
## 解法
### 思路
- 遍历arrays，记录4个东西：
  - 最大值
  - 最大值对应的坐标
  - 最小值
  - 最小值对应的坐标
- 如果最大值和最小值的坐标个数，不是都等于1，或者虽然都是1，但是值不同，那么说明存在不是同一个数组中的两个值能够形成最大距离
- 如果是在同一个数组里的，那么就分别遍历其他的最大值和最小值可能，找到最大的距离
### 代码
```java
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        List<Integer> minIndexes = new ArrayList<>(),
                maxIndexes = new ArrayList<>();

        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> array = arrays.get(i);
            int curMin = array.get(0), curMax = array.get(array.size() - 1);

            if (curMin < min) {
                min = curMin;
                minIndexes.clear();
                minIndexes.add(i);
            } else if (curMin == min) {
                minIndexes.add(i);
            }

            if (curMax > max) {
                max = curMax;
                maxIndexes.clear();
                maxIndexes.add(i);
            } else if (curMax == max) {
                maxIndexes.add(i);
            }
        }

        if (maxIndexes.size() > 1 || minIndexes.size() > 1 || !Objects.equals(maxIndexes.get(0), minIndexes.get(0))) {
            return max - min;
        }

        int ans = 0, maxIndex = maxIndexes.get(0), minIndex = minIndexes.get(0);
        for (int i = 0; i < arrays.size(); i++) {
            if (maxIndex != i) {
                ans = Math.max(ans, max - arrays.get(i).get(0));
            }

            if (minIndex != i) {
                ans = Math.max(ans, arrays.get(i).get(arrays.get(i).size() - 1) - min);
            }
        }

        return ans;
    }
}
```
# [LeetCode_640_求解方程](https://leetcode-cn.com/problems/solve-the-equation/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public String solveEquation(String equation) {
        int leftX = 0, rightX = 0, leftNum = 0, rightNum = 0;
        Set<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("=");

        char[] cs = equation.toCharArray();
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < cs.length; i++) {
            StringBuilder sb = new StringBuilder();
            while (i < cs.length && !operators.contains("" + cs[i])) {
                sb.append(cs[i++]);
            }

            tokens.add(sb.toString());

            if (i < cs.length && cs[i] != ' ') {
                tokens.add("" + cs[i]);
            }
        }

        boolean findEq = false;
        String preOperator = null;
        for (String token : tokens) {
            if (Objects.equals(token, "")) {
                continue;
            }

            if (isX(token)) {
                if (preOperator == null) {
                    if (findEq) {
                        rightX += countX(token);
                    } else {
                        leftX += countX(token);
                    }
                } else {
                    if (Objects.equals(preOperator, "+")) {
                        if (findEq) {
                            rightX += countX(token);
                        } else {
                            leftX += countX(token);
                        }
                    } else {
                        if (findEq) {
                            rightX -= countX(token);
                        } else {
                            leftX -= countX(token);
                        }
                    }
                }
                
                continue;
            }

            if (isEq(token)) {
                findEq = true;
                preOperator = null;
                continue;
            }

            if (isNum(token, operators)) {
                if (preOperator == null) {
                    if (findEq) {
                        rightNum += Integer.parseInt(token);
                    } else {
                        leftNum += Integer.parseInt(token);
                    }
                } else {
                    if (Objects.equals(preOperator, "+")) {
                        if (findEq) {
                            rightNum += Integer.parseInt(token);
                        } else {
                            leftNum += Integer.parseInt(token);
                        }
                    } else {
                        if (findEq) {
                            rightNum -= Integer.parseInt(token);
                        } else {
                            leftNum -= Integer.parseInt(token);
                        }
                    }
                }

                continue;
            }

            preOperator = token;
        }

        int xNum = leftX - rightX, num = rightNum - leftNum;
        if (xNum == 0) {
            return num == 0 ? "Infinite solutions" : "No solution";
        }

        return "x=" + (num / xNum);
    }

    private boolean isX(String token) {
        return token.contains("x");
    }

    private boolean isEq(String token) {
        return Objects.equals(token, "=");
    }

    private boolean isNum(String token, Set<String> operators) {
        return !operators.contains(token) && !token.contains("x");
    }

    private int countX(String token) {
        if (token.length() == 1) {
            return 1;
        }
        return Integer.parseInt(token.substring(0, token.length() - 1));
    }
}
```
# [LeetCode_2039_网络空闲时刻](https://leetcode-cn.com/problems/the-time-when-the-network-becomes-idle/)
## 解法
### 思路
- 通过数组生成邻接表，因为是无向图，所以是双向都要保存
- 通过bfs计算出数据服务器距离主服务器的最短距离
- 通过patience数组计算出每个节点最后收到数据包的秒数
- 通过计算秒数的最大值求得结果
  - 公式：time = pat * ((2 * dis - 1) / pat) + 2 * dis + 1
### 代码
```java
class Solution {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
        }

        Map<Integer, Integer> disMap = new HashMap<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                if (!disMap.containsKey(arr[0])) {
                    disMap.put(arr[0], arr[1]);
                }

                if (!map.containsKey(arr[0])) {
                    continue;
                }

                for (Integer next : map.get(arr[0])) {
                    if (disMap.containsKey(next)) {
                        continue;
                    }

                    queue.offer(new int[]{next, arr[1] + 1});
                }
            }
        }

        disMap.remove(0);

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : disMap.entrySet()) {
            int node = entry.getKey(), dis = entry.getValue(), pat = patience[node];
            int time = ((dis * 2 - 1) / pat) * pat, last = time + dis * 2 + 1;
            max = Math.max(max, last);
        }

        return max;
    }
}
```
## 解法二
### 思路
优化代码
- 使用list代替map作为邻接表
- 将遍历时间合并到bfs过程中
### 代码
```java
class Solution {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        int max = 0;
        Set<Integer> memo = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int count = queue.size();
            
            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }
                
                int node = arr[0], dis = arr[1], pat = patience[node];
                if (memo.contains(node)) {
                    continue;
                }
                
                if (dis != 0) {
                    max = Math.max(max, pat * ((2 * dis - 1) / pat) + 2 * dis + 1);
                }
                
                memo.add(node);
                for (Integer nextNode : graph.get(node)) {
                    queue.offer(new int[]{nextNode, dis + 1});
                }
            }
        }
        
        return max;
    }
}
```