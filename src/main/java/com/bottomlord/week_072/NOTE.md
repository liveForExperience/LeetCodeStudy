# [LeetCode_353_贪吃蛇](https://leetcode-cn.com/problems/design-snake-game/)
## 解法
### 思路
定义一个node类模拟贪吃蛇的身体单元，模拟移动的过程
### 代码
```java
class SnakeGame {
    private Node head, tail;
    private int width, height, score, index;
    private Map<String, int[]> directionMap;
    private int[][] food;
    public SnakeGame(int width, int height, int[][] food) {
        this.head = new Node(0, 0);
        this.tail = head;
        this.width = width;
        this.height = height;
        this.score = 0;
        this.directionMap = new HashMap<>();
        this.directionMap.put("U", new int[]{-1, 0});
        this.directionMap.put("D", new int[]{1, 0});
        this.directionMap.put("L", new int[]{0, -1});
        this.directionMap.put("R", new int[]{0, 1});
        this.food = food;
        this.index = 0;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] foodIndex = index >= food.length ? new int[]{-1,-1} : food[index];
        int[] move = directionMap.get(direction);

        int newX = head.x + move[0], newY = head.y + move[1];
        if (newX < 0 || newX >= height || newY < 0 || newY >= width) {
            return -1;
        }

        boolean remainTail = foodIndex[0] == newX && foodIndex[1] == newY;

        Node cur = remainTail ? tail : tail.next;
        while (cur != null) {
            if (cur.x == newX && cur.y == newY) {
                return -1;
            }

            cur = cur.next;
        }

        Node newHead = new Node(head.x + move[0], head.y + move[1]);
        head.next = newHead;
        head = newHead;

        if (remainTail) {
            score++;
            index++;
        } else {
            Node tailNext = tail.next;
            tail.next = null;
            tail = tailNext;
        }

        return score;
    }

    private class Node {
        private int x;
        private int y;
        private Node next;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
```