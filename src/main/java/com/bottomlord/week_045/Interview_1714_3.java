package com.bottomlord.week_045;

/**
 * @author ChenYue
 * @date 2020/5/11 8:32
 */
public class Interview_1714_3 {
    public int[] smallestK(int[] arr, int k) {
        int head = 0, tail = arr.length - 1;
        while (head < tail) {
            int pivot = partition(arr, head, tail);
            if (pivot == k - 1) {
                break;
            } else if (pivot < k - 1) {
                head = pivot + 1;
            } else {
                tail = pivot - 1;
            }
        }

        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }

    private int partition(int[] arr, int head, int tail) {
        int low = arr[head];
        while (head < tail) {
            while (head < tail && arr[tail] > low) {
                tail--;
            }
            arr[head] = arr[tail];

            while (head < tail && arr[head] < low) {
                head++;
            }
            arr[tail] = arr[head];
        }
        return head;
    }
}
