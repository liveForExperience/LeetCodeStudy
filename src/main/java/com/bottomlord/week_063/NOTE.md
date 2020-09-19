# LeetCode_269_火星词典
## 题目
现有一种使用字母的全新语言，这门语言的字母顺序与英语顺序不同。

假设，您并不知道其中字母之间的先后顺序。但是，会收到词典中获得一个 不为空的 单词列表。因为是从词典中获得的，所以该单词列表内的单词已经 按这门新语言的字母顺序进行了排序。

您需要根据这个输入的列表，还原出此语言中已知的字母顺序。

示例 1：
```
输入:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
输出: "wertf"
```
示例 2：
```
输入:
[
  "z",
  "x"
]
输出: "zx"
```
示例 3：
```
输入:
[
  "z",
  "x",
  "z"
] 
输出: "" 
解释: 此顺序是非法的，因此返回 ""。
```
提示：
```
你可以默认输入的全部都是小写字母
若给定的顺序是不合法的，则返回空字符串即可
若存在多种可能的合法字母顺序，请返回其中任意一种顺序即可
```
## 解法
### 思路
拓扑排序：
- 根据字符串数组构件图，key为字符，value为排在key之后的字符的集合
- 进行拓扑排序：
    - 初始化数组，出现的字符为0，未出现的字符为-1
    - 遍历构建好的图，将所有key对应的set集合中的字符计数，值越大，排序越靠后
- 使用队列bfs遍历，拼接可能的字符串：
    - 初始化第一层，遍历拓扑排序数组，将值为0的字符放入队列中，代表这个元素排名最靠前
    - 通过得到的值为0的字符，到图中找对应比它靠后的字符，找到一个，就在拓扑排序数组中减1，直到找到一个值为0的字符，代表这个字符在图中再也没有比它靠前的元素了，将它入队
- 计算下出现的字符和拼接得到的字符串的长度是否相等，如果不相等，代表这个图有环，类似a > b > c > a这种情况，使得拓扑数组中总有非0的元素
- 最后返回字符串
### 代码
```java
class Solution {
    public String alienOrder(String[] words) {
        if (words.length == 0) {
            return "";
        }

        Map<Character, Set<Character>> map = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            int len = Math.max(words[i].length(), words[i + 1].length());
            
            for (int j = 0; j < len; j++) {
                if (j >= words[i].length()) {
                    break;
                }

                if (j >= words[i + 1].length()) {
                    return "";
                }
                
                if (words[i].charAt(j) == words[i + 1].charAt(j)) {
                    continue;
                }

                Set<Character> set = map.getOrDefault(words[i].charAt(j), new HashSet<>());
                set.add(words[i + 1].charAt(j));
                map.put(words[i].charAt(j), set);
                break;
            }
        }

        int[] degree = new int[26];
        Arrays.fill(degree, -1);
        for (String word : words) {
            for (char c : word.toCharArray()) {
                degree[c - 'a'] = 0;
            }
        }

        for (Character key : map.keySet()) {
            for (Character c : map.get(key)) {
                degree[c - 'a']++;
            }
        }

        Queue<Character> queue = new ArrayDeque<>();
        int count = 0;
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] != -1) {
                count++;
            }

            if (degree[i] == 0) {
                queue.offer((char) (i + 'a'));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character key = queue.poll();
            sb.append(key);

            if (map.containsKey(key)) {
                Set<Character> set = map.get(key);
                for (char c : set) {
                    degree[c - 'a']--;
                    if (degree[c - 'a'] == 0) {
                        queue.offer(c);
                    }
                }
            }
        }

        return sb.length() == count ? sb.toString() : "";
    }
}
```
# LeetCode_270_最接近的二叉搜索数值
## 题目
给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接近目标值 target 的数值。

注意：
```
给定的目标值 target 是一个浮点数
题目保证在该二叉搜索树中只会存在一个最接近目标值的数
```
示例：
```
输入: root = [4,2,5,1,3]，目标值 target = 3.714286

    4
   / \
  2   5
 / \
1   3

输出: 4
```
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    private double minDiff = Double.MAX_VALUE;
    private int min = Integer.MAX_VALUE;
    public int closestValue(TreeNode root, double target) {
        dfs(root, target);
        return min;
    }
    
    private void dfs(TreeNode node, double target) {
        if (node == null) {
            return;
        }

        double diff = Math.abs(node.val - target);
        if (diff < minDiff) {
            minDiff = diff;
            min = node.val;
        }

        if (node.val > target) {
            dfs(node.left, target);
        } else {
            dfs(node.right, target);
        }
    }
}
```
## 解法二
### 思路
无状态dfs
### 代码
```java
class Solution {
    public int closestValue(TreeNode root, double target) {
        return dfs(root, target, root.val);
    }

