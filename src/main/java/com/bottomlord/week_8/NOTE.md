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
# LeetCode_645_错误的集合
## 题目
集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。

给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。

示例 1:
```
输入: nums = [1,2,2,4]
输出: [2,3]
```
注意:
```
给定数组的长度范围是 [2, 10000]。
给定的数组是无序的。
```
## 解法
### 思路
- 遍历过程中使用桶记录数组元素的个数
- 遍历桶，查询元素个数为0和为2的下标
### 代码
```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] bucket = new int[nums.length + 1];
        for (int num : nums) {
            bucket[num]++;
        }

        int[] ans = new int[2];
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                ans[1] = i;
            }
            
            if (bucket[i] == 2) {
                ans[0] = i;
            }
            
            if (ans[0] != 0 && ans[1] != 0) {
                break;
            }
        }
        
        return ans;
    }
}
```
# LeetCode_482_密钥的格式化
## 题目
给定一个密钥字符串S，只包含字母，数字以及 '-'（破折号）。N 个 '-' 将字符串分成了 N+1 组。给定一个数字 K，重新格式化字符串，除了第一个分组以外，每个分组要包含 K 个字符，第一个分组至少要包含 1 个字符。两个分组之间用 '-'（破折号）隔开，并且将所有的小写字母转换为大写字母。

给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。

示例 1：
```
输入：S = "5F3Z-2e-9-w", K = 4

输出："5F3Z-2E9W"

解释：字符串 S 被分成了两个部分，每部分 4 个字符；
     注意，两个额外的破折号需要删掉。
```
示例 2：
```
输入：S = "2-5g-3-J", K = 2

输出："2-5G-3J"

解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。
```
提示:
```
S 的长度不超过 12,000，K 为正整数
S 只包含字母数字（a-z，A-Z，0-9）以及破折号'-'
S 非空
```
## 解法
### 思路
- 去除-，获得所有有效字符拼接的字符串
- 根据字符串长度与4取余的余数，确定第一组字符的长度，并把字符转成大写
- 之后从该长度开始每4个字符一段拼接，且小写字母转成大写
### 代码
```java
class Solution {
    public String licenseKeyFormatting(String S, int K) {
        S = S.replaceAll("-" , "");
        int mod = S.length() % K;
        if (mod == 0) {
            mod = K;
        }

        if (S.length() == 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder(S.substring(0, mod).toUpperCase());
        while (mod + K <= S.length()) {
            sb.append("-").append(S.substring(mod, mod + K).toUpperCase());
            mod += K;
        }

        return sb.toString();
    }
}
```
## 优化代码
### 思路
- 使用数组代替StringBuilder
- 从字符串的尾部开始遍历，遍历到的字符转成大写，并使计数器+1
- 如果计数器==k，增加‘-’,并初始化计数器为0
- 直到遍历结束
- 返回字符串
### 代码
```java
class Solution {
    public String licenseKeyFormatting(String S, int K) {
        int len = S.length(), count = 0;
        char[] cs = S.toCharArray();
        char[] ans = new char[len * 2];
        int index = ans.length - 1;
        for (int i = len - 1; i >= 0; i--) {
            char c = cs[i];

            if (c == '-') {
                continue;
            }
            
            if (count == K) {
                ans[index--] = '-';
                count = 0;
            }

            if (c >= 'a' && c <= 'z') {
                c -= ' ';
            }

            ans[index--] = c;
            count++;
        }

        return new String(ans, ++index, ans.length - index);
    }
}
```
# LeetCode_1041_困于环中的机器人
## 题目
在无限的平面上，机器人最初位于 (0, 0) 处，面朝北方。机器人可以接受下列三条指令之一：
```
"G"：直走 1 个单位
"L"：左转 90 度
"R"：右转 90 度
机器人按顺序执行指令 instructions，并一直重复它们。
```
只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 false。

示例 1：
```
输入："GGLLGG"
输出：true
解释：
机器人从 (0,0) 移动到 (0,2)，转 180 度，然后回到 (0,0)。
重复这些指令，机器人将保持在以原点为中心，2 为半径的环中进行移动。
```
示例 2：
```
输入："GG"
输出：false
解释：
机器人无限向北移动。
```
示例 3：
```
输入："GL"
输出：true
解释：
机器人按 (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ... 进行移动。
```
提示：
```
1 <= instructions.length <= 100
instructions[i] 在 {'G', 'L', 'R'} 中
```
## 解法
### 思路
两种情况会导致进入一个环：
- 一次循环回到原点
- 一次循环不在原点，但面朝的方向和初始位置方向不同
### 代码
```java
class Solution {
    public boolean isRobotBounded(String instructions) {
        int[] p = new int[2];
        int face = 0;
        
        for (char c : instructions.toCharArray()) {
            if (c == 'L') {
                face = (face + 3) % 4;
            } else if (c == 'R') {
                face = (face + 1) % 4;
            } else if (c == 'G') {
                if (face == 0) {
                    p[1]++;
                }
                
                if (face == 1) {
                    p[0]++;
                }
                
                if (face == 2) {
                    p[1]--;
                }
                
                if (face == 3) {
                    p[0]--;
                }
            }
        }
        
        if (p[0] == 0 && p[1] == 0) {
            return true;
        }
        
        return face != 0;
    }
}
```
# LeetCode_970_强整数
## 题目
给定两个正整数 x 和 y，如果某一整数等于 x^i + y^j，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个强整数。

