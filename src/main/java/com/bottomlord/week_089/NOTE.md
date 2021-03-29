# [LeetCode_497_非重叠矩形中的随机点](https://leetcode-cn.com/problems/random-point-in-non-overlapping-rectangles/)
## 解法
### 思路
- 获取所有再矩形内随机点个数的总和
- 在获取总和时，同时记录总和的与矩形关系之间形成的前缀和，也就是第n个矩形，与形成n个矩形的随机点个数的关系
- 通过随机函数获取总和中一个随机的值
- 然后通过二分法，找到随机的值在前缀和数组中对应的下标值
- 最后通过下标值找到矩形的左下角坐标，然后找到随机值对应的横坐标和纵坐标
### 代码
```java
class Solution {
    private Random random = new Random();
    private int totalNum, len;
    private int[] pointNumSums;
    private int[][] rects;

    public Solution(int[][] rects) {
        this.rects = rects;
        this.len = rects.length;
        this.pointNumSums = new int[len];
        int index = 0;
        for (int[] rect : rects) {
            int width = rect[2] - rect[0] + 1,
                height = rect[3] - rect[1] + 1;
            totalNum += width * height;
            pointNumSums[index++] = totalNum;
        }
    }

    public int[] pick() {
        int r = random.nextInt(totalNum);

        int head = 0, tail = len - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (r >= pointNumSums[mid]) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        int[] rect = rects[head];
        int width = rect[2] - rect[0] + 1,
            height = rect[3] - rect[1] + 1,
            base = pointNumSums[head] - width * height;

        return new int[]{rect[0] + (r - base) % width, rect[1] + (r - base) / width};
    }
}
```
# [LeetCode_498_对角线遍历](https://leetcode-cn.com/problems/diagonal-traverse/)
## 解法
### 思路
模拟
- 通过规律发现，对角线的横坐标和纵坐标的和等于一个固定值，且这个固定值根据对角线的位置有规律的变化
### 代码
```java
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return new int[0];
        }

        int col = matrix[0].length;
        if (col == 0) {
            return new int[0];
        }

        int[] ans = new int[row * col];
        recurse(matrix, ans, row, col, 0, 0, true, 0);
        return ans;
    }

    private void recurse(int[][] matrix, int[] ans, int row, int col, int r, int c, boolean flag, int index) {
        if (r == row || c == col) {
            return;
        }

        ans[index] = matrix[r][c];

        if (r == row - 1 && c == col - 1) {
            return;
        }

        if (flag) {
            if (r == 0) {
                if (c + 1 == col) {
                    recurse(matrix, ans, row, col, r + 1, c, false, index + 1);
                } else {
                    recurse(matrix, ans, row, col, r, c + 1, false, index + 1);
                }
            } else if (c == col - 1) {
                recurse(matrix, ans, row, col, r + 1, c, false, index + 1);
            } else {
                recurse(matrix, ans, row, col, r - 1, c + 1, true, index + 1);
            }
        } else {
            if (c == 0) {
                if (r + 1 == row) {
                    recurse(matrix, ans, row, col, r, c + 1, true, index + 1);
                } else {
                    recurse(matrix, ans, row, col, r + 1, c, true, index + 1);
                }
            } else if (r == row - 1) {
                recurse(matrix, ans, row, col, r, c + 1, true, index + 1);
            } else {
                recurse(matrix, ans, row, col, r + 1, c - 1, false, index + 1);
            }
        }
    }
}
```
# [LeetCode_499_迷宫III](https://leetcode-cn.com/problems/the-maze-iii/)
## 失败解法
### 原因
超时
### 思路
在迷宫I的解法基础上做了简单改造，也是深度搜索
### 代码
```java
class Solution {
    private int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private String[] dirStrs = {"l", "u", "r", "d"};
    private int min = Integer.MAX_VALUE;
    private String ans;

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int row = maze.length;
        if (row == 0) {
            return null;
        }

        int col = maze[0].length;
        if (col == 0) {
            return null;
        }

        for (int i = 0; i < dirs.length; i++) {
            int newX = ball[0] + dirs[i][0], newY = ball[1] + dirs[i][1];
            if (outOfBound(newX, newY, row, col) || maze[newX][newY] == 1) {
                continue;
            }

            backTrack(maze, ball[0], ball[1], row, col, hole, i, new HashSet<>(), 0,  new StringBuilder(dirStrs[i]));
        }

        return ans == null ? "impossible" : ans;
    }

    private void backTrack(int[][] maze, int x, int y, int row, int col, int[] destination, int dirIndex, Set<String> memo, int step, StringBuilder sb) {
        if (step > min) {
            return;
        }

        if (x == destination[0] && y == destination[1]) {
            if (min == step) {
                ans = compareAndGet(ans, sb.toString());
            } else {
                ans = sb.toString();
            }
            min = step;
            return;
        }

        int nextX = x + dirs[dirIndex][0], nextY = y + dirs[dirIndex][1];
        if (outOfBound(nextX, nextY, row, col) || maze[nextX][nextY] == 1) {
            for (int i = 0; i < dirs.length; i++) {
                int newX = x + dirs[i][0], newY = y + dirs[i][1];
                String memoKey = initKey(x, y, i);
                if (outOfBound(newX, newY, row, col) || maze[newX][newY] == 1 || memo.contains(memoKey)) {
                    continue;
                }

                memo.add(memoKey);
                int sbLen = sb.length();
                sb.append(dirStrs[i]);
                backTrack(maze, newX, newY, row, col, destination, i, memo, step + 1, sb);
                sb.setLength(sbLen);
                memo.remove(memoKey);
            }
        } else {
            backTrack(maze, nextX, nextY, row, col, destination, dirIndex, memo, step + 1, sb);
        }
    }

    private String compareAndGet(String a, String b) {
        if (a == null) {
            return b;
        }

        char[] as = a.toCharArray(), bs = b.toCharArray();
        int ai = 0, bi = 0;
        while (ai < as.length && bi < bs.length) {
            if (as[ai] < bs[bi]) {
                return a;
            } else if (as[ai] > bs[bi]) {
                return b;
            }

            ai++;
            bi++;
        }

        return a;
    }

    private boolean outOfBound(int x, int y, int row, int col) {
        return x < 0 || x >= row || y < 0 || y >= col;
    }

    private String initKey(int x, int y, int dir) {
        return x + "#" + y + "#" + dir;
    }
}
```
## 解法
### 思路
记忆化+bfs：
- 首先确定记忆化的定义，如果某个节点在经过某个点时已经路过的路径超过了当前点记录的最小值，说明当前这个路径不符合要求，无需再继续走下去
- bfs处理过程中的每个节点，定义的是球滚动过程中停下来时候的状态，包括：
    - 停止时的横坐标
    - 停止时的纵坐标
    - 已经路径的路程
    - 所经过路程所对应的字符串表达式
