package com.asa.billtracker.db;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import com.asa.billtracker.AppData;

/**
 * Created by Aaron on 11/18/13.
 */
public class BillContract {

    public BillContract() {
    }

    public static abstract class BillEntry implements BaseColumns {
        public static final String TABLE_NAME = "bills";

        public static final Uri CONTENT_URI = getUri(TABLE_NAME);
        public static final String CONTENT_TYPE = getContentListType(TABLE_NAME);
        public static final String CONTENT_TYPE_ITEM = getContentItemType(TABLE_NAME);

    }

    public static abstract class HouseEntry implements BaseColumns {
        public static final String TABLE_NAME = "house";

        public static final Uri CONTENT_URI = getUri(TABLE_NAME);
        public static final String CONTENT_TYPE = getContentListType(TABLE_NAME);
        public static final String CONTENT_TYPE_ITEM = getContentItemType(TABLE_NAME);

        public static final String ADDRESS = "address";
        public static final String NAME = "name";
    }

    private static Uri getUri(String tableName) {
        return Uri.parse("content://" + AppData.AUTHORITY + "/" + tableName);
    }

    private static String getContentListType(String name) {
        return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + "vnd." + AppData.AUTHORITY + "." + name;
    }

    private static String getContentItemType(String name) {
        return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + "vnd." + AppData.AUTHORITY + "." + name;
    }
}
