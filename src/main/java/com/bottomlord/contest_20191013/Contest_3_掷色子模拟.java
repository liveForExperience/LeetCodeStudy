package com.bottomlord.contest_20191013;

public class Contest_3_掷色子模拟 {
    private long sum;

    public int dieSimulator(int n, int[] rollMax) {
        recurse(n, -1, new int[6], rollMax);
        return (int) (sum % 1000000007L);
    }

    private void recurse(int level, int pre, int[] record, int[] rollMax) {
        if (level == 0) {
            sum++;
            return;
        }

        for (int i = 0; i < 6; i++) {
            if (pre == i) {
                if (record[i] == 0) {
                    int[] tmp = new int[6];
                    tmp[i]++;
                    recurse(level - 1, i, tmp, rollMax);
                } else {
                    if (rollMax[i] != record[i]) {
                        record[i]++;
                        recurse(level - 1, i, record, rollMax);
                    }
                }
            } else {
                int[] tmp = new int[6];
                tmp[i]++;
                recurse(level - 1, i, tmp, rollMax);
            }
        }
    }

    public static void main(String[] args) {
        Contest_3_掷色子模拟 t = new Contest_3_掷色子模拟();
        t.dieSimulator(3, new int[]{1, 1, 1, 1, 1, 1});
    }
}