- 遍历过程中，就是基于当前获取的停止的节点状态，遍历4个方向，开始移动，边移动边记录移动的距离
- 移动的停止边界状态是
    - 要么越界
    - 要么碰壁
- 然后获取这个停止状态，更新便利的距离。
- 基于停止时的坐标，和记忆化内容的值进行比较
    - 如果比记忆化内容的值小，说明当前路径时可取的，更新记忆化状态，更新自身的表达式内容，放入队列进入下个判断循环
    - 如果和记忆化内容一样，说明当前路径可取，但无需更新记忆化状态，其他动作和小于的时候一致
    - 如果大于说明不可取，直接中断这个路径
- 同时如上比较过程中，还要考虑到达终点的情况，在可取的情况下，同时还到达终点了，那么外层4个方向的遍历过程就可以终止了，因为只可能有一个方法到达终点，而其他方向到达终点的路径一定长于当前
- 将终点状态放入treemap中，节点的状态表达式作为key
### 代码
```java
class Solution {
private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int row = maze.length, col = maze[0].length;
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        int[][] memo = new int[row][col];
        for (int[] arr : memo) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        Queue<Node> queue = new ArrayDeque<>();
        Node node = new Node(ball[0], ball[1], 0, "");
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur == null) {
                continue;
            }

            for (int i = 0; i < dirs.length; i++) {
                int x = cur.x, y = cur.y;
                int[] dir = dirs[i];
                int count = 0;
                int nextX = x + dir[0], nextY = y + dir[1];

                while (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && maze[nextX][nextY] != 1) {
                    nextX += dir[0];
                    nextY += dir[1];
                    count++;

                    if (nextX - dir[0] == hole[0] && nextY - dir[1] == hole[1]) {
                        break;
                    }
                }

                if (count == 0) {
                    continue;
                }

                count += cur.count;

                x = nextX - dir[0];
                y = nextY -  dir[1];
                Node newNode = new Node(x, y, count, cur.output + getOutput(i));
                if (count < memo[x][y]) {
                    memo[x][y] = count;
                    if (x == hole[0] && y == hole[1]) {
                        treeMap.clear();
                        treeMap.put(newNode.output, newNode.count);
                        break;
                    } else {
                        queue.offer(newNode);
                    }
                } else if (count == memo[x][y]) {
                    if (x == hole[0] && y == hole[1]) {
                        treeMap.put(newNode.output, newNode.count);
                        break;
                    } else {
                        queue.offer(newNode);
                    }
                }
            }
        }

        return treeMap.isEmpty() ? "impossible" : treeMap.firstKey();
    }

    private String getOutput(int index) {
        return index == 0 ? "r" : (index == 1 ? "l" : (index == 2 ? "d" : "u"));
    }

    static class Node {
        private final Integer x;
        private final Integer y;
        private final int count;
        private final String output;

        public Node(Integer x, Integer y, int count, String output) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.output = output;
        }
    }
}
```
# [LeetCode_82_删除排序链表中的重复元素II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/)
## 解法
### 思路
- 使用fake头，pre、node、next指针辅助操作
- 基于node指针遍历链表
- pre初始化为fake头
- 每一次遍历都初始化一个next指针
- 内层循环判断是否当前node和next的val相等，如果相等就移动next
- 内层循环结束后，判断next和node.next是否一致，不一致说明发生了移动，此时就通过pre之间next指向到新的next节点，跳过原来的重复节点，包括node
- 如果一一样就说明是不重复的两个相邻节点，就正常的遍历移动
### 代码
```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head, fake = new ListNode(0), pre = fake;
        fake.next = head;
        while (node != null && node.next != null) {
            ListNode next = node.next;
            while (next != null && node.val == next.val) {
                next = next.next;
            }
            
            if (node.next != next) {
                pre.next = next;
                node = next;
            } else {
                pre = node;
                node = node.next;
            }
        }
        return fake.next;
    }
}
```
# [LeetCode_83_删除排序链表中的重复元素](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/)
## 解法
### 思路
- 初始化一个用于遍历链表的指针node，遍历链表
- 遍历过程中，每次都初始化一个next指针对应node.next，然后通过next遍历链表，并判断是否与当前node指针对应的节点值一致，如果一致就向右移动
- 然后用node与可能移动过的next重新链接
- 直到循环结束
### 代码
```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            ListNode next = node.next;
            while (next != null && node.val == next.val) {
                next = next.next;
            }
            
            node.next = next;
            node = next;
        }
        return head;
    }
}
```
# [LeetCode_61_旋转链表](https://leetcode-cn.com/problems/rotate-list/)
## 解法
### 思路
- 头尾相连，计算链表长度，移动到长度-k的位置，断开
- k可能大于len，所以要特殊处理一下，`len = len - (k % len)`
### 代码
```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode node = head, pre = null;
        int len = 0;
        while (node != null) {
            len++;
            pre = node;
            node = node.next;
            
            if (node == null) {
                pre.next = head;
            }
        }
        
        if (pre == null) {
            return null;
        }
        
        len = len - k % len;
        node = pre.next;
        while (len-- > 0) {
            pre = node;
            node = node.next;
        }
        
        pre.next = null;
        
        return node;
    }
}
```