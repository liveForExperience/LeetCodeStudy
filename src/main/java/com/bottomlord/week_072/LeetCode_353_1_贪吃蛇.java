package com.bottomlord.week_072;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/11/23 8:27
 */
public class LeetCode_353_1_贪吃蛇 {
    class SnakeGame {
        private Node head, tail;
        private int width, height, score, index;
        private Map<String, int[]> directionMap;
        private int[][] food;
        public SnakeGame(int width, int height, int[][] food) {
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            this.width = width;
            this.height = height;
            this.score = 0;
            this.directionMap = new HashMap<>();
            this.directionMap.put("U", new int[]{1, 0});
            this.directionMap.put("D", new int[]{-1, 0});
            this.directionMap.put("L", new int[]{0, -1});
            this.directionMap.put("R", new int[]{1, 1});
            this.food = food;
            this.index = 0;
        }

        /** Moves the snake.
         @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         @return The game's score after the move. Return -1 if game over.
         Game over when snake crosses the screen boundary or bites its body. */
        public int move(String direction) {
            int[] foodIndex = food[index];
            int[] move = directionMap.get(direction);

            int newX = head.x + move[0], newY = head.y + move[1];
            if (newX < 0 || newX >= height || newY < 0 || newY >= width) {
                return -1;
            }

            Node cur = tail;
            while (cur != null) {
                if (cur.x == newX && cur.y == newY) {
                    return -1;
                }

                cur = cur.next;
            }

            boolean remainTail = foodIndex[0] == newX && foodIndex[1] == newY;
            Node newHead = new Node(head.x + move[0], head.y + move[1]);
            head.next = newHead;
            head = newHead;

            if (remainTail) {
                score++;
            } else {
                Node tailNext = tail.next;
                tail.next = null;
                tail = tailNext;
            }

            return score;
        }
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
