# LeetCode_697_数组的度
## 题目
给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。

你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。

示例 1:
```
输入: [1, 2, 2, 3, 1]
输出: 2
解释: 
输入数组的度是2，因为元素1和2的出现频数最大，均为2.
连续子数组里面拥有相同度的有如下所示:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
最短连续子数组[2, 2]的长度为2，所以返回2.
```
示例 2:
```
输入: [1,2,2,3,1,4,2]
输出: 6
```
注意:
```
nums.length 在1到50,000区间范围内。
nums[i] 是一个在0到49,999范围内的整数。
```
## 解法
### 思路
- 遍历数组，通过桶的方法，算出数组的度
- 同时还要有个桶，记录元素第一次出现时候的下标
- 然后通过次数和下标，再遍历原数组，看看长度是多少，如果又度一样的元素，就取短的那个
### 代码
```java
class Solution {
    public int findShortestSubArray(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        
        int[] countBucket = new int[max + 1];
        Integer[] indexBucket = new Integer[max + 1];
        
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            countBucket[nums[i]]++;
            maxCount = Math.max(countBucket[nums[i]], maxCount);
            
            if (indexBucket[nums[i]] == null) {
                indexBucket[nums[i]] = i;
            }
        }
        
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < countBucket.length; i++) {
            if (countBucket[i] == maxCount) {
                int tmpCount = maxCount;
                int tmpIndex = indexBucket[i];
                int tmpLen = 0;
                while (tmpCount > 0) {
                    if (nums[tmpIndex++] == i) {
                        tmpCount--;
                    }
                    tmpLen++;
                }
                len = Math.min(len, tmpLen);
            }
        }
        return len;
    }
}
```
# LeetCode_551_学生出勤记录
## 题目
给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：
```
'A' : Absent，缺勤
'L' : Late，迟到
'P' : Present，到场
如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),那么这个学生会被奖赏。

你需要根据这个学生的出勤记录判断他是否会被奖赏。
```
示例 1:
```
输入: "PPALLP"
输出: True
```
示例 2:
```
输入: "PPALLL"
输出: False
```
## 解法
### 思路
- 看缺勤是否符合条件：用String.replace后的字符串和源字符串来比较长度差是否超过1
- 看迟到是否符合条件：通过遍历字符数组
### 代码
```java
class Solution {
    public boolean checkRecord(String s) {
        if (s.length() - s.replaceAll("A", "").length() > 1) {
            return false;
        }
        
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == 'L' && s.charAt(i + 1) == 'L' && s.charAt(i + 2) == 'L') {
                return false;
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
解法一中看是否迟到，也可以使用String.replace的方法，replace一下三个L，看看长度是否改变
### 代码
```java
class Solution {
    public boolean checkRecord(String s) {
        if (s.length() - s.replaceAll("A", "").length() > 1) {
            return false;
        }

        return s.length() == s.replaceAll("LLL", "").length();
    }
}
```
## 解法三
### 思路
解法一中看是否阙清，也可以使用数组遍历的方式，使用一个变量count来计算A出现的次数
### 代码
```java
class Solution {
    public boolean checkRecord(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                if (count++ == 1) {
                    return false;
                }
            }
        }

        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == 'L' && s.charAt(i + 1) == 'L' && s.charAt(i + 2) == 'L') {
                return false;
            }
        }

        return true;
    }
}
```
# LeetCode_492_构造矩形
## 题目
作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：
```
1. 你设计的矩形页面必须等于给定的目标面积。

2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。

3. 长度 L 和宽度 W 之间的差距应当尽可能小。
你需要按顺序输出你设计的页面的长度 L 和宽度 W。
```
示例：
```
输入: 4
输出: [2, 2]
解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。
```
说明:
```
给定的面积不大于 10,000,000 且为正整数。
你设计的页面的长度和宽度必须都是正整数。
```
## 失败解法
### 思路
- 对area开根号，并对结果向下取整得到num
- 如果num与开根号的值相等，就返回这个值
- 否则就嵌套循环遍历
    - 外层是从1到num
    - 内层是从area到num
- 并且优化一下遍历的范围，如果发现成绩小于area了，就终止内层循环，进入下一个外层循环    

但是超出了时间限制
### 代码
```java
class Solution {
    public int[] constructRectangle(int area) {
        double squareRoot = Math.sqrt(area);
        int num = (int)squareRoot;
        if ((double)num == squareRoot) {
            return new int[]{num, num};
        }
        
        int min = Integer.MAX_VALUE;
        int[] ans = new int[2];
        for (int i = 1; i <= num; i++) {
            for (int j = area; j > num; j--) {
                if (i * j < area) {
                    break;
                }
                
                if (i * j == area && j - i < min) {
                    min = j - i;
                    ans[0] = j;
                    ans[1] = i;
                }
            }
        }
        return ans;
    }
}
```
## 解法
### 思路
失败的解法太蠢，直接求出平方根向下取整以后，如果不满足，那么不需要嵌套循环，只要递减或递增求出其中一个因数就可以了呀，判断的依据就是是否取余等于0，然后再通过area / num获得另一个数，只要位置放对就好了。
### 代码
```java
class Solution {
    public int[] constructRectangle(int area) {
        int num = (int) Math.sqrt(area);

        while (area % num != 0) {
            num--;
        }

        return new int[]{area / num, num};
    }
}
```
# LeetCode_541_反转字符串II
## 题目
给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。

示例:
```
输入: s = "abcdefg", k = 2
输出: "bacdfeg"
```
要求:
```
该字符串只包含小写的英文字母。
给定字符串的长度和 k 在[1, 10000]范围内。
```
## 解法
### 思路
- 遍历字符数组，根据要求旋转
### 代码
```java
class Solution {
    public String reverseStr(String s, int k) {
        int index = 0;
        char[] cs = s.toCharArray();
        while (index + 2 * k <= s.length() - 1) {
            int head = index, tail = index + k - 1;
            reserve(cs, head, tail);
            index += 2 * k;
        }

        if (index < s.length() - 1) {
            int tail = Math.min(index + k - 1, s.length() - 1);
            reserve(cs, index, tail);   
        }

        return new String(cs);
    }
    
