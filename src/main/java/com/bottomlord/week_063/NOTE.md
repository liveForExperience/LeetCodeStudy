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