package com.bigw.tt.foundation.basicadapter;

/**
 * Created by bigw on 19/12/2017.
 */

public class StringItemGenerator {

    private static int COUNT = 0;

    public static String next() {
        return "string:" + COUNT++;
    }
}
