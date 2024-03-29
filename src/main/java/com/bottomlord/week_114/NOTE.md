# [LeetCode_447_回旋镖的数量](https://leetcode-cn.com/problems/number-of-boomerangs/)
## 解法
### 思路
- 2层循环
  - 外层确定作为回旋镖交叉点的点
  - 内层遍历所有其他的点
- 内层开始循环前，初始化一个map，key为两点间距离，value为该距离对应的组成的对数
- 内层循环的时候，判断当前计算出的2点距离，在map中是否存在
  - 存在就取出该值，并在该值基础上乘以2，累加到结果变量上，因为该值代表除了当前对，已经存在的其他距离为当前值的对，且因为顺序变更是不同的回旋镖，所以要乘以2
  - 在取出的该值基础上，加1，代表当前对也统计到之后可能的计算中
### 代码
```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }

                double distance = Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2);
                int count = map.getOrDefault(distance, 0);
                ans += count * 2;
                map.put(distance, count + 1);
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_1656_设计有序流](https://leetcode-cn.com/problems/design-an-ordered-stream/)
## 解法
### 思路
- 初始化一个变量用于记录坐标，一个数组用于存储插入的元素
- 根据insert的idKey来判断是直接插入，还是返回连续的字符串列表
### 代码
```java
class OrderedStream {
    private String[] strs;
    private int cur;
    public OrderedStream(int n) {
        this.strs = new String[n + 1];
        this.cur = 1;
    }

