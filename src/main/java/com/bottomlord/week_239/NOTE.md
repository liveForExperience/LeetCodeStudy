# [LeetCode_LCP30_魔塔游戏](https://leetcode.cn/problems/p0NxJO)
## 解法
### 思路
- 思考过程：
  - 从头到尾遍历的话，累加遍历到的数字，就可以得到一个状态：现在是否已经累加到了负值
    - 如果是，就需要把已经遍历到的负值搬到数组最后，其实也就可以理解成去除掉，然后判断是否还是负值，循环往复
  - 如果要实现这个状态下的操作，就需要做2件事
    - 遍历和累加遍历到的元素
    - 将遍历到的负数存在一个集合中
  - 为了最小化搬移的次数，那么每次要往数组后面放的就应该是最小的那个负数，为了快速的获取到这个排序的负数列表，就可以用小顶堆来存储
- 算法过程：
  - 初始化变量`sum`，用于存储累加的生命值
  - 初始化变量`cur`，用于记录当前生命值的状态，如果该值小于零，就需要触发移动的逻辑
  - 初始化变量`cnt`，用于记录移动的次数
  - 初始化一个优先级队列`queue`，用于存储遍历到的负数
  - 遍历`nums`数组
    - 将遍历到的元素`num`累加到`sum`和`cur`上
    - 判断`num`是否是负数，如果是，就添加到`queue`中
    - 判断`cur`是否小于0，如果是
      - 就将`queue`顶部的元素弹出，并加强从`cur`中减去
      - 并累加`cnt`
  - 遍历结束后，判断`sum`是否小于0，如果是就返回-1，否则返回`cnt`
### 代码
```java
class Solution {
    public int magicTower(int[] nums) {
        long sum = 0, cur = 0;
        int cnt = 0;
        Queue<Long> queue = new PriorityQueue<>();
        for (int num : nums) {
            sum += num;
            cur += num;
            
            if (num < 0) {
                queue.offer((long)num);
            }

            if (cur < 0) {
                cnt++;
                cur -= queue.poll();
            }
        }
        
        return sum < 0 ? -1 : cnt;
    }
}
```
# [LeetCode_2641_二叉树的堂兄弟节点II](https://leetcode.cn/problems/cousins-in-binary-tree-ii)
## 解法
### 思路
- 思考过程：
  - 根据题目定义的堂兄弟节点的特征
    - 深度相同：深度相同的节点可以通过bfs来获取
    - 父节点不同：父节点不同可以通过bfs时传递父节点引用来判断
  - 基于如上的思考，用bfs，然后基于父节点进行group by，可以有效的计算出需要修改的值
- 算法过程：
  - 初始化bfs使用的队列`queue`，队列元素为一个节点数组，长度为2，分别存储父节点和当前节点
  - 通过bfs，将每一层的所有元素都获取出来
    - 将所有当前深度的节点放入一个`list`中，元素为`TreeNode[]`，数组中分别存储父节点和当前节点。用于在遍历完当前层之后，通过遍历这个`list`来处理修改`value`值的逻辑
    - 将所有节点的总和进行计算汇总，得到`sum`
    - 将所有节点基于父节点进行分组，每个节点都修改为`sum - curGroupValue`，`curGroupValue`是当前分组的节点的`value`和
      - 分组使用一个`key`为`TreeNode`，`value`为`Integer`的map，`key`存储父节点，`value`存储以`key`为父节点的节点的`value`和
  - 当前层的元素都取出后，遍历`list`，取出元素后，基于父节点从`map`中获取到`value`，再通过`sum - value`修改到当前节点中
  - bfs结束后返回根节点即可
### 代码
```java
class Solution {
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode[]> queue = new ArrayDeque<>();
        queue.offer(new TreeNode[]{root, root});
        while (!queue.isEmpty()) {
            int count = queue.size(), sum = 0;
            List<TreeNode[]> list = new ArrayList<>();
            Map<TreeNode, Integer> map = new HashMap<>();
            while (count-- > 0) {
                TreeNode[] item = queue.poll();

                if (item == null) {
                    continue;
                }
                
                TreeNode parentItem = item[0], curItem = item[1];

                list.add(item);
                int curValue = curItem.val;
                map.put(parentItem, map.getOrDefault(parentItem, 0) + curValue);
                sum += curValue;
                
                if (curItem.left != null) {
                    queue.offer(new TreeNode[]{curItem, curItem.left});
                }
                
                if (curItem.right != null) {
                    queue.offer(new TreeNode[]{curItem, curItem.right});
                }
            }

            for (TreeNode[] item : list) {
                item[1].val = sum - map.get(item[0]);
            }
        }

        return root;
    }
}
```
## 解法二
### 思路
- 解法一在bfs过程中使用了list和map两个数据结构来实现
  - 分组
  - 统计同深度节点
