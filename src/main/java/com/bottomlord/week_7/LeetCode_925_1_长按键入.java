package com.bottomlord.week_7;

public class LeetCode_925_1_长按键入 {
    public boolean isLongPressedName(String name, String typed) {
        int ni = 0, ti = 0;
        while (ni < name.length() && ti < typed.length()) {
            if (name.charAt(ni) != typed.charAt(ti)) {
                while (typed.charAt(ti - 1) == typed.charAt(ti)) {
                    ti++;
                }

                if (typed.charAt(ti) != name.charAt(ni)) {
                    return false;
                }
            }

            ni++;
            ti++;
        }

        for (int i = ti; i < typed.length(); i++) {
            if (typed.charAt(i - 1) != typed.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LeetCode_925_1_长按键入 test = new LeetCode_925_1_长按键入();
        test.isLongPressedName("kikcxmvzi",
                "kiikcxxmmvvzz");
    }
}
