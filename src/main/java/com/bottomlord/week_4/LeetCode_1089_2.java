package com.bottomlord.week_4;

/**
 * @author LiveForExperience
 * @date 2019/8/3 13:48
 */
public class LeetCode_1089_2 {
    public void duplicateZeros(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        boolean one = false;
        int fast = 0, slow = 0;
        for (; fast < arr.length; slow++, fast++) {
            if (arr[slow] == 0) {
                fast++;
            }

            if (fast++ >= arr.length) {
                one = true;
                break;
            }
        }

        slow--;
        fast = arr.length - 1;

        if (fast == slow) {
            return;
        }

        for (; slow >= 0; slow--) {
            if (arr[slow] == 0) {
                if (one) {
                    one = false;
                } else {
                    arr[fast--] = 0;
                }
            }

            if (fast <= 0) {
                return;
            }

            arr[fast--] = arr[slow];
        }
    }

    public static void main(String[] args) {
        LeetCode_1089_2 test = new LeetCode_1089_2();
        test.duplicateZeros(new int[]{8,4,5,0,0,0,0,7});
    }
}