package com.bottomlord.week_038;

/**
 * @author ChenYue
 * @date 2020/3/28 21:05
 */
public class Interview_0507_1_配对交换 {
    public int exchangeBits(int num) {
        return ((num & 0x55555555) << 1) | ((num & 0xaaaaaaaa) >>> 1);
    }
}
