# Interview_1714_最小K个数
## 题目
设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。

示例：
```
输入： arr = [1,3,5,7,2,4,6,8], k = 4
输出： [1,2,3,4]
```
提示：
```
0 <= len(arr) <= 100000
0 <= k <= min(100000, len(arr))
```
## 解法
### 思路
快排
### 代码
```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        
        Arrays.sort(arr);
        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }
}
```
## 解法二
### 思路
小顶堆
### 代码
```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int num : arr) {
            queue.offer(num);
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            if (queue.isEmpty()) {
                return ans;
            }
            
            ans[i] = queue.poll();
        }
        
        return ans;
    }
}
```
## 解法三
### 思路
自己实现快排
- 题目要求返回的数组元素不需要排序，所以只要partition的坐标位置与k-1相等就可以
- 因为确定partition的过程就是将小于当前坐标应有元素的值全部放置到了该坐标左侧
### 代码
```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        int head = 0, tail = arr.length - 1;
        while (head < tail) {
            int pivot = partition(arr, head, tail);
            if (pivot == k - 1) {
                break;
            } else if (pivot < k - 1) {
                head = pivot + 1;
            } else {
                tail = pivot - 1;
            }
        }

        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }

    private int partition(int[] arr, int head, int tail) {
        int low = arr[head];
        while (head < tail) {
            while (head < tail && arr[tail] >= low) {
                tail--;
            }
            arr[head] = arr[tail];
            
            while (head < tail && arr[head] <= low) {
                head++;
            }
            arr[tail] = arr[head];
        }
        
        arr[head] = low;
        return head;
    }
}
```
# Interview_1715_最长单词
## 题目
给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。

示例：
```
输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
输出： "dogwalker"
解释： "dogwalker"可由"dog"和"walker"组成。
```
提示：
```
0 <= len(words) <= 100
1 <= len(words[i]) <= 100
```
## 解法
### 思路
set+递归：
- 对字符串进行排序：
    - 长度降序
    - 字符升序
- 根据字符串数组生成set
- 遍历排序后的字符串数组
- 递归：
    - 退出条件：当字符串长度为0，返回true。代表之前所有字符在字符串数组中都能找到对应的字符串
    - 过程；
        - 从1开始遍历字符串的字符坐标，判断0到i生成的字符串是否在set中存在，且不能是自己
        - 如果存在，继续递归，并从i+1位置开始
### 代码
```java
class Solution {
    public String longestWord(String[] words) {
        Arrays.sort(words, (x1, x2) -> {
            if (x1.length() == x2.length()) {
                return x1.compareTo(x2);
            }

            return x2.length() - x1.length();
        });

        Set<String> set = new HashSet<>(Arrays.asList(words));

        for (String word : words) {
            if (recurse(word, 0, set)) {
                return word;
            }
        }
        
        return "";
    }

    private boolean recurse(String word, int start, Set<String> set) {
        if (start >= word.length()) {
            return true;
        }

        boolean flag = false;
        for (int i = start; i < word.length(); i++) {
            String tmp = word.substring(start, i + 1);
            if (!Objects.equals(tmp, word) && set.contains(word.substring(start, i + 1))) {
                flag = recurse(word, i + 1, set);
            }

            if (flag) {
                return true;
            }
        }

        return false;
    }
}
```
# Interview_1717_多次搜索
## 题目
给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。

示例：
```
输入：
big = "mississippi"
smalls = ["is","ppi","hi","sis","i","ssippi"]
输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
```
提示：
```
0 <= len(big) <= 1000
0 <= len(smalls[i]) <= 1000
smalls的总字符数不会超过 100000。
你可以认为smalls中没有重复字符串。
所有出现的字符均为英文小写字母。
```
## 解法
### 思路
使用indexOf暴力求解
- 注意small为空字符串的特殊情况
### 代码
```java
class Solution {
    public int[][] multiSearch(String big, String[] smalls) {
        int[][] ans = new int[smalls.length][];
        for (int i = 0; i < smalls.length; i++) {
            List<Integer> list = new ArrayList<>();
            String small = smalls[i];
            if (Objects.equals(small, "")) {
                ans[i] = new int[0];
                continue;
            }
            
            int index = 0, pos = big.indexOf(small, index);
            while (pos != -1) {
                list.add(pos);
                index = pos + 1;
                pos = big.indexOf(small, index);
            }
            ans[i] = list.stream().mapToInt(x -> x).toArray();
        }
        return ans;
    }
}
```
## 解法二
### 思路
字典树：
- 字典树节点属性：
    - children：子节点
    - flag：代表当前节点是否有字符串在此终止
    - index：代表该终止的字符串在smalls数组中的位置
- 字典树属性：
    - root：根节点
    - ans：保存当前small出现在big中的坐标位置
- insert方法：
    - 用于将smalls中的所有字符串插入到字典树中
- update方法：
    - 带入一个字符串，遍历这个字符串，查看是否在字典树中有对应的单词可以匹配
    - 如果有匹配就将该单词放入ans中
    - 如果字符串没有遍历完就继续遍历
- 主体逻辑：
    - 初始化字典树
    - 遍历smalls数组，将small插入到字典树中
    - 从[0,len]开始不断移动big字符串起始的坐标，缩短这个big，并带入到字典树中进行update
- 最终返回字典树中的ans
### 代码
```java
class Solution {
    public int[][] multiSearch(String big, String[] smalls) {
        TrieTree tree = new TrieTree(smalls);
        
        for (int i = 0; i < smalls.length; i++) {
            tree.insert(smalls[i], i);
        }
        
        int len = big.length();
        for (int i = 0; i < len; i++) {
            tree.update(big.substring(i, len), i);
        }
        
        int[][] ans = new int[smalls.length][];
        for (int i = 0; i < smalls.length; i++) {
            ans[i] = tree.ans[i].stream().mapToInt(x -> x).toArray();
        }
        return ans;
    }
}

class TrieTree {
    class TrieNode {
        private TrieNode[] children;
        private boolean flag;
        private int index;

        public TrieNode() {
            children = new TrieNode[26];
            flag = false;
            index = -1;
        }
    }

    List<Integer>[] ans;
    TrieNode root;

    public TrieTree(String[] smalls) {
        root = new TrieNode();
        int len = smalls.length;
        ans = new List[len];
        for (int i = 0; i < len; i++) {
            ans[i] = new ArrayList<>();
        }
    }

    public void insert(String word, int pos) {
        int len = word.length();
        char[] cs = word.toCharArray();

        TrieNode pivot = root;
        for (int i = 0; i < cs.length; i++) {
            int index = cs[i] - 'a';

            if (pivot.children[index] == null) {
                pivot.children[index] = new TrieNode();
            }

            pivot = pivot.children[index];
        }

        pivot.flag = true;
        pivot.index = pos;
    }

    public void update(String word, int start) {
        int len = word.length();
        char[] cs = word.toCharArray();

        TrieNode pivot = root;
        for (int i = 0; i < len; i++) {
            int index = cs[i] - 'a';

            TrieNode node = pivot.children[index];
            if (node == null) {
                return;
            }

            if (node.flag) {
                ans[node.index].add(start);
            }

            pivot = node;
        }
    }
}
```