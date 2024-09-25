package com.bottomlord;

import java.util.List;

public interface INestedInteger {
    boolean isInteger();

    Integer getInteger();

    void setInteger(int value);

    void add(NestedInteger ni);

    List<NestedInteger> getList();
}
