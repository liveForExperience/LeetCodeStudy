# [LeetCode_476_数字的补数](https://leetcode-cn.com/problems/number-complement/)
## 解法
### 思路
- 求出num中为1的最高位
- 然后左移一位-1，获得掩码
- 用掩码和原数字进行抑或求出补数
### 代码
```java
class Solution {
    public int findComplement(int num) {
        int highBit = 0;
        for (int i = 1; i <= 30; i++) {
            if (num >= 1 << i) {
                highBit = i;
            } else {
                break;
            }
        }

        int mask = highBit == 30 ? 0x7fffffff : (1 << (highBit + 1)) - 1;
        return mask ^ num;
    }
}
```
# [LeetCode_211_添加与搜索单词-数据结构设计](https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/)
## 解法
### 思路
字典树
### 代码
```java
class WordDictionary {
    private DictNode root;
    public WordDictionary() {
        this.root = new DictNode();
    }

    public void addWord(String word) {
        char[] cs = word.toCharArray();
        DictNode node = root;
        for (char c : cs) {
            DictNode child = node.children[c - 'a'];
            if (child == null) {
                child = new DictNode(c);
                node.children[c - 'a'] = child;
            }
            node = child;
        }

        node.isLeaf = true;
    }

    public boolean search(String word) {
        char c = word.charAt(0);
        if (c == '.') {
            for (DictNode child : root.children) {
                boolean result = doSearch(child, word, 1);
                if (result) {
                    return true;
                }
            }
            return false;
        }
        
        return doSearch(root.children[word.charAt(0) - 'a'], word, 1);
    }

    private boolean doSearch(DictNode node, String word, int index) {
        if (node == null) {
            return false;
        }
        
        if (index == word.length()) {
                return node.isLeaf;
        }
        
        if (word.charAt(index) == '.') {
            DictNode[] children = node.children;
            for (DictNode child : children) {
                boolean result = doSearch(child, word, index + 1);
                if (result) {
                    return true;
                }
            }
            
            return false;
        }
        
        return doSearch(node.children[word.charAt(index) - 'a'], word, index + 1);
    }

    private  class DictNode {
        private char c;
        private boolean isLeaf;
        private DictNode[] children;

        public DictNode() {
            this.children = new DictNode[26];
        }

        public DictNode(char c) {
            this.c = c;
            this.children = new DictNode[26];
        }
    }
}
```
# [LeetCode_453_最小操作次数使数组元素相等](https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/)
## 解法
### 思路
- 题目可以理解成使n-1个元素加1，反过来也可以理解成使1个元素-1
- 如果是每次都-1的话，那么只需要找出数组中的最小值，然后计算每个元素与最小值的差，再求和就可以了
### 代码
```java
class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(num, min);
        }

        int sum = 0;
        for (int num : nums) {
            sum += num - min;
        }

        return sum;
    }
}
```
# [LeetCode_66_加一](https://leetcode-cn.com/problems/plus-one/)
## 解法
### 思路
从数组尾部开始遍历，并进行计算，考虑是否需要进位，如果不需要就结束遍历
### 代码
```java
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length, carry = 1;
        for (int i = n - 1; i >= 0; i--) {
            int num = digits[i] + carry;
            digits[i] = num % 10;
            if (num / 10 == 0) {
                carry = 0;
                break;
            }
        }

        if (carry == 1) {
            int[] arr = new int[n + 1];
            arr[0] = 1;
            System.arraycopy(digits, 0, arr, 1, n);
            return arr;
        }

        return digits;
    }
}
```
# [LeetCode_282_给表达式添加运算符](https://leetcode-cn.com/problems/expression-add-operators/)
## 解法
### 思路

### 代码
```java

```