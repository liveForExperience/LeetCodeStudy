package com.bottomlord.week_095;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/5/8 8:31
 */
public class LeetCode_1723_1_完成所有工作的最短时间 {
    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        int sum = 0;
        for (int i = 0; i < jobs.length / 2; i++) {
            int tmp = jobs[i];
            jobs[i] = jobs[jobs.length - 1 - i];
            jobs[jobs.length - 1 - i] = tmp;

            sum += jobs[i] + jobs[jobs.length - 1 - i];
        }

        if (jobs.length % 2 == 1) {
            sum += jobs[jobs.length / 2];
        }

        int l = jobs[jobs.length - 1], r = sum;
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (backTrack(jobs, new int[k], 0, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return r;
    }

    private boolean backTrack(int[] jobs, int[] workloads, int index, int limit) {
        if (index >= jobs.length) {
            return true;
        }

        int workload = jobs[index];

        for (int i = 0; i < workloads.length; i++) {
            if (workloads[i] + jobs[index] <= limit) {
                workloads[i] += workload;
                if (backTrack(jobs, workloads, index + 1, limit)) {
                    return true;
                }
                workloads[i] -= workload;
            }

            if (workloads[i] == 0 || workloads[i] + workload == limit) {
                break;
            }
        }

        return false;
    }
}
