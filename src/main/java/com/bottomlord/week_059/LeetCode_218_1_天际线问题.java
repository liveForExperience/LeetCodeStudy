package com.bottomlord.week_059;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/8/19 8:21
 */
public class LeetCode_218_1_天际线问题 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int len = buildings.length;
        if (len == 0) {
            return new ArrayList<>();
        }

        if (len == 1) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Arrays.asList(buildings[0][0], buildings[0][2]));
            ans.add(Arrays.asList(buildings[0][1], 0));
            return ans;
        }

        List<List<Integer>> leftBuildings = getSkyline(Arrays.copyOfRange(buildings, 0, len / 2));
        List<List<Integer>> rightBuildings = getSkyline(Arrays.copyOfRange(buildings, len / 2, len));

        return merge(leftBuildings, rightBuildings);
    }

    private List<List<Integer>> merge(List<List<Integer>> leftBuildings, List<List<Integer>> rightBuildings) {
        int lLen = leftBuildings.size(), rLen = rightBuildings.size();
        int li = 0, ri = 0;
        int lh = 0, rh = 0;
        List<List<Integer>> output = new ArrayList<>();
        while (li < lLen && ri < rLen) {
            int lx = leftBuildings.get(li).get(0), rx = rightBuildings.get(ri).get(0);
            List<Integer> cp;
            if (lx < rx) {
                lh = leftBuildings.get(li).get(1);
                cp = Arrays.asList(lx, Math.max(lh, rh));
                li++;
            } else if (lx > rx) {
                rh = rightBuildings.get(ri).get(1);
                cp = Arrays.asList(rx, Math.max(lh, rh));
                ri++;
            } else {
                lh = leftBuildings.get(li).get(1);
                rh = rightBuildings.get(ri).get(1);
                cp = Arrays.asList(lx, Math.max(lh, rh));
                li++;
                ri++;
            }

            if (output.size() == 0 || !Objects.equals(output.get(output.size() - 1).get(1), cp.get(1))) {
                output.add(cp);
            }
        }

        while (li < lLen) {
            output.add(leftBuildings.get(li));
            li++;
        }

        while (ri < rLen) {
            output.add(rightBuildings.get(ri));
            ri++;
        }

        return output;
    }
}