    private int dfs(TreeNode node, double target, int best) {
        if (Math.abs(best - target) > Math.abs(node.val - target)) {
            best = node.val;
        }

        if (target > node.val) {
            if (node.right == null) {
                return best;
            } else {
                return dfs(node.right, target, best);
            }
        } else {
            if (node.left == null) {
                return best;
            } else {
                return dfs(node.left, target, best);
            }
        }
    }
}
```
# LeetCode_271_字符串的编码与解码
## 题目
请你设计一个算法，可以将一个 字符串列表 编码成为一个 字符串。这个编码后的字符串是可以通过网络进行高效传送的，并且可以在接收端被解码回原来的字符串列表。

1 号机（发送方）有如下函数：
```
string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
```
2 号机（接收方）有如下函数：
```
vector<string> decode(string s) {
  //... your code
  return strs;
}
```
1 号机（发送方）执行：
```
string encoded_string = encode(strs);
```
2 号机（接收方）执行：
```
vector<string> strs2 = decode(encoded_string);
```
此时，2 号机（接收方）的 strs2 需要和 1 号机（发送方）的 strs 相同。

请你来实现这个 encode 和 decode 方法。

注意：
```
因为字符串可能会包含 256 个合法 ascii 字符中的任何字符，所以您的算法必须要能够处理任何可能会出现的字符。
请勿使用 “类成员”、“全局变量” 或 “静态变量” 来存储这些状态，您的编码和解码算法应该是非状态依赖的。
请不要依赖任何方法库，例如 eval 又或者是 serialize 之类的方法。本题的宗旨是需要您自己实现 “编码” 和 “解码” 算法。
```
## 解法
### 思路
- 使用非ASCII码分隔字符串
- 注意：使用`String.split(,-1)`，重载方法的第二个参数需要选择`-1`，否则`["",""]`，会被只拆成一个`""`
### 代码
```java
public class Codec {
    public String encode(List<String> strs) {  
        if(strs.size() == 0) {
            return null;
        }
        
        String sep = Character.toString((char) 257);
        return String.join(sep, strs);
    }

    public List<String> decode(String s) {
        if (s == null) {
            return new ArrayList<>();
        }
        
        String sep = Character.toString((char) 257);
        return Arrays.asList(s.split(sep, -1));
    }
}
```
## 解法二
### 思路
- 在每个字符串前拼上4个字符长度的头信息，用来代表后面字符串的长度
- 数字转四个字符：处理为`x >> n * 8 & 255`，其中x为字符串长度，n为把32位的int值以8位为1部分后拆分成的4个部分，循环处理4部分，生成4个字符的长度字符串
- 四个字符转数字：遍历字符数组，依次左移8位并累加即可
### 代码
```java
public class Codec {
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(int2String(str.length()));
            sb.append(str);
        }

        return sb.toString();
    }

    private String int2String(int num) {
        char[] cs = new char[4];
        for (int i = 3; i >=0; i--) {
            cs[3 - i] = (char) (num >> (8 * i) & 0xff);
        }
        
        return new String(cs);
    }

    public List<String> decode(String s) {
        int index = 0, n = s.length();
        List<String> ans = new ArrayList<>();
        while (index < n) {
            int len = string2Int(s.substring(index, index + 4));
            index += 4;
            ans.add(s.substring(index, index + len));
            index += len;
        }
        return ans;
    }

    private int string2Int(String s) {
        int num = 0;
        for (char c : s.toCharArray()) {
            num = (num << 8) + c;
        }
        return num;
    }
}
```
# LeetCode_272_最接近的二叉搜索树值
## 题目
给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接近目标值 target 的 k 个值。

注意：
```
给定的目标值 target 是一个浮点数
你可以默认 k 值永远是有效的，即 k ≤ 总结点数
题目保证该二叉搜索树中只会存在一种 k 个值集合最接近目标值
```
示例：
```
输入: root = [4,2,5,1,3]，目标值 = 3.714286，且 k = 2

    4
   / \
  2   5
 / \
1   3

