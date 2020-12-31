# LeetCode_407_接雨水II
## 解法
### 思路
- 在三维中接雨水，需要考虑当前区块周围一圈的高度，只要一圈中有一个是低于当前区块高度的，就有可能导致无法接住水。
- 那么在考虑怎么求解的时候，就可以从当前矩阵的最外层开始往里收口，不断的从最外层高度最矮的那个区块去找其四周可以接水的位置，如果有，就将结果累加
- 为什么能累加的原因是，首先能够接水，说明接水区块比目前判断的所有区块中最矮的区块更矮，而正在考虑的区块至少是最外层的所有元素，而且是不断内收的，所以如果比这个最矮的区块还矮，那么一定能够接住水
- 接水的数量就是最矮的区块与当前可以接水区块高度的差
### 代码
```java
class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        int row = heightMap.length, col = heightMap[0].length;
        boolean[][] visited = new boolean[row][col];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    queue.offer(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }

        int[] dirs = {1, 0, -1, 0, 1};
        int sum = 0;
        while (!queue.isEmpty()) {
            int[] shortArr = queue.poll();
            int shortR = shortArr[0], shortC = shortArr[1], shortHeight = shortArr[2];

            for (int i = 0; i < 4; i++) {
                int r = shortR + dirs[i], c = shortC + dirs[i + 1];

                if (r < 0 || r >= row || c < 0 || c >= col || visited[r][c]) {
                    continue;
                }

                int height = heightMap[r][c];
                if (height < shortHeight) {
                    sum += shortHeight - height;
                }

                queue.offer(new int[]{r, c, Math.max(height, shortHeight)});
                visited[r][c] = true;
             }
        }

        return sum;
    }
}
```
# [LeetCode_408_有效单词缩写](https://leetcode-cn.com/problems/valid-word-abbreviation/)
## 解法
### 思路
- 同时遍历两个字符串的字符
- 如果字符相等，则同时向后移动
- 如果缩写字符为数字，则对应移动原字符串的字符个数，同时缩写字符移动至非数字字符
- 如果某一个字符串搜索完毕，而另一个没有搜索完毕，则不是缩写
- 如果数字的字符长度大于1，则不能以0开头
- 不能是0
### 代码
```java
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int wIndex = 0, aIndex = 0, wLen = word.length(), aLen = abbr.length();
        while (wIndex < wLen && aIndex < aLen) {
            if (word.charAt(wIndex) == abbr.charAt(aIndex)) {
                wIndex++;
                aIndex++;
                continue;
            }
            
            if (!Character.isDigit(abbr.charAt(aIndex))) {
                return false;
            }
            
            StringBuilder numSb = new StringBuilder();
            while (aIndex < aLen && Character.isDigit(abbr.charAt(aIndex))) {
                numSb.append(abbr.charAt(aIndex++));
            }
            
            if (numSb.charAt(0) == '0') {
                return false;
            }
            
            int num = Integer.parseInt(numSb.toString());
            wIndex += num;
        }
        
        return wIndex == wLen && aIndex == aLen;
    }
}
```
# [LeetCode_411_最短特异单词缩写](https://leetcode-cn.com/problems/minimum-unique-word-abbreviation/)
## 解法
### 思路
将[320](https://leetcode-cn.com/problems/generalized-abbreviation/submissions/)和[408](https://leetcode-cn.com/problems/valid-word-abbreviation/)结合起来就可以解决
### 代码
```java
class Solution {
    public String minAbbreviation(String target, String[] dictionary) {
        List<String> list = new ArrayList<>();
        backTrack(target, 0, 0, new StringBuilder(), list);
        list.sort(Comparator.comparingInt(String::length));
        for (String abbr : list) {
            boolean isAbbr = false;
            for (String word : dictionary) {
                if (isAbbr(word, abbr)) {
                    isAbbr = true;
                    break;
                }
            }
            
            if (!isAbbr) {
                return abbr;
            }
        }
        
        return target;
    }
    
    private void backTrack(String word, int index, int num, StringBuilder sb, List<String> list) {
        int len = sb.length();
        
        if (index == word.length()) {
            if (num != 0) {
                sb.append(num);
            }
            list.add(sb.toString());
        } else {
            backTrack(word, index + 1, num + 1, sb, list);
            
            if (num != 0) {
                sb.append(num);
            }
            
            sb.append(word.charAt(index));
            backTrack(word, index + 1, 0, sb, list);
        }
        
        sb.setLength(len);
    }
    
    private boolean isAbbr(String word, String abbr) {
        int wIndex = 0, aIndex = 0, wLen = word.length(), aLen = abbr.length();
        while (wIndex < wLen && aIndex < aLen) {
            if (word.charAt(wIndex) == abbr.charAt(aIndex)) {
                wIndex++;
                aIndex++;
                continue;
            }

            if (!Character.isDigit(abbr.charAt(aIndex))) {
                return false;
            }

            StringBuilder numSb = new StringBuilder();
            while (aIndex < aLen && Character.isDigit(abbr.charAt(aIndex))) {
                numSb.append(abbr.charAt(aIndex++));
            }

            if (numSb.charAt(0) == '0') {
                return false;
            }

            int num = Integer.parseInt(numSb.toString());
            wIndex += num;
        }

        return wIndex == wLen && aIndex == aLen;
    }
}
```
# [LeetCode_417_太平洋大西洋水流问题](https://leetcode-cn.com/problems/pacific-atlantic-water-flow/)
## 解法
### 思路
2个dfs+记忆化搜索
### 代码
```java
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        int row = matrix.length;
        if (row == 0) {
            return ans;
        }

        int col = matrix[0].length;
        if (col == 0) {
            return ans;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int a = bfs(matrix, null, i, j, row, col, -1, -1, new boolean[row][col]);
                int b = bfs(matrix, null, i, j, row, col, row, col, new boolean[row][col]);
                if (a + b == 2) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }

        return ans;
    }

    private int bfs(int[][] matrix, Integer pre, int r, int c, int row, int col, int targetR, int targetC, boolean[][] visited) {
        if (r == targetR || c == targetC) {
            return 1;
        }

        if (r < 0 || r >= row || c < 0 || c >= col || visited[r][c]) {
            return 0;
        }

        if (pre != null && matrix[r][c] > pre) {
            return 0;
        }
        visited[r][c] = true;

        int down = bfs(matrix, matrix[r][c], r + 1, c, row, col, targetR, targetC, visited),
            top = bfs(matrix, matrix[r][c], r - 1, c, row, col, targetR, targetC, visited),
            right = bfs(matrix, matrix[r][c], r, c + 1, row, col, targetR, targetC, visited),
            left = bfs(matrix, matrix[r][c], r, c - 1, row, col, targetR, targetC, visited);

        if (down + top + right + left >= 1) {
            return 1;
        }

        return 0;
    }
}
```
## 解法二
### 思路
- 解法一是遍历所有点，判断是否能到达海洋
- 当前解法反过来，从海洋边开始出发，判断对应坐标是否能到达海洋
### 代码
```java
class Solution {
    int row, col;
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }

        row = matrix.length;
        col = matrix[0].length;

        int[][] pac = new int[row][col], atl = new int[row][col];
        for (int i = 0; i < row; i++) {
            dfs(matrix, i, 0, pac);
            dfs(matrix, i, col - 1, atl);
        }

        for (int i = 0; i < col; i++) {
            dfs(matrix, 0, i, pac);
            dfs(matrix, row - 1, i, atl);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pac[i][j] == 1 && atl[i][j] == 1) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] matrix, int r, int c, int[][] memo) {
        memo[r][c] = 1;

        for (int[] dir : dirs) {
            int newR = r + dir[0], newC = c + dir[1];
            if (newR < 0 || newR >= row || newC < 0 || newC >= col || memo[newR][newC] == 1 || matrix[r][c] > matrix[newR][newC]) {
                continue;
            }
            
            dfs(matrix, newR, newC, memo);
        }
    }
}
```