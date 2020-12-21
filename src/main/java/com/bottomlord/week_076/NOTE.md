# [LeetCOde_385_UTF-8编码验证](https://leetcode-cn.com/problems/utf-8-validation/)
## 解法
### 思路
- 成为UTF8编码的条件：
    - 最多4个字节
    - 如果是1个字节，代表第一个字节的数字第一位是0
    - 如果是n个字节，代表第一个字节的数字的头n位是1，n+1位是0，且之后的n-1个字节是10开头
- 编码模拟过程
### 代码
```java
class Solution {
    public boolean validUtf8(int[] data) {
        int count = 0;
        for (int num : data) {
            String numBinaryStr = Integer.toBinaryString(num);
            if (numBinaryStr.length() >= 8) {
                numBinaryStr = numBinaryStr.substring(numBinaryStr.length() - 8);
            } else {
                numBinaryStr = "00000000".substring(numBinaryStr.length() % 8) + numBinaryStr;
            }
            
            if (count == 0) {
                for (char c : numBinaryStr.toCharArray()) {
                    if (c == '0') {
                        break;
                    }

                    count++;
                }

                if (count == 0) {
                    continue;
                }

                if (count > 4 || count == 1) {
                    return false;
                }
            } else {
                if (numBinaryStr.charAt(0) != '1' || numBinaryStr.charAt(1) != '0') {
                    return false;
                }
            }
            
            count--;
        }

        return count == 0;
    }
}
```
## 解法二
### 思路
使用位运算替代字符串
### 代码
```java
class Solution {
    public boolean validUtf8(int[] data) {
        int count = 0, maskOne = 1 << 7, maskTwo = 1 << 6;
        for (int num : data) {
            if (count == 0) {
                if ((maskOne & num) == 0) {
                    continue;
                }
                
                while ((num & maskOne) != 0) {
                    count++;
                    num <<= 1;    
                }
                
                if (count > 4 || count == 1) {
                    return false;
                }
            } else {
                if (!((num & maskOne) != 0 && (num & maskTwo) == 0)) {
                    return false;
                }
            }

            count--;
        }
        
        return count == 0;
    }
}
```