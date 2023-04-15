# [LeetCode_1019_链表中的下一个更大节点](https://leetcode.cn/problems/next-greater-node-in-linked-list/)
## 解法
### 思路
单调栈
- 遍历链表，生成数组
- 从右往左遍历，这样当到达某个坐标元素点的时候，它右边的元素都已经被遍历检查过一次了
- 考虑这些右侧的元素，对于当前坐标元素是否都有意义呢？其实不是的
- 如果当前元素比右侧元素大，那么这些小的元素是没有意义的
- 而更大的元素是有意义的，意义在于防止左侧还有更大的元素，那么就需要借助右侧更大的元素来做比较和记录
- 于是就可以在从右向左遍历的过程中，通过维护一个单调递增的栈来处理这道题的需求
- 具体过程
  - 遍历到当前坐标，与栈顶元素作比较，将所有不比当前元素大的元素弹出
  - 如果栈不为空，记录栈顶元素作为当前坐标元素对应的值
  - 如果栈为空，则记录0
### 代码
```java
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        int n = list.size();
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int num = list.get(i);
            
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            
            ans[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(num);
        }
        
        return ans;
    }
}
```
# [LeetCode_2614_对角线上的质数](https://leetcode.cn/problems/prime-in-diagonal/)
## 解法
### 思路
模拟，复习一下质数的判断逻辑
### 代码
```java
class Solution {
    public int diagonalPrime(int[][] nums) {
        int max = 0;

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && n - i - 1 != j) {
                    continue;
                }
                
                int num = nums[i][j];
                if (isPrime(num)) {
                    max = Math.max(max, num);
                }
            }
        }

        return max;
    }

    private boolean isPrime(int num) {
        if (num == 2) {
            return true;
        }

        if (num < 2 || num % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
```
# [LeetCode_1042_不邻接植花](https://leetcode.cn/problems/flower-planting-with-no-adjacent/)
## 解法
### 思路
领接表
- 声明一个List<List<Integer>>结构的动态列表
- 遍历paths生成领接表
- 初始化一个ans数组，长度为n
- 遍历n个元素，依次根据当前坐标领接的花的值，确定当前ans坐标的值
- 具体就是每遍历一个坐标，就初始化一个有4个元素的set，使用set是为了能够利用其hash特性快速删除领接中的花的值
- 等去除了领接的花，在剩余的花中选择第一个作为当前坐标的花即可
- 遍历结束，返回ans数组
### 代码
```java
class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int x = path[0] - 1, y = path[1] - 1;
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 0);
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            set.add(1);
            set.add(2);
            set.add(3);
            set.add(4);

            List<Integer> list = graph.get(i);
            for (Integer index : list) {
                set.remove(ans[index]);
            }
            
            ans[i] = set.iterator().next();
        }
        
        return ans;
    }
}
```