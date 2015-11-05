package com.matthewmckay.rahtzee;

/**
 * Created by matthewmckay on 11/1/15.
 */
public class CurrentUser {
    private static int id = 1;

    public static void setId(int _id) {
        id = _id;
    }

    public static int getId() {
        return id;
    }
}
