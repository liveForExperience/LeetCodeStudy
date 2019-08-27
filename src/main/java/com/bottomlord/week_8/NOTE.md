# LeetCode_28_实现strStr()
## 题目
实现 strStr() 函数。

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

示例 1:
```
输入: haystack = "hello", needle = "ll"
输出: 2
```
示例 2:
```
输入: haystack = "aaaaa", needle = "bba"
输出: -1
```
说明:
```
当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
```
## 解法
### 思路
- 通过indexOf函数，查找hayStack字符串中的needle字符串的首字符
- 判断needle字符串是否和找到的首字符之后的字符一致，如果一致就返回首字符的下标
- 循环这个过程，每次都从上一个首字符开始，直到indexOf返回-1
### 代码
```java
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }

        int index = 0, needleLen = needle.length(), hayLen = haystack.length();
        char c = needle.charAt(0);
        while (haystack.indexOf(c, index) != -1) {
            if (index + needleLen - 1 >= hayLen) {
                return -1;
            }

            boolean find = true;
            for (int i = 0; i < needleLen; i++) {
                if (needle.charAt(i) != haystack.charAt(index + i)) {
                    find = false;
                }
            }
            
            if (find) {
                return index;
            }

            index++;
        }

        return -1;
    }
}
```
## 优化代码
### 思路
直接使用indexOf
### 代码
```java
class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
```
## 解法二
### 思路
使用subString在遍历hayStack过程中判断是否与needle相等
### 代码
```java
class Solution {
    public int strStr(String haystack, String needle) {
        int hayLen = haystack.length(), needleLen = needle.length();
        if (hayLen < needleLen) {
            return -1;
        }

        int start = 0, end = needleLen - 1;
        while (end < hayLen) {
            if (haystack.substring(start++, ++end).equals(needle)) {
                return start - 1;
            }
        }
        
        return -1;
    }
}
```
# LeetCode_290_单词规律
## 题目
给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

示例1:
```
输入: pattern = "abba", str = "dog cat cat dog"
输出: true
```
示例 2:
```
输入:pattern = "abba", str = "dog cat cat fish"
输出: false
```
示例 3:
```
输入: pattern = "aaaa", str = "dog cat cat dog"
输出: false
```
示例 4:
```
输入: pattern = "abba", str = "dog dog dog dog"
输出: false
```
```
说明:
你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。   
```
## 解法
### 思路
使用两个map映射字符和单词之间的关系，用到两个是因为需要确认双向关系，遍历字符串数组，查看是否符合。
### 代码
```java
class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> cMap = new HashMap<>();
        Map<String, Character> sMap = new HashMap<>();
        
        char[] cs = pattern.toCharArray();
        String[] ss = str.split(" ");

        if (cs.length != ss.length) {
            return false;
        }

        for (int i = 0; i < ss.length; i++) {
            Character c = cs[i];
            String s = ss[i];
            
            if (!cMap.containsKey(c) && !sMap.containsKey(s)) {
                cMap.put(c, s);
                sMap.put(s, c);
                continue;
            }
            
            if (cMap.containsKey(c) && sMap.containsKey(s)) {
                if (cMap.get(c).equals(s) && sMap.get(s).equals(c)) {
                    continue;
                }
            }
            
            return false;
        }

        return true;
    }
}
```
## 优化代码
### 思路
省略一个map，如果map里没有字符对应的字符串，那么如果map里有对应的value是这个字符串，也是false的
### 代码
```java
class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        String[] ss = str.split(" ");
        if (pattern.length() != ss.length) {
            return false;
        }
        for (int i = 0; i < ss.length; i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(ss[i])) {
                    return false;
                }
            } else if (map.containsValue(ss[i])) {
                return false;
            } else {
                map.put(c, ss[i]);
            }
        }
        return true;
    }
}
```
# LeetCode_747_至少是其他数字两倍的最大数
## 题目
在一个给定的数组nums中，总是存在一个最大元素 。

查找数组中的最大元素是否至少是数组中每个其他数字的两倍。

如果是，则返回最大元素的索引，否则返回-1。

示例 1:
```
输入: nums = [3, 6, 1, 0]
输出: 1
解释: 6是最大的整数, 对于数组中的其他整数,
6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
```
示例 2:
```
输入: nums = [1, 2, 3, 4]
输出: -1
解释: 4没有超过3的两倍大, 所以我们返回 -1.
```
提示:
```
nums 的长度范围在[1, 50].
每个 nums[i] 的整数范围在 [0, 99].
```
## 解法
### 思路
- 遍历数组，记录最大的两个数的下标
    - 如果大于最大，最大变第二，当前变最大
    - 如果大于第二，当前变第二
