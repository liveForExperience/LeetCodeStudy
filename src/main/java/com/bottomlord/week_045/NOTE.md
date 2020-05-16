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
# Interview_1718_最短超串
## 题目
假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。

返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。

示例 1:
```
输入:
big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
small = [1,5,9]
输出: [7,10]
```
示例 2:
```
输入:
big = [1,2,3]
small = [4]
输出: []
```
提示：
```
big.length <= 100000
1 <= small.length <= 100000
```
## 解法
### 思路
哈希散列表
- 遍历small，生成map。
    - key为small数组元素
    - value存key在big中所在的坐标，初始为-1
- 初始small长度值count，作为需要找齐的元素的记录值
- 遍历big字符串：
    - 如果匹配到map中的key，判断是否已经找到过：
        - 如果是-1，那就是没有找到，就将当前元素放入map中，同时count值--
        - 如果已经找到，就直接将元素放入map中
    - 判断count值是否大于0
        - 如果是，说明所有元素都已经找到过，判断map中values的最小值，用当前坐标值减去这个最小值，再与暂存的最小值比较，如果是最小值，就更新返回的ans数组
        - 如果不是，就继续循环
### 代码
```java
class Solution {
    public int[] shortestSeq(int[] big, int[] small) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : small) {
            map.put(i, -1);
        }
        
        int count = small.length;
        int[] ans = new int[]{0, big.length - 1};
        
        for (int i = 0; i < big.length; i++) {
            int num = big[i];
            if (map.containsKey(num)) {
                if (map.get(num) == -1) {
                    count--;
                }
                
                map.put(num, i);
            }
            
            if (count <= 0) {
                int min = Collections.min(map.values());
                if (i - min < ans[1] - ans[0]) {
                    ans[0] = min;
                    ans[1] = i;
                }
            }
        }
        
        if (count > 0) {
            return new int[0];
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
解法一中，每次都会对values进行排序，找最小值。可以对这部分进行优化
- 使用一个指针记录临时子数组的起始坐标
- 遍历small数组生成map，起始值为-1
- 遍历big数组：
    - 如果map中包含当前元素，对应value+1，且count+1
    - 如果`count == small.length`，那么代表子数组已经找到
    - 遍历递增start
        - 如果map中包含start对应的元素，就将其value值-1。
        - 如果start对应的元素在map中的值不是0，说明这个start还可以递增，还有可以被消耗的small元素
        - 如果start对应的元素在map中的值是0，说明当前这个子数组是最短的了，可以进行比较判断
    - 遍历start结束，count--，也就是当前从start开始到i结束的数组范围内已经找不到所有small元素
- 最终返回结果数组，数组ans在初始化的时候在第一个元素放置-1，用来判断是否找到过子数组
### 代码
```java
class Solution {
	public int[] shortestSeq(int[] big, int[] small) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : small) {
            map.put(num, 0);
        }
        int count = 0, start = 0, size = small.length, len = big.length;
        int[] ans = new int[]{-1, len};

        for (int i = 0; i < len; i++) {
            int num = big[i];
            if (map.containsKey(num)) {
                if (map.get(num) == 0) {
                    count++;
                }

                map.put(num, map.get(num) + 1);
            }

            if (count == size) {
                while (start <= i) {
                    int startNum = big[start];
                    if (map.containsKey(startNum)) {
                        map.put(startNum, map.get(startNum) - 1);

                        if (map.get(startNum) == 0) {
                            if (i - start < ans[1] - ans[0]) {
                                ans[0] = start;
                                ans[1] = i;
                            }

                            start++;
                            break;
                        }
                    }
                    start++;
                }
                count--;
            }
        }

        return ans[0] == -1 ? new int[0] : ans;
    }
}
```
# Interview_1719_消失的两个数字
## 题目
给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？

以任意顺序返回这两个数字均可。

示例 1:
```
输入: [1]
输出: [2,3]
```
示例 2:
```
输入: [2,3]
输出: [1,4]
```
提示：
```
nums.length <= 30000
```
## 解法
### 思路
- 通过等差数列求和公式获得数组元素的sum值
- 遍历数组获得当前数组元素的和total
- 通过`sum - total`获得消失的两个数的和，并对其平分取下限，获得一个分解数mid
- 通过这个mid将数组分成两部分，两个消失的元素必定分别在两个数组中
- 再分别通过求和公式和遍历求和来获得这两个数，因为有可能会出现负数，还需要求绝对值
### 代码
```java
class Solution {
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2, sum = (1 + n) * n / 2, total = 0;
        for (int num : nums) {
            total += num;
        }
        
