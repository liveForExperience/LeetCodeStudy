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