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