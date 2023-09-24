# [LeetCode_2596_检查骑士巡视方案](https://leetcode.cn/problems/check-knight-tour-configuration)
## 解法
### 思路
- 初始化骑士可以移动的8个方向，可以用一个二维数组`dirs`表示
- 遍历棋盘数组，将步数对应的横竖坐标记录在一个二维数组`steps`中
  - 坐标对应步数
  - 元素存储横轴和纵轴坐标
- 从坐标`[0,0]`开始，遍历`steps`，并将`[0,0]`作为初始的前置坐标
- 基于`pre`，遍历`dirs`，计算是否有步数可以匹配当前`steps[i]`，如果不能匹配就终止，否则继续
- 因为可能存在`steps[0]`就在默认的起始`[0,0]`位置，所以可以将`[0,0]`加入到`dirs`数组中，覆盖这种情况
### 代码
```java
class Solution {
  private int[][] dirs = {{0, 0}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
  public boolean checkValidGrid(int[][] grid) {
    int row = grid.length, col = grid[0].length, n = row * col;
    int[][] steps = new int[n][2];
    for (int r = 0; r < row; r++) {
      for (int c = 0; c < col; c++) {
        steps[grid[r][c]] = new int[]{r, c};
      }
    }

    int[] pre = {0, 0};
    for (int i = 0; i < n; i++) {
      int r = pre[0], c = pre[1],
              nr = steps[i][0], nc = steps[i][1];

      boolean reach = false;
      for (int[] dir : dirs) {
        if (r + dir[0] == nr && c + dir[1] == nc) {
          pre = steps[i];
          reach = true;
          break;
        }
      }

      if (!reach) {
        return false;
      }
    }

    return true;
  }
}
```
# [LeetCode_146_LRU缓存](https://leetcode.cn/problems/lru-cache/)
## 解法
### 思路
- 使用hash表和自实现的简易双向链表来实现LRU
  - 双向链表节点存储key，value值，以及前后指针
  - hash表存储key和双向链表节点
- LRU基于双向链表，通过实现删除节点、移动节点到头部，删除尾部节点，添加节点到头部这4个方法，实现基本能力
### 代码
```java
class LRUCache {
    private Map<Integer, DListNode> cache;
    private int capacity, size;
    private DListNode head, tail;

    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.size = 0;
        this.capacity = capacity;
        head = new DListNode();
        tail = new DListNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DListNode node = cache.get(key);
        if (node == null) {
            return -1;
        }

        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DListNode node = cache.get(key);
        if (node == null) {
            node = new DListNode(key, value);
            cache.put(key, node);
            addToHead(node);
            size++;
            
            if (size > capacity) {
                DListNode tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
            
            return;
        }
        
        node.value = value;
        moveToHead(node);
    }

    private void moveToHead(DListNode node) {
        remove(node);
        addToHead(node);
    }
    
    private DListNode removeTail() {
        DListNode tail = this.tail.prev;
        remove(tail);
        return tail;
    }

    private void remove(DListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(DListNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private class DListNode {
        private int key, value;
        private DListNode prev, next;

        public DListNode(){}
        public DListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
```