    public List<String> insert(int idKey, String value) {
        if (idKey != cur) {
            strs[idKey] = value;
            return Collections.emptyList();
        } else {
            strs[idKey] = value;
            List<String> ans = new ArrayList<>();
            for (int i = cur; i < strs.length; i++, cur++) {
                if (strs[i] != null) {
                    ans.add(strs[i]);
                } else {
                    break;
                }
            }
            return ans;
        }
    }
}
```
# [LeetCode_1662_检查两个字符串数组是否相等](https://leetcode-cn.com/problems/check-if-two-string-arrays-are-equivalent/)
## 解法
### 思路
2个字符串数组依次拼接并最终比较
### 代码
```java
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
        for (String s : word1) {
            sb1.append(s);
        }

        for (String s : word2) {
            sb2.append(s);
        }
        
        return sb1.toString().equals(sb2.toString());
    }
}
```
# [LeetCode_1668_最大重复子字符串](https://leetcode-cn.com/problems/maximum-repeating-substring/)
## 解法
### 思路
2层循环：
- 外层确定起始坐标
- 内层确定从该位置开始能够找到的最大的连续字符串个数
- 更新最大值
- 遍历结束后返回
### 代码
```java
class Solution {
    public int maxRepeating(String sequence, String word) {
int wordLen = word.length(), max = 0;
        for (int i = 0; i < sequence.length(); i++) {
            int count = 0;
            for (int j = i; j < sequence.length() && j + wordLen <= sequence.length();) {
                String sub = sequence.substring(j, j + wordLen);
                if (Objects.equals(sub, word)) {
                    count++;
                    j += wordLen;
                } else {
                    max = Math.max(max, count);
                    count = 0;
                    j++;
                }
            }

            max = Math.max(max, count);
        }
        return max;
    }
}
```
## 解法二
### 思路
使用字符数组遍历计数来代替String API
### 代码
```java
class Solution {
    public int maxRepeating(String sequence, String word) {
        int max = 0, len = sequence.length(), wlen = word.length();
        char[] wcs = word.toCharArray(), scs = sequence.toCharArray();
        for (int i = 0; i < len; i++) {
            if (wcs[0] == scs[i]) {
                int w = 0, k = i;
                while (k < len && wcs[w % wlen] == scs[k]) {
                    k++;
                    w++;
                }

                max = Math.max(max, w / wlen);
            }
        }
        
        return max;
    }
}
```
# [LeetCode_524_通过删除字母匹配到字典里最长单词](https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/)
## 解法
### 思路
- map+treemap：
  - map用于存储字母和出现的坐标之间的映射关系
  - treemap用于快速找到下一个可能存在的坐标
- 先生成map+treemap的嵌套数据结构
- 对字典字符串数组排序
- 依次遍历找到第一个符合的字符串返回，否则返回空字符串
### 代码
```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        Map<Character, TreeSet<Integer>> map = new HashMap<>();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            map.computeIfAbsent(cs[i], x -> new TreeSet<>()).add(i);
        }

        dictionary.sort((x, y) -> {
            if (x.length() == y.length()) {
                return x.compareTo(y);
            }

            return y.length() - x.length();
        });

        for (String word : dictionary) {
            boolean flag = true;
            int index = -1;
            for (char c : word.toCharArray()) {
                if (!map.containsKey(c)) {
                    flag = false;
                    break;
                }

                TreeSet<Integer> set = map.get(c);
                Integer nextKey = set.ceiling(index);

                if (nextKey == null) {
                    flag = false;
                    break;
                }

                index = nextKey + 1;
            }

            if (flag) {
                return word;
            }
        }

        return "";
    }
}
```
## 解法二
### 思路
- 用数组替代treemap存储下一个有效坐标
- 初始化一个比字符串长1的二维数组
  - 第一维对应要比较的字符串的字符坐标
  - 第二维代表第一维坐标情况下，以第一维坐标为起始，往后的所有字母中，第一个和要比较字母相同的字符坐标
  - 要比字符串长1的原因是，每次要比较的字符串，找到它这个字母的在字符串中能匹配的位置，就需要右移1位，代表这个字母已经不能再使用了，需要在这个字母之后找到下一个匹配的坐标，那么当使用掉字符串最后一个字母之后，坐标就会因为右移而越界，所以多出的1就是为了处理这种情况
- 二维数组的第二维，用的是一个长度26的int数组，用来对应26个英文字母以及在字符串中的坐标，初始化为-1，目的是用-1来判断是否有效，如果找不到有效的坐标，就会返回-1
- 从字符串尾部开始向前遍历，将当前遍历到的字母及字符串中的坐标，放入到长度26的数组中，然后放入二维数组里
- 每遍历一次，那个长度26的数组都复制后一个字母产生的数组，因为之后判断的时候是正向的，所以之后的字母对应的坐标可能在正向判断的过程中是可以被使用到的，而复制过来以后，只更改当前字母对应的坐标，也就意味着如果之后有和当前字符一样的字母，那么当前遍历过程中就会对这个字母的坐标进行更新，从而使正向遍历过程中先找到这个靠前的坐标
- 生成二维数组后，开始依次遍历字典中的单词：
  - 如果当前单词比字符串长，那直接跳过，因为肯定不符合题目要求
  - 正向遍历单词的每一个字符，从二维数组中找到对应的坐标，判断是否是-1，如果是，那这个单词也不符合要求
  - 然后在下个字符开始判断前，将坐标+1，使得下次判断前跳过现在已经使用过的字母位置
```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        int length = s.length();
        int[][] next = new int[length + 1][];

        int[] lastArr = new int[26];
        Arrays.fill(lastArr,-1);
        next[length] = lastArr;

        for (int i = length - 1; i >= 0; i--) {
            int[] currentArr = new int[26];
            System.arraycopy(lastArr, 0, currentArr, 0, 26);
            currentArr[s.charAt(i) - 'a'] = i;
            next[i] = lastArr = currentArr;
        }

        String result = "";
        for (String word : dictionary) {
            if (isSubsequence(word, next)) {
                if (word.length() > result.length()) result = word;
                else if (word.length() == result.length() && word.compareTo(result) < 0) result = word;
            }
        }
        return result;
    }

    private boolean isSubsequence(String word, int[][] next) {
        int p = 0;
        for (int i = 0; i < word.length(); i++) {
            p = next[p][word.charAt(i) - 'a'];
            if (p == -1) return false;
            p++;
        }
        return true;
    }
}
```
# [LeetCode_162_寻找峰值](https://leetcode-cn.com/problems/find-peak-element/)
## 解法
### 思路
- 二分查找
- 查找时依赖mid值和相邻的mid+1的值进行比较，判断窗口缩小的区间
  - 如果mid < mid + 1指向的元素，那么说明右边有更大的元素，就选择右边这块区间
  - 否则就选择左边这块区间
### 代码
```java
class Solution {
    public int findPeakElement(int[] nums) {
        return binarySearch(0, nums.length - 1, nums);
    }

    private int binarySearch(int head, int tail, int[] nums) {
        if (head == tail) {
            return head;
        }
        
        int mid = head + (tail - head) / 2;
        return  (nums[mid] < nums[mid + 1]) ? binarySearch(mid + 1, tail, nums) : binarySearch(head, mid, nums);
    }
}
```
# [LeetCode_212_单词搜索](https://leetcode-cn.com/problems/word-search-ii/)
## 解法
### 思路
遍历+回溯+记忆化
### 代码
```java
class Solution {
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        int row = board.length, col = board[0].length;
        Map<Character, List<int[]>> mapping = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                mapping.computeIfAbsent(board[i][j], x -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (word == null || word.length() == 0) {
                continue;
            }

            List<int[]> indexes = mapping.get(word.charAt(0));
            if (indexes == null) {
                continue;
            }
            
            for (int[] index : indexes) {
                if (backTrack(index[0], index[1], row, col, 0, word, board, new boolean[row][col])) {
                    ans.add(word);
                    break;
                }
            }
        }

