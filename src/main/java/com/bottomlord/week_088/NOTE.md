# [LeetCode_488_祖玛游戏](https://leetcode-cn.com/problems/zuma-game/)
## 解法
### 思路
回溯
- 暂存手中有的球与球数量的映射
- 暂存一个全局变量result，用来暂存回溯搜索过程中比较获得的次数最小值
- 回溯：
    - 参数：
        - 字符串的StringBuilder
        - 当前处理次数step
    - 过程：
        - 退出条件：sb长度为0，代表所有元素处理完
        - 剪枝：当前处理次数step大于暂存的最小次数，直接淘汰
        - 核心：
            - 外层循环，指针i定义要处理的起始字符
            - 内层定义另一个指针j，初始化为i，然后判断之后与i字符相同的连续字符个数
                - 如果字符只有1个，且手上的对应求数大于等于2，则代表这个颜色可以处理，就将该状态备份后，处理这个字符串可能需要自动消除的字符，然后step+1，继续递归和回溯
                - 如果字符有2个：
                    - 考虑直接插相同颜色的球，使之消除，进而也进入迭代自动消除的函数
                    - 考虑将不同的颜色插入到2个相同颜色中，然后直接递归搜索
- 递归结束后，返回全局变量，返回时判断值是否是初始值，如果是就是返回-1，否则就是直接返回
### 代码
```java
class Solution {
    private int ans;
    private int[] bucket;

    public int findMinStep(String board, String hand) {
        ans = Integer.MAX_VALUE;
        bucket = new int[26];
        for (int i = 0; i < hand.length(); i++) {
            bucket[hand.charAt(i) - 'A']++;
        }

        backTrack(new StringBuilder(board), 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void backTrack(StringBuilder sb, int step) {
        if (step >= ans) {
            return;
        }

        if (sb.length() == 0) {
            ans = step;
            return;
        }

        for (int i = 0; i < sb.length(); i++) {
            int j = i;
            while (j + 1 < sb.length() && sb.charAt(j) == sb.charAt(j + 1)) {
                j++;
            }

            char c = sb.charAt(i);
            if (i == j && bucket[c - 'A'] >= 2) {
                StringBuilder cur = new StringBuilder(sb);
                cur.insert(i, c);
                eliminate(cur);
                bucket[c - 'A']--;
                backTrack(cur, step + 1);
                bucket[c - 'A']++;
            } else if (j - i == 1) {
                if (bucket[c - 'A'] >= 1) {
                    StringBuilder cur = new StringBuilder(sb);
                    cur.insert(i, c);
                    eliminate(cur);
                    bucket[c - 'A']--;
                    backTrack(cur, step + 1);
                    bucket[c - 'A']++;
                    continue;
                }

                for (int k = 0; k < bucket.length; k++) {
                    if (bucket[k] == 0 || k == (c - 'A')) {
                        continue;
                    }

                    StringBuilder cur = new StringBuilder(sb);
                    cur.insert(i + 1, (char) (k + 'A'));
                    bucket[k]--;
                    backTrack(cur, step + 1);
                    bucket[k]++;
                }
            }
        }
    }

    private void eliminate(StringBuilder sb) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < sb.length(); i++) {
                int j = i;
                while (j + 1 < sb.length() && sb.charAt(j) == sb.charAt(j + 1)) {
                    j++;
                }

                if (j - i >= 2) {
                    flag = true;
                    sb.delete(i, j + 1);
                }
            }
        }
    }
}
```
# [LeetCode_489_扫地机器人](https://leetcode-cn.com/problems/robot-room-cleaner/)
## 解法
### 思路
回溯：
- 初始化机器人的位置为`0,0`，朝向为0
- 定义机器人上下左右4个方向
- 机器人清扫过的位置也当作障碍，不再清扫
- 清扫过程：
    - 判断当前放下的下一个各自是否有障碍，如果没有，继续清扫
    - 如果有障碍，则右转，变换朝向
    - 如果四个方向都清扫完毕（已清扫回溯，或无法清扫有障碍），则回溯到上一格
