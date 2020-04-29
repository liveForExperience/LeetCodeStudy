package com.bottomlord.week_043;

/**
 * @author ChenYue
 * @date 2020/4/29 8:16
 */
public class LeetCode_1095_1_山脉数组中查找目标值 {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length(), head = 0, tail = len - 1, peak = -1;

        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            int pre = mountainArr.get(mid - 1);
            int cur = mountainArr.get(mid);
            int next = mountainArr.get(mid + 1);

            if (pre < cur && cur > next) {
                peak = mid;
                break;
            } else if (cur < next){
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        int index = getIndex(0, peak, mountainArr, target);

        return index == -1 ? getIndex(peak, len - 1, mountainArr, target) : index;
    }

    private int getIndex(int head, int tail, MountainArray mountainArr, int target) {
        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            int cur = mountainArr.get(mid);

            if (cur == target) {
                return mid;
            } else if (cur < target){
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return -1;
    }
}
