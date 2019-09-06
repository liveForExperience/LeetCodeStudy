package com.bottomlord.week_009;

import java.util.*;

public class LeetCode_1169_2 {
    public List<String> invalidTransactions(String[] transactions) {
        List<String> ans = new ArrayList<>();
        Map<String, List<Transaction2>> map = new HashMap<>();
        for (String transactionStr : transactions) {
            Transaction2 transaction = new Transaction2(transactionStr);

            if (map.containsKey(transaction.name)) {
                map.get(transaction.name).add(transaction);
            } else {
                List<Transaction2> list = new ArrayList<>();
                list.add(transaction);
                map.put(transaction.name, list);
            }
        }

        for (List<Transaction2> list : map.values()) {
            for (int i = 0; i < list.size(); i++) {
                Transaction2 a = list.get(i);
                if (a.amount > 1000) {
                    ans.add(a.raw);
                } else {
                    for (Transaction2 b : list) {
                        if (Math.abs(a.time - b.time) <= 60 && !a.city.equals(b.city)) {
                            ans.add(a.raw);
                            break;
                        }
                    }
                }
            }
        }

        return ans;
    }

    class Transaction2 {
        private String name;
        private Integer time;
        private Integer amount;
        private String city;
        private String raw;

        Transaction2(String transcation) {
            String[] factors = transcation.split(",");
            this.name = factors[0];
            this.time = Integer.parseInt(factors[1]);
            this.amount = Integer.parseInt(factors[2]);
            this.city = factors[3];
            this.raw = transcation;
        }
    }
}