        return ans;
    }

    private boolean backTrack(int x, int y, int row, int col, int index, String word, char[][] board, boolean[][] memo) {
        if (index == word.length()) {
            return true;
        }

        if (x < 0 || x >= row || y < 0 || y >= col || memo[x][y] || board[x][y] != word.charAt(index)) {
            return false;
        }

        memo[x][y] = true;
        for (int[] direction : directions) {
            if (backTrack(x + direction[0], y + direction[1], row, col, index + 1, word, board, memo)) {
                return true;
            }
        }
        return memo[x][y] = false;
    }
}
```
## 解法二
### 思路
- 解法一中的每个单词都会导致搜索一遍整个board数组，这个过程中有许多步骤是重复的
- 可以先将所有单词放入字典树中进行存储
- 然后就回溯查找一次board，在查找的过程中：
  - 如果当前途径的字符串在字典树中存在，就累加到结果里
  - 如果当前途径的字符串，在字典树中没有相应的路径，就及时终止
  - 本身回溯过程中的记忆化搜索
- 回溯查找完所有可能的路径后，将累加到的结果按字典序排列后返回即可
### 代码
```java
class Solution {
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        int row = board.length, col = board[0].length;

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> set = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                backTrack(i, j, row, col, trie, board, new StringBuilder(), set);
            }
        }

        return new ArrayList<>(set);
    }

    private void backTrack(int x, int y, int row, int col, Trie trie, char[][] board, StringBuilder sb, Set<String> set) {
        if (x < 0 || x >= row || y < 0 || y >= col || board[x][y] == '#') {
            return;
        }

        char c = board[x][y];
        int len = sb.length();
        sb.append(c);
        String str = sb.toString();
        board[x][y] = '#';
        
        if (!trie.search(str)) {
            sb.setLength(len);
            board[x][y] = c;
            return;
        }

        if (trie.isWord(str)) {
            set.add(str);
        }

        for (int[] direction : directions) {
            backTrack(x + direction[0], y + direction[1], row, col, trie, board, sb, set);
        }
        
        sb.setLength(len);
        board[x][y] = c;
    }

    private class Trie {
        private final TireNode root;

        public Trie() {
            this.root = new TireNode();
        }

        public void insert(String word) {
            TireNode node = root;

            char[] cs = word.toCharArray();
            for (char c : cs) {
                int index = c - 'a';

                if (node.children[index] == null) {
                    node.children[index] = new TireNode();
                }

                node = node.children[index];
            }

            node.isWord = true;
        }

        public boolean search(String word) {
            return doSearch(word) != null;
        }

        public boolean isWord(String word) {
            TireNode node = doSearch(word);
            return node != null && node.isWord;
        }

        private TireNode doSearch(String word) {
            TireNode node = root;

            char[] cs = word.toCharArray();
            for (char c : cs) {
                int index = c - 'a';

                TireNode childNode = node.children[index];
                if (childNode == null) {
                    return null;
                }

                node = childNode;
            }

            return node;
        }

        private class TireNode {
            private final TireNode[] children;
            private boolean isWord;

            public TireNode() {
                this.children = new TireNode[26];
            }
        }
    }
} 
```
# [LeetCode_36_有效的数独](https://leetcode-cn.com/problems/valid-sudoku/)
## 解法
### 思路
- 横、竖、区块分别建立一个数据结构用来存储出现的数字
- 如果这个部分对应的数字有重复就说明不符合题目要求
- 横竖用二维数组存储，区块用map存储
- 遍历二维数组然后做判断
### 代码
```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Map<String, Set<Character>> mapping = new HashMap<>();
        boolean[][] rows = new boolean[9][9], cols = new boolean[9][9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }

                if (rows[i][c - '1']) {
                    return false;
                }
                rows[i][c - '1'] = true;
                
                if (cols[j][c - '1']) {
                    return false;
                }
                cols[j][c - '1'] = true;
                
                String key = i / 3 + ":" + (j / 3);
                boolean add = mapping.computeIfAbsent(key, x -> new HashSet<>()).add(c);
                if (!add) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
