# [LeetCode_27_移除元素](https://leetcode-cn.com/problems/remove-element/submissions/)
## 解法
### 思路
- 使用一个坐标作为新数组的元素指针，然后遍历原数组，判断当前元素是否与val相等
    - 如果相等就跳过，继续移动原指针
    - 否则就将当前元素放在新指针对应的位置，同时移动新旧2个指针
- 遍历结束后，返回新指针的值，就是新数组的长度
### 代码
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                continue;
            }
            nums[index++] = nums[i];
        }
        return index;
    }
}
```
# [LeetCode_524_通过删除字母匹配到字典里最长单词](https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/)
## 解法
### 思路
- 对字典列表根据题目要求排序
- 遍历字典元素，判断是否有元素被s包含
### 代码
```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((x, y) -> {
            if (x.length() != y.length()) {
                return y.length() - x.length();
            }
            return x.compareTo(y);
        });
        
        for (String word : dictionary) {
            int wi = 0, si = 0;
            while (wi < word.length() && si < s.length()) {
                if (word.charAt(wi) == s.charAt(si)) {
                    wi++;
                    si++;
                } else {
                    si++;
                }
                
                if (wi == word.length()) {
                    return word;
                }
            }
        }
        
        return "";
    }
}
```
## 解法二
### 思路
不排序，直接比较所有字典元素，比较过程中保留符合的且长或者字典序小的作为暂存结果
### 代码
```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";
        for (String word : dictionary) {
            if (isSubSequence(s, word)) {
                if (word.length() > ans.length() || word.length() == ans.length() && word.compareTo(ans) < 0) {
                    ans = word;
                }
            }
        }

        return ans;
    }

    private boolean isSubSequence(String s, String word) {
        int wi = 0, si = 0;
        while (wi < word.length() && si < s.length()) {
            if (word.charAt(wi) == s.charAt(si)) {
                wi++;
                si++;
            } else {
                si++;
            }
        }

        return wi == word.length();
    }
}
```
## 解法三
### 思路
- 在解法二的结构上，优化isSubSequence函数，使用String的indexOf函数来判断字典元素当前字符是否是s的一部分
- 这个一部分还需要不停的缩短s的判断范围，而每一次判断的这个起始坐标（也就是s每次判断的范围）都基于上一次indexOf找到的字符的坐标再+1，这样加快了判断的效率
### 代码
```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";
        for (String word : dictionary) {
            if (isSubSequence(s, word)) {
                if (word.length() > ans.length() || word.length() == ans.length() && word.compareTo(ans) < 0) {
                    ans = word;
                }
            }
        }
        
        return ans;
    }

    private boolean isSubSequence(String s, String word) {
        int index = -1;
        for (int i = 0; i < word.length(); i++) {
            index = s.indexOf(word.charAt(i), index + 1);
            
            if (index == -1) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_525_连续数组](https://leetcode-cn.com/problems/contiguous-array/)
## 失败解法
### 原因
超时
### 思路
- 用两个数组，分别计算0和1这两个元素，在0到i坐标范围内的前缀和
- 2层嵌套循环
  - 外层确定窗口的长度
  - 内层确定窗口的左边界
- 找到2个数组在指定窗口中值一样的情况就直接返回该长度
### 代码
```java
class Solution {
    public int findMaxLength(int[] nums) {
        int len = nums.length, oneSum = 0, zeroSum = 0;
        int[] ones = new int[len], zeros = new int[len];
        
        for (int i = 0; i < len; i++) {
            ones[i] = oneSum += (nums[i] == 1 ? 1 : 0);
            zeros[i] = zeroSum += (nums[i] == 1 ? 0 : 1);
        }
        
        for (int l = len; l > 0; l--) {
            for (int i = 0; i + l <= len; i++) {
                int one = ones[i + l - 1] - ones[i] + (nums[i] == 1 ? 1 : 0),
                    zero = zeros[i + l - 1] - zeros[i] + (nums[i] == 1 ? 0 : 1);
                
                if (one == zero) {
                    return l;
                }
            }
        }
        
        return 0;
    }
}
```
## 解法
### 思路
- 因为数组中只有0和1两种数字，所以可以通过出现1记正数，出现0记负数的方式来判断当前数组的0和1是否平衡，记为count
- 然后通过额外的数组空间来记录count的值出现的最早坐标，也就是说额外的空间坐标对应count值
- 当发现记录的count值在数组对应坐标上有记录的坐标值，那就计算当前坐标与记录坐标的差，判断是否出现更大的距离
- 遍历结束后，返回长度结果
- 因为存在正负数，所以额外的数组是一个长度为2 * len + 1
- 因为初始就是count值为0的情况，所以需要初始化arr[len]的值，为了方便计算，就初始化为-1
- 既然初始化是-1，那么没有初始化就用-2来表示
### 代码
```java
class Solution {
    public int findMaxLength(int[] nums) {
        int count = 0, len = nums.length, ans = 0;
        int[] arr = new int[2 * len + 1];
        Arrays.fill(arr, -2);
        arr[len] = -1;
        for (int i = 0; i < len; i++) {
            count += nums[i] == 1 ? 1 : -1;
            
            if (arr[count + len] >= -1) {
                ans = Math.max(ans, i - arr[count + len]);
            } else {
                arr[count + len] = i;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_28_实现strStr](https://leetcode-cn.com/problems/implement-strstr/)
## 解法
### 思路
直接使用String的indexOf
### 代码
```java
class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
```
## 解法二
### 思路
自己实现indexOf
### 代码
```java
class Solution {
    public int strStr(String haystack, String needle) {
        if (Objects.equals("", needle)) {
            return 0;
        }
        
        if (Objects.equals("", haystack)) {
            return -1;
        }
        
        if (haystack.length() < needle.length()) {
            return -1;
        }
        
        if (haystack.length() == needle.length()) {
            return Objects.equals(haystack, needle) ? 0 : -1;
        }

        for (int i = 0; i < haystack.length(); i++) {
            int hi = i, ni = 0;
            while (hi < haystack.length() && ni < needle.length() && haystack.charAt(hi) == needle.charAt(ni)) {
                hi++;
                ni++;
            }

            if (ni == needle.length()) {
                return i;
            }
        }

        return -1;
    }
}
```
# [LeetCode_527_单词缩写](https://leetcode-cn.com/problems/word-abbreviation/)
## 失败解法
### 原因
思路错误，原来以为只要字符串之间的缩写不一样就可以，但实际上，缩写如果能对应2个字符串，也不行
- 例如like和loke，两个字符串最终的缩写只能是like和loke，不能是l2e和loke，因为l2e也能映射为loke
### 思路
字典树变形：
- 使用字典树存储每一个单词
- 在存储字母时，记录该字母所对应的单词，及当前字母对应单词的坐标
- 然后深度搜索字典树，在记录的单词数为1的时候，做缩写操作，缩写的时候判断缩写是否小于原始单词
### 代码
```java
class Solution {
    private TrieNode root = new TrieNode();

    public List<String> wordsAbbreviation(List<String> dict) {
        List<String> ans = new ArrayList<>();
        for (String word : dict) {
            addNode(root, word, 0, ans);
        }
        return ans;
    }

    static class TrieNode {
        private Map<String, Integer> map;
        private TrieNode[] nodes;

        TrieNode() {
            this.map = new HashMap<>();
            this.nodes = new TrieNode[26];
        }
    }

    private void addNode(TrieNode node, String word, int index, List<String> ans) {
        if (index == word.length()) {
            return;
        }

        if (node.nodes[word.charAt(index) - 'a'] == null) {
            node.nodes[word.charAt(index) - 'a'] = new TrieNode();
            String abbreviation = word.substring(0, index + 1) + (word.length() - index - 2 == 0 ? "" : (word.length() - index - 2));
            String suffix;
            if (index < word.length() - 1) {
                suffix = "" + word.charAt(word.length() - 1);
            } else if (index == word.length() - 1) {
                suffix = "" + word.charAt(word.length() - 1);
            } else {
                suffix = "";
            }
            abbreviation = abbreviation + suffix;
            ans.add(abbreviation.length() < word.length() ? abbreviation : word);
            return;
        }

        TrieNode trieNode = node.nodes[word.charAt(index) - 'a'];
        trieNode.map.put(word, index);

        addNode(trieNode, word, index + 1, ans);
    }
}
```
## 解法
### 思路
- 穷举每个单词所有的缩写可能
- 将缩写与单词做映射关系，映射关系的key是缩写，这个缩写需要通过treeMap排序，排序规则是谁短谁靠前
- 初始化一个单词与最终使用缩写的映射关系mapping，单词作为key
- 找到缩写映射的单词数为1的key，且mapping中不包含这个key且缩写长度短语单词长度，则将单词和缩写记录到mapping，否则就跳过。
- 遍历dict，如果mapping中有就将对应的缩写放入结果列表，否则就放入原始单词
- 最终返回原始单词
### 代码
```java
class Solution {
    public List<String> wordsAbbreviation(List<String> dict) {
        TreeMap<String, List<String>> map = new TreeMap<>((x, y) -> {
            if (x.length() != y.length()) {
                return x.length() - y.length();
            }

            return x.compareTo(y);
        });
        
        for (String word : dict) {
            for (int i = 0; i < word.length(); i++) {
                String abbreviation = getAbbreviation(word, i);
                List<String> list = map.getOrDefault(abbreviation, new ArrayList<>());
                list.add(word);
                map.put(abbreviation, list);
            }
        }


        Map<String, String> mapping = new HashMap<>();
        
        for (String abbreviation : map.keySet()) {
            if (map.get(abbreviation).size() == 1) {
                String word = map.get(abbreviation).get(0);
                if (mapping.containsKey(word)) {
                    continue;
                }
                
                if (abbreviation.length() < word.length()) {
                    mapping.put(word, abbreviation);
                }
            }
        }

        List<String> ans = new ArrayList<>();
        for (String word : dict) {
            ans.add(mapping.getOrDefault(word, word));
        }
        
        return ans;
    }

    private String getAbbreviation(String word, int index) {
        String abbreviation = word.substring(0, index + 1) + (word.length() - index - 2 == 0 ? "" : (word.length() - index - 2));
        String suffix;
        if (index < word.length() - 1) {
            suffix = "" + word.charAt(word.length() - 1);
        } else if (index == word.length() - 1) {
            suffix = "" + word.charAt(word.length() - 1);
        } else {
            suffix = "";
        }
        return abbreviation + suffix;
    }
}
```
## 解法二
### 思路
- 根据题目要求，一个单词的缩写是`前缀+中间代替的数字+结尾1个单词`，如果2个单词生成前缀重复，那么这个缩写的前缀就必须至少增加1的长度
- 先遍历列表的单词，生成第一次的缩写，然后将缩写作为key，相同缩写的单词列表作为value，存入map中。
- 列表中的元素作一个封装，封装实体中既包含单词字符串，也包含该字符串的坐标
- 遍历map的values列表，计算第一次生成的缩写重复的单词的重复单词长度，然后根据长度再做一次缩写的生成
- 将生成的没有重复的单词放入结果列表的对应位置，这个位置就可以从之前封装的实体中通过属性上取到
- 最终返回结果列表
### 代码
```java
class Solution {
    public List<String> wordsAbbreviation(List<String> dict) {
        Map<String, List<IndexedWord>> map = new HashMap<>();
        for (int i = 0; i < dict.size(); i++) {
            String word = dict.get(i);
            String abb = abbreviateWord(word, 0);
            List<IndexedWord> words = map.getOrDefault(abb, new ArrayList<>());
            words.add(new IndexedWord(word, i));
            map.put(abb, words);
        }

        String[] ansArr = new String[dict.size()];
        for (List<IndexedWord> indexedWords : map.values()) {
            indexedWords.sort(Comparator.comparing(x -> x.word));
            
            int len = indexedWords.size();
            
            int[] commonPrefixLens = new int[len];
            for (int i = 1; i < len; i++) {
                IndexedWord x = indexedWords.get(i), y = indexedWords.get(i - 1);
                int commonPrefixLen = longestCommonPrefix(x.word, y.word);
                commonPrefixLens[i] = commonPrefixLen;
                commonPrefixLens[i - 1] = Math.max(commonPrefixLen, commonPrefixLens[i - 1]);
            }

            for (int i = 0; i < commonPrefixLens.length; i++) {
                int commonPrefixLen = commonPrefixLens[i];
                IndexedWord indexedWord = indexedWords.get(i);
                String abb = abbreviateWord(indexedWord.word, commonPrefixLen);
                ansArr[indexedWord.index] = abb;
            }
        }

        return Arrays.asList(ansArr);
    }

    private String abbreviateWord(String word, int index) {
        int len = word.length();
        if (len - index <= 3) {
            return word;
        }

        return word.substring(0, index + 1) + (len - index - 2) + word.charAt(len - 1);
    }

    private int longestCommonPrefix(String x, String y) {
        int index = 0;
        while (index < x.length() && index < y.length() && x.charAt(index) == y.charAt(index)) {
            index++;
        }
        return index;
    }

    static class IndexedWord {
        private String word;
        private int index;

        public IndexedWord(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
}
```
# [LeetCode_91_解码方法](https://leetcode-cn.com/problems/decode-ways/)
## 失败解法
### 原因
超时
### 思路
记忆化+回溯
- 先做好数字和字母的映射，方便编程
- 然后记录并回溯查找所有的可能，通过记录进行剪枝
### 代码
```java
class Solution {
    private Map<String, String> map = new HashMap<>();

    {
        char c = 'A';
        for (int i = 1; i <= 26; i++) {
            map.put("" + i, "" + c);
            c = (char) ((int) c + 1);
        }
    }

    public int numDecodings(String s) {
        return backTrack(s, 0, new StringBuilder(), new HashSet<>());
    }

    private int backTrack(String s, int index, StringBuilder sb, Set<String> memo) {
        if (index == s.length()) {
            return 1;
        }

        int ans = 0, len = sb.length();
        String one = "" + s.charAt(index);
        if (map.containsKey(one)) {
            sb.append(map.get(one));
            if (memo.contains(sb.toString())) {
                sb.setLength(len);
            } else {
                memo.add(sb.toString());
                ans += backTrack(s, index + 1, sb, memo);
                sb.setLength(len);
            }
        }

        if (index < s.length() - 1) {
            String two = one + s.charAt(index + 1);
            if (map.containsKey(two)) {
                sb.append(map.get(two));
                if (memo.contains(sb.toString())) {
                    sb.setLength(len);
                } else {
                    memo.add(sb.toString());
                    ans += backTrack(s, index + 2, sb, memo);
                    sb.setLength(len);
                }
            }
        }

        return ans;
    }
}
```
## 解法
### 思路
动态规划：
- `dp[i]`：长度为i的字符编码，可以生成的结果数
- 状态转移方程：
    - dp[i] = charAt(i) valid ? dp[i - 1] : 0
    - dp[i] = dp[i] + (charAt(i - 1) + charAt(i) valid ? dp[i - 2] : 0) 
- base case：
    - dp[1] = charAt(0) valid ? 1 : 0
    - dp[2] = dp[1] + (charAt(0) + charAt(1) valid ? 1 : 0)
- 结果：dp[len]
- 过程：
    - 初始化dp数组，长度是字符串长度len+1
    - 完成base case
    - 从3开始遍历，通过状态转移方程处理dp数组
    - 遍历结束，返回dp[len]
### 代码
```java
class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        
        if (s.startsWith("0")) {
            return 0;
        }
        
        if (len <= 1) {
            return len;
        }

        int[] dp = new int[len + 1];
        dp[1] = 1;
        dp[2] = (isValid("" + s.charAt(1)) ? dp[1] : 0) + (isValid("" + s.charAt(0) + s.charAt(1)) ? 1 : 0);
        
        for (int i = 3; i <= len; i++) {
            if (isValid("" + s.charAt(i - 1))) {
                dp[i] = dp[i - 1];
            }
            
            if (isValid("" + s.charAt(i - 2) + s.charAt(i - 1))) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[len];
    }
    
    private boolean isValid(String s) {
        if (s.length() > 2 || s.length() < 1) {
            return false;
        }
        
        char c = s.charAt(0);
        if (s.length() == 1) {
            return c <= '9' && c >= '1';
        }
        
        if (c != '1' && c != '2') {
            return false;
        }
        
        char c2 = s.charAt(1);
        if (c == '1') {
            return c2 >= '0' && c2 <= '9';
        }
        
        return c2 >= '0' && c2 <= '6';
    }
}
```
## 解法二
### 思路
简化解法一中isValid的判断逻辑
### 代码
```java
class Solution {
    public int numDecodings(String s) {
        int len = s.length();

        if (s.charAt(0) == '0') {
            return 0;
        }

        if (len <= 1) {
            return len;
        }

        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) != '0') {
                dp[i] = dp[i - 1];
            }

            if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) < '7') {
                if (i == 1) {
                    dp[i] += 1;
                } else {
                    dp[i] += dp[i - 2];
                }
            }
        }
        
        return dp[len - 1];
    }
}
```
# [LeetCode_363_矩形区域不超过K的最大数值和](https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/)
## 解法
### 思路
动态规划：
- dp[r1][c1][r2][c2]：[r1,c1]代表矩形的左上角，[r2,c2]代表矩形的右下角，以这两个点所确定的矩形的和
- 简化dp的空间复杂度，从4维降到2维，因为其实在状态转移的时候，当确定了左上角，依赖这个点的所有状态都只需要根据右下角来进行转移，所以可以将dp数组变成2维的
- 状态转移方程：`dp[r][c] = dp[r - 1][c] + dp[r][c - 1] - dp[r - 1][c - 1] + matrix[r][c]`
- 结果：在遍历所有状态时，实时更新最大值，遍历结束返回
### 代码
```java
class Solution {
     public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length, ans = Integer.MIN_VALUE;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                int[][] dp = new int[row + 1][col + 1];
                for (int r = i; r <= row; r++) {
                    for (int c = j; c <= col; c++) {
                        dp[r][c] = dp[r - 1][c] + dp[r][c - 1] - dp[r - 1][c - 1] + matrix[r - 1][c - 1];

                        if (dp[r][c] <= k && dp[r][c] > ans) {
                            ans = dp[r][c];
                        }
                    }
                }
            }
        }
        return ans;
     }
}
```
## 解法二
### 思路
前缀和
- 生成二维数组记录每一行的前缀和
- 然后驱动一个2层循环
    - 外层确定矩形左上角点所在的列
        - 生成一个数组，坐标元素对应矩形的右下角坐标所在的行，所在的列取决于内层循环的坐标
    - 内层确定矩形右下角点所在的列
    - 内层通过前缀和获取以外层坐标作为左上角列，内层坐标作为右下角坐标，外层生成数组坐标作为行的矩形的总和
    - 2层遍历这个数组，获取可能的最大值
### 代码
```java
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        int[][] sums = new int[row][col];
        for (int i = 0; i < row; i++) {
            sums[i][0] = matrix[i][0];
            for (int j = 1; j < col; j++) {
                sums[i][j] = sums[i][j - 1] + matrix[i][j];
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < col; i++) {
            for (int j = i; j < col; j++) {
                int[] colSums = new int[row];
                for (int r = 0; r < row; r++) {
                    colSums[r] = sums[r][j] - sums[r][i] + matrix[r][i];
                }

                ans = Math.max(ans, getMax(colSums, k));
            }
        }

        return ans;
    }

    private int getMax(int[] sums, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int sum = 0;
            for (int j = i; j < sums.length; j++) {
                sum += sums[j];
                if (sum <= k && sum > max) {
                    max = sum;
                }
            }
        }

        return max;
    }
}
```
# [LeetCode_528_按权重随机选择](https://leetcode-cn.com/problems/random-pick-with-weight/)
## 解法
### 思路
- 如果将元素总和作为完整空间
- 那么一个元素值就是空间的一部分
- 那么累加所有元素的总和就是完整空间，每一个元素的位置用前缀和来记录，这样在总数空间里求随机数后，找到前缀和数组中比随机数小的最大值，用这个值对应坐标+1作为结果
- 寻找的过程用二分查找去找
### 代码 
```java
class Solution {
    private Random random = new Random();
    private int[] sums;
    public Solution(int[] w) {
        int len = w.length;
        this.sums = new int[len];

        sums[0] = w[0];
        for (int i = 1; i < len; i++) {
            sums[i] = sums[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        int target = random.nextInt(sums[sums.length - 1]);

        int head = 0, tail = sums.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (target >= sums[mid]) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return head;
    }
}
```
# [LeetCode_368_最大整数子集](https://leetcode-cn.com/problems/largest-divisible-subset/submissions/)
## 解法
### 思路
- 题目是要所有元素互相之间都可以两两整除，那么如果a<b，且a可以整除b，那么c如果大于b，且b可以整除c，则a一定也能整除c
- 基于如上的推断，可以先将数组进行排序
- 然后做动态规划
  - dp[i]：dp数组中存放的元素是一个list，这个数组坐标对应排序后数组的坐标，然后其元素list中的最大值就是这个坐标在排序后数组中对应的元素
    - 这个dp方程代表，list列表中包含的所有以坐标i对应的元素为最大值，且互相之间可以整除的列表
  - 状态转移方程：`nums[i] % nums[j] == 0` => `dp[i] = dp[j]`, `dp[i].add(nums[i])` 
  - base case：`dp[0] = new list`, `dp[0].add(nums[0])`
  - 在状态转移过程中，更新长度最大的结果值
### 代码
```java
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        
        if (len == 0) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        List[] dp = new ArrayList[len];
        for (int i = 0; i < len; i++) {
            dp[i] = new ArrayList();
        }
        dp[0].add(nums[0]);

        List<Integer> ans = dp[0];

        for (int i = 1; i < len; i++) {
            dp[i].add(nums[i]);
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[i].size() <= dp[j].size()) {
                    dp[i] = new ArrayList(dp[j]);
                    dp[i].add(nums[i]);
                }

                if (dp[i].size() > ans.size()) {
                    ans = dp[i];
                }
            }
        }

        return ans;
    }
}
```
# [LeetCode_897_递增顺序搜索树](https://leetcode-cn.com/problems/increasing-order-search-tree/)
## 解法
### 思路
- 中序dfs搜索生成列表
- 遍历列表组装新树
### 代码
```java
class Solution {
    public TreeNode increasingBST(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        dfs(root, nodes);
        for (int i = 0; i < nodes.size(); i++) {
            TreeNode curNode = nodes.get(i);
            curNode.left = null;
            if (i != nodes.size() - 1) {
                curNode.right = nodes.get(i + 1);
            } else {
                curNode.right = null;
            }
        }
        return nodes.get(0);
    }
    
