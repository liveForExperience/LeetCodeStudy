# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_731_我的日程安排表II](https://leetcode.cn/problems/my-calendar-ii/)
## 解法
### 思路
- 初始化两个列表
  - 存储第一次出现的区间
  - 存储有重叠1次的区间
- 判断是否有交叉的逻辑：
  - l：待比较区间左侧
  - r：待比较区间右侧
  - start：区间左侧
  - end：区间右侧
  - start < r && end > l 
### 代码
```java
class MyCalendarTwo {

    private List<int[]> once, twice;
    
    public MyCalendarTwo() {
        this.once = new ArrayList<>();
        this.twice = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] two : twice) {
            int l = two[0], r = two[1];
            if (start < r && end > l) {
                return false;
            }
        }

        for (int[] one : once) {
            int l = one[0], r = one[1];
            if (start < r && end > l) {
                twice.add(new int[]{Math.max(l, start), Math.min(r, end)});
            }
        }
        
        once.add(new int[]{start, end});
        return true;
    }
}
```
## 解法二
### 思路
差分
- 使用TreeMap统计差分
### 代码
```java
class MyCalendarTwo {

  private Map<Integer, Integer> map;
  public MyCalendarTwo() {
    this.map = new TreeMap<>();
  }

  public boolean book(int start, int end) {
    map.put(start, map.getOrDefault(start, 0) + 1);
    map.put(end, map.getOrDefault(end, 0) - 1);
    int num = 0;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      num += entry.getValue();

      if (num > 2) {
        map.put(start, map.getOrDefault(start, 0) - 1);
        map.put(end, map.getOrDefault(end, 0) + 1);
        return false;
      }
    }

    return true;
  }
}
```
## 解法三
### 思路
线段树
### 代码
```java
class MyCalendarTwo {
    private SegmentTree segmentTree;
    private Node root;

    public MyCalendarTwo() {
        segmentTree = new SegmentTree();
        root = new Node();
    }

    public boolean book(int start, int end) {
        if (segmentTree.query(root, 0, (int)1e9, start, end - 1) == 2) {
            return false;
        }
        segmentTree.update(root, 0, (int)1e9, start, end - 1, 1);
        return true;
    }

    class SegmentTree {

        public void update(Node node, int start, int end, int l, int r, int val) {
            if (start >= l && end <= r) {
                node.val += val;
                node.add += val;
                return;
            }

            int mid = (start + end) >> 1;
            pushDown(node);

            if (l <= mid) {
                update(node.left, start, mid, l, r, val);
            }

            if (r > mid) {
                update(node.right, mid + 1, end, l, r, val);
            }

            pushUp(node);
        }

        private void pushDown(Node node) {
            if (node.left == null) {
                node.left = new Node();
            }

            if (node.right == null) {
                node.right = new Node();
            }

            if (node.add == 0) {
                return;
            }

            node.left.val += node.add;
            node.right.val += node.add;
            node.left.add += node.add;
            node.right.add += node.add;
            node.add = 0;
        }

        public int query(Node node, int start, int end, int l, int r) {
            if (l <= start && r >= end) {
                return node.val;
            }
            pushDown(node);
            int mid = (start + end) >> 1, ans = 0;
            if (l <= mid) {
                ans = query(node.left, start, mid, l, r);
            }

            if (r > mid) {
                ans = Math.max(ans, query(node.right, mid + 1, end, l, r));
            }

            return ans;
        }

        private void pushUp(Node node) {
            node.val = Math.max(node.left.val, node.right.val);
        }
    }

    class Node {
          private Node left, right;
          private int val, add;
      }
}
```