输出: [4,3]
拓展：
假设该二叉搜索树是平衡的，请问您是否能在小于 O(n)（n 为总结点数）的时间复杂度内解决该问题呢？
```
## 解法
### 思路
- 中序遍历二叉搜索树，遍历的顺序是从小到大的升序
- 在遍历时用一个list存储可能的值
- 在处理当前节点时，使用如下规则：
    - 如果list的大小不到k，直接将当前值放入
    - 否则：
        - 如果当前值与target的差，小于第一个元素（最小）的元素与target的差，那么将第一个元素去除，将当前值放入list尾部
        - 如果不是，就直接跳过
    - 如上规则能够有效的原因：
        - 遍历顺序是升序，所以入list的值的序列也只会是升序
        - 那么从list头部的值开始，依次遍历并与target比较的过程，一定是如下情况：
            - 不断趋近：说明头部的值会是最差解，如果有比其更好的值，就替换
            - 不断远离：说明头部的值会是最优解，不可能有比其更好的值
            - 趋近后再远离：
                - 说明头部的值一定在开始阶段是最差解，此时有更优解，应该替换
                - 如果第一次出现比头部值更差的解，且此时还在list容量范围内，那么之后也就不会有比头部值更好的解了
- 遍历结束后，返回list
### 代码
```java
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> ans = new LinkedList<>();
        dfs(root, target, k, ans);
        return ans;
    }

    private void dfs(TreeNode node, double target, int k, LinkedList<Integer> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, target, k, list);
        if (list.size() < k) {
            list.addLast(node.val);
        } else if (Math.abs(node.val - target) < Math.abs(list.getFirst() - target)) {
            list.removeFirst();
            list.addLast(node.val);
        } else {
            return;
        }
        dfs(node.right, target, k, list);
    }
}
```
# LeetCode_685_冗余链接II
## 题目
在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。

输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。

结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。

返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。

示例 1:
```
输入: [[1,2], [1,3], [2,3]]
输出: [2,3]
解释: 给定的有向图如下:
  1
 / \
v   v
2-->3
```
示例 2:
```
输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
输出: [4,1]
解释: 给定的有向图如下:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
```
注意:
```
二维数组大小的在3到1000范围内。
二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。
```
## 解法
### 思路
并查集
- 有根树的特点:
    - 只有一个入度为0的节点，这个节点为根节点
    - 除根节点外，其他节点的入度都为1
    - 不成环
- 判断：
    - 根据有向图，计算入度
    - 然后倒序遍历有向图集合，判断入度为2的节点所在这条边，如果不放入并查集中，是否会形成有向图，如果不会，这条边就是额外的边
    - 如果入度都不为2，那就遍历有向图集合，判断哪条边删除后，不是有环图，那么这条边就是额外的边
### 代码
```java
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int len = edges.length;

        int[] inDegrees = new int[len + 1];
        for (int[] edge : edges) {
            inDegrees[edge[1]]++;
        }

        for (int i = len - 1; i >= 0; i--) {
            if (inDegrees[edges[i][1]] == 2 && removeAndNoCircle(edges, i)) {
                return edges[i];
            }
        }

        for (int i = len - 1; i >= 0; i--) {
            if (removeAndNoCircle(edges, i)) {
                return edges[i];
            }
        }

        return null;
    }

    private boolean removeAndNoCircle(int[][] edges, int i) {
        int len = edges.length;
        UnionFind unionFind = new UnionFind(len + 1);

        for (int j = 0; j < len; j++) {
            if (i == j) {
                continue;
            }

            if (!unionFind.union(edges[j][1], edges[j][0])) {
                return false;
            }
        }

        return true;
    }

    static class UnionFind {
        private int[] parents;
        public UnionFind(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n; i++) {
                this.parents[i] = i;
            }
        }

        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public boolean union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) {
                return false;
            }

            parents[px] = py;
            return true;
        }
    }
}
```
# LeetCode_273_整数转换英文表示
## 题目
将非负整数转换为其对应的英文表示。可以保证给定输入小于 231 - 1 。

示例 1:
```
输入: 123
输出: "One Hundred Twenty Three"
```
示例 2:
```
输入: 12345
输出: "Twelve Thousand Three Hundred Forty Five"
```
示例 3:
```
输入: 1234567
输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
```
示例 4:
```
输入: 1234567891
输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
```
## 解法
### 思路
硬做：
- 用两个map存储会用到的字符串
    - 一个用来存1000以内的数会用到的所有字符串
    - 一个用来存储3个位一段所对应的字符串
- 将数字分成3个一部分，用字符串表示出1000以内的数字
- 然后拼接上当前这段的字符串表示
### 代码
```java
class Solution {
    private Map<Integer, String> map = new HashMap<>();
    private Map<Integer, String> map2 = new HashMap<>();

    {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        map2.put(0, "");
        map2.put(1, "Thousand");
        map2.put(2, "Million");
        map2.put(3, "Billion");
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (num != 0) {
            int cur = num % 1000;

            if (cur != 0) {
                sb.insert(0, " ");
                sb.insert(0, map2.get(index));

                int n = cur % 100;
                if (n == 0) {

                } else if (n < 20) {
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n));
                } else if (map.containsKey(n)){
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n));
                } else {
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n % 10));
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n / 10 * 10));
                }

                if (cur / 100 * 100 != 0) {
                    sb.insert(0, " ");
                    sb.insert(0, map.get(cur / 100) + " Hundred");
                }
            }

            index++;
            num /= 1000;
        }

        return sb.toString().trim();
    }
}
```