package com.mashibing.strategy;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Cat[] a = {new Cat(8, 8), new Cat(5, 5), new Cat(9, 9)};
        Sorter<Cat> sorter = new Sorter<>();
        sorter.sort(a, new CatHeightComparator());
        System.out.println(Arrays.toString(a));
    }

}
