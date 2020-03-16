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