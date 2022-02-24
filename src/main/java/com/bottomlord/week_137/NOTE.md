# [LeetCode_838_推多米诺](https://leetcode-cn.com/problems/push-dominoes/)
## 解法
### 思路
- 通过观察可以发现，当骨牌是推倒的状态时，不受任何力的作用，所以反过来也可以理解为，只有站立的骨牌才会受到力的影响从而改变状态
- 所以一个站立的骨牌也就只其最近的两边有状态的骨牌的影响。例如`R....L`这样的组合，R和L中间的骨牌就会收到这两个边界骨牌的影响，这样的组合可以分成3种
  - 边界方向一样的：`R.....R`，`L.....L`，这种的效果就是站立的骨牌方向和左侧或右侧边界一致
  - `R.....L`：站立的骨牌就是`RRR.LLL`或者`RRRLLL`这种情况
  - `L.....R`：这种情况，站立的骨牌不受影响，仍然是站立的
- 考虑到一个组合的边界必须是有方向的，但是有些用例中的边界没有方向，所以可以人为的添加虚拟的方向，即`L` + dominoes + `R`
- 有了虚拟的骨牌，在记录的时候要注意将这两个虚拟的剔除掉
### 代码
```java
class Solution {
    public String pushDominoes(String dominoes) {
        dominoes = "L" + dominoes + "R";
        int len = dominoes.length();

        int l = 0;
        StringBuilder sb = new StringBuilder();
        for (int r = 1; r < len; r++) {
            if (dominoes.charAt(r) == '.') {
                continue;
            }

            if (l != 0) {
                sb.append(dominoes.charAt(l));
            }

            int dis = r - l - 1;
            if (dominoes.charAt(l) == dominoes.charAt(r)) {
                for (int i = 0; i < dis; i++) {
                    sb.append(dominoes.charAt(l));
                }
            } else if (dominoes.charAt(l) == 'L' && dominoes.charAt(r) == 'R') {
                for (int i = 0; i < dis; i++) {
                    sb.append(".");
                }
            } else {
                for (int i = 0; i < dis / 2; i++) {
                    sb.append("R");
                }

                if (dis % 2 == 1) {
                    sb.append(".");
                }

                for (int i = 0; i < dis / 2; i++) {
                    sb.append('L');
                }
            }

            l = r;
        }

        return sb.toString();
    }
}
```
## 解法二
### 思路
bfs
- 首先和解法一一样，需要确定，力只会对站立的骨牌产生影响，同时，如果站立的骨牌收到2个方向的影响，那么这个骨牌不会倒下
- 因为骨牌的倒下是具有传播性质的，所以需要通过时间序列来存储力的产生过程
- 这个时间序列会基于bfs的特性，确定每一轮产生的力对当前波及到的，站立的骨牌是否会产生影响
- 而波及的力，则通过另一个数组来存储，且通过这个数组，可以记录当前这个坐标的骨牌在当前这一轮次中收到了1个还是2个力的影响
  - 如果是1个力，那么这个骨牌就会朝这个力的方向倒下
  - 如果是2个力，那么这个骨牌就不会倒下
### 代码
```java
class Solution {
    public String pushDominoes(String dominoes) {
        int len = dominoes.length();
        List<Character>[] forces = new ArrayList[len];
        int[] times = new int[len];
        Arrays.fill(times, -1);
        for (int i = 0; i < len; i++) {
            forces[i] = new ArrayList<>();
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char force = dominoes.charAt(i);
            if (force != '.') {
                queue.offer(i);
                times[i] = 0;
                forces[i].add(force);
            }
        }
        
        char[] cs = new char[len];
        Arrays.fill(cs, '.');
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (forces[index].size() == 1) {
                char force = forces[index].get(0);
                cs[index] = force;
                int nextIndex = force == 'L' ? index - 1 : index + 1;
                if (nextIndex >= 0 && nextIndex < len) {
                    int time = times[index];
                    if (times[nextIndex] == -1) {
                        queue.offer(nextIndex);
                        times[nextIndex] = time + 1;
                        forces[nextIndex].add(force);
                    } else if (times[nextIndex] == time + 1) {
                        forces[nextIndex].add(force);
                    }
                }
            }
        }
        
        return new String(cs);
    }
}
```
# [LeetCode_1994_好子集的数目](https://leetcode-cn.com/problems/the-number-of-good-subsets/)
## 解法
### 思路
观察然后dfs
- 格子被左或右分割成上下两部分，球在上下两部分的行为是不同的
- 同时根据当前在上还是在下，朝左还是朝右，之前是朝左还是朝有，在上还是在下，回去分成多种情况需要判断
### 代码
```java
class Solution {
    public int[] findBall(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int[] ans = new int[c];
        for (int i = 0; i < c; i++) {
            ans[i] = dfs(0, i, r, c, grid, true, -1, 0);
        }
        
        return ans;
    }

    private int dfs(int x, int y, int r, int c, int[][] grid, boolean upper, int preDirIndex, int pre) {
        if (y < 0 || y >= c) {
            return -1;
        }
        
        int direction = grid[x][y];
        if (preDirIndex != 2 && pre + direction == 0) {
            return -1;
        }

        if (x == r - 1 && !upper) {
            return y;
        }

        if (direction == 1) {
            if (upper) {
                return dfs(x, y + 1, r, c, grid, false, 1, direction);
            } else {
                return dfs(x + 1, y, r, c, grid, true, 2, direction);
            }
        } else {
            if (upper) {
                return dfs(x, y - 1, r, c, grid, false, 0, direction);
            } else {
                return dfs(x + 1, y, r, c, grid, true, 2, direction);
            }
        }
    }
}
```