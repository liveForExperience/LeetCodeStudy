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
# [LeetCode_395_至少有K个重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/)
## 解法
### 思路
递归：
- 以不足k个的字符为界，切分字符串
- 然后进行递归
    - 退出条件：
        - 字符串总长度小于k
        - 当前字符串中不含有小于k个的字符，直接返回字符长度
    - 如果包含就继续递归，并返回所有可能的最大值
### 代码
```java
class Solution {
    public int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }

        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0 && arr[i] < k) {
                String[] strs = s.split("" + (char) (i + 'a'));
                int max = 0;
                for (String str : strs) {
                    if ("".equals(str)) {
                        continue;
                    }
                    max = Math.max(max, longestSubstring(str, k));
                }
                return max;
            }
        }

        return s.length();
    }
}
```
# [LeetCode_396_旋转函数](https://leetcode-cn.com/problems/rotate-function/)
## 解法
### 思路
旋转重新计算f的过程可以看作：在计算f(n)时，是在f(n - 1)的基础上增加所有元素的总和，这样所有元素的乘数上都加了1，但此时最后的那个元素是要被移动到头部并乘以0的，于是就只要确定当前要旋转哪个元素，把它从和中减去就可以了
### 代码
```java
class Solution {
    public int maxRotateFunction(int[] A) {
        int len = A.length, sum = 0, f = 0;
        for (int i = 0; i < len; i++) {
            sum += A[i];
            f += A[i] * i;
        }

        int max = f;
        for (int i = len - 1; i > 0; i--) {
            f = f + sum - (len) * A[i];
            max = Math.max(max, f);
        }

        return max;
    }
}
```
# [LeetCode_397_正数替换](https://leetcode-cn.com/problems/integer-replacement/)
## 解法
### 思路
记忆化搜索+递归
### 代码
```java
class Solution {
    public int integerReplacement(int n) {
        return recurse(n, new HashMap<>());
    }
    
    private int recurse(long num, Map<Long, Integer> memo) {
        if (num == 1) {
            return 0;
        }

        if (memo.containsKey(num)) {
            return memo.get(num);
        }

        int count = 0;
        if (num % 2 == 0) {
            count = recurse(num / 2, memo) + 1;
        } else {
            int bCount = recurse(num + 1, memo) + 1,
                sCount = recurse(num - 1, memo) + 1;

            count = Math.min(bCount, sCount);
        }

        memo.put(num, count);
        return count;
    }
}
```
# [LeetCode_403_青蛙过河](https://leetcode-cn.com/problems/frog-jump/)
## 解法
### 思路
递归+记忆化搜索
### 代码
```java
class Solution {
    public boolean canCross(int[] stones) {
        if (stones[1] - stones[0] != 1) {
            return false;
        }
        
        return recurse(0, 1, stones, new HashMap<>());
    }

    private boolean recurse(int preIndex, int curIndex, int[] stone, Map<String, Boolean> memo) {
        if (curIndex == stone.length - 1) {
            return true;
        }
        
        if (curIndex >= stone.length) {
            return false;
        }

        if (memo.containsKey(preIndex + " " + curIndex)) {
            return memo.get(preIndex + " " + curIndex);
        }

        int diff = stone[curIndex] - stone[preIndex];
        for (int i = curIndex + 1; i < stone.length; i++) {
            int curDiff = stone[i] - stone[curIndex];
            if (curDiff < diff - 1) {
                continue;
            }
            
            if (curDiff == diff - 1 || curDiff == diff || curDiff == diff + 1) {
                boolean curResult = recurse(curIndex, i, stone, memo);
                memo.put(curDiff + " " + i, curResult);
                
                if (curResult) {
                    return true;
                }
            }
            
            if (curDiff > diff + 1) {
                break;
            }
        }
        
        memo.put(preIndex + " " + curIndex, false);
        return false;
    }
}
```
## 解法二
### 思路
哈希表：
- 初始化一个哈希表map：
    - key存储的是stone中所有的值，另加一个0，代表青蛙可以落脚的位置
    - value中存储每个石头位置对应的，可以从其他之前石头跳跃过来的距离
    - 初始化的时候，key为0的value放入一个距离0
- 遍历map的key，之后遍历key对应的value集合，判断每个集合中的距离distance| distance + 1 | distance - 1三种情况在map中是否存在对应的key，如果存在，就将这个距离放入找到的key 对应的value集合中
- 最后判断最后石头的位置在map中的value长度是否大于0，大于达标可以到达
- value要用set去重，否则会有很多重复的距离放到list中
### 代码
```java
class Solution {
    int[] diffs = new int[]{-1,0,1};
    
    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            if (i == 0) {
                map.put(stones[0], new HashSet<Integer>(){{this.add(0);}});
            } else {
                map.put(stones[i], new HashSet<>());
            }
        }

        for (int i = 0; i < stones.length - 1; i++) {
            Set<Integer> values = map.get(stones[i]);

            for (int value : values) {
                for (int diff : diffs) {
                    if (value + diff <= 0) {
                        continue;
                    }

                    if (map.containsKey(stones[i] + value + diff)) {
                        map.get(stones[i] + value + diff).add(value + diff);
                    }
                }
            }
        }

        return !map.get(stones[stones.length - 1]).isEmpty();
    }
}
```