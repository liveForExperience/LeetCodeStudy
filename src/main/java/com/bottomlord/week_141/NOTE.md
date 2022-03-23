# [LeetCode_653_两数之和IV-输入BST](https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/)
## 解法
### 思路
dfs
- dfs中序遍历生成升序序列
- 双指针寻找target
- 找到就返回true，否则返回false
### 代码
```java
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        int head = 0, tail = list.size() - 1;
        while (head < tail) {
            int sum = list.get(head) + list.get(tail);
            if (sum > k) {
                tail--;
            } else if (sum < k) {
                head++;
            } else {
                return true;
            }
        }

        return false;
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
## 解法二
### 思路
dfs
- 2层dfs
  - 第一层用于确定第一个加数
  - 第二层从根节点开始寻找k - val(第一层的node值)是否存在，如果存在就返回true
- 需要注意，因为是bst，所以不存在val * 2 = target的情况，所以需要把这种情况剔除掉
### 代码
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, k, root);
    }

    private boolean dfs(TreeNode node, int target, TreeNode root) {
        if (node == null) {
            return false;
        }
        
        int val = node.val;
        if (val * 2 != target && search(root, target - val)) {
            return true;
        }
        
        return dfs(node.left, target, root) || dfs(node.right, target, root); 
    }
    
    private boolean search(TreeNode node, int target) {
        if (node == null) {
            return false;
        }
        
        int val = node.val;
        if (val == target) {
            return true;
        }
        
        return val < target ? search(node.right, target) : search(node.left, target);
    }
}
```
# [LeetCode_625_最小因式分解](https://leetcode-cn.com/problems/minimum-factorization/)
## 解法
### 思路
模拟
- 注意溢出，需要先用long声明进行计算，最后和int的极值进行比较后再判断是返回0还是强转为int
### 代码
```java
class Solution {
  public int smallestFactorization(int num) {
    long ans = 0, index = 0, ten = 1;
    while (num >= 10) {
      long cur = 0;
      for (int i = 9; i > 1; i--) {
        if (num % i == 0) {
          cur = i;
          break;
        }
      }

      if (cur == 0) {
        return 0;
      }

      ten *= index == 0 ? 1 : 10;
      ans += index == 0 ? cur : ten * cur;
      index++;
      num /= cur;
    }

    ans += index == 0 ? num : ten * 10 * num;

    return ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE ? 0 : (int) ans;
  }
}
```
# [LeetCode_2038_如果相邻两个颜色均相同则删除当前颜色](https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/)
## 解法
### 思路
模拟
- 分析题目可以发现，A和B的抽取数量与另一方没有关系，所以只需要计算出连续的字符长度以及连续字符的个数就可知道每一方可以抽取的数量
- 抽取的数量 = 连续长度 - 2
- 最后比较A的抽取数量是否大于B即可
### 代码
```java
class Solution {
    public boolean winnerOfGame(String colors) {
        char[] cs = colors.toCharArray();
        int aNum = 0, bNum = 0;
        for (int i = 0; i < cs.length;) {
            char c = cs[i];
            int count = 0;
            while (i < cs.length && c == cs[i]) {
                count++;
                i++;
            }
            
            if (c == 'A') {
                aNum += Math.max(count - 2, 0);
            } else {
                bNum += Math.max(count - 2, 0);
            }
        }
        
        return aNum > bNum;
    }
}
```
# [LeetCode_440_字典序的第K小数字](https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/)
## 解法
### 思路
- 字典序：可以理解为`1, 10, 100, 11, 110, 111`这样的顺序
- 观察顺序可以发现
  - 一开始是不断*10来生成新的一个数
  - 一直到无法再*10，否则就要超过n的那个数出现，例如n=187，那么就是1，10，100
  - 然后顺序就是，`1,10,100,101,102,103,...109,110,111,112,113...120,121...186,187,19`
  - 那么首先可以归纳出以1为开头的数字，就一共有1+10+88，一共99个
  - 其次可以看到
    - 如果k是大于99的，那么我不用管这99个数字的顺序，我直接k-=99，然后从2开始计算就可以了
    - 如果k是小于99的，那么如果我是从1开始计算的，我就要*10，也就是从10，这第二个数字开始计算
    - 也就是说，我通过从1开始计算，确定了这第k个数字一定是在以1为起始的99个数字里，也就是1是确定的了
    - 那我就看看第二个数字，10开头的值包不包含
      - 如果包含，那么就继续推100这第三个数
      - 如果不包含，那么说明不是10开头，那看看是不是11开头的，然后继续推
- 通过总结出的规律，那么整个算法就可以被拆分出两个部分
  - 一个部分是用来确定从哪个开头开始计算
  - 一个部分是用来确定这个开头可以生成多少有效的数字
- 然后根据生成的数字看看是不是囊括了第k个数
  - 不囊括就增大前缀开头数
  - 囊括就扩展前缀数的位数或者说缩小前缀生成数的范围
### 代码
```java
class Solution {
    public int findKthNumber(int n, int k) {
        long cur = 1;
        k--;
        while (k > 0) {
            long step = step(cur, cur + 1, n);

            if (step > k) {
                cur *= 10;
                k--;
            } else {
                cur++;
                k -= step;
            }
        }

        return (int)cur;
    }

    private long step(long cur, long next, int n) {
        long step = 0;
        while (cur <= n) {
            step += Math.min(n + 1, next) - cur;
            
            cur *= 10;
            next *= 10;
        }
        
        return step;
    }
}
```