返回值小于或等于 bound 的所有强整数组成的列表。

你可以按任何顺序返回答案。在你的回答中，每个值最多出现一次。

示例 1：
```
输入：x = 2, y = 3, bound = 10
输出：[2,3,4,5,7,9,10]
解释： 
2 = 2^0 + 3^0
3 = 2^1 + 3^0
4 = 2^0 + 3^1
5 = 2^1 + 3^1
7 = 2^2 + 3^1
9 = 2^3 + 3^0
10 = 2^0 + 3^2
```
示例 2：
```
输入：x = 3, y = 5, bound = 15
输出：[2,4,6,8,10,14]
```
提示：
```
1 <= x <= 100
1 <= y <= 100
0 <= bound <= 10^6
```
## 解法
### 思路
嵌套循环求2个数的指数和小于bound的结果，注意：
- 结果需要去重
- x或y为1的情况
### 代码
```java
class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        List<Integer> ans = new ArrayList<>();
        boolean[] bucket = new boolean[bound + 1];
        int xi = 0;

        while (true) {
            int count = 0, yi = 0;
            while (true) {
                int sum = (int)(Math.pow(x, xi) + Math.pow(y, yi));
                if (sum <= bound) {
                    bucket[sum] = true;
                    yi++;
                    count++;
                    
                    if (y == 1) {
                        break;
                    }
                } else {
                    break;
                }
            }

            if (x == 1) {
                break;
            }
            
            if (count > 0) {
                xi++;
            } else {
                break;
            }
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
- x和y分开求得小于bound的所有可能的pow的结果
- 嵌套遍历同时两组sum的和
- 把符合的放入list里
### 代码
```java
class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        List<Integer> ans = new ArrayList<>();
        if (bound < 2) {
            return ans;
        }
        
        List<Integer> xs = getPowList(x, bound);
        List<Integer> ys = getPowList(y, bound);
        for (Integer xSum : xs) {
            for (Integer ySum : ys) {
                int sum = xSum + ySum;
                if (sum > bound) {
                    break;
                }
                
                if (!ans.contains(sum)) {
                    ans.add(sum);
                }
            }
        }
        return ans;
    }

    private List<Integer> getPowList(int num, int bound) {
        List<Integer> list = new ArrayList<>();
        if (num == 1) {
            list.add(1);
            return list;
        }
        int i = 0;
        while (Math.pow(num, i) <= bound) {
            list.add((int)Math.pow(num, i++));
        }
        return list;
    }
}
```
# LeetCode_69_x的平方根
## 题目
实现 int sqrt(int x) 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

示例 1:
```
输入: 4
输出: 2
```
示例 2:
```
输入: 8
输出: 2
说明: 8 的平方根是 2.82842..., 
     由于返回类型是整数，小数部分将被舍去。
```
## 解法
### 思路
使用牛顿迭代法：对`x^2 - a = 0`求导，获得导数为2x，所以x - f(x) / 2x是比x更接近求根后的值，将`f(x) = x^2 - a`代入得到`(x + a * x) / 2`
### 代码
```java
class Solution {
    public int mySqrt(int x) {
        return x == 0 ? 0 : (int)sqrt(x, x);
    }
    
    private double sqrt(double x, int target) {
        double ans = (x + target / x) / 2;
        return ans == x ? ans : sqrt(ans, target);
    }
}
```
## 解法二
### 思路
使用二分查找，因为是返回int值的结果，所以只需要使`mid <= x / mid`且`mid + 1 > x / (mid +  1)`即可。
### 代码
```java
class Solution {
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        int head = 0, tail = x / 2 + 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            if (mid <= x / mid && mid + 1 > x / (mid + 1)) {
                return mid;
            }

            if (mid < x / mid) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return 0;
    }
}
```
# LeetCode_849_到最近的人的最大距离
## 题目
在一排座位（ seats）中，1 代表有人坐在座位上，0 代表座位上是空的。

至少有一个空座位，且至少有一人坐在座位上。

亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。

返回他到离他最近的人的最大距离。

示例 1：
```
输入：[1,0,0,0,1,0,1]
输出：2
解释：
如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
因此，他到离他最近的人的最大距离是 2 。 
```
示例 2：
```
输入：[1,0,0,0]
输出：3
解释： 
如果亚历克斯坐在最后一个座位上，他离最近的人有 3 个座位远。
这是可能的最大距离，所以答案是 3 。
```
提示：
```
1 <= seats.length <= 20000
seats 中只含有 0 和 1，至少有一个 0，且至少有一个 1。
```
## 解法
### 思路
- 遍历数组
- 从元素为0的位置开始循环查找到左右两边元素为1的下标之间的距离，求左右两边的最小值
- 与整个数组中记录的最大距离比较，取较大值
### 代码
```java
class Solution {
    public int maxDistToClosest(int[] seats) {
        int max = 0, left = -1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                left = i;
                continue;
            }