- 参考其他解法后发现，前一层实际是可以获取到下一层的所有节点的`value`之和`sum`
- 然后每一层的相同父节点的元素，实际就是要通过`sum - groupValue`来修改，而这个`groupValue`实际也可以在前一层获取，而参考其他解法后，可以直接将当前节点的左右子节点的值设置为左右节点值的和
- 通过如上的方式，就不需要再使用一开始提到的`list`和`map`2种数据结构了，且只需要在正常bfs过程中就可以完成这些操作
- 最后节点值的修改，可以在上一层统计完下一层的`sum`，遍历当前层元素的时候，对当前层的元素进行修改，修改的方式就是用`sum`减去上一层已经在当前节点上设置好的那个和即可
### 代码
```java
class Solution {
    public TreeNode replaceValueInTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        int sum = root.val;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size(), nextSum = 0;
            while (count-- > 0) {
                TreeNode cur = queue.poll();

                if (cur == null) {
                    continue;
                }

                cur.val = sum - cur.val;
                TreeNode left = cur.left, right = cur.right;
                int lv = left == null ? 0 : left.val, rv = right == null ? 0 : right.val;
                
                if (left != null) {
                    nextSum += left.val;
                    queue.offer(left);
                    left.val = lv + rv;
                }
                
                if (cur.right != null) {
                    nextSum += right.val;
                    queue.offer(right);
                    right.val = lv + rv;
                }
            }
            
            sum = nextSum;
        }
        
        return root;
    }
}
```
# [LeetCode_1696_跳跃游戏](https://leetcode.cn/problems/jump-game-vi)
## 解法
### 思路
- 思考过程：
  - 使用动态规划
  - `dp[i]`代表到达i位置的和的最大值
  - 状态转移方程：`dp[i] = max(dp[j]) + nums[i], j ∈ (j - k, j - 1)`
      - `j`代表从`i - k`到`i - 1`的所有位置
      - `nums[i]`代表当前位置的值
  - 初始化：`dp[0] = nums[0]`
  - 遍历方式：坐标1开始从前往后
  - 这样的时间复杂度是`O(n*k)`，但因为题目的参数较大，所以时间还是过长
  - 思考后发现，k次的遍历其实是为了找到区间中值最大的坐标，而这个坐标可以通过堆来实现
    - 堆存储的是坐标
    - 堆的比较规则是坐标对应的`dp`值，且是大顶堆
  - 但因为k是动态的窗口区间，所以每次取值的时候，都需要判断一下存储的坐标是否属于窗口区间
- 算法过程
  - 初始化一个`dp`数组，`dp[0] = nums[0]`
  - 初始化一个大顶堆`queue`，存储坐标，比较规则是以坐标对应的`dp`值为比较元素的大顶堆
  - 将坐标`0`存储入`queue`
  - 初始化变量`n = nums.length`
  - 从1开始遍历`n`次
    - 取出`queue`的堆顶元素，判断是否小于`i - k`
      - 如果是，将元素弹出，继续取堆顶元素，循环往复
      - 如果不是，就将`nums[i]`与堆顶元素相加，作为`dp[i]`的值
    - 将坐标存储到`queue`
  -  遍历结束，返回`dp[n - 1]`作为结果即可
### 代码
```java
class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Queue<Integer> queue = new PriorityQueue<>((x, y) -> dp[y] - dp[x]);
        dp[0] = nums[0];
        queue.offer(0);

        for (int i = 1; i < n; i++) {
            while (!queue.isEmpty() && queue.peek() < i - k) {
                queue.poll();
            }

            if (queue.isEmpty()) {
                break;
            }

            int index = queue.peek();
            dp[i] = nums[i] + dp[index];

            queue.offer(i);
        }

        return dp[n - 1];
    }
}
```