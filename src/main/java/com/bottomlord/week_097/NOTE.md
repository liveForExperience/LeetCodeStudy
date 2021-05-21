# [LeetCode_993_二叉树的堂兄弟节点](https://leetcode-cn.com/problems/cousins-in-binary-tree/)
## 解法
### 思路
dfs
- 分别对两个值做dfs搜索，找到后记录父节点和深度
- 最终比较记录的结果
### 代码
```java
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        Note noteX = new Note(), noteY = new Note();
        dfs(null, root, x, 0, noteX);
        dfs(null, root, y, 0, noteY);
        return noteX.parent != noteY.parent && noteX.level == noteY.level; 
    }
    
    private void dfs(TreeNode parent, TreeNode node, int target, int level, Note note) {
        if (node == null) {
            return;
        }
        
        if (node.val == target) {
            note.level = level;
            note.parent = parent;
            return;
        }
        
        dfs(node, node.left, target, level + 1, note);
        dfs(node, node.right, target, level + 1, note);
    }
    
    class Note {
        private TreeNode parent;
        private int level;
    }
}
```
## 解法二
### 思路
分别dfs计算2个节点的深度，和是不是兄弟节点，然后做判断
### 代码
```java
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        return deep(root, x) == deep(root, y) && !isBrother(root, x, y); 
    }
    
    private int deep(TreeNode node, int target) {
        if (node == null) {
            return -99999;
        }
        
        if (node.val == target) {
            return 0;
        }
        
        return Math.max(deep(node.left, target), deep(node.right, target)) + 1;
    }
    
    private boolean isBrother(TreeNode node, int x, int y) {
        if (node == null) {
            return false;
        }
        
        if (node.left != null && node.right != null) {
            if ((node.left.val == x && node.right.val == y) || (node.right.val == x && node.left.val == y)) {
                return true;
            }
        }
        
        return isBrother(node.left, x, y) || isBrother(node.right, x, y);
    }
}
```
# [LeetCode_1442_形成两个异或相等数组的三元组数目](https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/)
## 解法
### 思路
- 模仿前缀和求出累计异或的值，存储在数组xor中
- 实际要求出的两个子数组的异或和，通过运算可以获得公式：
    - `xor[i] = arr[0] ^ arr[1] ^ ... arr[i]`
    - `xor[j] = arr[0] ^ arr[1] ^ ... arr[i] ^ arr[i + 1] ^ ... arr[j]`
    - `arr[i] ^ ... arr[j - 1] = xor[i - 1] ^ xor[j - 1]`
    - `arr[j] ^ ... arr[k] = xor[j - 1] ^ xor[k]`
    - 为了达成左右元素相等，等式可以表达为：`xor[i - 1] ^ xor[j - 1] = xor[j - 1] ^ xor[k]`
    - 通过计算就可以获得`xor[i - 1] = xor[k]`