- 其实区块也可以用数字来表示，因为坐标的值范围是0-8，除以3之后，就是0-2，那么横坐标就可以用 r * 3来代表，每一个横坐标的区间就空出了3个位置，把纵坐标/3之后加上去，这样9个区间的就可以通过这个公式计算出来`(r / 3) * 3 + c / 3`
- 而计算是否有重复其实也只需要用一个32位的数字就可以表示，通过位上是1还是0来表示当前值是否有重复
- 所以可以通过3个一维数组来记录，判断的时候就通过数位移动来处理
### 代码
```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[9], col = new int[9], block = new int[9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                
                int index = c - '0';
                int shift = 1 << index;
                int blockIndex = i / 3 * 3 + j / 3;
                
                if ((row[i] & shift) > 0 || (col[j] & shift) > 0 || (block[blockIndex] & shift) > 0) {
                    return false;
                }
                
                row[i] |= shift;
                col[j] |= shift;
                block[blockIndex] |= shift;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_292_Nim游戏](https://leetcode-cn.com/problems/nim-game/)
## 失败解法
### 原因
超出内存限制
### 思路
动态规划，当前状态依赖前三次可能选择上是否有无法成功的状态，如果有的话，当前这轮操作就可以成功
- dp[i]：i个石头的情况下，是否能拿到最后一块石头
- base case：
  - `dp[0] = dp[1] = dp[2] = dp[3] = true`
- 状态转移方程：`dp[i] = !dp[i - 1] || !dp[i - 2] || !dp[i - 3]`
### 代码
```java
class Solution {
  public boolean canWinNim(int n) {
    if (n <= 3) {
      return true;
    }

    boolean[] dp = new boolean[n + 1];
    dp[0] = dp[1] = dp[2] = dp[3] = true;
    for (int i = 4; i <= n; i++) {
      dp[i] = !dp[i - 1] || !dp[i - 2] || !dp[i - 3];
    }

    return dp[n];
  }
}
```
## 失败解法
### 原因
超时
### 思路
从失败一解法中看出，状态只依赖前三个值得状态，所以可以不存储一个数组，而是只存储3个变量即可，转移过程中不断更新
### 代码
```java
class Solution {
    public boolean canWinNim(int n) {
        if (n <= 3) {
            return true;
        }

        boolean x = true, y = true, z = true;
        for (int i = 4; i <= n; i++) {
            boolean flag = !x | !y | !z;
            x = y;
            y = z;
            z = flag;
        }
        
        return z;
    }
}
```
## 解法
### 思路
- 推导状态的变化
```bash
111011101110 
```
- 发现1110这样的规律，也就是每三次一定能拿到最后一个，之后就跟一个不能，这也是因为之前推导出的状态转移的含义，就是之前3个里面只要有1个是不行的，当前就一定行
- 所以值是否能被4整除就可以用来判断是否是能拿到最后的石块，整除就不能，不整除就能
### 代码
```java
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
```
# [LeetCode_1672_最富有客户的资产总量](https://leetcode-cn.com/problems/richest-customer-wealth/)
## 解法
### 思路
- 初始化一维数组
- 遍历累加和更新最大值
- 遍历结束返回最大值
### 代码
```java
class Solution {
  public int maximumWealth(int[][] accounts) {
    int len = accounts.length, max = 0;
    int[] sums=  new int[len];

    for (int i = 0; i < accounts.length; i++) {
      for (int j = 0; j < accounts[i].length; j++) {
        sums[i] += accounts[i][j];
      }

      max = Math.max(max, sums[i]);
    }

    return max;
  }
}
```
# [LeetCode_650_只有两个键的键盘](https://leetcode-cn.com/problems/2-keys-keyboard/)
## 解法
### 思路
动态规划：
- `dp[i]`：能够生成i个字符需要的最少次数
- base case：`dp[1] = 0`
- 状态转移方程：
  - 外层循环遍历从2到n的字符串数，从而进行状态转移
  - 内层循环确定所有能够转移到当前i个字符的可能字符数j，这个j一定能够整除i，这样就能通过翻i / j倍来生成i个字符
  - 为了减少内层转移的次数，可以同时处理j倍的i/j个字符的情况，也就是如下两种方程：
    - `dp[i] = min(dp[i], dp[j] + i / j)`
    - `dp[i] = min(dp[i], dp[i / j] + j)`
### 代码
```java
class Solution {
  public int minSteps(int n) {
    int[] dp = new int[n + 1];
    for (int i = 2; i <= n; i++) {
      dp[i] = Integer.MAX_VALUE;
      for (int j = 1; j * j <= n; j++) {
        if (i % j == 0) {
          dp[i] = Math.min(dp[i], dp[j] + i / j);
          dp[i] = Math.min(dp[i], dp[i / j] + j);
        }
      }
    }

    return dp[n];
  }
}
```