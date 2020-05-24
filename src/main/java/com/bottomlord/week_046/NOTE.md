# LeetCode_152_乘积最大子数组
## 题目
给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

示例 1:
```
输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
```
示例 2:
```
输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
```
## 解法
### 思路
动态规划：
- dp[i]：以i为结尾的子序列中乘积最大值
- 因为包含负数，所以如果当前值是负数，就会希望之前的值越小越好。所以需要再维护一个最小值的dp序列
- 状态转移方程：
    - `dpMax[i] = max(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i], nums[i])` 
    - `dpMin[i] = min(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i], nums[i])`
- 遍历`dpMax[i]`，返回最大值
### 代码
```java
class Solution {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] dpMax = new int[len], dpMin = new int[len];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            dpMax[i] = Math.max(dpMax[i - 1] * num, Math.max(dpMin[i - 1] * num, num));
            dpMin[i] = Math.min(dpMax[i - 1] * num, Math.min(dpMin[i - 1] * num, num));
        }
        
        return Arrays.stream(dpMax).max().getAsInt();
    }
}
```
## 解法二
### 思路
- 观察解法一可以发现，状态之间的转换只依赖前一个最大值和最小值上，随意可以使用两个变量替换数组
- 同时在循环过程中直接计算最大值，省去最后的循环步骤
- 在循环体中需要暂存上一个循环的最大最小值，不能直接使用，因为有两个状态转移方程式，如果第一个方程式直接赋值，会导致第二个方程式求解错误
### 代码
```java
class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMax = max, curMin = min, num = nums[i];
            max = Math.max(curMax * num, Math.max(curMin * num, num));
            min = Math.min(curMax * num, Math.min(curMin * num, num));
            ans = Math.max(max, ans);
        }
        
        return ans;
    }
}
```
# Interview_1721_直方图的水量
## 题目
给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。

上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。

示例:
```
输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
```
## 解法
### 思路
暴力：
- 遍历直方图数组
- 以当前元素为中心，向左右遍历，找到左右方向上的2个最大值max
- 在2个最大值max中找到最小值min
- 如果min比当前值cur大，则差值就是当前位置可以储存的数量
- 将所有位置的值累加就是结果
### 代码
```java
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0, rightMax = 0, curIndex = i;
            while (--curIndex >= 0) {
                leftMax = Math.max(leftMax, height[curIndex]);
            }
            
            curIndex = i;
            while (++curIndex < height.length) {
                rightMax = Math.max(rightMax, height[curIndex]);
            }
            
            int min = Math.min(leftMax, rightMax);
            if (min > height[i]) {
                ans += min - height[i];
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
双指针：
- 初始化头尾两个指针
- 初始化头尾的最大值变量，起始为头尾元素
- 头尾指针相向移动
- 比较头尾最大值的大小：
    - 如果头最大值小于尾最大值，那么累加头最大值与头元素之间的差值
    - 如果尾最大值小于头最大值，那么累加尾最大值与尾元素之间的差值
    - 这么计算的原因是：当前元素是否能盛多少水，取决于当前元素的左右两边最大值中的最小值。头最大值如果小于尾最大值，那么头元素能盛多少水就能通过头最大值来确定，而此时尾最大值是否是当前头元素的真正右边的最大值是不用考虑的。反之亦然。
### 代码
```java
class Solution {
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        
        int ans = 0, head = 0, tail = height.length - 1;
        int headMax = height[head], tailMax = height[tail];
        
        while (head < tail) {
            if (headMax < tailMax) {
                ans += headMax - height[head++];
                headMax = Math.max(headMax, height[head]);
            } else {
                ans += tailMax - height[tail--];
                tailMax = Math.max(tailMax, height[tail]);
            }
        }
        
