# [LeetCode_482_秘钥格式化](https://leetcode-cn.com/problems/license-key-formatting/)
## 解法
### 思路
- 去除原有字符串的`-`
- 计算字母个数
- 算出分组的个数
  - 如果能够被k整除，就是len / k个区间
  - 如果不能被k整除，就是len / k + 1个区间，第一个区间是len % k个字母，剩下的都是k个
### 代码
```java
class Solution {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        s = s.toUpperCase();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c != '-') {
                sb.append(c);
            }
        }
        cs = sb.toString().toCharArray();

        int n = sb.length();
        boolean flag = n % k == 0;
        
        int block = flag ? n / k : n / k + 1, first = flag ? k : n % k;

        StringBuilder ans = new StringBuilder();
        int index = 0;
        for (int i = 0; i < block; i++) {
            int loop = i == 0 ? first : k;
            for (int i1 = 0; i1 < loop; i1++) {
                ans.append(cs[index++]);
            }

            if (i != block - 1) {
                ans.append("-");
            }
        }
        
        return ans.toString();
    }
}
```
# [LeetCode_1758_生成交替二进制字符串的最少操作数](https://leetcode-cn.com/problems/minimum-changes-to-make-alternating-binary-string/)
## 解法
### 思路
- 分别以0和1为起始，遍历字符串，并计算需要转换的次数，最后取最小值
### 代码
```java
class Solution {
    public int minOperations(String s) {
        char[] cs = s.toCharArray();
        boolean zero = true, one = true;
        int a = 0, b = 0;
        for (char c : cs) {
            if (c == '0') {
                a += zero ? 0 : 1;
                b += one ? 1 : 0;
            } else {
                a += zero ? 1 : 0;
                b += one ? 0 :  1;
            }
            
            zero = !zero;
            one = !one;
        }
        
        return Math.min(a, b);
    }
}
```
# [LeetCode_1763_最长的美好子字符串](https://leetcode-cn.com/problems/longest-nice-substring/)
## 解法
### 思路
嵌套遍历
- 外层确定可能结果的起始坐标
- 内层遍历确定字符串的结尾坐标
- 2层循环确定的字符串，判断其是否是符合题目要求的，然后更新最大长度
- 循环次数基于更新的最大长度动态变化，从而减少循环次数
### 代码
```java
class Solution {
  public String longestNiceSubstring(String s) {
    int n = s.length(), max = 1;
    String ans = "";
    for (int i = 0; i < n - max; i++) {
      for (int j = i + max; j < n; j++) {
        if (isNice(s.substring(i, j + 1))) {
          if (j + 1 - i > max) {
            max = j + 1 - i;
            ans = s.substring(i, j + 1);
          }
        }
      }
    }

    return ans;
  }

  private boolean isNice(String s) {
    Set<String> set = new HashSet<>();
    for (int i = 0; i < s.length(); i++) {
      set.add("" + s.charAt(i));
    }

    for (int i = 0; i < s.length(); i++) {
      if (!set.contains(("" + s.charAt(i)).toUpperCase()) ||
              !set.contains(("" + s.charAt(i)).toLowerCase())) {
        return false;
      }
    }

    return true;
  }
}
```
# [LeetCode_284_顶端迭代器](https://leetcode-cn.com/problems/peeking-iterator/)
## 解法
### 思路
- 使用一个peek来暂存顶部元素
- 在hasNext和next函数时，先判断peek是否不为空，如果不为空
  - hasNext将peek置为null，然后返回true
  - next将peek置为null，并返回之前peek的值
