package com.mashibing.strategy;

import java.util.Comparator;

public class CatHeightComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        return Integer.compare(o1.getHeight(), o2.getHeight());
    }
}