        return ans;
    }
}
```
## 解法三
### 思路
- 初始化两个数组
    - pre：以当前坐标结尾的数组中的最大值
    - suf：以当前坐标开头的数组中的最大值
- 遍历height填充pre和suf
- 遍历height，求height当前元素与min(pre, suf)之间的差值，如果是负值就取0
### 代码
```java
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        if (len < 3) {
            return 0;
        }
        
        int[] pre = new int[len], suf = new int[len];
        pre[0] = height[0];
        suf[len - 1] = height[len - 1];
        
        for (int i = 1; i < len - 1; i++) {
            pre[i] = Math.max(pre[i - 1], height[i]);
        }
        
        for (int i = len - 2; i >= 0; i--) {
            suf[i] = Math.max(suf[i + 1], height[i]);
        }
        
        int ans = 0;
        for (int i = 1; i < len - 1; i++) {
            ans += Math.max(0, Math.min(pre[i - 1], suf[i + 1]) - height[i]);
        }
        
        return ans;
    }
}
```
# LeetCode_1371_每个元音包含偶数次的最长子字符串
## 题目
给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。

示例 1：
```
输入：s = "eleetminicoworoep"
输出：13
解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
```
示例 2：
```
输入：s = "leetcodeisgreat"
输出：5
解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
```
示例 3：
```
输入：s = "bcbcbc"
输出：6
解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。
```
提示：
```
1 <= s.length <= 5 x 10^5
s 只包含小写英文字母。
```
## 解法
### 思路
前缀和+状态压缩
- 因为要使元音字符出现的次数是偶数次，所以前缀和记录的可以不是具体的数字，而是奇偶性。
    - 奇数+/-奇数=偶数
    - 偶数+/-偶数=偶数
- 只要两种状态一致，就可以进行距离相减的计算，算出区间的长度
- 也因为只需要记录奇偶性，所以可以直接使用0或1来记录这个状态，所以只需要用一个数字的低5位上数就能记录5个元音字符在以当前坐标为结尾元素的位置上的奇偶性状态
- 因为从00000到11111一共2^5个状态，所以使用一个长度为32的数组，下标对应这些状态，值用来存结尾坐标i
- 遍历字符串，计算状态
    - 如果该状态不存在，就记录这个状态第一次出现的坐标
    - 如果该状态已经存在，那么就计算当前坐标与记录坐标之间的差值
### 代码
```java
class Solution {
    public int findTheLongestSubstring(String s) {
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        pos[0] = 0;
        int ans = 0, status = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                status ^= 1;
            } else if (c == 'e') {
                status ^= 1 << 1;
            } else if (c == 'i') {
                status ^= 1 << 2;
            } else if (c == 'o') {
                status ^= 1 << 3;
            } else if (c == 'u') {
                status ^= 1 << 4;
            }
            
            if (pos[status] != -1) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;
            }
        }
        
        return ans;
    }
}
```
# LeetCode_5_最长回文子串
## 题目
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：
```
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
```
示例 2：
```
输入: "cbbd"
输出: "bb"
```
## 解法
### 思路
动态规划：
- `dp[i][j]`：s[i,j]是否为回文串
- 状态转移方程：
    - `dp[i][j] = dp[i + 1, j - 1] && s[i] == s[j]`
- 初始化：
    - `j == i：dp[i][j] = true`
    - `j = i + 1：dp[i][j] = s[i] == s[j]`
- 过程：两层循环
    - 外层遍历子回文串长度l，从0开始
    - 内容遍历子字符串起始位置i，并用l来确定结束位置j
    - 使用状态转移方程推演状态
    - 如果获得新的回文串，且长度大于暂存的回文串，就更新
    - 循环结束，返回记录的数组
### 代码
```java
class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        String ans = "";
        for (int l = 0; l < len; l++) {
            for (int i = 0; i < len; i++) {
                int j = i + l;
                if (j >= len) {
                    break;
                }

                if (l == 0) {
                    dp[i][j] = true;
                } else {
                    boolean equals = s.charAt(i) == s.charAt(j);
                    if (l == 1) {
                        dp[i][j] = equals;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] && equals;
                    }
                }
                
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }
}
```
## 解法二
### 思路
中心扩散：
- 以确定的回文字符串作为中心点，向两边扩散，获取回文子串
    - 长度为1
    - 长度为2，且两个字符相等
### 代码
```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        int len = s.length(), start = 0, end = 0;
        for (int i = 0; i < len; i++) {
            int l = Math.max(expand(i, i, s), expand(i, i + 1, s));
            if (l > end - start) {
                start = i - (l - 1) / 2;
                end = i + l / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expand(int start, int end, String s) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }

        return end - start - 1;
    }
}
```
# Interview_1722_单词转换
## 题目
给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。

编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。

示例 1:
```
输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出:
["hit","hot","dot","lot","log","cog"]
```
示例 2:
```
输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

输出: []

解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
```
## 解法
### 思路
回溯+记忆化搜索
- 定义一个函数，在下钻前，在wordList中确定与当前字符串只差一个字符的字符串，将他们作为下钻的字符串
- 使用一个path来记录下钻的路径字符串，回溯的时候去除最后一个字符串
- 如果下钻的字符串和endWord一样，那就放入结果中并直接返回
### 代码
```java
class Solution {
    private boolean[] memo;
    private List<String> wordList;
    private List<String> result;
    private String endWord;
    private List<String> path;

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        this.wordList=wordList;
        this.result=new ArrayList();
        this.wordList = wordList;
        this.path = new ArrayList<>();
        this.memo = new boolean[wordList.size()];
        this.endWord=endWord;
        backTrack(beginWord);
        return result;
    }

    private void backTrack(String beginWord) {
        this.path.add(beginWord);
        List<String> list = findWords(beginWord);
        for (String word : list) {
            if (Objects.equals(word, endWord)) {
                path.add(word);
                result = new ArrayList<>(path);
                return;
            }

            backTrack(word);

            path.remove(path.size() - 1);
        }
    }

    private List<String> findWords(String str) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            int diff = 0;
            if (memo[i] || word.length() != str.length()) {
                continue;
            }

            for (int j = 0; j < word.length(); j++) {
                if (diff > 1) {
                    break;
                }

                if (word.charAt(j) != str.charAt(j)) {
                    diff++;
                }
            }

            if (diff == 1) {
                list.add(word);
                memo[i] = true;
            }
        }

        return list;
    }
}
```