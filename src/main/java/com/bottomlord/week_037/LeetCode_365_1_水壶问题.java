package com.bottomlord.week_037;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/3/21 14:01
 */
public class LeetCode_365_1_水壶问题 {
    public boolean canMeasureWater(int x, int y, int z) {
        if (z == 0) {
            return true;
        }

        if (x + y < z) {
            return false;
        }

        Queue<State> queue = new ArrayDeque<>();
        Set<State> memo = new HashSet<>();

        State init = new State(0, 0);
        queue.offer(init);
        memo.add(init);

        while (!queue.isEmpty()) {
            int count = queue.size();

            while (count-- != 0) {
                State state = queue.poll();

                if (state == null) {
                    continue;
                }

                int curX = state.x, curY = state.y;
                if (curX == z || curY == z || curX + curY == z) {
                    return true;
                }

                for (State next : getNextStates(x, y, curX, curY)) {
                    if (!memo.contains(next)) {
                        queue.offer(next);
                        memo.add(next);
                    }
                }
            }
        }

        return false;
    }

    private List<State> getNextStates(int x, int y, int curX, int curY) {
        List<State> nextStates = new ArrayList<>();

        State fullX = new State(x, curY);
        State fullY = new State(curX, y);
        State emptyX = new State(0, curY);
        State emptyY = new State(curX, 0);
        State x2yLeft = new State(curX - (y - curY), y);
        State y2xLeft = new State(x, curY - (x - curX));
        State x2yNoLeft = new State(0, curX + curY);
        State y2xNotLeft = new State(curX + curY, 0);

        if (curX < x) {
            nextStates.add(fullX);
        }

        if (curY < y) {
            nextStates.add(fullY);
        }

        if (curX > 0) {
            nextStates.add(emptyX);
        }

        if (curY > 0) {
            nextStates.add(emptyY);
        }

        if (curX - (y - curY) > 0) {
            nextStates.add(x2yLeft);
        }

        if (curY - (x - curX) > 0) {
            nextStates.add(y2xLeft);
        }

        if (curX + curY < y) {
            nextStates.add(x2yNoLeft);
        }

        if (curX + curY < x) {
            nextStates.add(y2xNotLeft);
        }

        return nextStates;
    }

    private class State {
        private int x;
        private int y;

        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            State state = (State) o;
            return x == state.x &&
                    y == state.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
