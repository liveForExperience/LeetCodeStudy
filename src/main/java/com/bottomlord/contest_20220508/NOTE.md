# [Contest_1_字符串中最大的3位相同数字](https://leetcode-cn.com/problems/largest-3-same-digit-number-in-string/)
## 解法
### 思路
- 初始化3位相同数字的字符串列表，从999开始倒序排列
- 遍历字符串数组，利用String的contains方法查找num中是否存在当前字符串
- 如果存在就返回当前字符串
- 遍历结束，说明没有找到，就返回空字符串
### 代码
```java
class Solution {
    public String largestGoodInteger(String num) {
        String[] strs = new String[]{
                "999", "888",
                "777","666",
                "555","444",
                "333","222",
                "111","000"
        };

        for (String str : strs) {
            if (num.contains(str)) {
                return str;
            }
        }

        return "";
    }
}
```
# [Contest_2_统计值等于子树平均值的节点数](https://leetcode-cn.com/problems/count-nodes-equal-to-average-of-subtree/)
## 解法
### 思路
- dfs求出总和以及个数，然后计算平均值，通过类变量记录结果
- dfs结束后返回变量结果
### 代码
```java
class Solution {
    private int ans = 0;
    public int averageOfSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(node.left), right = dfs(node.right);
        int sum = left[0] + right[0] + node.val, count = left[1] + right[1] + 1,
            avg = sum / count;

        if (node.val == avg) {
            ans++;
        }

        return new int[]{sum, count};
    }
}
```
# [Contest_3_统计打字方案数](https://leetcode-cn.com/problems/count-number-of-texts/)
## 解法
### 思路
- 通过观察可以发现，方案数就是连续相同字符子串的可能数的乘积
- 将问题拆分成2部分
  - 拆分出子串
  - 计算出字符串的可能数
- 第一个问题通过遍历字符串就可以得到
- 第二个问题，通过记忆化回溯的方式可以求得
- 需要注意值溢出的问题，所以可以用long来承接，然后再通过取模控制值的大小
### 代码
```java
class Solution {
  public int countTexts(String pressedKeys) {
    int len = pressedKeys.length();
    if (len <= 1) {
      return len;
    }

    Map<Character, Integer> board = new HashMap<>();
    board.put('2', 3);
    board.put('3', 3);
    board.put('4', 3);
    board.put('5', 3);
    board.put('6', 3);
    board.put('8', 3);
    board.put('7', 4);
    board.put('9', 4);

    Map<String, Integer> map = new HashMap<>();
    List<String> list = new ArrayList<>();
    char[] cs = pressedKeys.toCharArray();

    StringBuilder sb = new StringBuilder().append(cs[0]);
    for (int i = 1; i < cs.length; i++) {
      if (cs[i] == cs[i - 1]) {
        sb.append(cs[i]);
      } else {
        list.add(sb.toString());
        sb = new StringBuilder().append(cs[i]);
      }
    }
    list.add(sb.toString());

    long ans = 1;
    for (String s : list) {
      ans = (ans * count(s.length(), board.get(s.charAt(0)), new HashMap<>())) % 1000000007;
    }

    return (int) ans;
  }

  private static long count(int n, int len, Map<Integer, Long> memo) {
    if (n == 0) {
      return 1;
    }

    if (n < 0) {
      return 0;
    }

    long count = 0;
    for (int i = 1; i <= len; i++) {
      int left = n - i;
      if (memo.containsKey(left)) {
        count += memo.get(left);
      } else {
        long num = count(left, len, memo);
        memo.put(left, num);
        count += num;
      }

      count %= 1000000007;
    }

    return count;
  }    
}
```
# [Contest_4_检查是否有合法括号字符串路径](https://leetcode-cn.com/problems/check-if-there-is-a-valid-parentheses-string-path/)
## 失败解法
### 原因
超时
### 思路
暴力回溯
### 代码
```java
class Solution {
  public boolean hasValidPath(char[][] grid) {
    return backTrack(grid, 0, 0, grid.length, grid[0].length, 0);
  }

  private boolean backTrack(char[][] grid, int x, int y, int r, int c, int count) {
    if (x < 0 || x >= r || y < 0 || y >= c) {
      return false;
    }

    int num = count + (grid[x][y] == '(' ? 1 : -1);

    if (num < 0) {
      return false;
    }

    if (x == r - 1 && y == c - 1 && num == 0) {
      return true;
    }

    return backTrack(grid, x + 1, y, r, c, num) || backTrack(grid, x, y + 1, r, c, num);
  }
}
```
## 解法
### 思路
动态规划：
- 因为遍历的方式只能是向右和向下，所以可以通过状态转移方程来获得新的格子想要的内容
- 需要思考每个格子存储的内容：
  - 如果将左括号记作1，右括号记作-1，那么一个正确的括号组合的总和一定是0，但这是充分被必要的
  - 还有一个约束条件是，任何时候都不能出现右括号比左括号多的情况，也就是总和不能是负数
  - 然后因为每个格子都可能从很多路径可能到达，所以每个格子的括号值总和应该是有多个的，所以每个格子要存的就是所有之前路径的括号值总和
  - 可以用set来存储，方便去重和判断
- 过程中，状态转移的方式就是用当前括号对应的值，与上面或左边的格子的set内元素的值进行相加，然后汇总得到新的总和
- 因为负数值是不允许的，所以应该将负数去除掉
- 然后双重循环遍历这个grid二维数组，最终就能够得到右下角的set集合
- 判断最终的set集合中是否存在0即可
### 代码
```java
class Solution {
    public boolean hasValidPath(char[][] grid) {
        int row = grid.length, col = grid[0].length;
        if (grid[0][0] == ')' || grid[row - 1][col - 1] == '(') {
            return false;
        }
        
        Set<Integer>[][] matrix = new HashSet[row][col];
        matrix[0][0] = new HashSet<>();
        matrix[0][0].add(1);
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                
                int num = grid[i][j] == '(' ? 1 : -1;
                Set<Integer> cur = new HashSet<>();
                if (i != 0) {
                    Set<Integer> set = matrix[i - 1][j];
                    for (Integer n : set) {
                        if (n + num >= 0) {
                            cur.add(n + num);
                        }
                    }
                }
                
                if (j != 0) {
                    Set<Integer> set = matrix[i][j - 1];
                    for (Integer n : set) {
                        if (n + num >= 0) {
                            cur.add(n + num);
                        }
                    }
                }
                
                matrix[i][j] = cur;
            }
        }
        
        return matrix[row - 1][col - 1].contains(0);
    }
}
```