    private void reserve(char[] cs, int head, int tail) {
        while (head < tail) {
            cs[head] ^= cs[tail];
            cs[tail] ^= cs[head];
            cs[head] ^= cs[tail];

            head++;
            tail--;
        }
    }
}
```
# LeetCode_1154_一年中的第几天
## 题目
给你一个按 YYYY-MM-DD 格式表示日期的字符串 date，请你计算并返回该日期是当年的第几天。

通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。

示例 1：
```
输入：date = "2019-01-09"
输出：9
```
示例 2：
```
输入：date = "2019-02-10"
输出：41
```
示例 3：
```
输入：date = "2003-03-01"
输出：60
```
示例 4：
```
输入：date = "2004-03-01"
输出：61
```
提示：
```
date.length == 10
date[4] == date[7] == '-'，其他的 date[i] 都是数字。
date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日。
```
## 解法
### 思路
- 初始化一个数组，下标是月份，元素是天数
- split字符串为三部分，判断第一部分的年份是否是闰年，从而决定是否+1
### 代码
```java
class Solution {
    public int dayOfYear(String date) {
        int[] dayOfMonth = new int[] {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        String[] factors = date.split("-");
        int year = Integer.parseInt(factors[0]);
        int month = Integer.parseInt(factors[1]);
        int day = Integer.parseInt(factors[2]);

        int days = 0;
        if (month > 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
            days += 1;
        }
        
        for (int i = 1; i < month; i++) {
            days += dayOfMonth[i];
        }
        
        days += day;
        
        return days;
    }
}
```
# LeetCode_844_比较含退格的字符串
## 题目
给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。

示例 1：
```
输入：S = "ab#c", T = "ad#c"
输出：true
解释：S 和 T 都会变成 “ac”。
```
示例 2：
```
输入：S = "ab##", T = "c#d#"
输出：true
解释：S 和 T 都会变成 “”。
```
示例 3：
```
输入：S = "a##c", T = "#a#c"
输出：true
解释：S 和 T 都会变成 “c”。
```
示例 4：
```
输入：S = "a#c", T = "b"
输出：false
解释：S 会变成 “c”，但 T 仍然是 “b”。
```
提示：
```
1 <= S.length <= 200
1 <= T.length <= 200
S 和 T 只含有小写字母以及字符 '#'。
```
## 解法
### 思路
- 从后往前遍历两个字符数组
- 初始化一个变量skip，用来记录需要回退的字符的个数
- 遇到#就累加skip
- 每次循环先判断skip是否为0，如果不为0，就跳过当前字符，并且skip--
- 如果不需要跳过，就将字符insert到StringBuilder中
- 最后判断两个StringBuilder是否相等
### 解法
```java
class Solution {
    public boolean backspaceCompare(String S, String T) {
        return generate(S).equals(generate(T));
    }
    
    private String generate(String str) {
        int skip = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '#') {
                skip++;
                continue;
            }

            if (skip > 0) {
                skip--;
                continue;
            }

            sb.insert(0, str.charAt(i));
        }
        
        return sb.toString();
    }
}
```
# LeetCode_796_旋转字符串
## 题目
给定两个字符串, A 和 B。

A 的旋转操作就是将 A 最左边的字符移动到最右边。 例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。如果在若干次旋转操作之后，A 能变成B，那么返回True。

示例 1:
```
输入: A = 'abcde', B = 'cdeab'
输出: true
```
示例 2:
```
输入: A = 'abcde', B = 'abced'
输出: false
```
注意：
```
A 和 B 长度不超过 100。
```
## 解法
### 思路
- 通过异或运算，原地交换元素位置
- 外部不断循环
- 内部：通过将第一个元素不断和后一位元素交换的方式，进行数组长度len - 1次的交换，达到题目旋转的要求
- 如果内部旋转完后和B相等，返回true
- 如果内部旋转完后和A相等，说明转了一圈也和B不匹配，返回false
### 代码
```java
class Solution {
    public boolean rotateString(String A, String B) {
        if (A.equals(B)) {
            return true;
        }
        
        String str = A;
        while (true) {
            str = rotate(str);
            if (A.equals(str)) {
                return false;
            }
            
            if (B.equals(str)) {
                return true;
            }
        }
    }
    
    private String rotate(String str) {
        char[] cs = str.toCharArray();
        for (int i = 0; i < str.length() - 1; i++) {
            cs[i] ^= cs[i + 1];
            cs[i + 1] ^= cs[i];
            cs[i] ^= cs[i + 1];
        }
        return new String(cs);
    }   
}
```
## 解法二
### 思路
因为是旋转的，所以如果再append一个字符串，那么可以旋转的字符串一定是可以包含在扩展的字符串里的，同时字符串长度要相等。
### 代码
```java
class Solution {
    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    } 
}
```
# LeetCode_345_反转字符串中的元音字母
## 题目
编写一个函数，以字符串作为输入，反转该字符串中的元音字母。

示例 1:
```
输入: "hello"
输出: "holle"
```
示例 2:
```
输入: "leetcode"
输出: "leotcede"
说明:
元音字母不包含字母"y"。
```
## 解法
### 思路
- 生成储存元音字母的数据结构
- 初始化头尾两个指针
- 头尾双向遍历字符数组，同时找到元音字母，进行换位操作
### 代码
```java
class Solution {
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');

        char[] cs = s.toCharArray();

        int head = 0, tail = s.length() - 1;
        while (head < tail) {
            while (!set.contains(cs[head])) {
                head++;
                
                if (head >= s.length()) {
                    break;
                }
            }
            
            if (head >= s.length()) {
                break;
            }

            while (!set.contains(cs[tail])) {
                tail--;
                
                if (tail < 0) {
                    break;
                }
            }
            
            if (tail < 0) {
                break;
            }

            if (head >= tail) {
                break;
            }

            cs[head] ^= cs[tail];
            cs[tail] ^= cs[head];
            cs[head] ^= cs[tail];

            head++;
            tail--;
        }

        return new String(cs);
    }
}
```
## 优化代码
### 思路
- 不使用存储元音字母的数据结构，而是使用或运算判断
- 简化逻辑分支，因为如果head < tail不满足，第一次发生的情况就是head == tail的情况，这种情况直接退出
### 代码
```java
class Solution {
    public String reverseVowels(String s) {
        char[] cs = s.toCharArray();
        int head = 0, tail = s.length() - 1;

        while (head < tail) {
            while (head < tail && !isVowel(cs[head])) {
                head++;
            }

            while (head < tail && !isVowel(cs[tail])) {
                tail--;
            }

            if (head == tail) {
                break;
            }

            cs[head] ^= cs[tail];
            cs[tail] ^= cs[head];
            cs[head] ^= cs[tail];

            head++;
            tail--;
        }

        return new String(cs);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O'
                || c == 'U';
    }
}
```
# LeetCode_112_路径总和
## 题目
给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

说明:
```
 叶子节点是指没有子节点的节点。
