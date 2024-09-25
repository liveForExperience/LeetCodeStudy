package com.bottomlord.week_088;

/**
 * @author ChenYue
 * @date 2021/3/19 8:15
 */
public class LeetCode_1603_1_设计停车系统 {
    class ParkingSystem {
        private int big, medium, small, bigSize, mediumSize, smallSize;
        public ParkingSystem(int big, int medium, int small) {
            this.big = big;
            this.medium = medium;
            this.small = small;
        }

        public boolean addCar(int carType) {
            if (carType == 1 && bigSize < big) {
                bigSize++;
                return true;
            } else if (carType == 2 && mediumSize < medium) {
                mediumSize++;
                return true;
            } else if (carType == 3 && smallSize < small) {
                smallSize++;
                return true;
            }

            return false;
        }
    }
}
