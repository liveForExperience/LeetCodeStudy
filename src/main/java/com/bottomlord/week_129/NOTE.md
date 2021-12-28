# [LeetCode_28_实现strStr()](https://leetcode-cn.com/problems/implement-strstr/)
## 解法
### 思路
kmp：

### 代码
```java

```
# [LeetCode_825_适龄的朋友](https://leetcode-cn.com/problems/friends-of-appropriate-ages/)
## 失败解法
### 原因
超时
### 思路
嵌套循环
### 代码
```java
class Solution {
    public int numFriendRequests(int[] ages) {
        int n = ages.length, count = 0;
        for (int i = 0; i < n; i++) {
            int ageA = ages[i];
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                
                if (ages[j] <= ageA * 0.5 + 7 ||
                ages[j] > ages[i] ||
                ages[j] > 100 && ages[i] < 100) {
                    continue;
                }

                count++;
            }
        }
        
        return count;
    }
}
```
## 解法二
### 思路
排序+双指针
- 先对数组进行排序
- 初始化三个指针
  - i对应有效对象值的下界
  - j对应有效对象值的上界
  - k对应发送者
- 遍历k，直到k数组越界
- 内层分别确定i和j，确定的方式就使用题目要求的条件
- 确定好上下界后，求差值就是当前k可以发送的人数，但因为这个范围中包含k自身，所以还需要减去1
- 因为数组是有序的，所以每次遍历k都无需重置i和j，从上一个k确定后的位置继续判断即可
- 注意：下界i一定不能超过坐标k
### 代码
```java
class Solution {
public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int ans = 0, n = ages.length;
        for (int i = 0, j = 0, k = 0; k < n; k++) {
            while (i < k && !check(ages[k], ages[i])) {
                i++;
            }
            if (j < k) {
                j = k;
            }

            while (j < n && check(ages[k], ages[j])) {
                j++;
            }

            if (j > i) {
                ans += j - i - 1;
            }
        }

        return ans;
    }

    private boolean check(int x, int y) {
        return (!(y <= 0.5 * x + 7)) && (y <= x);
    }
}
```
## 解法三
### 思路

### 代码
```java

```
# [LeetCode_472_连接词](https://leetcode-cn.com/problems/concatenated-words/)
## 解法
### 思路
字典树+dfs
- 对字符串数组排序，短字符串优先
- 遍历字符串
- 先dfs搜索字典树，如果当前字符串依次都能在字典树中找到，那么就放入结果中
- 此处不需要将放入结果中的字符串放到字典树中，因为结果中的字符串是由多个已有的字符串组成的，所以由它组成的其他字符串也可以由组成它的字符串组成
- 如果dfs搜索结果判断这个字符串不能由线程的字典树字符串组成，就把它放到字典树中
- 遍历结束以后，返回结果字符串数组
### 代码
```java
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Tire tire = new Tire();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (Objects.equals("", word)) {
                continue;
            }

            if (dfs(tire.root, 0, word)) {
                ans.add(word);
            } else {
                tire.insert(word);
            }
        }
        return ans;
    }

    private boolean dfs(TireNode root, int index, String word) {
        if (index == word.length()) {
            return true;
        }

        TireNode node = root;
        while (index < word.length()) {
            node = node.children[word.charAt(index) - 'a'];
            if (node == null) {
                return false;
            }

            if (node.isWord && dfs(root, index + 1, word)) {
                return true;
            }

            index++;
        }

        return false;
    }

    private class Tire {
        private TireNode root;

        public Tire() {
            this.root = new TireNode();
        }

        public void insert(String word) {
            TireNode node = root;
            char[] cs = word.toCharArray();
            for (char c : cs) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TireNode();
                }
                
                node = node.children[c - 'a'];
            }

            node.isWord = true;
        }
    }

    private class TireNode {
        private char c;
        private boolean isWord;
        private TireNode[] children;

        public TireNode() {
            this.children = new TireNode[26];
        }
    }
}
```