```
示例: 
```
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
```
## 解法
### 思路
dfs递归遍历
### 代码
```java
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, sum, 0);
    }
    
    private boolean dfs(TreeNode node, int sum, int add) {
        if (node == null) {
            return false;
        }
        
        if (node.left == null && node.right == null) {
            if (add + node.val == sum) {
                return true;
            }
        }
        
        return dfs(node.left, sum, add + node.val) || dfs(node.right, sum, add + node.val);
    }
}
```
# LeetCode_263_丑数
## 题目
编写一个程序判断给定的数是否为丑数。

丑数就是只包含质因数 2, 3, 5 的正整数。

示例 1:
```
输入: 6
输出: true
解释: 6 = 2 × 3
```
示例 2:
```
输入: 8
输出: true
解释: 8 = 2 × 2 × 2
```
示例 3:
```
输入: 14
输出: false 
解释: 14 不是丑数，因为它包含了另外一个质因数 7。
```
说明：
```
1 是丑数。
输入不会超过 32 位有符号整数的范围: [−231,  231 − 1]。
```
## 解法
### 思路
暴力解
- 如果小于等于0，直接false
- 循环取余，循环退出条件时num == 1
- 如果出现三种质数都不能整除的数，就返回false，否则当数为1时，返回true
### 代码
```java
class Solution {
    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        
        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2;
                continue;
            } 
            
            if (num % 3 == 0) {
                num /= 3;
                continue;
            } 
            
            if (num % 5 == 0) {
                num /= 5;
                continue;
            }
            
            return false;
        }
        
        return true;
    }
}
```
# LeetCode_993_二叉树的堂兄弟节点
## 题目
在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。

如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。

我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。

只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。

示例 1：
```
输入：root = [1,2,3,4], x = 4, y = 3
输出：false
```
示例 2：
```
输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
输出：true
```
示例 3：
```
输入：root = [1,2,3,null,4], x = 2, y = 3
输出：false
```
提示：
```
二叉树的节点数介于 2 到 100 之间。
每个节点的值都是唯一的、范围为 1 到 100 的整数。
```
## 解法
### 思路
dfs递归遍历，找到两个节点的深度和父节点，进行比较
### 代码
```java
class Solution {
    private Map<Integer, Integer> depthMap = new HashMap<>();
    private Map<Integer, TreeNode> parMap = new HashMap<>();

    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, null);
        return depthMap.get(x).equals(depthMap.get(y)) && parMap.get(x) != parMap.get(y);
    }

    private void dfs(TreeNode node, TreeNode par) {
        if (node == null) {
            return;
        }
        
        depthMap.put(node.val, par != null ? depthMap.get(par.val) + 1: 0);
        parMap.put(node.val, par);
        dfs(node.left, node);
        dfs(node.right, node);
    }
}
```
# LeetCode_83_删除排序链表中的重复元素
## 题目
给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:
```
输入: 1->1->2
输出: 1->2
```
示例 2:
```
输入: 1->1->2->3->3
输出: 1->2->3
```
## 解法
### 思路
- 使用set存储遍历链表过程中找到的元素
- 适用一个指针指向遍历节点的上一个节点，方便做删除操作
- 当发现set中已有当前元素时，就进行删除操作
### 代码
```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        
        ListNode node = head.next;
        ListNode pre = head;
        while (node != null) {
            if (set.contains(node.val)) {
                pre.next = node.next;
            } else {
                set.add(node.val);
                pre = node;
            }
            node = node.next;
        }

        return head;
    }
}
```
## 解法二
### 思路
递归删除。
- 过程：一直递归搜索到链表倒数第二个节点，从倒数第二个节点开始和后一个节点进行比较，如果相等，就返回后一个节点到上一层
- 退出：因为要遍历到倒数第二个节点，所以是node == null || node.next == null
- 返回：如果比较相等就返回后一个节点作为上一层，也就是前两个的那个节点，的next指针指向的节点
### 代码
```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }
}
```
## 解法三
### 思路
解法一中，不需要适用set，因为链表是排序的，所以重复只会出现在前后节点之间，可以直接通过遍历节点，比较前后节点值的方式来进行删除操作。
### 代码
```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode node = head;
        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        
        return head;
    }
}
```
# LeetCode_405_数字转换为十六进制数
## 题目
给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。

注意:
```
十六进制中所有字母(a-f)都必须是小写。
十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
给定的数确保在32位有符号整数范围内。
不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
```
示例 1：
```
输入:
26

输出:
"1a"
```
示例 2：
```
输入:
-1

