package com.bottomlord.contest_20220612;


import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-06-12 11:04:57
 */
public class Contest_3_1_公平分发饼干 {
    public int distributeCookies(int[] cookies, int k) {
        Arrays.sort(cookies);
        int sum = 0;
        for (int i = 0; i < cookies.length / 2; i++) {
            int tmp = cookies[i];
            cookies[i] = cookies[cookies.length - 1 - i];
            cookies[cookies.length - 1 - i] = tmp;

            sum += cookies[i] + cookies[cookies.length - 1 - i];
        }

        if (cookies.length % 2 == 1) {
            sum += cookies[cookies.length / 2];
        }

        int l = cookies[cookies.length - 1], r = sum;
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (backTrack(cookies, new int[k], 0, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return r;
    }

    private boolean backTrack(int[] cookies, int[] stores, int index, int limit) {
        if (index >= cookies.length) {
            return true;
        }

        int workload = cookies[index];

        for (int i = 0; i < stores.length; i++) {
            if (stores[i] + cookies[index] <= limit) {
                stores[i] += workload;
                if (backTrack(cookies, stores, index + 1, limit)) {
                    return true;
                }
                stores[i] -= workload;
            }
        }

        return false;
    }
}
