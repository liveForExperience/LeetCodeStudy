package com.bottomlord.week_039;

import java.util.List;

/**
 * @author ChenYue
 * @date 2020/3/31 8:47
 */
public class Interview_0806_1_汉诺塔问题 {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(), A, B, C);
    }

    private void move(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n == 1) {
            C.add(A.remove(A.size() - 1));
            return;
        }

        move(n - 1, A, C, B);
        C.add(A.remove(A.size() - 1));
        move(n - 1, B, A, C);
    }
}