输出:
"ffffffff"
```
## 解法
### 思路
通过每4位对应16进制1位的方式，不断地右移转换成16进制数，直到右移为0。
### 代码
```java
class Solution {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        
        char[] dict = new char[] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.insert(0, dict[num & 15]);
            num >>>= 4;
        }
        
        return sb.toString();
    }
}
```
# LeetCode_1005_k次取反后最大化的数组和
## 题目
给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）

以这种方式修改数组后，返回数组可能的最大和。

示例 1：
```
输入：A = [4,2,3], K = 1
输出：5
解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
```
示例 2：
```
输入：A = [3,-1,0,2], K = 3
输出：6
解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
```
示例 3：
```
输入：A = [2,-3,-1,5,-4], K = 2
输出：13
解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
```
提示：
```
1 <= A.length <= 10000
1 <= K <= 10000
-100 <= A[i] <= 100
```
## 解法
### 思路
- 使用桶记录A数组元素值的个数
- 然后从最小值开始遍历桶，将K值根据桶中记录的个数进行递减，同时进行取反后的累加，直到负数遍历完或者k的个数用完
- 如果k的个数还没有用完，且是奇数个的化，就比较遍历到的当前元素和前一个元素之间的绝对值谁小，就取这个数的负绝对值计算到sum中
- 然后继续遍历，最后返回sum。
### 代码
```java
class Solution {
    public int largestSumAfterKNegations(int[] A, int K) {
        int[] bucket = new int[201];
        for (int num: A) {
            bucket[num + 100]++;
        }

        int sum = 0;
        Integer pre = null;
        for (int i = 0; i < bucket.length; i++) {
            if (i < 100 && bucket[i] > 0) {
                pre = i - 100;
                if (K >= bucket[i]) {
                    K -= bucket[i];
                    sum += -bucket[i] * (i - 100);
                    continue;
                }

                if (K > 0) {
                    sum += (bucket[i] - 2 * K) * (i - 100);
                    K = 0;
                    continue;
                }

                sum += bucket[i] * (i - 100);
            }

            if (i >= 100 && bucket[i] > 0) {
                if (K > 0) {
                    K %= 2;
                    if (K == 1) {
                        if (pre == null) {
                            sum += -(i - 100) + (bucket[i] - 1) * (i - 100);
                            K = 0;
                        } else {
                            if (Math.abs(pre) < i - 100) {
                                sum += (i - 100) - 2 * Math.abs(pre) + (bucket[i] - 1) * (i - 100);
                            } else {
                                sum += (bucket[i] - 2) * (i - 100);
                            }
                            K = 0;
                        }
                        continue;
                    }
                }
                sum += bucket[i] * (i - 100);
            }
        }

        return sum;
    }
}
```
## 解法二
### 思路
解法一因为要在一次遍历中处理完所有的内容，其中的逻辑分支太多，且复杂。可以多次循环，其中一次根据K的数量处理存在的负数，最后一次负责累加。时间复杂度同样是O(N)
- 先根据K的个数处理相应的负数，处理时把位于负数位置的元素搬到对应的正数位置
- 这样做不仅有利于累加计算，而且，当如果K有多且为奇数的时候，只需要从0开始找到第一个正元素，此时这个正元素可能是原数组里正数，也可能时原数组里最大地负数，这样就很巧妙地规避了解法一中复杂地逻辑判断
### 代码
```java
class Solution {
    public int largestSumAfterKNegations(int[] A, int K) {
        int[] bucket = new int[201];
        int sum = 0;
        for (int num : A) {
            bucket[num + 100]++;
        }

        int i = 0;
        while (K != 0 && i < 100) {
            while (K != 0 && bucket[i] != 0) {
                bucket[i]--;
                bucket[200 - i]++;
                K--;
            }

            i++;

            if (K == 0) {
                break;
            }
        }

        if (K != 0 && bucket[100] != 0) {
            K = 0;
        }

        if (K % 2 != 0) {
            while (bucket[i] == 0) {
                i++;
            }

            if (i == 201) {
                return 0;
            }
            
            bucket[i]--;
            sum += -(i - 100);
        }

        for (i = 0; i < 201; i++) {
            sum += bucket[i] * (i - 100);
        }

        return sum;
    }
}
```
# LeetCode_415_字符串相加
## 题目
给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

注意：
```
num1 和num2 的长度都小于 5100.
num1 和num2 都只包含数字 0-9.
num1 和num2 都不包含任何前导零。
你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
```
## 解法
### 思路
- 反转一下字符数组
- 从字符串头部开始一个字符一个字符相加，并存储进位值，像拨算盘。
### 代码
```java
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder ansSb = new StringBuilder();
        
        char[] cs1 = reserve(num1.toCharArray());
        char[] cs2 = reserve(num2.toCharArray());
        
        int len = Math.min(cs1.length, cs2.length), carry = 0;
        
        for (int i = 0; i < len; i++) {
            int val = Integer.parseInt(Character.toString(cs1[i])) + Integer.parseInt(Character.toString(cs2[i])) + carry;
            carry = val / 10;
            ansSb.insert(0, Integer.toString(val % 10));
        }
        
        if (cs1.length == cs2.length) {
            return carry == 0 ? ansSb.toString() : ansSb.insert(0, carry).toString();
        }
        
        char[] arr = cs1.length > cs2.length ? cs1 : cs2;
        for (int i = len; i < arr.length; i++) {
            int val = Integer.parseInt(Character.toString(arr[i])) + carry;
            carry = val / 10;
            ansSb.insert(0, Integer.toString(val % 10));
        }

        return carry == 0 ? ansSb.toString() : ansSb.insert(0, carry).toString();
    }
    
    private char[] reserve(char [] cs) {
        int head = 0, tail = cs.length - 1;
        while (head < tail) {
            cs[head] ^= cs[tail];
            cs[tail] ^= cs[head];
            cs[head] ^= cs[tail];
            head++;
            tail--;
        }
        return cs;
    }
}
```
## 优化代码
### 思路
- 通过长度减去遍历的次数来获取从后往前的下标，从而省去解法一中的反转字符数组的步骤
- 进位只存在进1，所以可以直接在字符数组中修改，carry值只判断是否超过了10，省去了解法一中的取余等操作
### 代码
```java
class Solution {
    public String addStrings(String num1, String num2) {
        char[] cs1 = null;
        char[] cs2 = null;
        if (num1.length() > num2.length()) {
            cs1 = num1.toCharArray();
            cs2 = num2.toCharArray();
        } else {
            cs1 = num2.toCharArray();
            cs2 = num1.toCharArray();
        }

        int carry = 0;
        int i = 1;
        for (; i <= cs2.length; i++) {
            cs1[cs1.length - i] += cs2[cs2.length - i] - '0' + carry;
            if (cs1[cs1.length - i] > '9') {
                cs1[cs1.length - i] -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
        }

        if (carry > 0) {
            for (; i <= cs1.length; i++) {
                cs1[cs1.length - i] += carry;
                if (cs1[cs1.length - i] > '9') {
                    cs1[cs1.length - i] -= 10;
                    carry = 1;
                } else {
                    carry = 0;
                    break;
                }
            }
        }

        return carry > 0 ? "1" + String.valueOf(cs1) : String.valueOf(cs1);
    }
}
```
# LeetCode_997_找到小镇的法官
## 题目
在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。

如果小镇的法官真的存在，那么：
```
小镇的法官不相信任何人。
每个人（除了小镇法官外）都信任小镇的法官。
只有一个人同时满足属性 1 和属性 2 。
给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。
```
如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。

示例 1：
```
输入：N = 2, trust = [[1,2]]
输出：2
```
示例 2：
```
输入：N = 3, trust = [[1,3],[2,3]]
输出：3
```
示例 3：
```
输入：N = 3, trust = [[1,3],[2,3],[3,1]]
输出：-1
```
示例 4：
```
输入：N = 3, trust = [[1,2],[2,3]]
输出：-1
```
示例 5：
```
输入：N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
输出：3
```
提示：
```
1 <= N <= 1000
trust.length <= 10000
trust[i] 是完全不同的
trust[i][0] != trust[i][1]
1 <= trust[i][0], trust[i][1] <= N
```
## 解法
### 思路
这题考的是图，需要算入度(信任的人数)和出度(被信任的数量)，求出入度为0，出度为N - 1的节点就是法官
- 遍历trust，得到入读和出度
- 使用一个二维数组graph记录i下标对应的入度和出度
- 遍历graph找到符合题目要求的下标数组，返回该下标
### 代码
```java
class Solution {
    public int findJudge(int N, int[][] trust) {
        int[][] graph = new int[N][2];
        for (int[] arr : trust) {
            int a = arr[0];
            int b = arr[1];

            graph[a - 1][0]++;
            graph[b - 1][1]++;
        }

        for (int i = 0; i < graph.length; i++) {
            if (graph[i][0] == 0 && graph[i][1] == N - 1) {
                return i + 1;
            }
        }
        
        return -1;
    }
}
```
## 优化代码
### 思路
解法一中的二维数组可以换成一维数组，只累加被信任的次数，同时，如果信任他人就--，因为法官不信任他人，所以他的被信任数应该就是N - 1个
### 代码
```java
class Solution {
    public int findJudge(int N, int[][] trust) {
        int[] ans = new int[N];
        for (int[] arr : trust) {
            ans[arr[0] - 1]--;
            ans[arr[1] - 1]++;
        }
        
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == N - 1) {
                return i + 1;
            }
        }
        