            if (seats[i] == 0) {
                int leftLen = left == -1 ? -1 : i - left;
                int rightLen = -1;
                for (int j = i + 1; j < seats.length; j++) {
                    if (seats[j] == 1) {
                        rightLen = j - i;
                        break;
                    }
                }
                max = Math.max(max, leftLen == -1 ? rightLen : rightLen == -1 ? leftLen : Math.min(leftLen, rightLen));
            }
        }

        return max;
    }
}
```
## 解法二
### 思路
- 遍历数组，直到遇到1为止
- 将下标作为max值，代表从左手边开始到第一个1的距离
- 通过一个变量count来记录路过的0的个数，然后通过`count \ 2 + count % 2`来作为当前两个1之间的最大距离
- 最后再比较最后一个1到右手边结束的距离
### 代码
```java
class Solution {
    public int maxDistToClosest(int[] seats) {
        int count = 0, max = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                max = i;
                break;
            }
        }
        
        for (int i = max + 1; i < seats.length; i++) {
            if (seats[i] == 1) {
                max = Math.max(max, count / 2 + count % 2);
                count = 0;
                continue;
            }
            
            count++;
        }
        
        return Math.max(count, max);
    }
}
```
# LeetCode_687_最长同值路径
## 题目
给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。

注意：两个节点之间的路径长度由它们之间的边数表示。

示例 1:
```
输入:

              5
             / \
            4   5
           / \   \
          1   1   5
输出:

2
```
示例 2:
```
输入:

              1
             / \
            4   5
           / \   \
          4   4   5
输出:

2
```
```
注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
```
## 解法
### 思路
dfs嵌套递归
- 外层遍历所有节点
- 内层累加当前节点左右子树val相等的树的深度的和
- 取最大值返回
### 代码
```java
class Solution {
    private int count = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfsOut(root);
        return count;
    }

    private void dfsOut(TreeNode node) {
        if (node == null) {
            return;
        }

        count = Math.max(count, dfsIn(node.left, node.val, 0) + dfsIn(node.right, node.val, 0));

        dfsOut(node.left);
        dfsOut(node.right);
    }

    private int dfsIn(TreeNode node, int pre, int sum) {
        if (node == null) {
            return sum;
        }

        if (node.val == pre) {
            sum++;
        } else {
            return sum;
        }

        return Math.max(dfsIn(node.left, pre, sum), dfsIn(node.right, pre, sum));
    }
}
```
## 解法二
### 思路
想通过map来记录路径上的值，减少重复递归，但发掘使用map速度反而因为计算hash等内部逻辑而变得更慢
### 代码
```java
class Solution {
    private int count = 0;
    public int longestUnivaluePath(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        dfsOut(root, map);
        return count;
    }

    private void dfsOut(TreeNode node, Map<TreeNode, Integer> map) {
        if (node == null) {
            return;
        }

        count = Math.max(count, dfsIn(node.left, node.val, map) + dfsIn(node.right, node.val, map));

        dfsOut(node.left, map);
        dfsOut(node.right, map);
    }

    private int dfsIn(TreeNode node, int pre, Map<TreeNode, Integer> map) {
        if (node == null) {
            return 0;
        }

        if (map.containsKey(node)) {
            return map.get(node);
        }

        if (node.val != pre) {
            return 0;
        }

        int val = Math.max(dfsIn(node.left, pre, map) + 1, dfsIn(node.right, pre, map) + 1);
        map.put(node, val);
        return val;
    }
}
```
## 解法三
### 思路
与解法一相反，与解法二类似，不使用sum变量从上往下统计，而是从下往上统计
### 代码
```java
class Solution {
    private int count = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfsOut(root);
        return count;
    }

    private void dfsOut(TreeNode node) {
        if (node == null) {
            return;
        }

        count = Math.max(count, dfsIn(node.left, node.val) + dfsIn(node.right, node.val));

        dfsOut(node.left);
        dfsOut(node.right);
    }

    private int dfsIn(TreeNode node, int pre) {
        if (node == null) {
            return 0;
        }

        if (node.val != pre) {
            return 0;
        }

        return Math.max(dfsIn(node.left, pre) + 1, dfsIn(node.right, pre) + 1);
    }
}
```
## 优化代码
### 思路
因为是从下往上统计，所以不需要两层嵌套的递归，直接使用同一套逻辑，在下钻的每一个节点，计算节点值和上一个节点值是否相同
- 如果相同，就返回它的左右子树的深度的最大值+1，1代表当前有效层
- 如果不相同，就返回0，说明当前层的左右子树的深度和上一层没有关系，不能加在一起统计
### 代码
```java
class Solution {
    private int count = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, root.val);
        return count;
    }
    
    private int dfs(TreeNode node, int preVal) {
        if (node == null) {
            return 0;
        }
        
        int left = dfs(node.left, node.val);
        int right = dfs(node.right, node.val);
        
        count = Math.max(count, left + right);
        return node.val == preVal ? Math.max(left, right) + 1 : 0;
    }
}
```
# LeetCode_443_压缩字符串
## 题目
给定一组字符，使用原地算法将其压缩。

压缩后的长度必须始终小于或等于原数组长度。

数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。

在完成原地修改输入数组后，返回数组的新长度。
 
```
进阶：
你能否仅使用O(1) 空间解决问题？
```
示例 1：
```
输入：
["a","a","b","b","c","c","c"]

输出：
返回6，输入数组的前6个字符应该是：["a","2","b","2","c","3"]

说明：
"aa"被"a2"替代。"bb"被"b2"替代。"ccc"被"c3"替代。
```
示例 2：
```
输入：
["a"]

输出：
返回1，输入数组的前1个字符应该是：["a"]

