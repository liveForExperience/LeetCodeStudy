# LeetCode_249_移位字符串数组
## 题目
给定一个字符串，对该字符串可以进行 “移位” 的操作，也就是将字符串中每个字母都变为其在字母表中后续的字母，比如："abc" -> "bcd"。这样，我们可以持续进行 “移位” 操作，从而生成如下移位序列：
```
"abc" -> "bcd" -> ... -> "xyz"
给定一个包含仅小写字母字符串的列表，将该列表中所有满足 “移位” 操作规律的组合进行分组并返回。
```
示例：
```
输入：["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
输出：
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
解释：可以认为字母表首尾相接，所以 'z' 的后续为 'a'，所以 ["az","ba"] 也满足 “移位” 操作规律。
```
## 解法
### 思路
三层循环+记忆化搜索
- 外层循环确定起始字符串
- 中层循环寻找移位的字符串
- 内层比较每一位字符的`(字符差值 + 26) % 26`是否相等
- 在每次开始寻找和确定找到的时候，将字符串放入记忆化布尔数组中标志
### 代码
```java
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        int len = strings.length;
        boolean[] memo = new boolean[len];
        List<List<String>> ans = new ArrayList<>();
        
        for (int i = 0; i < len; i++) {
            if (memo[i]) {
                continue;
            }
            
            memo[i] = true;
            
            String s1 = strings[i];
            List<String> list = new ArrayList<String>(){{this.add(s1);}};
            
            for (int j = i + 1; j < len; j++) {
                String s2 = strings[j];
                if (memo[j] || s1.length() != s2.length()) {
                    continue;
                }
                
                int dis = (s1.charAt(0) - s2.charAt(0) + 26) % 26;
                boolean flag = true;
                for (int k = 1; k < s1.length(); k++) {
                    if ((s1.charAt(k) - s2.charAt(k) + 26) % 26 != dis) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    list.add(s2);
                    memo[j] = true;
                }
            }
            
            ans.add(list);
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
map：
- 遍历字符串，将所有字符串都对齐成起始字符为`a`的状态
- 将转移后相同的字符串放在一个key后的list中
- 遍历结束后，返回所有的集合
### 代码
```java
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String shift = shift(str);
            List<String> list = map.getOrDefault(shift, new ArrayList<>());
            list.add(str);
            map.put(shift, list);
        }

        return new ArrayList<>(map.values());
    }

    private String shift(String str) {
        int bit = str.charAt(0) - 'a';
        char[] cs = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            cs[i] = cs[i] - (char)bit - 'a' >= 0 ? (char)(cs[i] - (char)bit) : (char)(cs[i] - (char)(bit - 26));
        }
        return new String(cs);
    }
}
```
# LeetCode_250_统计共值子树
## 题目
给定一个二叉树，统计该二叉树数值相同的子树个数。

同值子树是指该子树的所有节点都拥有相同的数值。

示例：
```
输入: root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

输出: 4
```
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    private int count = 0;
    
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        dfs(root);
        return count;
    }
    
    private boolean dfs(TreeNode node) {
        if (node.left == null && node.right == null) {
            count++;
            return true;
        }
        
        boolean same = true;
        if (node.left != null) {
            same = dfs(node.left) && node.val == node.left.val;
        }
        
        if (node.right != null) {
            same = dfs(node.right) && same && node.val == node.right.val;
        }
        
        if (!same) {
            return false;
        }
        
        count++;
        return true;
    }
}
```
## 解法二
### 思路
dfs：
- 退出条件：当前节点为null，返回true
- 在递归处理的这一层，如果下层返回的都是true，那么就累加count值，说明当前节点的值与左右子节点的值相等
- 如果下层有任一返回false或者当前节点与上层节点不等，就返回false
- 注意判断下层左右节点是否与当前节点是否相等的条件语句，如果用或来组合在一起，那就必须时非短路的或，否则会导致递归搜索不完整
### 代码
```java
class Solution {
    private int count = 0; 
    public int countUnivalSubtrees(TreeNode root) {
        dfs(root, null);
        return count;
    }
    
    private boolean dfs(TreeNode node, Integer preVal) {
        if (node == null) {
            return true;
        }
        
        if (!dfs(node.left, node.val) | !dfs(node.right, node.val)) {
            return false;
        }
        
        count++;
        
        return Objects.equals(node.val, preVal);
    }
}
```