    private void dfs(TreeNode node, List<TreeNode> nodes) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, nodes);
        nodes.add(node);
        dfs(node.right, nodes);
    }
}
```
# [LeetCode_531_孤独像素I](https://leetcode-cn.com/problems/lonely-pixel-i/)
## 解法
### 思路
- 遍历二维数组，分别维护纵和横的B出现的个数，并记录这个坐标
- 遍历维护的B坐标，判断其横坐标和纵坐标，再之前维护的数组中存储的值是否都只是1，如果是的话就累加
- 最终返回累加的个数
### 代码
```java
class Solution {
    public int findLonelyPixel(char[][] picture) {
        int row = picture.length, col = picture[0].length;
        int[] rc = new int[row], cc = new int[col];
        List<int[]> list = new ArrayList<>();
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (picture[r][c] == 'B') {
                    rc[r] += 1;
                    cc[c] += 1;
                    list.add(new int[]{r,c});
                }
            }
        }
        
        int ans = 0;
        for (int[] arr : list) {
            if (rc[arr[0]] == 1 && cc[arr[1]] == 1) {
                ans++;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_533_孤独像素II](https://leetcode-cn.com/problems/lonely-pixel-ii/)
## 解法
### 思路
思路和[LeetCode_531_孤独像素I](https://leetcode-cn.com/problems/lonely-pixel-i/)类似，求出个数，同时再补充例如每一列B所在的行数
### 代码
```java

```