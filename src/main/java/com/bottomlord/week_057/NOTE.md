# LeetCode_163_缺失的区间
## 题目
给定一个排序的整数数组 nums，其中元素的范围在闭区间[lower, upper]当中，返回不包含在数组中的缺失区间。

示例：
```
输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
输出: ["2", "4->49", "51->74", "76->99"]
```
## 失败解法
### 失败原因
超时
### 思路
- 从`lower`到`upper`开始遍历，并暂存一个记录数组下标的`index`
- 逐一比较，如果不相等，就开始内层循环，继续逐一比较，直到相等为止
- 将内层循环的起始和结尾组成字符串放入list中
### 代码
```java
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        int index = 0, len = nums.length;
        for (int i = lower; i <= upper;) {
            if (index < len && i == nums[index]) {
                index++;
                i++;
            } else {
                int start = i;
                StringBuilder sb = new StringBuilder();
                sb.append(start);
                i++;
                while (i <= upper && (index >= len || i != nums[index])) {
                    i++;
                }
                int end = i - 1;
                if (start != end) {
                    sb.append("->").append(end);
                }
                ans.add(sb.toString());
            }
        }
        
        return ans;
    }
}
```
## 失败解法二
### 失败原因
示例元素导致计算时int越界
### 思路
基于失败解法，内层不再循环，因为是升序的，所以可以直接以`nums[index] - 1`作为生成的字符串的有边界元素
### 代码
```java
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        int index = 0, len = nums.length;
        for (int i = lower; i <= upper;) {
            if (index < len && i == nums[index]) {
                index++;
                i++;
            } else {
                int start = i;
                i++;
                String end = "";
                if (index >= len) {
                    end = "->" + upper;
                    i = upper + 1;
                } else if (i != nums[index]) {
                    end = "->" + nums[index];
                    i = nums[index];
                }
                list.add(start + end);
            }
        }
        
        return list;
    }
}
```
## 解法
### 思路
- 失败解法二不仅需要处理越界，还需要处理`nums`数组元素相等的情况
- 前两种解法都需要考虑`index`越界的问题，非常麻烦，如果外层循环是遍历`nums`数组，那么就可以少一部分的边界检查
- 另外还要考虑`nums`搜索完后，没有遍历到的区间内容，以及`nums`是空的情况
### 代码
```java
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            list.add(lower + (lower == upper ? "" : "->" + upper));
            return list;
        }
        
        long index = lower;
        for (int i = 0; i < nums.length;) {
            while (i < len && nums[i] == index) {
                while (i < len && nums[i] == index) {
                    i++;
                }
                index++;
            }
            
            if (i < len) {
                list.add(index + (nums[i] - 1 == index ? "" : "->" + (nums[i] - 1)));
                index = nums[i];
            }
        }
        
        if (nums[len - 1] < upper) {
            list.add(nums[len - 1] + 1 + (upper == nums[len - 1] + 1 ? "" : "->" + upper));
        }

        return list;
    }
}
```
# LeetCode_164_最大间距
## 题目
给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。

如果数组元素个数小于 2，则返回 0。

示例1:
```
输入: [3,6,9,1]
输出: 3
解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
```
示例2:
```
输入: [10]
输出: 0
解释: 数组元素个数小于 2，因此返回 0。
```
说明:
```
你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
```
## 解法
### 思路
排序后遍历比较
### 代码
```java
class Solution {
    public int maximumGap(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        
        int ans = Integer.MIN_VALUE;
        quickSort(nums, 0, len - 1);
        for (int i = 0; i < len - 1; i++) {
            ans = Math.max(nums[i + 1] - nums[i], ans);
        }
        return ans;
    }

    private void quickSort(int[] nums, int head, int tail) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(nums, head, tail);

        quickSort(nums, head, pivot - 1);
        quickSort(nums, pivot + 1, tail);
    }

    private int partition(int[] nums, int head, int tail) {
        int num = nums[head];
        while (head < tail) {
            while (head < tail && num <= nums[tail]) {
                tail--;
            }

            nums[head] = nums[tail];

            while (head < tail && num >= nums[head]) {
                head++;
            }

            nums[tail] = nums[head];
        }

        nums[head] = num;
        return head;
    }
}
```
## 解法二
### 思路
桶+鸽笼理论：
- 鸽笼理论：m个各自，n个笼子，如果m>n，那么必然有一个容器装至少2只鸽子
- 令`max`是序列中的最大值，`min`是序列中的最小值，`n`是序列元素的个数，那么间隔数就是`n - 1`个，而所有可能中最小的最大间隔就是`t = (max - min) / (n - 1)`
- 如果用`t`作为桶的个数，那么最大区间一定是桶与桶之间的最小和最大值之差
- 如何将元素放入指定的桶中：
    - 首先通过`t = (max - min) / (n - 1)`获得桶可以存放元素的个数
    - 再通过`s = (max - min) / t + 1`获得桶的个数
    - 那么元素在桶中的坐标就是：`i = (num - min) / s`