说明：
没有任何字符串被替代。
```
示例 3：
```
输入：
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

输出：
返回4，输入数组的前4个字符应该是：["a","b","1","2"]。

说明：
由于字符"a"不重复，所以不会被压缩。"bbbbbbbbbbbb"被“b12”替代。
注意每个数字在数组中都有它自己的位置。
```
注意：
```
所有字符都有一个ASCII值在[35, 126]区间内。
1 <= len(chars) <= 1000。
```
## 解法
### 思路
- 使用三个指针：
    - 一个指针代表遍历到的元素下标oldI
    - 一个指针代表新数组的下标newI
    - 一个指针保存改变后的第一个元素的下标pre
- 过程中，通过遍历数组，判断当前下标元素和后一个元素是否相等来决定具体操作：
    - 相等：意味着指向新数组的指针不用动，遍历用的指针需要继续移动
    - 不相等：有两种情况
        - pre和oldI相等，pre会在每次发生不相等情况的时候，记录oldI指向的不相等的第一个元素的下标。如果这个时候发现oldI和pre相等，说明当前元素只有一个值，这时候就只需要让newId指向下一个元素下标并记录下一个不同元素即可，同时pre和oldI同时向后移动
        - pre和oldI不相等，说明相同元素大于一个，这时候就要让newI开始记录重复了多少个，因为每一元素只能是字符，所以如果个数超过9，就需要特殊处理，这里用了转成String再遍历字符数组的方式，记录完后，再和上一种情况一样，三个指针都向后一格
    - 在相等的情况中，还有一个特殊情况，就是重复的元素一直重复到数组结尾，这个时候就需要在最后一个元素的时候触发一次计算重复次数并操作newI的动作，步骤和字符变化时处理重复元素时一致
### 代码
```java
class Solution {
    public int compress(char[] chars) {
        int oldI = 0, newI = 0, pre = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                oldI++;
                if (i + 1 == chars.length - 1) {
                    int len = ++oldI - pre;
                    String lenS = String.valueOf(len);
                    for (char c : lenS.toCharArray()) {
                        chars[++newI] = c;
                    }
                }
            } else {
                if (oldI == pre) {
                    oldI++;
                } else {
                    int len = ++oldI - pre;
                    String lenS = String.valueOf(len);
                    for (char c : lenS.toCharArray()) {
                        chars[++newI] = c;
                    }
                }
                chars[++newI] = chars[i + 1];
                pre = oldI;
            }
        }
        return newI + 1;
    }
}
```
# LeetCode_1037_有效的回旋镖
## 题目
回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。

给出平面上三个点组成的列表，判断这些点是否可以构成回旋镖。

示例 1：
```
输入：[[1,1],[2,3],[3,2]]
输出：true
```
示例 2：
```
输入：[[1,1],[2,2],[3,3]]
输出：false
```
提示：
```
points.length == 3
points[i].length == 2
0 <= points[i][j] <= 100
```
## 解法
### 思路
判断二维坐标系中的三个点是否在同一条直线上，因为三个点在同一条直线上，必然有一条边等于另两条边的和，所以只要计算两点之间距离，判断是否有这种可能即可。
### 代码
```java
class Solution {
    public boolean isBoomerang(int[][] points) {
        int ax = points[0][0];
        int ay = points[0][1];
        int bx = points[1][0];
        int by = points[1][1];
        int cx = points[2][0];
        int cy = points[2][1];

        double lenA = Math.sqrt(Math.pow(ax - bx, 2) + Math.pow(ay - by, 2));
        double lenB = Math.sqrt(Math.pow(ax - cx, 2) + Math.pow(ay - cy, 2));
        double lenC = Math.sqrt(Math.pow(bx - cx, 2) + Math.pow(by - cy, 2));

        return !(lenA + lenB == lenC || lenA + lenC == lenB || lenB + lenC == lenA);
    }
}
```
## 解法二
### 思路
如果斜率相等，说明在同一条直线上，但为了防止除数为0的情况，斜率方程转换成乘法的方式计算
### 代码
```java
class Solution {
    public boolean isBoomerang(int[][] points) {
        int ax = points[0][0];
        int ay = points[0][1];
        int bx = points[1][0];
        int by = points[1][1];
        int cx = points[2][0];
        int cy = points[2][1];
        
        double s1 = (ax - bx) * (by - cy);
        double s2 = (bx - cx) * (ay - by);
        
        return s1 != s2;
    }
}
```
# LeetCode_724_寻找数组的中心索引
## 题目
给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。

我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。

如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。

示例 1:
```
输入: 
nums = [1, 7, 3, 6, 5, 6]
输出: 3
解释: 
索引3 (nums[3] = 6) 的左侧数之和(1 + 7 + 3 = 11)，与右侧数之和(5 + 6 = 11)相等。
同时, 3 也是第一个符合要求的中心索引。
```
示例 2:
```
输入: 
nums = [1, 2, 3]
输出: -1
解释: 
数组中不存在满足此条件的中心索引。
```
说明:
```
nums 的长度范围为 [0, 10000]。
任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
```
## 解法
### 思路
- 第一次遍历数组，求出总和sum
- 第二次遍历数组，记录当前元素之前的总和，看当前元素与之前元素总和的2倍之和是否等于sum，是的话就返回下标
### 代码
```java
class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0 , pre = 0;
        for (int num : nums) {
            sum += num;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (2 * pre + nums[i] == sum) {
                return i;
            }
            
            pre += nums[i];
        }
        
        return -1;
    }
}
```
# LeetCode_219_存在重复元素II
## 题目
给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。

示例 1:
```
输入: nums = [1,2,3,1], k = 3
输出: true
```
示例 2:
```
输入: nums = [1,0,1,1], k = 1
输出: true
```
示例 3:
```
输入: nums = [1,2,3,1,2,3], k = 2
输出: false
```
## 解法
### 思路
- 第一次循环找到所有元素对应的下标
- 遍历记录元素下标的集合，判断是否有距离绝对值等于k的
### 代码
```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> list;
            if (map.containsKey(num)) {
                list = map.get(num);
            } else {
                list = new ArrayList<>();
                map.put(num, list);
            }
            list.add(i);
        }
        
        for (List<Integer> list : map.values()) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (Math.abs(list.get(i) - list.get(j)) <= k) {
                        return true;
                    }
                }
            }
        }
        return false;     
    }
}
```
## 优化代码
### 思路
使用解法一的思路，其实是审题不清楚，意味距离必需是k，所以需要记录所有的下标，依次比较。如果不需要比较，其实就只要看距离最近的两个元素之间的下标距离是否<=k就可以了。map里只需要存储一个int的包装类
### 代码
```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && Math.abs(i - map.get(nums[i])) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
```
## 解法二
### 思路
判断数组中所有可能存在的距离为k的元素是否相等，同时不断缩小k，但如果k很大，则需要遍历的次数会很多，尤其是k很大距离很小的情况
### 代码
```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length == 0) {
            return false;
        }
        
        while (k > 0) {
            for (int i = 0; i < nums.length - k; i++) {
                if (nums[i] == nums[i + k]) {
                    return true;
                }
            }
            
            k--;
        }
        
        return false;
    }
}
```
# LeetCode_643_子数组最大平均值I
## 题目
给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。

示例 1:
```
输入: [1,12,-5,-6,50,3], k = 4
输出: 12.75
解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
```
注意:
```
1 <= k <= n <= 30,000。
所给数据范围 [-10,000，10,000]。
```
## 解法
### 思路
遍历数组，计算每k个元素的平均值，找最大值
### 代码
```java
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        Integer pre = null;
        for (int i = 0; i <= nums.length - k; i++) {
            if (pre == null) {
                pre = 0;
                int tmp = k;
                while (tmp > 0) {
                    pre += nums[i + tmp-- - 1];
                }
            } else {
                pre = pre - nums[i - 1] + nums[i + k - 1];
            }
            max = Math.max(max, pre);
        }
        return max * 1D / k;
    }
}
```
## 优化代码
### 思路
不使用null值做初始判断，直接先计算一次第一批k个元素的平均值，然后再继续遍历数组，和解法一的过程类似
### 代码
```java
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int pre = 0;
        for (int i = 0; i < k; i++) {
            pre += nums[i];
        }
        int max = pre;
        for (int i = 1; i <= nums.length - k; i++) {
            pre = pre - nums[i - 1] + nums[i + k - 1];
            max = Math.max(pre, max);
        }
        return max * 1D / k;
    }
}
```
# LeetCode_507_完美数
## 题目
对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。

给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False

示例：
```
输入: 28
输出: True
解释: 28 = 1 + 2 + 4 + 7 + 14
```
提示：
```
输入的数字 n 不会超过 100,000,000. (1e8)
```
## 解法
### 思路
- 完美数的因子不包括本身，1去除
- 因为除了1和整数平方根外，正整数因子都是成对出现，所以只需要遍历到num的平方根处即可
- 计算因子之和是否等于其本身
### 代码
```java
class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }

        int sum = 1, i = 2;
        double sqrt = Math.sqrt(num);
        for (; i < sqrt; i++) {
            if (num % i == 0) {
                sum += i;
                sum += num / i;
            }
        }
        
        if (num % sqrt == 0) {
            sum += sqrt;
        }
        
        return sum == num;
    }
}
```
# LeetCode_819_最常见的单词
## 题目
给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。题目保证至少有一个词不在禁用列表中，而且答案唯一。

禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。

示例：
```
输入: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
输出: "ball"
解释: 
"hit" 出现了3次，但它是一个禁用的单词。
"ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。 
注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"）， 
"hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
```
说明：
```
1 <= 段落长度 <= 1000.
1 <= 禁用单词个数 <= 100.
1 <= 禁用单词长度 <= 10.
答案是唯一的, 且都是小写字母 (即使在 paragraph 里是大写的，即使是一些特定的名词，答案都是小写的。)
paragraph 只包含字母、空格和下列标点符号!?',;.
不存在没有连字符或者带有连字符的单词。
单词里只包含字母，不会出现省略号或者其他标点符号。
```
## 解法
### 思路
- 先处理句子中的所有单词为小写
- 循环句子，将英文字母放入StringBuilder中，直到不是英文字母为止
- 将sb放入map中计数，并初始化sb
- 循环结束后再进行一次map的put动作，将sb中最后的单词放入
- 遍历map，查找最大数，且同时判断是否在禁用单词列表中
- 返回出现次数最多的单词
### 代码
```java
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (char c : paragraph.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            } else {
                String str = sb.toString();
                if ("".equals(str)) {
                    continue;
                }
                map.put(str, map.getOrDefault(str, 0) + 1);
                sb = new StringBuilder();
            }
        }

        String str = sb.toString();
        if (!"".equals(str)) {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
        }

        String max = null;
        for (String word : map.keySet()) {
            if (max == null) {
                max = validate(max, word, banned);
            } else {
                max = map.get(max) < map.get(word) ? validate(max, word, banned) : max;
            }
        }
        return max;
    }

    private String validate(String max, String str, String[] banned) {
        boolean noMatch = true;
        for (String banStr : banned) {
            if (banStr.equals(str)) {
                noMatch = false;
                break;
            }
        }
        return noMatch ? str : max;
    }
}
```
# LeetCode_1128_等价多米诺骨牌对的数量
## 题目
给你一个由一些多米诺骨牌组成的列表 dominoes。

如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。

形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。

在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。

示例：
```
输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
输出：1
```
## 失败解法
### 失败原因
超时
### 思路
嵌套循环整个二维数组，比较两个数组之间元素是否相等
### 代码
```java
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int sum = 0;
        for (int i = 0; i < dominoes.length; i++) {
            for (int j = i + 1; j < dominoes.length; j++) {
                int[] left = dominoes[i];
                int[] right = dominoes[j];

                if ((left[0] == right[0]) && (right[1] == left[1]) || (left[0] == right[1] && left[1] == right[0])) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
```
## 解法一
### 思路
- 遍历二维数组，使用嵌套的map来记录每一个多米诺骨牌，为了使反转的骨牌统一，统一小的数为元素1，大的为元素2，这样进行统计
- 遍历map，将内层map的次数取出，使用组合公式，`n * (n - 1) / 2`来计算每一组相同骨牌能组成的对数，并对结果进行累加
- 返回累加的值
### 代码
```java
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] arr : dominoes) {
            int one = arr[0];
            int two = arr[1];

            int out = Math.min(one, two);
            int in = Math.max(one, two);

            map.put(out, map.getOrDefault(out, new HashMap<>()));
            Map<Integer, Integer> innerMap = map.get(out);
            innerMap.put(in, innerMap.getOrDefault(in, 0) + 1);
        }

        int sum = 0;
        for (Map<Integer, Integer> innerMap : map.values()) {
            for (Integer num : innerMap.values()) {
                sum += num * (num - 1) / 2;
            }
        }
        return sum;
    }
}
```
## 解法二
### 思路
- 因为多米诺的每一个元素都是0-9的数字，所以可以用一个二维数组`int[10][10] dict`记录某一种多米诺出现的次数
- 之后就通过遍历dict，将下标交换后的元素与当前元素相加(元素相同的情况，不能相加)，通过解法一一样的公式累加入结果中
### 代码
```java
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[][] dict = new int[10][10];
        for (int[] dominoe : dominoes) {
            dict[dominoe[0]][dominoe[1]]++;
        }

        int sum = 0;
        for (int i = 0; i < dict.length; i++) {
            for (int j = i; j < dict.length; j++) {
                int num = dict[i][j];
                if (i != j) {
                    num += dict[j][i];
                }
                sum += num * (num - 1) / 2;
            }
        }

        return sum;
    }
}
```
# LeetCode_1033_移动石子直到连续
## 题目
三枚石子放置在数轴上，位置分别为 a，b，c。

每一回合，我们假设这三枚石子当前分别位于位置 x, y, z 且 x < y < z。从位置 x 或者是位置 z 拿起一枚石子，并将该石子移动到某一整数位置 k 处，其中 x < k < z 且 k != y。

当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。

要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves]

示例 1：
```
输入：a = 1, b = 2, c = 5
输出：[1, 2]
解释：将石子从 5 移动到 4 再移动到 3，或者我们可以直接将石子移动到 3。
```
示例 2：
```
输入：a = 4, b = 3, c = 2
输出：[0, 0]
解释：我们无法进行任何移动。
```
提示：
```
1 <= a <= 100
1 <= b <= 100
1 <= c <= 100
a != b, b != c, c != a
```
## 解法
### 思路
- 最大距离应该是最大值和最小值之间的距离-2
- 最小值是却决于中间值和左右两个点的距离中的最小值：
    - 如果为1：那么说明有两个点相邻，结果就是1
    - 如果为2：那么只要把另一个放到两个间隔为1的点中就可以，结果也是1
    - 如果大于2：那么就比上面两种情况多出一步，移动一个点到另一个点边上，形成上两种情况的任意只用，然后和上两种情况一下处理就好，所以是2
- 还有一种特殊情况，就是三个点已经是连续的，这时大小值都是零
### 代码
```java
class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        int mid = a + b + c - max - min;

        int leftDiff = max - mid;
        int rightDiff = mid - min;
        if (leftDiff == 1 && leftDiff == rightDiff) {
            return new int[2];
        }
        
        int diff = Math.min(leftDiff, rightDiff);

        return new int[]{diff <= 2 ? 1: 2, max - min - 2};
    }
}
```
# LeetCode_168_Excel表列名称
## 题目
给定一个正整数，返回它在 Excel 表中相对应的列名称。

例如，
```
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
```
示例 1:
```
输入: 1
输出: "A"
```
示例 2:
```
输入: 28
输出: "AB"
```
示例 3:
```
输入: 701
输出: "ZY"
```
## 解法
### 思路
10进制转26进制，通过`StringBuilder.insert(0, char)`来记录:
- 如果被26整除，说明有进位，需要再`n /= 26`的基础上减去进位的1，这个进位的1由Z在低位代表
- 如果没有被整除，可以使用`(char)(n % 26 - 1 + 'A')`的方式表示
### 代码
```java
class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int mod = n % 26;
            n /= 26;
            if (mod == 0) {
                sb.insert(0, 'Z');
                n--;
            } else {
                sb.insert(0, (char)(mod - 1 + 'A'));
            }
        }
        return sb.toString();
    }
}
```
## 优化代码
### 思路
通过整理解法一，发现无论是否被整除，n总是要-1的，且先-1后，整个过程就整洁多了。
### 代码
```java
class Solution {
    public String convertToTitle(int n) {
        if (n <= 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.insert(0, (char)(n % 26 + 'A'));
            n /= 26;
        }
        return sb.toString();
    }
}
```
# LeetCode_941_有效的山脉数组
## 题目
给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。

让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：

A.length >= 3
在 0 < i < A.length - 1 条件下，存在 i 使得：
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[B.length - 1]

示例 1：
```
输入：[2,1]
输出：false
```
示例 2：
```
输入：[3,5,5]
输出：false
```
示例 3：
```
输入：[0,3,2,1]
输出：true
```
提示：
```
0 <= A.length <= 10000
0 <= A[i] <= 10000 
```
## 解法
### 思路
- 有效的数组元素的排列有依次的两种状态：
    - 先升序
    - 再降序
- 升序转降序可以，但只能有1次
- 降序不能再转成升序
- 连续的元素不能相等
### 代码
```java
class Solution {
    public boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }
        if (A[1] <= A[0]) {
            return false;
        }

        boolean up = true;
        for (int i = 2; i < A.length; i++) {
            if (A[i] == A[i - 1]) {
                return false;
            }

            if (A[i] > A[i - 1]) {
                if (!up) {
                    return false;
                }
            } else {
                if (up) {
                    up = false;
                }
            }
        }

        return !up;
    }
}
```
## 解法二
### 思路
使用双指针，从两头开始遍历，因为是只能先上再下，所以有效的数组应该会使得双指针在单调递增的过程中相会
### 代码
```java
class Solution {
    public boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }
        
        int head = 0, tail = A.length - 1;
        while (head < A.length - 2 && A[head] < A[head + 1]) {
            head++;
        }
        
        while (tail > 1 && A[tail] < A[tail - 1]) {
            tail--;
        }
        
        return head == tail;
    }
}
```
# LeetCode_14_最长公共前缀
## 题目
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

示例 1:
```
输入: ["flower","flow","flight"]
输出: "fl"
```
示例 2:
```
输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
```
说明:
```
所有输入只包含小写字母 a-z 。
```
## 解法
### 思路
嵌套循环：
- 外层用来移动String的charAt对用的指针
- 内存遍历n个字符串，看他们外层指针对应的字符是否相等
- 如果都相等继续移动，并计数，否则就停止并返回结果
- 外层循环的次数取决于字符串中的最小长度
### 代码
```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        int len = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            len = Math.min(len, strs[i].length());
        }

        int count = 0;
        for (int i = 0; i < len; i++) {
            char c = strs[0].charAt(i);
            boolean equal = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    equal = false;
                    break;
                }
            }
            if (equal) {
                count++;
            } else {
                break;
            }
        }
        
        return strs[0].substring(0, count);
    }
}
```
# LeetCode_400_第n个数字
## 题目
在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。

注意:
```
n 是正数且在32为整形范围内 ( n < 231)。
```
示例 1:
```
输入:
3

