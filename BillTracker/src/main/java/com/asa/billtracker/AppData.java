package com.asa.billtracker;

/**
 * Created by Aaron on 9/27/13.
 */
public class AppData {

    public static final String PARSE_APP_ID = "n4rA4Aopy0DVH2rJ9flrGlR2rk9GDA0b1HuzVcsO";
    public static final String PARSE_CLIENT_ID = "tZQEHeafIG4M0xhPsvVL3P2SXUHVfo0p9SfMUUwl";
    //    public static final String PARSE_MASTER_KEY = "";
    public static final String INTENT_BILL_ADDED = "com.asa.billtracker.INTENT_BILL_ADDED";

    public static class ActivityResult {
        public static final int ADD_BILL = 100;
    }

    public static class ParseTables {
        public static final String BILLS = "bills";
        public static final String USERS = "users";
        public static final String CATEGORY = "category";
        public static final String HOUSE = "house";
    }

}
