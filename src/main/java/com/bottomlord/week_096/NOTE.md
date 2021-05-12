# [LeetCode_872_叶子相似的树](https://leetcode-cn.com/problems/leaf-similar-trees/)
## 解法
### 思路
- 分别dfs两棵树，搜索收集从左到右的叶子
- 然后比较搜集到的集合是否相等
### 代码
```java
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (!Objects.equals(list1.get(i), list2.get(i))) {
                return false;
            }
        }
        
        return true;
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        if (node.left == null && node.right == null) {
            list.add(node.val);
        }
        
        dfs(node.left, list);
        dfs(node.right, list);
    }
}
```
# [LeetCode_760_找出变位映射](https://leetcode-cn.com/problems/find-anagram-mappings/)
## 解法
### 思路
2层循环找到对应坐标，存储到结果数组中
### 代码
```java
class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    ans[i] = j;
                }
            }
        }
        return ans;
    }
}
```
## 解法二
### 思路
- 将B数组的值与坐标生成对应的映射表
- 遍历A数组，根据映射表找到B的坐标并放入结果数组
### 代码
```java
class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            mapping.put(B[i], i);
        }

        for (int i = 0; i < A.length; i++) {
            A[i] = mapping.get(A[i]);
        }
        return A;
    }
}
```
# [LeetCode_1734_解码异或后的排序](https://leetcode-cn.com/problemset/all/)
## 解法
### 思路
- 按照题目的描述，原数组是[1,n]这n个元素组成的，那么用total可以表示为他们累计异或的值
- 根据题目意思，n是奇数，那么n-1就是偶数，且encoded的所有元素都是依次相邻元素的异或值，因此encoded[1] ^ encoded[3] ^ ... encoded[n - 2]，就是除原数组第一个元素外所有元素相异或的值
- 将这两部分的值再相异或，就能得到第一个元素，然后依次遍历encoded数组，就能还原出原始数组了  
### 代码
```java
class Solution {
    public int[] decode(int[] encoded) {
                int n = encoded.length + 1;
        int[] ans = new int[n];

        int total = 0;
        for (int i = 1; i <= n; i++) {
            total ^= i;
        }

        int xor = 0;
        for (int i = 1; i < n - 1; i += 2) {
            xor ^= encoded[i];
        }
        
        int first = total ^ xor;

        ans[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            ans[i + 1] = ans[i] ^ encoded[i];
        }

        return ans;
    }
}
```
# [LeetCode_LCP29_乐团站位](https://leetcode-cn.com/problems/SNJvJP/)
## 失败解法
### 原因
栈溢出
### 思路
- 初始化二维数组
- 模拟填充二维数组
- 当遇到题目要求的坐标时返回
### 代码
```java
class Solution {
    private int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int orchestraLayout(int num, int xPos, int yPos) {
        int[][] matrix = new int[num][num];
        for (int[] row : matrix) {
            Arrays.fill(row, 0);
        }
        recurse(matrix, num, 0, 0, xPos, yPos, 0, 0);
        return matrix[xPos][yPos];
    }

    private void recurse(int[][] matrix, int num, int x, int y, int px, int py, int index, int count) {
        matrix[x][y] = count % 9 + 1;
        
        if (px == x && py == y) {
            return;
        }
        
        int nextX = x + directions[index][0], nextY = y + directions[index][1];
        if (nextX >= num || nextX < 0 || nextY >= num || nextY < 0 || matrix[nextX][nextY] != 0) {
            index = (index + 1) % 4;
            nextX = x + directions[index][0];
            nextY = y + directions[index][1];
        }
        
        recurse(matrix, num, nextX, nextY, px, py, index, count + 1);
    }
}
```
## 解法
### 思路
- 根据入参坐标确定在往内循环的第几层，方法是找到4个边与坐标距离最小的值
- 根据层数，确定到达该层前一层时共累计了多少数值，然后根据坐标确定其在最后一圈累计的个数
- 对最终的个数进行取模就获得最后的答案
### 代码
```java

```
# [LeetCode_1310_子数组异或查询](https://leetcode-cn.com/problems/xor-queries-of-a-subarray/)
## 解法
### 思路
和前缀和类似
### 代码
```java
class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int len = arr.length, ansLen = queries.length;
        int[] xors = new int[len];
        xors[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            xors[i] = xors[i - 1] ^ arr[i];
        }

        int[] ans = new int[ansLen];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0], end = queries[i][1];
            ans[i] = (start == 0 ? 0 : xors[start - 1]) ^ xors[end];
        }

        return ans;
    }
}
```