输出:
3
```
示例 2:
```
输入:
11

输出:
0

说明:
第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
```
## 解法
### 思路
- 找到规律
    - 1-9是1位的，有9个
    - 10-99是2位的，90个
    - 100-999是3位的，有900个
    - k位数有`9 * 10 ^ (k - 1)`个
- 循环递增k，计算减去k位数后是否<0，如果是就计算是第几个k位数，并算出处在该数的第几位上
### 代码
```java
class Solution {
    public int findNthDigit(int n) {
        double dn = n;
        int k = 0, count = 0;
        while (dn > 0) {
            dn = dn - 9 * Math.pow(10, ++k - 1) * k;
        }
        dn += 9 * Math.pow(10, k - 1) * k;
        while (dn > 0) {
            dn -= k;
            count++;
        }
        double num = Math.pow(10, k - 1) + count - 1;
        while (dn++ < 0) {
            num /= 10;
        }
        return (int)num % 10;
    }
}
```
## 优化代码
### 思路
- 先计算n处在到第几位数
- 计算处在当前位中的中第几个数
- 再算n具体处在这个数的第几位
### 代码
```java
class Solution {
    public int findNthDigit(int n) {
        int digit = 1;
        while (n > digit * Math.pow(10, digit - 1) * 9) {
            n -= digit * Math.pow(10, digit - 1) * 9;
            digit++;
        }

        int num = (n - 1) / digit + (int)Math.pow(10, digit - 1);
        
        int index = n % digit == 0 ? digit - 1 : n % digit - 1;
        return Integer.toString(num).charAt(index) - '0';
    }
}
```
# LeetCode_278_第一个错误的版本
## 题目
你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。

假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。

你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。

示例:
```
给定 n = 5，并且 version = 4 是第一个错误的版本。

