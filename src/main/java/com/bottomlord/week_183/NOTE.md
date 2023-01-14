# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1806_还原排列的最少操作步数](https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/)
## 解法
### 思路
暴力模拟
### 代码
```java
class Solution {
    public int reinitializePermutation(int n) {
        int[] perm = new int[n], arr = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
            arr[i] = i;
        }

        int ans = 0;
        while (true) {
            int[] cur = new int[n];

            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    cur[i] = arr[i / 2];
                } else {
                    cur[i] = arr[n / 2 + (i - 1) / 2];
                }
            }

            ans++;
            if (Arrays.equals(cur, perm)) {
                break;
            }
            
            arr = cur;
        }

        return ans;
    }
}
```
## 解法二
### 思路
- 找规律发现，一个坐标值根据题目要求进行变化，一定会最终变回到原始值
- 这就形成了一个环
- 且发现最大环的长度是所有小环长度的公倍数
- 那么最大的环就是题目要求的操作数结果
### 代码
```java
class Solution {
    public int reinitializePermutation(int n) {
        int i = 1, path = 0;
        while (true) {
            i = i % 2 == 0 ? i / 2 : n / 2 + (i - 1) / 2;
            path++;
            
            if (i == 1) {
                return path;
            }
        }
    }
}
```
# [LeetCode_753_破解保险箱](https://leetcode.cn/problems/cracking-the-safe/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1807_替换字符串中的扩号内容](https://leetcode.cn/problems/evaluate-the-bracket-pairs-of-a-string/)
## 解法
### 思路
- 使用哈希表和Regex API进行处理
### 代码
```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> str : knowledge) {
            map.put("(" + str.get(0) + ")", str.get(1));
        }

        String regex = "\\([a-z]+\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        StringBuffer stringBuffer = new StringBuffer();

        while (matcher.find()) {
            String substring = s.substring(matcher.start(), matcher.end());
            matcher.appendReplacement(stringBuffer, matcher.group().replace(substring, map.getOrDefault(substring, "?")));
        }

        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
```
# [LeetCode_1819_序列中不同最大公约数的数目](https://leetcode.cn/problems/number-of-different-subsequences-gcds/)
## 解法
### 思路
- 如果算出一个序列中的最大公约数p，那么在序列中的值就是ci * p，那么任意增加一个序列中的值A = ck * p，它的最大公约数也一定是p
- 通过布尔数组记录nums元素
- 那么就可以遍历1到最大值作为最大公约数的候选值
- 内部遍历时，通过找到当前所有候选最大公约数值的倍数，看看nums中是否存在倍数，如果存在，就计算候选公约数与num之间的公约数，如果计算出来的公约数与候选公约数一致，那就累加计数
- 遍历结束后返回计数值
- 迭代方式的gcd：
```java
class Solution {
    public int gcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1;
            num1 = num2;
            num2 = temp % num2;
        }
        return num1;
    }
}
```
### 代码
```java
class Solution {
    public int countDifferentSubsequenceGCDs(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        boolean[] hasNum = new boolean[max + 1];
        for (int num : nums) {
            hasNum[num] = true;
        }

        int ans = 0;
        for (int i = 1; i <= max; i++) {
            int gcd = 0;
            for (int j = i; j <= max; j += i) {
                if (!hasNum[j]) {
                    continue;
                }

                if (gcd == 0) {
                    gcd = j;
                } else {
                    gcd = gcd(j, gcd);
                }

                if (gcd == i) {
                    ans++;
                    break;
                }
            }
        }

        return ans;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
```