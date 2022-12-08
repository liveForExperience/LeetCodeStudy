package com.bottomlord.week_178;

/**
 * @author chen yue
 * @date 2022-12-08 15:05:17
 */
public class LeetCode_2409_1_统计共同度过的日子数 {
    private final int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] sums = new int[13];
        for (int i = 1; i <= arr.length; i++) {
            sums[i] = sums[i - 1] + arr[i - 1];
        }

        String rod = "-";
        String[] arriveAliceStrs = arriveAlice.split(rod), leaveAliceStrs = leaveAlice.split(rod),
                arriveBobStrs = arriveBob.split(rod), leaveBobStrs = leaveBob.split(rod);
        int aam = Integer.parseInt(arriveAliceStrs[0]), aad = Integer.parseInt(arriveAliceStrs[1]),
            lam = Integer.parseInt(leaveAliceStrs[0]), lad = Integer.parseInt(leaveAliceStrs[1]),
            abm = Integer.parseInt(arriveBobStrs[0]), abd = Integer.parseInt(arriveBobStrs[1]),
            lbm = Integer.parseInt(leaveBobStrs[0]), lbd = Integer.parseInt(leaveBobStrs[1]);

        int day = Math.min(sums[lam - 1] + lad, sums[lbm - 1] + lbd) - Math.max(sums[aam - 1] + aad, sums[abm - 1] + abd);

        return day < 0 ? 0 : day + 1;
    }
}
