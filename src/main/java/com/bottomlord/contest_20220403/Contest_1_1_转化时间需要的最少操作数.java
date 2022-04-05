package com.bottomlord.contest_20220403;

/**
 * @author chen yue
 * @date 2022-04-03 10:30:22
 */
public class Contest_1_1_转化时间需要的最少操作数 {
    public int convertTime(String current, String correct) {
        String curHour = current.split(":")[0], curMin = current.split(":")[1];
        String correctHour = correct.split(":")[0], correctMin = correct.split(":")[1];

        int hour = Integer.parseInt(correctHour) - Integer.parseInt(curHour);
        int corMinInt = Integer.parseInt(correctMin), curMinInt = Integer.parseInt(curMin);

        if (corMinInt < curMinInt) {
            corMinInt += 60;
            hour--;
        }

        int sumMin = corMinInt - curMinInt;

        int min = 0;
        min += sumMin / 15;
        sumMin %= 15;

        min += sumMin / 5;
        sumMin %= 5;

        return hour + min + sumMin;
    }
}