- 遍历序列元素，根据坐标放入桶中，并更新当前桶的最大和最小值
- 遍历桶，比较桶与桶之间的间距，更新最大值
- 注意计算桶个数的时候，如果所有元素相同，那么桶至少为1
### 代码
```java
class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int t = Math.max(1, (max - min) / (n - 1)), s = (max - min) / t + 1;
        int[][] buckets = new int[s][3];
        for (int[] bucket : buckets) {
            bucket[0] = max;
            bucket[1] = min;
            bucket[2] = -1;
        }

        for (int num : nums) {
            int i = (num - min) / t;
            buckets[i][0] = Math.min(buckets[i][0], num);
            buckets[i][1] = Math.max(buckets[i][1], num);
            buckets[i][2] = 1;
        }
        
        int ans = 0, pre = min;
        for (int[] bucket : buckets) {
            if (bucket[2] == -1) {
                continue;
            }
            
            ans = Math.max(ans, bucket[0] - pre);
            pre = bucket[1];
        }
        return ans;
    }
}
```
# LeetCode_165_比较版本号
## 题目
比较两个版本号 version1 和 version2。

如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1， 除此之外返回 0。

你可以假设版本字符串非空，并且只包含数字和 . 字符。

 . 字符不代表小数点，而是用于分隔数字序列。

例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。

你可以假设版本号的每一级的默认修订版号为 0。例如，版本号 3.4 的第一级（大版本）和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。

示例 1:
```
输入: version1 = "0.1", version2 = "1.1"
输出: -1
```
示例 2:
```
输入: version1 = "1.0.1", version2 = "1"
输出: 1
```
示例 3:
```
输入: version1 = "7.5.2.4", version2 = "7.5.3"
输出: -1
```
示例 4：
```
输入：version1 = "1.01", version2 = "1.001"
输出：0
解释：忽略前导零，“01” 和 “001” 表示相同的数字 “1”。
```
示例 5：
```
输入：version1 = "1.0", version2 = "1.0.0"
输出：0
解释：version1 没有第三级修订号，这意味着它的第三级修订号默认为 “0”。
```
提示：
```
版本字符串由以点 （.） 分隔的数字字符串组成。这个数字字符串可能有前导零。
版本字符串不以点开始或结束，并且其中不会有两个连续的点。
```
## 解法
### 思路
- 根据`.`拆分字符串
- 遍历最长的字符串
- String转int进行比较，如果比较过程中出现较短字符串当前坐标已没有对应字符串，就用0代替
- 根据是否大于或小于返回1或-1
- 循环结束也没有返回，就说明两个数相等，返回0
### 代码
```java
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1s = version1.split("\\."), v2s = version2.split("\\.");
        int len1 = v1s.length, len2 = v2s.length, index = 0;
        
        while (index < len1 || index < len2) {
            int v1Num = index >= len1 ? 0 : Integer.parseInt(v1s[index]),
                v2Num = index >= len2 ? 0 : Integer.parseInt(v2s[index]);
            
            if (v1Num > v2Num) {
                return 1;
            } else if (v1Num < v2Num) {
                return -1;
            }
            
            index++;
        }

        return 0;
    }
}
```
# LeetCode_166_分数到小数
## 题目
给定两个整数，分别表示分数的分子numerator 和分母 denominator，以字符串形式返回小数。

如果小数部分为循环小数，则将循环的部分括在括号内。

示例 1:
```
输入: numerator = 1, denominator = 2
输出: "0.5"
```
示例2:
```
输入: numerator = 2, denominator = 1
输出: "2"
```
示例3:
```
输入: numerator = 2, denominator = 3
输出: "0.(6)"
```
## 解法
### 思路
hash表+硬做
- 出现的变量：
    - 被除数：numerator
    - 除数：denominator
    - 商：ans
    - 余数：reminder
- 被除数也可以是`reminder * 10`，上一次计算得到的余数再乘以10
- 商就是`numerator / denominator`
- 需要确定的事情：
    - `numerator == 0`的情况，直接返回0
    - 确定符号，`numerator < 0 ^ denominator < 0`的情况下，也就是判断结果并不相同的情况，就是负，否则为正
