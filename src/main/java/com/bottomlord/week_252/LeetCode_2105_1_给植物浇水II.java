package com.bottomlord.week_252;

/**
 * @author chen yue
 * @date 2024-05-09 09:03:42
 */
public class LeetCode_2105_1_给植物浇水II {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int leftA = capacityA, leftB = capacityB, ia = 0, ib = plants.length - 1, back = 0;
        while (ia <= ib) {
            int needA = plants[ia], needB = plants[ib];

            if (ia == ib) {
                if (needA > leftA && needB > leftB) {
                    back++;
                }

                break;
            }

            if (needA > leftA) {
                back++;
                leftA = capacityA;
            }

            if (needB > leftB) {
                back++;
                leftB = capacityB;
            }

            leftA -= needA;
            leftB -= needB;
            ia++;
            ib--;
        }

        return back;
    }
}
