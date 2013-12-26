package com.asa.billtracker;

/**
 * Created by Aaron on 9/27/13.
 */
public class AppData {

    public static final String PARSE_APP_ID = "n4rA4Aopy0DVH2rJ9flrGlR2rk9GDA0b1HuzVcsO";
    public static final String PARSE_CLIENT_ID = "tZQEHeafIG4M0xhPsvVL3P2SXUHVfo0p9SfMUUwl";
    //    public static final String PARSE_MASTER_KEY = "";
    public static final String INTENT_BILL_ADDED = "com.asa.billtracker.INTENT_BILL_ADDED";

    // The two different different house types: join/add
    public static final int HOUSE_TYPE_JOIN = 100;
    public static final int HOUSE_TYPE_ADD = 101;

    // the different types of ways to enter the house.
    public static final int HOUSE_FROM_REG = 192;
    public static final int HOUSE_FROM_MAIN = 193;

    public static class ActivityResult {
        public static final int ADD_BILL = 100;
    }

    public static class ParseTables {
        public static final String BILLS = "bills";
        public static final String USERS = "users";
        public static final String CATEGORY = "category";
        public static final String HOUSE = "house";
    }

    public static class Extras {
        // Deals with the houses
        public static final String HOUSE_TYPE = "house_type";
        public static final String HOUSE_FROM = "house_from";
    }

}