        return -1;
    }
}
```
# LeetCode_599_两个列表的最小索引总和
## 题目
假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。

你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。

示例 1:
```
输入:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
输出: ["Shogun"]
解释: 他们唯一共同喜爱的餐厅是“Shogun”。
```
示例 2:
```
输入:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
输出: ["Shogun"]
解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
```
提示:
```
两个列表的长度范围都在 [1, 1000]内。
两个列表中的字符串的长度将在[1，30]的范围内。
下标从0开始，到列表的长度减1。
两个列表都没有重复的元素。
```
## 解法
### 思路
- 使用一个map存储其中一个的喜好餐厅名字和下标
- 遍历另一个数组，取最小的下标和的那个或多个餐厅名字
### 代码
```java
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>(list1.length);
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        
        Integer min = null;
        List<String> ansList = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            String name = list2[i];
             Integer index = map.get(name);
             if (index != null) {
                 int diff = index + i;
                 
                 if (min == null) {
                     min = diff;
                     ansList.add(name);
                     continue;
                 }
                 
                 if (min == diff) {
                     ansList.add(name);
                     continue;
                 }
                 
                 if (min > diff) {
                     min = diff;
                     ansList.clear();
                     ansList.add(name);
                 }
             }
        }
        
        return ansList.toArray(new String[0]);
    }
}
```
## 解法二
### 思路
找到最小的数组进行hash
### 代码
```java
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        int list1Len = list1.length;
        int list2Len = list2.length;

        if (list1Len > list2Len) {
            return findRestaurant(list2, list1);
        }
        Map<String, Integer> map = new HashMap<>(list1Len);
        for (int i = 0; i < list1Len; i++) {
            map.put(list1[i], i);
        }

        Integer min = null;
        List<String> ansList = new ArrayList<>();
        for (int i = 0; i < list2Len; i++) {
            String name = list2[i];
            Integer index = map.get(name);
            if (index != null) {
                int sum = index + i;

                if (min == null) {
                    min = sum;
                    ansList.add(name);
                    continue;
                }

                if (min == sum) {
                    ansList.add(name);
                    continue;
                }

                if (min > sum) {
                    min = sum;
                    ansList.clear();
                    ansList.add(name);
                }
            }
        }

        return ansList.toArray(new String[0]);
    }
}
```
# LeetCode_160_相交链表
## 题目
编写一个程序，找到两个单链表相交的起始节点。

在节点 c1 开始相交。

示例 1：
```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```
示例 2：
```
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```
示例 3：
```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
```
注意：
```
如果两个链表没有交点，返回 null.
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
```
## 解法
### 思路
- 遍历两个链表，得到节点个数
- 遍历长的链表，根据长度差，遍历掉多出的节点
- 从同样长度的节点开始同步遍历节点，直到两个节点指针同时指向同一个节点
### 代码
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int aLen = 0,  bLen = 0;
        ListNode aNode = headA;
        while (aNode != null) {
            aLen++;
            aNode = aNode.next;
        }

        ListNode bNode = headB;
        while (bNode != null) {
            bLen++;
            bNode = bNode.next;
        }

        ListNode longList;
        ListNode shortList;
        int diff;
        if (aLen > bLen) {
            longList = headA;
            shortList = headB;
            diff = aLen - bLen;
        } else {
            longList = headB;
            shortList = headA;
            diff = bLen - aLen;
        }

        while (diff-- > 0) {
            longList = longList.next;
        }

        while (longList != shortList && longList != null && shortList != null) {
            longList = longList.next;
            shortList = shortList.next;
        }

        return longList;
    }
}
```
# LeetCode_696_计算二进制子串
## 题目
给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。

重复出现的子串要计算它们出现的次数。

示例 1 :
```
输入: "00110011"
输出: 6
解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。

请注意，一些重复出现的子串要计算它们出现的次数。

另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
```
示例 2 :
```
输入: "10101"
输出: 4
解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
```
注意：
```
s.length 在1到50,000之间。
s 只包含“0”或“1”字符。
```
## 解法
### 思路
- 当字符出现第一次从0到1的变化，记录变化后的字符出现了几次，次数代表需要记录的值
- 如果字符出现第二次变化，就清空该字符的记录，重新开始记录，并等待第三次变化，重复第一次变化时的过程
- 基于如上的过程，循环遍历整个字符串
### 代码
```java
class Solution {
    public int countBinarySubstrings(String s) {
        int count0 = 0, count1 = 0, sum = 0;
        boolean flag = true;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                if (flag) {
                    count0 = 0;
                    flag = false;
                }
                count0++;
                if (count0 <= count1) {
                    sum++;
                }
                continue;
            }

            if (c == '1') {
                if (!flag) {
                    count1 = 0;
                    flag = true;
                }
                count1++;
                if (count1 <= count0) {
                    sum++;
                }
            }
        }
        
        return sum;
    }
}
```
## 优化代码
### 思路
- 整个过程是计算出现变化前后的字符的数，计算这两个数之间的最小值
- 且在每次变化的时候都重新开始上一步的过程
- 所以可以直接通过使用变量切换着记录变化前的值，并计算变化后的值来计算子串的个数
### 代码
```java
class Solution {
    public int countBinarySubstrings(String s) {
        char[] cs = s.toCharArray();
        int cur = 1, last = 0, sum = 0;
        
        for (int i = 1; i < cs.length; i++) {
            if (cs[i] == cs[i - 1]) {
                cur++;
            } else {
                last = cur;
                cur = 1;
            }
            
            if (last >= cur) {
                sum++;
            }
        }
        
        return sum;
    }
}
```
# LeetCode_231_2的幂
## 题目
给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