调用 isBadVersion(3) -> false
调用 isBadVersion(5) -> true
调用 isBadVersion(4) -> true

所以，4 是第一个错误的版本。 
```
## 解法
### 思路
使用二分法搜索
- n-1=false,n=true的情况是答案
- n-1=true,n=true的情况说明n大了
- n-1=false,n=false的情况说明n小了
### 代码
```java
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if(isBadVersion(1)) {
            return 1;
        }
        
        int head = 2, tail = n;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if (!isBadVersion(mid - 1) && isBadVersion(mid)) {
                return mid;
            }

            if (isBadVersion(mid) && isBadVersion(mid - 1)) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }

        return 0;
    }
}
```
## 优化代码
### 思路
二分的过程中可以不用同时考虑前后两个元素的结果，while语句不设置除head<=tail之外的退出条件，让头尾指针一直趋近，直到退出，这时候就会有且只有两种情况
- head指针指向的元素是true，那么就是结果
- head指针指向的元素是false，那么指针指在了结果的左边，只需要+1就可以
### 代码
```java
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int head = 0, tail = n;
            while (head <= tail) {
                int mid = head + (tail - head) / 2;
                if (isBadVersion(mid)) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            }
            return isBadVersion(head) ? head : head + 1;
    }
}
```
## 优化代码
### 思路
还可以在二分的时候再优化头尾指针变化的方式，因为要找到的第一个为true的元素，所以，当mid为true的时候，mid有可能就是结果，所以此时`tail = mid`，而如果mid为false的时候，那么mid + 1有可能是结果，所以`head = mid + 1`，那么最终就应该是head和tail都指向正确的元素，那么退出条件就应该是`head < tail`
### 代码
```java
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int head = 0, tail = n;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (isBadVersion(mid)) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        return head;
    }
}
```
# LeetCode_7_整数反转
## 题目
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:
```
输入: 123
输出: 321
```
 示例 2:
```
输入: -123
输出: -321
```
示例 3:
```
输入: 120
输出: 21
```
注意:
```
假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
```
## 解法
### 思路
- 将数字转成字符数组
- 处理负号，并通过头尾指针进行换位
- 过滤掉头部所有连续的0
- 通过StringBuilder的`append`方法累加字符
- 根据x是否是负数加上-号
- 转成long类型的数字，防止int值溢出
- 判断是否超出int类型的范围，如果超出返回0
- 否则返回将long类型强转并返回
### 代码
```java
class Solution {
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }

        String numStr = Integer.toString(x);
        if (x < 0) {
            numStr = numStr.substring(1);
        }

        char[] nums = numStr.toCharArray();
        int head = 0, tail = nums.length - 1;
        while (head < tail) {
            nums[head] ^= nums[tail];
            nums[tail] ^= nums[head];
            nums[head] ^= nums[tail];

            head++;
            tail--;
        }

        int index = 0;
        while (nums[index] == '0') {
            index++;
        }

        StringBuilder sb = new StringBuilder();
        for (; index < nums.length; index++) {
            sb.append(nums[index]);
        }

        if (x < 0) {
            sb.insert(0, "-");
        }

        long ansL = Long.parseLong(sb.toString());
        return ansL > Integer.MAX_VALUE || ansL < Integer.MIN_VALUE ? 0 :(int)ansL;
    }
}
```
## 解法二
### 思路
不使用字符串来转换，直接通过取余和乘10进位的方式
- 使用long类型变量ans来作为累加过程中的暂存值
- 创建循环体，退出条件是x == 0
- 获得`x % 10`的余数并与`ans * 10`相加作为新的ans，取余就是获得x的最低位的值，并通过累加的方式放在了对应的高位
- 同时判断ans是否超过了int值的范围，如果超过就直接返回0
- 同时通过`x / 10`截取x
- 直到循环退出，强转ans并返回
### 代码
```java
class Solution {
    public int reverse(int x) {
        long ans = 0;
        while (x != 0) {
            ans = ans * 10 + x % 10;
            if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
                return 0;
            }
            x /= 10;
        }
        
        return (int)ans;
    }
}
```
# LeetCode_535_TinyURL的加密与解密
## 题目
TinyURL是一种URL简化服务， 比如：当你输入一个URL https://leetcode.com/problems/design-tinyurl 时，它将返回一个简化的URL http://tinyurl.com/4e9iAk.

要求：设计一个 TinyURL 的加密 encode 和解密 decode 的方法。你的加密和解密算法如何设计和运作是没有限制的，你只需要保证一个URL可以被加密成一个TinyURL，并且这个TinyURL可以用解密方法恢复成原本的URL。
## 解法
### 思路
使用map和伪随机函数来记录url，默认生成长度为5的字符串，如果map中已存在，就增加一个长度并再次判断，直到map中不存在
### 代码
```java
public class Codec {
    private Map<String, String> map = new HashMap<>();
    private int max = 'z';
    private Random random = new Random();
    public String encode(String longUrl) {
        String key = "";
        int i = 0;
        while (i++ < 5) {
            key += (char) random.nextInt(max);
        }

        while (map.containsKey(key)) {
            key += (char) random.nextInt(max);
        }

        map.put(key, longUrl);
        return "http://" + key;
    }

    public String decode(String shortUrl) {
        return map.get(shortUrl.substring(7));
    }
}
```