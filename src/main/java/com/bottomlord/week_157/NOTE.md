# [LeetCode_732_我的日程安排III](https://leetcode.cn/problems/my-calendar-iii/)
## 解法
### 思路
不需要query的线段树
- 动态开点
- 懒加载
### 代码
```java
class MyCalendarThree {

    public MyCalendarThree() {
        this.root = new Node();
    }
    
    public int book(int start, int end) {
        update(root, 0, (int)1e9, start, end - 1, 1);
        return root.val;
    }

    private Node root;

    private void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && r >= end) {
            node.val += val;
            node.add += val;
            return;
        }

        pushDown(node);
        int mid = (start + end) >> 1;

        if (l <= mid) {
            update(node.left, start, mid, l, r, val);
        }

        if (r > mid) {
            update(node.right, mid + 1, end, l, r, val);
        }

        pushUp(node);
    }

    private void pushDown(Node node) {
        if (node.left == null) {
            node.left = new Node();
        }

        if (node.right == null) {
            node.right = new Node();
        }

        if (node.add == 0) {
            return;
        }

        node.left.val += node.add;
        node.right.val += node.add;
        node.left.add += node.add;
        node.right.add += node.add;
        node.add = 0;
    }

    private void pushUp(Node node) {
        node.val = Math.max(node.left.val, node.right.val);
    }

    private class Node {
        private Node left, right;
        private int val, add;
    }
}
```
# [LeetCode_676_实现一个魔法字典](https://leetcode.cn/problems/implement-magic-dictionary/)
## 解法
### 思路
哈希表
- build的时候，初始化map，key为int值，对应字符串长度，value为list，放对应key长度的字符串
- search的时候，判断是否有word的长度对应的字符串，如果有，就判断是否有差异字符个数是1的字符串，如果有就返回true，否则就返回false
### 代码
```java
class MagicDictionary {
    private Map<Integer, List<String>> map;
    public MagicDictionary() {
        map = new HashMap<>();
    }

    public void buildDict(String[] dict) {
        for (String word : dict) {
            map.computeIfAbsent(word.length(), x -> new ArrayList<>()).add(word);
        }
    }

    public boolean search(String word) {
        if (!map.containsKey(word.length())) {
            return false;
        }

        for (String letter : map.get(word.length())) {
            int count = 0;
            for (int i = 0; i < word.length(); i++) {
                if (letter.charAt(i) != word.charAt(i)) {
                    if (++count > 1) {
                        break;
                    }
                }
            }

            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
```
# [LeetCode_735_行星碰撞](https://leetcode.cn/problems/asteroid-collision/)
## 解法
### 思路
栈模拟
### 代码
```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
        for (int asteroid : asteroids) {
            if (stack.isEmpty()) {
                stack.push(asteroid);
                continue;
            }
            
            boolean push = false;
            while (!stack.isEmpty()) {
                Integer peek = stack.peek();
                if (peek * asteroid < 0 && peek > 0) {
                    if (peek < -asteroid) {
                        stack.pop();
                        push = true;
                    } else if (peek == -asteroid) {
                        stack.pop();
                        push = false;
                        break;
                    } else {
                        push = false;
                        break;
                    }
                } else {
                    push = true;
                    break;
                }
            }
            
            if (push) {
                stack.push(asteroid);
            }
        }
        
        int[] ans = new int[stack.size()];
        int index = 0;
        for (Integer num : stack) {
            ans[index++] = num;
        }
        return ans;
    }
}
```
# [LeetCode_745_前缀和后缀搜索](https://leetcode.cn/problems/prefix-and-suffix-search/)
## 解法
### 思路
- 生成两个字典树，分别按照正序和倒序来初始化
- 每一层都记录当前字符对应的字符串坐标
- 查询获取正向和反向字符串对应的坐标列表
- 通过双指针从尾部（因为坐标列表一定是递增序列）开始找坐标值相等的值，找到后返回，否则返回-1
### 代码
```java
class WordFilter {
        private TrieTree p, n;
        public WordFilter(String[] words) {
            p = new TrieTree();
            n = new TrieTree();
            
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                p.insert(word, i);
                n.insert(reserve(word), i);
            }
        }

        public int f(String pref, String suff) {
            List<Integer> pis = p.query(pref),
                          nis = n.query(reserve(suff));

            int i = pis.size() - 1, j = nis.size() - 1;
            while (i >= 0 && j >= 0) {
                int pn = pis.get(i), nn = nis.get(j);
                if (pn == nn) {
                    return pn;
                }

                if (pn > nn) {
                    i--;
                } else {
                    j--;
                }
            }
            
            return -1;
        }

        private class TrieTree {
            private TrieNode root;

            public TrieTree() {
                this.root = new TrieNode();
            }

            public void insert(String str, int index) {
                TrieNode node = root;
                char[] cs = str.toCharArray();
            
                for (char c : cs) {
                    TrieNode child = node.children[c - 'a'];
                    if (child == null) {
                        child = new TrieNode();
                        node.children[c - 'a'] = child;
                    }

                    child.idxes.add(index);
                    node = child;
                }
            }

            public List<Integer> query(String str) {
                TrieNode node = root;
                char[] cs = str.toCharArray();

                for (char c : cs) {
                    TrieNode child = node.children[c - 'a'];
                    if (child == null) {
                        return new ArrayList<>();
                    }

                    node = child;
                }

                return node.idxes;
            }
        }

        private class TrieNode {
            private List<Integer> idxes;
            private TrieNode[] children;

            public TrieNode() {
                this.idxes = new ArrayList<>();
                this.children = new TrieNode[26];
            }
        }

        private String reserve(String str) {
            char[] cs = str.toCharArray();
            int l = 0, r = cs.length - 1;
            while (l < r) {
                char c = cs[l];
                cs[l] = cs[r];
                cs[r] = c;

                l++;
                r--;
            }

            return new String(cs);
        }
    }
```