### 代码
```java
class PeekingIterator implements Iterator<Integer> {
    private Integer peek;
    private Iterator<Integer> iterator;
    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    public Integer peek() {
        if (!hasNext()) {
            return null;
        }
        
        if (peek == null) {
            peek = next();
            return peek;
        }
        
        return peek;
    }

    @Override
    public Integer next() {
        if (peek != null) {
            Integer ans = peek;
            peek = null;
            return ans;
        }

        if (!hasNext()) {
            return null;
        }

        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        if (peek != null) {
            return true;
        }
        return iterator.hasNext();
    }
}
```
# [LeetCode_414_第三大的数](https://leetcode-cn.com/problems/third-maximum-number/)
## 解法
### 思路
使用3个变量记录和更新，使用set集合辅助，将遍历过得数字过滤掉
### 代码
```java
class Solution {
    public int thirdMax(int[] nums) {
        int a = nums[0];
        Integer b = null, c = null;
        Set<Integer> set = new HashSet<>();
        set.add(a);
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (!set.add(num)) {
                continue;
            }
            
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (b == null) {
                b = num;
            } else if (num > b) {
                c = b;
                b = num;
            } else if (c == null) {
                c = num;
            } else if (num > c) {
                c = num;
            }
        }

        return c == null ? a : c;
    }
}
```
## 解法二
### 思路
- 使用int最小值代替null做循环过程中的更新和判断，从而减小处理null值时候拆包空指针引起的复杂问题，也就不需要引入set过滤重复数据。
- 因为测试用来中会使用int最小值，所以在int最小值的判断上要做特殊处理
  - 如果测试用例中存在最小值，则用一个布尔值做标记
  - 在最后返回值的时候，根据是否有遇到int最小值来判断
