package com.bottomlord.week_101;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/6/21 8:17
 */
public class LeetCode_65_1_有效数字 {
    public boolean isNumber(String s) {
        Map<State, Map<CharType, State>> transfer = new HashMap<>();
        Map<CharType, State> stateInitialMap = new HashMap<>();
        stateInitialMap.put(CharType.NUMBER, State.STATE_INT);
        stateInitialMap.put(CharType.SIGN, State.STATE_INT_SIGN);
        stateInitialMap.put(CharType.POINT, State.STATE_POINT_WITHOUT_INT);
        transfer.put(State.STATE_INITIAL, stateInitialMap);

        Map<CharType, State> stateIntMap = new HashMap<>();
        stateIntMap.put(CharType.NUMBER, State.STATE_INT);
        stateIntMap.put(CharType.POINT, State.STATE_POINT);
        stateIntMap.put(CharType.EXPRESSION, State.STATE_EXPRESSION);
        transfer.put(State.STATE_INT, stateIntMap);

        Map<CharType, State> stateIntSignMap = new HashMap<>();
        stateIntSignMap.put(CharType.NUMBER, State.STATE_INT);
        stateIntSignMap.put(CharType.POINT, State.STATE_POINT_WITHOUT_INT);
        transfer.put(State.STATE_INT_SIGN, stateIntSignMap);

        Map<CharType, State> statePointWithoutIntMap = new HashMap<>();
        statePointWithoutIntMap.put(CharType.NUMBER, State.STATE_FRACTION);
        transfer.put(State.STATE_POINT_WITHOUT_INT, statePointWithoutIntMap);

        Map<CharType, State> statePointMap = new HashMap<>();
        statePointMap.put(CharType.NUMBER, State.STATE_FRACTION);
        statePointMap.put(CharType.EXPRESSION, State.STATE_EXPRESSION);
        transfer.put(State.STATE_POINT, statePointMap);

        Map<CharType, State> stateExpressionMap = new HashMap<>();
        stateExpressionMap.put(CharType.NUMBER, State.STATE_EXPRESSION_NUMBER);
        stateExpressionMap.put(CharType.SIGN, State.STATE_EXPRESSION_SIGN);
        transfer.put(State.STATE_EXPRESSION, stateExpressionMap);

        Map<CharType, State> stateExpressionNumMap = new HashMap<>();
        stateExpressionNumMap.put(CharType.NUMBER, State.STATE_EXPRESSION_NUMBER);
        transfer.put(State.STATE_EXPRESSION_NUMBER, stateExpressionNumMap);

        Map<CharType, State> stateExpressionSignMap = new HashMap<>();
        stateExpressionSignMap.put(CharType.NUMBER, State.STATE_EXPRESSION_NUMBER);
        transfer.put(State.STATE_EXPRESSION_SIGN, stateExpressionSignMap);

        Map<CharType, State> stateFractionMap = new HashMap<>();
        stateFractionMap.put(CharType.NUMBER, State.STATE_FRACTION);
        stateFractionMap.put(CharType.EXPRESSION, State.STATE_EXPRESSION);
        transfer.put(State.STATE_FRACTION, stateFractionMap);

        int len = s.length();
        State state = State.STATE_INITIAL;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            CharType charType = toCharType(c);

            Map<CharType, State> charTypeStateMap = transfer.get(state);
            if (!charTypeStateMap.containsKey(charType)) {
                return false;
            }

            state = charTypeStateMap.get(charType);
        }

        return state == State.STATE_INT || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXPRESSION_NUMBER || state == State.STATE_END;
    }

    private CharType toCharType(char c) {
        if (c <= '9' && c >= '0') {
            return CharType.NUMBER;
        } else if (c == '+' || c == '-') {
            return CharType.SIGN;
        } else if (c == 'e' || c == 'E') {
            return CharType.EXPRESSION;
        } else if (c == '.') {
            return CharType.POINT;
        } else {
            return CharType.ILLEGAL;
        }
    }

    enum State {
        /**
         * 状态
         */
        STATE_INITIAL,
        STATE_INT,
        STATE_INT_SIGN,
        STATE_POINT,
        STATE_POINT_WITHOUT_INT,
        STATE_FRACTION,
        STATE_EXPRESSION,
        STATE_EXPRESSION_SIGN,
        STATE_EXPRESSION_NUMBER,
        STATE_END;
    }

    enum CharType {
        /**
         * 字符串类型
         */
        NUMBER,
        SIGN,
        EXPRESSION,
        POINT,
        ILLEGAL;
    }
}