示例 1:
```
输入: 1
输出: true
解释: 20 = 1
```
示例 2:
```
输入: 16
输出: true
解释: 24 = 16
```
示例 3:
```
输入: 218
输出: false
```
## 解法
### 思路
循环：
- 推出条件：n == 1
- 过程：n分别和2.0以及2相除，通过比较double和int两种基本类型的商，来看int型的是否有截取的动作，从而判断是否是2的幂
- 如果循环到最后是1，返回true
### 代码
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        }
        
        while (n != 1) {
            double num = n / 2.0;
            n /= 2;
            if (n != num) {
                return false;
            }
        }

        return true;
    }
}
```
## 解法二
### 思路
- 通过2进制的特性，使用位运算n & n-1来判断是否是2的幂，如果是2的幂乘积就一定是0，因为它们之间没有相同的位存在。
- 还要排除<=0的情况
### 代码
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```
## 解法三
### 思路
- 小于等于0的数肯定不是
- 循环判断n，如果取余2为0，就除以2，继续下一个循环
- 否则就直接返回false
- 直到n == 1，退出循环，返回true
### 代码
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        
        while (n != 1) {
            if (n % 2 == 0) {
                n  /= 2;
            } else {
                return false;
            }
        }
        
        return true;
    }
}
```
# LeetCode_342_4的幂
## 题目
给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。

示例 1:
```
输入: 16
输出: true
```
示例 2:
```
输入: 5
输出: false
```
进阶：
```
你能不使用循环或者递归来完成本题吗？
```
## 解法
### 思路
循环，使用int和double的转换，通过判断int是否被截取来断定是否是的幂
### 解法
```java
class Solution {
    public boolean isPowerOfFour(int num) {
        if (num < 1) {
            return false;
        }
        
        while (num != 1 ) {
            double doubleNum = num / 4.0;
            num /= 4;
            if (num != doubleNum) {
                return false;
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
4的幂在二进制位上表现就是，奇数位上有且只有一个数为1，可以使用0x55555555与自身进行与运算得到的结果是否为本身来检验
### 代码
```java
class Solution {
    public boolean isPowerOfFour(int num) {
        if (num < 1) {
            return false;
        }

        return (0x55555555 & num) == num && (num & (num - 1)) == 0;
    }
}
```
## 解法三
### 思路
因为是4的幂，所以被3取余必定为1。先通过判断是否是2的幂，在加上这个条件，也能得知是否是4的幂
### 代码
```java
class Solution {
    public boolean isPowerOfFour(int num) {
        if (num < 1) {
            return false;
        }

        return (num & (num - 1)) == 0 && num % 3 == 1;
    }
}
```
# LeetCode_628_三个数的最大乘积
## 题目
给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。

示例 1:
```
输入: [1,2,3]
输出: 6
```
示例 2:
```
输入: [1,2,3,4]
输出: 24
```
注意:
```
给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
```
## 解法
### 思路
- 使用一个大顶堆和一个小顶堆
- 遍历数组，分别放入大小顶堆
- 遍历结束，分别从两个堆中拿出3个元素
- 如果都是负数或都是正数，就取大顶堆的三个元素的成绩
- 如果正数只有一个，取最小的两个值及正数的成绩
- 如果正数只有两个，取最大的三个数
- 如果负数只有一个，取最大的三个数
- 如果负数只有两个，比较最小的2个数与最大的数与最大的三个数的乘积的大小
### 代码
```java
class Solution {
    public int maximumProduct(int[] nums) {
        Queue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> minQ = new PriorityQueue<>();

        for (int num : nums) {
            maxQ.offer(num);
            minQ.offer(num);
        }

        int[] arr = new int[6];
        int count = 3, index = 0, negative = 0, ans = 0;
        while (count-- > 0) {
            int max = maxQ.poll();
            if (max < 0) {
                negative++;
            }
            arr[index] = max;

            int min = minQ.poll();
            if (min < 0) {
                negative++;
            }
            arr[index + 3] = min;
            index++;
        }
        
        if (negative == 5) {
            return arr[3] * arr[4] * arr[0];
        }
        
        return Math.max(arr[0] * arr[1] * arr[2], arr[0] * arr[3] * arr[4]);
    }
}
```
## 优化
### 思路
- 根据解法一的逻辑，最终需要的是5个变量，最大的三个数和最小的两个数
- 而且不需要判断是否有负数，返回最大3个数和最大1个数与最小两个数的乘积的最大值
### 代码
```java
class Solution {
    public int maximumProduct(int[] nums) {
        int max1 = -1001, max2 = -1001, max3 = -1001, min1 = 1001, min2 = 1001;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}
```
# LeetCode_543_二叉树的直径
## 题目
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。

示例 :
```
给定二叉树

          1
         / \
        2   3
       / \     
      4   5    
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
```
注意 :
```
两结点之间的路径长度是以它们之间边的数目表示。
```
## 解法
### 思路
直径：所有节点的左右子树之和中的最大值
- 类变量max
- 退出条件：node == null返回0
- 过程：
    - 递归到左右子树，获得左右子树的深度
    - 取左右子树深度之和与max的最大值为max
- 返回：左右子树的最大值+1
### 代码
```java
class Solution {
    private int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int left = dfs(node.left);
        int right = dfs(node.right);
        
        max = Math.max(left + right, max);
        return Math.max(left, right) + 1;
    }
}
```
# LeetCode_190_颠倒二进制位
## 题目
颠倒给定的 32 位无符号整数的二进制位。

示例 1：
```
输入: 00000010100101000001111010011100
输出: 00111001011110000010100101000000
解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
```
示例 2：
```
输入：11111111111111111111111111111101
输出：10111111111111111111111111111111
解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
      因此返回 3221225471 其二进制表示形式为 10101111110010110010011101101001。
```
提示：
```
请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
```
进阶:
+```
如果多次调用这个函数，你将如何优化你的算法？
```
## 解法
### 思路
使用1 & n >> i的方式(使用算数右移位)，在遍历的过程中一个个的获得二进制位上的值，然后再<<（31 - i)将该位的值放到对应颠倒的位置上，然后累加到ans上，遍历结束后，返回ans
### 代码
```java
public class Solution {
    public int reverseBits(int n) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            ans += ((1 & (n >> i)) << (31 - i));
        }

        return ans;
    }
}
```
# LeetCode_504_七进制数
## 题目
给定一个整数，将其转化为7进制，并以字符串形式输出。

示例 1:
```
输入: 100
输出: "202"
```
示例 2:
```
输入: -7
输出: "-10"
注意: 输入范围是 [-1e7, 1e7] 。
```
## 解法
### 思路
- 如果num == 0直接返回0
- 使用StringBuilder
- 通过循环，取余获得当前位的值，除以7获得下一位的被除数，如果被除数为零返回结果
### 代码
```java
class Solution {
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        boolean isP = num >= 0;
        num = isP ? num : Math.abs(num);

        while (num != 0) {
            int p = num % 7;
            sb.insert(0, p);

            num /= 7;
        }
        
        if (!isP) {
            sb.insert(0, "-");
        }
        return sb.toString();
    }
}
```
## 解法二
### 思路
直接使用Integer的toString
### 代码
```java
class Solution {
    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }
}
```
# LeetCode_205_同构字符串
## 题目
给定两个字符串 s 和 t，判断它们是否是同构的。

如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。

所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

示例 1:
```
输入: s = "egg", t = "add"
输出: true
```
示例 2:
```
输入: s = "foo", t = "bar"
输出: false
```
示例 3:
```
输入: s = "paper", t = "title"
输出: true
说明:
你可以假设 s 和 t 具有相同的长度。
```
## 解法
### 思路
两边的字符相对应，如果有一组对应不是1对1，就返回false
### 代码
```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            } else {
                if (map.containsValue(t.charAt(i))) {
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
- 使用两个数组来代替解法一种的map，因为题目中的测试案例用的都是英文字母，所以数组长度就是128
- 两个数组分别用下标代替一个的字符，元素代表另一个的字符
- 循环的时候判断两个字符数组的对应字符是否是一致的
    - 如果字符下标对应的元素是遍历到的元素，说明之前有设置过，且是正确的，就继续遍历
    - 如果下标对应的是0，说明还没有设置过，看下另一个字符数组是否也是没有设置过的，是就设置然后继续遍历
    - 否则说明之前设置过，且不是对应的元素，就返回false
    - 其他情况也说明是不匹配的，返回false
- 如果遍历完整个数组没有返回，说明字符串符合要求，返回true    
### 代码
```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] sMap = new int[128];
        int[] tMap = new int[128];

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);

