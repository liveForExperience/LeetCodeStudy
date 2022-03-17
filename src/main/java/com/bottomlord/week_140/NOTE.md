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