- 过程：
    - 先确定整数部分
    - 再确定小数部分：
        - 循环判断`reminder == 0`作为退出条件
        - 根据`reminder`获得`numerator`
        - 计算`numerator / denominator`作为商
        - 计算`numerator % denominator`作为余数
### 代码
```java
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        Map<Long, Integer> map = new HashMap<>();
        if (numerator < 0 ^ denominator < 0) {
            sb.append("-");
        }

        long n = Math.abs(Long.parseLong(String.valueOf(numerator))),
             d = Math.abs(Long.parseLong(String.valueOf(denominator)));

        long reminder = n % d;
        sb.append(n / d);
        if (reminder == 0) {
            return sb.toString();
        }
        sb.append(".");

        while (reminder != 0) {
            if (map.containsKey(reminder)) {
                sb.insert(map.get(reminder), "(").append(")");
                return sb.toString();
            }

            map.put(reminder, sb.length());
            n = reminder * 10;
            sb.append(n / d);
            reminder = n % d;
        }
        
        return sb.toString();
    }
}
```
# LeetCode_336_回文对
## 题目
给定一组唯一的单词， 找出所有不同的索引对(i, j)，使得列表中的两个单词，words[i] + words[j]，可拼接成回文串。

示例 1:
```
输入: ["abcd","dcba","lls","s","sssll"]
输出: [[0,1],[1,0],[3,2],[2,4]] 
解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
```
示例 2:
```
输入: ["bat","tab","cat"]
输出: [[0,1],[1,0]] 
解释: 可拼接成的回文串为 ["battab","tabbat"]
```
## 失败解法
### 失败原因
超时，时间复杂度O(n^3)
### 思路
暴力3层循环
### 代码
```java
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }
                
                String str = words[i] + words[j];
                if (set.contains(str)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                    continue;
                }
                
                int head = 0, tail = str.length() - 1;
                boolean flag = true;
                while (head < tail) {
                    if (str.charAt(head++) != str.charAt(tail--)) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                    set.add(str);
                }
            }
        }
        
        return ans;
    }
}
```
## 解法
### 思路
哈希表：
- 假设两个字符串`s1`和`s2`能够组成回文串，那么会产生三种情况：
    - `len1 == len2`：那么`s1`翻转后应该和`s2`相等
    - `len1 > len2`：那么`s1`可以拆分成`t1`和`t2`两部分，其中`t2`是回文串，`t1`翻转后和`s2`相等
    - `len1 < len2`：那么`s2`可以拆分成`t1`和`t2`两部分，其中`t2`是回文串，`t1`翻转后和`s1`相等
- 先遍历字符串，翻转每个字符串并放入hash表中，与坐标做映射关系
- 再遍历字符串，将字符串切分成`t1`和`t2`两部分，然后分别判断：
    - 一个是否是回文串
    - 一个反转后在hash表中是否能查询到
- 如果如上两种情况都满足，那么就记录坐标组合，并且这种判断要做两次，原来的`t1`当作`t2`，原来的`t2`当作`t1`
### 代码
```java
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(new StringBuilder(words[i]).reverse().toString(), i);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            if (len == 0) {
                continue;
            }

            for (int j = 0; j <= len; j++) {
                if (isPalindrome(words[i], j, len - 1)) {
                    int index = map.getOrDefault(words[i].substring(0, j), -1);
                    if (index != -1 && index != i) {
                        ans.add(Arrays.asList(i, index));
                    }
                }

                if (j != 0 && isPalindrome(words[i], 0, j - 1)) {
                    int index = map.getOrDefault(words[i].substring(j, len), -1);
                    if (index != -1 && index != i) {
                        ans.add(Arrays.asList(index, i));
                    }
                }
            }
        }

        return ans;
    }

    private boolean isPalindrome(String word, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (word.charAt(left + i) != word.charAt(right - i)) {
                return false;
            }
        }

        return true;
    }
}
```
## 解法二
### 思路
- 使用字典树代替解法一中的hash表
- 遍历字符串，倒序生成字典树，通过倒序快速确定是否为翻转字符串
- 字典树
    - 属性：
        - 26个字母对应的字典树节点
        - 当前节点是否为一个目标字符，如果是保存其下标值
    - 方法：
        - 插入：传入一个翻转后的字符串，遍历其所有字符，在字典树中生成对应的节点，并在最后的字符处标记这个被翻转字符的下标
        - 查询：传入一个未翻转的字符串，遍历其所有字符，如果找到就返回记录的坐标，否则就返回-1
