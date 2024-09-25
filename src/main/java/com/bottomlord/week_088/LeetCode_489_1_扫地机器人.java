package com.bottomlord.week_088;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/3/16 8:24
 */
public class LeetCode_489_1_扫地机器人 {
    private final int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
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
