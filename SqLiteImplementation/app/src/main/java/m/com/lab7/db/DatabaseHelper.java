package m.com.lab7.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import m.com.lab7.bo.BookBO;

/**
 * Created by Bhavin on 09-Apr-18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


        //Constants for db name and version
        public static final String DATABASE_NAME = "books.db";
        public static final int DATABASE_VERSION = 6;

        public static final String TABLE_BOOKS = "books";

        //Create Table
        private final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_BOOKS + " (" +
                        BookBO.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "book TEXT, " +
                        "author TEXT, " +
                        "bookrating TEXT )";
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_BOOKS);
            onCreate(sqLiteDatabase);
        }

}