### 代码
```java
class Solution {
    public int thirdMax(int[] nums) {
        int a = nums[0], b = Integer.MIN_VALUE, c = b;
        boolean meet = a == Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num == Integer.MIN_VALUE) {
                meet = true;
            }
            
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (a > num && num > b) {
                c = b;
                b = num;
            } else if (b > num && num > c) {
                c = num;
            }
        }

        if (meet) {
            if (b == c && c == Integer.MIN_VALUE) {
                return a;
            } else {
                return c;
            }
        } else {
            return c == Integer.MIN_VALUE ? a : c;
        }
    }
}
```
# [LeetCode_1768_交替合并字符串](https://leetcode-cn.com/problems/merge-strings-alternately/)
## 解法
### 思路
- 循环交替追加字母
- 循环退出条件为坐标超过了2个字符串
- 在循环过程中判断是否有超过某个单词的情况，有的话就不处理这个字符串
### 代码
```java
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < word1.length() || index < word2.length()) {
            if (index < word1.length()) {
                sb.append(word1.charAt(index));
            }
            
            if (index < word2.length()) {
                sb.append(word2.charAt(index));
            }
            
            index++;
        }
        
        return sb.toString();
    }
}
```
## 解法二
### 思路
使用字符数组替换StringBuilder进行累加处理
### 代码
```java
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        char[] cs = new char[len1 + len2];
        int i = 0, c = 0;
        
        while (i < len1 || i < len2) {
            if (i < len1) {
                cs[c++] = word1.charAt(i);
            }
            
            if (i < len2) {
                cs[c++] = word2.charAt(i);
            }
            
            i++;
        }
        
        return new String(cs);
    }
}
```
# [LeetCode_1773_统计匹配检索规则的物品数量](https://leetcode-cn.com/problems/count-items-matching-a-rule/)
## 解法
### 思路
循环items并逐个判断key和value并计数
### 代码
```java
class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0;
        for (List<String> item : items) {
            if (Objects.equals(ruleKey, "type")) {
                count += Objects.equals(ruleValue, item.get(0)) ? 1 : 0;
            } else if (Objects.equals(ruleKey, "color")) {
                count += Objects.equals(ruleValue, item.get(1)) ? 1 : 0;
            } else if (Objects.equals(ruleKey, "name")) {
                count += Objects.equals(ruleValue, item.get(2)) ? 1 : 0;
            }
        }
        
        return count;
    }
}
```
## 解法二
### 思路
- 提前通过typeKey获取到item对应元素的坐标，从而使得后续循环时候能够减少if判断，充分利用cpu的分支预测优化
### 代码
```java
class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int index;
        if (Objects.equals(ruleKey, "type")) {
            index = 0;
        } else if (Objects.equals(ruleKey, "color")) {
            index = 1;
        } else {
            index = 2;
        }
        
        int count = 0;
        for (List<String> item : items) {
            if (Objects.equals(ruleValue, item.get(index))) {
                count++;
            }
        }
        
        return count;
    }
}
```
# [LeetCode_434_字符串中的单词数](https://leetcode-cn.com/problems/number-of-segments-in-a-string/)
## 解法
### 思路
双层循环，外层确定可能的单词起始，内层继续移动坐标，通过坐标是否与起始有差异，来计数，遍历结束就返回计数值
### 代码
```java
class Solution {
    public int countSegments(String s) {
        int n = s.length(), count = 0;
        char[] cs = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int start = i;
            while (i < n && cs[i] != ' ') {
                i++;
            }
            
            if (i != start) {
                count++;
            }
        }
        
        return count;
    }
}
```
# [LeetCode_1779_找到最近的有相同X或Y坐标的点](https://leetcode-cn.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/)
## 解法
### 思路
- 初始化一个数组，初始值为-1，在指定坐标上存储当前坐标有效坐标算出来的曼哈顿距离
- 遍历数组，找到有效坐标，算出距离，更新最小值
- 遍历list，找到第一个和最小值一样的坐标并返回，没有的话就返回-1
### 代码
```java
class Solution {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int min = Integer.MAX_VALUE, n = points.length;
        int[] arr = new int[n];
        Arrays.fill(arr, -1);
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            if (point[0] == x || point[1] == y) {
                arr[i] = Math.abs(x - point[0]) + Math.abs(y - point[1]);
                min = Math.min(min, arr[i]);
            }
        }
        
        if (min == Integer.MIN_VALUE) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == min) {
                return i;
            }
        }
        
        return -1;
    }
}
```
# [LeetCode_1784_检查二进制字符串字段](https://leetcode-cn.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/)
## 解法
### 思路
题目的意思是所有1必须连在一起，循环判断并计数即可
### 代码
```java
class Solution {
    public boolean checkOnesSegment(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            }
            
            while (i < s.length() && s.charAt(i) == '1') {
                i++;
            }
        }
        
        return count == 1;
    }
}
```
# [LeetCode_187_重复的DNA序列](https://leetcode-cn.com/problems/repeated-dna-sequences/)
## 解法
### 思路
- 遍历字符串，用map存储长度为10的子串，并计数
- 最后返回长度大于1的子串即可
### 代码
```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        Map<String, Integer> mapping = new HashMap<>();
        for (int i = 0; i <= n - 10; i++) {
            String sub = s.substring(i, i + 10);
            mapping.put(sub, mapping.getOrDefault(sub, 0) + 1);
            
            if (mapping.get(sub) == 2) {
                ans.add(sub);
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_352_将数据流变为多个不相交的区间](https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/)
## 解法
### 思路
- 使用TreeSet存储数据，使其在存储是就具有顺序性
- 获取区间的时候，遍历TreeSet，根据当前元素在set中是否存在前后元素来判断是否生成数组元素，且是否为元素的某一个值
  - 当前元素前后没有相邻元素，生成数组元素，头尾都是当前元素
  - 当前元素前后都有相邻元素，不生成数据元素
  - 当前元素之前没有元素，之后有元素，生成数组元素，且当前元素为该数组元素的第一个元素
  - 当前元素之前有元素，但之后没有元素，当前元素为数组元素的第二个元素
- 遍历结束后返回结果
### 代码
```java
class SummaryRanges {
    private TreeSet<Integer> set;
    public SummaryRanges() {
        this.set = new TreeSet<>();
    }

    public void addNum(int val) {
        this.set.add(val);
    }