- 遍历`words`，翻转迭代到的字符串，并插入字典树中
- 遍历`words`：
    - 查看当前字符串的翻转字符串是否存在，如果存在，记录
    - 拆分字符串，分别判断如解法一样的`t1`和`t2`两部分，并分别假设为回文串
    - 然后到字典树中查找是否有对应的字符串，如果有就记录
### 代码
```java
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(new StringBuilder(words[i]).reverse().toString(), i);
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = words[i].length();

            int target = trie.find(word);
            if (target != -1 && target != i) {
                ans.add(Arrays.asList(i, target));
            }

            if (isPalindrome(word)) {
                int index = trie.find("");
                if (index != -1 && index != i) {
                    ans.add(Arrays.asList(i, index));
                }
            }

            for (int j = 0; j < len; j++) {
                String left = word.substring(0, j + 1),
                        right = word.substring(j + 1);

                if (isPalindrome(left)) {
                    int index = trie.find(right);
                    if (index != -1 && index != i) {
                        ans.add(Arrays.asList(index, i));
                    }
                }

                if (j != len - 1 && isPalindrome(right)) {
                    int index = trie.find(left);
                    if (index != -1 && index != i) {
                        ans.add(Arrays.asList(i, index));
                    }
                }
            }
        }
        return ans;
    }

    private boolean isPalindrome(String word) {
        int head = 0, tail = word.length() - 1;
        while (head < tail) {
            if (word.charAt(head) != word.charAt(tail)) {
                return false;
            }

            head++;
            tail--;
        }

        return true;
    }

    private class Trie {
        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word, int index) {
            char[] cs = word.toCharArray();
            TrieNode cur = root;

            for (char c : cs) {
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new TrieNode();
                }

                cur = cur.next[c - 'a'];
            }

            cur.target = index;
        }

        public int find(String word) {
            char[] cs = word.toCharArray();
            TrieNode cur = root;
            for (char c : cs) {
                TrieNode next = cur.next[c - 'a'];
                if (next == null) {
                    return -1;
                }

                cur = next;
            }

            return cur.target;
        }

        private class TrieNode {
            private TrieNode[] next = new TrieNode[26];
            private int target = -1;
        }
    }
}
```
# LeetCode_170_两数之和III数据结构设计
## 题目
设计并实现一个TwoSum 的类，使该类需要支持 add和find的操作。
```
add操作 - 对内部数据结构增加一个数。
find 操作 - 寻找内部数据结构中是否存在一对整数，使得两数之和与给定的数相等。
```
示例1:
```
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
```
示例2:
```
add(3); add(1); add(2);
find(3) -> true
find(6) -> false
```
## 解法
### 思路
- 两个变量：
    - `list`：用来存数字
    - `order`：用来标记当前list是否有序
- add：add的时候往`list`中放数据，并标记`ordered`为false
- find：find的时候检查`ordered`是否有序，如果不是，先排序，然后双指针查找
### 代码
```java
class TwoSum {
    private List<Integer> list;
    private boolean ordered;
    
    public TwoSum() {
        this.list  = new ArrayList<>();
    }

    public void add(int number) {
        this.list.add(number);
        this.ordered = false;
    }

    public boolean find(int value) {
        if (!ordered) {
            Collections.sort(this.list);
            ordered = true;
        }
        int head = 0, tail = this.list.size() - 1;
        while (head < tail) {
            int sum = list.get(head) + list.get(tail);

            if (sum == value) {
                return true;
            } else if (sum < value) {
                head++;
            } else {
                tail--;
            }
        }

        return false;
    }
}
```
## 解法二
### 思路
- 使用map记录add的值和出现的个数
- 查找时，遍历map的key值
- 用value和key值相减，获得要查找的值
- 在map中查找：
    - 如果和key值相同，就看key值的个数是否大于1
    - 如果不一样，就看是否存在这个差值
- 如果都没找到，就返回false
### 代码
```java
class TwoSum {
        private Map<Integer, Integer> map;
        public TwoSum() {
            this.map = new HashMap<>();
        }

        public void add(int number) {
            this.map.put(number, map.getOrDefault(number, 0) + 1);
        }

        public boolean find(int value) {
            for (Integer num : map.keySet()) {
                int left = value - num;
                if (left != num) {
                    if (this.map.containsKey(left)) {
                        return true;
                    }
                } else {
                    if (map.getOrDefault(left, 0) > 1) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
```