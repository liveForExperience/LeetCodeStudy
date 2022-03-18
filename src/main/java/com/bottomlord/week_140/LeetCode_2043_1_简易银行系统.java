package com.bottomlord.week_140;

/**
 * @author chen yue
 * @date 2022-03-18 21:58:51
 */
public class LeetCode_2043_1_简易银行系统 {
    class Bank {
        private long[] balance;
        public Bank(long[] balance) {
            this.balance = balance;
        }

        public boolean transfer(int account1, int account2, long money) {
            if (balance[account1 - 1] >= money) {
                balance[account1 - 1] -= money;
                balance[account2 - 1] += money;
                return true;
            } else {
                return false;
            }
        }

        public boolean deposit(int account, long money) {
            balance[account - 1] += money;
            return true;
        }

        public boolean withdraw(int account, long money) {
            if (balance[account - 1] < money) {
                return false;
            }

            balance[account - 1] -= money;
            return true;
        }
    }
}