            if (sMap[sc] == tc) {
                continue;
            }
            
            if (sMap[sc] == 0) {
                if (tMap[tc] != 0) {
                    return false;
                }
                
                sMap[sc] = tc;
                tMap[tc] = sc;
                
                continue;
            }
            return false;
        }      
        return true;
    }
}
```
# LeetCode_994_腐烂的橘子
## 题目
在给定的网格中，每个单元格可以有以下三个值之一：
```
值 0 代表空单元格；
值 1 代表新鲜橘子；
值 2 代表腐烂的橘子。
每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
```
返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。

示例 1：
```
输入：[[2,1,1],[1,1,0],[0,1,1]]
输出：4
```
示例 2：
```
输入：[[2,1,1],[0,1,1],[1,0,1]]
输出：-1
解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
```
示例 3：
```
输入：[[0,2]]
输出：0
解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
```
提示：
```
1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] 仅为 0、1 或 2
```
## 解法
### 思路
bfs遍历
- 遍历找到烂橘子，使用num = r * x + c的方式将二维坐标放入队列(x是r和c中的最大值)，之后就可以通过取余，和相除的方式获得这个坐标
- 开始遍历，将坐标取出，然后通过边界和是否是好橘子作为判断，将好橘子变成烂橘子，并再次把变烂的橘子坐标放入队列中，并暂存遍历深度
- 遍历结束，再遍历二维数组，如果找到1，就返回-1，否则就返回暂存的bfs深度作为次数
### 代码
```java
class Solution {
    int[] dr = new int[]{-1, 1, 0, 0};
    int[] dc = new int[]{0, 0, -1, 1};
    public int orangesRotting(int[][] grid) {
        int ans = 0, gr = grid.length, gc = grid[0].length, x = Math.max(gr, gc);
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < gr; i++) {
            for (int j = 0; j < gc; j++) {
                if (grid[i][j] == 2) {
                    int num = x * i + j;
                    queue.offer(num);
                    map.put(num, 0);
                }
            }
        }

        while (!queue.isEmpty()) {
            int num = queue.poll();
            int r = num / x;
            int c = num % x;

            for (int i = 0; i < 4; i++) {
                int tmpR = r + dr[i];
                int tmpC = c + dc[i];
                if (tmpR >= 0 && tmpR < gr && tmpC >= 0 && tmpC < gc && grid[tmpR][tmpC] == 1) {
                    int tmpNum = tmpR * x + tmpC;
                    grid[tmpR][tmpC] = 2;
                    queue.offer(tmpR * x + tmpC);
                    map.put(tmpNum, map.get(num) + 1);
                    ans = map.get(tmpNum);
                }
            }
        }

