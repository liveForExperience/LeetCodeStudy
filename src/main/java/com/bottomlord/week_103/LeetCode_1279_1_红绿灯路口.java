package com.bottomlord.week_103;

/**
 * @author ChenYue
 * @date 2021/7/2 8:43
 */
public class LeetCode_1279_1_红绿灯路口 {
    class TrafficLight {
        private boolean aIsGreen;
        public TrafficLight() {
            this.aIsGreen = true;
        }

        public void carArrived(
                int carId,           // ID of the car
                int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
                int direction,       // Direction of the car
                Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
                Runnable crossCar    // Use crossCar.run() to make car cross the intersection
        ) {
            doCarArrived(roadId, turnGreen, crossCar);
        }

        private synchronized void doCarArrived(
                int roadId,
                Runnable turnGreen,
                Runnable crossCar
        ) {
            if ((roadId != 1) == aIsGreen) {
                turnGreen.run();
                aIsGreen = !aIsGreen;
            }
            crossCar.run();
        }
    }
}
