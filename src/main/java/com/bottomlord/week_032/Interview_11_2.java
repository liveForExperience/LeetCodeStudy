package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/16 13:44
 */
public class Interview_11_2 {
    public int minArray(int[] numbers) {
        int head = 0, tail = numbers.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (numbers[mid] > numbers[tail]) {
                head = mid + 1;
            } else if (numbers[mid] < numbers[tail]) {
                tail = mid;
            } else {
                tail--;
            }
        }

        return numbers[head];
    }
}
