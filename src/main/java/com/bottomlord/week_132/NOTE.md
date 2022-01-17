# [LeetCode_1220_统计元音字母序列的数目](https://leetcode-cn.com/problems/count-vowels-permutation/)
## 解法
### 思路
动态规划：
- `dp[i][j]`：代表长度为i的字符串以j为结尾的个数
- 假设[a,e,i,o,u]使用数字对应为[0,1,2,3,4]
- 状态转移方程（根据题意可得）：
  - `dp[i][0] = dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4]`
  - `dp[i][1] = dp[i - 1][0] + dp[i - 1][2]`
  - `dp[i][2] = dp[i - 1][1] + dp[i - 1][3]`
  - `dp[i][3] = dp[i - 1][2]`
  - `dp[i][4] = dp[i - 1][2] + dp[i - 1][3]`
- base case:
  - `dp[1][0] = 1`
  - `dp[1][1] = 1`
  - `dp[1][2] = 1`
  - `dp[1][3] = 1`
  - `dp[1][4] = 1`
- 状态压缩：因为i的值都是由i-1推导而得，所以这个维度可以省略，只处理`dp[j]`即可
- 获得结果：将5种状态的值累加即是结果，需要取模以防止过大
- 为了放置溢出，变量也需要声明为long类型
### 代码
```java
class Solution {
    public int countVowelPermutation(int n) {
        long[] dp = new long[5];
        for (int i = 0; i < 5; i++) {
            dp[i] = 1;
        }

        int mod = 1000000007;

        for (int j = 2; j <= n; j++) {
            long a = dp[0], e = dp[1], i = dp[2], o = dp[3], u = dp[4];

            dp[0] = (e + i + u) % mod;
            dp[1] = (a + i) % mod;
            dp[2] = (e + o) % mod;
            dp[3] = i % mod;
            dp[4] = (i + o) % mod;
        }

        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + dp[i]) % mod;
        }
        return (int)ans;
    }
}
```
# [LeetCode_2085_统计出现过一次的公共字符串](https://leetcode-cn.com/problems/count-common-words-with-one-occurrence/)
## 解法
### 思路
map映射计数
### 代码
```java
class Solution {
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words1) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        map.keySet().removeIf(key -> map.get(key) != 1);
        
        for (String word : words2) {
            map.put(word, map.getOrDefault(word, 0) - 1);
        }

        int count = 0;
        for (String word : map.keySet()) {
            if (map.get(word) == 0) {
                count++;
            }
        }
        
        return count;
    }
}
```
## 解法二
### 思路
- 使用4个set来判断
  - oneMet：在第一个字符串数组中出现
  - oneDup：在第一个字符串数组中重复出现
  - twoMet：在第二个字符串数组中出现
  - twoDup：在第二个字符串数组中重复出现
- 遍历第一个字符串数组，填充oneMet和oneDup
- 遍历第二个字符串数组
  - 如果oneDup中存在，则无需判断，肯定不符合，跳过
  - 如果oneMet中存在，说明在第一个里面是只出现一次
    - 如果在twoMet中存在，说明不只出现一次，不累加，可能要累减
    - 再判断，如果在twoDup中也出现，说明已经在第二次重复出现的时候已经累减过，无需累减
    - 如果在twoDup中没出现，说明当前是第一次重复出现当前字符串，做累减操作，并记录到twoDup中
    - 如果如上都不符合，那么说明可以暂时先认为是只出现一次的，累加同时，放入twoMet中
- 遍历结束，返回count
### 代码
```java
class Solution {
    public int countWords(String[] words1, String[] words2) {
        Set<String> oneMet = new HashSet<>(), oneDup = new HashSet<>(),
                    twoMet = new HashSet<>(), twoDup = new HashSet<>();

        int count = 0;

        for (String word : words1) {
            if (!oneMet.add(word)) {
                oneDup.add(word);
            }
        }

        for (String word : words2) {
            if (oneDup.contains(word)) {
                continue;
            }

            if (oneMet.contains(word)) {
                if (twoMet.contains(word)) {
                    if (twoDup.contains(word)) {
                        continue;
                    }
                    
                    count--;
                    twoDup.add(word);
                    continue;
                }

                count++;
                twoMet.add(word);
            }
        }

        return count;
    }
}
```