- 在清扫的过程中，为了做到不再清扫已经清扫过的路径，就需要做记忆化搜索，将路径记录下来，可以用set记录，元素为横坐标和纵坐标加特殊符号的拼接
### 代码
```java
class Solution {
    private final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public void cleanRoom(Robot robot) {
        backTrack(robot, 0, 0, 0, new HashSet<>());
    }

    private void backTrack(Robot robot, int r, int c, int d, Set<String> memo) {
        memo.add(getDir(r, c));
        robot.clean();

        for (int i = 0; i < 4; i++) {
            int newD = (d + i) % 4;
            int newR = r + directions[newD][0], newC = c + directions[newD][1];
            String newDir = getDir(newR, newC);

            if (!memo.contains(newDir) && robot.move()) {
                backTrack(robot, newR, newC, newD, memo);
                back(robot);
            }

            robot.turnRight();
        }
    }

    public void back(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    private String getDir(int x, int y) {
        return x + "#" + y;
    }
}
```
# [LeetCode_490_迷宫](https://leetcode-cn.com/problems/the-maze/)
## 解法
### 思路
记忆化+dfs
### 代码
```java
class Solution {
    private final int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    boolean result = false;
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int row = maze.length;
        if (row == 0) {
            return false;
        }

        int col = maze[0].length;
        if (col == 0) {
            return false;
        }

        Set<String> memo = new HashSet<>();
        for (int i = 0; i < dirs.length; i++) {
            memo.add(initKey(start[0], start[1], i));
            backTrack(maze, start[0], start[1], row, col, destination, i, memo);
        }
        return result;
    }

    private void backTrack(int[][] maze, int x, int y, int row, int col, int[] destination, int dirIndex, Set<String> memo) {
        if (result) {
            return;
        }

        int nextX = x + dirs[dirIndex][0], nextY = y + dirs[dirIndex][1];
        if (x == destination[0] && y == destination[1] && (outOfBound(nextX, nextY, row, col) || maze[nextX][nextY] == 1)) {
            result = true;
            return;
        }

        if (outOfBound(nextX, nextY, row, col) || maze[nextX][nextY] == 1) {
            for (int i = 1; i < dirs.length; i++) {
                int newDirIndex = (dirIndex + i) % dirs.length, newX = x + dirs[newDirIndex][0], newY = y + dirs[newDirIndex][1];
                if (outOfBound(newX, newY, row, col) || maze[newX][newY] == 1 || memo.contains(initKey(x, y, newDirIndex))) {
                    continue;
                }
                memo.add(initKey(x, y, newDirIndex));

                backTrack(maze, newX, newY, row, col, destination, newDirIndex, memo);
                if (result) {
                    return;
                }
            }
        } else {
            backTrack(maze, nextX, nextY, row, col, destination, dirIndex, memo);
        }
    }

    private boolean outOfBound(int x, int y, int row, int col) {
        return x < 0 || x >= row || y < 0 || y >= col;
    }

    private String initKey(int x, int y, int dir) {
        return x + "#" + y + "#" + dir;
    }
}
```
# [LeetCode_92_反转链表](https://leetcode-cn.com/problems/reverse-linked-list-ii/)
## 解法
### 思路
- 初始化一个假头，方便编程
- 遍历链表，过程中记录前置指针，前置指针初始化时指向假头
- 先找到需要反转的链表区间起始节点
- 找到后记录需要反转的区间的前置节点
- 然后开始反转，并在最后一个节点时停下
- 将前置节点原来的next指针指向的节点的next，指向当前停下节点的next
- 然后将前置节点的next指向停下的节点
- 然后继续遍历，直到遍历结束
### 代码
```java
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode node = head, fakeHead = new ListNode(0), pre = fakeHead, beforeStart;
        fakeHead.next = head;
        int index = 1;
        while (node != null) {
            if (index == left) {
                beforeStart = pre;
                ListNode start = node, afterEnd = null;
                while (index <= right) {
                    ListNode next = node.next;
                    node.next = pre;
                    pre = node;
                    if (index == right) {
                        afterEnd = next;
                    } else {
                        node = next;
                    }

                    index++;
                }


                beforeStart.next = node;
                start.next = afterEnd;

                node = afterEnd;
            } else {
                pre = node;
                node = node.next;
                index++;
            }
        }

        return fakeHead.next;
    }
}
```