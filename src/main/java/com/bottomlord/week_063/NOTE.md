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