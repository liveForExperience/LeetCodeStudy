package com.bottomlord.contest_20191019;

public class Contest_4_分享巧克力 {
    public int maximizeSweetness(int[] sweetness, int K) {
        int head = 0, tail = (int)1e9 + 1;

        while (head + 1 < tail) {
            int mid = head + (tail - head) / 2;

            int count = 0, sum = 0;
            for (int i = 0; i < sweetness.length; i++) {
                sum += sweetness[i];

                if (sum >= mid) {
                    count++;
                    sum = 0;
                }
            }

            if (count >= K + 1) {
                head = mid;
            } else {
                tail = mid;
            }
        }

        return head;
    }
}
