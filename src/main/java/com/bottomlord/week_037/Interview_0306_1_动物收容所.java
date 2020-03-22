package com.bottomlord.week_037;

import java.util.LinkedList;

/**
 * @author ThinkPad
 * @date 2020/3/22 20:36
 */
public class Interview_0306_1_动物收容所 {
    class AnimalShelf {
        private LinkedList<int[]> dogs;
        private LinkedList<int[]> cats;
        private LinkedList<int[]> list;

        public AnimalShelf() {
            dogs = new LinkedList<>();
            cats = new LinkedList<>();
            list = new LinkedList<>();
        }

        public void enqueue(int[] animal) {
            list.add(animal);
            if (animal[1] == 0) {
                cats.add(animal);
            } else {
                dogs.add(animal);
            }
        }

        public int[] dequeueAny() {
            if (list.size() == 0) {
                return new int[]{-1, -1};
            }

            int[] arr = list.removeLast();
            if (arr[1] == 0) {
                cats.remove(arr);
            } else {
                dogs.remove(arr);
            }

            return arr;
        }

        public int[] dequeueDog() {
            if (dogs.size() == 0) {
                return new int[]{-1, -1};
            }

            int[] arr = dogs.removeLast();
            list.remove(arr);
            return arr;
        }

        public int[] dequeueCat() {
            if (cats.size() == 0) {
                return new int[]{-1, -1};
            }

            int[] arr = cats.removeLast();
            list.remove(arr);
            return arr;
        }
    }
}
