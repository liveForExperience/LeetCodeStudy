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