- 3层循环，分别确定i，j，k
- 然后判断`xor[i - 1] == xor[k]`
- 为了方便计算，让xor数组整体右移一位，所以判断的公式可以为`xor[j] == xor[k + 1]`
### 代码
```java
class Solution {
    public int countTriplets(int[] arr) {
        int[] xor = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            xor[i + 1] = xor[i] ^ arr[i];
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j; k < arr.length; k++) {
                    if (xor[i] == xor[k + 1]) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
```
## 解法二
### 思路
- 实际并不需要使用到j，所以可以将解法1的3重循环降低为2重
- 但计算个数的时候需要注意，因为i和k + 1符合要求了，所以中间任意的j都是ok的，那么个数就应该是k-i
### 代码
```java
class Solution {
    public int countTriplets(int[] arr) {
        int len = arr.length;
        int[] xor = new int[len + 1];
        for (int i = 0; i < arr.length; i++) {
            xor[i + 1] = xor[i] ^ arr[i];
        }
        
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int k = i + 1; k < len; k++) {
                if (xor[i] == xor[k + 1]) {
                    count += k - i;   
                }
            }
        }
        
        return count;
    }
}
```
# [LeetCode_1738_找出第K大的异或坐标值](https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value/)
## 解法
### 思路
- 初始化一个容量为k的大顶堆
- 先求出每一行的异或前缀和数组
- 再合并求出真正的二维数组的合并前缀和
- 在第二步的时候也同时将求出的前缀和放入大顶堆
- 遍历大顶堆，求出第k大的元素
### 代码
```java
class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int[][] xorMatrix = new int[row + 1][col + 1];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                xorMatrix[i][j + 1] = xorMatrix[i][j] ^ matrix[i][j];
            }
        }

        Arrays.stream(xorMatrix[0]).forEach(queue::offer);

        for (int i = 1; i <= col; i++) {
            for (int j = 1; j < row; j++) {
                xorMatrix[j][i] ^= xorMatrix[j - 1][i];
                queue.offer(xorMatrix[j][i]);
            }
        }

        int index = 0, ans = -1;
        while (index != k) {
            ans = queue.poll();
            index++;
        }

        return ans;
    }
}
```
## 解法二
### 思路
使用数组的快排，取代大顶堆获取第K大的元素
### 代码
```java
class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;

        if (row == 1 && col > 1) {
            for (int i = 1; i < col; i++) {
                matrix[0][i] ^= matrix[0][i - 1];
            }
        } else if (row > 1 && col == 1) {
            for (int i = 1; i < row; i++) {
                matrix[i][0] ^= matrix[i - 1][0];
            }
        } else {
            for (int i = 0; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    matrix[i][j] ^= matrix[i][j - 1];
                }
            }

            for (int i = 0; i < col; i++) {
                for (int j = 1; j < row; j++) {
                    matrix[j][i] ^= matrix[j - 1][i];
                }
            }
        }

        int[] arr = new int[row * col];
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[index++] = matrix[i][j];
            }
        }

        Arrays.sort(arr);
        return arr[arr.length - k];
    }
}
```
# [LeetCode_692_前K个高频单词](https://leetcode-cn.com/problems/top-k-frequent-words/)
## 解法
### 思路
map+优先级队列
### 代码
```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((o1, o2) -> {
            if (!Objects.equals(o1.getValue(), o2.getValue())) {
                return o2.getValue() - o1.getValue();
            }
            
            return o1.getKey().compareTo(o2.getKey());
        });
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }
        
        int index = 0;
        List<String> ans = new ArrayList<>();
        while (index != k && !queue.isEmpty()) {
            ans.add(queue.poll().getKey());
            index++;
        }
        
        return ans;  
    }
}
```
## 解法二
### 思路
- 自己用数组模拟一个hash表keys，通过开放地址法解决hash冲突，在计算hash的时候，需要注意将计算出来的hash值的符号位都置成0，否则会导致hash值为负，无法映射到数组下标
- 同时基于keys对应元素的下标，维护同一个values数组，用于保存出现的个数
- 然后再用一个优先级队列数组counts，该数组的下标对应元素出现的个数，故values中的最大值 + 1就是这个数组的长度，然后将所有元素按照出现个数放入counts中
- 最后从后向前遍历counts数组，将前K个最大的元素放入结果列表中
- 遍历结束返回
### 代码
```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        int len = words.length;
        String[] keys = new String[len];
        int[] values = new int[len];

        for (String word : words) {
            int hash = (word.hashCode() & 0x7FFFFFFF) % len;
            while (!Objects.equals(keys[hash], word) && values[hash] > 0) {
                hash = (hash + 1) % len;
            }

            keys[hash] = word;
            values[hash]++;
        }

        PriorityQueue<String>[] counts = new PriorityQueue[len + 1];
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            if (value > 0) {
                if (counts[value] == null) {
                    counts[value] = new PriorityQueue<>();
                }
                counts[value].add(keys[i]);
            }
        }

        int count = 0;
        List<String> ans = new ArrayList<>();
        for (int i = counts.length - 1; i >= 0; i--) {
            if (counts[i] == null) {
                continue;
            }

            PriorityQueue<String> queue = counts[i];
            while (count != k && !queue.isEmpty()) {
                ans.add(queue.poll());
                count++;
            }
        }

        return ans;
    }
}
```
# [LeetCode_1056_易混淆数](https://leetcode-cn.com/problems/confusing-number/)
## 解法
### 思路
- 列出10个数字的180度反转的映射
- 如果是反转后不能成为正确数字的，映射值就是-1
- 然后对N进行10进制逐位的反转，从低位开始
- 判断反转后的映射值，如果是-1，就直接返回false
- 否则就设置成相对的高位
### 代码
```java
class Solution {
    public boolean confusingNumber(int N) {
        int[] reverses = new int[]{0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        int o = N;
        int n = 0;
        while (N != 0) {
            int num = N % 10;
            if (reverses[num] == -1) {
                return false;
            }
            N /= 10;
            n = n * 10 + reverses[num];
        }

        return n != o;
    }
}
```