- 最终返回时判断最大和第二之间的是否符合要求
### 代码
```java
class Solution {
    public int dominantIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int one = nums[0] > nums[1] ? 0 : 1, two = one == 0 ? 1 : 0;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > nums[one]) {
                two = one;
                one = i;
            } else if (nums[i] > nums[two]) {
                two = i;
            }
        }

        return nums[one] >= nums[two] * 2 ? one : -1;
    }
}
```
# LeetCode_234_回文链表
## 题目
请判断一个链表是否为回文链表。

示例 1:
```
输入: 1->2
输出: false
```
示例 2:
```
输入: 1->2->2->1
输出: true
```
```
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
```
## 解法
### 思路
- 遍历链表，使用一个list存储路径上的元素的值
- 使用头尾指针判断值是否相等
### 代码
```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}
```
## 解法二
### 思路
- 通过快慢指针找到链表的中点
- 反转中点之后的链表
- 比较前后两段链表
### 代码
```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head, fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode node = slow.next, pre = null, next;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }

        while (pre != null && head != null) {
            if (pre.val != head.val) {
                return false;
            }
            
            pre = pre.next;
            head = head.next;
        }
        
        return true;
    }
}
```
# LeetCode_438_找到字符串中所有字母异位词
## 题目
给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

说明：
```
字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。
```
示例 1:
```
输入:
s: "cbaebabacd" p: "abc"

输出:
[0, 6]

解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
```
 示例 2:
```
输入:
s: "abab" p: "ab"

输出:
[0, 1, 2]
```
```
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
```
## 解法
### 思路
- 针对字符串p生成一个桶记录字符出现的个数
- 遍历字符串s，看当前下标及之后p长度的字符串中的字符个数是否和桶中的个数一致，如果是就记录下起始下标
### 代码
```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] bucket = new int[26];
        int len = p.length();
        for (char c : p.toCharArray()) {
            bucket[c - 'a']++;
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i + len - 1 < s.length(); i++) {
            int[] copy = Arrays.copyOf(bucket, bucket.length);
            for (int j = i; j < i + len; j++) {
                copy[s.charAt(j) - 'a']--;
            }
            
            boolean flag = true;
            for (int num : copy) {
                if (num != 0) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                ans.add(i);
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
不需要嵌套循环，只需要在一次遍历中使用两个指针：
- 一个指针负责找到桶中的字符，然后将桶中的字符递减，直到遍历到并不是桶桶中的字符，停住
- 这时另一个指针开始追第一个指针，同时将桶中的字符增加回来，直到追到第一个指针所在的位置
- 然后如果遍历到的都不是p字符，那么两个指针会交替移动，但第二个指针一定不会超过第一个指针
- 直到再次找到p字符，r开始拉大距离，直到再次停止，如果找到了p的异位词，那么就会拉大到p的长度，所以这个时候可以将l所在的位置放入ans中
- 直到第一个指针遍历结束
### 代码
```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] bucket = new int[26];
        for (char c : p.toCharArray()) {
            bucket[c - 'a']++;
        }
        
        int one = 0, two = 0, len = p.length();
        char[] ss = s.toCharArray();
        List<Integer> ans = new ArrayList<>();
        
        while (one < ss.length) {
            if (bucket[ss[one] - 'a'] > 0) {
                bucket[ss[one++] - 'a']--;
                if (one - two == len) {
                    ans.add(two);
                }
            } else {
                bucket[ss[two++] - 'a']++;
            }
        }
        
        return ans;
    }
}
```
# LeetCode_754_到达终点数字
## 题目
在一根无限长的数轴上，你站在0的位置。终点在target的位置。

每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。

返回到达终点需要的最小移动次数。

示例 1:
```
输入: target = 3
输出: 2
解释:
第一次移动，从 0 到 1 。
第二次移动，从 1 到 3 。
```
示例 2:
```
输入: target = 2
输出: 3
解释:
第一次移动，从 0 到 1 。
第二次移动，从 1 到 -1 。
第三次移动，从 -1 到 2 。
```
注意:
```
target是在[-10^9, 10^9]范围中的非零整数。
```
## 解法
### 思路
- 先只往右走，当累加得到target后就返回
- 如果大于target了，说明需要往左走，因为往左走移位累加值需要- 2 * n，所以当累加值大于target为偶数时，就是最小的n
### 代码
```java
class Solution {
    public int reachNumber(int target) {
        int sum = 0, path = 1;
        target = Math.abs(target);

        while (true) {
            if (sum == target) {
                return path - 1;
            }
            
            if (sum > target && (sum - target) % 2 == 0) {
                return path - 1;
            }
            
            sum += path++;
        }
    }
}
```
## 优化代码
### 思路
- 利用递增数列求和公式，快速计算离target较近的小于target的值
### 代码
```java
class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int n = (int) Math.sqrt((double) target * 2.0);
        
        while (sum(n) < target) {
            n++;
        }

        int diff = sum(n) - target;
        if (diff % 2 == 0) {
            return n;
        }
        
        return n + 1 + n % 2;
    }

    private int sum(int n) {
        return (1 + n) * n / 2;
    }
}
```