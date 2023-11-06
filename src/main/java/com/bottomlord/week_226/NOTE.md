# [LeetCode_318_最大单词长度乘积](https://leetcode.cn/problems/maximum-product-of-word-lengths)
## 解法
### 思路
- 思考过程： 
  - 题目要求计算和比较字符串长度的乘积最大值，相乘的两个字符串不能有公共字母，因为题目限制了字符串中只能出现小写字母，所以可以通过一个32位的整数存储26位小写字母在字符串中的出现情况，从而判断是否有重复
  - 重复的判断逻辑也很简单，就是通过2个32位整数的2进制位进行相与操作，如果`与`之后的结果是0，那么就说明没有公共字母
  - 然后2层循环遍历数组，根据字母的出现情况判断是否可以将2个字符串的长度相乘，如果可以就得到相乘的结果后和暂存的最大值进行比较即可
- 算法过程：
  - 初始化int数组`arr`，用来存储每个字符串字母出现情况的统计值
  - 初始化变量`ans`，初始值为0，用来作为成绩最大值的暂存变量
  - 循环一次数组，迭代并计算每个元素字符串的字母出现情况，记录到`arr`对应坐标中
  - 再次2层循环数组，两两判断是否有重复字母，判断方式`(arr[i] & arr[j]) == 0`，如果判断结果为没有重复字母，则相乘后与暂存`ans`进行比较并更新为较大值
  - 2层循环结束，返回`ans`座位结果
### 代码
```java
class Solution {
    public int maxProduct(String[] words) {
        int n = words.length, index = 0;
        int[] arr = new int[n];
        for (String word : words) {
            arr[index++] = mask(word);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((arr[i] & arr[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }

        return ans;
    }

    private int mask(String s) {
        char[] cs = s.toCharArray();
        int mask = 0;
        for (char c : cs) {
            mask |= 1 << (c - 'a');
        }
        return mask;
    }
}
```