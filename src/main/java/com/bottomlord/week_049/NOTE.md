# LeetCode_990_等式方程的可满足性
## 题目
给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。

只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 

示例 1：
```
输入：["a==b","b!=a"]
输出：false
解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
```
示例 2：
```
输出：["b==a","a==b"]
输入：true
解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
```
示例 3：
```
输入：["a==b","b==c","a==c"]
输出：true
```
示例 4：
```
输入：["a==b","b!=c","c==a"]
输出：false
```
示例 5：
```
输入：["c==c","b==d","x!=z"]
输出：true
```
提示：
```
1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] 和 equations[i][3] 是小写字母
equations[i][1] 要么是 '='，要么是 '!'
equations[i][2] 是 '='
```
## 解法
### 思路
并查集：
- 根据表达式初始化并查集
- 遍历表达式数组，当代表不等的字符串中的两个字母，在并查集中的结果相等时，返回false
- 否则返回true
### 代码
```java
class Solution {
    public boolean equationsPossible(String[] equations) {
        Dsu dsu = new Dsu(26);
        
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                int x = equation.charAt(0) - 'a';
                int y = equation.charAt(3) - 'a';
                
                dsu.union(x, y);
            }
        }
        
        for (String equation : equations) {
            if (equation.charAt(1) != '=') {
                int x = equation.charAt(0) - 'a';
                int y = equation.charAt(3) - 'a';
                
                if (dsu.find(x) == dsu.find(y)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private class Dsu {
        private int[] parent;
        
        public Dsu(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) { 
                parent[x] = find(parent[x]);
            }
            
            return parent[x];
        }
        
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    } 
}
```
# LeetCode_29_两数相除
## 题目
给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2

示例 1:
```
输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
```
示例 2:
```
输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2
```
提示：
```
被除数和除数均为 32 位有符号整数。
除数不为 0。
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
```
## 失败解法
### 原因
超时
### 思路
- 特例：
    - 被除数为0，结果为0
    - 被除数为int最小值，除数为-1，结果为int最大值
- 循环累减被除数，累加结果值
- 返回结果
### 代码
```java
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean positive = false;
        if ((dividend > 0 && divisor > 0) ||
            (dividend < 0 && divisor < 0)) {
            positive = true;
        }
        
        int result = 0;
        
        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);
        
        while (dividend <= divisor) {
            dividend -= divisor;
            result++;
        }
        
        return positive ? result : -result;
    }
}
```
## 解法
### 思路
在失败解法的基础上，最大化每次减去的值，从而降低时间复杂度
- 每次都减去divisor的2的整数次幂
### 代码
```java
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int result = 0;
        boolean positive = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0;

        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);

        while (dividend <= divisor) {
            int num = divisor;
            int count = 1;
            while (dividend - num <= num) {
                num <<= 1;
                count <<= 1;
            }

            dividend -= num;
            result += count;
        }
        
        return positive ? result : -result;
    }
}
```
# LeetCode_30_串联所有单词的子串
## 题目
给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。

注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。

示例 1：
```
输入：
  s = "barfoothefoobarman",
  words = ["foo","bar"]
输出：[0,9]
解释：
从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
输出的顺序不重要, [9,0] 也是有效答案。
```
示例 2：
```
输入：
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
输出：[]
```
## 解法
### 思路
hashmap：
- 遍历words，将字符串放入map中计数映射
    - key：word字符串
    - value：出现的次数
- 遍历字符串s：
    - 判断当前子串中出现单词是否在map中存在
    - 如果不存在直接跳过，开始判断下一个单词
    - 如果存在，将该单词放入新的map中进行计数，同时判断出现次数是否一致，如果超过就终止，说明不是
    - 遍历子串结束后，判断出现次数是否一致，如果一致就记录起始坐标，否则就跳过
### 代码
```java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return ans;
        }

        int wordLen = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            Map<String, Integer> tmp = new HashMap<>();
            int num = 0;
            while (num < wordNum) {
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                
                if (!map.containsKey(word)) {
                    break;
                }
                
                tmp.put(word, tmp.getOrDefault(word, 0) + 1);
                if (tmp.get(word) > map.get(word)) {
                    break;
                }
                
                num++;
            }
            
            if (num == wordNum) {
                ans.add(i);
            }
        }
        
        return ans;
    }
}
```