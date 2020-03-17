# Interview_0106_字符串压缩
## 题目
字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。

示例1:
```
 输入："aabcccccaaa"
 输出："a2b1c5a3"
```
示例2:
```
 输入："abbccd"
 输出："abbccd"
 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
```
提示：
```
字符串长度在[0, 50000]范围内。
```
## 解法
### 思路
嵌套循环：
- 外层确定重复字符的起始位置和字符
- 内层循环迭代所有和起始字符一致的字符，计算个数
- 通过StringBuilder叠加
- 循环结束，与原字符串比较，如果没有原字符串短就返回原字符串，否则返回StringBuilder
### 代码
```java
class Solution {
    public String compressString(String S) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length();) {
            char start = S.charAt(i);
            int j = i + 1;
            while (j < S.length() && start == S.charAt(j)) {
                j++;
            }
            sb.append(start).append(j - i);
            i = j;
        }
        return sb.length() >= S.length() ? S : sb.toString();
    }
}
```
# Interview_0107_旋转矩阵
## 题目

给定一幅由N × N矩阵表示的图像，其中每个像素的大小为4字节，编写一种方法，将图像旋转90度。

不占用额外内存空间能否做到？

示例 1:
```
给定 matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
```
示例 2:
```
给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
```
## 解法
### 思路
- 初始化一个新的二维数组
- 嵌套遍历原来的二维数组，并放入新数组中
    - 从第一列开始到最后一列开始遍历
    - 从最后一行到第一行开始遍历
### 代码
```java
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        
        int row = matrix.length, col = matrix[0].length;
        int[][] ans = new int[col][row];
        
        for (int i = 0; i < col; i++) {
            for (int j = row - 1; j >= 0; j--) {
                ans[i][row - j - 1] = matrix[j][i];
            }
        }
        
        for (int i = 0; i < row; i++) {
            System.arraycopy(ans[i], 0, matrix[i], 0, col);
        }
    }
}
```
## 解法二
### 思路
矩阵旋转90度，相当于：
1. 对角线反转
2. 左右翻转
### 代码
```java
class Solution {
    public void rotate(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < i; j++) {
                matrix[i][j] += matrix[j][i];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] = matrix[i][j] - matrix[j][i];
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col / 2; j++) {
                matrix[i][j] += matrix[i][col - 1 - j];
                matrix[i][col - 1 - j] = matrix[i][j] - matrix[i][col - 1 - j];
                matrix[i][j] = matrix[i][j] - matrix[i][col - 1 - j];
            }
        }
    }
}
```
# Interview_0108_零矩阵
## 题目
编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。

示例 1：
```
输入：
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出：
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
```
示例 2：
```
输入：
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出：
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
```
## 解法
### 思路
- 遍历二维数组，获得所有为0的坐标
- 初始化2个set，分别存放获得的0的坐标的行与列的值
- 循环结束，遍历两个set，确定需要修改为0的行与列
### 代码
```java
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        
        int row = matrix.length, col = matrix[0].length;
        Set<Integer> rows = new HashSet<>(), cols = new HashSet<>();
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        
        for (int i : rows) {
            Arrays.fill(matrix[i], 0);
        }
        
        for (int i : cols) {
            for (int j = 0; j < row; j++) {
                matrix[j][i] = 0;
            }
        }
    }
}
```
## 优化代码
### 思路
使用布尔数组作为哈希表实现，记录需要清零的行列
### 代码
```java
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int row = matrix.length, col = matrix[0].length;
        boolean[] rows = new boolean[row], cols = new boolean[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            if (rows[i]) {
                Arrays.fill(matrix[i], 0);
            }
        }

        for (int i = 0; i < col; i++) {
            if (cols[i]) {
                for (int j = 0; j < row; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }
}
```
# LeetCode_1160_拼写单词
## 题目
给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。

假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。

注意：每次拼写时，chars 中的每个字母都只能用一次。

返回词汇表 words 中你掌握的所有单词的 长度之和。

示例 1：
```
输入：words = ["cat","bt","hat","tree"], chars = "atach"
输出：6
解释： 
可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
```
示例 2：
```
输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
输出：10
解释：
可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
```
提示：
```
1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
所有字符串中都仅包含小写英文字母
```
## 解法
### 思路
- 外层遍历词汇表
    - 初始化数组，记录字母表中字符出现的个数
- 内层遍历单词
    - 将遍历到的字符从字母表中减一，如果字母小于0，说明单词无法掌握
    - 如果遍历结束，没有小于0的字符个数，累加结果
### 代码
```java
class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] bucket = new int[256];
        for (char c : chars.toCharArray()) {
            bucket[c]++;
        }
        int ans = 0;
        for (String word : words) {
            int[] tmp = Arrays.copyOf(bucket, bucket.length);
            boolean learn = true;
            for (char c : word.toCharArray()) {
                tmp[c]--;
                if (tmp[c] < 0) {
                    learn = false;
                    break;
                }
            }
            
            if (learn) {
                ans += word.length();
            }
        }
        
        return ans;
    }
}
```
# Interview_0109_字符串轮转
## 题目
字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。

示例1:
```
 输入：s1 = "waterbottle", s2 = "erbottlewat"
 输出：True
```
示例2:
```
 输入：s1 = "aa", "aba"
 输出：False
```
提示：
```
字符串长度在[0, 100000]范围内。
```
说明:
```
你能只调用一次检查子串的方法吗？
```
## 解法
### 思路
- 移动旋转字符串的指针i，判断`i`指向的字符与原字符串的第一个字符是否相等
- 如果相等，尝试比较从当前位置到字符串尾部和从头到当前位置的两部分字符串拼接后，是否与原字符串相等
- 如果不是相同字符串，继续移动`i`，直到找到或`i`越界为止
### 代码
```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        if (Objects.equals(s1, "") && Objects.equals(s2, "")) {
            return true;
        }

        int len2 = s2.length();
        for (int i = 0; i < len2; i++) {
            if (s1.charAt(0) == s2.charAt(i)) {
                if (Objects.equals(s1, String.valueOf(s2.toCharArray(), i, len2 - i) + String.valueOf(s2.toCharArray(), 0, i))) {
                    return true;
                }
            }
        }

        return false;
    }
}
```
## 解法二
### 思路
如果`s2`是`s1`旋转后的字符串，那么将两个`s2`拼接后，必然能包含一个完整的字符串`s1`
### 代码
```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() && (s2 + s2).contains(s1);
    }
}
```