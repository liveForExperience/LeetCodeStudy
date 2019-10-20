package com.bottomlord.contest_20191019;

import java.util.ArrayList;
import java.util.List;

public class Contest_3_抛掷硬币 {
    public double probabilityOfHeads(double[] prob, int target) {
        List<Double> list = new ArrayList<>();
        dfs(prob, 0, target, 0, 1, list);
        double ans = 0.0;
        for (double num : list) {
            ans += num;
        }
        return ans;
    }

    private void dfs(double[] prob, int index, int target, int count, double pro, List<Double> list) {
        if (target == count && index >= prob.length) {
            list.add(pro);
            return;
        }

        if (target > count) {
            dfs(prob, index + 1, target, count + 1, pro * prob[index], list);
            dfs(prob, index + 1, target, count + 1, pro * (1 - prob[index]), list);
        } else {
            for (int i = index; i < prob.length; i++) {
                pro *= prob[i];
            }
            list.add(pro);
        }
    }
}
