# [LeetCode_1905_统计子岛屿](https://leetcode.cn/problems/count-sub-islands/)
## 解法
### 思路
bfs
### 代码
```java
class Solution {
  private int[][] grid1, grid2;
  private int r, c;
  private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public int countSubIslands(int[][] grid1, int[][] grid2) {
    this.grid1 = grid1;
    this.grid2 = grid2;
    this.r = grid1.length;
    this.c = grid1[0].length;
    int count = 0;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (grid2[i][j] == 1) {
          count += bfs(i, j) ? 1 : 0;
        }
      }
    }

    return count;
  }

  private boolean bfs(int x, int y) {
    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{x, y});
    grid2[x][y] = 0;
    boolean flag = grid1[x][y] == 1;
    while (!queue.isEmpty()) {
      int[] arr = queue.poll();
      if (arr == null) {
        continue;
      }

      int a = arr[0], b = arr[1];

      for (int[] dir : dirs) {
        int na = a + dir[0], nb = b + dir[1];
        if (na >= 0 && na < r && nb >= 0 && nb < c && grid2[na][nb] == 1) {
          queue.offer(new int[]{na, nb});
          grid2[na][nb] = 0;

          if (grid1[na][nb] != 1) {
            flag = false;
          }

        }
      }
    }

    return flag;
  }
}
```
# [LeetCode_732_我的日程安排III](https://leetcode.cn/problems/my-calendar-iii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_2283_判断一个数的数字技术是否等于数位的值](https://leetcode.cn/problems/check-if-number-has-equal-digit-count-and-digit-value/)
## 解法
### 思路
- hash表计数
  - 记录数位应该有的个数
  - 记录数位在字符串中实际出现的个数
- 遍历字符串，根据数位比较两个记录值是否相等，如果不相等就返回false
- 遍历结束，返回true
### 代码
```java
class Solution {
    public boolean digitCount(String num) {
        int[] nums = new int[10], targets = new int[10];
        char[] cs = num.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            targets[i] = cs[i] - '0';
            nums[cs[i] - '0']++;
        }

        for (int i = 0; i < cs.length; i++) {
            if (targets[i] != nums[i]) {
                return false;
            }
        }

        return true;
    }
}
```
# [LeetCode_6095_强密码检验器II](https://leetcode.cn/problems/strong-password-checker-ii/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public boolean strongPasswordCheckerII(String password) {
        boolean hasLower = false, hasUpper = false, hasNum = false, hasSpecial = false;
        int n = password.length();
        if (n < 8) {
            return false;
        }
        
        Set<Character> specials = new HashSet<>();
        String specialStr = "!@#$%^&*()-+";
        char[] cs = specialStr.toCharArray();

        for (char c : cs) {
            specials.add(c);
        }
        
        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                if (password.charAt(i) == password.charAt(i + 1)) {
                    return false;
                }
            }
            
            char c = password.charAt(i);
            
            if (Character.isDigit(c)) {
                hasNum = true;
            }
            
            if (Character.isLowerCase(c)) {
                hasLower = true;
            }
            
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }
            
            if (specials.contains(c)) {
                hasSpecial = true;
            }
        }
        
        return hasNum && hasUpper && hasLower && hasSpecial;
    }
}
```
# [LeetCode_719_找出第K小的数对距离](https://leetcode.cn/problems/find-k-th-smallest-pair-distance/)
## 解法
### 思路
二分查找
- 排序数组
- 2层二分查找
  - 外层确定距离值
  - 内层查找距离值小于等于外层距离值的个数
### 代码
```java
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0, r = nums[n - 1] - nums[0];

        while (l <= r) {
            int mid = (r - l) / 2 + l;

            int count = 0;
            for (int i = 0; i < n; i++) {
                int index = binarySearch(nums, i, nums[i] - mid);
                count += i - index;
            }

            if (count >= k) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private int binarySearch(int[] nums, int end, int target) {
        int l = 0, r = end;

        while (l < r) {
            int mid = l + (r - l) / 2;

            int num = nums[mid];
            if (num >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}
```
## 解法二
### 思路
二分查找+双指针
### 代码
```java
class Solution {
public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, l = 0, r = nums[n - 1] - nums[0];

        while (l <= r) {
            int mid = l + (r - l) / 2, count = 0;
            for (int end = 0, start = 0; end < n; end++) {
                while (nums[end] - nums[start] > mid) {
                     start++;
                }
                
                count += end - start;
            }
            
            if (count >= k) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return l;
    }
}
```
# [LeetCode_offerII029_排序的环形链表](https://leetcode.cn/problems/4ueAj6/https://leetcode.cn/problems/4ueAj6/)
## 解法
### 思路
- 没有节点：直接插入
- 只有1个节点，插入到节点后面
- 超过1个节点，遍历节点，用head记录链表的头节点
- 使用两个指针，cur和next，分别指向当前节点，和当前节点的next指针指向的节点
- 假设新增的节点为node，那么在如下情境下需要新增：
  - cur > next，并且node >= cur && node >= next
  - cur > next，并且node <= cur && node <= next
  - node >= cur && node <= next
- 如果遍历到next==head，说明链表里没有可以插入的位置，也就是说，所有的节点都是相等的，那么就可以直接在head后面添加，因为这样是符合题目要求的
### 代码

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
  public Node insert(Node head, int insertVal) {
    Node node = new Node(insertVal);
    if (head == null) {
      head = node;
      head.next = head;
      return head;
    }

    Node cur = head, next = cur.next;
    if (next == null) {
      cur.next = node;
      node.next = cur;
      return head;
    }

    while (next != head) {
      int curVal = cur.val, nextVal = next.val;

      if (curVal > nextVal) {
        if (insertVal >= curVal) {
          cur.next = node;
          node.next = next;
          return head;
        } else if (insertVal <= nextVal) {
          cur.next = node;
          node.next = next;
          return head;
        }
      } else if (insertVal >= curVal && insertVal <= nextVal) {
        cur.next = node;
        node.next = next;
        return head;
      }

      cur = next;
      next = cur.next;
    }

    cur.next = node;
    node.next = next;

    return head;
  }
}
```