    public int[][] getIntervals() {
        Iterator<Integer> iterator = set.iterator();
        List<int[]> list = new ArrayList<>();
        int[] arr = new int[2];
        while (iterator.hasNext()) {
            int num = iterator.next();
            
            if (!set.contains(num - 1) && !set.contains(num + 1)) {
                list.add(new int[]{num, num});
                continue;
            }
            
            if (set.contains(num - 1) && set.contains(num + 1)) {
                continue;
            }
            
            if (!set.contains(num - 1) && set.contains(num + 1)) {
                arr[0] = num;
                continue;
            }
            
            if (set.contains(num - 1) && !set.contains(num + 1)) {
                arr[1] = num;
                list.add(arr);
                arr = new int[2];
            }
        }

        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i][0] = list.get(i)[0];
            ans[i][1] = list.get(i)[1];
        }
        return ans;
    }
}
```
## 解法二
### 思路
- 解法一是在get的时候对区间进行整理和生成
- 也可以在add的时候对区间数组做维护
- 维护的时候，根据add的val在原有数组中做二分查找，找到区间起始元素与val最接近的数组cur，然后根据不同的情况来处理原有的区间列表
  - 如果`cur[0] > val`（只有在add的元素是当前数组中最小的时候，会出现这种情况，所以不需要去考虑cur之前的数组，因为没有）
    - `cur[0] - 1 == val`，那么和cur数组做整合，val作为cur的起始元素
    - 否则就在cur之前插入一个新的数组元素
  - 如果`cur[0] <= val && cur[1] >= val`，不做处理
  - 剩下的情况就是val在cur数组的右边，这个时候就需要配合下一个数组进行配合，但此时还需要做一个判断，就是如果cur是最后一个数组，那么就不能获取下一个数组，否则越界
    - 如果是最后一个数组
      - `cur[1] + 1 == val`，与cur进行整合，val作为结尾元素
      - 否则，直接在列表后追加数组元素
    - 如果不是最后一个数组
      - 先获取下一个数组next
      - 判断是否能将cur和next连接，如果可以，就将两个数组整合，并删去其中一个
      - 然后在判断是否只能和其中一个整合
      - 最后就只能单独新增一个数组
- get的时候，就在现有的list的基础上进行转换并返回
### 代码
```java
class SummaryRanges {
    private List<int[]> list;

    public SummaryRanges() {
        this.list = new ArrayList<>();
    }

    public void addNum(int val) {
        if (list.size() == 0) {
            list.add(new int[]{val, val});
            return;
        }

        int l = 0, r = list.size() - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (val >= list.get(mid)[0]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        int[] cur = list.get(r);

        if (val >= cur[0] && val <= cur[1]) {
            return;
        }

        if (val < cur[0]) {
            if (val + 1 == cur[0]) {
                cur[0] = val;
            } else {
                list.add(r, new int[]{val, val});
            }

            return;
        }

        if (r == list.size() - 1) {
            if (cur[1] + 1 == val) {
                cur[1] = val;
            } else {
                list.add(new int[]{val, val});
            }
            return;
        }
        
        int[] next = list.get(r + 1);
        if (cur[1] + 1 == val && val == next[0] - 1) {
            cur[1] = next[1];
            list.remove(r + 1);
        } else if (cur[1] + 1 == val) {
            cur[1] = val;
        } else if (next[0] - 1 == val) {
            next[0] = val;
        } else {
            list.add(r + 1, new int[]{val, val});
        }
        
    }

    public int[][] getIntervals() {
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
```
# [LeetCode_441_排列硬币](https://leetcode-cn.com/problems/arranging-coins/)
## 解法
### 思路
模拟循环，注意int越界，记录值要用long
### 代码
```java
class Solution {
    public int arrangeCoins(int n) {
        long index = 1;
        int count = 1;
        while (index <= n) {
            index += ++count;
        }
        return count - 1;
    }
}
```
## 解法二
### 思路
二分查找
### 代码
```java
class Solution {
    public int arrangeCoins(int n) {
        long l = 1, r = n;
        while (l <= r) {
            long mid = l + (r - l) / 2,
                cost = (1 + mid) * mid >> 1;

            if (cost == n) {
                return (int) mid;
            } else if (cost < n) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        return (int) r;
    }
}
```