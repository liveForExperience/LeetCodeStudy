package com.bottomlord.week_105;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/7/13 8:20
 */
public class LeetCode_218_1_天际线问题 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return Collections.emptyList();
        }

        if (buildings.length == 1) {
            return Arrays.asList(
                    Arrays.asList(buildings[0][0], buildings[0][2]),
                    Arrays.asList(buildings[0][1], 0)
            );
        }

        List<List<Integer>> leftBuildings = getSkyline(Arrays.copyOfRange(buildings, 0, buildings.length / 2)),
                            rightBuildings = getSkyline(Arrays.copyOfRange(buildings, buildings.length / 2, buildings.length));

        return merge(leftBuildings, rightBuildings);
    }

    private List<List<Integer>> merge(List<List<Integer>> leftBuildings, List<List<Integer>> rightBuildings) {
        int lh = 0, rh = 0, li = 0, ri = 0, lLen = leftBuildings.size(), rLen = rightBuildings.size();
        List<List<Integer>> output = new ArrayList<>();
        while (li < lLen && ri < rLen) {
            List<Integer> left = leftBuildings.get(li), right = rightBuildings.get(ri);
            int lx = left.get(0), rx = right.get(0);
            List<Integer> list;
            if (lx < rx) {
                lh = left.get(1);
                li++;
                list = Arrays.asList(lx, Math.max(lh, rh));
            } else if (lx > rx) {
                rh = right.get(1);
                ri++;
                list = Arrays.asList(rx, Math.max(lh, rh));
            } else {
                li++;
                ri++;
                lh = left.get(1);
                rh = right.get(1);
                list = Arrays.asList(lx, Math.max(lh, rh));
            }

            if (output.size() == 0 || !Objects.equals(output.get(output.size() - 1).get(1), list.get(1))) {
                output.add(list);
            }
        }

        while (li < lLen) {
            output.add(leftBuildings.get(li++));
        }

        while (ri < rLen) {
            output.add(rightBuildings.get(ri++));
        }

        return output;
    }
}