        for (int[] arr : grid) {
            for (int num : arr) {
                if (num == 1) {
                    return -1;
                }
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
- 初始化变量i = 2，因为腐烂的橘子是2，而这里把被腐烂的橘子感染的橘子标记为i + 1,通过这个i来判断被感染了几次，而结果要么是-1，要么是i - 3，-3的原因和for循环的过程有关系，如果出现了一次感染，那么一定是4，所以3的话说明出现感染的过程
- 遍历数组，找到为2的元素，然后把上下左右的元素变成i + 1，也就是3，然后进入下一次循环，同时i也变成3，这样就去找上次感染的橘子，找它们的上下左右
- 还需要有一个标识符来判断是否出现了感染，如果出现了感染，那么就继续下一个循环，如果没有就退出，进行二维数组遍历，判断是否有1
### 代码
```java
class Solution {
    public int orangesRotting(int[][] grid) {
        boolean flag = true;
        int bad = 2, gr = grid.length, gc = grid[0].length;

        for (; flag; bad++) {
            flag = false;
            for (int i = 0; i < gr; i++) {
                for (int j = 0; j < gc; j++) {
                    if (grid[i][j] == bad) {
                        if (i + 1 < gr && grid[i + 1][j] == 1) {
                            grid[i + 1][j] = bad + 1;
                            flag = true;
                        }

                        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                            grid[i - 1][j] = bad + 1;
                            flag = true;
                        }

                        if (j + 1 < gc && grid[i][j + 1] == 1) {
                            grid[i][j + 1] = bad + 1;
                            flag = true;
                        }

                        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                            grid[i][j - 1] = bad + 1;
                            flag = true;
                        }
                    }
                }
            }
        }

        for (int[] ints : grid) {
            for (int j = 0; j < gc; j++) {
                if (ints[j] == 1) {
                    return -1;
                }
            }
        }
        
        return bad - 3;
    }
}
```
# LeetCode_1071_字符串的最大公因子
## 题目
对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。

返回字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。

示例 1：
```
输入：str1 = "ABCABC", str2 = "ABC"
输出："ABC"
```
示例 2：
```
输入：str1 = "ABABAB", str2 = "ABAB"
输出："AB"
```
示例 3：
```
输入：str1 = "LEET", str2 = "CODE"
输出：""
```
提示：
```
1 <= str1.length <= 1000
1 <= str2.length <= 1000
str1[i] 和 str2[i] 为大写英文字母
```
## 解法
### 思路
辗转相除法找到最大公约数
- 如果长字符串中没有短字符串，说明不符合要求，直接返回“”
- 让长字符串中的短字符串部分替换成空字符串
- 然后替换后的作为短字符串，短字符串作为长字符串，反复的执行，直到短字符串是空字符串的时候，说明已经找到了，就是最新的长字符串
### 代码
```java
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        boolean longer = str1.length() > str2.length();
        String longS = longer ? str1 : str2;
        String shortS = longer ? str2 : str1;
        
        while (!shortS.equals("")) {
            if (!longS.contains(shortS)) {
                return "";
            }
            
            String tmp = longS.replace(shortS, "");
            longS = shortS;
            shortS = tmp;
        }
        
        return longS;
    }
}
```
## 解法二
### 思路
- 如果是字符串中的最大公因数，那么长度也应该是同样的最大公因数
- 先找到长度的最大公因数
- 再通过遍历这个长度的字符串是否能完全清除两个字符串来找到最大公因数字符串
### 代码
```java
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        int len = gcd(Math.max(str1.length(), str2.length()), Math.min(str1.length(), str2.length()));
        String ans = str1.substring(0, len);
        if (!"".equals(str1.replaceAll(ans, "")) || !"".equals(str2.replaceAll(ans, ""))) {
            return "";
        }
        return ans;
    }
    
    private int gcd(int a, int b) {
        int mod = a % b;
        return mod == 0 ? b : gcd(b, mod);
    }
}
```
## 代码优化
### 思路
使用字符数组替代String Api的replaceAll
### 代码
```java
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        int len = gcd(Math.max(str1.length(), str2.length()), Math.min(str1.length(), str2.length()));
        String ans = str1.substring(0, len);
        for (int i = 0; i < str1.length(); i += len) {
            if (!ans.equals(str1.substring(i, i + len))) {
                return "";
            }
        }

        for (int i = 0; i < str2.length(); i += len) {
            if (!ans.equals(str2.substring(i, i + len))) {
                return "";
            }
        }
        
        return ans;
    }
    
    private int gcd(int a, int b) {
        int mod = a % b;
        return mod == 0 ? b : gcd(b, mod);
    }
}
```
# LeetCode_88_合并两个有序数组
## 题目
给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

说明:
```
初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
```
示例:
```
输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]
```
## 解法
### 思路
使用JDK提供的API
- 把数组合并起来
- 排序
### 代码
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
}
```
## 解法二
### 思路
使用双指针，遍历两个数组，选择两个指针中元素最小值放在游标所对应的位置上
### 代码
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0, index = 0;
        int[] nums1Copy = Arrays.copyOf(nums1, nums1.length);
        while (index < n + m) {
            if (p1 < m && p2 < n) {
                if (nums1Copy[p1] < nums2[p2]) {
                    nums1[index++] = nums1Copy[p1++];
                } else {
                    nums1[index++] = nums2[p2++];
                }
                continue;
            }
            
            if (p1 >= m) {
                System.arraycopy(nums2, p2, nums1, index, n - p2);
                index = n + m;
                continue;
            }
            
            System.arraycopy(nums1Copy, p1, nums1, index, m - p1);
            index = n + m;
        }
    }
}
```
# LeetCode_326_3的幂
## 题目
给定一个整数，写一个函数来判断它是否是 3 的幂次方。

示例 1:
```
输入: 27
输出: true
```
示例 2:
```
输入: 0
输出: false
```
示例 3:
```
输入: 9
输出: true
```
示例 4:
```
输入: 45
输出: false
```
进阶：
```
你能不使用循环或者递归来完成本题吗？
```
## 解法
### 思路
使用int的截取特性，循环比较与3相除的double值和int值是否相等来判断是否是3的幂，和231题2的幂，342题4的幂解法类似
### 代码
```java
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        
        while (n != 1) {
            double dNum = n / 3.0;
            n /= 3;
            if (dNum != n) {
                return false;
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
可以直接通过是否取余为0来判断是否是3的幂
### 代码
```java
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }

        while (n != 1) {
            if (n % 3 == 0) {
                n /= 3;
            } else {
                return false;
            }
        }

        return true;
    }
}
```
## 解法三
### 思路
计算得到int基本类型中3的幂的最大值是3的19次方1162261467，所以只要1162261467取余n==0且n>0就是该题范围内3的幂
### 代码
```java
class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
```
## 解法四
### 思路
使用数学公式，利用对数来求出3的幂，且注意浮点数的不精确问题
### 代码
```java
class Solution {
    public boolean isPowerOfThree(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }
}
```
# LeetCode_671_二叉树的第二小节点
## 题目
给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。 

给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

示例 1:
```
输入: 
    2
   / \
  2   5
     / \
    5   7

输出: 5
说明: 最小的值是 2 ，第二小的值是 5 。
```
示例 2:
```
输入: 
    2
   / \
  2   2

输出: -1
说明: 最小的值是 2, 但是不存在第二小的值。
```
## 解法
### 思路
dfs遍历
### 代码
```java
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        Integer[] arr = new Integer[]{root.val, null};
        dfs(root, arr);
        if (arr[0].equals(arr[1]) || arr[1] == null) {
            return -1;
        }
        return arr[1];
    }

    private void dfs(TreeNode node, Integer[] arr) {
        if (node == null) {
            return;
        }

        int val = node.val;
        if (val < arr[0]) {
            arr[1] = arr[0];
            arr[0] = val;
        } else if (val != arr[0] && (arr[1] == null || val < arr[1])) {
            arr[1] =val;
        }

        dfs(node.left, arr);
        dfs(node.right, arr);
    }
}
```
## 解法二
### 思路
- 因为根节点的值不大于子节点的值，所以最简单的情况就是求根节点的左右子树中的最小值
- 但因为左右子树的可以和根节点一样大，所以当子树节点和根节点一样大的时候就需要向下遍历
- 然后再求它们的左右子树，如果没有子树了，说明这个方向上没有第二小的子树
- 如果右子树节点，而且没有和根节点一样的了，就直接返回它们中的最小值
### 代码
```java

```