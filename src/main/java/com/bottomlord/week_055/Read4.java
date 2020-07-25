package com.bottomlord.week_055;

/**
 * @author ChenYue
 * @date 2020/7/24 8:49
 */
public class Read4 {
    String file;
    int pointer = 0;
    Read4(){}

    Read4(String input){
        this.file = input;
    }

    public int read4(char[] buf){
        int readCount = 0;
        for (int i = pointer; i < file.length(); i++){
            if (readCount == buf.length){
                return readCount;
            }
            buf[readCount] = file.charAt(i);
            readCount++;
            pointer++;
        }
        return readCount;
    }
}
