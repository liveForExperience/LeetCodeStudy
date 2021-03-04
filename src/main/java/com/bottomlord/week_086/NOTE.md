# [LeetCode_471_编码最短长度的字符串](https://leetcode-cn.com/problems/encode-string-with-shortest-length/)
## 解法
### 思路
- 首先解决的问题是，找到当前字符串的重复子字符串：
    - 如果字符串为s，那么将`s + s`，然后从第2个字符(下标为1的位置)开始找字符串s，并判断找到的坐标`p`是否与s的长度一致
        - 如果一致：说明当前字符串内不包含重复子字符串
        - 如果不一致：则`[0,p]`就是字符串`s`中的子字符串
    - 然后通过字符串长度n，除以子字符串的长度，就是s中包含重复子字符串的个数
- 然后定义动态规划：
    - `dp[i][j]`：`[i,j]`区间的字符串表达式，可能是原始字符串，也可能是编码过的字符串
    - 状态转移方程：`dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j])`，看以k为中点分割成的2个字符串，拼接在一起的长度是否小于`dp[i][j]`的长度
    - 返回结果：`dp[i][n - 1]`，这个值代表s这个字符串的编码值
- 算法过程：
    - 定义dp集合，二维表的长宽都是字符串s的长度
    - 接着是循环体：
        - 外层定义要填充二维表的子字符串长度`len`，从1开始
        - 内层定义子字符串的起始坐标，需要注意退出条件是`i + len - 1 < n`
        - 然后通过最开始的算法求出当前字符串的最优表达式，设置为当前二维表坐标的值
        - 接着做状态转移，在当前子字符串中，寻找是否还有可能的编码可能，也就是子字符串的子字符串是否有编码过，从而长度变短了
        - 做第三层遍历，定义k值，k的起始值为内层的`i`坐标，退出条件为`k < i + len - 1`
        - 在第三层中，确定拼接的子字符串是否比内层求出的子字符串更短，如果是，就替换
### 代码
```java
class Solution {
        public String encode(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                dp[i][i + len - 1] = getEncodeString(dp, s, i, i + len - 1);

                for (int k = i; k < i + len - 1; k++) {
                    String str = dp[i][k] + dp[k + 1][i + len - 1];
                    if (str.length() < dp[i][i + len - 1].length()) {
                        dp[i][i + len - 1] = str;
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    private String getEncodeString(String[][] dp, String s, int i, int j) {
        s = s.substring(i, j + 1);
        if (s.length() < 5) {
            return s;
        }

        int p = (s + s).indexOf(s, 1);
        if (p < s.length()) {
            int count = s.length() / p;
            return count + "[" + dp[i][i + p - 1] + "]";
        }
        return s;
    }
}
```
# [LeetCode_472_连接词](https://leetcode-cn.com/problems/concatenated-words/)
## 失败解法
### 原因
时间复杂度过高
### 思路
- 使用set集合存储所有word
- 遍历word，再通过回溯判断是否是链接单词
    - 回溯中通过遍历word的字符，判断当前子字符串是否存在于set中
    - 回溯过程中有一个前提，需要把当前word从set中去除掉，否则判断时需要去除本身这个特殊情况
    - 回溯时，不断切除头部包含在set中的子字符串，然后将剩余的字符串递归到下一层
    - 回溯的退出条件是字符串为空，所以回溯前还要跳过字符串为空的情况
    - 将符合条件的字符串放入结果
### 代码
```java
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (Objects.equals("", word)) {
                continue;
            }

            set.remove(word);
            if (backTrack(word, set)) {
                ans.add(word);
            }
            set.add(word);
        }

        return ans;
    }

    private boolean backTrack(String word, Set<String> set) {
        if (word.length() == 0) {
            return true;
        }

        boolean result = false;
        for (int i = 0; i < word.length(); i++) {
            if (set.contains(word.substring(0, i + 1))) {
                result = backTrack(word.substring(i + 1), set);
            }

            if (result) {
                break;
            }
        }

        return result;
    }
}
```
## 解法
### 思路
- 失败解法的原因是时间复杂度过高，没有记录之前遍历过的字符串中失败的内容，导致重复失败。
- 在算法中增加记忆化搜索，记录失败的字符串，形成剪枝
### 代码
```java
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(words)), memo = new HashSet<>();
        for (String word : words) {
            if (Objects.equals("", word)) {
                continue;
            }

            set.remove(word);
            if (backTrack(word, set, memo)) {
                ans.add(word);
            }
            set.add(word);
        }

        return ans;
    }

    private boolean backTrack(String word, Set<String> set, Set<String> memo) {
        if (word.length() == 0) {
            return true;
        }

        boolean result = false;
        for (int i = 1; i <= word.length(); i++) {
            if (set.contains(word.substring(0, i))) {
                String right = word.substring(i);
                if (memo.contains(right)) {
                    continue;
                }
                
                if (backTrack(word.substring(i), set, memo)) {
                    return true;
                }
                
                memo.add(right);
            }
        }

        return result;
    }
}
```
# [LeetCode_473_火柴拼正方形](https://leetcode-cn.com/problems/matchsticks-to-square/)
## 解法
### 思路
- 先求总长度，再求边长
- 初始化map，用来统计nums出现的个数
- 初始化memo，set集合，用来记录失败的快照（升序map的key的序列+当前长度）
- 开始回溯获取结果，回溯中生成快照与memo中比较，形成剪枝
### 代码
```java
class Solution {
    public boolean makesquare(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int sum = Arrays.stream(nums).sum();
        if (sum % 4 != 0 || sum < 4) {
            return false;
        }

        int sideLen = sum / 4;

        return backTrack(0, 0, sideLen, map, nums, new HashSet<>());
    }

    private boolean backTrack(int curLen, int sideIndex, int sideLen, Map<Integer, Integer> map, int[] nums, Set<String> memo) {
        if (curLen == sideLen) {
            sideIndex++;
            curLen = 0;
        }

        if (sideIndex == 4 && curLen == 0 && isEmpty(map)) {
            return true;
        }

        if (memo.contains("" + curLen + "-" + snapshot(map))) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 0) {
                continue;
            }

            if (curLen + nums[i] <= sideLen) {
                map.put(nums[i], map.get(nums[i]) - 1);
                boolean result = backTrack(curLen + nums[i], sideIndex, sideLen, map, nums, memo);

                if (result) {
                    return true;
                }

                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        memo.add("" + curLen + "-" + snapshot(map));
        return false;
    }

    private boolean isEmpty(Map<Integer, Integer> map) {
        for (Integer key : map.keySet()) {
            if (map.get(key) != 0) {
                return false;
            }
        }

        return true;
    }

    private String snapshot(Map<Integer, Integer> map) {
        return map.keySet().stream().filter(x -> map.get(x) != 0).map(x -> "" + x).collect(Collectors.joining(" "));
    }
}
```