        int mid = (sum - total) / 2;
        
        int firstSum = (1 + mid) * mid / 2,
            secondSum = (mid + 1 + n) * (n - mid) / 2,
            firstTotal = 0, secondTotal = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= mid) {
                firstTotal += nums[i];
            } else {
                secondTotal += nums[i];
            }
        }
        
        return new int[]{Math.abs(firstSum - firstTotal), Math.abs(secondSum - secondTotal)};
    }
}
```
## 优化代码
### 思路
只要求得一个数，另一个数通过之前`sum - total`的值求差也就得到了。
### 代码
```java
class Solution {
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2, sum = (1 + n) * n / 2, total = 0;
        for (int num : nums) {
            total += num;
        }

        int twoSum = sum - total,
            mid = twoSum / 2;

        int firstSum = (1 + mid) * mid / 2,
                firstTotal = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= mid) {
                firstTotal += nums[i];
            }
        }
        
        int firstNum = Math.abs(firstSum - firstTotal);
        
        return new int[]{firstNum, twoSum - firstNum};
    }
}
```
## 解法三
### 思路
有两个数只出现一次，其他数出现2次的题的异或解法
- 找到两个值的异或值
- 找到两个值不同的一位
- 根据这一位将数字分成两部分进行异或
- 最终返回结果
### 代码
```java
class Solution {
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2; int xor = 0;
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }

        for (int num : nums) {
            xor ^= num;
        }

        int diff = xor & (-xor);

        int[] ans = new int[2];
        for (int i = 1; i <= n; i++) {
            if ((diff & i) == 0) {
                ans[0] ^= i;
            } else {
                ans[1] ^= i;
            }
        }

        for (int num : nums) {
            if ((diff & num) == 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }

        return ans;
    }
}
```
# LeetCode_560_和为k的子数组
## 题目
给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :
```
输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
```
说明 :
```
数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
```
## 解法
### 思路
枚举：
- 嵌套循环
### 代码
```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    ans += 1;
                }
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
前缀和+hashmap
- 定义map，key为以i为结尾的前缀和，value为出现次数，初始化一个(0，1)
- 遍历数组，计算i为结尾的前缀和，放入map中
- 判断当前前缀和-k的值是否存在，如果存在就说明有子数组，个数就是该key对应的value，做一下累加就好了
### 代码
```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        
        int sum = 0, ans = 0;
        for (int num : nums) {
            sum += num;
            ans += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return ans;
    }
}
```
# LeetCode_25_K个一组翻转链表
## 题目
给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

示例：
```
给你这个链表：1->2->3->4->5

当 k = 2 时，应当返回: 2->1->4->3->5

当 k = 3 时，应当返回: 3->2->1->4->5
```
说明：
```
你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
```
## 解法
### 思路
暴力：
- 遍历一遍链表，根据k记录每段的起始和结束节点，放入不同的list中
- 翻转链表
    - 如果链表长度不能被k整除，最后一段不翻转
- 如果能被k整除，最后一个结尾节点的next指向null
- 遍历存放结尾节点的list，将头尾相连
- 最终返回头节点list中的第一个节点
### 代码
```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        
        if (k == 1) {
            return head;
        }
        
        LinkedList<ListNode> heads = new LinkedList<>(),
                             tails = new LinkedList<>();

        ListNode node = head, pre = null;
        int count = 0;
        while(node != null) {
            count++;
            if (count % k == 1) {
                tails.add(node);
            }

            if (count % k == 0) {
                heads.add(node);
            }

            node = node.next;
        }

        if (heads.size() != tails.size()) {
            heads.add(tails.getLast());
            tails.removeLast();
        }

        node = head;
        while (node != null) {
            if (heads.size() != tails.size()) {
                if (node == heads.getLast()) {
                    while (node != null) {
                        pre = node;
                        node = node.next;
                    }
                    break;
                }
            }

            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }

        if (heads.size() == tails.size()) {
            tails.getLast().next = null;
        }

        for (int i = 0; i < tails.size() && i + 1 < heads.size(); i++) {
            tails.get(i).next = heads.get(i + 1);
        }

        return heads.getFirst();
    }
}
```