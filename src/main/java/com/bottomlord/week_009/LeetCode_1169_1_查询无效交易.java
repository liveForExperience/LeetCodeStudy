package com.bottomlord.week_009;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_1169_1_查询无效交易 {
    public List<String> invalidTransactions(String[] transactions) {
        List<String> ans = new ArrayList<>();
        Map<String, List<Transaction1>> map = new HashMap<>();
        for (String transactionStr : transactions) {
            Transaction1 transaction = new Transaction1(transactionStr);
            if (transaction.amount > 1000) {
                transaction.valid = false;
            }

            if (map.containsKey(transaction.name)) {
                map.get(transaction.name).add(transaction);
            } else {
                List<Transaction1> list = new ArrayList<>();
                list.add(transaction);
                map.put(transaction.name, list);
            }
        }

        for (List<Transaction1> list : map.values()) {
            if (list.size() >= 2) {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if (i == j) {
                            continue;
                        }

                        Transaction1 a = list.get(i);
                        Transaction1 b = list.get(j);

                        if (Math.abs(a.time - b.time) <= 60 && !a.city.equals(b.city)) {
                            a.valid = false;
                            b.valid = false;
                        }
                    }
                }
            }
        }

        for (List<Transaction1> list : map.values()) {
            for (Transaction1 t : list) {
                if (!t.valid) {
                    ans.add(t.toString());
                }
            }
        }

        return ans;
    }

    class Transaction1 {
        private String name;
        private Integer time;
        private Integer amount;
        private String city;
        private boolean valid;

        Transaction1(String transcation) {
            String[] factors = transcation.split(",");
            this.name = factors[0];
            this.time = Integer.parseInt(factors[1]);
            this.amount = Integer.parseInt(factors[2]);
            this.city = factors[3];
            this.valid = true;
        }

        public String toString() {
            return name + "," + time + "," + amount + "," + city;
        }
    }
}