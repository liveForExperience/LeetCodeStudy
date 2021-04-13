# [LeetCode_517_超级洗衣机](https://leetcode-cn.com/problems/super-washing-machines/)
## 解法
### 思路
- 先计算出平均值，如果不能整除，直接返回-1
- 然后计算每个洗衣机是要流出还是要流入衣服，通过遍历元素减去平均值获得这个差值
- 得到差值的元素数组后，先初始化将元素头尾的值做一个比较，判断这两个元素的最大值作为可能的做小操作数
- 然后再遍历差值元素数组，遍历这个数组的时候，计算的逻辑是：
    - 累加左边所有的差值，如果是正数，说明左边的衣服会流入到右边
    - 如果是负数，就说明右边的衣服会流入到左边
    - 然后以当前这个元素左边标准，就能得到一种操作数的可能值，这个可能值就是左边或右边的流入数，与当前差值元素中的最大值、
    - 然后将所有可能值枚举后，获得其中的最大值
### 代码
```java
class Solution {
    public int findMinMoves(int[] machines) {
        int len = machines.length, sum = Arrays.stream(machines).sum();
        if (sum % len != 0) {
            return -1;
        }

        int avg = sum / len;
        for (int i = 0; i < len; i++) {
            machines[i] = machines[i] - avg;
        }

        int ans = 0, leftSum = 0;
        for (int machine : machines) {
            leftSum += machine;
            int cur = Math.max(Math.abs(leftSum), machine);
            ans = Math.max(ans, cur);
        }

        return ans;
    }
}
```
# [LeetCode_783_二叉搜索树节点最小距离](https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/)
## 解法
### 思路
- 中序搜索获取升序序列
- 遍历升序序列找到最小差值
### 代码
```java
class Solution {
    public int minDiffInBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++) {
            min = Math.min(min, list.get(i) - list.get(i - 1));
        }
        return min;
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    } 
}
```
## 解法
### 思路
不使用额外的空间，直接在搜索的过程中记录最小距离
### 代码
```java
class Solution {
    public int minDiffInBST(TreeNode root) {
        Result result = new Result(-1, Integer.MAX_VALUE);
        dfs(root, result);
        return result.min;
    }
    
    private void dfs(TreeNode node, Result result) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, result);
        if (result.init) {
            result.init = false;
        } else {
            result.min = Math.min(result.min, node.val - result.pre);
        }
        result.pre = node.val;
        dfs(node.right, result);
    }
    
    static class Result {
        private boolean init;
        private int pre;
        private int min;
        public Result(int pre, int min) {
            this.pre = pre;
            this.min = min;
            this.init = true;
        }
    } 
}
```
# [LeetCode_519_随机翻转矩阵](https://leetcode-cn.com/problems/random-flip-matrix/)
## 解法
### 思路
- 二维变一维
- 随机函数获取随机值，随机值范围是一维数组长度
- set记录翻转过的坐标，重复尝试
### 代码
```java
class Solution {
    private int len;
    private int row;
    private int col;
    private Set<Integer> memo;
    private Random random;

    public Solution(int n_rows, int n_cols) {
        this.len = n_cols * n_rows;
        this.row = n_rows;
        this.col = n_cols;
        this.memo = new HashSet<>();
        this.random = new Random();
    }

    public int[] flip() {
        int r = random.nextInt(len);
        while (memo.contains(r)) {
            r = random.nextInt(len);
        }

        memo.add(r);
        return new int[]{r / col, r % col};
    }

    public void reset() {
        this.memo.clear();
    }
}
```
## 解法二
### 思路
- 解法一为了避免重复会多次调用随机函数
- 为了解决这问题，就需要使求随机数的时候能够避免找到已经使用坐标
- 但又因为坐标使随机的，在使用过一个随机数后，能够使用的坐标数是线性减少的，但这个值是随机，这样就无法直接使用设置bound的方式通过random函数求到随机数，而需要中间做一次映射
- 整个过程可以理解成：
    - 初始化一个随机数和坐标的映射关系map
    - 通过可以使用的坐标个数作为随机数的边界，这个边界会在求解过程中不断-1，求得随机数r
    - 用r在map中查找对应的坐标值，获得的坐标值有2种情况：
        - r值本身，说明这个随机数代表的坐标值没有被翻转过，这是第一次翻转
        - 当前边界+1的值，也就是随机数取不到的值，这个值说明与当前随机数值相等的坐标已经被翻转过，它在上一次翻转后，被赋予了当前这个边界+1的数值
    - 将map中以当前随机值为key的映射所对应的value设置为当前的可用坐标个数，也就是下次随机的边界+1
    - 同时返回获得的坐标所对应的x和y轴的值
### 代码
```java
class Solution {
    private int num;
    private int row;
    private int col;
    private Map<Integer, Integer> map;
    private Random random;
    public Solution(int n_rows, int n_cols) {
        this.num = n_cols * n_rows;
        this.row = n_rows;
        this.col = n_cols;
        this.map = new HashMap<>();
        this.random = new Random();
    }

    public int[] flip() {
        int r = random.nextInt(num--);
        int index = map.getOrDefault(r, r);
        map.put(r, map.getOrDefault(num, num));
        return new int[]{index / col, index % col};
    }

    public void reset() {
        this.map.clear();
        this.num = col * row;
    }
}

```